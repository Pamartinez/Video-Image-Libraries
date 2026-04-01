package com.samsung.android.sivs.ai.sdkcommon.translation;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new B3.a(20);
    public String d;
    public String e;

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && a.class == obj.getClass()) {
            a aVar = (a) obj;
            if (!Objects.equals(this.d, aVar.d) || !Objects.equals(this.e, aVar.e)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.d, this.e});
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
    }
}
