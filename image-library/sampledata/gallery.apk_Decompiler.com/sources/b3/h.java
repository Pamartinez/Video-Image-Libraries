package B3;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h implements Parcelable.Creator {
    public final Object createFromParcel(Parcel parcel) {
        j.e(parcel, "source");
        String readString = parcel.readString();
        j.b(readString);
        return i.valueOf(readString);
    }

    public final Object[] newArray(int i2) {
        return new i[i2];
    }
}
