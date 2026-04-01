package com.google.android.material.datepicker;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* renamed from: com.google.android.material.datepicker.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0119d implements Parcelable {
    public static final Parcelable.Creator<C0119d> CREATOR = new a(18);
    public final long d;

    public C0119d(long j2) {
        this.d = j2;
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof C0119d) && this.d == ((C0119d) obj).d) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.d)});
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeLong(this.d);
    }
}
