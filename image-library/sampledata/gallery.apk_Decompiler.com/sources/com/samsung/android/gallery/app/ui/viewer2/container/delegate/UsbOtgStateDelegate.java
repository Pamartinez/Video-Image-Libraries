package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import k7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UsbOtgStateDelegate extends AbsVuDelegate<IVuContainerView> {
    public UsbOtgStateDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void onUsbStorageVolumeChanged(Object obj, Bundle bundle) {
        if (TextUtils.isEmpty((String) obj)) {
            Log.d(this.TAG, "usb otg Ejected");
            ((IVuContainerView) this.mView).finish();
        }
    }

    public void createGlobalSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        arrayList.add(new SubscriberInfo("event/UsbStorageVolumeChanged", new j(2, this)).setWorkingOnUI());
    }
}
