package com.samsung.android.gallery.module.publisher;

import L5.b;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.LocationApi;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.CursorCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchKeywordQuery {
    private static final boolean VISUAL_SEARCH_71 = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
    final Blackboard mBlackboard;

    public SearchKeywordQuery(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private String getClusterKeyNames(ArrayList<String> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return "";
        }
        return (String) arrayList.stream().map(new b(12)).collect(Collectors.joining("','", "'", "'"));
    }

    public Cursor queryAlbum(Bundle bundle, boolean z) {
        return queryAlbum((String) bundle.get(z ? "search_cluster_carousel_album" : "name"));
    }

    public Cursor queryLocation(Bundle bundle, boolean z) {
        return queryLocation((String) bundle.get(z ? "search_cluster_carousel_location" : "name"));
    }

    public Cursor queryPeople(Bundle bundle, boolean z) {
        return queryPeople((String) bundle.get(z ? "search_cluster_carousel_people" : "name"));
    }

    public Cursor queryPets(String str) {
        if (TextUtils.isEmpty(str) || !Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        return DbCompat.query(new QueryParams("mp://Pets").setNames(str));
    }

    public Cursor queryStory(Bundle bundle, boolean z) {
        String str;
        if (z) {
            str = "search_cluster_carousel_story";
        } else {
            str = "name";
        }
        String str2 = (String) bundle.get(str);
        if (TextUtils.isEmpty(str2)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        this.mBlackboard.publishIfEmpty("debug://TimeQueryStart", Long.valueOf(System.currentTimeMillis()));
        return DbCompat.query(new QueryParams(DbKey.STORIES).setNames(str2).setLimit(5));
    }

    public Cursor queryTags(String str) {
        if (TextUtils.isEmpty(str)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        return DbCompat.query(new QueryParams("mp://myTag").setNames(str));
    }

    public Cursor queryAlbum(ArrayList<String> arrayList) {
        return queryAlbum(getClusterKeyNames(arrayList));
    }

    public Cursor queryLocation(String str) {
        if (TextUtils.isEmpty(str)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        QueryParams names = new QueryParams().setReplaceVolumeName().setNames(str);
        if (!VISUAL_SEARCH_71) {
            names.setLimit(5);
        }
        return new LocationApi(names).getLocationClusterCursor();
    }

    public Cursor queryPeople(String str) {
        if (TextUtils.isEmpty(str)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        QueryParams names = new QueryParams("mp://People").setNames(str);
        if (!VISUAL_SEARCH_71) {
            names.setLimit(5);
        }
        return DbCompat.query(names);
    }

    public Cursor queryAlbum(String str) {
        if (TextUtils.isEmpty(str)) {
            return CursorCompat.EMPTY_CURSOR;
        }
        QueryParams names = new QueryParams(DbKey.ALBUMS).setNames(str);
        if (!VISUAL_SEARCH_71) {
            names.setLimit(5);
        }
        return DbCompat.query(names);
    }

    public Cursor queryPets(ArrayList<String> arrayList) {
        return queryPets(getClusterKeyNames(arrayList));
    }

    public Cursor queryTags(ArrayList<String> arrayList) {
        return queryTags(getClusterKeyNames(arrayList));
    }

    public Cursor queryLocation(ArrayList<String> arrayList) {
        return queryLocation(getClusterKeyNames(arrayList));
    }

    public Cursor queryPeople(ArrayList<String> arrayList) {
        return queryPeople(getClusterKeyNames(arrayList));
    }
}
