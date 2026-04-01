package com.samsung.android.gallery.module.groupshot;

import android.content.Context;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.GroupItemUtil;
import com.samsung.android.gallery.support.library.sef.SefInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SingleTakenShotFormat extends GroupShotFormat {
    public FileItemInterface findBestItem(FileItemInterface fileItemInterface) {
        return GroupItemUtil.findBestItem(fileItemInterface, DbKey.FILES_SINGLETAKE, GroupType.SINGLE_TAKEN);
    }

    public void handleSefInfo(FileItemInterface fileItemInterface, String str) {
        super.handleSefInfo(fileItemInterface, str);
        removeSefInfo(str, SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
        removeSefInfo(str, SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
    }

    public boolean setBestItemC2pa(Context context, FileItemInterface fileItemInterface, double[] dArr) {
        byte[] sefInfo = getSefInfo(fileItemInterface.getPath(), SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
        if (sefInfo != null) {
            return addSefInfo(fileItemInterface.getPath(), SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO, sefInfo);
        }
        return false;
    }

    public String tag() {
        return "SingleTakenShotFormat";
    }

    public boolean unsetBestItemC2pa(FileItemInterface fileItemInterface) {
        return removeSefInfo(fileItemInterface.getPath(), SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
    }

    public boolean updateFileBestItem(FileItemInterface fileItemInterface, String str) {
        byte[] sefInfo = getSefInfo(str, SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
        if (sefInfo == null) {
            return false;
        }
        SefInfo sefInfo2 = SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO;
        if (!addSefInfo(str, sefInfo2, sefInfo)) {
            return false;
        }
        if (fileItemInterface == null || fileItemInterface.getPath() == null || removeSefInfo(fileItemInterface.getPath(), sefInfo2.keyName)) {
            return true;
        }
        return false;
    }
}
