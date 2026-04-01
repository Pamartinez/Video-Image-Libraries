package com.samsung.android.sdk.moneta.memory.option;

import A.a;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.cover.ScoverState;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0007\u0018\u00002\u00020\u0001:\u0003()*Ba\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0005¢\u0006\u0004\b\u0016\u0010\u0017R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0018\u001a\u0004\b\u001b\u0010\u001aR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001c\u001a\u0004\b\u001d\u0010\u0017R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001c\u001a\u0004\b\u001e\u0010\u0017R\u0017\u0010\b\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001f\u0010\u0017R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010#\u001a\u0004\b$\u0010%R\u0019\u0010\r\u001a\u0004\u0018\u00010\u000b8\u0006¢\u0006\f\n\u0004\b\r\u0010&\u001a\u0004\b\r\u0010'¨\u0006+"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "Landroid/os/Parcelable;", "", "startTimestamp", "endTimestamp", "", "queryType", "limit", "offset", "", "engramId", "", "contentFill", "isComplete", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIILjava/lang/String;ZLjava/lang/Boolean;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/Long;", "getStartTimestamp", "()Ljava/lang/Long;", "getEndTimestamp", "I", "getQueryType", "getLimit", "getOffset", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "Z", "getContentFill", "()Z", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "Builder", "ByEngramIdBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TravelEngramQueryOption implements Parcelable {
    public static final Parcelable.Creator<TravelEngramQueryOption> CREATOR = new Creator();
    private final boolean contentFill;
    private final Long endTimestamp;
    private final String engramId;
    private final Boolean isComplete;
    private final int limit;
    private final int offset;
    private final int queryType;
    private final Long startTimestamp;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010\u0011\u001a\u00020\u0012R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\rR\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption$Builder;", "", "startTimestamp", "", "endTimestamp", "limit", "", "offset", "contentFill", "", "isComplete", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIZLjava/lang/Boolean;)V", "Ljava/lang/Long;", "Ljava/lang/Boolean;", "startTimeStamp", "endTimeStamp", "build", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private boolean contentFill;
        private Long endTimestamp;
        private Boolean isComplete;
        private int limit;
        private int offset;
        private Long startTimestamp;

        public Builder() {
            this((Long) null, (Long) null, 0, 0, false, (Boolean) null, 63, (e) null);
        }

        public final TravelEngramQueryOption build() {
            return new TravelEngramQueryOption(this.startTimestamp, this.endTimestamp, EngramQueryType.BETWEEN_TIMESTAMP.getValue(), this.limit, this.offset, (String) null, this.contentFill, this.isComplete, (e) null);
        }

        public final Builder contentFill(boolean z) {
            this.contentFill = z;
            return this;
        }

        public final Builder endTimeStamp(long j2) {
            this.endTimestamp = Long.valueOf(j2);
            return this;
        }

        public final Builder isComplete(boolean z) {
            this.isComplete = Boolean.valueOf(z);
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

        public Builder(Long l, Long l8, int i2, int i7, boolean z, Boolean bool) {
            this.startTimestamp = l;
            this.endTimestamp = l8;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
            this.isComplete = bool;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Builder(Long l, Long l8, int i2, int i7, boolean z, Boolean bool, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : l, (i8 & 2) != 0 ? null : l8, (i8 & 4) != 0 ? 20 : i2, (i8 & 8) != 0 ? 0 : i7, (i8 & 16) != 0 ? true : z, (i8 & 32) != 0 ? null : bool);
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<TravelEngramQueryOption> {
        public final TravelEngramQueryOption createFromParcel(Parcel parcel) {
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
            return new TravelEngramQueryOption(valueOf, valueOf2, readInt, readInt2, readInt3, readString, z3, bool, (e) null);
        }

        public final TravelEngramQueryOption[] newArray(int i2) {
            return new TravelEngramQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B_\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u0012\u001a\u00020\u0013R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption$WrapBuilder;", "", "startTimestamp", "", "endTimestamp", "queryType", "", "limit", "offset", "engramId", "", "contentFill", "", "isComplete", "<init>", "(Ljava/lang/Long;Ljava/lang/Long;IIILjava/lang/String;ZLjava/lang/Boolean;)V", "Ljava/lang/Long;", "Ljava/lang/Boolean;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private boolean contentFill;
        private Long endTimestamp;
        private String engramId;
        private Boolean isComplete;
        private int limit;
        private int offset;
        private int queryType;
        private Long startTimestamp;

        public WrapBuilder() {
            this((Long) null, (Long) null, 0, 0, 0, (String) null, false, (Boolean) null, ScoverState.TYPE_NFC_SMART_COVER, (e) null);
        }

        public final /* synthetic */ TravelEngramQueryOption build() {
            return new TravelEngramQueryOption(this.startTimestamp, this.endTimestamp, this.queryType, this.limit, this.offset, this.engramId, this.contentFill, this.isComplete, (e) null);
        }

        public WrapBuilder(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool) {
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
        public /* synthetic */ WrapBuilder(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool, int i10, e eVar) {
            this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? null : l8, (i10 & 4) != 0 ? EngramQueryType.BETWEEN_TIMESTAMP.getValue() : i2, (i10 & 8) != 0 ? 20 : i7, (i10 & 16) != 0 ? 0 : i8, (i10 & 32) != 0 ? null : str, (i10 & 64) != 0 ? true : z, (i10 & 128) != 0 ? null : bool);
        }
    }

    public /* synthetic */ TravelEngramQueryOption(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool, e eVar) {
        this(l, l8, i2, i7, i8, str, z, bool);
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

    private TravelEngramQueryOption(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool) {
        this.startTimestamp = l;
        this.endTimestamp = l8;
        this.queryType = i2;
        this.limit = i7;
        this.offset = i8;
        this.engramId = str;
        this.contentFill = z;
        this.isComplete = bool;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\n\u0010\u000bJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "contentFill", "", "isComplete", "<init>", "(Ljava/lang/String;IIZLjava/lang/Boolean;)V", "Ljava/lang/Boolean;", "build", "Lcom/samsung/android/sdk/moneta/memory/option/TravelEngramQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByEngramIdBuilder {
        private boolean contentFill;
        private String engramId;
        private Boolean isComplete;
        private int limit;
        private int offset;

        public ByEngramIdBuilder(String str, int i2, int i7, boolean z, Boolean bool) {
            j.e(str, "engramId");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentFill = z;
            this.isComplete = bool;
        }

        public final TravelEngramQueryOption build() {
            return new TravelEngramQueryOption((Long) null, (Long) null, EngramQueryType.BY_ENGRAM_ID.getValue(), this.limit, this.offset, this.engramId, this.contentFill, this.isComplete, (e) null);
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
        public /* synthetic */ ByEngramIdBuilder(String str, int i2, int i7, boolean z, Boolean bool, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? true : z, (i8 & 16) != 0 ? null : bool);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TravelEngramQueryOption(Long l, Long l8, int i2, int i7, int i8, String str, boolean z, Boolean bool, int i10, e eVar) {
        this((i10 & 1) != 0 ? null : l, (i10 & 2) != 0 ? null : l8, (i10 & 4) != 0 ? EngramQueryType.BETWEEN_TIMESTAMP.getValue() : i2, (i10 & 8) != 0 ? 20 : i7, (i10 & 16) != 0 ? 0 : i8, (i10 & 32) != 0 ? null : str, (i10 & 64) != 0 ? true : z, (i10 & 128) != 0 ? null : bool);
    }
}
