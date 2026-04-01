package com.samsung.android.gallery.module.groupshot;

import android.content.Context;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.GroupItemUtil;
import com.samsung.android.gallery.support.providers.MediaUri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SimilarShotFormat extends GroupShotFormat {
    public FileItemInterface findBestItem(FileItemInterface fileItemInterface) {
        return GroupItemUtil.findBestItem(fileItemInterface, DbKey.FILES_SIMILAR, GroupType.SIMILAR);
    }

    public boolean setBestItemC2pa(Context context, FileItemInterface fileItemInterface, double[] dArr) {
        if (new FilesApi().updateGroupMediaBestImage(MediaUri.getInstance().getGroupTableUri(), fileItemInterface.getFileId(), 1) > 0) {
            return true;
        }
        return false;
    }

    public boolean skipDbUpdate() {
        return false;
    }

    public String tag() {
        return "SimilarShotFormat";
    }

    public boolean unsetBestItemC2pa(FileItemInterface fileItemInterface) {
        if (new FilesApi().updateGroupMediaBestImage(MediaUri.getInstance().getGroupTableUri(), fileItemInterface.getFileId(), 0) > 0) {
            return true;
        }
        return false;
    }

    public int updateMediaBestImage(FileItemInterface fileItemInterface, int i2) {
        return new FilesApi().updateGroupMediaBestImage(MediaUri.getInstance().getGroupTableUri(), fileItemInterface.getFileId(), i2);
    }
}
