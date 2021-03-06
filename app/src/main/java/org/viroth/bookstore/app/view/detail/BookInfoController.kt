package org.viroth.bookstore.app.view.detail

import com.airbnb.epoxy.EpoxyController
import org.viroth.bookstore.app.component.loading
import org.viroth.bookstore.app.model.BookInformation
import org.viroth.bookstore.app.model.Review
import org.viroth.bookstore.app.util.DateUtil
import org.viroth.bookstore.app.util.Util
import java.util.concurrent.CopyOnWriteArrayList

class BookInfoController : EpoxyController() {

    private var bookInformation: BookInformation? = null
    private val reviews: CopyOnWriteArrayList<Review> = CopyOnWriteArrayList()

    fun submitData(bookInformation: BookInformation) {
        this.reviews.clear()
        this.bookInformation = bookInformation
        this.reviews.addAll(bookInformation.reviews)
        requestModelBuild()
    }

    override fun buildModels() {

        if (bookInformation != null) {

            bookDetailItem {
                id("book_header")
                title(this@BookInfoController.bookInformation?.title)
                author(this@BookInfoController.bookInformation?.author)
                date(DateUtil.formatTimeToStandard(this@BookInfoController.bookInformation?.publicationDate!!))
                description(this@BookInfoController.bookInformation?.description)
                backgroundColor(Util.generateColorFromString(this@BookInfoController.bookInformation?.title!!))
                titlePlaceHolder(Util.splitTheWord(this@BookInfoController.bookInformation?.title!!))
            }

            this.bookInformation?.reviews?.forEach { review ->
                review {
                    id(review.id)
                    body(review.body)
                }
            }

        } else {
            loading {
                id("loading")
            }
        }

    }
}