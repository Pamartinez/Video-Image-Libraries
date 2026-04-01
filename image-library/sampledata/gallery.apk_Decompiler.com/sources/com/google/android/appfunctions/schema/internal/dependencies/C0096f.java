package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0096f {
    DOUBLE(0, 1, r6),
    FLOAT(1, 1, r12),
    INT64(2, 1, r19),
    UINT64(3, 1, r19),
    INT32(4, 1, r27),
    FIXED64(5, 1, r19),
    FIXED32(6, 1, r27),
    BOOL(7, 1, r36),
    STRING(8, 1, r43),
    MESSAGE(9, 1, r49),
    BYTES(10, 1, r56),
    UINT32(11, 1, r27),
    ENUM(12, 1, r65),
    SFIXED32(13, 1, r27),
    SFIXED64(14, 1, r19),
    SINT32(15, 1, r27),
    SINT64(16, 1, r19),
    GROUP(17, 1, r49),
    DOUBLE_LIST(18, 2, r6),
    FLOAT_LIST(19, 2, r12),
    INT64_LIST(20, 2, r19),
    UINT64_LIST(21, 2, r19),
    INT32_LIST(22, 2, r27),
    FIXED64_LIST(23, 2, r19),
    FIXED32_LIST(24, 2, r27),
    BOOL_LIST(25, 2, r36),
    STRING_LIST(26, 2, r43),
    MESSAGE_LIST(27, 2, r49),
    BYTES_LIST(28, 2, r56),
    UINT32_LIST(29, 2, r27),
    ENUM_LIST(30, 2, r65),
    SFIXED32_LIST(31, 2, r27),
    SFIXED64_LIST(32, 2, r19),
    SINT32_LIST(33, 2, r27),
    SINT64_LIST(34, 2, r19),
    DOUBLE_LIST_PACKED(35, 3, r6),
    FLOAT_LIST_PACKED(36, 3, r12),
    INT64_LIST_PACKED(37, 3, r19),
    UINT64_LIST_PACKED(38, 3, r19),
    INT32_LIST_PACKED(39, 3, r27),
    FIXED64_LIST_PACKED(40, 3, r19),
    FIXED32_LIST_PACKED(41, 3, r27),
    BOOL_LIST_PACKED(42, 3, r36),
    UINT32_LIST_PACKED(43, 3, r27),
    ENUM_LIST_PACKED(44, 3, r65),
    SFIXED32_LIST_PACKED(45, 3, r27),
    SFIXED64_LIST_PACKED(46, 3, r19),
    SINT32_LIST_PACKED(47, 3, r27),
    SINT64_LIST_PACKED(48, 3, r19),
    GROUP_LIST(49, 2, r49),
    MAP(50, 4, C0108s.VOID);
    
    private static final C0096f[] zzaa = null;
    private final int zzZ;

    static {
        zzaa = new C0096f[r1];
        for (C0096f fVar : values()) {
            zzaa[fVar.zzZ] = fVar;
        }
    }

    /* access modifiers changed from: public */
    C0096f(int i2, int i7, C0108s sVar) {
        this.zzZ = i2;
        int i8 = i7 - 1;
        if (i8 == 1) {
            sVar.getClass();
        } else if (i8 == 3) {
            sVar.getClass();
        }
        if (i7 == 1) {
            C0108s sVar2 = C0108s.VOID;
            sVar.ordinal();
        }
    }

    public final int a() {
        return this.zzZ;
    }
}
