package B3;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f implements Parcelable {
    public static final Parcelable.Creator<f> CREATOR = new a(3);
    public final String d;
    public final String e;

    public f(Parcel parcel) {
        String readString = parcel.readString();
        String str = "";
        readString = readString == null ? str : readString;
        String readString2 = parcel.readString();
        str = readString2 != null ? readString2 : str;
        this.d = readString;
        this.e = str;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        if (j.a(this.d, fVar.d) && j.a(this.e, fVar.e)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.e.hashCode() + (this.d.hashCode() * 31);
    }

    public final String toString() {
        return N2.j.d("PromptTensor(mPromptName=", this.d, ", mPromptString=", this.e, ")");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }
}
