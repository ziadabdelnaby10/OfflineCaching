# NetworkBound-OfflineCaching

[![platform](https://img.shields.io/badge/platform-Android-yellow.svg)](https://www.android.com)
An android app to  demonstrate offline caching capabilities offered by JetPack Libraries Using:-
- Retrofit
- Room
- Picasso
- RecyclerView with ListAdapter
- Coroutines (Defered for api)
- MVVM
- NetworkBoundResource
- Dependency Injection Using Dagger Hilt
- Two-Way databinding & BindingAdapters
- Navigation Component
- Single Activity Architecture

[Link](https://www.youtube.com/playlist?list=PLrnPJCHvNZuCLOE6tNcoOHSJ5rvhi0t0p) to CodingInFlow tutorial

## ScreenShots
![1](https://github.com/ziadabdelnaby10/OfflineCaching/blob/main/1.png)
![2](https://github.com/ziadabdelnaby10/OfflineCaching/blob/main/2.png)
![3](https://github.com/ziadabdelnaby10/OfflineCaching/blob/main/3.png)
![4](https://github.com/ziadabdelnaby10/OfflineCaching/blob/main/4.png)
![5](https://github.com/ziadabdelnaby10/OfflineCaching/blob/main/5.png)

### Dependencies

```
    implementation 'androidx.core:core-ktx:1.9.0'
    implementation("androidx.activity:activity-ktx:1.6.0")
    implementation("androidx.fragment:fragment-ktx:1.5.4")
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.6.0-alpha03'
    implementation("androidx.lifecycle:lifecycle-livedata-core-ktx:2.6.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha03")
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha03'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.5.3'
    implementation 'androidx.navigation:navigation-ui-ktx:2.5.3'
    implementation "androidx.navigation:navigation-runtime-ktx:2.5.3"
    implementation 'androidx.appcompat:appcompat:1.5.1'
    implementation 'com.google.android.material:material:1.7.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation "com.squareup.moshi:moshi-kotlin:1.13.0"
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-moshi:2.9.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4"
    implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation("androidx.room:room-ktx:2.5.0-beta01")
    implementation "androidx.room:room-runtime:2.5.0-beta01"
    implementation 'com.google.dagger:hilt-android:2.44'
    kapt 'com.google.dagger:hilt-compiler:2.44'
    kapt "androidx.room:room-compiler:2.5.0-beta01"
    implementation 'com.jakewharton.timber:timber:5.0.1'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
```

### Plugins

```
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'androidx.navigation.safeargs.kotlin'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
```

### NetworkBound Function

```
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Failed(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)
}
```

# The Algorithm
The block diagram represents the control flow :
![NetworkBoundResource](https://user-images.githubusercontent.com/56694152/130604011-73d3ca1d-aee2-4dc2-995f-9e0d7e9a522e.jpg)

The goal here is to minimize the changes in the user-expereince. Important things to be noted:

* For the first time, when the application is started, the data is fetched from the webservice(restAPI, AWS, etc) in a background thread.
* As soon as the data fetch is done, all the data is stored in the local database, using the ROOM persistence library
* Parallelly, the data is exposed and shown in the activity/ fragment.
* If any change is there in the data, then the whole view is not refreshed, rather it is fetched in the background thread, laterwards the new data is replaced with the old data



The app follows the [MVVM](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjYuM7Xv8nyAhVMAHIKHSOjDr4QFnoECAYQAQ&url=https%3A%2F%2Fdeveloper.android.com%2Ftopic%2Flibraries%2Farchitecture%2Fviewmodel&usg=AOvVaw3f_7HpGuQps9xX6BXFMqhB) architecture.

The core part of this application is the `NetworkBoundResource.kt` file, where the magic happens.

##### Code
```kotlin
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
```

The above code first checks if there is any requirement for fetching the data or not.
If it is required to fetch the data, then it is emitted.
Else just look into the map
Kotlin Flow is used here


# Application
This repository contains code for an android application, which basically shows a list of data fetched from a random API generator, using `RetrofitAPI` using which APIs are converted into callable objects.

The following data are required to be fetched  and shown in the activity.

##### Code

```kotlin
// Data Class to store the data
@Entity(tableName = Constants.COFFEE_TABLE)
data class Coffee(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val blend_name: String,
    val intensifier: String,
    val notes: String,
    val origin: String,
    val uid: String,
    val variety: String
)
```

A `repository` will be used. Repository pattern is one of the design patterns that available out there.A Repository is defined as a collection of domain objects that reside in the memory.


`DAO` for the API path

##### Code

```kotlin
interface CoffeeServices {
    @GET(Constants.COFFEE_PATH)
    fun getCoffeeAsync(@Query("size") size: Int): Deferred<List<Coffee>>
}
```

`Repository` class to centralize the data access

##### Code

```kotlin
class CoffeeRepository @Inject constructor(
    private val api: CoffeeServices,
    private val db: AppDatabase
) {
    private val coffeeDao = db.coffeeDao()

    fun getCoffee() = networkBoundResource(
        query = {
            coffeeDao.getAllData()
        },
        fetch = {
            api.getCoffeeAsync(30).await()
        },
        saveFetchResult = { desserts ->
            db.withTransaction {
                coffeeDao.deleteAll()
                coffeeDao.insertData(desserts)
            }
        }
    )
}
```

This thing has to be implemented in a `viewModel` from which data will be exposed on a view or a fragment.
The view model can get data from the repository by observing it's live data.


##### Code

```kotlin
@HiltViewModel
class CoffeeViewModel @Inject constructor(repository: CoffeeRepository) : ViewModel() {

    val coffeeLiveData = repository.getCoffee().asLiveData()
}
```
Adding a repository between the data source and a view is recommended by Android, as it seperates the view, so that focus can be put seperately on increasing the UI of app and the database. Moreover, the repository helps by centralising the data access, which directly reduces the boilerplate code.

The app uses [MVVM Architecture](https://developer.android.com/jetpack/docs/guide#recommended-app-arch)
