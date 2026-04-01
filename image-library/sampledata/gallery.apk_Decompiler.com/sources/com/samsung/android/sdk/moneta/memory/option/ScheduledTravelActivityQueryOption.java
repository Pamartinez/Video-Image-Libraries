package com.samsung.android.sdk.moneta.memory.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u00002\u00020\u0001:\u0004)*+,B_\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\f\u0012\b\b\u0002\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\t¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\t¢\u0006\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0019\u001a\u0004\b\u001c\u0010\u001bR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010#\u001a\u0004\b$\u0010\u0018R\u0017\u0010\u000b\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\u000b\u0010#\u001a\u0004\b%\u0010\u0018R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b'\u0010(R\u0017\u0010\u000e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000e\u0010&\u001a\u0004\b\u000e\u0010(¨\u0006-"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "queryType", "", "id", "", "limit", "offset", "", "contentFill", "isRealized", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;Ljava/lang/String;IIZZ)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "getQueryType", "()Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Z", "getContentFill", "()Z", "Builder", "ByEngramIdBuilder", "ByActivityIdBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ScheduledTravelActivityQueryOption implements Parcelable {
    public static final Parcelable.Creator<ScheduledTravelActivityQueryOption> CREATOR = new Creator();
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String id;
    private final boolean isRealized;
    private final int limit;
    private final int offset;
    private final ActivityQueryType queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BG\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\u0011R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption$Builder;", "", "startTimestamp", "", "endTimestamp", "limit", "", "offset", "contentFill", "", "isRealized", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIZZ)V", "Ljava/lang/Long;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean contentFill;
        private Long endTimestamp;
        private boolean isRealized;
        private int limit;
        private int offset;
        private Long startTimestamp;

        public Builder() {
            this((Long) null, (Long) null, 0, 0, false, false, 63, (e) null);
        }

        public final ScheduledTravelActivityQueryOption build() {
            return new ScheduledTravelActivityQueryOption(this.startTimestamp, this.endTimestamp, ActivityQueryType.BETWEEN_TIMESTAMP, (String) null, this.limit, this.offset, this.contentFill, this.isRealized, (e) null);
        }

        public final Builder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final Builder isRealized(boolean z) {
            this.isRealized = z;
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

        public Builder(Long l, Long l8, int i2, int i7, boolean z, boolean z3) {
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
            this.isRealized = z3;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Long l, Long l8, int i2, int i7, boolean z, boolean z3, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? 100 : i2, (i8 & 8) != 0 ? 0 : i7, (i8 & 16) != 0 ? true : z, (i8 & 32) != 0 ? false : z3);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ScheduledTravelActivityQueryOption> {
        public final ScheduledTravelActivityQueryOption createFromParcel(Parcel parcel) {
            boolean z;
            j.e(parcel, "parcel");
            Long l = null;
            Long valueOf = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            Long l8 = l;
            ActivityQueryType valueOf2 = ActivityQueryType.valueOf(parcel.readString());
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
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
            return new ScheduledTravelActivityQueryOption(valueOf, l8, valueOf2, readString, readInt, readInt2, z3, z7, (e) null);
        }

        public final ScheduledTravelActivityQueryOption[] newArray(int i2) {
            return new ScheduledTravelActivityQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B]\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0006\u0010\u0012\u001a\u00020\u0013R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption$WrapBuilder;", "", "startTimestamp", "", "endTimestamp", "queryType", "Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;", "id", "", "limit", "", "offset", "contentFill", "", "isRealized", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;Lcom/samsung/android/sdk/moneta/memory/option/ActivityQueryType;Ljava/lang/String;IIZZ)V", "Ljava/lang/Long;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private boolean contentFill;
        private Long endTimestamp;
        private String id;
        private boolean isRealized;
        private int limit;
        private int offset;
        private ActivityQueryType queryType;
        private Long startTimestamp;

        public WrapBuilder() {
            this((Long) null, (Long) null, (ActivityQueryType) null, (String) null, 0, 0, false, false, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
        }

        public final /* synthetic */ ScheduledTravelActivityQueryOption build() {
            return new ScheduledTravelActivityQueryOption(this.startTimestamp, this.endTimestamp, this.queryType, this.id, this.limit, this.offset, this.contentFill, this.isRealized, (e) null);
        }

        public WrapBuilder(Long l, Long l8, ActivityQueryType activityQueryType, String str, int i2, int i7, boolean z, boolean z3) {
            j.e(activityQueryType, "queryType");
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.queryType = activityQueryType;
            this.id = str;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
            this.isRealized = z3;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(Long l, Long l8, ActivityQueryType activityQueryType, String str, int i2, int i7, boolean z, boolean z3, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP : activityQueryType, (i8 & 8) != 0 ? null : str, (i8 & 16) != 0 ? 20 : i2, (i8 & 32) != 0 ? 0 : i7, (i8 & 64) != 0 ? true : z, (i8 & 128) != 0 ? false : z3);
        }
    }

    public /* synthetic */ ScheduledTravelActivityQueryOption(Long l, Long l8, ActivityQueryType activityQueryType, String str, int i2, int i7, boolean z, boolean z3, e eVar) {
        this(l, l8, activityQueryType, str, i2, i7, z, z3);
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

    public final ActivityQueryType getQueryType() {
        return this.queryType;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final boolean isRealized() {
        return this.isRealized;
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
        parcel.writeString(this.queryType.name());
        parcel.writeString(this.id);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeInt(this.contentFill ? 1 : 0);
        parcel.writeInt(this.isRealized ? 1 : 0);
    }

    private ScheduledTravelActivityQueryOption(Long l, Long l8, ActivityQueryType activityQueryType, String str, int i2, int i7, boolean z, boolean z3) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.queryType = activityQueryType;
        this.id = str;
        this.limit = i2;
        this.offset = i7;
        this.contentFill = z;
        this.isRealized = z3;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption$ByActivityIdBuilder;", "", "activityId", "", "limit", "", "offset", "contentFill", "", "<init>", "(Ljava/lang/String;IIZ)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        public final ScheduledTravelActivityQueryOption build() {
            return new ScheduledTravelActivityQueryOption((Long) null, (Long) null, ActivityQueryType.BY_ENGRAM_ID, this.activityId, this.limit, this.offset, this.contentFill, false, 128, (e) null);
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

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "contentFill", "", "<init>", "(Ljava/lang/String;IIZ)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ScheduledTravelActivityQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
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

        public final ScheduledTravelActivityQueryOption build() {
            return new ScheduledTravelActivityQueryOption((Long) null, (Long) null, ActivityQueryType.BY_ENGRAM_ID, this.engramId, this.limit, this.offset, this.contentFill, false, 128, (e) null);
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
    public /* synthetic */ ScheduledTravelActivityQueryOption(Long l, Long l8, ActivityQueryType activityQueryType, String str, int i2, int i7, boolean z, boolean z3, int i8, e eVar) {
        this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? ActivityQueryType.BETWEEN_TIMESTAMP : activityQueryType, (i8 & 8) != 0 ? null : str, (i8 & 16) != 0 ? 100 : i2, (i8 & 32) != 0 ? 0 : i7, (i8 & 64) != 0 ? true : z, (i8 & 128) != 0 ? false : z3);
    }
}
