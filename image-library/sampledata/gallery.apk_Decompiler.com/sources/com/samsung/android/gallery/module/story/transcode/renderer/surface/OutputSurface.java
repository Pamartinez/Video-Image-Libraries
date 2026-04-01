package com.samsung.android.gallery.module.story.transcode.renderer.surface;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.renderer.render.GLBase;
import com.samsung.android.gallery.module.story.transcode.util.VideoHandler;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OutputSurface implements SurfaceTexture.OnFrameAvailableListener, GLBase {
    private boolean mFrameAvailable;
    private final Object mFrameSyncObject = new Object();
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;

    public OutputSurface(int i2) {
        SurfaceTexture surfaceTexture = new SurfaceTexture(i2);
        this.mSurfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(this, VideoHandler.getHandler());
        this.mSurface = new Surface(this.mSurfaceTexture);
    }

    public boolean checkForNewImage(int i2) {
        synchronized (this.mFrameSyncObject) {
            do {
                try {
                    if (!this.mFrameAvailable) {
                        this.mFrameSyncObject.wait((long) i2);
                    } else {
                        this.mFrameAvailable = false;
                        checkGLError("before updateTexImage");
                        this.mSurfaceTexture.updateTexImage();
                        return true;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            } while (this.mFrameAvailable);
            return false;
        }
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.mSurfaceTexture;
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        synchronized (this.mFrameSyncObject) {
            try {
                if (!this.mFrameAvailable) {
                    this.mFrameAvailable = true;
                    this.mFrameSyncObject.notifyAll();
                } else {
                    throw new RuntimeException("mFrameAvailable already set, frame could be dropped");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void release() {
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
        }
        this.mSurface = null;
        this.mSurfaceTexture = null;
    }
}
