package com.ewind.boilerplate.ui.main.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.elegantmedia.dod.util.ext.showToast
import com.ewind.boilerplate.R
import com.ewind.boilerplate.domain.model.Category
import com.ewind.boilerplate.domain.model.DArticles
import com.ewind.boilerplate.ui.component.adapter.CategoryAdapter
import com.ewind.boilerplate.ui.component.adapter.NewsAdapter
import com.ewind.boilerplate.ui.main.base.BaseFragment
import com.ewind.boilerplate.util.Msg
import com.ewind.boilerplate.util.PaginationScrollListener
import com.ewind.boilerplate.util.Resource
import com.ewind.boilerplate.util.ResourceState
import com.ewind.boilerplate.util.ext.startRefresh
import com.ewind.boilerplate.util.ext.stopRefresh
import com.ewind.boilerplate.util.ext.withNetwork
import kotlinx.android.synthetic.main.fragment_news.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment(), CategoryAdapter.AdapterListener {

    private val newsViewModel by viewModel<NewsViewModel>()
    private val categoryArray = mutableListOf("All", "bitcoin", "apple", "earthquake", "animal")
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsViewModel.newsliveDate.observe(this, androidx.lifecycle.Observer { updateView(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categorys = categoryArray.mapIndexed { index, s ->
            if (index == 0) {
                Category(s, true)
            } else {
                Category(s)
            }
        }.toMutableList()
        val adapter = CategoryAdapter(categorys)
        adapter.listener = this
        rv_category.adapter = adapter

        newsAdapter = NewsAdapter(mutableListOf())
        rv_top_news.adapter = newsAdapter
        rv_top_news.addOnScrollListener(object :
            PaginationScrollListener(rv_top_news.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                newsViewModel.currentPage++
                getNews()
            }

            override fun isLastPage(): Boolean {
                return if (newsViewModel.totalCount != null) {
                    newsAdapter.itemCount >= newsViewModel.totalCount!!
                } else {
                    false
                }
            }

            override fun isLoading(): Boolean {
                return newsViewModel.isLoading
            }

        })

        pull_refresh.setOnRefreshListener {
            refreshData()
            getNews()
        }

        getNews()
    }

    private fun getNews() {
        context?.withNetwork(
            {
                newsViewModel.getNews()
            }
            , {
                Msg.INTERNET_ISSUE.showToast(context!!)
            }
        )
    }

    private fun refreshData() {
        newsViewModel.currentPage = 1
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

    override fun onCategorySelected(category: Category) {
        refreshData()
        newsViewModel.keyword = category.key
        getNews()
    }
}