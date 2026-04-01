package O1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import t1.C0276a;
import w1.n;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends C0333a {
    public static final Parcelable.Creator<e> CREATOR = new a(14);
    public final int d;
    public final C0276a e;
    public final n f;

    public e(int i2, C0276a aVar, n nVar) {
        this.d = i2;
        this.e = aVar;
        this.f = nVar;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.S(parcel, 2, this.e, i2);
        c.S(parcel, 3, this.f, i2);
        c.X(parcel, W6);
    }
}
