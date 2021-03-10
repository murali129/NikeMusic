# NikeMusic
This is a project using the latest architecture components to search music using last fm track.search api

## View
It's based in a [Activity - Fragment] architecture, also on a RecyclerView and Adapter view based on the data that has to be shown.

## ViewModel
-Songs ViewModel handling the most business logic and obtain the songs using songsRepo injected in it
-Also manages the network state

## Repository
The repo has the ApiService and the Room Service. 
- We get the search data from the ApiService.
- We can save data to the Room Service
- We can delete the data to the Room Service.
- We can get the saved data from the Room Service.

## Dependency Injection
We use Koin for the dependency injection, it's a very simple library for DI for Kotlin. Very easy to understand and to use.
We have 4 major modules:
- networkModule > Retrofit, OkHttpClient, Gson...
- repoModule > Provides the ApiService and the Room Service to the RepoModule.
- roomModule > Provides the instance of the DataBase and the Dao Interface.
- viewModelModule > Provides the Repository instance to both viewmodels.

## Testing
- For Repo Unit testing I have been using [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) to mock the response from the API.
- The DAO is tested as well.

## Main libraries used
- [Kotlin](https://kotlinlang.org/docs/reference/) :heart:
- [Koin](https://github.com/InsertKoinIO/koin) (DI)
- [MVVM](https://developer.android.com/jetpack/docs/guide) (Architecture)
- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging) (Paging)
- [Room](https://developer.android.com/topic/libraries/architecture/room) (Persistence)
- [Retrofit](https://square.github.io/retrofit/) (Network)
- [Coroutines](https://developer.android.com/kotlin/coroutines)

## TODO
- Did not use the Room persistent layer in this project to persist the data, but we can use it to cache songs to be loaded in offline mode
- Instrumented Test

## License
[MIT](https://choosealicense.com/licenses/mit/)
