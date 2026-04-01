package com.samsung.android.gallery.app.ui.viewer2.menu;

import A.a;
import android.content.ClipData;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartSimplePhotoEditorCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.editor.ModeInfo;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SafeClipboard;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PasteClipboardMenuItem extends ViewerMenuItem {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class FeatureHolder {
        static final boolean SUPPORT = PreferenceFeatures.isEnabled(PreferenceFeatures.PasteClipboardViewer);
    }

    public PasteClipboardMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.paste_clipboard);
    }

    private Uri getClipboardUri() {
        Context context = this.mEventContext.getContext();
        if (context != null) {
            try {
                ClipData primaryClip = new SafeClipboard(context).getPrimaryClip();
                if (primaryClip != null) {
                    return primaryClip.getItemAt(0).getUri();
                }
                return null;
            } catch (Exception e) {
                a.s(e, new StringBuilder("getClipboardUri failed. e="), this.TAG);
            }
        }
        return null;
    }

    private boolean hasImageOnClipboard() {
        return SafeClipboard.computeImageClipAvailable(false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isCloudOnly()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        if (mediaItem == null || mediaItem.isGif()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$2(MediaItem mediaItem, String str) {
        return !Features.isEnabled(Features.IS_GED);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$3(MediaItem mediaItem, String str) {
        if (!FeatureHolder.SUPPORT || !hasImageOnClipboard()) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        EventContext eventContext = this.mEventContext;
        if (eventContext == null) {
            Log.e(this.TAG, "paste from clipboard failed. null event context");
        } else {
            MediaItem currentItem = eventContext.getCurrentItem();
            Uri clipboardUri = getClipboardUri();
            if (currentItem == null || clipboardUri == null) {
                Log.e(this.TAG, "paste from clipboard failed. null item");
                Utils.showToast(this.mEventContext.getContext(), (int) R.string.there_is_no_media_file_to_paste);
                SafeClipboard.computeImageClipAvailable(true);
                this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
            } else {
                this.mActionInvoker.invoke(ViewerAction.ZOOM_TO_MIN_SCALE, new Object[0]);
                this.mActionInvoker.invoke(ViewerAction.PREPARE_EDITOR, currentItem);
                ActionInvoker actionInvoker = this.mActionInvoker;
                ViewerAction viewerAction = ViewerAction.SET_DECOR_VISIBILITY;
                Boolean bool = Boolean.FALSE;
                actionInvoker.invoke(viewerAction, bool);
                new StartSimplePhotoEditorCmd().execute(this.mEventContext, currentItem, StartSimplePhotoEditorCmd.PhotoEditorMode.spe_sticker, null, bool, new ModeInfo(1, clipboardUri));
                postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_PASTE_FROM_CLIPBOARD_IN_VIEWER.toString());
            }
        }
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).exclude("location://mtp/fileList", "location://trash", "location://family/shared/trash", "location://dynamicViewList", "location://AllDayTimeLapse", "location://revitalized", "location://revitalized/fileList", "location://revitalized/single", "location://colorCorrectView").validate(ViewerMenuItem.IS_NOT_VIDEO).validate(ViewerMenuItem.IS_NOT_KNOX).validate(ViewerMenuItem.IS_NOT_AFW).validate(ViewerMenuItem.IS_NOT_UPSM).validate(ViewerMenuItem.IS_NOT_POSTPROCESSING).validate(ViewerMenuItem.IS_NOT_DRM).validate(ViewerMenuItem.IS_NOT_BROKEN).validate(ViewerMenuItem.IS_NOT_URI_ITEM).validate(ViewerMenuItem.IS_NOT_SUGGESTION_VIEW_LIST).validate(ViewerMenuItem.IS_NOT_SHARING).validate(new c(12)).validate(new c(13)).validate(new c(14)).validate(new b(this, 15));
    }
}
