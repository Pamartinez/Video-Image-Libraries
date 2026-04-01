package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.opengl.GLES20;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RenderObject implements GLBase {
    private static final float[] sImageVerticesData = {-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f};
    private static final float[] sVideoVerticesData = {-1.0f, -1.0f, 0.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f};
    private FloatBuffer mVerticesFloatBuffer;

    public RenderObject(GLRenderTexture.Builder builder) {
        int length;
        float[] fArr;
        if (builder.useVideoVertices) {
            length = sVideoVerticesData.length;
        } else {
            length = sImageVerticesData.length;
        }
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        if (builder.useVideoVertices) {
            fArr = sVideoVerticesData;
        } else {
            fArr = sImageVerticesData;
        }
        FloatBuffer put = asFloatBuffer.put(fArr);
        this.mVerticesFloatBuffer = put;
        put.position(0);
    }

    public void draw(Shader shader, float[] fArr) {
        this.mVerticesFloatBuffer.position(0);
        GLES20.glVertexAttribPointer(shader.ma_PositionHandle, 2, 5126, false, 16, this.mVerticesFloatBuffer);
        GLES20.glEnableVertexAttribArray(shader.ma_PositionHandle);
        checkGLError("[VertexObject] glEnableVertexAttribArray ma_PositionHandle");
        this.mVerticesFloatBuffer.position(2);
        GLES20.glVertexAttribPointer(shader.ma_TextureCoordinatesHandle, 2, 5126, false, 16, this.mVerticesFloatBuffer);
        GLES20.glEnableVertexAttribArray(shader.ma_TextureCoordinatesHandle);
        checkGLError("[VertexObject] glEnableVertexAttribArray ma_TextureCoordinatesHandle");
        GLES20.glDrawArrays(5, 0, 4);
    }

    public void release() {
        this.mVerticesFloatBuffer = null;
    }
}
