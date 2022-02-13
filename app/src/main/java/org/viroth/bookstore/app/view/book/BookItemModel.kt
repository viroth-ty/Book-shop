package org.viroth.bookstore.app.view.book

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentBookItemBinding

@EpoxyModelClass
abstract class BookItemModel : EpoxyModelWithHolder<BookItemModel.BookItemViewModel>() {

    @field:EpoxyAttribute
    var author: String? = null

    @field:EpoxyAttribute
    var title: String? = null

    @field:EpoxyAttribute
    var titlePlaceholderTextView: String? = null

    @field:EpoxyAttribute
    var clickListener: (() -> Unit)? = null

    @field:EpoxyAttribute
    var backgroundColor: Int? = null

    override fun getDefaultLayout(): Int {
        return R.layout.component_book_item
    }

    override fun shouldSaveViewState(): Boolean {
        return true
    }

    override fun bind(holder: BookItemViewModel) {
        super.bind(holder)

        holder.binding.bookAuthorTextView.text = author
        holder.binding.bookTitleTextView.text = title
        backgroundColor?.let { holder.binding.bookImageView.setBackgroundColor(it) }
        holder.binding.bookPlaceholderTextView.text = titlePlaceholderTextView
        holder.binding.root.setOnClickListener {
            clickListener?.invoke()
        }

    }

    class BookItemViewModel : EpoxyHolder() {
        lateinit var binding: ComponentBookItemBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentBookItemBinding.bind(itemView)
        }

    }
}