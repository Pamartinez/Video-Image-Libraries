package com.samsung.android.gallery.module.search.filter;

import X9.a;
import android.text.TextUtils;
import com.samsung.android.gallery.module.search.root.FilterResultsEntity;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.module.search.root.PeopleFilterResultsEntity;
import com.samsung.android.gallery.module.search.root.PetFilterResultsEntity;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SearchFilterUtil {
    public static String addSelectedFilter(String str, FilterResultsEntity filterResultsEntity) {
        JSONArray jSONArray;
        try {
            if (!TextUtils.isEmpty(str)) {
                jSONArray = new JSONArray(str);
            } else {
                jSONArray = new JSONArray();
            }
            jSONArray.put(getSelectedFilterObject(filterResultsEntity));
            Log.s("SearchFilterUtil", "addSelectedFilter : total filter [" + jSONArray.length() + "]");
            return jSONArray.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<FilterResultsEntity> getFilterEntities(String str) {
        FilterResultsEntity filterResultsEntity;
        ArrayList<FilterResultsEntity> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                String string = jSONObject.getString("name");
                String string2 = jSONObject.getString("selection");
                String string3 = jSONObject.getJSONArray("selection_args").getString(0);
                String replace = string2.replace(" = ? ", "");
                if ("recommended_id".equals(replace)) {
                    filterResultsEntity = new PeopleFilterResultsEntity(string3, "recommended_id");
                } else if ("pet_recommended_id".equals(replace)) {
                    filterResultsEntity = new PetFilterResultsEntity(string3, "pet_recommended_id");
                } else {
                    FilterResultsEntity filterResultsEntity2 = new FilterResultsEntity(string, IntelligentSearchIndexFieldIcon.create(replace, string), replace);
                    filterResultsEntity2.addRawLabel(string3);
                    filterResultsEntity = filterResultsEntity2;
                }
                arrayList.add(filterResultsEntity);
            }
            return arrayList;
        } catch (JSONException e) {
            Log.e("SearchFilterUtil", e.getMessage());
            return arrayList;
        }
    }

    public static String getSelectedFilter(Blackboard blackboard) {
        return ArgumentsUtil.getArgValue(BlackboardUtils.readLocationKeyCurrent(blackboard), "SelectedFilter");
    }

    private static JSONObject getSelectedFilterObject(FilterResultsEntity filterResultsEntity) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", filterResultsEntity.getName());
            jSONObject.put("selection", filterResultsEntity.getSelection());
            jSONObject.put("selection_args", filterResultsEntity.getSelectionArgs());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private static String removeLastKeyword(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length() - 1;
            if (jSONArray.getJSONObject(length).getString("name").equals(str2)) {
                jSONArray.remove(length);
            }
            if (jSONArray.length() > 0) {
                return jSONArray.toString();
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String removeSelectedFilter(String str, FilterResultsEntity filterResultsEntity) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                }
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (filterResultsEntity.getSelection().equals(jSONObject.getString("selection")) && filterResultsEntity.getSelectionArgs().toString().equals(jSONObject.getString("selection_args"))) {
                    jSONArray.remove(i2);
                    break;
                }
                i2++;
            }
            if (jSONArray.length() > 0) {
                return jSONArray.toString();
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String replaceFilterToFuzzyOnLocationKey(String str, FilterResultsEntity filterResultsEntity, String str2) {
        return ArgumentsUtil.removeArg(ArgumentsUtil.appendArgs(ArgumentsUtil.removeArg(str, "SelectedFilter"), "SelectedFilter", addSelectedFilter(removeLastKeyword(ArgumentsUtil.getArgValue(str, "SelectedFilter"), str2), filterResultsEntity)), "fuzzyKeyword");
    }

    public static String updateSelectedFilter(String str, FilterResultsEntity filterResultsEntity, boolean z) {
        if (str != null) {
            try {
                ArrayList arrayList = new ArrayList();
                JSONArray jSONArray = new JSONArray(str);
                int i2 = 0;
                boolean z3 = false;
                while (true) {
                    if (i2 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (filterResultsEntity.getSelection().equals(jSONObject.getString("selection")) && filterResultsEntity.getSelectionArgs().toString().equals(jSONObject.getString("selection_args"))) {
                        if (!z3) {
                            arrayList.add(Integer.valueOf(i2));
                            z3 = true;
                        }
                        if (z) {
                            break;
                        }
                    } else if (!z && jSONObject.getString("selection").contains("face_count")) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                    i2++;
                }
                arrayList.stream().sorted(Collections.reverseOrder()).forEach(new a(jSONArray, 0));
                if (jSONArray.length() > 0) {
                    return jSONArray.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String removeLastKeyword(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            jSONArray.remove(jSONArray.length() - 1);
            if (jSONArray.length() > 0) {
                return jSONArray.toString();
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
