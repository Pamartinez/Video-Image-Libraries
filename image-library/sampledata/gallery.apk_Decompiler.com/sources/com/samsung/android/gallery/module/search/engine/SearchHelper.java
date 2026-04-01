package com.samsung.android.gallery.module.search.engine;

import W9.h;
import android.database.Cursor;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.query.QueryParams;
import com.samsung.android.gallery.database.dal.mp.helper.SearchApi;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchHelper {
    QueryParams mParams;

    public SearchHelper(QueryParams queryParams) {
        this.mParams = queryParams;
    }

    private Consumer<QueryParams> getQueryParamModifierForPeople(String str, SearchFilter searchFilter) {
        return new h(this, searchFilter, str, 0);
    }

    private Consumer<QueryParams> getQueryParamModifierForPet(String str, SearchFilter searchFilter) {
        return new h(this, searchFilter, str, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForPeople$0(SearchFilter searchFilter, String str, QueryParams queryParams) {
        queryParams.setSubCategory(IdentityCreatureUtil.createWithUnifiedId(Long.parseLong(searchFilter.getRawKeyword()), IdentityCreatureUtil.Category.PEOPLE));
        if (str.isEmpty()) {
            str = "''";
        }
        queryParams.setFileIds(str);
        QueryParams queryParams2 = this.mParams;
        queryParams.mIsForOnDemandQuery = queryParams2.mIsForOnDemandQuery;
        queryParams.mUseFileIdsConcat = queryParams2.mUseFileIdsConcat;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getQueryParamModifierForPet$1(SearchFilter searchFilter, String str, QueryParams queryParams) {
        queryParams.setSubCategory(IdentityCreatureUtil.createWithUnifiedId(Long.parseLong(searchFilter.getRawKeyword()), IdentityCreatureUtil.Category.PET));
        if (str.isEmpty()) {
            str = "''";
        }
        queryParams.setFileIds(str);
        QueryParams queryParams2 = this.mParams;
        queryParams.mIsForOnDemandQuery = queryParams2.mIsForOnDemandQuery;
        queryParams.mUseFileIdsConcat = queryParams2.mUseFileIdsConcat;
    }

    private Cursor searchItemsForPeople(String str, SearchFilter searchFilter) {
        String str2;
        Consumer<QueryParams> queryParamModifierForPeople = getQueryParamModifierForPeople(str, searchFilter);
        if (searchFilter.isForQueryOnDemand()) {
            str2 = "mp://People/files/fileIds";
        } else {
            str2 = "mp://People/files";
        }
        return DbCompat.query(str2, queryParamModifierForPeople);
    }

    private Cursor searchItemsForPet(String str, SearchFilter searchFilter) {
        String str2;
        Consumer<QueryParams> queryParamModifierForPet = getQueryParamModifierForPet(str, searchFilter);
        if (searchFilter.isForQueryOnDemand()) {
            str2 = "mp://Pets/files/fileIds";
        } else {
            str2 = "mp://Pets/files";
        }
        return DbCompat.query(str2, queryParamModifierForPet);
    }

    public Cursor searchItemFileIds(String str, SearchFilter searchFilter) {
        return searchItems(str, searchFilter);
    }

    public Cursor searchItems(String str, SearchFilter searchFilter) {
        if ("recommended_id".equals(searchFilter.getTerm())) {
            return searchItemsForPeople(str, searchFilter);
        }
        if ("pet_recommended_id".equals(searchFilter.getTerm())) {
            return searchItemsForPet(str, searchFilter);
        }
        return new SearchApi(this.mParams).searchItemsCommon(str, searchFilter);
    }
}
