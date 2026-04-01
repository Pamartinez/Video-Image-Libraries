package com.samsung.android.sdk.moneta.memory.option.wrapper.v3.search;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.globalpostprocmgr.parameter.GPPInterfaceKey;
import com.samsung.android.sdk.moneta.memory.option.RecommendationsGetOption;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/RecommendationsGetOptionBundleWrapper;", "Landroid/os/Parcelable;", "Landroid/os/Bundle;", "properties", "<init>", "(Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "toOption", "()Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RecommendationsGetOptionBundleWrapper implements Parcelable {
    public static final String BUNDLE_KEY_EXTRAS = "extras";
    public static final String BUNDLE_KEY_MEDIA_ID_LIST = "mediaIdList";
    public static final Parcelable.Creator<RecommendationsGetOptionBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final Bundle properties;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/RecommendationsGetOptionBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_MEDIA_ID_LIST", "", "BUNDLE_KEY_EXTRAS", "fromOption", "Lcom/samsung/android/sdk/moneta/memory/option/wrapper/v3/search/RecommendationsGetOptionBundleWrapper;", "option", "Lcom/samsung/android/sdk/moneta/memory/option/RecommendationsGetOption;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ RecommendationsGetOptionBundleWrapper fromOption(RecommendationsGetOption recommendationsGetOption) {
            j.e(recommendationsGetOption, GPPInterfaceKey.JSON_KEY_OPTION);
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("mediaIdList", new ArrayList(recommendationsGetOption.getMediaIdList()));
            bundle.putBundle("extras", recommendationsGetOption.getExtras());
            return new RecommendationsGetOptionBundleWrapper(bundle);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<RecommendationsGetOptionBundleWrapper> {
        public final RecommendationsGetOptionBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new RecommendationsGetOptionBundleWrapper(parcel.readBundle(RecommendationsGetOptionBundleWrapper.class.getClassLoader()));
        }

        public final RecommendationsGetOptionBundleWrapper[] newArray(int i2) {
            return new RecommendationsGetOptionBundleWrapper[i2];
        }
    }

    public RecommendationsGetOptionBundleWrapper(Bundle bundle) {
        j.e(bundle, "properties");
        this.properties = bundle;
    }

    public final int describeContents() {
        return 0;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final /* synthetic */ RecommendationsGetOption toOption() {
        ArrayList<String> stringArrayList = this.properties.getStringArrayList("mediaIdList");
        if (stringArrayList == null) {
            stringArrayList = new ArrayList<>();
        }
        return new RecommendationsGetOption.WrapBuilder(stringArrayList, this.properties.getBundle("extras")).build();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeBundle(this.properties);
    }
}
