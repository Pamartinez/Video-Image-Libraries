package com.samsung.android.gallery.module.mde;

import com.samsung.android.gallery.module.mde.MdeSharingService;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Runnable {
    public final /* synthetic */ MdeSharingService.MobileServiceSessionResultCallback d;
    public final /* synthetic */ HashMap e;

    public /* synthetic */ a(MdeSharingService.MobileServiceSessionResultCallback mobileServiceSessionResultCallback, HashMap hashMap) {
        this.d = mobileServiceSessionResultCallback;
        this.e = hashMap;
    }

    public final void run() {
        this.d.lambda$onSuccess$0(this.e);
    }
}
