package com.samsung.android.gallery.module.trash.factory;

import android.content.Context;
import android.net.Uri;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.cloud.SamsungCloudCompat;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhTrashFactory extends AbsTrashFactory {
    public CmhTrashFactory(Context context) {
        super(context);
    }

    public int[] bulkDelete(TrashDeleteItem trashDeleteItem, boolean z) {
        int i2;
        int[] iArr = new int[2];
        long currentTimeMillis = System.currentTimeMillis();
        putIds(trashDeleteItem);
        int size = this.mLocalDelete.size();
        if ((size >= 33 || z) && !this.mLocalDelete.isEmpty()) {
            i2 = this.mReferenceManager.delete(getRefFilesUri(), getDeleteWhere(size), (String[]) this.mLocalDelete.toArray(new String[0]));
            Log.e(this.TAG, "bulk delete time [" + (System.currentTimeMillis() - currentTimeMillis) + "][" + size + "][" + i2 + "]");
            this.mLocalDelete.clear();
        } else {
            i2 = 0;
        }
        iArr[0] = i2;
        return iArr;
    }

    public int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z) {
        return 0;
    }

    public boolean deleteCloudFromRef(FileItemInterface fileItemInterface) {
        return SamsungCloudCompat.delete(AppResources.getAppContext(), fileItemInterface.getCloudServerId());
    }

    public boolean deleteCloudRecordDeleteTable(String str) {
        return false;
    }

    public String getCloudThumbRootPath() {
        return "/storage/emulated/0/.cloudagent/thumbnail/";
    }

    public Uri getDeleteUri() {
        return getRefFilesUri();
    }

    public long getTargetId(FileItemInterface fileItemInterface) {
        return fileItemInterface.getMediaId();
    }

    public boolean supportTrash() {
        return false;
    }

    public String tag() {
        return "CmhTrashFactory";
    }

    public boolean useStoreApi() {
        return true;
    }
}
