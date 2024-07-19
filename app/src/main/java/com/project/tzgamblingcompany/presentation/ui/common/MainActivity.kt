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
import com.project.tzgamblingcompany.presentation.ui.common.navigation.AppNavHost
import com.project.tzgamblingcompany.presentation.ui.deteilrepo.RepositoryDeteilScreen

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var context: Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContent {
          AppNavHost()
        }
    }

}