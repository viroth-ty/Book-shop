package org.viroth.bookstore.app.view.management

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.viroth.bookstore.app.R

class ManagementFragment : Fragment() {

    companion object {
        fun newInstance() = ManagementFragment()
    }

    private lateinit var viewModel: ManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.management_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ManagementViewModel::class.java)
        // TODO: Use the ViewModel
    }

}