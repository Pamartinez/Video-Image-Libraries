package com.samsung.scsp.framework.core.api;

import com.samsung.scsp.error.FaultBarrier;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SCHashMap extends HashMap<String, Object> {
    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$getAsBoolean$2(Object obj) {
        if (obj instanceof CharSequence) {
            return Boolean.valueOf(obj.toString());
        }
        if (obj != null) {
            return (Boolean) obj;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$getAsInteger$1(Object obj) {
        if (obj instanceof CharSequence) {
            return Integer.valueOf(obj.toString());
        }
        if (obj != null) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Long lambda$getAsLong$0(Object obj) {
        if (obj instanceof CharSequence) {
            return Long.valueOf(obj.toString());
        }
        if (obj != null) {
            return Long.valueOf(((Number) obj).longValue());
        }
        return null;
    }

    public Boolean getAsBoolean(String str) {
        return (Boolean) FaultBarrier.get(new e(2, get(str)), null).obj;
    }

    public Integer getAsInteger(String str) {
        return (Integer) FaultBarrier.get(new e(0, get(str)), null).obj;
    }

    public Long getAsLong(String str) {
        return (Long) FaultBarrier.get(new e(1, get(str)), null).obj;
    }

    public String getAsString(String str) {
        Object obj = get(str);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }
}
