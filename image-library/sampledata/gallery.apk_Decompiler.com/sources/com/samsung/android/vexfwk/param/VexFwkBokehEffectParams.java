package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.vexfwk.Bokeheffect.BokehEffectType;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 \"2\u00020\u0001:\u0001\"B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u0012R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u0018\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u001bR\"\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u001c\u0010\u0012\"\u0004\b\u001d\u0010\u001bR\"\u0010\u0007\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\u0018\u001a\u0004\b\u001e\u0010\u0012\"\u0004\b\u001f\u0010\u001bR\"\u0010\b\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010\u0018\u001a\u0004\b \u0010\u0012\"\u0004\b!\u0010\u001b¨\u0006#"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "effectTypeId", "", "effectLevel", "imageOrientation", "saveAsFlip", "reLightLevel", "<init>", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;IIII)V", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "getEffectTypeId", "()Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "setEffectTypeId", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;)V", "I", "getEffectLevel", "setEffectLevel", "(I)V", "getImageOrientation", "setImageOrientation", "getSaveAsFlip", "setSaveAsFlip", "getReLightLevel", "setReLightLevel", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkBokehEffectParams implements Parcelable {
    public static final Parcelable.Creator<VexFwkBokehEffectParams> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private int effectLevel;
    private BokehEffectType effectTypeId;
    private int imageOrientation;
    private int reLightLevel;
    private int saveAsFlip;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkBokehEffectParams> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkBokehEffectParams.CREATOR;
        }

        private Companion() {
            super(new a(20));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkBokehEffectParams> {
        public final VexFwkBokehEffectParams createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkBokehEffectParams(BokehEffectType.valueOf(parcel.readString()), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        public final VexFwkBokehEffectParams[] newArray(int i2) {
            return new VexFwkBokehEffectParams[i2];
        }
    }

    public VexFwkBokehEffectParams(BokehEffectType bokehEffectType, int i2, int i7, int i8, int i10) {
        j.e(bokehEffectType, "effectTypeId");
        this.effectTypeId = bokehEffectType;
        this.effectLevel = i2;
        this.imageOrientation = i7;
        this.saveAsFlip = i8;
        this.reLightLevel = i10;
    }

    public final int describeContents() {
        return 0;
    }

    public final int getEffectLevel() {
        return this.effectLevel;
    }

    public final BokehEffectType getEffectTypeId() {
        return this.effectTypeId;
    }

    public final int getImageOrientation() {
        return this.imageOrientation;
    }

    public final int getReLightLevel() {
        return this.reLightLevel;
    }

    public final int getSaveAsFlip() {
        return this.saveAsFlip;
    }

    public final void setEffectLevel(int i2) {
        this.effectLevel = i2;
    }

    public final void setEffectTypeId(BokehEffectType bokehEffectType) {
        j.e(bokehEffectType, "<set-?>");
        this.effectTypeId = bokehEffectType;
    }

    public final void setImageOrientation(int i2) {
        this.imageOrientation = i2;
    }

    public final void setReLightLevel(int i2) {
        this.reLightLevel = i2;
    }

    public final void setSaveAsFlip(int i2) {
        this.saveAsFlip = i2;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.effectTypeId.name());
        parcel.writeInt(this.effectLevel);
        parcel.writeInt(this.imageOrientation);
        parcel.writeInt(this.saveAsFlip);
        parcel.writeInt(this.reLightLevel);
    }
}
