package com.samsung.android.gallery.settings.ui;

import C3.C0391a;
import android.os.Bundle;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.settings.ui.abstaction.IBasePreferenceView;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingServicePresenter<V extends IBasePreferenceView> extends BasePresenter<V> {
    public SharingServicePresenter(V v) {
        super(v);
    }

    /* access modifiers changed from: private */
    public void onSharingNotificationChanged(Object obj, Bundle bundle) {
        ((IBasePreferenceView) this.mView).setSwitchPreferenceChecked(SettingPreference.SharedNotification);
    }

    public void setGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("global://setting/sharings/Notification", new C0391a(5, this)).setWorkingOnUI());
    }
}
