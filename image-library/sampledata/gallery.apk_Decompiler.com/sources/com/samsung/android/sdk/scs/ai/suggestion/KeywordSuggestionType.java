package com.samsung.android.sdk.scs.ai.suggestion;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum KeywordSuggestionType {
    MEDIA("gallery");
    
    private static final Map<String, KeywordSuggestionType> mEnumMap = null;
    private final String mSuggestionType;

    static {
        int i2;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (KeywordSuggestionType keywordSuggestionType : values()) {
            concurrentHashMap.put(keywordSuggestionType.getType(), keywordSuggestionType);
        }
        mEnumMap = Collections.unmodifiableMap(concurrentHashMap);
    }

    private KeywordSuggestionType(String str) {
        this.mSuggestionType = str;
    }

    public static KeywordSuggestionType get(String str) {
        return mEnumMap.get(str);
    }

    public String getType() {
        return this.mSuggestionType;
    }
}
