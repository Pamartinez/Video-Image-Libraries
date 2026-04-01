package com.samsung.android.gallery.support.library.v11.media;

import N2.j;
import Ta.a;
import Ta.b;
import Ta.c;
import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureBgmCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.utils.Reflector;
import com.samsung.android.media.mediacapture.SemMediaCapture;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.common.ErrorCodeConvertor;
import java.io.FileDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem120MediaCaptureCompat extends MediaCaptureCompat {
    protected SemMediaCapture mMediaCapture = new SemMediaCapture();
    protected MediaCaptureBgmCompat mMediaCaptureBgmCompat = createBgmCompat();
    private Consumer<Integer> mProgressListener;
    private ProgressTick mProgressTick;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ProgressTick {
        private final Consumer<Integer> listener;
        private SemMediaCapture mediaCapture;
        private Timer timer;

        public ProgressTick(SemMediaCapture semMediaCapture, Consumer<Integer> consumer) {
            this.mediaCapture = semMediaCapture;
            this.listener = consumer;
        }

        /* access modifiers changed from: private */
        public void onProgressUpdate() {
            if (this.timer != null) {
                try {
                    int min = Math.min((int) (this.mediaCapture.getProgressForCapture() * 100.0f), 100);
                    this.listener.accept(Integer.valueOf(min));
                    if (min == 100) {
                        stop();
                        Log.d("ProgressTick", "stop self");
                    }
                } catch (Exception unused) {
                    Log.w("ProgressTick", "onProgressUpdate failed");
                }
            }
        }

        public void start() {
            Log.d("ProgressTick", "start");
            Timer timer2 = new Timer("media-capture");
            this.timer = timer2;
            timer2.schedule(new TimerTask() {
                public void run() {
                    ProgressTick.this.onProgressUpdate();
                }
            }, 100, 200);
        }

        public void stop() {
            try {
                Timer timer2 = this.timer;
                if (timer2 != null) {
                    timer2.cancel();
                    this.timer = null;
                }
                this.mediaCapture = null;
            } catch (Exception e) {
                j.D(e, new StringBuilder("stop e ="), "ProgressTick");
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setOnErrorListener$2(MediaCaptureCompat.OnErrorListener onErrorListener, SemMediaCapture semMediaCapture, int i2, int i7) {
        return onErrorListener.onError(this, i2, i7);
    }

    private void startProgressUpdate() {
        ProgressTick progressTick = new ProgressTick(this.mMediaCapture, this.mProgressListener);
        this.mProgressTick = progressTick;
        progressTick.start();
    }

    public MediaCaptureBgmCompat createBgmCompat() {
        return new MediaCaptureBgmCompat();
    }

    public Object getMediaCapture() {
        return this.mMediaCapture;
    }

    public void initBackgroundMusic(BgmOptions bgmOptions) {
        this.mMediaCaptureBgmCompat.initBackgroundMusic(this, bgmOptions);
        if (bgmOptions.mUseFadeOut) {
            setAudioVolumeFadeout(bgmOptions.mDuration - 2000, 2000);
        }
    }

    public void prepare() {
        try {
            this.mMediaCapture.prepare();
        } catch (Exception e) {
            j.C(e, new StringBuilder("prepare failed. e="), this.TAG);
        }
    }

    public void release() {
        try {
            ProgressTick progressTick = this.mProgressTick;
            if (progressTick != null) {
                progressTick.stop();
            }
            this.mMediaCapture.stopCapture();
            this.mMediaCapture.reset();
            this.mMediaCapture.release();
            this.mMediaCapture = null;
            this.mMediaCaptureBgmCompat.release();
            this.mMediaCaptureBgmCompat = null;
        } catch (Exception e) {
            j.C(e, new StringBuilder("release failed. e="), this.TAG);
        }
    }

    public void setAudioVolumeFadeout(int i2, int i7) {
        boolean z;
        Class<SemMediaCapture> cls = SemMediaCapture.class;
        try {
            Class cls2 = Integer.TYPE;
            Method method = Reflector.getMethod(cls, "setAudioVolumeFade", cls2, cls2, cls2, cls2, cls2);
            if (method != null) {
                method.invoke(this.mMediaCapture, new Object[]{2, -1, -1, Integer.valueOf(i2), Integer.valueOf(i7)});
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("setAudioVolumeFade {");
            if (method != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(i2);
            sb2.append("}");
            Log.d(str, sb2.toString());
        } catch (Exception e) {
            j.C(e, new StringBuilder("setAudioVolumeFade failed. e="), this.TAG);
        }
    }

    public boolean setDataSource(FileDescriptor fileDescriptor) {
        try {
            this.mMediaCapture.setDataSource(fileDescriptor);
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("setDataSource failed. e="), this.TAG);
            return false;
        }
    }

    public void setDynamicViewFormat(int i2) {
        try {
            this.mMediaCapture.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_MSISDN, 2);
            this.mMediaCapture.setParameter(ErrorCodeConvertor.TEMP_AGENT_INVALID_PARAMETER, 1);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setParamAndFormat failed. e="), this.TAG);
        }
    }

    public void setDynamicViewingConfigurations(int i2, int i7, float f) {
        if (this.mMediaCapture != null) {
            ArrayList arrayList = new ArrayList();
            SemMediaCapture semMediaCapture = this.mMediaCapture;
            Objects.requireNonNull(semMediaCapture);
            arrayList.add(new SemMediaCapture.DynamicViewingConfiguration(semMediaCapture, i2, i7, f));
            this.mMediaCapture.setDynamicViewingConfigurations(arrayList);
        }
    }

    public void setMediaCapturePlaybacks(List<MediaPlayback> list) {
        try {
            ArrayList arrayList = new ArrayList();
            for (MediaPlayback next : list) {
                SemMediaCapture semMediaCapture = this.mMediaCapture;
                Objects.requireNonNull(semMediaCapture);
                arrayList.add(new SemMediaCapture.DynamicViewingConfiguration(semMediaCapture, next.startMs, next.endMs, next.speed));
            }
            this.mMediaCapture.setDynamicViewingConfigurations(arrayList);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setSemMediaCapturePlaybacks failed. e="), this.TAG);
        }
    }

    public void setOnCompleteListener(Consumer<Boolean> consumer) {
        try {
            this.mMediaCapture.setOnRecordingCompletionListener(new c(consumer));
        } catch (Exception e) {
            j.C(e, new StringBuilder("setOnRecordingCompletionListener failed. e="), this.TAG);
        }
    }

    public void setOnErrorListener(MediaCaptureCompat.OnErrorListener onErrorListener) {
        try {
            this.mMediaCapture.setOnErrorListener(new b(this, onErrorListener));
        } catch (Exception e) {
            j.C(e, new StringBuilder("setOnErrorListener failed. e="), this.TAG);
        }
    }

    public void setOnPreparedListener(Consumer<Boolean> consumer) {
        try {
            this.mMediaCapture.setOnPreparedListener(new a(consumer));
        } catch (Exception e) {
            j.C(e, new StringBuilder("setOnPreparedListener failed. e="), this.TAG);
        }
    }

    public void setOnProgressListener(Consumer<Integer> consumer) {
        this.mProgressListener = consumer;
    }

    public boolean setOutputFile(FileDescriptor fileDescriptor) {
        try {
            this.mMediaCapture.setOutputFile(fileDescriptor);
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("setOutputFile failed. e="), this.TAG);
            return false;
        }
    }

    public void setParameter(int i2, int i7) {
        super.setParameter(i2, i7);
        SemMediaCapture semMediaCapture = this.mMediaCapture;
        if (semMediaCapture != null) {
            semMediaCapture.setParameter(i2, i7);
        }
    }

    public void setStartEndTime(int i2, int i7) {
        try {
            this.mMediaCapture.setStartEndTime(i2, i7);
        } catch (Exception e) {
            j.C(e, new StringBuilder("setStartEndTime failed. e="), this.TAG);
        }
    }

    public boolean startCapture() {
        try {
            this.mMediaCapture.startCapture();
            startProgressUpdate();
            return true;
        } catch (Exception e) {
            j.C(e, new StringBuilder("startCapture failed. e="), this.TAG);
            return false;
        }
    }

    public boolean support() {
        return true;
    }
}
