package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.opengl.GLES20;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface GLBase {
    int checkGLError(String str) {
        int glGetError;
        do {
            glGetError = GLES20.glGetError();
        } while (glGetError != 0);
        return glGetError;
    }
}
