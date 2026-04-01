package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.option.ActivityQueryType;
import com.samsung.android.sdk.moneta.memory.option.ScheduledTravelActivityQueryOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B]\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\b\b\u0002\u0010\r\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0005¢\u0006\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b \u0010\u001aR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\t\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\t\u0010\u001f\u001a\u0004\b$\u0010\u001aR\u0017\u0010\n\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\n\u0010\u001f\u001a\u0004\b%\u0010\u001aR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\r\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b\r\u0010(¨\u0006*"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ScheduledTravelActivityQueryOptionWrapperV1;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "queryType", "", "id", "limit", "offset", "", "contentFill", "isRealized", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;ILjava/lang/String;IIZZ)V", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "I", "getQueryType", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getLimit", "getOffset", "Z", "getContentFill", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledTravelActivityQueryOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<ScheduledTravelActivityQueryOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String id;
    private final boolean isRealized;
    private final int limit;
    private final int offset;
    private final int queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ScheduledTravelActivityQueryOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ScheduledTravelActivityQueryOptionWrapperV1;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ScheduledTravelActivityQueryOptionWrapperV1 fromOption(ScheduledTravelActivityQueryOption scheduledTravelActivityQueryOption) {
            j.e(scheduledTravelActivityQueryOption, GPPInterfaceKey.JSON_KEY_OPTION);
            return new ScheduledTravelActivityQueryOptionWrapperV1(scheduledTravelActivityQueryOption.getStartTimestamp(), scheduledTravelActivityQueryOption.getEndTimestamp(), scheduledTravelActivityQueryOption.getQueryType().getValue(), scheduledTravelActivityQueryOption.getId(), scheduledTravelActivityQueryOption.getLimit(), scheduledTravelActivityQueryOption.getOffset(), scheduledTravelActivityQueryOption.getContentFill(), scheduledTravelActivityQueryOption.isRealized());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ScheduledTravelActivityQueryOptionWrapperV1> {
        public final ScheduledTravelActivityQueryOptionWrapperV1 createFromParcel(Parcel parcel) {
            boolean z;
            j.e(parcel, "parcel");
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            Long l8 = l;
            int readInt = parcel.readInt();
            String readString = parcel.readString();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            boolean z3 = false;
            boolean z7 = true;
            if (parcel.readInt() != 0) {
                z = false;
                z3 = true;
            } else {
                z = false;
            }
            if (parcel.readInt() == 0) {
                z7 = z;
            }
            return new ScheduledTravelActivityQueryOptionWrapperV1(valueOf, l8, readInt, readString, readInt2, readInt3, z3, z7);
        }

        public final ScheduledTravelActivityQueryOptionWrapperV1[] newArray(int i2) {
            return new ScheduledTravelActivityQueryOptionWrapperV1[i2];
        }
    }

    public ScheduledTravelActivityQueryOptionWrapperV1() {
        this((Long) null, (Long) null, 0, (String) null, 0, 0, false, false, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
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

    public final String getId() {
        return this.id;
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

    public final boolean isRealized() {
        return this.isRealized;
    }

    public final /* synthetic */ ScheduledTravelActivityQueryOption toOption() {
        Long l = this.startTimestamp;
        Long l8 = this.endTimestamp;
        ActivityQueryType fromInt = ActivityQueryType.Companion.fromInt(this.queryType);
        if (fromInt == null) {
            fromInt = ActivityQueryType.BETWEEN_TIMESTAMP;
        }
        return new ScheduledTravelActivityQueryOption.WrapBuilder(l, l8, fromInt, this.id, this.limit, this.offset, this.contentFill, this.isRealized).build();
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
        parcel.writeString(this.id);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeInt(this.contentFill ? 1 : 0);
        parcel.writeInt(this.isRealized ? 1 : 0);
    }

    public ScheduledTravelActivityQueryOptionWrapperV1(Long l, Long l8, int i2, String str, int i7, int i8, boolean z, boolean z3) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.queryType = i2;
        this.id = str;
        this.limit = i7;
        this.offset = i8;
        this.contentFill = z;
        this.isRealized = z3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ScheduledTravelActivityQueryOptionWrapperV1(Long l, Long l8, int i2, String str, int i7, int i8, boolean z, boolean z3, int i10, e eVar) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? null : l8, (i10 & 4) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP.getValue() : i2, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? 100 : i7, (i10 & 32) != 0 ? 0 : i8, (i10 & 64) != 0 ? true : z, (i10 & 128) != 0 ? false : z3);
    }
}
