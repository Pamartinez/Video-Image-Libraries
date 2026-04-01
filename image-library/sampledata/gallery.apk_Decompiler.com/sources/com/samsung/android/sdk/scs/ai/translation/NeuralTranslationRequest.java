package com.samsung.android.sdk.scs.ai.translation;

import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NeuralTranslationRequest {
    /* access modifiers changed from: private */
    public Consumer<NeuralTranslationErrorCode> onFailure;
    /* access modifiers changed from: private */
    public Consumer<NeuralTranslationResult> onSuccess;
    /* access modifiers changed from: private */
    public NeuralTranslationSource source;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BuildStep {
        NeuralTranslationRequest build();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface FailureCallbackStep {
        BuildStep onFailure(Consumer<NeuralTranslationErrorCode> consumer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SourceStep {
        SuccessCallbackStep source(NeuralTranslationSource neuralTranslationSource);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface SuccessCallbackStep {
        FailureCallbackStep onSuccess(Consumer<NeuralTranslationResult> consumer);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TranslationRequestBuilder implements SourceStep, SuccessCallbackStep, FailureCallbackStep, BuildStep {
        Consumer<NeuralTranslationErrorCode> onFailure;
        Consumer<NeuralTranslationResult> onSuccess;
        NeuralTranslationSource source;

        public NeuralTranslationRequest build() {
            NeuralTranslationRequest neuralTranslationRequest = new NeuralTranslationRequest(0);
            neuralTranslationRequest.source = this.source;
            neuralTranslationRequest.onSuccess = this.onSuccess;
            neuralTranslationRequest.onFailure = this.onFailure;
            return neuralTranslationRequest;
        }

        public BuildStep onFailure(Consumer<NeuralTranslationErrorCode> consumer) {
            this.onFailure = consumer;
            return this;
        }

        public FailureCallbackStep onSuccess(Consumer<NeuralTranslationResult> consumer) {
            this.onSuccess = consumer;
            return this;
        }

        public SuccessCallbackStep source(NeuralTranslationSource neuralTranslationSource) {
            this.source = neuralTranslationSource;
            return this;
        }
    }

    public /* synthetic */ NeuralTranslationRequest(int i2) {
        this();
    }

    public static SourceStep builder() {
        return new TranslationRequestBuilder();
    }

    public void deRegisterCallback() {
        this.onSuccess = null;
        this.onFailure = null;
    }

    public Consumer<NeuralTranslationErrorCode> getOnFailure() {
        return this.onFailure;
    }

    public Consumer<NeuralTranslationResult> getOnSuccess() {
        return this.onSuccess;
    }

    public NeuralTranslationSource getSource() {
        return this.source;
    }

    private NeuralTranslationRequest() {
    }
}
