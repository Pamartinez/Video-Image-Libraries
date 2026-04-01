package com.samsung.android.gallery.module.dataset.chapter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Layout {
    REAL_RATIO,
    FULL,
    LARGE,
    AVERAGE,
    SMALL;

    public boolean average() {
        if (this == AVERAGE) {
            return true;
        }
        return false;
    }

    public boolean full() {
        if (this == FULL) {
            return true;
        }
        return false;
    }

    public boolean large() {
        if (this == LARGE) {
            return true;
        }
        return false;
    }

    public boolean small() {
        if (this == SMALL) {
            return true;
        }
        return false;
    }
}
