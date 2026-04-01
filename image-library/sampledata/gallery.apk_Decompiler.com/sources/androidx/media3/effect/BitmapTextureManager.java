package androidx.media3.effect;

import Ec.a;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.media3.common.Format;
import androidx.media3.common.FrameInfo;
import androidx.media3.common.GlObjectsProvider;
import androidx.media3.common.GlTextureInfo;
import androidx.media3.common.VideoFrameProcessingException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.TimestampIterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class BitmapTextureManager extends TextureManager {
    private boolean currentInputStreamEnded;
    private GlTextureInfo currentSdrGlTextureInfo;
    private int downstreamShaderProgramCapacity;
    private final GlObjectsProvider glObjectsProvider;
    private boolean isNextFrameInTexture;
    private final Queue<BitmapFrameSequenceInfo> pendingBitmaps = new LinkedBlockingQueue();
    private RepeatingGainmapShaderProgram repeatingGainmapShaderProgram;
    private final boolean signalRepeatingSequence;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BitmapFrameSequenceInfo {
        public final Bitmap bitmap;
        /* access modifiers changed from: private */
        public final FrameInfo frameInfo;
        /* access modifiers changed from: private */
        public final TimestampIterator inStreamOffsetsUs;

        public BitmapFrameSequenceInfo(Bitmap bitmap2, FrameInfo frameInfo2, TimestampIterator timestampIterator) {
            this.bitmap = bitmap2;
            this.frameInfo = frameInfo2;
            this.inStreamOffsetsUs = timestampIterator;
        }
    }

    public BitmapTextureManager(GlObjectsProvider glObjectsProvider2, VideoFrameProcessingTaskExecutor videoFrameProcessingTaskExecutor, boolean z) {
        super(videoFrameProcessingTaskExecutor);
        this.glObjectsProvider = glObjectsProvider2;
        this.signalRepeatingSequence = z;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onReadyToAcceptInputFrame$0() {
        this.downstreamShaderProgramCapacity++;
        maybeQueueToShaderProgram();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$queueInputBitmap$1(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        setupBitmap(bitmap, frameInfo, timestampIterator);
        this.currentInputStreamEnded = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$3() {
        GlTextureInfo glTextureInfo = this.currentSdrGlTextureInfo;
        if (glTextureInfo != null) {
            glTextureInfo.release();
        }
        this.pendingBitmaps.clear();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$signalEndOfCurrentInputStream$2() {
        if (this.pendingBitmaps.isEmpty()) {
            ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalEndOfCurrentInputStream();
            DebugTraceUtil.logEvent("BitmapTextureManager", "SignalEOS", Long.MIN_VALUE);
            return;
        }
        this.currentInputStreamEnded = true;
    }

    private void maybeQueueToShaderProgram() {
        if (!this.pendingBitmaps.isEmpty() && this.downstreamShaderProgramCapacity != 0) {
            BitmapFrameSequenceInfo element = this.pendingBitmaps.element();
            FrameInfo access$000 = element.frameInfo;
            TimestampIterator access$100 = element.inStreamOffsetsUs;
            Assertions.checkState(element.inStreamOffsetsUs.hasNext());
            long next = access$100.next() + element.frameInfo.offsetToAddUs;
            if (!this.isNextFrameInTexture) {
                this.isNextFrameInTexture = true;
                updateCurrentGlTextureInfo(access$000, element.bitmap);
            }
            this.downstreamShaderProgramCapacity--;
            ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).queueInputFrame(this.glObjectsProvider, (GlTextureInfo) Assertions.checkNotNull(this.currentSdrGlTextureInfo), next);
            DebugTraceUtil.logEvent("VideoFrameProcessor", "QueueBitmap", next, "%dx%d", Integer.valueOf(access$000.format.width), Integer.valueOf(access$000.format.height));
            if (!element.inStreamOffsetsUs.hasNext()) {
                this.isNextFrameInTexture = false;
                this.pendingBitmaps.remove().bitmap.recycle();
                if (this.pendingBitmaps.isEmpty() && this.currentInputStreamEnded) {
                    ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalEndOfCurrentInputStream();
                    DebugTraceUtil.logEvent("BitmapTextureManager", "SignalEOS", Long.MIN_VALUE);
                    this.currentInputStreamEnded = false;
                }
            }
        }
    }

    private void setupBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        Assertions.checkArgument(timestampIterator.hasNext(), "Bitmap queued but no timestamps provided.");
        this.pendingBitmaps.add(new BitmapFrameSequenceInfo(bitmap, frameInfo, timestampIterator));
        maybeQueueToShaderProgram();
    }

    private void updateCurrentGlTextureInfo(FrameInfo frameInfo, Bitmap bitmap) {
        try {
            GlTextureInfo glTextureInfo = this.currentSdrGlTextureInfo;
            if (glTextureInfo != null) {
                glTextureInfo.release();
            }
            int createTexture = GlUtil.createTexture(bitmap);
            Format format = frameInfo.format;
            this.currentSdrGlTextureInfo = new GlTextureInfo(createTexture, -1, -1, format.width, format.height);
            if (Build.VERSION.SDK_INT >= 34 && bitmap.hasGainmap()) {
                ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).setGainmap(a.g(Assertions.checkNotNull(bitmap.getGainmap())));
            }
            if (this.signalRepeatingSequence) {
                ((RepeatingGainmapShaderProgram) Assertions.checkNotNull(this.repeatingGainmapShaderProgram)).signalNewRepeatingFrameSequence();
            }
        } catch (GlUtil.GlException e) {
            throw VideoFrameProcessingException.from(e);
        }
    }

    public int getPendingFrameCount() {
        return 0;
    }

    public void onReadyToAcceptInputFrame() {
        this.videoFrameProcessingTaskExecutor.submit(new a(this, 2));
    }

    public void queueInputBitmap(Bitmap bitmap, FrameInfo frameInfo, TimestampIterator timestampIterator) {
        this.videoFrameProcessingTaskExecutor.submit(new b(this, bitmap, frameInfo, timestampIterator));
    }

    public void release() {
        this.videoFrameProcessingTaskExecutor.submit(new a(this, 1));
    }

    public void setSamplingGlShaderProgram(GlShaderProgram glShaderProgram) {
        Assertions.checkState(glShaderProgram instanceof RepeatingGainmapShaderProgram);
        this.downstreamShaderProgramCapacity = 0;
        this.repeatingGainmapShaderProgram = (RepeatingGainmapShaderProgram) glShaderProgram;
    }

    public void signalEndOfCurrentInputStream() {
        this.videoFrameProcessingTaskExecutor.submit(new a(this, 0));
    }
}
