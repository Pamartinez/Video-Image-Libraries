package kf;

import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.LinkedHashMap;
import java.util.Map;
import ne.z;

/* renamed from: kf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C1116b {
    UNKNOWN(0),
    CLASS(1),
    FILE_FACADE(2),
    SYNTHETIC_CLASS(3),
    MULTIFILE_CLASS(4),
    MULTIFILE_CLASS_PART(5);
    
    public static final C1115a Companion = null;
    /* access modifiers changed from: private */
    public static final Map<Integer, C1116b> entryById = null;
    private final int id;

    /* JADX WARNING: type inference failed for: r0v3, types: [java.lang.Object, kf.a] */
    static {
        int i2;
        C1116b[] bVarArr;
        $ENTRIES = c.t(bVarArr);
        Companion = new Object();
        C1116b[] values = values();
        int Z = z.Z(values.length);
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        for (C1116b bVar : values) {
            linkedHashMap.put(Integer.valueOf(bVar.id), bVar);
        }
        entryById = linkedHashMap;
    }

    /* access modifiers changed from: public */
    C1116b(int i2) {
        this.id = i2;
    }
}
