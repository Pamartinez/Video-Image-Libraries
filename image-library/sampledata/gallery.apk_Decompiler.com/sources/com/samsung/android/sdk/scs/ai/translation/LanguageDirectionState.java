package com.samsung.android.sdk.scs.ai.translation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum LanguageDirectionState {
    UNKNOWN(-1),
    AVAILABLE(0),
    AVAILABLE_BY_PIVOT(1),
    DOWNLOADABLE(2),
    UNAUTHORIZED_RESOURCE(3);
    
    private final int value;

    private LanguageDirectionState(int i2) {
        this.value = i2;
    }

    public static LanguageDirectionState from(int i2) {
        for (LanguageDirectionState languageDirectionState : values()) {
            if (languageDirectionState.value() == i2) {
                return languageDirectionState;
            }
        }
        return UNKNOWN;
    }

    public int value() {
        return this.value;
    }
}
