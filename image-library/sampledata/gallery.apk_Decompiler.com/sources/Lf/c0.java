package lf;

import rf.C1268s;
import rf.r;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum c0 implements r {
    LANGUAGE_VERSION(0),
    COMPILER_VERSION(1),
    API_VERSION(2);
    
    private static C1268s internalValueMap;
    private final int value;

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, rf.s] */
    static {
        internalValueMap = new Object();
    }

    /* access modifiers changed from: public */
    c0(int i2) {
        this.value = i2;
    }

    public final int a() {
        return this.value;
    }
}
