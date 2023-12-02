package com.example.finsave.demo.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.example.finsave.R
import com.example.finsave.SpendingApplication
import com.example.finsave.databinding.FragmentProfilePageBinding
import com.example.finsave.databinding.FragmentRecordPageBinding
import com.example.finsave.databinding.FragmentStatisticsPageBinding
import com.example.finsave.demo.activity.MainActivity
import com.example.finsave.demo.model.db.entity.Spending
import com.example.finsave.viewmodel.SpendingViewModel
import com.example.finsave.viewmodel.SpendingViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordPageFragment : Fragment() {

    private var _binding: FragmentRecordPageBinding? = null
    private lateinit var viewModel: SpendingViewModel
    private val binding
        get() = _binding!!

    companion object{
        fun newInstance() = RecordPageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_record_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this,
            SpendingViewModelFactory(SpendingApplication())
        ).get(SpendingViewModel::class.java)

        val editTextName = binding.spendingName
        val editTextCategory = binding.spendingCategory
        val editTextCost = binding.spendingCost
        val editTextValue = binding.spendingValue

        binding.recordButton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                val spending = Spending(
                    name = editTextName.text.toString(),
                    category = editTextCategory.text.toString(),
                    price = editTextCost.text.toString(),
                    value = editTextValue.text.toString()
                )
                viewModel.insert(spending)

                editTextCategory.text.clear()
                editTextName.text.clear()
                editTextCost.text.clear()
                editTextValue.text.clear()
            }
        }
    }
}