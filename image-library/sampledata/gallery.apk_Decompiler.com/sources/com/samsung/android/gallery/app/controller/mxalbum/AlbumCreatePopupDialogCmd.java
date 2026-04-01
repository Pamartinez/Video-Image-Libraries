package com.samsung.android.gallery.app.controller.mxalbum;

import O3.y;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.mde.MdeSharingService;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.UriBuilder;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumCreatePopupDialogCmd extends AbsAlbumCreatePopupDialogCmd {
    private boolean mSupportFamilySharedAlbumCreation;
    private boolean mSupportSharedAlbumCreation;

    private String buildCreateAlbumTypes() {
        String str;
        if (Features.isEnabled(Features.SUPPORT_AUTO_UPDATING_ALBUM)) {
            str = "2131296895,2131296902,2131296896";
        } else {
            str = "2131296895,2131296902";
        }
        if (supportSharedAlbumCreation()) {
            str = C0212a.A(str, ",2131296909");
            if (supportFamilySharedAlbumCreation()) {
                return C0212a.A(str, ",2131296901");
            }
        }
        return str;
    }

    private boolean supportFamilySharedAlbumCreation() {
        if (!this.mSupportFamilySharedAlbumCreation || !Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM)) {
            return false;
        }
        return true;
    }

    private boolean supportSharedAlbumCreation() {
        if (!this.mSupportSharedAlbumCreation || !LocationKey.isAlbumsMatch(getHandler().getLocationKey()) || !MdeSharingService.getInstance().isServiceAvailable()) {
            return false;
        }
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        super.onExecute(eventContext, objArr);
        this.mSupportFamilySharedAlbumCreation = objArr[0].booleanValue();
        this.mSupportSharedAlbumCreation = objArr[1].booleanValue();
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/AlbumCreatePopup").appendArg("create_album_types", buildCreateAlbumTypes()).build()).setOnDataCompleteListener(new y(7, this)).start();
    }
}
