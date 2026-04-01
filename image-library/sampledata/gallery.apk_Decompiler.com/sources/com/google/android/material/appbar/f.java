package com.google.android.material.appbar;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.customview.view.AbsSavedState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends AbsSavedState {
    public static final Parcelable.Creator<f> CREATOR = new Object();
    public boolean d;
    public boolean e;
    public boolean f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public float f1392h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f1393i;

    /* renamed from: j  reason: collision with root package name */
    public int f1394j;
    public int k;

    public f(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        boolean z;
        boolean z3;
        boolean z7;
        boolean z9 = false;
        if (parcel.readByte() != 0) {
            z = true;
        } else {
            z = false;
        }
        this.d = z;
        if (parcel.readByte() != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.e = z3;
        if (parcel.readByte() != 0) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.f = z7;
        this.g = parcel.readInt();
        this.f1392h = parcel.readFloat();
        this.f1393i = parcel.readByte() != 0 ? true : z9;
        this.f1394j = parcel.readInt();
        this.k = parcel.readInt();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeByte(this.f ? (byte) 1 : 0);
        parcel.writeInt(this.g);
        parcel.writeFloat(this.f1392h);
        parcel.writeByte(this.f1393i ? (byte) 1 : 0);
        parcel.writeInt(this.f1394j);
        parcel.writeInt(this.k);
    }
}
