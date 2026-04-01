package com.samsung.android.sdk.scs.ai.translation;

import A4.Q;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import c4.C0431a;
import com.samsung.android.gallery.module.dynamicview.a;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.ai.language.Configuration;
import com.samsung.android.sdk.scs.ai.language.Result;
import com.samsung.android.sdk.scs.ai.language.Translator;
import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import com.samsung.android.sivs.ai.sdkcommon.translation.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NeuralTranslator {
    private static final String CLASS_NAME = "NeuralTranslator";
    private static final String TAG = "ScsApi@NeuralTranslator";
    Context context;
    Map<LanguageDirection, LanguageDirectionState> languageDirectionStateMap;
    Translator llmTranslator;
    NeuralTranslationRunnableExecutor neuralTranslationRunnableExecutor;

    public NeuralTranslator(Context context2) {
        this(context2, false);
    }

    private AppInfo copyAppInfoWithOnDeviceType(AppInfo appInfo) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        boolean z;
        AppInfo.Builder builder = new AppInfo.Builder();
        String str7 = "";
        if (appInfo != null) {
            str = appInfo.getAppId();
        } else {
            str = str7;
        }
        AppInfo.Builder appId = builder.setAppId(str);
        if (appInfo != null) {
            str2 = appInfo.getSigningKey();
        } else {
            str2 = str7;
        }
        AppInfo.Builder signingKey = appId.setSigningKey(str2);
        if (appInfo != null) {
            str3 = appInfo.getAppId();
        } else {
            str3 = str7;
        }
        AppInfo.Builder appId2 = signingKey.setAppId(str3);
        if (appInfo != null) {
            str4 = appInfo.getAccessToken();
        } else {
            str4 = str7;
        }
        AppInfo.Builder accessToken = appId2.setAccessToken(str4);
        if (appInfo != null) {
            str5 = appInfo.getUserId();
        } else {
            str5 = str7;
        }
        AppInfo.Builder userId = accessToken.setUserId(str5);
        if (appInfo != null) {
            str7 = appInfo.getServerUrl();
        }
        AppInfo.Builder serverUrl = userId.setServerUrl(str7);
        if (appInfo != null) {
            str6 = appInfo.getAccountType();
        } else {
            str6 = null;
        }
        AppInfo.Builder requestType = serverUrl.setAccountType(str6).setRequestType(AppInfo.RequestType.ONDEVICE);
        if (appInfo != null) {
            z = appInfo.isStreamingMode();
        } else {
            z = false;
        }
        return requestType.setStreamingMode(z).build();
    }

    private List<String> getAvailableLanguageDirectionStringList(LanguageDirectionState languageDirectionState) {
        return (List) this.languageDirectionStateMap.entrySet().stream().filter(new g(1, languageDirectionState)).map(new C0431a(28)).map(new C0431a(26)).distinct().sorted().collect(Collectors.toList());
    }

    private boolean isLLMTranslationSupported(LlmTranslationRequest llmTranslationRequest, Configuration configuration) {
        Log.i(TAG, "NeuralTranslator -- isLLMTranslationSupported() executed ");
        String ondeviceCapability = configuration.getOndeviceCapability();
        String languageTag = llmTranslationRequest.getFromLanguage().toLanguageTag();
        String languageTag2 = llmTranslationRequest.getToLanguage().toLanguageTag();
        try {
            JSONArray jSONArray = new JSONArray(ondeviceCapability);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (jSONObject.getString("language").equals(languageTag)) {
                    JSONArray jSONArray2 = jSONObject.getJSONArray("supportFunction");
                    String str = "Translation-" + languageTag + "2" + languageTag2;
                    for (int i7 = 0; i7 < jSONArray2.length(); i7++) {
                        if (jSONArray2.getString(i7).equals(str)) {
                            return true;
                        }
                    }
                    continue;
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse Configuration");
            e.printStackTrace();
        } catch (NullPointerException e7) {
            Log.e(TAG, "Looks like Configuration is empty" + ondeviceCapability);
            e7.printStackTrace();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clear$3(Task task) {
        try {
            this.languageDirectionStateMap = (Map) task.getResult();
            Log.i(TAG, "NeuralTranslator -- clear() - Available LanguageDirection list [(source, target)]: " + getAvailableLanguageDirectionStringList(LanguageDirectionState.AVAILABLE));
        } catch (RuntimeException e) {
            Log.e(TAG, "NeuralTranslator -- Exception: " + e);
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$clear$4(Task task) {
        try {
            this.languageDirectionStateMap = (Map) task.getResult();
            Log.i(TAG, "NeuralTranslator -- clear() - Available LanguageDirection list [(source, target)]: " + getAvailableLanguageDirectionStringList(LanguageDirectionState.AVAILABLE));
        } catch (RuntimeException e) {
            Log.e(TAG, "NeuralTranslator -- Exception: " + e);
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getAvailableLanguageDirectionStringList$9(LanguageDirectionState languageDirectionState, Map.Entry entry) {
        if (entry.getValue() == languageDirectionState) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSourceLanguageList$6(Map.Entry entry) {
        if (entry.getValue() == LanguageDirectionState.AVAILABLE || entry.getValue() == LanguageDirectionState.AVAILABLE_BY_PIVOT) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getTargetLanguageList$7(Map.Entry entry) {
        if (entry.getValue() == LanguageDirectionState.AVAILABLE || entry.getValue() == LanguageDirectionState.AVAILABLE_BY_PIVOT) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refresh$5(Task task) {
        try {
            this.languageDirectionStateMap = (Map) task.getResult();
            Log.i(TAG, "NeuralTranslator -- refresh() - Available LanguageDirection list [(source, target)]: " + getAvailableLanguageDirectionStringList(LanguageDirectionState.AVAILABLE));
            Log.i(TAG, "NeuralTranslator -- refresh() - Available by pivot LanguageDirection list [(source, target)]: " + getAvailableLanguageDirectionStringList(LanguageDirectionState.AVAILABLE_BY_PIVOT));
            Log.i(TAG, "NeuralTranslator -- refresh() - Available downloadable LanguageDirection list [(source, target)]: " + getAvailableLanguageDirectionStringList(LanguageDirectionState.DOWNLOADABLE));
        } catch (RuntimeException e) {
            Log.e(TAG, "NeuralTranslator -- Exception: " + e);
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$translate$0(LlmTranslationRequest llmTranslationRequest, Task task) {
        llmTranslationRequest.getOnSuccess().accept((Result) ((List) task.getResult()).get(0));
        llmTranslationRequest.deregistercallback();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$translate$1(LlmTranslationRequest llmTranslationRequest, Task task) {
        llmTranslationRequest.getOnFailure().accept(task.getException());
        llmTranslationRequest.deregistercallback();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$translate$2(Handler handler, LlmTranslationRequest llmTranslationRequest, NeuralTranslationRequest neuralTranslationRequest, Task task) {
        if (task.isSuccessful()) {
            Log.i(TAG, "NeuralTranslator -- llm translation success");
            handler.post(new h(llmTranslationRequest, task, 0));
            neuralTranslationRequest.deRegisterCallback();
            return;
        }
        Log.i(TAG, "NeuralTranslator -- llm translation failed with Exception");
        handler.post(new h(llmTranslationRequest, task, 1));
        neuralTranslationRequest.deRegisterCallback();
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> clear() {
        Log.i(TAG, "NeuralTranslator -- clear() executed");
        Task<Map<LanguageDirection, LanguageDirectionState>> executeClearAndRefresh = this.neuralTranslationRunnableExecutor.executeClearAndRefresh();
        executeClearAndRefresh.addOnCompleteListener(new f(this, 0));
        return executeClearAndRefresh;
    }

    public void close() {
        Log.i(TAG, "NeuralTranslator -- close() executed");
        NeuralTranslationRunnableExecutor neuralTranslationRunnableExecutor2 = this.neuralTranslationRunnableExecutor;
        if (neuralTranslationRunnableExecutor2 != null) {
            neuralTranslationRunnableExecutor2.deInit();
        }
        Translator translator = this.llmTranslator;
        if (translator != null) {
            translator.release();
        }
    }

    public Map<LanguageDirection, LanguageDirectionState> getLanguageDirectionStateMap() {
        Log.i(TAG, "NeuralTranslator -- getLanguageDirectionStateMap() executed");
        return this.languageDirectionStateMap;
    }

    public Task<String> getResourcePackPackageName(String str, String str2) {
        Log.i(TAG, "NeuralTranslator -- getResourcePackPackageName() executed");
        return this.neuralTranslationRunnableExecutor.executeGetResourcePackPackageName(str, str2);
    }

    public List<String> getSourceLanguageList() {
        Log.i(TAG, "NeuralTranslator -- getSourceLanguageList() executed");
        return (List) this.languageDirectionStateMap.entrySet().stream().filter(new a(2)).map(new C0431a(28)).map(new C0431a(27)).distinct().sorted().collect(Collectors.toList());
    }

    public Task<List<String>> getSpeechLlmSupportedSourceLanguage() {
        Log.i(TAG, "NeuralTranslator -- getSpeechLLMSupportedSourceLanguage() executed");
        return this.neuralTranslationRunnableExecutor.executeGetSpeechLlmSupportedSourceLanguage();
    }

    public Task<List<String>> getSpeechLlmSupportedTargetLanguage(String str) {
        Log.i(TAG, "NeuralTranslator -- getSpeechLLMSupportedTargetLanguage() executed");
        return this.neuralTranslationRunnableExecutor.executeGetSpeechLlmSupportedTargetLanguage(str);
    }

    public List<String> getTargetLanguageList(String str) {
        Log.i(TAG, "NeuralTranslator -- getTargetLanguageList() executed");
        return (List) this.languageDirectionStateMap.entrySet().stream().filter(new a(3)).map(new C0431a(28)).filter(new g(0, str)).map(new C0431a(29)).distinct().sorted().collect(Collectors.toList());
    }

    public Task<String> identifyLanguage(String str) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguage() executed - default");
        return identifyLanguage(str, "en");
    }

    public Task<List<b>> identifyLanguageAndGetCandidate(String str, Integer num, boolean z, boolean z3) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguageAndGetCandidate() executed");
        return this.neuralTranslationRunnableExecutor.executeLanguageIdentificationAndGetCandidate(str, num, Boolean.valueOf(z), Boolean.valueOf(z3));
    }

    public Task<String> identifyLanguagePackCode(String str) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguagePackCode() executed - default");
        return identifyLanguagePackCode(str, "en");
    }

    public Task<List<String>> identifyLanguageWithList(ArrayList<String> arrayList) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguageWithList() executed - default");
        return identifyLanguageWithList(arrayList, "en");
    }

    public boolean isAvailableDirection(LanguageDirection languageDirection) {
        Log.i(TAG, "NeuralTranslator -- isAvailableDirection() executed");
        LanguageDirectionState languageDirectionState = this.languageDirectionStateMap.get(languageDirection);
        if (languageDirectionState == LanguageDirectionState.AVAILABLE || languageDirectionState == LanguageDirectionState.AVAILABLE_BY_PIVOT) {
            return true;
        }
        return false;
    }

    public Task<Boolean> isTaggedTranslationSupported(String str, String str2) {
        Log.i(TAG, "NeuralTranslator -- isTaggedTranslationSupported() executed");
        return this.neuralTranslationRunnableExecutor.executeIsTaggedTranslationSupported(str, str2);
    }

    public Task<Boolean> prepareSpeechLlmTranslation(SpeechLlmPrePareInfo speechLlmPrePareInfo) {
        Log.i(TAG, "NeuralTranslator -- prepareSpeechLlmTranslation() executed");
        return this.neuralTranslationRunnableExecutor.executePrepareSpeechLlmTranslationRunnable(speechLlmPrePareInfo);
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> refresh() {
        Log.i(TAG, "NeuralTranslator -- refresh() executed");
        Task<Map<LanguageDirection, LanguageDirectionState>> executeRefresh = this.neuralTranslationRunnableExecutor.executeRefresh();
        executeRefresh.addOnCompleteListener(new f(this, 1));
        return executeRefresh;
    }

    public Task<Void> translate(NeuralTranslationRequest neuralTranslationRequest) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (without app info)");
        return translate((AppInfo) null, neuralTranslationRequest);
    }

    public Task<Void> translateByChunk(AppInfo appInfo, NeuralTranslationRequest neuralTranslationRequest) {
        Log.i(TAG, "NeuralTranslator -- translateByChunk() executed");
        if (Feature.checkFeature(this.context, Feature.FEATURE_NEURAL_TRANSLATION_BY_CHUNK) == 0) {
            return this.neuralTranslationRunnableExecutor.executeTranslationByChunkRunnable(neuralTranslationRequest, appInfo);
        }
        Log.i(TAG, "NeuralTranslator -- translate chunk not supported with service app");
        return this.neuralTranslationRunnableExecutor.executeTranslationRunnable(neuralTranslationRequest, appInfo);
    }

    public NeuralTranslator(Context context2, boolean z) {
        this.languageDirectionStateMap = new HashMap();
        this.neuralTranslationRunnableExecutor = NeuralTranslationRunnableExecutor.from(new NeuralTranslationServiceExecutor(context2, z));
        this.context = context2.getApplicationContext();
        this.llmTranslator = new Translator(context2);
    }

    public Task<String> identifyLanguage(String str, String str2) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguage() executed - fallbackLanguage: " + str2);
        return this.neuralTranslationRunnableExecutor.executeLanguageIdentification(str, str2);
    }

    public Task<String> identifyLanguagePackCode(String str, String str2) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguagePackCode() executed - fallbackLanguage: " + str2);
        return this.neuralTranslationRunnableExecutor.executeLanguagePackCodeIdentification(str, str2);
    }

    public Task<List<String>> identifyLanguageWithList(ArrayList<String> arrayList, String str) {
        Log.i(TAG, "NeuralTranslator -- identifyLanguageWithList() executed - fallbackLanguage: " + str);
        return this.neuralTranslationRunnableExecutor.executeLanguageIdentificationWithList(arrayList, str);
    }

    public void translate(AppInfo appInfo, NeuralTranslationRequest neuralTranslationRequest, LlmTranslationRequest llmTranslationRequest) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (with LLM request)");
        if (isLLMTranslationSupported(llmTranslationRequest, new Configuration(this.context))) {
            Log.i(TAG, "NeuralTranslator -- llm translation supported, run llm translation");
            this.llmTranslator.translate(copyAppInfoWithOnDeviceType(appInfo), llmTranslationRequest.getInputTextList(), llmTranslationRequest.getFromLanguage(), llmTranslationRequest.getToLanguage()).addOnCompleteListener(new Q((Object) new Handler(Looper.getMainLooper()), (Object) llmTranslationRequest, (Object) neuralTranslationRequest, 15));
            return;
        }
        Log.i(TAG, "NeuralTranslator -- llm translation not supported, run neuralTranslation");
        llmTranslationRequest.deregistercallback();
        translate(appInfo, neuralTranslationRequest);
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> clear(String str) {
        Task<Map<LanguageDirection, LanguageDirectionState>> task;
        Log.i(TAG, "NeuralTranslator -- clear() executed - SourceId : " + str);
        if (Feature.checkFeature(this.context, Feature.FEATURE_NEURAL_TRANSLATION_CLEAR_WITH_SOURCE_ID) == 0) {
            task = this.neuralTranslationRunnableExecutor.executeClearWithSourceId(str);
        } else {
            task = this.neuralTranslationRunnableExecutor.executeClearAndRefresh();
        }
        task.addOnCompleteListener(new f(this, 2));
        return task;
    }

    public Task<Void> translateByChunk(AppInfo appInfo, NeuralTranslationRequest neuralTranslationRequest, int i2) {
        Log.i(TAG, "NeuralTranslator -- translateByChunk() executed - chunkBaseLen: " + i2);
        if (Feature.checkFeature(this.context, Feature.FEATURE_NEURAL_TRANSLATION_BY_CHUNK) == 0) {
            return this.neuralTranslationRunnableExecutor.executeTranslationByChunkRunnable(neuralTranslationRequest, appInfo, i2);
        }
        Log.i(TAG, "NeuralTranslator -- translate chunk not supported with service app");
        return this.neuralTranslationRunnableExecutor.executeTranslationRunnable(neuralTranslationRequest, appInfo);
    }

    public Task<Result> translate(AppInfo appInfo, String str, Locale locale, Locale locale2) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (server request)");
        return this.llmTranslator.interpret(appInfo, str, locale, locale2);
    }

    public Task<Result> translate(AppInfo appInfo, String str, Locale locale, Locale locale2, Map<String, String> map) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (server request)");
        return this.llmTranslator.interpret(appInfo, str, locale, locale2, map);
    }

    public Task<Result> translate(AppInfo appInfo, String str, String str2, String str3) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (server request)");
        return this.llmTranslator.interpret(appInfo, str, str2, str3);
    }

    public Task<Result> translate(AppInfo appInfo, String str, String str2, String str3, Map<String, String> map) {
        Log.i(TAG, "NeuralTranslator -- translate() executed (server request)");
        return this.llmTranslator.interpret(appInfo, str, str2, str3, map);
    }

    public Task<Void> translate(AppInfo appInfo, NeuralTranslationRequest neuralTranslationRequest) {
        Log.i(TAG, "NeuralTranslator -- translate() executed");
        return this.neuralTranslationRunnableExecutor.executeTranslationRunnable(neuralTranslationRequest, appInfo);
    }

    public Task<Void> translate(AppInfo appInfo, NeuralTranslationRequest neuralTranslationRequest, Bundle bundle) {
        Log.i(TAG, "NeuralTranslator -- translate() executed");
        return this.neuralTranslationRunnableExecutor.executeTranslationRunnable(neuralTranslationRequest, bundle, appInfo);
    }
}
