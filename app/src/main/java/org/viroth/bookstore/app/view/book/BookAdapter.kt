package org.viroth.bookstore.app.view.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.BookItemViewBinding
import org.viroth.bookstore.app.model.HydraMember

class BookAdapter(private val clickListener: () -> Unit) :
    androidx.recyclerview.widget.ListAdapter<HydraMember, BookAdapter.PostViewHolder>(
        PostDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_item_view, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.imageView.load(item.url) {
            crossfade(true)
            placeholder(R.mipmap.ic_launcher)
            error(R.mipmap.ic_launcher)

            transformations(CircleCropTransformation())
        }
        holder.binding.bookAuthor.text = item.author
        holder.binding.bookTitle.text = item.title
        holder.binding.root.setOnClickListener {
            clickListener();
        }
    }

    override fun getItemCount(): Int = super.getItemCount()

    override fun getItemId(position: Int): Long = position.toLong()

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = BookItemViewBinding.bind(itemView)
    }

    class PostDiffCallback : DiffUtil.ItemCallback<HydraMember>() {

        override fun areItemsTheSame(oldItem: HydraMember, newItem: HydraMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HydraMember, newItem: HydraMember): Boolean {
            return oldItem == newItem
        }
    }
}