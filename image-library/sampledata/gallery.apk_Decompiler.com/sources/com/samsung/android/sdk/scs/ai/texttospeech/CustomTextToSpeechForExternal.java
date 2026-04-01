package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomTextToSpeechForExternal extends CustomTextToSpeech {
    public CustomTextToSpeechForExternal(Context context) {
        super(context);
    }

    public void createExecutor(Context context) {
        this.mTTSServiceExecutor = new TextToSpeechServiceExecutorForExternal(context);
    }

    public int openCustomVoice(String str) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        String customVoicePackageName = getCustomVoicePackageName(callerName, callerId, str);
        if (customVoicePackageName != null && !customVoicePackageName.equals("")) {
            List<String> availableCustomVoiceLanguages = getAvailableCustomVoiceLanguages();
            if (!(str == null || availableCustomVoiceLanguages.size() == 0 || !availableCustomVoiceLanguages.contains(str))) {
                Intent intent = new Intent("com.samsung.android.ptts.action.CREATE_PERSONAL_VOICE_FOR_EXTERNAL");
                try {
                    intent.setPackage(customVoicePackageName);
                    intent.addFlags(335544352);
                    intent.putExtra("callerName", callerName);
                    intent.putExtra("externalCall", true);
                    this.mContext.startActivity(intent);
                    return 0;
                } catch (ActivityNotFoundException unused) {
                    Log.i("ScsApi@TTS", "Can't find compatible custom voice.");
                    return openCustomVoiceDeepLink(str);
                }
            }
        }
        return -51;
    }
}
