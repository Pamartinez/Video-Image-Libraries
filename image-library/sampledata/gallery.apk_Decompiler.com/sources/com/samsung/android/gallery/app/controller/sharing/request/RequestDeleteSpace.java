package com.samsung.android.gallery.app.controller.sharing.request;

import S3.d;
import T3.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDeleteSpace extends AbsRequest {
    public RequestDeleteSpace(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteSpace$1(Integer num, FileItemInterface fileItemInterface, int i2) {
        if (MdeResultCode.isSuccess(num.intValue())) {
            String str = this.TAG;
            Log.sh(str, "galleryLocalGroup deleted. " + MediaItemMde.getGroupId(fileItemInterface));
            return;
        }
        MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteSpace$2(Integer num, FileItemInterface fileItemInterface) {
        MdeGroupHelper.requestLocalGroupDeletion(MediaItemMde.getGroupId(fileItemInterface), new c(this, num, fileItemInterface, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDeleteSpace$3(List list, Integer num) {
        int i2;
        if (!MdeResultCode.isSuccess(num.intValue())) {
            Log.she(this.TAG, "Failed to requestDeleteSpace.");
            if (list.size() > 1) {
                i2 = R.string.could_not_delete_shared_albums;
            } else {
                i2 = R.string.could_not_delete_shared_album;
            }
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), i2);
            return;
        }
        Log.sh(this.TAG, "requestDeleteSpace succeed.");
        list.stream().filter(new d(8)).forEach(new N3.c(26, this, num));
    }

    private void requestDeleteSpace() {
        Log.sh(this.TAG, "requestDeleteSpace called");
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList != null && !mdeItemList.isEmpty()) {
            MdeSharingHelper.requestSpaceDeletion(mdeItemList, new N3.c(25, this, mdeItemList));
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

    public void onPostExecute(boolean z) {
        if (z) {
            this.mBlackboard.postEvent(EventMessage.obtain(1003));
        }
    }

    public void request() {
        requestDeleteSpace();
    }
}
