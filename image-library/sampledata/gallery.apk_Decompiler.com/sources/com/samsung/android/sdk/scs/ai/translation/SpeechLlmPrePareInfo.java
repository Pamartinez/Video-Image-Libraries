package com.samsung.android.sdk.scs.ai.translation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpeechLlmPrePareInfo {
    private String sourceLanguage;
    private String targetLanguage;

    public SpeechLlmPrePareInfo(String str, String str2) {
        this.sourceLanguage = str;
        this.targetLanguage = str2;
    }

    public String getSourceLanguage() {
        return this.sourceLanguage;
    }

    public String getTargetLanguage() {
        return this.targetLanguage;
    }
}
