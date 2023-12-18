package com.example.finsave.demo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.R
import com.example.finsave.databinding.FragmentInvestmentsPageBinding
import com.example.finsave.databinding.FragmentProfilePageBinding
import com.example.finsave.databinding.FragmentRecordPageBinding
import com.example.finsave.demo.adapter.StockListAdapter
import com.example.finsave.network.RetrofitInstance
import com.example.finsave.network.StockRepository
import com.example.finsave.viewmodel.SpendingViewModel
import com.example.finsave.viewmodel.StockViewModel
import com.example.finsave.viewmodel.StockViewModelFactory

class InvestmentsPageFragment : Fragment() {

    private var _binding: FragmentInvestmentsPageBinding? = null
    private lateinit var viewModel: StockViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StockListAdapter
    private val binding get() = _binding!!

    companion object{
        fun newInstance() = InvestmentsPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInvestmentsPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        // Initialize RecyclerView and Adapter
        recyclerView = binding.stocksValueRecyclerView
        adapter = StockListAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, StockViewModelFactory(StockRepository(RetrofitInstance.apiService))).get(StockViewModel::class.java)
        viewModel.loadStockList() // API key is no longer needed here

        // Observe LiveData from ViewModel
        viewModel.stockList.observe(viewLifecycleOwner, { stockItems ->
            adapter.updateStocks(stockItems)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}