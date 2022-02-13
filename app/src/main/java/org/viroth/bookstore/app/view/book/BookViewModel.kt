package org.viroth.bookstore.app.view.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookViewModel : BaseViewModel() {

    val searchBy: MutableLiveData<String> =  MutableLiveData(Constant.SearchBy.AUTHOR)
    val books: MutableLiveData<ArrayList<HydraMember>> = MutableLiveData(arrayListOf())
    private val tempBooks: ArrayList<HydraMember> = ArrayList()

    fun updateSearchBy(newSearchBy: String) {
        searchBy.postValue(newSearchBy)
    }

    fun getBook(
        query: Query,
        isSearchingOrRefreshing: Boolean = false)
    {
        if(books.value?.isEmpty() == true || isSearchingOrRefreshing) {
            viewModelScope.launch(Dispatchers.IO) {
                loading.postValue(true)
                when (val result = BookApplication.appRepository.book(query = query)) {
                    is ResultOf.Success -> {
                        tempBooks.addAll(result.data.hydraMember)
                        books.postValue(tempBooks)
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