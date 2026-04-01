package com.samsung.android.gallery.support.utils;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Size;
import android.widget.ImageView;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ImageMatrix {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MatrixParam {
        /* access modifiers changed from: private */
        public Rect cropRect;
        /* access modifiers changed from: private */
        public int[] extraArea;
        /* access modifiers changed from: private */
        public final boolean isStretchable;
        /* access modifiers changed from: private */
        public int orientation;
        /* access modifiers changed from: private */
        public int orientationTag;
        /* access modifiers changed from: private */
        public final ImageView tgtView;
        /* access modifiers changed from: private */
        public Size viewSize;

        public /* synthetic */ MatrixParam(ImageView imageView) {
            this(imageView, true);
        }

        public static MatrixParam create(ImageView imageView, boolean z) {
            return new MatrixParam(imageView, z);
        }

        public MatrixParam withCropRect(Rect rect) {
            this.cropRect = rect;
            return this;
        }

        public MatrixParam withExtraArea(int[] iArr) {
            this.extraArea = iArr;
            return this;
        }

        public MatrixParam withOrientation(int i2) {
            this.orientation = i2;
            return this;
        }

        public MatrixParam withOrientationTag(int i2) {
            this.orientationTag = i2;
            return this;
        }

        public MatrixParam withViewSize(Size size) {
            this.viewSize = size;
            return this;
        }

        private MatrixParam(ImageView imageView, boolean z) {
            this.cropRect = null;
            this.orientation = 0;
            this.orientationTag = 0;
            this.extraArea = new int[]{0, 0};
            this.tgtView = imageView;
            this.isStretchable = z;
            this.viewSize = new Size(imageView.getWidth(), imageView.getHeight());
        }
    }

    private static float checkRange(float f, float f5, float f8) {
        return Math.max(Math.min(f, f5), f8);
    }

    public static Matrix create(MatrixParam matrixParam) {
        if (matrixParam.cropRect == null) {
            return getCenterCropMatrix(matrixParam);
        }
        return getCropMatrix(matrixParam);
    }

    public static Matrix createFitCenter(MatrixParam matrixParam) {
        Rect rect;
        float f;
        int i2;
        int i7;
        float f5;
        float f8;
        Drawable drawable = matrixParam.tgtView.getDrawable();
        if (matrixParam.tgtView.getWidth() == 0 || matrixParam.tgtView.getHeight() == 0 || drawable == null) {
            return matrixParam.tgtView.getImageMatrix();
        }
        float width = (float) (matrixParam.tgtView.getWidth() + matrixParam.extraArea[0]);
        float height = (float) (matrixParam.tgtView.getHeight() + matrixParam.extraArea[1]);
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            f = Math.min(width / ((float) drawable.getIntrinsicWidth()), height / ((float) drawable.getIntrinsicHeight()));
            rect = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            f = Math.min(width / ((float) drawable.getIntrinsicHeight()), height / ((float) drawable.getIntrinsicWidth()));
            rect = new Rect(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth());
        }
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            i2 = drawable.getIntrinsicWidth();
        } else {
            i2 = drawable.getIntrinsicHeight();
        }
        float f10 = ((float) i2) * f;
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            i7 = drawable.getIntrinsicHeight();
        } else {
            i7 = drawable.getIntrinsicWidth();
        }
        float f11 = ((float) i7) * f;
        float centerX = ((float) rect.centerX()) * f;
        float centerY = ((float) rect.centerY()) * f;
        if (matrixParam.orientation == 90 || matrixParam.orientation == 180) {
            f5 = (width / 2.0f) + f10;
        } else {
            f5 = width / 2.0f;
        }
        float f12 = f5 - centerX;
        if (matrixParam.orientation == 0 || matrixParam.orientation == 90) {
            f8 = height / 2.0f;
        } else {
            f8 = (height / 2.0f) + f11;
        }
        return updateMatrix(matrixParam, f, f12, f8 - centerY);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0066, code lost:
        if (r8 > 0.0f) goto L_0x0099;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
        if (r8 > 0.0f) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0088, code lost:
        if (r8 > 0.0f) goto L_0x007a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0097, code lost:
        if (r8 > 0.0f) goto L_0x0099;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Matrix createScaledImageMatrix(com.samsung.android.gallery.support.utils.ImageMatrix.MatrixParam r6, float r7, android.graphics.PointF r8) {
        /*
            android.widget.ImageView r0 = r6.tgtView
            int r0 = r0.getWidth()
            float r0 = (float) r0
            android.widget.ImageView r1 = r6.tgtView
            int r1 = r1.getHeight()
            float r1 = (float) r1
            android.widget.ImageView r2 = r6.tgtView
            android.graphics.drawable.Drawable r2 = r2.getDrawable()
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x009e
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 == 0) goto L_0x009e
            if (r2 != 0) goto L_0x0027
            goto L_0x009e
        L_0x0027:
            int r0 = r6.orientation
            r1 = 180(0xb4, float:2.52E-43)
            int r0 = r0 % r1
            if (r0 != 0) goto L_0x0035
            int r0 = r2.getIntrinsicWidth()
            goto L_0x0039
        L_0x0035:
            int r0 = r2.getIntrinsicHeight()
        L_0x0039:
            float r0 = (float) r0
            float r0 = r0 * r7
            int r4 = r6.orientation
            int r4 = r4 % r1
            if (r4 != 0) goto L_0x0047
            int r2 = r2.getIntrinsicHeight()
            goto L_0x004b
        L_0x0047:
            int r2 = r2.getIntrinsicWidth()
        L_0x004b:
            float r2 = (float) r2
            float r2 = r2 * r7
            int r4 = r6.orientation
            r5 = 90
            if (r4 == r5) goto L_0x008b
            if (r4 == r1) goto L_0x007c
            r0 = 270(0x10e, float:3.78E-43)
            if (r4 == r0) goto L_0x006b
            float r0 = r8.x
            int r1 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0062
            r0 = r3
        L_0x0062:
            float r8 = r8.y
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0069
            goto L_0x0099
        L_0x0069:
            r3 = r8
            goto L_0x0099
        L_0x006b:
            float r0 = r8.x
            int r1 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0072
            r0 = r3
        L_0x0072:
            float r8 = r8.y
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r3 = r8
        L_0x007a:
            float r3 = r3 + r2
            goto L_0x0099
        L_0x007c:
            float r1 = r8.x
            int r4 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r4 <= 0) goto L_0x0083
            r1 = r3
        L_0x0083:
            float r0 = r0 + r1
            float r8 = r8.y
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0079
            goto L_0x007a
        L_0x008b:
            float r1 = r8.x
            int r2 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0092
            r1 = r3
        L_0x0092:
            float r0 = r0 + r1
            float r8 = r8.y
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x0069
        L_0x0099:
            android.graphics.Matrix r6 = updateMatrix(r6, r7, r0, r3)
            return r6
        L_0x009e:
            android.widget.ImageView r6 = r6.tgtView
            android.graphics.Matrix r6 = r6.getImageMatrix()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.ImageMatrix.createScaledImageMatrix(com.samsung.android.gallery.support.utils.ImageMatrix$MatrixParam, float, android.graphics.PointF):android.graphics.Matrix");
    }

    private static Matrix getCenterCropMatrix(MatrixParam matrixParam) {
        Rect rect;
        Drawable drawable = matrixParam.tgtView.getDrawable();
        if (drawable == null) {
            return matrixParam.tgtView.getMatrix();
        }
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            rect = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            rect = new Rect(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth());
        }
        return getCropMatrix(new MatrixParam(matrixParam.tgtView).withCropRect(rect).withOrientation(matrixParam.orientation).withOrientationTag(matrixParam.orientationTag));
    }

    private static Matrix getCropMatrix(MatrixParam matrixParam) {
        float f;
        int i2;
        int i7;
        float f5;
        float f8;
        ImageView f10 = matrixParam.tgtView;
        Drawable drawable = f10.getDrawable();
        float width = (float) matrixParam.viewSize.getWidth();
        float height = (float) matrixParam.viewSize.getHeight();
        if (height == 0.0f || width == 0.0f || drawable == null) {
            return f10.getImageMatrix();
        }
        if (matrixParam.isStretchable) {
            f = getOriginScale(f10, matrixParam.orientation, f10.getWidth(), f10.getHeight());
        } else {
            f = Math.max(height / ((float) matrixParam.cropRect.height()), width / ((float) matrixParam.cropRect.width()));
        }
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            i2 = drawable.getIntrinsicWidth();
        } else {
            i2 = drawable.getIntrinsicHeight();
        }
        float f11 = ((float) i2) * f;
        if (matrixParam.orientation % MOCRLang.KHMER == 0) {
            i7 = drawable.getIntrinsicHeight();
        } else {
            i7 = drawable.getIntrinsicWidth();
        }
        float f12 = ((float) i7) * f;
        float centerX = ((float) matrixParam.cropRect.centerX()) * f;
        float centerY = ((float) matrixParam.cropRect.centerY()) * f;
        if (matrixParam.orientation == 90 || matrixParam.orientation == 180) {
            f5 = ((width / 2.0f) + f11) - centerX;
        } else {
            f5 = (width / 2.0f) - centerX;
            width = -(f11 - width);
            f11 = 0.0f;
        }
        if (matrixParam.orientation == 0 || matrixParam.orientation == 90) {
            height = -(f12 - height);
            f12 = 0.0f;
            f8 = (height / 2.0f) - centerY;
        } else {
            f8 = ((height / 2.0f) + f12) - centerY;
        }
        return updateMatrix(matrixParam, f, checkRange(f5, f11, width), checkRange(f8, f12, height));
    }

    public static Matrix getMatrix(ImageView imageView, int i2, int i7, int i8) {
        Rect rect;
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            return imageView.getMatrix();
        }
        if (i2 % MOCRLang.KHMER == 0) {
            rect = new Rect(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        } else {
            rect = new Rect(0, 0, drawable.getIntrinsicHeight(), drawable.getIntrinsicWidth());
        }
        return getCropMatrix(MatrixParam.create(imageView, true).withCropRect(rect).withOrientation(i2).withViewSize(new Size(i7, i8)));
    }

    private static float getOriginScale(ImageView imageView, int i2, int i7, int i8) {
        Drawable drawable = imageView.getDrawable();
        if (i2 % MOCRLang.KHMER == 0) {
            return Math.max(((float) i7) / ((float) drawable.getIntrinsicWidth()), ((float) i8) / ((float) drawable.getIntrinsicHeight()));
        }
        return Math.max(((float) i7) / ((float) drawable.getIntrinsicHeight()), ((float) i8) / ((float) drawable.getIntrinsicWidth()));
    }

    private static Matrix updateMatrix(MatrixParam matrixParam, float f, float f5, float f8) {
        Matrix imageMatrix = matrixParam.tgtView.getImageMatrix();
        imageMatrix.setScale(f, f);
        imageMatrix.postRotate((float) matrixParam.orientation);
        imageMatrix.postTranslate(f5, f8);
        if (ExifUtils.isHorizontalMirror(matrixParam.orientationTag)) {
            imageMatrix.postScale(-1.0f, 1.0f);
            imageMatrix.postTranslate((float) matrixParam.tgtView.getWidth(), 0.0f);
        }
        return imageMatrix;
    }
}
