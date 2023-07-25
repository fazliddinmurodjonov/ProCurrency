package com.programmsoft.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.programmsoft.adapters.SpinnerAdapter
import com.programmsoft.models.ExchangeCurrencyModel
import com.programmsoft.procurrency.MainActivity.Companion.bottomSpinnerPosition
import com.programmsoft.procurrency.MainActivity.Companion.topSpinnerPosition
import com.programmsoft.procurrency.databinding.FragmentCalculatorBinding
import com.programmsoft.utils.App
import com.programmsoft.utils.Components


class CalculatorFragment : Fragment() {
    private lateinit var binding: FragmentCalculatorBinding
    val list = Components.getCurrencySpinnerList()
    private lateinit var topSpinnerAdapter: SpinnerAdapter
    private lateinit var bottomSpinnerAdapter: SpinnerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        setSelectionSpinners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createdUI()
    }

    private fun createdUI() {
        val position = arguments?.getInt("position")
        if (position != null) {
            topSpinnerPosition = position
        }
        bottomSpinnerPosition = list.size - 1
        setTextBuyAndSell()
        spinnerAdapters()
        clickBtns()
        val exchangedCurrency = ObservableField<String>()
        val exchangeCurrencyModel = ExchangeCurrencyModel(exchangedCurrency)
        binding.exchangeCurrencyModel = exchangeCurrencyModel
        binding.lifecycleOwner = this
    }

    private fun setTextBuyAndSell() {
        val currency =
            Components.db.currencyDao().getCurrencyByCode(list[topSpinnerPosition].code!!)
        binding.topCurrency = currency
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun clickBtns() {
        binding.topSpinner.setOnTouchListener { _, p1 ->
            if (p1!!.action == MotionEvent.ACTION_UP) {
                Components.topSpinnerDialog(requireActivity().supportFragmentManager)
                true
            } else {
                false
            }
        }
        binding.bottomSpinner.setOnTouchListener { _, p1 ->
            if (p1!!.action == MotionEvent.ACTION_UP) {
                Components.bottomSpinnerDialog(requireActivity().supportFragmentManager)
                true
            } else {
                false
            }
        }
        binding.exchangeBtn.setOnClickListener {
            topSpinnerPosition += bottomSpinnerPosition
            bottomSpinnerPosition = topSpinnerPosition - bottomSpinnerPosition
            topSpinnerPosition -= bottomSpinnerPosition
            setSelectionSpinners()
            editTextDrawable(topSpinnerPosition)
            setTextBuyAndSell()
            setTextToEditText()
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "topSpinnerDialog", this
        ) { _, bundle ->
            topSpinnerPosition = bundle.getInt("topPosition")
            setSelectionSpinners()
            editTextDrawable(topSpinnerPosition)
            setTextBuyAndSell()
            setTextToEditText()
        }
        requireActivity().supportFragmentManager.setFragmentResultListener(
            "bottomSpinnerDialog", this
        ) { _, bundle ->
            bottomSpinnerPosition = bundle.getInt("bottomPosition")
            setSelectionSpinners()
            setTextToEditText()
        }
    }

    private fun setTextToEditText() {
        val editTextNumber = binding.currencyEdt.text.toString()
        binding.currencyEdt.setText(editTextNumber)
    }

    private fun spinnerAdapters() {
        topSpinnerAdapter = SpinnerAdapter(requireActivity(), list)
        bottomSpinnerAdapter = SpinnerAdapter(requireContext(), list)
        binding.topSpinnerAdapter = topSpinnerAdapter
        binding.bottomSpinnerAdapter = bottomSpinnerAdapter
        editTextDrawable(topSpinnerPosition)
        setSelectionSpinners()
    }

    @SuppressLint("DiscouragedApi")
    fun editTextDrawable(position: Int) {
        val flagName = if (list[position].code == "TRY") {
            App.instance.resources.getIdentifier(
                "flag_${list[position].code!!.lowercase()}",
                "drawable",
                App.instance.packageName
            )
        } else {
            App.instance.resources.getIdentifier(
                list[position].code!!.lowercase(),
                "drawable",
                App.instance.packageName
            )
        }
        val flagDrawable = getDrawable(requireContext(), flagName)
        binding.currencyEdt.setCompoundDrawablesWithIntrinsicBounds(flagDrawable, null, null, null)
    }

    private fun setSelectionSpinners() {
        binding.topSpinner.post {
            binding.topSpinner.setSelection(topSpinnerPosition, true)
        }
        binding.bottomSpinner.post {
            binding.bottomSpinner.setSelection(bottomSpinnerPosition, true)
        }
    }

    override fun onResume() {
        super.onResume()
        setSelectionSpinners()
    }

    override fun onStop() {
        super.onStop()
        topSpinnerPosition = 0
        bottomSpinnerPosition = list.size - 1
    }
}