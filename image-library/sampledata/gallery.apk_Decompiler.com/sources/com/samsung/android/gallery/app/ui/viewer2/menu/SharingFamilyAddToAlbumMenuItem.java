package com.samsung.android.gallery.app.ui.viewer2.menu;

import android.text.TextUtils;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingFamilyAddToAlbumMenuItem extends ViewerMenuItem {
    public SharingFamilyAddToAlbumMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.add_to_album);
    }

    private void addContentsToSharedAlbum() {
        String argValue = ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "groupId");
        String argValue2 = ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), BundleKey.SPACE_ID);
        if (!TextUtils.isEmpty(argValue) && !TextUtils.isEmpty(argValue2)) {
            RequestSharedAlbumCmd requestSharedAlbumCmd = new RequestSharedAlbumCmd();
            EventContext eventContext = this.mEventContext;
            requestSharedAlbumCmd.execute(eventContext, RequestCmdType.REQUEST_ADD_CONTENTS, argValue2, argValue, eventContext.getSelectedItems());
        }
    }

    private void removeContentsFromAutoAlbum() {
        int i2 = UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mEventContext.getLocationKey(), "id"));
        if (i2 > 0 && this.mEventContext.getCurrentItem() != null) {
            AutoAlbumHelper.getInstance().removeAutoAlbumContent(this.mEventContext.getCurrentItem().getFileId(), (long) i2);
        }
    }

    public boolean onMenuSelectInternal(View view) {
        addContentsToSharedAlbum();
        removeContentsFromAutoAlbum();
        forceSwipe(this.mEventContext.getBlackboard());
        this.mEventContext.getBlackboard().postBroadcastEvent(EventMessage.obtain(101));
        return true;
    }

    public void setMenuAttribute() {
        setIconResId(R.drawable.menu_bottombar_icon_add).setIconTintId(R.color.daynight_gallery_color_primary).setShowAsActionFlag(6).include("location://family/shared/suggested/fileList", "location://timeline").setFastOptionMenu();
    }
}
