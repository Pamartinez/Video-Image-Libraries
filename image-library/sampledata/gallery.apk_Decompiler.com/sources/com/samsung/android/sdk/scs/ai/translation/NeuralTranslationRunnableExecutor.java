package com.samsung.android.sdk.scs.ai.translation;

import android.os.Bundle;
import com.samsung.android.sdk.scs.ai.language.AppInfo;
import com.samsung.android.sdk.scs.base.tasks.Task;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.sivs.ai.sdkcommon.translation.LanguageDirection;
import com.samsung.android.sivs.ai.sdkcommon.translation.b;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NeuralTranslationRunnableExecutor {
    NeuralTranslationServiceExecutor serviceExecutor;

    public NeuralTranslationRunnableExecutor(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor) {
        this.serviceExecutor = neuralTranslationServiceExecutor;
    }

    private <T> Task<T> executeAndGetTask(TaskRunnable<T> taskRunnable) {
        this.serviceExecutor.execute(taskRunnable);
        return taskRunnable.getTask();
    }

    public static NeuralTranslationRunnableExecutor from(NeuralTranslationServiceExecutor neuralTranslationServiceExecutor) {
        return new NeuralTranslationRunnableExecutor(neuralTranslationServiceExecutor);
    }

    public void deInit() {
        this.serviceExecutor.deInit();
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> executeClearAndRefresh() {
        return executeAndGetTask(new ClearAndRefreshNeuralTranslatorRunnable(this.serviceExecutor));
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> executeClearWithSourceId(String str) {
        return executeAndGetTask(new ClearWithSourceIdRunnable(this.serviceExecutor, str));
    }

    public Task<String> executeGetResourcePackPackageName(String str, String str2) {
        return executeAndGetTask(new GetResourcePackPackageNameRunnable(this.serviceExecutor, str, str2));
    }

    public Task<List<String>> executeGetSpeechLlmSupportedSourceLanguage() {
        return executeAndGetTask(new GetSpeechLlmSupportedSourceLanguageRunnable(this.serviceExecutor));
    }

    public Task<List<String>> executeGetSpeechLlmSupportedTargetLanguage(String str) {
        return executeAndGetTask(new GetSpeechLlmSupportedTargetLanguageRunnable(this.serviceExecutor, str));
    }

    public Task<Boolean> executeIsTaggedTranslationSupported(String str, String str2) {
        return executeAndGetTask(new IsTaggedTranslationSupportedRunnable(this.serviceExecutor, str, str2));
    }

    public Task<String> executeLanguageIdentification(String str, String str2) {
        return executeAndGetTask(new LanguageIdentificationRunnable(this.serviceExecutor, str, str2));
    }

    public Task<List<b>> executeLanguageIdentificationAndGetCandidate(String str, Integer num, Boolean bool, Boolean bool2) {
        return executeAndGetTask(new LanguageIdentificationAndGetCandidateRunnable(this.serviceExecutor, str, num, bool.booleanValue(), bool2.booleanValue()));
    }

    public Task<List<String>> executeLanguageIdentificationWithList(ArrayList<String> arrayList, String str) {
        return executeAndGetTask(new LanguageIdentificationWithListRunnable(this.serviceExecutor, arrayList, str));
    }

    public Task<String> executeLanguagePackCodeIdentification(String str, String str2) {
        return executeAndGetTask(new LanguagePackCodeIdentificationRunnable(this.serviceExecutor, str, str2));
    }

    public Task<Boolean> executePrepareSpeechLlmTranslationRunnable(SpeechLlmPrePareInfo speechLlmPrePareInfo) {
        return executeAndGetTask(new PrepareSpeechLlmTranslationRunnable(this.serviceExecutor, speechLlmPrePareInfo));
    }

    public Task<Map<LanguageDirection, LanguageDirectionState>> executeRefresh() {
        return executeAndGetTask(new RefreshNeuralTranslatorRunnable(this.serviceExecutor));
    }

    public Task<Void> executeTranslationByChunkRunnable(NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo) {
        return executeAndGetTask(new NeuralTranslationByChunkRunnable(this.serviceExecutor, neuralTranslationRequest, appInfo));
    }

    public Task<Void> executeTranslationRunnable(NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo) {
        return executeAndGetTask(new NeuralTranslationRunnable(this.serviceExecutor, neuralTranslationRequest, appInfo));
    }

    public Task<Void> executeTranslationByChunkRunnable(NeuralTranslationRequest neuralTranslationRequest, AppInfo appInfo, int i2) {
        return executeAndGetTask(new NeuralTranslationByChunkRunnable(this.serviceExecutor, neuralTranslationRequest, appInfo, i2));
    }

    public Task<Void> executeTranslationRunnable(NeuralTranslationRequest neuralTranslationRequest, Bundle bundle, AppInfo appInfo) {
        return executeAndGetTask(new NeuralTranslationRunnable(this.serviceExecutor, neuralTranslationRequest, bundle, appInfo));
    }
}
