package com.samsung.android.sdk.scs.base.feature;

import Wc.a;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FeatureStatusCache {
    private static final String TAG = "ScsApi@FeatureHolder";
    private static final HashMap<String, Integer> statusMap;

    static {
        HashMap<String, Integer> hashMap = new HashMap<>();
        statusMap = hashMap;
        Integer valueOf = Integer.valueOf(StatusCodes.UNDEFINED);
        hashMap.put(Feature.FEATURE_IMAGE_GET_BOUNDARIES, valueOf);
        hashMap.put(Feature.FEATURE_IMAGE_GET_LARGEST_BOUNDARY, valueOf);
        hashMap.put(Feature.FEATURE_SUGGESTION_SUGGEST_KEYWORD, valueOf);
        hashMap.put(Feature.FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY, valueOf);
        hashMap.put(Feature.FEATURE_SUGGESTION_SUGGEST_APP_CATEGORY_DETAILS, valueOf);
        hashMap.put(Feature.FEATURE_SUGGESTION_SUGGEST_FOLDER_NAME, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_DATETIME_NUMERAL, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_DATETIME_SEARCH, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_PHONE_NUMBER, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_POI, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_BANK, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_IS_MAPPABLE, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_IS_RELATIVE, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_IS_SPECIAL_DAY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_HAS_YEAR_MONTH_DAY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_UPI_ID, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_UNIT, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_START_OF_WEEK, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_ENTITY_LOCAL_DATE_TIME, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_EVENT, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_EVENT_HAS_YEAR_MONTH_DAY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_EVENT_INDEX, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_KEY_PHRASE, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_KEY_PHRASE_EVENT_TITLE, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_DOCUMENT_CATEGORY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_BNLP, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_BNLP_TOKEN, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_BNLP_LIGHT_MODEL, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_DETECT_LANGUAGE, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_CONVERT_UNIT, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_REMINDER_ENTITY, valueOf);
        hashMap.put(Feature.FEATURE_TEXT_GET_EVENT_CATEGORY, valueOf);
        hashMap.put(Feature.FEATURE_NATURAL_LANGUAGE_QUERY, valueOf);
    }

    public static void clear() {
        statusMap.replaceAll(new a(0));
    }

    public static int getStatus(String str) {
        Integer num = statusMap.get(str);
        if (num == null) {
            return StatusCodes.UNDEFINED;
        }
        return num.intValue();
    }

    public static void setStatus(String str, int i2) {
        statusMap.put(str, Integer.valueOf(i2));
        Log.i(TAG, "setStatus() : " + str + " : " + i2);
    }
}
