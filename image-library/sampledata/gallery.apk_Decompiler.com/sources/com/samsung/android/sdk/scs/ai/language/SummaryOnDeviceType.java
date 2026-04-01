package com.samsung.android.sdk.scs.ai.language;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SummaryOnDeviceType {
    NOTE("Note"),
    ST_ENERGY("StEnergy"),
    VOICE_RECORDER_KEYWORDS("Keywords"),
    TITLE("Title"),
    ST_SOUND("StSound"),
    ACTION_POINT("ActionPoint");
    
    private final String name;

    private SummaryOnDeviceType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
