package org.viroth.bookstore.app.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookDetailViewModel : BaseViewModel() {

    val bookInformation: MutableLiveData<BookInformation> = MutableLiveData()

    fun getBookInformation(bookId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when(val result = BookApplication.appRepository.getBookInformation(bookId = bookId)) {
                is ResultOf.Success -> {
                    bookInformation.postValue(result.data)
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