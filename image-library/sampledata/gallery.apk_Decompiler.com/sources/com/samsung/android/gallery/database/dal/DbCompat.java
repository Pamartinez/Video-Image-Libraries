package com.samsung.android.gallery.database.dal;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.abstraction.AutoAlbumApi;
import com.samsung.android.gallery.database.dal.abstraction.StoryApi;
import com.samsung.android.gallery.database.dal.abstraction.StoryBadgeApi;
import com.samsung.android.gallery.database.dal.abstraction.StoryNotificationApi;
import com.samsung.android.gallery.database.dal.abstraction.SuggestedApi;
import com.samsung.android.gallery.database.dal.abstraction.TextExtractionApi;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhAutoAlbumImpl;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryApiImpl;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryBadgeImpl;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhStoryNotificationImpl;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhSuggestedImpl;
import com.samsung.android.gallery.database.dal.cmh.helper.CmhTextExtractionImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpAutoAlbumImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryApiImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryBadgeImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpStoryNotificationImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpSuggestedImpl;
import com.samsung.android.gallery.database.dal.mp.impl.MpTextExtractionImpl;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DbCompat {
    public static AutoAlbumApi autoAlbumApi() {
        return autoAlbumApi(new QueryParams());
    }

    public static Cursor query(QueryParams queryParams) {
        return new ProviderMap().query(queryParams);
    }

    public static StoryApi storyApi() {
        return storyApi(new QueryParams());
    }

    public static StoryBadgeApi storyBadgeApi() {
        return storyBadgeApi(new QueryParams());
    }

    public static StoryApi storyFeatureApi() {
        return new CmhStoryApiImpl(new QueryParams());
    }

    public static StoryNotificationApi storyNotificationApi() {
        return storyNotificationApi(new QueryParams());
    }

    public static SuggestedApi suggestedApi() {
        return suggestedApi(new QueryParams());
    }

    public static TextExtractionApi textExtractionApi() {
        return textExtractionApi(new QueryParams());
    }

    public static AutoAlbumApi autoAlbumApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpAutoAlbumImpl(queryParams) : new CmhAutoAlbumImpl(queryParams);
    }

    public static Cursor query(String str) {
        return query(str, (Consumer<QueryParams>) null);
    }

    public static StoryApi storyApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpStoryApiImpl(queryParams) : new CmhStoryApiImpl(queryParams);
    }

    public static StoryBadgeApi storyBadgeApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpStoryBadgeImpl(queryParams) : new CmhStoryBadgeImpl(queryParams);
    }

    public static StoryNotificationApi storyNotificationApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpStoryNotificationImpl(queryParams) : new CmhStoryNotificationImpl(queryParams);
    }

    public static SuggestedApi suggestedApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpSuggestedImpl(queryParams) : new CmhSuggestedImpl(queryParams);
    }

    public static TextExtractionApi textExtractionApi(QueryParams queryParams) {
        return queryParams.CMH_TO_MP_MIGRATION ? new MpTextExtractionImpl(queryParams) : new CmhTextExtractionImpl(queryParams);
    }

    public static Cursor query(String str, Consumer<QueryParams> consumer) {
        QueryParams queryParams = new QueryParams(str);
        if (consumer != null) {
            consumer.accept(queryParams);
        }
        return query(queryParams);
    }

    public static Cursor query(Consumer<QueryParams> consumer) {
        QueryParams queryParams = new QueryParams();
        if (consumer != null) {
            consumer.accept(queryParams);
        }
        return query(queryParams);
    }
}
