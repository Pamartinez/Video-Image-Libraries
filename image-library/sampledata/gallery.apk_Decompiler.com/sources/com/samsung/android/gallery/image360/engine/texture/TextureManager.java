package com.samsung.android.gallery.image360.engine.texture;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.IOnGLIdleListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TextureManager {
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    private int mBitmapHeight;
    private final BitmapUploader mBitmapUploader = new BitmapUploader(this, 0);
    private int mBitmapWidth;
    /* access modifiers changed from: private */
    public StatusHandler mStatusHandler = null;
    /* access modifiers changed from: private */
    public final int[] mTextureDataHandle = new int[1];

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class BitmapUploader implements IOnGLIdleListener {
        public /* synthetic */ BitmapUploader(TextureManager textureManager, int i2) {
            this();
        }

        public void onGLIdle() {
            if (TextureManager.this.mBitmap == null || TextureManager.this.mBitmap.isRecycled()) {
                Log.e("TextureManager", "onGLIdle: null bitmap.");
                if (TextureManager.this.mStatusHandler != null) {
                    TextureManager.this.mStatusHandler.onError(1);
                    return;
                }
                return;
            }
            if (TextureManager.this.mTextureDataHandle[0] == 0) {
                GLES20.glGenTextures(1, TextureManager.this.mTextureDataHandle, 0);
            }
            if (TextureManager.this.mTextureDataHandle[0] != 0) {
                GLES20.glBindTexture(3553, TextureManager.this.mTextureDataHandle[0]);
                GLES20.glTexParameteri(3553, 10241, 9728);
                GLES20.glTexParameteri(3553, 10240, 9728);
                try {
                    GLUtils.texImage2D(3553, 0, TextureManager.this.mBitmap, 0);
                } catch (IllegalArgumentException | NullPointerException e) {
                    Log.e("TextureManager", "Error loading Texture: texImage2D call failed with exception\n" + e.getMessage());
                    if (TextureManager.this.mStatusHandler != null) {
                        TextureManager.this.mStatusHandler.onError(2);
                    }
                }
            } else {
                Log.e("TextureManager", "Error loading Texture. glGenTextures call failed");
                if (TextureManager.this.mStatusHandler != null) {
                    TextureManager.this.mStatusHandler.onError(2);
                }
            }
        }

        private BitmapUploader() {
        }
    }

    public void clearGlTexture() {
        GLES20.glDeleteTextures(1, this.mTextureDataHandle, 0);
        this.mTextureDataHandle[0] = 0;
    }

    public int getBitmapHeight() {
        return this.mBitmapHeight;
    }

    public int getBitmapWidth() {
        return this.mBitmapWidth;
    }

    public int[] getTextureDataHandle() {
        return this.mTextureDataHandle;
    }

    public IOnGLIdleListener requestGlUpload(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = this.mBitmap.getHeight();
        return this.mBitmapUploader;
    }

    public void setStatusHandler(StatusHandler statusHandler) {
        this.mStatusHandler = statusHandler;
    }
}
