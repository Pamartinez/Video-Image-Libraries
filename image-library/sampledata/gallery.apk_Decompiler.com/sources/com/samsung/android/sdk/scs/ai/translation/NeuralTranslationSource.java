package com.samsung.android.sdk.scs.ai.translation;

import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NeuralTranslationSource {
    /* access modifiers changed from: private */
    public Boolean appendMeta;
    /* access modifiers changed from: private */
    public int contextCandidate;
    /* access modifiers changed from: private */
    public boolean endOfContext;
    /* access modifiers changed from: private */
    public String fallbackLanguage;
    /* access modifiers changed from: private */
    public Boolean forcePivot;
    /* access modifiers changed from: private */
    public Boolean formality;
    /* access modifiers changed from: private */
    public String id;
    /* access modifiers changed from: private */
    public String mode;
    /* access modifiers changed from: private */
    public boolean needSentenceSplit;
    /* access modifiers changed from: private */
    public String requestingPackageName;
    /* access modifiers changed from: private */
    public String requestingSourceId;
    /* access modifiers changed from: private */
    public String sourceLanguage;
    /* access modifiers changed from: private */
    public String sourceText;
    /* access modifiers changed from: private */
    public String targetLanguage;
    /* access modifiers changed from: private */
    public Boolean verbose;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TranslationSourceBuilder {
        Boolean appendMeta;
        int contextCandidate;
        boolean endOfContext;
        String fallbackLanguage = "en";
        Boolean forcePivot;
        Boolean formality;
        String id = "";
        String mode;
        boolean needSentenceSplit;
        String requestingPackageName;
        String requestingSourceId;
        String sourceLanguage;
        String sourceText;
        String targetLanguage;
        Boolean verbose;

        public TranslationSourceBuilder(String str, String str2, String str3) {
            Boolean bool = Boolean.FALSE;
            this.verbose = bool;
            this.appendMeta = bool;
            this.formality = bool;
            this.mode = "plain";
            this.forcePivot = bool;
            this.requestingPackageName = "";
            this.needSentenceSplit = true;
            this.requestingSourceId = "";
            this.endOfContext = false;
            this.contextCandidate = 1;
            this.sourceText = str;
            this.sourceLanguage = str2;
            this.targetLanguage = str3;
        }

        public TranslationSourceBuilder appendMeta(Boolean bool) {
            this.appendMeta = bool;
            return this;
        }

        public NeuralTranslationSource build() {
            NeuralTranslationSource neuralTranslationSource = new NeuralTranslationSource(0);
            neuralTranslationSource.sourceText = this.sourceText;
            neuralTranslationSource.sourceLanguage = this.sourceLanguage;
            neuralTranslationSource.targetLanguage = this.targetLanguage;
            neuralTranslationSource.verbose = this.verbose;
            neuralTranslationSource.appendMeta = this.appendMeta;
            neuralTranslationSource.id = this.id;
            neuralTranslationSource.fallbackLanguage = this.fallbackLanguage;
            neuralTranslationSource.forcePivot = this.forcePivot;
            neuralTranslationSource.mode = this.mode;
            neuralTranslationSource.formality = this.formality;
            neuralTranslationSource.requestingPackageName = this.requestingPackageName;
            neuralTranslationSource.needSentenceSplit = this.needSentenceSplit;
            neuralTranslationSource.requestingSourceId = this.requestingSourceId;
            neuralTranslationSource.endOfContext = this.endOfContext;
            neuralTranslationSource.contextCandidate = this.contextCandidate;
            return neuralTranslationSource;
        }

        public TranslationSourceBuilder contextCandidate(int i2) {
            this.contextCandidate = i2;
            return this;
        }

        public TranslationSourceBuilder endOfContext(boolean z) {
            this.endOfContext = z;
            return this;
        }

        public TranslationSourceBuilder fallbackLanguage(String str) {
            this.fallbackLanguage = str;
            return this;
        }

        public TranslationSourceBuilder forcePivot(Boolean bool) {
            this.forcePivot = bool;
            return this;
        }

        public TranslationSourceBuilder formality(Boolean bool) {
            this.formality = bool;
            return this;
        }

        public TranslationSourceBuilder id(String str) {
            this.id = str;
            return this;
        }

        public TranslationSourceBuilder mode(String str) {
            this.mode = str;
            return this;
        }

        public TranslationSourceBuilder requestingPackageName(String str) {
            this.requestingPackageName = str;
            return this;
        }

        public TranslationSourceBuilder requestingSourceId(String str) {
            this.requestingSourceId = str;
            return this;
        }

        public TranslationSourceBuilder sentenceSplit(boolean z) {
            this.needSentenceSplit = z;
            return this;
        }

        public TranslationSourceBuilder verbose(Boolean bool) {
            this.verbose = bool;
            return this;
        }
    }

    public /* synthetic */ NeuralTranslationSource(int i2) {
        this();
    }

    public static TranslationSourceBuilder builder(String str, String str2, String str3) {
        return new TranslationSourceBuilder(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            NeuralTranslationSource neuralTranslationSource = (NeuralTranslationSource) obj;
            if (!Objects.equals(this.sourceLanguage, neuralTranslationSource.sourceLanguage) || !Objects.equals(this.targetLanguage, neuralTranslationSource.targetLanguage) || !Objects.equals(this.sourceText, neuralTranslationSource.sourceText) || !Objects.equals(this.id, neuralTranslationSource.id) || !Objects.equals(this.fallbackLanguage, neuralTranslationSource.fallbackLanguage) || !Objects.equals(this.verbose, neuralTranslationSource.verbose) || !Objects.equals(this.appendMeta, neuralTranslationSource.appendMeta) || !Objects.equals(this.formality, neuralTranslationSource.formality) || !Objects.equals(this.mode, neuralTranslationSource.mode) || !Objects.equals(this.forcePivot, neuralTranslationSource.forcePivot) || !Objects.equals(this.requestingPackageName, neuralTranslationSource.requestingPackageName) || !Boolean.valueOf(this.needSentenceSplit).equals(Boolean.valueOf(neuralTranslationSource.needSentenceSplit)) || !Objects.equals(this.requestingSourceId, neuralTranslationSource.requestingSourceId) || !Boolean.valueOf(this.endOfContext).equals(Boolean.valueOf(neuralTranslationSource.endOfContext)) || !Integer.valueOf(this.contextCandidate).equals(Integer.valueOf(neuralTranslationSource.contextCandidate))) {
                return false;
            }
            return true;
        }
        return false;
    }

    public Boolean getAppendMeta() {
        return this.appendMeta;
    }

    public int getContextCandidate() {
        return this.contextCandidate;
    }

    public boolean getEndOfContext() {
        return this.endOfContext;
    }

    public String getFallbackLanguage() {
        return this.fallbackLanguage;
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

    public String getMode() {
        return this.mode;
    }

    public String getRequestingPackageName() {
        return this.requestingPackageName;
    }

    public String getRequestingSourceId() {
        return this.requestingSourceId;
    }

    public boolean getSentenceSplit() {
        return this.needSentenceSplit;
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

    public Boolean getVerbose() {
        return this.verbose;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.sourceLanguage, this.targetLanguage, this.sourceText, this.id, this.fallbackLanguage, this.verbose, this.appendMeta, this.formality, this.mode, this.forcePivot, this.requestingPackageName, Boolean.valueOf(this.needSentenceSplit), this.requestingSourceId, Boolean.valueOf(this.endOfContext), Integer.valueOf(this.contextCandidate)});
    }

    private NeuralTranslationSource() {
    }
}
