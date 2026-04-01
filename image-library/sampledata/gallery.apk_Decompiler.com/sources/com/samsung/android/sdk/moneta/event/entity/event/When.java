package com.samsung.android.sdk.moneta.event.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u000b\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0019J\u0010\u0010\u001b\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJB\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u001f\u0010\u0019J\u0010\u0010 \u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b \u0010\u0014J\u001a\u0010#\u001a\u00020\b2\b\u0010\"\u001a\u0004\u0018\u00010!HÖ\u0003¢\u0006\u0004\b#\u0010$R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010%\u001a\u0004\b&\u0010\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010%\u001a\u0004\b'\u0010\u0016R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010(\u001a\u0004\b)\u0010\u0019R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010(\u001a\u0004\b*\u0010\u0019R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010+\u001a\u0004\b\t\u0010\u001c¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "Landroid/os/Parcelable;", "", "startTime", "endTime", "", "sourcePackage", "sourceUri", "", "isLunar", "<init>", "(JJLjava/lang/String;Ljava/lang/String;Z)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()J", "component2", "component3", "()Ljava/lang/String;", "component4", "component5", "()Z", "copy", "(JJLjava/lang/String;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/event/entity/event/When;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "J", "getStartTime", "getEndTime", "Ljava/lang/String;", "getSourcePackage", "getSourceUri", "Z", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class When implements Parcelable {
    public static final Parcelable.Creator<When> CREATOR = new Creator();
    private final /* synthetic */ long endTime;
    private final /* synthetic */ boolean isLunar;
    private final /* synthetic */ String sourcePackage;
    private final /* synthetic */ String sourceUri;
    private final /* synthetic */ long startTime;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<When> {
        public final When createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new When(parcel.readLong(), parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
        }

        public final When[] newArray(int i2) {
            return new When[i2];
        }
    }

    public When(long j2, long j3, String str, String str2, boolean z) {
        j.e(str, "sourcePackage");
        j.e(str2, "sourceUri");
        this.startTime = j2;
        this.endTime = j3;
        this.sourcePackage = str;
        this.sourceUri = str2;
        this.isLunar = z;
    }

    public static /* synthetic */ When copy$default(When when, long j2, long j3, String str, String str2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = when.startTime;
        }
        long j8 = j2;
        if ((i2 & 2) != 0) {
            j3 = when.endTime;
        }
        long j10 = j3;
        if ((i2 & 4) != 0) {
            str = when.sourcePackage;
        }
        String str3 = str;
        if ((i2 & 8) != 0) {
            str2 = when.sourceUri;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            z = when.isLunar;
        }
        return when.copy(j8, j10, str3, str4, z);
    }

    public final long component1() {
        return this.startTime;
    }

    public final long component2() {
        return this.endTime;
    }

    public final String component3() {
        return this.sourcePackage;
    }

    public final String component4() {
        return this.sourceUri;
    }

    public final boolean component5() {
        return this.isLunar;
    }

    public final When copy(long j2, long j3, String str, String str2, boolean z) {
        j.e(str, "sourcePackage");
        j.e(str2, "sourceUri");
        return new When(j2, j3, str, str2, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof When)) {
            return false;
        }
        When when = (When) obj;
        if (this.startTime == when.startTime && this.endTime == when.endTime && j.a(this.sourcePackage, when.sourcePackage) && j.a(this.sourceUri, when.sourceUri) && this.isLunar == when.isLunar) {
            return true;
        }
        return false;
    }

    public final long getEndTime() {
        return this.endTime;
    }

    public final String getSourcePackage() {
        return this.sourcePackage;
    }

    public final String getSourceUri() {
        return this.sourceUri;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public int hashCode() {
        return Boolean.hashCode(this.isLunar) + C0212a.d(C0212a.d(C0212a.c(Long.hashCode(this.startTime) * 31, 31, this.endTime), 31, this.sourcePackage), 31, this.sourceUri);
    }

    public final boolean isLunar() {
        return this.isLunar;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("When(startTime=");
        sb2.append(this.startTime);
        sb2.append(", endTime=");
        sb2.append(this.endTime);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        sb2.append(this.sourceUri);
        sb2.append(", isLunar=");
        return C0086a.n(sb2, this.isLunar, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.endTime);
        parcel.writeString(this.sourcePackage);
        parcel.writeString(this.sourceUri);
        parcel.writeInt(this.isLunar ? 1 : 0);
    }
}
