package com.samsung.android.gallery.settings.ui;

import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class M implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ SettingCloud e;

    public /* synthetic */ M(SettingCloud settingCloud, int i2) {
        this.d = i2;
        this.e = settingCloud;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        SettingCloud settingCloud = this.e;
        switch (i2) {
            case 0:
                settingCloud.lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
            case 1:
                settingCloud.lambda$createGlobalSubscriberList$2(obj, bundle);
                return;
            default:
                settingCloud.lambda$createGlobalSubscriberList$3(obj, bundle);
                return;
        }
    }
}
