package com.samsung.android.gallery.app.ui.list.sharings.pictures.setting;

import android.os.Bundle;
import com.samsung.android.gallery.app.ui.list.sharings.pictures.setting.SharingPicturesSettingFragment;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements SubscriberListener {
    public final /* synthetic */ SharingPicturesSettingFragment.AnonymousClass1 d;

    public /* synthetic */ a(SharingPicturesSettingFragment.AnonymousClass1 r1) {
        this.d = r1;
    }

    public final void onNotify(Object obj, Bundle bundle) {
        this.d.lambda$setGlobalSubscriberList$0(obj, bundle);
    }
}
