package com.google.android.gms.maps.model;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import java.util.Arrays;
import w1.r;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LatLngBounds extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new a(9);
    public final LatLng d;
    public final LatLng e;

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        boolean z;
        r.c(latLng, "southwest must not be null.");
        r.c(latLng2, "northeast must not be null.");
        double d2 = latLng2.d;
        double d3 = latLng.d;
        if (d2 >= d3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.d = latLng;
            this.e = latLng2;
            return;
        }
        throw new IllegalArgumentException("southern latitude exceeds northern latitude (" + d3 + " > " + d2 + ")");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        if (!this.d.equals(latLngBounds.d) || !this.e.equals(latLngBounds.e)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.e});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(this.d, "southwest");
        w02.v0(this.e, "northeast");
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.S(parcel, 2, this.d, i2);
        c.S(parcel, 3, this.e, i2);
        c.X(parcel, W6);
    }
}
