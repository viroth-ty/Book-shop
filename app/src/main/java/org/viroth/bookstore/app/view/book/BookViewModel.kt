package org.viroth.bookstore.app.view.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.service.SQLiteDatabaseHandler
import org.viroth.bookstore.app.util.Util
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookViewModel : BaseViewModel() {
    val searchBy: MutableLiveData<String> =  MutableLiveData(Constant.SearchBy.AUTHOR)
    val books: MutableLiveData<List<HydraMember>> = MutableLiveData()
    var favouriteBooks : ArrayList<HydraMember> = arrayListOf()

    private var databaseHandler: SQLiteDatabaseHandler = SQLiteDatabaseHandler(BookApplication.context)

    init {
        favouriteBooks.addAll(databaseHandler.getFavouriteBook())
    }

    fun updateSearchBy(newSearchBy: String) {
        searchBy.postValue(newSearchBy)
    }

    fun addToSqlite(hydraMember: HydraMember) {
        favouriteBooks.find { item -> item.isbn == hydraMember.isbn }?.isSave = 1
        databaseHandler.addFavouriteNews(id = hydraMember.id, isbn = hydraMember.isbn!!, title = hydraMember.title!!)
    }

    fun removeFromSqlite(hydraMember: HydraMember) {
        favouriteBooks.find { item -> item.isbn == hydraMember.isbn }?.isSave = 0
        databaseHandler.removeFavouriteNews(id = hydraMember.isbn!!)
    }

    fun getBook(query: Query) {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            when (val result = BookApplication.appRepository.getBook(query = query)) {
                is ResultOf.Success -> {
                    val tempList = result.data.hydraMember
                    tempList.forEach { item ->
                        if(item.isbn == favouriteBooks.find { favItem -> favItem.isbn == item.isbn }?.isbn) {
                            item.isSave = 1
                        }
                    }
                    books.postValue(tempList)
                    loading.postValue(true)
                }
                is ResultOf.Error -> {
                    loading.postValue(true)
                    error.postValue(true)
                    errorMessage.postValue(result.error?.message)
                }

                is ResultOf.NetworkError -> {
                    error.postValue(true)
                    errorMessage.postValue("Network has problem, Please check and try again!")
                }
            }
        }
    }
}