package com.samsung.android.gallery.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchImageView extends SquareImageView {
    private Drawable mBackground;
    private BackgroundDrawingMode mBgDrawingMode;
    private final Paint mBgPaint;
    private BitmapInfo mFromImage;
    private boolean mIsMultiMode;
    private final Paint mPaint = new Paint();
    private BitmapInfo mToImage;

    /* renamed from: com.samsung.android.gallery.widget.PinchImageView$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$widget$PinchImageView$BackgroundDrawingMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.widget.PinchImageView$BackgroundDrawingMode[] r0 = com.samsung.android.gallery.widget.PinchImageView.BackgroundDrawingMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$widget$PinchImageView$BackgroundDrawingMode = r0
                com.samsung.android.gallery.widget.PinchImageView$BackgroundDrawingMode r1 = com.samsung.android.gallery.widget.PinchImageView.BackgroundDrawingMode.ALWAYS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$PinchImageView$BackgroundDrawingMode     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.widget.PinchImageView$BackgroundDrawingMode r1 = com.samsung.android.gallery.widget.PinchImageView.BackgroundDrawingMode.FADE_IN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$widget$PinchImageView$BackgroundDrawingMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.widget.PinchImageView$BackgroundDrawingMode r1 = com.samsung.android.gallery.widget.PinchImageView.BackgroundDrawingMode.FADE_OUT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.PinchImageView.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum BackgroundDrawingMode {
        ALWAYS,
        FADE_IN,
        FADE_OUT,
        NEVER
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapInfo {
        /* access modifiers changed from: private */
        public int alpha;
        /* access modifiers changed from: private */
        public Bitmap bitmap;
        /* access modifiers changed from: private */
        public PinchMatrix fromMatrix;
        /* access modifiers changed from: private */
        public String thumbKey;
        /* access modifiers changed from: private */
        public PinchMatrix toMatrix;

        public /* synthetic */ BitmapInfo(int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public boolean isValid() {
            Bitmap bitmap2;
            PinchMatrix pinchMatrix = this.toMatrix;
            if (pinchMatrix == null || !pinchMatrix.isReady() || (bitmap2 = this.bitmap) == null || bitmap2.getWidth() == 0 || this.bitmap.getHeight() == 0 || this.bitmap.isRecycled()) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: private */
        public void setAlpha(float f) {
            this.alpha = (int) (f * 255.0f);
        }

        /* access modifiers changed from: private */
        public void setBitmap(String str, Bitmap bitmap2) {
            this.thumbKey = str;
            this.bitmap = bitmap2;
        }

        /* access modifiers changed from: private */
        public BitmapInfo setMatrix(PinchMatrix[] pinchMatrixArr) {
            this.fromMatrix = pinchMatrixArr[0];
            this.toMatrix = pinchMatrixArr[1];
            return this;
        }

        private BitmapInfo() {
            this.alpha = ScoverState.TYPE_NFC_SMART_COVER;
        }
    }

    public PinchImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mBgPaint = paint;
        paint.setColor(getDefaultBackgroundColor(context));
    }

    private void drawBackground(Canvas canvas) {
        BackgroundDrawingMode backgroundDrawingMode = this.mBgDrawingMode;
        if (backgroundDrawingMode != BackgroundDrawingMode.NEVER) {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$widget$PinchImageView$BackgroundDrawingMode[backgroundDrawingMode.ordinal()];
            int i7 = ScoverState.TYPE_NFC_SMART_COVER;
            if (i2 == 1) {
                this.mBgPaint.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
            } else if (i2 == 2) {
                Paint paint = this.mBgPaint;
                if (isValid(this.mToImage)) {
                    i7 = this.mToImage.alpha;
                }
                paint.setAlpha(i7);
            } else if (i2 == 3) {
                Paint paint2 = this.mBgPaint;
                if (isValid(this.mFromImage)) {
                    i7 = this.mFromImage.alpha;
                }
                paint2.setAlpha(i7);
            }
            canvas.drawPaint(this.mBgPaint);
        }
    }

    private boolean drawMultiImage(Canvas canvas) {
        if (!isValid(this.mFromImage) && !isValid(this.mToImage)) {
            return false;
        }
        int saveCount = canvas.getSaveCount();
        canvas.save();
        drawBackground(canvas);
        canvas.translate((float) getPaddingLeft(), (float) getPaddingTop());
        if (hasSameBitmap()) {
            this.mPaint.setAlpha(ScoverState.TYPE_NFC_SMART_COVER);
            canvas.drawBitmap(this.mToImage.bitmap, this.mToImage.toMatrix, this.mPaint);
        } else {
            if (isValid(this.mFromImage)) {
                this.mPaint.setAlpha(this.mFromImage.alpha);
                canvas.drawBitmap(this.mFromImage.bitmap, this.mFromImage.toMatrix, this.mPaint);
            }
            if (isValid(this.mToImage)) {
                this.mPaint.setAlpha(this.mToImage.alpha);
                canvas.drawBitmap(this.mToImage.bitmap, this.mToImage.toMatrix, this.mPaint);
            }
        }
        canvas.restoreToCount(saveCount);
        return true;
    }

    private int getDefaultBackgroundColor(Context context) {
        try {
            int textColorFromTextColorAttr = Utils.getTextColorFromTextColorAttr(context, R$attr.thumbnailBackgroundColor);
            if (textColorFromTextColorAttr != 0) {
                return textColorFromTextColorAttr;
            }
            return context.getColor(R$color.thumbnail_icon_background);
        } catch (Resources.NotFoundException unused) {
            return context.getColor(R$color.thumbnail_icon_background);
        }
    }

    private boolean hasSameBitmap() {
        if (!isValid(this.mFromImage) || !isValid(this.mToImage) || !TextUtils.equals(this.mFromImage.thumbKey, this.mToImage.thumbKey)) {
            return false;
        }
        return true;
    }

    private boolean isValid(BitmapInfo bitmapInfo) {
        if (bitmapInfo == null || !bitmapInfo.isValid()) {
            return false;
        }
        return true;
    }

    private void updateBackground() {
        Drawable drawable;
        if (this.mIsMultiMode && getBackground() != null) {
            this.mBackground = getBackground();
            setBackground((Drawable) null);
        } else if (!this.mIsMultiMode && (drawable = this.mBackground) != null) {
            setBackground(drawable);
            this.mBackground = null;
        }
    }

    public void draw(Canvas canvas) {
        updateBackground();
        super.draw(canvas);
    }

    public void enableDrawBackground(BackgroundDrawingMode backgroundDrawingMode) {
        this.mBgDrawingMode = backgroundDrawingMode;
    }

    public PinchMatrix[] getFromImageMatrix() {
        BitmapInfo bitmapInfo = this.mFromImage;
        if (bitmapInfo == null) {
            return null;
        }
        return new PinchMatrix[]{bitmapInfo.fromMatrix, this.mFromImage.toMatrix};
    }

    public PinchMatrix[] getToImageMatrix() {
        BitmapInfo bitmapInfo = this.mToImage;
        if (bitmapInfo == null) {
            return null;
        }
        return new PinchMatrix[]{bitmapInfo.fromMatrix, this.mToImage.toMatrix};
    }

    public void onDraw(Canvas canvas) {
        if (!this.mIsMultiMode || !drawMultiImage(canvas)) {
            super.onDraw(canvas);
        }
    }

    public void onDrawForeground(Canvas canvas) {
        if (!this.mIsMultiMode) {
            super.onDrawForeground(canvas);
        }
    }

    public void setFromBitmap(String str, Bitmap bitmap) {
        BitmapInfo bitmapInfo = this.mFromImage;
        if (bitmapInfo != null) {
            bitmapInfo.setBitmap(str, bitmap);
        }
    }

    public void setFromImageMatrix(PinchMatrix[] pinchMatrixArr) {
        this.mFromImage = new BitmapInfo(0).setMatrix(pinchMatrixArr);
    }

    public void setMultiMode(boolean z) {
        this.mIsMultiMode = z;
        this.mFromImage = null;
        this.mToImage = null;
    }

    public void setToBitmap(String str, Bitmap bitmap) {
        BitmapInfo bitmapInfo = this.mToImage;
        if (bitmapInfo != null) {
            bitmapInfo.setBitmap(str, bitmap);
        }
    }

    public void setToImageMatrix(PinchMatrix[] pinchMatrixArr) {
        this.mToImage = new BitmapInfo(0).setMatrix(pinchMatrixArr);
    }

    public void updateAlpha(float f, float f5) {
        BitmapInfo bitmapInfo = this.mFromImage;
        if (bitmapInfo != null) {
            bitmapInfo.setAlpha(f);
        }
        BitmapInfo bitmapInfo2 = this.mToImage;
        if (bitmapInfo2 != null) {
            bitmapInfo2.setAlpha(f5);
        }
    }
}
