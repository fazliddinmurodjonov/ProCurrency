package com.programmsoft.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.programmsoft.adapters.CurrencySliderAdapter
import com.programmsoft.adapters.CurrencyViewPager
import com.programmsoft.adapters.ViewPager2Adapter
import com.programmsoft.procurrency.databinding.FragmentMainBinding
import com.programmsoft.room.entity.Currency


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    lateinit var viewPagerAdapter: ViewPager2Adapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //     binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_main)

        //    viewPagerAdapter = ViewPager2Adapter(requireContext())
        val list = ArrayList<Currency>()
        for (i in 0..5) {
            val currency = Currency()
            currency.code = i.toString()
            list.add(currency)
        }
   // val adapter = CurrencyViewPager(list, requireActivity())

    val adapter = CurrencySliderAdapter(list, requireActivity())
    //   binding.currencyViewPager.adapter = adapter
     // binding.currencyViewPager.offscreenPageLimit=1
        binding.currencyViewPager.adapter = adapter
        binding.currencyViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                // Handle page selection event
                // You can perform any necessary actions when a new page is selected
                // For example, update UI components based on the selected page
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Handle page scroll event
                // You can perform any necessary actions while the page is being scrolled
                // For example, update UI components based on the current scroll position
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Handle page scroll state change event
                // You can perform any necessary actions when the scroll state changes
                // For example, pause any ongoing animations or update UI components based on the scroll state
            }
        })
//        view.layoutParams = LinearLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT
//        )


    }
}