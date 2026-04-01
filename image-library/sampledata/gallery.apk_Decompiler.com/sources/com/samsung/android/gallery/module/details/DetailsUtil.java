package com.samsung.android.gallery.module.details;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsUtil {
    public static void emptyChangedFile(ArrayList<MediaItem> arrayList) {
        ThreadUtil.assertBgThread("emptyDeletedFile");
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            MediaItem mediaItem = arrayList.get(i2);
            File file = new File(mediaItem.getPath());
            if (!file.exists() || file.lastModified() / 1000 != mediaItem.getDateModified() || file.length() != mediaItem.getFileSize()) {
                arrayList.set(i2, (Object) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$removeDuplicated$0(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2 == null || mediaItem2.getFileId() != mediaItem.getFileId()) {
            return false;
        }
        return true;
    }

    public static ArrayList<MediaItem> loadCursorData(Cursor cursor) {
        ArrayList<MediaItem> arrayList = new ArrayList<>();
        if (cursor == null || !cursor.moveToFirst()) {
            return arrayList;
        }
        do {
            arrayList.add(MediaItemLoader.load(cursor));
        } while (cursor.moveToNext());
        return arrayList;
    }

    public static void removeDuplicated(ArrayList<MediaItem> arrayList, ArrayList<MediaItem> arrayList2) {
        arrayList.removeIf(new e(arrayList2, 2));
    }

    public static boolean supportDbLoad(MediaItem mediaItem) {
        if (!supportFileLoad(mediaItem) || mediaItem.getFileId() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean supportFileDataLoad(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken()) {
            return false;
        }
        return true;
    }

    public static boolean supportFileLoad(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.isMtp() || FileUtils.isEmptyDummyImage(mediaItem.getPath()) || mediaItem.isSharing()) {
            return false;
        }
        return true;
    }
}
