package com.samsung.android.gallery.app.controller.trash;

import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DialogTask;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ShowLowStorageCmd;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.RestoreMsgMgr;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.trash.helper.TrashRestoreHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.NetworkUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.StringResources;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RestoreSingleTrashCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RestoreTrashTask extends DialogTask {
        private final Blackboard mBlackboard;
        private final TrashRestoreHelper mHelper;
        private final MediaItem mMediaItem;
        private final WeakReference<Context> mRef;

        public RestoreTrashTask(EventContext eventContext, MediaItem mediaItem) {
            this.mBlackboard = eventContext.getBlackboard();
            this.mMediaItem = mediaItem;
            Context context = eventContext.getContext();
            this.mHelper = TrashHelperFactory.getRestoreHelper(context, false);
            this.mRef = new WeakReference<>(context);
            createDialog(context);
        }

        private String getGdprRestoreSingleFailure(Context context) {
            int i2;
            if (this.mMediaItem.isImage()) {
                i2 = R.string.can_not_restore_image_gdpr;
            } else {
                i2 = R.string.can_not_restore_video_gdpr;
            }
            return context.getString(i2, new Object[]{StringResources.getCloudBrand()});
        }

        private boolean handleResult() {
            String str;
            Context context = this.mRef.get();
            if (context == null) {
                Log.e(this.TAG, "unable to handle result, context is null");
                return false;
            }
            boolean z = true;
            if (this.mHelper.isSucceed()) {
                str = RestoreMsgMgr.getSuccessMessage(context, this.mHelper.getImageSucceedCount(), this.mHelper.getVideoSucceedCount());
            } else if (this.mHelper.getErrorType() == CloudErrorType.GDPR) {
                SamsungCloudCompat.changeSyncState(context, false);
                str = getGdprRestoreSingleFailure(context);
            } else {
                z = false;
                str = RestoreMsgMgr.getFailMessage(context, this.mHelper.getErrorType(), false, this.mHelper.getImageFailedCount(), this.mHelper.getVideoFailedCount());
            }
            showToast(context, str);
            return z;
        }

        public void onPreExecute() {
            showDialog();
        }

        public Void doInBackground(Void... voidArr) {
            this.mHelper.restoreItem(this.mMediaItem);
            this.mHelper.done();
            this.mHelper.dump(TrashLogType.RESTORE_SINGLE, "location://trash");
            return null;
        }

        public void onPostExecute(Void voidR) {
            dismissDialog();
            if (handleResult()) {
                this.mBlackboard.postEvent(EventMessage.obtain(3015));
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    BlackboardUtils.forceRefreshPicturesDataGlobal();
                } else {
                    Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
                }
            }
        }
    }

    private void checkRoamingState() {
        PreferenceCache preferenceCache = PreferenceCache.RoamingTip;
        if (preferenceCache.getBoolean()) {
            Log.e(this.TAG, "roaming message is already shown.");
        } else if (NetworkUtils.isNetworkRoaming(getContext())) {
            showToast((int) R.string.restoring_while_roaming, 1);
            preferenceCache.setBoolean(true);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_RESTORE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (isLowStorageWithTrash()) {
            new ShowLowStorageCmd().execute(eventContext, new Object[0]);
            return;
        }
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "delivered item is null");
            return;
        }
        checkRoamingState();
        new RestoreTrashTask(eventContext, mediaItem).execute(new Void[0]);
    }
}
