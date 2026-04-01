package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import u1.l;
import x1.C0333a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Scope extends C0333a implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new l(0);
    public final int d;
    public final String e;

    public Scope(int i2, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.d = i2;
            this.e = str;
            return;
        }
        throw new IllegalArgumentException("scopeUri must not be null or empty");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Scope)) {
            return false;
        }
        return this.e.equals(((Scope) obj).e);
    }

    public final int hashCode() {
        return this.e.hashCode();
    }

    public final String toString() {
        return this.e;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        int W6 = c.W(parcel, 20293);
        c.Y(1, 4, parcel);
        parcel.writeInt(this.d);
        c.T(parcel, 2, this.e);
        c.X(parcel, W6);
    }
}
