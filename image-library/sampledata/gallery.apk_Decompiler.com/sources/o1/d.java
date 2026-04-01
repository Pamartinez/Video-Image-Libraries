package O1;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.ArrayList;
import java.util.List;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C0333a {
    public static final Parcelable.Creator<d> CREATOR = new a(13);
    public final List d;
    public final String e;

    public d(String str, ArrayList arrayList) {
        this.d = arrayList;
        this.e = str;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        List list = this.d;
        if (list != null) {
            int W10 = c.W(parcel, 1);
            parcel.writeStringList(list);
            c.X(parcel, W10);
        }
        c.T(parcel, 2, this.e);
        c.X(parcel, W6);
    }
}
