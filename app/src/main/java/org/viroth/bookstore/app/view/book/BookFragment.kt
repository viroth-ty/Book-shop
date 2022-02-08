package org.viroth.bookstore.app.view.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.databinding.BookFragmentBinding

class BookFragment : Fragment() {

    private val viewModel: BookViewModel by viewModels()
    private var _binding: BookFragmentBinding? = null
    private lateinit var bookAdapter: BookAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookFragmentBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        initView();
        initEvent()
        initObservation()
    }

    private fun initView() {
        bookAdapter = BookAdapter {
            findNavController().navigate(R.id.action_bookFragment_to_bookDetailFragment)
        }
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.bookRecyclerView.layoutManager = layoutManager
        binding.bookRecyclerView.adapter = bookAdapter
    }

    private fun initEvent() {
        viewModel.getBook();
    }

    private fun initObservation() {
        viewModel.books.observe(viewLifecycleOwner) {
            bookAdapter.submitList(it.hydraMember)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}