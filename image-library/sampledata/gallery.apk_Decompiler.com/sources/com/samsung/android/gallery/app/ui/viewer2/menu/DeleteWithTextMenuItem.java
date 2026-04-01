package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteWithTextMenuItem extends DeleteMenuItem {
    public DeleteWithTextMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_delete).setFastOptionMenu().setShowAsActionFlag(6).include("location://cleanOut/fileList", "location://cleanOut/duplicated/fileList", "location://cleanOut/burstSimilar/fileList", "location://trash", "location://family/shared/trash", "location://private/trash").validate(ViewerMenuItem.IS_NOT_GROUP_SHOT).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING_FOR_PPPV3).setSupportPpp(true);
    }
}
