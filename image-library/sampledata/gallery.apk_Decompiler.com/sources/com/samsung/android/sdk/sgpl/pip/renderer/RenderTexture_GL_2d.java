package com.samsung.android.sdk.sgpl.pip.renderer;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.sdk.sgpl.pip.util.Constants;
import com.samsung.android.sdk.sgpl.pip.util.OpenGlHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenderTexture_GL_2d {
    private static final String A_POSITION = "a_Position";
    private static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
    private static final int FLOAT_SIZE_BYTES = 4;
    public static final int PREPARE_FAILURE = 0;
    public static final int PREPARE_SUCCESS = 1;
    private static final String TEXTURE_FRAGMENT_SHADER_CODE = "precision mediump float;\nuniform sampler2D u_TextureUnit;\nvarying vec2 v_TextureCoordinates;\nvoid main(){ \ngl_FragColor = texture2D(u_TextureUnit, v_TextureCoordinates);\n}\n";
    private static final String TEXTURE_VERTEX_SHADER_CODE = "uniform mat4 u_Matrix;\nattribute vec4 a_Position;\nattribute vec2 a_TextureCoordinates;\nvarying vec2 v_TextureCoordinates;\nvoid main(){\nv_TextureCoordinates = a_TextureCoordinates;\ngl_Position =u_Matrix*a_Position;\n}\n";
    private static final String U_MATRIX = "u_Matrix";
    private static final String U_STMATRIX = "u_STMatrix";
    private static final String U_TEXTURE_UNIT = "u_TextureUnit";
    private static final int VERTICES_DATA_POS_COUNT = 2;
    private static final int VERTICES_DATA_POS_OFFSET = 0;
    private static final int VERTICES_DATA_STRIDE_BYTES = 16;
    private static final int VERTICES_DATA_TEX_COORD_COUNT = 2;
    private static final int VERTICES_DATA_TEX_COORD_OFFSET = 2;
    private int mHeight;
    private int mProgram;
    private final float[] mSTMatrix = new float[16];
    private int mTextureId;
    private final float[] mVerticesData;
    private FloatBuffer mVerticesFloatBuffer;
    private int mWidth;
    private int ma_PositionHandle;
    private int ma_TextureCoordinatesHandle;
    private int mu_MatrixHandle;
    private int mu_TextureUnitHandle;
    private final float[] projectionMatrix = new float[16];

    public RenderTexture_GL_2d() {
        float[] fArr = {-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};
        this.mVerticesData = fArr;
        FloatBuffer put = ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr);
        this.mVerticesFloatBuffer = put;
        put.position(0);
    }

    private void deleteTexture() {
        GLES20.glBindTexture(3553, 0);
        OpenGlHelper.deleteTexture(this.mTextureId);
        this.mTextureId = 0;
    }

    public void draw() {
        GLES20.glUseProgram(this.mProgram);
        GLES20.glUniformMatrix4fv(this.mu_MatrixHandle, 1, false, this.projectionMatrix, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTextureId);
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
        Log.d(Constants.TAG, "Calling glFinish blocking call");
        GLES20.glFinish();
        Log.d(Constants.TAG, "Finished glFinish");
    }

    public int loadTexture(Bitmap bitmap, int i2, int i7, int i8) {
        float f;
        float f5;
        if (this.mTextureId != 0) {
            deleteTexture();
        }
        Rect rect = new Rect();
        rect.left = 0;
        rect.top = 0;
        rect.right = bitmap.getWidth();
        rect.bottom = bitmap.getHeight();
        int loadTexture = OpenGlHelper.loadTexture(bitmap);
        this.mTextureId = loadTexture;
        if (loadTexture == 0) {
            Log.d(Constants.TAG, "not able to load new texture");
        }
        float width = ((float) rect.width()) / ((float) i7);
        float height = ((float) rect.height()) / ((float) i8);
        if (width >= height) {
            f5 = height / width;
            f = 1.0f;
        } else {
            f = width / height;
            f5 = 1.0f;
        }
        Log.d("framebuffer", "BG Image Framebuffer : " + this.mTextureId);
        Matrix.setIdentityM(this.projectionMatrix, 0);
        Matrix.scaleM(this.projectionMatrix, 0, f, f5, 1.0f);
        Matrix.setRotateM(this.projectionMatrix, 0, (float) (i2 * -1), 0.0f, 0.0f, 1.0f);
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glViewport(0, 0, i7, i8);
        return this.mTextureId;
    }

    public int prepare() {
        int createProgram = OpenGlHelper.createProgram(TEXTURE_VERTEX_SHADER_CODE, TEXTURE_FRAGMENT_SHADER_CODE);
        this.mProgram = createProgram;
        if (createProgram == 0) {
            return 0;
        }
        this.mu_MatrixHandle = GLES20.glGetUniformLocation(createProgram, U_MATRIX);
        this.ma_PositionHandle = GLES20.glGetAttribLocation(this.mProgram, A_POSITION);
        this.ma_TextureCoordinatesHandle = GLES20.glGetAttribLocation(this.mProgram, A_TEXTURE_COORDINATES);
        this.mu_TextureUnitHandle = GLES20.glGetUniformLocation(this.mProgram, U_TEXTURE_UNIT);
        return 1;
    }

    public void release() {
        deleteTexture();
        GLES20.glDeleteProgram(this.mProgram);
        this.mProgram = 0;
        this.mu_MatrixHandle = 0;
        this.ma_PositionHandle = 0;
        this.ma_TextureCoordinatesHandle = 0;
        this.mu_TextureUnitHandle = 0;
        this.mVerticesFloatBuffer = null;
    }

    public void setProjectionMatrixRotate(float f, float f5, float f8, float f10) {
        Matrix.rotateM(this.projectionMatrix, 0, f, f5, f8, f10);
    }

    public void setProjectionMatrixScale(float f, float f5) {
        Matrix.scaleM(this.projectionMatrix, 0, f, f5, 1.0f);
    }

    public void setProjectionMatrixTranslate(float f, float f5) {
        Matrix.translateM(this.projectionMatrix, 0, f, f5, 0.0f);
    }

    public int loadTexture(String str, int i2, int i7) {
        float f;
        float f5;
        if (this.mTextureId != 0) {
            deleteTexture();
        }
        Rect rect = new Rect();
        int loadTexture = OpenGlHelper.loadTexture(str, i2, i7, rect);
        this.mTextureId = loadTexture;
        if (loadTexture == 0) {
            Log.d(Constants.TAG, "not able to load new texture");
        }
        float width = ((float) rect.width()) / ((float) i2);
        float height = ((float) rect.height()) / ((float) i7);
        if (width >= height) {
            f = height / width;
            f5 = 1.0f;
        } else {
            f5 = width / height;
            f = 1.0f;
        }
        Matrix.setIdentityM(this.projectionMatrix, 0);
        Matrix.scaleM(this.projectionMatrix, 0, f5, f, 1.0f);
        return this.mTextureId;
    }

    public void draw(float f, float f5) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glUseProgram(this.mProgram);
        GLES20.glUniformMatrix4fv(this.mu_MatrixHandle, 1, false, this.projectionMatrix, 0);
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(3553, this.mTextureId);
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
        Log.d(Constants.TAG, "Calling glFinish blocking call");
        GLES20.glFinish();
        Log.d(Constants.TAG, "Finished glFinish");
    }
}
