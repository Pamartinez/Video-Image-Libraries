package com.samsung.android.gallery.settings.ui;

import android.os.Bundle;
import com.samsung.android.gallery.settings.ui.DetailEnhancerFragment;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* renamed from: com.samsung.android.gallery.settings.ui.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0646a implements SubscriberListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0646a(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        int i2 = this.d;
        Object obj2 = this.e;
        switch (i2) {
            case 0:
                ((DetailEnhancerFragment.AnonymousClass1) obj2).lambda$setGlobalSubscriberList$0(obj, bundle);
                return;
            default:
                ((SettingCloud2) obj2).lambda$createGlobalSubscriberList$1(obj, bundle);
                return;
        }
    }
}
