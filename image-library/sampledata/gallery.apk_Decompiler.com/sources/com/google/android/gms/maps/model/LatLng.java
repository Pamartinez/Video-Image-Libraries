package com.google.android.gms.maps.model;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class LatLng extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLng> CREATOR = new a(10);
    public final double d;
    public final double e;

    public LatLng(double d2, double d3) {
        if (d3 < -180.0d || d3 >= 180.0d) {
            this.e = ((((d3 - 0.02490234375d) % 360.0d) + 360.0d) % 360.0d) - 0.02490234375d;
        } else {
            this.e = d3;
        }
        this.d = Math.max(-90.0d, Math.min(90.0d, d2));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        if (Double.doubleToLongBits(this.d) == Double.doubleToLongBits(latLng.d) && Double.doubleToLongBits(this.e) == Double.doubleToLongBits(latLng.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.d);
        long j2 = doubleToLongBits ^ (doubleToLongBits >>> 32);
        long doubleToLongBits2 = Double.doubleToLongBits(this.e);
        return ((((int) j2) + 31) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public final String toString() {
        return "lat/lng: (" + this.d + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.e + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(2, 8, parcel);
        parcel.writeDouble(this.d);
        c.Y(3, 8, parcel);
        parcel.writeDouble(this.e);
        c.X(parcel, W6);
    }
}
