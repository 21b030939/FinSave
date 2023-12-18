package com.example.finsave.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsave.databinding.FragmentInvestmentsPageBinding
import com.example.finsave.ui.adapter.StockListAdapter
import com.example.finsave.data.network.RetrofitInstance
import com.example.finsave.data.network.StockRepository
import com.example.finsave.ui.viewmodel.StockViewModel
import com.example.finsave.ui.viewmodel.StockViewModelFactory

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
        binding.stocksValueRecyclerView.visibility = View.GONE
        binding.lottieAnimationView.visibility = View.VISIBLE
        binding.lottieAnimationView.playAnimation()
        // Initialize RecyclerView and Adapter
        recyclerView = binding.stocksValueRecyclerView
        adapter = StockListAdapter(emptyList())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, StockViewModelFactory(StockRepository(RetrofitInstance.apiService))).get(
            StockViewModel::class.java)
        viewModel.loadStockList() // API key is no longer needed here

        // Observe LiveData from ViewModel
        viewModel.stockList.observe(viewLifecycleOwner, { stockItems ->
            adapter.updateStocks(stockItems)
            Log.d("StockFragment", "Observing data: $stockItems")
            binding.lottieAnimationView.visibility = View.GONE
            binding.lottieAnimationView.cancelAnimation()
            binding.stocksValueRecyclerView.visibility = View.VISIBLE
        })
    }

}