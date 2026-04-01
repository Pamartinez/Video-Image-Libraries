package k2;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import h2.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l implements Parcelable {
    public static final Parcelable.Creator<l> CREATOR = new a(23);
    public int d;
    public i e;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.d);
        parcel.writeParcelable(this.e, 0);
    }
}
