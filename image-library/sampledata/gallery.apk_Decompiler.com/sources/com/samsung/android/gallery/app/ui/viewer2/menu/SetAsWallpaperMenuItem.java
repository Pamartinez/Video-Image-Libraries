package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.SetAsWallpaperChooserDialogCmd;
import com.samsung.android.gallery.app.controller.externals.SetWallpaperCmd;
import com.samsung.android.gallery.app.controller.internals.SetAsChooserDialogCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SetAsWallpaperMenuItem extends ViewerMenuItem {
    public SetAsWallpaperMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.set_as_wallpaper);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if ((mediaItem == null || !mediaItem.isImage()) && !supportVideoWallpaper(mediaItem)) {
            return false;
        }
        return true;
    }

    private boolean supportVideoWallpaper(MediaItem mediaItem) {
        return !ViewerMenuItem.isCloudOnly(mediaItem) && supportVideoWallpaper(this.mEventContext.getContext());
    }

    public boolean isRotateRecoveryRequired() {
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "SetAsWallPaper Menu Select failed: null item");
            return false;
        }
        publishPopoverInfo(getMenuId(), view);
        if (Features.isEnabled(Features.SET_AS_CHOOSER_BY_INTENT_FILTER)) {
            new SetAsChooserDialogCmd().execute(this.mEventContext, new Object[0]);
            return true;
        } else if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            new SetAsWallpaperChooserDialogCmd().execute(this.mEventContext, new Object[0]);
            return true;
        } else {
            new SetWallpaperCmd().execute(this.mEventContext, currentItem);
            return true;
        }
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://superSlowViewList", "location://highlightViewList", "location://SingleTakenShotviewer/suggestionHighlight", "location://SingleTakenShotviewer/highlight", "location://SingleTakenShotviewer/superslow", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(new b(this, 19)).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_KNOX).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_SHARING).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_REMOTE_ITEM);
    }

    private boolean supportVideoWallpaper(Context context) {
        if (ViewerMenuItem.isDexMode(context)) {
            return false;
        }
        if (Features.isEnabled(Features.USE_SET_AS_VIDEO_WALLPAPER) || Features.isEnabled(Features.USE_SET_AS_COVER_VIDEO_WALLPAPER)) {
            return true;
        }
        return false;
    }
}
