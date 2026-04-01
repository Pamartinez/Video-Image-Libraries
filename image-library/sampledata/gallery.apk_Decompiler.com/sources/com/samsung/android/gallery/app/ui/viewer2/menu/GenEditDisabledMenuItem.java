package com.samsung.android.gallery.app.ui.viewer2.menu;

import F6.f;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GenEditDisabledMenuItem extends EditMenuItem {
    public static final boolean SUPPORT = PreferenceFeatures.OneUi6x.SUPPORT_PE_GEN_EDIT;

    public GenEditDisabledMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.photo_assist);
    }

    private boolean isSupportEdit(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isImage() || mediaItem.isGif()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0() {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setMenuAttribute$1(MediaItem mediaItem, String str) {
        if (!SUPPORT || isSupportEdit(this.mEventContext.getCurrentItem())) {
            return false;
        }
        return true;
    }

    public boolean onMenuSelectInternal(View view) {
        Utils.showToast(this.mEventContext.getContext(), (int) R.string.photo_assist_only_works);
        return true;
    }

    public void setMenuAttribute() {
        super.setMenuAttribute();
        setIconResId(R.drawable.gallery_ic_detail_intelligence).exclude("location://sharing/albums/fileList", "location://mtp/fileList").setEnabledDimCondition(new f(7)).validate(new b(this, 9));
        if (Features.isEnabled(Features.SUPPORT_AUDIO_ERASER)) {
            validate(ViewerMenuItem.IS_NOT_VIDEO);
        }
    }
}
