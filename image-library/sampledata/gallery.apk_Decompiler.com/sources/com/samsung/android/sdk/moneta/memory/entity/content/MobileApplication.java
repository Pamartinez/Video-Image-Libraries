package com.samsung.android.sdk.moneta.memory.entity.content;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.MobileApplicationBundleWrapper;
import i.C0212a;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0014J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J>\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0014J\u0010\u0010\u001c\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u0012J\u001a\u0010 \u001a\u00020\u001f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b \u0010!R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0004¢\u0006\f\n\u0004\b\u0003\u0010\"\u001a\u0004\b#\u0010\u0014R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\"\u001a\u0004\b$\u0010\u0014R\u0017\u0010\u0005\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\"\u001a\u0004\b%\u0010\u0014R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010&\u001a\u0004\b'\u0010\u0018¨\u0006("}, d2 = {"Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/Content;", "", "id", "packageId", "name", "", "altNames", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "()Ljava/util/List;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lcom/samsung/android/sdk/moneta/memory/entity/content/MobileApplication;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getId", "getPackageId", "getName", "Ljava/util/List;", "getAltNames", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MobileApplication extends Content {
    public static final Parcelable.Creator<MobileApplication> CREATOR = new Creator();
    private final List<String> altNames;
    private final String id;
    private final String name;
    private final String packageId;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<MobileApplication> {
        public final MobileApplication createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new MobileApplication(parcel.readString(), parcel.readString(), parcel.readString(), parcel.createStringArrayList());
        }

        public final MobileApplication[] newArray(int i2) {
            return new MobileApplication[i2];
        }
    }

    public MobileApplication(String str, String str2, String str3, List<String> list) {
        j.e(str, "id");
        j.e(str2, MobileApplicationBundleWrapper.BUNDLE_KEY_PACKAGE_ID);
        j.e(str3, "name");
        j.e(list, MobileApplicationBundleWrapper.BUNDLE_KEY_ALT_NAMES);
        this.id = str;
        this.packageId = str2;
        this.name = str3;
        this.altNames = list;
    }

    public static /* synthetic */ MobileApplication copy$default(MobileApplication mobileApplication, String str, String str2, String str3, List<String> list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = mobileApplication.id;
        }
        if ((i2 & 2) != 0) {
            str2 = mobileApplication.packageId;
        }
        if ((i2 & 4) != 0) {
            str3 = mobileApplication.name;
        }
        if ((i2 & 8) != 0) {
            list = mobileApplication.altNames;
        }
        return mobileApplication.copy(str, str2, str3, list);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.packageId;
    }

    public final String component3() {
        return this.name;
    }

    public final List<String> component4() {
        return this.altNames;
    }

    public final MobileApplication copy(String str, String str2, String str3, List<String> list) {
        j.e(str, "id");
        j.e(str2, MobileApplicationBundleWrapper.BUNDLE_KEY_PACKAGE_ID);
        j.e(str3, "name");
        j.e(list, MobileApplicationBundleWrapper.BUNDLE_KEY_ALT_NAMES);
        return new MobileApplication(str, str2, str3, list);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MobileApplication)) {
            return false;
        }
        MobileApplication mobileApplication = (MobileApplication) obj;
        if (j.a(this.id, mobileApplication.id) && j.a(this.packageId, mobileApplication.packageId) && j.a(this.name, mobileApplication.name) && j.a(this.altNames, mobileApplication.altNames)) {
            return true;
        }
        return false;
    }

    public final List<String> getAltNames() {
        return this.altNames;
    }

    public String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPackageId() {
        return this.packageId;
    }

    public int hashCode() {
        return this.altNames.hashCode() + C0212a.d(C0212a.d(this.id.hashCode() * 31, 31, this.packageId), 31, this.name);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("MobileApplication(id=");
        sb2.append(this.id);
        sb2.append(", packageId=");
        sb2.append(this.packageId);
        sb2.append(", name=");
        sb2.append(this.name);
        sb2.append(", altNames=");
        return C0212a.r(sb2, this.altNames, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.id);
        parcel.writeString(this.packageId);
        parcel.writeString(this.name);
        parcel.writeStringList(this.altNames);
    }
}
