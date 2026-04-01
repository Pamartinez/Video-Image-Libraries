package com.samsung.android.gallery.app.ui.viewer2.menu;

import Fa.C0571z;
import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.sec.android.gallery3d.R;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakeBestImageMenuItem extends BestImageMenuItem {
    public SingleTakeBestImageMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker);
    }

    private boolean hasNoBestImage() {
        MediaItem[] selectedItems = this.mEventContext.getSelectedItems();
        if (selectedItems.length != 1 || selectedItems[0].getBestImage() == 1) {
            return false;
        }
        return true;
    }

    private boolean isSupportedToolbarMenu() {
        FoldStateManager instance = FoldStateManager.getInstance(this.mEventContext.getBlackboard());
        if (instance == null || !instance.isTableMode() || !ResourceCompat.isLandscape((Context) this.mEventContext.getActivity())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return isSupportedToolbarMenu();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        return hasNoBestImage();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        return Arrays.stream(this.mEventContext.getSelectedItems()).noneMatch(new C0571z(5));
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_bestshot).setFastOptionMenu().setShowAsActionFlag(2).setExecutableOnScreenLocked().validate(new m(this, 0)).validate(new m(this, 1)).validate(new m(this, 2));
    }
}
