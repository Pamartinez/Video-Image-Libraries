package com.samsung.android.gallery.module.search.engine;

import B8.d;
import N2.j;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.search.root.FilterOnlyThem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ExtraResults {
    private static final int MAX_TOP_RESULT;
    private static final boolean SUPPORT_ACTION_SUGGESTER = Features.isEnabled(Features.SUPPORT_ACTION_SUGGESTER);
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION);
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION_V2 = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2);
    private static final boolean SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST = Features.isEnabled(Features.SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST);
    private static final boolean SUPPORT_RELATIONSHIP_SEARCH = Features.isEnabled(Features.SUPPORT_RELATIONSHIP_SEARCH);
    private static final boolean SUPPORT_SCS_SEARCH = Features.isEnabled(Features.SUPPORT_SCS_SEARCH);
    private static final boolean SUPPORT_SCS_SEARCH_CHECK_PERMISSION = Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_PERMISSION);
    private static final boolean SUPPORT_SEARCH_CLUSTER = PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster);
    private String mActionResult;
    private String mClusterResult;
    private String mFallbackResult;
    private Serializable mFallbackResultCollection;
    private String mFallbackResultTranslated;
    private FilterOnlyThem mFilterOnlyThem;
    private String mFilterResult;
    private final HashMap<Long, String> mFrameIdMap = new HashMap<>();
    private String mFuzzySuggestKeyword;
    private boolean mHasMoreFacesThanOnlyThem;
    private String mLlmParsedQuery;
    private long mLlmQueryParse;
    private String mNoRelationKeywordsArrayResult;
    private String mOcrIds;
    private int mOnlyThemMatchedCount = 0;
    private String mPdcToken;
    private ArrayList<String> mQueryTimeInfo;
    private String mRelationArrayResult;
    private String mScsDisabledReason;
    private Bundle mScsQueryExtra;
    private ArrayList<String> mTopRecommendResult;

    static {
        int i2;
        if (Features.isEnabled(Features.IS_TABLET_BY_SYSTEM_PROPERTIES)) {
            i2 = 10;
        } else {
            i2 = 8;
        }
        MAX_TOP_RESULT = i2;
    }

    private boolean isOnlyThemEnable(Collection<String> collection) {
        for (String equals : collection) {
            if (TextUtils.equals(equals, "face_count = ? ")) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<String> makeTopRecommendResult(Bundle bundle) {
        try {
            HashMap hashMap = (HashMap) bundle.getSerializable("top_result");
            if (hashMap == null) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList arrayList2 = (ArrayList) hashMap.get("semantic");
            ArrayList arrayList3 = (ArrayList) hashMap.get("lexical");
            Log.v("ExtraResults", "semantic:" + arrayList2 + ", lexical: " + arrayList3);
            if (!(arrayList2 == null || arrayList3 == null)) {
                arrayList2.stream().forEach(new d(arrayList3, 20));
            }
            Optional.ofNullable(arrayList2).ifPresent(new d(arrayList, 21));
            Optional.ofNullable(arrayList3).ifPresent(new d(arrayList, 22));
            int size = arrayList.size();
            if (size <= 0) {
                return null;
            }
            int i2 = MAX_TOP_RESULT;
            if (size > i2) {
                arrayList.subList(i2, size).clear();
            }
            return arrayList;
        } catch (Exception e) {
            Log.e("ExtraResults", e.toString());
            return null;
        }
    }

    private void parseEntityResult(Bundle bundle) {
        String string = bundle.getString("entity");
        try {
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                if (jSONObject.has("relation")) {
                    this.mRelationArrayResult = RelationshipKeySet.getValidRelationshipData(jSONObject.getJSONArray("relation").toString());
                }
                if (SdkConfig.atLeast(SdkConfig.SEM.V) && jSONObject.has("no_relation_keywords")) {
                    this.mNoRelationKeywordsArrayResult = jSONObject.getJSONArray("no_relation_keywords").toString();
                }
            }
        } catch (JSONException e) {
            Log.w("ExtraResults", "parseEntityResult " + e);
        }
    }

    public String getActionResult() {
        return this.mActionResult;
    }

    public String getClusterResult() {
        return this.mClusterResult;
    }

    public String getFallbackResult() {
        return this.mFallbackResult;
    }

    public Serializable getFallbackResultCollection() {
        return this.mFallbackResultCollection;
    }

    public String getFallbackResultTranslated() {
        return this.mFallbackResultTranslated;
    }

    public FilterOnlyThem getFilterOnlyThem() {
        FilterOnlyThem filterOnlyThem = this.mFilterOnlyThem;
        if (filterOnlyThem == null || !filterOnlyThem.isAvailable()) {
            return null;
        }
        return this.mFilterOnlyThem;
    }

    public String getFilterResult() {
        return this.mFilterResult;
    }

    public HashMap<Long, String> getFrameIdMap() {
        return this.mFrameIdMap;
    }

    public String getFuzzyKeyword() {
        return this.mFuzzySuggestKeyword;
    }

    public String getLlmParsedQuery() {
        return this.mLlmParsedQuery;
    }

    public long getLlmQueryParse() {
        return this.mLlmQueryParse;
    }

    public String getNoRelationKeywords() {
        return this.mNoRelationKeywordsArrayResult;
    }

    public String getOcrResultIds() {
        return this.mOcrIds;
    }

    public Bundle getOriginBundle() {
        return this.mScsQueryExtra;
    }

    public String getPdcToken() {
        return this.mPdcToken;
    }

    public ArrayList<String> getQueryTimeInfo() {
        return this.mQueryTimeInfo;
    }

    public String getRelationshipResult() {
        return this.mRelationArrayResult;
    }

    public String getScsDisabledReason() {
        return this.mScsDisabledReason;
    }

    public ArrayList<String> getTopRecommendResult() {
        return this.mTopRecommendResult;
    }

    public boolean hasOnlyThemFaces() {
        FilterOnlyThem filterOnlyThem = this.mFilterOnlyThem;
        if (filterOnlyThem == null || filterOnlyThem.isEmpty()) {
            return false;
        }
        return true;
    }

    public void initFilterOnlyThem(SearchFilter searchFilter) {
        this.mFilterOnlyThem = new FilterOnlyThem(searchFilter.getCreatureKeys());
        if (isOnlyThemEnable(searchFilter.getFilterSelections())) {
            this.mFilterOnlyThem.setAvailable(true);
        }
    }

    public boolean isExistFallbackResult() {
        boolean isEmpty;
        Serializable serializable = this.mFallbackResultCollection;
        if (serializable != null) {
            if (SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST) {
                isEmpty = ((ArrayList) serializable).isEmpty();
            } else if (SUPPORT_HIERARCHICAL_SUGGESTION_V2) {
                isEmpty = ((HashMap) serializable).isEmpty();
            }
            return !isEmpty;
        }
        isEmpty = TextUtils.isEmpty(this.mFallbackResult);
        return !isEmpty;
    }

    public void parse(Bundle bundle, boolean z) {
        String str;
        if (SUPPORT_SCS_SEARCH) {
            this.mFilterResult = bundle.getString("facet");
            if (SUPPORT_SEARCH_CLUSTER) {
                str = bundle.getString("cluster");
            } else {
                str = null;
            }
            this.mClusterResult = str;
            if (PreferenceFeatures.OneUi7x.SUPPORT_TOP_RESULT_SEARCH) {
                this.mTopRecommendResult = makeTopRecommendResult(bundle);
            }
            if (!z && Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
                this.mPdcToken = bundle.getString("pdc_info");
                j.y(new StringBuilder("pdc_info : "), this.mPdcToken, "ExtraResults");
            }
        }
        if ((SUPPORT_SCS_SEARCH_CHECK_PERMISSION || Features.isEnabled(Features.IS_CHINESE_DEVICE)) && "disabled".equals(bundle.get("index_status"))) {
            String string = bundle.getString("disabled_reason");
            this.mScsDisabledReason = string;
            if ("PERMISSION".equals(string)) {
                this.mScsDisabledReason = null;
            }
        }
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SseDebug)) {
            this.mQueryTimeInfo = bundle.getStringArrayList("total_query_elapsed_time_info");
            this.mLlmQueryParse = bundle.getLong("llm_query_elapsed_time");
            this.mLlmParsedQuery = bundle.getString("llm_parsed_query");
            StringBuilder sb2 = new StringBuilder("mQueryTimeInfo:");
            sb2.append(this.mQueryTimeInfo);
            sb2.append(", mLlmQueryParse: ");
            sb2.append(this.mLlmQueryParse);
            sb2.append(", mLlmParsedQuery: ");
            j.y(sb2, this.mLlmParsedQuery, "ExtraResults");
        }
        if (SUPPORT_ACTION_SUGGESTER) {
            this.mActionResult = bundle.getString("action");
        }
        if (SUPPORT_HIERARCHICAL_SUGGESTION_V2 || SUPPORT_HIERARCHICAL_SUGGESTION_V2_AS_LIST) {
            this.mFallbackResultCollection = bundle.getSerializable("result-fallback-query");
        } else if (SUPPORT_HIERARCHICAL_SUGGESTION) {
            this.mFallbackResult = bundle.getString("result-fallback-query");
            if (Features.isEnabled(Features.SUPPORT_SCS_TRANSLATED_KEYWORD)) {
                this.mFallbackResultTranslated = bundle.getString("result-fallback-query-translated");
            }
        }
        if (SUPPORT_RELATIONSHIP_SEARCH) {
            parseEntityResult(bundle);
        }
    }

    public void parseFilterResult(Cursor cursor) {
        if (!SUPPORT_SCS_SEARCH && cursor.isFirst() && cursor.getColumnCount() > 1) {
            String string = cursor.getString(1);
            this.mFilterResult = string;
            if (TextUtils.isEmpty(string)) {
                Log.sw("ExtraResults", "Facets data is empty");
            }
        }
    }

    public void parseFrameId(Cursor cursor) {
        String str;
        if (PreferenceFeatures.OneUi8x.VIDEO_SEARCH) {
            long j2 = UnsafeCast.toLong(cursor.getString(0), -1);
            int columnIndex = cursor.getColumnIndex("frame_id");
            if (j2 <= -1 || columnIndex <= -1) {
                str = null;
            } else {
                str = cursor.getString(columnIndex);
            }
            if (!TextUtils.isEmpty(str)) {
                this.mFrameIdMap.put(Long.valueOf(j2), str);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:2:0x0006 A[LOOP:0: B:2:0x0006->B:5:0x0011, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setFilterOnlyThemAvailable(android.database.Cursor r2) {
        /*
            r1 = this;
            boolean r0 = r2.moveToFirst()
            if (r0 == 0) goto L_0x0013
        L_0x0006:
            boolean r0 = r1.setFilterOnlyThemAvailableForEach(r2)
            if (r0 == 0) goto L_0x000d
            goto L_0x0013
        L_0x000d:
            boolean r0 = r2.moveToNext()
            if (r0 != 0) goto L_0x0006
        L_0x0013:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.search.engine.ExtraResults.setFilterOnlyThemAvailable(android.database.Cursor):void");
    }

    public boolean setFilterOnlyThemAvailableForEach(Cursor cursor) {
        if (this.mFilterOnlyThem.isAvailable()) {
            return true;
        }
        int size = this.mFilterOnlyThem.size();
        try {
            int i2 = cursor.getInt(cursor.getColumnIndex("face_count"));
            if (i2 == size) {
                this.mOnlyThemMatchedCount++;
            }
            if (i2 > size) {
                this.mHasMoreFacesThanOnlyThem = true;
            }
            if (this.mOnlyThemMatchedCount < 2 || !this.mHasMoreFacesThanOnlyThem) {
                return false;
            }
            Log.s("ExtraResults", "setFilterOnlyThemAvailableForEach() : mFilterOnlyThem available!!");
            this.mFilterOnlyThem.setAvailable(true);
            return false;
        } catch (Exception e) {
            Log.se("ExtraResults", "setFilterOnlyThemAvailableForEach() : " + e.getMessage());
            return false;
        }
    }

    public void setFuzzySuggestKeyword(String str) {
        this.mFuzzySuggestKeyword = str;
    }

    public void setOcrResultIds(String str) {
        this.mOcrIds = str;
    }

    public void setScsDisabledReason(String str) {
        this.mScsDisabledReason = str;
    }

    public void setScsQueryExtra(Bundle bundle) {
        this.mScsQueryExtra = bundle;
    }
}
