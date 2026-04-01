package com.samsung.android.sdk.scs.ai.translation;

import com.samsung.android.sdk.scs.ai.language.Result;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LlmTranslationRequest {
    /* access modifiers changed from: private */
    public Locale fromLanguage;
    /* access modifiers changed from: private */
    public List<String> inputTextList;
    /* access modifiers changed from: private */
    public Consumer<Exception> onFailure;
    /* access modifiers changed from: private */
    public Consumer<Result> onSuccess;
    /* access modifiers changed from: private */
    public Locale toLanguage;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BuildStep {
        LlmTranslationRequest build();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FromLanguageStep {
        ToLanguageStep fromLanguage(Locale locale);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface InputTextStep {
        FromLanguageStep inputText(String str);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LlmTranslationRequestBuilder implements InputTextStep, FromLanguageStep, ToLanguageStep, OnSuccessCallbackStep, OnFailureCallbackStep, BuildStep {
        private Locale fromLanguage;
        private String inputText;
        private Consumer<Exception> onFailure;
        private Consumer<Result> onSuccess;
        private Locale toLanguage;

        public LlmTranslationRequest build() {
            LlmTranslationRequest llmTranslationRequest = new LlmTranslationRequest();
            llmTranslationRequest.inputTextList = List.of(this.inputText);
            llmTranslationRequest.fromLanguage = this.fromLanguage;
            llmTranslationRequest.toLanguage = this.toLanguage;
            llmTranslationRequest.onSuccess = this.onSuccess;
            llmTranslationRequest.onFailure = this.onFailure;
            return llmTranslationRequest;
        }

        public ToLanguageStep fromLanguage(Locale locale) {
            this.fromLanguage = locale;
            return this;
        }

        public FromLanguageStep inputText(String str) {
            this.inputText = str;
            return this;
        }

        public BuildStep onFailure(Consumer<Exception> consumer) {
            this.onFailure = consumer;
            return this;
        }

        public OnFailureCallbackStep onSuccess(Consumer<Result> consumer) {
            this.onSuccess = consumer;
            return this;
        }

        public OnSuccessCallbackStep toLanguage(Locale locale) {
            this.toLanguage = locale;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnFailureCallbackStep {
        BuildStep onFailure(Consumer<Exception> consumer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnSuccessCallbackStep {
        OnFailureCallbackStep onSuccess(Consumer<Result> consumer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ToLanguageStep {
        OnSuccessCallbackStep toLanguage(Locale locale);
    }

    public static InputTextStep builder() {
        return new LlmTranslationRequestBuilder();
    }

    public void deregistercallback() {
        this.onSuccess = null;
        this.onFailure = null;
    }

    public Locale getFromLanguage() {
        return this.fromLanguage;
    }

    public List<String> getInputTextList() {
        return this.inputTextList;
    }

    public Consumer<Exception> getOnFailure() {
        return this.onFailure;
    }

    public Consumer<Result> getOnSuccess() {
        return this.onSuccess;
    }

    public Locale getToLanguage() {
        return this.toLanguage;
    }
}
