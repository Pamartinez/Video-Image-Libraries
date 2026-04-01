package com.samsung.android.vexfwk.param;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.sdk.mobileservice.social.buddy.provider.BuddyContract;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u001d\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\f¢\u0006\u0004\b\u0015\u0010\u0016J\r\u0010\u0017\u001a\u00020\f¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001cJ\u0010\u0010 \u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b \u0010\u001eJ\u0010\u0010!\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b#\u0010\u0018J\u0010\u0010$\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b$\u0010\u0018J`\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b%\u0010&J\u0010\u0010'\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b'\u0010\u001cJ\u0010\u0010(\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b(\u0010\u0018J\u001a\u0010,\u001a\u00020+2\b\u0010*\u001a\u0004\u0018\u00010)HÖ\u0003¢\u0006\u0004\b,\u0010-R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010.\u001a\u0004\b/\u0010\u001aR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00100\u001a\u0004\b1\u0010\u001cR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00102\u001a\u0004\b3\u0010\u001eR\u0017\u0010\b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\b\u00100\u001a\u0004\b4\u0010\u001cR\u0017\u0010\t\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\t\u00102\u001a\u0004\b5\u0010\u001eR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00106\u001a\u0004\b7\u0010\"R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u00108\u001a\u0004\b9\u0010\u0018R\u0017\u0010\u000e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000e\u00108\u001a\u0004\b:\u0010\u0018¨\u0006;"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/param/EntityType;", "entityType", "", "mainCategoryTag", "", "mainCategoryTagScore", "subCategoryTag", "subCategoryTagScore", "Landroid/graphics/Rect;", "region", "", "clusterId", "clusterIdStory", "<init>", "(Lcom/samsung/android/vexfwk/param/EntityType;Ljava/lang/String;FLjava/lang/String;FLandroid/graphics/Rect;II)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/vexfwk/param/EntityType;", "component2", "()Ljava/lang/String;", "component3", "()F", "component4", "component5", "component6", "()Landroid/graphics/Rect;", "component7", "component8", "copy", "(Lcom/samsung/android/vexfwk/param/EntityType;Ljava/lang/String;FLjava/lang/String;FLandroid/graphics/Rect;II)Lcom/samsung/android/vexfwk/param/VexFwkImageTagEntityProp;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/param/EntityType;", "getEntityType", "Ljava/lang/String;", "getMainCategoryTag", "F", "getMainCategoryTagScore", "getSubCategoryTag", "getSubCategoryTagScore", "Landroid/graphics/Rect;", "getRegion", "I", "getClusterId", "getClusterIdStory", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageTagEntityProp implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageTagEntityProp> CREATOR = new Creator();
    private final int clusterId;
    private final int clusterIdStory;
    private final EntityType entityType;
    private final String mainCategoryTag;
    private final float mainCategoryTagScore;
    private final Rect region;
    private final String subCategoryTag;
    private final float subCategoryTagScore;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageTagEntityProp> {
        public final VexFwkImageTagEntityProp createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkImageTagEntityProp(EntityType.CREATOR.createFromParcel(parcel), parcel.readString(), parcel.readFloat(), parcel.readString(), parcel.readFloat(), (Rect) parcel.readParcelable(VexFwkImageTagEntityProp.class.getClassLoader()), parcel.readInt(), parcel.readInt());
        }

        public final VexFwkImageTagEntityProp[] newArray(int i2) {
            return new VexFwkImageTagEntityProp[i2];
        }
    }

    public VexFwkImageTagEntityProp(EntityType entityType2, String str, float f, String str2, float f5, Rect rect, int i2, int i7) {
        j.e(entityType2, "entityType");
        j.e(str, "mainCategoryTag");
        j.e(str2, "subCategoryTag");
        j.e(rect, BuddyContract.Address.REGION);
        this.entityType = entityType2;
        this.mainCategoryTag = str;
        this.mainCategoryTagScore = f;
        this.subCategoryTag = str2;
        this.subCategoryTagScore = f5;
        this.region = rect;
        this.clusterId = i2;
        this.clusterIdStory = i7;
    }

    public static /* synthetic */ VexFwkImageTagEntityProp copy$default(VexFwkImageTagEntityProp vexFwkImageTagEntityProp, EntityType entityType2, String str, float f, String str2, float f5, Rect rect, int i2, int i7, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            entityType2 = vexFwkImageTagEntityProp.entityType;
        }
        if ((i8 & 2) != 0) {
            str = vexFwkImageTagEntityProp.mainCategoryTag;
        }
        if ((i8 & 4) != 0) {
            f = vexFwkImageTagEntityProp.mainCategoryTagScore;
        }
        if ((i8 & 8) != 0) {
            str2 = vexFwkImageTagEntityProp.subCategoryTag;
        }
        if ((i8 & 16) != 0) {
            f5 = vexFwkImageTagEntityProp.subCategoryTagScore;
        }
        if ((i8 & 32) != 0) {
            rect = vexFwkImageTagEntityProp.region;
        }
        if ((i8 & 64) != 0) {
            i2 = vexFwkImageTagEntityProp.clusterId;
        }
        if ((i8 & 128) != 0) {
            i7 = vexFwkImageTagEntityProp.clusterIdStory;
        }
        int i10 = i2;
        int i11 = i7;
        float f8 = f5;
        Rect rect2 = rect;
        String str3 = str2;
        String str4 = str;
        return vexFwkImageTagEntityProp.copy(entityType2, str4, f, str3, f8, rect2, i10, i11);
    }

    public final EntityType component1() {
        return this.entityType;
    }

    public final String component2() {
        return this.mainCategoryTag;
    }

    public final float component3() {
        return this.mainCategoryTagScore;
    }

    public final String component4() {
        return this.subCategoryTag;
    }

    public final float component5() {
        return this.subCategoryTagScore;
    }

    public final Rect component6() {
        return this.region;
    }

    public final int component7() {
        return this.clusterId;
    }

    public final int component8() {
        return this.clusterIdStory;
    }

    public final VexFwkImageTagEntityProp copy(EntityType entityType2, String str, float f, String str2, float f5, Rect rect, int i2, int i7) {
        j.e(entityType2, "entityType");
        j.e(str, "mainCategoryTag");
        j.e(str2, "subCategoryTag");
        j.e(rect, BuddyContract.Address.REGION);
        return new VexFwkImageTagEntityProp(entityType2, str, f, str2, f5, rect, i2, i7);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkImageTagEntityProp)) {
            return false;
        }
        VexFwkImageTagEntityProp vexFwkImageTagEntityProp = (VexFwkImageTagEntityProp) obj;
        if (this.entityType == vexFwkImageTagEntityProp.entityType && j.a(this.mainCategoryTag, vexFwkImageTagEntityProp.mainCategoryTag) && Float.compare(this.mainCategoryTagScore, vexFwkImageTagEntityProp.mainCategoryTagScore) == 0 && j.a(this.subCategoryTag, vexFwkImageTagEntityProp.subCategoryTag) && Float.compare(this.subCategoryTagScore, vexFwkImageTagEntityProp.subCategoryTagScore) == 0 && j.a(this.region, vexFwkImageTagEntityProp.region) && this.clusterId == vexFwkImageTagEntityProp.clusterId && this.clusterIdStory == vexFwkImageTagEntityProp.clusterIdStory) {
            return true;
        }
        return false;
    }

    public final int getClusterId() {
        return this.clusterId;
    }

    public final int getClusterIdStory() {
        return this.clusterIdStory;
    }

    public final EntityType getEntityType() {
        return this.entityType;
    }

    public final String getMainCategoryTag() {
        return this.mainCategoryTag;
    }

    public final float getMainCategoryTagScore() {
        return this.mainCategoryTagScore;
    }

    public final Rect getRegion() {
        return this.region;
    }

    public final String getSubCategoryTag() {
        return this.subCategoryTag;
    }

    public final float getSubCategoryTagScore() {
        return this.subCategoryTagScore;
    }

    public int hashCode() {
        int a7 = N2.j.a(this.subCategoryTagScore, C0212a.d(N2.j.a(this.mainCategoryTagScore, C0212a.d(this.entityType.hashCode() * 31, 31, this.mainCategoryTag), 31), 31, this.subCategoryTag), 31);
        return Integer.hashCode(this.clusterIdStory) + C0212a.b(this.clusterId, (this.region.hashCode() + a7) * 31, 31);
    }

    public String toString() {
        EntityType entityType2 = this.entityType;
        String str = this.mainCategoryTag;
        float f = this.mainCategoryTagScore;
        String str2 = this.subCategoryTag;
        float f5 = this.subCategoryTagScore;
        Rect rect = this.region;
        int i2 = this.clusterId;
        int i7 = this.clusterIdStory;
        return "VexFwkImageTagEntityProp(entityType=" + entityType2 + ", mainCategoryTag=" + str + ", mainCategoryTagScore=" + f + ", subCategoryTag=" + str2 + ", subCategoryTagScore=" + f5 + ", region=" + rect + ", clusterId=" + i2 + ", clusterIdStory=" + i7 + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        this.entityType.writeToParcel(parcel, i2);
        parcel.writeString(this.mainCategoryTag);
        parcel.writeFloat(this.mainCategoryTagScore);
        parcel.writeString(this.subCategoryTag);
        parcel.writeFloat(this.subCategoryTagScore);
        parcel.writeParcelable(this.region, i2);
        parcel.writeInt(this.clusterId);
        parcel.writeInt(this.clusterIdStory);
    }
}
