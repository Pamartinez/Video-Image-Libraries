package com.samsung.android.gallery.support.library.v16;

import android.text.TextUtils;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.security.SemPassStorageManager;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class KeepStorage175Impl implements KeepStorage {
    private final Object LOCK = new Object();
    protected final String TAG = getClass().getSimpleName();
    volatile String root;
    protected final PassStorageManager storageManager = new PassStorageManager();
    volatile boolean unlockState;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class PassStorageManager {
        final SemPassStorageManager storageManager = SemPassStorageManager.getInstance();

        public int create() {
            return this.storageManager.create(1);
        }

        public String getPath() {
            return this.storageManager.getPath(1);
        }

        public boolean isUnlocked() {
            return this.storageManager.isUnlocked(1);
        }

        public int prepare() {
            return this.storageManager.prepareStorage();
        }

        public int unlock() {
            return this.storageManager.unlock(1);
        }
    }

    public String create() {
        int i2;
        if (this.root == null) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.root = this.storageManager.getPath();
                if (TextUtils.isEmpty(this.root)) {
                    i2 = this.storageManager.create();
                    this.root = this.storageManager.getPath();
                } else {
                    i2 = 1;
                }
                if (!TextUtils.isEmpty(this.root)) {
                    synchronized (this.LOCK) {
                        this.unlockState = initUnlockState();
                    }
                }
                String str = this.TAG;
                Log.d(str, "create {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Error | Exception e) {
                C0212a.y(e, new StringBuilder("create failed. e="), this.TAG);
            }
        }
        return this.root;
    }

    public String getRoot() {
        return this.root;
    }

    public boolean initUnlockState() {
        return this.storageManager.isUnlocked();
    }

    public final boolean isLocked() {
        return !isUnlocked();
    }

    public final boolean isUnlocked() {
        boolean z;
        synchronized (this.LOCK) {
            z = this.unlockState;
        }
        return z;
    }

    public boolean prepare() {
        long currentTimeMillis = System.currentTimeMillis();
        int prepare = this.storageManager.prepare();
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(prepare, "prepare {", "} +");
        o2.append(System.currentTimeMillis() - currentTimeMillis);
        Log.d(str, o2.toString());
        if (prepare == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        char c5;
        StringBuilder sb2 = new StringBuilder("PS[");
        if (this.root != null) {
            str = "T";
        } else {
            str = "F";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.unlockState) {
            c5 = 'U';
        } else {
            c5 = 'L';
        }
        sb2.append(c5);
        sb2.append("]");
        return sb2.toString();
    }

    public boolean unlock() {
        if (isUnlocked()) {
            return true;
        }
        try {
            int unlock = this.storageManager.unlock();
            if (unlock == 0) {
                synchronized (this.LOCK) {
                    this.unlockState = true;
                }
                return true;
            }
            String str = this.TAG;
            Log.e(str, "unlock failed {" + unlock + "} " + this);
            return false;
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("unlock failed. e="), this.TAG);
            return false;
        }
    }
}
