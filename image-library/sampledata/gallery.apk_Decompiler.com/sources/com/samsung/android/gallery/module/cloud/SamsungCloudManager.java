package com.samsung.android.gallery.module.cloud;

import A.a;
import O3.y;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.scloud.lib.setting.SamsungCloudRPCSetting;
import com.samsung.android.scloud.rpc.SamsungCloudRPCStatus;
import i.C0212a;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SamsungCloudManager {
    private static final SamsungCloudManager sInstance = new SamsungCloudManager();
    private OnCloudSyncStatusListener mListener;
    private final OnCloudSyncStatusListener mLocalListener = new y(11, this);
    /* access modifiers changed from: private */
    public SamsungCloudRPCSetting mSCloudRPCSetting;
    private CloudSyncOnTask mTask;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CloudSyncOnTask extends AsyncTask<Context, Integer, Boolean> {
        private boolean mCanceled;
        private WeakReference<Context> mContextRef;
        private final OnCloudSyncStatusListener mListener;

        public CloudSyncOnTask(OnCloudSyncStatusListener onCloudSyncStatusListener) {
            this.mListener = onCloudSyncStatusListener;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onPostExecute$0() {
            this.mListener.onUpdateCloudSyncStatus(1);
        }

        public void cancelSyncOn() {
            this.mCanceled = true;
        }

        public void onPreExecute() {
            this.mListener.onUpdateCloudSyncStatus(0);
            super.onPreExecute();
        }

        public Boolean doInBackground(Context... contextArr) {
            Context context = contextArr[0];
            this.mContextRef = new WeakReference<>(context);
            try {
                long currentTimeMillis = System.currentTimeMillis();
                boolean changeSyncState = SamsungCloudCompat.changeSyncState(context, true);
                Log.d("CloudSyncOnTask", "sync on {" + changeSyncState + "} +" + (System.currentTimeMillis() - currentTimeMillis));
                return Boolean.valueOf(changeSyncState);
            } catch (IllegalArgumentException e) {
                Log.e((CharSequence) "CloudSyncOnTask", "sync on failed", (Throwable) e);
                return Boolean.FALSE;
            }
        }

        public void onPostExecute(Boolean bool) {
            ThreadUtil.postOnUiThreadDelayed(new c(this), 300);
            if (!bool.booleanValue()) {
                this.mListener.onUpdateCloudSyncStatus(2);
            } else if (this.mCanceled) {
                Log.d("CloudSyncOnTask", "sync on canceled");
                SamsungCloudCompat.changeSyncState(this.mContextRef.get(), false);
            }
            super.onPostExecute(bool);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCloudSyncStatusListener {
        void onUpdateCloudSyncStatus(int i2);
    }

    private SamsungCloudManager() {
    }

    public static SamsungCloudManager getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(int i2) {
        if (i2 == 1) {
            this.mTask = null;
        }
        notifyListener(i2);
    }

    private void notifyListener(int i2) {
        OnCloudSyncStatusListener onCloudSyncStatusListener = this.mListener;
        if (onCloudSyncStatusListener != null) {
            onCloudSyncStatusListener.onUpdateCloudSyncStatus(i2);
        }
    }

    private void startRPCSetting(Context context) {
        Log.d("SamsungCloudManager", "startRPCSetting");
        if (context == null) {
            Log.e("SamsungCloudManager", "Unable to start RPC Setting, context is null");
        } else {
            this.mSCloudRPCSetting = new SamsungCloudRPCSetting(context, new SamsungCloudRPCStatus() {
                public String getServiceType() {
                    return "SYNC";
                }

                public void onBind(boolean z) {
                    C0212a.x("RPC service binding result : ", "SamsungCloudManager", z);
                    if (z && SamsungCloudManager.this.mSCloudRPCSetting != null) {
                        SamsungCloudManager.this.mSCloudRPCSetting.showSetting("media");
                    }
                }
            });
        }
    }

    public void cancelSyncOn() {
        CloudSyncOnTask cloudSyncOnTask = this.mTask;
        if (cloudSyncOnTask != null) {
            cloudSyncOnTask.cancelSyncOn();
            notifyListener(1);
            this.mListener = null;
            this.mTask = null;
        }
    }

    public void removeListener() {
        if (this.mListener != null) {
            this.mListener = null;
        }
    }

    public void setSyncOff(Context context) {
        SamsungCloudCompat.changeSyncState(context, false);
    }

    public void setSyncOn(Context context, OnCloudSyncStatusListener onCloudSyncStatusListener) {
        if (this.mTask == null && context != null) {
            this.mListener = onCloudSyncStatusListener;
            CloudSyncOnTask cloudSyncOnTask = new CloudSyncOnTask(this.mLocalListener);
            this.mTask = cloudSyncOnTask;
            cloudSyncOnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Context[]{context});
        }
    }

    public void startPermissionSetting(Context context) {
        if (CloudStateCompat.isLegacyServiceStatusRequired()) {
            startRPCSetting(context);
        } else {
            startSetting((Activity) context);
        }
    }

    public void startSetting(Activity activity) {
        Log.d("SamsungCloudManager", "startSetting");
        try {
            activity.startActivity(new Intent("com.samsung.android.scloud.GALLERY_SETTING"));
        } catch (Exception e) {
            a.s(e, new StringBuilder("startSetting failed e="), "SamsungCloudManager");
        }
    }

    public void startUpselling(Context context) {
        Log.d("SamsungCloudManager", "startUpselling");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("samsungcloud://com.samsung.android.scloud/premium/web?landing=onboarding"));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("startUpselling failed e="), "SamsungCloudManager");
        }
    }
}
