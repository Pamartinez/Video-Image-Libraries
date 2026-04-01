package de;

import N2.j;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.samsung.android.sdk.sgpl.media.iso.IsoInterface;
import i.C0212a;
import java.io.FileDescriptor;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: de.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0931b implements f {

    /* renamed from: a  reason: collision with root package name */
    public final FileDescriptor f4227a;
    public final long b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f4228c = new ArrayList();

    public C0931b(FileDescriptor fileDescriptor) {
        this.f4227a = fileDescriptor;
        this.b = Os.lseek(fileDescriptor, 0, OsConstants.SEEK_END);
        Os.lseek(fileDescriptor, 0, OsConstants.SEEK_SET);
        do {
        } while (e(this.f4228c, fileDescriptor, this.b, ""));
    }

    public static boolean e(List list, FileDescriptor fileDescriptor, long j2, String str) {
        List list2 = list;
        FileDescriptor fileDescriptor2 = fileDescriptor;
        long j3 = j2;
        long lseek = Os.lseek(fileDescriptor2, 0, OsConstants.SEEK_CUR);
        long j8 = j3 - lseek;
        int i2 = 8;
        if (j8 < ((long) 8)) {
            return false;
        }
        long unsignedLong = Integer.toUnsignedLong(f.b(fileDescriptor2));
        int b5 = f.b(fileDescriptor2);
        if (unsignedLong != 0) {
            if (unsignedLong == 1) {
                j8 = (((long) f.b(fileDescriptor2)) & 4294967295L) | (((long) f.b(fileDescriptor2)) << 32);
                i2 = 16;
            } else {
                j8 = unsignedLong;
            }
        }
        long j10 = (long) i2;
        int i7 = (j8 > j10 ? 1 : (j8 == j10 ? 0 : -1));
        if (i7 >= 0) {
            int i8 = i7;
            long j11 = lseek + j8;
            if (j11 <= j3) {
                C0930a aVar = new C0930a(b5, new long[]{lseek, j8});
                aVar.d = i2;
                if (b5 == 1768517222 || b5 == 1768714083) {
                    int i10 = (int) (j8 - j10);
                    byte[] bArr = null;
                    if (i10 > 67108864) {
                        try {
                            Log.w("HeicPeekParser", "Iso box(" + b5 + ") data size is too large: " + i10);
                        } catch (OutOfMemoryError e) {
                            Log.w("HeicPeekParser", j.b(b5, i10, "Couldn't read large box(", "), size: "), e);
                        }
                    } else {
                        bArr = new byte[i10];
                    }
                    aVar.f4226c = bArr;
                    if (bArr == null) {
                        return false;
                    }
                    Os.read(fileDescriptor2, bArr, 0, bArr.length);
                } else if (b5 == 1835365473 && i8 != 0) {
                    f.b(fileDescriptor2);
                    if (f.b(fileDescriptor2) != 1751411826) {
                        aVar.d += 4;
                    }
                    Os.lseek(fileDescriptor2, lseek + ((long) aVar.d), OsConstants.SEEK_SET);
                }
                switch (b5) {
                    case 1768517222:
                    case IsoInterface.BOX_ILOC:
                    case IsoInterface.BOX_META:
                    case 1885959277:
                        list2.add(aVar);
                        break;
                }
                if (b5 == 1768517222 || b5 == 1769104742 || b5 == 1835365473) {
                    do {
                    } while (e(list2, fileDescriptor2, j11, C0212a.A(str, "  ")));
                    Os.lseek(fileDescriptor2, j11, OsConstants.SEEK_SET);
                    return true;
                }
                Os.lseek(fileDescriptor2, j11, OsConstants.SEEK_SET);
                return true;
            }
        }
        StringBuilder j12 = j.j(lseek, "Invalid box at ", " of length ");
        j12.append(j8);
        j12.append(". End of parent ");
        j12.append(j3);
        Log.w("HeicPeekParser", j12.toString());
        return false;
    }

    public final boolean c() {
        try {
            return d();
        } catch (ErrnoException | InterruptedIOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v13, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r27v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v20, resolved type: int} */
    /* JADX WARNING: type inference failed for: r1v0, types: [de.d, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v2 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean d() {
        /*
            r29 = this;
            r0 = r29
            de.d r1 = new de.d
            r1.<init>()
            r2 = 10
            long[] r3 = new long[r2]
            r1.d = r3
            r3 = 0
            r1.e = r3
            java.util.ArrayList r4 = r0.f4228c
            java.util.Iterator r5 = r4.iterator()
        L_0x0016:
            boolean r6 = r5.hasNext()
            r7 = 1
            r8 = 1768517222(0x69696e66, float:1.7637566E25)
            if (r6 == 0) goto L_0x003e
            java.lang.Object r6 = r5.next()
            de.a r6 = (de.C0930a) r6
            int r9 = r6.f4225a
            long[] r10 = r6.b
            if (r9 != r8) goto L_0x0016
            r8 = r10[r3]
            int r6 = r6.d
            long r11 = (long) r6
            long r8 = r8 + r11
            r1.a(r8)
            r8 = r10[r3]
            r6 = r10[r7]
            long r8 = r8 + r6
            r1.a(r8)
            goto L_0x0016
        L_0x003e:
            long[] r5 = r1.d
            int r1 = r1.e
            long[] r1 = java.util.Arrays.copyOf(r5, r1)
            int r5 = r1.length
            java.lang.String r6 = "HeicPeekParser"
            r9 = 2
            if (r5 == r9) goto L_0x0052
            java.lang.String r0 = "iinf box is not one"
            android.util.Log.w(r6, r0)
            return r3
        L_0x0052:
            r10 = r1[r7]
            r10 = r1[r3]
            java.util.Iterator r1 = r4.iterator()
        L_0x005a:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x006d
            java.lang.Object r5 = r1.next()
            de.a r5 = (de.C0930a) r5
            int r11 = r5.f4225a
            if (r11 != r8) goto L_0x005a
            byte[] r1 = r5.f4226c
            goto L_0x006e
        L_0x006d:
            r1 = 0
        L_0x006e:
            java.util.Iterator r4 = r4.iterator()
        L_0x0072:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0088
            java.lang.Object r5 = r4.next()
            de.a r5 = (de.C0930a) r5
            int r8 = r5.f4225a
            r11 = 1768714083(0x696c6f63, float:1.7864531E25)
            if (r8 != r11) goto L_0x0072
            byte[] r4 = r5.f4226c
            goto L_0x0089
        L_0x0088:
            r4 = 0
        L_0x0089:
            if (r1 == 0) goto L_0x0222
            byte r5 = r1[r3]
            r8 = 6
            r12 = 4
            if (r5 != 0) goto L_0x0099
            java.nio.ByteOrder r5 = java.nio.ByteOrder.BIG_ENDIAN
            int r5 = D1.f.J(r1, r12, r5)
            r13 = r8
            goto L_0x00a1
        L_0x0099:
            java.nio.ByteOrder r5 = java.nio.ByteOrder.BIG_ENDIAN
            int r5 = D1.f.I(r1, r12, r5)
            r13 = 8
        L_0x00a1:
            r14 = r3
        L_0x00a2:
            if (r14 >= r5) goto L_0x0222
            java.nio.ByteOrder r15 = java.nio.ByteOrder.BIG_ENDIAN
            int r16 = D1.f.I(r1, r13, r15)
            int r2 = r13 + 4
            int r2 = D1.f.I(r1, r2, r15)
            r17 = r7
            r7 = 1768842853(0x696e6665, float:1.8012993E25)
            if (r2 != r7) goto L_0x0203
            int r2 = r13 + 8
            byte r2 = r1[r2]
            if (r2 != r9) goto L_0x00c6
            int r7 = r13 + 16
            int r7 = D1.f.I(r1, r7, r15)
        L_0x00c3:
            r18 = 0
            goto L_0x00d0
        L_0x00c6:
            r7 = 3
            if (r2 != r7) goto L_0x01fc
            int r7 = r13 + 18
            int r7 = D1.f.I(r1, r7, r15)
            goto L_0x00c3
        L_0x00d0:
            r10 = 1953325424(0x746d6170, float:7.522892E31)
            if (r7 != r10) goto L_0x00d7
            goto L_0x01c2
        L_0x00d7:
            r10 = 1835625829(0x6d696d65, float:4.515141E27)
            if (r7 != r10) goto L_0x01f1
            if (r2 != r9) goto L_0x00e5
            int r2 = r13 + 12
            int r2 = D1.f.J(r1, r2, r15)
            goto L_0x00eb
        L_0x00e5:
            int r2 = r13 + 12
            int r2 = D1.f.I(r1, r2, r15)
        L_0x00eb:
            if (r4 == 0) goto L_0x01eb
            byte r7 = r4[r3]
            byte r10 = r4[r12]
            r10 = r10 & 15
            r19 = 5
            byte r19 = r4[r19]
            r19 = r19 & 15
            if (r7 >= r9) goto L_0x0102
            int r15 = D1.f.J(r4, r8, r15)
            r20 = 8
            goto L_0x010a
        L_0x0102:
            if (r7 != r9) goto L_0x01e4
            int r15 = D1.f.I(r4, r8, r15)
            r20 = 10
        L_0x010a:
            r11 = r3
            r8 = r20
            r20 = 8
        L_0x010f:
            if (r11 >= r15) goto L_0x01da
            if (r7 >= r9) goto L_0x0120
            r21 = r9
            java.nio.ByteOrder r9 = java.nio.ByteOrder.BIG_ENDIAN
            int r9 = D1.f.J(r4, r8, r9)
            int r8 = r8 + 2
        L_0x011d:
            r22 = r12
            goto L_0x012b
        L_0x0120:
            r21 = r9
            java.nio.ByteOrder r9 = java.nio.ByteOrder.BIG_ENDIAN
            int r9 = D1.f.I(r4, r8, r9)
            int r8 = r8 + 4
            goto L_0x011d
        L_0x012b:
            java.nio.ByteOrder r12 = java.nio.ByteOrder.BIG_ENDIAN
            int r12 = D1.f.J(r4, r8, r12)
            r12 = r12 & 15
            int r8 = r8 + 4
            int r8 = r8 + r19
            int r8 = r8 + 2
            int r8 = r8 + r19
            if (r9 != r2) goto L_0x0140
            r9 = r17
            goto L_0x0141
        L_0x0140:
            r9 = r3
        L_0x0141:
            if (r12 != 0) goto L_0x0146
            r12 = r17
            goto L_0x0147
        L_0x0146:
            r12 = r3
        L_0x0147:
            r9 = r9 & r12
            if (r9 == 0) goto L_0x01c3
            r23 = 0
            r9 = r3
            r12 = r9
            r25 = r4
            r3 = r23
        L_0x0152:
            if (r9 >= r10) goto L_0x016d
            long r3 = r3 << r20
            int r26 = r8 + r9
            r27 = r12
            byte r12 = r25[r26]
            r12 = r12 & 255(0xff, float:3.57E-43)
            r26 = r1
            r28 = r2
            long r1 = (long) r12
            long r3 = r3 | r1
            int r9 = r9 + 1
            r1 = r26
            r12 = r27
            r2 = r28
            goto L_0x0152
        L_0x016d:
            r26 = r1
            r28 = r2
            r27 = r12
            long r1 = r0.b
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x017f
            java.lang.String r0 = "Invalid file format #heic"
            android.util.Log.e(r6, r0)
            return r27
        L_0x017f:
            int r8 = r8 + r10
            r1 = r23
            r12 = r27
        L_0x0184:
            if (r12 >= r10) goto L_0x0196
            long r1 = r1 << r20
            int r9 = r8 + r12
            byte r9 = r25[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            r23 = r1
            long r1 = (long) r9
            long r1 = r23 | r1
            int r12 = r12 + 1
            goto L_0x0184
        L_0x0196:
            int r8 = r8 + r10
            r23 = 4294967295(0xffffffff, double:2.1219957905E-314)
            int r9 = (r1 > r23 ? 1 : (r1 == r23 ? 0 : -1))
            if (r9 < 0) goto L_0x01a6
            java.lang.String r0 = "xmpLength is too big"
            android.util.Log.e(r6, r0)
            return r27
        L_0x01a6:
            int r1 = (int) r1
            byte[] r2 = new byte[r1]
            int r9 = android.system.OsConstants.SEEK_SET
            java.io.FileDescriptor r12 = r0.f4227a
            android.system.Os.lseek(r12, r3, r9)
            r3 = r27
            android.system.Os.read(r12, r2, r3, r1)
            java.lang.String r1 = new java.lang.String
            r1.<init>(r2)
            java.lang.String r2 = "xmlns:hdrgm=\"http://ns.adobe.com/hdr-gain-map/1.0/\""
            boolean r1 = r1.contains(r2)
            if (r1 == 0) goto L_0x01cb
        L_0x01c2:
            return r17
        L_0x01c3:
            r26 = r1
            r28 = r2
            r25 = r4
            int r8 = r8 + r10
            int r8 = r8 + r10
        L_0x01cb:
            int r11 = r11 + 1
            r9 = r21
            r12 = r22
            r4 = r25
            r1 = r26
            r2 = r28
            r3 = 0
            goto L_0x010f
        L_0x01da:
            r26 = r1
            r25 = r4
            r21 = r9
            r22 = r12
        L_0x01e2:
            r12 = r3
            goto L_0x020e
        L_0x01e4:
            java.lang.String r0 = "Invalid version info#ILOC"
            android.util.Log.e(r6, r0)
            r12 = 0
            return r12
        L_0x01eb:
            java.lang.String r0 = "ILOC DATA NULL"
            android.util.Log.e(r6, r0)
            throw r18
        L_0x01f1:
            r26 = r1
            r25 = r4
            r21 = r9
            r22 = r12
        L_0x01f9:
            r20 = 8
            goto L_0x01e2
        L_0x01fc:
            r12 = r3
            java.lang.String r0 = "Invalid version info#INFE"
            android.util.Log.e(r6, r0)
            return r12
        L_0x0203:
            r26 = r1
            r25 = r4
            r21 = r9
            r22 = r12
            r18 = 0
            goto L_0x01f9
        L_0x020e:
            int r13 = r13 + r16
            int r14 = r14 + 1
            r3 = r12
            r7 = r17
            r9 = r21
            r12 = r22
            r4 = r25
            r1 = r26
            r2 = 10
            r8 = 6
            goto L_0x00a2
        L_0x0222:
            r12 = r3
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: de.C0931b.d():boolean");
    }
}
