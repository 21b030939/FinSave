package com.example.finsave.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.finsave.data.model.db.SpendingApplication
import com.example.finsave.databinding.FragmentRecordPageBinding
import com.example.finsave.data.model.entity.Spending
import com.example.finsave.ui.viewmodel.SpendingViewModel
import com.example.finsave.ui.viewmodel.SpendingViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecordPageFragment : Fragment() {

    private var _binding: FragmentRecordPageBinding? = null
    private lateinit var viewModel: SpendingViewModel
    private val binding get() = _binding!!

    companion object{
        fun newInstance() = RecordPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,
            SpendingViewModelFactory((requireActivity().application as SpendingApplication).repository)
        ).get(SpendingViewModel::class.java)

        val editTextName = binding.spendingName
        val editTextCategory = binding.spendingCategory
        val editTextCost = binding.spendingCost
        val editTextValue = binding.spendingValue

        binding.recordButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val spending = Spending(
                    name = editTextName.text.toString(),
                    category = editTextCategory.text.toString(),
                    price = editTextCost.text.toString() + "tg",
                    value = editTextValue.text.toString() + "%"
                )
                viewModel.insert(spending)

                withContext(Dispatchers.Main) {
                    editTextCategory.text.clear()
                    editTextName.text.clear()
                    editTextCost.text.clear()
                    editTextValue.text.clear()
                }
            }
        }
    }
}