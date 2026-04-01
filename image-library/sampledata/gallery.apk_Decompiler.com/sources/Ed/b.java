package Ed;

import A0.l;
import B0.a;
import D0.f;
import Dd.C0732c;
import Df.C0736b;
import Gf.m;
import He.F;
import N2.d;
import Qe.C0823m;
import Qe.V;
import Tf.n;
import We.C;
import a.C0068a;
import android.app.Application;
import android.content.Context;
import android.os.SharedMemory;
import android.os.Trace;
import cf.C0922a;
import cf.e;
import df.C0937F;
import gf.C1074e;
import i.C0212a;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import mg.h;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements e {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3371a;
    public int b;

    /* renamed from: c  reason: collision with root package name */
    public Object f3372c;
    public Object d;
    public Object e;
    public Object f;

    public /* synthetic */ b() {
        this.f3371a = 4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean b(Ed.b r4) {
        /*
            java.lang.String r0 = "Tracker is not initialized, status : "
            monitor-enter(r4)
            int r1 = r4.b     // Catch:{ all -> 0x001c }
            r2 = 0
            r3 = -1
            if (r3 != r1) goto L_0x001e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x001c }
            r1.<init>(r0)     // Catch:{ all -> 0x001c }
            int r0 = r4.b     // Catch:{ all -> 0x001c }
            r1.append(r0)     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x001c }
            a.C0068a.b(r0)     // Catch:{ all -> 0x001c }
            monitor-exit(r4)
            return r2
        L_0x001c:
            r0 = move-exception
            goto L_0x0032
        L_0x001e:
            int r0 = r4.u()     // Catch:{ all -> 0x001c }
            r1 = 1
            if (r1 != r0) goto L_0x0030
            java.lang.Object r0 = r4.f     // Catch:{ all -> 0x001c }
            Od.b r0 = (Od.b) r0     // Catch:{ all -> 0x001c }
            boolean r0 = r0.a()     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x0030
            r2 = r1
        L_0x0030:
            monitor-exit(r4)
            return r2
        L_0x0032:
            monitor-exit(r4)     // Catch:{ all -> 0x001c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ed.b.b(Ed.b):boolean");
    }

    public static /* synthetic */ void p(b bVar, String str, int i2, String str2, int i7) {
        if ((i7 & 2) != 0) {
            i2 = bVar.b;
        }
        if ((i7 & 4) != 0) {
            str2 = "";
        }
        bVar.o(i2, str, str2);
        throw null;
    }

    public static b t(SharedMemory sharedMemory) {
        try {
            b bVar = new b();
            F.K("MeshLayer", "fromSharedMemory() - E");
            ByteBuffer mapReadOnly = sharedMemory.mapReadOnly();
            F.K("MeshLayer", "encoded data size: " + mapReadOnly.limit());
            bVar.m(mapReadOnly);
            SharedMemory.unmap(mapReadOnly);
            F.K("MeshLayer", "fromSharedMemory() - X");
            return bVar;
        } catch (Exception e7) {
            F.D("MeshLayer", "Failed to read mesh layer from shared memory");
            throw new RuntimeException("Failed to read mesh layer from shared memory", e7);
        }
    }

    public SharedMemory A() {
        F.K("MeshLayer", "toSharedMemory() - E");
        F.K("MeshLayer", "encode() - E");
        F.C("MeshLayer", "creating buffers for data elements");
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.asIntBuffer().put(this.b);
        ByteBuffer allocate2 = ByteBuffer.allocate(4);
        allocate2.asIntBuffer().put(((int[]) this.f3372c).length);
        ByteBuffer allocate3 = ByteBuffer.allocate(((int[]) this.f3372c).length * 4);
        allocate3.asIntBuffer().put((int[]) this.f3372c);
        ByteBuffer allocate4 = ByteBuffer.allocate(4);
        allocate4.asIntBuffer().put(((float[]) this.d).length);
        ByteBuffer allocate5 = ByteBuffer.allocate(((float[]) this.d).length * 4);
        allocate5.asFloatBuffer().put((float[]) this.d);
        ByteBuffer allocate6 = ByteBuffer.allocate(4);
        allocate6.asIntBuffer().put(((float[]) this.e).length);
        ByteBuffer allocate7 = ByteBuffer.allocate(((float[]) this.e).length * 4);
        allocate7.asFloatBuffer().put((float[]) this.e);
        ByteBuffer allocate8 = ByteBuffer.allocate(4);
        allocate8.asIntBuffer().put(((float[]) this.f).length);
        ByteBuffer allocate9 = ByteBuffer.allocate(((float[]) this.f).length * 4);
        allocate9.asFloatBuffer().put((float[]) this.f);
        int limit = allocate9.limit() + allocate8.limit() + allocate7.limit() + allocate6.limit() + allocate5.limit() + allocate4.limit() + allocate3.limit() + allocate2.limit() + allocate.limit();
        F.C("MeshLayer", "overall buffer size: " + limit);
        F.C("MeshLayer", "Setting up combined buffer");
        ByteBuffer allocate10 = ByteBuffer.allocate(limit);
        allocate10.put(allocate);
        allocate10.put(allocate2);
        allocate10.put(allocate3);
        allocate10.put(allocate4);
        allocate10.put(allocate5);
        allocate10.put(allocate6);
        allocate10.put(allocate7);
        allocate10.put(allocate8);
        allocate10.put(allocate9);
        F.K("MeshLayer", "encode() - X");
        byte[] array = allocate10.array();
        F.C("MeshLayer", "encoded data size: " + array.length);
        SharedMemory create = SharedMemory.create("MeshLayer", array.length);
        ByteBuffer mapReadWrite = create.mapReadWrite();
        mapReadWrite.put(array);
        SharedMemory.unmap(mapReadWrite);
        F.K("MeshLayer", "toSharedMemory() - X");
        return create;
    }

    public boolean B() {
        int z = z();
        String str = (String) this.f;
        if (z == str.length() || z == -1 || str.charAt(z) != ',') {
            return false;
        }
        this.b++;
        return true;
    }

    public boolean C(boolean z) {
        int y = y(z());
        String str = (String) this.f;
        int length = str.length() - y;
        if (length >= 4 && y != -1) {
            int i2 = 0;
            while (true) {
                if (i2 < 4) {
                    if ("null".charAt(i2) != str.charAt(y + i2)) {
                        break;
                    }
                    i2++;
                } else if (length <= 4 || h.f(str.charAt(y + 4)) != 0) {
                    if (z) {
                        this.b = y + 4;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: finally extract failed */
    public void D(char c5) {
        int i2 = this.b;
        if (i2 > 0 && c5 == '\"') {
            try {
                this.b = i2 - 1;
                String l = l();
                this.b = i2;
                if (j.a(l, "null")) {
                    o(this.b - 1, "Expected string literal but 'null' literal was found", "Use 'coerceInputValues = true' in 'Json {}' builder to coerce nulls to default values.");
                    throw null;
                }
            } catch (Throwable th) {
                this.b = i2;
                throw th;
            }
        }
        q(h.f(c5), true);
        throw null;
    }

    public V a(C c5) {
        j.e(c5, "javaTypeParameter");
        C0937F f5 = (C0937F) ((Gf.j) this.f).invoke(c5);
        if (f5 != null) {
            return f5;
        }
        return ((e) ((a) this.f3372c).e).a(c5);
    }

    public int c(CharSequence charSequence, int i2) {
        int i7 = i2 + 4;
        if (i7 >= charSequence.length()) {
            this.b = i2;
            if (i7 < charSequence.length()) {
                return c(charSequence, this.b);
            }
            p(this, "Unexpected EOF during unicode escape", 0, (String) null, 6);
            throw null;
        }
        ((StringBuilder) this.e).append((char) (s(charSequence, i2 + 3) + (s(charSequence, i2) << 12) + (s(charSequence, i2 + 1) << 8) + (s(charSequence, i2 + 2) << 4)));
        return i7;
    }

    public boolean d() {
        String str = (String) this.f;
        int i2 = this.b;
        boolean z = false;
        if (i2 == -1) {
            return false;
        }
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9) {
                i2++;
            } else {
                this.b = i2;
                if (charAt == '}' || charAt == ']' || charAt == ':' || charAt == ',') {
                    z = true;
                }
                return !z;
            }
        }
        this.b = i2;
        return false;
    }

    public void e(int i2, String str) {
        String str2 = (String) this.f;
        if (str2.length() - i2 >= str.length()) {
            int length = str.length();
            int i7 = 0;
            while (i7 < length) {
                if (str.charAt(i7) == (str2.charAt(i2 + i7) | ' ')) {
                    i7++;
                } else {
                    p(this, "Expected valid boolean literal prefix, but had '" + l() + '\'', 0, (String) null, 6);
                    throw null;
                }
            }
            this.b = str.length() + i2;
            return;
        }
        p(this, "Unexpected end of boolean literal", 0, (String) null, 6);
        throw null;
    }

    public String f() {
        int i2;
        String str;
        char c5;
        StringBuilder sb2 = (StringBuilder) this.e;
        String str2 = (String) this.f;
        i('\"');
        int i7 = this.b;
        int A02 = n.A0(str2, '\"', i7, 4);
        if (A02 != -1) {
            int i8 = i7;
            while (i2 < A02) {
                if (str2.charAt(i2) == '\\') {
                    int i10 = this.b;
                    char charAt = str2.charAt(i2);
                    boolean z = false;
                    while (charAt != '\"') {
                        if (charAt == '\\') {
                            sb2.append(str2, i10, i2);
                            int y = y(i2 + 1);
                            if (y != -1) {
                                int i11 = y + 1;
                                char charAt2 = str2.charAt(y);
                                if (charAt2 == 'u') {
                                    i11 = c(str2, i11);
                                } else {
                                    if (charAt2 < 'u') {
                                        c5 = mg.b.f4923a[charAt2];
                                    } else {
                                        c5 = 0;
                                    }
                                    if (c5 != 0) {
                                        sb2.append(c5);
                                    } else {
                                        p(this, "Invalid escaped char '" + charAt2 + '\'', 0, (String) null, 6);
                                        throw null;
                                    }
                                }
                                i10 = y(i11);
                                if (i10 == -1) {
                                    p(this, "Unexpected EOF", i10, (String) null, 4);
                                    throw null;
                                }
                            } else {
                                p(this, "Expected escape sequence to continue, got EOF", 0, (String) null, 6);
                                throw null;
                            }
                        } else {
                            i2++;
                            if (i2 >= str2.length()) {
                                sb2.append(str2, i10, i2);
                                i10 = y(i2);
                                if (i10 == -1) {
                                    p(this, "Unexpected EOF", i10, (String) null, 4);
                                    throw null;
                                }
                            } else {
                                continue;
                                charAt = str2.charAt(i2);
                            }
                        }
                        i2 = i10;
                        z = true;
                        charAt = str2.charAt(i2);
                    }
                    if (!z) {
                        str = str2.subSequence(i10, i2).toString();
                    } else {
                        str = n(i10, i2);
                    }
                    this.b = i2 + 1;
                    return str;
                }
                i8 = i2 + 1;
            }
            this.b = A02 + 1;
            String substring = str2.substring(i7, A02);
            j.d(substring, "substring(...)");
            return substring;
        }
        l();
        q((byte) 1, false);
        throw null;
    }

    public byte g() {
        byte f5;
        String str = (String) this.f;
        do {
            int i2 = this.b;
            if (i2 == -1 || i2 >= str.length()) {
                return 10;
            }
            int i7 = this.b;
            this.b = i7 + 1;
            f5 = h.f(str.charAt(i7));
        } while (f5 == 3);
        return f5;
    }

    public byte h(byte b5) {
        byte g = g();
        if (g == b5) {
            return g;
        }
        q(b5, true);
        throw null;
    }

    public void i(char c5) {
        if (this.b != -1) {
            String str = (String) this.f;
            while (this.b < str.length()) {
                int i2 = this.b;
                this.b = i2 + 1;
                char charAt = str.charAt(i2);
                if (charAt != ' ' && charAt != 10 && charAt != 13 && charAt != 9) {
                    if (charAt != c5) {
                        D(c5);
                        throw null;
                    }
                    return;
                }
            }
            this.b = -1;
            D(c5);
            throw null;
        }
        D(c5);
        throw null;
    }

    /* JADX WARNING: type inference failed for: r6v0 */
    /* JADX WARNING: type inference failed for: r6v2, types: [java.lang.Throwable, java.lang.String] */
    /* JADX WARNING: type inference failed for: r6v21 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x018a, code lost:
        if (r14 == false) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x018c, code lost:
        return r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0191, code lost:
        if (r10 == Long.MIN_VALUE) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0194, code lost:
        return -r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0195, code lost:
        p(r0, "Numeric value overflow", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x019a, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x019b, code lost:
        p(r0, "Expected numeric literal", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01a0, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0104, code lost:
        if (r12 == r1) goto L_0x0108;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0106, code lost:
        r3 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0108, code lost:
        r3 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0109, code lost:
        if (r1 == r12) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010b, code lost:
        if (r14 == false) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x010f, code lost:
        if (r1 == (r12 - 1)) goto L_0x0112;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0116, code lost:
        if (r20 == false) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0118, code lost:
        if (r3 == false) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0120, code lost:
        if (r2.charAt(r12) != '\"') goto L_0x0125;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0122, code lost:
        r12 = r12 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0125, code lost:
        p(r0, "Expected closing quotation mark", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x012c, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x012d, code lost:
        p(r0, "EOF", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0132, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0133, code lost:
        r0.b = r12;
        r1 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0137, code lost:
        if (r13 == false) goto L_0x0189;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0139, code lost:
        r1 = (double) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x013c, code lost:
        if (r11 != false) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x013e, code lost:
        r3 = java.lang.Math.pow(10.0d, -((double) r9));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0146, code lost:
        if (r11 != true) goto L_0x0183;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0148, code lost:
        r3 = java.lang.Math.pow(10.0d, (double) r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x014d, code lost:
        r1 = r1 * r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0152, code lost:
        if (r1 > 9.223372036854776E18d) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0158, code lost:
        if (r1 < -9.223372036854776E18d) goto L_0x017d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0160, code lost:
        if (java.lang.Math.floor(r1) != r1) goto L_0x0164;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0162, code lost:
        r10 = (long) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0164, code lost:
        p(r0, "Can't convert " + r1 + " to Long", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x017c, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x017d, code lost:
        p(r0, "Numeric value overflow", 0, (java.lang.String) null, 6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0182, code lost:
        throw null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0188, code lost:
        throw new java.lang.RuntimeException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0189, code lost:
        r10 = r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long j() {
        /*
            r21 = this;
            r0 = r21
            int r1 = r0.z()
            int r1 = r0.y(r1)
            java.lang.Object r2 = r0.f
            java.lang.String r2 = (java.lang.String) r2
            int r3 = r2.length()
            java.lang.String r4 = "EOF"
            r5 = 6
            r6 = 0
            r7 = 0
            if (r1 >= r3) goto L_0x01a1
            r3 = -1
            if (r1 == r3) goto L_0x01a1
            char r3 = r2.charAt(r1)
            r8 = 34
            if (r3 != r8) goto L_0x0032
            int r1 = r1 + 1
            int r3 = r2.length()
            if (r1 == r3) goto L_0x002e
            r3 = 1
            goto L_0x0033
        L_0x002e:
            p(r0, r4, r7, r6, r5)
            throw r6
        L_0x0032:
            r3 = r7
        L_0x0033:
            r12 = r1
            r11 = r7
            r13 = r11
            r14 = r13
            r9 = 0
            r16 = 0
            r18 = 0
        L_0x003d:
            int r15 = r2.length()
            java.lang.String r8 = "Numeric value overflow"
            if (r12 == r15) goto L_0x0102
            char r15 = r2.charAt(r12)
            r5 = 101(0x65, float:1.42E-43)
            if (r15 == r5) goto L_0x0051
            r5 = 69
            if (r15 != r5) goto L_0x0075
        L_0x0051:
            if (r13 != 0) goto L_0x0075
            if (r12 == r1) goto L_0x005d
            int r12 = r12 + 1
            r5 = 6
            r8 = 34
            r11 = 1
            r13 = 1
            goto L_0x003d
        L_0x005d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected symbol "
            r1.<init>(r2)
            r1.append(r15)
            java.lang.String r2 = " in numeric literal"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r5 = 6
            p(r0, r1, r7, r6, r5)
            throw r6
        L_0x0075:
            java.lang.String r5 = "Unexpected symbol '-' in numeric literal"
            r6 = 45
            if (r15 != r6) goto L_0x008d
            if (r13 == 0) goto L_0x008d
            if (r12 == r1) goto L_0x0087
            int r12 = r12 + 1
            r11 = r7
        L_0x0082:
            r5 = 6
            r6 = 0
            r8 = 34
            goto L_0x003d
        L_0x0087:
            r6 = 6
            r8 = 0
            p(r0, r5, r7, r8, r6)
            throw r8
        L_0x008d:
            r6 = 0
            r6 = 43
            if (r15 != r6) goto L_0x00a6
            if (r13 == 0) goto L_0x00a6
            if (r12 == r1) goto L_0x009e
            int r12 = r12 + 1
            r5 = 6
            r6 = 0
            r8 = 34
            r11 = 1
            goto L_0x003d
        L_0x009e:
            java.lang.String r1 = "Unexpected symbol '+' in numeric literal"
            r2 = 0
            r6 = 6
            p(r0, r1, r7, r2, r6)
            throw r2
        L_0x00a6:
            r20 = r3
            r3 = 0
            r6 = 6
            r3 = 45
            if (r15 != r3) goto L_0x00bf
            if (r12 != r1) goto L_0x00ba
            int r12 = r12 + 1
            r5 = r6
            r3 = r20
            r6 = 0
            r8 = 34
            r14 = 1
            goto L_0x003d
        L_0x00ba:
            r3 = 0
            p(r0, r5, r7, r3, r6)
            throw r3
        L_0x00bf:
            byte r3 = mg.h.f(r15)
            if (r3 != 0) goto L_0x0104
            int r12 = r12 + 1
            int r3 = r15 + -48
            if (r3 < 0) goto L_0x00e9
            r5 = 10
            if (r3 >= r5) goto L_0x00e9
            if (r13 == 0) goto L_0x00d8
            long r5 = (long) r5
            long r9 = r9 * r5
            long r5 = (long) r3
            long r9 = r9 + r5
        L_0x00d5:
            r3 = r20
            goto L_0x0082
        L_0x00d8:
            long r5 = (long) r5
            long r16 = r16 * r5
            long r5 = (long) r3
            long r16 = r16 - r5
            int r3 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r3 > 0) goto L_0x00e3
            goto L_0x00d5
        L_0x00e3:
            r3 = 0
            r6 = 6
            p(r0, r8, r7, r3, r6)
            throw r3
        L_0x00e9:
            r3 = 0
            r6 = 6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected symbol '"
            r1.<init>(r2)
            r1.append(r15)
            java.lang.String r2 = "' in numeric literal"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            p(r0, r1, r7, r3, r6)
            throw r3
        L_0x0102:
            r20 = r3
        L_0x0104:
            if (r12 == r1) goto L_0x0108
            r3 = 1
            goto L_0x0109
        L_0x0108:
            r3 = r7
        L_0x0109:
            if (r1 == r12) goto L_0x0112
            if (r14 == 0) goto L_0x0116
            int r5 = r12 + -1
            if (r1 == r5) goto L_0x0112
            goto L_0x0116
        L_0x0112:
            r3 = 0
            r6 = 6
            goto L_0x019b
        L_0x0116:
            if (r20 == 0) goto L_0x0133
            if (r3 == 0) goto L_0x012d
            char r1 = r2.charAt(r12)
            r2 = 34
            if (r1 != r2) goto L_0x0125
            int r12 = r12 + 1
            goto L_0x0133
        L_0x0125:
            java.lang.String r1 = "Expected closing quotation mark"
            r3 = 0
            r6 = 6
            p(r0, r1, r7, r3, r6)
            throw r3
        L_0x012d:
            r3 = 0
            r6 = 6
            p(r0, r4, r7, r3, r6)
            throw r3
        L_0x0133:
            r0.b = r12
            r1 = r16
            if (r13 == 0) goto L_0x0189
            double r1 = (double) r1
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            if (r11 != 0) goto L_0x0145
            double r5 = (double) r9
            double r5 = -r5
            double r3 = java.lang.Math.pow(r3, r5)
            goto L_0x014d
        L_0x0145:
            r5 = 1
            if (r11 != r5) goto L_0x0183
            double r5 = (double) r9
            double r3 = java.lang.Math.pow(r3, r5)
        L_0x014d:
            double r1 = r1 * r3
            r3 = 4890909195324358656(0x43e0000000000000, double:9.223372036854776E18)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x017d
            r3 = -4332462841530417152(0xc3e0000000000000, double:-9.223372036854776E18)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 < 0) goto L_0x017d
            double r3 = java.lang.Math.floor(r1)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 != 0) goto L_0x0164
            long r10 = (long) r1
            goto L_0x018a
        L_0x0164:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Can't convert "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = " to Long"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r3 = 0
            r6 = 6
            p(r0, r1, r7, r3, r6)
            throw r3
        L_0x017d:
            r3 = 0
            r6 = 6
            p(r0, r8, r7, r3, r6)
            throw r3
        L_0x0183:
            Dd.a r0 = new Dd.a
            r0.<init>()
            throw r0
        L_0x0189:
            r10 = r1
        L_0x018a:
            if (r14 == 0) goto L_0x018d
            return r10
        L_0x018d:
            r1 = -9223372036854775808
            int r1 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r1 == 0) goto L_0x0195
            long r0 = -r10
            return r0
        L_0x0195:
            r3 = 0
            r6 = 6
            p(r0, r8, r7, r3, r6)
            throw r3
        L_0x019b:
            java.lang.String r1 = "Expected numeric literal"
            p(r0, r1, r7, r3, r6)
            throw r3
        L_0x01a1:
            r3 = r6
            r6 = r5
            p(r0, r4, r7, r3, r6)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: Ed.b.j():long");
    }

    public String k() {
        String str = (String) this.d;
        if (str == null) {
            return f();
        }
        j.b(str);
        this.d = null;
        return str;
    }

    public String l() {
        String str;
        String str2 = (String) this.f;
        String str3 = (String) this.d;
        if (str3 != null) {
            j.b(str3);
            this.d = null;
            return str3;
        }
        int z = z();
        if (z >= str2.length() || z == -1) {
            p(this, "EOF", z, (String) null, 4);
            throw null;
        }
        byte f5 = h.f(str2.charAt(z));
        if (f5 == 1) {
            return k();
        }
        if (f5 == 0) {
            boolean z3 = false;
            while (h.f(str2.charAt(z)) == 0) {
                z++;
                if (z >= str2.length()) {
                    ((StringBuilder) this.e).append(str2, this.b, z);
                    int y = y(z);
                    if (y == -1) {
                        this.b = z;
                        return n(0, 0);
                    }
                    z = y;
                    z3 = true;
                }
            }
            if (!z3) {
                str = str2.subSequence(this.b, z).toString();
            } else {
                str = n(this.b, z);
            }
            this.b = z;
            return str;
        }
        p(this, "Expected beginning of the string, but got " + str2.charAt(z), 0, (String) null, 6);
        throw null;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, Pd.c] */
    public void m(ByteBuffer byteBuffer) {
        F.K("MeshLayer", "decode() - E");
        ? obj = new Object();
        obj.f3630a = 0;
        obj.b = byteBuffer;
        this.b = obj.b();
        int b5 = obj.b();
        int[] iArr = new int[b5];
        byteBuffer.position(obj.f3630a);
        byteBuffer.asIntBuffer().get(iArr);
        obj.f3630a = (b5 * 4) + obj.f3630a;
        this.f3372c = iArr;
        this.d = obj.a(obj.b());
        this.e = obj.a(obj.b());
        this.f = obj.a(obj.b());
        F.K("MeshLayer", "encode() - X");
    }

    public String n(int i2, int i7) {
        StringBuilder sb2 = (StringBuilder) this.e;
        sb2.append((String) this.f, i2, i7);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        sb2.setLength(0);
        return sb3;
    }

    public void o(int i2, String str, String str2) {
        String str3;
        j.e(str, "message");
        j.e(str2, "hint");
        if (str2.length() == 0) {
            str3 = "";
        } else {
            str3 = "\n".concat(str2);
        }
        StringBuilder t = C0212a.t(str, " at path: ");
        t.append(((mg.j) this.f3372c).a());
        t.append(str3);
        throw h.d(i2, t.toString(), (String) this.f);
    }

    public void q(byte b5, boolean z) {
        int i2;
        String str;
        String str2 = (String) this.f;
        String o2 = h.o(b5);
        if (z) {
            i2 = this.b - 1;
        } else {
            i2 = this.b;
        }
        if (this.b == str2.length() || i2 < 0) {
            str = "EOF";
        } else {
            str = String.valueOf(str2.charAt(i2));
        }
        p(this, N2.j.d("Expected ", o2, ", but had '", str, "' instead"), i2, (String) null, 4);
        throw null;
    }

    public void r() {
        if (((d) this.d) == null) {
            f fVar = (f) this.f3372c;
            fVar.D();
            this.d = (d) fVar.g;
        }
    }

    public int s(CharSequence charSequence, int i2) {
        char charAt = charSequence.charAt(i2);
        if ('0' <= charAt && charAt < ':') {
            return charAt - '0';
        }
        if ('a' <= charAt && charAt < 'g') {
            return charAt - 'W';
        }
        if ('A' <= charAt && charAt < 'G') {
            return charAt - '7';
        }
        p(this, "Invalid toHexChar char '" + charAt + "' in unicode escape", 0, (String) null, 6);
        throw null;
    }

    public String toString() {
        switch (this.f3371a) {
            case 3:
                StringBuilder sb2 = new StringBuilder("JsonReader(source='");
                sb2.append((String) this.f);
                sb2.append("', currentPosition=");
                return N2.j.e(sb2, this.b, ')');
            default:
                return super.toString();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:46:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0132  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int u() {
        /*
            r18 = this;
            r1 = r18
            java.lang.Object r0 = r1.f3372c
            r2 = r0
            android.app.Application r2 = (android.app.Application) r2
            java.lang.Object r0 = r1.d
            r3 = r0
            Dd.c r3 = (Dd.C0732c) r3
            java.lang.Object r0 = r1.e
            r4 = r0
            android.content.Context r4 = (android.content.Context) r4
            int r0 = r1.b
            r5 = 1
            if (r0 != 0) goto L_0x0211
            java.lang.String r0 = "user"
            java.lang.Object r0 = r4.getSystemService(r0)
            android.os.UserManager r0 = (android.os.UserManager) r0
            r6 = 0
            if (r0 == 0) goto L_0x002f
            boolean r0 = r0.isUserUnlocked()
            if (r0 != 0) goto L_0x002f
            java.lang.String r0 = "current user is locked"
            a.C0068a.b(r0)
            r1.b = r6
            return r6
        L_0x002f:
            r3.getClass()
            int r0 = og.k.f
            r7 = 3
            r8 = 2
            r9 = -1
            if (r0 == r9) goto L_0x003a
            goto L_0x0050
        L_0x003a:
            int r0 = a.C0068a.x(r4)
            r10 = 540000000(0x202fbf00, float:1.4886273E-19)
            if (r0 < r10) goto L_0x004e
            r10 = 600000000(0x23c34600, float:2.1171589E-17)
            if (r0 < r10) goto L_0x004a
            r0 = r7
            goto L_0x004b
        L_0x004a:
            r0 = r8
        L_0x004b:
            og.k.f = r0
            goto L_0x0050
        L_0x004e:
            og.k.f = r9
        L_0x0050:
            int r0 = og.k.f
            if (r0 != 0) goto L_0x0091
            android.content.SharedPreferences r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r2)
            com.samsung.context.sdk.samsunganalytics.internal.connection.c r10 = com.samsung.context.sdk.samsunganalytics.internal.connection.c.DLS
            java.lang.String r11 = "dom"
            java.lang.String r12 = ""
            java.lang.String r11 = r0.getString(r11, r12)
            r10.a(r11)
            com.samsung.context.sdk.samsunganalytics.internal.connection.b r10 = com.samsung.context.sdk.samsunganalytics.internal.connection.b.DLS_DIR
            java.lang.String r11 = "uri"
            java.lang.String r11 = r0.getString(r11, r12)
            r10.a(r11)
            com.samsung.context.sdk.samsunganalytics.internal.connection.b r10 = com.samsung.context.sdk.samsunganalytics.internal.connection.b.DLS_DIR_BAT
            java.lang.String r11 = "bat-uri"
            java.lang.String r0 = r0.getString(r11, r12)
            r10.a(r0)
            boolean r0 = og.k.F(r4)
            if (r0 == 0) goto L_0x0091
            t1.i r0 = t1.i.e()
            Fd.a r10 = Fd.C0744a.j(r4)
            Dd.e r11 = new Dd.e
            r11.<init>(r1)
            og.k.V(r2, r3, r0, r10, r11)
        L_0x0091:
            android.content.SharedPreferences r10 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r4)
            java.lang.String r11 = "enable_device"
            int r0 = r10.getInt(r11, r6)
            if (r0 != 0) goto L_0x0123
            java.lang.String r0 = "com.samsung.android.feature.SemFloatingFeature"
            java.lang.String r12 = "getBoolean"
            r13 = 0
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x00cf }
            java.lang.String r14 = "getInstance"
            java.lang.Class[] r15 = new java.lang.Class[]{r13}     // Catch:{ Exception -> 0x00cf }
            java.lang.reflect.Method r14 = r0.getMethod(r14, r15)     // Catch:{ Exception -> 0x00cf }
            java.lang.Object r14 = r14.invoke(r13, r13)     // Catch:{ Exception -> 0x00cf }
            java.lang.Class<java.lang.String> r15 = java.lang.String.class
            java.lang.Class[] r15 = new java.lang.Class[]{r15}     // Catch:{ Exception -> 0x00cf }
            java.lang.reflect.Method r0 = r0.getMethod(r12, r15)     // Catch:{ Exception -> 0x00cf }
            java.lang.String r12 = "SEC_FLOATING_FEATURE_CONTEXTSERVICE_ENABLE_SURVEY_MODE"
            java.lang.Object[] r12 = new java.lang.Object[]{r12}     // Catch:{ Exception -> 0x00cf }
            java.lang.Object r0 = r0.invoke(r14, r12)     // Catch:{ Exception -> 0x00cf }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ Exception -> 0x00cf }
            boolean r0 = r0.booleanValue()     // Catch:{ Exception -> 0x00cf }
            goto L_0x00ff
        L_0x00cf:
            r0 = move-exception
            java.lang.String r12 = "content://com.sec.android.log.diagmonagent.sa/check/diagnostic"
            android.net.Uri r12 = android.net.Uri.parse(r12)     // Catch:{ Exception -> 0x00f0 }
            android.content.ContentResolver r14 = r4.getContentResolver()     // Catch:{ Exception -> 0x00f0 }
            android.database.Cursor r12 = r14.query(r12, r13, r13, r13)     // Catch:{ Exception -> 0x00f0 }
            if (r12 == 0) goto L_0x00f2
            r12.moveToNext()     // Catch:{ Exception -> 0x00f0 }
            int r13 = r12.getInt(r6)     // Catch:{ Exception -> 0x00f0 }
            if (r5 != r13) goto L_0x00eb
            r13 = r5
            goto L_0x00ec
        L_0x00eb:
            r13 = r6
        L_0x00ec:
            r12.close()     // Catch:{ Exception -> 0x00f4 }
            goto L_0x00fe
        L_0x00f0:
            r13 = r6
            goto L_0x00f4
        L_0x00f2:
            r0 = r6
            goto L_0x00ff
        L_0x00f4:
            java.lang.String r12 = "DMA is not supported"
            a.C0068a.b(r12)
            java.lang.Class<Gd.a> r12 = Gd.a.class
            a.C0068a.f(r12, r0)
        L_0x00fe:
            r0 = r13
        L_0x00ff:
            if (r0 != 0) goto L_0x0112
            java.lang.String r12 = "feature is not supported"
            a.C0068a.b(r12)
            android.content.SharedPreferences$Editor r10 = r10.edit()
            android.content.SharedPreferences$Editor r10 = r10.putInt(r11, r8)
            r10.apply()
            goto L_0x0128
        L_0x0112:
            java.lang.String r12 = "cf feature is supported"
            a.C0068a.b(r12)
            android.content.SharedPreferences$Editor r10 = r10.edit()
            android.content.SharedPreferences$Editor r10 = r10.putInt(r11, r5)
            r10.apply()
            goto L_0x0128
        L_0x0123:
            if (r0 != r5) goto L_0x0127
            r0 = r5
            goto L_0x0128
        L_0x0127:
            r0 = r6
        L_0x0128:
            if (r0 != 0) goto L_0x0132
            java.lang.String r0 = "Device is not enabled for logging"
            a.C0068a.b(r0)
            r1.b = r9
            return r9
        L_0x0132:
            int r0 = og.k.f
            if (r9 != r0) goto L_0x013e
            java.lang.String r0 = "SenderType is None"
            a.C0068a.b(r0)
            r1.b = r9
            return r9
        L_0x013e:
            if (r0 != r8) goto L_0x0168
            android.content.pm.PackageInfo r0 = a.C0068a.H(r4)
            if (r0 == 0) goto L_0x015d
            java.lang.String[] r0 = r0.requestedPermissions
            if (r0 == 0) goto L_0x015d
            int r8 = r0.length
            r10 = r6
        L_0x014c:
            if (r10 >= r8) goto L_0x015d
            r11 = r0[r10]
            java.lang.String r12 = "com.sec.spp.permission.TOKEN"
            boolean r11 = r11.startsWith(r12)
            if (r11 == 0) goto L_0x015a
            r0 = r5
            goto L_0x015e
        L_0x015a:
            int r10 = r10 + 1
            goto L_0x014c
        L_0x015d:
            r0 = r6
        L_0x015e:
            if (r0 != 0) goto L_0x0168
            java.lang.String r0 = "SamsungAnalytics2 need to define 'com.sec.spp.permission.TOKEN_XXXX' permission in AndroidManifest"
            o1.C0246a.m0(r0)
            r1.b = r9
            return r9
        L_0x0168:
            boolean r0 = o1.C0246a.b0(r4)
            if (r0 == 0) goto L_0x020f
            r0 = 710000000(0x2a51bd80, float:1.8628675E-13)
            int r8 = a.C0068a.x(r4)
            if (r0 > r8) goto L_0x0179
            r0 = r5
            goto L_0x017a
        L_0x0179:
            r0 = r6
        L_0x017a:
            if (r0 != 0) goto L_0x0184
            G0.e r0 = r3.d
            boolean r0 = r0.M()
            if (r0 == 0) goto L_0x020f
        L_0x0184:
            int r0 = og.k.f
            if (r0 != r7) goto L_0x020f
            android.content.SharedPreferences r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.E(r4)
            java.lang.String r4 = a.C0068a.G(r4)
            boolean r8 = android.text.TextUtils.isEmpty(r4)
            java.lang.String r9 = "None"
            if (r8 == 0) goto L_0x0199
            r4 = r9
        L_0x0199:
            java.lang.String r8 = "sendCommonSuccess"
            boolean r6 = r0.getBoolean(r8, r6)
            java.lang.String r8 = "appVersion"
            java.lang.String r9 = r0.getString(r8, r9)
            r10 = 0
            java.lang.String r12 = "sendCommonTime"
            long r10 = r0.getLong(r12, r10)
            java.lang.Long r13 = java.lang.Long.valueOf(r10)
            java.lang.String r14 = ", prefAppVersion = "
            java.lang.String r15 = ", beforeSendCommonTime = "
            java.lang.String r5 = "AppVersion = "
            java.lang.StringBuilder r5 = c0.C0086a.q(r5, r4, r14, r9, r15)
            r5.append(r13)
            java.lang.String r14 = ", success = "
            r5.append(r14)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            a.C0068a.b(r5)
            boolean r5 = r4.equals(r9)
            if (r5 == 0) goto L_0x01ee
            if (r6 == 0) goto L_0x01dc
            r5 = 7
            boolean r5 = o1.C0246a.O(r5, r13)
            if (r5 != 0) goto L_0x01ee
        L_0x01dc:
            if (r6 != 0) goto L_0x020f
            long r5 = java.lang.System.currentTimeMillis()
            r9 = 6
            long r13 = (long) r9
            r16 = 3600000(0x36ee80, double:1.7786363E-317)
            long r13 = r13 * r16
            long r13 = r13 + r10
            int r5 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r5 <= 0) goto L_0x020f
        L_0x01ee:
            java.lang.String r5 = "send app common"
            a.C0068a.b(r5)
            android.content.SharedPreferences$Editor r0 = r0.edit()
            android.content.SharedPreferences$Editor r0 = r0.putString(r8, r4)
            long r4 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r0 = r0.putLong(r12, r4)
            r0.apply()
            com.samsung.context.sdk.samsunganalytics.internal.sender.a r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.w(r2, r7, r3)
            Jd.c r0 = (Jd.c) r0
            r0.f()
        L_0x020f:
            r2 = 1
            goto L_0x0212
        L_0x0211:
            r2 = r5
        L_0x0212:
            r1.b = r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Ed.b.u():int");
    }

    public String v(String str) {
        j.e(str, "keyToMatch");
        int i2 = this.b;
        try {
            if (g() == 6) {
                if (j.a(x(), str)) {
                    this.d = null;
                    if (g() == 5) {
                        String x9 = x();
                        this.b = i2;
                        this.d = null;
                        return x9;
                    }
                }
            }
            return null;
        } finally {
            this.b = i2;
            this.d = null;
        }
    }

    public byte w() {
        String str = (String) this.f;
        int i2 = this.b;
        while (true) {
            int y = y(i2);
            if (y != -1) {
                char charAt = str.charAt(y);
                if (charAt == ' ' || charAt == 10 || charAt == 13 || charAt == 9) {
                    i2 = y + 1;
                } else {
                    this.b = y;
                    return h.f(charAt);
                }
            } else {
                this.b = y;
                return 10;
            }
        }
    }

    public String x() {
        if (w() != 1) {
            return null;
        }
        String k = k();
        this.d = k;
        return k;
    }

    public int y(int i2) {
        if (i2 < ((String) this.f).length()) {
            return i2;
        }
        return -1;
    }

    public int z() {
        String str = (String) this.f;
        int i2 = this.b;
        if (i2 == -1) {
            return i2;
        }
        while (i2 < str.length() && ((r2 = str.charAt(i2)) == ' ' || r2 == 10 || r2 == 13 || r2 == 9)) {
            i2++;
        }
        this.b = i2;
        return i2;
    }

    public b(a aVar, C0823m mVar, C1074e eVar, int i2) {
        this.f3371a = 2;
        j.e(aVar, "c");
        j.e(eVar, "typeParameterOwner");
        this.f3372c = aVar;
        this.d = mVar;
        this.b = i2;
        ArrayList<Object> typeParameters = eVar.getTypeParameters();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i7 = 0;
        for (Object put : typeParameters) {
            linkedHashMap.put(put, Integer.valueOf(i7));
            i7++;
        }
        this.e = linkedHashMap;
        this.f = ((m) ((C0922a) ((a) this.f3372c).d).f4006a).c(new C0736b(17, this));
    }

    public b(f fVar) {
        this.f3371a = 1;
        this.f3372c = fVar;
        this.d = null;
        this.e = null;
        this.b = 0;
        this.f = null;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, Od.b] */
    public b(Application application, C0732c cVar) {
        this.f3371a = 0;
        this.b = 0;
        Trace.beginSection("Tracker Constructor");
        this.f3372c = application;
        this.d = cVar;
        Context applicationContext = application.getApplicationContext();
        this.e = applicationContext;
        ? obj = new Object();
        obj.f3610c = applicationContext;
        this.f = obj;
        cVar.getClass();
        cVar.d = new G0.e((Object) this);
        Trace.beginAsyncSection("Tracker Constructor SingleThreadExecutor", -757204973);
        i e7 = i.e();
        l lVar = new l(this, cVar, application, 2);
        e7.getClass();
        i.d(lVar);
        Trace.endAsyncSection("Tracker Constructor SingleThreadExecutor", -757204973);
        C0068a.g("Tracker start:6.05.082");
        Trace.endSection();
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [mg.j, java.lang.Object] */
    public b(String str) {
        this.f3371a = 3;
        j.e(str, "source");
        ? obj = new Object();
        obj.f4929a = new Object[8];
        int[] iArr = new int[8];
        for (int i2 = 0; i2 < 8; i2++) {
            iArr[i2] = -1;
        }
        obj.b = iArr;
        obj.f4930c = -1;
        this.f3372c = obj;
        this.e = new StringBuilder();
        this.f = str;
    }
}
