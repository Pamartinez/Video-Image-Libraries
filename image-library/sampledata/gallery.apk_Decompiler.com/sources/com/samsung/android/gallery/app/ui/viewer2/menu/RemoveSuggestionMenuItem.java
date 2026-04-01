package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.KeepCleanOutCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveHighlightCmd;
import com.samsung.android.gallery.app.controller.internals.RemoveRemasteredImageCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveSuggestionMenuItem extends ViewerMenuItem {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public RemoveSuggestionMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        this(eventContext, actionInvoker, PreferenceFeatures.OneUi7x.IS_ONE_UI_70 ? R.string.remove_from_suggestions_v : R.string.remove_from_suggestions);
    }

    private void removeContentsFromAutoAlbum() {
        int i2 = UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "id"));
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (i2 > 0 && currentItem != null) {
            AutoAlbumHelper.getInstance().removeAutoAlbumContent(currentItem.getFileId(), (long) i2);
        }
    }

    public boolean onMenuSelectInternal(View view) {
        this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        if (LocationKey.isRevitalizationView(this.mEventContext.getLocationKey())) {
            RemoveRemasteredImageCmd removeRemasteredImageCmd = new RemoveRemasteredImageCmd();
            EventContext eventContext = this.mEventContext;
            removeRemasteredImageCmd.execute(eventContext, new MediaItem[]{eventContext.getCurrentItem()});
            return true;
        } else if (LocationKey.isHighLightPictures(this.mEventContext.getLocationKey()) || LocationKey.is2ndDepthSuggestionHighlightView(this.mEventContext.getLocationKey())) {
            RemoveHighlightCmd removeHighlightCmd = new RemoveHighlightCmd();
            EventContext eventContext2 = this.mEventContext;
            removeHighlightCmd.execute(eventContext2, new MediaItem[]{eventContext2.getCurrentItem()});
            return true;
        } else if (LocationKey.isSharingFamilyAlbumSuggested(this.mEventContext.getLocationKey())) {
            removeContentsFromAutoAlbum();
            forceSwipe(this.mEventContext.getBlackboard());
            this.mEventContext.getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
            return true;
        } else {
            KeepCleanOutCmd keepCleanOutCmd = new KeepCleanOutCmd();
            EventContext eventContext3 = this.mEventContext;
            keepCleanOutCmd.execute(eventContext3, new MediaItem[]{eventContext3.getCurrentItem()});
            return true;
        }
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_bottombar_remove).setShowAsActionFlag(6).include("location://cleanOut/fileList", "location://family/shared/suggested/fileList", "location://cleanOut/motionPhotoClip/fileList").setFastOptionMenu();
    }

    public RemoveSuggestionMenuItem(EventContext eventContext, ActionInvoker actionInvoker, int i2) {
        super(eventContext, actionInvoker, i2);
    }
}
