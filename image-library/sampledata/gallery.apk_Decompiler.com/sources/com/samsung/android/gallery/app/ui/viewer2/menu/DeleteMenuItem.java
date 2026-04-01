package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.DeleteFromCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveRemasteredImageCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedItemFromTrashCmd;
import com.samsung.android.gallery.app.controller.trash.EmptySingleTrashCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteSingleCmd;
import com.samsung.android.gallery.app.controller.viewer.DeleteUndoSingleCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteMenuItem extends ViewerMenuItem {
    public DeleteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.delete);
    }

    private boolean isInTrash(String str) {
        if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !LocationKey.isPrivateTrash(str)) {
            return LocationKey.isTrash(str);
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        int i2;
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Delete Menu Select failed: null item");
            return false;
        }
        String locationKey = this.mEventContext.getLocationKey();
        this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        publishPopoverInfo(getMenuId(), view);
        if (MediaItemSuggest.isCleanOutItem(currentItem) || LocationKey.isCleanOutBurstSimilarPhoto(locationKey)) {
            new DeleteFromCleanOutCmd().execute(this.mEventContext, new MediaItem[]{currentItem});
            return true;
        } else if (LocationKey.isRevitalizationView(locationKey)) {
            new RemoveRemasteredImageCmd().execute(this.mEventContext, new MediaItem[]{currentItem});
            return true;
        } else if (isInTrash(locationKey)) {
            new EmptySingleTrashCmd().execute(this.mEventContext, currentItem);
            return true;
        } else if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) && LocationKey.isFamilySharedTrash(locationKey)) {
            new DeleteSharedItemFromTrashCmd().execute(this.mEventContext, new MediaItem[]{currentItem});
            return true;
        } else if (!PocFeatures.UNDO_DELETE || !PreferenceFeatures.isEnabled(PreferenceFeatures.UseTrash)) {
            new DeleteSingleCmd().execute(this.mEventContext, currentItem, locationKey);
            return true;
        } else {
            if (this.mEventContext.getMediaData() != null) {
                i2 = this.mEventContext.getMediaData().findPositionByFileId(currentItem.getFileId());
            } else {
                i2 = -1;
            }
            new DeleteUndoSingleCmd().execute(this.mEventContext, currentItem, DataKey.getViewerDataKey(locationKey), Integer.valueOf(i2));
            return true;
        }
    }

    public void setMenuAttribute() {
        int i2;
        if (isFlipCoverTheme()) {
            i2 = R.drawable.gallery_ic_flip_cover_delete;
        } else {
            i2 = R.drawable.gallery_ic_detail_delete;
        }
        setIconResId(i2).setFastOptionMenu().setShowAsActionFlag(2).setExecutableOnScreenLocked().exclude("location://trash", "location://family/shared/trash", "location://mtp/fileList", "location://dynamicViewList", "location://revitalized", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView", "location://private/trash").validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).validate(ViewerMenuItem.IS_DELETABLE_FOR_SHARING).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_AUTO_ALBUM).setSupportPpp(Features.isEnabled(Features.SUPPORT_PPP_MENU));
    }
}
