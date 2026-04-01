package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.content.MobileApplication;
import com.samsung.android.sdk.moneta.memory.entity.wrapper.ContentWrapper;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import ne.C1202t;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\nJ\r\u0010\f\u001a\u00020\b¢\u0006\u0004\b\f\u0010\nJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/MobileApplicationBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/wrapper/ContentWrapper;", "", "id", "Landroid/os/Bundle;", "properties", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "createContent", "()Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "toContent", "toContentV4", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getProperties", "()Landroid/os/Bundle;", "Companion", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MobileApplicationBundleWrapper extends ContentWrapper {
    public static final String BUNDLE_KEY_ALT_NAMES = "altNames";
    public static final String BUNDLE_KEY_NAME = "name";
    public static final String BUNDLE_KEY_PACKAGE_ID = "packageId";
    public static final Parcelable.Creator<MobileApplicationBundleWrapper> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final String id;
    private final Bundle properties;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/MobileApplicationBundleWrapper$Companion;", "", "<init>", "()V", "BUNDLE_KEY_PACKAGE_ID", "", "BUNDLE_KEY_NAME", "BUNDLE_KEY_ALT_NAMES", "getUnknownContent", "Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final /* synthetic */ MobileApplication getUnknownContent() {
            return new MobileApplication("", "", "", C1202t.d);
        }

        private Companion() {
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MobileApplicationBundleWrapper> {
        public final MobileApplicationBundleWrapper createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MobileApplicationBundleWrapper(parcel.readString(), parcel.readBundle(MobileApplicationBundleWrapper.class.getClassLoader()));
        }

        public final MobileApplicationBundleWrapper[] newArray(int i2) {
            return new MobileApplicationBundleWrapper[i2];
        }
    }

    public MobileApplicationBundleWrapper(String str, Bundle bundle) {
        j.e(str, "id");
        j.e(bundle, "properties");
        this.id = str;
        this.properties = bundle;
    }

    /* access modifiers changed from: private */
    /* renamed from: createContent */
    public final MobileApplication toContent() {
        String str = this.id;
        String string = this.properties.getString(BUNDLE_KEY_PACKAGE_ID, "");
        j.d(string, "getString(...)");
        String string2 = this.properties.getString("name", "");
        j.d(string2, "getString(...)");
        List stringArrayList = this.properties.getStringArrayList(BUNDLE_KEY_ALT_NAMES);
        if (stringArrayList == null) {
            stringArrayList = null;
        }
        if (stringArrayList == null) {
            stringArrayList = C1202t.d;
        }
        return new MobileApplication(str, string, string2, stringArrayList);
    }

    public final int describeContents() {
        return 0;
    }

    public final String getId() {
        return this.id;
    }

    public final Bundle getProperties() {
        return this.properties;
    }

    public final MobileApplication toContentV4() {
        return toContent();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeBundle(this.properties);
    }
}
