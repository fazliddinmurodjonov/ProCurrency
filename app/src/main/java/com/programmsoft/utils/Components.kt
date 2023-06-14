package com.programmsoft.utils

import android.app.Activity
import android.content.Intent
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.programmsoft.fragments.DialogFragment
import com.programmsoft.procurrency.R
import com.programmsoft.procurrency.databinding.ActivityMainBinding

object Components {
    private fun drawerClose(context: Activity) {
        val drawer = context.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
    }

    fun clickMenu(binding: ActivityMainBinding,activity:Activity,fragmentManager: FragmentManager) {
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
            putExtra(Intent.EXTRA_TEXT,
                "Pro Currencies")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        App.instance.startActivity(shareIntent)
    }

    private fun aboutAppDialog(fragmentManager: FragmentManager) {
        val dialogFragment = DialogFragment()
        dialogFragment.show(fragmentManager, "About App Dialog")
    }

}