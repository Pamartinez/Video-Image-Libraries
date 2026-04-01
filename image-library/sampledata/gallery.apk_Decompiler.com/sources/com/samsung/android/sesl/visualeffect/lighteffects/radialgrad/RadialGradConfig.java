package com.samsung.android.sesl.visualeffect.lighteffects.radialgrad;

import android.graphics.Bitmap;
import android.util.Size;
import com.samsung.android.sesl.visualeffect.lighteffects.common.config.BaseVibeEffectConfig;
import i.C0212a;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\b\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0003\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0018\b\u0002\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016HÖ\u0003¢\u0006\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u0015R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010 \u001a\u0004\b!\u0010\"R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u001b\u001a\u0004\b#\u0010\u0015R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010$\u001a\u0004\b%\u0010&R'\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010'\u001a\u0004\b(\u0010)¨\u0006*"}, d2 = {"Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/RadialGradConfig;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/common/config/BaseVibeEffectConfig;", "", "backgroundColor", "Landroid/graphics/Bitmap;", "radialMap", "Landroid/util/Size;", "drawingBufferSize", "blurRadius", "", "ditherVariation", "Ljava/util/ArrayList;", "Lcom/samsung/android/sesl/visualeffect/lighteffects/radialgrad/SpotConfig;", "Lkotlin/collections/ArrayList;", "spotConfigs", "<init>", "(ILandroid/graphics/Bitmap;Landroid/util/Size;IFLjava/util/ArrayList;)V", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getBackgroundColor", "Landroid/graphics/Bitmap;", "getRadialMap", "()Landroid/graphics/Bitmap;", "Landroid/util/Size;", "getDrawingBufferSize", "()Landroid/util/Size;", "getBlurRadius", "F", "getDitherVariation", "()F", "Ljava/util/ArrayList;", "getSpotConfigs", "()Ljava/util/ArrayList;", "sesl-visualeffect-INTERNAL-2.1.6_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RadialGradConfig extends BaseVibeEffectConfig {
    private final int backgroundColor;
    private final int blurRadius;
    private final float ditherVariation;
    private final Size drawingBufferSize;
    private final Bitmap radialMap;
    private final ArrayList<SpotConfig> spotConfigs;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RadialGradConfig(int r2, android.graphics.Bitmap r3, android.util.Size r4, int r5, float r6, java.util.ArrayList r7, int r8, kotlin.jvm.internal.e r9) {
        /*
            r1 = this;
            r9 = r8 & 1
            if (r9 == 0) goto L_0x0005
            r2 = -1
        L_0x0005:
            r9 = r8 & 2
            r0 = 0
            if (r9 == 0) goto L_0x000b
            r3 = r0
        L_0x000b:
            r9 = r8 & 4
            if (r9 == 0) goto L_0x0010
            r4 = r0
        L_0x0010:
            r9 = r8 & 8
            if (r9 == 0) goto L_0x0015
            r5 = 0
        L_0x0015:
            r9 = r8 & 16
            if (r9 == 0) goto L_0x001c
            r6 = 1032805417(0x3d8f5c29, float:0.07)
        L_0x001c:
            r8 = r8 & 32
            if (r8 == 0) goto L_0x0025
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x0025:
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sesl.visualeffect.lighteffects.radialgrad.RadialGradConfig.<init>(int, android.graphics.Bitmap, android.util.Size, int, float, java.util.ArrayList, int, kotlin.jvm.internal.e):void");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RadialGradConfig)) {
            return false;
        }
        RadialGradConfig radialGradConfig = (RadialGradConfig) obj;
        if (this.backgroundColor == radialGradConfig.backgroundColor && j.a(this.radialMap, radialGradConfig.radialMap) && j.a(this.drawingBufferSize, radialGradConfig.drawingBufferSize) && this.blurRadius == radialGradConfig.blurRadius && Float.compare(this.ditherVariation, radialGradConfig.ditherVariation) == 0 && j.a(this.spotConfigs, radialGradConfig.spotConfigs)) {
            return true;
        }
        return false;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public final int getBlurRadius() {
        return this.blurRadius;
    }

    public final Size getDrawingBufferSize() {
        return this.drawingBufferSize;
    }

    public final Bitmap getRadialMap() {
        return this.radialMap;
    }

    public final ArrayList<SpotConfig> getSpotConfigs() {
        return this.spotConfigs;
    }

    public int hashCode() {
        int i2;
        int hashCode = Integer.hashCode(this.backgroundColor) * 31;
        Bitmap bitmap = this.radialMap;
        int i7 = 0;
        if (bitmap == null) {
            i2 = 0;
        } else {
            i2 = bitmap.hashCode();
        }
        int i8 = (hashCode + i2) * 31;
        Size size = this.drawingBufferSize;
        if (size != null) {
            i7 = size.hashCode();
        }
        return this.spotConfigs.hashCode() + N2.j.a(this.ditherVariation, C0212a.b(this.blurRadius, (i8 + i7) * 31, 31), 31);
    }

    public String toString() {
        int i2 = this.backgroundColor;
        Bitmap bitmap = this.radialMap;
        Size size = this.drawingBufferSize;
        int i7 = this.blurRadius;
        float f = this.ditherVariation;
        ArrayList<SpotConfig> arrayList = this.spotConfigs;
        return "RadialGradConfig(backgroundColor=" + i2 + ", radialMap=" + bitmap + ", drawingBufferSize=" + size + ", blurRadius=" + i7 + ", ditherVariation=" + f + ", spotConfigs=" + arrayList + ")";
    }

    public RadialGradConfig(int i2, Bitmap bitmap, Size size, int i7, float f, ArrayList<SpotConfig> arrayList) {
        j.e(arrayList, "spotConfigs");
        this.backgroundColor = i2;
        this.radialMap = bitmap;
        this.drawingBufferSize = size;
        this.blurRadius = i7;
        this.ditherVariation = f;
        this.spotConfigs = arrayList;
    }
}
