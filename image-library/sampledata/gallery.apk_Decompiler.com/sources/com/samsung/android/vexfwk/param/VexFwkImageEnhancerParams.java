package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.imageenhancer.EnhanceType;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\r\u0010\r\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0016\u0010\u000eJ\u001a\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u0010¨\u0006\u001f"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageEnhancerParams;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;", "enhanceType", "<init>", "(Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;)V", "Landroid/os/Parcel;", "dest", "", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;", "copy", "(Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;)Lcom/samsung/android/vexfwk/param/VexFwkImageEnhancerParams;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/imageenhancer/EnhanceType;", "getEnhanceType", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageEnhancerParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageEnhancerParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private final EnhanceType enhanceType;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageEnhancerParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkImageEnhancerParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkImageEnhancerParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkImageEnhancerParams.CREATOR;
        }

        private Companion() {
            super(new a(28));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageEnhancerParams> {
        public final VexFwkImageEnhancerParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkImageEnhancerParams(EnhanceType.valueOf(parcel.readString()));
        }

        public final VexFwkImageEnhancerParams[] newArray(int i2) {
            return new VexFwkImageEnhancerParams[i2];
        }
    }

    public VexFwkImageEnhancerParams(EnhanceType enhanceType2) {
        j.e(enhanceType2, "enhanceType");
        this.enhanceType = enhanceType2;
    }

    public static /* synthetic */ VexFwkImageEnhancerParams copy$default(VexFwkImageEnhancerParams vexFwkImageEnhancerParams, EnhanceType enhanceType2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            enhanceType2 = vexFwkImageEnhancerParams.enhanceType;
        }
        return vexFwkImageEnhancerParams.copy(enhanceType2);
    }

    public final EnhanceType component1() {
        return this.enhanceType;
    }

    public final VexFwkImageEnhancerParams copy(EnhanceType enhanceType2) {
        j.e(enhanceType2, "enhanceType");
        return new VexFwkImageEnhancerParams(enhanceType2);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VexFwkImageEnhancerParams) && this.enhanceType == ((VexFwkImageEnhancerParams) obj).enhanceType) {
            return true;
        }
        return false;
    }

    public final EnhanceType getEnhanceType() {
        return this.enhanceType;
    }

    public int hashCode() {
        return this.enhanceType.hashCode();
    }

    public String toString() {
        EnhanceType enhanceType2 = this.enhanceType;
        return "VexFwkImageEnhancerParams(enhanceType=" + enhanceType2 + ")";
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.enhanceType.name());
    }
}
