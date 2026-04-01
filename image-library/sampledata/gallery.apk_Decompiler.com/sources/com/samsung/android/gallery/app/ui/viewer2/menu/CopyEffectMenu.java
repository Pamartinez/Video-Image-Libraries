package com.samsung.android.gallery.app.ui.viewer2.menu;

import K5.a;
import M9.o;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.CopyEffectCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CopyEffectMenu extends ViewerMenuItem {
    public CopyEffectMenu(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.copy_edits);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !ViewerMenuItem.isCloudOnly(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        return supportCopyEffect(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateMenu$3() {
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    private boolean supportCopyEffect(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.hasFilterAndTone() || !FileUtils.exists(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateMenu(Object obj) {
        if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
            ThreadUtil.postOnUiThread(new o(26, this));
        }
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "null item");
            return false;
        }
        new CopyEffectCmd().addExecuteListener(new a(28, this)).execute(this.mEventContext, new MediaItem[]{currentItem});
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://family/shared/trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://revitalized", "location://revitalized/fileList", "location://revitalized/single", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_VIDEO).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(10)).validate(new a(11)).validate(new b(this, 4));
    }
}
