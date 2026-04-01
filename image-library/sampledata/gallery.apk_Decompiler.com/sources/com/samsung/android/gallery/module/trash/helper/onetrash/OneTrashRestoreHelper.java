package com.samsung.android.gallery.module.trash.helper.onetrash;

import A.a;
import N2.j;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.trash.abstraction.TrashRestoreItem;
import com.samsung.android.gallery.module.trash.helper.TrashRestoreHelper;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.FileUtils;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneTrashRestoreHelper extends TrashRestoreHelper {
    public OneTrashRestoreHelper(Context context, boolean z) {
        super(context, z);
    }

    private boolean isGoogleTrash(String str) {
        if (!TextUtils.isEmpty(str)) {
            return "N/A".equals(str);
        }
        return true;
    }

    public boolean deleteFromTrashDB(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            boolean z = true;
            if (this.mTrashProvider.deleteTrash("_data =? ", new String[]{str}, true) <= 0) {
                z = false;
            }
            return z;
        } finally {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("deleted from Trash db ["));
        }
    }

    public String getRestoreTargetPath(TrashRestoreItem trashRestoreItem) {
        String path = trashRestoreItem.getPath();
        if (FileUtils.isInRemovableStorage(path)) {
            String originalPath = TrashData.getOriginalPath(trashRestoreItem);
            String storageName = FileUtils.getStorageName(path);
            String storageName2 = FileUtils.getStorageName(originalPath);
            if (!storageName.equals(storageName2)) {
                return originalPath.replace(storageName2, storageName);
            }
            return originalPath;
        } else if (!trashRestoreItem.isLocalCloud()) {
            return super.getRestoreTargetPath(trashRestoreItem);
        } else {
            String directoryFromPath = FileUtils.getDirectoryFromPath(TrashData.getOriginalPath(trashRestoreItem));
            return C0212a.A(directoryFromPath, FileUtils.getUniqueFilename(directoryFromPath, FileUtils.getNameFromPath(TrashData.getOriginalPath(trashRestoreItem))));
        }
    }

    public boolean isNormalTrash(TrashRestoreItem trashRestoreItem) {
        return !isGoogleTrash(trashRestoreItem.getOwnerPackage());
    }

    public String tag() {
        return "OneTrashRestoreHelper";
    }

    public boolean updateMpRestoreItem(TrashRestoreItem trashRestoreItem) {
        try {
            if (isNormalTrash(trashRestoreItem)) {
                return true;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("is_trashed", 0);
            if (this.mTrashProvider.updateTrash(ContentUris.withAppendedId(MediaUri.getInstance().getFilesUri(), trashRestoreItem.getTrashMediaId()), contentValues, (String) null, (String[]) null) > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to update mp restore item "), this.TAG);
            return false;
        }
    }
}
