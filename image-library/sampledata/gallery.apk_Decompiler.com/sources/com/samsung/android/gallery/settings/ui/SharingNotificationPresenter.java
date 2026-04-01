package com.samsung.android.gallery.settings.ui;

import Fa.Y;
import android.os.Bundle;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.abstaction.ISharingNotificationView;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingNotificationPresenter<V extends ISharingNotificationView> extends BasePresenter<V> {
    public SharingNotificationPresenter(V v) {
        super(v);
    }

    /* access modifiers changed from: private */
    public void onSharingAlbumNotificationChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            ((ISharingNotificationView) this.mView).setSwitchPreferenceChecked(SettingPreference.SharedNotificationAlbum);
        }
    }

    /* access modifiers changed from: private */
    public void onSharingMemberNotificationChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            ((ISharingNotificationView) this.mView).setSwitchPreferenceChecked(SettingPreference.SharedNotificationMember);
        }
    }

    /* access modifiers changed from: private */
    public void onSharingNotificationChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            SettingPreference settingPreference = SettingPreference.SharedNotification;
            ((ISharingNotificationView) this.mView).setSwitchPreferenceChecked(PreferenceName.SHOW_NOTIFICATION_MAIN_SWITCH.key(), settingPreference.isEnabled());
            ((ISharingNotificationView) this.mView).setMainSwitchPreferenceChecked(settingPreference.isEnabled());
        }
    }

    /* access modifiers changed from: private */
    public void onSharingPostNotificationChanged(Object obj, Bundle bundle) {
        if (!isDestroyed()) {
            ((ISharingNotificationView) this.mView).setSwitchPreferenceChecked(SettingPreference.SharedNotificationPosting);
        }
    }

    public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification", new Y(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification/album", new Y(this, 1)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification/post", new Y(this, 2)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification/member", new Y(this, 3)).setWorkingOnUI());
    }
}
