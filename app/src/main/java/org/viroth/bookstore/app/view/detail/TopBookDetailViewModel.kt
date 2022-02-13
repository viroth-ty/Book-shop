package org.viroth.bookstore.app.view.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.model.TopBookHydraMember
import org.viroth.bookstore.app.networking.http.ResultOf
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class TopBookDetailViewModel : BaseViewModel() {

    val detail: MutableLiveData<TopBookHydraMember> = MutableLiveData()

    fun getBook(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            when (val result = BookApplication.appRepository.topBookInformation(bookId = id)) {
                is ResultOf.Success -> {
                    detail.postValue(result.data)
                    loading.postValue(false)
                }
                is ResultOf.Error -> {
                    loading.postValue(false)
                    error.postValue(true)
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