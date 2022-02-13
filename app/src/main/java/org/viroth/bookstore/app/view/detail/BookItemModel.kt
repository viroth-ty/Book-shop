package org.viroth.bookstore.app.view.detail

import android.annotation.SuppressLint
import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentDetailBookItemBinding

@EpoxyModelClass(layout = R.layout.component_detail_book_item)
abstract class BookItemModel : EpoxyModelWithHolder<BookItemModel.BookItemModelViewHolder>() {

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

    @SuppressLint("SetTextI18n")
    override fun bind(holder: BookItemModelViewHolder) {
        super.bind(holder)

        Glide.with(holder.binding.bookImageView)
            .load(R.drawable.img_placeholder)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.binding.bookImageView)

        holder.binding.apply {
            bookTitleTextView.text = title
            bookAuthorTextView.text = "By $author"
            bookPublishDateTextView.text = date
            bookDescriptionTextView.text = description
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