package com.samsung.android.gallery.settings.ui;

import E9.a;
import Fa.X;
import O3.l;
import android.os.Bundle;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.R$string;
import com.samsung.android.gallery.settings.R$xml;
import com.samsung.android.gallery.settings.ui.abstaction.ISharingNotificationView;
import com.samsung.android.gallery.settings.widget.MainSwitchPreference;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingNotificationFragment<V extends ISharingNotificationView> extends BasePreferenceFragment<V> implements ISharingNotificationView {
    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadMainSwitchPreference$0(Preference preference, Object obj) {
        onCheckedChanged(preference, ((Boolean) obj).booleanValue());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadMainSwitchPreference$1(Preference preference) {
        onCheckedChanged(preference, ((SwitchPreferenceCompat) preference).isChecked());
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadMainSwitchPreference$2(MainSwitchPreference mainSwitchPreference) {
        boolean isEnabled = SettingPreference.SharedNotification.isEnabled();
        if (mainSwitchPreference.isChecked() != isEnabled) {
            mainSwitchPreference.setChecked(isEnabled);
        }
        mainSwitchPreference.setOnPreferenceChangeListener(new X(this, 3));
        mainSwitchPreference.setOnPreferenceClickListener(new X(this, 4));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$3(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_NEW_ALBUMS.toString(), bool.booleanValue());
        return SettingPreference.SharedNotificationAlbum.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$4(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_NEW_POSTS.toString(), bool.booleanValue());
        return SettingPreference.SharedNotificationPosting.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadPreference$5(Preference preference, Object obj) {
        Boolean bool = (Boolean) obj;
        postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_MEMBER_UPDATES.toString(), bool.booleanValue());
        return SettingPreference.SharedNotificationMember.setAndNotifyIfChanged(preference.getContext(), bool.booleanValue());
    }

    private void loadMainSwitchPreference() {
        Optional.ofNullable((MainSwitchPreference) findPreference(PreferenceName.SHOW_NOTIFICATION_MAIN_SWITCH.key())).ifPresent(new a(13, this));
    }

    private void loadPreference() {
        Optional.ofNullable(getPreferenceScreen()).ifPresent(new l(7));
        try {
            addPreferencesFromResource(R$xml.shared_album_notification);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("Failed to add preference e="), this.TAG);
        }
        setMainSwitchPreferenceChecked(SettingPreference.SharedNotification.isEnabled());
        setTwoStatePreference(SettingPreference.SharedNotificationAlbum, new X(this, 0));
        setTwoStatePreference(SettingPreference.SharedNotificationPosting, new X(this, 1));
        setTwoStatePreference(SettingPreference.SharedNotificationMember, new X(this, 2));
    }

    private void onCheckedChanged(Preference preference, boolean z) {
        if (SettingPreference.SharedNotification.setAndNotifyIfChanged(preference.getContext(), z)) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SETTING_SHARED_ALBUM_NOTIFICATIONS_MASTER_SWITCH.toString(), z);
            ((SwitchPreferenceCompat) preference).setChecked(z);
        }
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SETTING_SHARED_ALBUM_NOTIFICATION.toString();
    }

    public int getTitleId() {
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            return R$string.shared_album_notification;
        }
        return R$string.share_notification;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        loadPreference();
    }

    public RecyclerView.Adapter onCreateAdapter(PreferenceScreen preferenceScreen) {
        return new HighlightGroupAdapter(preferenceScreen);
    }

    public boolean onHandleEvent(Object obj, Bundle bundle) {
        EventMessage eventMessage = (EventMessage) obj;
        String str = this.TAG;
        Log.d(str, "onHandleEvent " + eventMessage);
        if (eventMessage.what != 7005) {
            return false;
        }
        loadPreference();
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewUtils.setLastRoundedCorner(getListView(), false);
        loadMainSwitchPreference();
        showHighlightIfGuided(getArguments());
    }

    public void setMainSwitchPreferenceChecked(boolean z) {
        try {
            setPreferenceEnabled(SettingPreference.SharedNotificationAlbum.key, z);
            setPreferenceEnabled(SettingPreference.SharedNotificationPosting.key, z);
            setPreferenceEnabled(SettingPreference.SharedNotificationMember.key, z);
        } catch (Exception e) {
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("setMainSwitchPreferenceChecked {");
            sb2.append(z);
            sb2.append("} failed e=");
            A.a.s(e, sb2, str);
        }
    }

    public SharingNotificationPresenter createPresenter() {
        return new SharingNotificationPresenter(this);
    }
}
