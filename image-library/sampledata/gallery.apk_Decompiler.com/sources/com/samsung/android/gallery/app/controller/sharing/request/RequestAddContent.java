package com.samsung.android.gallery.app.controller.sharing.request;

import N3.c;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.local.table.HistoryTable;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.debugger.LocalProviderDebugHelper;
import com.samsung.android.gallery.module.mde.MdeAlbumHelper;
import com.samsung.android.gallery.module.mde.MdeServiceString;
import com.samsung.android.gallery.module.mde.MdeSharingHelper;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.mobileservice.social.share.result.SharedItemListResult;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RequestAddContent extends AbsRequest {
    public RequestAddContent(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestAddContents$0(List list, SharedItemListResult sharedItemListResult) {
        int code = sharedItemListResult.getStatus().getCode();
        String str = this.TAG;
        Log.sh(str, "requestAddContents result" + Logger.v(Integer.valueOf(code)));
        if (MdeResultCode.isSuccess(sharedItemListResult.getStatus().getCode())) {
            LocalProviderDebugHelper.addOperationHistory(HistoryTable.ActionKeyword.SHARE_SHARED, list);
        } else {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, code, getUnknownErrorStringId());
        }
    }

    private void requestAddContents() {
        Object[] objArr = this.mParams;
        String str = (String) objArr[1];
        String str2 = (String) objArr[2];
        List<FileItemInterface> mdeItemList = getMdeItemList();
        if (mdeItemList == null || mdeItemList.isEmpty()) {
            Log.she(this.TAG, "requestAddContents : items list is null or empty.");
            return;
        }
        String str3 = this.TAG;
        Log.sh(str3, "requestAddContents : items count[" + mdeItemList.size() + "]");
        MdeSharingHelper.requestAddContents(getContext(), str, str2, mdeItemList, new c(20, this, mdeItemList));
    }

    public List<FileItemInterface> getMdeItemList() {
        Object obj = this.mParams[3];
        if (obj != null) {
            return (List) Arrays.stream((MediaItem[]) obj).collect(Collectors.toList());
        }
        return null;
    }

    public int getNetworkErrorStringId() {
        return MdeServiceString.getAddNetworkError(getItemTypeWithMediaList());
    }

    public int getUnknownErrorStringId() {
        return MdeServiceString.getAddUnknownError(getItemTypeWithMediaList());
    }

    public void request() {
        requestAddContents();
    }
}
