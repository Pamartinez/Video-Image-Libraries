package com.samsung.android.gallery.bixby.bixbycard;

import android.database.Cursor;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.providers.MediaUri;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PhotoDataFetcher extends AbsDataFetcher {
    private static final Uri URI = MediaUri.getInstance().getFilesUri();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        /* access modifiers changed from: private */
        public static final PhotoDataFetcher INSTANCE = new PhotoDataFetcher();
    }

    private String getFileUri(Cursor cursor) {
        return Uri.withAppendedPath(URI, Integer.toString(cursor.getInt(cursor.getColumnIndex("__fileMediaId")))).toString();
    }

    public static PhotoDataFetcher getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getPhotoData$0(Collection collection, QueryParams queryParams) {
        queryParams.addAlbumIds((Collection<Integer>) collection).setCreationTimeLimit(getTodayStartTime(), getTodayEndTime()).isShowLocal();
    }

    public List<GalleryCardData> getPhotoData() {
        Log.bx("PhotoDataFetcher", "getPhotoData start");
        List<Integer> valuesCamera = BucketUtils.valuesCamera();
        ArrayList arrayList = null;
        if (valuesCamera.size() == 0) {
            Log.bxe("PhotoDataFetcher", "getPhotoData failed. camera album ids are not set");
            return null;
        }
        int i2 = -1;
        try {
            Cursor query = DbCompat.query(DbKey.ALBUM_FILES, new e(this, valuesCamera));
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        i2 = query.getCount();
                        ArrayList arrayList2 = new ArrayList();
                        do {
                            try {
                                arrayList2.add(0, new GalleryCardData(getFileUri(query), query.getString(query.getColumnIndex("__mimeType"))));
                            } catch (Throwable th) {
                                th = th;
                                arrayList = arrayList2;
                                try {
                                    query.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        } while (query.moveToNext());
                        arrayList = arrayList2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    query.close();
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            Log.bx("PhotoDataFetcher", "getPhotoData end [" + i2 + "][" + arrayList + "]");
            return arrayList;
        } catch (Error | Exception e) {
            Log.bxe("PhotoDataFetcher", e.getMessage());
            Log.bx("PhotoDataFetcher", "getPhotoData end [" + i2 + "][" + arrayList + "]");
            return arrayList;
        } catch (Throwable th4) {
            Log.bx("PhotoDataFetcher", "getPhotoData end [" + i2 + "][" + arrayList + "]");
            throw th4;
        }
    }
}
