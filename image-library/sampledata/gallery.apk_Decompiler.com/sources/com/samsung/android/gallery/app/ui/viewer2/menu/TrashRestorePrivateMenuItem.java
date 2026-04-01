package com.samsung.android.gallery.app.ui.viewer2.menu;

import F3.b;
import J6.c;
import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.service.message.RestoreMsgMgr;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRestorePrivateMenuItem extends ViewerMenuItem {
    public TrashRestorePrivateMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.restore);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$2(MediaItem mediaItem, Context context) {
        this.mEventContext.getBlackboard().postEvent(EventMessage.obtain(3015));
        if (PrivateDatabase.getInstance().untrash(new MediaItem[]{mediaItem}) > 0) {
            ThreadUtil.runOnUiThread(new b(context, RestoreMsgMgr.getSuccessMessage(context, mediaItem.isImage() ? 1 : 0, mediaItem.isImage() ^ true ? 1 : 0), 3));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || !mediaItem.isPrivateItem()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        Context context = this.mEventContext.getContext();
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (context == null || currentItem == null) {
            Log.e(this.TAG, "onMenuSelect failed. null item");
            return false;
        }
        this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        ThreadUtil.postOnBgThread(new c(this, currentItem, context, 28));
        return true;
    }

    public void setMenuAttribute() {
        setFastOptionMenu().setIconResId(R.drawable.menu_bottombar_icon_restore).setShowAsActionFlag(6).validate(new l(14)).include("location://private/trash");
    }
}
