package com.samsung.android.gallery.support.library.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface KeepStorage {
    String create() {
        return null;
    }

    String getRoot() {
        return null;
    }

    boolean isLocked() {
        return !isUnlocked();
    }

    boolean isUnlocked() {
        return true;
    }

    boolean lock() {
        return true;
    }

    boolean prepare() {
        return true;
    }

    boolean unlock() {
        return true;
    }
}
