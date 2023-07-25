package com.programmsoft.fragments

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.programmsoft.adapters.DialogSpinnerAdapter
import com.programmsoft.procurrency.MainActivity.Companion.bottomSpinnerPosition
import com.programmsoft.procurrency.MainActivity.Companion.topSpinnerPosition
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.DialogAboutApplicationBinding
import com.programmsoft.procurrency.databinding.DialogSpinnerBinding
import com.programmsoft.utils.Components


class DialogFragment : DialogFragment() {
    @SuppressLint("UseGetLayoutInflater")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity())

        when (tag) {
            "about_app" -> {
                val dialogView =
                    DialogAboutApplicationBinding.inflate(
                        LayoutInflater.from(requireActivity()),
                        null,
                        false
                    )
                dialog.setContentView(dialogView.root)
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialogView.root.setOnClickListener {
                    dialog.cancel()
                }
                dialogView.aboutApp.setTextColor(
                    ContextCompat.getColor(
                        requireActivity(),
                        R.color.black
                    )
                )
                dialog.show()
            }

            "top_spinner_dialog" -> {
                val list = Components.getCurrencySpinnerList()
                val dialogView =
                    DialogSpinnerBinding.inflate(
                        LayoutInflater.from(requireActivity()),
                        null,
                        false
                    )
                dialog.setContentView(dialogView.root)
                val dialogSpinnerAdapter = DialogSpinnerAdapter(list)
                dialogView.currenciesTitleAdapter = dialogSpinnerAdapter
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialogView.root.setOnClickListener {
                    dialog.cancel()
                }
                dialogSpinnerAdapter.setOnItemClickListener { position ->
                    if (list[position].code != list[bottomSpinnerPosition].code) {
                        setFragmentResult(
                            "topSpinnerDialog",
                            bundleOf("topPosition" to position)
                        )
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }

            "bottom_spinner_dialog" -> {
                val list = Components.getCurrencySpinnerList()
                val dialogView =
                    DialogSpinnerBinding.inflate(
                        LayoutInflater.from(requireActivity()),
                        null,
                        false
                    )
                dialog.setContentView(dialogView.root)
                val dialogSpinnerAdapter = DialogSpinnerAdapter(list)
                dialogView.currenciesTitleAdapter = dialogSpinnerAdapter
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                dialogView.root.setOnClickListener {
                    dialog.cancel()
                }
                dialogSpinnerAdapter.setOnItemClickListener {position->
                    if (list[position].code != list[topSpinnerPosition].code) {
                        setFragmentResult(
                            "bottomSpinnerDialog",
                            bundleOf("bottomPosition" to position)
                        )
                        dialog.dismiss()
                    }
                }
                dialog.show()
            }
        }


        return dialog
    }
}