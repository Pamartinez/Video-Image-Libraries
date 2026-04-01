package com.samsung.android.gallery.module.search.engine;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.search.root.AllScreenshotFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.IntelligentSearch;
import com.samsung.android.gallery.support.translation.TranslationManager;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SearchLog;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FuzzyKeywordSearch {
    private static final boolean ONLY_GET_FUZZY_KEYWORD = PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85;

    private String getFuzzySuggestKeyword(IntelligentSearch intelligentSearch, SearchFilter searchFilter) {
        Cursor query;
        String str;
        SearchFilter searchFilter2 = (SearchFilter) searchFilter.clone();
        searchFilter2.setFuzzySuggestKeywordQueryEnabled(true);
        if (searchFilter2.getSelectedFilter() != null) {
            if ("all_screenshot".equals(searchFilter2.getTerm())) {
                AllScreenshotFilterResultsEntity allScreenshotFilterResultsEntity = new AllScreenshotFilterResultsEntity("all");
                searchFilter2.setMainEntityInfo(new String[]{allScreenshotFilterResultsEntity.getSelection(), TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, allScreenshotFilterResultsEntity.getRawLabelsList())});
            } else {
                searchFilter2.setMainEntityInfo(new String[]{searchFilter2.getTerm() + " = ?", searchFilter2.getRawKeyword()});
            }
        }
        try {
            query = intelligentSearch.query(searchFilter2);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(query.getColumnIndex("keywords"));
                    if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
                        str = query.getString(query.getColumnIndex("keyword_translated"));
                    } else {
                        str = TranslationManager.getInstance().translateWithIntelligentSearch(string);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        string = str;
                    }
                    if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
                        searchFilter.setSuggestedKeywordFeature(query.getString(query.getColumnIndex("featureName")));
                    }
                    Log.s("FuzzyKeywordSearch", "getFuzzySuggestKeyword : " + Logger.getEncodedString(string));
                    query.close();
                    return string;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            Log.se("FuzzyKeywordSearch", "getFuzzySuggestKeyword " + e);
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public Cursor query(IntelligentSearch intelligentSearch, SearchFilter searchFilter, ExtraResults extraResults) {
        SearchLog.d("FuzzyKeywordSearch", "SSE#fuzzy");
        String fuzzySuggestKeyword = getFuzzySuggestKeyword(intelligentSearch, searchFilter);
        if (!TextUtils.isEmpty(fuzzySuggestKeyword)) {
            extraResults.setFuzzySuggestKeyword(fuzzySuggestKeyword);
            if (ONLY_GET_FUZZY_KEYWORD || searchFilter.fromInstantSearch()) {
                return null;
            }
            searchFilter.replaceKeyword(AppResources.getAppContext(), fuzzySuggestKeyword);
            return intelligentSearch.query(searchFilter);
        }
        Log.sw("FuzzyKeywordSearch", "Fuzzy keyword is null");
        return null;
    }
}
