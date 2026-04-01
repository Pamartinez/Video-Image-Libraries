package com.samsung.android.gallery.module.story.transcode.decoder.video.processor;

import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecodedFrame;
import com.samsung.android.gallery.module.story.transcode.decoder.video.decodeframe.DecoderFrameManager;
import com.samsung.android.gallery.module.story.transcode.decoder.video.postprocessor.PostProcessor;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import g6.g;
import java.util.function.Consumer;
import la.C0699a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilterProcessor implements Processor {
    private String TAG = "FilterProcessor";
    private boolean mDecodeDone;
    protected final int mFilterIntensity;
    protected final String mFilterPath;
    protected DecoderFrameManager mFrameManager;
    private final Handler mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("FilterProcessor@" + hashCode()));
    private int mId;
    protected BufferQueue mInputBufferQueue;
    private Surface mInputSurface;
    private boolean mInterrupt;
    protected final PostProcessor mPostProcessor = PostProcessor.create();
    private long mPresentationTimeUs;

    public FilterProcessor(String str, int i2) {
        this.mFilterPath = str;
        this.mFilterIntensity = i2;
    }

    private boolean alive() {
        if (this.mDecodeDone || this.mInputBufferQueue.isEmpty() || this.mInterrupt) {
            return false;
        }
        return true;
    }

    private boolean checkEndOfStream() {
        Buffer peek = this.mInputBufferQueue.peek();
        if (peek == null || !peek.isEos()) {
            return false;
        }
        this.mFrameManager.notifyFrameDecoded(new DecodedFrame(this.mId, 0, 0, -4));
        this.mPostProcessor.signalEndOfInputStream();
        return true;
    }

    private void inputProcess() {
        this.mHandler.post(new C0699a(this, 0));
    }

    /* access modifiers changed from: private */
    public void inputProcessInternal() {
        Buffer peek = this.mInputBufferQueue.peek();
        if (!this.mDecodeDone && peek != null) {
            try {
                peek.releaseAndRender();
                outputProcess();
            } catch (IllegalStateException e) {
                String str = this.TAG;
                Log.e((CharSequence) str, "fail to releaseAndRender, may be released =" + e.getMessage(), Boolean.valueOf(this.mDecodeDone));
                e.printStackTrace();
                this.mInputBufferQueue.pop();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setInputBufferQueue$0(Boolean bool) {
        if (this.mInputBufferQueue.size() <= 1) {
            inputProcess();
        }
    }

    private void outputProcess() {
        this.mHandler.post(new C0699a(this, 1));
    }

    /* access modifiers changed from: private */
    public void outputProcessInternal() {
        PostProcessor.BufferInfoWrapper createBufferInfo = this.mPostProcessor.createBufferInfo();
        if (createBufferInfo == null) {
            Log.e(this.TAG, "outputProcessInternal failed. null buffer info");
        } else if (this.mPostProcessor.dequeueOutputBuffer(createBufferInfo) != null) {
            long presentationTimeUs = createBufferInfo.getPresentationTimeUs();
            if (createBufferInfo.isEndOfStream()) {
                Log.d(this.TAG, "end of stream");
                this.mPostProcessor.signalEndOfInputStream();
                presentationTimeUs = -4;
            }
            this.mFrameManager.notifyFrameDecoded(new DecodedFrame(this.mId, createBufferInfo.getIndex(), 1, presentationTimeUs));
            this.mInputBufferQueue.pop();
            if (checkEndOfStream()) {
                Log.d(this.TAG, "reach end of stream");
            } else if (alive()) {
                this.mHandler.post(new C0699a(this, 0));
            }
        } else if (alive()) {
            this.mHandler.postDelayed(new C0699a(this, 1), 20);
        }
    }

    public void configure(int i2, MediaFormat mediaFormat, Surface surface) {
        this.TAG += com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + i2;
        this.mId = i2;
        this.mInputSurface = this.mPostProcessor.createInputSurface();
        this.mPostProcessor.setFilter(this.mFilterPath, this.mFilterIntensity);
        this.mPostProcessor.configure(mediaFormat, surface);
        Log.d(this.TAG, "configure");
    }

    public Surface createInputSurface() {
        return this.mInputSurface;
    }

    public long getPresentationTimeUs() {
        return this.mPresentationTimeUs;
    }

    public void interrupt() {
        this.mInterrupt = true;
    }

    public boolean isProcessDone() {
        return this.mDecodeDone;
    }

    public void release() {
        Log.d(this.TAG, "release");
        this.mDecodeDone = true;
        this.mInputBufferQueue.enqueueListener((Consumer<Boolean>) null);
        this.mFrameManager.clearDecodedFrame(this.mId);
        try {
            this.mPostProcessor.signalEndOfInputStream();
            this.mPostProcessor.release();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "PostProcessor release failed", e.getMessage());
        }
        this.mHandler.getLooper().quitSafely();
    }

    public void releaseAndRender(DecodedFrame decodedFrame) {
        int i2;
        if (decodedFrame != null && decodedFrame.presentationTimeUS == -4) {
            Log.d(this.TAG, "releaseAndRender eos", Integer.valueOf(decodedFrame.bufferIndex), Long.valueOf(decodedFrame.presentationTimeUS));
            this.mDecodeDone = true;
        } else if (decodedFrame != null && (i2 = decodedFrame.bufferIndex) >= 0) {
            long j2 = decodedFrame.presentationTimeUS;
            this.mPresentationTimeUs = j2;
            this.mPostProcessor.renderAndReleaseOutputBuffer(i2, j2, j2 * 1000);
        }
    }

    public void setDecodeFrameManager(DecoderFrameManager decoderFrameManager) {
        this.mFrameManager = decoderFrameManager;
    }

    public void setInputBufferQueue(BufferQueue bufferQueue) {
        this.mInputBufferQueue = bufferQueue;
        bufferQueue.enqueueListener(new g(20, this));
    }

    public boolean support() {
        PostProcessor postProcessor = this.mPostProcessor;
        if (postProcessor == null || !postProcessor.support()) {
            return false;
        }
        return true;
    }

    public void start() {
    }
}
