package com.samsung.android.gallery.module.myquery;

import android.text.TextUtils;
import com.samsung.android.gallery.module.myquery.SearchMyQuery;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MyQueryFilterDataUtil {
    public static String composeFilterData(String str, String str2, String str3, String str4, String str5, String str6) {
        try {
            JSONObject jSONObject = new JSONObject();
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            jSONObject.put("mainFilterTitle", str);
            if (str2 == null) {
                str2 = "";
            }
            jSONObject.put("mainCategory", str2);
            jSONObject.put("subCategory", str3);
            jSONObject.put("selectedFilter", str4);
            jSONObject.put("term", str5);
            jSONObject.put("people_only_them", str6);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> getCreatureIdentityInfoList(String str, String str2, String str3) {
        IdentityCreatureUtil.Category category;
        ArrayList<String> arrayList = new ArrayList<>();
        if (TextUtils.equals(str, "People") || TextUtils.equals(str, "Pet")) {
            arrayList.add(str2);
            try {
                JSONArray jSONArray = new JSONArray(str3);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i2);
                    String replace = jSONObject.getString("selection").replace(" = ? ", "");
                    if (!TextUtils.equals(replace, "recommended_id")) {
                        if (!TextUtils.equals(replace, "pet_recommended_id")) {
                        }
                    }
                    long parseLong = Long.parseLong(jSONObject.getJSONArray("selection_args").getString(0));
                    if (TextUtils.equals(replace, "recommended_id")) {
                        category = IdentityCreatureUtil.Category.PEOPLE;
                    } else {
                        category = IdentityCreatureUtil.Category.PET;
                    }
                    arrayList.add(IdentityCreatureUtil.createWithUnifiedId(parseLong, category));
                }
            } catch (JSONException e) {
                Log.e("MyQueryFilterDataUtil", e.getMessage());
                return arrayList;
            }
        }
        return arrayList;
    }

    public static SearchMyQuery.MyQueryData parse(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new SearchMyQuery.MyQueryData(jSONObject.optString("mainFilterTitle"), jSONObject.optString("mainCategory"), jSONObject.optString("subCategory"), jSONObject.optString("selectedFilter"), jSONObject.optString("term"), jSONObject.optString("people_only_them"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
