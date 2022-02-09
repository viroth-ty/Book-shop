package org.viroth.bookstore.app.view.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seanghay.statusbar.statusBar
import org.viroth.bookstore.app.data.local.Constant
import org.viroth.bookstore.app.databinding.BookDetailFragmentBinding

class BookDetailFragment : Fragment() {

    private val viewModel: BookDetailViewModel by viewModels()
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
        _binding = BookDetailFragmentBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView();
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
        val id = arguments?.getString(Constant.Book.BOOKING_ID)
        id?.let { viewModel.getBookInformation(it) }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservation() {
        viewModel.bookInformation.observe(viewLifecycleOwner) { information ->
            controller.submitData(information)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}