package com.samsung.android.gallery.database.dal.cmh.helper;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.StoryNotificationApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.providers.CmhUri;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmhStoryNotificationImpl extends CmhHelperBaseImpl implements StoryNotificationApi {
    Uri CMH_AUTOITEMSTATUS_TABLE_URI = CmhUri.getAutoItemStatus();
    Uri CMH_VISUALART_TABLE_URI = CmhUri.getVisualArt();

    public CmhStoryNotificationImpl(QueryParams queryParams) {
        super(queryParams);
    }

    private String getStringFromArray(long[] jArr) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        for (long valueOf : jArr) {
            stringJoiner.add(String.valueOf(valueOf));
        }
        return stringJoiner.toString();
    }

    public Uri insertVisualArt(long[] jArr, String str, boolean z) {
        ThreadUtil.assertBgThread("insertVisualArt");
        long j2 = UnsafeCast.toLong(str.substring(str.lastIndexOf("/") + 1));
        String stringFromArray = getStringFromArray(jArr);
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("type", 1);
            contentValues.put("title", "Collage");
        } else {
            contentValues.put("type", 2);
            contentValues.put("title", "AnimatedGIF");
        }
        contentValues.put("cover", Long.valueOf(j2));
        contentValues.put("fileid", stringFromArray);
        contentValues.put(SpaceContract.Space.MEDIA_COUNT, Integer.valueOf(jArr.length));
        contentValues.put("creation_time", Long.valueOf(System.currentTimeMillis()));
        try {
            return getContentResolver().insert(this.CMH_VISUALART_TABLE_URI, contentValues);
        } catch (SQLiteException e) {
            String str2 = this.TAG;
            Log.e(str2, "insertVisualArt e=" + e.getMessage());
            return null;
        }
    }

    public String tag() {
        return "CmhStoryNotificationImpl";
    }

    public int updateAutoItem(int i2, int i7) {
        ThreadUtil.assertBgThread("updateAutoItem");
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", Integer.valueOf(i7));
        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
        try {
            return getContentResolver().update(this.CMH_AUTOITEMSTATUS_TABLE_URI, contentValues, "type=" + i2, (String[]) null);
        } catch (SQLiteException e) {
            Log.e("", e.toString());
            return 0;
        }
    }
}
