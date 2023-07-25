package com.programmsoft.utils

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class DateViewModel : ViewModel() {
    val currentTime = ObservableField<String>()

    init {
        // Get the current date and time
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentDateTime: String = dateFormat.format(calendar.time)
        // Set the current date and time to the observable field
        currentTime.set(currentDateTime)
    }
}
