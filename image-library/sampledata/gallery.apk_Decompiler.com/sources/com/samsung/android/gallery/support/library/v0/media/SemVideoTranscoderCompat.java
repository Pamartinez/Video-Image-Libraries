package com.samsung.android.gallery.support.library.v0.media;

import N2.j;
import Qb.c;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.TranscodingOptions;
import com.samsung.android.gallery.support.library.abstraction.VideoTranscoderCompat;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.media.codec.SemVideoTranscoder;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SemVideoTranscoderCompat implements VideoTranscoderCompat, SemVideoTranscoder.ProgressEventListener {
    protected final String TAG = tag();
    private int mEstimatedSize;
    private Consumer<Boolean> mOnCompleteListener;
    protected Consumer<Integer> mOnProgressListener;
    private Consumer<Boolean> mOnStartListener;
    protected TranscodingOptions mOptions;
    protected int mProgress;
    private ProgressTick mProgressTick;
    protected final SemVideoTranscoder mTranscoder = new SemVideoTranscoder();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProgressTick {
        private final File file;
        private Consumer<Integer> listener;
        private final int maxSize;
        private Timer timer;

        public ProgressTick(String str, int i2, Consumer<Integer> consumer) {
            this.file = new File(str);
            this.maxSize = i2;
            this.listener = consumer;
        }

        /* access modifiers changed from: private */
        public void onProgressUpdate() {
            if (this.timer != null) {
                long length = this.file.length() / ErrorCodeConvertor.ERROR_NOT_ALLOWED_CALLER;
                int i2 = (int) ((100 * length) / ((long) this.maxSize));
                if (i2 < 1) {
                    i2 *= 10;
                }
                this.listener.accept(Integer.valueOf(Math.min(i2, 100)));
                if (length >= ((long) this.maxSize)) {
                    stop();
                }
            }
        }

        public void start() {
            Log.d("ProgressTick", "start");
            Timer timer2 = new Timer("video-transcoding");
            this.timer = timer2;
            timer2.schedule(new TimerTask() {
                public void run() {
                    ProgressTick.this.onProgressUpdate();
                }
            }, 100, 200);
        }

        public void stop() {
            Timer timer2 = this.timer;
            if (timer2 != null) {
                timer2.cancel();
                this.timer = null;
            }
        }
    }

    public void encode() {
        String str = this.TAG;
        Log.d(str, "encode " + this.mOptions);
        try {
            startProgressUpdate();
            this.mTranscoder.encode();
        } catch (Error | Exception e) {
            Log.e(this.TAG, "encode failed", e);
            Consumer<Boolean> consumer = this.mOnCompleteListener;
            if (consumer != null) {
                consumer.accept(Boolean.FALSE);
            }
        }
    }

    public int getEstimatedSize() {
        return this.mEstimatedSize;
    }

    public void onCompleted() {
        Log.d(this.TAG, "onCompleted");
        Consumer<Boolean> consumer = this.mOnCompleteListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public void onProgressChanged(int i2) {
        Consumer<Integer> consumer = this.mOnProgressListener;
        if (consumer != null && i2 > this.mProgress) {
            this.mProgress = i2;
            consumer.accept(Integer.valueOf(i2));
        }
    }

    public void onStarted() {
        Log.d(this.TAG, "onStarted");
        Consumer<Boolean> consumer = this.mOnStartListener;
        if (consumer != null) {
            consumer.accept(Boolean.TRUE);
        }
    }

    public void setEncodingOptions(SemVideoTranscoder semVideoTranscoder, TranscodingOptions transcodingOptions) {
        try {
            Class<?> cls = semVideoTranscoder.getClass();
            Class cls2 = Integer.TYPE;
            Method method = Reflector.getMethod(cls, "setEncodingCodecs", cls2, cls2);
            if (method != null) {
                method.invoke(semVideoTranscoder, new Object[]{Integer.valueOf(transcodingOptions.videoCodec), Integer.valueOf(transcodingOptions.audioCodec)});
            }
            if (transcodingOptions.colorDepth > 0) {
                Method method2 = Reflector.getMethod(semVideoTranscoder.getClass(), "setOutputBitdepth", cls2);
                if (method2 != null) {
                    method2.invoke(semVideoTranscoder, new Object[]{Integer.valueOf(transcodingOptions.colorDepth)});
                }
            }
        } catch (Exception e) {
            j.D(e, new StringBuilder("fail setEncodingOptions="), this.TAG);
        }
    }

    public void setOnCompleteListener(Consumer<Boolean> consumer) {
        this.mOnCompleteListener = consumer;
    }

    public void setOnProgressListener(Consumer<Integer> consumer) {
        this.mOnProgressListener = consumer;
    }

    public void setOptions(TranscodingOptions transcodingOptions) {
        int i2;
        if (transcodingOptions.videoCodec == 0) {
            if (transcodingOptions.hdr || transcodingOptions.apv) {
                i2 = SemVideoTranscoder.CodecType.VIDEO_CODEC_H265;
            } else {
                i2 = SemVideoTranscoder.CodecType.VIDEO_CODEC_H264;
            }
            transcodingOptions.videoCodec = i2;
        }
        if (transcodingOptions.audioCodec == 0) {
            transcodingOptions.audioCodec = SemVideoTranscoder.CodecType.AUDIO_CODEC_AAC;
        }
        if (transcodingOptions.hdr && !transcodingOptions.apv) {
            transcodingOptions.colorDepth = 8;
        }
        this.mOptions = transcodingOptions;
        try {
            this.mTranscoder.initialize(transcodingOptions.output, transcodingOptions.width, transcodingOptions.height, transcodingOptions.input);
            setEncodingOptions(this.mTranscoder, transcodingOptions);
            setProgressListener(this.mTranscoder);
            this.mEstimatedSize = this.mTranscoder.getOutputFileSize();
        } catch (Exception e) {
            Log.e(this.TAG, "setOptions failed", e);
        }
    }

    public void setProgressListener(SemVideoTranscoder semVideoTranscoder) {
        semVideoTranscoder.setProgressEventListener(this);
    }

    public void startProgressUpdate() {
        ProgressTick progressTick = new ProgressTick(this.mOptions.output, this.mEstimatedSize, new c(5, this));
        this.mProgressTick = progressTick;
        progressTick.start();
    }

    public void stop() {
        Log.d(this.TAG, "stop");
        stopProgressUpdate();
        this.mTranscoder.stop();
    }

    public void stopProgressUpdate() {
        ProgressTick progressTick = this.mProgressTick;
        if (progressTick != null) {
            progressTick.stop();
            this.mProgressTick = null;
        }
    }

    public String tag() {
        return "SemVideoTranscoderCompat";
    }
}
