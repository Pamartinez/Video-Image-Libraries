package com.samsung.android.gallery.module.cloud;

import A.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SyncStateHelper {
    private static final Uri CLOUD_AUTHORITY_URI = Uri.parse("content://com.samsung.android.scloud.mediaservice/");
    protected final String TAG = getClass().getSimpleName();
    private Bundle mAlbumSyncData;
    private int mCloudSyncOn = 0;
    protected final AtomicInteger mSyncState = new AtomicInteger(-1);

    private int getCloudSyncOn(Context context, boolean z) {
        int i2;
        int i7 = this.mCloudSyncOn;
        if (i7 != 0 && !z) {
            return i7;
        }
        ThreadUtil.assertBgThread("getCloudSyncOn should be on worker thread");
        if (SamsungCloudCompat.isSyncOn(context)) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        this.mCloudSyncOn = i2;
        String str = this.TAG;
        Log.d(str, "getCloudSyncOn {" + this.mCloudSyncOn + '}');
        return this.mCloudSyncOn;
    }

    private int getSyncState(String str) {
        return UnsafeCast.toInt(str, -2);
    }

    public Bundle getAlbumSyncData(Context context) {
        Bundle bundle;
        boolean z;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            ThreadUtil.assertBgThread("getAlbumSyncData should be on worker thread");
            Bundle call = context.getContentResolver().call(CLOUD_AUTHORITY_URI, "2", (String) null, (Bundle) null);
            if (call != null) {
                bundle = (Bundle) call.clone();
            } else {
                bundle = null;
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("getAlbumSyncData {");
            if (call != null) {
                z = true;
            } else {
                z = false;
            }
            sb2.append(z);
            sb2.append("} +");
            sb2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.d(str, sb2.toString());
            return bundle;
        } catch (Exception e) {
            a.s(e, new StringBuilder("getAlbumSyncData failed. e="), this.TAG);
            return null;
        }
    }

    public int getAlbumSyncStatus(Context context, int i2, String str) {
        if (getCloudSyncOn(context, false) == 1) {
            updateAlbumSyncData((Bundle) null);
            return 0;
        }
        Bundle bundle = this.mAlbumSyncData;
        if (bundle == null) {
            bundle = getAlbumSyncData(context);
            updateAlbumSyncData(bundle);
        }
        if (bundle == null) {
            return 0;
        }
        return bundle.getInt(i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str);
    }

    public String getCloudSyncString() {
        int i2 = this.mCloudSyncOn;
        if (i2 == 0) {
            return "cloud init";
        }
        if (i2 == 2) {
            return "cloud on";
        }
        return "cloud off";
    }

    public boolean isCloudSyncOn(Context context) {
        if (getCloudSyncOn(context, true) == 2) {
            return true;
        }
        return false;
    }

    public boolean isCloudSyncOnCached(Context context) {
        if (getCloudSyncOn(context, false) == 2) {
            return true;
        }
        return false;
    }

    public boolean isSyncSuccess(Context context) {
        return getSyncState(context) == 1;
    }

    public boolean isSyncing(Context context) {
        return getSyncState(context) == 0;
    }

    public void releaseSyncState() {
        synchronized (SyncStateHelper.class) {
            this.mSyncState.set(-1);
            this.mAlbumSyncData = null;
            this.mCloudSyncOn = 0;
        }
    }

    public String toString() {
        String str;
        int i2 = this.mSyncState.get();
        StringBuilder sb2 = new StringBuilder("SyncState{");
        if (i2 == 0) {
            str = "syncing";
        } else if (i2 == 1) {
            str = "sync success";
        } else if (i2 == 2) {
            str = "sync fail";
        } else if (i2 == 3) {
            str = "sync ready";
        } else if (i2 == -1) {
            str = "sync init";
        } else {
            str = "sync unknown";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getCloudSyncString());
        sb2.append("}");
        return sb2.toString();
    }

    public void updateAlbumSyncData(Bundle bundle) {
        this.mAlbumSyncData = bundle;
    }

    public void updateCloudSyncOnCache(boolean z) {
        int i2;
        if (z) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        this.mCloudSyncOn = i2;
    }

    public void updateSyncStatus(int i2) {
    }

    private int getSyncState(Context context) {
        if (this.mSyncState.get() == -1) {
            int i2 = -2;
            try {
                long currentTimeMillis = System.currentTimeMillis();
                ThreadUtil.assertBgThread("getSyncState should be on worker thread");
                Bundle call = context.getContentResolver().call(CLOUD_AUTHORITY_URI, "1", (String) null, (Bundle) null);
                if (call != null) {
                    i2 = getSyncState(call.getString("sync_status"));
                }
                String str = this.TAG;
                Log.d(str, "getSyncState loading {" + i2 + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            } catch (Exception e) {
                a.s(e, new StringBuilder("getSyncState failed e="), this.TAG);
            }
            this.mSyncState.compareAndSet(-1, i2);
        }
        String str2 = this.TAG;
        Log.d(str2, "getSyncState " + this);
        return this.mSyncState.get();
    }

    public boolean isSyncSuccess() {
        return this.mSyncState.get() == 1;
    }

    public boolean isSyncing() {
        return this.mSyncState.get() == 0;
    }

    public void updateSyncStatus(Bundle bundle) {
        if (bundle != null) {
            this.mSyncState.set(getSyncState(bundle.getString("sync_status")));
            String str = this.TAG;
            Log.d(str, "updateSyncStatus(bundle) " + this);
        }
    }
}
