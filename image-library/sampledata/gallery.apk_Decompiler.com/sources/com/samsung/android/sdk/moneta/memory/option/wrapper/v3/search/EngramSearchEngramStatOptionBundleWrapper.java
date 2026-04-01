package com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.option.EngramSearchEngramStatOption;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/EngramSearchEngramStatOptionBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class EngramSearchEngramStatOptionBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_KEYWORDS = "keywords";
    public static final Parcelable.Creator<EngramSearchEngramStatOptionBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/EngramSearchEngramStatOptionBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_KEYWORDS", "", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/EngramSearchEngramStatOptionBundleWrapper;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/EngramSearchEngramStatOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ EngramSearchEngramStatOptionBundleWrapper fromOption(EngramSearchEngramStatOption engramSearchEngramStatOption) {
            j.e(engramSearchEngramStatOption, GPPInterfaceKey.JSON_KEY_OPTION);
            Bundle bundle = new Bundle();
            bundle.putString("keywords", engramSearchEngramStatOption.getKeywords());
            return new EngramSearchEngramStatOptionBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<EngramSearchEngramStatOptionBundleWrapper> {
        public final EngramSearchEngramStatOptionBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new EngramSearchEngramStatOptionBundleWrapper(parcel.readBundle(EngramSearchEngramStatOptionBundleWrapper.class.getClassLoader()));
        }

        public final EngramSearchEngramStatOptionBundleWrapper[] newArray(int i2) {
            return new EngramSearchEngramStatOptionBundleWrapper[i2];
        }
    }

    public EngramSearchEngramStatOptionBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ EngramSearchEngramStatOption toOption() {
        String string = this.properties.getString("keywords", "");
        j.d(string, "getString(...)");
        return new EngramSearchEngramStatOption.WrapBuilder(string).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
