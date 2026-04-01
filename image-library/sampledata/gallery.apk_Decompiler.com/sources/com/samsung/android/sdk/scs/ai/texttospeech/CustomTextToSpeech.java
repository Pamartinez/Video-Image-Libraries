package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.scs.ai.texttospeech.TextToSpeech;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesizerInitCallback;
import com.samsung.android.sivs.ai.sdkcommon.tts.Voice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CustomTextToSpeech extends TextToSpeech {
    public CustomTextToSpeech(Context context) {
        super(context);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindSynthesizer$0(TextToSpeech.OnConntectListener onConntectListener, Task task) {
        if (!task.isSuccessful() && onConntectListener != null) {
            onConntectListener.onConnected(-2);
        }
    }

    public int bindSynthesizer(final TextToSpeech.OnConntectListener onConntectListener) {
        CreateCustomVoiceSynthesizerRunnable createCustomVoiceSynthesizerRunnable = new CreateCustomVoiceSynthesizerRunnable(this.mTTSServiceExecutor, new ISynthesizerInitCallback.Stub() {
            public void onConnected(int i2) {
                Log.i("ScsApi@TTS", "onConnected : " + i2);
                TextToSpeech.OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onConnected(i2);
                }
            }

            public void onDisconnected() {
                TextToSpeech.OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onDisconnected();
                }
            }

            public void onVoiceUpdated(List<Voice> list) {
                TextToSpeech.OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onVoiceUpdated(list);
                }
            }
        });
        this.mTTSServiceExecutor.execute(createCustomVoiceSynthesizerRunnable);
        createCustomVoiceSynthesizerRunnable.getTask().addOnCompleteListener(new a(onConntectListener, 0));
        return 0;
    }

    public List<String> getAvailableCustomVoiceLanguages() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> availableCustomVoiceLocales = this.mTTSServiceExecutor.getTTService().getAvailableCustomVoiceLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (availableCustomVoiceLocales == null || availableCustomVoiceLocales.size() <= 0) {
                return arrayList;
            }
            arrayList.addAll(availableCustomVoiceLocales);
            return arrayList;
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return arrayList;
        }
    }

    public List<Locale> getAvailableCustomVoiceLocales() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> availableCustomVoiceLocales = this.mTTSServiceExecutor.getTTService().getAvailableCustomVoiceLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (availableCustomVoiceLocales != null && availableCustomVoiceLocales.size() > 0) {
                for (String convertLocaleCodeToLocale : availableCustomVoiceLocales) {
                    arrayList.add(TextToSpeech.convertLocaleCodeToLocale(convertLocaleCodeToLocale));
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return arrayList;
        }
    }

    public String getCustomVoicePackageName(String str, int i2, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            return this.mTTSServiceExecutor.getTTService().getCustomVoicePackageName(str, i2, str2);
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return null;
        }
    }

    public List<String> getSupportCustomVoiceLanguages() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> supportCustomVoiceLocales = this.mTTSServiceExecutor.getTTService().getSupportCustomVoiceLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (supportCustomVoiceLocales == null || supportCustomVoiceLocales.size() <= 0) {
                return arrayList;
            }
            arrayList.addAll(supportCustomVoiceLocales);
            return arrayList;
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return arrayList;
        }
    }

    public List<Locale> getSupportCustomVoiceLocales() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> supportCustomVoiceLocales = this.mTTSServiceExecutor.getTTService().getSupportCustomVoiceLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (supportCustomVoiceLocales != null && supportCustomVoiceLocales.size() > 0) {
                for (String convertLocaleCodeToLocale : supportCustomVoiceLocales) {
                    arrayList.add(TextToSpeech.convertLocaleCodeToLocale(convertLocaleCodeToLocale));
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return arrayList;
        }
    }

    public int openCustomVoice(String str) {
        if (!checkConnection()) {
            return -4;
        }
        String customVoicePackageName = getCustomVoicePackageName(this.mTTSServiceExecutor.callerName(), this.mTTSServiceExecutor.callerId(), str);
        if (customVoicePackageName != null && !customVoicePackageName.equals("")) {
            List<String> availableCustomVoiceLanguages = getAvailableCustomVoiceLanguages();
            if (!(str == null || availableCustomVoiceLanguages.size() == 0 || !availableCustomVoiceLanguages.contains(str))) {
                Intent intent = new Intent("com.samsung.android.ptts.action.CREATE_PERSONAL_VOICE_FOR_EXTERNAL");
                try {
                    intent.setPackage(customVoicePackageName);
                    intent.addFlags(335544352);
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

    public int openCustomVoiceDeepLink(String str) {
        if (!checkConnection()) {
            return -4;
        }
        String customVoicePackageName = getCustomVoicePackageName(this.mTTSServiceExecutor.callerName(), this.mTTSServiceExecutor.callerId(), str);
        if (customVoicePackageName != null && !customVoicePackageName.equals("")) {
            List<String> supportCustomVoiceLanguages = getSupportCustomVoiceLanguages();
            if (!(str == null || supportCustomVoiceLanguages.size() == 0 || !supportCustomVoiceLanguages.contains(str))) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("samsungapps://productDetail/".concat(customVoicePackageName)));
                intent.putExtra("type", "cover");
                intent.addFlags(335544352);
                this.mContext.startActivity(intent);
                return 0;
            }
        }
        return -51;
    }

    public int removeCustomVoice(Voice voice) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (voice == null) {
            return -1;
        }
        try {
            return this.mTTSServiceExecutor.getTTService().removeCustomVoice(callerName, callerId, voice);
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return -1;
        }
    }

    public int renameCustomVoice(Voice voice, String str) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (voice == null || str == null) {
            return -1;
        }
        if (str.equals("")) {
            return -53;
        }
        try {
            return this.mTTSServiceExecutor.getTTService().renameCustomVoice(callerName, callerId, voice, str);
        } catch (RemoteException e) {
            Log.e("ScsApi@TTS", e.getMessage());
            return -1;
        }
    }
}
