package com.samsung.android.gallery.app.controller.sharing.request;

import Qb.c;
import T3.f;
import android.util.Pair;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.MediaType;
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
public class RequestEmptyFromTrash extends AbsRequest {
    public RequestEmptyFromTrash(EventContext eventContext, Object[] objArr) {
        super(eventContext, objArr);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$0(Pair pair, long j2, List list, Integer num) {
        String str = this.TAG;
        Log.sh(str, "EmptyTrash" + Logger.vt(StringCompat.substring((String) pair.second, 4), pair.first, num, Integer.valueOf(list.size()), Long.valueOf(j2)));
        if (!MdeResultCode.isSuccess(num.intValue()) || !list.isEmpty()) {
            MdeAlbumHelper.handleMdeFailResultCode(this.mAppContext, num.intValue(), MdeServiceString.getDeleteUnknownError(this.mAppContext, list.size()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$request$1(Pair pair) {
        MdeSharingHelper.requestEmptyFromTrash((String) pair.second, (String) pair.first, new f(this, pair, System.currentTimeMillis(), 0));
    }

    public int getRunningStringId() {
        return MdeServiceString.getDeleteRunning(MediaType.Media);
    }

    public void request() {
        Log.sh(this.TAG, "EmptyTrash");
        new MdeDatabase().getSharedTrashSpaces().forEach(new c(21, this));
    }
}
