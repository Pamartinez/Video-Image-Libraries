package com.samsung.android.gallery.app.ui.list.search.analysis;

import com.samsung.android.gallery.database.dal.mp.helper.MpHelper;
import com.samsung.android.gallery.module.analyticsquery.AnalyticsQuery;
import com.samsung.android.gallery.module.analyticsquery.SearchAnalysisStatus;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Features;
import h4.C0464a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchAnalysisHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SearchEngineCheckList {
        static final ArrayList<String> list = AnalyticsQuery.getSearchEngineCheckList();
    }

    public static boolean checkAnalysisTipCondition(String str) {
        if (LocationKey.isSearchKeyword(str)) {
            return AnalyticsQuery.loadSearchAnalysisStatus().entrySet().stream().anyMatch(new C0464a(1));
        }
        if (LocationKey.isSearchCategoryCreature(str) && new MpHelper().getPeopleCount() < 9 && new MpHelper().getPetsCount() < 9) {
            HashMap<String, SearchAnalysisStatus> loadSearchAnalysisStatus = AnalyticsQuery.loadSearchAnalysisStatus();
            if (isAnalyzing(loadSearchAnalysisStatus.get("Face"))) {
                return true;
            }
            if (Features.isEnabled(Features.SUPPORT_PET_CLUSTER)) {
                return isAnalyzing(loadSearchAnalysisStatus.get("Pet"));
            }
        }
        return false;
    }

    private static boolean isAnalyzing(SearchAnalysisStatus searchAnalysisStatus) {
        if (searchAnalysisStatus == null || searchAnalysisStatus.getAnalyzedRatio() > 80) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$checkAnalysisTipCondition$0(Map.Entry entry) {
        if (!SearchEngineCheckList.list.contains(entry.getKey()) || !isAnalyzing((SearchAnalysisStatus) entry.getValue())) {
            return false;
        }
        return true;
    }
}
