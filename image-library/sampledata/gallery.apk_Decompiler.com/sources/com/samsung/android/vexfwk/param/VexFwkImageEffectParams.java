package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.imageeffect.ImageEffectType;
import i.C0212a;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u001b\b\b\u0018\u0000 C2\u00020\u0001:\u0001CBG\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0017J\u0010\u0010\u001b\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u0017J\u0010\u0010\u001c\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001c\u0010\u0017J\u0010\u0010\u001d\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0017J\u0010\u0010\u001e\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0017J\u0010\u0010\u001f\u001a\u00020\nHÆ\u0003¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\fHÆ\u0003¢\u0006\u0004\b!\u0010\"J`\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001¢\u0006\u0004\b#\u0010$J\u0010\u0010&\u001a\u00020%HÖ\u0001¢\u0006\u0004\b&\u0010'J\u0010\u0010(\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b(\u0010\u0017J\u001a\u0010+\u001a\u00020\f2\b\u0010*\u001a\u0004\u0018\u00010)HÖ\u0003¢\u0006\u0004\b+\u0010,R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010-\u001a\u0004\b.\u0010\u0019R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010/\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u00102R\"\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010/\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u00102R\"\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010/\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u00102R\"\u0010\b\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010/\u001a\u0004\b7\u0010\u0017\"\u0004\b8\u00102R\"\u0010\t\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\t\u0010/\u001a\u0004\b9\u0010\u0017\"\u0004\b:\u00102R\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010;\u001a\u0004\b<\u0010 \"\u0004\b=\u0010>R\"\u0010\r\u001a\u00020\f8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\r\u0010?\u001a\u0004\b@\u0010\"\"\u0004\bA\u0010B¨\u0006D"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "effect", "", "intensity", "temperature", "saturation", "contrast", "grain", "", "radius", "", "applyNegativeSegMap", "<init>", "(Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;IIIIIFZ)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "component1", "()Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "component2", "component3", "component4", "component5", "component6", "component7", "()F", "component8", "()Z", "copy", "(Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;IIIIIFZ)Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "Lcom/samsung/android/vexfwk/imageeffect/ImageEffectType;", "getEffect", "I", "getIntensity", "setIntensity", "(I)V", "getTemperature", "setTemperature", "getSaturation", "setSaturation", "getContrast", "setContrast", "getGrain", "setGrain", "F", "getRadius", "setRadius", "(F)V", "Z", "getApplyNegativeSegMap", "setApplyNegativeSegMap", "(Z)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkImageEffectParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkImageEffectParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private boolean applyNegativeSegMap;
    private int contrast;
    private final ImageEffectType effect;
    private int grain;
    private int intensity;
    private float radius;
    private int saturation;
    private int temperature;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkImageEffectParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkImageEffectParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkImageEffectParams.CREATOR;
        }

        private Companion() {
            super(new a(27));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkImageEffectParams> {
        public final VexFwkImageEffectParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkImageEffectParams(ImageEffectType.valueOf(parcel.readString()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readFloat(), parcel.readInt() != 0);
        }

        public final VexFwkImageEffectParams[] newArray(int i2) {
            return new VexFwkImageEffectParams[i2];
        }
    }

    public VexFwkImageEffectParams(ImageEffectType imageEffectType, int i2, int i7, int i8, int i10, int i11, float f, boolean z) {
        j.e(imageEffectType, "effect");
        this.effect = imageEffectType;
        this.intensity = i2;
        this.temperature = i7;
        this.saturation = i8;
        this.contrast = i10;
        this.grain = i11;
        this.radius = f;
        this.applyNegativeSegMap = z;
    }

    public static /* synthetic */ VexFwkImageEffectParams copy$default(VexFwkImageEffectParams vexFwkImageEffectParams, ImageEffectType imageEffectType, int i2, int i7, int i8, int i10, int i11, float f, boolean z, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            imageEffectType = vexFwkImageEffectParams.effect;
        }
        if ((i12 & 2) != 0) {
            i2 = vexFwkImageEffectParams.intensity;
        }
        if ((i12 & 4) != 0) {
            i7 = vexFwkImageEffectParams.temperature;
        }
        if ((i12 & 8) != 0) {
            i8 = vexFwkImageEffectParams.saturation;
        }
        if ((i12 & 16) != 0) {
            i10 = vexFwkImageEffectParams.contrast;
        }
        if ((i12 & 32) != 0) {
            i11 = vexFwkImageEffectParams.grain;
        }
        if ((i12 & 64) != 0) {
            f = vexFwkImageEffectParams.radius;
        }
        if ((i12 & 128) != 0) {
            z = vexFwkImageEffectParams.applyNegativeSegMap;
        }
        float f5 = f;
        boolean z3 = z;
        int i13 = i10;
        int i14 = i11;
        int i15 = i8;
        int i16 = i2;
        return vexFwkImageEffectParams.copy(imageEffectType, i16, i7, i15, i13, i14, f5, z3);
    }

    public final ImageEffectType component1() {
        return this.effect;
    }

    public final int component2() {
        return this.intensity;
    }

    public final int component3() {
        return this.temperature;
    }

    public final int component4() {
        return this.saturation;
    }

    public final int component5() {
        return this.contrast;
    }

    public final int component6() {
        return this.grain;
    }

    public final float component7() {
        return this.radius;
    }

    public final boolean component8() {
        return this.applyNegativeSegMap;
    }

    public final VexFwkImageEffectParams copy(ImageEffectType imageEffectType, int i2, int i7, int i8, int i10, int i11, float f, boolean z) {
        j.e(imageEffectType, "effect");
        return new VexFwkImageEffectParams(imageEffectType, i2, i7, i8, i10, i11, f, z);
    }

    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VexFwkImageEffectParams)) {
            return false;
        }
        VexFwkImageEffectParams vexFwkImageEffectParams = (VexFwkImageEffectParams) obj;
        if (this.effect == vexFwkImageEffectParams.effect && this.intensity == vexFwkImageEffectParams.intensity && this.temperature == vexFwkImageEffectParams.temperature && this.saturation == vexFwkImageEffectParams.saturation && this.contrast == vexFwkImageEffectParams.contrast && this.grain == vexFwkImageEffectParams.grain && Float.compare(this.radius, vexFwkImageEffectParams.radius) == 0 && this.applyNegativeSegMap == vexFwkImageEffectParams.applyNegativeSegMap) {
            return true;
        }
        return false;
    }

    public final boolean getApplyNegativeSegMap() {
        return this.applyNegativeSegMap;
    }

    public final int getContrast() {
        return this.contrast;
    }

    public final ImageEffectType getEffect() {
        return this.effect;
    }

    public final int getGrain() {
        return this.grain;
    }

    public final int getIntensity() {
        return this.intensity;
    }

    public final float getRadius() {
        return this.radius;
    }

    public final int getSaturation() {
        return this.saturation;
    }

    public final int getTemperature() {
        return this.temperature;
    }

    public int hashCode() {
        return Boolean.hashCode(this.applyNegativeSegMap) + N2.j.a(this.radius, C0212a.b(this.grain, C0212a.b(this.contrast, C0212a.b(this.saturation, C0212a.b(this.temperature, C0212a.b(this.intensity, this.effect.hashCode() * 31, 31), 31), 31), 31), 31), 31);
    }

    public final void setApplyNegativeSegMap(boolean z) {
        this.applyNegativeSegMap = z;
    }

    public final void setContrast(int i2) {
        this.contrast = i2;
    }

    public final void setGrain(int i2) {
        this.grain = i2;
    }

    public final void setIntensity(int i2) {
        this.intensity = i2;
    }

    public final void setRadius(float f) {
        this.radius = f;
    }

    public final void setSaturation(int i2) {
        this.saturation = i2;
    }

    public final void setTemperature(int i2) {
        this.temperature = i2;
    }

    public String toString() {
        ImageEffectType imageEffectType = this.effect;
        int i2 = this.intensity;
        int i7 = this.temperature;
        int i8 = this.saturation;
        int i10 = this.contrast;
        int i11 = this.grain;
        float f = this.radius;
        boolean z = this.applyNegativeSegMap;
        StringBuilder sb2 = new StringBuilder("VexFwkImageEffectParams(effect=");
        sb2.append(imageEffectType);
        sb2.append(", intensity=");
        sb2.append(i2);
        sb2.append(", temperature=");
        N2.j.x(sb2, i7, ", saturation=", i8, ", contrast=");
        N2.j.x(sb2, i10, ", grain=", i11, ", radius=");
        sb2.append(f);
        sb2.append(", applyNegativeSegMap=");
        sb2.append(z);
        sb2.append(")");
        return sb2.toString();
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.effect.name());
        parcel.writeInt(this.intensity);
        parcel.writeInt(this.temperature);
        parcel.writeInt(this.saturation);
        parcel.writeInt(this.contrast);
        parcel.writeInt(this.grain);
        parcel.writeFloat(this.radius);
        parcel.writeInt(this.applyNegativeSegMap ? 1 : 0);
    }
}
