package com.samsung.android.gallery.app.ui.viewer2.menu;

import M9.o;
import Ob.a;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DownloadFromRemoteMenuItem extends ViewerMenuItem {
    public DownloadFromRemoteMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.download);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$1() {
        Utils.showToast(this.mEventContext.getContext(), (int) R.string.toast_image_saved);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$2(MediaItem mediaItem) {
        RemoteGalleryUtil.download(mediaItem, new o(27, this));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.getStorageType() != StorageType.RemoteItem) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null || currentItem.getStorageType() != StorageType.RemoteItem) {
            Log.w(this.TAG, "Menu Select failed. null or wrong item");
            return false;
        }
        SimpleThreadPool.getInstance().execute(new a(5, this, currentItem));
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_save).setFastOptionMenu().setShowAsActionFlag(2).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(new a(25));
    }
}
