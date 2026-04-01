package com.google.android.gms.maps.model;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import java.util.Arrays;
import w1.r;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CameraPosition extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<CameraPosition> CREATOR = new a(7);
    public final LatLng d;
    public final float e;
    public final float f;
    public final float g;

    public CameraPosition(LatLng latLng, float f5, float f8, float f10) {
        r.c(latLng, "camera target must not be null.");
        boolean z = false;
        if (f8 >= 0.0f && f8 <= 90.0f) {
            z = true;
        }
        if (z) {
            this.d = latLng;
            this.e = f5;
            this.f = f8 + 0.0f;
            this.g = (((double) f10) <= MapUtil.INVALID_LOCATION ? (f10 % 360.0f) + 360.0f : f10) % 360.0f;
            return;
        }
        throw new IllegalArgumentException("Tilt needs to be between 0 and 90 inclusive: " + f8);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CameraPosition)) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        if (this.d.equals(cameraPosition.d) && Float.floatToIntBits(this.e) == Float.floatToIntBits(cameraPosition.e) && Float.floatToIntBits(this.f) == Float.floatToIntBits(cameraPosition.f) && Float.floatToIntBits(this.g) == Float.floatToIntBits(cameraPosition.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, Float.valueOf(this.e), Float.valueOf(this.f), Float.valueOf(this.g)});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(this.d, "target");
        w02.v0(Float.valueOf(this.e), "zoom");
        w02.v0(Float.valueOf(this.f), "tilt");
        w02.v0(Float.valueOf(this.g), "bearing");
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.S(parcel, 2, this.d, i2);
        c.Y(3, 4, parcel);
        parcel.writeFloat(this.e);
        c.Y(4, 4, parcel);
        parcel.writeFloat(this.f);
        c.Y(5, 4, parcel);
        parcel.writeFloat(this.g);
        c.X(parcel, W6);
    }
}
