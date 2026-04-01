package com.samsung.android.gallery.app.ui.list.timeline;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.smartswitch.SmartSwitchState;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SmartSwitchUpdateManager {
    private final WeakReference<Context> mContextRef;
    private int mHeaderPosition;
    private Consumer<View> mListener;
    private SmartSwitchUpdateTask mTask;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SmartSwitchUpdateTask extends AsyncTask<Void, Void, SmartSwitchState> {
        private WeakReference<SmartSwitchUpdateManager> mManager;

        public SmartSwitchUpdateTask(SmartSwitchUpdateManager smartSwitchUpdateManager) {
            this.mManager = new WeakReference<>(smartSwitchUpdateManager);
        }

        public SmartSwitchState doInBackground(Void... voidArr) {
            if (isCancelled()) {
                Log.e("SmartSwitchUpdateManager", "cancelled");
                return SmartSwitchState.INIT;
            }
            if (this.mManager.get() != null) {
                if (SmartSwitchState.isRestoringNow()) {
                    SmartSwitchState smartSwitchState = SmartSwitchState.RESTORING;
                    SmartSwitchState.save(smartSwitchState);
                    return smartSwitchState;
                }
                SmartSwitchState.save(SmartSwitchState.NOT_RESTORE);
            }
            return SmartSwitchState.INIT;
        }

        public void onPostExecute(SmartSwitchState smartSwitchState) {
            Log.d("SmartSwitchUpdateManager", "updateState", smartSwitchState);
            Optional.ofNullable(this.mManager.get()).ifPresent(new a(smartSwitchState, 0));
            this.mManager = null;
        }
    }

    public SmartSwitchUpdateManager(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    private View createUpdateView() {
        if (getContext() != null) {
            return LayoutInflater.from(getContext()).inflate(R.layout.smartswitch_update_layout, (ViewGroup) null);
        }
        return null;
    }

    private Context getContext() {
        return this.mContextRef.get();
    }

    private void stopTask() {
        SmartSwitchUpdateTask smartSwitchUpdateTask = this.mTask;
        if (smartSwitchUpdateTask != null) {
            smartSwitchUpdateTask.cancel(true);
            this.mTask = null;
        }
    }

    /* access modifiers changed from: private */
    public void updateSmartSwitch(SmartSwitchState smartSwitchState) {
        View view;
        Consumer<View> consumer = this.mListener;
        if (consumer != null) {
            if (smartSwitchState == SmartSwitchState.RESTORING) {
                view = createUpdateView();
            } else {
                view = null;
            }
            consumer.accept(view);
            return;
        }
        Log.w((CharSequence) "SmartSwitchUpdateManager", "updateSmartSwitch skip", smartSwitchState);
    }

    public int getHeaderPosition() {
        return this.mHeaderPosition;
    }

    public void getSmartSwitchState() {
        int load = SmartSwitchState.load();
        SmartSwitchState smartSwitchState = SmartSwitchState.INIT;
        if (load == smartSwitchState.ordinal() || load == SmartSwitchState.RESTORING.ordinal()) {
            Log.d("SmartSwitchUpdateManager", "getSmartSwitchState : init or restoring");
            stopTask();
            if (getContext() != null) {
                SmartSwitchUpdateTask smartSwitchUpdateTask = new SmartSwitchUpdateTask(this);
                this.mTask = smartSwitchUpdateTask;
                smartSwitchUpdateTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
            }
        } else if (load == SmartSwitchState.RESTORE_COMPLETED.ordinal()) {
            Log.d("SmartSwitchUpdateManager", "get completed");
            updateSmartSwitch(smartSwitchState);
            SmartSwitchState.save(SmartSwitchState.NOT_RESTORE);
        }
    }

    public SmartSwitchUpdateManager setHeaderPosition(int i2) {
        this.mHeaderPosition = i2;
        return this;
    }

    public SmartSwitchUpdateManager setListener(Consumer<View> consumer) {
        this.mListener = consumer;
        return this;
    }

    public void stop() {
        stopTask();
    }
}
