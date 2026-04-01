package com.samsung.android.gallery.module.data;

import A.a;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GroupItemUtil {
    public static MediaItem findBestItem(FileItemInterface fileItemInterface, String str, GroupType groupType) {
        Cursor query;
        if (fileItemInterface == null) {
            Log.e("GroupItemUtil", "findBestItem failed. base item is null");
            return null;
        }
        try {
            query = DbCompat.query(str, new o(1, groupType, fileItemInterface));
            if (query != null) {
                if (query.moveToFirst()) {
                    do {
                        MediaItem load = MediaItemLoader.load(query);
                        if (load.getBestImage() == 1) {
                            query.close();
                            return load;
                        }
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Exception e) {
            a.s(e, new StringBuilder("findBestItem failed, e="), "GroupItemUtil");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static boolean isSameGroupContent(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || mediaItem.getGroupMediaId() != mediaItem2.getGroupMediaId() || mediaItem.getBucketID() != mediaItem2.getBucketID() || mediaItem.getGroupMode() == null || !mediaItem.getGroupMode().equals(mediaItem2.getGroupMode())) {
            return false;
        }
        if (mediaItem.isSingleTakenPostProcessing() || mediaItem2.getCount() > 1) {
            return true;
        }
        return false;
    }
}
