package com.samsung.android.gallery.module.location;

import com.samsung.android.gallery.module.location.LocationManager;
import com.samsung.android.gallery.module.map.manager.AddressHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements AddressHelper.OnAddressUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ LocationManager.LocationTask e;

    public /* synthetic */ a(LocationManager.LocationTask locationTask, int i2) {
        this.d = i2;
        this.e = locationTask;
    }

    public final void onAddressUpdate(Object[] objArr) {
        int i2 = this.d;
        LocationManager.LocationTask locationTask = this.e;
        switch (i2) {
            case 0:
                locationTask.lambda$start$0(objArr);
                return;
            case 1:
                locationTask.lambda$startFull$2(objArr);
                return;
            case 2:
                locationTask.lambda$startSimple$1(objArr);
                return;
            default:
                locationTask.lambda$start$3(objArr);
                return;
        }
    }
}
