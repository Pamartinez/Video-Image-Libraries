package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAsCopyMenuItem extends DownloadMenuItem {
    public SaveAsCopyMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save_as_copy);
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_save).setFastOptionMenu().setShowAsActionFlag(6).include("location://dynamicViewList", "location://highlight/fileList", "location://AllDayTimeLapse", "location://LongExposure", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView");
    }
}
