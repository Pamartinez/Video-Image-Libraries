package com.samsung.android.gallery.settings.ui.abstaction;

import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPrefApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IBasePreferenceView extends IBaseView {
    <T extends Preference> T findPreference(String str);

    Blackboard getBlackboard();

    void postAnalyticsLog(String str, boolean z);

    void setPreferenceCategoryVisible(String str);

    void setPreferenceEnabled(String str, boolean z);

    void setPreferenceScreenEnabled(boolean z);

    void setPreferenceSummary(String str, String str2);

    void setPreferenceVisible(String str, boolean z);

    void setSwitchPreferenceChecked(SettingPrefApi settingPrefApi);

    void setSwitchPreferenceChecked(String str, boolean z);
}
