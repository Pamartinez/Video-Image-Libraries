package com.samsung.android.gallery.support.blur;

import B3.c;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.BlendMode;
import android.graphics.RenderEffect;
import android.graphics.RuntimeShader;
import android.graphics.Shader;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryGradientBlur implements GradientBlurCompatible {
    private static final Bitmap.Config CURVE_CONFIG = Bitmap.Config.ARGB_8888;
    private final int[] bezierBuffer = new int[256];
    private final BitmapCache bitmapCache = new BitmapCache(0);
    private float curveLevel;
    private int endColor = 65535;
    private float maxX;
    private float maxY;
    private float minX;
    private float minY;
    private int startColor = -16776961;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapCache {
        private final LinkedHashMap<IntKey, BitmapEntry> cache;

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class IntKey {

            /* renamed from: i1  reason: collision with root package name */
            final int f3127i1;
            final int i2;

            /* renamed from: i3  reason: collision with root package name */
            final int f3128i3;

            /* renamed from: i4  reason: collision with root package name */
            final int f3129i4;

            /* renamed from: i5  reason: collision with root package name */
            final int f3130i5;

            public IntKey(float f, float f5, float f8, float f10, float f11) {
                this.f3127i1 = (int) (f * 100.0f);
                this.i2 = (int) (f5 * 100.0f);
                this.f3128i3 = (int) (f8 * 100.0f);
                this.f3129i4 = (int) (f10 * 100.0f);
                this.f3130i5 = (int) (f11 * 100.0f);
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof IntKey)) {
                    return false;
                }
                IntKey intKey = (IntKey) obj;
                if (this.f3127i1 == intKey.f3127i1 && this.i2 == intKey.i2 && this.f3128i3 == intKey.f3128i3 && this.f3129i4 == intKey.f3129i4 && this.f3130i5 == intKey.f3130i5) {
                    return true;
                }
                return false;
            }

            public int hashCode() {
                return (((((((this.f3127i1 * 31) + this.i2) * 31) + this.f3128i3) * 31) + this.f3129i4) * 31) + this.f3130i5;
            }
        }

        public /* synthetic */ BitmapCache(int i2) {
            this();
        }

        public boolean contains(float f, float f5, float f8, float f10, float f11) {
            return this.cache.containsKey(new IntKey(f, f5, f8, f10, f11));
        }

        public BitmapEntry get(float f, float f5, float f8, float f10, float f11) {
            return this.cache.get(new IntKey(f, f5, f8, f10, f11));
        }

        public void put(float f, float f5, float f8, float f10, float f11, Bitmap bitmap) {
            IntKey intKey = new IntKey(f, f5, f8, f10, f11);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.cache.put(intKey, new BitmapEntry(intKey, bitmap, new BitmapShader(bitmap, tileMode, tileMode)));
        }

        private BitmapCache() {
            this.cache = new LinkedHashMap<IntKey, BitmapEntry>(30, 0.75f, true) {
                public boolean removeEldestEntry(Map.Entry<IntKey, BitmapEntry> entry) {
                    if (size() <= 30) {
                        return false;
                    }
                    entry.getValue().bitmap.recycle();
                    return true;
                }
            };
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapEntry {
        final Bitmap bitmap;
        final BitmapCache.IntKey key;
        final BitmapShader shader;

        public BitmapEntry(BitmapCache.IntKey intKey, Bitmap bitmap2, BitmapShader bitmapShader) {
            this.key = intKey;
            this.bitmap = bitmap2;
            this.shader = bitmapShader;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Point {

        /* renamed from: x  reason: collision with root package name */
        float f3131x;
        float y;

        public Point(float f, float f5) {
            this.f3131x = f;
            this.y = f5;
        }
    }

    private float clamp(float f, float f5, float f8) {
        return Math.max(f5, Math.min(f8, f));
    }

    private RenderEffect createColorGradientEffect(BlurParams blurParams) {
        float f;
        float f5;
        RuntimeShader k = c.k();
        k.setIntUniform("viewWidth", blurParams.getWidth());
        k.setIntUniform("viewHeight", blurParams.getHeight());
        k.setFloatUniform("uProSaturation", ((float) Math.round(((((float) Math.pow((double) Math.max(blurParams.getSaturation(), 0.0f), 2.0d)) * 4.0f) + (Math.min(blurParams.getSaturation(), 0.0f) + 1.0f)) * 100.0f)) / 100.0f);
        if (blurParams.isDither()) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        k.setFloatUniform("enable", f);
        this.curveLevel = blurParams.getCurveLevel();
        this.minX = blurParams.getCurveMinX();
        this.minY = blurParams.getCurveMinY();
        this.maxX = blurParams.getCurveMaxX();
        float curveMaxY = blurParams.getCurveMaxY();
        this.maxY = curveMaxY;
        boolean z = true;
        if (!this.bitmapCache.contains(this.minX, this.minY, this.maxX, curveMaxY, this.curveLevel)) {
            Bitmap.Config config = CURVE_CONFIG;
            Bitmap createCurveBitmap = createCurveBitmap(256, 1, config);
            if (isValidBitmap(createCurveBitmap, 256, 1, config)) {
                this.bitmapCache.put(this.minX, this.minY, this.maxX, this.maxY, this.curveLevel, createCurveBitmap);
            } else if (createCurveBitmap != null) {
                createCurveBitmap.recycle();
            }
        }
        BitmapEntry bitmapEntry = this.bitmapCache.get(this.minX, this.minY, this.maxX, this.maxY, this.curveLevel);
        if (bitmapEntry == null || !isValidBitmap(bitmapEntry.bitmap, 256, 1, CURVE_CONFIG)) {
            Log.w("GalleryGradientBlur", "mapping shader is null");
            z = false;
        } else {
            k.setInputShader("curveTexture", bitmapEntry.shader);
        }
        if (z) {
            f5 = 1.0f;
        } else {
            f5 = 0.0f;
        }
        k.setFloatUniform("isValid", f5);
        float clamp = clamp(((float) ((this.startColor >> 24) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp2 = clamp(((float) ((this.startColor >> 16) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp3 = clamp(((float) ((this.startColor >> 8) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp4 = clamp(((float) (this.startColor & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp5 = clamp(((float) ((this.endColor >> 24) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp6 = clamp(((float) ((this.endColor >> 16) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp7 = clamp(((float) ((this.endColor >> 8) & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        float clamp8 = clamp(((float) (this.endColor & ScoverState.TYPE_NFC_SMART_COVER)) / 255.0f, 0.0f, 1.0f);
        k.setFloatUniform("uPercent", 0.0f);
        k.setFloatUniform("uPivotPercent", 0.0f);
        k.setFloatUniform("uDirection", 0.0f, 1.0f);
        k.setFloatUniform("uP1", 0.42f);
        k.setFloatUniform("uP2", 0.58f);
        k.setFloatUniform("uBezierStart", 0.0f);
        k.setFloatUniform("uBezierEnd", 0.0f);
        k.setFloatUniform("uBezierAlphaStart", 0.0f);
        k.setFloatUniform("uBezierAlphaEnd", 0.0f);
        k.setFloatUniform("uColor0", clamp, clamp2, clamp3, clamp4);
        k.setFloatUniform("uColor1", clamp5, clamp6, clamp7, clamp8);
        return RenderEffect.createRuntimeShaderEffect(k, "viewImage");
    }

    private Bitmap createCurveBitmap(int i2, int i7, Bitmap.Config config) {
        setBezierBuffer();
        return Bitmap.createBitmap(this.bezierBuffer, i2, i7, config).copy(config, true);
    }

    private static RenderEffect createGradientBlurEffect(BlurParams blurParams) {
        RuntimeShader b = c.b();
        float gradientBlurPercent = blurParams.getGradientBlurPercent() * blurParams.getGradientBlurPivotPercent();
        b.setIntUniform("viewWidth", blurParams.getWidth());
        b.setIntUniform("viewHeight", blurParams.getHeight());
        b.setFloatUniform("uColor0", 0.0f, 0.0f, 1.0f, 1.0f);
        b.setFloatUniform("uColor1", 1.0f, 0.0f, 0.0f, 0.0f);
        b.setFloatUniform("uDirection", blurParams.getGradientBlurDirection());
        b.setFloatUniform("uBezierStart", gradientBlurPercent);
        b.setFloatUniform("uBezierEnd", blurParams.getGradientBlurPercent());
        b.setFloatUniform("uControlX1", 0.42f);
        b.setFloatUniform("uControlY1", 0.0f);
        b.setFloatUniform("uControlX2", 0.58f);
        b.setFloatUniform("uControlY2", 1.0f);
        return RenderEffect.createRuntimeShaderEffect(b, "viewImage");
    }

    private int findLowerIndex(ArrayList<Point> arrayList, float f) {
        int size = arrayList.size() - 2;
        int i2 = 0;
        if (f <= arrayList.get(0).f3131x) {
            return 0;
        }
        while (i2 <= size) {
            int i7 = (i2 + size) >>> 1;
            float f5 = arrayList.get(i7).f3131x;
            int i8 = i7 + 1;
            float f8 = arrayList.get(i8).f3131x;
            if (f5 <= f && f < f8) {
                return i7;
            }
            if (f < f5) {
                size = i7 - 1;
            } else {
                i2 = i8;
            }
        }
        return arrayList.size() - 2;
    }

    private Map.Entry<float[], Integer> getControlPoints() {
        float f;
        float f5;
        int i2;
        if (this.minX > this.maxX || this.minY > this.maxY) {
            Log.w("GalleryGradientBlur", "Must minX < maxX, minY < maxY " + this.minX + "<" + this.maxX + ArcCommonLog.TAG_COMMA + this.minY + "<" + this.maxY);
        }
        float f8 = this.maxX - this.minX;
        float f10 = f8 / 120.0f;
        float min = Math.min(f8, this.maxY - this.minY);
        if (min < 30.0f) {
            f = min / 10.0f;
        } else {
            f = (min / 30.0f) + 2.0f;
        }
        float max = Math.max(f - 1.0f, 0.0f);
        float f11 = this.curveLevel;
        if (f11 > 0.0f) {
            f5 = f11;
        } else {
            f5 = -f11;
        }
        float f12 = (((max * f5) * 2.0f) / 120.0f) + 1.0f;
        float[] fArr = new float[12];
        float f13 = this.minX;
        fArr[0] = f13;
        float f14 = this.minY;
        fArr[1] = f14;
        if (f11 == 0.0f) {
            fArr[2] = this.maxX;
            fArr[3] = this.maxY;
            i2 = 2;
        } else {
            float f15 = this.maxX;
            fArr[10] = f15;
            float f16 = this.maxY;
            fArr[11] = f16;
            if (f11 > 0.0f) {
                float f17 = f15 - (f10 * f11);
                fArr[8] = f17;
                fArr[9] = f16;
                float f18 = (f17 - ((f17 - f13) / 3.0f)) - f12;
                fArr[4] = f18;
                fArr[5] = (f16 - ((f16 - f14) / 3.0f)) + f12;
                float f19 = (min / 255.0f) * f12;
                fArr[6] = (Math.min((-(f11 - 70.0f)) / 25.0f, f11 / 45.0f) * 2.0f * f19) + (((f17 - f18) * f11) / 120.0f) + f18;
                float f20 = fArr[5];
                float f21 = this.curveLevel;
                fArr[7] = (((((fArr[9] - f20) * f21) * 1.25f) / 120.0f) + f20) - (Math.max((100.0f / f21) * -1.0f, Math.min((-(f21 - 70.0f)) / 15.0f, f21 / 55.0f)) * f19);
                float f22 = fArr[0];
                fArr[2] = ((((fArr[8] - f22) / 3.0f) + f22) - f12) - (Math.max(-1.2f, Math.min((-(this.curveLevel - 70.0f)) / 20.0f, 1.0f)) * (min / 50.0f));
                float f23 = fArr[1];
                fArr[3] = (f12 / ((this.curveLevel + 100.0f) / 64.0f)) + ((fArr[9] - f23) / 3.0f) + f23;
            } else {
                float f24 = f13 - (f10 * f11);
                fArr[2] = f24;
                fArr[3] = f14;
                float f25 = ((f15 - f24) / 3.0f) + f24 + f12;
                fArr[6] = f25;
                fArr[7] = (((f16 - f14) / 3.0f) + f14) - f12;
                float f26 = (min / 255.0f) * f12;
                fArr[4] = ((((f25 - f24) * f11) / 120.0f) + f25) - (Math.min((-((-f11) - 70.0f)) / 25.0f, (-f11) / 45.0f) * (2.0f * f26));
                float f27 = fArr[7];
                float f28 = this.curveLevel;
                fArr[5] = (Math.max((100.0f / (-f28)) * -1.0f, Math.min((-((-f28) - 70.0f)) / 15.0f, (-f28) / 55.0f)) * f26) + ((((f27 - fArr[3]) * f28) * 1.25f) / 120.0f) + f27;
                float f29 = fArr[10];
                fArr[8] = (Math.max(-1.2f, Math.min((-((-this.curveLevel) - 70.0f)) / 20.0f, 1.0f)) * (min / 50.0f)) + (f29 - ((f29 - fArr[2]) / 3.0f)) + f12;
                float f30 = fArr[11];
                fArr[9] = (f30 - ((f30 - fArr[3]) / 3.0f)) - (f12 / (((-this.curveLevel) + 100.0f) / 64.0f));
            }
            i2 = 6;
        }
        float f31 = fArr[0];
        for (int i7 = 1; i7 < i2; i7++) {
            int i8 = i7 * 2;
            if (f31 > fArr[i8]) {
                f31 += 0.001f;
                fArr[i8] = f31;
            }
        }
        return Map.entry(fArr, Integer.valueOf(i2));
    }

    private boolean isValidBitmap(Bitmap bitmap, int i2, int i7, Bitmap.Config config) {
        if (bitmap != null && !bitmap.isRecycled() && bitmap.getWidth() == i2 && bitmap.getHeight() == i7 && bitmap.getConfig() == config) {
            return true;
        }
        return false;
    }

    private void setBezierBuffer() {
        float f;
        int length = this.bezierBuffer.length;
        float f5 = (float) length;
        float f8 = 1.0f / f5;
        Map.Entry<float[], Integer> controlPoints = getControlPoints();
        float[] key = controlPoints.getKey();
        Integer value = controlPoints.getValue();
        ArrayList arrayList = new ArrayList();
        float[] fArr = new float[key.length];
        for (float f10 = 0.0f; ((double) f10) < 1.0d; f10 += f8) {
            for (int i2 = 0; i2 < value.intValue() * 2; i2++) {
                fArr[i2] = key[i2];
            }
            for (int intValue = value.intValue() - 1; intValue > 0; intValue--) {
                for (int i7 = 0; i7 < intValue; i7++) {
                    int i8 = i7 * 2;
                    float f11 = fArr[i8];
                    fArr[i8] = C0212a.a(fArr[i8 + 2], f11, f10, f11);
                    int i10 = i8 + 1;
                    float f12 = fArr[i10];
                    fArr[i10] = C0212a.a(fArr[i8 + 3], f12, f10, f12);
                }
            }
            arrayList.add(new Point(fArr[0] / f5, fArr[1] / f5));
        }
        ((Point) C0212a.i(arrayList, 1)).f3131x = 1.0f;
        float f13 = 0.0f;
        ((Point) arrayList.get(0)).f3131x = 0.0f;
        ((Point) arrayList.get(0)).y = ((Point) arrayList.get(1)).y;
        int i11 = 0;
        while (i11 < length) {
            float f14 = ((float) i11) / f5;
            if (f14 == f13) {
                f14 += 0.001f;
            }
            int findLowerIndex = findLowerIndex(arrayList, f14);
            Point point = (Point) arrayList.get(findLowerIndex);
            Point point2 = (Point) arrayList.get(findLowerIndex + 1);
            float f15 = point2.f3131x;
            float f16 = point.f3131x;
            float f17 = f15 - f16;
            if (f17 > 0.0f) {
                f = (f14 - f16) / f17;
            } else {
                f = 0.0f;
            }
            float f18 = point.y;
            int max = Math.max(0, Math.min(ScoverState.TYPE_NFC_SMART_COVER, Math.round((((point2.y - f18) * f) + f18) * 255.0f)));
            int[] iArr = this.bezierBuffer;
            int i12 = max & ScoverState.TYPE_NFC_SMART_COVER;
            iArr[i11] = i12 | (i12 << 16) | -16777216 | (i12 << 8);
            i11++;
            f13 = 0.0f;
        }
    }

    public RenderEffect getRenderEffect(BlurParams blurParams) {
        RenderEffect createColorGradientEffect = createColorGradientEffect(blurParams);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        RenderEffect d = RenderEffect.createChainEffect(RenderEffect.createBlurEffect(blurParams.getRadius() / 2.25f, blurParams.getRadius() / 2.25f, Shader.TileMode.CLAMP), createColorGradientEffect);
        if (blurParams.hasGradientBlur()) {
            d = RenderEffect.createChainEffect(createGradientBlurEffect(blurParams), d);
        }
        RenderEffect b = RenderEffect.createOffsetEffect(0.0f, 0.0f);
        BlendMode blendMode = BlendMode.SRC_OVER;
        return RenderEffect.createBlendModeEffect(b, d, BlendMode.SRC_OVER);
    }
}
