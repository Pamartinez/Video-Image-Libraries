package com.samsung.android.gallery.database.dbtype;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchSelectedFilter {
    private String mContent;

    public SearchSelectedFilter(String str) {
        this.mContent = str;
    }

    public String getContent() {
        return this.mContent;
    }

    public LinkedHashSet<String> getCreatureKeys() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        if (!TextUtils.isEmpty(this.mContent)) {
            try {
                JSONArray jSONArray = new JSONArray(this.mContent);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    if (jSONObject.getString("selection").contains("recommended_id")) {
                        linkedHashSet.add(jSONObject.getJSONArray("selection_args").getString(0));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return linkedHashSet;
    }

    public LinkedHashSet<String> getFilterSelections() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        if (!TextUtils.isEmpty(this.mContent)) {
            try {
                JSONArray jSONArray = new JSONArray(this.mContent);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    linkedHashSet.add(jSONArray.getJSONObject(i2).getString("selection"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return linkedHashSet;
    }

    public String getLastKeyword() {
        if (TextUtils.isEmpty(this.mContent)) {
            return null;
        }
        try {
            JSONArray jSONArray = new JSONArray(this.mContent);
            JSONObject jSONObject = jSONArray.getJSONObject(jSONArray.length() - 1);
            if (jSONObject.getString("selection").contains("key_word")) {
                return jSONObject.getString("name");
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean hasKeyword() {
        if (!TextUtils.isEmpty(this.mContent)) {
            try {
                JSONArray jSONArray = new JSONArray(this.mContent);
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    if (jSONArray.getJSONObject(i2).getString("selection").contains("key_word")) {
                        return true;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void replaceLastKeyword(String str) {
        String str2;
        if (!TextUtils.isEmpty(this.mContent)) {
            try {
                JSONArray jSONArray = new JSONArray(this.mContent);
                JSONObject jSONObject = jSONArray.getJSONObject(jSONArray.length() - 1);
                if (jSONObject.getString("selection").contains("key_word")) {
                    jSONObject.put("name", str);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(str);
                    jSONObject.put("selection_args", new JSONArray(arrayList));
                }
                if (jSONArray.length() > 0) {
                    str2 = jSONArray.toString();
                } else {
                    str2 = null;
                }
                this.mContent = str2;
            } catch (Exception e) {
                e.printStackTrace();
                this.mContent = null;
            }
        }
    }
}
