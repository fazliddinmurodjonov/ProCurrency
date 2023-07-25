package com.programmsoft.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.programmsoft.adapters.CurrencyHistoryAdapter
import com.programmsoft.adapters.CurrencyViewPagerAdapter
import com.programmsoft.models.CurrencyCode
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.FragmentMainBinding
import com.programmsoft.procurrency.databinding.ItemCodeTabLayoutBinding
import com.programmsoft.procurrency.databinding.ItemIndicatorTabLayoutBinding
import com.programmsoft.procurrency.databinding.ItemIndicatorTabLayoutSelectedBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.Components
import com.programmsoft.utils.ScalePageTransformer
import com.programmsoft.utils.SharedPreference
import com.programmsoft.viewmodels.CurrenciesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var currencyHistoryAdapter: CurrencyHistoryAdapter
    private lateinit var currencyViewPagerAdapter: CurrencyViewPagerAdapter
    private lateinit var currencyCodeList: List<CurrencyCode>
    var selectedPosition = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        SharedPreference.init(requireContext())
        if (SharedPreference.downloadCurrency == 0) {
            binding.root.visibility = View.INVISIBLE
            uploadData()
        } else {
            currencyCodeList = Components.getCountryCodeList()
            setAdapters()
        }
    }

    private fun uploadData() {
        val currenciesViewModel: CurrenciesViewModel =
            ViewModelProvider(this)[CurrenciesViewModel::class.java]
        currenciesViewModel.getCurrencies().observe(requireActivity())
        {
            SharedPreference.downloadCurrency = 1
            binding.root.visibility = View.VISIBLE
            currencyCodeList = Components.getCountryCodeList()
            setAdapters()
        }

    }

    private fun setAdapters() {
        val list = ArrayList<Currency>()
        for (i in 0 until currencyCodeList.size) {
            val currency = Currency()
            currency.code = i.toString()
            list.add(currency)
        }

        currencyHistoryAdapter = CurrencyHistoryAdapter()
        currencyHistoryAdapter.submitList(Components.db.currencyDao().getAllCurrencyIsNotEmpty())
        binding.historyRVAdapter = currencyHistoryAdapter
        currencyViewPagerAdapter = CurrencyViewPagerAdapter()
        binding.currencyViewPager.offscreenPageLimit = 1
        val scaleFactor = 0.3f
        binding.currencyViewPager.setPageTransformer(ScalePageTransformer(scaleFactor))
        currencyViewPagerAdapter.submitList(Components.db.currencyDao().getAllCurrency())
        binding.currencyViewPager.adapter = currencyViewPagerAdapter
        TabLayoutMediator(
            binding.currencyCodeTabLayout,
            binding.currencyViewPager
        ) { tab, position ->
        }.attach()
        TabLayoutMediator(
            binding.indicatorTabLayout,
            binding.currencyViewPager
        ) { tab, position ->

        }.attach()
        setCodeTab()
        setIndicatorTab()

        binding.currencyViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Handle page selection event
                // You can perform any necessary actions when a new page is selected
                // For example, update UI components based on the selected page
                selectedPosition = position
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int,
            ) {
                // Handle page scroll event
                // You can perform any necessary actions while the page is being scrolled
                // For example, update UI components based on the current scroll position
            }

            override fun onPageScrollStateChanged(state: Int) {
                //     Toast.makeText(requireActivity(), state.toString(), Toast.LENGTH_SHORT).show()
                // Handle page scroll state change event
                // You can perform any necessary actions when the scroll state changes
                // For example, pause any ongoing animations or update UI components based on the scroll state
            }
        })
        binding.indicatorTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val itemIndicatorSelected =
                    DataBindingUtil.inflate<ItemIndicatorTabLayoutSelectedBinding>(
                        LayoutInflater.from(requireContext()),
                        R.layout.item_indicator_tab_layout_selected,
                        null,
                        false
                    )
                tab!!.customView = itemIndicatorSelected.root
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val itemIndicatorTabLayout = DataBindingUtil.inflate<ItemIndicatorTabLayoutBinding>(
                    LayoutInflater.from(requireContext()),
                    R.layout.item_indicator_tab_layout,
                    null,
                    false
                )
                tab!!.customView = itemIndicatorTabLayout.root
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val itemIndicatorSelected =
                    DataBindingUtil.inflate<ItemIndicatorTabLayoutSelectedBinding>(
                        LayoutInflater.from(requireContext()),
                        R.layout.item_indicator_tab_layout_selected,
                        null,
                        false
                    )
                tab!!.customView = itemIndicatorSelected.root
            }

        })

    }

    private fun setCodeTab() {
        val tabCount = binding.currencyCodeTabLayout.tabCount
        for (i in 0 until tabCount) {
            val itemCodeTabLayout = DataBindingUtil.inflate<ItemCodeTabLayoutBinding>(
                LayoutInflater.from(requireActivity()),
                R.layout.item_code_tab_layout,
                null,
                false
            )
            val tabAt = binding.currencyCodeTabLayout.getTabAt(i)
            tabAt?.customView = itemCodeTabLayout.root
            itemCodeTabLayout.currencyCode = currencyCodeList[i]
        }
    }

    override fun onResume() {
        super.onResume()
        binding.currencyViewPager.setCurrentItem(selectedPosition + 1, false)
        binding.currencyViewPager.setCurrentItem(selectedPosition - 1, false)
    }

    private fun setIndicatorTab() {
        val tabCount = binding.indicatorTabLayout.tabCount
        for (i in 0 until tabCount) {
            val itemIndicatorTabLayout = DataBindingUtil.inflate<ItemIndicatorTabLayoutBinding>(
                LayoutInflater.from(requireContext()),
                R.layout.item_indicator_tab_layout,
                null,
                false
            )
            val tabAt = binding.indicatorTabLayout.getTabAt(i)
            tabAt?.customView = itemIndicatorTabLayout.root
        }
    }

}