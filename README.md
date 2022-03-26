# Book shop

Book shop is app that collect all books, you can 

- find book
- find book by search author or title
- read detail of book, author or release date
- in book detail, user can save book to favourite list or remove from favourite list

**2. App Archetichure**

This app is single activity. This means that there is only one **Activity** and other views are fragment.

- MVVM (Model, View, ViewModel)

  Jetpack libraries:
  * [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
  * [Live Data](https://developer.android.com/jetpack/androidx/releases/lifecycle)
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
  * [View Binding](https://developer.android.com/topic/libraries/view-binding)
  
  Epoxy RecyclerView (Use for complex views in recycler view)
  * [Airbnb/epoxy](https://github.com/airbnb/epoxy/)

  Network and network interceptor
  * [Retrofit](https://square.github.io/retrofit/)
  * [Chucker](https://github.com/ChuckerTeam/chucker)

  Json converter
  * [Gson](https://github.com/google/gson)

  Animation 
  * [Lottie](https://airbnb.io/projects/lottie-android/#:~:text=Lottie%20for%20Android,painstakingly%20recreating%20it%20by%20hand)
