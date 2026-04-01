package com.google.protobuf;

import java.lang.reflect.Type;

/* renamed from: com.google.protobuf.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum C0150w {
    DOUBLE(0, r4, r11),
    FLOAT(1, r5, r17),
    INT64(2, r5, r6),
    UINT64(3, r5, r6),
    INT32(4, r5, r32),
    FIXED64(5, r5, r24),
    FIXED32(6, r5, r32),
    BOOL(7, r5, r41),
    STRING(8, r5, r48),
    MESSAGE(9, r5, r6),
    BYTES(10, r5, r6),
    UINT32(11, r5, r32),
    ENUM(12, r5, r70),
    SFIXED32(13, r5, r32),
    SFIXED64(14, r5, r24),
    SINT32(15, r5, r32),
    SINT64(16, r5, r24),
    GROUP(17, r5, r55),
    DOUBLE_LIST(18, r23, r11),
    FLOAT_LIST(19, r23, r17),
    INT64_LIST(20, r23, r24),
    UINT64_LIST(21, r23, r24),
    INT32_LIST(22, r31, r32),
    FIXED64_LIST(23, r23, r24),
    FIXED32_LIST(24, r31, r32),
    BOOL_LIST(25, r23, r41),
    STRING_LIST(26, r23, r48),
    MESSAGE_LIST(27, r54, r55),
    BYTES_LIST(28, r23, r6),
    UINT32_LIST(29, r31, r32),
    ENUM_LIST(30, r23, r70),
    SFIXED32_LIST(31, r31, r32),
    SFIXED64_LIST(32, r23, r24),
    SINT32_LIST(33, r31, r32),
    SINT64_LIST(34, r23, r24),
    DOUBLE_LIST_PACKED(35, r23, r11),
    FLOAT_LIST_PACKED(36, r23, r17),
    INT64_LIST_PACKED(37, r23, r24),
    UINT64_LIST_PACKED(38, r23, r24),
    INT32_LIST_PACKED(39, r31, r32),
    FIXED64_LIST_PACKED(40, r23, r24),
    FIXED32_LIST_PACKED(41, r31, r32),
    BOOL_LIST_PACKED(42, r23, r41),
    UINT32_LIST_PACKED(43, r31, r32),
    ENUM_LIST_PACKED(44, r23, r70),
    SFIXED32_LIST_PACKED(45, r31, r32),
    SFIXED64_LIST_PACKED(46, r23, r24),
    SINT32_LIST_PACKED(47, r31, r32),
    SINT64_LIST_PACKED(48, r23, r24),
    GROUP_LIST(49, r54, r55),
    MAP(50, C0149v.MAP, G.VOID);
    
    private static final Type[] EMPTY_TYPES = null;
    private static final C0150w[] VALUES = null;
    private final C0149v collection;
    private final Class<?> elementType;
    private final int id;
    private final G javaType;
    private final boolean primitiveScalar;

    static {
        EMPTY_TYPES = new Type[0];
        C0150w[] values = values();
        VALUES = new C0150w[values.length];
        for (C0150w wVar : values) {
            VALUES[wVar.id] = wVar;
        }
    }

    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r1 = com.google.protobuf.C0148u.b[r5.ordinal()];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    C0150w(int r3, com.google.protobuf.C0149v r4, com.google.protobuf.G r5) {
        /*
            r0 = this;
            r0.<init>(r1, r2)
            r0.id = r3
            r0.collection = r4
            r0.javaType = r5
            int[] r1 = com.google.protobuf.C0148u.f1631a
            int r2 = r4.ordinal()
            r1 = r1[r2]
            r2 = 2
            r3 = 1
            if (r1 == r3) goto L_0x0022
            if (r1 == r2) goto L_0x001b
            r1 = 0
            r0.elementType = r1
            goto L_0x0028
        L_0x001b:
            java.lang.Class r1 = r5.a()
            r0.elementType = r1
            goto L_0x0028
        L_0x0022:
            java.lang.Class r1 = r5.a()
            r0.elementType = r1
        L_0x0028:
            com.google.protobuf.v r1 = com.google.protobuf.C0149v.SCALAR
            if (r4 != r1) goto L_0x003c
            int[] r1 = com.google.protobuf.C0148u.b
            int r4 = r5.ordinal()
            r1 = r1[r4]
            if (r1 == r3) goto L_0x003c
            if (r1 == r2) goto L_0x003c
            r2 = 3
            if (r1 == r2) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r3 = 0
        L_0x003d:
            r0.primitiveScalar = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.C0150w.<init>(java.lang.String, int, int, com.google.protobuf.v, com.google.protobuf.G):void");
    }

    public final int a() {
        return this.id;
    }
}
