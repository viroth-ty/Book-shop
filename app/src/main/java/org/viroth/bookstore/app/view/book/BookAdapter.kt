package org.viroth.bookstore.app.view.book

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.ComponentBookItemBinding
import org.viroth.bookstore.app.model.HydraMember

class BookAdapter(
    private val clickListener: (HydraMember) -> Unit,
    private val favouriteClickListener: ((HydraMember) -> Unit)? = null
) :
    androidx.recyclerview.widget.ListAdapter<HydraMember, BookAdapter.PostViewHolder>(
        PostDiffCallback()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.component_detail_book_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.binding.bookImageView)
            .load(R.drawable.img_placeholder)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .into(holder.binding.bookImageView)
        holder.binding.bookAuthorTextView.text = item.author
        holder.binding.bookTitleTextView.text = item.title
        holder.binding.favouriteButton.visibility = View.GONE
        holder.binding.favouriteButton.setOnClickListener {
            favouriteClickListener?.invoke(item)
        }
        holder.binding.root.setOnClickListener {
            clickListener.invoke(item)
        }
    }

    override fun getItemId(position: Int): Long = position.toLong()

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ComponentBookItemBinding.bind(itemView)
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