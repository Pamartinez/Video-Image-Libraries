package w1;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import t1.C0278c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends C0333a {
    public static final Parcelable.Creator<x> CREATOR = new l(7);
    public Bundle d;
    public C0278c[] e;
    public int f;
    public C0316c g;

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        Bundle bundle = this.d;
        if (bundle != null) {
            int W10 = c.W(parcel, 1);
            parcel.writeBundle(bundle);
            c.X(parcel, W10);
        }
        c.U(parcel, 2, this.e, i2);
        int i7 = this.f;
        c.Y(3, 4, parcel);
        parcel.writeInt(i7);
        c.S(parcel, 4, this.g, i2);
        c.X(parcel, W6);
    }
}
