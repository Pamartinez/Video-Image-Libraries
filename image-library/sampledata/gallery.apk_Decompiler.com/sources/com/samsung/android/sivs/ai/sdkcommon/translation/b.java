package com.samsung.android.sivs.ai.sdkcommon.translation;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements Parcelable {
    public static final Parcelable.Creator<b> CREATOR = new a(21);
    public String d;
    public float e;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeFloat(this.e);
    }
}
