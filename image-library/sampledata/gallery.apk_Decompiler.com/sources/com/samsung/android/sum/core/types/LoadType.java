package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum LoadType {
    NONE,
    LAZY,
    INSTANT,
    CACHED;

    public boolean isCached() {
        if (this == CACHED) {
            return true;
        }
        return false;
    }

    public boolean isInstant() {
        if (this == INSTANT) {
            return true;
        }
        return false;
    }

    public boolean isLazy() {
        if (this == LAZY) {
            return true;
        }
        return false;
    }
}
