package com.samsung.android.gallery.image360.engine.view;

import android.graphics.Color;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.texture.StatusHandler;
import com.samsung.android.gallery.image360.engine.util.ShaderHelper;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class BackgroundView {
    private final float[] mColor = new float[4];
    private int mColorHandle;
    private final float[] mMVPMatrix = new float[16];
    private int mMVPMatrixHandle;
    private final float[] mModelMatrix = new float[16];
    private int mPositionHandle;
    private final FloatBuffer mPositions;
    private int mProgramHandle;
    private final float[] mProjectionMatrix = new float[16];
    private StatusHandler mStatusHandler;
    private final float[] mViewMatrix = new float[16];

    public BackgroundView() {
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(48).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.mPositions = asFloatBuffer;
        asFloatBuffer.put(new float[]{-1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f}).position(0);
    }

    public void draw() {
        GLES20.glDepthFunc(512);
        GLES20.glUseProgram(this.mProgramHandle);
        Matrix.setLookAtM(this.mViewMatrix, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -5.0f, 0.0f, 1.0f, 0.0f);
        Matrix.frustumM(this.mProjectionMatrix, 0, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, 11.0f);
        Matrix.setIdentityM(this.mModelMatrix, 0);
        Matrix.translateM(this.mModelMatrix, 0, 0.0f, 0.0f, -1.0f);
        this.mPositions.position(0);
        GLES20.glEnableVertexAttribArray(this.mPositionHandle);
        GLES20.glVertexAttribPointer(this.mPositionHandle, 3, 5126, false, 0, this.mPositions);
        synchronized (this) {
            GLES20.glUniform4fv(this.mColorHandle, 1, this.mColor, 0);
        }
        Matrix.multiplyMM(this.mMVPMatrix, 0, this.mViewMatrix, 0, this.mModelMatrix, 0);
        float[] fArr = this.mMVPMatrix;
        Matrix.multiplyMM(fArr, 0, this.mProjectionMatrix, 0, fArr, 0);
        GLES20.glUniformMatrix4fv(this.mMVPMatrixHandle, 1, false, this.mMVPMatrix, 0);
        GLES20.glDrawArrays(5, 0, 4);
    }

    public void setBackgroundColor(int i2) {
        synchronized (this) {
            this.mColor[0] = ((float) Color.red(i2)) / 255.0f;
            this.mColor[1] = ((float) Color.green(i2)) / 255.0f;
            this.mColor[2] = ((float) Color.blue(i2)) / 255.0f;
            this.mColor[3] = ((float) Color.alpha(i2)) / 255.0f;
        }
    }

    public void setStatusHandler(StatusHandler statusHandler) {
        this.mStatusHandler = statusHandler;
    }

    public void updateGlAttributes() {
        if (this.mProgramHandle == 0) {
            int loadShader = ShaderHelper.loadShader(35633, "uniform mat4 u_MVPMatrix;\nattribute vec4 a_Position;                \nvoid main() {                             \n  gl_Position = u_MVPMatrix * a_Position; \n}                                         \n");
            int loadShader2 = ShaderHelper.loadShader(35632, "precision mediump float;\nuniform vec4 v_Color;      \nvoid main() {              \n  gl_FragColor = v_Color;  \n}                          \n");
            if (!(loadShader == 0 || loadShader2 == 0)) {
                this.mProgramHandle = ShaderHelper.createProgram(loadShader, loadShader2, new String[]{"a_Position"});
            }
            int i2 = this.mProgramHandle;
            if (i2 == 0) {
                Log.e("BackgroundView", "updateGlAttributes: invalid program handle");
                StatusHandler statusHandler = this.mStatusHandler;
                if (statusHandler != null) {
                    statusHandler.onError(2);
                    return;
                }
                return;
            }
            this.mMVPMatrixHandle = GLES20.glGetUniformLocation(i2, "u_MVPMatrix");
            this.mColorHandle = GLES20.glGetUniformLocation(this.mProgramHandle, "v_Color");
            this.mPositionHandle = GLES20.glGetAttribLocation(this.mProgramHandle, "a_Position");
        }
    }
}
