package com.samsung.android.gallery.module.concurrent;

import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RwLock {
    private static final boolean ENABLE_TAG_LOG = (!Features.isEnabled(Features.IS_SEP_LITE));
    private final Semaphore mReadSemaphore = null;
    private final ConcurrentHashMap<String, Long> mReadTagMap = null;
    private final Semaphore mWriteSemaphore = new Semaphore(1);

    public boolean acquireReadLock(String str) {
        return true;
    }

    public boolean acquireWriteLock() {
        int i2;
        try {
            if (ThreadUtil.isMainThread()) {
                i2 = 5;
            } else {
                i2 = 30;
            }
            if (this.mWriteSemaphore.tryAcquire(1, (long) i2, TimeUnit.SECONDS)) {
                return true;
            }
            Log.e("RwLock", "fail to get write Lock : " + this);
            new InternalException("FAIL GET WRITE_LOCK V2").post();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void releaseWriteLock() {
        this.mWriteSemaphore.release();
    }

    public String toString() {
        return "RwLock@" + Integer.toHexString(hashCode()) + '{' + this.mWriteSemaphore.availablePermits() + '}';
    }

    public void releaseReadLock(String str) {
    }
}
