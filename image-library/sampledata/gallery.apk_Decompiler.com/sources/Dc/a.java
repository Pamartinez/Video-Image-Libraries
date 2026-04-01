package Dc;

import N2.j;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new B3.a(6);
    public String d;
    public String e;
    public int f;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("AppCategoryDetail{mPackageName='");
        sb2.append(this.d);
        sb2.append("', mCategoryString='");
        sb2.append(this.e);
        sb2.append("', mCategoryId=");
        return j.e(sb2, this.f, '}');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
    }
}
