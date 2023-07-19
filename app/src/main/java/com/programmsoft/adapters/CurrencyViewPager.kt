package com.programmsoft.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.programmsoft.fragments.CurrencySliderFragment
import com.programmsoft.room.entity.Currency


class CurrencyViewPager(var list: ArrayList<Currency>, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return CurrencySliderFragment.newInstance(list[position].code.toString(), "sd")
    }

    fun refreshFragment(index: Int) {
        notifyItemChanged(index)
    }
}