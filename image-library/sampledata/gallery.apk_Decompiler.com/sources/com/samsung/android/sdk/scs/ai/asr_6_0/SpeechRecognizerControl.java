package com.samsung.android.sdk.scs.ai.asr_6_0;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.asr_6_0.ExpiringData;
import com.samsung.android.sdk.scs.ai.asr_6_0.tasks.IdleTask;
import com.samsung.android.sdk.scs.ai.asr_6_0.tasks.SttRecognitionTask;
import com.samsung.android.sdk.scs.base.feature.Feature;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SpeechRecognizerControl {
    private static final Duration DEFAULT_DURATION;
    private static final String TAG = "SpeechRecognizerControl";
    private static final ExpiringData<RemoteServiceExecutor> sServiceExecutorPool;
    private final Context mContext;
    private final SpeechRecognitionListener mListener;
    private final RemoteServiceExecutor mServiceExecutor;
    private SttRecognitionTask mSttTask;

    static {
        Duration duration;
        if (ExpiringData.isDevelop) {
            duration = Duration.ofHours(1);
        } else {
            duration = Duration.ofDays(1);
        }
        DEFAULT_DURATION = duration;
        sServiceExecutorPool = new ExpiringData.Builder("Executor", new f(2)).setTimeout(duration).build();
    }

    public SpeechRecognizerControl(Context context, SpeechRecognitionListener speechRecognitionListener) {
        this.mContext = context;
        this.mListener = speechRecognitionListener;
        this.mServiceExecutor = sServiceExecutorPool.getOrCompute(new h(0, context));
    }

    private static Context getGlobalContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ RemoteServiceExecutor lambda$new$1(Context context) {
        return new RemoteServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ RemoteServiceExecutor lambda$static$0() {
        return new RemoteServiceExecutor(getGlobalContext());
    }

    public void cancel() {
        SttRecognitionTask sttRecognitionTask = this.mSttTask;
        if (sttRecognitionTask != null && !sttRecognitionTask.isComplete()) {
            this.mSttTask.cancel();
        }
    }

    public void init() {
        if (Feature.checkFeature(this.mContext, Feature.FEATURE_SPEECH_RECOGNITION) != 0) {
            this.mListener.onError(-1, "SpeechRecognizerService is UNAVAILABLE", new Bundle());
        } else if (!this.mServiceExecutor.isConnected()) {
            this.mServiceExecutor.execute(new IdleTask());
        }
    }

    public void release() {
        cancel();
        SttRecognitionTask sttRecognitionTask = this.mSttTask;
        if (sttRecognitionTask != null) {
            sttRecognitionTask.release();
        }
        this.mSttTask = null;
    }

    public void start(RecognitionConfig recognitionConfig, InputStream inputStream) {
        init();
        SttRecognitionTask sttRecognitionTask = new SttRecognitionTask(recognitionConfig, inputStream, this.mListener);
        this.mSttTask = sttRecognitionTask;
        this.mServiceExecutor.execute(sttRecognitionTask);
    }
}
