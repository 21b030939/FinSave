package com.example.finsave.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.finsave.databinding.FragmentStatisticsPageBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.SpendingApplication
import com.example.finsave.demo.adapter.SpendingListAdapter
import com.example.finsave.demo.model.db.entity.Spending
import com.example.finsave.viewmodel.SpendingViewModel
import com.example.finsave.viewmodel.SpendingViewModelFactory


class StatisticsPageFragment : Fragment() {

    private var _binding: FragmentStatisticsPageBinding? = null
    private val viewModel: SpendingViewModel by viewModels {
        SpendingViewModelFactory((application as SpendingApplication).repository)
    }
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance() = StatisticsPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_statistics_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.spendings_recycler_view)
        val adapter = SpendingListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allSpending.observe(viewLifecycleOwner) { spending ->
            spending.let { adapter.submitList(it) }
        }
    }

}