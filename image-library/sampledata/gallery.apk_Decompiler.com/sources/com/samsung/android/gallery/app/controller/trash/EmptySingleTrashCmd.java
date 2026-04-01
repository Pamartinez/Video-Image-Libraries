package com.samsung.android.gallery.app.controller.trash;

import U3.a;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.DialogTask;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.service.message.EmptyMsgMgr;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.PrivateTrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EmptySingleTrashCmd extends BaseCommand {
    private MediaItem mItem = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EmptyTrashTask extends DialogTask {
        private final Blackboard mBlackBoard;
        private TrashEmptyHelper mHelper;
        private final MediaItem mMediaItem;
        private WeakReference<Context> mRef;

        public EmptyTrashTask(EventContext eventContext, MediaItem mediaItem) {
            this.mBlackBoard = eventContext.getBlackboard();
            this.mMediaItem = mediaItem;
            if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !mediaItem.isPrivateItem()) {
                this.mHelper = TrashHelperFactory.getEmptyHelper(eventContext.getContext(), false);
            } else {
                this.mHelper = new PrivateTrashEmptyHelper(eventContext.getContext(), false);
            }
            this.mRef = new WeakReference<>(eventContext.getContext());
            createDialog(eventContext.getContext());
        }

        private String getGdprEmptySingleTrashFailure(Context context) {
            int i2;
            if (this.mMediaItem.isImage()) {
                i2 = R.string.can_not_delete_image_gdpr;
            } else {
                i2 = R.string.can_not_delete_video_gdpr;
            }
            return context.getString(i2, new Object[]{StringResources.getCloudBrand()});
        }

        private void handleResult() {
            Context context = this.mRef.get();
            if (context == null) {
                Log.e(this.TAG, "unable to handle result, context is null");
                return;
            }
            if (!this.mHelper.isSucceed()) {
                if (this.mHelper.isGDPRErrorHappened()) {
                    SamsungCloudCompat.changeSyncState(context, false);
                    showToast(context, getGdprEmptySingleTrashFailure(context));
                } else {
                    return;
                }
            }
            this.mBlackBoard.postEvent(EventMessage.obtain(3015));
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(1029, 1, 0, (Object) null));
        }

        public void onPreExecute() {
            showDialog();
        }

        public Void doInBackground(Void... voidArr) {
            this.mHelper.emptyItem(this.mMediaItem);
            this.mHelper.dump(TrashLogType.EMPTY_SINGLE, "location://trash");
            return null;
        }

        public void onPostExecute(Void voidR) {
            dismissDialog();
            handleResult();
        }
    }

    /* access modifiers changed from: private */
    public void onDataCompleted(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            try {
                i2 = ((Integer) arrayList.get(0)).intValue();
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "unexpected result delivered." + e.toString());
                return;
            }
        } else {
            i2 = -1;
        }
        if (i2 == 1) {
            operateEmptyTrash();
        } else {
            Log.e(this.TAG, "cancel or unexpected option selected.");
        }
    }

    private void operateEmptyTrash() {
        if (getHandler().getContext() != null) {
            new EmptyTrashTask(getHandler(), this.mItem).execute(new Void[0]);
        } else {
            Log.e(this.TAG, "Unable to execute EmptyTrashTask. context is null.");
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mItem = mediaItem;
        if (mediaItem == null) {
            Log.e(this.TAG, "delivered item is null");
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", EmptyMsgMgr.getSingleTitle(getContext(), this.mItem.isVideo())).appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", getScreenId()).appendArg("option1EventId", AnalyticsEventId.EVENT_DELETE_ITEM_DELETE.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_DELETE_ITEM_CANCEL.toString()).build()).setOnDataCompleteListener(new a(7, this)).start();
    }
}
