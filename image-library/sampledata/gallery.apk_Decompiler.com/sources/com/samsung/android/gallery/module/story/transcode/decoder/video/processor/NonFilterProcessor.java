package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager;
import com.samsung.android.sdk.scs.base.utils.Log;
import g6.g;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NonFilterProcessor implements Processor {
    private String TAG = "NonFilterProcessor";
    protected BufferQueue mBufferQueue;
    protected DecoderFrameManager mFrameManager;
    protected int mId;
    private Surface mOutputSurface;
    private long mPresentationTimeUs;
    private boolean mVideoDecodeDone;

    private void inputProcess() {
        outputProcess();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setInputBufferQueue$0(Boolean bool) {
        inputProcess();
    }

    private void notifyFrameDecoded(Buffer buffer) {
        long j2;
        MediaCodec codec = buffer.getCodec();
        int i2 = this.mId;
        int index = buffer.getIndex();
        int size = buffer.getSize();
        if (buffer.isEos()) {
            j2 = -4;
        } else {
            j2 = buffer.getTimeUs();
        }
        this.mFrameManager.notifyFrameDecoded(new DecodedFrame(codec, i2, index, size, j2));
    }

    private void outputProcess() {
        Buffer pop = this.mBufferQueue.pop();
        if (pop != null) {
            notifyFrameDecoded(pop);
        }
    }

    public void configure(int i2, MediaFormat mediaFormat, Surface surface) {
        String str = this.TAG + Log.TAG_SEPARATOR + i2;
        this.TAG = str;
        this.mId = i2;
        this.mOutputSurface = surface;
        com.samsung.android.gallery.support.utils.Log.d(str, "configure");
    }

    public Surface createInputSurface() {
        return this.mOutputSurface;
    }

    public long getPresentationTimeUs() {
        return this.mPresentationTimeUs;
    }

    public boolean isProcessDone() {
        return this.mVideoDecodeDone;
    }

    public void release() {
        com.samsung.android.gallery.support.utils.Log.d(this.TAG, "release");
        this.mVideoDecodeDone = true;
        this.mBufferQueue.enqueueListener((Consumer<Boolean>) null);
        this.mFrameManager.clearDecodedFrame(this.mId);
    }

    public void releaseAndRender(DecodedFrame decodedFrame) {
        MediaCodec mediaCodec;
        if (decodedFrame != null) {
            mediaCodec = decodedFrame.codec;
        } else {
            mediaCodec = null;
        }
        if (mediaCodec != null) {
            try {
                long j2 = decodedFrame.presentationTimeUS;
                boolean z = true;
                if (j2 == -4) {
                    this.mVideoDecodeDone = true;
                    com.samsung.android.gallery.support.utils.Log.d(this.TAG, "releaseAndRender processDone");
                    return;
                }
                int i2 = decodedFrame.bufferIndex;
                if (i2 != -1) {
                    this.mPresentationTimeUs = j2;
                    if (decodedFrame.size == 0) {
                        z = false;
                    }
                    mediaCodec.releaseOutputBuffer(i2, z);
                }
            } catch (IllegalStateException e) {
                String str = this.TAG;
                com.samsung.android.gallery.support.utils.Log.e((CharSequence) str, "fail to releaseAndRender, may be released" + e.getMessage(), Boolean.valueOf(this.mVideoDecodeDone));
                e.printStackTrace();
            }
        }
    }

    public void setDecodeFrameManager(DecoderFrameManager decoderFrameManager) {
        this.mFrameManager = decoderFrameManager;
    }

    public void setInputBufferQueue(BufferQueue bufferQueue) {
        this.mBufferQueue = bufferQueue;
        bufferQueue.enqueueListener(new g(21, this));
    }

    public void start() {
    }
}
