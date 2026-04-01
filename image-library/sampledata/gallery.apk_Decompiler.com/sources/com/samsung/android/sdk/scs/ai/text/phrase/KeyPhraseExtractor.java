package com.samsung.android.sdk.scs.ai.text.phrase;

import Tc.a;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import c4.C0431a;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.android.sdk.scs.ai.text.TextServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.Def;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeyPhraseExtractor {
    public static final int EXTRA_HANDWRITING = 1;
    public static final int EXTRA_LOCATION = 2;
    public static final int EXTRA_TITLE = 0;
    private static final Set<String> SUPPORTED_LANGUAGE = ((Set) Stream.of(new String[]{"KO", "ZH", "EN", "DE", "FR", "ES", "IT"}).collect(Collectors.collectingAndThen(Collectors.toSet(), new C0431a(25))));
    private static final String TAG = "ScsApi@KeyPhraseExtractor";
    private final boolean isEventTitleGeneratorSupported;
    private TextServiceExecutor mServiceExecutor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ActionType {
        MANUAL,
        AUTO
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExtractOptions {
        String actionType;
        Map<Integer, String> extraInfo;
        String language;
        String requestType;
        int topK;
        Map<String, Integer> userTags;

        public ExtractOptions(RequestType requestType2, ActionType actionType2) {
            this.requestType = requestType2.name();
            this.actionType = actionType2.name();
        }

        public String getActionType() {
            return this.actionType;
        }

        public Map<Integer, String> getExtraInfo() {
            return this.extraInfo;
        }

        public String getLanguage() {
            return this.language;
        }

        public String getRequestType() {
            return this.requestType;
        }

        public int getTopK() {
            return this.topK;
        }

        public Map<String, Integer> getUserTags() {
            return this.userTags;
        }

        public ExtractOptions setExtraInfo(Map<Integer, String> map) {
            this.extraInfo = map;
            return this;
        }

        public ExtractOptions setLanguage(String str) {
            this.language = str;
            return this;
        }

        public ExtractOptions setTopK(int i2) {
            this.topK = i2;
            return this;
        }

        public ExtractOptions setUserTags(Map<String, Integer> map) {
            this.userTags = map;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum RequestType {
        IMAGE_TAG,
        NOTE_TAG,
        NOTE_TITLE,
        EVENT_TITLE
    }

    public KeyPhraseExtractor(Context context) {
        boolean z;
        if (Feature.checkFeature(context, Feature.FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE) == 0) {
            z = true;
        } else {
            z = false;
        }
        this.isEventTitleGeneratorSupported = z;
        initConnection(context);
    }

    private void initConnection(Context context) {
        Log.d(TAG, "initConnection");
        this.mServiceExecutor = new TextServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$isSupported$0(RequestType requestType, String str) {
        try {
            ContentResolver textContentResolver = this.mServiceExecutor.getTextContentResolver();
            Bundle bundle = new Bundle();
            bundle.putString("requestType", requestType.name());
            bundle.putString("language", str);
            Bundle call = textContentResolver.call(Uri.parse("content://com.samsung.android.scs.ai.text"), "getKeyPhraseSupported", (String) null, bundle);
            if (call == null) {
                Log.e(TAG, "KeyPhraseExtractor.isSupported(). ContentResolver result is null!!");
                return Boolean.FALSE;
            } else if (call.isEmpty()) {
                Log.e(TAG, "KeyPhraseExtractor.isSupported(). result is empty!! App version is lower than Sdk so just check in static Array");
                return Boolean.valueOf(SUPPORTED_LANGUAGE.contains(str.toUpperCase()));
            } else {
                int i2 = call.getInt(OCRServiceConstant.KEY_RESULT_CODE);
                if (i2 == 1) {
                    return Boolean.valueOf(call.getBoolean("textSupportedBoolean"));
                }
                Log.e(TAG, "unexpected resultCode!!! resultCode: " + i2);
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception :: isSupported", e);
            return Boolean.FALSE;
        }
    }

    public Task<List<KeyPhrase>> extract(String str, ExtractOptions extractOptions) {
        Log.d(TAG, "KeyPhraseExtractor extract");
        KeyPhraseExtractRunnable keyPhraseExtractRunnable = new KeyPhraseExtractRunnable(this.mServiceExecutor, extractOptions.requestType);
        int length = str.length();
        if (length > 100000) {
            Log.i(TAG, String.format("KeyPhraseExtractor input length(%d) exceed MAX_VAL(%d), so cut to %d", new Object[]{Integer.valueOf(length), 100000, 100000}));
            str = str.substring(0, 100000);
        }
        keyPhraseExtractRunnable.setText(str);
        keyPhraseExtractRunnable.setOptions(extractOptions);
        this.mServiceExecutor.execute(keyPhraseExtractRunnable);
        return keyPhraseExtractRunnable.getTask();
    }

    public boolean isSupported(String str) {
        Log.d(TAG, "KeyPhraseExtractor isSupported - language : " + str);
        return SUPPORTED_LANGUAGE.contains(str.toUpperCase());
    }

    public boolean isSupported(RequestType requestType, String str) {
        Boolean bool;
        String name = requestType.name();
        Log.d(TAG, "KeyPhraseExtractor isSupported - requestType : " + name + ", language : " + str);
        if (!this.isEventTitleGeneratorSupported && requestType == RequestType.EVENT_TITLE) {
            return false;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        Future submit = newSingleThreadExecutor.submit(new a((Object) this, (Serializable) requestType, str, 3));
        try {
            bool = (Boolean) submit.get(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            submit.cancel(true);
            Log.e(TAG, "Timeout in isSupported : " + e.getMessage());
            bool = Boolean.FALSE;
        } catch (Exception e7) {
            Log.e(TAG, "Exception occurred in isSupported : " + e7.getMessage());
            bool = Boolean.FALSE;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdownNow();
            throw th;
        }
        newSingleThreadExecutor.shutdownNow();
        return bool.booleanValue();
    }
}
