package com.samsung.android.gallery.app.ui.viewer2.menu;

import K5.a;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.abstraction.DownloadType;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AddPortraitEffectMenuItem extends ViewerMenuItem {
    public AddPortraitEffectMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.add_portrait_effect);
    }

    private void execute(MediaItem mediaItem, boolean z) {
        if (Features.isEnabled(Features.SUPPORT_PHOTO_EDITOR_PORTRAIT_EFFECT) && mediaItem != null) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, mediaItem);
            new StartSimplePhotoEditorCmd().execute(this.mEventContext, mediaItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_bgblur);
        } else if (mediaItem != null) {
            new PlayDualShotCmd().execute(this.mEventContext, mediaItem, Integer.valueOf(PlayDualShotCmd.MORE_OPTION));
        } else if (z) {
            Log.e(this.TAG, "change portrait effect after download failed: downloaded item is null");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$1(Object obj) {
        execute((MediaItem) obj, true);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "AddPortraitEffect Menu Select failed: null item");
            return false;
        } else if (currentItem.isCloudOnly()) {
            executeAfterDownload(this.mEventContext, currentItem, DownloadType.SHOT_MODE, new a(27, this));
            return true;
        } else {
            execute(currentItem, false);
            return true;
        }
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash").validate(ViewerMenuItem.IS_NOT_VIDEO).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new a(0));
    }
}
