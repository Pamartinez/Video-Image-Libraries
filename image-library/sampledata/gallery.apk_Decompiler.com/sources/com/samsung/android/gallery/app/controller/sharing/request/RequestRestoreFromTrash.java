package com.samsung.android.gallery.app.controller.sharing.request;

import Ba.h;
import T3.g;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
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
public class RequestRestoreFromTrash extends AbsRequest {
    List<FileItemInterface> mList;
    BaseCommand mRequestCmd;

    public RequestRestoreFromTrash(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
        this.mList = objArr[1];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(String str, String str2, List list, long j2, List list2, Integer num) {
        String str3 = this.TAG;
        Log.sh(str3, "restoreFromTrash" + Logger.vt(StringCompat.substring(str, 4), str2, Integer.valueOf(list.size()), num, Long.valueOf(j2)));
        if (!MdeResultCode.isSuccess(num.intValue()) || list2.size() > 0) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), MdeServiceString.getDeleteUnknownError(this.mAppContext, list2.size()));
        }
        updateFamilyQuota();
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
        List list2 = list;
        MdeSharingHelper.requestRestoreFromTrash(str2, str3, list2, new g(this, str2, str3, list2, currentTimeMillis));
    }

    private void updateFamilyQuota() {
        EventContext eventContext = getEventContext();
        if (eventContext != null) {
            this.mRequestCmd.execute(eventContext, RequestCmdType.REQUEST_FAMILY_QUOTA);
        } else {
            Log.sh(this.TAG, "updateFamilyQuota failed after restoring from trash");
        }
    }

    public List<FileItemInterface> getMdeItemList() {
        return this.mList;
    }

    public int getRunningStringId() {
        return MdeServiceString.getRestoreRunning(getItemTypeWithMediaList());
    }

    public void request() {
        Log.sh(this.TAG, "restoreFromTrash");
        processBySpace(this.mList, new h(15, this));
    }

    public void setRequestCmd(BaseCommand baseCommand) {
        this.mRequestCmd = baseCommand;
    }
}
