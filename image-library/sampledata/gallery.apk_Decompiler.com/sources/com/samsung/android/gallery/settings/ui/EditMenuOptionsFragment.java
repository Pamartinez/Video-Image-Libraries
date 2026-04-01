package com.samsung.android.gallery.settings.ui;

import A.a;
import Fa.C0553g;
import Fa.C0554h;
import O3.l;
import android.content.Context;
import android.os.Bundle;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditMenuOptionsFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private void initPreference(Context context) {
        computePreference(context, SettingPreference.BaiduCloud, new C0553g(this, 0));
        computePreference(context, SettingPreference.TencentCloud, new C0553g(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$0(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_EDIT_MENU_OPTIONS_BAIDU.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$1(CheckBoxPreference checkBoxPreference, SettingPreference settingPreference) {
        checkBoxPreference.setChecked(settingPreference.isEnabled());
        checkBoxPreference.setOnPreferenceChangeListener(new C0554h(this, settingPreference, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$2(SettingPreference settingPreference, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_EDIT_MENU_OPTIONS_TENCENT.toString(), bool.booleanValue());
        return settingPreference.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$3(CheckBoxPreference checkBoxPreference, SettingPreference settingPreference) {
        checkBoxPreference.setChecked(settingPreference.isEnabled());
        checkBoxPreference.setOnPreferenceChangeListener(new C0554h(this, settingPreference, 0));
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.edit_menu_options_screen);
            initPreference(getContext());
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadPreference failed e="), this.TAG);
        }
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING.toString();
    }

    public int getTitleId() {
        return R$string.edit_menu_options;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }
}
