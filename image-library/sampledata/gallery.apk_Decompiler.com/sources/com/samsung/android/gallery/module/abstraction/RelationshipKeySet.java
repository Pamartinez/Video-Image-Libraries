package com.samsung.android.gallery.module.abstraction;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.moneta.basicdomain.entity.RelationShip;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RelationshipKeySet {
    public static final HashMap<String, RelationShip> PDC_RELATIONSHIP_MAP = new HashMap() {
        {
            put("me", RelationShip.UNKNOWN);
            put("wife", RelationShip.WIFE);
            put("husband", RelationShip.HUSBAND);
            put("mother", RelationShip.MOTHER);
            put("father", RelationShip.FATHER);
            put("daughter", RelationShip.DAUGHTER);
            put("son", RelationShip.SON);
            put("older_sister_male", RelationShip.OLDER_SISTER_MALE);
            put("older_sister_female", RelationShip.OLDER_SISTER_FEMALE);
            put("younger_sister", RelationShip.YOUNGER_SISTER);
            put("older_brother_male", RelationShip.OLDER_BROTHER_MALE);
            put("older_brother_female", RelationShip.OLDER_BROTHER_FEMALE);
            put("younger_brother", RelationShip.YOUNGER_BROTHER);
            put("paternal_grandmother", RelationShip.PATERNAL_GRANDMOTHER);
            put("maternal_grandmother", RelationShip.MATERNAL_GRANDMOTHER);
            put("paternal_grandfather", RelationShip.PATERNAL_GRANDFATHER);
            put("maternal_grandfather", RelationShip.MATERNAL_GRANDFATHER);
            put("motherinlaw_husbandsmother", RelationShip.MOTHER_IN_LAW_HUSBAND);
            put("motherinlaw_wifesmother", RelationShip.MOTHER_IN_LAW_WIFE);
            put("fatherinlaw_husbandsfather", RelationShip.FATHER_IN_LAW_HUSBAND);
            put("fatherinlaw_wifesfather", RelationShip.FATHER_IN_LAW_WIFE);
            put("daughterinlaw", RelationShip.DAUGHTER_IN_LAW);
            put("soninlaw", RelationShip.SON_IN_LAW);
        }
    };
    public static final LinkedHashMap<String, Integer> RELATIONSHIP_MAP = new LinkedHashMap() {
        {
            if (PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE) {
                put("me", Integer.valueOf(R$string.relationship_me));
                put("mother", Integer.valueOf(R$string.mother));
                put("father", Integer.valueOf(R$string.father));
                put("parents", Integer.valueOf(R$string.parents));
                put("older_sister_female", Integer.valueOf(R$string.older_sister_female));
                put("older_brother_female", Integer.valueOf(R$string.older_brother_female));
                put("older_sister_male", Integer.valueOf(R$string.older_sister_male));
                put("older_brother_male", Integer.valueOf(R$string.older_brother_male));
                put("younger_sister", Integer.valueOf(R$string.younger_sister));
                put("younger_brother", Integer.valueOf(R$string.younger_brother));
                put("wife", Integer.valueOf(R$string.wife));
                put("husband", Integer.valueOf(R$string.husband));
                put("spouse", Integer.valueOf(R$string.spouse));
                put("daughter", Integer.valueOf(R$string.daughter));
                put("son", Integer.valueOf(R$string.son));
                return;
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP) {
                put("me", Integer.valueOf(R$string.relationship_me));
            }
            put("wife", Integer.valueOf(R$string.wife));
            put("husband", Integer.valueOf(R$string.husband));
            put("mother", Integer.valueOf(R$string.mother));
            put("father", Integer.valueOf(R$string.father));
            put("daughter", Integer.valueOf(R$string.daughter));
            put("son", Integer.valueOf(R$string.son));
            put("older_sister_male", Integer.valueOf(R$string.older_sister_male));
            put("older_sister_female", Integer.valueOf(R$string.older_sister_female));
            put("younger_sister", Integer.valueOf(R$string.younger_sister));
            put("older_brother_male", Integer.valueOf(R$string.older_brother_male));
            put("older_brother_female", Integer.valueOf(R$string.older_brother_female));
            put("younger_brother", Integer.valueOf(R$string.younger_brother));
            put("paternal_grandmother", Integer.valueOf(R$string.paternal_grandmother));
            put("maternal_grandmother", Integer.valueOf(R$string.maternal_grandmother));
            put("paternal_grandfather", Integer.valueOf(R$string.paternal_grandfather));
            put("maternal_grandfather", Integer.valueOf(R$string.maternal_grandfather));
            put("motherinlaw_husbandsmother", Integer.valueOf(R$string.mother_in_law_husband));
            put("motherinlaw_wifesmother", Integer.valueOf(R$string.mother_in_law_wife));
            put("fatherinlaw_husbandsfather", Integer.valueOf(R$string.father_in_law_husband));
            put("fatherinlaw_wifesfather", Integer.valueOf(R$string.father_in_law_wife));
            put("daughterinlaw", Integer.valueOf(R$string.daughter_in_law));
            put("soninlaw", Integer.valueOf(R$string.son_in_law));
        }
    };
    public static final LinkedHashMap<String, Integer> RELATIONSHIP_PRESET_LEGACY_MAP = new LinkedHashMap() {
        {
            if (PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE) {
                put("paternal_grandmother", Integer.valueOf(R$string.paternal_grandmother));
                put("maternal_grandmother", Integer.valueOf(R$string.maternal_grandmother));
                put("paternal_grandfather", Integer.valueOf(R$string.paternal_grandfather));
                put("maternal_grandfather", Integer.valueOf(R$string.maternal_grandfather));
                put("motherinlaw_husbandsmother", Integer.valueOf(R$string.mother_in_law_husband));
                put("motherinlaw_wifesmother", Integer.valueOf(R$string.mother_in_law_wife));
                put("fatherinlaw_husbandsfather", Integer.valueOf(R$string.father_in_law_husband));
                put("fatherinlaw_wifesfather", Integer.valueOf(R$string.father_in_law_wife));
                put("daughterinlaw", Integer.valueOf(R$string.daughter_in_law));
                put("soninlaw", Integer.valueOf(R$string.son_in_law));
                put("child", Integer.valueOf(R$string.child));
            }
        }
    };

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IgnoreRelationshipHolder {
        static final ArrayList<String> list = new ArrayList<>(List.of("family", "parents", "spouse"));
    }

    public static String getRelationshipName(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            Log.e("RelationshipKeySet", "Fail to get relationship name -> [type]: " + str + " [context]: " + context);
            return null;
        } else if (PreferenceFeatures.OneUi6x.SUPPORT_CUSTOM_RELATIONSHIP && CustomRelationshipKeySet.isCustomRelationshipType(str)) {
            return CustomRelationshipKeySet.getInstance().getName(str);
        } else {
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            StringJoiner stringJoiner = new StringJoiner("/");
            for (String str2 : split) {
                Integer num = RELATIONSHIP_MAP.get(str2);
                if (num == null && PreferenceFeatures.OneUi7x.REDUCED_RELATIONSHIP_TYPE) {
                    num = RELATIONSHIP_PRESET_LEGACY_MAP.get(str2);
                }
                if (num != null) {
                    String string = context.getString(num.intValue());
                    if (!stringJoiner.toString().contains(string)) {
                        stringJoiner.add(string);
                    }
                }
            }
            return stringJoiner.toString();
        }
    }

    public static String getRelationshipTypes(String str) {
        try {
            return getRelationshipTypesInternal(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getRelationshipTypesInternal(JSONObject jSONObject) {
        StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("type");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                stringJoiner.add(jSONArray.getString(i2));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stringJoiner.toString();
    }

    public static String getValidRelationshipData(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONArray jSONArray2 = new JSONArray(str);
            boolean z = false;
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                JSONArray jSONArray3 = jSONObject.getJSONArray("type");
                int i7 = 0;
                while (true) {
                    if (i7 >= jSONArray3.length()) {
                        break;
                    } else if (IgnoreRelationshipHolder.list.contains(jSONArray3.getString(i7))) {
                        z = true;
                        break;
                    } else {
                        i7++;
                    }
                }
                if (z) {
                    z = false;
                } else {
                    jSONArray.put(jSONObject);
                }
            }
            if (jSONArray.length() > 0) {
                return jSONArray.toString();
            }
            return null;
        } catch (JSONException e) {
            Log.w("RelationshipKeySet", "getValidRelationshipData " + e);
            return null;
        }
    }

    public static boolean hasPredefinedRelationshipType(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String containsKey : str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            if (RELATIONSHIP_MAP.containsKey(containsKey)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> parseJsonRelationship(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONArray(str);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.getJSONObject(i2).toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("RelationshipKeySet", "parseJsonRelationship : " + Logger.getEncodedString((Object) arrayList));
        return arrayList;
    }

    public static String[] parseRelationshipEntry(String str) {
        return parseRelationshipEntry(str, false);
    }

    public static String parseRelationshipType(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                return getRelationshipTypesInternal(new JSONArray(str).getJSONObject(0));
            }
            return str;
        } catch (JSONException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String[] parseRelationshipEntry(String str, boolean z) {
        JSONObject jSONObject;
        if (z) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            jSONObject = new JSONArray(str).getJSONObject(0);
        }
        return new String[]{getRelationshipTypesInternal(jSONObject), jSONObject.getString(Contract.QUERY)};
    }
}
