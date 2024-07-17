package com.project.tzgamblingcompany.presentation.ui.common

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
class MainActivity : ComponentActivity() {
    companion object {
        lateinit var context: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContent {
            Navigation()
          Log.d("kjk",getWebBrowserApps(this).toString())
        }
    }


    fun getWebBrowserApps(context: Context): List<String> {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = android.net.Uri.parse("http://")
        }

        val packageManager: PackageManager = context.packageManager
        val resolveInfoList: List<ResolveInfo> =
            packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolveInfoList.map { resolveInfo ->
            val packageName = resolveInfo.activityInfo.packageName
            val appLabel =
                packageManager.getApplicationLabel(resolveInfo.activityInfo.applicationInfo)
                    .toString()
            "$appLabel ($packageName)"
        }
    }
}