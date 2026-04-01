package com.samsung.android.sdk.scs.ai.language;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SmartReplyCategory {
    LONG("LONG"),
    SHORT("SHORT"),
    CONTEXTUAL("CONTEXTUAL");
    
    private final String name;

    private SmartReplyCategory(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
