package com.samsung.android.gallery.settings.ui;

import A.a;
import Fa.C0555i;
import Fa.C0556j;
import O3.l;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentFeaturesFragment extends BasePreferenceFragment<IBasePreferenceView> {
    private void initPreference(Context context) {
        computePreference(context, SettingPreference.EditSuggestions, new C0555i(this, 0));
        computePreference(context, SettingPreference.ObjectEraser, new C0555i(this, 1));
        computePreference(context, SettingPreference.BestFace, new C0555i(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$0(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startEditSuggestions(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$1(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new C0556j(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$2(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startObjectEraser(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$3(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new C0556j(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$initPreference$4(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startBestFace(preference.getContext());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initPreference$5(Preference preference, SettingPreference settingPreference) {
        preference.setOnPreferenceClickListener(new C0556j(this, 1));
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.intelligent_features_screen);
            initPreference(getContext());
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadPreference failed e="), this.TAG);
        }
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_INTELLIGENT_FEATURES.toString();
    }

    public int getTitleId() {
        return R$string.intelligent_features;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new HighlightGroupAdapter(preferenceScreen);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        showHighlightIfGuided(getArguments());
    }
}
