package com.samsung.android.sdk.moneta.memory.option;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "Landroid/os/Parcelable;", "", "keywords", "<init>", "(Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "Builder", "WrapBuilder", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchEngramStatOption implements Parcelable {
    public static final Parcelable.Creator<EngramSearchEngramStatOption> CREATOR = new Creator();
    private final String keywords;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption$Builder;", "", "keywords", "", "<init>", "(Ljava/lang/String;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Builder {
        private String keywords;

        public Builder(String str) {
            j.e(str, "keywords");
            this.keywords = str;
        }

        public final EngramSearchEngramStatOption build() {
            return new EngramSearchEngramStatOption(this.keywords, (e) null);
        }

        public final Builder keywords(String str) {
            j.e(str, "keywords");
            this.keywords = str;
            return this;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchEngramStatOption> {
        public final EngramSearchEngramStatOption createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchEngramStatOption(parcel.readString(), (e) null);
        }

        public final EngramSearchEngramStatOption[] newArray(int i2) {
            return new EngramSearchEngramStatOption[i2];
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption$WrapBuilder;", "", "keywords", "", "<init>", "(Ljava/lang/String;)V", "build", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class WrapBuilder {
        private String keywords;

        public WrapBuilder(String str) {
            j.e(str, "keywords");
            this.keywords = str;
        }

        public final /* synthetic */ EngramSearchEngramStatOption build() {
            return new EngramSearchEngramStatOption(this.keywords, (e) null);
        }
    }

    public /* synthetic */ EngramSearchEngramStatOption(String str, e eVar) {
        this(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String getKeywords() {
        return this.keywords;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
    }

    private EngramSearchEngramStatOption(String str) {
        this.keywords = str;
    }
}
