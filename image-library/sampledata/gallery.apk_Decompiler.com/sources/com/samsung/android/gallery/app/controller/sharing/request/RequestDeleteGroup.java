package com.samsung.android.gallery.app.controller.sharing.request;

import T3.c;
import android.content.Context;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDeleteGroup extends AbsRequest {
    public RequestDeleteGroup(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteGroup$0(String str, List list, int i2) {
        if (!MdeResultCode.isSuccess(i2)) {
            String str2 = this.TAG;
            Log.sh(str2, "failed to delete group: " + str);
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, i2);
            return;
        }
        Context context = this.mAppContext;
        Utils.showToast(context, SeApiCompat.naturalizeText(context.getString(R.string.shared_album_delete_success, new Object[]{((FileItemInterface) list.get(0)).getTitle()})));
    }

    private void requestDeleteGroup() {
        Log.sh(this.TAG, "requestDeleteGroup called");
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList.size() == 1 && mdeItemList.get(0) != null) {
            String groupId = MediaItemMde.getGroupId(mdeItemList.get(0));
            MdeGroupHelper.requestLocalGroupDeletion(groupId, new c(this, groupId, mdeItemList, 0));
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
        requestDeleteGroup();
    }
}
