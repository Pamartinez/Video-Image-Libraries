package q1;

import B3.a;
import N2.j;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k implements Parcelable {
    public static final Parcelable.Creator<k> CREATOR = new a(26);
    public int d;
    public int e;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("SavedState{mAnchorPosition=");
        sb2.append(this.d);
        sb2.append(", mAnchorOffset=");
        return j.e(sb2, this.e, '}');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
    }
}
