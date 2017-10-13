package org.sriramkasyap.mybudgetapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

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
        Toast error = Toast.makeText(getContext(), "Entered Value is invalid. Please enter correct value", Toast.LENGTH_SHORT);
        String MBKey = getString(R.string.MonthlyBudget);
        if(preference.getKey().equals(MBKey)) {
            String EnteredValue = ((String) newValue).trim();
            if(EnteredValue.equals("")) EnteredValue = "0";
            try {
                Double EnteredAmount = Double.parseDouble(EnteredValue);
                if(EnteredAmount <= 0) {
                    error.show();
                }
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                error.show();
                return false;
            }

        }
        return true;
    }
}
