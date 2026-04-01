package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search.EngramSearchGraphOptionBundleWrapper;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0014\u0015B\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0010\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;", "Landroid/os/Parcelable;", "", "keywords", "destIri", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "getDestIri", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchGraphOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchGraphOption> CREATOR = new Creator();
    private final String destIri;
    private final String keywords;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption$Builder;", "", "keywords", "", "destIri", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String destIri;
        private String keywords;

        public Builder(String str, String str2) {
            j.e(str, "keywords");
            j.e(str2, EngramSearchGraphOptionBundleWrapper.BUNDLE_KEY_DEST_IRI);
            this.keywords = str;
            this.destIri = str2;
        }

        public final EngramSearchGraphOption build() {
            return new EngramSearchGraphOption(this.keywords, this.destIri, (e) null);
        }

        public final Builder destIri(String str) {
            j.e(str, EngramSearchGraphOptionBundleWrapper.BUNDLE_KEY_DEST_IRI);
            this.destIri = str;
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
    public static final class Creator implements Parcelable.Creator<EngramSearchGraphOption> {
        public final EngramSearchGraphOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchGraphOption(parcel.readString(), parcel.readString(), (e) null);
        }

        public final EngramSearchGraphOption[] newArray(int i2) {
            return new EngramSearchGraphOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption$WrapBuilder;", "", "keywords", "", "destIri", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchGraphOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private String destIri;
        private String keywords;

        public WrapBuilder(String str, String str2) {
            j.e(str, "keywords");
            j.e(str2, EngramSearchGraphOptionBundleWrapper.BUNDLE_KEY_DEST_IRI);
            this.keywords = str;
            this.destIri = str2;
        }

        public final /* synthetic */ EngramSearchGraphOption build() {
            return new EngramSearchGraphOption(this.keywords, this.destIri, (e) null);
        }
    }

    public /* synthetic */ EngramSearchGraphOption(String str, String str2, e eVar) {
        this(str, str2);
    }

    public final int describeContents() {
        return 0;
    }

    public final String getDestIri() {
        return this.destIri;
    }

    public final String getKeywords() {
        return this.keywords;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
        parcel.writeString(this.destIri);
    }

    private EngramSearchGraphOption(String str, String str2) {
        this.keywords = str;
        this.destIri = str2;
    }
}
