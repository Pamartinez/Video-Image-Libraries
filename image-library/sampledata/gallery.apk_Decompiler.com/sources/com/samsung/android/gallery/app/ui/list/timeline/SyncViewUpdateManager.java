package com.samsung.android.gallery.app.ui.list.timeline;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SyncViewUpdateManager {
    private final WeakReference<Context> mContextRef;
    private int mHeaderPosition;
    private Consumer<View> mListener;
    private TimelineSyncStateTask mTask;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TimelineSyncStateTask extends AsyncTask<Void, Void, UpdateState> {
        private WeakReference<Context> mContext;
        private WeakReference<SyncViewUpdateManager> mManager;

        public /* synthetic */ TimelineSyncStateTask(Context context, SyncViewUpdateManager syncViewUpdateManager, int i2) {
            this(context, syncViewUpdateManager);
        }

        private TimelineSyncStateTask(Context context, SyncViewUpdateManager syncViewUpdateManager) {
            this.mContext = new WeakReference<>(context);
            this.mManager = new WeakReference<>(syncViewUpdateManager);
        }

        public UpdateState doInBackground(Void... voidArr) {
            if (isCancelled()) {
                Log.i("SyncViewUpdateManager", "task cancelled");
                return UpdateState.CLEAR;
            }
            SyncViewUpdateManager syncViewUpdateManager = this.mManager.get();
            Context context = this.mContext.get();
            if (!(syncViewUpdateManager == null || context == null)) {
                if (!CloudStateCompat.isEnabled() || !CloudStateCompat.isSyncOnInPref()) {
                    Log.d("SyncViewUpdateManager", "cloud is not enabled or sync off");
                    syncViewUpdateManager.resetSyncState();
                    return UpdateState.CLEAR;
                } else if (SamsungCloudCompat.isSyncing(context)) {
                    syncViewUpdateManager.resetSyncState();
                    return UpdateState.SYNCING;
                } else if (SamsungCloudCompat.isSyncSuccess(context)) {
                    return UpdateState.SUCCESS;
                }
            }
            return UpdateState.CLEAR;
        }

        public void onPostExecute(UpdateState updateState) {
            if (isCancelled()) {
                Log.i("SyncViewUpdateManager", "task cancelled");
                return;
            }
            Log.d("SyncViewUpdateManager", "task{" + updateState + "}");
            Optional.ofNullable(this.mManager.get()).ifPresent(new a(updateState, 1));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum UpdateState {
        CLEAR,
        SYNCING,
        SUCCESS
    }

    public SyncViewUpdateManager(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    private View createSyncView(UpdateState updateState) {
        if (updateState == UpdateState.CLEAR) {
            Log.d("SyncViewUpdateManager", "createSyncView: not update");
            return null;
        } else if (getContext() == null) {
            return null;
        } else {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cloud_sync_view_layout, (ViewGroup) null);
            updateSyncViewByState(updateState, inflate);
            return inflate;
        }
    }

    private Context getContext() {
        return this.mContextRef.get();
    }

    private String getTag(UpdateState updateState) {
        if (updateState == UpdateState.SYNCING) {
            return "SYNC_PROGRESS";
        }
        return "SYNC_SUCCESS";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateSyncState$2(UpdateState updateState) {
        this.mListener.accept(createSyncView(updateState));
    }

    /* access modifiers changed from: private */
    public void resetSyncState() {
        PreferenceCache.CloudTimelineSyncState.setBoolean(false);
    }

    private void stopTask() {
        TimelineSyncStateTask timelineSyncStateTask = this.mTask;
        if (timelineSyncStateTask != null) {
            timelineSyncStateTask.cancel(true);
            this.mTask = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateSyncState(UpdateState updateState) {
        PreferenceCache preferenceCache = PreferenceCache.CloudTimelineSyncState;
        if (preferenceCache.getBoolean()) {
            Log.d("SyncViewUpdateManager", "getSyncState: last Status is Sync success. so skip this");
            return;
        }
        if (updateState == UpdateState.SUCCESS) {
            preferenceCache.setBoolean(true);
        }
        ThreadUtil.runOnUiThread(new b(updateState, this));
    }

    public int getHeaderPosition() {
        return this.mHeaderPosition;
    }

    public void getSyncState() {
        stopTask();
        if (getContext() != null) {
            TimelineSyncStateTask timelineSyncStateTask = new TimelineSyncStateTask(getContext(), this, 0);
            this.mTask = timelineSyncStateTask;
            timelineSyncStateTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public boolean isSyncSuccessTag(String str) {
        return "SYNC_SUCCESS".equals(str);
    }

    public void onMediaContentSyncOff() {
        resetSyncState();
        updateSyncState(UpdateState.CLEAR);
        Log.d("SyncViewUpdateManager", "onMediaContentSyncOff");
    }

    public void onMediaSyncStatusChanged() {
        UpdateState updateState = UpdateState.CLEAR;
        if (SamsungCloudCompat.isSyncSuccess()) {
            updateState = UpdateState.SUCCESS;
        } else {
            if (SamsungCloudCompat.isSyncing()) {
                updateState = UpdateState.SYNCING;
            }
            resetSyncState();
        }
        Log.d("SyncViewUpdateManager", "onMediaSyncStatusChanged: " + updateState.ordinal());
        updateSyncState(updateState);
    }

    public SyncViewUpdateManager setHeaderPosition(int i2) {
        this.mHeaderPosition = i2;
        return this;
    }

    public SyncViewUpdateManager setListener(Consumer<View> consumer) {
        this.mListener = consumer;
        return this;
    }

    public void stop() {
        stopTask();
    }

    public void updateSyncViewByState(UpdateState updateState, View view) {
        int i2;
        int i7;
        if (view != null && getContext() != null) {
            view.setTag(getTag(updateState));
            ImageView imageView = (ImageView) view.findViewById(R.id.sync_icon);
            TextView textView = (TextView) view.findViewById(R.id.sync_text);
            if (updateState == UpdateState.SYNCING) {
                ViewUtils.consumeIfPresent(imageView, new c(0));
                if (CloudStateCompat.isNewGalleryAvailable()) {
                    i7 = R.string.syncing_with_samsung_cloud;
                } else {
                    i7 = R.string.add_items_to_onedrive;
                }
                ViewUtils.setText(textView, i7);
            } else if (updateState == UpdateState.SUCCESS) {
                ViewUtils.consumeIfPresent(imageView, new c(1));
                if (CloudStateCompat.isNewGalleryAvailable()) {
                    i2 = R.string.synced_with_samsung_cloud;
                } else {
                    i2 = R.string.synced_with_onedrive;
                }
                ViewUtils.setText(textView, i2);
            }
        }
    }
}
