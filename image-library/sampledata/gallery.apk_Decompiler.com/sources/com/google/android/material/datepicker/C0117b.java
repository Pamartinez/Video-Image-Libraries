package com.google.android.material.datepicker;

import B3.a;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

/* renamed from: com.google.android.material.datepicker.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0117b implements Parcelable {
    public static final Parcelable.Creator<C0117b> CREATOR = new a(17);
    public final u d;
    public final u e;
    public final C0119d f;
    public final u g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1451h;

    /* renamed from: i  reason: collision with root package name */
    public final int f1452i;

    /* renamed from: j  reason: collision with root package name */
    public final int f1453j;

    public C0117b(u uVar, u uVar2, C0119d dVar, u uVar3, int i2) {
        Objects.requireNonNull(uVar, "start cannot be null");
        Objects.requireNonNull(uVar2, "end cannot be null");
        Objects.requireNonNull(dVar, "validator cannot be null");
        this.d = uVar;
        this.e = uVar2;
        this.g = uVar3;
        this.f1451h = i2;
        this.f = dVar;
        if (uVar3 != null && uVar.d.compareTo(uVar3.d) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (uVar3 != null && uVar3.d.compareTo(uVar2.d) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        } else if (i2 < 0 || i2 > D.c((Calendar) null).getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        } else {
            this.f1453j = uVar.i(uVar2) + 1;
            this.f1452i = (uVar2.f - uVar.f) + 1;
        }
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0117b)) {
            return false;
        }
        C0117b bVar = (C0117b) obj;
        if (!this.d.equals(bVar.d) || !this.e.equals(bVar.e) || !ObjectsCompat.equals(this.g, bVar.g) || this.f1451h != bVar.f1451h || !this.f.equals(bVar.f)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.e, this.g, Integer.valueOf(this.f1451h), this.f});
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.d, 0);
        parcel.writeParcelable(this.e, 0);
        parcel.writeParcelable(this.g, 0);
        parcel.writeParcelable(this.f, 0);
        parcel.writeInt(this.f1451h);
    }
}
