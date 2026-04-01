package com.samsung.android.gallery.app.ui.viewer2.menu;

import O3.b;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.NondestructiveSaveRemasterCmd;
import com.samsung.android.gallery.app.controller.internals.SaveAsRemasterCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterSaveMenuItem extends ViewerMenuItem {
    public RemasterSaveMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.save);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showSaveConfirmDialog$1(MediaItem mediaItem, EventContext eventContext, ArrayList arrayList) {
        int i2;
        boolean z;
        boolean z3 = false;
        if (arrayList != null) {
            i2 = ((Integer) arrayList.get(0)).intValue();
        } else {
            i2 = 0;
        }
        if (i2 == 1) {
            z = true;
        } else {
            z = false;
        }
        if (i2 == 2) {
            z3 = true;
        }
        if (z) {
            new NondestructiveSaveRemasterCmd().execute(this.mEventContext, mediaItem);
        } else if (z3) {
            new SaveAsRemasterCmd().execute(this.mEventContext, mediaItem);
        }
    }

    private void showSaveConfirmDialog(Context context, MediaItem mediaItem) {
        if (context == null) {
            Log.e(this.TAG, "failed to show confirm dialog -> Context was null");
            return;
        }
        DataCollectionDelegate.getInitInstance(this.mEventContext).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("title", context.getString(R.string.remaster_save_as_still_image)).appendArg("description", context.getString(R.string.remaster_saving_motion_photo_into_still_image)).appendArg("option1", context.getString(R.string.save)).appendArg("option2", context.getString(R.string.save_as_copy)).build()).setOnDataCompleteListener(new b(9, this, mediaItem)).start();
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "Download Menu Select failed: null item");
            return false;
        }
        boolean isEmpty = TextUtils.isEmpty(MediaItemSuggest.getOriginPath(currentItem));
        boolean z = !isEmpty;
        if (!currentItem.isRevitalization() || isEmpty) {
            Log.e((CharSequence) this.TAG, "failed to save a remastered image: ", Logger.v(Boolean.valueOf(currentItem.isRevitalization()), Boolean.valueOf(z)));
            Utils.showToast(this.mEventContext.getContext(), (int) R.string.image_save_fail);
            return true;
        }
        if (LocationKey.isRemasterPictures(this.mEventContext.getLocationKey())) {
            this.mActionInvoker.invoke(ViewerAction.PREPARE_FORCE_SWIPE, new Object[0]);
        }
        if (PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_REMASTER) {
            this.mActionInvoker.invoke(ViewerAction.STOP_GIF, new Object[0]);
            if (currentItem.isMotionPhoto()) {
                showSaveConfirmDialog(this.mEventContext.getContext(), currentItem);
                return true;
            }
            new NondestructiveSaveRemasterCmd().execute(this.mEventContext, currentItem);
            return true;
        }
        new SaveAsRemasterCmd().execute(this.mEventContext, currentItem);
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.gallery_ic_detail_save).setFastOptionMenu().setShowAsActionFlag(6).include("location://revitalized/fileList", "location://revitalized/single").validate(ViewerMenuItem.IS_NOT_BROKEN).validate(new c(20));
    }
}
