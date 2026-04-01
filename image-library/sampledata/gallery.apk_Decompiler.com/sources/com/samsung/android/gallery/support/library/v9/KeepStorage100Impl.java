package com.samsung.android.gallery.support.library.v9;

import android.util.Log;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class KeepStorage100Impl implements KeepStorage {
    volatile boolean isCreated;
    volatile boolean lockState = true;
    final AtomicInteger unlockCount = new AtomicInteger(0);

    public String create() {
        boolean z;
        if (!this.isCreated) {
            File file = new File("/data/sec/gallery/secured");
            if (file.exists() || file.mkdirs()) {
                z = true;
            } else {
                z = false;
            }
            this.isCreated = z;
            this.lockState = true;
        }
        return "/data/sec/gallery/secured";
    }

    public String getRoot() {
        return "/data/sec/gallery/secured";
    }

    public boolean isLocked() {
        return this.lockState;
    }

    public boolean lock() {
        if (this.unlockCount.get() > 0) {
            if (this.unlockCount.decrementAndGet() == 0) {
                this.lockState = true;
            }
            return true;
        }
        Log.e("KeepStorageImpl(P)", "lock failed. wrong value " + this);
        return false;
    }

    public String toString() {
        char c5;
        StringBuilder sb2 = new StringBuilder("PS[");
        if (this.lockState) {
            c5 = 'L';
        } else {
            c5 = 'U';
        }
        sb2.append(c5);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.unlockCount);
        sb2.append("]");
        return sb2.toString();
    }

    public boolean unlock() {
        if (this.unlockCount.getAndIncrement() != 0) {
            return true;
        }
        this.lockState = false;
        return true;
    }
}
