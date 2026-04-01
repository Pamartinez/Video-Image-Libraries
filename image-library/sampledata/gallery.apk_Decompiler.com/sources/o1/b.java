package O1;

import B3.a;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends C0333a {
    public static final Parcelable.Creator<b> CREATOR = new a(12);
    public final int d;
    public final int e;
    public final Intent f;

    public b(int i2, int i7, Intent intent) {
        this.d = i2;
        this.e = i7;
        this.f = intent;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.Y(2, 4, parcel);
        parcel.writeInt(this.e);
        c.S(parcel, 3, this.f, i2);
        c.X(parcel, W6);
    }
}
