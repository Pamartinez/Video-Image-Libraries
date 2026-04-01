package com.samsung.android.gallery.module.story.transcode.renderer.render;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.nio.Buffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Texture implements GLBase {
    public int mId;
    private final int mTexType;

    public Texture(int i2) {
        this.mTexType = i2;
    }

    private int bindTexture(int[] iArr) {
        GLES20.glBindTexture(3553, iArr[0]);
        return iArr[0];
    }

    private int generateGLTexture(int[] iArr, int i2) {
        GLES20.glGenTextures(1, iArr, 0);
        int i7 = iArr[0];
        if (i7 == 0) {
            Log.d("framebuffer", "loadTexture: error -1 ");
            return 0;
        }
        GLES20.glBindTexture(i2, i7);
        return iArr[0];
    }

    private int generateTextureIdFor2D(int i2, int i7) {
        int[] iArr = new int[1];
        if (generateGLTexture(iArr, 3553) == 0 || setGLTextureParameters(iArr, 3553) == 0) {
            return 0;
        }
        GLES20.glTexImage2D(3553, 0, 6408, i2, i7, 0, 6408, 5121, (Buffer) null);
        return bindTexture(iArr);
    }

    private int generateTextureIdForOES() {
        int[] iArr = new int[1];
        if (generateGLTexture(iArr, 36197) == 0) {
            return 0;
        }
        return setGLTextureParameters(iArr, 36197);
    }

    private int loadTexture(Bitmap bitmap) {
        int[] iArr = new int[1];
        if (generateGLTexture(iArr, 3553) == 0 || setGLTextureParameters(iArr, 3553) == 0) {
            return 0;
        }
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        return bindTexture(iArr);
    }

    private int setGLTextureParameters(int[] iArr, int i2) {
        GLES20.glTexParameteri(i2, 10241, 9729);
        GLES20.glTexParameteri(i2, 10240, 9729);
        GLES20.glTexParameterf(i2, 10242, 33071.0f);
        GLES20.glTexParameterf(i2, 10243, 33071.0f);
        return iArr[0];
    }

    public void bind(Shader shader) {
        GLES20.glActiveTexture(33984);
        GLES20.glBindTexture(this.mTexType, this.mId);
        GLES20.glUniform1i(shader.mu_TextureUnitHandle, 0);
    }

    public void generateIdOnly(int i2, int i7) {
        int i8;
        release();
        if (this.mTexType == 36197) {
            i8 = generateTextureIdForOES();
        } else {
            i8 = generateTextureIdFor2D(i2, i7);
        }
        this.mId = i8;
        if (i8 == 0) {
            Log.d("Texture", "not able to generate new texture id");
        }
    }

    public void load(Bitmap bitmap) {
        release();
        int loadTexture = loadTexture(bitmap);
        this.mId = loadTexture;
        if (loadTexture == 0) {
            Log.d("Texture", "not able to load new texture");
        }
    }

    public void release() {
        if (this.mId != 0) {
            GLES20.glBindTexture(this.mTexType, 0);
            GLES20.glDeleteTextures(1, new int[]{this.mId}, 0);
            this.mId = 0;
        }
    }
}
