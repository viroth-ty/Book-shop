# Book shop

Book shop is app that collect all books, you can 

- find book
- find book by search author or title
- read detail of book, author or release date
- in book detail, user can save book to favourite or remove from favourite to view later time.

**2. App Archetichure**

This app is single activity, means that there is only **Activity** and other views are fragment.

- MVVM (Model, View, ViewModel)

  Jetpack libraries:
  * [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)
  * [Live Data](https://developer.android.com/jetpack/androidx/releases/lifecycle)
  
  UI (complex view in recycler view)
  * [airbnb/epoxy](https://github.com/airbnb/epoxy/)

  Network and network interceptor
  * [Retrofit](https://square.github.io/retrofit/)
  * [Chucker](https://github.com/ChuckerTeam/chucker)

  Json converter
  * [Gson](https://github.com/google/gson)
