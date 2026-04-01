package com.samsung.android.gallery.app.ui.viewer2.aiedit.items;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ExecuteTriggerType {
    DEFAULT,
    COLLAPSE_ONLY,
    COLLAPSE_ONLY_KEEP_DECOR,
    COLLAPSE_WITH_TRANSPARENT_PROGRESS,
    COLLAPSE_WITH_AI_VISUAL_PROGRESS;

    public static ExecuteTriggerType get(int i2) {
        if (i2 < 0 || i2 >= values().length) {
            return DEFAULT;
        }
        return values()[i2];
    }

    public boolean isNeedCollapse() {
        if (this != DEFAULT) {
            return true;
        }
        return false;
    }

    public boolean needHideDecor() {
        if (this != COLLAPSE_ONLY_KEEP_DECOR) {
            return true;
        }
        return false;
    }
}
