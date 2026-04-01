package B3;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import i.C0212a;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a(0);
    public final String d;
    public final String e;
    public final String f;
    public final String g;

    public b(Parcel parcel) {
        String readString = parcel.readString();
        String str = "";
        readString = readString == null ? str : readString;
        String readString2 = parcel.readString();
        readString2 = readString2 == null ? str : readString2;
        String readString3 = parcel.readString();
        readString3 = readString3 == null ? str : readString3;
        String readString4 = parcel.readString();
        str = readString4 != null ? readString4 : str;
        this.d = readString;
        this.e = readString2;
        this.f = readString3;
        this.g = str;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (j.a(this.d, bVar.d) && j.a(this.e, bVar.e) && j.a(this.f, bVar.f) && j.a(this.g, bVar.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.g.hashCode() + C0212a.d(C0212a.d(this.d.hashCode() * 31, 31, this.e), 31, this.f);
    }

    public final String toString() {
        return C0212a.q(C0086a.q("AdapterInfo(loraConfigJson=", this.d, ", samplerJson=", this.e, ", loraName="), this.f, ", language=", this.g, ")");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "parcel");
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
