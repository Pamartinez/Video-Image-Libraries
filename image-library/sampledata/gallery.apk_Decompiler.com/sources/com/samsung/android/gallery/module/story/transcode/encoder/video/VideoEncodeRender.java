package com.samsung.android.gallery.module.story.transcode.encoder.video;

import android.opengl.GLES20;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLRenderTexture;
import com.samsung.android.gallery.module.story.transcode.renderer.surface.OutputSurface;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoEncodeRender {
    private final int[] mFrameBufferID = new int[1];
    private OutputSurface mOutputSurface;
    protected GLRenderTexture mRenderTexture;

    public VideoEncodeRender(int i2, int i7) {
        GLRenderTexture build = new GLRenderTexture.Builder(3553, i2, i7).useVideoVertices().build();
        this.mRenderTexture = build;
        this.mOutputSurface = new OutputSurface(build.getTextureId());
        generateFrameBuffer();
    }

    private void bindFrameBuffer(int i2) {
        GLES20.glBindFramebuffer(36160, i2);
    }

    private void bindFrameBufferToTexture() {
        bindFrameBuffer(this.mFrameBufferID[0]);
        GLES20.glBindTexture(3553, this.mRenderTexture.getTextureId());
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.mRenderTexture.getTextureId(), 0);
    }

    private void generateFrameBuffer() {
        GLES20.glGenFramebuffers(1, this.mFrameBufferID, 0);
    }

    public void draw() {
        bindFrameBuffer(0);
        this.mRenderTexture.drawOnSurface(this.mOutputSurface.getSurfaceTexture());
    }

    public void prepareDraw() {
        bindFrameBufferToTexture();
    }

    public void release() {
        OutputSurface outputSurface = this.mOutputSurface;
        if (outputSurface != null) {
            try {
                outputSurface.release();
                this.mOutputSurface = null;
            } catch (Exception e) {
                Log.e("VideoEncodeRender", "Exception in releasing outputSurface.");
                e.printStackTrace();
            }
        }
        GLRenderTexture gLRenderTexture = this.mRenderTexture;
        if (gLRenderTexture != null) {
            gLRenderTexture.release();
            this.mRenderTexture = null;
        }
    }
}
