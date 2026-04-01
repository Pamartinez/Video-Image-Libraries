package com.samsung.android.sdk.sgpl.pip.renderer;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.sgpl.pip.util.Constants;
import com.samsung.android.sdk.sgpl.pip.util.OpenGlHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderTexture_GL_OES {
    private static final String A_POSITION = "a_Position";
    private static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    private static final String BLUR_FRAGMENT_SHADER_CODE = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 v_TextureCoord;\nuniform samplerExternalOES u_TextureUnit;\nuniform float fWidth;\nuniform float fHeight;\nvoid main()\t\t\t\t\t\t\t\t\t\t \n        {\n  vec4 lightColor; \t\t\t\t\t\t\t\t \n\thighp vec4 color = vec4(0,0,0,1);\t\t\n\thighp vec2 gaussFilter[7];\t\t\t\t\n\t gaussFilter[0] = vec2(-3.0, 0.1063);\t \n\t gaussFilter[1] = vec2(-2.0, 0.1403);\t \n\t gaussFilter[2] = vec2(-1.0, 0.1658);\t \n\t gaussFilter[3] = vec2(0.0, 0.1752); \n\t gaussFilter[4] = vec2(1.0, 0.1658);\t\n\t gaussFilter[5] = vec2(2.0, 0.1403);\t\n\t gaussFilter[6] = vec2(3.0, 0.1063);\t\n\t\t\t\t\t\t\t\t\t\t\t\t\t\n\tfor( int i = 0; i < 7; i++ )\t\t\n\t\tcolor += texture2D(u_TextureUnit, vec2( v_TextureCoord.x+gaussFilter[i].x*fWidth, v_TextureCoord.y+gaussFilter[i].x*fHeight))*gaussFilter[i].y;\t\t\n  gl_FragColor = color ;\t\n}\n";
    private static final int FLOAT_SIZE_BYTES = 4;
    private static final int ORIENTATION_0 = 0;
    private static final int ORIENTATION_180 = 180;
    private static final int ORIENTATION_270 = 270;
    private static final int ORIENTATION_90 = 90;
    public static final int PREPARE_FAILURE = 0;
    public static final int PREPARE_SUCCESS = 1;
    private static final String TEXTURE_FRAGMENT_SHADER_CODE = "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 v_TextureCoord;\nuniform samplerExternalOES u_TextureUnit;\nvoid main() {\n  gl_FragColor = texture2D(u_TextureUnit, v_TextureCoord);\n}\n";
    private static final String TEXTURE_VERTEX_SHADER_CODE = "uniform mat4 u_MVPMatrix;\nuniform mat4 u_STMatrix;\nattribute vec4 a_Position;\nattribute vec4 a_TextureCoordinates;\nvarying vec2 v_TextureCoord;\nvoid main() {\n  gl_Position = u_MVPMatrix * a_Position;\n  v_TextureCoord = (u_STMatrix * a_TextureCoordinates).xy;\n}\n";
    private static final String U_FRMAE_HEIGHT = "fHegiht";
    private static final String U_FRMAE_WIDTH = "fWidth";
    private static final String U_MVPMATRIX = "u_MVPMatrix";
    private static final String U_STMATRIX = "u_STMatrix";
    private static final String U_TEXTURE_UNIT = "u_TextureUnit";
    private static final int VERTICES_DATA_POS_COUNT = 2;
    private static final int VERTICES_DATA_POS_OFFSET = 0;
    private static final int VERTICES_DATA_STRIDE_BYTES = 16;
    private static final int VERTICES_DATA_TEX_COORD_COUNT = 2;
    private static final int VERTICES_DATA_TEX_COORD_OFFSET = 2;
    private float destHeightRatio = -1.0f;
    private float destWidthRatio = -1.0f;
    private boolean isSquare = false;
    private float mDestHeight;
    private float mDestWidth;
    private float mHeight;
    private float mHeightRatio;
    private int mInputVideoRotation;
    private boolean mMMSMode = false;
    private final float[] mMVPMatrix = new float[16];
    private int mOriginalHeight;
    private int mOriginalWidth;
    private int mProgram;
    private int mRotation;
    private final float[] mSTMatrix = new float[16];
    float mScaleX = 0.0f;
    float mScaleY = 0.0f;
    private int mTextureId;
    private final float[] mVerticesData;
    private FloatBuffer mVerticesFloatBuffer;
    private float mWidth;
    private float mWidthRatio;
    private int ma_PositionHandle;
    private int ma_TextureCoordinatesHandle;
    private int mu_FheightHandle;
    private int mu_FwidthHandle;
    private int mu_MVPMatrixHandle;
    private int mu_STMatrixHandle;
    private int mu_TextureUnitHandle;

    public RenderTexture_GL_OES() {
        float[] fArr = {-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
        this.mVerticesData = fArr;
        FloatBuffer put = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
        this.mVerticesFloatBuffer = put;
        put.position(0);
    }

    private void deleteTexture() {
        GLES20.glBindTexture(36197, 0);
        OpenGlHelper.deleteTexture(this.mTextureId);
        this.mTextureId = 0;
    }

    private void sclaeSTMatrixByScaleRatio(float f, float f5, float f8) {
        Matrix.translateM(this.mSTMatrix, 0, 0.5f, 0.5f, 1.0f);
        Matrix.scaleM(this.mSTMatrix, 0, f, f5, f8);
        Matrix.translateM(this.mSTMatrix, 0, -0.5f, -0.5f, 1.0f);
    }

    public void draw(SurfaceTexture surfaceTexture, int i2) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        surfaceTexture.getTransformMatrix(this.mSTMatrix);
        int abs = Math.abs(this.mRotation - this.mInputVideoRotation);
        if (abs == 90 || abs == ORIENTATION_270) {
            sclaeSTMatrixByScaleRatio(this.mHeightRatio, this.mWidthRatio, 1.0f);
        } else {
            sclaeSTMatrixByScaleRatio(this.mWidthRatio, this.mHeightRatio, 1.0f);
        }
        GLES20.glUseProgram(this.mProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(36197, this.mTextureId);
        GLES20.glUniformMatrix4fv(this.mu_MVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(this.mu_STMatrixHandle, 1, false, this.mSTMatrix, 0);
        GLES20.glUniform1i(this.mu_TextureUnitHandle, 0);
        if (this.mMMSMode) {
            GLES20.glUniform1f(this.mu_FwidthHandle, 1.0f / this.mWidth);
            GLES20.glUniform1f(this.mu_FheightHandle, 1.0f / this.mHeight);
        }
        this.mVerticesFloatBuffer.position(0);
        GLES20.glVertexAttribPointer(this.ma_PositionHandle, 2, 5126, false, 16, this.mVerticesFloatBuffer);
        GLES20.glEnableVertexAttribArray(this.ma_PositionHandle);
        OpenGlHelper.checkGLError("glEnableVertexAttribArray ma_PositionHandle");
        this.mVerticesFloatBuffer.position(2);
        GLES20.glVertexAttribPointer(this.ma_TextureCoordinatesHandle, 2, 5126, false, 16, this.mVerticesFloatBuffer);
        GLES20.glEnableVertexAttribArray(this.ma_TextureCoordinatesHandle);
        OpenGlHelper.checkGLError("glEnableVertexAttribArray ma_TextureCoordinatesHandle");
        GLES20.glEnable(3042);
        GLES20.glBlendFunc(ASVLOFFSCREEN.ASVL_PAF_RGB32_B8G8R8A8, ASVLOFFSCREEN.ASVL_PAF_RGB32_R8G8B8);
        GLES20.glDrawArrays(5, 0, 4);
        Log.d(Constants.TAG, "Calling glFinish blocking call");
        GLES20.glFinish();
        Log.d(Constants.TAG, "Finished glFinish");
    }

    public int getTextureId() {
        return this.mTextureId;
    }

    public void initMVPMatrixProperty(int i2, float f, float f5) {
        float f8;
        int abs = Math.abs(this.mRotation - i2);
        this.mInputVideoRotation = i2;
        float f10 = this.mWidth;
        float f11 = this.mHeight;
        float f12 = f / f10;
        float f13 = f5 / f11;
        float f14 = f10 * f12;
        int i7 = this.mOriginalWidth;
        this.mWidthRatio = f14 / ((float) i7);
        float f15 = f13 * f11;
        int i8 = this.mOriginalHeight;
        this.mHeightRatio = f15 / ((float) i8);
        if (abs == 90 || abs == ORIENTATION_270) {
            this.mWidthRatio = f15 / ((float) i7);
            this.mHeightRatio = f14 / ((float) i8);
            f8 = f / f11;
        } else {
            f8 = f12;
        }
        float f16 = this.mWidthRatio;
        float f17 = this.mHeightRatio;
        if (f16 > f17) {
            float f18 = f17 / f16;
            this.mHeightRatio = f18;
            float f19 = f16 / f16;
            this.mWidthRatio = f19;
            if (f18 > 1.0f) {
                this.mWidthRatio = f19 / f18;
                this.mHeightRatio = f18 / f18;
            }
        } else {
            float f20 = f16 / f17;
            this.mWidthRatio = f20;
            float f21 = f17 / f17;
            this.mHeightRatio = f21;
            if (f20 > 1.0f) {
                float f22 = f20 / f20;
                this.mWidthRatio = f22;
                this.mHeightRatio = f21 / f22;
            }
        }
        this.mScaleX = f12 * f8;
        this.mScaleY = f8 * f13;
        this.mDestWidth = f;
        this.mDestHeight = f5;
        Matrix.setIdentityM(this.mMVPMatrix, 0);
        Matrix.scaleM(this.mMVPMatrix, 0, f12, f13, 1.0f);
    }

    public void initSurfaceAspectRatioAndScaleMVPMatrix(int i2, float f, float f5) {
        initMVPMatrixProperty(i2, f, f5);
    }

    public int loadTexture(int i2) {
        if (this.mTextureId != 0) {
            deleteTexture();
        }
        int loadTextureOES = OpenGlHelper.loadTextureOES();
        this.mTextureId = loadTextureOES;
        if (loadTextureOES == 0) {
            Log.d(Constants.TAG, "not able to load new texture");
        }
        Matrix.setIdentityM(this.mMVPMatrix, 0);
        Matrix.setRotateM(this.mMVPMatrix, 0, (float) i2, 0.0f, 0.0f, 1.0f);
        return this.mTextureId;
    }

    public int prepare(int i2) {
        int createProgram = OpenGlHelper.createProgram(TEXTURE_VERTEX_SHADER_CODE, TEXTURE_FRAGMENT_SHADER_CODE);
        this.mProgram = createProgram;
        if (createProgram == 0) {
            return 0;
        }
        this.mu_MVPMatrixHandle = GLES20.glGetUniformLocation(createProgram, U_MVPMATRIX);
        this.mu_STMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, U_STMATRIX);
        this.ma_PositionHandle = GLES20.glGetAttribLocation(this.mProgram, A_POSITION);
        this.ma_TextureCoordinatesHandle = GLES20.glGetAttribLocation(this.mProgram, A_TEXTURE_COORDINATES);
        this.mu_TextureUnitHandle = GLES20.glGetUniformLocation(this.mProgram, U_TEXTURE_UNIT);
        loadTexture(i2);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        return 1;
    }

    public void release() {
        deleteTexture();
        GLES20.glDeleteProgram(this.mProgram);
        this.mProgram = 0;
        this.mu_MVPMatrixHandle = 0;
        this.mu_STMatrixHandle = 0;
        this.ma_PositionHandle = 0;
        this.ma_TextureCoordinatesHandle = 0;
        this.mu_TextureUnitHandle = 0;
        this.mVerticesFloatBuffer = null;
        this.mu_FwidthHandle = 0;
        this.mu_FheightHandle = 0;
        this.mMMSMode = false;
    }

    public void setMVPMatrixPosition(float f, float f5, float f8) {
        Matrix.translateM(this.mMVPMatrix, 0, f, f5, f8);
    }

    public void setSurfacePosition(int i2, int i7) {
        float f = this.mDestWidth;
        float f5 = ((((float) i2) / f) * this.mWidth) / f;
        float f8 = this.mDestHeight;
        float f10 = ((((float) i7) / f8) * this.mHeight) / f8;
        Log.d("wonguen", "setSurfacePosition: " + f5 + ArcCommonLog.TAG_COMMA + f10);
        setMVPMatrixPosition(f5, f10, 0.0f);
    }

    public int prepare(int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14, int i15) {
        int createProgram = OpenGlHelper.createProgram(TEXTURE_VERTEX_SHADER_CODE, TEXTURE_FRAGMENT_SHADER_CODE);
        this.mProgram = createProgram;
        if (createProgram == 0) {
            return 0;
        }
        this.mu_MVPMatrixHandle = GLES20.glGetUniformLocation(createProgram, U_MVPMATRIX);
        this.mu_STMatrixHandle = GLES20.glGetUniformLocation(this.mProgram, U_STMATRIX);
        this.ma_PositionHandle = GLES20.glGetAttribLocation(this.mProgram, A_POSITION);
        this.ma_TextureCoordinatesHandle = GLES20.glGetAttribLocation(this.mProgram, A_TEXTURE_COORDINATES);
        this.mu_TextureUnitHandle = GLES20.glGetUniformLocation(this.mProgram, U_TEXTURE_UNIT);
        loadTexture(i2);
        this.mWidth = (float) i10;
        this.mHeight = (float) i11;
        this.mOriginalWidth = i12;
        this.mOriginalHeight = i13;
        this.mRotation = i2;
        Log.d("framebuffer", "prepare: Video Texture ID " + this.mTextureId);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glViewport(i7, i8, i10, i11);
        return 1;
    }
}
