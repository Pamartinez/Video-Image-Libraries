package com.samsung.android.gallery.module.secured;

import A.a;
import A8.C0545b;
import c0.C0086a;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.KeepStorage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.scsp.media.api.d;
import i.C0212a;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class KeepStorageManager {
    volatile String mFilesRoot;
    volatile String mKeepRoot;
    final KeepStorage mKeepStorage = SeApiCompat.getPassStorage();
    volatile String mNdeRoot;
    final ConcurrentHashMap<String, AtomicInteger> mReferenceSet = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final KeepStorageManager instance = new KeepStorageManager();
    }

    public static KeepStorageManager getInstance() {
        return LazyHolder.instance;
    }

    private void init() {
        long currentTimeMillis = System.currentTimeMillis();
        String root = this.mKeepStorage.getRoot();
        if (root == null) {
            root = this.mKeepStorage.create();
        }
        this.mKeepRoot = root;
        String deviceSerial = DeviceInfo.getDeviceSerial();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mKeepRoot);
        String str = File.separator;
        this.mFilesRoot = C0212a.q(sb2, str, deviceSerial, str, "files");
        StringBuilder sb3 = new StringBuilder();
        C0086a.z(sb3, this.mKeepRoot, str, deviceSerial, str);
        sb3.append("nde");
        this.mNdeRoot = sb3.toString();
        boolean mkdirsIfAbsent = mkdirsIfAbsent(this.mFilesRoot);
        boolean mkdirsIfAbsent2 = mkdirsIfAbsent(this.mNdeRoot);
        a.A(new Object[]{Logger.getEncodedString(deviceSerial), Boolean.valueOf(mkdirsIfAbsent), Boolean.valueOf(mkdirsIfAbsent2), Long.valueOf(currentTimeMillis)}, new StringBuilder("init"), "KeepStorageManager");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AtomicInteger lambda$getCounter$0(String str) {
        return new AtomicInteger(0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$toString$1(Map.Entry entry) {
        return ((String) entry.getKey()) + "=" + entry.getValue();
    }

    private boolean mkdirsIfAbsent(String str) {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        Log.e("KeepStorageManager", "mkdirsIfAbsent failed " + file.getName());
        return false;
    }

    public boolean close(String str) {
        if (getCounter(str).decrementAndGet() == 0 && sumCounter() == 0) {
            long currentTimeMillis = System.currentTimeMillis();
            boolean lock = this.mKeepStorage.lock();
            StringBuilder sb2 = new StringBuilder("close ");
            sb2.append(this);
            sb2.append(" +");
            a.x(sb2, currentTimeMillis, "KeepStorageManager");
            return lock;
        }
        Log.d("KeepStorageManager", "close skip " + this);
        return true;
    }

    public void closeSession(String str) {
        if (sumCounter() == 0 && !isLocked()) {
            this.mKeepStorage.lock();
        }
        Log.d("KeepStorageManager", "closeSession", str, this);
    }

    public AtomicInteger getCounter(String str) {
        return this.mReferenceSet.computeIfAbsent(str, new d(5));
    }

    public String getFilesPath() {
        if (this.mFilesRoot == null) {
            init();
        }
        return this.mFilesRoot;
    }

    public String getNdePath() {
        if (this.mNdeRoot == null) {
            init();
        }
        return this.mNdeRoot;
    }

    public String getRoot() {
        if (this.mKeepRoot == null) {
            init();
        }
        return this.mKeepRoot;
    }

    public boolean isLocked() {
        return this.mKeepStorage.isLocked();
    }

    public boolean open(String str) {
        if (!(getCounter(str).getAndIncrement() == 0 && sumCounter() == 1) && !this.mKeepStorage.isLocked()) {
            Log.d("KeepStorageManager", "open skip " + this);
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean unlock = this.mKeepStorage.unlock();
        StringBuilder sb2 = new StringBuilder("open ");
        sb2.append(this);
        sb2.append(" +");
        a.x(sb2, currentTimeMillis, "KeepStorageManager");
        return unlock;
    }

    public void openSession(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if ((this.mReferenceSet.isEmpty() || this.mKeepRoot == null) && this.mKeepRoot == null) {
            init();
        }
        getCounter(str);
        a.A(new Object[]{str, this, Long.valueOf(currentTimeMillis)}, new StringBuilder("openSession"), "KeepStorageManager");
    }

    public int sumCounter() {
        return this.mReferenceSet.values().stream().mapToInt(new C0545b(24)).sum();
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("PSM{");
        if (this.mKeepStorage.isLocked()) {
            str = "L";
        } else {
            str = "U";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        return C0212a.p(sb2, (String) this.mReferenceSet.entrySet().stream().map(new d(6)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX, "[", "]")), "}");
    }
}
