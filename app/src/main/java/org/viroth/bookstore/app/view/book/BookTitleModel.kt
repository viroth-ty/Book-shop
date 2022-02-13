package org.viroth.bookstore.app.view.book

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentTitleBinding

@EpoxyModelClass(layout = R.layout.component_title)
abstract class BookTitleModel: EpoxyModelWithHolder<BookTitleModel.BookTitleViewHolder>() {

    @field:EpoxyAttribute
    var title: String? = null

    override fun bind(holder: BookTitleViewHolder) {
        super.bind(holder)
        holder.binding.titleTextView.text = title
    }

    class BookTitleViewHolder: EpoxyHolder() {
        lateinit var binding: ComponentTitleBinding
            private set
        override fun bindView(itemView: View) {
            binding = ComponentTitleBinding.bind(itemView)
        }

    }
}