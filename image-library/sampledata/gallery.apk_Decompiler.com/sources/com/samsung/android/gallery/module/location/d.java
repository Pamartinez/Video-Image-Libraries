package com.samsung.android.gallery.module.location;

import android.location.Geocoder$GeocodeListener;
import com.samsung.android.gallery.module.location.LocationManager;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Geocoder$GeocodeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ LocationManager.LocationTask33 f3029a;
    public final /* synthetic */ long b;

    public /* synthetic */ d(LocationManager.LocationTask33 locationTask33, long j2) {
        this.f3029a = locationTask33;
        this.b = j2;
    }

    public final void onGeocode(List list) {
        this.f3029a.lambda$start$1(this.b, list);
    }
}
