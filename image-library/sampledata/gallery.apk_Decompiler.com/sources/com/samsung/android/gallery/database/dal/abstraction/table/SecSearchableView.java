package com.samsung.android.gallery.database.dal.abstraction.table;

import N2.j;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryBuilder;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SecSearchableView extends BaseView {
    public SecSearchableView(QueryParams queryParams) {
        super(queryParams);
    }

    private void addOrder(SearchFilter searchFilter) {
        if ("latest".equals(searchFilter.getOrder())) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " DESC, A." + getFileIdColumnName() + " DESC");
        } else if ("oldest".equals(searchFilter.getOrder())) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.addOrderBy("A." + this.mFilesTable.getColumnDateTaken() + " ASC, A." + getFileIdColumnName() + " ASC");
        } else {
            QueryBuilder queryBuilder3 = this.mQueryBuilder;
            queryBuilder3.addOrderBy("A." + getFileIdColumnName());
        }
    }

    private String createFilterQuery(SearchFilter searchFilter, String str) {
        StringBuilder sb2 = new StringBuilder(" ( 0 ");
        if (searchFilter.hasHashTag()) {
            sb2.append(" OR ");
            sb2.append(hashTagSearch(searchFilter.getRawKeyword().substring(1)));
        } else {
            String shotModeQuery = getShotModeQuery(searchFilter);
            if (!TextUtils.isEmpty(shotModeQuery)) {
                j.z(sb2, " OR  (", shotModeQuery, ") ");
            }
            if (searchFilter.isBlurry()) {
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterBlurryImages());
            }
            if (searchFilter.isFavorite()) {
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterIsFavorite());
            }
            if (searchFilter.isImageOnly()) {
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterImage());
            } else if (searchFilter.isVideoOnly()) {
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterVideo());
            } else if (searchFilter.isAll()) {
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterImage());
                sb2.append(" OR ");
                sb2.append(this.mFilesTable.getFilterVideo());
            }
            if (searchFilter.getFromKeywordTime() > 0) {
                sb2.append(" OR ");
                sb2.append("(A." + this.mFilesTable.getColumnDateTaken() + ">=");
                sb2.append(searchFilter.getFromKeywordTime());
                sb2.append(")");
            }
            if (!TextUtils.isEmpty(searchFilter.getRawKeyword())) {
                sb2.append(" OR ");
                sb2.append(stringSearch(searchFilter));
            }
            if (!TextUtils.isEmpty(str)) {
                j.z(sb2, " OR  (", str, ") ");
            }
        }
        sb2.append(" ) ");
        return sb2.toString();
    }

    private String createSubStoryFilterQuery(SearchFilter searchFilter) {
        StringBuilder sb2 = new StringBuilder(" ( 0 ");
        if (!TextUtils.isEmpty(searchFilter.getRawKeyword())) {
            sb2.append(" OR ");
            sb2.append(stringSearch(searchFilter));
        }
        sb2.append(" ) ");
        return sb2.toString();
    }

    private String createSuggestionFilterQuery(SearchFilter searchFilter) {
        StringBuilder sb2 = new StringBuilder(" ( 0 ");
        if (searchFilter.isBlurry()) {
            sb2.append(" OR ");
            sb2.append(this.mFilesTable.getFilterBlurryImages());
        }
        if (searchFilter.isFavorite()) {
            sb2.append(" OR ");
            sb2.append(this.mFilesTable.getFilterIsFavorite());
        }
        if (searchFilter.getFromKeywordTime() > 0) {
            sb2.append(" OR ");
            sb2.append("(A." + this.mFilesTable.getColumnDateTaken() + ">=");
            sb2.append(searchFilter.getFromKeywordTime());
            sb2.append(")");
        }
        sb2.append(" ) ");
        return sb2.toString();
    }

    private void filterTime(SearchFilter searchFilter) {
        if (searchFilter.getFromTime() > 0) {
            QueryBuilder queryBuilder = this.mQueryBuilder;
            queryBuilder.andCondition("A." + this.mFilesTable.getColumnDateTaken() + ">=" + searchFilter.getFromTime());
        }
        if (searchFilter.getToTime() > 0) {
            QueryBuilder queryBuilder2 = this.mQueryBuilder;
            queryBuilder2.andCondition("A." + this.mFilesTable.getColumnDateTaken() + "<=" + searchFilter.getToTime());
        }
    }

    private String getSearchCondition(SearchFilter searchFilter, ArrayList<String> arrayList) {
        StringJoiner stringJoiner = new StringJoiner(" OR ", "(", ")");
        ArrayList<SearchFilter> subFilterList = searchFilter.getSubFilterList();
        if (subFilterList.size() > 1) {
            stringJoiner.add(mergeCondition(searchFilter.getMajorFilterList(), (ArrayList<String>) null));
        }
        stringJoiner.add(mergeCondition(subFilterList, arrayList));
        return stringJoiner.toString();
    }

    private String getShotModeQuery(SearchFilter searchFilter) {
        if (searchFilter.isBurstShot()) {
            return this.mFilesTable.getBurstShotOnlyQuery();
        }
        if (searchFilter.isSingleTaken()) {
            return this.mFilesTable.getSingleTakenQuery();
        }
        ArrayList<Integer> sefFileType = searchFilter.getSefFileType();
        if (searchFilter.is360Video()) {
            String str = this.mFilesTable.get360VideoQuery();
            if (sefFileType.size() <= 0) {
                return str;
            }
            StringBuilder t = C0212a.t(str, " OR A.sef_file_type in ");
            t.append(convertToArray(sefFileType));
            return t.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        if (sefFileType.size() > 0) {
            sb2.append("A.sef_file_type in ");
            sb2.append(convertToArray(sefFileType));
        }
        ArrayList<Integer> recordingModes = searchFilter.getRecordingModes();
        if (recordingModes.size() > 0) {
            if (sb2.length() > 0) {
                sb2.append(" OR ");
            }
            sb2.append("A.recording_mode in ");
            sb2.append(convertToArray(recordingModes));
        }
        return sb2.toString();
    }

    private String mergeCondition(ArrayList<SearchFilter> arrayList, ArrayList<String> arrayList2) {
        StringJoiner stringJoiner = new StringJoiner(" AND ", "(", ")");
        Iterator<SearchFilter> it = arrayList.iterator();
        int i2 = 0;
        String str = null;
        while (it.hasNext()) {
            SearchFilter next = it.next();
            StringBuilder sb2 = new StringBuilder();
            if (arrayList2 != null) {
                str = arrayList2.get(i2);
            }
            String createFilterQuery = createFilterQuery(next, str);
            if (!createFilterQuery.isEmpty()) {
                sb2.append("(");
                sb2.append(createFilterQuery);
                sb2.append("\n)");
                stringJoiner.add(sb2.toString());
            }
            i2++;
        }
        return stringJoiner.toString();
    }

    public void applySearchFilter(SearchFilter searchFilter, ArrayList<String> arrayList) {
        this.mQueryBuilder.andCondition(getSearchCondition(searchFilter, arrayList));
        filterTime(searchFilter);
        addOrder(searchFilter);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy("A." + getFileIdColumnName());
    }

    public void applySearchStorySubFilter(SearchFilter searchFilter) {
        StringBuilder s = C0212a.s("(");
        s.append(createSubStoryFilterQuery(searchFilter));
        s.append("\n)");
        if (s.length() > 3) {
            this.mQueryBuilder.andCondition(s.toString());
        }
        this.mQueryBuilder.clearOrderBy();
        filterTime(searchFilter);
        addOrder(searchFilter);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy("A." + getFileIdColumnName());
    }

    public void applySearchSuggestionFilter(SearchFilter searchFilter) {
        StringBuilder s = C0212a.s("(");
        s.append(createSuggestionFilterQuery(searchFilter));
        s.append("\n)");
        if (s.length() > 3) {
            this.mQueryBuilder.andCondition(s.toString());
        }
        this.mQueryBuilder.clearOrderBy();
        addOrder(searchFilter);
        QueryBuilder queryBuilder = this.mQueryBuilder;
        queryBuilder.groupBy("A." + getFileIdColumnName());
    }

    public String getFileIdColumnName() {
        return "_id";
    }

    public abstract String hashTagSearch(String str);

    public final void projectionForSearchResult() {
        this.mQueryBuilder.clearProjection();
        this.mFilesTable.getQueryBuilder().clearProjection();
        this.mFilesTable.setDefaultProjection();
        this.mQueryBuilder.addProjection((List<String>) this.mFilesTable.getProjectionArray());
    }

    public abstract String stringSearch(SearchFilter searchFilter);
}
