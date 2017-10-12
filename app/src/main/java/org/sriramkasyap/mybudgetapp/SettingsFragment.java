package org.sriramkasyap.mybudgetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by srirammeduri on 11/10/17.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        Preference MonthlyBudgetPreference = findPreference(getString(R.string.MonthlyBudget));
        MonthlyBudgetPreference.setOnPreferenceChangeListener(this);

    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String sizeKey = getString(R.string.MonthlyBudget);
        if(preference.getKey().equals(sizeKey)) {
            String EnteredValue = ((String) newValue).trim();
        }
        return false;
    }
}
