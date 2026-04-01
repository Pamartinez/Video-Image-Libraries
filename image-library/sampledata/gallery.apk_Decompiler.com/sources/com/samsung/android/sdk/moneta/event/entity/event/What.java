package com.samsung.android.sdk.moneta.event.entity.event;

import android.os.Parcel;
import android.os.Parcelable;
import c0.C0086a;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\n\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0005HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u0015J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0015JB\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u0010\u0010\u001e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u001e\u0010\u0013J\u001a\u0010!\u001a\u00020\u00052\b\u0010 \u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b!\u0010\"R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010#\u001a\u0004\b$\u0010\u0015R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010#\u001a\u0004\b%\u0010\u0015R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010&\u001a\u0004\b\u0006\u0010\u0018R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b'\u0010\u0015R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010#\u001a\u0004\b(\u0010\u0015¨\u0006)"}, d2 = {"Lcom/samsung/android/sdk/moneta/event/entity/event/What;", "Landroid/os/Parcelable;", "", "title", "category", "", "isMain", "sourcePackage", "sourceUri", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Ljava/lang/String;", "component2", "component3", "()Z", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)Lcom/samsung/android/sdk/moneta/event/entity/event/What;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Ljava/lang/String;", "getTitle", "getCategory", "Z", "getSourcePackage", "getSourceUri", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class What implements Parcelable {
    public static final Parcelable.Creator<What> CREATOR = new Creator();
    private final /* synthetic */ String category;
    private final /* synthetic */ boolean isMain;
    private final /* synthetic */ String sourcePackage;
    private final /* synthetic */ String sourceUri;
    private final /* synthetic */ String title;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<What> {
        public final What createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new What(parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        public final What[] newArray(int i2) {
            return new What[i2];
        }
    }

    public What(String str, String str2, boolean z, String str3, String str4) {
        j.e(str, "title");
        j.e(str2, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(str3, "sourcePackage");
        j.e(str4, "sourceUri");
        this.title = str;
        this.category = str2;
        this.isMain = z;
        this.sourcePackage = str3;
        this.sourceUri = str4;
    }

    public static /* synthetic */ What copy$default(What what, String str, String str2, boolean z, String str3, String str4, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = what.title;
        }
        if ((i2 & 2) != 0) {
            str2 = what.category;
        }
        if ((i2 & 4) != 0) {
            z = what.isMain;
        }
        if ((i2 & 8) != 0) {
            str3 = what.sourcePackage;
        }
        if ((i2 & 16) != 0) {
            str4 = what.sourceUri;
        }
        String str5 = str3;
        String str6 = str4;
        return what.copy(str, str2, z, str5, str6);
    }

    public final String component1() {
        return this.title;
    }

    public final String component2() {
        return this.category;
    }

    public final boolean component3() {
        return this.isMain;
    }

    public final String component4() {
        return this.sourcePackage;
    }

    public final String component5() {
        return this.sourceUri;
    }

    public final What copy(String str, String str2, boolean z, String str3, String str4) {
        j.e(str, "title");
        j.e(str2, KeywordBundleWrapper.BUNDLE_KEY_CATEGORY);
        j.e(str3, "sourcePackage");
        j.e(str4, "sourceUri");
        return new What(str, str2, z, str3, str4);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof What)) {
            return false;
        }
        What what = (What) obj;
        if (j.a(this.title, what.title) && j.a(this.category, what.category) && this.isMain == what.isMain && j.a(this.sourcePackage, what.sourcePackage) && j.a(this.sourceUri, what.sourceUri)) {
            return true;
        }
        return false;
    }

    public final String getCategory() {
        return this.category;
    }

    public final String getSourcePackage() {
        return this.sourcePackage;
    }

    public final String getSourceUri() {
        return this.sourceUri;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return this.sourceUri.hashCode() + C0212a.d(C0212a.e(C0212a.d(this.title.hashCode() * 31, 31, this.category), 31, this.isMain), 31, this.sourcePackage);
    }

    public final boolean isMain() {
        return this.isMain;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("What(title=");
        sb2.append(this.title);
        sb2.append(", category=");
        sb2.append(this.category);
        sb2.append(", isMain=");
        sb2.append(this.isMain);
        sb2.append(", sourcePackage=");
        sb2.append(this.sourcePackage);
        sb2.append(", sourceUri=");
        return C0086a.m(sb2, this.sourceUri, ')');
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.title);
        parcel.writeString(this.category);
        parcel.writeInt(this.isMain ? 1 : 0);
        parcel.writeString(this.sourcePackage);
        parcel.writeString(this.sourceUri);
    }
}
