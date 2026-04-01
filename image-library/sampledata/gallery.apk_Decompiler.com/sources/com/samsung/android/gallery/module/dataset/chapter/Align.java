package com.samsung.android.gallery.module.dataset.chapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Align {
    START,
    MIDDLE,
    END;

    public boolean end() {
        if (this == END) {
            return true;
        }
        return false;
    }

    public boolean middle() {
        if (this == MIDDLE) {
            return true;
        }
        return false;
    }

    public boolean start() {
        if (this == START) {
            return true;
        }
        return false;
    }
}
