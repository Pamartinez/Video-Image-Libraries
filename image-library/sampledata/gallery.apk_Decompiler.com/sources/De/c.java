package de;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements f {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f4229a;
    public final long b;

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0213, code lost:
        if (new java.lang.String(r4).contains("urn:iso:std:iso:ts:2") != false) goto L_0x01dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0227, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        android.util.Log.e(r15, "Invalid file format#FFE2");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r14.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0092, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x009c, code lost:
        r1 = r0;
        r11 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x014d, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0150, code lost:
        r13 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01e0, code lost:
        r12.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0223 A[SYNTHETIC, Splitter:B:107:0x0223] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x024a A[LOOP:0: B:1:0x0019->B:124:0x024a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x0240 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(java.io.FileDescriptor r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r0.<init>()
            int r2 = android.system.OsConstants.SEEK_END
            r3 = 0
            long r5 = android.system.Os.lseek(r1, r3, r2)
            r0.b = r5
            int r2 = android.system.OsConstants.SEEK_SET
            android.system.Os.lseek(r1, r3, r2)
            de.f.a(r1)
        L_0x0019:
            int r2 = de.f.a(r1)
            int r7 = android.system.OsConstants.SEEK_CUR
            long r7 = android.system.Os.lseek(r1, r3, r7)
            int r9 = de.f.a(r1)
            long r9 = (long) r9
            r11 = 65280(0xff00, float:9.1477E-41)
            r12 = r2 & r11
            r13 = 0
            if (r12 == r11) goto L_0x0032
            goto L_0x0247
        L_0x0032:
            r12 = 65505(0xffe1, float:9.1792E-41)
            java.lang.String r15 = "JpgPeekParser"
            r3 = 2
            if (r2 != r12) goto L_0x006c
            int r4 = (int) r9
            int r4 = r4 - r3
            byte[] r14 = new byte[r4]
            int r12 = android.system.Os.read(r1, r14, r13, r4)
            if (r12 == r4) goto L_0x004b
            java.lang.String r1 = "Invalid file format#FFE1"
            android.util.Log.e(r15, r1)
            goto L_0x0247
        L_0x004b:
            r12 = 28
            if (r4 <= r12) goto L_0x006c
            java.lang.String r4 = new java.lang.String
            r4.<init>(r14, r13, r12)
            java.lang.String r12 = "http://ns.adobe.com/xap/1.0/"
            boolean r4 = r4.equals(r12)
            if (r4 == 0) goto L_0x006c
            java.lang.String r4 = new java.lang.String
            r4.<init>(r14)
            java.lang.String r12 = "xmlns:hdrgm=\"http://ns.adobe.com/hdr-gain-map/1.0/\""
            boolean r4 = r4.contains(r12)
            if (r4 == 0) goto L_0x006c
        L_0x0069:
            r13 = 1
            goto L_0x0247
        L_0x006c:
            r4 = 65506(0xffe2, float:9.1793E-41)
            if (r2 != r4) goto L_0x0235
            int r2 = (int) r9
            int r2 = r2 - r3
            byte[] r4 = new byte[r2]
            java.io.FileInputStream r12 = new java.io.FileInputStream
            r12.<init>(r1)
            java.nio.channels.FileChannel r14 = r12.getChannel()     // Catch:{ all -> 0x0097 }
            long r16 = r14.position()     // Catch:{ all -> 0x021d }
            r18 = r11
            int r11 = android.system.Os.read(r1, r4, r13, r2)     // Catch:{ all -> 0x021d }
            if (r11 == r2) goto L_0x00a0
            java.lang.String r1 = "Invalid file format#FFE2"
            android.util.Log.e(r15, r1)     // Catch:{ all -> 0x009b }
            r14.close()     // Catch:{ all -> 0x0097 }
            r12.close()
            goto L_0x0247
        L_0x0097:
            r0 = move-exception
            r1 = r0
            goto L_0x022c
        L_0x009b:
            r0 = move-exception
            r1 = r0
            r11 = r14
            goto L_0x0221
        L_0x00a0:
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x021d }
            int r11 = D1.f.I(r4, r13, r2)     // Catch:{ all -> 0x021d }
            r3 = 1297106432(0x4d504600, float:2.18390528E8)
            if (r11 != r3) goto L_0x0202
            r3 = 4
            r11 = r14
            long r13 = (long) r3
            long r16 = r16 + r13
            int r13 = D1.f.I(r4, r3, r2)     // Catch:{ all -> 0x00bc }
            r14 = 1229531648(0x49492a00, float:823968.0)
            if (r13 != r14) goto L_0x00c0
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x00bc }
            goto L_0x00c0
        L_0x00bc:
            r0 = move-exception
        L_0x00bd:
            r1 = r0
            goto L_0x0221
        L_0x00c0:
            r13 = 8
            int r13 = D1.f.I(r4, r13, r2)     // Catch:{ all -> 0x00bc }
            int r14 = r3 + r13
            int r14 = D1.f.J(r4, r14, r2)     // Catch:{ all -> 0x00bc }
            int r13 = r13 + 6
            r19 = r3
            r22 = r5
            r3 = 0
            r5 = 0
            r20 = 0
            r21 = 0
        L_0x00d8:
            if (r3 >= r14) goto L_0x0106
            int r6 = D1.f.J(r4, r13, r2)     // Catch:{ all -> 0x00bc }
            r24 = r3
            r3 = 45057(0xb001, float:6.3138E-41)
            if (r6 != r3) goto L_0x00ec
            int r3 = r13 + 8
            int r3 = D1.f.I(r4, r3, r2)     // Catch:{ all -> 0x00bc }
            r5 = r3
        L_0x00ec:
            r3 = 45058(0xb002, float:6.314E-41)
            if (r6 != r3) goto L_0x0101
            int r3 = r13 + 4
            int r3 = D1.f.I(r4, r3, r2)     // Catch:{ all -> 0x00bc }
            int r6 = r13 + 8
            int r6 = D1.f.I(r4, r6, r2)     // Catch:{ all -> 0x00bc }
            r20 = r3
            r21 = r6
        L_0x0101:
            int r13 = r13 + 12
            int r3 = r24 + 1
            goto L_0x00d8
        L_0x0106:
            if (r20 == 0) goto L_0x01fe
            if (r5 == 0) goto L_0x01fe
            int r3 = r19 + r21
            r6 = 0
        L_0x010d:
            if (r6 >= r5) goto L_0x01fe
            int r13 = D1.f.I(r4, r3, r2)     // Catch:{ all -> 0x00bc }
            r14 = 196608(0x30000, float:2.75506E-40)
            if (r13 != r14) goto L_0x0123
            int r3 = r3 + 16
            r20 = r2
            r24 = r5
            r25 = r7
            r7 = r15
            r8 = r6
            goto L_0x01ee
        L_0x0123:
            int r13 = r3 + 4
            int r13 = D1.f.I(r4, r13, r2)     // Catch:{ all -> 0x00bc }
            int r14 = r3 + 8
            int r14 = D1.f.I(r4, r14, r2)     // Catch:{ all -> 0x00bc }
            r20 = r2
            r21 = r3
            long r2 = (long) r14     // Catch:{ all -> 0x00bc }
            long r2 = r16 + r2
            r24 = r5
            r14 = r19
            byte[] r5 = new byte[r14]     // Catch:{ all -> 0x00bc }
            r25 = r7
            r8 = r6
            long r6 = r0.b     // Catch:{ all -> 0x00bc }
            int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x0153
            java.lang.String r1 = "Invalid file format #jpg"
            android.util.Log.e(r15, r1)     // Catch:{ all -> 0x00bc }
        L_0x014a:
            r11.close()     // Catch:{ all -> 0x0097 }
            r12.close()
        L_0x0150:
            r13 = 0
            goto L_0x0247
        L_0x0153:
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r5)     // Catch:{ all -> 0x00bc }
            int r6 = r11.read(r6, r2)     // Catch:{ all -> 0x00bc }
            r14 = 4
            if (r14 == r6) goto L_0x0164
            java.lang.String r1 = "Invalid file status.#MPF"
            android.util.Log.e(r15, r1)     // Catch:{ all -> 0x00bc }
            goto L_0x014a
        L_0x0164:
            java.nio.ByteOrder r6 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x00bc }
            r7 = 0
            int r6 = D1.f.J(r5, r7, r6)     // Catch:{ all -> 0x00bc }
            r7 = 65496(0xffd8, float:9.178E-41)
            if (r6 == r7) goto L_0x0175
            int r3 = r21 + 16
            r7 = r15
            goto L_0x01ee
        L_0x0175:
            r6 = 2
        L_0x0176:
            if (r13 <= r6) goto L_0x01a2
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.wrap(r5)     // Catch:{ all -> 0x00bc }
            r27 = r2
            long r2 = (long) r6     // Catch:{ all -> 0x00bc }
            long r2 = r27 + r2
            int r2 = r11.read(r7, r2)     // Catch:{ all -> 0x00bc }
            r14 = 4
            if (r14 == r2) goto L_0x018e
            java.lang.String r1 = "Invalid file status.#MPF-ENTRY"
            android.util.Log.e(r15, r1)     // Catch:{ all -> 0x00bc }
            goto L_0x014a
        L_0x018e:
            int r6 = r6 + 4
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN     // Catch:{ all -> 0x00bc }
            r7 = 0
            int r3 = D1.f.J(r5, r7, r2)     // Catch:{ all -> 0x00bc }
            r7 = 2
            int r2 = D1.f.J(r5, r7, r2)     // Catch:{ all -> 0x00bc }
            r7 = r3 & r18
            r14 = r18
            if (r7 == r14) goto L_0x01a4
        L_0x01a2:
            r7 = r15
            goto L_0x01ec
        L_0x01a4:
            r7 = 65505(0xffe1, float:9.1792E-41)
            if (r3 == r7) goto L_0x01b1
            int r2 = r2 + -2
            int r6 = r6 + r2
            r18 = r14
            r2 = r27
            goto L_0x0176
        L_0x01b1:
            int r2 = r2 + -2
            byte[] r3 = new byte[r2]     // Catch:{ all -> 0x00bc }
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.wrap(r3)     // Catch:{ all -> 0x00bc }
            r18 = r15
            long r14 = (long) r6     // Catch:{ all -> 0x00bc }
            long r14 = r27 + r14
            int r7 = r11.read(r7, r14)     // Catch:{ all -> 0x00bc }
            if (r2 == r7) goto L_0x01cd
            java.lang.String r1 = "Invalid file status.#subFFE1"
            r7 = r18
            android.util.Log.e(r7, r1)     // Catch:{ all -> 0x00bc }
            goto L_0x014a
        L_0x01cd:
            r7 = r18
            int r6 = r6 + r2
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00bc }
            r2.<init>(r3)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = "xmlns:HDRGainMap=\"http://ns.apple.com/HDRGainMap/1.0/\""
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x00bc }
            if (r2 == 0) goto L_0x01e5
        L_0x01dd:
            r11.close()     // Catch:{ all -> 0x0097 }
            r12.close()
            goto L_0x0069
        L_0x01e5:
            r15 = r7
            r2 = r27
            r18 = 65280(0xff00, float:9.1477E-41)
            goto L_0x0176
        L_0x01ec:
            int r3 = r21 + 16
        L_0x01ee:
            int r6 = r8 + 1
            r15 = r7
            r2 = r20
            r5 = r24
            r7 = r25
            r18 = 65280(0xff00, float:9.1477E-41)
            r19 = 4
            goto L_0x010d
        L_0x01fe:
            r25 = r7
            r7 = r15
            goto L_0x0216
        L_0x0202:
            r22 = r5
            r25 = r7
            r11 = r14
            r7 = r15
            java.lang.String r2 = new java.lang.String     // Catch:{ all -> 0x00bc }
            r2.<init>(r4)     // Catch:{ all -> 0x00bc }
            java.lang.String r3 = "urn:iso:std:iso:ts:2"
            boolean r2 = r2.contains(r3)     // Catch:{ all -> 0x00bc }
            if (r2 == 0) goto L_0x0216
            goto L_0x01dd
        L_0x0216:
            r11.close()     // Catch:{ all -> 0x0097 }
            r12.close()
            goto L_0x023a
        L_0x021d:
            r0 = move-exception
            r11 = r14
            goto L_0x00bd
        L_0x0221:
            if (r11 == 0) goto L_0x022b
            r11.close()     // Catch:{ all -> 0x0227 }
            goto L_0x022b
        L_0x0227:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ all -> 0x0097 }
        L_0x022b:
            throw r1     // Catch:{ all -> 0x0097 }
        L_0x022c:
            r12.close()     // Catch:{ all -> 0x0230 }
            goto L_0x0234
        L_0x0230:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0234:
            throw r1
        L_0x0235:
            r22 = r5
            r25 = r7
            r7 = r15
        L_0x023a:
            long r2 = r25 + r9
            int r4 = (r2 > r22 ? 1 : (r2 == r22 ? 0 : -1))
            if (r4 < 0) goto L_0x024a
            java.lang.String r1 = "File End"
            android.util.Log.e(r7, r1)
            goto L_0x0150
        L_0x0247:
            r0.f4229a = r13
            return
        L_0x024a:
            int r4 = android.system.OsConstants.SEEK_SET
            android.system.Os.lseek(r1, r2, r4)
            r5 = r22
            r3 = 0
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: de.c.<init>(java.io.FileDescriptor):void");
    }

    public boolean c() {
        return this.f4229a;
    }

    public c(long j2, boolean z) {
        this.f4229a = z;
        this.b = j2;
    }
}
