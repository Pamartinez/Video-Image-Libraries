package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd;
import com.samsung.android.gallery.app.controller.internals.ChangeBestItemCmd2;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.groupshot.SingleTakenShotFormat;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BestImageMenuItem extends ViewerMenuItem {
    public BestImageMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.set_as_best_shot);
    }

    public boolean onMenuSelectInternal(View view) {
        BaseCommand baseCommand;
        MediaItem bestItem = this.mEventContext.getBestItem();
        MediaItem[] selectedItems = this.mEventContext.getSelectedItems();
        if (selectedItems == null) {
            Log.d(this.TAG, "BestImage Menu Select failed: null item");
            return false;
        }
        if (PocFeatures.isEnabled(PocFeatures.C2paFileEdit)) {
            baseCommand = new ChangeBestItemCmd2();
        } else {
            baseCommand = new ChangeBestItemCmd();
        }
        baseCommand.execute(this.mEventContext, selectedItems, bestItem, new SingleTakenShotFormat());
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_bestshot).setFastOptionMenu().setShowAsActionFlag(2).setExecutableOnScreenLocked();
    }
}
