package com.samsung.android.gallery.module.search.engine;

import M9.n;
import W9.a;
import W9.f;
import W9.g;
import android.content.Context;
import android.database.Cursor;
import android.database.MergeCursor;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.cmh.impl.StoriesImpl;
import com.samsung.android.gallery.database.dal.mp.helper.SearchApi;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchEngine extends BaseSearchEngine {
    public SearchEngine(Context context) {
        super(context);
    }

    public static String escapeString(String str) {
        if (str != null) {
            return str.trim().replaceAll("'", "''");
        }
        return str;
    }

    private String getCursorJoinedIds(Cursor cursor, boolean z) {
        String str;
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (cursor != null && cursor.moveToFirst()) {
            String str2 = this.TAG;
            StringBuilder sb2 = new StringBuilder("getCursorJoinedIds ");
            if (z) {
                str = "except id count : ";
            } else {
                str = "include id count : ";
            }
            sb2.append(str);
            sb2.append(cursor.getCount());
            Log.s(str2, sb2.toString());
            int i2 = 0;
            do {
                stringJoiner.add(cursor.getString(cursor.getColumnIndex("__absID")));
                i2++;
                if (!cursor.moveToNext()) {
                    break;
                }
            } while (i2 >= 40000);
        }
        return stringJoiner.toString();
    }

    private Cursor getFileIdsCursor(Cursor cursor, SearchFilter searchFilter) {
        return convertFileIdsCollectionCursor(cursor, searchFilter.getOrder());
    }

    private Cursor[] getSearchMergedCursor(SearchFilter searchFilter) {
        ArrayList arrayList = new ArrayList();
        String rawKeyword = searchFilter.getRawKeyword();
        if (rawKeyword == null) {
            Log.s(this.TAG, "search : keyword is null");
        } else {
            setKeywordToFilter(rawKeyword, searchFilter);
            Iterator it = new ArrayList(searchFilter.getSearchKeyword()).iterator();
            while (it.hasNext()) {
                searchFilter.addMajorFilter(searchFilter.createFilter(this.mAppContext, (String) it.next()));
            }
            Iterator it2 = new ArrayList(searchFilter.getDividedSearchKeyword()).iterator();
            while (it2.hasNext()) {
                searchFilter.addSubFilter(searchFilter.createFilter(this.mAppContext, (String) it2.next()));
            }
        }
        Cursor searchStoryAlbum = new StoriesImpl(new QueryParams().setReplaceVolumeName()).searchStoryAlbum(searchFilter);
        if (searchStoryAlbum != null) {
            arrayList.add(searchStoryAlbum);
            setExceptedIdsFromStory(searchStoryAlbum, searchFilter);
        }
        Cursor searchTag = new SearchApi(new QueryParams().setReplaceVolumeName()).searchTag(searchFilter);
        if (searchTag != null) {
            arrayList.add(searchTag);
        }
        if (arrayList.size() != 0) {
            return (Cursor[]) arrayList.toArray(new Cursor[0]);
        }
        Log.sw(this.TAG, "no result for search");
        return null;
    }

    private String getSearchResult(SearchFilter searchFilter) {
        Cursor[] searchMergedCursor = getSearchMergedCursor(searchFilter);
        if (searchMergedCursor == null) {
            return "";
        }
        MergeCursor mergeCursor = new MergeCursor(searchMergedCursor);
        String cursorJoinedIds = getCursorJoinedIds(mergeCursor, false);
        for (Cursor closeSilently : searchMergedCursor) {
            Utils.closeSilently(closeSilently);
        }
        Utils.closeSilently(mergeCursor);
        return cursorJoinedIds;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchForTimeline$0(Cursor[] cursorArr, String str, SearchFilter searchFilter) {
        cursorArr[0] = new SearchHelper(new QueryParams()).searchItems(str, searchFilter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchForTimeline$1(Cursor[] cursorArr, String str, SearchFilter searchFilter) {
        cursorArr[1] = new SearchApi(new QueryParams()).searchDateItems(str, searchFilter);
        if (searchFilter.isForQueryOnDemand()) {
            cursorArr[5] = getFileIdsCursor(cursorArr[1], searchFilter);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$searchForTimeline$2(Cursor[] cursorArr, SearchFilter searchFilter, String str) {
        cursorArr[3] = getRealRatioCursor(searchFilter, str, createQueryParams(searchFilter));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchForTimeline$4(Cursor[] cursorArr, String str, SearchFilter searchFilter) {
        cursorArr[0] = new SearchHelper(new QueryParams()).searchItems(str, searchFilter);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchForTimeline$5(Cursor[] cursorArr, String str, SearchFilter searchFilter) {
        cursorArr[1] = new SearchApi(new QueryParams()).searchDateItems(str, searchFilter);
    }

    private void setExceptedIdsFromStory(Cursor cursor, SearchFilter searchFilter) {
        String cursorJoinedIds = getCursorJoinedIds(cursor, true);
        if (!cursorJoinedIds.isEmpty()) {
            searchFilter.setExceptedIds("(" + cursorJoinedIds + ")");
        }
    }

    private void setKeywordToFilter(String str, SearchFilter searchFilter) {
        if (str != null && !str.isEmpty()) {
            String escapeString = escapeString(str);
            searchFilter.setRawKeyword(escapeString);
            searchFilter.addSearchKeyword(escapeString);
            for (String addDividedSearchKeyword : escapeString.split("\\s+")) {
                searchFilter.addDividedSearchKeyword(addDividedSearchKeyword);
            }
        }
    }

    public Cursor getRealRatioCursor(SearchFilter searchFilter, String str, QueryParams queryParams) {
        return getCursorByPartition(str, new a(queryParams, searchFilter, 2));
    }

    public String getResultIdList(SearchFilter searchFilter) {
        return null;
    }

    public Cursor search(SearchFilter searchFilter) {
        return new SearchHelper(new QueryParams()).searchItems(getSearchResult(searchFilter), searchFilter);
    }

    public Cursor searchAutoComplete(SearchFilter searchFilter) {
        return null;
    }

    public Cursor[] searchForTimeline(SearchFilter searchFilter) {
        String searchResult = getSearchResult(searchFilter);
        if (searchResult.isEmpty()) {
            return new Cursor[]{createDummyCursor()};
        } else if (PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH) {
            Cursor[] cursorArr = new Cursor[9];
            LatchBuilder latchBuilder = new LatchBuilder("searchForTimeline_QOD");
            if (!searchFilter.isForQueryOnDemand()) {
                latchBuilder.setCurrent(new f(cursorArr, searchResult, searchFilter, 0));
            }
            if (searchFilter.isForQueryOnDemand()) {
                latchBuilder.addWorker(new g(this, cursorArr, searchResult, searchFilter));
            }
            latchBuilder.addWorker(new g(this, cursorArr, searchFilter, searchResult)).setPostExecutor((Runnable) new n(cursorArr, 10)).setTimeout(10000).start();
            return cursorArr;
        } else {
            Cursor[] cursorArr2 = new Cursor[2];
            new LatchBuilder("searchForTimeline").setCurrent(new f(cursorArr2, searchResult, searchFilter, 1)).addWorker(new f(cursorArr2, searchResult, searchFilter, 2)).setPostExecutor((Runnable) new n(cursorArr2, 11)).setTimeout(10000).start();
            return cursorArr2;
        }
    }

    public String tag() {
        return "SearchEngine";
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchForTimeline$3(Cursor[] cursorArr) {
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$searchForTimeline$6(Cursor[] cursorArr) {
    }
}
