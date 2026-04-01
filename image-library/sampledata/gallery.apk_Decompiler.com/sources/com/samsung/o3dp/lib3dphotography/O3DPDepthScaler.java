package com.samsung.o3dp.lib3dphotography;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Point;
import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.MathUtil;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPDepthScaler {
    private static final float DEFAULT_FOCAL_LENGTH = 35.0f;
    private static final float SCALE_DEPTH_THRESHOLD = 1.0f;
    private static final String TAG = "O3DPDepthScaler";
    private final DepthMap mDepthMap;
    private final DepthMap mRawDepthMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PixelMatcher {
        public static final int RGB_BIT = 24;
        private final int[] pixels;
        private final int sourceHeight;
        private final int sourceWidth;
        private final int stride;
        private final double sx;
        private final double sy;

        public PixelMatcher(Bitmap bitmap, int i2, int i7) {
            double d;
            int width = bitmap.getWidth();
            this.sourceWidth = width;
            int height = bitmap.getHeight();
            this.sourceHeight = height;
            double d2 = MapUtil.INVALID_LOCATION;
            if (i2 == 0) {
                d = 0.0d;
            } else {
                d = ((double) (width - 1)) / ((double) (i2 - 1));
            }
            this.sx = d;
            this.sy = i7 != 0 ? ((double) (height - 1)) / ((double) (i7 - 1)) : d2;
            int[] iArr = new int[(bitmap.getHeight() * bitmap.getWidth())];
            this.pixels = iArr;
            int width2 = bitmap.getWidth();
            this.stride = width2;
            bitmap.getPixels(iArr, 0, width2, 0, 0, bitmap.getWidth(), bitmap.getHeight());
        }

        private Point getNearestPixelPoint(int i2, int i7) {
            return new Point(Math.max(Math.min((int) Math.round(((double) i2) * this.sx), this.sourceWidth - 1), 0), Math.max(Math.min((int) Math.round(((double) i7) * this.sy), this.sourceHeight - 1), 0));
        }

        public boolean isTransparent(int i2, int i7) {
            Point nearestPixelPoint = getNearestPixelPoint(i2, i7);
            if ((this.pixels[(this.stride * nearestPixelPoint.y) + nearestPixelPoint.x] >> 24) == 0) {
                return true;
            }
            return false;
        }
    }

    public O3DPDepthScaler(DepthMap depthMap) {
        this.mRawDepthMap = depthMap;
        DepthMap depthMap2 = new DepthMap();
        this.mDepthMap = depthMap2;
        depthMap2.width = depthMap.width;
        depthMap2.height = depthMap.height;
        depthMap2.data = (float[]) depthMap.data.clone();
    }

    public static Interpolator getAdaptiveDepthInterpolator(float f, float f5, float f8) {
        float f10 = f5 - f;
        return getAdaptiveDepthInterpolator(f8, f10 <= 1.0f ? 0.5f - ((1.0f - f10) * 0.5f) : 0.5f + (MathUtil.normalizedFraction(f10, 1.0f, 5.0f) * 0.5f));
    }

    public O3DPDepthScaler convertMetricDepth(Bitmap bitmap) {
        double pow = Math.pow(36.0d, 2.0d);
        double pow2 = Math.pow((double) bitmap.getWidth(), 2.0d);
        float width = ((float) bitmap.getWidth()) / ((float) ((Math.sqrt(Math.pow((double) bitmap.getHeight(), 2.0d) + pow2) * 35.0d) / ((double) ((float) Math.sqrt(Math.pow(24.0d, 2.0d) + pow)))));
        int i2 = 0;
        while (true) {
            float[] fArr = this.mRawDepthMap.data;
            if (i2 < fArr.length) {
                DepthMap depthMap = this.mDepthMap;
                float[] fArr2 = depthMap.data;
                float f = fArr[i2] * width;
                fArr2[i2] = f;
                depthMap.depthMin = Math.min(depthMap.depthMin, f);
                DepthMap depthMap2 = this.mDepthMap;
                depthMap2.depthMax = Math.max(depthMap2.depthMax, depthMap2.data[i2]);
                i2++;
            } else {
                LogUtil.d(TAG, "metricDepth Max : " + this.mDepthMap.depthMax + " , Min : " + this.mDepthMap.depthMin);
                StringBuilder sb2 = new StringBuilder("metricDepth Range : ");
                DepthMap depthMap3 = this.mDepthMap;
                sb2.append(depthMap3.depthMax - depthMap3.depthMin);
                LogUtil.d(TAG, sb2.toString());
                return this;
            }
        }
    }

    public DepthMap getDepthMap() {
        return this.mDepthMap;
    }

    public DepthMap getNormalizedDepthMap() {
        DepthMap depthMap = this.mRawDepthMap;
        float f = depthMap.depthMax - depthMap.depthMin;
        float f5 = 1.0f;
        if (f > 0.0f) {
            f5 = 1.0f / f;
        }
        DepthMap depthMap2 = new DepthMap();
        DepthMap depthMap3 = this.mRawDepthMap;
        depthMap2.width = depthMap3.width;
        depthMap2.height = depthMap3.height;
        depthMap2.data = (float[]) depthMap3.data.clone();
        int i2 = 0;
        while (true) {
            DepthMap depthMap4 = this.mRawDepthMap;
            float[] fArr = depthMap4.data;
            if (i2 >= fArr.length) {
                return depthMap2;
            }
            float[] fArr2 = depthMap2.data;
            float f8 = (fArr[i2] - depthMap4.depthMin) * f5;
            fArr2[i2] = f8;
            depthMap2.depthMin = Math.min(depthMap2.depthMin, f8);
            depthMap2.depthMax = Math.max(depthMap2.depthMax, depthMap2.data[i2]);
            i2++;
        }
    }

    public boolean scaleDepthBySegmentArea(Bitmap bitmap) {
        int i2;
        DepthMap depthMap = this.mDepthMap;
        float f = depthMap.depthMax - depthMap.depthMin;
        int i7 = 0;
        if (f <= 0.0f) {
            LogUtil.e(TAG, "abnormal depth range : " + f);
            return false;
        } else if (bitmap == null) {
            LogUtil.i(TAG, "segmentedBitmap is null");
            return false;
        } else {
            PixelMatcher pixelMatcher = new PixelMatcher(bitmap, depthMap.width, depthMap.height);
            HashMap hashMap = new HashMap(100);
            int i8 = 0;
            for (int i10 = 0; i10 < this.mDepthMap.height; i10++) {
                int i11 = 0;
                while (true) {
                    DepthMap depthMap2 = this.mDepthMap;
                    if (i11 >= depthMap2.width) {
                        break;
                    }
                    float[] fArr = depthMap2.data;
                    fArr[i8] = (fArr[i8] - depthMap2.depthMin) / f;
                    if (!pixelMatcher.isTransparent(i11, i10)) {
                        int round = Math.round(this.mDepthMap.data[i8] * 99.0f);
                        Integer num = (Integer) hashMap.get(Integer.valueOf(round));
                        Integer valueOf = Integer.valueOf(round);
                        if (num == null) {
                            i2 = 1;
                        } else {
                            i2 = num.intValue() + 1;
                        }
                        hashMap.put(valueOf, Integer.valueOf(i2));
                        DepthMap depthMap3 = this.mDepthMap;
                        depthMap3.fgDepthMin = Math.min(depthMap3.fgDepthMin, depthMap3.data[i8]);
                        DepthMap depthMap4 = this.mDepthMap;
                        depthMap4.fgDepthMax = Math.max(depthMap4.fgDepthMax, depthMap4.data[i8]);
                    }
                    i8++;
                    i11++;
                }
            }
            int i12 = 0;
            int i13 = 0;
            for (Map.Entry entry : hashMap.entrySet()) {
                if (i13 < ((Integer) entry.getValue()).intValue()) {
                    i13 = ((Integer) entry.getValue()).intValue();
                    i12 = ((Integer) entry.getKey()).intValue();
                }
            }
            DepthMap depthMap5 = this.mDepthMap;
            float f5 = ((float) i12) / 100.0f;
            depthMap5.fgDepthMean = f5;
            Interpolator adaptiveDepthInterpolator = getAdaptiveDepthInterpolator(depthMap5.depthMin, depthMap5.depthMax, f5);
            while (true) {
                float[] fArr2 = this.mDepthMap.data;
                if (i7 < fArr2.length) {
                    fArr2[i7] = adaptiveDepthInterpolator.getInterpolation(fArr2[i7]);
                    i7++;
                } else {
                    LogUtil.d(TAG, "foreground depth max : " + this.mDepthMap.fgDepthMax + ", min : " + this.mDepthMap.fgDepthMin + ", mean : " + this.mDepthMap.fgDepthMean);
                    return true;
                }
            }
        }
    }

    private static Interpolator getAdaptiveDepthInterpolator(float f, float f5) {
        float pow = (float) Math.pow(3.0d, (double) ((0.5f - f5) * 2.0f));
        float f8 = 1.0f / pow;
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        float max = Math.max(f, 1.0E-4f) * 0.33333334f;
        float f10 = max + 0.0f;
        float f11 = f - max;
        float clamp = MathUtil.clamp((pow * max) + 0.0f, 0.0f, 1.0f);
        float clamp2 = MathUtil.clamp(f - (max * f8), 0.0f, 1.0f);
        float f12 = clamp;
        float f13 = f;
        path.cubicTo(f10, f12, f11, clamp2, f13, f);
        float max2 = Math.max(1.0f - f13, 1.0E-4f) * 0.33333334f;
        path.cubicTo(f13 + max2, MathUtil.clamp((f8 * max2) + f13, 0.0f, 1.0f), 1.0f - max2, MathUtil.clamp(1.0f - (pow * max2), 0.0f, 1.0f), 1.0f, 1.0f);
        return PathInterpolatorCompat.create(path);
    }
}
