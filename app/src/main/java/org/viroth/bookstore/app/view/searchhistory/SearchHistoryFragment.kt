package org.viroth.bookstore.app.view.searchhistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.viroth.bookstore.app.R

class SearchHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = SearchHistoryFragment()
    }

    private lateinit var viewModel: SearchHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_history_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}