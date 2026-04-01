package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.query;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import com.samsung.android.sdk.moneta.memory.option.ActivityQueryOption;
import com.samsung.android.sdk.moneta.memory.option.ActivityQueryType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B_\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\u001d\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0007¢\u0006\u0004\b\u0018\u0010\u0019J\r\u0010\u001a\u001a\u00020\u0007¢\u0006\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001c\u001a\u0004\b\u001f\u0010\u001eR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b$\u0010\u001bR\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010#\u001a\u0004\b%\u0010\u001bR\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\f\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\f\u0010#\u001a\u0004\b)\u0010\u001bR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010*\u001a\u0004\b+\u0010,¨\u0006."}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ActivityQueryOptionWrapperV1;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "engramId", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "activityType", "queryType", "", "contentFill", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;IZ)V", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "getActivityType", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "getQueryType", "Z", "getContentFill", "()Z", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActivityQueryOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<ActivityQueryOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final ActivityType activityType;
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String engramId;
    private final int limit;
    private final int offset;
    private final int queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ActivityQueryOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/query/ActivityQueryOptionWrapperV1;", "activityQueryOption", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ ActivityQueryOptionWrapperV1 fromOption(ActivityQueryOption activityQueryOption) {
            j.e(activityQueryOption, "activityQueryOption");
            return new ActivityQueryOptionWrapperV1(activityQueryOption.getStartTimestamp(), activityQueryOption.getEndTimestamp(), activityQueryOption.getEngramId(), activityQueryOption.getLimit(), activityQueryOption.getOffset(), activityQueryOption.getActivityType(), activityQueryOption.getQueryType().getValue(), activityQueryOption.getContentFill());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ActivityQueryOptionWrapperV1> {
        public final ActivityQueryOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            ActivityType activityType = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            Long valueOf2 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            if (parcel.readInt() != 0) {
                activityType = ActivityType.valueOf(parcel.readString());
            }
            return new ActivityQueryOptionWrapperV1(valueOf, valueOf2, readString, readInt, readInt2, activityType, parcel.readInt(), parcel.readInt() != 0);
        }

        public final ActivityQueryOptionWrapperV1[] newArray(int i2) {
            return new ActivityQueryOptionWrapperV1[i2];
        }
    }

    public ActivityQueryOptionWrapperV1() {
        this((Long) null, (Long) null, (String) null, 0, 0, (ActivityType) null, 0, false, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
    }

    public final int describeContents() {
        return 0;
    }

    public final ActivityType getActivityType() {
        return this.activityType;
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

    public final /* synthetic */ ActivityQueryOption toOption() {
        Long l = this.startTimestamp;
        Long l8 = this.endTimestamp;
        String str = this.engramId;
        int i2 = this.limit;
        int i7 = this.offset;
        ActivityType activityType2 = this.activityType;
        ActivityQueryType fromInt = ActivityQueryType.Companion.fromInt(this.queryType);
        if (fromInt == null) {
            fromInt = ActivityQueryType.BETWEEN_TIMESTAMP;
        }
        return new ActivityQueryOption.WrapBuilder(l, l8, str, i2, i7, activityType2, fromInt, this.contentFill).build();
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
        parcel.writeString(this.engramId);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        ActivityType activityType2 = this.activityType;
        if (activityType2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(activityType2.name());
        }
        parcel.writeInt(this.queryType);
        parcel.writeInt(this.contentFill ? 1 : 0);
    }

    public ActivityQueryOptionWrapperV1(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, int i8, boolean z) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.engramId = str;
        this.limit = i2;
        this.offset = i7;
        this.activityType = activityType2;
        this.queryType = i8;
        this.contentFill = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ActivityQueryOptionWrapperV1(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, int i8, boolean z, int i10, e eVar) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? null : l8, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? 100 : i2, (i10 & 16) != 0 ? 0 : i7, (i10 & 32) != 0 ? null : activityType2, (i10 & 64) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP.getValue() : i8, (i10 & 128) != 0 ? true : z);
    }
}
