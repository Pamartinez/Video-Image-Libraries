package com.samsung.android.gallery.settings.widget;

import Fb.h;
import android.text.TextUtils;
import androidx.appcompat.widget.SearchView;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.samsung.android.gallery.module.commandline.CommandLine;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LabsSearchDelegate {
    private static final HashSet<String> ALWAYS_VISIBLE_PREF_KEY = new HashSet<String>() {
        {
            add("labs_preference_screen");
            add("labs_developer_master_switch");
            add("labs_search_bar");
        }
    };
    private PreferenceScreen mPreferenceScreen;
    private final SearchView.OnQueryTextListener mQueryTextListener = new SearchView.OnQueryTextListener() {
        public boolean onQueryTextChange(String str) {
            LabsSearchDelegate.this.updatePreferenceVisible(str);
            return false;
        }

        public boolean onQueryTextSubmit(String str) {
            new CommandLine().onCmd(LabsSearchDelegate.this.mSearchView.getContext(), str);
            return false;
        }
    };
    /* access modifiers changed from: private */
    public SearchView mSearchView;

    private boolean checkVisible(Preference preference, String str) {
        if (ALWAYS_VISIBLE_PREF_KEY.contains(preference.getKey())) {
            return true;
        }
        String str2 = (String) preference.getTitle();
        String str3 = (String) preference.getSummary();
        if ((TextUtils.isEmpty(str2) || !str2.toLowerCase().contains(str.toLowerCase())) && (TextUtils.isEmpty(str3) || !str3.toLowerCase().contains(str.toLowerCase()))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean onCloseButtonClicked() {
        updatePreferenceVisible("");
        return false;
    }

    private void setVisibleAll() {
        int preferenceCount = this.mPreferenceScreen.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = this.mPreferenceScreen.getPreference(i2);
            if (preference instanceof PreferenceCategory) {
                PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
                int preferenceCount2 = preferenceCategory.getPreferenceCount();
                for (int i7 = 0; i7 < preferenceCount2; i7++) {
                    preferenceCategory.getPreference(i7).setVisible(true);
                }
            }
            preference.setVisible(true);
        }
    }

    /* access modifiers changed from: private */
    public void updatePreferenceVisible(String str) {
        if (TextUtils.isEmpty(str)) {
            setVisibleAll();
            return;
        }
        int preferenceCount = this.mPreferenceScreen.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = this.mPreferenceScreen.getPreference(i2);
            if (!ALWAYS_VISIBLE_PREF_KEY.contains(preference.getKey())) {
                if (preference instanceof PreferenceCategory) {
                    PreferenceCategory preferenceCategory = (PreferenceCategory) preference;
                    int preferenceCount2 = preferenceCategory.getPreferenceCount();
                    int i7 = 0;
                    boolean z = false;
                    while (true) {
                        if (i7 >= preferenceCount2) {
                            break;
                        }
                        Preference preference2 = preferenceCategory.getPreference(i7);
                        if (preference2 != null) {
                            if (ALWAYS_VISIBLE_PREF_KEY.contains(preference2.getKey())) {
                                z = true;
                                break;
                            }
                            boolean checkVisible = checkVisible(preference2, str);
                            if (checkVisible) {
                                z = true;
                            }
                            preference2.setVisible(checkVisible);
                        }
                        i7++;
                    }
                    preference.setVisible(z);
                } else {
                    preference.setVisible(checkVisible(preference, str));
                }
            }
        }
    }

    public void setSearchView(SearchView searchView, PreferenceScreen preferenceScreen) {
        this.mSearchView = searchView;
        searchView.setOnQueryTextListener(this.mQueryTextListener);
        this.mSearchView.setOnCloseListener(new h(16, this));
        this.mPreferenceScreen = preferenceScreen;
    }
}
