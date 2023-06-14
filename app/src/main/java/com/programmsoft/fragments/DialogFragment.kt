package com.programmsoft.fragments

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.DialogAboutApplicationBinding


class DialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireActivity())

        val dialogView =
            DialogAboutApplicationBinding.inflate(LayoutInflater.from(requireActivity()),
                null,
                false)
        dialog.setContentView(dialogView.root)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogView.root.setOnClickListener {
            dialog.cancel()
        }
        dialogView.aboutApp.setTextColor(ContextCompat.getColor(requireActivity(),
            R.color.black))
        dialog.show()
        return dialog
    }
}