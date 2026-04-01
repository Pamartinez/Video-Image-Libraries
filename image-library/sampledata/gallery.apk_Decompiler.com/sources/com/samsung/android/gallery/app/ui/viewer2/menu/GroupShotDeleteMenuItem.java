package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DeleteBurstShotCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteSimilarShotCmd;
import com.samsung.android.gallery.app.controller.internals.DeleteSingleTakenShotCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupShotDeleteMenuItem extends ViewerMenuItem {
    private final boolean mIsSelectMode;

    public GroupShotDeleteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.delete);
        this.mIsSelectMode = false;
    }

    private void deleteSingleItem(MediaItem mediaItem) {
        int i2;
        if (!PocFeatures.UNDO_DELETE || !PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            DeleteSingleCmd deleteSingleCmd = new DeleteSingleCmd();
            EventContext eventContext = this.mEventContext;
            deleteSingleCmd.execute(eventContext, mediaItem, eventContext.getLocationKey());
            return;
        }
        String locationKey = this.mEventContext.getLocationKey();
        if (locationKey != null) {
            if (this.mEventContext.getMediaData() != null) {
                i2 = this.mEventContext.getMediaData().findPositionByFileId(mediaItem.getFileId());
            } else {
                i2 = -1;
            }
            new DeleteUndoSingleCmd().execute(this.mEventContext, mediaItem, DataKey.getViewerDataKey(locationKey), Integer.valueOf(i2));
        }
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem[] mediaItemArr = (MediaItem[]) this.mEventContext.getSelectedItems().clone();
        if (mediaItemArr.length == 0) {
            if (this.mIsSelectMode) {
                Utils.showToast(this.mEventContext.getContext(), (int) R.string.select_pictures_to_delete);
            } else {
                Log.d(this.TAG, "delete group item failed: selectedItem is nulls");
            }
            return true;
        }
        publishPopoverInfo(getMenuId(), view);
        if (mediaItemArr[0].isSingleTakenShot()) {
            new DeleteSingleTakenShotCmd().execute(this.mEventContext, mediaItemArr);
        } else if (mediaItemArr[0].isBurstShot()) {
            new DeleteBurstShotCmd().execute(this.mEventContext, mediaItemArr);
        } else if (mediaItemArr[0].isSimilarShot()) {
            new DeleteSimilarShotCmd().execute(this.mEventContext, mediaItemArr);
        } else if (mediaItemArr.length == 1) {
            deleteSingleItem(mediaItemArr[0]);
        }
        return true;
    }

    public void setMenuAttribute() {
        int i2;
        if (isFlipCoverTheme()) {
            i2 = R.drawable.gallery_ic_flip_cover_delete;
        } else {
            i2 = R.drawable.gallery_ic_detail_delete;
        }
        setIconResId(i2).setFastOptionMenu().setShowAsActionFlag(2).setExecutableOnScreenLocked().exclude("location://mtp/fileList", "location://revitalized").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING);
    }

    public GroupShotDeleteMenuItem(EventContext eventContext, ActionInvoker actionInvoker, boolean z) {
        super(eventContext, actionInvoker, R.string.delete);
        this.mIsSelectMode = z;
    }
}
