package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import T3.b;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestDeleteFromTrash extends AbsRequest {
    List<FileItemInterface> mList;

    public RequestDeleteFromTrash(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
        this.mList = objArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(String str, String str2, long j2, List list, Integer num) {
        String str3 = this.TAG;
        Log.sh(str3, "deleteFromTrash" + Logger.vt(StringCompat.substring(str, 4), str2, num, Long.valueOf(j2)));
        if (!MdeResultCode.isSuccess(num.intValue()) || !list.isEmpty()) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), MdeServiceString.getDeleteUnknownError(this.mAppContext, list.size()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$1(String str, List list) {
        long currentTimeMillis = System.currentTimeMillis();
        String groupId = MediaItemMde.getGroupId((FileItemInterface) list.get(0));
        if (TextUtils.isEmpty(groupId)) {
            groupId = new MdeDatabase().getGroupIdInSpace(str);
        }
        String str2 = groupId;
        String str3 = str;
        MdeSharingHelper.requestDeleteFromTrash(str2, str3, list, new b(this, str2, str3, currentTimeMillis));
    }

    public List<FileItemInterface> getMdeItemList() {
        return this.mList;
    }

    public int getRunningStringId() {
        return MdeServiceString.getDeleteRunning(getItemTypeWithMediaList());
    }

    public void request() {
        Log.sh(this.TAG, "deleteFromTrash");
        processBySpace(this.mList, new h(11, this));
    }
}
