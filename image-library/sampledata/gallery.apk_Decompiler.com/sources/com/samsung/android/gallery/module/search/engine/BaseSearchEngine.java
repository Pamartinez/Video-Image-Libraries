package com.samsung.android.gallery.module.search.engine;

import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.SearchProviderApi;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.dataset.tables.MediaDataFileIdSorter;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseSearchEngine {
    protected final String TAG = tag();
    protected Context mAppContext;

    public BaseSearchEngine(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    private String getSubCategory(String str) {
        return ArgumentsUtil.getArgValue(str, "sub", (String) null);
    }

    public Cursor complexSearch(SearchFilter searchFilter) {
        String locationKey = searchFilter.getLocationKey();
        if (locationKey == null) {
            Log.s(this.TAG, "Couldn't get locationKey");
            return null;
        } else if (isKeywordSearchedOnSupportMultipleKeyword(locationKey)) {
            return search(searchFilter);
        } else {
            String subCategory = getSubCategory(locationKey);
            String mediaType = searchFilter.getMediaType();
            if (locationKey.startsWith("location://search/fileList/Category/MyTag")) {
                return new SearchProviderApi().getMyTagFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/ShotMode")) {
                return new SearchProviderApi(new QueryParams(GroupType.BURST, GroupType.SINGLE_TAKEN)).getShotModeFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/People")) {
                return new SearchProviderApi().getPeopleFileCursor(UnsafeCast.toLong(subCategory), mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/Pet")) {
                return new SearchProviderApi().getPetsFileCursor(UnsafeCast.toLong(subCategory), mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/Expressions")) {
                return new SearchProviderApi().getExpressionFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/Location")) {
                return new SearchProviderApi().getLocationFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/Documents")) {
                return new SearchProviderApi().getDocumentFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Category/Scene")) {
                return new SearchProviderApi().getSceneFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/VIDEOS")) {
                return new SearchProviderApi().getVideoFileCursor();
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/SMILES")) {
                return new SearchProviderApi().getSmileFileCursor(mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/BLURRY")) {
                return new SearchProviderApi().getBlurredFileCursor(mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/TIME")) {
                return new SearchProviderApi().getFromTimedFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/LOCATION")) {
                return new SearchProviderApi().getLocationFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/CATEGORY")) {
                return new SearchProviderApi().getSceneFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Recommendation/SuggestionKeyword/RECENTLY_ADDED")) {
                return new SearchProviderApi().getFromTimedFileCursor(subCategory, mediaType);
            }
            if (locationKey.startsWith("location://search/fileList/Keyword")) {
                return search(searchFilter);
            }
            if (locationKey.startsWith("location://virtual/album/video/fileList")) {
                return new SearchProviderApi().getAlbumVirtualVideoCursor();
            }
            if (locationKey.startsWith("location://virtual/album/favorite/fileList")) {
                return new SearchProviderApi().getAlbumVirtualFavoriteCursor(mediaType);
            }
            Log.sw(this.TAG, "no result for search");
            return null;
        }
    }

    public Cursor convertFileIdsCollectionCursor(Cursor cursor, String str) {
        int i2;
        String[] strArr;
        TimeTickLog timeTickLog = new TimeTickLog();
        StringBuilder sb2 = new StringBuilder();
        CollectionCursor collectionCursor = new CollectionCursor("__absID");
        timeTickLog.tick();
        try {
            if (cursor.moveToFirst()) {
                if ("oldest".equals(str)) {
                    i2 = 1;
                } else {
                    i2 = 2;
                }
                MediaDataFileIdSorter mediaDataFileIdSorter = new MediaDataFileIdSorter(i2);
                do {
                    String string = cursor.getString(cursor.getColumnIndex("__clusterIdsDateTimeConcat"));
                    sb2.append(string);
                    if (string != null) {
                        strArr = string.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                    } else {
                        strArr = new String[0];
                    }
                    for (String split : strArr) {
                        String[] split2 = split.split(NumericEnum.SEP);
                        mediaDataFileIdSorter.add(Long.parseLong(split2[0]), Long.parseLong(split2[1]));
                    }
                } while (cursor.moveToNext());
                mediaDataFileIdSorter.sort();
                collectionCursor.addAll(mediaDataFileIdSorter.getSortedIds());
            }
            String str2 = sb2.toString().hashCode() + "_" + collectionCursor.getCount();
            Bundle extras = cursor.getExtras();
            if (extras == null || extras == Bundle.EMPTY) {
                extras = new Bundle();
            }
            extras.putString("dataHash", str2);
            collectionCursor.setExtras(extras);
            timeTickLog.tick();
            Log.d(this.TAG, "convertFileIdsCollectionCursorFromSecMpCursor" + Logger.vt(str2, timeTickLog));
            return collectionCursor;
        } catch (Exception e) {
            j.v("convertFileIdsCollectionCursorFromSecMpCursor failed : ", e, this.TAG);
            return collectionCursor;
        }
    }

    public Cursor createDummyCursor() {
        return new MatrixCursor(new String[]{"_id"});
    }

    public QueryParams createQueryParams(SearchFilter searchFilter) {
        QueryParams.DbStorageType dbStorageType;
        QueryParams queryParams = new QueryParams();
        if (searchFilter.isPickMode()) {
            queryParams.setMediaTypeFilter(searchFilter.getFilterMediaType());
            if (searchFilter.isLocalOnly()) {
                dbStorageType = QueryParams.DbStorageType.LocalOnly;
            } else {
                dbStorageType = QueryParams.DbStorageType.All;
            }
            queryParams.setStorageTypes(dbStorageType);
        }
        return queryParams;
    }

    public Cursor getCursorByPartition(String str, Function<String, Cursor> function) {
        if (TextUtils.isEmpty(str)) {
            return createDummyCursor();
        }
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        int i2 = 0;
        while (split.length > i2) {
            StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            int i7 = 0;
            while (i2 < split.length) {
                stringJoiner.add(split[i2]);
                int i8 = i7 + 1;
                if (i7 == 5000) {
                    break;
                }
                i2++;
                i7 = i8;
            }
            arrayList.add(function.apply(stringJoiner.toString()));
            i2++;
        }
        if (arrayList.size() == 1) {
            return (Cursor) arrayList.get(0);
        }
        if (arrayList.size() > 1) {
            return new MergeCursor((Cursor[]) arrayList.toArray(new Cursor[0]));
        }
        return createDummyCursor();
    }

    public Bundle getFilterResultsBundle(SearchFilter searchFilter, int i2) {
        return null;
    }

    public int[] getMediaTypeCount(Bundle bundle) {
        return null;
    }

    public abstract String getResultIdList(SearchFilter searchFilter);

    public boolean isKeywordSearchedOnSupportMultipleKeyword(String str) {
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !ArgumentsUtil.getArgValue(str, "need_keyword_search", false)) {
            return false;
        }
        return true;
    }

    public abstract Cursor search(SearchFilter searchFilter);

    public abstract Cursor searchAutoComplete(SearchFilter searchFilter);

    public abstract Cursor[] searchForTimeline(SearchFilter searchFilter);

    public abstract String tag();

    public void clearCache() {
    }
}
