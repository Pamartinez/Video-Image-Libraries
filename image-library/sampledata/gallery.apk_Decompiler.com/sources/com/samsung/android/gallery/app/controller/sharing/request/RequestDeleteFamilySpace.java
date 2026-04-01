package com.samsung.android.gallery.app.controller.sharing.request;

import B4.c;
import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDeleteFamilySpace extends AbsRequest {
    public RequestDeleteFamilySpace(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    private void deleteFamilyAutoAlbum() {
        AutoAlbumHelper.getInstance().deleteFamilyAutoAlbum();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteFamilySpace$0(boolean z) {
        String str;
        Context context = null;
        this.mBlackboard.post("command://FamilySharedAlbumRemoveFinished", (Object) null);
        if (getEventContext() != null) {
            context = getEventContext().getContext();
        }
        if (context != null) {
            if (z) {
                str = context.getString(R.string.shared_family_album_delete_success);
            } else {
                str = SeApiCompat.naturalizeText(context.getString(R.string.could_not_delete_try_again, new Object[]{context.getString(R.string.shared_family_album)}));
            }
            Utils.showToast(context, str);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteFamilySpace$1(String str, Integer num) {
        boolean isSuccess = MdeResultCode.isSuccess(num.intValue());
        if (isSuccess) {
            deleteFamilyAutoAlbum();
        } else {
            String str2 = this.TAG;
            Log.sh(str2, "failed to delete family space : " + str);
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue());
        }
        ThreadUtil.runOnUiThread(new c((Object) this, isSuccess, 15));
    }

    private void requestDeleteFamilySpace() {
        Log.sh(this.TAG, "requestDeleteFamilySpace called");
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList.size() == 1 && mdeItemList.get(0) != null) {
            String groupId = MediaItemMde.getGroupId(mdeItemList.get(0));
            MdeGroupHelper.requestFamilyGroupDeletion(groupId, new N3.c(24, this, groupId));
        }
    }

    public List<FileItemInterface> getMdeItemList() {
        return (List) this.mParams[1];
    }

    public int getNetworkErrorStringId() {
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList == null) {
            return -1;
        }
        if (mdeItemList.size() > 1) {
            return R.string.could_not_delete_shared_albums_network_status;
        }
        return R.string.could_not_delete_shared_album_network_status;
    }

    public void request() {
        requestDeleteFamilySpace();
    }
}
