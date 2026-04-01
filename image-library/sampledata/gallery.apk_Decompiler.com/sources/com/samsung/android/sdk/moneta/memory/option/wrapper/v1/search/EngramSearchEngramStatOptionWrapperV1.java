package com.samsung.android.sdk.moneta.memory.option.wrapper.v1.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchEngramStatOptionWrapperV1;", "Landroid/os/Parcelable;", "", "keywords", "<init>", "(Ljava/lang/String;)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getKeywords", "()Ljava/lang/String;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchEngramStatOptionWrapperV1 implements Parcelable {
    public static final Parcelable.Creator<EngramSearchEngramStatOptionWrapperV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String keywords;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchEngramStatOptionWrapperV1$Companion;", "", "<init>", "()V", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v1/search/EngramSearchEngramStatOptionWrapperV1;", "engramSearchEngramStatOption", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramSearchEngramStatOptionWrapperV1 fromOption(EngramSearchEngramStatOption engramSearchEngramStatOption) {
            j.e(engramSearchEngramStatOption, "engramSearchEngramStatOption");
            return new EngramSearchEngramStatOptionWrapperV1(engramSearchEngramStatOption.getKeywords());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchEngramStatOptionWrapperV1> {
        public final EngramSearchEngramStatOptionWrapperV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchEngramStatOptionWrapperV1(parcel.readString());
        }

        public final EngramSearchEngramStatOptionWrapperV1[] newArray(int i2) {
            return new EngramSearchEngramStatOptionWrapperV1[i2];
        }
    }

    public EngramSearchEngramStatOptionWrapperV1(String str) {
        j.e(str, "keywords");
        this.keywords = str;
    }

    public final int describeContents() {
        return 0;
    }

    public final String getKeywords() {
        return this.keywords;
    }

    public final /* synthetic */ EngramSearchEngramStatOption toOption() {
        return new EngramSearchEngramStatOption.WrapBuilder(this.keywords).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.keywords);
    }
}
