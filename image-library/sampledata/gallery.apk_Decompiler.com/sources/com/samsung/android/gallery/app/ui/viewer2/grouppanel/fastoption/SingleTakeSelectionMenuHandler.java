package com.samsung.android.gallery.app.ui.viewer2.grouppanel.fastoption;

import M4.j;
import android.view.Menu;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.menu.BestImageMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.GroupShotDeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.SingleTakeSaveMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.SingleTakeShareMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.ViewerMenuItem;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.fastoption2.FastOptionItemParams;
import com.samsung.android.gallery.widget.fastoption2.FastOptionView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakeSelectionMenuHandler {
    private final FastOptionView mFastOptionView;
    private final ArrayList<ViewerMenuItem> mMenuItems;

    public SingleTakeSelectionMenuHandler(EventContext eventContext, ActionInvoker actionInvoker, FastOptionView fastOptionView) {
        ArrayList<ViewerMenuItem> arrayList = new ArrayList<>();
        this.mMenuItems = arrayList;
        this.mFastOptionView = fastOptionView;
        arrayList.add(new BestImageMenuItem(eventContext, actionInvoker));
        arrayList.add(new SingleTakeShareMenuItem(eventContext, actionInvoker));
        arrayList.add(new SingleTakeSaveMenuItem(eventContext, actionInvoker));
        arrayList.add(new GroupShotDeleteMenuItem(eventContext, actionInvoker, true));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$removeUnsupportedMenu$0(ViewerMenuItem viewerMenuItem) {
        return viewerMenuItem instanceof BestImageMenuItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$removeUnsupportedMenu$1(ViewerMenuItem viewerMenuItem) {
        return viewerMenuItem instanceof SingleTakeSaveMenuItem;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$removeUnsupportedMenu$2(ViewerMenuItem viewerMenuItem) {
        return viewerMenuItem instanceof GroupShotDeleteMenuItem;
    }

    public void addFastOptionViewItem() {
        Iterator<ViewerMenuItem> it = this.mMenuItems.iterator();
        while (it.hasNext()) {
            ViewerMenuItem next = it.next();
            this.mFastOptionView.addItem(FastOptionItemParams.builder().setTitleRes(next.getTitleResId()).setDrawable(next.getDrawable()).setMenuId(next.getMenuId()).setTitle(next.getTitle()).setFastOptionMenu(true).build());
        }
    }

    public void addPopupMenu(Menu menu, MediaItem mediaItem) {
        Iterator<ViewerMenuItem> it = this.mMenuItems.iterator();
        int i2 = 1;
        while (it.hasNext()) {
            ViewerMenuItem next = it.next();
            if (next.getTitleResId() != R.string.set_as_best_shot || mediaItem.getBestImage() != 1) {
                menu.add(1, next.getMenuId(), i2, next.getTitleResId());
                i2++;
            }
        }
    }

    public void onItemSelected(int i2, View view) {
        Iterator<ViewerMenuItem> it = this.mMenuItems.iterator();
        while (it.hasNext()) {
            ViewerMenuItem next = it.next();
            if (next.getMenuId() == i2) {
                Log.d("SingleTakeSelectionMenuHandler", "onMenuSelect", next.tag());
                next.onMenuSelect(view);
                return;
            }
        }
    }

    public void removeUnsupportedMenu(boolean z, boolean z3) {
        if (z || z3) {
            this.mMenuItems.removeIf(new j(19));
            this.mMenuItems.removeIf(new j(20));
        }
        if (z3) {
            this.mMenuItems.removeIf(new j(21));
        }
    }

    public void updateDim(boolean z, int i2) {
        boolean z3;
        Iterator<ViewerMenuItem> it = this.mMenuItems.iterator();
        while (it.hasNext()) {
            ViewerMenuItem next = it.next();
            if (next.getTitleResId() == R.string.set_as_best_shot) {
                this.mFastOptionView.updateFastOptionItemDisabledDim(next.getMenuId(), z);
            } else {
                FastOptionView fastOptionView = this.mFastOptionView;
                int menuId = next.getMenuId();
                if (i2 == 0) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                fastOptionView.updateFastOptionItemDisabledDim(menuId, z3);
            }
        }
    }
}
