package p1;

import com.google.android.appfunctions.schema.internal.dependencies.C0104n;

/* renamed from: p1.d  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0256d {
    TYPE_UNSPECIFIED(0),
    IMAGE(1),
    VIDEO(2),
    UNRECOGNIZED(-1);
    
    public static final int IMAGE_VALUE = 1;
    public static final int TYPE_UNSPECIFIED_VALUE = 0;
    public static final int VIDEO_VALUE = 2;
    private static final C0104n internalValueMap = null;
    private final int value;

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.android.appfunctions.schema.internal.dependencies.n, java.lang.Object] */
    static {
        internalValueMap = new Object();
    }

    /* access modifiers changed from: public */
    C0256d(int i2) {
        this.value = i2;
    }

    public final int a() {
        if (this != UNRECOGNIZED) {
            return this.value;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }

    public final String toString() {
        return Integer.toString(this.value);
    }
}
