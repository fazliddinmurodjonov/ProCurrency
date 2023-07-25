package com.programmsoft.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.BindingAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.programmsoft.fragments.DialogFragment
import com.programmsoft.models.CurrencyCode
import com.programmsoft.models.CurrencySpinner
import com.programmsoft.procurrency.MainActivity
import com.programmsoft.procurrency.MainActivity.Companion.bottomSpinnerPosition
import com.programmsoft.procurrency.MainActivity.Companion.topSpinnerPosition
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ActivityMainBinding
import com.programmsoft.retrofit.ApiClient
import com.programmsoft.room.db.DB
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Components {

    const val BASE_URL = "https://nbu.uz/uz/exchange-rates/"
    val serviceOfCurrencies = ApiClient.serviceOfCurrencies
    val db = DB.getInstance(App.instance)

    fun getCurrencySpinnerList(): ArrayList<CurrencySpinner> {
        val currencySpinnerList = db.currencyDao().getAllCurrencyForSpinner()
        val list = ArrayList<CurrencySpinner>()
        for (currencySpinner in currencySpinnerList) {
            list.add(currencySpinner)
        }
        return list
    }

    private fun drawerClose(context: Activity) {
        val drawer = context.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }

    fun clickMenu(
        binding: ActivityMainBinding,
        activity: Activity,
        fragmentManager: FragmentManager,
    ) {
        val shareItem = binding.navView.menu.findItem(R.id.nav_share)
        shareItem.setOnMenuItemClickListener {
            shareAppLink()
            drawerClose(activity)
            true
        }
        val aboutItem = binding.navView.menu.findItem(R.id.nav_about)
        aboutItem.setOnMenuItemClickListener {
            aboutAppDialog(fragmentManager)
            drawerClose(activity)
            true
        }
    }

    private fun shareAppLink() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Pro Currencies"
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        App.instance.startActivity(shareIntent)
    }

    private fun aboutAppDialog(fragmentManager: FragmentManager) {
        val dialogFragment = DialogFragment()
        dialogFragment.show(fragmentManager, "about_app")
    }

    fun topSpinnerDialog(fragmentManager: FragmentManager) {
        val dialogFragment = DialogFragment()
        dialogFragment.show(fragmentManager, "top_spinner_dialog")
    }

    fun bottomSpinnerDialog(fragmentManager: FragmentManager) {
        val dialogFragment = DialogFragment()
        dialogFragment.show(fragmentManager, "bottom_spinner_dialog")
    }

    fun getCountryCodeList(): ArrayList<CurrencyCode> {
        val list = ArrayList<CurrencyCode>()
        val currenciesDbList = db.currencyDao().getAllCurrency()
        for (currency in currenciesDbList) {
            list.add(CurrencyCode(currency.code!!))
        }
        return list
    }

    fun getHoursAndMinutes(): String {
        val calendar: Calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

    fun getTimeOrDate(dateInAPI: String, timeOrDate: String): String {
        val parts = dateInAPI.split(" ")
        val dateStr = parts[0]
        val timeStr = parts[1]
        val timeParts = timeStr.split(":")
        val hourMinute = "${timeParts[0]}:${timeParts[1]}"
        return when (timeOrDate) {
            "time" -> {
                hourMinute
            }

            "date" -> {

                dateStr
            }

            else -> {
                ""
            }
        }
    }

}