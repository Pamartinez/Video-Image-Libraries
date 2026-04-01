package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Bitmap;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StaticImageRender {
    private Bitmap mBitmap;
    private final int mBitmapHeight;
    private final int mBitmapWidth;
    private final int mOutputHeight;
    private final int mOutputWidth;
    protected GLRenderTexture mTextureRender;

    public StaticImageRender(Bitmap bitmap, BlendOption blendOption, int i2, int i7) {
        this.mBitmap = bitmap;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        this.mOutputWidth = i2;
        this.mOutputHeight = i7;
        this.mTextureRender = new GLRenderTexture.Builder(3553, i2, i7).useBlending(blendOption).setBitmap(this.mBitmap).build();
        recycleBitmap();
        applyMatrices();
    }

    private void applyMatrices() {
        float[] calculateSTRatioXY = calculateSTRatioXY();
        float[] calculateTranslateXY = calculateTranslateXY(calculateSTRatioXY);
        this.mTextureRender.translateSTMatrix(calculateTranslateXY[0], calculateTranslateXY[1]);
        this.mTextureRender.scaleSTMatrix(covertGlScale(calculateSTRatioXY[0]), covertGlScale(calculateSTRatioXY[1]), 1.0f);
        this.mTextureRender.rotateMVPMatrix(0, 0.0f, 0.0f, 1.0f);
    }

    private float[] calculateSTRatioXY() {
        float f;
        float f5 = ((float) this.mBitmapWidth) / ((float) this.mOutputWidth);
        float f8 = ((float) this.mBitmapHeight) / ((float) this.mOutputHeight);
        float f10 = 1.0f;
        if (f5 > f8) {
            float f11 = f5 / f8;
            f = 1.0f;
            f10 = f11;
        } else {
            f = f8 / f5;
        }
        return new float[]{f10, f};
    }

    private float[] calculateTranslateXY(float[] fArr) {
        float f = fArr[0];
        float max = Math.max(((f - 1.0f) / 2.0f) / f, 0.0f);
        float f5 = fArr[1];
        return new float[]{max, Math.max(((f5 - 1.0f) / 2.0f) / f5, 0.0f)};
    }

    private float covertGlScale(float f) {
        return 1.0f / f;
    }

    private void recycleBitmap() {
        this.mBitmap.recycle();
        this.mBitmap = null;
    }

    private void updateEncodingInfo(FrameProperty frameProperty) {
        if (frameProperty != null) {
            this.mTextureRender.setAlpha(frameProperty.getAlpha());
        }
    }

    public void draw(FrameProperty frameProperty) {
        updateEncodingInfo(frameProperty);
        this.mTextureRender.draw();
    }

    public void release() {
        this.mTextureRender.release();
    }
}
