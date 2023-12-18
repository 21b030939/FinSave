package com.example.finsave.ui.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finsave.databinding.MainActivityBinding
import com.example.finsave.R
import com.example.finsave.ui.view.fragment.InvestmentsPageFragment
import com.example.finsave.ui.view.fragment.ProfilePageFragment
import com.example.finsave.ui.view.fragment.RecordPageFragment
import com.example.finsave.ui.view.fragment.StatisticsPageFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val fragmentStatistics = StatisticsPageFragment.newInstance()
    private val fragmentRecord = RecordPageFragment.newInstance()
    private val fragmentInvestment = InvestmentsPageFragment.newInstance()
    private val fragmentProfile = ProfilePageFragment.newInstance()

    private var activeFragment: Fragment = fragmentStatistics

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, fragmentStatistics)
            .add(R.id.fragment_container_view, fragmentRecord)
            .add(R.id.fragment_container_view, fragmentInvestment)
            .add(R.id.fragment_container_view, fragmentProfile)
            .hide(fragmentRecord)
            .hide(fragmentInvestment)
            .hide(fragmentProfile)
            .commit()

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.statistics -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentStatistics)
                        .commit()

                    activeFragment = fragmentStatistics
                    true
                }

                R.id.record -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentRecord)
                        .commit()

                    activeFragment = fragmentRecord
                    true
                }

                R.id.investment -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentInvestment)
                        .commit()

                    activeFragment = fragmentInvestment
                    true
                }

                R.id.profile -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentProfile)
                        .commit()

                    activeFragment = fragmentProfile
                    true
                }

                else -> false

            }

        }

    }

    override fun onStart() {
        super.onStart()
    }
}