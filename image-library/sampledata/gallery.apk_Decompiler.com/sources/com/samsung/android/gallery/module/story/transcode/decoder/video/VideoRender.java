package com.samsung.android.gallery.module.story.transcode.decoder.video;

import android.graphics.Rect;
import android.graphics.RectF;
import android.media.MediaFormat;
import android.view.Surface;
import android.widget.ImageView;
import com.samsung.android.gallery.module.story.transcode.config.FrameProperty;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;
import com.samsung.android.gallery.module.story.transcode.renderer.render.RectCorners;
import com.samsung.android.gallery.module.story.transcode.renderer.surface.OutputSurface;
import com.samsung.android.gallery.module.story.transcode.util.ThumbProvider;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoRender {
    private String TAG = "VideoRender";
    private Rect mCropRect;
    private Rect mDisplayRect;
    private int mId;
    private int mOrientation;
    private final int mOutputHeight;
    protected OutputSurface mOutputSurface;
    private final int mOutputWidth;
    private RectCorners mRoundness;
    private ImageView.ScaleType mScaleType;
    protected GLRenderTexture mTextureRender;
    private float[] mTranslateSTXY;
    private final int mVideoHeight;
    private float[] mVideoRatioSTXY;
    private final int mVideoWidth;

    public VideoRender(int i2, int i7, MediaFormat mediaFormat) {
        this.mOutputWidth = i2;
        this.mOutputHeight = i7;
        this.mVideoWidth = mediaFormat.getInteger("width");
        this.mVideoHeight = mediaFormat.getInteger("height");
    }

    private float computeGLPosition(float f, float f5, boolean z) {
        float f8 = 1.0f;
        float f10 = (((f5 / 2.0f) + f) * 2.0f) - 1.0f;
        if (z) {
            f8 = -1.0f;
        }
        return f10 * f8;
    }

    private float getTransformTranslate(float[] fArr, int i2) {
        return (this.mVideoRatioSTXY[i2] * fArr[i2]) + this.mTranslateSTXY[i2];
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

    private float[] getTranslateMVPXY() {
        if (!RectUtils.isValidRect(this.mDisplayRect)) {
            return new float[2];
        }
        RectF rectF = new RectF();
        Rect rect = this.mDisplayRect;
        int i2 = this.mOutputWidth;
        float f = ((float) rect.left) / ((float) i2);
        rectF.left = f;
        int i7 = this.mOutputHeight;
        rectF.top = ((float) rect.top) / ((float) i7);
        rectF.right = ((float) rect.right) / ((float) i2);
        rectF.bottom = ((float) rect.bottom) / ((float) i7);
        return new float[]{computeGLPosition(f, rectF.width(), false), computeGLPosition(rectF.top, rectF.height(), true)};
    }

    private float[] getTranslateXY() {
        if (RectUtils.isValidRect(this.mCropRect)) {
            Rect rect = this.mCropRect;
            float f = ((float) rect.left) / ((float) this.mVideoWidth);
            float f5 = ((float) rect.top) / ((float) this.mVideoHeight);
            if (this.mOrientation % MOCRLang.KHMER == 0) {
                return new float[]{f, f5};
            }
            return new float[]{f5, f};
        } else if (!RectUtils.isValidRect(this.mDisplayRect)) {
            return new float[2];
        } else {
            float[] fArr = this.mVideoRatioSTXY;
            return new float[]{(1.0f - fArr[0]) / 2.0f, (1.0f - fArr[1]) / 2.0f};
        }
    }

    private int getVideoHeight() {
        if (this.mOrientation % MOCRLang.KHMER == 0) {
            return this.mVideoHeight;
        }
        return this.mVideoWidth;
    }

    private float[] getVideoRatioXY() {
        if (RectUtils.isValidRect(this.mCropRect)) {
            float width = ((float) this.mCropRect.width()) / ((float) this.mVideoWidth);
            float height = ((float) this.mCropRect.height()) / ((float) this.mVideoHeight);
            if (this.mOrientation % MOCRLang.KHMER == 0) {
                return new float[]{width, height};
            }
            return new float[]{height, width};
        } else if (!RectUtils.isValidRect(this.mDisplayRect)) {
            return new float[]{1.0f, 1.0f};
        } else {
            float width2 = ((float) this.mDisplayRect.width()) / ((float) this.mDisplayRect.height());
            float videoWidth = ((float) getVideoWidth()) / ((float) getVideoHeight());
            if (width2 < videoWidth) {
                return new float[]{width2 / videoWidth, 1.0f};
            }
            return new float[]{1.0f, videoWidth / width2};
        }
    }

    private int getVideoWidth() {
        if (this.mOrientation % MOCRLang.KHMER == 0) {
            return this.mVideoWidth;
        }
        return this.mVideoHeight;
    }

    private void initDisplayRect() {
        if (RectUtils.isValidRect(this.mDisplayRect)) {
            return;
        }
        if (ImageView.ScaleType.CENTER_CROP.equals(this.mScaleType)) {
            this.mDisplayRect = new Rect(0, 0, this.mOutputWidth, this.mOutputHeight);
            return;
        }
        int videoWidth = getVideoWidth();
        float f = (float) videoWidth;
        float videoHeight = (float) getVideoHeight();
        Rect rect = new Rect();
        if (((float) this.mOutputWidth) / ((float) this.mOutputHeight) >= f / videoHeight) {
            int i2 = this.mOutputHeight;
            int i7 = (int) ((((float) i2) / videoHeight) * f);
            int i8 = (this.mOutputWidth - i7) / 2;
            rect.left = i8;
            rect.right = i8 + i7;
            rect.top = 0;
            rect.bottom = i2;
        } else {
            int i10 = this.mOutputWidth;
            int i11 = (int) ((((float) i10) / f) * videoHeight);
            rect.left = 0;
            rect.right = i10;
            int i12 = (this.mOutputHeight - i11) / 2;
            rect.top = i12;
            rect.bottom = i12 + i11;
        }
        this.mDisplayRect = rect;
    }

    private void initOutputMatrix() {
        float f;
        float f5;
        float[] translateMVPXY = getTranslateMVPXY();
        if (RectUtils.isValidRect(this.mDisplayRect)) {
            f5 = ((float) this.mDisplayRect.width()) / ((float) this.mOutputWidth);
            f = ((float) this.mDisplayRect.height()) / ((float) this.mOutputHeight);
        } else {
            f5 = 1.0f;
            f = 1.0f;
        }
        this.mTextureRender.translateMVPMatrix(translateMVPXY[0], translateMVPXY[1]);
        this.mTextureRender.scaleMVPMatrix(f5, f);
    }

    private void initRatio() {
        initDisplayRect();
        this.mVideoRatioSTXY = getVideoRatioXY();
        this.mTranslateSTXY = getTranslateXY();
    }

    private void initSurface() {
        try {
            this.mOutputSurface = new OutputSurface(this.mTextureRender.getTextureId());
        } catch (Exception unused) {
            String str = this.TAG;
            Log.e(str, "Can't get input video resolution: black screen for content id=" + this.mId);
            this.mOutputSurface = new OutputSurface(0);
        }
    }

    private void initTextureRender() {
        this.mTextureRender = new GLRenderTexture.Builder(36197, this.mOutputWidth, this.mOutputHeight).useVideoVertices().useBlending().setRoundness(this.mRoundness).build();
    }

    private void updateEncodingInfo(FrameProperty frameProperty) {
        if (frameProperty != null) {
            this.mTextureRender.loadDefaultSTMatrix(this.mOutputSurface.getSurfaceTexture());
            float[] translateDelta = getTranslateDelta(frameProperty);
            this.mTextureRender.translateSTMatrix(getTransformTranslate(translateDelta, 0), getTransformTranslate(translateDelta, 1));
            GLRenderTexture gLRenderTexture = this.mTextureRender;
            float pivotX = frameProperty.getPivotX();
            float pivotY = frameProperty.getPivotY();
            float[] fArr = this.mVideoRatioSTXY;
            gLRenderTexture.setPivot(pivotX, pivotY, fArr[0], fArr[1], frameProperty.getScaleX(), frameProperty.getScaleY());
            this.mTextureRender.scaleSTMatrix(this.mVideoRatioSTXY[0] / frameProperty.getScaleX(), this.mVideoRatioSTXY[1] / frameProperty.getScaleY(), 1.0f);
            this.mTextureRender.setAlpha(frameProperty.getAlpha());
        }
    }

    public void draw(FrameProperty frameProperty) {
        updateEncodingInfo(frameProperty);
        this.mTextureRender.draw();
    }

    public Surface getSurface() {
        return this.mOutputSurface.getSurface();
    }

    public void initRender(int i2, int i7) {
        this.TAG += com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + i2;
        this.mId = i2;
        this.mOrientation = i7;
        initTextureRender();
        initSurface();
        initRatio();
        initOutputMatrix();
    }

    public void release() {
        OutputSurface outputSurface = this.mOutputSurface;
        if (outputSurface != null) {
            try {
                outputSurface.release();
            } catch (Exception e) {
                String str = this.TAG;
                Log.e(str, "Exception in releasing outputSurface. videoID : " + this.mId);
                e.printStackTrace();
            }
        }
        GLRenderTexture gLRenderTexture = this.mTextureRender;
        if (gLRenderTexture != null) {
            gLRenderTexture.release();
            this.mTextureRender = null;
        }
    }

    public void setDisplayRect(RectF rectF) {
        if (RectUtils.isValidRect(rectF)) {
            float f = rectF.left;
            int i2 = this.mOutputWidth;
            float f5 = rectF.top;
            int i7 = this.mOutputHeight;
            this.mDisplayRect = new Rect((int) (f * ((float) i2)), (int) (f5 * ((float) i7)), (int) (rectF.right * ((float) i2)), (int) (rectF.bottom * ((float) i7)));
        }
    }

    public void setRoundness(RectCorners rectCorners) {
        this.mRoundness = rectCorners;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        if (ImageView.ScaleType.FIT_CENTER.equals(scaleType)) {
            this.mScaleType = null;
        }
    }

    public void setSmartCropRect(ThumbProvider thumbProvider) {
        Rect[] displayRectWithSmartCrop = thumbProvider.getDisplayRectWithSmartCrop(this.mId, new Rect(0, 0, this.mOutputWidth, this.mOutputHeight), this.mVideoWidth, this.mVideoHeight);
        this.mDisplayRect = displayRectWithSmartCrop[0];
        this.mCropRect = displayRectWithSmartCrop[1];
    }

    public boolean waitRenderFrame(int i2) {
        return this.mOutputSurface.checkForNewImage(i2);
    }
}
