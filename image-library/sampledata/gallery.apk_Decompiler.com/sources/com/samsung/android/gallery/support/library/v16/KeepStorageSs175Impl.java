package com.samsung.android.gallery.support.library.v16;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class KeepStorageSs175Impl extends KeepStorage175Impl {
    public boolean initUnlockState() {
        if (super.initUnlockState() || this.storageManager.unlock() == 0) {
            return true;
        }
        return false;
    }

    public boolean lock() {
        return true;
    }
}
