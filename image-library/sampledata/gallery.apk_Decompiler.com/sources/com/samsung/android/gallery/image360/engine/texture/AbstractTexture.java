package com.samsung.android.gallery.image360.engine.texture;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.util.ShaderHelper;
import x8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class AbstractTexture implements ITexture {
    protected final String TAG = getClass().getSimpleName();
    boolean mAnimate = false;
    float mAnimationXDir;
    float mAnimationYDir;
    float mAnimationZDir;
    int mHeight;
    int mMVMatrixHandle;
    final float[] mMVPMatrix = new float[16];
    int mMVPMatrixHandle;
    float mMaxDisplacement;
    final float[] mModelMatrix = new float[16];
    int mPositionHandle;
    int mProgramHandle;
    final float[] mProjectionMatrix = new float[16];
    private RenderRequestListener mRendererRequester = null;
    int mRepeatCount;
    float mScreenSizeRatio = 1.0f;
    private StatusHandler mStatusHandler;
    int mTextureCoordinateHandle;
    int[] mTextureDataHandle;
    TextureManager mTextureManager;
    private int mTextureUniformHandle;
    final float[] mViewMatrix = new float[16];
    int mWidth;

    public void doAnimation() {
        int i2 = this.mRepeatCount;
        if (i2 > 0) {
            this.mRepeatCount = i2 - 1;
            RenderRequestListener renderRequestListener = this.mRendererRequester;
            if (renderRequestListener != null) {
                ((a) renderRequestListener).a();
                return;
            }
            return;
        }
        this.mAnimate = false;
    }

    public void getCommonDrawHandles() {
        GLES20.glUseProgram(this.mProgramHandle);
        this.mMVPMatrixHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "u_MVPMatrix");
        this.mMVMatrixHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "u_MVMatrix");
        this.mTextureUniformHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "u_Texture");
        this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgramHandle, "a_Position");
        this.mTextureCoordinateHandle = GLES20.glGetAttribLocation(this.mProgramHandle, "a_TexCoordinate");
    }

    public void setAnimValues() {
        this.mRepeatCount = 8;
        this.mAnimate = true;
    }

    public void setCommonDrawAttributes() {
        GLES20.glEnable(2884);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTextureDataHandle[0]);
        GLES20.glUniform1i(this.mTextureUniformHandle, 0);
        Matrix.setIdentityM(this.mModelMatrix, 0);
    }

    public void setRendererRequester(RenderRequestListener renderRequestListener) {
        this.mRendererRequester = renderRequestListener;
    }

    public void setScreenSize(int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
        this.mMaxDisplacement = ((float) Math.sqrt((double) ((i7 * i7) + (i2 * i2)))) / 4.0f;
        this.mScreenSizeRatio = ((float) i2) / ((float) i7);
    }

    public void setStatusHandler(StatusHandler statusHandler) {
        this.mStatusHandler = statusHandler;
    }

    public void setTextureManager(TextureManager textureManager) {
        this.mTextureManager = textureManager;
    }

    public void updateGlAttributes(String str) {
        int loadShader = ShaderHelper.loadShader(35633, str);
        int loadShader2 = ShaderHelper.loadShader(35632, "precision mediump float; \nuniform sampler2D u_Texture;   \nvarying vec2 v_TexCoordinate;  \nvoid main()                    \n{                              \n gl_FragColor = texture2D(u_Texture, v_TexCoordinate); \n}                              \n");
        if (!(loadShader == 0 || loadShader2 == 0)) {
            this.mProgramHandle = ShaderHelper.createProgram(loadShader, loadShader2, new String[]{"a_Position", "a_TexCoordinate"});
        }
        if (this.mProgramHandle == 0) {
            String str2 = this.TAG;
            Log.e(str2, "updateGlAttributes: invalid program handle. " + this);
            StatusHandler statusHandler = this.mStatusHandler;
            if (statusHandler != null) {
                statusHandler.onError(2);
                return;
            }
            return;
        }
        TextureManager textureManager = this.mTextureManager;
        if (textureManager != null) {
            this.mTextureDataHandle = textureManager.getTextureDataHandle();
        }
    }
}
