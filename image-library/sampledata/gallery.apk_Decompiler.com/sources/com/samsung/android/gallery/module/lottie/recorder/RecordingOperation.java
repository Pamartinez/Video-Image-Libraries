package com.samsung.android.gallery.module.lottie.recorder;

import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;
import x0.w;
import x7.l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecordingOperation {
    private final BgmMixer bgmMixer;
    private int currentFrameIdx = 0;
    private final FrameCreator frameCreator;
    private final Consumer<Boolean> listener;
    Function<Integer, Boolean> mFrameDropper;
    AtomicBoolean mInterrupted = new AtomicBoolean(false);
    Consumer<Float> progressListener;
    private final Recorder recorder;
    private int totalFrame;

    public RecordingOperation(w wVar, File file, Consumer<Boolean> consumer) {
        this.recorder = new Recorder(file, (int) wVar.d.n, (float) wVar.getIntrinsicWidth(), (float) wVar.getIntrinsicHeight());
        FrameCreator frameCreator2 = new FrameCreator(wVar);
        this.frameCreator = frameCreator2;
        this.listener = consumer;
        this.bgmMixer = new BgmMixer(file, new l(14, this));
        this.totalFrame = frameCreator2.getFrameCount();
    }

    private boolean isRecording() {
        return !this.frameCreator.hasEnded();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        this.listener.accept(Boolean.valueOf(this.mInterrupted.get()));
    }

    public void cancel() {
        this.mInterrupted.set(true);
        this.bgmMixer.cancel();
    }

    public void setFrameDropper(Function<Integer, Boolean> function) {
        this.mFrameDropper = function;
    }

    public void start() {
        w wVar = null;
        while (isRecording() && !this.mInterrupted.get()) {
            int i2 = this.currentFrameIdx;
            if (i2 % 100 == 0) {
                float f = (((float) i2) / ((float) this.totalFrame)) * 100.0f;
                Log.i("RecapRecording", this.currentFrameIdx + "/" + this.totalFrame, String.format("%.1f %%", new Object[]{Float.valueOf(f)}));
                Consumer<Float> consumer = this.progressListener;
                if (consumer != null) {
                    consumer.accept(Float.valueOf(f));
                }
            }
            this.currentFrameIdx++;
            w generateFrame = this.frameCreator.generateFrame();
            if (this.mFrameDropper.apply(Integer.valueOf(this.currentFrameIdx)).booleanValue()) {
                Log.w("RecapRecording", "Drop Frame #" + this.currentFrameIdx);
            } else {
                if (!(wVar == null || wVar.getIntrinsicWidth() == generateFrame.getIntrinsicWidth())) {
                    this.recorder.matrix = null;
                }
                try {
                    this.recorder.nextFrame(generateFrame, this.currentFrameIdx);
                    wVar = generateFrame;
                } catch (Exception e) {
                    String message = e.getMessage();
                    if (message != null && message.contains("recycled")) {
                        new InternalException("RecapRecording", "fail draw : " + this.currentFrameIdx).post();
                    }
                    throw new RuntimeException("fail draw : " + this.currentFrameIdx, e);
                }
            }
        }
        Consumer<Float> consumer2 = this.progressListener;
        if (consumer2 != null) {
            consumer2.accept(Float.valueOf(99.9f));
        }
        this.recorder.end();
        if (!this.mInterrupted.get()) {
            this.bgmMixer.mix();
        }
    }

    public void setProgressListener(Consumer<Float> consumer) {
    }
}
