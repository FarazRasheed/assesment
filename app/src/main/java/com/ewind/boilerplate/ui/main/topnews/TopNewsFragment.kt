package com.ewind.boilerplate.ui.main.topnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.elegantmedia.dod.util.ext.showToast
import com.ewind.boilerplate.R
import com.ewind.boilerplate.domain.model.DArticles
import com.ewind.boilerplate.ui.component.adapter.NewsAdapter
import com.ewind.boilerplate.ui.main.base.BaseFragment
import com.ewind.boilerplate.util.Msg
import com.ewind.boilerplate.util.PaginationScrollListener
import com.ewind.boilerplate.util.Resource
import com.ewind.boilerplate.util.ResourceState
import com.ewind.boilerplate.util.ext.startRefresh
import com.ewind.boilerplate.util.ext.stopRefresh
import com.ewind.boilerplate.util.ext.withNetwork
import kotlinx.android.synthetic.main.fragment_top_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopNewsFragment : BaseFragment() {

    private val topNewsViewModel by viewModel<TopNewsViewModel>()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topNewsViewModel.newsliveDate.observe(this, androidx.lifecycle.Observer { updateView(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_top_news, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter(mutableListOf())
        rv_top_news.adapter = newsAdapter
        rv_top_news.addOnScrollListener(object :
            PaginationScrollListener(rv_top_news.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                topNewsViewModel.currentPage++
                getTopNews()
            }

            override fun isLastPage(): Boolean {
                return if (topNewsViewModel.totalCount != null) {
                    newsAdapter.itemCount >= topNewsViewModel.totalCount!!
                } else {
                    false
                }
            }

            override fun isLoading(): Boolean {
                return topNewsViewModel.isLoading
            }

        })

        pull_refresh.setOnRefreshListener {
            refreshData()
            getTopNews()
        }

        getTopNews()
    }

    private fun getTopNews() {
        context?.withNetwork(
            {
                topNewsViewModel.getTopNews()
            }
            , {
                Msg.INTERNET_ISSUE.showToast(context!!)
            }
        )
    }

    private fun refreshData() {
        topNewsViewModel.currentPage = 1
        newsAdapter.clearDate()
    }

    private fun updateView(resource: Resource<MutableList<DArticles>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> {
                    pull_refresh.startRefresh()
                }
                ResourceState.SUCCESS -> {
                    pull_refresh.stopRefresh()
                    it.data?.let { it1 ->
                        newsAdapter.addNews(it1)
                    }
                }
                ResourceState.ERROR -> {
                    pull_refresh.stopRefresh()
                    Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}