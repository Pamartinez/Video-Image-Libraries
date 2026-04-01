package com.samsung.android.sdk.scs.ai.asr;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.scs.ai.asr.ExpiringData;
import com.samsung.android.sdk.scs.ai.asr.tasks.IdleTask;
import com.samsung.android.sdk.scs.ai.asr.tasks.SttRecognitionTask;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SpeechRecognizerControl {
    private static final Duration DEFAULT_DURATION;
    private static final ExpiringData<RemoteServiceExecutor> sServiceExecutorPool;
    private final String TAG;
    private boolean isReleased = false;
    private final String mCallerPackage;
    private final Context mContext;
    private SpeechRecognitionListener mListener;
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
        sServiceExecutorPool = new ExpiringData.Builder("Executor", new h(2)).setTimeout(duration).build();
    }

    public SpeechRecognizerControl(Context context, SpeechRecognitionListener speechRecognitionListener) {
        String str = "SpeechRecognizerControl@" + hashCode();
        this.TAG = str;
        this.mContext = context;
        this.mCallerPackage = context.getPackageName();
        this.mListener = speechRecognitionListener;
        this.mServiceExecutor = sServiceExecutorPool.getOrCompute(new j(0, context));
        ASRLog.i(str, "created", speechRecognitionListener);
    }

    private static Context getGlobalContext() {
        try {
            return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private boolean initInternal() {
        if (Feature.checkFeature(this.mContext, Feature.FEATURE_SPEECH_RECOGNITION) != 0) {
            this.mListener.onError(-1, "SpeechRecognizerService is UNAVAILABLE", new Bundle());
            return false;
        } else if (isReleased()) {
            this.mListener.onError(15, "SpeechRecognizer is UNINITIALIZED", new Bundle());
            return false;
        } else if (this.mServiceExecutor.isConnected()) {
            return true;
        } else {
            this.mServiceExecutor.execute(new IdleTask());
            return true;
        }
    }

    private boolean isReleased() {
        return this.isReleased;
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
        Log.i(this.TAG, Contract.COMMAND_ID_CANCEL);
        SttRecognitionTask sttRecognitionTask = this.mSttTask;
        if (sttRecognitionTask != null && !sttRecognitionTask.isComplete()) {
            this.mSttTask.cancel();
        }
    }

    public void init() {
        ASRLog.i(this.TAG, "init", this.mListener, Boolean.valueOf(initInternal()));
    }

    public void release() {
        this.isReleased = true;
        Log.i(this.TAG, "release");
        cancel();
        SttRecognitionTask sttRecognitionTask = this.mSttTask;
        if (sttRecognitionTask != null) {
            sttRecognitionTask.release();
        }
        this.mSttTask = null;
    }

    public void start(RecognitionConfig recognitionConfig, InputStream inputStream) {
        if (initInternal()) {
            Log.i(this.TAG, "start");
            SttRecognitionTask sttRecognitionTask = new SttRecognitionTask(recognitionConfig, inputStream, this.mCallerPackage, this.mListener);
            this.mSttTask = sttRecognitionTask;
            this.mServiceExecutor.execute(sttRecognitionTask);
            return;
        }
        Log.w(this.TAG, "start failed");
    }
}
