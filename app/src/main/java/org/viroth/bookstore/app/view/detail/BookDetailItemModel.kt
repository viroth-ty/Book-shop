package org.viroth.bookstore.app.view.detail

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentDetailBookItemBinding

@EpoxyModelClass
abstract class BookDetailItemModel : EpoxyModelWithHolder<BookDetailItemModel.BookItemModelViewHolder>() {

    @field:EpoxyAttribute
    var title: String? = null

    @field:EpoxyAttribute
    var author: String? = null

    @field:EpoxyAttribute
    var imageUrl: String? = null

    @field:EpoxyAttribute
    var date: String? = null

    @field:EpoxyAttribute
    var description: String? = null

    @field:EpoxyAttribute
    var backgroundColor: Int? = null

    @field:EpoxyAttribute
    var titlePlaceHolder: String? = null

    override fun getDefaultLayout(): Int = R.layout.component_detail_book_item

    @SuppressLint("SetTextI18n")
    override fun bind(holder: BookItemModelViewHolder) {
        super.bind(holder)

        holder.binding.apply {
            bookTitleTextView.text = title
            bookAuthorTextView.text = author
            bookPublishDateTextView.text = date
            bookDescriptionTextView.text = description
            bookImageView.setBackgroundColor(backgroundColor!!)
            bookPlaceholderTextView.text = titlePlaceHolder
        }
    }

    class BookItemModelViewHolder : EpoxyHolder() {

        lateinit var binding: ComponentDetailBookItemBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentDetailBookItemBinding.bind(itemView)
        }
    }
}