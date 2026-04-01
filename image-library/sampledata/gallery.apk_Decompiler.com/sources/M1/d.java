package M1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import ge.W0;
import java.util.Arrays;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C0333a {
    public static final Parcelable.Creator<d> CREATOR = new a(8);
    public final LatLng d;
    public final LatLng e;
    public final LatLng f;
    public final LatLng g;

    /* renamed from: h  reason: collision with root package name */
    public final LatLngBounds f423h;

    public d(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.d = latLng;
        this.e = latLng2;
        this.f = latLng3;
        this.g = latLng4;
        this.f423h = latLngBounds;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        if (!this.d.equals(dVar.d) || !this.e.equals(dVar.e) || !this.f.equals(dVar.f) || !this.g.equals(dVar.g) || !this.f423h.equals(dVar.f423h)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.e, this.f, this.g, this.f423h});
    }

    public final String toString() {
        W0 w02 = new W0((Object) this);
        w02.v0(this.d, "nearLeft");
        w02.v0(this.e, "nearRight");
        w02.v0(this.f, "farLeft");
        w02.v0(this.g, "farRight");
        w02.v0(this.f423h, "latLngBounds");
        return w02.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.S(parcel, 2, this.d, i2);
        c.S(parcel, 3, this.e, i2);
        c.S(parcel, 4, this.f, i2);
        c.S(parcel, 5, this.g, i2);
        c.S(parcel, 6, this.f423h, i2);
        c.X(parcel, W6);
    }
}
