package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.mtp.SingleMtpImportCmd;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpImportMenuItem extends ViewerMenuItem {
    public MtpImportMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.import_file);
    }

    public boolean onMenuSelectInternal(View view) {
        new SingleMtpImportCmd().execute(this.mEventContext, this.mEventContext.getCurrentItem());
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(1).include("location://mtp/fileList").setToolbarOnly();
    }
}
