package org.viroth.bookstore.app.view.book

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seanghay.statusbar.statusBar
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.databinding.BookFragmentBinding
import org.viroth.bookstore.app.model.Query
import org.viroth.bookstore.app.util.Util


class BookFragment : Fragment() {

    private val viewModel: BookViewModel by viewModels()
    private var _binding: BookFragmentBinding? = null
    private lateinit var bookAdapter: BookAdapter
    private val binding get() = _binding!!
    private var query: Query = Query(page = 1, title = "", author = "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        statusBar.color(Color.TRANSPARENT).light(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
        initObservation()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        binding.searchInputText.requestFocus()
        bookAdapter = BookAdapter(clickListener = {
            val bundle = bundleOf(Constant.Book.BOOKING_ID to Util.findBookId(it.id))
            findNavController().navigate(R.id.action_bookFragment_to_bookDetailFragment, bundle)
        }, favouriteClickListener = {
            if(it.isbn == viewModel.favouriteBooks.find { item -> item.isbn == it.isbn }?.isbn) {
                if(it.isSave == 1) {
                    viewModel.removeFromSqlite(hydraMember = it)
                    bookAdapter.currentList.find { item -> item.isbn == it.isbn }?.isSave = 0
                } else {
                    viewModel.addToSqlite(hydraMember = it)
                    bookAdapter.currentList.find { item -> item.isbn == it.isbn }?.isSave = 1
                }
            } else {
                viewModel.addToSqlite(hydraMember = it)
                bookAdapter.currentList.find { item -> item.isbn == it.isbn }?.isSave = 1
            }
            bookAdapter.notifyDataSetChanged()
        })

        val layoutManager = LinearLayoutManager(requireContext())
        binding.bookRecyclerView.layoutManager = layoutManager
        binding.bookRecyclerView.adapter = bookAdapter
    }

    private fun initEvent() {
        binding.loadingProgress.root.visibility = View.VISIBLE
        viewModel.getBook(query = query)
        binding.searchInputText.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.searchInputText.text?.isNotEmpty() == true) {
                    binding.loadingProgress.root.visibility = View.VISIBLE
                    if(viewModel.searchBy.value == Constant.SearchBy.AUTHOR) {
                        query.author = binding.searchInputText.text.toString()
                        query.title = ""
                    } else {
                        query.title = binding.searchInputText.text.toString()
                        query.author = ""
                    }
                    viewModel.getBook(query = query)
                    binding.loadingProgress.root.visibility = View.GONE
                    binding.searchInputText.setText("")
                }
                return@OnEditorActionListener false
            }
            false
        })
        binding.refreshIndicator.setOnRefreshListener {
            binding.loadingProgress.root.visibility = View.VISIBLE
            query = Query(page = 1, title = "", author = "")
            viewModel.getBook(query)
            binding.refreshIndicator.isRefreshing = false
            binding.loadingProgress.root.visibility = View.GONE
        }
    }

    private fun initObservation() {
        viewModel.books.observe(viewLifecycleOwner) { books ->
            bookAdapter.submitList(books)
            binding.loadingProgress.root.visibility = View.GONE
        }
        viewModel.searchBy.observe(viewLifecycleOwner) {
            binding.textField.placeholderText = String.format(requireContext().getString(R.string.searchBy), it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.actionAuthor -> {
            viewModel.updateSearchBy(Constant.SearchBy.AUTHOR)
            true
        }

        R.id.actionTitle -> {
            viewModel.updateSearchBy(Constant.SearchBy.TITLE)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}