package B3;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.photoremaster.sdk.common.Constants;
import kotlin.jvm.internal.j;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a(2);
    public final JSONObject d;

    public e(Parcel parcel) {
        String readString = parcel.readString();
        this.d = new JSONObject(readString == null ? Constants.EMPTY_JSON_STRING : readString);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        return j.a(this.d.toString(), ((e) obj).d.toString());
    }

    public final int hashCode() {
        return this.d.toString().hashCode();
    }

    public final String toString() {
        return "ModelInfo(modelConfigJson=" + this.d + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "parcel");
        parcel.writeString(this.d.toString());
    }
}
