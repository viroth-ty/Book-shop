package org.viroth.bookstore.app.view.book

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.carousel
import com.airbnb.epoxy.group
import org.viroth.bookstore.app.BookApplication
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.model.TopBookHydraMember
import org.viroth.bookstore.app.util.Util
import java.util.concurrent.CopyOnWriteArrayList

class BookController(
    private val itemClickListener: (HydraMember) -> Unit,
    private val topItemClickListener: (TopBookHydraMember) -> Unit
) : EpoxyController() {

    private var books: CopyOnWriteArrayList<HydraMember> = CopyOnWriteArrayList()
    private var topBooks: CopyOnWriteArrayList<TopBookHydraMember> = CopyOnWriteArrayList()

    fun submitBook(books: ArrayList<HydraMember>) {
        this.books.clear()
        this.books.addAll(books)
        requestModelBuild()
    }

    fun submitTopBook(books: ArrayList<TopBookHydraMember>) {
        topBooks.addAll(books)
        requestModelBuild()
    }


    override fun buildModels() {

        if ( books.isNotEmpty() && topBooks.isNotEmpty()) {

            val topBookModel: ArrayList<BookItemModel_> = ArrayList()
            topBooks.forEach { item ->
                topBookModel.add(
                    BookItemModel_()
                        .id("top_book_id${item.id}")
                        .author(item.author)
                        .title(item.title)
                        .titlePlaceholderTextView(Util.splitTheWord(item.title))
                        .backgroundColor(Util.generateColorFromString(seed = item.title))
                        .clickListener {
                            topItemClickListener(item)
                        }
                )
            }

            bookTitle {
                id("top_book_title")
                title(BookApplication.context.getString(R.string.top_book))
            }

            group {
                id("top_book_group")
                layout(R.layout.component_horizontal_group)
                carousel {
                    id("top_book_carousel")
                    models(topBookModel)
                    paddingDp(4)
                    numViewsToShowOnScreen(1.5f)
                    hasFixedSize(true)
                }
                shouldSaveViewState(true)
            }

            bookTitle {
                id("library_title")
                title(BookApplication.context.getString(R.string.library))
            }

            books.forEach { item ->
                bookItem {
                    id("book_id${item.isbn}")
                    author(item.author)
                    title(item.title)
                    titlePlaceholderTextView(Util.splitTheWord(item.title!!))
                    backgroundColor(Util.generateColorFromString(seed = item.title))
                    clickListener {
                        this@BookController.itemClickListener.invoke(item)
                    }
                }
            }
        }
    }
}