package I0;

import D0.e;
import java.io.EOFException;
import java.io.IOException;
import og.a;
import og.c;
import og.i;
import og.n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends c {

    /* renamed from: o  reason: collision with root package name */
    public static final c f342o = c.a("'\\");

    /* renamed from: p  reason: collision with root package name */
    public static final c f343p = c.a("\"\\");
    public static final c q = c.a("{}[]:, \n\t\r\f/\\;#=");

    /* renamed from: i  reason: collision with root package name */
    public final i f344i;

    /* renamed from: j  reason: collision with root package name */
    public final a f345j;
    public int k = 0;
    public long l;
    public int m;
    public String n;

    static {
        c.a("\n\r");
        c.a("*/");
    }

    public d(i iVar) {
        this.e = new int[32];
        this.f = new String[32];
        this.g = new int[32];
        this.f344i = iVar;
        this.f345j = iVar.d;
        p(6);
    }

    public final int A(boolean z) {
        int i2 = 0;
        while (true) {
            int i7 = i2 + 1;
            i iVar = this.f344i;
            if (iVar.k((long) i7)) {
                long j2 = (long) i2;
                a aVar = this.f345j;
                byte a7 = aVar.a(j2);
                if (a7 == 10 || a7 == 32 || a7 == 13 || a7 == 9) {
                    i2 = i7;
                } else {
                    aVar.h(j2);
                    if (a7 == 47) {
                        if (iVar.k(2)) {
                            v();
                            throw null;
                        }
                    } else if (a7 == 35) {
                        v();
                        throw null;
                    }
                    return a7;
                }
            } else if (!z) {
                return -1;
            } else {
                throw new EOFException("End of input");
            }
        }
    }

    public final String B(c cVar) {
        StringBuilder sb2 = null;
        while (true) {
            long a7 = this.f344i.a(cVar);
            if (a7 != -1) {
                a aVar = this.f345j;
                if (aVar.a(a7) == 92) {
                    if (sb2 == null) {
                        sb2 = new StringBuilder();
                    }
                    sb2.append(aVar.g(a7, n.f5020a));
                    aVar.c();
                    sb2.append(D());
                } else if (sb2 == null) {
                    String g = aVar.g(a7, n.f5020a);
                    aVar.c();
                    return g;
                } else {
                    sb2.append(aVar.g(a7, n.f5020a));
                    aVar.c();
                    return sb2.toString();
                }
            } else {
                t("Unterminated string");
                throw null;
            }
        }
    }

    public final String C() {
        long a7 = this.f344i.a(q);
        int i2 = (a7 > -1 ? 1 : (a7 == -1 ? 0 : -1));
        a aVar = this.f345j;
        aVar.getClass();
        if (i2 != 0) {
            return aVar.g(a7, n.f5020a);
        }
        try {
            return aVar.g(aVar.e, n.f5020a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final char D() {
        int i2;
        i iVar = this.f344i;
        if (iVar.k(1)) {
            a aVar = this.f345j;
            byte c5 = aVar.c();
            if (c5 == 10 || c5 == 34 || c5 == 39 || c5 == 47 || c5 == 92) {
                return (char) c5;
            }
            if (c5 == 98) {
                return 8;
            }
            if (c5 == 102) {
                return 12;
            }
            if (c5 == 110) {
                return 10;
            }
            if (c5 == 114) {
                return 13;
            }
            if (c5 == 116) {
                return 9;
            }
            if (c5 != 117) {
                t("Invalid escape sequence: \\" + ((char) c5));
                throw null;
            } else if (iVar.k(4)) {
                char c6 = 0;
                for (int i7 = 0; i7 < 4; i7++) {
                    byte a7 = aVar.a((long) i7);
                    char c8 = (char) (c6 << 4);
                    if (a7 >= 48 && a7 <= 57) {
                        i2 = a7 - 48;
                    } else if (a7 >= 97 && a7 <= 102) {
                        i2 = a7 - 87;
                    } else if (a7 < 65 || a7 > 70) {
                        t("\\u".concat(aVar.g(4, n.f5020a)));
                        throw null;
                    } else {
                        i2 = a7 - 55;
                    }
                    c6 = (char) (i2 + c8);
                }
                aVar.h(4);
                return c6;
            } else {
                throw new EOFException("Unterminated escape sequence at path " + getPath());
            }
        } else {
            t("Unterminated escape sequence");
            throw null;
        }
    }

    public final void E(c cVar) {
        while (true) {
            long a7 = this.f344i.a(cVar);
            if (a7 != -1) {
                a aVar = this.f345j;
                if (aVar.a(a7) == 92) {
                    aVar.h(a7 + 1);
                    D();
                } else {
                    aVar.h(a7 + 1);
                    return;
                }
            } else {
                t("Unterminated string");
                throw null;
            }
        }
    }

    public final void a() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 3) {
            p(1);
            this.g[this.d - 1] = 0;
            this.k = 0;
            return;
        }
        throw new RuntimeException("Expected BEGIN_ARRAY but was " + n() + " at path " + getPath());
    }

    public final void c() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 1) {
            p(3);
            this.k = 0;
            return;
        }
        throw new RuntimeException("Expected BEGIN_OBJECT but was " + n() + " at path " + getPath());
    }

    public final void close() {
        this.k = 0;
        this.e[0] = 8;
        this.d = 1;
        a aVar = this.f345j;
        aVar.getClass();
        try {
            aVar.h(aVar.e);
            this.f344i.close();
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final void f() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 4) {
            int i7 = this.d;
            this.d = i7 - 1;
            int[] iArr = this.g;
            int i8 = i7 - 2;
            iArr[i8] = iArr[i8] + 1;
            this.k = 0;
            return;
        }
        throw new RuntimeException("Expected END_ARRAY but was " + n() + " at path " + getPath());
    }

    public final void g() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 2) {
            int i7 = this.d;
            int i8 = i7 - 1;
            this.d = i8;
            this.f[i8] = null;
            int[] iArr = this.g;
            int i10 = i7 - 2;
            iArr[i10] = iArr[i10] + 1;
            this.k = 0;
            return;
        }
        throw new RuntimeException("Expected END_OBJECT but was " + n() + " at path " + getPath());
    }

    public final boolean h() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 2 || i2 == 4 || i2 == 18) {
            return false;
        }
        return true;
    }

    public final boolean i() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 5) {
            this.k = 0;
            int[] iArr = this.g;
            int i7 = this.d - 1;
            iArr[i7] = iArr[i7] + 1;
            return true;
        } else if (i2 == 6) {
            this.k = 0;
            int[] iArr2 = this.g;
            int i8 = this.d - 1;
            iArr2[i8] = iArr2[i8] + 1;
            return false;
        } else {
            throw new RuntimeException("Expected a boolean but was " + n() + " at path " + getPath());
        }
    }

    public final double j() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 16) {
            this.k = 0;
            int[] iArr = this.g;
            int i7 = this.d - 1;
            iArr[i7] = iArr[i7] + 1;
            return (double) this.l;
        }
        if (i2 == 17) {
            a aVar = this.f345j;
            aVar.getClass();
            this.n = aVar.g((long) this.m, n.f5020a);
        } else if (i2 == 9) {
            this.n = B(f343p);
        } else if (i2 == 8) {
            this.n = B(f342o);
        } else if (i2 == 10) {
            this.n = C();
        } else if (i2 != 11) {
            throw new RuntimeException("Expected a double but was " + n() + " at path " + getPath());
        }
        this.k = 11;
        try {
            double parseDouble = Double.parseDouble(this.n);
            if (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble)) {
                throw new IOException("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPath());
            }
            this.n = null;
            this.k = 0;
            int[] iArr2 = this.g;
            int i8 = this.d - 1;
            iArr2[i8] = iArr2[i8] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new RuntimeException("Expected a double but was " + this.n + " at path " + getPath());
        }
    }

    public final int l() {
        String str;
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 16) {
            long j2 = this.l;
            int i7 = (int) j2;
            if (j2 == ((long) i7)) {
                this.k = 0;
                int[] iArr = this.g;
                int i8 = this.d - 1;
                iArr[i8] = iArr[i8] + 1;
                return i7;
            }
            throw new RuntimeException("Expected an int but was " + this.l + " at path " + getPath());
        }
        if (i2 == 17) {
            a aVar = this.f345j;
            aVar.getClass();
            this.n = aVar.g((long) this.m, n.f5020a);
        } else if (i2 == 9 || i2 == 8) {
            if (i2 == 9) {
                str = B(f343p);
            } else {
                str = B(f342o);
            }
            this.n = str;
            try {
                int parseInt = Integer.parseInt(str);
                this.k = 0;
                int[] iArr2 = this.g;
                int i10 = this.d - 1;
                iArr2[i10] = iArr2[i10] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            throw new RuntimeException("Expected an int but was " + n() + " at path " + getPath());
        }
        this.k = 11;
        try {
            double parseDouble = Double.parseDouble(this.n);
            int i11 = (int) parseDouble;
            if (((double) i11) == parseDouble) {
                this.n = null;
                this.k = 0;
                int[] iArr3 = this.g;
                int i12 = this.d - 1;
                iArr3[i12] = iArr3[i12] + 1;
                return i11;
            }
            throw new RuntimeException("Expected an int but was " + this.n + " at path " + getPath());
        } catch (NumberFormatException unused2) {
            throw new RuntimeException("Expected an int but was " + this.n + " at path " + getPath());
        }
    }

    public final String m() {
        String str;
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 10) {
            str = C();
        } else if (i2 == 9) {
            str = B(f343p);
        } else if (i2 == 8) {
            str = B(f342o);
        } else if (i2 == 11) {
            str = this.n;
            this.n = null;
        } else if (i2 == 16) {
            str = Long.toString(this.l);
        } else if (i2 == 17) {
            a aVar = this.f345j;
            aVar.getClass();
            str = aVar.g((long) this.m, n.f5020a);
        } else {
            throw new RuntimeException("Expected a string but was " + n() + " at path " + getPath());
        }
        this.k = 0;
        int[] iArr = this.g;
        int i7 = this.d - 1;
        iArr[i7] = iArr[i7] + 1;
        return str;
    }

    public final b n() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        switch (i2) {
            case 1:
                return b.BEGIN_OBJECT;
            case 2:
                return b.END_OBJECT;
            case 3:
                return b.BEGIN_ARRAY;
            case 4:
                return b.END_ARRAY;
            case 5:
            case 6:
                return b.BOOLEAN;
            case 7:
                return b.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return b.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return b.NAME;
            case 16:
            case 17:
                return b.NUMBER;
            case 18:
                return b.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00cd A[EDGE_INSN: B:70:0x00cd->B:54:0x00cd ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int q(D0.e r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            int r2 = r0.k
            if (r2 != 0) goto L_0x000c
            int r2 = r0.w()
        L_0x000c:
            r3 = 12
            r4 = -1
            if (r2 < r3) goto L_0x0129
            r3 = 15
            if (r2 <= r3) goto L_0x0017
            goto L_0x0129
        L_0x0017:
            if (r2 != r3) goto L_0x0020
            java.lang.String r2 = r0.n
            int r0 = r0.x(r2, r1)
            return r0
        L_0x0020:
            java.lang.Object r2 = r1.f
            og.f r2 = (og.f) r2
            og.i r5 = r0.f344i
            og.a r6 = r5.d
            boolean r7 = r5.f
            if (r7 != 0) goto L_0x0121
        L_0x002c:
            og.j r7 = r6.d
            if (r7 != 0) goto L_0x0031
            goto L_0x0073
        L_0x0031:
            byte[] r8 = r7.f5016a
            int r9 = r7.b
            int r10 = r7.f5017c
            int[] r11 = r2.e
            r13 = -1
            r15 = r7
            r16 = r13
            r14 = 0
        L_0x003e:
            int r17 = r14 + 1
            r18 = r11[r14]
            int r14 = r14 + 2
            r12 = r11[r17]
            if (r12 == r13) goto L_0x004a
            r16 = r12
        L_0x004a:
            if (r15 != 0) goto L_0x004d
            goto L_0x0073
        L_0x004d:
            if (r18 >= 0) goto L_0x008e
            int r18 = r18 * -1
            int r12 = r18 + r14
        L_0x0053:
            int r13 = r9 + 1
            byte r9 = r8[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r3 = r14 + 1
            r14 = r11[r14]
            if (r9 == r14) goto L_0x0060
            goto L_0x0098
        L_0x0060:
            if (r3 != r12) goto L_0x0064
            r9 = 1
            goto L_0x0065
        L_0x0064:
            r9 = 0
        L_0x0065:
            if (r13 != r10) goto L_0x007e
            og.j r8 = r15.f
            int r10 = r8.b
            byte[] r13 = r8.f5016a
            int r14 = r8.f5017c
            if (r8 != r7) goto L_0x007b
            if (r9 != 0) goto L_0x0078
        L_0x0073:
            r16 = -2
        L_0x0075:
            r3 = r16
            goto L_0x00b9
        L_0x0078:
            r8 = r13
            r15 = 0
            goto L_0x0080
        L_0x007b:
            r15 = r8
            r8 = r13
            goto L_0x0080
        L_0x007e:
            r14 = r10
            r10 = r13
        L_0x0080:
            if (r9 == 0) goto L_0x0087
            r3 = r11[r3]
            r9 = r10
            r10 = r14
            goto L_0x00b7
        L_0x0087:
            r9 = r10
            r10 = r14
            r13 = -1
            r14 = r3
            r3 = 15
            goto L_0x0053
        L_0x008e:
            int r3 = r9 + 1
            byte r9 = r8[r9]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r12 = r14 + r18
        L_0x0096:
            if (r14 != r12) goto L_0x0099
        L_0x0098:
            goto L_0x0075
        L_0x0099:
            r13 = r11[r14]
            if (r9 != r13) goto L_0x011b
            int r14 = r14 + r18
            r9 = r11[r14]
            if (r3 != r10) goto L_0x00b2
            og.j r15 = r15.f
            int r3 = r15.b
            byte[] r8 = r15.f5016a
            int r10 = r15.f5017c
            if (r15 != r7) goto L_0x00b2
            r15 = r9
            r9 = r3
            r3 = r15
            r15 = 0
            goto L_0x00b7
        L_0x00b2:
            r19 = r9
            r9 = r3
            r3 = r19
        L_0x00b7:
            if (r3 < 0) goto L_0x0114
        L_0x00b9:
            if (r3 != r4) goto L_0x00bc
            goto L_0x00cd
        L_0x00bc:
            r7 = -2
            if (r3 != r7) goto L_0x00d3
            og.m r3 = r5.e
            r7 = 8192(0x2000, double:4.0474E-320)
            long r7 = r3.e(r6, r7)
            r9 = -1
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x00cf
        L_0x00cd:
            r3 = r4
            goto L_0x00df
        L_0x00cf:
            r3 = 15
            goto L_0x002c
        L_0x00d3:
            og.c[] r2 = r2.d
            r2 = r2[r3]
            int r2 = r2.g()
            long r7 = (long) r2
            r6.h(r7)
        L_0x00df:
            if (r3 == r4) goto L_0x00f3
            r2 = 0
            r0.k = r2
            java.lang.String[] r2 = r0.f
            int r0 = r0.d
            int r0 = r0 + -1
            java.lang.Object r1 = r1.e
            java.lang.String[] r1 = (java.lang.String[]) r1
            r1 = r1[r3]
            r2[r0] = r1
            return r3
        L_0x00f3:
            java.lang.String[] r2 = r0.f
            int r3 = r0.d
            int r3 = r3 + -1
            r2 = r2[r3]
            java.lang.String r3 = r0.z()
            int r1 = r0.x(r3, r1)
            if (r1 != r4) goto L_0x0113
            r13 = 15
            r0.k = r13
            r0.n = r3
            java.lang.String[] r3 = r0.f
            int r0 = r0.d
            int r0 = r0 + -1
            r3[r0] = r2
        L_0x0113:
            return r1
        L_0x0114:
            r13 = 15
            int r14 = -r3
            r3 = r13
            r13 = -1
            goto L_0x003e
        L_0x011b:
            r13 = 15
            int r14 = r14 + 1
            goto L_0x0096
        L_0x0121:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "closed"
            r0.<init>(r1)
            throw r0
        L_0x0129:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: I0.d.q(D0.e):int");
    }

    public final void r() {
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 14) {
            long a7 = this.f344i.a(q);
            int i7 = (a7 > -1 ? 1 : (a7 == -1 ? 0 : -1));
            a aVar = this.f345j;
            if (i7 == 0) {
                a7 = aVar.e;
            }
            aVar.h(a7);
        } else if (i2 == 13) {
            E(f343p);
        } else if (i2 == 12) {
            E(f342o);
        } else if (i2 != 15) {
            throw new RuntimeException("Expected a name but was " + n() + " at path " + getPath());
        }
        this.k = 0;
        this.f[this.d - 1] = "null";
    }

    public final void s() {
        int i2 = 0;
        do {
            int i7 = this.k;
            if (i7 == 0) {
                i7 = w();
            }
            if (i7 == 3) {
                p(1);
            } else if (i7 == 1) {
                p(3);
            } else {
                if (i7 == 4) {
                    i2--;
                    if (i2 >= 0) {
                        this.d--;
                    } else {
                        throw new RuntimeException("Expected a value but was " + n() + " at path " + getPath());
                    }
                } else if (i7 == 2) {
                    i2--;
                    if (i2 >= 0) {
                        this.d--;
                    } else {
                        throw new RuntimeException("Expected a value but was " + n() + " at path " + getPath());
                    }
                } else {
                    a aVar = this.f345j;
                    if (i7 == 14 || i7 == 10) {
                        long a7 = this.f344i.a(q);
                        if (a7 == -1) {
                            a7 = aVar.e;
                        }
                        aVar.h(a7);
                    } else if (i7 == 9 || i7 == 13) {
                        E(f343p);
                    } else if (i7 == 8 || i7 == 12) {
                        E(f342o);
                    } else if (i7 == 17) {
                        aVar.h((long) this.m);
                    } else if (i7 == 18) {
                        throw new RuntimeException("Expected a value but was " + n() + " at path " + getPath());
                    }
                }
                this.k = 0;
            }
            i2++;
            this.k = 0;
        } while (i2 != 0);
        int[] iArr = this.g;
        int i8 = this.d - 1;
        iArr[i8] = iArr[i8] + 1;
        this.f[i8] = "null";
    }

    public final String toString() {
        return "JsonReader(" + this.f344i + ")";
    }

    public final void v() {
        t("Use JsonReader.setLenient(true) to accept malformed JSON");
        throw null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0196, code lost:
        if (y(r10) != false) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0198, code lost:
        if (r1 != 2) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x019a, code lost:
        if (r4 == false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01a0, code lost:
        if (r8 != Long.MIN_VALUE) goto L_0x01a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01a2, code lost:
        if (r13 == false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x01a6, code lost:
        if (r8 != r17) goto L_0x01aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x01a8, code lost:
        if (r13 != false) goto L_0x01b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x01aa, code lost:
        if (r13 == false) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x01ad, code lost:
        r8 = -r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x01ae, code lost:
        r0.l = r8;
        r7.h((long) r2);
        r9 = 16;
        r0.k = 16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01b9, code lost:
        if (r1 == 2) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x01bc, code lost:
        if (r1 == 4) goto L_0x01c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x01bf, code lost:
        if (r1 != 7) goto L_0x0156;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x01c1, code lost:
        r0.m = r2;
        r9 = 17;
        r0.k = 17;
     */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0116 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int w() {
        /*
            r22 = this;
            r0 = r22
            int[] r1 = r0.e
            int r2 = r0.d
            r3 = 1
            int r2 = r2 - r3
            r4 = r1[r2]
            r8 = 93
            r9 = 0
            r10 = 6
            r11 = 3
            r12 = 59
            r13 = 44
            r14 = 7
            r15 = 4
            r16 = 0
            r5 = 5
            r6 = 2
            og.a r7 = r0.f345j
            if (r4 != r3) goto L_0x0020
            r1[r2] = r6
            goto L_0x007c
        L_0x0020:
            if (r4 != r6) goto L_0x003c
            int r1 = r0.A(r3)
            r7.c()
            if (r1 == r13) goto L_0x007c
            if (r1 == r12) goto L_0x0038
            if (r1 != r8) goto L_0x0032
            r0.k = r15
            return r15
        L_0x0032:
            java.lang.String r1 = "Unterminated array"
            r0.t(r1)
            throw r16
        L_0x0038:
            r0.v()
            throw r16
        L_0x003c:
            if (r4 == r11) goto L_0x0040
            if (r4 != r5) goto L_0x0044
        L_0x0040:
            r19 = r15
            goto L_0x023e
        L_0x0044:
            if (r4 != r15) goto L_0x0061
            r1[r2] = r5
            int r1 = r0.A(r3)
            r7.c()
            r2 = 58
            if (r1 == r2) goto L_0x007c
            r2 = 61
            if (r1 == r2) goto L_0x005d
            java.lang.String r1 = "Expected ':'"
            r0.t(r1)
            throw r16
        L_0x005d:
            r0.v()
            throw r16
        L_0x0061:
            if (r4 != r10) goto L_0x0066
            r1[r2] = r14
            goto L_0x007c
        L_0x0066:
            if (r4 != r14) goto L_0x0078
            int r1 = r0.A(r9)
            r2 = -1
            if (r1 != r2) goto L_0x0074
            r1 = 18
            r0.k = r1
            return r1
        L_0x0074:
            r0.v()
            throw r16
        L_0x0078:
            r1 = 8
            if (r4 == r1) goto L_0x0236
        L_0x007c:
            int r1 = r0.A(r3)
            r2 = 34
            if (r1 == r2) goto L_0x022e
            r2 = 39
            if (r1 == r2) goto L_0x022a
            if (r1 == r13) goto L_0x021b
            if (r1 == r12) goto L_0x021b
            r2 = 91
            if (r1 == r2) goto L_0x0214
            if (r1 == r8) goto L_0x020b
            r2 = 123(0x7b, float:1.72E-43)
            if (r1 == r2) goto L_0x0205
            r1 = 0
            byte r4 = r7.a(r1)
            r8 = 116(0x74, float:1.63E-43)
            og.i r12 = r0.f344i
            if (r4 == r8) goto L_0x00c9
            r8 = 84
            if (r4 != r8) goto L_0x00a7
            goto L_0x00c9
        L_0x00a7:
            r8 = 102(0x66, float:1.43E-43)
            if (r4 == r8) goto L_0x00c3
            r8 = 70
            if (r4 != r8) goto L_0x00b0
            goto L_0x00c3
        L_0x00b0:
            r8 = 110(0x6e, float:1.54E-43)
            if (r4 == r8) goto L_0x00bd
            r8 = 78
            if (r4 != r8) goto L_0x00b9
            goto L_0x00bd
        L_0x00b9:
            r17 = r1
            r13 = r9
            goto L_0x0114
        L_0x00bd:
            java.lang.String r4 = "null"
            java.lang.String r8 = "NULL"
            r13 = r14
            goto L_0x00cf
        L_0x00c3:
            java.lang.String r4 = "false"
            java.lang.String r8 = "FALSE"
            r13 = r10
            goto L_0x00cf
        L_0x00c9:
            java.lang.String r4 = "true"
            java.lang.String r8 = "TRUE"
            r13 = r5
        L_0x00cf:
            int r9 = r4.length()
            r17 = r1
            r1 = r3
        L_0x00d6:
            if (r1 >= r9) goto L_0x00f9
            int r2 = r1 + 1
            long r14 = (long) r2
            boolean r14 = r12.k(r14)
            if (r14 != 0) goto L_0x00e3
        L_0x00e1:
            r13 = 0
            goto L_0x0114
        L_0x00e3:
            long r14 = (long) r1
            byte r14 = r7.a(r14)
            char r15 = r4.charAt(r1)
            if (r14 == r15) goto L_0x00f5
            char r1 = r8.charAt(r1)
            if (r14 == r1) goto L_0x00f5
            goto L_0x00e1
        L_0x00f5:
            r1 = r2
            r14 = 7
            r15 = 4
            goto L_0x00d6
        L_0x00f9:
            int r1 = r9 + 1
            long r1 = (long) r1
            boolean r1 = r12.k(r1)
            if (r1 == 0) goto L_0x010e
            long r1 = (long) r9
            byte r1 = r7.a(r1)
            boolean r1 = r0.y(r1)
            if (r1 == 0) goto L_0x010e
            goto L_0x00e1
        L_0x010e:
            long r1 = (long) r9
            r7.h(r1)
            r0.k = r13
        L_0x0114:
            if (r13 == 0) goto L_0x0117
            return r13
        L_0x0117:
            r4 = r3
            r8 = r17
            r1 = 0
            r2 = 0
            r13 = 0
        L_0x011d:
            int r14 = r2 + 1
            long r10 = (long) r14
            boolean r10 = r12.k(r10)
            if (r10 != 0) goto L_0x0128
            goto L_0x0198
        L_0x0128:
            long r10 = (long) r2
            byte r10 = r7.a(r10)
            r11 = 43
            if (r10 == r11) goto L_0x01e2
            r11 = 69
            if (r10 == r11) goto L_0x01d9
            r11 = 101(0x65, float:1.42E-43)
            if (r10 == r11) goto L_0x01d9
            r11 = 45
            if (r10 == r11) goto L_0x01ce
            r11 = 46
            if (r10 == r11) goto L_0x01c8
            r11 = 48
            if (r10 < r11) goto L_0x0192
            r11 = 57
            if (r10 <= r11) goto L_0x014a
            goto L_0x0192
        L_0x014a:
            if (r1 == r3) goto L_0x014e
            if (r1 != 0) goto L_0x0150
        L_0x014e:
            r15 = 6
            goto L_0x018c
        L_0x0150:
            if (r1 != r6) goto L_0x017c
            int r2 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r2 != 0) goto L_0x0159
        L_0x0156:
            r9 = 0
            goto L_0x01ec
        L_0x0159:
            r20 = 10
            long r20 = r20 * r8
            int r10 = r10 + -48
            long r10 = (long) r10
            long r20 = r20 - r10
            r10 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 > 0) goto L_0x0174
            if (r2 != 0) goto L_0x0172
            int r2 = (r20 > r8 ? 1 : (r20 == r8 ? 0 : -1))
            if (r2 >= 0) goto L_0x0172
            goto L_0x0174
        L_0x0172:
            r2 = 0
            goto L_0x0175
        L_0x0174:
            r2 = r3
        L_0x0175:
            r4 = r4 & r2
            r8 = r20
        L_0x0178:
            r10 = 7
            r15 = 6
            goto L_0x01e7
        L_0x017c:
            r2 = 3
            if (r1 != r2) goto L_0x0181
            r1 = 4
            goto L_0x0178
        L_0x0181:
            r15 = 6
            if (r1 == r5) goto L_0x018a
            if (r1 != r15) goto L_0x0187
            goto L_0x018a
        L_0x0187:
            r10 = 7
            goto L_0x01e7
        L_0x018a:
            r1 = 7
            goto L_0x0187
        L_0x018c:
            int r10 = r10 + -48
            int r1 = -r10
            long r8 = (long) r1
            r1 = r6
            goto L_0x0187
        L_0x0192:
            boolean r3 = r0.y(r10)
            if (r3 != 0) goto L_0x0156
        L_0x0198:
            if (r1 != r6) goto L_0x01b9
            if (r4 == 0) goto L_0x01b9
            r3 = -9223372036854775808
            int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x01a4
            if (r13 == 0) goto L_0x01b9
        L_0x01a4:
            int r3 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r3 != 0) goto L_0x01aa
            if (r13 != 0) goto L_0x01b9
        L_0x01aa:
            if (r13 == 0) goto L_0x01ad
            goto L_0x01ae
        L_0x01ad:
            long r8 = -r8
        L_0x01ae:
            r0.l = r8
            long r1 = (long) r2
            r7.h(r1)
            r9 = 16
            r0.k = r9
            goto L_0x01ec
        L_0x01b9:
            if (r1 == r6) goto L_0x01c1
            r3 = 4
            if (r1 == r3) goto L_0x01c1
            r10 = 7
            if (r1 != r10) goto L_0x0156
        L_0x01c1:
            r0.m = r2
            r9 = 17
            r0.k = r9
            goto L_0x01ec
        L_0x01c8:
            r10 = 7
            r15 = 6
            if (r1 != r6) goto L_0x0156
            r1 = 3
            goto L_0x01e7
        L_0x01ce:
            r10 = 7
            r15 = 6
            if (r1 != 0) goto L_0x01d5
            r1 = r3
            r13 = r1
            goto L_0x01e7
        L_0x01d5:
            if (r1 != r5) goto L_0x0156
        L_0x01d7:
            r1 = r15
            goto L_0x01e7
        L_0x01d9:
            r10 = 7
            r15 = 6
            if (r1 == r6) goto L_0x01e0
            r2 = 4
            if (r1 != r2) goto L_0x0156
        L_0x01e0:
            r1 = r5
            goto L_0x01e7
        L_0x01e2:
            r10 = 7
            r15 = 6
            if (r1 != r5) goto L_0x0156
            goto L_0x01d7
        L_0x01e7:
            r2 = r14
            r10 = r15
            r11 = 3
            goto L_0x011d
        L_0x01ec:
            if (r9 == 0) goto L_0x01ef
            return r9
        L_0x01ef:
            r1 = r17
            byte r1 = r7.a(r1)
            boolean r1 = r0.y(r1)
            if (r1 != 0) goto L_0x0201
            java.lang.String r1 = "Expected value"
            r0.t(r1)
            throw r16
        L_0x0201:
            r0.v()
            throw r16
        L_0x0205:
            r7.c()
            r0.k = r3
            return r3
        L_0x020b:
            if (r4 != r3) goto L_0x021b
            r7.c()
            r2 = 4
            r0.k = r2
            return r2
        L_0x0214:
            r7.c()
            r2 = 3
            r0.k = r2
            return r2
        L_0x021b:
            if (r4 == r3) goto L_0x0226
            if (r4 != r6) goto L_0x0220
            goto L_0x0226
        L_0x0220:
            java.lang.String r1 = "Unexpected value"
            r0.t(r1)
            throw r16
        L_0x0226:
            r0.v()
            throw r16
        L_0x022a:
            r0.v()
            throw r16
        L_0x022e:
            r7.c()
            r1 = 9
            r0.k = r1
            return r1
        L_0x0236:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "JsonReader is closed"
            r0.<init>(r1)
            throw r0
        L_0x023e:
            r1[r2] = r19
            r1 = 125(0x7d, float:1.75E-43)
            if (r4 != r5) goto L_0x025e
            int r2 = r0.A(r3)
            r7.c()
            if (r2 == r13) goto L_0x025e
            if (r2 == r12) goto L_0x025a
            if (r2 != r1) goto L_0x0254
            r0.k = r6
            return r6
        L_0x0254:
            java.lang.String r1 = "Unterminated object"
            r0.t(r1)
            throw r16
        L_0x025a:
            r0.v()
            throw r16
        L_0x025e:
            int r2 = r0.A(r3)
            r3 = 34
            if (r2 == r3) goto L_0x0285
            r3 = 39
            if (r2 == r3) goto L_0x027e
            if (r2 != r1) goto L_0x027a
            if (r4 == r5) goto L_0x0274
            r7.c()
            r0.k = r6
            return r6
        L_0x0274:
            java.lang.String r1 = "Expected name"
            r0.t(r1)
            throw r16
        L_0x027a:
            r0.v()
            throw r16
        L_0x027e:
            r7.c()
            r0.v()
            throw r16
        L_0x0285:
            r7.c()
            r1 = 13
            r0.k = r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: I0.d.w():int");
    }

    public final int x(String str, e eVar) {
        int length = ((String[]) eVar.e).length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(((String[]) eVar.e)[i2])) {
                this.k = 0;
                this.f[this.d - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    public final boolean y(int i2) {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (!(i2 == 47 || i2 == 61)) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        v();
        throw null;
    }

    public final String z() {
        String str;
        int i2 = this.k;
        if (i2 == 0) {
            i2 = w();
        }
        if (i2 == 14) {
            str = C();
        } else if (i2 == 13) {
            str = B(f343p);
        } else if (i2 == 12) {
            str = B(f342o);
        } else if (i2 == 15) {
            str = this.n;
        } else {
            throw new RuntimeException("Expected a name but was " + n() + " at path " + getPath());
        }
        this.k = 0;
        this.f[this.d - 1] = str;
        return str;
    }
}
