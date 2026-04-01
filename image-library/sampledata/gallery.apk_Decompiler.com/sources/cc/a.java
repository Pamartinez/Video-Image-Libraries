package Cc;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new B3.a(5);
    public Rect d;
    public int e;
    public ArrayList f;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, Cc.a] */
    public static a b(Parcel parcel) {
        ? obj = new Object();
        obj.d = new Rect(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        obj.e = parcel.readInt();
        int readInt = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < readInt; i2++) {
            arrayList.add(b(parcel));
        }
        obj.f = arrayList;
        return obj;
    }

    public static void d(Parcel parcel, a aVar) {
        parcel.writeInt(aVar.d.left);
        parcel.writeInt(aVar.d.top);
        parcel.writeInt(aVar.d.right);
        parcel.writeInt(aVar.d.bottom);
        parcel.writeInt(aVar.e);
        ArrayList arrayList = aVar.f;
        if (arrayList != null) {
            parcel.writeInt(arrayList.size());
            if (aVar.f.size() > 0) {
                Iterator it = aVar.f.iterator();
                while (it.hasNext()) {
                    d(parcel, (a) it.next());
                }
                return;
            }
            return;
        }
        parcel.writeInt(0);
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        d(parcel, this);
    }
}
