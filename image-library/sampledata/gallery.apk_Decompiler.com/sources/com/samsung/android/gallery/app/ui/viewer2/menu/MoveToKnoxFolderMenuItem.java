package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.FetchContentsForKnoxCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.KnoxUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToKnoxFolderMenuItem extends ViewerMenuItem {
    public MoveToKnoxFolderMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.move_to_knox);
    }

    public String getTitle() {
        String knoxContainerName = KnoxUtil.getInstance().getKnoxContainerName(KnoxUtil.MoveType.MOVE_TO_KNOX);
        if (!TextUtils.isEmpty(knoxContainerName)) {
            return AppResources.getString(R.string.move_to_knox_s, knoxContainerName);
        }
        return null;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "MoveToKnoxFolder Menu Select failed: null item");
            return false;
        }
        FetchContentsForKnoxCmd fetchContentsForKnoxCmd = new FetchContentsForKnoxCmd();
        EventContext eventContext = this.mEventContext;
        fetchContentsForKnoxCmd.execute(eventContext, eventContext.getLocationKey(), KnoxUtil.MoveType.MOVE_TO_KNOX, new MediaItem[]{currentItem});
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_KNOX).validate(ViewerMenuItem.IS_NOT_AFW).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new c(8));
    }
}
