package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import V3.b;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.controller.internals.DeleteBaseCmd;
import com.samsung.android.gallery.app.controller.internals.ShowLowStorageCmd;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.service.message.DeleteMsgMgr;
import com.samsung.android.gallery.module.service.support.DeleteInfo;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.module.trash.helper.TrashProgressListener;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteSingleCmd extends DeleteBaseCmd {
    private String mLocationKey = null;
    private MediaItem mMediaItem = null;
    private boolean mUseTrash = false;

    /* access modifiers changed from: private */
    public void finishDeletion(TrashDeleteHelper trashDeleteHelper) {
        boolean isSucceed = trashDeleteHelper.isSucceed();
        if (isSucceed) {
            getBlackboard().postEvent(EventMessage.obtain(3015));
        }
        showDeleteResult(trashDeleteHelper);
        if (SdkConfig.atLeast(SdkConfig.GED.Q) && isSucceed) {
            Log.d(this.TAG, "finishDeletion > force refresh viewer");
            if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && this.mMediaItem.isPostProcessing()) {
                BlackboardUtils.removeDataChangeObservingDelay(getBlackboard());
            }
            getBlackboard().post("command://event/DataDirty", (Object) null);
            getBlackboard().publish("viewer_force_refresh", Boolean.TRUE);
        } else if (isSucceed || trashDeleteHelper.isAbnormalRecordDeleteRequested()) {
            BlackboardUtils.forceRefreshPicturesData(getBlackboard(), false);
        }
    }

    private String[] getTitleAndDescription(boolean z) {
        DeleteInfo deleteInfo = new DeleteInfo(false, isVirtualAlbum(this.mLocationKey), z, false);
        deleteInfo.calculateCount(new MediaItem[]{this.mMediaItem});
        return DeleteMsgMgr.getSingleItemMessage(getContext(), deleteInfo.getItemInfo(), CloudStateCompat.isNewGalleryAvailable());
    }

    private boolean isItemInvalidated(MediaItem mediaItem) {
        if (mediaItem == null || this.mMediaItem.getFileId() != mediaItem.getFileId()) {
            return false;
        }
        if (!TextUtils.equals(this.mMediaItem.getPath(), mediaItem.getPath()) || this.mMediaItem.isPostProcessing() != mediaItem.isPostProcessing()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void operateDeleteInternal() {
        MediaItem mediaItem;
        TrashLogType trashLogType;
        EventContext handler = getHandler();
        if (handler != null) {
            mediaItem = handler.getCurrentItem();
        } else {
            mediaItem = null;
        }
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || mediaItem == null || !mediaItem.isPrivateItem()) {
            if (isItemInvalidated(mediaItem)) {
                String str = this.TAG;
                Log.d(str, "mediaItem invalidated while delete dialog shown: " + MediaItemUtil.getSimpleLog(this.mMediaItem) + " > " + MediaItemUtil.getSimpleLog(mediaItem));
                this.mMediaItem = mediaItem;
            }
            String str2 = this.TAG;
            Log.d(str2, "operate delete internal : " + MediaItemUtil.getSimpleLog(this.mMediaItem));
            try {
                final TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(getContext());
                deleteHelper.setProgressListener(1, new TrashProgressListener() {
                    public void onComplete() {
                        DeleteSingleCmd.this.finishDeletion(deleteHelper);
                    }

                    public void onProgress(int i2) {
                    }
                });
                deleteHelper.deleteItem(this.mMediaItem);
                removeItemFromAutoAlbum();
                deleteHelper.done();
                if (this.mUseTrash) {
                    trashLogType = TrashLogType.MOVE_TO_TRASH_SINGLE;
                } else {
                    trashLogType = TrashLogType.DELETE_SINGLE;
                }
                deleteHelper.dump(trashLogType, this.mLocationKey);
            } catch (Exception e) {
                Log.e(this.TAG, "unable to delete item.");
                e.printStackTrace();
                showToast((int) R.string.delete_item_failed);
            }
        } else {
            getBlackboard().postEvent(EventMessage.obtain(3015));
            if (this.mUseTrash) {
                Log.d(this.TAG, "trash(pv)", 1, Integer.valueOf(PrivateDatabase.getInstance().trash(new MediaItem[]{mediaItem}, true)), Long.valueOf(mediaItem.getFileId()));
            } else if (PrivateDatabase.getInstance().delete((FileItemInterface[]) new MediaItem[]{mediaItem}) <= 0) {
                Log.e((CharSequence) this.TAG, "delete failed", Long.valueOf(mediaItem.getFileId()));
            }
        }
    }

    private void removeItemFromAutoAlbum() {
        if (AlbumType.isAutoAlbum(ArgumentsUtil.getArgValue(getHandler().getLocationKey(), "type", 0))) {
            AutoAlbumHelper.getInstance().removeAutoAlbumContent(this.mMediaItem.getFileId(), (long) ArgumentsUtil.getArgValue(getHandler().getLocationKey(), "id", 0));
        }
    }

    private void showDeleteResult(TrashDeleteHelper trashDeleteHelper) {
        if (!trashDeleteHelper.isSucceed() && !trashDeleteHelper.isAbnormalRecordDeleted()) {
            if (this.mUseTrash) {
                int deleteFailedToastMessage = DeleteMsgMgr.getDeleteFailedToastMessage(trashDeleteHelper.getImageFailedCount(), trashDeleteHelper.getVideoFailedCount());
                if (deleteFailedToastMessage != -1) {
                    showToast(deleteFailedToastMessage);
                    return;
                }
                return;
            }
            showToast((int) R.string.delete_item_failed);
        }
    }

    private void startConfirmDialog(EventContext eventContext) {
        String str;
        Log.d(this.TAG, "show confirm dialog");
        String[] titleAndDescription = getTitleAndDescription(this.mUseTrash);
        UriBuilder appendArg = new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", titleAndDescription[0]).appendArg("description", titleAndDescription[1]);
        if (this.mUseTrash) {
            str = getContext().getString(R.string.move_to_trash);
        } else {
            str = getContext().getString(R.string.delete);
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(appendArg.appendArg("option1", str).appendArg("screenId", getScreenId()).appendArg("option1EventId", getEventIdOfDelete(this.mUseTrash)).appendArg("option1ColorRed", !this.mUseTrash).appendArg("cancelEventId", getEventIdOfCancel(this.mUseTrash)).build()).setOnDataCompleteListener(new a(11, this)).start();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mMediaItem = mediaItem;
        this.mLocationKey = objArr[1];
        if (mediaItem == null) {
            Log.e(this.TAG, "unable to operate. item is null.");
        } else if (isLowStorageWithTrash()) {
            Log.e(this.TAG, "not enough storage");
            new ShowLowStorageCmd().execute(eventContext, new Object[0]);
        } else {
            this.mUseTrash = PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash);
            if (PocFeatures.SUPPORT_PRIVATE_ALBUM && this.mMediaItem.isPrivateItem()) {
                this.mUseTrash = true;
            }
            startConfirmDialog(eventContext);
        }
    }

    public void operateDelete() {
        Log.d(this.TAG, "operate delete");
        SimpleThreadPool.getInstance().execute(new b(5, this));
    }

    public void postAnalyticsLog() {
        AnalyticsLogger.getInstance().postCustomDimension(getScreenId(), getEventId(), VuAnalytics.getViewerCustomDimensions(this.mMediaItem, getAnalyticsDetail()));
    }
}
