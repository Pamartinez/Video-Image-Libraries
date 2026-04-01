package w1;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.List;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends C0333a {
    public static final Parcelable.Creator<i> CREATOR = new l(2);
    public final int d;
    public List e;

    public i(int i2, List list) {
        this.d = i2;
        this.e = list;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.V(parcel, this.e, 2);
        c.X(parcel, W6);
    }
}
