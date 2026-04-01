package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0007\u0018\u00002\u00020\u0001:\u0003!\"#B?\b\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012J\r\u0010\u0013\u001a\u00020\u0004¢\u0006\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u0014R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001a\u0010\u0014R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u001e\u001a\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "Landroid/os/Parcelable;", "", "engramId", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "contentType", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;", "queryType", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getEngramId", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "getContentType", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;", "getQueryType", "()Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;", "ByEngramIdBuilder", "ByActivityIdBuilder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ContentQueryOption implements Parcelable {
    public static final Parcelable.Creator<ContentQueryOption> CREATOR = new Creator();
    private final ContentType contentType;
    private final String engramId;
    private final int limit;
    private final int offset;
    private final ContentQueryType queryType;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<ContentQueryOption> {
        public final ContentQueryOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new ContentQueryOption(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() == 0 ? null : ContentType.valueOf(parcel.readString()), ContentQueryType.valueOf(parcel.readString()), (e) null);
        }

        public final ContentQueryOption[] newArray(int i2) {
            return new ContentQueryOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B=\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption$WrapBuilder;", "", "activityId", "", "limit", "", "offset", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "queryType", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private String activityId;
        private ContentType contentType;
        private int limit;
        private int offset;
        private ContentQueryType queryType;

        public WrapBuilder() {
            this((String) null, 0, 0, (ContentType) null, (ContentQueryType) null, 31, (e) null);
        }

        public final /* synthetic */ ContentQueryOption build() {
            return new ContentQueryOption(this.activityId, this.limit, this.offset, this.contentType, this.queryType, (e) null);
        }

        public WrapBuilder(String str, int i2, int i7, ContentType contentType2, ContentQueryType contentQueryType) {
            j.e(contentQueryType, "queryType");
            this.activityId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentType = contentType2;
            this.queryType = contentQueryType;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(String str, int i2, int i7, ContentType contentType2, ContentQueryType contentQueryType, int i8, e eVar) {
            this((i8 & 1) != 0 ? null : str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? null : contentType2, (i8 & 16) != 0 ? ContentQueryType.BY_ENGRAM_ID : contentQueryType);
        }
    }

    public /* synthetic */ ContentQueryOption(String str, int i2, int i7, ContentType contentType2, ContentQueryType contentQueryType, e eVar) {
        this(str, i2, i7, contentType2, contentQueryType);
    }

    public final int describeContents() {
        return 0;
    }

    public final ContentType getContentType() {
        return this.contentType;
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

    public final ContentQueryType getQueryType() {
        return this.queryType;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.engramId);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        ContentType contentType2 = this.contentType;
        if (contentType2 == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(1);
            parcel.writeString(contentType2.name());
        }
        parcel.writeString(this.queryType.name());
    }

    private ContentQueryOption(String str, int i2, int i7, ContentType contentType2, ContentQueryType contentQueryType) {
        this.engramId = str;
        this.limit = i2;
        this.offset = i7;
        this.contentType = contentType2;
        this.queryType = contentQueryType;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption$ByActivityIdBuilder;", "", "activityId", "", "limit", "", "offset", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByActivityIdBuilder {
        private String activityId;
        private ContentType contentType;
        private int limit;
        private int offset;

        public ByActivityIdBuilder(String str, int i2, int i7, ContentType contentType2) {
            j.e(str, "activityId");
            j.e(contentType2, "contentType");
            this.activityId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentType = contentType2;
        }

        public final ContentQueryOption build() {
            return new ContentQueryOption(this.activityId, this.limit, this.offset, this.contentType, ContentQueryType.BY_ENGRAM_ID, (e) null);
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
        public /* synthetic */ ByActivityIdBuilder(String str, int i2, int i7, ContentType contentType2, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, contentType2);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption$ByEngramIdBuilder;", "", "engramId", "", "limit", "", "offset", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/ContentQueryOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ByEngramIdBuilder {
        private ContentType contentType;
        private String engramId;
        private int limit;
        private int offset;

        public ByEngramIdBuilder(String str, int i2, int i7, ContentType contentType2) {
            j.e(str, "engramId");
            j.e(contentType2, "contentType");
            this.engramId = str;
            this.limit = i2;
            this.offset = i7;
            this.contentType = contentType2;
        }

        public final ContentQueryOption build() {
            return new ContentQueryOption(this.engramId, this.limit, this.offset, this.contentType, ContentQueryType.BY_ENGRAM_ID, (e) null);
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
        public /* synthetic */ ByEngramIdBuilder(String str, int i2, int i7, ContentType contentType2, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, contentType2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ContentQueryOption(String str, int i2, int i7, ContentType contentType2, ContentQueryType contentQueryType, int i8, e eVar) {
        this((i8 & 1) != 0 ? null : str, (i8 & 2) != 0 ? 100 : i2, (i8 & 4) != 0 ? 0 : i7, (i8 & 8) != 0 ? null : contentType2, (i8 & 16) != 0 ? ContentQueryType.BY_ENGRAM_ID : contentQueryType);
    }
}
