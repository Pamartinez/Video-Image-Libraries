package androidx.media3.effect;

import android.content.Context;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.util.Assertions;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ReplayableFrameCacheGlShaderProgram extends FrameCacheGlShaderProgram {
    private int cacheSize;
    private final TimedGlTextureInfo[] cachedFrames = new TimedGlTextureInfo[2];

    public ReplayableFrameCacheGlShaderProgram(Context context, boolean z) {
        super(context, 2, z);
    }

    public void onFrameRendered(long j2) {
        int i2 = this.cacheSize;
        if (i2 >= 2) {
            TimedGlTextureInfo[] timedGlTextureInfoArr = this.cachedFrames;
            TimedGlTextureInfo timedGlTextureInfo = timedGlTextureInfoArr[1];
            if (j2 >= timedGlTextureInfo.presentationTimeUs) {
                TimedGlTextureInfo timedGlTextureInfo2 = timedGlTextureInfoArr[0];
                timedGlTextureInfoArr[0] = timedGlTextureInfo;
                this.cacheSize = i2 - 1;
                super.releaseOutputFrame(timedGlTextureInfo2.glTextureInfo);
            }
        }
    }

    public void queueInputFrame(GlObjectsProvider glObjectsProvider, GlTextureInfo glTextureInfo, long j2) {
        boolean z;
        if (this.cacheSize < 2) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        super.queueInputFrame(glObjectsProvider, glTextureInfo, j2);
        TimedGlTextureInfo[] timedGlTextureInfoArr = this.cachedFrames;
        int i2 = this.cacheSize;
        this.cacheSize = i2 + 1;
        timedGlTextureInfoArr[i2] = new TimedGlTextureInfo((GlTextureInfo) Assertions.checkNotNull(this.outputTexturePool.getMostRecentlyUsedTexture()), j2);
    }

    public void signalEndOfCurrentInputStream() {
        for (int i2 = 0; i2 < this.cacheSize; i2++) {
            super.releaseOutputFrame(this.cachedFrames[i2].glTextureInfo);
        }
        this.cacheSize = 0;
        super.signalEndOfCurrentInputStream();
    }

    public void releaseOutputFrame(GlTextureInfo glTextureInfo) {
    }
}
