package P1;

import D1.f;
import D1.i;
import Kd.a;
import N2.j;
import Pd.b;
import Pd.c;
import Pd.d;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h {

    /* renamed from: a  reason: collision with root package name */
    public boolean f575a = true;
    public Object b;

    /* renamed from: c  reason: collision with root package name */
    public Object f576c;
    public Object d;
    public Object e;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[SYNTHETIC, Splitter:B:15:0x0038] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051 A[Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public h(Pd.d r8) {
        /*
            r7 = this;
            java.lang.String r0 = "HeifSyntaxEditor"
            java.lang.String r1 = "brand: "
            r7.<init>()
            r2 = 1
            r7.f575a = r2
            int r3 = android.system.OsConstants.SEEK_SET     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            r4 = 4
            r8.a(r3, r4)     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            r3 = 0
            r4 = -1
            int r5 = k(r8)     // Catch:{ EOFException -> 0x002f }
            int r4 = k(r8)     // Catch:{ EOFException -> 0x002f }
            java.lang.String r6 = m(r4)     // Catch:{ EOFException -> 0x002f }
            java.lang.String r1 = r1.concat(r6)     // Catch:{ EOFException -> 0x002f }
            android.util.Log.i(r0, r1)     // Catch:{ EOFException -> 0x002f }
            r1 = 1718909296(0x66747970, float:2.8862439E23)
            if (r5 != r1) goto L_0x002f
            r1 = r2
            goto L_0x0030
        L_0x002d:
            r7 = move-exception
            goto L_0x0054
        L_0x002f:
            r1 = r3
        L_0x0030:
            if (r1 == 0) goto L_0x0051
            r1 = 1751476579(0x68656963, float:4.333464E24)
            if (r4 == r1) goto L_0x0038
            goto L_0x0051
        L_0x0038:
            int r1 = android.system.OsConstants.SEEK_END     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            r3 = 0
            long r5 = r8.a(r1, r3)     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            int r1 = android.system.OsConstants.SEEK_SET     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            r8.a(r1, r3)     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
        L_0x0045:
            java.lang.String r1 = ""
            D1.i r1 = r7.i(r8, r5, r1)     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            if (r1 == 0) goto L_0x004e
            goto L_0x0045
        L_0x004e:
            r7.f575a = r2     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
            goto L_0x0053
        L_0x0051:
            r7.f575a = r3     // Catch:{ ErrnoException -> 0x0063, OutOfMemoryError -> 0x002d }
        L_0x0053:
            return
        L_0x0054:
            java.lang.String r8 = "Too many boxes in file. This might imply a corrupted file."
            android.util.Log.e(r0, r8, r7)
            java.io.IOException r8 = new java.io.IOException
            java.lang.String r7 = r7.getMessage()
            r8.<init>(r7)
            throw r8
        L_0x0063:
            java.io.IOException r7 = new java.io.IOException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.h.<init>(Pd.d):void");
    }

    /* JADX WARNING: type inference failed for: r0v5, types: [java.io.ByteArrayInputStream, java.io.InputStream, Pd.d] */
    public static h a(Object obj) {
        if (obj instanceof File) {
            FileInputStream fileInputStream = new FileInputStream((File) obj);
            try {
                h d2 = d(fileInputStream.getFD());
                fileInputStream.close();
                fileInputStream.close();
                return d2;
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        } else if (obj instanceof FileDescriptor) {
            return d((FileDescriptor) obj);
        } else {
            if (obj instanceof ByteArrayInputStream) {
                ByteArrayInputStream byteArrayInputStream = (ByteArrayInputStream) obj;
                int available = byteArrayInputStream.available();
                byte[] bArr = new byte[available];
                byteArrayInputStream.read(bArr, 0, available);
                ? byteArrayInputStream2 = new ByteArrayInputStream(bArr);
                try {
                    h hVar = new h(byteArrayInputStream2);
                    byteArrayInputStream2.close();
                    return hVar;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            } else {
                Log.e("HeifSyntaxEditor", "Never be here");
                throw new IllegalArgumentException("Unsupported input type");
            }
        }
        throw th;
        throw th;
    }

    public static int c(File file, File file2) {
        try {
            h a7 = a(file);
            if (!a7.f575a) {
                Log.i("HeifSyntaxEditor", "Not a heic image");
                return 1;
            }
            int b5 = ((b) a7.b).b();
            if (b5 != 2) {
                Log.i("HeifSyntaxEditor", "SyntaxEditing not needed");
                return b5;
            } else if (a7.b(file2)) {
                Log.i("HeifSyntaxEditor", "SyntaxEditing success");
                return 0;
            } else {
                Log.e("HeifSyntaxEditor", "SyntaxEditing fail");
                return -2;
            }
        } catch (IOException e7) {
            Log.e("HeifSyntaxEditor", "IOException");
            e7.printStackTrace();
            return -2;
        } catch (Error | Exception e8) {
            Log.e("HeifSyntaxEditor", "Unknown exception/error");
            e8.printStackTrace();
            return -2;
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, P1.h] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c A[SYNTHETIC, Splitter:B:16:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0055 A[Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static P1.h d(java.io.FileDescriptor r8) {
        /*
            if (r8 == 0) goto L_0x006d
            P1.h r0 = new P1.h
            java.lang.String r1 = "HeifSyntaxEditor"
            java.lang.String r2 = "brand: "
            r0.<init>()
            r3 = 1
            r0.f575a = r3
            int r4 = android.system.OsConstants.SEEK_SET     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            r5 = 4
            android.system.Os.lseek(r8, r5, r4)     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            r4 = 0
            r5 = -1
            int r6 = l(r8)     // Catch:{ EOFException -> 0x0033 }
            int r5 = l(r8)     // Catch:{ EOFException -> 0x0033 }
            java.lang.String r7 = m(r5)     // Catch:{ EOFException -> 0x0033 }
            java.lang.String r2 = r2.concat(r7)     // Catch:{ EOFException -> 0x0033 }
            android.util.Log.i(r1, r2)     // Catch:{ EOFException -> 0x0033 }
            r2 = 1718909296(0x66747970, float:2.8862439E23)
            if (r6 != r2) goto L_0x0033
            r2 = r3
            goto L_0x0034
        L_0x0031:
            r8 = move-exception
            goto L_0x0058
        L_0x0033:
            r2 = r4
        L_0x0034:
            if (r2 == 0) goto L_0x0055
            r2 = 1751476579(0x68656963, float:4.333464E24)
            if (r5 == r2) goto L_0x003c
            goto L_0x0055
        L_0x003c:
            int r2 = android.system.OsConstants.SEEK_END     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            r4 = 0
            long r6 = android.system.Os.lseek(r8, r4, r2)     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            int r2 = android.system.OsConstants.SEEK_SET     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            android.system.Os.lseek(r8, r4, r2)     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
        L_0x0049:
            java.lang.String r2 = ""
            D1.i r2 = r0.j(r8, r6, r2)     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            if (r2 == 0) goto L_0x0052
            goto L_0x0049
        L_0x0052:
            r0.f575a = r3     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
            goto L_0x0057
        L_0x0055:
            r0.f575a = r4     // Catch:{ ErrnoException -> 0x0067, OutOfMemoryError -> 0x0031 }
        L_0x0057:
            return r0
        L_0x0058:
            java.lang.String r0 = "Too many boxes in file. This might imply a corrupted file."
            android.util.Log.e(r1, r0, r8)
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r8 = r8.getMessage()
            r0.<init>(r8)
            throw r0
        L_0x0067:
            r8 = move-exception
            java.io.IOException r8 = r8.rethrowAsIOException()
            throw r8
        L_0x006d:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "FileDescriptor cannot be null"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.h.d(java.io.FileDescriptor):P1.h");
    }

    public static int h(Object obj) {
        try {
            h a7 = a(obj);
            if (a7.f575a) {
                return ((b) a7.b).b();
            }
            Log.i("HeifSyntaxEditor", "Not a heic image");
            return 1;
        } catch (IOException e7) {
            Log.e("HeifSyntaxEditor", "IOException");
            e7.printStackTrace();
            return -2;
        } catch (Error | Exception e8) {
            Log.e("HeifSyntaxEditor", "Unknown exception/error");
            e8.printStackTrace();
            return -2;
        }
    }

    public static int k(d dVar) {
        byte[] bArr = new byte[4];
        if (dVar.read(bArr, 0, 4) == 4) {
            ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
            return f.H(bArr);
        }
        throw new EOFException();
    }

    public static int l(FileDescriptor fileDescriptor) {
        byte[] bArr = new byte[4];
        if (Os.read(fileDescriptor, bArr, 0, 4) == 4) {
            ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
            return f.H(bArr);
        }
        throw new EOFException();
    }

    public static String m(int i2) {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        return new String(new byte[]{(byte) ((i2 >> 24) & ScoverState.TYPE_NFC_SMART_COVER), (byte) ((i2 >> 16) & ScoverState.TYPE_NFC_SMART_COVER), (byte) ((i2 >> 8) & ScoverState.TYPE_NFC_SMART_COVER), (byte) (i2 & ScoverState.TYPE_NFC_SMART_COVER)});
    }

    /* JADX WARNING: type inference failed for: r16v3, types: [boolean] */
    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.Object, N2.t] */
    /* JADX WARNING: type inference failed for: r16v4 */
    /* JADX WARNING: type inference failed for: r16v6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:108:0x023f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(java.io.File r28) {
        /*
            r27 = this;
            r1 = r27
            r2 = r28
            java.lang.String r3 = "HeifSyntaxEditor"
            java.lang.String r4 = "rw"
            java.lang.Object r0 = r1.b
            Pd.b r0 = (Pd.b) r0
            int r0 = r0.b()
            r6 = 2
            if (r0 != r6) goto L_0x02a7
            java.lang.Object r0 = r1.b
            Pd.b r0 = (Pd.b) r0
            java.nio.ByteBuffer r7 = r0.d
            r8 = 1
            r9 = 0
            r10 = -1
            java.lang.String r11 = "IpcoTable"
            if (r7 != 0) goto L_0x0028
            java.lang.String r0 = "Chunk not set. Returning..."
            android.util.Log.i(r11, r0)
        L_0x0025:
            r16 = 0
            goto L_0x008e
        L_0x0028:
            java.util.ArrayList r12 = r0.f3628a
            if (r12 != 0) goto L_0x0032
            java.lang.String r0 = "No Ipco Item in the list"
            android.util.Log.e(r11, r0)
            goto L_0x0025
        L_0x0032:
            int r7 = r7.capacity()
            if (r7 == 0) goto L_0x0044
            boolean r7 = r0.e
            if (r7 == 0) goto L_0x0044
            java.lang.String r0 = "Already sorted. Returning..."
            android.util.Log.i(r11, r0)
            r16 = 0
            goto L_0x009e
        L_0x0044:
            int r7 = r0.b()
            if (r7 != r8) goto L_0x0050
            java.lang.String r0 = "Editing not needed. Returning..."
            android.util.Log.e(r11, r0)
            goto L_0x0025
        L_0x0050:
            java.nio.ByteBuffer r7 = r0.d
            int r7 = r7.capacity()
            java.nio.ByteBuffer r12 = java.nio.ByteBuffer.allocate(r7)
            java.util.ArrayList r13 = r0.f3628a
            java.util.Iterator r13 = r13.iterator()
        L_0x0060:
            boolean r14 = r13.hasNext()
            if (r14 == 0) goto L_0x0092
            java.lang.Object r14 = r13.next()
            Pd.a r14 = (Pd.a) r14
            int r15 = r14.b
            int r14 = r14.f3627c
            r16 = 0
            if (r15 < 0) goto L_0x0089
            int r5 = r15 + r14
            if (r5 <= r7) goto L_0x0079
            goto L_0x0089
        L_0x0079:
            java.nio.ByteBuffer r5 = r0.d
            r5.position(r15)
            byte[] r5 = new byte[r14]
            java.nio.ByteBuffer r14 = r0.d
            r14.get(r5)
            r12.put(r5)
            goto L_0x0060
        L_0x0089:
            java.lang.String r0 = "Invalid mapping table data"
            android.util.Log.e(r11, r0)
        L_0x008e:
            r0 = r9
            r5 = r10
            goto L_0x0206
        L_0x0092:
            r16 = 0
            r12.rewind()
            r12.array()
            r0.d = r12
            r0.e = r8
        L_0x009e:
            java.lang.Object r0 = r1.b
            Pd.b r0 = (Pd.b) r0
            java.nio.ByteBuffer r5 = r0.d
            if (r5 == 0) goto L_0x00b2
            int r5 = r5.capacity()
            if (r5 <= 0) goto L_0x00b2
            java.nio.ByteBuffer r0 = r0.d
            byte[] r9 = r0.array()
        L_0x00b2:
            if (r9 == 0) goto L_0x00bb
            java.lang.Object r0 = r1.b
            Pd.b r0 = (Pd.b) r0
            int r0 = r0.f3629c
            goto L_0x00bc
        L_0x00bb:
            r0 = r10
        L_0x00bc:
            java.lang.Object r5 = r1.f576c
            Pd.c r5 = (Pd.c) r5
            java.lang.Object r7 = r1.b
            Pd.b r7 = (Pd.b) r7
            java.util.HashMap r7 = r7.b
            r5.getClass()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r10)
            java.nio.ByteBuffer r12 = r5.b     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.nio.ByteBuffer r12 = r12.duplicate()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            N2.t r13 = new N2.t     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r13.<init>()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r13.f = r12     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r14 = r12.capacity()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r13.e = r14     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r14 = r13.e     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            byte r15 = r13.g()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r17 = r6
            r6 = 3
            byte[] r6 = r13.h(r6)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            if (r15 >= r8) goto L_0x00f2
            r15 = r17
            goto L_0x00f3
        L_0x00f2:
            r15 = 4
        L_0x00f3:
            byte r6 = r6[r17]     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r6 = r6 & r8
            if (r6 != r8) goto L_0x00fb
            r6 = r17
            goto L_0x00fc
        L_0x00fb:
            r6 = r8
        L_0x00fc:
            int r8 = r13.d     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.String r10 = "Index out of buffer bounds"
            if (r8 < 0) goto L_0x0289
            r20 = r0
            int r0 = r8 + 4
            if (r0 > r14) goto L_0x0289
            r19 = r9
            r0 = 4
            byte[] r9 = new byte[r0]     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r12.position(r8)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r12.get(r9)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r8 = r13.d     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r8 = r8 + r0
            r13.d = r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r9)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r0 = r0.getInt()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r8 = r16
        L_0x0122:
            if (r8 >= r0) goto L_0x01e5
            int r9 = r13.d     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            if (r9 < 0) goto L_0x01df
            int r9 = r9 + r15
            if (r9 > r14) goto L_0x01df
            r13.d = r9     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            byte r9 = r13.g()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r9 = java.lang.Byte.toUnsignedInt(r9)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r21 = r0
            r0 = r16
        L_0x0139:
            if (r0 >= r9) goto L_0x01d5
            r22 = r0
            int r0 = r13.d     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r23 = r8
            r8 = 1
            if (r6 != r8) goto L_0x017f
            byte r8 = r13.g()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r24 = r9
            r9 = r8 & 128(0x80, float:1.794E-43)
            r8 = r8 & 127(0x7f, float:1.78E-43)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.Object r8 = r7.getOrDefault(r8, r11)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r8 = r8.intValue()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r25 = r9
            r9 = -1
            if (r8 == r9) goto L_0x0179
            r8 = r8 & 127(0x7f, float:1.78E-43)
            r8 = r25 | r8
            byte r8 = (byte) r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            if (r0 < 0) goto L_0x0173
            int r9 = r0 + 1
            if (r9 > r14) goto L_0x0173
            r12.position(r0)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r12.put(r8)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            goto L_0x0179
        L_0x0173:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r0.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x017c }
        L_0x0179:
            r25 = r7
            goto L_0x01cb
        L_0x017c:
            r0 = move-exception
            goto L_0x028f
        L_0x017f:
            r24 = r9
            byte[] r8 = r13.h(r6)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            byte r9 = r8[r16]     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r25 = r8
            r8 = r9 & 128(0x80, float:1.794E-43)
            r9 = r9 & 127(0x7f, float:1.78E-43)
            int r9 = r9 << 8
            r17 = 1
            byte r25 = r25[r17]     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r9 = r9 | r25
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.Object r9 = r7.getOrDefault(r9, r11)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.Integer r9 = (java.lang.Integer) r9     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r9 = r9.intValue()     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r25 = r7
            r7 = -1
            if (r9 == r7) goto L_0x01cb
            byte[] r7 = new byte[r6]     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            int r26 = r9 >> 8
            r26 = r26 & 127(0x7f, float:1.78E-43)
            r8 = r8 | r26
            byte r8 = (byte) r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r7[r16] = r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            byte r8 = (byte) r9     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r17 = 1
            r7[r17] = r8     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            if (r0 < 0) goto L_0x01c5
            int r8 = r0 + r6
            if (r8 > r14) goto L_0x01c5
            r12.position(r0)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r12.put(r7)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            goto L_0x01cb
        L_0x01c5:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r0.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x017c }
        L_0x01cb:
            int r0 = r22 + 1
            r8 = r23
            r9 = r24
            r7 = r25
            goto L_0x0139
        L_0x01d5:
            r25 = r7
            r23 = r8
            int r8 = r23 + 1
            r0 = r21
            goto L_0x0122
        L_0x01df:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r0.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x017c }
        L_0x01e5:
            r5.b = r12     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            java.lang.Object r0 = r1.f576c
            Pd.c r0 = (Pd.c) r0
            java.nio.ByteBuffer r0 = r0.b
            byte[] r9 = r0.array()
            if (r9 == 0) goto L_0x0200
            java.lang.Object r0 = r1.f576c
            Pd.c r0 = (Pd.c) r0
            int r10 = r0.f3630a
            r0 = r9
            r5 = r10
            r9 = r19
            r10 = r20
            goto L_0x0206
        L_0x0200:
            r0 = r9
            r9 = r19
            r10 = r20
            r5 = -1
        L_0x0206:
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x023f }
            r6.<init>(r2, r4)     // Catch:{ IOException -> 0x023f }
            long r7 = r6.length()     // Catch:{ all -> 0x0234 }
            r6.setLength(r7)     // Catch:{ all -> 0x0234 }
            java.nio.channels.FileChannel r18 = r6.getChannel()     // Catch:{ all -> 0x0234 }
            java.nio.channels.FileChannel$MapMode r19 = java.nio.channels.FileChannel.MapMode.READ_WRITE     // Catch:{ all -> 0x0234 }
            long r22 = r6.length()     // Catch:{ all -> 0x0234 }
            r20 = 0
            java.nio.MappedByteBuffer r7 = r18.map(r19, r20, r22)     // Catch:{ all -> 0x0234 }
            r7.position(r10)     // Catch:{ all -> 0x0234 }
            r7.put(r9)     // Catch:{ all -> 0x0234 }
            r7.position(r5)     // Catch:{ all -> 0x0234 }
            r7.put(r0)     // Catch:{ all -> 0x0234 }
            r6.close()     // Catch:{ IOException -> 0x023f }
            r17 = 1
            return r17
        L_0x0234:
            r0 = move-exception
            r7 = r0
            r6.close()     // Catch:{ all -> 0x023a }
            goto L_0x023e
        L_0x023a:
            r0 = move-exception
            r7.addSuppressed(r0)     // Catch:{ IOException -> 0x023f }
        L_0x023e:
            throw r7     // Catch:{ IOException -> 0x023f }
        L_0x023f:
            java.io.RandomAccessFile r6 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0283 }
            r6.<init>(r2, r4)     // Catch:{ IOException -> 0x0283 }
            java.lang.String r0 = "Editing failed, roll back to original"
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x0278 }
            long r7 = r6.length()     // Catch:{ all -> 0x0278 }
            r6.setLength(r7)     // Catch:{ all -> 0x0278 }
            java.nio.channels.FileChannel r17 = r6.getChannel()     // Catch:{ all -> 0x0278 }
            java.nio.channels.FileChannel$MapMode r18 = java.nio.channels.FileChannel.MapMode.READ_WRITE     // Catch:{ all -> 0x0278 }
            long r21 = r6.length()     // Catch:{ all -> 0x0278 }
            r19 = 0
            java.nio.MappedByteBuffer r0 = r17.map(r18, r19, r21)     // Catch:{ all -> 0x0278 }
            r0.position(r10)     // Catch:{ all -> 0x0278 }
            java.lang.Object r2 = r1.d     // Catch:{ all -> 0x0278 }
            java.nio.ByteBuffer r2 = (java.nio.ByteBuffer) r2     // Catch:{ all -> 0x0278 }
            r0.put(r2)     // Catch:{ all -> 0x0278 }
            r0.position(r5)     // Catch:{ all -> 0x0278 }
            java.lang.Object r1 = r1.e     // Catch:{ all -> 0x0278 }
            java.nio.ByteBuffer r1 = (java.nio.ByteBuffer) r1     // Catch:{ all -> 0x0278 }
            r0.put(r1)     // Catch:{ all -> 0x0278 }
            r6.close()     // Catch:{ IOException -> 0x0283 }
            goto L_0x02a9
        L_0x0278:
            r0 = move-exception
            r1 = r0
            r6.close()     // Catch:{ all -> 0x027e }
            goto L_0x0282
        L_0x027e:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x0283 }
        L_0x0282:
            throw r1     // Catch:{ IOException -> 0x0283 }
        L_0x0283:
            java.lang.String r0 = "Roll back failed"
            android.util.Log.e(r3, r0)
            return r16
        L_0x0289:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            r0.<init>(r10)     // Catch:{ IndexOutOfBoundsException -> 0x017c }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x017c }
        L_0x028f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Error parsing IPMA Table: "
            r1.<init>(r2)
            java.lang.String r0 = r0.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "Ipma"
            android.util.Log.e(r1, r0)
            return r16
        L_0x02a7:
            r16 = 0
        L_0x02a9:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: P1.h.b(java.io.File):boolean");
    }

    public Exception e() {
        Exception exc;
        synchronized (this.b) {
            exc = (Exception) this.e;
        }
        return exc;
    }

    public Object f() {
        Object obj;
        synchronized (this.b) {
            try {
                if (this.f575a) {
                    Exception exc = (Exception) this.e;
                    if (exc == null) {
                        obj = this.f576c;
                    } else {
                        throw new RuntimeException(exc);
                    }
                } else {
                    throw new IllegalStateException("Task is not yet complete");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public boolean g() {
        boolean z;
        synchronized (this.b) {
            z = false;
            if (this.f575a && ((Exception) this.e) == null) {
                z = true;
            }
        }
        return z;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [D1.i, java.lang.Object] */
    public i i(d dVar, long j2, String str) {
        d dVar2 = dVar;
        long j3 = j2;
        long a7 = dVar2.a(OsConstants.SEEK_CUR, 0);
        long j8 = j3 - a7;
        int i2 = 8;
        if (j8 < ((long) 8)) {
            return null;
        }
        long unsignedLong = Integer.toUnsignedLong(k(dVar2));
        int k = k(dVar2);
        if (unsignedLong != 0) {
            if (unsignedLong == 1) {
                j8 = (((long) k(dVar2)) & 4294967295L) | (((long) k(dVar2)) << 32);
                i2 = 16;
            } else {
                j8 = unsignedLong;
            }
        }
        int i7 = (j8 > ((long) i2) ? 1 : (j8 == ((long) i2) ? 0 : -1));
        if (i7 >= 0) {
            long j10 = a7 + j8;
            if (j10 <= j3) {
                ? obj = new Object();
                obj.d = i2;
                if (k == 1835365473 && i7 != 0) {
                    k(dVar2);
                    if (k(dVar2) != 1751411826) {
                        obj.d += 4;
                    }
                    dVar2.a(OsConstants.SEEK_SET, a7 + ((long) obj.d));
                } else if (k == 1768973167) {
                    this.b = new b((int) a7);
                    int i8 = (int) (j8 - 8);
                    if (i8 > 0) {
                        byte[] bArr = new byte[i8];
                        if (dVar2.read(bArr, 0, i8) == i8) {
                            ByteBuffer wrap = ByteBuffer.wrap(bArr);
                            this.d = wrap.duplicate();
                            if (((b) this.b).a(wrap) && ((b) this.b).e()) {
                                if (((b) this.b).b() == 2) {
                                    ((b) this.b).d(wrap);
                                }
                                ((b) this.b).c();
                            }
                        } else {
                            throw new EOFException();
                        }
                    }
                    throw new IOException();
                } else if (k == 1768975713) {
                    this.f576c = new c((int) a7);
                    if (((b) this.b).b() == 2) {
                        int i10 = (int) (j8 - 8);
                        byte[] bArr2 = new byte[i10];
                        if (dVar2.read(bArr2, 0, i10) == i10) {
                            ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
                            this.e = wrap2.duplicate();
                            c cVar = (c) this.f576c;
                            cVar.getClass();
                            cVar.b = wrap2.duplicate();
                        } else {
                            throw new EOFException();
                        }
                    } else {
                        dVar2.a(OsConstants.SEEK_SET, j10);
                    }
                }
                if (k == 1768973167 || k == 1768977008 || k == 1835365473) {
                    do {
                    } while (i(dVar2, j10, C0212a.A(str, "  ")) != null);
                    dVar2.a(OsConstants.SEEK_SET, j10);
                    return obj;
                }
                dVar2.a(OsConstants.SEEK_SET, j10);
                return obj;
            }
        }
        StringBuilder j11 = j.j(a7, "Invalid box at ", " of length ");
        j11.append(j8);
        j11.append(". End of parent ");
        j11.append(j3);
        Log.w("HeifSyntaxEditor", j11.toString());
        return null;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [D1.i, java.lang.Object] */
    public i j(FileDescriptor fileDescriptor, long j2, String str) {
        FileDescriptor fileDescriptor2 = fileDescriptor;
        long j3 = j2;
        long lseek = Os.lseek(fileDescriptor2, 0, OsConstants.SEEK_CUR);
        long j8 = j3 - lseek;
        int i2 = 8;
        if (j8 < ((long) 8)) {
            return null;
        }
        long unsignedLong = Integer.toUnsignedLong(l(fileDescriptor2));
        int l = l(fileDescriptor2);
        if (unsignedLong != 0) {
            if (unsignedLong == 1) {
                j8 = (((long) l(fileDescriptor2)) & 4294967295L) | (((long) l(fileDescriptor2)) << 32);
                i2 = 16;
            } else {
                j8 = unsignedLong;
            }
        }
        int i7 = (j8 > ((long) i2) ? 1 : (j8 == ((long) i2) ? 0 : -1));
        if (i7 >= 0) {
            long j10 = lseek + j8;
            if (j10 <= j3) {
                ? obj = new Object();
                obj.d = i2;
                if (l == 1835365473 && i7 != 0) {
                    l(fileDescriptor2);
                    if (l(fileDescriptor2) != 1751411826) {
                        obj.d += 4;
                    }
                    Os.lseek(fileDescriptor2, lseek + ((long) obj.d), OsConstants.SEEK_SET);
                } else if (l == 1768973167) {
                    this.b = new b((int) lseek);
                    int i8 = (int) (j8 - 8);
                    if (i8 > 0) {
                        byte[] bArr = new byte[i8];
                        if (Os.read(fileDescriptor2, bArr, 0, i8) == i8) {
                            ByteBuffer wrap = ByteBuffer.wrap(bArr);
                            this.d = wrap.duplicate();
                            if (((b) this.b).a(wrap) && ((b) this.b).e()) {
                                if (((b) this.b).b() == 2) {
                                    ((b) this.b).d(wrap);
                                }
                                ((b) this.b).c();
                            }
                        } else {
                            throw new EOFException();
                        }
                    }
                    throw new IOException();
                } else if (l == 1768975713) {
                    this.f576c = new c((int) lseek);
                    if (((b) this.b).b() == 2) {
                        int i10 = (int) (j8 - 8);
                        byte[] bArr2 = new byte[i10];
                        if (Os.read(fileDescriptor2, bArr2, 0, i10) == i10) {
                            ByteBuffer wrap2 = ByteBuffer.wrap(bArr2);
                            this.e = wrap2.duplicate();
                            c cVar = (c) this.f576c;
                            cVar.getClass();
                            cVar.b = wrap2.duplicate();
                        } else {
                            throw new EOFException();
                        }
                    } else {
                        Os.lseek(fileDescriptor2, j10, OsConstants.SEEK_SET);
                    }
                }
                if (l == 1768973167 || l == 1768977008 || l == 1835365473) {
                    do {
                    } while (j(fileDescriptor2, j10, C0212a.A(str, "  ")) != null);
                    Os.lseek(fileDescriptor2, j10, OsConstants.SEEK_SET);
                    return obj;
                }
                Os.lseek(fileDescriptor2, j10, OsConstants.SEEK_SET);
                return obj;
            }
        }
        StringBuilder j11 = j.j(lseek, "Invalid box at ", " of length ");
        j11.append(j8);
        j11.append(". End of parent ");
        j11.append(j3);
        Log.w("HeifSyntaxEditor", j11.toString());
        return null;
    }

    public void n() {
        boolean z;
        IllegalStateException illegalStateException;
        String str;
        if (this.f575a) {
            int i2 = a.d;
            synchronized (this.b) {
                z = this.f575a;
            }
            if (z) {
                Exception e7 = e();
                if (e7 != null) {
                    str = "failure";
                } else if (g()) {
                    str = "result ".concat(String.valueOf(f()));
                } else {
                    str = "unknown issue";
                }
                illegalStateException = new IllegalStateException("Complete with: ".concat(str), e7);
            } else {
                illegalStateException = new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
            }
            throw illegalStateException;
        }
    }

    public void o() {
        synchronized (this.b) {
            try {
                if (this.f575a) {
                    ((a) this.d).i(this);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
