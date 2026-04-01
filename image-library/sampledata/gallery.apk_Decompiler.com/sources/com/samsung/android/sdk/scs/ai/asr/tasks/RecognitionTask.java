package com.samsung.android.sdk.scs.ai.asr.tasks;

import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.asr.ISpeechRecognizerService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RecognitionTask<T> extends TaskRunnable<T> {
    private static final String TAG = "RecognitionTask";
    private boolean mIsCancelled = false;
    protected ISpeechRecognizerService mService;

    public void active(ISpeechRecognizerService iSpeechRecognizerService) {
        this.mService = iSpeechRecognizerService;
    }

    public void cancel() {
        if (!isComplete() && !isCancelled()) {
            this.mIsCancelled = true;
            try {
                this.mSource.setException(new InterruptedException("cancelled"));
            } catch (IllegalStateException unused) {
                Log.e(TAG, "cannot cancel, already completed");
            }
        }
    }

    public void complete(T t) {
        if (!isComplete()) {
            this.mSource.setResult(t);
            this.mService = null;
            return;
        }
        Log.i(TAG, "already completed");
    }

    public String getFeatureName() {
        return Feature.FEATURE_SPEECH_RECOGNITION;
    }

    public boolean isCancelled() {
        return this.mIsCancelled;
    }

    public boolean isComplete() {
        return this.mSource.getTask().isComplete();
    }

    public void setError(Exception exc) {
        if (!isComplete()) {
            this.mSource.setException(exc);
            this.mService = null;
            return;
        }
        Log.i(TAG, "already completed");
    }
}
