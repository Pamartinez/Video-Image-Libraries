package com.samsung.android.sdk.moneta.memory.entity.wrapper.v1.context;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.Engram;
import com.samsung.android.sdk.moneta.memory.entity.context.TravelState;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.EngramWrapper;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.EngramWrapperV2;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB%\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u0007\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/TravelStateWrapperV1;", "Landroid/os/Parcelable;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;", "engramWrapper", "", "countryCode", "", "isAbroad", "<init>", "(Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;Ljava/lang/String;Z)V", "Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "toTravelState", "()Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;", "getEngramWrapper", "()Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/EngramWrapper;", "Ljava/lang/String;", "getCountryCode", "()Ljava/lang/String;", "Z", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelStateWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<TravelStateWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String countryCode;
    private final EngramWrapper engramWrapper;
    private final boolean isAbroad;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/TravelStateWrapperV1$Companion;", "", "<init>", "()V", "fromTravelState", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v1/context/TravelStateWrapperV1;", "travelState", "Lcom/samsung/android/sdk/moneta/memory/entity/context/TravelState;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ TravelStateWrapperV1 fromTravelState(TravelState travelState) {
            EngramWrapperV2 engramWrapperV2;
            j.e(travelState, "travelState");
            Engram engram = travelState.getEngram();
            if (engram != null) {
                engramWrapperV2 = j.e(engram, "<this>");
            } else {
                engramWrapperV2 = null;
            }
            return new TravelStateWrapperV1(engramWrapperV2, travelState.getCountryCode(), travelState.isAbroad());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<TravelStateWrapperV1> {
        public final TravelStateWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new TravelStateWrapperV1((EngramWrapper) parcel.readParcelable(TravelStateWrapperV1.class.getClassLoader()), parcel.readString(), parcel.readInt() != 0);
        }

        public final TravelStateWrapperV1[] newArray(int i2) {
            return new TravelStateWrapperV1[i2];
        }
    }

    public TravelStateWrapperV1(EngramWrapper engramWrapper2, String str, boolean z) {
        this.engramWrapper = engramWrapper2;
        this.countryCode = str;
        this.isAbroad = z;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final EngramWrapper getEngramWrapper() {
        return this.engramWrapper;
    }

    public final boolean isAbroad() {
        return this.isAbroad;
    }

    public final /* synthetic */ TravelState toTravelState() {
        Engram engram;
        EngramWrapper engramWrapper2 = this.engramWrapper;
        if (engramWrapper2 != null) {
            engram = engramWrapper2.toEngram();
        } else {
            engram = null;
        }
        return new TravelState(engram, this.countryCode, this.isAbroad);
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeParcelable(this.engramWrapper, i2);
        parcel.writeString(this.countryCode);
        parcel.writeInt(this.isAbroad ? 1 : 0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TravelStateWrapperV1(EngramWrapper engramWrapper2, String str, boolean z, int i2, e eVar) {
        this(engramWrapper2, (i2 & 2) != 0 ? null : str, z);
    }
}
