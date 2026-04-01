package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.moneta.memory.option.EngramQueryType;
import com.samsung.android.sdk.moneta.memory.option.TravelEngramQueryOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+B_\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010\u001aR\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001f\u001a\u0004\b!\u0010\u001aR\u0017\u0010\b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u001f\u001a\u0004\b\"\u0010\u001aR\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010&\u001a\u0004\b'\u0010(R\u0019\u0010\r\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010)\u001a\u0004\b\r\u0010*¨\u0006,"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelEngramQueryOptionWrapperV1;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "queryType", "limit", "offset", "", "engramId", "", "contentFill", "isComplete", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIILjava/lang/String;ZLjava/lang/Boolean;)V", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "I", "getQueryType", "getLimit", "getOffset", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "Z", "getContentFill", "()Z", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelEngramQueryOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<TravelEngramQueryOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String engramId;
    private final Boolean isComplete;
    private final int limit;
    private final int offset;
    private final int queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelEngramQueryOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/TravelEngramQueryOptionWrapperV1;", "travelEngramQueryOption", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ TravelEngramQueryOptionWrapperV1 fromOption(TravelEngramQueryOption travelEngramQueryOption) {
            j.e(travelEngramQueryOption, "travelEngramQueryOption");
            return new TravelEngramQueryOptionWrapperV1(travelEngramQueryOption.getStartTimestamp(), travelEngramQueryOption.getEndTimestamp(), travelEngramQueryOption.getQueryType(), travelEngramQueryOption.getLimit(), travelEngramQueryOption.getOffset(), travelEngramQueryOption.getEngramId(), travelEngramQueryOption.getContentFill(), travelEngramQueryOption.isComplete());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<TravelEngramQueryOptionWrapperV1> {
        public final TravelEngramQueryOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            Boolean bool = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            Long valueOf2 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            String readString = parcel.readString();
            boolean z = false;
            boolean z3 = parcel.readInt() != 0;
            if (parcel.readInt() != 0) {
                if (parcel.readInt() != 0) {
                    z = true;
                }
                bool = Boolean.valueOf(z);
            }
            return new TravelEngramQueryOptionWrapperV1(valueOf, valueOf2, readInt, readInt2, readInt3, readString, z3, bool);
        }

        public final TravelEngramQueryOptionWrapperV1[] newArray(int i2) {
            return new TravelEngramQueryOptionWrapperV1[i2];
        }
    }

    public TravelEngramQueryOptionWrapperV1() {
        this((Long) null, (Long) null, 0, 0, 0, (String) null, false, (Boolean) null, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
    }

    public final int describeContents() {
        return 0;
    }

    public final boolean getContentFill() {
        return this.contentFill;
    }

    public final Long getEndTimestamp() {
        return this.endTimestamp;
    }

    public final String getEngramId() {
        return this.engramId;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final int getQueryType() {
        return this.queryType;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final Boolean isComplete() {
        return this.isComplete;
    }

    public final /* synthetic */ TravelEngramQueryOption toOption() {
        return new TravelEngramQueryOption.WrapBuilder(this.startTimestamp, this.endTimestamp, this.queryType, this.limit, this.offset, this.engramId, this.contentFill, this.isComplete).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        Long l = this.startTimestamp;
        if (l == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l);
        }
        Long l8 = this.endTimestamp;
        if (l8 == null) {
            parcel.writeInt(0);
        } else {
            a.o(parcel, 1, l8);
        }
        parcel.writeInt(this.queryType);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.engramId);
        parcel.writeInt(this.contentFill ? 1 : 0);
        Boolean bool = this.isComplete;
        if (bool == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(bool.booleanValue() ? 1 : 0);
    }

    public TravelEngramQueryOptionWrapperV1(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.queryType = i2;
        this.limit = i7;
        this.offset = i8;
        this.engramId = str;
        this.contentFill = z;
        this.isComplete = bool;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TravelEngramQueryOptionWrapperV1(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool, int i10, e eVar) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? null : l8, (i10 & 4) != 0 ? EngramQueryType.BETWEEN_TIMESTAMP.getValue() : i2, (i10 & 8) != 0 ? 20 : i7, (i10 & 16) != 0 ? 0 : i8, (i10 & 32) != 0 ? null : str, (i10 & 64) != 0 ? true : z, (i10 & 128) != 0 ? null : bool);
    }
}
