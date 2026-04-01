package com.samsung.android.gallery.module.trash.factory;

import A.a;
import N2.j;
import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Iterator;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpUTrashFactory extends MpTTrashFactory {
    public MpUTrashFactory(Context context) {
        super(context);
    }

    public int bulkUpdate(TrashDeleteItem trashDeleteItem, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        putUpdateIds(trashDeleteItem);
        int size = this.mSecLocalDelete.size();
        if ((size < 100 && !z) || this.mSecLocalDelete.isEmpty()) {
            return 0;
        }
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        Iterator<String> it = this.mSecLocalDelete.iterator();
        while (it.hasNext()) {
            stringJoiner.add(it.next());
        }
        int updateIsTrashed = updateIsTrashed(stringJoiner.toString(), true);
        this.mSecLocalDelete.clear();
        j.m(currentTimeMillis, "]", this.TAG, a.h(size, updateIsTrashed, "bulk update is_trashed [", "][", "]["));
        return updateIsTrashed;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.mReferenceManager.deleteMatchInclude(uri, str, strArr);
    }

    public void putUpdateIds(TrashDeleteItem trashDeleteItem) {
        if (!PocFeatures.SUPPORT_QUICK_DELETE && trashDeleteItem != null && trashDeleteItem.isLocal() && trashDeleteItem.getSefFileType() != 2928 && !trashDeleteItem.getPath().startsWith("/data/sec/")) {
            this.mSecLocalDelete.add(String.valueOf(trashDeleteItem.getFileId()));
        }
    }

    public String tag() {
        return "MpUTrashFactory";
    }
}
