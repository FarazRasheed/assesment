# News app

### Major technologies
- Language: Kotlin
- Architecture: MVVM with clean
- Android architecture components: ViewModel, LiveData, Room and Navigation
- Dependency injection: koin
- Network: Retrofit, RxJava, okHttp

#### Structure of this project with 3 layers:
- Presentation
- Domain
- Data

#### Communication between layers
1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Use case communicate with Repositories.
4. Each Repository returns data from a Data Source (Local or Remote).
5. Information flows back to the UI where we display the list.

### The app has following packages:
1. **data**: Contains APIs, Database
2. **ki**: Dependency providing classes using koin.
3. **ui**: View classes along with their corresponding ViewModel.
4. **util**: Utility classes.
5. **doamin**: Contains Use Cases, and Model Objects. Business logic happens here.

