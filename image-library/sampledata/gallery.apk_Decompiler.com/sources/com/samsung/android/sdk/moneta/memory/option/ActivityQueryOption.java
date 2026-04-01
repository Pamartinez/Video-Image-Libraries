package com.samsung.android.sdk.moneta.memory.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\b\u0007\u0018\u00002\u00020\u0001:\u0004-./0Ba\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0007¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0007¢\u0006\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010!\u001a\u0004\b\"\u0010\u0019R\u0017\u0010\t\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010!\u001a\u0004\b#\u0010\u0019R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010'\u001a\u0004\b(\u0010)R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010*\u001a\u0004\b+\u0010,¨\u00061"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "engramId", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "activityType", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "queryType", "", "contentFill", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;Z)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "getActivityType", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "getQueryType", "()Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "Z", "getContentFill", "()Z", "Builder", "ByEngramIdBuilder", "ByActivityIdBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ActivityQueryOption implements Parcelable {
    public static final Parcelable.Creator<ActivityQueryOption> CREATOR = new Creator();
    private final ActivityType activityType;
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String engramId;
    private final int limit;
    private final int offset;
    private final ActivityQueryType queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\u0011\u001a\u00020\u0012R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption$Builder;", "", "activityType", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "startTimestamp", "", "endTimestamp", "limit", "", "offset", "contentFill", "", "<init>", "(Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;Ljava/lang/Long;Ljava/lang/Long;IIZ)V", "Ljava/lang/Long;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ActivityType activityType;
        private boolean contentFill;
        private Long endTimestamp;
        private int limit;
        private int offset;
        private Long startTimestamp;

        public Builder() {
            this((ActivityType) null, (Long) null, (Long) null, 0, 0, false, 63, (e) null);
        }

        public final Builder activityType(ActivityType activityType2) {
            j.e(activityType2, "activityType");
            this.activityType = activityType2;
            return this;
        }

        public final ActivityQueryOption build() {
            return new ActivityQueryOption(this.startTimestamp, this.endTimestamp, (String) null, this.limit, this.offset, this.activityType, ActivityQueryType.BETWEEN_TIMESTAMP, this.contentFill, (e) null);
        }

        public final Builder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final Builder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final Builder offset(int i2) {
            this.offset = i2;
            return this;
        }

        public final Builder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        public Builder(ActivityType activityType2, Long l, Long l8, int i2, int i7, boolean z) {
            this.activityType = activityType2;
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(ActivityType activityType2, Long l, Long l8, int i2, int i7, boolean z, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : activityType2, (i8 & 2) != 0 ? null : l, (i8 & 4) != 0 ? null : l8, (i8 & 8) != 0 ? 100 : i2, (i8 & 16) != 0 ? 0 : i7, (i8 & 32) != 0 ? true : z);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ActivityQueryOption> {
        public final ActivityQueryOption createFromParcel(Parcel parcel) {
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
            return new ActivityQueryOption(valueOf, valueOf2, readString, readInt, readInt2, activityType, ActivityQueryType.valueOf(parcel.readString()), parcel.readInt() != 0, (e) null);
        }

        public final ActivityQueryOption[] newArray(int i2) {
            return new ActivityQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B_\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0006\u0010\u0013\u001a\u00020\u0014R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption$WrapBuilder;", "", "startTimestamp", "", "endTimestamp", "engramId", "", "limit", "", "offset", "activityType", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "queryType", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "contentFill", "", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;Z)V", "Ljava/lang/Long;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private ActivityType activityType;
        private boolean contentFill;
        private Long endTimestamp;
        private String engramId;
        private int limit;
        private int offset;
        private ActivityQueryType queryType;
        private Long startTimestamp;

        public WrapBuilder() {
            this((Long) null, (Long) null, (String) null, 0, 0, (ActivityType) null, (ActivityQueryType) null, false, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
        }

        public final /* synthetic */ ActivityQueryOption build() {
            return new ActivityQueryOption(this.startTimestamp, this.endTimestamp, this.engramId, this.limit, this.offset, this.activityType, this.queryType, this.contentFill, (e) null);
        }

        public WrapBuilder(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, ActivityQueryType activityQueryType, boolean z) {
            j.e(activityQueryType, "queryType");
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
            this.activityType = activityType2;
            this.queryType = activityQueryType;
            this.contentFill = z;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, ActivityQueryType activityQueryType, boolean z, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? null : str, (i8 & 8) != 0 ? 100 : i2, (i8 & 16) != 0 ? 0 : i7, (i8 & 32) != 0 ? null : activityType2, (i8 & 64) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP : activityQueryType, (i8 & 128) != 0 ? true : z);
        }
    }

    public /* synthetic */ ActivityQueryOption(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, ActivityQueryType activityQueryType, boolean z, e eVar) {
        this(l, l8, str, i2, i7, activityType2, activityQueryType, z);
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

    public final ActivityQueryType getQueryType() {
        return this.queryType;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
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
        parcel.writeString(this.queryType.name());
        parcel.writeInt(this.contentFill ? 1 : 0);
    }

    private ActivityQueryOption(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, ActivityQueryType activityQueryType, boolean z) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.engramId = str;
        this.limit = i2;
        this.offset = i7;
        this.activityType = activityType2;
        this.queryType = activityQueryType;
        this.contentFill = z;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption$ByActivityIdBuilder;", "", "activityId", "", "limit", "", "offset", "contentFill", "", "<init>", "(Ljava/lang/String;IIZ)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByActivityIdBuilder {
        private String activityId;
        private boolean contentFill;
        private int limit;
        private int offset;

        public ByActivityIdBuilder(String str, int i2, int i7, boolean z) {
            j.e(str, "activityId");
            this.activityId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
        }

        public final ActivityQueryOption build() {
            return new ActivityQueryOption((Long) null, (Long) null, this.activityId, this.limit, this.offset, (ActivityType) null, ActivityQueryType.BY_ENGRAM_ID, this.contentFill, (e) null);
        }

        public final ByActivityIdBuilder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final ByActivityIdBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByActivityIdBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ByActivityIdBuilder(String str, int i2, int i7, boolean z, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? true : z);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "contentFill", "", "<init>", "(Ljava/lang/String;IIZ)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByEngramIdBuilder {
        private boolean contentFill;
        private String engramId;
        private int limit;
        private int offset;

        public ByEngramIdBuilder(String str, int i2, int i7, boolean z) {
            j.e(str, "engramId");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
        }

        public final ActivityQueryOption build() {
            return new ActivityQueryOption((Long) null, (Long) null, this.engramId, this.limit, this.offset, (ActivityType) null, ActivityQueryType.BY_ENGRAM_ID, this.contentFill, (e) null);
        }

        public final ByEngramIdBuilder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final ByEngramIdBuilder limit(int i2) {
            this.limit = i2;
            return this;
        }

        public final ByEngramIdBuilder offset(int i2) {
            this.offset = i2;
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ByEngramIdBuilder(String str, int i2, int i7, boolean z, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? true : z);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ActivityQueryOption(Long l, Long l8, String str, int i2, int i7, ActivityType activityType2, ActivityQueryType activityQueryType, boolean z, int i8, e eVar) {
        this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? null : str, (i8 & 8) != 0 ? 100 : i2, (i8 & 16) != 0 ? 0 : i7, (i8 & 32) != 0 ? null : activityType2, (i8 & 64) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP : activityQueryType, (i8 & 128) != 0 ? true : z);
    }
}
