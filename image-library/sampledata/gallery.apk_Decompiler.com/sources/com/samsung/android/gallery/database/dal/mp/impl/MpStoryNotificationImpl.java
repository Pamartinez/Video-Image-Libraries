package com.samsung.android.gallery.database.dal.mp.impl;

import android.net.Uri;
import com.samsung.android.gallery.database.dal.abstraction.StoryNotificationApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MpStoryNotificationImpl extends StoryHelperBaseImpl implements StoryNotificationApi {
    public MpStoryNotificationImpl(QueryParams queryParams) {
        super(queryParams);
    }

    public Uri insertVisualArt(long[] jArr, String str, boolean z) {
        ThreadUtil.assertBgThread("insertVisualArt");
        Log.w(this.TAG, "insertVisualArt Unsupported");
        return null;
    }

    public String tag() {
        return "MpStoryNotificationImpl";
    }

    public int updateAutoItem(int i2, int i7) {
        ThreadUtil.assertBgThread("updateAutoItem");
        Log.w(this.TAG, "updateAutoItem Unsupported");
        return 0;
    }
}
