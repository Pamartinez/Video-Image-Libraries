package com.samsung.android.sdk.moneta.memory.entity.context;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J2\u0010\u0019\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0016J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010\u001f\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010\u0014R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010\u0016R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010%\u001a\u0004\b\u0007\u0010\u0018¨\u0006&"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "Landroid/os/Parcelable;", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "engram", "", "countryCode", "", "isAbroad", "<init>", "(Lcom/samsung/android/sdk/moneta/memory/entity/Engram;Ljava/lang/String;Z)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "component2", "()Ljava/lang/String;", "component3", "()Z", "copy", "(Lcom/samsung/android/sdk/moneta/memory/entity/Engram;Ljava/lang/String;Z)Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/sdk/moneta/memory/entity/Engram;", "getEngram", "Ljava/lang/String;", "getCountryCode", "Z", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelState implements Parcelable {
    public static final Parcelable.Creator<TravelState> CREATOR = new Creator();
    private final String countryCode;
    private final Engram engram;
    private final boolean isAbroad;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<TravelState> {
        public final TravelState createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new TravelState(parcel.readInt() == 0 ? null : Engram.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readInt() != 0);
        }

        public final TravelState[] newArray(int i2) {
            return new TravelState[i2];
        }
    }

    public TravelState(Engram engram2, String str, boolean z) {
        this.engram = engram2;
        this.countryCode = str;
        this.isAbroad = z;
    }

    public static /* synthetic */ TravelState copy$default(TravelState travelState, Engram engram2, String str, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            engram2 = travelState.engram;
        }
        if ((i2 & 2) != 0) {
            str = travelState.countryCode;
        }
        if ((i2 & 4) != 0) {
            z = travelState.isAbroad;
        }
        return travelState.copy(engram2, str, z);
    }

    public final Engram component1() {
        return this.engram;
    }

    public final String component2() {
        return this.countryCode;
    }

    public final boolean component3() {
        return this.isAbroad;
    }

    public final TravelState copy(Engram engram2, String str, boolean z) {
        return new TravelState(engram2, str, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TravelState)) {
            return false;
        }
        TravelState travelState = (TravelState) obj;
        if (j.a(this.engram, travelState.engram) && j.a(this.countryCode, travelState.countryCode) && this.isAbroad == travelState.isAbroad) {
            return true;
        }
        return false;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final Engram getEngram() {
        return this.engram;
    }

    public int hashCode() {
        int i2;
        Engram engram2 = this.engram;
        int i7 = 0;
        if (engram2 == null) {
            i2 = 0;
        } else {
            i2 = engram2.hashCode();
        }
        int i8 = i2 * 31;
        String str = this.countryCode;
        if (str != null) {
            i7 = str.hashCode();
        }
        return Boolean.hashCode(this.isAbroad) + ((i8 + i7) * 31);
    }

    public final boolean isAbroad() {
        return this.isAbroad;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("TravelState(engram=");
        sb2.append(this.engram);
        sb2.append(", countryCode=");
        sb2.append(this.countryCode);
        sb2.append(", isAbroad=");
        return C0086a.n(sb2, this.isAbroad, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Engram engram2 = this.engram;
        if (engram2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            engram2.writeToParcel(parcel, i2);
        }
        parcel.writeString(this.countryCode);
        parcel.writeInt(this.isAbroad ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TravelState(Engram engram2, String str, boolean z, int i2, e eVar) {
        this(engram2, (i2 & 2) != 0 ? null : str, z);
    }
}
