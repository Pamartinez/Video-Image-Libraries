package com.google.protobuf;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h0 {
    public static UnknownFieldSetLite a(Object obj) {
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        if (unknownFieldSetLite != UnknownFieldSetLite.f) {
            return unknownFieldSetLite;
        }
        UnknownFieldSetLite unknownFieldSetLite2 = new UnknownFieldSetLite();
        generatedMessageLite.unknownFields = unknownFieldSetLite2;
        return unknownFieldSetLite2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d A[LOOP:0: B:16:0x003d->B:19:0x004a, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(java.lang.Object r8, com.google.protobuf.C0141m r9) {
        /*
            int r0 = r9.b
            com.google.protobuf.CodedInputStream r1 = r9.f1619a
            int r2 = r0 >>> 3
            r0 = r0 & 7
            r3 = 0
            r4 = 1
            r5 = 3
            if (r0 == 0) goto L_0x0087
            if (r0 == r4) goto L_0x0073
            r6 = 2
            if (r0 == r6) goto L_0x0066
            if (r0 == r5) goto L_0x0034
            r6 = 4
            if (r0 == r6) goto L_0x0033
            r3 = 5
            if (r0 != r3) goto L_0x002e
            r9.v(r3)
            int r9 = r1.n()
            com.google.protobuf.UnknownFieldSetLite r8 = (com.google.protobuf.UnknownFieldSetLite) r8
            int r0 = r2 << 3
            r0 = r0 | r3
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r8.f(r0, r9)
            return r4
        L_0x002e:
            com.google.protobuf.E r8 = com.google.protobuf.F.c()
            throw r8
        L_0x0033:
            return r3
        L_0x0034:
            com.google.protobuf.UnknownFieldSetLite r0 = new com.google.protobuf.UnknownFieldSetLite
            r0.<init>()
            int r1 = r2 << 3
            r2 = r1 | 4
        L_0x003d:
            int r6 = r9.a()
            r7 = 2147483647(0x7fffffff, float:NaN)
            if (r6 == r7) goto L_0x004c
            boolean r6 = b(r0, r9)
            if (r6 != 0) goto L_0x003d
        L_0x004c:
            int r9 = r9.b
            if (r2 != r9) goto L_0x005e
            boolean r9 = r0.e
            if (r9 == 0) goto L_0x0056
            r0.e = r3
        L_0x0056:
            com.google.protobuf.UnknownFieldSetLite r8 = (com.google.protobuf.UnknownFieldSetLite) r8
            r9 = r1 | 3
            r8.f(r9, r0)
            return r4
        L_0x005e:
            com.google.protobuf.F r8 = new com.google.protobuf.F
            java.lang.String r9 = "Protocol message end-group tag did not match expected tag."
            r8.<init>(r9)
            throw r8
        L_0x0066:
            com.google.protobuf.ByteString r9 = r9.e()
            com.google.protobuf.UnknownFieldSetLite r8 = (com.google.protobuf.UnknownFieldSetLite) r8
            int r0 = r2 << 3
            r0 = r0 | r6
            r8.f(r0, r9)
            return r4
        L_0x0073:
            r9.v(r4)
            long r0 = r1.o()
            com.google.protobuf.UnknownFieldSetLite r8 = (com.google.protobuf.UnknownFieldSetLite) r8
            int r9 = r2 << 3
            r9 = r9 | r4
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r8.f(r9, r0)
            return r4
        L_0x0087:
            r9.v(r3)
            long r0 = r1.r()
            com.google.protobuf.UnknownFieldSetLite r8 = (com.google.protobuf.UnknownFieldSetLite) r8
            int r9 = r2 << 3
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r8.f(r9, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.h0.b(java.lang.Object, com.google.protobuf.m):boolean");
    }
}
