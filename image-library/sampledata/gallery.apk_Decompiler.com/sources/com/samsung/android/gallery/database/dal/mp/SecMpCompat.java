package com.samsung.android.gallery.database.dal.mp;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.CursorProvider;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.executor.SecMpQueryExecutor;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SecMpCompat {
    private final SecMpMethodMap mMethodMap;
    final QueryParams mQueryParams;

    public SecMpCompat(QueryParams queryParams) {
        this.mQueryParams = queryParams;
        if (support(queryParams)) {
            if (queryParams.inQueryExecutor == null) {
                queryParams.inQueryExecutor = new SecMpQueryExecutor();
            }
            this.mMethodMap = new SecMpMethodMap(queryParams);
            return;
        }
        throw new UnsupportedOperationException("queryParams : " + queryParams.getOsVersion());
    }

    public void dump(String str) {
        SecMpQueryExecutor secMpQueryExecutor = new SecMpQueryExecutor();
        secMpQueryExecutor.dumpTableToFile("files", str);
        secMpQueryExecutor.dumpTableToFile("faces", str);
        secMpQueryExecutor.dumpTableToFile("persons", str);
        secMpQueryExecutor.dumpTableToFile("tag_view", str);
        secMpQueryExecutor.dumpTableToFile("scene", str);
        secMpQueryExecutor.dumpTableToFile("usertag", str);
        secMpQueryExecutor.dumpTableToFile("location", str);
        secMpQueryExecutor.dumpTableToFile("poi", str);
        secMpQueryExecutor.dumpTableToFile("landmark", str);
        if (PreferenceFeatures.OneUi41.HIGHLIGHT_SUGGESTIONS) {
            secMpQueryExecutor.dumpTableToFile("highlight", str);
        }
        if (Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE)) {
            secMpQueryExecutor.dumpTableToFile("group_contents", str);
        }
        if (Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER)) {
            secMpQueryExecutor.dumpTableToFile("nondestruction", str);
        }
        if (Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT)) {
            secMpQueryExecutor.dumpTableToFile("contacts_recommendation", str);
        }
        if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            secMpQueryExecutor.dumpTableToFile("cluster_rect", str);
            secMpQueryExecutor.dumpTableToFile("cluster_info", str);
        }
        if (Features.isEnabled(Features.SUPPORT_VIDEO_SEARCH)) {
            secMpQueryExecutor.dumpTableToFile("video_segments", str);
            secMpQueryExecutor.dumpTableToFile("video_frames", str);
        }
        if (Features.isEnabled(Features.SUPPORT_FACES_GROUP)) {
            secMpQueryExecutor.dumpTableToFile("faces_group", str);
        }
        if (Features.isEnabled(Features.CMH_TO_MP_MIGRATION)) {
            secMpQueryExecutor.dumpTableToFile("story", str);
            secMpQueryExecutor.dumpTableToFile("story_map", str);
            secMpQueryExecutor.dumpTableToFile("auto_album", str);
            secMpQueryExecutor.dumpTableToFile("auto_album_map", str);
            secMpQueryExecutor.dumpTableToFile("auto_album_rule", str);
            secMpQueryExecutor.dumpTableToFile("hide_rule", str);
        }
    }

    public Cursor query() {
        CursorProvider cursorProvider = (CursorProvider) this.mMethodMap.get(this.mQueryParams.getDbKey());
        if (cursorProvider != null) {
            return cursorProvider.query(this.mQueryParams);
        }
        return null;
    }

    public boolean support(QueryParams queryParams) {
        if (queryParams.getOsVersion() >= 28) {
            return true;
        }
        return false;
    }
}
