package org.viroth.bookstore.app.view.detail

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentReviewBinding

@EpoxyModelClass(layout = R.layout.component_review)
abstract class ReviewModel: EpoxyModelWithHolder<ReviewModel.BookReviewViewHolder>(){

    @field:EpoxyAttribute
    var body: String? = null

    override fun bind(holder: BookReviewViewHolder) {
        super.bind(holder)
        holder.binding.titleTextView.text = body
    }

    class BookReviewViewHolder : EpoxyHolder() {

        lateinit var binding: ComponentReviewBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentReviewBinding.bind(itemView)
        }
    }
}