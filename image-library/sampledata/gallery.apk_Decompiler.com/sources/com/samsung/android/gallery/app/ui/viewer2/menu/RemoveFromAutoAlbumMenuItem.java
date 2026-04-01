package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.album.RemoveAutoUpdatedItemsCmd;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoveFromAutoAlbumMenuItem extends ViewerMenuItem {
    public RemoveFromAutoAlbumMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.remove_abb);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (ArgumentsUtil.getArgValue(str, "type", 0) == AlbumType.AutoUpdated.toInt()) {
            return true;
        }
        return false;
    }

    public boolean onMenuSelectInternal(View view) {
        boolean z;
        String locationKey = this.mEventContext.getLocationKey();
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || locationKey == null || TextUtils.isEmpty(locationKey)) {
            String str = this.TAG;
            boolean z3 = true;
            if (currentItem == null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (locationKey != null) {
                z3 = false;
            }
            Log.d(str, "failed", valueOf, Boolean.valueOf(z3));
            return false;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_REMOVE_FROM_AUTO_UPDATING_ALBUM.toString());
        new RemoveAutoUpdatedItemsCmd().execute(this.mEventContext, Integer.valueOf(ArgumentsUtil.getArgValue(locationKey, "id", 0)), Integer.valueOf(ArgumentsUtil.getArgValue(locationKey, "type", AlbumType.None.toInt())));
        return false;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_bottombar_remove).setFastOptionMenu().setShowAsActionFlag(2).validate(new c(24));
    }
}
