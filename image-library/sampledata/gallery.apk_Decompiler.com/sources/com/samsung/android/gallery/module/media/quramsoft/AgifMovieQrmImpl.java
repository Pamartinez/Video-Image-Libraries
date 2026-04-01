package com.samsung.android.gallery.module.media.quramsoft;

import A.a;
import android.graphics.Bitmap;
import com.quramsoft.agifDecoder.QuramAGIFDecoder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AgifMovieQrmImpl extends QuramAGIFDecoder implements AgifMovie {
    private final Object LOCK = new Object();
    private int mDuration;
    private int mNumOfFrame = 1;

    public AgifMovieQrmImpl(String str) {
        super(str);
        initialize();
    }

    private void initialize() {
        try {
            this.mNumOfFrame = getNumOfFrame();
            this.mDuration = getDelay();
        } catch (Exception e) {
            a.s(e, new StringBuilder("loadDuration failed e="), "AgifMovieQrmImpl");
            this.mNumOfFrame = 1;
            this.mDuration = 0;
        }
    }

    public int decodeFrame(Bitmap bitmap) {
        int decodeFrame;
        synchronized (this.LOCK) {
            decodeFrame = super.decodeFrame(bitmap);
        }
        return decodeFrame;
    }

    public boolean finish() {
        boolean finish;
        synchronized (this.LOCK) {
            finish = super.finish();
        }
        return finish;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getFrameCount() {
        return this.mNumOfFrame;
    }

    public int getNumOfFrame() {
        return Math.max(1, super.getNumOfFrame());
    }

    public String toString() {
        return "AGIF(QRM){" + getWidth() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getHeight() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getDuration() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getNumOfFrame() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getFrameInterval() + "}";
    }

    public AgifMovieQrmImpl(InputStream inputStream) {
        super(inputStream);
        initialize();
    }

    public AgifMovieQrmImpl(byte[] bArr, int i2) {
        super("byte-array");
        nativeInitByteArrayHandle(this, bArr, Math.min(i2, 52428800));
        initialize();
    }
}
