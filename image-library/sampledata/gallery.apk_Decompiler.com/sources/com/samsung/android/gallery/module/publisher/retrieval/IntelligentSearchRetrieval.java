package com.samsung.android.gallery.module.publisher.retrieval;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.FilterResultsKeySet;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.engine.BaseSearchEngine;
import com.samsung.android.gallery.module.search.engine.SearchEngineFactory;
import com.samsung.android.gallery.support.type.MediaFilterType;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSearchRetrieval implements StorageRetrieval {
    private final Context mAppContext;
    private final QueryParams mCommonParams;
    private final boolean mIsPickMode;
    private final BaseSearchEngine mSearchEngine;

    public IntelligentSearchRetrieval(Context context, QueryParams queryParams, boolean z) {
        this.mAppContext = context.getApplicationContext();
        this.mCommonParams = queryParams;
        this.mIsPickMode = z;
        this.mSearchEngine = SearchEngineFactory.create(context);
    }

    private SearchFilter createFilter(String str, SearchFilter.Builder builder, int i2) {
        builder.term(str);
        if (PreferenceFeatures.OneUi7x.SEARCH_RESULT_EXPAND) {
            builder.setExpandedDates(this.mCommonParams.getExpandedDates()).setAddedCount(i2);
        }
        if (this.mIsPickMode) {
            builder.setPickMode(true);
            String mediaTypeFilter = this.mCommonParams.getMediaTypeFilter();
            if (mediaTypeFilter.equals(MediaFilterType.IMAGE_ONLY.toString())) {
                builder.mediaType(String.valueOf(MediaType.Image.toInt()));
            } else if (mediaTypeFilter.equals(MediaFilterType.VIDEO_ONLY.toString())) {
                builder.mediaType(String.valueOf(MediaType.Video.toInt()));
            }
            builder.setLocalOnly(this.mCommonParams.isShowLocalOnly());
        }
        return builder.build(this.mAppContext);
    }

    private Cursor[] getCursors(String str, SearchFilter.Builder builder) {
        return getCursors(str, builder, false, 0);
    }

    private Cursor[] getCursorsOnDemand(String str, SearchFilter.Builder builder, int i2) {
        return getCursors(str, builder, true, i2);
    }

    public Cursor[] getDocumentFileIds(String str, SearchFilter.Builder builder, int i2) {
        if ("Other Documents".equals(str)) {
            "Documents".toLowerCase();
        }
        return getCursorsOnDemand("scenetag", builder, i2);
    }

    public Cursor[] getDocumentFiles(String str, SearchFilter.Builder builder) {
        if ("Other Documents".equals(str)) {
            "Documents".toLowerCase();
        }
        return getCursors("scenetag", builder);
    }

    public Cursor[] getExpressionFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand("expressionstag", builder, i2);
    }

    public Cursor[] getExpressionFiles(String str, SearchFilter.Builder builder) {
        return getCursors("expressionstag", builder);
    }

    public Cursor[] getGeneratedFileIds(String str, int i2) {
        String str2;
        if ("GIF".equals(str)) {
            str2 = "image/gif";
        } else {
            str2 = str;
        }
        return getCursorsOnDemand(FilterResultsKeySet.getShotModeField(str), new SearchFilter.Builder(str2), i2);
    }

    public Cursor[] getGeneratedFiles(String str) {
        return getCursors(FilterResultsKeySet.getShotModeField(str), new SearchFilter.Builder(str));
    }

    public Cursor[] getLocationFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand(str2, builder, i2);
    }

    public Cursor[] getLocationFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors(str2, builder);
    }

    public Cursor[] getMyTagsFileIds(String str, SearchFilter.Builder builder, int i2) {
        return getCursorsOnDemand("usertag", builder, i2);
    }

    public Cursor[] getMyTagsFiles(String str, SearchFilter.Builder builder) {
        return getCursors("usertag", builder);
    }

    public Cursor[] getPeopleFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursorsOnDemand("recommended_id", builder, i2);
        }
        if (!TextUtils.isEmpty(str2)) {
            return getCursorsOnDemand("persontag", builder.keyword(str2), i2);
        }
        return new Cursor[0];
    }

    public Cursor[] getPeopleFileIdsMultiple(String str, SearchFilter.Builder builder, int i2) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursorsOnDemand("recommended_id", builder, i2);
        }
        return new Cursor[0];
    }

    public Cursor[] getPeopleFiles(String str, SearchFilter.Builder builder, String str2) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursors("recommended_id", builder);
        }
        if (!TextUtils.isEmpty(str2)) {
            return getCursors("persontag", builder.keyword(str2));
        }
        return new Cursor[0];
    }

    public Cursor[] getPeopleFilesMultiple(String str, SearchFilter.Builder builder) {
        if (Features.isEnabled(Features.SUPPORT_UNIFIED_PEOPLE_KEY)) {
            return getCursors("recommended_id", builder);
        }
        return new Cursor[0];
    }

    public Cursor[] getPetFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand("pet_recommended_id", builder, i2);
    }

    public Cursor[] getPetFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors("pet_recommended_id", builder);
    }

    public Cursor[] getRecentlyEdited(String str) {
        return new Cursor[0];
    }

    public Cursor[] getRecentlyEditedFileIds(String str, int i2) {
        return new Cursor[0];
    }

    public Cursor[] getSceneFileIds(String str, SearchFilter.Builder builder, String str2, int i2) {
        return getCursorsOnDemand("scenetag", builder, i2);
    }

    public Cursor[] getSceneFiles(String str, SearchFilter.Builder builder, String str2) {
        return getCursors("scenetag", builder);
    }

    public Cursor[] getShotModeFileIds(String str, SearchFilter.Builder builder, int i2) {
        String shotModeValue = FilterResultsKeySet.getShotModeValue(str);
        if (!TextUtils.equals(str, shotModeValue)) {
            builder.keyword(shotModeValue);
        }
        return getCursorsOnDemand(FilterResultsKeySet.getShotModeField(str), builder, i2);
    }

    public Cursor[] getShotModeFiles(String str, SearchFilter.Builder builder) {
        return getCursors(FilterResultsKeySet.getShotModeField(str), builder.keyword(FilterResultsKeySet.getShotModeValue(str)));
    }

    private Cursor[] getCursors(String str, SearchFilter.Builder builder, boolean z, int i2) {
        SearchFilter createFilter = createFilter(str, builder, i2);
        createFilter.setQueryOnDemand(z);
        return this.mSearchEngine.searchForTimeline(createFilter);
    }
}
