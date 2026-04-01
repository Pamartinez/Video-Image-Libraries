package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.sdk.scs.base.utils.ConnectionHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextToSpeechServiceExecutorForExternal extends TextToSpeechServiceExecutor {
    public TextToSpeechServiceExecutorForExternal(Context context) {
        super(context);
    }

    public Intent getServiceIntent() {
        return ConnectionHelper.getTextToSpeechServiceIntentForExternal();
    }
}
