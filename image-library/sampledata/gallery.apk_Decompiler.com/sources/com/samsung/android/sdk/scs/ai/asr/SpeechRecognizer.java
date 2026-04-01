package com.samsung.android.sdk.scs.ai.asr;

import android.content.Context;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpeechRecognizer {
    private static final String TAG = "SpeechRecognizer";
    private final Context mContext;
    private final SpeechRecognitionListener mInnerListener;
    private final SpeechRecognizerControl mRecognizerController;

    public SpeechRecognizer(Context context, SpeechRecognitionListener speechRecognitionListener) {
        this.mContext = context;
        this.mInnerListener = speechRecognitionListener;
        this.mRecognizerController = new SpeechRecognizerControl(context, speechRecognitionListener);
    }

    public void cancel() {
        this.mRecognizerController.cancel();
    }

    public List<Locale> getSupportedLocales() {
        return Environment.getSupportedLocales(this.mContext);
    }

    public void init() {
        this.mRecognizerController.init();
    }

    public void release() {
        this.mRecognizerController.release();
    }

    public void start(RecognitionConfig recognitionConfig, InputStream inputStream) {
        this.mRecognizerController.start(recognitionConfig, inputStream);
        Log.e(TAG, "started");
    }
}
