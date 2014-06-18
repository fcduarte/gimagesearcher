package com.fcduarte.gimagesearcher.activity;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.fcduarte.gimagesearcher.R;

public class ImageSearchPreferenceActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getFragmentManager().beginTransaction().replace(android.R.id.content,
			new ImageSearchPreferenceFragment()).commit();
	}

	public static class ImageSearchPreferenceFragment extends
			PreferenceFragment implements OnSharedPreferenceChangeListener {
		@Override
		public void onCreate(final Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			addPreferencesFromResource(R.xml.image_search_preferences);
			
			initSumaries();
		}
		
		@Override
		public void onResume() {
			super.onResume();
			getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onPause() {
			super.onPause();
			getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
		}

		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			Preference preference = findPreference(key);
			updatePreference(preference);
		}
		
		private void initSumaries() {
			for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
			    updatePreference(getPreferenceScreen().getPreference(i));
			}			
		}

		private void updatePreference(Preference preference) {
			if (preference instanceof ListPreference) {
				ListPreference listPreference = (ListPreference) preference;
				listPreference.setSummary(listPreference.getEntry());
			} else if (preference instanceof EditTextPreference) {
				EditTextPreference editTextPreference = (EditTextPreference) preference;
				editTextPreference.setSummary(editTextPreference.getText());
			}
		}
	}

}
