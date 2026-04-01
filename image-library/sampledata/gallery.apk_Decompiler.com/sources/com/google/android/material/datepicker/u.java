package com.google.android.material.datepicker;

import B3.a;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import android.icu.util.TimeZone;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u implements Comparable, Parcelable {
    public static final Parcelable.Creator<u> CREATOR = new a(19);
    public final Calendar d;
    public final int e;
    public final int f;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final int f1470h;

    /* renamed from: i  reason: collision with root package name */
    public final long f1471i;

    /* renamed from: j  reason: collision with root package name */
    public String f1472j;

    public u(Calendar calendar) {
        calendar.set(5, 1);
        Calendar a7 = D.a(calendar);
        this.d = a7;
        this.e = a7.get(2);
        this.f = a7.get(1);
        this.g = a7.getMaximum(7);
        this.f1470h = a7.getActualMaximum(5);
        this.f1471i = a7.getTimeInMillis();
    }

    public static u b(int i2, int i7) {
        Calendar c5 = D.c((Calendar) null);
        c5.set(1, i2);
        c5.set(2, i7);
        return new u(c5);
    }

    public static u d(long j2) {
        Calendar c5 = D.c((Calendar) null);
        c5.setTimeInMillis(j2);
        return new u(c5);
    }

    public final int compareTo(Object obj) {
        return this.d.compareTo(((u) obj).d);
    }

    public final int describeContents() {
        return 0;
    }

    public final String e() {
        if (this.f1472j == null) {
            long timeInMillis = this.d.getTimeInMillis();
            Locale locale = Locale.getDefault();
            AtomicReference atomicReference = D.f1449a;
            DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton("yMMMM", locale);
            instanceForSkeleton.setTimeZone(TimeZone.getTimeZone("UTC"));
            instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
            this.f1472j = instanceForSkeleton.format(new Date(timeInMillis));
        }
        return this.f1472j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof u)) {
            return false;
        }
        u uVar = (u) obj;
        if (this.e == uVar.e && this.f == uVar.f) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.e), Integer.valueOf(this.f)});
    }

    public final int i(u uVar) {
        if (this.d instanceof GregorianCalendar) {
            return (uVar.e - this.e) + ((uVar.f - this.f) * 12);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f);
        parcel.writeInt(this.e);
    }
}
