package org.viroth.bookstore.app.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.service.SQLiteDatabaseHandler
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookDetailViewModel : BaseViewModel() {

    private var databaseHandler: SQLiteDatabaseHandler = SQLiteDatabaseHandler(BookApplication.context)
    val bookInformation: MutableLiveData<BookInformation> = MutableLiveData()
    private var favouriteBooks: ArrayList<HydraMember> = arrayListOf()
    val isFavourite: MutableLiveData<Boolean> = MutableLiveData()

    init {
        favouriteBooks.addAll(databaseHandler.getFavouriteBook())
    }

    fun updateFavourite(bookId: String, isbn: String) {

        val book = bookInformation.value
        val hydraMember = HydraMember(id = book!!.id, isbn = book.isbn, title = book.title)
        if (book.isbn == favouriteBooks.find { item -> item.isbn == isbn }?.isbn) {
            if (book.isSaved == 1) {
                bookInformation.value?.isSaved = 0
                databaseHandler.removeFavouriteNews(id = hydraMember.isbn!!)
                isFavourite.postValue(false)
            } else {
                bookInformation.value?.isSaved = 1
                databaseHandler.addFavouriteNews(id = hydraMember.id, isbn = hydraMember.isbn.toString(), title = hydraMember.title.toString())
                isFavourite.postValue(true)
            }
        } else {
            bookInformation.value?.isSaved = 1
            databaseHandler.addFavouriteNews(id = hydraMember.id, isbn = hydraMember.isbn.toString(), title = hydraMember.title.toString())
            isFavourite.postValue(true)
        }

    }

    fun getBookInformation(bookId: String, isbn: String) {
        if(bookInformation.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                loading.postValue(true)
                when (val result = BookApplication.appRepository.bookInformation(bookId = bookId)) {
                    is ResultOf.Success -> {
                        if (isbn == favouriteBooks.find { item -> item.isbn == isbn }?.isbn) {
                            result.data.isSaved = 1
                            isFavourite.postValue(true)
                        } else {
                            result.data.isSaved = 1
                            isFavourite.postValue(false)
                        }
                        bookInformation.postValue(result.data)
                        loading.postValue(false)
                    }
                    is ResultOf.Error -> {
                        loading.postValue(false)
                        error.postValue(true)
                        print(result.error?.message)
                        errorMessage.postValue(result.error?.message)
                    }

                    is ResultOf.NetworkError -> {
                        loading.postValue(false)
                        error.postValue(true)
                        errorMessage.postValue("Network has problem, Please check and try again!")
                    }
                }
            }
        }
    }
}