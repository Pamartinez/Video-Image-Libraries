package com.samsung.android.gallery.module.search.root;

import N2.j;
import X9.a;
import android.os.Bundle;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.support.search.LlmQpOperation;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class IntelligentSearchArgs {
    private final SearchFilter mSearchFilter;
    private final ISelectionAnalyzer mSelectionAnalyzer;

    public IntelligentSearchArgs(SearchFilter searchFilter) {
        ISelectionAnalyzer iSelectionAnalyzer;
        this.mSearchFilter = searchFilter;
        if (checkTermQuery(searchFilter.getSelectedFilter())) {
            iSelectionAnalyzer = new IntelligentSelectionAnalyzer(searchFilter);
        } else {
            iSelectionAnalyzer = new SimpleSelectionAnalyzer(searchFilter);
        }
        this.mSelectionAnalyzer = iSelectionAnalyzer;
    }

    private boolean checkTermQuery(String str) {
        if (Features.isEnabled(Features.SUPPORT_HASHTAG_SEARCH) || !TextUtils.isEmpty(str)) {
            return true;
        }
        return false;
    }

    private JSONArray composeClusterRequestFields() {
        JSONArray jSONArray = new JSONArray();
        IntelligentSearchIndexField.CLUSTER_REQUEST_FIELDS.forEach(new a(jSONArray, 1));
        return jSONArray;
    }

    private JSONArray composeClusterRequestPrefixMatchFields() {
        JSONArray jSONArray = new JSONArray();
        IntelligentSearchIndexField.CLUSTER_REQUEST_PREFIX_MATCH_FIELDS.forEach(new a(jSONArray, 1));
        return jSONArray;
    }

    private JSONArray composeClusterRequestTermMatchFields() {
        JSONArray jSONArray = new JSONArray();
        IntelligentSearchIndexField.CLUSTER_REQUEST_TERM_MATCH_FIELDS.forEach(new a(jSONArray, 1));
        return jSONArray;
    }

    private JSONArray composeFacetRequestTermsFields() {
        ArrayList<String> arrayList;
        JSONArray jSONArray = new JSONArray();
        if (this.mSearchFilter.getFacetTermsFields() == null || this.mSearchFilter.getFacetTermsFields().isEmpty()) {
            arrayList = (ArrayList) IntelligentSearchIndexField.FACET_REQUEST_FIELDS.clone();
        } else {
            arrayList = this.mSearchFilter.getFacetTermsFields();
        }
        if (excludeSubLocationFacetRequest()) {
            arrayList.remove("sub_location");
        }
        arrayList.forEach(new a(jSONArray, 1));
        return jSONArray;
    }

    private IntelligentSearchArgs contentFiltering(Bundle bundle) {
        if (this.mSearchFilter.isKeywordSuggestion() && Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CONTENT_FILTERING_ON_AUTOCOMPLETE)) {
            if (this.mSearchFilter.isPickMode()) {
                contentFilteringWithMediaTypeAndStorage(bundle);
                return this;
            } else if (this.mSearchFilter.getMainEntityInfo() != null) {
                contentFilteringWithSelectedFilter(bundle);
            }
        }
        return this;
    }

    private void contentFilteringWithMediaTypeAndStorage(Bundle bundle) {
        StringBuilder sb2 = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        String mediaType = this.mSearchFilter.getMediaType();
        if (!TextUtils.isEmpty(mediaType)) {
            sb2.append("media_type=?");
            arrayList.add(MediaType.valueOf(Integer.parseInt(mediaType)).toString().toLowerCase());
        }
        if (this.mSearchFilter.isLocalOnly()) {
            if (!sb2.toString().isEmpty()) {
                sb2.append(" and ");
            }
            sb2.append("is_cloud=?");
            arrayList.add(String.valueOf(StorageType.Local.toInt()));
        }
        if (!arrayList.isEmpty()) {
            bundle.putString("se:filter-sql-selection", sb2.toString());
            bundle.putStringArray("se:filter-sql-selection-args", (String[]) arrayList.toArray(new String[0]));
        }
    }

    private void contentFilteringWithSelectedFilter(Bundle bundle) {
        int i2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mSearchFilter.getMainEntityInfo()[0]);
        ArrayList arrayList2 = new ArrayList(Arrays.asList(this.mSearchFilter.getMainEntityInfo()[1].split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        if (this.mSearchFilter.getSelectedFilter() != null) {
            try {
                JSONArray jSONArray = new JSONArray(this.mSearchFilter.getSelectedFilter());
                if (this.mSearchFilter.isFuzzySuggestKeywordQueryEnabled()) {
                    i2 = jSONArray.length() - 1;
                } else {
                    i2 = jSONArray.length();
                }
                for (int i7 = 0; i7 < i2; i7++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i7);
                    arrayList.add(extractSelection(jSONObject));
                    arrayList2.add(extractSelectionArgs(jSONObject).get(0));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (!arrayList2.isEmpty()) {
            bundle.putString("se:filter-sql-selection", String.join(" AND ", arrayList));
            bundle.putStringArray("se:filter-sql-selection-args", (String[]) arrayList2.toArray(new String[0]));
        }
    }

    private IntelligentSearchArgs enableCluster(Bundle bundle) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.SearchCluster) && this.mSearchFilter.isClusterEnabled() && !this.mSearchFilter.isRequestFacetOnly()) {
            bundle.putString("query-arg-cluster-request", getClusterRequest());
        }
        return this;
    }

    private IntelligentSearchArgs enableFilter(Bundle bundle) {
        if (this.mSearchFilter.isFilterEnabled()) {
            bundle.putBoolean("query-arg-keyword-filter-enabled", true);
            if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH)) {
                bundle.putString("query-arg-facet-request", getFacetRequest());
            }
        }
        return this;
    }

    private boolean excludeSubLocationFacetRequest() {
        if (!Features.isEnabled(Features.SUPPORT_SCS_SEARCH_SUB_LOCATION_FACET)) {
            return true;
        }
        if ("facet_location".equals(this.mSearchFilter.getTerm()) || "poitag".equals(this.mSearchFilter.getTerm())) {
            return false;
        }
        return true;
    }

    private String extractSelection(JSONObject jSONObject) {
        return jSONObject.getString("selection");
    }

    private ArrayList<String> extractSelectionArgs(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONArray("selection_args");
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            arrayList.add(jSONArray.getString(i2));
        }
        return arrayList;
    }

    private IntelligentSearchArgs fallbackFilter(Bundle bundle) {
        if (!this.mSearchFilter.isAutoCompleteQueryEnabled() && !this.mSearchFilter.isFuzzySuggestKeywordQueryEnabled()) {
            bundle.putString("query-arg-lucene-fallback-query", "hierarchical");
        }
        return this;
    }

    private String getClusterRequest() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (this.mSearchFilter.isFullClusterEnabled()) {
                jSONObject2.put("fields", composeClusterRequestFields());
                if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    jSONObject2.put("prefix_match_fields", composeClusterRequestPrefixMatchFields());
                }
                jSONObject2.put("term_match_fields", composeClusterRequestTermMatchFields());
            }
            jSONObject2.put("count", 1);
            jSONObject.put("policy", jSONObject2);
        } catch (JSONException e) {
            Log.se("IntelligentSearchArgs", e.getMessage());
        }
        return jSONObject.toString();
    }

    private String getFacetRequest() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("fields", composeFacetRequestTermsFields());
            jSONObject2.put("size", getMaxFacetTermsSize());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("terms", jSONObject2);
            jSONObject.put("gallery", jSONObject3);
        } catch (JSONException e) {
            Log.se("IntelligentSearchArgs", e.getMessage());
        }
        return jSONObject.toString();
    }

    private int getMaxFacetTermsSize() {
        if (this.mSearchFilter.getMaxFacetTermsSize() != null) {
            return this.mSearchFilter.getMaxFacetTermsSize().intValue();
        }
        return 5;
    }

    private HashMap<String, Boolean> getPolicy() {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        if (this.mSearchFilter.isAutoCompleteQueryEnabled()) {
            hashMap.put(IntelligentSearchPolicy.FUZZY_QUERY.getPolicyName(), Boolean.FALSE);
            hashMap.put(IntelligentSearchPolicy.PREFIX_QUERY.getPolicyName(), Boolean.TRUE);
            return hashMap;
        }
        boolean isFuzzySuggestKeywordQueryEnabled = this.mSearchFilter.isFuzzySuggestKeywordQueryEnabled();
        String keyboardLanguage = this.mSearchFilter.getKeyboardLanguage();
        IntelligentSearchPolicy intelligentSearchPolicy = IntelligentSearchPolicy.PHONETIC;
        boolean isActive = intelligentSearchPolicy.isActive(keyboardLanguage);
        IntelligentSearchPolicy intelligentSearchPolicy2 = IntelligentSearchPolicy.TRANSLITERATION;
        boolean isActive2 = intelligentSearchPolicy2.isActive(keyboardLanguage);
        IntelligentSearchPolicy intelligentSearchPolicy3 = IntelligentSearchPolicy.EXTREME_FUZZY;
        boolean isActive3 = intelligentSearchPolicy3.isActive(keyboardLanguage);
        IntelligentSearchPolicy intelligentSearchPolicy4 = IntelligentSearchPolicy.KOREAN_QUERY_CONVERSION_ENABLED;
        boolean isActive4 = intelligentSearchPolicy4.isActive(keyboardLanguage);
        boolean isSemanticQueryEnabled = this.mSearchFilter.isSemanticQueryEnabled();
        hashMap.put(IntelligentSearchPolicy.FUZZY_QUERY.getPolicyName(), Boolean.valueOf(isFuzzySuggestKeywordQueryEnabled));
        hashMap.put(intelligentSearchPolicy.getPolicyName(), Boolean.valueOf(isActive));
        hashMap.put(intelligentSearchPolicy2.getPolicyName(), Boolean.valueOf(isActive2));
        hashMap.put(intelligentSearchPolicy3.getPolicyName(), Boolean.valueOf(isActive3));
        hashMap.put(intelligentSearchPolicy4.getPolicyName(), Boolean.valueOf(isActive4));
        IntelligentSearchPolicy intelligentSearchPolicy5 = IntelligentSearchPolicy.SEMANTIC_QUERY_ENABLED;
        if (intelligentSearchPolicy5.isFeatureEnabled()) {
            hashMap.put(intelligentSearchPolicy5.getPolicyName(), Boolean.valueOf(isSemanticQueryEnabled));
        }
        return hashMap;
    }

    private IntelligentSearchArgs includePdcResults(Bundle bundle) {
        boolean z;
        if (!this.mSearchFilter.isRequestFacetOnly()) {
            if (!Features.isEnabled(Features.SUPPORT_PDC_CLUSTER) || !SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
                z = false;
            } else {
                z = true;
            }
            bundle.putBoolean("query-arg-include-pdc-results", z);
        }
        return this;
    }

    private IntelligentSearchArgs limit(Bundle bundle) {
        bundle.putInt("android:query-arg-limit", this.mSearchFilter.getLimit());
        return this;
    }

    private IntelligentSearchArgs locationFilter(Bundle bundle) {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.LocationAuth)) {
            if (PreferenceFeatures.OneUi8x.SUPPORT_SELECTION_COLUMN_BLOCK_FILTER) {
                bundle.putString("query-arg-lucene-selection-columns-block-filter", IntelligentSearchIndexField.NO_LOCATION_SELECTION_BLOCK_FIELDS);
                return this;
            }
            bundle.putString("query-arg-lucene-selection-columns-filter", IntelligentSearchIndexField.NO_LOCATION_SELECTION_FIELDS);
        }
        return this;
    }

    private IntelligentSearchArgs orderBy(Bundle bundle) {
        String str;
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_FEEDBACK)) {
            if ("oldest".equals(this.mSearchFilter.getOrder())) {
                str = "ASC";
            } else {
                str = "DESC";
            }
            ArrayList arrayList = new ArrayList(Arrays.asList(new String[]{"datetaken_sort", "_id_sort"}));
            ArrayList arrayList2 = new ArrayList(Arrays.asList(new String[]{str, str}));
            bundle.putStringArrayList("android:query-arg-sort-columns", arrayList);
            bundle.putStringArrayList("android:query-arg-sql-sort-order", arrayList2);
        }
        return this;
    }

    private IntelligentSearchArgs queryPolicy(Bundle bundle) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        HashMap<String, Boolean> policy = getPolicy();
        bundle.putSerializable("query-arg-lucene-query-policy", policy);
        StringBuilder sb2 = new StringBuilder("");
        Boolean bool = Boolean.TRUE;
        if (bool.equals(policy.get(IntelligentSearchPolicy.FUZZY_QUERY.getPolicyName()))) {
            str = "F";
        } else {
            str = "f";
        }
        sb2.append(str);
        if (bool.equals(policy.get(IntelligentSearchPolicy.EXTREME_FUZZY.getPolicyName()))) {
            str2 = "E";
        } else {
            str2 = "e";
        }
        sb2.append(str2);
        if (bool.equals(policy.get(IntelligentSearchPolicy.PHONETIC.getPolicyName()))) {
            str3 = "P";
        } else {
            str3 = "p";
        }
        sb2.append(str3);
        if (bool.equals(policy.get(IntelligentSearchPolicy.TRANSLITERATION.getPolicyName()))) {
            str4 = "T";
        } else {
            str4 = "t";
        }
        sb2.append(str4);
        if (bool.equals(policy.get(IntelligentSearchPolicy.KOREAN_QUERY_CONVERSION_ENABLED.getPolicyName()))) {
            str5 = "K";
        } else {
            str5 = "k";
        }
        sb2.append(str5);
        IntelligentSearchPolicy intelligentSearchPolicy = IntelligentSearchPolicy.SEMANTIC_QUERY_ENABLED;
        if (!intelligentSearchPolicy.isFeatureEnabled()) {
            str6 = "-";
        } else if (bool.equals(policy.get(intelligentSearchPolicy.getPolicyName()))) {
            str6 = "S";
        } else {
            str6 = "s";
        }
        sb2.append(str6);
        bundle.putString("debugPolicy", sb2.toString());
        return this;
    }

    private IntelligentSearchArgs selection(Bundle bundle) {
        bundle.putString("android:query-arg-sql-selection", this.mSelectionAnalyzer.getSelection());
        return this;
    }

    private IntelligentSearchArgs selectionArgs(Bundle bundle) {
        String[] selectionArgs = this.mSelectionAnalyzer.getSelectionArgs();
        if (selectionArgs != null) {
            bundle.putStringArray("android:query-arg-sql-selection-args", selectionArgs);
        }
        return this;
    }

    private IntelligentSearchArgs setForceSync(Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_SCS_FORCE_SYNC_ALL)) {
            bundle.putBoolean("query-arg-force-sync-all", this.mSearchFilter.isForceSync());
        }
        return this;
    }

    private IntelligentSearchArgs setLlmQpOperation(Bundle bundle) {
        LlmQpOperation llmQpOperation;
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            if (this.mSearchFilter.isLlmEnabled()) {
                llmQpOperation = LlmQpOperation.ENABLE;
            } else {
                llmQpOperation = LlmQpOperation.DISABLE;
            }
            bundle.putString("query-arg-llm-qp-operation", LlmQpOperation.valueOf(llmQpOperation.ordinal()).getValue());
        }
        return this;
    }

    private IntelligentSearchArgs setPdcInfo(Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER) && SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
            bundle.putString("query-arg-pdc-info", this.mSearchFilter.getPdcToken());
        }
        return this;
    }

    private IntelligentSearchArgs suggestAction(Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_ACTION_SUGGESTER)) {
            bundle.putString("query-arg-action-suggest-type", "gallery");
        }
        return this;
    }

    private IntelligentSearchArgs suggestKeywordQuery(Bundle bundle) {
        if (this.mSearchFilter.isFuzzySuggestKeywordQueryEnabled()) {
            bundle.putBoolean("query-arg-suggest-keyword", true);
            return this;
        }
        if (this.mSearchFilter.isAutoCompleteQueryEnabled()) {
            bundle.putBoolean("query-arg-suggest-keyword", true);
            bundle.putBoolean("query-arg-suggest-keyword-field-value", true);
        }
        return this;
    }

    private IntelligentSearchArgs suggestedKeywordFeature(Bundle bundle) {
        if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_CHECK_SUGGESTED_KEYWORD_FEATURE)) {
            String suggestedKeywordFeature = this.mSearchFilter.getSuggestedKeywordFeature();
            if (!TextUtils.isEmpty(suggestedKeywordFeature)) {
                bundle.putString("query-arg-suggested-keyword-feature", suggestedKeywordFeature);
            }
        }
        return this;
    }

    private IntelligentSearchArgs timeRange(Bundle bundle) {
        String str;
        long fromTime = this.mSearchFilter.getFromTime();
        long toTime = this.mSearchFilter.getToTime();
        if (fromTime > 0 && toTime > 0) {
            bundle.putStringArray("query-arg-lucene-selection-range-args", new String[]{Long.toString(fromTime), Long.toString(toTime)});
            if (Features.isEnabled(Features.SUPPORT_BIXBY_LOCAL_TIME_SEARCH)) {
                str = "localtime";
            } else {
                str = IParameterKey.DATE_TAKEN;
            }
            bundle.putString("query-arg-lucene-selection-range-column", str);
        }
        return this;
    }

    public static String toDebugString(Bundle bundle) {
        String str;
        String string = bundle.getString("debugPolicy");
        String string2 = bundle.getString("query-arg-cluster-request", (String) null);
        String string3 = bundle.getString("android:query-arg-sql-selection");
        String[] stringArray = bundle.getStringArray("android:query-arg-sql-selection-args");
        String str2 = "null";
        if (stringArray != null) {
            str = Logger.getEncodedString(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, stringArray));
        } else {
            str = str2;
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER)) {
            str2 = bundle.getString("query-arg-pdc-info");
        }
        String string4 = bundle.getString("query-arg-llm-qp-operation");
        boolean z = bundle.getBoolean("query-arg-include-pdc-results");
        StringBuilder q = C0086a.q("SearchArgs{", string, ",cluster=", string2, ",selection=");
        C0086a.z(q, string3, ",args=", str, ", pdcInfo=");
        q.append(Boolean.valueOf(TextUtils.isEmpty(str2)));
        q.append(", llmQp=");
        q.append(string4);
        q.append(", includePdcResults=");
        return j.h(q, z, "}");
    }

    public Bundle create() {
        Bundle bundle = new Bundle();
        return selection(bundle).selectionArgs(bundle).limit(bundle).timeRange(bundle).locationFilter(bundle).suggestKeywordQuery(bundle).suggestedKeywordFeature(bundle).enableFilter(bundle).enableCluster(bundle).fallbackFilter(bundle).contentFiltering(bundle).suggestAction(bundle).queryPolicy(bundle).orderBy(bundle).setForceSync(bundle).setLlmQpOperation(bundle).setPdcInfo(bundle).includePdcResults(bundle).build(bundle);
    }

    public Bundle createAutoComplete() {
        Bundle bundle = new Bundle();
        return selection(bundle).selectionArgs(bundle).limit(bundle).timeRange(bundle).locationFilter(bundle).suggestKeywordQuery(bundle).suggestedKeywordFeature(bundle).enableFilter(bundle).enableCluster(bundle).fallbackFilter(bundle).contentFiltering(bundle).queryPolicy(bundle).build(bundle);
    }

    private Bundle build(Bundle bundle) {
        return bundle;
    }
}
