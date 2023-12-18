package com.example.finsave.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.finsave.databinding.FragmentStatisticsPageBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.data.model.db.SpendingApplication
import com.example.finsave.ui.adapter.SpendingListAdapter
import com.example.finsave.ui.viewmodel.SpendingViewModel
import com.example.finsave.ui.viewmodel.SpendingViewModelFactory


class StatisticsPageFragment : Fragment() {

    private var _binding: FragmentStatisticsPageBinding? = null
    private val viewModel: SpendingViewModel by viewModels {
        SpendingViewModelFactory((requireActivity().application as SpendingApplication).repository)
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
        _binding = FragmentStatisticsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.spendings_recycler_view)
        val adapter = SpendingListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.allSpending.observe(viewLifecycleOwner) { spending ->
            if (spending.isEmpty()) {
                binding.textNoSpendings.visibility = View.VISIBLE
                binding.spendingsRecyclerView.visibility = View.GONE
            } else {
                binding.textNoSpendings.visibility = View.GONE
                binding.spendingsRecyclerView.visibility = View.VISIBLE
            }
            spending.let { adapter.submitList(it) }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}