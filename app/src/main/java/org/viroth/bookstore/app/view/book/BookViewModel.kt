package org.viroth.bookstore.app.view.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.model.Book
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookViewModel : BaseViewModel() {
    val books: MutableLiveData<Book> = MutableLiveData()

    fun getBook() {
        if (books.value == null) {
            viewModelScope.launch(Dispatchers.IO) {
                loading.postValue(true)
                when (val result = BookApplication.appRepository.getBook()) {
                    is ResultOf.Success -> {
                        books.postValue(result.data)
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
}