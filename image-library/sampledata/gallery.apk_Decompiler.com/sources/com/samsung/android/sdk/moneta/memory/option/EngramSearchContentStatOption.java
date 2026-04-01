package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.ContentType;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;", "Landroid/os/Parcelable;", "", "keywords", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "contentType", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "getContentType", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchContentStatOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchContentStatOption> CREATOR = new Creator();
    private final ContentType contentType;
    private final String keywords;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption$Builder;", "", "keywords", "", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private ContentType contentType;
        private String keywords;

        public Builder(String str, ContentType contentType2) {
            j.e(str, "keywords");
            j.e(contentType2, "contentType");
            this.keywords = str;
            this.contentType = contentType2;
        }

        public final EngramSearchContentStatOption build() {
            return new EngramSearchContentStatOption(this.keywords, this.contentType, (e) null);
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
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchContentStatOption> {
        public final EngramSearchContentStatOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchContentStatOption(parcel.readString(), ContentType.valueOf(parcel.readString()), (e) null);
        }

        public final EngramSearchContentStatOption[] newArray(int i2) {
            return new EngramSearchContentStatOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption$WrapBuilder;", "", "keywords", "", "contentType", "Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;", "<init>", "(Ljava/lang/String;Lcom/samsung/android/sdk/moneta/memory/entity/content/ContentType;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchContentStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private ContentType contentType;
        private String keywords;

        public WrapBuilder(String str, ContentType contentType2) {
            j.e(str, "keywords");
            j.e(contentType2, "contentType");
            this.keywords = str;
            this.contentType = contentType2;
        }

        public final /* synthetic */ EngramSearchContentStatOption build() {
            return new EngramSearchContentStatOption(this.keywords, this.contentType, (e) null);
        }
    }

    public /* synthetic */ EngramSearchContentStatOption(String str, ContentType contentType2, e eVar) {
        this(str, contentType2);
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

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeString(this.contentType.name());
    }

    private EngramSearchContentStatOption(String str, ContentType contentType2) {
        this.keywords = str;
        this.contentType = contentType2;
    }
}
