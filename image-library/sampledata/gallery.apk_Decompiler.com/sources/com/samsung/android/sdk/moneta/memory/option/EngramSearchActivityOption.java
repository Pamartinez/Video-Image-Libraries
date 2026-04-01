package com.samsung.android.sdk.moneta.memory.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.activity.ActivityType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001:\u0002()BO\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001c\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010$\u001a\u0004\b%\u0010&R\u0019\u0010\r\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010$\u001a\u0004\b'\u0010&¨\u0006*"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "activityType", "", "contentFill", "", "startTimestamp", "endTimestamp", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;ZLjava/lang/Long;Ljava/lang/Long;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "getActivityType", "()Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "Z", "getContentFill", "()Z", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchActivityOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchActivityOption> CREATOR = new Creator();
    private final ActivityType activityType;
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String keywords;
    private final int limit;
    private final int offset;
    private final Long startTimestamp;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchActivityOption> {
        public final EngramSearchActivityOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            ActivityType valueOf = ActivityType.valueOf(parcel.readString());
            boolean z = parcel.readInt() != 0;
            Long l = null;
            Long valueOf2 = parcel.readInt() == 0 ? null : Long.valueOf(parcel.readLong());
            if (parcel.readInt() != 0) {
                l = Long.valueOf(parcel.readLong());
            }
            return new EngramSearchActivityOption(readString, readInt, readInt2, valueOf, z, valueOf2, l, (e) null);
        }

        public final EngramSearchActivityOption[] newArray(int i2) {
            return new EngramSearchActivityOption[i2];
        }
    }

    public /* synthetic */ EngramSearchActivityOption(String str, int i2, int i7, ActivityType activityType2, boolean z, Long l, Long l8, e eVar) {
        this(str, i2, i7, activityType2, z, l, l8);
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

    public final String getKeywords() {
        return this.keywords;
    }

    public final int getLimit() {
        return this.limit;
    }

    public final int getOffset() {
        return this.offset;
    }

    public final Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.activityType.name());
        parcel.writeInt(this.contentFill ? 1 : 0);
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
    }

    private EngramSearchActivityOption(String str, int i2, int i7, ActivityType activityType2, boolean z, Long l, Long l8) {
        this.keywords = str;
        this.limit = i2;
        this.offset = i7;
        this.activityType = activityType2;
        this.contentFill = z;
        this.startTimestamp = l;
        this.endTimestamp = l8;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption$Builder;", "", "keywords", "", "limit", "", "offset", "activityType", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "contentFill", "", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;Z)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ActivityType activityType;
        private boolean contentFill;
        private String keywords;
        private int limit;
        private int offset;

        public Builder(String str, int i2, int i7, ActivityType activityType2, boolean z) {
            j.e(str, "keywords");
            j.e(activityType2, "activityType");
            this.keywords = str;
            this.limit = i2;
            this.offset = i7;
            this.activityType = activityType2;
            this.contentFill = z;
        }

        public final Builder activityType(ActivityType activityType2) {
            j.e(activityType2, "activityType");
            this.activityType = activityType2;
            return this;
        }

        public final EngramSearchActivityOption build() {
            return new EngramSearchActivityOption(this.keywords, this.limit, this.offset, this.activityType, this.contentFill, (Long) null, (Long) null, 96, (e) null);
        }

        public final Builder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final Builder keywords(String str) {
            j.e(str, "keywords");
            this.keywords = str;
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

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(String str, int i2, int i7, ActivityType activityType2, boolean z, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, activityType2, (i8 & 16) != 0 ? false : z);
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\r\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption$WrapBuilder;", "", "keywords", "", "limit", "", "offset", "activityType", "Lcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;", "contentFill", "", "startTimestamp", "", "endTimestamp", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/activity/ActivityType;ZLjava/lang/Long;Ljava/lang/Long;)V", "Ljava/lang/Long;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchActivityOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private ActivityType activityType;
        private boolean contentFill;
        private Long endTimestamp;
        private String keywords;
        private int limit;
        private int offset;
        private Long startTimestamp;

        public WrapBuilder(String str, int i2, int i7, ActivityType activityType2, boolean z, Long l, Long l8) {
            j.e(str, "keywords");
            j.e(activityType2, "activityType");
            this.keywords = str;
            this.limit = i2;
            this.offset = i7;
            this.activityType = activityType2;
            this.contentFill = z;
            this.startTimestamp = l;
            this.endTimestamp = l8;
        }

        public final /* synthetic */ EngramSearchActivityOption build() {
            return new EngramSearchActivityOption(this.keywords, this.limit, this.offset, this.activityType, this.contentFill, this.startTimestamp, this.endTimestamp, (e) null);
        }

        public final WrapBuilder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final WrapBuilder startTimeStamp(long j2) {
            this.startTimestamp = Long.valueOf(j2);
            return this;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(String str, int i2, int i7, ActivityType activityType2, boolean z, Long l, Long l8, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, activityType2, z, (i8 & 32) != 0 ? null : l, (i8 & 64) != 0 ? null : l8);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchActivityOption(String str, int i2, int i7, ActivityType activityType2, boolean z, Long l, Long l8, int i8, e eVar) {
        this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, activityType2, (i8 & 16) != 0 ? false : z, (i8 & 32) != 0 ? null : l, (i8 & 64) != 0 ? null : l8);
    }
}
