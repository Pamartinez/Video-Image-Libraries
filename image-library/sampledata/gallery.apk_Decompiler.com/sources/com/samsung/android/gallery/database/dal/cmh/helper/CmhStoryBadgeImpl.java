package com.samsung.android.gallery.database.dal.cmh.helper;

import android.content.ContentValues;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.StoryBadgeApi;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryBadgeImpl extends CmhHelperBaseImpl implements StoryBadgeApi {
    public CmhStoryBadgeImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public int getNewStoryCount() {
        Cursor cursor;
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.addTable("story");
        queryBuilder.andCondition("is_visible=1");
        queryBuilder.andCondition("notify_status=0");
        queryBuilder.andCondition("type<=100");
        int i2 = 0;
        try {
            cursor = this.mQueryExecutor.getCursor(new Query(queryBuilder), "getNewStoryCount");
            if (cursor != null) {
                i2 = cursor.getCount();
            } else {
                Log.w(this.TAG, "invalid cursor");
            }
            if (cursor == null) {
                return i2;
            }
            cursor.close();
            return i2;
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
            return i2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public String tag() {
        return "CmhStoryBadgeImpl";
    }

    public void updateNotifyStatusChecked() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("notify_status", 2);
        update(contentValues, "notify_status = 0 AND is_visible = 1", (String[]) null);
    }

    public void updateNotifyStatusViewed(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("notify_status", 1);
        update(contentValues, "story_id = " + i2, (String[]) null);
    }
}
