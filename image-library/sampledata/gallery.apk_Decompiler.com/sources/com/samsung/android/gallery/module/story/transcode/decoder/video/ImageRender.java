package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;
import com.samsung.android.gallery.support.utils.RectUtils;
import ic.l;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageRender {
    protected Bitmap mBitmap;
    private int mBitmapHeight;
    protected Consumer<Bitmap> mBitmapRecycler = new l(16);
    private int mBitmapWidth;
    protected Rect mImageCropRect;
    protected float mMvpHeightRatio = 1.0f;
    protected float mMvpWidthRatio = 1.0f;
    private final int mOrientation;
    private final int mOutputHeight;
    private final int mOutputWidth;
    protected float[] mSizeRatio;
    protected GLRenderTexture mTextureRender;
    protected float[] mTranslateXY;

    public ImageRender(int i2, int i7, int i8) {
        this.mOutputWidth = i2;
        this.mOutputHeight = i7;
        this.mOrientation = i8;
    }

    private float[] getMvpRatio(Rect rect, int i2, int i7) {
        float f;
        float width = ((float) rect.width()) / ((float) i2);
        float height = ((float) rect.height()) / ((float) i7);
        float f5 = 1.0f;
        if (width > height) {
            f = height / width;
        } else {
            float f8 = width / height;
            f = 1.0f;
            f5 = f8;
        }
        return new float[]{f5, f};
    }

    private float[] getSizeRatio() {
        Rect rect = this.mImageCropRect;
        if (rect == null) {
            return new float[]{1.0f, 1.0f};
        }
        return new float[]{((float) rect.width()) / ((float) this.mBitmapWidth), ((float) this.mImageCropRect.height()) / ((float) this.mBitmapHeight)};
    }

    private float getTransformTranslate(float[] fArr, int i2) {
        return (this.mSizeRatio[i2] * fArr[i2]) + this.mTranslateXY[i2];
    }

    private float[] getTranslateDelta(FrameProperty frameProperty) {
        float[] fArr = new float[2];
        int i2 = this.mOrientation;
        if (i2 == 90) {
            fArr[0] = frameProperty.getTransY();
            fArr[1] = -frameProperty.getTransX();
            return fArr;
        } else if (i2 == 180) {
            fArr[0] = -frameProperty.getTransX();
            fArr[1] = -frameProperty.getTransY();
            return fArr;
        } else if (i2 != 270) {
            fArr[0] = frameProperty.getTransX();
            fArr[1] = frameProperty.getTransY();
            return fArr;
        } else {
            fArr[0] = frameProperty.getTransY();
            fArr[1] = frameProperty.getTransX();
            return fArr;
        }
    }

    private float[] getTranslateXY() {
        if (!RectUtils.isValidRect(this.mImageCropRect)) {
            return new float[2];
        }
        Rect rect = this.mImageCropRect;
        return new float[]{((float) rect.left) / ((float) this.mBitmapWidth), ((float) rect.top) / ((float) this.mBitmapHeight)};
    }

    private void initSurfaceTexture() {
        this.mTextureRender.scaleMVPMatrix(this.mMvpWidthRatio, this.mMvpHeightRatio);
        float[] translateXY = getTranslateXY();
        this.mTranslateXY = translateXY;
        this.mTextureRender.translateSTMatrix(translateXY[0], translateXY[1]);
        float[] sizeRatio = getSizeRatio();
        this.mSizeRatio = sizeRatio;
        this.mTextureRender.scaleSTMatrix(sizeRatio[0], sizeRatio[1], 1.0f);
        this.mTextureRender.rotateMVPMatrix(this.mOrientation * -1, 0.0f, 0.0f, 1.0f);
    }

    private void updateEncodingInfo(FrameProperty frameProperty) {
        if (frameProperty != null) {
            this.mTextureRender.setIdentitySTMatrix();
            float[] translateDelta = getTranslateDelta(frameProperty);
            this.mTextureRender.translateSTMatrix(getTransformTranslate(translateDelta, 0), getTransformTranslate(translateDelta, 1));
            GLRenderTexture gLRenderTexture = this.mTextureRender;
            float pivotX = frameProperty.getPivotX();
            float pivotY = frameProperty.getPivotY();
            float[] fArr = this.mSizeRatio;
            gLRenderTexture.setPivot(pivotX, pivotY, fArr[0], fArr[1], frameProperty.getScaleX(), frameProperty.getScaleY());
            this.mTextureRender.scaleSTMatrix(this.mSizeRatio[0] / frameProperty.getScaleX(), this.mSizeRatio[1] / frameProperty.getScaleY(), 1.0f);
            this.mTextureRender.setAlpha(frameProperty.getAlpha());
        }
    }

    public void draw(FrameProperty frameProperty) {
        updateEncodingInfo(frameProperty);
        this.mTextureRender.draw();
    }

    public void initRender() {
        this.mTextureRender = new GLRenderTexture.Builder(3553, this.mOutputWidth, this.mOutputHeight).useBlending().setBitmap(this.mBitmap).build();
        initSurfaceTexture();
        this.mBitmapRecycler.accept(this.mBitmap);
        this.mBitmap = null;
    }

    public void release() {
        this.mTextureRender.release();
    }

    public void setBitmapWithRecycler(Bitmap bitmap, Consumer<Bitmap> consumer) {
        this.mBitmap = bitmap;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = this.mBitmap.getHeight();
        this.mBitmapRecycler = consumer;
    }

    public void setCropRect(Rect rect) {
        this.mImageCropRect = rect;
    }

    public void setMvpRatio(Rect rect) {
        float[] mvpRatio = getMvpRatio(rect, this.mOutputWidth, this.mOutputHeight);
        this.mMvpWidthRatio = mvpRatio[0];
        this.mMvpHeightRatio = mvpRatio[1];
    }
}
