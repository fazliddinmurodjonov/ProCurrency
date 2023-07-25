package com.programmsoft.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmsoft.adapters.AllCurrenciesAdapter
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.FragmentAllCurrenciesBinding
import com.programmsoft.room.entity.Currency
import com.programmsoft.utils.Components


class AllCurrenciesFragment : Fragment() {
    private lateinit var binding: FragmentAllCurrenciesBinding
    private lateinit var allCurrenciesAdapter: AllCurrenciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentAllCurrenciesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allCurrenciesAdapter = AllCurrenciesAdapter()
        searchMenu()
        adapter()
    }

    @SuppressLint("CheckResult", "UseRequireInsteadOfGet")
    private fun adapter() {
        allCurrenciesAdapter.submitList(Components.db.currencyDao().getAllCurrency())
        binding.allCurrenciesRV.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.allCurrenciesAdapter = allCurrenciesAdapter
        allCurrenciesAdapter.setOnItemCalculatorClickListener { position ->
            //  MainActivity.binding.appBarMain.contentMain.btnNav.selectedItemId = R.id.nav_calculator
            val bundle = bundleOf("position" to position)
            findNavController()
                .navigate(
                    R.id.nav_calculator,
                    bundle,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.nav_main, true)
                        .build()
                )
        }
    }

    private fun searchMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                searchView.queryHint = "Search"
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        filter(newText)
                        return true
                    }
                })
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        true
                    }

                    else -> false
                }

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun filter(text: String) {
        val filteredList = ArrayList<Currency>()
        val currenciesList = Components.db.currencyDao().getAllCurrency()
        var removeSpaceText = ""
        for (c in text) {
            if (c != ' ') {
                removeSpaceText += c.toString()
            }
        }
        for (currency in currenciesList) {
            if (currency.title?.lowercase()
                    ?.contains(removeSpaceText.lowercase()) == true && text != " " && removeSpaceText != ""
            ) {
                filteredList.add(currency)
            }
        }
        allCurrenciesAdapter.submitList(filteredList)
    }

}