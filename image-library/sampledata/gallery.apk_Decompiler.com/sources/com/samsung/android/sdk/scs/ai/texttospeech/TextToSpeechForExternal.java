package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextToSpeechForExternal extends TextToSpeech {
    public TextToSpeechForExternal(Context context) {
        super(context);
    }

    public void createExecutor(Context context) {
        this.mTTSServiceExecutor = new TextToSpeechServiceExecutorForExternal(context);
    }
}
