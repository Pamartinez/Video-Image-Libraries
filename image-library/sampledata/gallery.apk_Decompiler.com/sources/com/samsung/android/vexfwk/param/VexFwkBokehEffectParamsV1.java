package com.samsung.android.vexfwk.param;

import android.os.Parcel;
import android.os.Parcelable;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.vexfwk.Bokeheffect.BokehEffectType;
import j4.a;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fB\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000b\u0010\u000fJ\r\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\r\u0010\u0018\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019R\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u001f\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\"R\"\u0010\u0006\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\u0006\u0010\u001f\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010\"R\"\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\b\u0010%\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\"\u0010\n\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0012\n\u0004\b\n\u0010*\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00060"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1;", "Landroid/os/Parcelable;", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "effectTypeId", "", "effectLevel", "reLightLevel", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "imageOrientation", "", "saveAsFlip", "<init>", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;IILcom/samsung/android/vexfwk/param/VexFwkOrientation;Z)V", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;", "effectParams", "(Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;)V", "convertToVexFwkBokehEffectParams", "()Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParams;", "Landroid/os/Parcel;", "dest", "flags", "Lme/x;", "writeToParcel", "(Landroid/os/Parcel;I)V", "describeContents", "()I", "Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "getEffectTypeId", "()Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;", "setEffectTypeId", "(Lcom/samsung/android/vexfwk/Bokeheffect/BokehEffectType;)V", "I", "getEffectLevel", "setEffectLevel", "(I)V", "getReLightLevel", "setReLightLevel", "Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "getImageOrientation", "()Lcom/samsung/android/vexfwk/param/VexFwkOrientation;", "setImageOrientation", "(Lcom/samsung/android/vexfwk/param/VexFwkOrientation;)V", "Z", "getSaveAsFlip", "()Z", "setSaveAsFlip", "(Z)V", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkBokehEffectParamsV1 implements Parcelable {
    public static final Parcelable.Creator<VexFwkBokehEffectParamsV1> CREATOR = new Creator();
    public static final Companion Companion = new Companion((e) null);
    private int effectLevel;
    private BokehEffectType effectTypeId;
    private VexFwkOrientation imageOrientation;
    private int reLightLevel;
    private boolean saveAsFlip;

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1$Companion;", "Lcom/samsung/android/vexfwk/param/VexFwkParamBaseParcelable;", "Lcom/samsung/android/vexfwk/param/VexFwkBokehEffectParamsV1;", "<init>", "()V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion extends VexFwkParamBaseParcelable<VexFwkBokehEffectParamsV1> {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        public static final Parcelable.Creator _init_$lambda$0() {
            return VexFwkBokehEffectParamsV1.CREATOR;
        }

        private Companion() {
            super(new a(21));
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Creator implements Parcelable.Creator<VexFwkBokehEffectParamsV1> {
        public final VexFwkBokehEffectParamsV1 createFromParcel(Parcel parcel) {
            j.e(parcel, "parcel");
            return new VexFwkBokehEffectParamsV1(BokehEffectType.valueOf(parcel.readString()), parcel.readInt(), parcel.readInt(), VexFwkOrientation.valueOf(parcel.readString()), parcel.readInt() != 0);
        }

        public final VexFwkBokehEffectParamsV1[] newArray(int i2) {
            return new VexFwkBokehEffectParamsV1[i2];
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                com.samsung.android.vexfwk.param.VexFwkOrientation[] r0 = com.samsung.android.vexfwk.param.VexFwkOrientation.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.samsung.android.vexfwk.param.VexFwkOrientation r1 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_0     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                com.samsung.android.vexfwk.param.VexFwkOrientation r1 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_90     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                com.samsung.android.vexfwk.param.VexFwkOrientation r1 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_180     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                com.samsung.android.vexfwk.param.VexFwkOrientation r1 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_270     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1.WhenMappings.<clinit>():void");
        }
    }

    public VexFwkBokehEffectParamsV1(BokehEffectType bokehEffectType, int i2, int i7, VexFwkOrientation vexFwkOrientation, boolean z) {
        j.e(bokehEffectType, "effectTypeId");
        j.e(vexFwkOrientation, "imageOrientation");
        this.effectTypeId = bokehEffectType;
        this.effectLevel = i2;
        this.reLightLevel = i7;
        this.imageOrientation = vexFwkOrientation;
        this.saveAsFlip = z;
    }

    public final VexFwkBokehEffectParams convertToVexFwkBokehEffectParams() {
        int i2;
        BokehEffectType bokehEffectType = this.effectTypeId;
        int i7 = this.effectLevel;
        int i8 = WhenMappings.$EnumSwitchMapping$0[this.imageOrientation.ordinal()];
        if (i8 == 1) {
            i2 = 270;
        } else if (i8 == 2) {
            i2 = 0;
        } else if (i8 == 3) {
            i2 = 90;
        } else if (i8 == 4) {
            i2 = MOCRLang.KHMER;
        } else {
            throw new RuntimeException();
        }
        boolean z = this.saveAsFlip;
        return new VexFwkBokehEffectParams(bokehEffectType, i7, i2, z ? 1 : 0, this.reLightLevel);
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

    public final VexFwkOrientation getImageOrientation() {
        return this.imageOrientation;
    }

    public final int getReLightLevel() {
        return this.reLightLevel;
    }

    public final boolean getSaveAsFlip() {
        return this.saveAsFlip;
    }

    public final void setEffectLevel(int i2) {
        this.effectLevel = i2;
    }

    public final void setEffectTypeId(BokehEffectType bokehEffectType) {
        j.e(bokehEffectType, "<set-?>");
        this.effectTypeId = bokehEffectType;
    }

    public final void setImageOrientation(VexFwkOrientation vexFwkOrientation) {
        j.e(vexFwkOrientation, "<set-?>");
        this.imageOrientation = vexFwkOrientation;
    }

    public final void setReLightLevel(int i2) {
        this.reLightLevel = i2;
    }

    public final void setSaveAsFlip(boolean z) {
        this.saveAsFlip = z;
    }

    public final void writeToParcel(Parcel parcel, int i2) {
        j.e(parcel, "dest");
        parcel.writeString(this.effectTypeId.name());
        parcel.writeInt(this.effectLevel);
        parcel.writeInt(this.reLightLevel);
        parcel.writeString(this.imageOrientation.name());
        parcel.writeInt(this.saveAsFlip ? 1 : 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VexFwkBokehEffectParamsV1(com.samsung.android.vexfwk.param.VexFwkBokehEffectParams r8) {
        /*
            r7 = this;
            java.lang.String r0 = "effectParams"
            kotlin.jvm.internal.j.e(r8, r0)
            com.samsung.android.vexfwk.Bokeheffect.BokehEffectType r2 = r8.getEffectTypeId()
            int r3 = r8.getEffectLevel()
            int r4 = r8.getReLightLevel()
            int r0 = r8.getImageOrientation()
            if (r0 == 0) goto L_0x003d
            r1 = 90
            if (r0 == r1) goto L_0x003a
            r1 = 180(0xb4, float:2.52E-43)
            if (r0 == r1) goto L_0x0037
            r1 = 270(0x10e, float:3.78E-43)
            if (r0 != r1) goto L_0x0027
            com.samsung.android.vexfwk.param.VexFwkOrientation r0 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_0
        L_0x0025:
            r5 = r0
            goto L_0x0040
        L_0x0027:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            int r8 = r8.getImageOrientation()
            java.lang.String r0 = "Not supported image orientation "
            java.lang.String r8 = c0.C0086a.i(r8, r0)
            r7.<init>(r8)
            throw r7
        L_0x0037:
            com.samsung.android.vexfwk.param.VexFwkOrientation r0 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_270
            goto L_0x0025
        L_0x003a:
            com.samsung.android.vexfwk.param.VexFwkOrientation r0 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_180
            goto L_0x0025
        L_0x003d:
            com.samsung.android.vexfwk.param.VexFwkOrientation r0 = com.samsung.android.vexfwk.param.VexFwkOrientation.ORIENTATION_ROTATE_90
            goto L_0x0025
        L_0x0040:
            int r8 = r8.getSaveAsFlip()
            r0 = 1
            if (r8 != r0) goto L_0x004a
        L_0x0047:
            r1 = r7
            r6 = r0
            goto L_0x004c
        L_0x004a:
            r0 = 0
            goto L_0x0047
        L_0x004c:
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.vexfwk.param.VexFwkBokehEffectParamsV1.<init>(com.samsung.android.vexfwk.param.VexFwkBokehEffectParams):void");
    }
}
