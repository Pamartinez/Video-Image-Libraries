package com.samsung.android.gallery.support.search;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum LlmQpOperation {
    DISABLE("disable"),
    ENABLE("enable"),
    FORCE_ENABLE("force_enable");
    
    private final String mValue;

    private LlmQpOperation(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public static LlmQpOperation valueOf(int i2) {
        LlmQpOperation llmQpOperation = DISABLE;
        if (i2 == llmQpOperation.ordinal()) {
            return llmQpOperation;
        }
        LlmQpOperation llmQpOperation2 = FORCE_ENABLE;
        if (i2 == llmQpOperation2.ordinal()) {
            return llmQpOperation2;
        }
        return ENABLE;
    }
}
