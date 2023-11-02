package com.example.finsave.demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.finsave.databinding.MainActivityBinding
import com.example.finsave.R
import com.example.finsave.demo.fragment.AnalysisPageFragment
import com.example.finsave.demo.fragment.InvestmentsPageFragment
import com.example.finsave.demo.fragment.ProfilePageFragment
import com.example.finsave.demo.fragment.RecordPageFragment
import com.example.finsave.demo.fragment.StatisticsPageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    private val fragmentStatistics = StatisticsPageFragment.newInstance()
    private val fragmentRecord = RecordPageFragment.newInstance()
    private val fragmentAnalysis = AnalysisPageFragment.newInstance()
    private val fragmentInvestment = InvestmentsPageFragment.newInstance()
    private val fragmentProfile = ProfilePageFragment.newInstance()

    private var activeFragment: Fragment = fragmentStatistics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container_view, fragmentStatistics)
            .add(R.id.fragment_container_view, fragmentRecord)
            .add(R.id.fragment_container_view, fragmentAnalysis)
            .add(R.id.fragment_container_view, fragmentInvestment)
            .add(R.id.fragment_container_view, fragmentProfile)
            .hide(fragmentRecord)
            .hide(fragmentAnalysis)
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

                R.id.analysis -> {
                    supportFragmentManager
                        .beginTransaction()
                        .hide(activeFragment)
                        .show(fragmentAnalysis)
                        .commit()

                    activeFragment = fragmentAnalysis
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


}