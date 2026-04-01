package Df;

import B1.b;
import Ff.m;
import Qe.C0822l;
import X2.w;
import java.io.ByteArrayInputStream;
import java.util.List;
import kotlin.jvm.internal.j;
import nf.C1204a;
import nf.C1209f;
import nf.C1211h;
import v0.C0295e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n {

    /* renamed from: j  reason: collision with root package name */
    public static /* synthetic */ int[] f3357j;

    /* renamed from: a  reason: collision with root package name */
    public Object f3358a;
    public Object b;

    /* renamed from: c  reason: collision with root package name */
    public Object f3359c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    /* renamed from: h  reason: collision with root package name */
    public Object f3360h;

    /* renamed from: i  reason: collision with root package name */
    public Object f3361i;

    public n(ByteArrayInputStream byteArrayInputStream) {
        this.f3358a = byteArrayInputStream;
        this.b = new w(this, byteArrayInputStream);
        this.f3359c = new w(this, byteArrayInputStream);
        this.d = new w(this, byteArrayInputStream);
        this.e = new w(this, byteArrayInputStream);
        this.f = new w(this, byteArrayInputStream);
        this.g = new w(this, byteArrayInputStream);
        this.f3360h = new w(this, byteArrayInputStream);
        this.f3361i = new C0295e(this, byteArrayInputStream);
    }

    public n a(C0822l lVar, List list, C1209f fVar, b bVar, C1211h hVar, C1204a aVar) {
        C1211h hVar2;
        C1204a aVar2 = aVar;
        j.e(list, "typeParameterProtos");
        j.e(fVar, "nameResolver");
        j.e(hVar, "versionRequirementTable");
        j.e(aVar2, "metadataVersion");
        l lVar2 = (l) this.f3358a;
        int i2 = aVar2.b;
        if ((i2 != 1 || aVar2.f4952c < 4) && i2 <= 1) {
            hVar2 = (C1211h) this.e;
        } else {
            hVar2 = hVar;
        }
        return new n(lVar2, fVar, lVar, bVar, hVar2, aVar2, (m) this.g, (H) this.f3360h, list);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0034 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0044 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0056 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
    /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0066 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public w0.C0304e c() {
        /*
            r20 = this;
            r0 = r20
            java.lang.Object r1 = r0.f3358a     // Catch:{ IOException -> 0x04d1 }
            java.io.ByteArrayInputStream r1 = (java.io.ByteArrayInputStream) r1     // Catch:{ IOException -> 0x04d1 }
            int r1 = r1.read()     // Catch:{ IOException -> 0x04d1 }
            r2 = 0
            r3 = -1
            if (r1 != r3) goto L_0x000f
            return r2
        L_0x000f:
            int[] r4 = f3357j
            r5 = 8
            r6 = 3
            r7 = 7
            r8 = 4
            r9 = 6
            r10 = 5
            r11 = 2
            r12 = 1
            if (r4 == 0) goto L_0x001d
            goto L_0x0070
        L_0x001d:
            w0.i[] r4 = w0.C0308i.values()
            int r4 = r4.length
            int[] r4 = new int[r4]
            w0.i r13 = w0.C0308i.ARRAY     // Catch:{ NoSuchFieldError -> 0x002c }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x002c }
            r4[r13] = r9     // Catch:{ NoSuchFieldError -> 0x002c }
        L_0x002c:
            w0.i r13 = w0.C0308i.BYTE_STRING     // Catch:{ NoSuchFieldError -> 0x0034 }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0034 }
            r4[r13] = r8     // Catch:{ NoSuchFieldError -> 0x0034 }
        L_0x0034:
            w0.i r13 = w0.C0308i.INVALID     // Catch:{ NoSuchFieldError -> 0x003c }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x003c }
            r4[r13] = r12     // Catch:{ NoSuchFieldError -> 0x003c }
        L_0x003c:
            w0.i r13 = w0.C0308i.MAP     // Catch:{ NoSuchFieldError -> 0x0044 }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
            r4[r13] = r7     // Catch:{ NoSuchFieldError -> 0x0044 }
        L_0x0044:
            w0.i r13 = w0.C0308i.NEGATIVE_INTEGER     // Catch:{ NoSuchFieldError -> 0x004c }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x004c }
            r4[r13] = r6     // Catch:{ NoSuchFieldError -> 0x004c }
        L_0x004c:
            w0.i r13 = w0.C0308i.SPECIAL     // Catch:{ NoSuchFieldError -> 0x0056 }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
            r14 = 9
            r4[r13] = r14     // Catch:{ NoSuchFieldError -> 0x0056 }
        L_0x0056:
            w0.i r13 = w0.C0308i.TAG     // Catch:{ NoSuchFieldError -> 0x005e }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x005e }
            r4[r13] = r5     // Catch:{ NoSuchFieldError -> 0x005e }
        L_0x005e:
            w0.i r13 = w0.C0308i.UNICODE_STRING     // Catch:{ NoSuchFieldError -> 0x0066 }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x0066 }
            r4[r13] = r10     // Catch:{ NoSuchFieldError -> 0x0066 }
        L_0x0066:
            w0.i r13 = w0.C0308i.UNSIGNED_INTEGER     // Catch:{ NoSuchFieldError -> 0x006e }
            int r13 = r13.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
            r4[r13] = r11     // Catch:{ NoSuchFieldError -> 0x006e }
        L_0x006e:
            f3357j = r4
        L_0x0070:
            int r13 = r1 >> 5
            switch(r13) {
                case 0: goto L_0x008d;
                case 1: goto L_0x008a;
                case 2: goto L_0x0087;
                case 3: goto L_0x0084;
                case 4: goto L_0x0081;
                case 5: goto L_0x007e;
                case 6: goto L_0x007b;
                case 7: goto L_0x0078;
                default: goto L_0x0075;
            }
        L_0x0075:
            w0.i r13 = w0.C0308i.INVALID
            goto L_0x008f
        L_0x0078:
            w0.i r13 = w0.C0308i.SPECIAL
            goto L_0x008f
        L_0x007b:
            w0.i r13 = w0.C0308i.TAG
            goto L_0x008f
        L_0x007e:
            w0.i r13 = w0.C0308i.MAP
            goto L_0x008f
        L_0x0081:
            w0.i r13 = w0.C0308i.ARRAY
            goto L_0x008f
        L_0x0084:
            w0.i r13 = w0.C0308i.UNICODE_STRING
            goto L_0x008f
        L_0x0087:
            w0.i r13 = w0.C0308i.BYTE_STRING
            goto L_0x008f
        L_0x008a:
            w0.i r13 = w0.C0308i.NEGATIVE_INTEGER
            goto L_0x008f
        L_0x008d:
            w0.i r13 = w0.C0308i.UNSIGNED_INTEGER
        L_0x008f:
            int r13 = r13.ordinal()
            r4 = r4[r13]
            java.lang.String r13 = "Unexpected major type "
            r14 = 1
            r16 = 0
            r18 = -1
            java.lang.String r2 = "Unexpected end of stream"
            r3 = 0
            switch(r4) {
                case 2: goto L_0x04c1;
                case 3: goto L_0x04ab;
                case 4: goto L_0x0445;
                case 5: goto L_0x03ce;
                case 6: goto L_0x0376;
                case 7: goto L_0x0301;
                case 8: goto L_0x020a;
                case 9: goto L_0x00af;
                default: goto L_0x00a3;
            }
        L_0x00a3:
            u0.a r0 = new u0.a
            java.lang.String r2 = "Not implemented major type "
            java.lang.String r1 = c0.C0086a.i(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x00af:
            java.lang.Object r0 = r0.f3361i
            v0.e r0 = (v0.C0295e) r0
            r0.getClass()
            int[] r2 = v0.C0294d.b
            r4 = 31
            r1 = r1 & r4
            switch(r1) {
                case 24: goto L_0x00d0;
                case 25: goto L_0x00cd;
                case 26: goto L_0x00ca;
                case 27: goto L_0x00c7;
                case 28: goto L_0x00c4;
                case 29: goto L_0x00c4;
                case 30: goto L_0x00c4;
                case 31: goto L_0x00c1;
                default: goto L_0x00be;
            }
        L_0x00be:
            w0.o r13 = w0.o.SIMPLE_VALUE
            goto L_0x00d2
        L_0x00c1:
            w0.o r13 = w0.o.BREAK
            goto L_0x00d2
        L_0x00c4:
            w0.o r13 = w0.o.UNALLOCATED
            goto L_0x00d2
        L_0x00c7:
            w0.o r13 = w0.o.IEEE_754_DOUBLE_PRECISION_FLOAT
            goto L_0x00d2
        L_0x00ca:
            w0.o r13 = w0.o.IEEE_754_SINGLE_PRECISION_FLOAT
            goto L_0x00d2
        L_0x00cd:
            w0.o r13 = w0.o.IEEE_754_HALF_PRECISION_FLOAT
            goto L_0x00d2
        L_0x00d0:
            w0.o r13 = w0.o.SIMPLE_VALUE_NEXT_BYTE
        L_0x00d2:
            int r13 = r13.ordinal()
            r2 = r2[r13]
            java.lang.String r13 = "Not implemented"
            switch(r2) {
                case 1: goto L_0x0207;
                case 2: goto L_0x01c8;
                case 3: goto L_0x0160;
                case 4: goto L_0x0137;
                case 5: goto L_0x00ee;
                case 6: goto L_0x00e3;
                default: goto L_0x00dd;
            }
        L_0x00dd:
            u0.a r0 = new u0.a
            r0.<init>(r13)
            throw r0
        L_0x00e3:
            w0.l r1 = new w0.l
            int r0 = r0.d()
            r1.<init>((int) r0)
            goto L_0x0209
        L_0x00ee:
            v0.b r0 = r0.e
            byte[] r0 = r0.e(r5)
            byte r1 = r0[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            long r1 = (long) r1
            long r1 = r1 << r5
            byte r3 = r0[r12]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r3 = r0[r11]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r3 = r0[r6]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r3 = r0[r8]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r3 = r0[r10]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r3 = r0[r9]
            r3 = r3 & 255(0xff, float:3.57E-43)
            long r3 = (long) r3
            long r1 = r1 | r3
            long r1 = r1 << r5
            byte r0 = r0[r7]
            r0 = r0 & 255(0xff, float:3.57E-43)
            long r3 = (long) r0
            long r0 = r1 | r3
            w0.f r2 = new w0.f
            double r0 = java.lang.Double.longBitsToDouble(r0)
            r2.<init>(r0)
            r1 = r2
            goto L_0x0209
        L_0x0137:
            v0.b r0 = r0.d
            byte[] r0 = r0.e(r8)
            byte r1 = r0[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r5
            byte r2 = r0[r12]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1 | r2
            int r1 = r1 << r5
            byte r2 = r0[r11]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r1 = r1 | r2
            int r1 = r1 << r5
            byte r0 = r0[r6]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | r1
            w0.g r1 = new w0.g
            float r0 = java.lang.Float.intBitsToFloat(r0)
            w0.o r2 = w0.o.IEEE_754_SINGLE_PRECISION_FLOAT
            r1.<init>(r2, r0)
            goto L_0x0209
        L_0x0160:
            v0.b r0 = r0.f1952c
            byte[] r0 = r0.e(r11)
            byte r1 = r0[r3]
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << r5
            byte r0 = r0[r12]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r0 = r0 | r1
            w0.g r1 = new w0.g
            r2 = 32768(0x8000, float:4.5918E-41)
            r2 = r2 & r0
            int r2 = r2 >> 15
            r3 = r0 & 31744(0x7c00, float:4.4483E-41)
            int r3 = r3 >> 10
            r0 = r0 & 1023(0x3ff, float:1.434E-42)
            r5 = 4621819117588971520(0x4024000000000000, double:10.0)
            r7 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r3 != 0) goto L_0x019a
            if (r2 == 0) goto L_0x0188
            r3 = -1
            goto L_0x0189
        L_0x0188:
            r3 = r12
        L_0x0189:
            double r2 = (double) r3
            r9 = -4599301119452119040(0xc02c000000000000, double:-14.0)
            double r9 = java.lang.Math.pow(r7, r9)
            double r9 = r9 * r2
            double r2 = (double) r0
            double r4 = java.lang.Math.pow(r7, r5)
            double r2 = r2 / r4
            double r2 = r2 * r9
            float r0 = (float) r2
            goto L_0x01c2
        L_0x019a:
            if (r3 != r4) goto L_0x01ab
            if (r0 == 0) goto L_0x01a1
            r0 = 2143289344(0x7fc00000, float:NaN)
            goto L_0x01c2
        L_0x01a1:
            if (r2 == 0) goto L_0x01a5
            r3 = -1
            goto L_0x01a6
        L_0x01a5:
            r3 = r12
        L_0x01a6:
            float r0 = (float) r3
            r2 = 2139095040(0x7f800000, float:Infinity)
            float r0 = r0 * r2
            goto L_0x01c2
        L_0x01ab:
            if (r2 == 0) goto L_0x01ae
            r12 = -1
        L_0x01ae:
            double r9 = (double) r12
            int r3 = r3 + -15
            double r2 = (double) r3
            double r2 = java.lang.Math.pow(r7, r2)
            double r2 = r2 * r9
            double r9 = (double) r0
            double r4 = java.lang.Math.pow(r7, r5)
            double r9 = r9 / r4
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r9 = r9 + r4
            double r9 = r9 * r2
            float r0 = (float) r9
        L_0x01c2:
            w0.o r2 = w0.o.IEEE_754_HALF_PRECISION_FLOAT
            r1.<init>(r2, r0)
            goto L_0x0209
        L_0x01c8:
            int[] r0 = v0.C0294d.f1951a
            switch(r1) {
                case 20: goto L_0x01dc;
                case 21: goto L_0x01d9;
                case 22: goto L_0x01d6;
                case 23: goto L_0x01d3;
                case 24: goto L_0x01d0;
                case 25: goto L_0x01d0;
                case 26: goto L_0x01d0;
                case 27: goto L_0x01d0;
                case 28: goto L_0x01d0;
                case 29: goto L_0x01d0;
                case 30: goto L_0x01d0;
                case 31: goto L_0x01d0;
                default: goto L_0x01cd;
            }
        L_0x01cd:
            w0.m r2 = w0.m.UNALLOCATED
            goto L_0x01de
        L_0x01d0:
            w0.m r2 = w0.m.RESERVED
            goto L_0x01de
        L_0x01d3:
            w0.m r2 = w0.m.UNDEFINED
            goto L_0x01de
        L_0x01d6:
            w0.m r2 = w0.m.NULL
            goto L_0x01de
        L_0x01d9:
            w0.m r2 = w0.m.TRUE
            goto L_0x01de
        L_0x01dc:
            w0.m r2 = w0.m.FALSE
        L_0x01de:
            int r2 = r2.ordinal()
            r0 = r0[r2]
            if (r0 == r12) goto L_0x0204
            if (r0 == r11) goto L_0x0201
            if (r0 == r6) goto L_0x01fe
            if (r0 == r8) goto L_0x01fb
            if (r0 != r10) goto L_0x01f5
            w0.l r0 = new w0.l
            r0.<init>((int) r1)
            r1 = r0
            goto L_0x0209
        L_0x01f5:
            u0.a r0 = new u0.a
            r0.<init>(r13)
            throw r0
        L_0x01fb:
            w0.l r1 = w0.C0311l.f1988j
            goto L_0x0209
        L_0x01fe:
            w0.l r1 = w0.C0311l.f1987i
            goto L_0x0209
        L_0x0201:
            w0.l r1 = w0.C0311l.f1986h
            goto L_0x0209
        L_0x0204:
            w0.l r1 = w0.C0311l.g
            goto L_0x0209
        L_0x0207:
            w0.n r1 = w0.n.d
        L_0x0209:
            return r1
        L_0x020a:
            java.lang.Object r2 = r0.f3360h
            v0.b r2 = (v0.C0292b) r2
            w0.p r4 = new w0.p
            long r1 = r2.b(r1)
            r4.<init>(r1)
            w0.e r0 = r0.c()
            if (r0 == 0) goto L_0x02f9
            long r1 = r4.f1990c
            r5 = 30
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 != 0) goto L_0x0290
            boolean r1 = r0 instanceof w0.C0301b
            if (r1 == 0) goto L_0x0288
            w0.b r0 = (w0.C0301b) r0
            java.util.ArrayList r0 = r0.d
            int r1 = r0.size()
            if (r1 != r11) goto L_0x0280
            java.lang.Object r1 = r0.get(r3)
            w0.e r1 = (w0.C0304e) r1
            boolean r2 = r1 instanceof w0.C0310k
            if (r2 == 0) goto L_0x0278
            java.lang.Object r0 = r0.get(r12)
            w0.e r0 = (w0.C0304e) r0
            boolean r2 = r0 instanceof w0.C0310k
            if (r2 == 0) goto L_0x0270
            w0.k r1 = (w0.C0310k) r1
            w0.k r0 = (w0.C0310k) r0
            w0.h r2 = new w0.h
            r2.<init>()
            w0.p r3 = new w0.p
            r3.<init>(r5)
            r2.b = r3
            java.math.BigInteger r3 = r0.f1985c
            java.math.BigInteger r4 = java.math.BigInteger.ZERO
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0268
            r2.a(r1)
            r2.a(r0)
            return r2
        L_0x0268:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Denominator is zero"
            r0.<init>(r1)
            throw r0
        L_0x0270:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding RationalNumber: second data item is not a number"
            r0.<init>(r1)
            throw r0
        L_0x0278:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding RationalNumber: first data item is not a number"
            r0.<init>(r1)
            throw r0
        L_0x0280:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding RationalNumber: array size is not 2"
            r0.<init>(r1)
            throw r0
        L_0x0288:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding RationalNumber: not an array"
            r0.<init>(r1)
            throw r0
        L_0x0290:
            r5 = 38
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 != 0) goto L_0x02ef
            boolean r1 = r0 instanceof w0.C0301b
            if (r1 == 0) goto L_0x02e7
            w0.b r0 = (w0.C0301b) r0
            java.util.ArrayList r0 = r0.d
            int r1 = r0.size()
            if (r1 != r11) goto L_0x02df
            java.lang.Object r1 = r0.get(r3)
            w0.e r1 = (w0.C0304e) r1
            boolean r2 = r1 instanceof w0.q
            if (r2 == 0) goto L_0x02d7
            java.lang.Object r0 = r0.get(r12)
            w0.e r0 = (w0.C0304e) r0
            boolean r2 = r0 instanceof w0.q
            if (r2 == 0) goto L_0x02cf
            w0.q r1 = (w0.q) r1
            w0.q r0 = (w0.q) r0
            w0.h r2 = new w0.h
            r2.<init>()
            w0.p r3 = new w0.p
            r3.<init>(r5)
            r2.b = r3
            r2.a(r1)
            r2.a(r0)
            return r2
        L_0x02cf:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding LanguageTaggedString: second data item is not an UnicodeString"
            r0.<init>(r1)
            throw r0
        L_0x02d7:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding LanguageTaggedString: first data item is not an UnicodeString"
            r0.<init>(r1)
            throw r0
        L_0x02df:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding LanguageTaggedString: array size is not 2"
            r0.<init>(r1)
            throw r0
        L_0x02e7:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Error decoding LanguageTaggedString: not an array"
            r0.<init>(r1)
            throw r0
        L_0x02ef:
            r1 = r0
        L_0x02f0:
            w0.p r2 = r1.b
            if (r2 == 0) goto L_0x02f6
            r1 = r2
            goto L_0x02f0
        L_0x02f6:
            r1.b = r4
            return r0
        L_0x02f9:
            u0.a r0 = new u0.a
            java.lang.String r1 = "Unexpected end of stream: tag without following data item."
            r0.<init>(r1)
            throw r0
        L_0x0301:
            java.lang.Object r0 = r0.g
            v0.b r0 = (v0.C0292b) r0
            java.lang.Object r3 = r0.b
            Df.n r3 = (Df.n) r3
            long r0 = r0.b(r1)
            int r4 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r4 != 0) goto L_0x0344
            w0.j r0 = new w0.j
            r0.<init>()
            r0.f1983c = r12
            r3.getClass()
        L_0x031b:
            w0.e r1 = r3.c()
            w0.n r4 = w0.n.d
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x0328
            goto L_0x0375
        L_0x0328:
            w0.e r4 = r3.c()
            if (r1 == 0) goto L_0x033e
            if (r4 == 0) goto L_0x033e
            java.util.LinkedHashMap r5 = r0.d
            java.lang.Object r4 = r5.put(r1, r4)
            if (r4 != 0) goto L_0x031b
            java.util.LinkedList r4 = r0.e
            r4.add(r1)
            goto L_0x031b
        L_0x033e:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x0344:
            w0.j r4 = new w0.j
            int r5 = (int) r0
            int r5 = java.lang.Math.abs(r5)
            r4.<init>(r5)
        L_0x034e:
            int r5 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x0374
            w0.e r5 = r3.c()
            w0.e r6 = r3.c()
            if (r5 == 0) goto L_0x036e
            if (r6 == 0) goto L_0x036e
            java.util.LinkedHashMap r7 = r4.d
            java.lang.Object r6 = r7.put(r5, r6)
            if (r6 != 0) goto L_0x036b
            java.util.LinkedList r6 = r4.e
            r6.add(r5)
        L_0x036b:
            long r16 = r16 + r14
            goto L_0x034e
        L_0x036e:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x0374:
            r0 = r4
        L_0x0375:
            return r0
        L_0x0376:
            java.lang.Object r0 = r0.f
            v0.b r0 = (v0.C0292b) r0
            java.lang.Object r3 = r0.b
            Df.n r3 = (Df.n) r3
            long r0 = r0.b(r1)
            int r4 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r4 != 0) goto L_0x03ac
            w0.b r0 = new w0.b
            r0.<init>()
            r0.f1983c = r12
            r3.getClass()
        L_0x0390:
            w0.e r1 = r3.c()
            if (r1 == 0) goto L_0x03a6
            w0.n r4 = w0.n.d
            boolean r5 = r4.equals(r1)
            if (r5 == 0) goto L_0x03a2
            r0.a(r4)
            goto L_0x03cd
        L_0x03a2:
            r0.a(r1)
            goto L_0x0390
        L_0x03a6:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x03ac:
            w0.b r4 = new w0.b
            int r5 = (int) r0
            int r5 = java.lang.Math.abs(r5)
            r4.<init>(r5)
        L_0x03b6:
            int r5 = (r16 > r0 ? 1 : (r16 == r0 ? 0 : -1))
            if (r5 >= 0) goto L_0x03cc
            w0.e r5 = r3.c()
            if (r5 == 0) goto L_0x03c6
            r4.a(r5)
            long r16 = r16 + r14
            goto L_0x03b6
        L_0x03c6:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x03cc:
            r0 = r4
        L_0x03cd:
            return r0
        L_0x03ce:
            java.lang.Object r0 = r0.e
            v0.b r0 = (v0.C0292b) r0
            java.lang.Object r4 = r0.b
            Df.n r4 = (Df.n) r4
            long r5 = r0.b(r1)
            int r1 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r1 != 0) goto L_0x0434
            r4.getClass()
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
        L_0x03e6:
            w0.e r1 = r4.c()
            if (r1 == 0) goto L_0x042e
            w0.i r5 = r1.f1984a
            w0.n r6 = w0.n.d
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0407
            w0.q r1 = new w0.q
            java.lang.String r2 = new java.lang.String
            byte[] r0 = r0.toByteArray()
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            r2.<init>(r0, r3)
            r1.<init>(r2)
            goto L_0x0444
        L_0x0407:
            w0.i r6 = w0.C0308i.UNICODE_STRING
            if (r5 != r6) goto L_0x041c
            w0.q r1 = (w0.q) r1
            java.lang.String r1 = r1.toString()
            java.nio.charset.Charset r5 = java.nio.charset.StandardCharsets.UTF_8
            byte[] r1 = r1.getBytes(r5)
            int r5 = r1.length
            r0.write(r1, r3, r5)
            goto L_0x03e6
        L_0x041c:
            u0.a r0 = new u0.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r13)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x042e:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x0434:
            w0.q r1 = new w0.q
            java.lang.String r2 = new java.lang.String
            byte[] r0 = r0.a(r5)
            java.nio.charset.Charset r3 = java.nio.charset.StandardCharsets.UTF_8
            r2.<init>(r0, r3)
            r1.<init>(r2)
        L_0x0444:
            return r1
        L_0x0445:
            java.lang.Object r0 = r0.d
            v0.b r0 = (v0.C0292b) r0
            java.lang.Object r4 = r0.b
            Df.n r4 = (Df.n) r4
            long r5 = r0.b(r1)
            int r1 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r1 != 0) goto L_0x04a1
            r4.getClass()
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
        L_0x045d:
            w0.e r1 = r4.c()
            if (r1 == 0) goto L_0x049b
            w0.i r5 = r1.f1984a
            w0.n r6 = w0.n.d
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x0477
            w0.c r1 = new w0.c
            byte[] r0 = r0.toByteArray()
            r1.<init>(r0)
            goto L_0x04aa
        L_0x0477:
            w0.i r6 = w0.C0308i.BYTE_STRING
            if (r5 != r6) goto L_0x0489
            w0.c r1 = (w0.C0302c) r1
            byte[] r1 = r1.d
            if (r1 != 0) goto L_0x0482
            r1 = 0
        L_0x0482:
            if (r1 == 0) goto L_0x045d
            int r5 = r1.length
            r0.write(r1, r3, r5)
            goto L_0x045d
        L_0x0489:
            u0.a r0 = new u0.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r13)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x049b:
            u0.a r0 = new u0.a
            r0.<init>(r2)
            throw r0
        L_0x04a1:
            w0.c r1 = new w0.c
            byte[] r0 = r0.a(r5)
            r1.<init>(r0)
        L_0x04aa:
            return r1
        L_0x04ab:
            java.lang.Object r0 = r0.f3359c
            v0.c r0 = (v0.C0293c) r0
            w0.k r2 = new w0.k
            java.math.BigInteger r3 = v0.C0293c.f1950c
            java.math.BigInteger r0 = r0.c(r1)
            java.math.BigInteger r0 = r3.subtract(r0)
            w0.i r1 = w0.C0308i.NEGATIVE_INTEGER
            r2.<init>(r1, r0)
            return r2
        L_0x04c1:
            java.lang.Object r0 = r0.b
            v0.b r0 = (v0.C0292b) r0
            w0.k r2 = new w0.k
            java.math.BigInteger r0 = r0.c(r1)
            w0.i r1 = w0.C0308i.UNSIGNED_INTEGER
            r2.<init>(r1, r0)
            return r2
        L_0x04d1:
            r0 = move-exception
            u0.a r1 = new u0.a
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.n.c():w0.e");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x004b, code lost:
        r3 = r8.a();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public n(Df.l r2, nf.C1209f r3, Qe.C0822l r4, B1.b r5, nf.C1211h r6, nf.C1204a r7, Ff.m r8, Df.H r9, java.util.List r10) {
        /*
            r1 = this;
            java.lang.String r0 = "components"
            kotlin.jvm.internal.j.e(r2, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.j.e(r3, r0)
            java.lang.String r0 = "containingDeclaration"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "versionRequirementTable"
            kotlin.jvm.internal.j.e(r6, r0)
            java.lang.String r0 = "metadataVersion"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = "typeParameters"
            kotlin.jvm.internal.j.e(r10, r0)
            r1.<init>()
            r1.f3358a = r2
            r1.b = r3
            r1.f3359c = r4
            r1.d = r5
            r1.e = r6
            r1.f = r7
            r1.g = r8
            r2 = r1
            Df.H r1 = new Df.H
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r5 = "Deserializer for \""
            r3.<init>(r5)
            qf.g r4 = r4.getName()
            r3.append(r4)
            r4 = 34
            r3.append(r4)
            java.lang.String r5 = r3.toString()
            if (r8 == 0) goto L_0x0056
            java.lang.String r3 = r8.a()
            if (r3 != 0) goto L_0x0052
            goto L_0x0056
        L_0x0052:
            r6 = r3
            r3 = r9
            r4 = r10
            goto L_0x0059
        L_0x0056:
            java.lang.String r3 = "[container not found]"
            goto L_0x0052
        L_0x0059:
            r1.<init>(r2, r3, r4, r5, r6)
            r2.f3360h = r1
            Df.w r1 = new Df.w
            r1.<init>(r2)
            r2.f3361i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.n.<init>(Df.l, nf.f, Qe.l, B1.b, nf.h, nf.a, Ff.m, Df.H, java.util.List):void");
    }
}
