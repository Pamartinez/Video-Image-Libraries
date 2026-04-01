package com.samsung.android.sdk.moneta.memory.entity.wrapper.v2.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.activity.SearchingActivityBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u001a\u0010\u0018R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/MobileApplicationWrapperV2;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "packageId", "name", "", "altNames", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "toContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getPackageId", "getName", "Ljava/util/List;", "getAltNames", "()Ljava/util/List;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MobileApplicationWrapperV2 extends ContentWrapper {
    public static final Parcelable.Creator<MobileApplicationWrapperV2> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final List<String> altNames;
    private final String id;
    private final String name;
    private final String packageId;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/MobileApplicationWrapperV2$Companion;", "", "<init>", "()V", "fromContent", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/v2/content/MobileApplicationWrapperV2;", "mobileApplication", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ MobileApplicationWrapperV2 fromContent(MobileApplication mobileApplication) {
            j.e(mobileApplication, SearchingActivityBundleWrapper.BUNDLE_KEY_MOBILE_APPLICATION);
            return new MobileApplicationWrapperV2(mobileApplication.getId(), mobileApplication.getPackageId(), mobileApplication.getName(), mobileApplication.getAltNames());
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MobileApplicationWrapperV2> {
        public final MobileApplicationWrapperV2 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MobileApplicationWrapperV2(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createStringArrayList());
        }

        public final MobileApplicationWrapperV2[] newArray(int i2) {
            return new MobileApplicationWrapperV2[i2];
        }
    }

    public MobileApplicationWrapperV2(String str, String str2, String str3, List<String> list) {
        j.e(str, "id");
        j.e(str2, MobileApplicationBundleWrapper.BUNDLE_KEY_PACKAGE_ID);
        j.e(str3, "name");
        j.e(list, MobileApplicationBundleWrapper.BUNDLE_KEY_ALT_NAMES);
        this.id = str;
        this.packageId = str2;
        this.name = str3;
        this.altNames = list;
    }

    public final int describeContents() {
        return 0;
    }

    public final List<String> getAltNames() {
        return this.altNames;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageId() {
        return this.packageId;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.packageId);
        parcel.writeString(this.name);
        parcel.writeStringList(this.altNames);
    }
}
