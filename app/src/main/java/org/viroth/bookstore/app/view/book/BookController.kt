package org.viroth.bookstore.app.view.book

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.util.Util
import java.util.concurrent.CopyOnWriteArrayList

class BookController(
    private val itemClickListener: (HydraMember) -> Unit
) : EpoxyController() {

    private var books: CopyOnWriteArrayList<HydraMember> = CopyOnWriteArrayList()
    private var topBooks: CopyOnWriteArrayList<HydraMember> = CopyOnWriteArrayList()

    fun submitList(books: ArrayList<HydraMember>) {
        this.books.clear()
        this.topBooks.addAll(books)
        this.books.addAll(books)
        requestModelBuild()
    }

    override fun buildModels() {

        bookTitle {
            id("library_id")
            title(BookApplication.context.getString(R.string.library))
        }

        if (topBooks.isNotEmpty()) {
            val topBookModel: ArrayList<BookItemModel_> = ArrayList()
            topBooks.forEach { item ->
                topBookModel.add(
                    BookItemModel_()
                        .id("top_book_${item.isbn}")
                        .author(item.author)
                        .title(item.title)
                        .titlePlaceholderTextView(Util.splitTheWord(item.title!!))
                        .clickListener {
                            itemClickListener(item)
                        }
                )
            }
            group {
                id("group_title")
                layout(R.layout.component_horizontal_group)
                carousel {
                    id("carousel_title")
                    models(topBookModel)
                    paddingDp(4)
                    numViewsToShowOnScreen(1.5f)
                    hasFixedSize(true)
                }
                shouldSaveViewState(true)
            }

            bookTitle {
                id("top_book_title")
                title(BookApplication.context.getString(R.string.top_book))
            }

            if (books.isNotEmpty()) {
                this.books.forEach { item ->
                    bookItem {
                        id("book_${item.isbn}")
                        author(item.author)
                        title(item.title)
                        clickListener {
                            this@BookController.itemClickListener.invoke(item)
                        }
                    }
                }
            }
        }
    }
}