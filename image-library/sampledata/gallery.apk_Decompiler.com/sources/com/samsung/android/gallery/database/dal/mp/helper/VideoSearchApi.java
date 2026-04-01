package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.AppResources;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VideoSearchApi {
    private static final Uri VIDEO_FRAMES = Uri.parse("content://secmedia/video/frames");
    private static final Uri VIDEO_SEGMENT = Uri.parse("content://secmedia/video/segments");

    public static ArrayList<Long[]> getSegmentInfoList(String str) {
        Cursor query;
        ArrayList<Long[]> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            Bundle bundle = new Bundle();
            bundle.putBoolean("android:query-arg-join-table", true);
            bundle.putString("android:query-arg-sql-selection", "video_frames._id IN (" + str + ")");
            bundle.putString("android:query-arg-sql-sort-order", "pos ASC");
            try {
                query = AppResources.getAppContext().getContentResolver().query(VIDEO_FRAMES, new String[]{"video_frames._id AS __frameId", "segment_id", "pos", "pos_start", "pos_end"}, bundle, (CancellationSignal) null);
                if (query != null) {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(new Long[]{Long.valueOf(query.getLong(2)), Long.valueOf(query.getLong(3)), Long.valueOf(query.getLong(4))});
                        } while (query.moveToNext());
                        query.close();
                        return arrayList;
                    }
                }
                if (query != null) {
                    query.close();
                    return arrayList;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return arrayList;
        throw th;
    }
}
