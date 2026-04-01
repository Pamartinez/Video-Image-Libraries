package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import com.samsung.android.gallery.module.story.transcode.renderer.constants.BlendOption;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GLRenderTexture {
    private float mAlpha;
    private final float[] mMVPMatrix;
    private final RenderObject mRenderObject;
    private final float[] mSTMatrix;
    private final Shader mShader;
    private final Texture mTexture;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        Bitmap bitmap;
        BlendOption blendOption = BlendOption.NONE;
        int height;
        RectCorners roundness;
        int texType;
        boolean useVideoVertices;
        int width;

        public Builder(int i2, int i7, int i8) {
            this.texType = i2;
            this.width = i7;
            this.height = i8;
        }

        public GLRenderTexture build() {
            return new GLRenderTexture(this, 0);
        }

        public Builder setBitmap(Bitmap bitmap2) {
            this.bitmap = bitmap2;
            return this;
        }

        public Builder setRoundness(RectCorners rectCorners) {
            this.roundness = rectCorners;
            return this;
        }

        public Builder useBlending() {
            this.blendOption = BlendOption.NORMAL;
            return this;
        }

        public Builder useVideoVertices() {
            this.useVideoVertices = true;
            return this;
        }

        public Builder useBlending(BlendOption blendOption2) {
            this.blendOption = blendOption2;
            return this;
        }
    }

    public /* synthetic */ GLRenderTexture(Builder builder, int i2) {
        this(builder);
    }

    private void clearColorBuffer() {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClear(16384);
    }

    private void initMatrices() {
        Matrix.setIdentityM(this.mMVPMatrix, 0);
        Matrix.setIdentityM(this.mSTMatrix, 0);
    }

    private void initTexture(Builder builder) {
        Bitmap bitmap = builder.bitmap;
        if (bitmap != null) {
            this.mTexture.load(bitmap);
        } else {
            this.mTexture.generateIdOnly(builder.width, builder.height);
        }
    }

    private void initViewPort(int i2, int i7) {
        GLES20.glViewport(0, 0, i2, i7);
    }

    private void postDraw() {
        GLES20.glDisable(3042);
        GLES20.glFinish();
    }

    public void draw() {
        this.mShader.use();
        this.mShader.applyMatrix(this.mMVPMatrix, this.mSTMatrix);
        this.mShader.applyAlpha(this.mAlpha);
        this.mTexture.bind(this.mShader);
        this.mRenderObject.draw(this.mShader, this.mMVPMatrix);
        postDraw();
    }

    public void drawOnSurface(SurfaceTexture surfaceTexture) {
        surfaceTexture.getTransformMatrix(this.mSTMatrix);
        draw();
    }

    public int getTextureId() {
        return this.mTexture.mId;
    }

    public void loadDefaultSTMatrix(SurfaceTexture surfaceTexture) {
        surfaceTexture.getTransformMatrix(this.mSTMatrix);
    }

    public void release() {
        this.mTexture.release();
        this.mRenderObject.release();
        this.mShader.release();
    }

    public void rotateMVPMatrix(int i2, float f, float f5, float f8) {
        Matrix.rotateM(this.mMVPMatrix, 0, (float) i2, f, f5, f8);
    }

    public void scaleMVPMatrix(float f, float f5) {
        Matrix.scaleM(this.mMVPMatrix, 0, f, f5, 1.0f);
    }

    public void scaleSTMatrix(float f, float f5, float f8) {
        Matrix.scaleM(this.mSTMatrix, 0, f, f5, f8);
    }

    public void setAlpha(float f) {
        this.mAlpha = f;
    }

    public void setIdentitySTMatrix() {
        Matrix.setIdentityM(this.mSTMatrix, 0);
    }

    public void setPivot(float f, float f5, float f8, float f10, float f11, float f12) {
        float f13 = 1.0f / f11;
        float f14 = 1.0f / f12;
        Matrix.translateM(this.mSTMatrix, 0, f8 * f * 2.0f * ((((1.0f - f13) * f8) / 2.0f) / f13), f10 * f5 * 2.0f * ((((1.0f - f14) * f10) / 2.0f) / f14), 0.0f);
    }

    public void translateMVPMatrix(float f, float f5) {
        Matrix.translateM(this.mMVPMatrix, 0, f, f5, 1.0f);
    }

    public void translateSTMatrix(float f, float f5) {
        Matrix.translateM(this.mSTMatrix, 0, f, f5, 1.0f);
    }

    private GLRenderTexture(Builder builder) {
        this.mMVPMatrix = new float[16];
        this.mSTMatrix = new float[16];
        this.mAlpha = 1.0f;
        boolean z = builder.roundness != null;
        this.mRenderObject = z ? new RoundRenderObject(builder) : new RenderObject(builder);
        this.mShader = new Shader(builder.texType, builder.blendOption, z);
        this.mTexture = new Texture(builder.texType);
        initMatrices();
        initTexture(builder);
        clearColorBuffer();
        initViewPort(builder.width, builder.height);
    }
}
