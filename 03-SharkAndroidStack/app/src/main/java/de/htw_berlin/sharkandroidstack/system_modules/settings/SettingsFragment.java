package de.htw_berlin.sharkandroidstack.system_modules.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import de.htw_berlin.sharkandroidstack.R;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.system_module_settings);
    }
}