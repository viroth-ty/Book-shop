package org.viroth.bookstore.app.view.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.service.SQLiteDatabaseHandler
import org.viroth.bookstore.app.viewmodel.BaseViewModel

class FavouriteViewModel : BaseViewModel() {

    val books: MutableLiveData<List<HydraMember>> = MutableLiveData()

    fun getFavouriteBook() {
        viewModelScope.launch(Dispatchers.IO) {
            loading.postValue(true)
            val sqLiteOpenHelper = SQLiteDatabaseHandler(BookApplication.context)
            val bookList = sqLiteOpenHelper.getFavouriteBook()
            books.postValue(bookList)
        }
    }
}