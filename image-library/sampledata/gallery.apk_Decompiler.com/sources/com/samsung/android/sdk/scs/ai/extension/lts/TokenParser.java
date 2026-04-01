package com.samsung.android.sdk.scs.ai.extension.lts;

import android.util.Log;
import i.C0212a;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TokenParser {
    private static final String COUNT_KEY = "count";
    private static final String COUNT_MAX_KEY = "countMax";
    private static final String DIGIT_PATTERN = "\\d+";
    private static final String KEY_PATTERN_FORMAT = "\"%s\":%s%s%s";
    private static final String NEGATIVE_NUMBER_PATTERN = "-(\\d+)";
    private static final String NEGATIVE_SIGN = "-";
    private static final String[] PATTERNS = {"\"count\":\"(\\d+)\"", "\"count\":\"-(\\d+)\"", "\"countMax\":\"(\\d+)\"", "\"countMax\":\"-(\\d+)\""};
    private static final String POSITIVE_PATTERN = "(\\d+)";
    private static final String QUOTE = "\"";
    private static final String TAG = "TokenParser";

    private static Map.Entry<String, String> calculateTokenValue(String str, String str2) {
        String str3;
        String str4 = COUNT_MAX_KEY;
        if (!str.contains(str4)) {
            str4 = "count";
        }
        if (str.contains(NEGATIVE_NUMBER_PATTERN)) {
            str3 = NEGATIVE_SIGN;
        } else {
            str3 = "";
        }
        Log.i(TAG, str4 + " : " + str3 + str2);
        return new AbstractMap.SimpleEntry(str4, C0212a.A(str3, str2));
    }

    public static Map<String, String> getToken(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : PATTERNS) {
            String parseToken = parseToken(str, str2);
            if (!parseToken.isEmpty()) {
                Map.Entry<String, String> calculateTokenValue = calculateTokenValue(str2, parseToken);
                hashMap.put(calculateTokenValue.getKey(), calculateTokenValue.getValue());
            }
        }
        return hashMap;
    }

    private static String parseToken(String str, String str2) {
        Matcher matcher = Pattern.compile(str2).matcher(str);
        if (matcher.find()) {
            String group = matcher.group(1);
            Log.i(TAG, group);
            return group;
        }
        Log.i(TAG, "No match found : " + str2);
        return "";
    }
}
