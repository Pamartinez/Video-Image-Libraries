package com.samsung.android.gallery.module.debugger;

import V3.b;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HistoryLogger {
    private final Object LOCK = new Object();
    private long mLastUpdated;
    private boolean mPrefSaved;
    private final GalleryPreference mPreference = GalleryPreference.getInstanceCache();
    private final HashSet<Long> mViewList = new HashSet<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Holder {
        static final HistoryLogger sInstance = new HistoryLogger();
    }

    public static HistoryLogger getInstance() {
        return Holder.sInstance;
    }

    public void addViewHistory(long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mLastUpdated == 0) {
            this.mLastUpdated = currentTimeMillis;
            loadViewHistory();
        }
        synchronized (this.LOCK) {
            try {
                if (!this.mViewList.contains(Long.valueOf(j2))) {
                    this.mViewList.add(Long.valueOf(j2));
                    this.mPrefSaved = false;
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (currentTimeMillis - this.mLastUpdated > 3600000) {
            writeViewHistory(currentTimeMillis);
        }
    }

    public void keepViewHistory() {
        String str;
        if (this.mLastUpdated > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.mLastUpdated > 3600000) {
                writeViewHistory(currentTimeMillis);
            } else if (!this.mPrefSaved) {
                synchronized (this.LOCK) {
                    try {
                        if (this.mViewList.size() == 0) {
                            str = "";
                        } else {
                            str = TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, this.mViewList);
                        }
                    } catch (Throwable th) {
                        while (true) {
                            throw th;
                        }
                    }
                }
                this.mPrefSaved = true;
                this.mPreference.saveState("view_history", str);
                this.mPreference.saveState("view_history_timestamp", this.mLastUpdated);
                Log.d("HistoryLogger", "keepViewHistory" + Logger.vt(Long.valueOf(this.mLastUpdated), Integer.valueOf(this.mViewList.size()), Long.valueOf(currentTimeMillis)) + "");
            }
        }
    }

    public void loadViewHistory() {
        System.currentTimeMillis();
        long loadLong = this.mPreference.loadLong("view_history_timestamp", 0);
        if (loadLong > 0) {
            this.mLastUpdated = loadLong;
        }
        String loadString = this.mPreference.loadString("view_history", (String) null);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(loadString)) {
            for (String str : loadString.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
                long j2 = UnsafeCast.toLong(str);
                if (j2 > 0) {
                    arrayList.add(Long.valueOf(j2));
                }
            }
        }
        synchronized (this.LOCK) {
            this.mViewList.addAll(arrayList);
        }
        this.mPrefSaved = true;
    }

    public void writeViewHistory(long j2) {
        this.mLastUpdated = j2;
        if (!this.mViewList.isEmpty()) {
            ArrayList arrayList = new ArrayList(this.mViewList);
            synchronized (this.LOCK) {
                this.mViewList.clear();
            }
            this.mPreference.removeState("view_history");
            this.mPreference.removeState("view_history_timestamp");
            this.mPrefSaved = true;
            ThreadUtil.runOnBgThread(new b(25, arrayList));
        }
    }
}
