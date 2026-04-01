package com.samsung.android.sdk.scs.ai.translation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NeuralTranslationResult {
    /* access modifiers changed from: private */
    public Boolean appendMeta;
    /* access modifiers changed from: private */
    public Boolean forcePivot;
    /* access modifiers changed from: private */
    public Boolean formality;
    /* access modifiers changed from: private */
    public String id;
    /* access modifiers changed from: private */
    public String interimResult;
    /* access modifiers changed from: private */
    public Boolean isComplete;
    /* access modifiers changed from: private */
    public String sourceLanguage;
    /* access modifiers changed from: private */
    public String sourceText;
    /* access modifiers changed from: private */
    public String targetLanguage;
    /* access modifiers changed from: private */
    public String targetText;
    /* access modifiers changed from: private */
    public Boolean verbose;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TranslationResultBuilder {
        private Boolean appendMeta;
        private Boolean forcePivot;
        private Boolean formality;
        private String id = "";
        private String interimResult;
        private Boolean isComplete;
        private String sourceLanguage = "";
        private String sourceText = "";
        private String targetLanguage;
        private String targetText = "";
        private Boolean verbose;

        public TranslationResultBuilder() {
            Boolean bool = Boolean.FALSE;
            this.verbose = bool;
            this.appendMeta = bool;
            this.formality = bool;
            this.forcePivot = bool;
            this.interimResult = "";
            this.isComplete = bool;
        }

        public TranslationResultBuilder appendMeta(Boolean bool) {
            this.appendMeta = bool;
            return this;
        }

        public NeuralTranslationResult build() {
            NeuralTranslationResult neuralTranslationResult = new NeuralTranslationResult();
            neuralTranslationResult.sourceText = this.sourceText;
            neuralTranslationResult.targetText = this.targetText;
            neuralTranslationResult.sourceLanguage = this.sourceLanguage;
            neuralTranslationResult.targetLanguage = this.targetLanguage;
            neuralTranslationResult.id = this.id;
            neuralTranslationResult.verbose = this.verbose;
            neuralTranslationResult.appendMeta = this.appendMeta;
            neuralTranslationResult.formality = this.formality;
            neuralTranslationResult.forcePivot = this.forcePivot;
            neuralTranslationResult.interimResult = this.interimResult;
            neuralTranslationResult.isComplete = this.isComplete;
            return neuralTranslationResult;
        }

        public TranslationResultBuilder forcePivot(Boolean bool) {
            this.forcePivot = bool;
            return this;
        }

        public TranslationResultBuilder formality(Boolean bool) {
            this.formality = bool;
            return this;
        }

        public TranslationResultBuilder id(String str) {
            this.id = str;
            return this;
        }

        public TranslationResultBuilder interimResult(String str) {
            this.interimResult = str;
            return this;
        }

        public TranslationResultBuilder isComplete(Boolean bool) {
            this.isComplete = bool;
            return this;
        }

        public TranslationResultBuilder sourceLanguage(String str) {
            this.sourceLanguage = str;
            return this;
        }

        public TranslationResultBuilder sourceText(String str) {
            this.sourceText = str;
            return this;
        }

        public TranslationResultBuilder targetLanguage(String str) {
            this.targetLanguage = str;
            return this;
        }

        public TranslationResultBuilder targetText(String str) {
            this.targetText = str;
            return this;
        }

        public TranslationResultBuilder verbose(Boolean bool) {
            this.verbose = bool;
            return this;
        }
    }

    public static TranslationResultBuilder builder() {
        return new TranslationResultBuilder();
    }

    public Boolean getAppendMeta() {
        return this.appendMeta;
    }

    public Boolean getForcePivot() {
        return this.forcePivot;
    }

    public Boolean getFormality() {
        return this.formality;
    }

    public String getId() {
        return this.id;
    }

    public String getInterimResult() {
        return this.interimResult;
    }

    public String getSourceLanguage() {
        return this.sourceLanguage;
    }

    public String getSourceText() {
        return this.sourceText;
    }

    public String getTargetLanguage() {
        return this.targetLanguage;
    }

    public String getTargetText() {
        return this.targetText;
    }

    public Boolean getVerbose() {
        return this.verbose;
    }

    public boolean isComplete() {
        return this.isComplete.booleanValue();
    }
}
