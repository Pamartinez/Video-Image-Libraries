package B3;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import te.C1295a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum i implements Parcelable {
    ;
    
    public static final h CREATOR = null;

    /* JADX WARNING: type inference failed for: r0v0, types: [B3.i, java.lang.Enum] */
    /* JADX WARNING: type inference failed for: r1v1, types: [B3.i, java.lang.Enum] */
    /* JADX WARNING: type inference failed for: r2v2, types: [B3.i, java.lang.Enum] */
    /* JADX WARNING: type inference failed for: r3v2, types: [B3.i, java.lang.Enum] */
    /* JADX WARNING: type inference failed for: r0v3, types: [B3.h, java.lang.Object] */
    static {
        i[] iVarArr;
        $ENTRIES = c.t(iVarArr);
        CREATOR = new Object();
    }

    public static C1295a b() {
        return $ENTRIES;
    }

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(name());
    }
}
