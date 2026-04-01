package com.samsung.android.sdk.scs.ai.texttospeech;

import android.os.RemoteException;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesizerInitCallback;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreateSynthesizerRunnable extends TaskRunnable<Integer> {
    private static final String TAG = "ScsApi@TTS:CreateSynthesizerRunnable";
    private ISynthesizerInitCallback mListener;
    private TextToSpeechServiceExecutor mServiceExecutor;

    public CreateSynthesizerRunnable(TextToSpeechServiceExecutor textToSpeechServiceExecutor, ISynthesizerInitCallback iSynthesizerInitCallback) {
        this.mServiceExecutor = textToSpeechServiceExecutor;
        this.mListener = iSynthesizerInitCallback;
    }

    public void execute() {
        if (this.mListener == null) {
            this.mSource.setException(new IllegalArgumentException("createSynthesizer fail : invalid listener"));
            return;
        }
        try {
            int callerId = this.mServiceExecutor.callerId();
            int createSynthesizer = this.mServiceExecutor.getTTService().createSynthesizer(this.mServiceExecutor.callerName(), callerId, this.mListener);
            if (createSynthesizer != 0) {
                this.mSource.setException(new IllegalArgumentException(String.format("createSynthesizer fail : %d", new Object[]{Integer.valueOf(createSynthesizer)})));
            } else {
                this.mSource.setResult(0);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            this.mSource.setException(e);
        }
    }

    public String getFeatureName() {
        return Feature.FEATURE_AI_TEXT_TO_SPEECH;
    }
}
