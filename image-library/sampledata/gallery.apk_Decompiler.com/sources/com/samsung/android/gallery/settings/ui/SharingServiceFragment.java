package com.samsung.android.gallery.settings.ui;

import A.a;
import A4.C0371f;
import A4.H;
import A4.L;
import A4.O;
import A4.Q;
import B8.g;
import O3.l;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import androidx.preference.PreferenceScreen;
import androidx.preference.SeslSwitchPreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.settings.ui.abstaction.IBaseView;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.settings.widget.MainSwitchPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingServiceFragment extends BasePreferenceFragment<IBasePreferenceView> {
    final SettingPreference mServiceEnabler = SettingPreference.SharingServiceEnabler;

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadPreference$0(boolean z, SeslSwitchPreferenceScreen seslSwitchPreferenceScreen) {
        seslSwitchPreferenceScreen.setEnabled(z);
        seslSwitchPreferenceScreen.setChecked(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreference$1(PreferenceCategory preferenceCategory, boolean z) {
        preferenceCategory.setVisible(z);
        Optional.ofNullable((SeslSwitchPreferenceScreen) findPreference(SettingPreference.SharedNotification.key)).ifPresent(new L(z, 10));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$2(Context context, PreferenceCategory preferenceCategory, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        boolean booleanValue = bool.booleanValue();
        if (booleanValue == this.mServiceEnabler.isEnabled()) {
            return true;
        }
        Log.d(this.TAG, "master enabled", bool);
        setSharingNotificationEnabled(context, booleanValue);
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_SERVICE_SWITCH.toString(), bool.booleanValue());
        this.mServiceEnabler.setAndNotifyIfChanged(context, booleanValue);
        ThreadUtil.postOnUiThread(new g((Object) this, (Object) preferenceCategory, booleanValue, 2));
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreference$3(Context context, PreferenceCategory preferenceCategory, MainSwitchPreference mainSwitchPreference) {
        mainSwitchPreference.setChecked(this.mServiceEnabler.isEnabled());
        mainSwitchPreference.setOnPreferenceChangeListener(new Q((Object) this, (Object) context, (Object) preferenceCategory, 3));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$4(Context context, Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_SWITCH.toString(), bool.booleanValue());
        setSharingNotificationEnabled(context, bool.booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$5(Preference preference) {
        return new NavigateAppCmd((IBaseView) this).startNotifications(preference.getContext(), ((SeslSwitchPreferenceScreen) preference).isChecked());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadPreference$6(Context context, SwitchPreferenceCompat switchPreferenceCompat, SettingPreference settingPreference) {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            switchPreferenceCompat.setTitle(R$string.shared_album_notification);
        }
        switchPreferenceCompat.setChecked(settingPreference.isEnabled());
        switchPreferenceCompat.setOnPreferenceChangeListener(new H(11, (Object) this, (Object) context));
        switchPreferenceCompat.setOnPreferenceClickListener(new O(29, this));
    }

    private void loadPreference(Context context) {
        if (context == null) {
            Log.e(this.TAG, "loadPreference failed. null context");
            return;
        }
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.sharing_service);
        } catch (Exception e) {
            a.s(e, new StringBuilder("Failed to add preference e="), this.TAG);
        }
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreference("cat_shared_album_notification");
        Optional.ofNullable((MainSwitchPreference) findPreference("sharing_service_enabler")).ifPresent(new C0371f((Object) this, (Object) context, (Object) preferenceCategory, 2));
        if (preferenceCategory != null) {
            preferenceCategory.setVisible(this.mServiceEnabler.isEnabled());
            computePreference((PreferenceGroup) preferenceCategory, SettingPreference.SharedNotification, new A9.a(4, this, context));
        }
    }

    private void setSharingNotificationEnabled(Context context, boolean z) {
        SettingPreference.SharedNotification.setAndNotifyIfChanged(context, z);
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_SHARED_ALBUM_SERVICE.toString();
    }

    public int getTitleId() {
        return R$string.shared_album;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference(getContext());
    }

    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new HighlightGroupAdapter(preferenceScreen);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewUtils.setLastRoundedCorner(getListView(), false);
        showHighlightIfGuided(getArguments());
    }

    public SharingServicePresenter createPresenter() {
        return new SharingServicePresenter(this);
    }
}
