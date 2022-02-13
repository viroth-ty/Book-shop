package org.viroth.bookstore.app.view.detail

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seanghay.statusbar.statusBar
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.databinding.TopBookDetailFragmentBinding
import org.viroth.bookstore.app.util.Util

class TopBookDetailFragment : Fragment() {

    private val viewModel: TopBookDetailViewModel by viewModels()
    private var _binding: TopBookDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.color(Color.TRANSPARENT).light(false)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TopBookDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
        initObservable()
    }

    private fun initView() {

    }

    private fun initEvent() {

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        val id = requireArguments().getInt(Constant.Book.BOOKING_ID)
        viewModel.getBook(id = id)
    }

    private fun initObservable() {
        viewModel.detail.observe(viewLifecycleOwner) {
            binding.apply {
                bookAuthorTextView.text = it.author
                bookPlaceholderTextView.text = Util.splitTheWord(it.title)
                bookImageView.setBackgroundColor(Util.generateColorFromString(it.title))
                bookTitleTextView.text = it.title
                numberOfBorrowTextView.text = String.format(requireContext().getString(R.string.borrowed), it.borrowCount)
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if(it){
                Toast.makeText(requireContext(), "${viewModel.errorMessage.value}", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding.loadingProgress.root.visibility = if(it) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}