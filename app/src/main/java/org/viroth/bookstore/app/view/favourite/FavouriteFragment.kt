package org.viroth.bookstore.app.view.favourite

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seanghay.statusbar.statusBar
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.databinding.FavouriteFragmentBinding
import org.viroth.bookstore.app.service.SQLiteDatabaseHandler
import org.viroth.bookstore.app.util.Util
import org.viroth.bookstore.app.view.book.BookAdapter

class FavouriteFragment : Fragment() {

    private val viewModel: FavouriteViewModel by viewModels()
    private var _binding: FavouriteFragmentBinding? = null
    private lateinit var bookAdapter: BookAdapter
    private val binding get() = _binding!!
    private lateinit var databaseHandler: SQLiteDatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.color(Color.TRANSPARENT).light(false)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavouriteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHandler = SQLiteDatabaseHandler(requireContext())

        initView()
        initEvent()
        initObservation()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        bookAdapter = BookAdapter(clickListener = {
            val bundle = bundleOf(
                Constant.Book.BOOKING_ID to Util.findBookId(it.id),
                Constant.Book.BOOKING_ISBN to it.isbn
            )
            findNavController().navigate(R.id.action_favouriteFragment_to_bookDetailFragment, bundle)
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = bookAdapter
    }

    private fun initEvent() {
        viewModel.getFavouriteBook()
        binding.loadingProgress.root.visibility = View.VISIBLE
    }

    private fun initObservation() {
        viewModel.books.observe(viewLifecycleOwner) {
            bookAdapter.submitList(it)
            binding.loadingProgress.root.visibility = View.GONE
        }
        viewModel.empty.observe(viewLifecycleOwner) {
            binding.emptyView.root.visibility = if(it) View.VISIBLE else View.GONE
        }
    }
}