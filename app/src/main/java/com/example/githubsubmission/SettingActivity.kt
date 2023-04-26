package com.example.githubsubmission

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.githubsubmission.databinding.ActivitySettingBinding
import com.example.githubsubmission.utils.SettingPreferences
import com.example.githubsubmission.viewmodel.MainViewModel
import com.example.githubsubmission.viewmodel.SettingVMFactory
import com.example.githubsubmission.viewmodel.SettingViewModel
import com.example.githubsubmission.viewmodel.ViewModelFactory
import com.google.android.material.switchmaterial.SwitchMaterial

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingActivity : AppCompatActivity() {

    private val pref by lazy {
        SettingPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.Setting)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)

        switchTheme.isChecked = pref.getBoolean("pref_is_dark")
        switchTheme.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked == true) {
                pref.getValue("pref_is_dark", true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                pref.getValue("pref_is_dark", false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
//        val pref = SettingPreferences.getInstance(dataStore)
//        val settingViewModel = ViewModelProvider(this, SettingVMFactory(pref)).get(
//            SettingViewModel::class.java
//        )
//
//        settingViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
//            if (isDarkModeActive) {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                switchTheme.isChecked = true
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                switchTheme.isChecked = false
//            }
//        }
//
//        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
//            settingViewModel.saveThemeSetting(isChecked)
//        }
}

override fun onOptionsItemSelected(back: MenuItem): Boolean {
    when (back.itemId) {
        android.R.id.home -> {
            finish()
        }
    }
    return super.onOptionsItemSelected(back)
}
}