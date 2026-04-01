package com.samsung.android.gallery.module.trash.factory;

import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dal.local.ReferenceDatabaseManager;
import com.samsung.android.gallery.module.trash.abstraction.PostProcessingDeleteException;
import com.samsung.android.gallery.module.trash.abstraction.TrashBaseItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashDeleteItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.Features;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpTTrashFactory extends MpSTrashFactory {
    public MpTTrashFactory(Context context) {
        super(context);
    }

    private int updatePppTrashed(TrashBaseItem trashBaseItem, int i2, long j2) {
        String str;
        ContentValues contentValues = new ContentValues();
        contentValues.put("is_trashed", Integer.valueOf(i2));
        long fileId = trashBaseItem.getFileId();
        if (Features.isEnabled(Features.IS_REPAIR_MODE)) {
            str = "/data/sec_maintenance/";
        } else {
            str = "/data/sec/";
        }
        String str2 = "";
        if (Features.isEnabled(Features.SUPPORT_PPP_DRAFT)) {
            if (i2 == 1) {
                str2 = C0212a.m("and _data_draft like '", str, "%'");
            }
        } else if (i2 == 1) {
            str2 = C0212a.m("and _data like '", str, "%'");
        }
        ReferenceDatabaseManager referenceDatabaseManager = this.mReferenceManager;
        Uri secMediaUri = MediaUri.getInstance().getSecMediaUri();
        int update = referenceDatabaseManager.update(secMediaUri, contentValues, "_id IN (" + fileId + ") " + str2, (String[]) null);
        if (i2 == 1 && update == 0) {
            DebugLogger pppInstance = DebugLogger.getPppInstance();
            pppInstance.insertLog(fileId + ":trashed fail");
            throw new PostProcessingDeleteException("fail update ppp is_trashed");
        }
        DebugLogger pppInstance2 = DebugLogger.getPppInstance();
        pppInstance2.insertLog(fileId + ":trashed(" + i2 + ")");
        String str3 = this.TAG;
        StringBuilder o2 = C0086a.o(i2, "ppp update trashed to ", ",  time [");
        o2.append(System.currentTimeMillis() - j2);
        o2.append("][");
        o2.append(fileId);
        o2.append("][");
        o2.append(update);
        j.y(o2, "]", str3);
        return update;
    }

    public int[] bulkDelete(TrashDeleteItem trashDeleteItem, boolean z) {
        int i2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!Features.isEnabled(Features.SUPPORT_PPP_MENU) || trashDeleteItem == null || trashDeleteItem.getSefFileType() != 2928) {
            i2 = 0;
        } else {
            i2 = updatePppTrashed(trashDeleteItem, 1, currentTimeMillis);
            trashDeleteItem = null;
        }
        int[] bulkDelete = super.bulkDelete(trashDeleteItem, z);
        bulkDelete[0] = bulkDelete[0] + i2;
        return bulkDelete;
    }

    public int bulkInsert(TrashRestoreItem trashRestoreItem, boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 0;
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU) && trashRestoreItem != null && trashRestoreItem.getSefFileType() == 2928) {
            i2 = updatePppTrashed(trashRestoreItem, 0, currentTimeMillis);
            trashRestoreItem = null;
        }
        return super.bulkInsert(trashRestoreItem, z) + i2;
    }

    public String tag() {
        return "MpTTrashFactory";
    }
}
