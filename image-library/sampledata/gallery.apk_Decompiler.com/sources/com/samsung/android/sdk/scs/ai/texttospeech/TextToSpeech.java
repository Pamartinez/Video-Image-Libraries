package com.samsung.android.sdk.scs.ai.texttospeech;

import android.content.Context;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesisCallback;
import com.samsung.android.sivs.ai.sdkcommon.tts.ISynthesizerInitCallback;
import com.samsung.android.sivs.ai.sdkcommon.tts.ITextToSpeechService;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import com.samsung.android.sivs.ai.sdkcommon.tts.Voice;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.StringTokenizer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextToSpeech {
    protected static final boolean DEBUG = false;
    protected static final String LOCALE_DELIMITER = "-";
    protected static final String TAG = "ScsApi@TTS";
    protected Context mContext = null;
    protected final Bundle mParams = new Bundle();
    protected TextToSpeechServiceExecutor mTTSServiceExecutor = null;
    protected Voice mVoice = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnConntectListener {
        void onConnected(int i2);

        void onDisconnected();

        void onVoiceUpdated(List<Voice> list);
    }

    public TextToSpeech(Context context) {
        this.mContext = context;
        createExecutor(context);
    }

    public static Locale convertLocaleCodeToLocale(String str) {
        String str2;
        String str3;
        if (str == null || str.equals("")) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, LOCALE_DELIMITER);
        if (stringTokenizer.hasMoreTokens()) {
            str2 = stringTokenizer.nextToken();
        } else {
            str2 = "";
        }
        if (stringTokenizer.hasMoreTokens()) {
            str3 = stringTokenizer.nextToken();
        } else {
            str3 = "";
        }
        if (str2.equals("") || str3.equals("")) {
            return null;
        }
        return new Locale(str2, str3);
    }

    private static String convertLocaleToLocaleCode(Locale locale) {
        if (locale == null) {
            return "";
        }
        return locale.getISO3Language() + LOCALE_DELIMITER + locale.getISO3Country();
    }

    private Bundle getParams(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return this.mParams;
        }
        Bundle bundle2 = new Bundle(this.mParams);
        bundle2.putAll(bundle);
        return bundle2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$bindSynthesizer$0(OnConntectListener onConntectListener, Task task) {
        if (!task.isSuccessful() && onConntectListener != null) {
            onConntectListener.onConnected(-2);
        }
    }

    public int bindSynthesizer(final OnConntectListener onConntectListener) {
        CreateSynthesizerRunnable createSynthesizerRunnable = new CreateSynthesizerRunnable(this.mTTSServiceExecutor, new ISynthesizerInitCallback.Stub() {
            public void onConnected(int i2) {
                Log.i(TextToSpeech.TAG, "onConnected : " + i2);
                OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onConnected(i2);
                }
            }

            public void onDisconnected() {
                OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onDisconnected();
                }
            }

            public void onVoiceUpdated(List<Voice> list) {
                OnConntectListener onConntectListener = onConntectListener;
                if (onConntectListener != null) {
                    onConntectListener.onVoiceUpdated(list);
                }
            }
        });
        this.mTTSServiceExecutor.execute(createSynthesizerRunnable);
        createSynthesizerRunnable.getTask().addOnCompleteListener(new a(onConntectListener, 1));
        return 0;
    }

    public boolean checkConnection() {
        if (this.mTTSServiceExecutor.getTTService() != null) {
            return true;
        }
        Log.e(TAG, "error : not connected to synthesizer.");
        return false;
    }

    public void createExecutor(Context context) {
        this.mTTSServiceExecutor = new TextToSpeechServiceExecutor(context);
    }

    public List<String> getAvailableLanguages() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> availableLocales = this.mTTSServiceExecutor.getTTService().getAvailableLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (availableLocales == null || availableLocales.size() <= 0) {
                return arrayList;
            }
            arrayList.addAll(availableLocales);
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return arrayList;
        }
    }

    public List<Locale> getAvailableLocales() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> availableLocales = this.mTTSServiceExecutor.getTTService().getAvailableLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (availableLocales != null && availableLocales.size() > 0) {
                for (String convertLocaleCodeToLocale : availableLocales) {
                    arrayList.add(convertLocaleCodeToLocale(convertLocaleCodeToLocale));
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return arrayList;
        }
    }

    public int getAvailableSynthesizerCount() {
        if (!checkConnection()) {
            return -1;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            return this.mTTSServiceExecutor.getTTService().getAvailableSynthesizerCount(this.mTTSServiceExecutor.callerName(), callerId);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return 0;
        }
    }

    public int getMaxInputLength() {
        return TextToSpeechConst.MAX_SPEECH_INPUT;
    }

    public List<String> getSupportLanguages() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> supportLocales = this.mTTSServiceExecutor.getTTService().getSupportLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (supportLocales == null || supportLocales.size() <= 0) {
                return arrayList;
            }
            arrayList.addAll(supportLocales);
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return arrayList;
        }
    }

    public List<Locale> getSupportLocales() {
        if (!checkConnection()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<String> supportLocales = this.mTTSServiceExecutor.getTTService().getSupportLocales(this.mTTSServiceExecutor.callerName(), callerId);
            if (supportLocales != null && supportLocales.size() > 0) {
                for (String convertLocaleCodeToLocale : supportLocales) {
                    arrayList.add(convertLocaleCodeToLocale(convertLocaleCodeToLocale));
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return arrayList;
        }
    }

    public Voice getVoice(String str, String str2, String str3) {
        if (!checkConnection()) {
            return null;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (!(str == null || str2 == null || str3 == null)) {
            try {
                return this.mTTSServiceExecutor.getTTService().getVoice(callerName, callerId, str, str2, str3);
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return null;
    }

    public Set<Voice> getVoices() {
        if (!checkConnection()) {
            return Collections.EMPTY_SET;
        }
        HashSet hashSet = new HashSet();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<Voice> voices = this.mTTSServiceExecutor.getTTService().getVoices(this.mTTSServiceExecutor.callerName(), callerId);
            if (voices == null || voices.size() <= 0) {
                return hashSet;
            }
            hashSet.addAll(voices);
            return hashSet;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return hashSet;
        }
    }

    public int isLanguageAvailable(String str, String str2, String str3) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (str == null || str2 == null || str3 == null) {
            return -1;
        }
        try {
            return this.mTTSServiceExecutor.getTTService().isLanguageAvailable(callerName, callerId, str, str2, str3);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return -1;
        }
    }

    public int setAudioAttributes(AudioAttributes audioAttributes) {
        this.mParams.putParcelable(TextToSpeechConst.PARAM_KEY_AUDIO_ATTRIBUTES, audioAttributes);
        return 0;
    }

    public int setEndSilence(int i2) {
        this.mParams.putInt(TextToSpeechConst.PARAM_KEY_END_SILENCE, i2);
        return 0;
    }

    public int setPitch(float f) {
        this.mParams.putFloat(TextToSpeechConst.PARAM_KEY_PITCH, f);
        return 0;
    }

    public int setSpeechRate(float f) {
        this.mParams.putFloat(TextToSpeechConst.PARAM_KEY_SPEECH_RATE, f);
        return 0;
    }

    public int setStreamType(int i2) {
        this.mParams.putInt(TextToSpeechConst.PARAM_KEY_STREAM_TYPE, i2);
        return 0;
    }

    public int setVoice(Voice voice) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (voice != null) {
            try {
                int voice2 = this.mTTSServiceExecutor.getTTService().setVoice(callerName, callerId, voice);
                if (voice2 != 0) {
                    return voice2;
                }
                this.mVoice = voice;
                return voice2;
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return -1;
    }

    public int shutdown() {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            return this.mTTSServiceExecutor.getTTService().releaseSynthesizer(this.mTTSServiceExecutor.callerName(), callerId);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return -1;
        }
    }

    public int speak(CharSequence charSequence, Voice voice, int i2, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        if (!checkConnection()) {
            return -4;
        }
        if (charSequence == null || charSequence.equals("")) {
            Log.i(TAG, "speak : empty input.");
            return -31;
        } else if (charSequence.length() > 4000) {
            Log.e(TAG, "speak error : ERROR_INVALID_REQUEST_EXCEED_MAX_INPUT_LENGTH");
            return -32;
        } else {
            int callerId = this.mTTSServiceExecutor.callerId();
            String callerName = this.mTTSServiceExecutor.callerName();
            try {
                ITextToSpeechService tTService = this.mTTSServiceExecutor.getTTService();
                final SynthesizeProgressListener synthesizeProgressListener2 = synthesizeProgressListener;
                return tTService.speak(callerName, callerId, voice, charSequence, i2, getParams(bundle), str, new ISynthesisCallback.Stub() {
                    public void onDone(String str) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onDone(str);
                        }
                    }

                    public void onError(String str, int i2) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onError(str, i2);
                        }
                    }

                    public void onProgress(String str, byte[] bArr, int i2, int i7) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onProgress(str, bArr, i2, i7);
                        }
                    }

                    public void onRangeStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onRangeStart(str, i2, i7, i8);
                        }
                    }

                    public void onStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onStart(str, i2, i7, i8);
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return -1;
            }
        }
    }

    public int stop() {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            return this.mTTSServiceExecutor.getTTService().stop(this.mTTSServiceExecutor.callerName(), callerId);
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return -1;
        }
    }

    public int synthesize(CharSequence charSequence, Voice voice, Bundle bundle, String str, final SynthesizeProgressListener synthesizeProgressListener) {
        if (!checkConnection()) {
            return -4;
        }
        if (charSequence == null || charSequence.equals("")) {
            Log.i(TAG, "synthesize : empty input.");
            return -31;
        } else if (charSequence.length() > 4000) {
            Log.e(TAG, "synthesize error : ERROR_INVALID_REQUEST_EXCEED_MAX_INPUT_LENGTH");
            return -32;
        } else {
            int callerId = this.mTTSServiceExecutor.callerId();
            try {
                return this.mTTSServiceExecutor.getTTService().synthesize(this.mTTSServiceExecutor.callerName(), callerId, voice, charSequence, getParams(bundle), str, new ISynthesisCallback.Stub() {
                    public void onDone(String str) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onDone(str);
                        }
                    }

                    public void onError(String str, int i2) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onError(str, i2);
                        }
                    }

                    public void onProgress(String str, byte[] bArr, int i2, int i7) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onProgress(str, bArr, i2, i7);
                        }
                    }

                    public void onRangeStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onRangeStart(str, i2, i7, i8);
                        }
                    }

                    public void onStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onStart(str, i2, i7, i8);
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return -1;
            }
        }
    }

    public int synthesizeToFile(CharSequence charSequence, Voice voice, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        if (!checkConnection()) {
            return -4;
        }
        if (charSequence == null || charSequence.equals("")) {
            Log.i(TAG, "synthesizeToFile : empty input.");
            return -31;
        } else if (charSequence.length() > 4000) {
            Log.e(TAG, "synthesizeToFile error : ERROR_INVALID_REQUEST_EXCEED_MAX_INPUT_LENGTH");
            return -32;
        } else {
            int callerId = this.mTTSServiceExecutor.callerId();
            String callerName = this.mTTSServiceExecutor.callerName();
            try {
                ITextToSpeechService tTService = this.mTTSServiceExecutor.getTTService();
                final SynthesizeProgressListener synthesizeProgressListener2 = synthesizeProgressListener;
                return tTService.synthesizeToFile(callerName, callerId, voice, charSequence, parcelFileDescriptor, getParams(bundle), str, new ISynthesisCallback.Stub() {
                    public void onDone(String str) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onDone(str);
                        }
                    }

                    public void onError(String str, int i2) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onError(str, i2);
                        }
                    }

                    public void onProgress(String str, byte[] bArr, int i2, int i7) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onProgress(str, bArr, i2, i7);
                        }
                    }

                    public void onRangeStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onRangeStart(str, i2, i7, i8);
                        }
                    }

                    public void onStart(String str, int i2, int i7, int i8) {
                        SynthesizeProgressListener synthesizeProgressListener = synthesizeProgressListener2;
                        if (synthesizeProgressListener != null) {
                            synthesizeProgressListener.onStart(str, i2, i7, i8);
                        }
                    }
                });
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
                return -1;
            }
        }
    }

    public Voice getVoice(Locale locale) {
        if (!checkConnection()) {
            return null;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (locale != null) {
            try {
                return this.mTTSServiceExecutor.getTTService().getVoice(callerName, callerId, locale.getLanguage(), locale.getCountry(), locale.getVariant());
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return null;
    }

    public int isLanguageAvailable(Locale locale) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (locale == null) {
            return -1;
        }
        try {
            return this.mTTSServiceExecutor.getTTService().isLanguageAvailable(callerName, callerId, locale.getLanguage(), locale.getCountry(), locale.getVariant());
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return -1;
        }
    }

    public int setVoice(Locale locale) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (locale != null) {
            try {
                Voice voice = this.mTTSServiceExecutor.getTTService().getVoice(callerName, callerId, locale.getLanguage(), locale.getCountry(), locale.getVariant());
                if (voice != null) {
                    int voice2 = this.mTTSServiceExecutor.getTTService().setVoice(callerName, callerId, voice);
                    if (voice2 != 0) {
                        return voice2;
                    }
                    this.mVoice = voice;
                    return voice2;
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return -1;
    }

    public Set<Voice> getVoices(String str) {
        if (!checkConnection()) {
            return Collections.EMPTY_SET;
        }
        HashSet hashSet = new HashSet();
        int callerId = this.mTTSServiceExecutor.callerId();
        try {
            List<Voice> voicesWithLocale = this.mTTSServiceExecutor.getTTService().getVoicesWithLocale(this.mTTSServiceExecutor.callerName(), callerId, str);
            if (voicesWithLocale == null || voicesWithLocale.size() <= 0) {
                return hashSet;
            }
            hashSet.addAll(voicesWithLocale);
            return hashSet;
        } catch (RemoteException e) {
            Log.e(TAG, e.getMessage());
            return hashSet;
        }
    }

    public int speak(CharSequence charSequence, int i2, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        Voice voice = this.mVoice;
        if (voice == null) {
            return -33;
        }
        return speak(charSequence, voice, i2, bundle, str, synthesizeProgressListener);
    }

    public int synthesize(CharSequence charSequence, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        Voice voice = this.mVoice;
        if (voice == null) {
            return -33;
        }
        return synthesize(charSequence, voice, bundle, str, synthesizeProgressListener);
    }

    public int synthesizeToFile(CharSequence charSequence, ParcelFileDescriptor parcelFileDescriptor, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        Voice voice = this.mVoice;
        if (voice == null) {
            return -33;
        }
        return synthesizeToFile(charSequence, voice, parcelFileDescriptor, bundle, str, synthesizeProgressListener);
    }

    public int speak(CharSequence charSequence, int i2, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return speak(charSequence, i2, this.mParams, str, synthesizeProgressListener);
    }

    public int synthesize(CharSequence charSequence, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return synthesize(charSequence, this.mParams, str, synthesizeProgressListener);
    }

    public int synthesizeToFile(CharSequence charSequence, ParcelFileDescriptor parcelFileDescriptor, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return synthesizeToFile(charSequence, parcelFileDescriptor, this.mParams, str, synthesizeProgressListener);
    }

    public int speak(CharSequence charSequence, Voice voice, int i2, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return speak(charSequence, voice, i2, this.mParams, str, synthesizeProgressListener);
    }

    public int synthesize(CharSequence charSequence, Voice voice, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return synthesize(charSequence, voice, this.mParams, str, synthesizeProgressListener);
    }

    public int synthesizeToFile(CharSequence charSequence, Voice voice, ParcelFileDescriptor parcelFileDescriptor, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return synthesizeToFile(charSequence, voice, parcelFileDescriptor, this.mParams, str, synthesizeProgressListener);
    }

    public int setVoice(String str, String str2, String str3) {
        if (!checkConnection()) {
            return -4;
        }
        int callerId = this.mTTSServiceExecutor.callerId();
        String callerName = this.mTTSServiceExecutor.callerName();
        if (!(str == null || str2 == null || str3 == null)) {
            try {
                Voice voice = this.mTTSServiceExecutor.getTTService().getVoice(callerName, callerId, str, str2, str3);
                if (voice != null) {
                    int voice2 = this.mTTSServiceExecutor.getTTService().setVoice(callerName, callerId, voice);
                    if (voice2 != 0) {
                        return voice2;
                    }
                    this.mVoice = voice;
                    return voice2;
                }
            } catch (RemoteException e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return -1;
    }

    public int speak(CharSequence charSequence, String str, SynthesizeProgressListener synthesizeProgressListener) {
        Voice voice = this.mVoice;
        if (voice == null) {
            return -33;
        }
        return speak(charSequence, voice, 0, this.mParams, str, synthesizeProgressListener);
    }

    public int speak(CharSequence charSequence, Bundle bundle, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return speak(charSequence, 0, bundle, str, synthesizeProgressListener);
    }

    public Set<Voice> getVoices(Locale locale) {
        return getVoices(convertLocaleToLocaleCode(locale));
    }

    public int speak(CharSequence charSequence, Voice voice, String str, SynthesizeProgressListener synthesizeProgressListener) {
        return speak(charSequence, voice, 0, this.mParams, str, synthesizeProgressListener);
    }
}
