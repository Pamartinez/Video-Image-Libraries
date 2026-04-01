package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.option.TravelStateQueryOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelStateQueryOptionWrapperV1;", "Landroid/os/Parcelable;", "", "contentFill", "<init>", "(Z)V", "Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Z", "getContentFill", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelStateQueryOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<TravelStateQueryOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean contentFill;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelStateQueryOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelStateQueryOptionWrapperV1;", "travelStateQueryOption", "Lcom/samsung/android/sdk/moneta/memory/option/TravelStateQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ TravelStateQueryOptionWrapperV1 fromOption(TravelStateQueryOption travelStateQueryOption) {
            j.e(travelStateQueryOption, "travelStateQueryOption");
            return new TravelStateQueryOptionWrapperV1(travelStateQueryOption.getContentFill());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<TravelStateQueryOptionWrapperV1> {
        public final TravelStateQueryOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new TravelStateQueryOptionWrapperV1(parcel.readInt() != 0);
        }

        public final TravelStateQueryOptionWrapperV1[] newArray(int i2) {
            return new TravelStateQueryOptionWrapperV1[i2];
        }
    }

    public TravelStateQueryOptionWrapperV1() {
        this(false, 1, (e) null);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean getContentFill() {
        return this.contentFill;
    }

    public final /* synthetic */ TravelStateQueryOption toOption() {
        return new TravelStateQueryOption.WrapBuilder(this.contentFill).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeInt(this.contentFill ? 1 : 0);
    }

    public TravelStateQueryOptionWrapperV1(boolean z) {
        this.contentFill = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TravelStateQueryOptionWrapperV1(boolean z, int i2, e eVar) {
        this((i2 & 1) != 0 ? false : z);
    }
}
