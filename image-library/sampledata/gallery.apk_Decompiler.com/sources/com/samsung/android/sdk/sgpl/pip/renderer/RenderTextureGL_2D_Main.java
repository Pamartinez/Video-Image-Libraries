package com.samsung.android.sdk.sgpl.pip.renderer;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.sdk.sgpl.pip.util.OpenGlHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderTextureGL_2D_Main {
    private static final String A_POSITION = "a_Position";
    private static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    private static final int FLOAT_SIZE_BYTES = 4;
    public static final int PREPARE_FAILURE = 0;
    public static final int PREPARE_SUCCESS = 1;
    private static final String TAG = "PIP";
    private static final String TEXTURE_FRAGMENT_SHADER_CODE = "precision mediump float;\nvarying vec2 v_TextureCoord;\nuniform sampler2D u_TextureUnit;\nvoid main() {\n  gl_FragColor = texture2D(u_TextureUnit, v_TextureCoord);\n}\n";
    private static final String TEXTURE_VERTEX_SHADER_CODE = "uniform mat4 u_MVPMatrix;\nuniform mat4 u_STMatrix;\nattribute vec4 a_Position;\nattribute vec4 a_TextureCoordinates;\nvarying vec2 v_TextureCoord;\nvoid main() {\n  gl_Position = u_MVPMatrix * a_Position;\n  v_TextureCoord = (u_STMatrix * a_TextureCoordinates).xy;\n}\n";
    private static final String U_MVPMATRIX = "u_MVPMatrix";
    private static final String U_STMATRIX = "u_STMatrix";
    private static final String U_TEXTURE_UNIT = "u_TextureUnit";
    private static final int VERTICES_DATA_POS_COUNT = 2;
    private static final int VERTICES_DATA_POS_OFFSET = 0;
    private static final int VERTICES_DATA_STRIDE_BYTES = 16;
    private static final int VERTICES_DATA_TEX_COORD_COUNT = 2;
    private static final int VERTICES_DATA_TEX_COORD_OFFSET = 2;
    private int mHeight;
    private final float[] mMVPMatrix = new float[16];
    private int mProgram;
    private int mRotation;
    private final float[] mSTMatrix = new float[16];
    private int mTextureId;
    private final float[] mVerticesData;
    private FloatBuffer mVerticesFloatBuffer;
    private int mWidth;
    private int ma_PositionHandle;
    private int ma_TextureCoordinatesHandle;
    private int mu_MVPMatrixHandle;
    private int mu_STMatrixHandle;
    private int mu_TextureUnitHandle;

    public RenderTextureGL_2D_Main() {
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

    private void drawGLES20Helper(SurfaceTexture surfaceTexture) {
        GLES20.glUniformMatrix4fv(this.mu_MVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(this.mu_STMatrixHandle, 1, false, this.mSTMatrix, 0);
        GLES20.glUniform1i(this.mu_TextureUnitHandle, 0);
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
        Log.d("PIP", "Calling glFinish blocking call");
        GLES20.glFinish();
        Log.d("PIP", "Finished glFinish");
    }

    public void draw(SurfaceTexture surfaceTexture) {
        GLES20.glClearColor(0.0f, 1.0f, 0.0f, 1.0f);
        surfaceTexture.getTransformMatrix(this.mSTMatrix);
        GLES20.glUseProgram(this.mProgram);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTextureId);
        drawGLES20Helper(surfaceTexture);
    }

    public int getTextureId() {
        return this.mTextureId;
    }

    public int loadTexture(int i2, int i7, int i8) {
        if (this.mTextureId != 0) {
            deleteTexture();
        }
        int loadMainTexture = OpenGlHelper.loadMainTexture(i7, i8);
        this.mTextureId = loadMainTexture;
        if (loadMainTexture == 0) {
            Log.d("PIP", "not able to load new texture");
        }
        Log.d("PIP", "loadTexture: " + this.mTextureId + "'s rotation : " + i2);
        Matrix.setIdentityM(this.mMVPMatrix, 0);
        Matrix.setRotateM(this.mMVPMatrix, 0, (float) i2, 0.0f, 0.0f, 1.0f);
        return this.mTextureId;
    }

    public int prepare(int i2, int i7, int i8, int i10, int i11) {
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
        loadTexture(i2, i10, i11);
        Log.d("framebuffer", "prepare: TEXTURE_FRAGMENT_SHADER_CODE " + this.mTextureId);
        this.mWidth = i10;
        this.mHeight = i11;
        this.mRotation = i2;
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
    }

    public void rotationMainOutputSurface(int i2) {
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        Matrix.setRotateM(fArr, 0, (float) i2, 0.0f, 0.0f, 1.0f);
        float[] fArr2 = fArr;
        Matrix.multiplyMM(this.mMVPMatrix, 0, (float[]) this.mMVPMatrix.clone(), 0, fArr2, 0);
    }
}
