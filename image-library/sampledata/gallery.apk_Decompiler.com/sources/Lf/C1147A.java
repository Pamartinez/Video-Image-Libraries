package lf;

import rf.C1268s;
import rf.r;

/* renamed from: lf.A  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1147A implements r {
    FINAL(0),
    OPEN(1),
    ABSTRACT(2),
    SEALED(3);
    
    private static C1268s internalValueMap;
    private final int value;

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Object, rf.s] */
    static {
        internalValueMap = new Object();
    }

    /* access modifiers changed from: public */
    C1147A(int i2) {
        this.value = i2;
    }

    public final int a() {
        return this.value;
    }
}
