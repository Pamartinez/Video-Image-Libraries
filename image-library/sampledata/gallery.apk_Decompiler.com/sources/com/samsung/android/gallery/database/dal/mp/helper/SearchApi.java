package com.samsung.android.gallery.database.dal.mp.helper;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.Query;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.abstraction.table.SecFilesTable;
import com.samsung.android.gallery.database.dal.mp.impl.BaseImpl;
import com.samsung.android.gallery.database.dal.mp.impl.ImplHelper;
import com.samsung.android.gallery.database.dal.mp.table.MpFacesView;
import com.samsung.android.gallery.database.dal.mp.table.MpFilesTable;
import com.samsung.android.gallery.database.dal.mp.table.MpLocationView;
import com.samsung.android.gallery.database.dal.mp.table.MpTagView;
import com.samsung.android.gallery.database.dal.util.QueryUtils;
import com.samsung.android.gallery.database.dbtype.DateType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchApi extends BaseImpl {
    public SearchApi(QueryParams queryParams) {
        super(queryParams);
    }

    private void applyOrderBy(String str, SearchFilter searchFilter, MpFilesTable mpFilesTable) {
        if (searchFilter.useIdOrder()) {
            mpFilesTable.addProjectionIdOrder(str);
            mpFilesTable.orderByIds();
            return;
        }
        applyOrderBy(mpFilesTable, searchFilter);
    }

    private String applySortOrderForDateItem(SearchFilter searchFilter) {
        String str;
        if ("oldest".equals(searchFilter.getOrder())) {
            str = " ASC";
        } else {
            str = " DESC";
        }
        return "__dateTaken".concat(str);
    }

    private MpFacesView getExpressionView(SearchFilter searchFilter) {
        MpFacesView mpFacesView = new MpFacesView(this.mParams);
        mpFacesView.resetProjectionForID();
        mpFacesView.clearOrderBy();
        if (searchFilter.isHappy()) {
            mpFacesView.filterSmile(true);
            return mpFacesView;
        } else if (searchFilter.isNeutral()) {
            mpFacesView.filterNeutral(true);
            return mpFacesView;
        } else if (searchFilter.isDislike()) {
            mpFacesView.filterDisLike(true);
            return mpFacesView;
        } else {
            if (searchFilter.isSurprise()) {
                mpFacesView.filterSurprise(true);
            }
            return mpFacesView;
        }
    }

    private ArrayList<String> getExpressionsCondition(SearchFilter searchFilter) {
        if (Features.isEnabled(Features.SUPPORT_INTELLIGENT_SEARCH)) {
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<SearchFilter> it = searchFilter.getSubFilterList().iterator();
        while (it.hasNext()) {
            SearchFilter next = it.next();
            if (next.isExpressions()) {
                String buildSql = getExpressionView(next).buildSelectQuery().buildSql();
                arrayList.add("A._id in (" + buildSql + ")");
            } else {
                arrayList.add("");
            }
        }
        return arrayList;
    }

    private MpFilesTable getSearchFilesTable(String str, SearchFilter searchFilter) {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.filterIds(str);
        mpFilesTable.filterMediaType(searchFilter.getMediaType());
        if (searchFilter.filterOutCloudVideo()) {
            mpFilesTable.filterExceptCloudVideo(searchFilter.getMediaType());
        }
        mpFilesTable.filterGroupMediaBest(true);
        return mpFilesTable;
    }

    private Query getSearchTagQuery(SearchFilter searchFilter) {
        MpTagView mpTagView = new MpTagView(this.mParams);
        mpTagView.applySearchFilter(searchFilter, getExpressionsCondition(searchFilter));
        mpTagView.projectionForSearchResult();
        mpTagView.filterBurstShotBestImage(true);
        mpTagView.filterMediaType(searchFilter.getMediaType());
        if (searchFilter.filterOutCloudVideo()) {
            mpTagView.filterExceptCloudVideo(searchFilter.getMediaType());
        }
        if (!TextUtils.isEmpty(searchFilter.getExceptedIds())) {
            QueryBuilder queryBuilder = mpTagView.getQueryBuilder();
            queryBuilder.andCondition("__absID not in " + searchFilter.getExceptedIds());
        }
        return mpTagView.buildSelectQuery();
    }

    private Query getSuggestionQuery(SearchFilter searchFilter) {
        MpTagView mpTagView = new MpTagView(this.mParams);
        mpTagView.applySearchSuggestionFilter(searchFilter);
        mpTagView.projectionForSearchResult();
        mpTagView.filterBurstShotBestImage(true);
        mpTagView.filterMediaType(searchFilter.getMediaType());
        if (!TextUtils.isEmpty(searchFilter.getExceptedIds())) {
            QueryBuilder queryBuilder = mpTagView.getQueryBuilder();
            queryBuilder.andCondition("__absID not in " + searchFilter.getExceptedIds());
        }
        return mpTagView.buildSelectQuery();
    }

    public Cursor getAllDatesCursor() {
        MpFilesTable mpFilesTable = new MpFilesTable(this.mParams);
        mpFilesTable.modifyForAllFilesData(DateType.DAY, getDateTakenColumnName());
        mpFilesTable.filterGroupMediaBest(true);
        return getCursor(QueryUtils.updateQueryForAllDates(mpFilesTable.buildSelectQuery()), "getAllDatesCursor");
    }

    public Cursor searchDateItems(String str, SearchFilter searchFilter) {
        MpFilesTable searchFilesTable = getSearchFilesTable(str, searchFilter);
        searchFilesTable.modifyForTimelineDateData(DateType.DAY, getDateTakenColumnName());
        searchFilesTable.distinctIdCountForTimelineDateData();
        return getCursor(QueryUtils.updateQueryForMultipleLocations(searchFilesTable.buildSelectQuery(), applySortOrderForDateItem(searchFilter), new MpLocationView(this.mParams), PreferenceFeatures.OneUi6x.SUPPORT_QOD_SEARCH), "Search Items(date)");
    }

    public Cursor searchItemsCommon(String str, SearchFilter searchFilter) {
        MpFilesTable searchFilesTable = getSearchFilesTable(str, searchFilter);
        applyOrderBy(str, searchFilter, searchFilesTable);
        if (searchFilter.isForQueryOnDemand()) {
            searchFilesTable.resetProjectionForID();
        }
        return getCursor(searchFilesTable.buildSelectQuery(), "Search Items");
    }

    public Cursor searchRealRatioItems(String str, SearchFilter searchFilter) {
        MpFilesTable searchFilesTable = getSearchFilesTable(str, searchFilter);
        applyOrderBy(str, searchFilter, searchFilesTable);
        return ImplHelper.getRealRatioMergedCursor((SecFilesTable) searchFilesTable, this.mQueryExecutor);
    }

    public Cursor searchSuggestionKeyword(SearchFilter searchFilter) {
        SearchFilter searchFilter2 = searchFilter.getMajorFilterList().get(0);
        searchFilter2.setExceptedIds(searchFilter.getExceptedIds());
        return getCursor(getSuggestionQuery(searchFilter2), "SearchSuggestionKeyword");
    }

    public Cursor searchTag(SearchFilter searchFilter) {
        return getCursor(getSearchTagQuery(searchFilter), "SearchTag");
    }

    public String tag() {
        return "SearchApi";
    }

    private void applyOrderBy(SecFilesTable secFilesTable, SearchFilter searchFilter) {
        secFilesTable.orderBy("oldest".equals(searchFilter.getOrder()) ? "ASC" : "DESC");
    }
}
