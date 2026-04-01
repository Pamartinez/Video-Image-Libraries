package com.samsung.android.gallery.module.location;

import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.map.manager.AddressCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class c implements Runnable {
    public final /* synthetic */ LocationManager.LocationTask33 d;
    public final /* synthetic */ AddressCompat e;

    public /* synthetic */ c(LocationManager.LocationTask33 locationTask33, AddressCompat addressCompat) {
        this.d = locationTask33;
        this.e = addressCompat;
    }

    public final void run() {
        this.d.lambda$notifyCallback$2(this.e);
    }
}
