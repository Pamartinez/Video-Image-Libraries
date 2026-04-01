package com.samsung.android.vexfwk.param;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0000\n\u0002\b\u0010\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\n¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u001bJ\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u001e\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0010\u0010 \u001a\u00020\bHÆ\u0003¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\"\u0010\u0019J\u0010\u0010#\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b%\u0010$J\u0010\u0010&\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b&\u0010$J`\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\f2\b\b\u0002\u0010\u000f\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b'\u0010(J\u0010\u0010)\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b)\u0010\u001dJ\u0010\u0010*\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b*\u0010\u0019J\u001a\u0010-\u001a\u00020\f2\b\u0010,\u001a\u0004\u0018\u00010+HÖ\u0003¢\u0006\u0004\b-\u0010.R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u0010\u001bR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00101\u001a\u0004\b2\u0010\u001dR\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00103\u001a\u0004\b4\u0010\u001fR\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u00105\u001a\u0004\b6\u0010!R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u00107\u001a\u0004\b8\u0010\u0019R\u0017\u0010\r\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u00109\u001a\u0004\b:\u0010$R\u0017\u0010\u000e\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000e\u00109\u001a\u0004\b\u000e\u0010$R\u0017\u0010\u000f\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\u000f\u00109\u001a\u0004\b\u000f\u0010$¨\u0006;"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagInfo;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;", "tagCategory", "", "tag", "", "score", "Landroid/graphics/Rect;", "rect", "", "trackId", "", "supportedTrackId", "isSceneCategory", "isODCategory", "<init>", "(Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;Ljava/lang/String;FLandroid/graphics/Rect;IZZZ)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;", "component2", "()Ljava/lang/String;", "component3", "()F", "component4", "()Landroid/graphics/Rect;", "component5", "component6", "()Z", "component7", "component8", "copy", "(Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;Ljava/lang/String;FLandroid/graphics/Rect;IZZZ)Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagInfo;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/param/VexFwkUnifiedDetectorTagCategory;", "getTagCategory", "Ljava/lang/String;", "getTag", "F", "getScore", "Landroid/graphics/Rect;", "getRect", "I", "getTrackId", "Z", "getSupportedTrackId", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkUnifiedDetectorTagInfo implements Parcelable {
    public static final Parcelable.Creator<VexFwkUnifiedDetectorTagInfo> CREATOR = new Creator();
    private final boolean isODCategory;
    private final boolean isSceneCategory;
    private final Rect rect;
    private final float score;
    private final boolean supportedTrackId;
    private final String tag;
    private final VexFwkUnifiedDetectorTagCategory tagCategory;
    private final int trackId;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkUnifiedDetectorTagInfo> {
        public final VexFwkUnifiedDetectorTagInfo createFromParcel(Parcel parcel) {
            boolean z;
            boolean z3;
            j.e(parcel, "parcel");
            VexFwkUnifiedDetectorTagCategory createFromParcel = VexFwkUnifiedDetectorTagCategory.CREATOR.createFromParcel(parcel);
            String readString = parcel.readString();
            float readFloat = parcel.readFloat();
            Rect rect = (Rect) parcel.readParcelable(VexFwkUnifiedDetectorTagInfo.class.getClassLoader());
            int readInt = parcel.readInt();
            boolean z7 = false;
            boolean z9 = true;
            if (parcel.readInt() != 0) {
                z = false;
                z7 = true;
            } else {
                z = false;
            }
            if (parcel.readInt() != 0) {
                z3 = true;
            } else {
                z3 = true;
                z9 = z;
            }
            if (parcel.readInt() == 0) {
                z3 = z;
            }
            return new VexFwkUnifiedDetectorTagInfo(createFromParcel, readString, readFloat, rect, readInt, z7, z9, z3);
        }

        public final VexFwkUnifiedDetectorTagInfo[] newArray(int i2) {
            return new VexFwkUnifiedDetectorTagInfo[i2];
        }
    }

    public VexFwkUnifiedDetectorTagInfo(VexFwkUnifiedDetectorTagCategory vexFwkUnifiedDetectorTagCategory, String str, float f, Rect rect2, int i2, boolean z, boolean z3, boolean z7) {
        j.e(vexFwkUnifiedDetectorTagCategory, "tagCategory");
        j.e(str, "tag");
        j.e(rect2, "rect");
        this.tagCategory = vexFwkUnifiedDetectorTagCategory;
        this.tag = str;
        this.score = f;
        this.rect = rect2;
        this.trackId = i2;
        this.supportedTrackId = z;
        this.isSceneCategory = z3;
        this.isODCategory = z7;
    }

    public static /* synthetic */ VexFwkUnifiedDetectorTagInfo copy$default(VexFwkUnifiedDetectorTagInfo vexFwkUnifiedDetectorTagInfo, VexFwkUnifiedDetectorTagCategory vexFwkUnifiedDetectorTagCategory, String str, float f, Rect rect2, int i2, boolean z, boolean z3, boolean z7, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            vexFwkUnifiedDetectorTagCategory = vexFwkUnifiedDetectorTagInfo.tagCategory;
        }
        if ((i7 & 2) != 0) {
            str = vexFwkUnifiedDetectorTagInfo.tag;
        }
        if ((i7 & 4) != 0) {
            f = vexFwkUnifiedDetectorTagInfo.score;
        }
        if ((i7 & 8) != 0) {
            rect2 = vexFwkUnifiedDetectorTagInfo.rect;
        }
        if ((i7 & 16) != 0) {
            i2 = vexFwkUnifiedDetectorTagInfo.trackId;
        }
        if ((i7 & 32) != 0) {
            z = vexFwkUnifiedDetectorTagInfo.supportedTrackId;
        }
        if ((i7 & 64) != 0) {
            z3 = vexFwkUnifiedDetectorTagInfo.isSceneCategory;
        }
        if ((i7 & 128) != 0) {
            z7 = vexFwkUnifiedDetectorTagInfo.isODCategory;
        }
        boolean z9 = z3;
        boolean z10 = z7;
        int i8 = i2;
        boolean z11 = z;
        Rect rect3 = rect2;
        String str2 = str;
        return vexFwkUnifiedDetectorTagInfo.copy(vexFwkUnifiedDetectorTagCategory, str2, f, rect3, i8, z11, z9, z10);
    }

    public final VexFwkUnifiedDetectorTagCategory component1() {
        return this.tagCategory;
    }

    public final String component2() {
        return this.tag;
    }

    public final float component3() {
        return this.score;
    }

    public final Rect component4() {
        return this.rect;
    }

    public final int component5() {
        return this.trackId;
    }

    public final boolean component6() {
        return this.supportedTrackId;
    }

    public final boolean component7() {
        return this.isSceneCategory;
    }

    public final boolean component8() {
        return this.isODCategory;
    }

    public final VexFwkUnifiedDetectorTagInfo copy(VexFwkUnifiedDetectorTagCategory vexFwkUnifiedDetectorTagCategory, String str, float f, Rect rect2, int i2, boolean z, boolean z3, boolean z7) {
        j.e(vexFwkUnifiedDetectorTagCategory, "tagCategory");
        j.e(str, "tag");
        j.e(rect2, "rect");
        return new VexFwkUnifiedDetectorTagInfo(vexFwkUnifiedDetectorTagCategory, str, f, rect2, i2, z, z3, z7);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkUnifiedDetectorTagInfo)) {
            return false;
        }
        VexFwkUnifiedDetectorTagInfo vexFwkUnifiedDetectorTagInfo = (VexFwkUnifiedDetectorTagInfo) obj;
        if (this.tagCategory == vexFwkUnifiedDetectorTagInfo.tagCategory && j.a(this.tag, vexFwkUnifiedDetectorTagInfo.tag) && Float.compare(this.score, vexFwkUnifiedDetectorTagInfo.score) == 0 && j.a(this.rect, vexFwkUnifiedDetectorTagInfo.rect) && this.trackId == vexFwkUnifiedDetectorTagInfo.trackId && this.supportedTrackId == vexFwkUnifiedDetectorTagInfo.supportedTrackId && this.isSceneCategory == vexFwkUnifiedDetectorTagInfo.isSceneCategory && this.isODCategory == vexFwkUnifiedDetectorTagInfo.isODCategory) {
            return true;
        }
        return false;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final float getScore() {
        return this.score;
    }

    public final boolean getSupportedTrackId() {
        return this.supportedTrackId;
    }

    public final String getTag() {
        return this.tag;
    }

    public final VexFwkUnifiedDetectorTagCategory getTagCategory() {
        return this.tagCategory;
    }

    public final int getTrackId() {
        return this.trackId;
    }

    public int hashCode() {
        int a7 = N2.j.a(this.score, C0212a.d(this.tagCategory.hashCode() * 31, 31, this.tag), 31);
        return Boolean.hashCode(this.isODCategory) + C0212a.e(C0212a.e(C0212a.b(this.trackId, (this.rect.hashCode() + a7) * 31, 31), 31, this.supportedTrackId), 31, this.isSceneCategory);
    }

    public final boolean isODCategory() {
        return this.isODCategory;
    }

    public final boolean isSceneCategory() {
        return this.isSceneCategory;
    }

    public String toString() {
        VexFwkUnifiedDetectorTagCategory vexFwkUnifiedDetectorTagCategory = this.tagCategory;
        String str = this.tag;
        float f = this.score;
        Rect rect2 = this.rect;
        int i2 = this.trackId;
        boolean z = this.supportedTrackId;
        boolean z3 = this.isSceneCategory;
        boolean z7 = this.isODCategory;
        return "VexFwkUnifiedDetectorTagInfo(tagCategory=" + vexFwkUnifiedDetectorTagCategory + ", tag=" + str + ", score=" + f + ", rect=" + rect2 + ", trackId=" + i2 + ", supportedTrackId=" + z + ", isSceneCategory=" + z3 + ", isODCategory=" + z7 + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        this.tagCategory.writeToParcel(parcel, i2);
        parcel.writeString(this.tag);
        parcel.writeFloat(this.score);
        parcel.writeParcelable(this.rect, i2);
        parcel.writeInt(this.trackId);
        parcel.writeInt(this.supportedTrackId ? 1 : 0);
        parcel.writeInt(this.isSceneCategory ? 1 : 0);
        parcel.writeInt(this.isODCategory ? 1 : 0);
    }
}
