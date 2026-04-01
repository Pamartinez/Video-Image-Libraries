package com.samsung.android.gallery.module.search.root;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.SearchFilter;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.settings.SmartSuggestionsSettingApi;
import com.samsung.android.gallery.support.type.IntelligentSearchIndexField;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringJoiner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntelligentSelectionAnalyzer implements ISelectionAnalyzer {
    private static final boolean SUPPORT_ALBUM_PICTURES_SEARCH = Features.isEnabled(Features.SUPPORT_SCS_ALBUM_PICTURES_SEARCH);
    private String mSelection;
    private String[] mSelectionArgs;

    public IntelligentSelectionAnalyzer(SearchFilter searchFilter) {
        analysis(searchFilter);
    }

    private boolean allowSplitKeyword(SearchFilter searchFilter) {
        return "key_word".equals(searchFilter.getTerm());
    }

    private void analysis(SearchFilter searchFilter) {
        ArrayList arrayList = new ArrayList();
        analysisKeyword(arrayList, searchFilter);
        analysisContentType(arrayList, searchFilter);
        analysisSelectedFilter(arrayList, searchFilter);
        pullOutResults(arrayList, searchFilter);
    }

    private void analysisContentType(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        if (searchFilter.isPickMode() && !searchFilter.isKeywordSuggestion()) {
            String mediaType = searchFilter.getMediaType();
            if (!TextUtils.isEmpty(mediaType)) {
                arrayList.add(new SelectInfo(appendSelectionMark(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE), MediaType.valueOf(Integer.parseInt(mediaType)).toString().toLowerCase()));
            }
            if (searchFilter.isLocalOnly()) {
                arrayList.add(new SelectInfo(appendSelectionMark("is_cloud"), String.valueOf(StorageType.Local.toInt())));
            }
        }
    }

    private void analysisKeyword(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        if (searchFilter.isAutoCompleteQueryEnabled()) {
            analysisKeywordForAutoComplete(arrayList, searchFilter);
        } else if (searchFilter.isFuzzySuggestKeywordQueryEnabled()) {
            analysisKeywordForFuzzy(arrayList, searchFilter);
        } else if ("all_screenshot".equals(searchFilter.getTerm())) {
            analysisKeywordForAllScreenshot(arrayList, searchFilter);
        } else if ("sef_file_type".equals(searchFilter.getTerm())) {
            analysisKeywordForShotMode(arrayList, searchFilter);
        } else {
            analysisKeywordForNormal(arrayList, searchFilter);
        }
    }

    private void analysisKeywordForAllScreenshot(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        AllScreenshotFilterResultsEntity allScreenshotFilterResultsEntity = new AllScreenshotFilterResultsEntity("all");
        arrayList.add(new SelectInfo(allScreenshotFilterResultsEntity.getSelection(), allScreenshotFilterResultsEntity.getRawLabelsList()));
    }

    private void analysisKeywordForAutoComplete(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        String rawKeyword = searchFilter.getRawKeyword();
        if (searchFilter.isTagKeyword()) {
            arrayList.add(new SelectInfo(IntelligentSearchIndexField.getAutoCompleteTermSelection(true), getTagKeyword(rawKeyword)));
        } else {
            arrayList.add(new SelectInfo(IntelligentSearchIndexField.getAutoCompleteTermSelection(false), getKeywordSelectionArgs(rawKeyword, IntelligentSearchIndexField.getAutoCompleteTermSize())));
        }
    }

    private void analysisKeywordForFuzzy(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        arrayList.add(new SelectInfo(IntelligentSearchIndexField.getFuzzySuggestionKeywordTermSelection(), getKeywordSelectionArgs(searchFilter.getFuzzyAnalysisKeyword(), IntelligentSearchIndexField.getFuzzySuggestionKeywordTermSize())));
    }

    private void analysisKeywordForNormal(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        String rawKeyword = searchFilter.getRawKeyword();
        if (allowSplitKeyword(searchFilter)) {
            String[] split = rawKeyword.split("\\s+");
            StringJoiner stringJoiner = new StringJoiner(" ");
            for (String add : split) {
                stringJoiner.add(add);
            }
            if (!TextUtils.isEmpty(stringJoiner.toString())) {
                arrayList.add(new SelectInfo(appendSelectionMark(searchFilter.getTerm()), stringJoiner.toString()));
                return;
            }
            return;
        }
        String term = searchFilter.getTerm();
        if (supportSelectionOrQuery(term)) {
            String[] split2 = rawKeyword.split("\u001f");
            if (split2.length > 1) {
                arrayList.add(new SelectInfo(makeOrSelection(term, split2.length), (ArrayList<String>) new ArrayList(Arrays.asList(split2))));
            } else {
                arrayList.add(new SelectInfo(appendSelectionMark(term), rawKeyword));
            }
        } else {
            arrayList.add(new SelectInfo(appendSelectionMark(term), rawKeyword));
        }
    }

    private void analysisKeywordForShotMode(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        ShotModeFilterResultsEntity shotModeFilterResultsEntity = new ShotModeFilterResultsEntity(searchFilter.getTerm(), searchFilter.getRawKeyword());
        arrayList.add(new SelectInfo(shotModeFilterResultsEntity.getSelection(), shotModeFilterResultsEntity.getRawLabelsList()));
    }

    private void analysisSelectedFilter(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        String selectedFilter = searchFilter.getSelectedFilter();
        if (TextUtils.isEmpty(selectedFilter)) {
            return;
        }
        if (!PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD || !searchFilter.isKeywordSuggestion()) {
            try {
                if (PreferenceFeatures.OneUi5x.SUPPORT_SEARCH_MULTIPLE_KEYWORD) {
                    JSONArray jSONArray = new JSONArray(selectedFilter);
                    int length = jSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        ArrayList<String> extractSelectionArgs = extractSelectionArgs(jSONObject);
                        String extractSelection = extractSelection(jSONObject);
                        if (extractSelection != null) {
                            if (extractSelection.contains("recommended_id")) {
                                String[] split = extractSelectionArgs.get(0).split("\u001f");
                                if (split.length > 1) {
                                    arrayList.add(new SelectInfo(makeOrSelection(extractSelection, split.length), (ArrayList<String>) new ArrayList(Arrays.asList(split))));
                                    return;
                                }
                            }
                            arrayList.add(new SelectInfo(extractSelection, extractSelectionArgs));
                        }
                    }
                    return;
                }
                JSONObject jSONObject2 = new JSONObject(selectedFilter);
                arrayList.add(new SelectInfo(extractSelection(jSONObject2), extractSelectionArgs(jSONObject2)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String appendSelectionMark(String str) {
        if (str == null || str.endsWith("=?")) {
            return str;
        }
        return str.concat("=?");
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

    private ArrayList<String> getKeywordSelectionArgs(String str, int i2) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i7 = 0; i7 < i2; i7++) {
            arrayList.add(str);
        }
        return arrayList;
    }

    private String getTagKeyword(String str) {
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return str;
        }
        if (str.length() > 1) {
            return str.substring(1);
        }
        return null;
    }

    private String makeOrSelection(String str, int i2) {
        ArrayList arrayList = new ArrayList();
        boolean contains = str.contains("?");
        for (int i7 = 0; i7 < i2; i7++) {
            if (!contains) {
                arrayList.add(str.concat("=?"));
            } else {
                arrayList.add(str);
            }
        }
        return "(" + TextUtils.join(" OR ", arrayList) + ")";
    }

    private void pullOutResults(ArrayList<SelectInfo> arrayList, SearchFilter searchFilter) {
        StringJoiner stringJoiner = new StringJoiner(" and ");
        ArrayList arrayList2 = new ArrayList();
        Iterator<SelectInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            SelectInfo next = it.next();
            stringJoiner.add(next.getSelection());
            arrayList2.addAll(next.getSelectionArgs());
        }
        if (Features.isEnabled(Features.SUPPORT_PDC_CLUSTER) && SmartSuggestionsSettingApi.getInstance().isAppPdcAvailability()) {
            String pdcToken = searchFilter.getPdcToken();
            if (!TextUtils.isEmpty(pdcToken)) {
                stringJoiner.add("pdc_token=?");
                arrayList2.add(pdcToken);
            }
        }
        this.mSelection = stringJoiner.toString();
        this.mSelectionArgs = (String[]) arrayList2.toArray(new String[0]);
    }

    private boolean supportSelectionOrQuery(String str) {
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return false;
        }
        if ((!SUPPORT_ALBUM_PICTURES_SEARCH || !"bucket_id".equals(str)) && !"recommended_id".equals(str)) {
            return false;
        }
        return true;
    }

    public String getSelection() {
        return this.mSelection;
    }

    public String[] getSelectionArgs() {
        return this.mSelectionArgs;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SelectInfo {
        private final String mSelection;
        private final ArrayList<String> mSelectionArgs;

        public SelectInfo(String str, ArrayList<String> arrayList) {
            this.mSelection = str;
            this.mSelectionArgs = arrayList;
        }

        public String getSelection() {
            return this.mSelection;
        }

        public ArrayList<String> getSelectionArgs() {
            return this.mSelectionArgs;
        }

        public SelectInfo(String str, String str2) {
            this(str, (ArrayList<String>) new ArrayList(Collections.singletonList(str2)));
        }
    }
}
