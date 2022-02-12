package org.viroth.bookstore.app.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seanghay.statusbar.statusBar
import org.viroth.bookstore.app.R
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.databinding.BookDetailFragmentBinding
import org.viroth.bookstore.app.model.HydraMember
import org.viroth.bookstore.app.view.favourite.FavouriteViewModel

class BookDetailFragment : Fragment() {

    private val viewModel: BookDetailViewModel by viewModels()
    private val favouriteViewModel: FavouriteViewModel by activityViewModels()
    private var _binding: BookDetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val controller: BookInfoController by lazy {
        BookInfoController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        statusBar.color(Color.TRANSPARENT).light(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initEvent()
        initObservation()

    }

    private fun initView() {

        binding.bookDetailRecyclerView.apply {
            layoutManager = layoutManager
            setController(controller = controller)
        }
    }

    private fun initEvent() {

        val id = requireArguments().getString(Constant.Book.BOOKING_ID)
        val isbn = arguments?.getString(Constant.Book.BOOKING_ISBN)

        binding.favouriteButton.setOnClickListener {
            if (id != null &&  isbn != null) {
                viewModel.updateFavourite(bookId = id, isbn = isbn)
            }
        }

        id?.let {
            viewModel.getBookInformation(bookId = id, isbn = isbn.toString())
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservation() {
        viewModel.bookInformation.observe(viewLifecycleOwner) { information ->
            controller.submitData(information)
        }
        viewModel.isFavourite.observe(viewLifecycleOwner) { isFavourite ->
            binding.favouriteButton.setImageResource(if (isFavourite) R.drawable.ic_active_favourite else R.drawable.ic_inactive_favourite)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}