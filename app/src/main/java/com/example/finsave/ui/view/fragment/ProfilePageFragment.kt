package com.example.finsave.ui.view.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.finsave.R
import com.example.finsave.databinding.FragmentProfilePageBinding
import com.google.firebase.auth.FirebaseAuth

class ProfilePageFragment : Fragment() {

    private var _binding: FragmentProfilePageBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = ProfilePageFragment()
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfilePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load data from SharedPreferences
        loadData()

        // Initialize gender spinner
        initializeGenderSpinner()

        // Set listeners for saving data on changes
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                saveData()
            }
        }

        binding.userName.addTextChangedListener(textWatcher)
        binding.userSurname.addTextChangedListener(textWatcher)
        binding.birthDateEditText.addTextChangedListener(textWatcher)

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                saveData()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        binding.userImage.setOnClickListener {
            openImagePicker()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            val selectedImageUri = data?.data
            binding.userImage.setImageURI(selectedImageUri)

            // Save the image URI to SharedPreferences
            val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("ProfileImageUri", selectedImageUri.toString()).apply()
        }
    }

    private fun openImagePicker() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_MEDIA_IMAGES), PERMISSION_CODE)
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, IMAGE_PICK_CODE)
        }
    }


    private fun initializeGenderSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerGender.adapter = adapter
        }
    }

    private fun saveData() {
        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("Name", binding.userName.text.toString())
        editor.putString("Surname", binding.userSurname.text.toString())
        editor.putString("Email", binding.userEmail.text.toString())
        editor.putString("BirthDate", binding.birthDateEditText.text.toString())
        editor.putString("Gender", binding.spinnerGender.selectedItem.toString())

        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        binding.userName.setText(sharedPreferences.getString("Name", ""))
        binding.userSurname.setText(sharedPreferences.getString("Surname", ""))
        binding.userEmail.text = sharedPreferences.getString("Email", "")
        binding.birthDateEditText.setText(sharedPreferences.getString("BirthDate", ""))

        val imageUriString = sharedPreferences.getString("ProfileImageUri", null)
        imageUriString?.let {
            binding.userImage.setImageURI(Uri.parse(it))
        }

        // Set the spinner value
        val genderArray = resources.getStringArray(R.array.gender_array)
        binding.spinnerGender.setSelection(genderArray.indexOf(sharedPreferences.getString("Gender", "")))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission was granted, open the image picker
                openImagePicker()
            } else {
                // Permission was denied
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}