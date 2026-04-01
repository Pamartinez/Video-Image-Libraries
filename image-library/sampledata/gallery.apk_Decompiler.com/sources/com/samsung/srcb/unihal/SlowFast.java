package com.samsung.srcb.unihal;

import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlowFast {
    public static final int ACTION_TYPE_FAST = 2;
    public static final int ACTION_TYPE_MEDIUM = 3;
    public static final int ACTION_TYPE_SLOW = 4;
    public static final int ACTION_TYPE_VERY_FAST = 1;
    public static final int ACTION_TYPE_VERY_SLOW = 5;
    public static final Map<Integer, String> EMap;

    static {
        HashMap hashMap = new HashMap();
        EMap = hashMap;
        hashMap.put(1, "very fast");
        hashMap.put(2, "fast");
        hashMap.put(3, "medium");
        hashMap.put(4, "slow");
        hashMap.put(5, "very slow");
    }

    public static int getTypeIdByName(String str) {
        for (Integer next : EMap.keySet()) {
            int intValue = next.intValue();
            if (EMap.get(next).equals(str)) {
                return intValue;
            }
        }
        return -1;
    }

    public static String getTypeNameById(int i2) {
        return EMap.get(Integer.valueOf(i2));
    }
}
