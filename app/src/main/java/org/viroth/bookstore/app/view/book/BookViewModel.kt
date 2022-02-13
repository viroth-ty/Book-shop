package org.viroth.bookstore.app.view.book

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.model.TopBookHydraMember
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class BookViewModel : BaseViewModel() {

    val searchBy: MutableLiveData<String> =  MutableLiveData(Constant.SearchBy.AUTHOR)
    val books: MutableLiveData<ArrayList<HydraMember>> = MutableLiveData(arrayListOf())
    val topBooks: MutableLiveData<ArrayList<TopBookHydraMember>> = MutableLiveData(arrayListOf())

    fun updateSearchBy(newSearchBy: String) {
        searchBy.postValue(newSearchBy)
    }

    fun getBook(
        query: Query,
        isSearchingOrRefreshing: Boolean = false)
    {
        if((books.value?.isEmpty() == true && topBooks.value?.isEmpty() == true) || isSearchingOrRefreshing) {
            viewModelScope.launch(Dispatchers.IO) {
                loading.postValue(true)
                when (val result = BookApplication.appRepository.book(query = query)) {
                    is ResultOf.Success -> {
                        if(result.data.hydraMember.isEmpty()) {
                            empty.postValue(true)
                        } else {
                            empty.postValue(false)
                        }
                        books.postValue(result.data.hydraMember)
                        loading.postValue(false)
                    }
                    is ResultOf.Error -> {
                        loading.postValue(false)
                        error.postValue(true)
                        errorMessage.postValue(result.error?.message)
                    }

                    is ResultOf.NetworkError -> {
                        error.postValue(true)
                        loading.postValue(false)
                        errorMessage.postValue("Network has problem, Please check and try again!")
                    }
                }

                when (val result = BookApplication.appRepository.topBook(query = query)) {
                    is ResultOf.Success -> {
                        topBooks.postValue(result.data.hydraMember)
                        loading.postValue(false)
                    }
                    is ResultOf.Error -> {
                        loading.postValue(false)
                        error.postValue(true)
                        errorMessage.postValue(result.error?.message)
                    }

                    is ResultOf.NetworkError -> {
                        error.postValue(true)
                        loading.postValue(false)
                        errorMessage.postValue("Network has problem, Please check and try again!")
                    }
                }
            }
        }
    }
}