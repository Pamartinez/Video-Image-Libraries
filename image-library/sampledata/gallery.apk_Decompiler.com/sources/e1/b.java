package E1;

import android.os.BadParcelableException;
import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f163a = 0;

    static {
        b.class.getClassLoader();
    }

    public static Parcelable a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }

    public static void b(Parcel parcel) {
        int dataAvail = parcel.dataAvail();
        if (dataAvail > 0) {
            throw new BadParcelableException(C0086a.i(dataAvail, "Parcel data not fully consumed, unread size: "));
        }
    }
}
