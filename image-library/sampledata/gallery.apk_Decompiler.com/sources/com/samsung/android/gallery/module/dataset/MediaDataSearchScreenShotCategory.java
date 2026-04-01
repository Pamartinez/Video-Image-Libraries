package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.ScreenShotFilterType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.ScreenShotHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaDataSearchScreenShotCategory extends MediaDataSearchCategory {
    public MediaDataSearchScreenShotCategory(Blackboard blackboard, String str, boolean z) {
        super(blackboard, str, z);
    }

    private static MediaItem loadCaptureAllItem() {
        QueryParams queryParams = new QueryParams(DbKey.ALBUM_FILES);
        ScreenShotFilterType screenShotFilterType = ScreenShotFilterType.All;
        Cursor query = DbCompat.query(queryParams.setSubCategory(screenShotFilterType.key()).setScreenShotFolderId(FileUtils.getBucketId(ScreenShotHelper.getScreenshotFolder(AppResources.getAppContext()))).setImageOnly());
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    int count = query.getCount();
                    MediaItem create = MediaItemBuilder.create(query);
                    create.setCount(count);
                    create.setCategory("ScreenShot");
                    create.setSubCategory(screenShotFilterType.key());
                    query.close();
                    return create;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
        throw th;
    }

    public MediaItem loadMediaItem(Cursor cursor, int i2) {
        MediaItem loadMediaItem = super.loadMediaItem(cursor, i2);
        if (loadMediaItem != null) {
            loadMediaItem.setCategory("ScreenShot");
            if ("cap_all".equals(loadMediaItem.getSubCategory())) {
                MediaItem loadCaptureAllItem = loadCaptureAllItem();
                if (loadCaptureAllItem != null) {
                    loadMediaItem = loadCaptureAllItem;
                }
                loadMediaItem.setTitle(AppResources.getString(R$string.all));
                return loadMediaItem;
            } else if (!PocFeatures.ASK_SCREENSHOT || !"cap_ask".equals(loadMediaItem.getSubCategory())) {
                loadMediaItem.setTitle(ScreenShotFilterTitle.getInstance().getTitle(loadMediaItem.getSubCategory()));
            } else {
                loadMediaItem.setTitle(AppResources.getString(R$string.ask_screenshot));
                return loadMediaItem;
            }
        }
        return loadMediaItem;
    }
}
