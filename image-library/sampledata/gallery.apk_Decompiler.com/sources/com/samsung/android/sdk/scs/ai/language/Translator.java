package com.samsung.android.sdk.scs.ai.language;

import android.content.Context;
import android.os.RemoteException;
import c4.C0431a;
import com.samsung.android.gallery.support.utils.K;
import com.samsung.android.sdk.scs.ai.AiServices;
import com.samsung.android.sdk.scs.ai.language.service.AuthHeader;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceObserver2;
import com.samsung.android.sdk.scs.ai.language.service.LlmServiceRunnable;
import com.samsung.android.sdk.scs.ai.language.service.TranslationRunnable2;
import com.samsung.android.sdk.scs.ai.language.service.TranslationServiceExecutor;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.tasks.TaskCompletionSource;
import com.samsung.android.sdk.scs.base.tasks.TaskStreamingCompletionSource;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.language.g0;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Translator {
    private static final String TAG = "Translator";
    private final TranslationServiceExecutor mServiceExecutor;

    public Translator(Context context) {
        Log.d(TAG, TAG);
        this.mServiceExecutor = new TranslationServiceExecutor(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$interpret$0(AppInfo appInfo, String str, String str2, String str3, Map map, LlmServiceObserver2 llmServiceObserver2) {
        try {
            ((g0) this.mServiceExecutor.getService()).a(new AuthHeader(appInfo).generateHeaderMap(this.mServiceExecutor.context), str, str2, str3, llmServiceObserver2, map);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public Task<String> identifyLanguage(String str) {
        Log.i(TAG, "identifyLanguage: " + str);
        return AiServices.getNeuralTranslator(this.mServiceExecutor.context).identifyLanguage(str);
    }

    public Task<Result> interpret(AppInfo appInfo, String str, Locale locale, Locale locale2) {
        return interpret(appInfo, str, locale.toLanguageTag(), locale2.toLanguageTag(), (Map<String, String>) new HashMap());
    }

    public void release() {
        Log.i(TAG, "release: " + this.mServiceExecutor.isConnected());
        this.mServiceExecutor.deInit();
    }

    public Task<List<Result>> translate(AppInfo appInfo, List<String> list, Locale locale, Locale locale2) {
        return translate(appInfo, list, locale.toLanguageTag(), locale2.toLanguageTag());
    }

    public Task<Result> interpret(AppInfo appInfo, String str, Locale locale, Locale locale2, Map<String, String> map) {
        return interpret(appInfo, str, locale.toLanguageTag(), locale2.toLanguageTag(), map);
    }

    public Task<List<Result>> translate(AppInfo appInfo, List<String> list, String str, String str2) {
        return translate(appInfo, list, str, str2, (Map<String, String>) null);
    }

    public Task<Result> interpret(AppInfo appInfo, String str, String str2, String str3) {
        return interpret(appInfo, str, str2, str3, (Map<String, String>) new HashMap());
    }

    public Task<List<Result>> translate(AppInfo appInfo, List<String> list, String str, String str2, Map<String, String> map) {
        TranslationRunnable2 translationRunnable2 = new TranslationRunnable2(this.mServiceExecutor, appInfo.isStreamingMode() ? new TaskStreamingCompletionSource() : new TaskCompletionSource());
        Log.i(TAG, "created: " + translationRunnable2.hashCode());
        translationRunnable2.setAppInfo(appInfo);
        translationRunnable2.setInputText(list);
        translationRunnable2.setFromLanguage(str);
        translationRunnable2.setToLanguage(str2);
        if (map != null) {
            translationRunnable2.setExtraPrompt(map);
        }
        this.mServiceExecutor.execute(translationRunnable2);
        Log.i(TAG, "executed: " + translationRunnable2.hashCode());
        return translationRunnable2.getTask();
    }

    public Task<Result> interpret(AppInfo appInfo, String str, String str2, String str3, Map<String, String> map) {
        LlmServiceRunnable llmServiceRunnable = new LlmServiceRunnable(Feature.FEATURE_AI_CLOUD_LLM_INTERPRETATION, appInfo.isStreamingMode(), new K(this, appInfo, str, str2, str3, map, 2), new C0431a(22));
        this.mServiceExecutor.execute(llmServiceRunnable);
        return llmServiceRunnable.getTask();
    }
}
