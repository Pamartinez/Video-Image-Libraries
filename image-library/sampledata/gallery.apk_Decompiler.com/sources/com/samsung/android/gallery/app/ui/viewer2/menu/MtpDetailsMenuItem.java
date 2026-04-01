package com.samsung.android.gallery.app.ui.viewer2.menu;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpDetailsMenuItem extends DetailsMenuItem {
    public MtpDetailsMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(1).include("location://mtp/fileList").setToolbarOnly();
    }
}
