package B3;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.RandomAccess;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements Parcelable {
    public static final Parcelable.Creator<g> CREATOR = new a(4);
    public final Object d;
    public final Object e;
    public final Object f;
    public final ArrayList g;

    public g(Parcel parcel) {
        RandomAccess createStringArrayList = parcel.createStringArrayList();
        RandomAccess randomAccess = C1202t.d;
        createStringArrayList = createStringArrayList == null ? randomAccess : createStringArrayList;
        RandomAccess createTypedArrayList = parcel.createTypedArrayList(f.CREATOR);
        createTypedArrayList = createTypedArrayList == null ? randomAccess : createTypedArrayList;
        RandomAccess createTypedArrayList2 = parcel.createTypedArrayList(d.CREATOR);
        randomAccess = createTypedArrayList2 != null ? createTypedArrayList2 : randomAccess;
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, Integer.TYPE.getClassLoader());
        this.d = createStringArrayList;
        this.e = createTypedArrayList;
        this.f = randomAccess;
        this.g = arrayList;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.util.List, java.lang.Object] */
    public final int describeContents() {
        d dVar = (d) C1194l.N0(this.f);
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (j.a(this.d, gVar.d) && j.a(this.e, gVar.e) && j.a(this.f, gVar.f) && j.a(this.g, gVar.g)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = this.e.hashCode();
        int hashCode2 = this.f.hashCode();
        return this.g.hashCode() + ((hashCode2 + ((hashCode + (this.d.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "TaskIOTensor(promptIDs=" + this.d + ", mPrompts=" + this.e + ", mTensors=" + this.f + ", mTokenIds=" + this.g + ")";
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [java.util.List, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v3, types: [java.util.List, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.util.List, java.lang.Object] */
    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeStringList(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeList(this.g);
    }
}
