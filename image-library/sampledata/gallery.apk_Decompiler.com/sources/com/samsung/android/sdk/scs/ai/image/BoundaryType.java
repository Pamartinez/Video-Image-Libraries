package com.samsung.android.sdk.scs.ai.image;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum BoundaryType {
    UNKNOWN(0),
    IMAGE(1),
    TEXT(2),
    OUTER_BLOCK(3);
    
    private static final Map<Integer, BoundaryType> mEnumMap = null;
    private int mType;

    static {
        int i2;
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (BoundaryType boundaryType : values()) {
            concurrentHashMap.put(Integer.valueOf(boundaryType.getType()), boundaryType);
        }
        mEnumMap = Collections.unmodifiableMap(concurrentHashMap);
    }

    private BoundaryType(int i2) {
        this.mType = i2;
    }

    public static BoundaryType get(int i2) {
        return mEnumMap.get(Integer.valueOf(i2));
    }

    public int getType() {
        return this.mType;
    }
}
