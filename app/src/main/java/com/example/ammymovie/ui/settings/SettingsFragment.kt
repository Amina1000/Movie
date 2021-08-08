package com.example.ammymovie.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.ammymovie.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}