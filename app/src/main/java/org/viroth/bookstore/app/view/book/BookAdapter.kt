package org.viroth.bookstore.app.view.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.viroth.bookstore.app.databinding.ComponentBookItemBinding
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.util.Util

class BookAdapter(
    private val clickListener: (HydraMember) -> Unit,
    private val favouriteClickListener: ((HydraMember) -> Unit)? = null
) :
    androidx.recyclerview.widget.ListAdapter<HydraMember, BookAdapter.PostViewHolder>(
        PostDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val binding = ComponentBookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostViewHolder(binding.root, binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.apply {
            bookImageView.setBackgroundColor(Util.generateColorFromString(item.title!!))
            bookPlaceholderTextView.text = Util.splitTheWord(item.title)
            bookAuthorTextView.text = item.author
            bookTitleTextView.text = item.title
            favouriteButton.visibility = View.GONE
            favouriteButton.setOnClickListener {
                favouriteClickListener?.invoke(item)
            }
            root.setOnClickListener {
                clickListener.invoke(item)
            }
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class PostViewHolder(itemView: View, val binding: ComponentBookItemBinding) : RecyclerView.ViewHolder(itemView)

    class PostDiffCallback : DiffUtil.ItemCallback<HydraMember>() {

        override fun areItemsTheSame(oldItem: HydraMember, newItem: HydraMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HydraMember, newItem: HydraMember): Boolean {
            return oldItem == newItem
        }
    }
}