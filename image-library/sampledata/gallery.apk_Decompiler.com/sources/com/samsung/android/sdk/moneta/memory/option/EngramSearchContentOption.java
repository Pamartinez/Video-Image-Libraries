package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB-\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0012R\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0016\u001a\u0004\b\u0018\u0010\u0012R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001e"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "Landroid/os/Parcelable;", "", "keywords", "", "limit", "offset", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "contentType", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "I", "getLimit", "getOffset", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "getContentType", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchContentOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchContentOption> CREATOR = new Creator();
    private final ContentType contentType;
    private final String keywords;
    private final int limit;
    private final int offset;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchContentOption> {
        public final EngramSearchContentOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchContentOption(parcel.readString(), parcel.readInt(), parcel.readInt(), ContentType.valueOf(parcel.readString()), (e) null);
        }

        public final EngramSearchContentOption[] newArray(int i2) {
            return new EngramSearchContentOption[i2];
        }
    }

    public /* synthetic */ EngramSearchContentOption(String str, int i2, int i7, ContentType contentType2, e eVar) {
        this(str, i2, i7, contentType2);
    }

    public final int describeContents() {
        return 0;
    }

    public final ContentType getContentType() {
        return this.contentType;
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

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeInt(this.limit);
        parcel.writeInt(this.offset);
        parcel.writeString(this.contentType.name());
    }

    private EngramSearchContentOption(String str, int i2, int i7, ContentType contentType2) {
        this.keywords = str;
        this.limit = i2;
        this.offset = i7;
        this.contentType = contentType2;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption$Builder;", "", "keywords", "", "limit", "", "offset", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ContentType contentType;
        private String keywords;
        private int limit;
        private int offset;

        public Builder(String str, int i2, int i7, ContentType contentType2) {
            j.e(str, "keywords");
            j.e(contentType2, "contentType");
            this.keywords = str;
            this.limit = i2;
            this.offset = i7;
            this.contentType = contentType2;
        }

        public final EngramSearchContentOption build() {
            return new EngramSearchContentOption(this.keywords, this.limit, this.offset, this.contentType, (e) null);
        }

        public final Builder contentType(ContentType contentType2) {
            j.e(contentType2, "contentType");
            this.contentType = contentType2;
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
        public /* synthetic */ Builder(String str, int i2, int i7, ContentType contentType2, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, contentType2);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption$WrapBuilder;", "", "keywords", "", "limit", "", "offset", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;IILcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private ContentType contentType;
        private String keywords;
        private int limit;
        private int offset;

        public WrapBuilder(String str, int i2, int i7, ContentType contentType2) {
            j.e(str, "keywords");
            j.e(contentType2, "contentType");
            this.keywords = str;
            this.limit = i2;
            this.offset = i7;
            this.contentType = contentType2;
        }

        public final /* synthetic */ EngramSearchContentOption build() {
            return new EngramSearchContentOption(this.keywords, this.limit, this.offset, this.contentType, (e) null);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ WrapBuilder(String str, int i2, int i7, ContentType contentType2, int i8, e eVar) {
            this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, contentType2);
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngramSearchContentOption(String str, int i2, int i7, ContentType contentType2, int i8, e eVar) {
        this(str, (i8 & 2) != 0 ? 20 : i2, (i8 & 4) != 0 ? 0 : i7, contentType2);
    }
}
