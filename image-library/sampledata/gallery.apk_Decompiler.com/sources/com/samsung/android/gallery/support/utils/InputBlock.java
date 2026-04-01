package com.samsung.android.gallery.support.utils;

import com.samsung.android.gallery.support.utils.Timer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InputBlock {
    private final int INPUT_BLOCK_TIMER_KEY = Timer.getTimerId();
    private final Object LOCK = new Object();
    private volatile String mInputBlockTag;
    private final Timer.OnTimer mReleaseCallback = new C0682u(this);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final InputBlock sInstance = new InputBlock();
    }

    public static InputBlock getInstance() {
        return LazyHolder.sInstance;
    }

    public boolean isBlocked() {
        boolean z;
        synchronized (this.LOCK) {
            if (this.mInputBlockTag != null) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public boolean isBlockedExceptBackKey() {
        boolean z;
        synchronized (this.LOCK) {
            try {
                if (this.mInputBlockTag == null || "BackKeyBlock".equals(this.mInputBlockTag)) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public void release(int i2) {
        if (i2 == -1) {
            Log.d("InputBlock", "force release input block");
        }
        synchronized (this.LOCK) {
            this.mInputBlockTag = null;
        }
    }

    public boolean set(String str) {
        return set(str, 300);
    }

    public boolean setAndGet(String str) {
        synchronized (this.LOCK) {
            try {
                if (this.mInputBlockTag != null) {
                    return false;
                }
                this.mInputBlockTag = str;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean set(String str, long j2) {
        if (setAndGet(str)) {
            Timer.startTimer(this.INPUT_BLOCK_TIMER_KEY, j2, this.mReleaseCallback);
            return true;
        }
        Log.w("InputBlock", "blocked by [" + this.mInputBlockTag + "], ignore " + str);
        return false;
    }
}
