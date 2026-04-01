package com.samsung.android.gallery.module.groupshot;

import android.content.Context;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.module.data.GroupItemUtil;
import com.samsung.android.gallery.module.location.LocationUpdater;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.sdk.scs.ai.language.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BurstShotFormat extends GroupShotFormat {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLocation$0(boolean z) {
        notifyCompletion(true);
    }

    public FileItemInterface findBestItem(FileItemInterface fileItemInterface) {
        return GroupItemUtil.findBestItem(fileItemInterface, DbKey.FILES_BURSTSHOT, GroupType.BURST);
    }

    public void handleExifInfo(String str, double d, double d2) {
        ExifUtils.changeLocation(str, d, d2);
    }

    public void handleSefInfo(FileItemInterface fileItemInterface, String str) {
        super.handleSefInfo(fileItemInterface, str);
        removeSefInfo(str, SefInfo.BURST_SHOT_INFO.keyName);
        removeSefInfo(str, SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
    }

    public boolean setBestItemC2pa(Context context, FileItemInterface fileItemInterface, double[] dArr) {
        LocationUpdater locationUpdater = new LocationUpdater(context);
        if (addSefInfo(fileItemInterface.getPath(), SefInfo.BURST_SHOT_BEST_PHOTO_INFO, SefInfo.Data.BURST_SHOT_BEST_PHOTO_DATA)) {
            if (locationUpdater.changeLocation(fileItemInterface, dArr[0], dArr[1])) {
                return true;
            }
        }
        return false;
    }

    public String tag() {
        return "BurstShotFormat";
    }

    public boolean unsetBestItemC2pa(FileItemInterface fileItemInterface) {
        return removeSefInfo(fileItemInterface.getPath(), SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
    }

    public boolean updateFileBestItem(FileItemInterface fileItemInterface, String str) {
        SefInfo sefInfo = SefInfo.BURST_SHOT_BEST_PHOTO_INFO;
        if (!addSefInfo(str, sefInfo, SefInfo.Data.BURST_SHOT_BEST_PHOTO_DATA)) {
            return false;
        }
        if (fileItemInterface == null || fileItemInterface.getPath() == null || removeSefInfo(fileItemInterface.getPath(), sefInfo.keyName)) {
            return true;
        }
        return false;
    }

    public void updateLocation(Context context, FileItemInterface fileItemInterface, double[] dArr) {
        if (dArr != null) {
            LocationUpdater locationUpdater = new LocationUpdater(context);
            locationUpdater.setCompletionListener(new a(26, this));
            locationUpdater.updateLocation(fileItemInterface, dArr);
            return;
        }
        notifyCompletion(true);
    }

    public void notifyCompletion() {
    }
}
