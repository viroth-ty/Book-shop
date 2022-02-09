package org.viroth.bookstore.app.component

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentLoadingBinding

@EpoxyModelClass(layout = R.layout.component_loading)
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.CategoryViewHolder>() {

    class CategoryViewHolder : EpoxyHolder() {
        lateinit var binding: ComponentLoadingBinding
            private set

        override fun bindView(itemView: View) {
            binding = ComponentLoadingBinding.bind(itemView)
        }
    }
}