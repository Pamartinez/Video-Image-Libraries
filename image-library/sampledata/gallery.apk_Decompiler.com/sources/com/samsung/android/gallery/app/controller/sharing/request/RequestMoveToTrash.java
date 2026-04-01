package com.samsung.android.gallery.app.controller.sharing.request;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestMoveToTrash extends RequestDeleteContent {
    private String mGroupId;
    private String mSpaceId;

    public RequestMoveToTrash(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    public int getRunningStringId() {
        return MdeServiceString.getMoveToTrashRunning(getItemTypeWithMediaList());
    }

    public void request() {
        String str;
        String str2;
        if (getMdeItemList() != null && !getMdeItemList().isEmpty()) {
            Object[] objArr = this.mParams;
            if (objArr.length > 2) {
                str = (String) objArr[2];
            } else {
                str = MediaItemMde.getGroupId(getMdeItemList().get(0));
            }
            this.mGroupId = str;
            Object[] objArr2 = this.mParams;
            if (objArr2.length > 3) {
                str2 = (String) objArr2[3];
            } else {
                str2 = MediaItemMde.getSpaceId(getMdeItemList().get(0));
            }
            this.mSpaceId = str2;
            if (TextUtils.isEmpty(this.mGroupId)) {
                this.mGroupId = new MdeDatabase().getGroupIdInSpace(this.mSpaceId);
            }
            String str3 = this.TAG;
            Log.sh(str3, "moveToTrash " + Logger.vt(StringCompat.substring(this.mGroupId, 4), this.mSpaceId, Integer.valueOf(getMdeItemList().size())));
            MdeSharingHelper.requestMoveToTrash(this.mGroupId, this.mSpaceId, getMdeItemList(), getResultsCallback());
        }
    }
}
