package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GifRemasterMenuItem extends RemasterMenuItem {
    public GifRemasterMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remaster_gif);
    }

    private void updatePreferenceForBadge() {
        if (PreferenceCache.RemasterGifNewBadge.compareAndSet(true, false)) {
            this.mActionInvoker.invoke(ViewerAction.UPDATE_MORE_OPTION_NEW_BADGE, new Object[0]);
        }
    }

    public boolean onMenuSelectInternal(View view) {
        updatePreferenceForBadge();
        return super.onMenuSelectInternal(view);
    }

    public boolean supportedRemaster(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF) || mediaItem == null || mediaItem.isRemasterSaved() || ViewerMenuItem.hasPortraitChanged(mediaItem) || !mediaItem.isGif()) {
            return false;
        }
        return true;
    }
}
