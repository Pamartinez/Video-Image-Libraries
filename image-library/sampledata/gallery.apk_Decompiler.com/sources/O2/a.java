package O2;

import N2.f;
import N2.g;
import N2.y;
import P0.b;
import R2.k;
import R2.l;
import S2.m;
import S2.n;
import T2.c;
import U2.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.cover.ScoverState;
import java.util.BitSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends B1.a {

    /* renamed from: A  reason: collision with root package name */
    public static final a f554A = new a(16);
    public static final a B = new a(17);

    /* renamed from: C  reason: collision with root package name */
    public static final a f555C = new a(18);
    public static final a D = new a(19);
    public static final a E = new a(20);

    /* renamed from: F  reason: collision with root package name */
    public static final a f556F = new a(21);

    /* renamed from: G  reason: collision with root package name */
    public static final a f557G = new a(22);

    /* renamed from: H  reason: collision with root package name */
    public static final a f558H = new a(23);

    /* renamed from: I  reason: collision with root package name */
    public static final a f559I = new a(24);

    /* renamed from: J  reason: collision with root package name */
    public static final a f560J = new a(25);

    /* renamed from: K  reason: collision with root package name */
    public static final a f561K = new a(26);
    public static final a k = new a(0);
    public static final a l = new a(1);
    public static final a m = new a(2);
    public static final a n = new a(3);

    /* renamed from: o  reason: collision with root package name */
    public static final a f562o = new a(4);

    /* renamed from: p  reason: collision with root package name */
    public static final a f563p = new a(5);
    public static final a q = new a(6);
    public static final a r = new a(7);
    public static final a s = new a(8);
    public static final a t = new a(9);
    public static final a u = new a(10);
    public static final a v = new a(11);
    public static final a w = new a(12);

    /* renamed from: x  reason: collision with root package name */
    public static final a f564x = new a(13);
    public static final a y = new a(14);
    public static final a z = new a(15);

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ int f565j;

    public /* synthetic */ a(int i2) {
        this.f565j = i2;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [U2.d, Nf.f, R2.l] */
    public static l b0(l lVar) {
        int c02 = c0(lVar);
        int length = lVar.e.length;
        if (c02 == length) {
            return lVar;
        }
        ? dVar = new d(c02);
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            k kVar = (k) lVar.d(i7);
            dVar.e(i2, kVar);
            if (kVar.c() == 2) {
                dVar.e(i2 + 1, k.d(kVar.d + 1, c.r));
                i2 += 2;
            } else {
                i2++;
            }
        }
        dVar.d = false;
        return dVar;
    }

    public static int c0(l lVar) {
        int length = lVar.e.length;
        if (length > 5) {
            return -1;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            k kVar = (k) lVar.d(i7);
            i2 += kVar.c();
            if (!B1.a.X((kVar.c() + kVar.d) - 1)) {
                return -1;
            }
        }
        if (i2 <= 5) {
            return i2;
        }
        return -1;
    }

    public final String A(N2.l lVar) {
        switch (this.f565j) {
            case 0:
                return B1.a.c(lVar);
            case 1:
                return "";
            case 2:
                l lVar2 = lVar.d;
                return ((k) lVar2.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 3:
                return ((k) lVar.d.d(0)).e();
            case 4:
                l lVar3 = lVar.d;
                int length = lVar3.e.length;
                return ((k) lVar3.d(length - 2)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar3.d(length - 1)).e();
            case 5:
                return B1.a.c(lVar);
            case 6:
                l lVar4 = lVar.d;
                return ((k) lVar4.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 7:
                l lVar5 = lVar.d;
                return ((k) lVar5.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 8:
                l lVar6 = lVar.d;
                return ((k) lVar6.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 9:
                l lVar7 = lVar.d;
                return ((k) lVar7.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.c(lVar);
            case 10:
                l lVar8 = lVar.d;
                return ((k) lVar8.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar8.d(1)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 11:
                l lVar9 = lVar.d;
                return ((k) lVar9.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar9.d(1)).e() + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 12:
                l lVar10 = lVar.d;
                return ((k) lVar10.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar10.d(1)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 13:
                l lVar11 = lVar.d;
                return ((k) lVar11.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar11.d(1)).e() + ArcCommonLog.TAG_COMMA + B1.a.c(lVar);
            case 14:
                l lVar12 = lVar.d;
                return ((k) lVar12.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar12.d(1)).e();
            case 15:
                l lVar13 = lVar.d;
                return ((k) lVar13.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar13.d(1)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar13.d(2)).e();
            case 16:
                l lVar14 = lVar.d;
                return ((k) lVar14.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 17:
                l lVar15 = lVar.d;
                return ((k) lVar15.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 18:
                l lVar16 = lVar.d;
                return ((k) lVar16.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.c(lVar);
            case 19:
                l lVar17 = lVar.d;
                return ((k) lVar17.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar17.d(1)).e();
            case 20:
                l b0 = b0(lVar.d);
                StringBuilder sb2 = new StringBuilder();
                int length2 = b0.e.length;
                StringBuffer stringBuffer = new StringBuffer((length2 * 5) + 2);
                stringBuffer.append('{');
                for (int i2 = 0; i2 < length2; i2++) {
                    if (i2 != 0) {
                        stringBuffer.append(ArcCommonLog.TAG_COMMA);
                    }
                    stringBuffer.append(((k) b0.d(i2)).e());
                }
                stringBuffer.append('}');
                sb2.append(stringBuffer.toString());
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(B1.a.r(lVar));
                return sb2.toString();
            case 21:
                return B1.a.Q(lVar.d) + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 22:
                l lVar18 = lVar.d;
                return ((k) lVar18.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 23:
                l lVar19 = lVar.d;
                return ((k) lVar19.d(0)).e() + ArcCommonLog.TAG_COMMA + B1.a.K((n) ((f) lVar).f);
            case 24:
                l lVar20 = lVar.d;
                return ((k) lVar20.d(0)).e() + ArcCommonLog.TAG_COMMA + ((k) lVar20.d(1)).e() + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            case 25:
                return B1.a.Q(lVar.d) + ArcCommonLog.TAG_COMMA + B1.a.r(lVar);
            default:
                throw new RuntimeException("unsupported");
        }
    }

    public final String B(N2.l lVar) {
        int i2;
        switch (this.f565j) {
            case 0:
                return B1.a.a(lVar);
            case 1:
                return "";
            case 2:
                return B1.a.J((n) ((f) lVar).f, 4);
            case 3:
                return "";
            case 4:
                return "";
            case 5:
                return B1.a.a(lVar);
            case 6:
                return B1.a.q(lVar);
            case 7:
                l lVar2 = lVar.d;
                n nVar = (n) ((f) lVar).f;
                if (((k) lVar2.d(0)).c() == 1) {
                    i2 = 32;
                } else {
                    i2 = 64;
                }
                return B1.a.J(nVar, i2);
            case 8:
                return B1.a.J((n) ((f) lVar).f, 16);
            case 9:
                return B1.a.a(lVar);
            case 10:
                return B1.a.J((n) ((f) lVar).f, 8);
            case 11:
                return B1.a.q(lVar);
            case 12:
                return B1.a.J((n) ((f) lVar).f, 16);
            case 13:
                return B1.a.a(lVar);
            case 14:
                return "";
            case 15:
                return "";
            case 16:
                return B1.a.q(lVar);
            case 17:
                return B1.a.J((n) ((f) lVar).f, 32);
            case 18:
                return B1.a.a(lVar);
            case 19:
                return "";
            case 20:
                return B1.a.q(lVar);
            case 21:
                return B1.a.q(lVar);
            case 22:
                return B1.a.q(lVar);
            case 23:
                return B1.a.J((n) ((f) lVar).f, 64);
            case 24:
                return B1.a.q(lVar);
            case 25:
                return B1.a.q(lVar);
            default:
                throw new RuntimeException("unsupported");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: R2.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v45, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v12, resolved type: R2.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v107, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: R2.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v149, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v61, resolved type: R2.k} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean D(N2.g r6) {
        /*
            r5 = this;
            int r0 = r5.f565j
            switch(r0) {
                case 0: goto L_0x054b;
                case 1: goto L_0x053c;
                case 2: goto L_0x0503;
                case 3: goto L_0x04e6;
                case 4: goto L_0x049b;
                case 5: goto L_0x047e;
                case 6: goto L_0x042c;
                case 7: goto L_0x03dc;
                case 8: goto L_0x03a6;
                case 9: goto L_0x037b;
                case 10: goto L_0x0336;
                case 11: goto L_0x02f2;
                case 12: goto L_0x02ad;
                case 13: goto L_0x0273;
                case 14: goto L_0x0247;
                case 15: goto L_0x020c;
                case 16: goto L_0x01c5;
                case 17: goto L_0x0199;
                case 18: goto L_0x017b;
                case 19: goto L_0x014f;
                case 20: goto L_0x0127;
                case 21: goto L_0x00e1;
                case 22: goto L_0x009e;
                case 23: goto L_0x007b;
                case 24: goto L_0x0042;
                case 25: goto L_0x0007;
                default: goto L_0x0005;
            }
        L_0x0005:
            r5 = 1
            return r5
        L_0x0007:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x000d
            goto L_0x0041
        L_0x000d:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r1 = r5 instanceof S2.q
            if (r1 != 0) goto L_0x001a
            boolean r5 = r5 instanceof S2.u
            if (r5 != 0) goto L_0x001a
            goto L_0x0041
        L_0x001a:
            R2.l r5 = r6.d
            java.lang.Object[] r6 = r5.e
            int r1 = r6.length
            int r6 = r6.length
            if (r6 == 0) goto L_0x0040
            boolean r6 = B1.a.F(r5)
            if (r6 == 0) goto L_0x0041
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            int r6 = r6.d
            boolean r6 = B1.a.Y(r6)
            if (r6 == 0) goto L_0x0041
            int r5 = r5.g()
            boolean r5 = B1.a.Y(r5)
            if (r5 == 0) goto L_0x0041
        L_0x0040:
            r0 = 1
        L_0x0041:
            return r0
        L_0x0042:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x007a
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 2
            if (r0 != r2) goto L_0x007a
            java.lang.Object r0 = r5.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.Y(r0)
            if (r0 == 0) goto L_0x007a
            r0 = 1
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x006d
            goto L_0x007a
        L_0x006d:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.u
            if (r6 != 0) goto L_0x0079
            boolean r5 = r5 instanceof S2.h
            if (r5 == 0) goto L_0x007a
        L_0x0079:
            r1 = r0
        L_0x007a:
            return r1
        L_0x007b:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x009d
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x009d
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x0097
            goto L_0x009d
        L_0x0097:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r1 = r5 instanceof S2.m
        L_0x009d:
            return r1
        L_0x009e:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x00a4
            goto L_0x00e0
        L_0x00a4:
            R2.l r5 = r6.d
            java.lang.Object[] r1 = r5.e
            int r1 = r1.length
            r2 = 1
            if (r1 == r2) goto L_0x00c3
            r3 = 2
            if (r1 == r3) goto L_0x00b0
            goto L_0x00e0
        L_0x00b0:
            java.lang.Object r1 = r5.d(r0)
            R2.k r1 = (R2.k) r1
            int r3 = r1.d
            java.lang.Object r5 = r5.d(r2)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            if (r3 == r5) goto L_0x00ca
            goto L_0x00e0
        L_0x00c3:
            java.lang.Object r5 = r5.d(r0)
            r1 = r5
            R2.k r1 = (R2.k) r1
        L_0x00ca:
            int r5 = r1.d
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x00d3
            goto L_0x00e0
        L_0x00d3:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.u
            if (r6 != 0) goto L_0x00df
            boolean r5 = r5 instanceof S2.h
            if (r5 == 0) goto L_0x00e0
        L_0x00df:
            r0 = r2
        L_0x00e0:
            return r0
        L_0x00e1:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x00e7
            goto L_0x0126
        L_0x00e7:
            N2.f r6 = (N2.f) r6
            int r5 = r6.l()
            S2.a r1 = r6.f
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x00f6
            goto L_0x0126
        L_0x00f6:
            boolean r5 = r1 instanceof S2.q
            if (r5 != 0) goto L_0x00ff
            boolean r5 = r1 instanceof S2.u
            if (r5 != 0) goto L_0x00ff
            goto L_0x0126
        L_0x00ff:
            R2.l r5 = r6.d
            java.lang.Object[] r6 = r5.e
            int r1 = r6.length
            int r6 = r6.length
            if (r6 == 0) goto L_0x0125
            boolean r6 = B1.a.F(r5)
            if (r6 == 0) goto L_0x0126
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            int r6 = r6.d
            boolean r6 = B1.a.Y(r6)
            if (r6 == 0) goto L_0x0126
            int r5 = r5.g()
            boolean r5 = B1.a.W(r5)
            if (r5 == 0) goto L_0x0126
        L_0x0125:
            r0 = 1
        L_0x0126:
            return r0
        L_0x0127:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x012d
            goto L_0x014e
        L_0x012d:
            N2.f r6 = (N2.f) r6
            int r5 = r6.l()
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x013a
            goto L_0x014e
        L_0x013a:
            S2.a r5 = r6.f
            boolean r1 = r5 instanceof S2.q
            if (r1 != 0) goto L_0x0145
            boolean r5 = r5 instanceof S2.u
            if (r5 != 0) goto L_0x0145
            goto L_0x014e
        L_0x0145:
            R2.l r5 = r6.d
            int r5 = c0(r5)
            if (r5 < 0) goto L_0x014e
            r0 = 1
        L_0x014e:
            return r0
        L_0x014f:
            R2.l r5 = r6.d
            boolean r6 = r6 instanceof N2.x
            r0 = 0
            if (r6 == 0) goto L_0x017a
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 2
            if (r6 != r1) goto L_0x017a
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            int r6 = r6.d
            boolean r6 = B1.a.Y(r6)
            if (r6 == 0) goto L_0x017a
            r6 = 1
            java.lang.Object r5 = r5.d(r6)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.Y(r5)
            if (r5 == 0) goto L_0x017a
            r0 = r6
        L_0x017a:
            return r0
        L_0x017b:
            R2.l r5 = r6.d
            boolean r6 = r6 instanceof N2.y
            r0 = 0
            if (r6 == 0) goto L_0x0198
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 1
            if (r6 != r1) goto L_0x0198
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x0197
            goto L_0x0198
        L_0x0197:
            r0 = r1
        L_0x0198:
            return r0
        L_0x0199:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x01c4
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x01c4
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x01b5
            goto L_0x01c4
        L_0x01b5:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.n
            if (r6 != 0) goto L_0x01be
            goto L_0x01c4
        L_0x01be:
            S2.n r5 = (S2.n) r5
            boolean r1 = r5.f()
        L_0x01c4:
            return r1
        L_0x01c5:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x01cb
            goto L_0x020b
        L_0x01cb:
            R2.l r5 = r6.d
            java.lang.Object[] r1 = r5.e
            int r1 = r1.length
            r2 = 1
            if (r1 == r2) goto L_0x01ea
            r3 = 2
            if (r1 == r3) goto L_0x01d7
            goto L_0x020b
        L_0x01d7:
            java.lang.Object r1 = r5.d(r0)
            R2.k r1 = (R2.k) r1
            int r3 = r1.d
            java.lang.Object r5 = r5.d(r2)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            if (r3 == r5) goto L_0x01f1
            goto L_0x020b
        L_0x01ea:
            java.lang.Object r5 = r5.d(r0)
            r1 = r5
            R2.k r1 = (R2.k) r1
        L_0x01f1:
            int r5 = r1.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x01fa
            goto L_0x020b
        L_0x01fa:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.u
            if (r6 != 0) goto L_0x020a
            boolean r6 = r5 instanceof S2.h
            if (r6 != 0) goto L_0x020a
            boolean r5 = r5 instanceof S2.t
            if (r5 == 0) goto L_0x020b
        L_0x020a:
            r0 = r2
        L_0x020b:
            return r0
        L_0x020c:
            R2.l r5 = r6.d
            boolean r6 = r6 instanceof N2.x
            r0 = 0
            if (r6 == 0) goto L_0x0246
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 3
            if (r6 != r1) goto L_0x0246
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            int r6 = r6.d
            boolean r6 = B1.a.W(r6)
            if (r6 == 0) goto L_0x0246
            r6 = 1
            java.lang.Object r1 = r5.d(r6)
            R2.k r1 = (R2.k) r1
            int r1 = r1.d
            boolean r1 = B1.a.W(r1)
            if (r1 == 0) goto L_0x0246
            r1 = 2
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 == 0) goto L_0x0246
            r0 = r6
        L_0x0246:
            return r0
        L_0x0247:
            R2.l r5 = r6.d
            boolean r6 = r6 instanceof N2.x
            r0 = 0
            if (r6 == 0) goto L_0x0272
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 2
            if (r6 != r1) goto L_0x0272
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            int r6 = r6.d
            boolean r6 = B1.a.W(r6)
            if (r6 == 0) goto L_0x0272
            r6 = 1
            java.lang.Object r5 = r5.d(r6)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.Y(r5)
            if (r5 == 0) goto L_0x0272
            r0 = r6
        L_0x0272:
            return r0
        L_0x0273:
            R2.l r0 = r6.d
            boolean r1 = r6 instanceof N2.y
            r2 = 0
            if (r1 == 0) goto L_0x02ac
            java.lang.Object[] r1 = r0.e
            int r1 = r1.length
            r3 = 2
            if (r1 != r3) goto L_0x02ac
            java.lang.Object r1 = r0.d(r2)
            R2.k r1 = (R2.k) r1
            int r1 = r1.d
            boolean r1 = B1.a.X(r1)
            if (r1 == 0) goto L_0x02ac
            r1 = 1
            java.lang.Object r0 = r0.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.X(r0)
            if (r0 != 0) goto L_0x029e
            goto L_0x02ac
        L_0x029e:
            N2.y r6 = (N2.y) r6
            boolean r0 = r6.m()
            if (r0 == 0) goto L_0x02ab
            boolean r2 = r5.b(r6)
            goto L_0x02ac
        L_0x02ab:
            r2 = r1
        L_0x02ac:
            return r2
        L_0x02ad:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x02f1
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 2
            if (r0 != r2) goto L_0x02f1
            java.lang.Object r0 = r5.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.X(r0)
            if (r0 == 0) goto L_0x02f1
            r0 = 1
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.X(r5)
            if (r5 != 0) goto L_0x02d8
            goto L_0x02f1
        L_0x02d8:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.n
            if (r6 != 0) goto L_0x02e1
            goto L_0x02f1
        L_0x02e1:
            S2.n r5 = (S2.n) r5
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x02f1
            int r5 = r5.g()
            short r6 = (short) r5
            if (r6 != r5) goto L_0x02f1
            r1 = r0
        L_0x02f1:
            return r1
        L_0x02f2:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x0335
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 2
            if (r0 != r2) goto L_0x0335
            java.lang.Object r0 = r5.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.X(r0)
            if (r0 == 0) goto L_0x0335
            r0 = 1
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.X(r5)
            if (r5 != 0) goto L_0x031d
            goto L_0x0335
        L_0x031d:
            N2.f r6 = (N2.f) r6
            int r5 = r6.l()
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x032a
            goto L_0x0335
        L_0x032a:
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.u
            if (r6 != 0) goto L_0x0334
            boolean r5 = r5 instanceof S2.h
            if (r5 == 0) goto L_0x0335
        L_0x0334:
            r1 = r0
        L_0x0335:
            return r1
        L_0x0336:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x037a
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 2
            if (r0 != r2) goto L_0x037a
            java.lang.Object r0 = r5.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.W(r0)
            if (r0 == 0) goto L_0x037a
            r0 = 1
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x0361
            goto L_0x037a
        L_0x0361:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.n
            if (r6 != 0) goto L_0x036a
            goto L_0x037a
        L_0x036a:
            S2.n r5 = (S2.n) r5
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x037a
            int r5 = r5.g()
            byte r6 = (byte) r5
            if (r6 != r5) goto L_0x037a
            r1 = r0
        L_0x037a:
            return r1
        L_0x037b:
            R2.l r0 = r6.d
            boolean r1 = r6 instanceof N2.y
            r2 = 0
            if (r1 == 0) goto L_0x03a5
            java.lang.Object[] r1 = r0.e
            int r1 = r1.length
            r3 = 1
            if (r1 != r3) goto L_0x03a5
            java.lang.Object r0 = r0.d(r2)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.W(r0)
            if (r0 != 0) goto L_0x0397
            goto L_0x03a5
        L_0x0397:
            N2.y r6 = (N2.y) r6
            boolean r0 = r6.m()
            if (r0 == 0) goto L_0x03a4
            boolean r2 = r5.b(r6)
            goto L_0x03a5
        L_0x03a4:
            r2 = r3
        L_0x03a5:
            return r2
        L_0x03a6:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x03db
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x03db
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x03c2
            goto L_0x03db
        L_0x03c2:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.n
            if (r6 != 0) goto L_0x03cb
            goto L_0x03db
        L_0x03cb:
            S2.n r5 = (S2.n) r5
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x03db
            int r5 = r5.g()
            short r6 = (short) r5
            if (r6 != r5) goto L_0x03db
            r1 = r2
        L_0x03db:
            return r1
        L_0x03dc:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x042b
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x042b
            java.lang.Object r0 = r5.d(r1)
            R2.k r0 = (R2.k) r0
            int r0 = r0.d
            boolean r0 = B1.a.W(r0)
            if (r0 != 0) goto L_0x03f8
            goto L_0x042b
        L_0x03f8:
            N2.f r6 = (N2.f) r6
            S2.a r6 = r6.f
            boolean r0 = r6 instanceof S2.n
            if (r0 != 0) goto L_0x0401
            goto L_0x042b
        L_0x0401:
            S2.n r6 = (S2.n) r6
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.c()
            if (r5 != r2) goto L_0x041a
            int r5 = r6.g()
            r6 = 65535(0xffff, float:9.1834E-41)
            r5 = r5 & r6
            if (r5 != 0) goto L_0x042b
            goto L_0x042a
        L_0x041a:
            long r5 = r6.h()
            r3 = 281474976710655(0xffffffffffff, double:1.390671161566996E-309)
            long r5 = r5 & r3
            r3 = 0
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x042b
        L_0x042a:
            r1 = r2
        L_0x042b:
            return r1
        L_0x042c:
            boolean r5 = r6 instanceof N2.f
            r0 = 0
            if (r5 != 0) goto L_0x0432
            goto L_0x047d
        L_0x0432:
            R2.l r5 = r6.d
            java.lang.Object[] r1 = r5.e
            int r1 = r1.length
            r2 = 1
            if (r1 == r2) goto L_0x0451
            r3 = 2
            if (r1 == r3) goto L_0x043e
            goto L_0x047d
        L_0x043e:
            java.lang.Object r1 = r5.d(r0)
            R2.k r1 = (R2.k) r1
            int r3 = r1.d
            java.lang.Object r5 = r5.d(r2)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            if (r3 == r5) goto L_0x0458
            goto L_0x047d
        L_0x0451:
            java.lang.Object r5 = r5.d(r0)
            r1 = r5
            R2.k r1 = (R2.k) r1
        L_0x0458:
            int r5 = r1.d
            boolean r5 = B1.a.W(r5)
            if (r5 != 0) goto L_0x0461
            goto L_0x047d
        L_0x0461:
            N2.f r6 = (N2.f) r6
            int r5 = r6.l()
            S2.a r6 = r6.f
            boolean r5 = B1.a.Y(r5)
            if (r5 != 0) goto L_0x0470
            goto L_0x047d
        L_0x0470:
            boolean r5 = r6 instanceof S2.u
            if (r5 != 0) goto L_0x047c
            boolean r5 = r6 instanceof S2.h
            if (r5 != 0) goto L_0x047c
            boolean r5 = r6 instanceof S2.t
            if (r5 == 0) goto L_0x047d
        L_0x047c:
            r0 = r2
        L_0x047d:
            return r0
        L_0x047e:
            boolean r0 = r6 instanceof N2.y
            if (r0 == 0) goto L_0x0499
            R2.l r0 = r6.d
            java.lang.Object[] r0 = r0.e
            int r0 = r0.length
            if (r0 == 0) goto L_0x048a
            goto L_0x0499
        L_0x048a:
            N2.y r6 = (N2.y) r6
            boolean r0 = r6.m()
            if (r0 == 0) goto L_0x0497
            boolean r5 = r5.b(r6)
            goto L_0x049a
        L_0x0497:
            r5 = 1
            goto L_0x049a
        L_0x0499:
            r5 = 0
        L_0x049a:
            return r5
        L_0x049b:
            boolean r5 = r6 instanceof N2.x
            r0 = 0
            if (r5 != 0) goto L_0x04a1
            goto L_0x04e5
        L_0x04a1:
            R2.l r5 = r6.d
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 2
            r2 = 1
            if (r6 == r1) goto L_0x04c7
            r3 = 3
            if (r6 == r3) goto L_0x04ae
            goto L_0x04e5
        L_0x04ae:
            java.lang.Object r6 = r5.d(r2)
            R2.k r6 = (R2.k) r6
            java.lang.Object r1 = r5.d(r1)
            R2.k r1 = (R2.k) r1
            int r3 = r6.d
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            if (r3 == r5) goto L_0x04d4
            goto L_0x04e5
        L_0x04c7:
            java.lang.Object r6 = r5.d(r0)
            R2.k r6 = (R2.k) r6
            java.lang.Object r5 = r5.d(r2)
            r1 = r5
            R2.k r1 = (R2.k) r1
        L_0x04d4:
            int r5 = r6.d
            boolean r5 = B1.a.X(r5)
            if (r5 == 0) goto L_0x04e5
            int r5 = r1.d
            boolean r5 = B1.a.X(r5)
            if (r5 == 0) goto L_0x04e5
            r0 = r2
        L_0x04e5:
            return r0
        L_0x04e6:
            R2.l r5 = r6.d
            boolean r6 = r6 instanceof N2.x
            r0 = 0
            if (r6 == 0) goto L_0x0502
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r1 = 1
            if (r6 != r1) goto L_0x0502
            java.lang.Object r5 = r5.d(r0)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.W(r5)
            if (r5 == 0) goto L_0x0502
            r0 = r1
        L_0x0502:
            return r0
        L_0x0503:
            R2.l r5 = r6.d
            boolean r0 = r6 instanceof N2.f
            r1 = 0
            if (r0 == 0) goto L_0x053b
            java.lang.Object[] r0 = r5.e
            int r0 = r0.length
            r2 = 1
            if (r0 != r2) goto L_0x053b
            java.lang.Object r5 = r5.d(r1)
            R2.k r5 = (R2.k) r5
            int r5 = r5.d
            boolean r5 = B1.a.X(r5)
            if (r5 != 0) goto L_0x051f
            goto L_0x053b
        L_0x051f:
            N2.f r6 = (N2.f) r6
            S2.a r5 = r6.f
            boolean r6 = r5 instanceof S2.n
            if (r6 != 0) goto L_0x0528
            goto L_0x053b
        L_0x0528:
            S2.n r5 = (S2.n) r5
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x053b
            int r5 = r5.g()
            r6 = -8
            if (r5 < r6) goto L_0x053b
            r6 = 7
            if (r5 > r6) goto L_0x053b
            r1 = r2
        L_0x053b:
            return r1
        L_0x053c:
            boolean r5 = r6 instanceof N2.x
            if (r5 == 0) goto L_0x0549
            R2.l r5 = r6.d
            java.lang.Object[] r5 = r5.e
            int r5 = r5.length
            if (r5 != 0) goto L_0x0549
            r5 = 1
            goto L_0x054a
        L_0x0549:
            r5 = 0
        L_0x054a:
            return r5
        L_0x054b:
            boolean r0 = r6 instanceof N2.y
            if (r0 == 0) goto L_0x0566
            R2.l r0 = r6.d
            java.lang.Object[] r0 = r0.e
            int r0 = r0.length
            if (r0 == 0) goto L_0x0557
            goto L_0x0566
        L_0x0557:
            N2.y r6 = (N2.y) r6
            boolean r0 = r6.m()
            if (r0 == 0) goto L_0x0564
            boolean r5 = r5.b(r6)
            goto L_0x0567
        L_0x0564:
            r5 = 1
            goto L_0x0567
        L_0x0566:
            r5 = 0
        L_0x0567:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: O2.a.D(N2.g):boolean");
    }

    public final void a0(b bVar, N2.l lVar) {
        int h5;
        int i2;
        int i7;
        int i8;
        int i10;
        switch (this.f565j) {
            case 0:
                bVar.m(B1.a.M(lVar, ((y) lVar).l() & ScoverState.TYPE_NFC_SMART_COVER));
                return;
            case 1:
                bVar.m(B1.a.M(lVar, 0));
                return;
            case 2:
                bVar.m(B1.a.M(lVar, B1.a.L(((k) lVar.d.d(0)).d, ((n) ((f) lVar).f).g() & 15)));
                return;
            case 3:
                bVar.m(B1.a.M(lVar, ((k) lVar.d.d(0)).d));
                return;
            case 4:
                l lVar2 = lVar.d;
                int length = lVar2.e.length;
                bVar.m(B1.a.M(lVar, B1.a.L(((k) lVar2.d(length - 2)).d, ((k) lVar2.d(length - 1)).d)));
                return;
            case 5:
                int l8 = ((y) lVar).l();
                bVar.m(B1.a.M(lVar, 0));
                bVar.m((short) l8);
                return;
            case 6:
                l lVar3 = lVar.d;
                int l10 = ((f) lVar).l();
                bVar.m(B1.a.M(lVar, ((k) lVar3.d(0)).d));
                bVar.m((short) l10);
                return;
            case 7:
                l lVar4 = lVar.d;
                n nVar = (n) ((f) lVar).f;
                if (((k) lVar4.d(0)).c() == 1) {
                    h5 = nVar.g() >>> 16;
                } else {
                    h5 = (int) (nVar.h() >>> 48);
                }
                short s5 = (short) h5;
                bVar.m(B1.a.M(lVar, ((k) lVar4.d(0)).d));
                bVar.m(s5);
                return;
            case 8:
                l lVar5 = lVar.d;
                int g = ((n) ((f) lVar).f).g();
                bVar.m(B1.a.M(lVar, ((k) lVar5.d(0)).d));
                bVar.m((short) g);
                return;
            case 9:
                l lVar6 = lVar.d;
                int l11 = ((y) lVar).l();
                bVar.m(B1.a.M(lVar, ((k) lVar6.d(0)).d));
                bVar.m((short) l11);
                return;
            case 10:
                l lVar7 = lVar.d;
                int g3 = ((n) ((f) lVar).f).g();
                short M2 = B1.a.M(lVar, ((k) lVar7.d(0)).d);
                short j2 = B1.a.j(((k) lVar7.d(1)).d, g3 & ScoverState.TYPE_NFC_SMART_COVER);
                bVar.m(M2);
                bVar.m(j2);
                return;
            case 11:
                l lVar8 = lVar.d;
                int l12 = ((f) lVar).l();
                bVar.m(B1.a.M(lVar, B1.a.L(((k) lVar8.d(0)).d, ((k) lVar8.d(1)).d)));
                bVar.m((short) l12);
                return;
            case 12:
                l lVar9 = lVar.d;
                int g10 = ((n) ((f) lVar).f).g();
                bVar.m(B1.a.M(lVar, B1.a.L(((k) lVar9.d(0)).d, ((k) lVar9.d(1)).d)));
                bVar.m((short) g10);
                return;
            case 13:
                l lVar10 = lVar.d;
                int l13 = ((y) lVar).l();
                bVar.m(B1.a.M(lVar, B1.a.L(((k) lVar10.d(0)).d, ((k) lVar10.d(1)).d)));
                bVar.m((short) l13);
                return;
            case 14:
                l lVar11 = lVar.d;
                bVar.m(B1.a.M(lVar, ((k) lVar11.d(0)).d));
                bVar.m((short) ((k) lVar11.d(1)).d);
                return;
            case 15:
                l lVar12 = lVar.d;
                short M10 = B1.a.M(lVar, ((k) lVar12.d(0)).d);
                short j3 = B1.a.j(((k) lVar12.d(1)).d, ((k) lVar12.d(2)).d);
                bVar.m(M10);
                bVar.m(j3);
                return;
            case 16:
                l lVar13 = lVar.d;
                int l14 = ((f) lVar).l();
                bVar.m(B1.a.M(lVar, ((k) lVar13.d(0)).d));
                bVar.m((short) l14);
                bVar.m((short) (l14 >> 16));
                return;
            case 17:
                l lVar14 = lVar.d;
                int g11 = ((n) ((f) lVar).f).g();
                bVar.m(B1.a.M(lVar, ((k) lVar14.d(0)).d));
                bVar.m((short) g11);
                bVar.m((short) (g11 >> 16));
                return;
            case 18:
                l lVar15 = lVar.d;
                int l15 = ((y) lVar).l();
                bVar.m(B1.a.M(lVar, ((k) lVar15.d(0)).d));
                bVar.m((short) l15);
                bVar.m((short) (l15 >> 16));
                return;
            case 19:
                l lVar16 = lVar.d;
                bVar.m(B1.a.M(lVar, 0));
                bVar.m((short) ((k) lVar16.d(0)).d);
                bVar.m((short) ((k) lVar16.d(1)).d);
                return;
            case 20:
                int l16 = ((f) lVar).l();
                l b0 = b0(lVar.d);
                int length2 = b0.e.length;
                int i11 = 0;
                if (length2 > 0) {
                    i2 = ((k) b0.d(0)).d;
                } else {
                    i2 = 0;
                }
                if (length2 > 1) {
                    i7 = ((k) b0.d(1)).d;
                } else {
                    i7 = 0;
                }
                if (length2 > 2) {
                    i8 = ((k) b0.d(2)).d;
                } else {
                    i8 = 0;
                }
                if (length2 > 3) {
                    i10 = ((k) b0.d(3)).d;
                } else {
                    i10 = 0;
                }
                if (length2 > 4) {
                    i11 = ((k) b0.d(4)).d;
                }
                short M11 = B1.a.M(lVar, B1.a.L(i11, length2));
                short s6 = (short) l16;
                if ((i2 & 15) != i2) {
                    throw new IllegalArgumentException("n0 out of range 0..15");
                } else if ((i7 & 15) != i7) {
                    throw new IllegalArgumentException("n1 out of range 0..15");
                } else if ((i8 & 15) != i8) {
                    throw new IllegalArgumentException("n2 out of range 0..15");
                } else if ((i10 & 15) == i10) {
                    bVar.m(M11);
                    bVar.m(s6);
                    bVar.m((short) ((i7 << 4) | i2 | (i8 << 8) | (i10 << 12)));
                    return;
                } else {
                    throw new IllegalArgumentException("n3 out of range 0..15");
                }
            case 21:
                l lVar17 = lVar.d;
                int l17 = ((f) lVar).l();
                int i12 = 0;
                if (lVar17.e.length != 0) {
                    i12 = ((k) lVar17.d(0)).d;
                }
                bVar.m(B1.a.M(lVar, lVar17.g()));
                bVar.m((short) l17);
                bVar.m((short) i12);
                return;
            case 22:
                l lVar18 = lVar.d;
                int l18 = ((f) lVar).l();
                bVar.m(B1.a.N(lVar));
                bVar.m((short) l18);
                bVar.m((short) (l18 >> 16));
                bVar.m((short) ((k) lVar18.d(0)).d);
                return;
            case 23:
                l lVar19 = lVar.d;
                long j8 = ((m) ((f) lVar).f).d;
                bVar.m(B1.a.M(lVar, ((k) lVar19.d(0)).d));
                bVar.m((short) ((int) j8));
                bVar.m((short) ((int) (j8 >> 16)));
                bVar.m((short) ((int) (j8 >> 32)));
                bVar.m((short) ((int) (j8 >> 48)));
                return;
            case 24:
                l lVar20 = lVar.d;
                int l19 = ((f) lVar).l();
                bVar.m(B1.a.N(lVar));
                bVar.m((short) l19);
                bVar.m((short) (l19 >> 16));
                bVar.m((short) ((k) lVar20.d(0)).d);
                bVar.m((short) ((k) lVar20.d(1)).d);
                return;
            case 25:
                l lVar21 = lVar.d;
                int l20 = ((f) lVar).l();
                int i13 = 0;
                if (lVar21.e.length != 0) {
                    i13 = ((k) lVar21.d(0)).d;
                }
                int g12 = lVar21.g();
                bVar.m(B1.a.N(lVar));
                bVar.m((short) l20);
                bVar.m((short) (l20 >> 16));
                bVar.m((short) g12);
                bVar.m((short) i13);
                return;
            default:
                throw new RuntimeException("unsupported");
        }
    }

    public boolean b(y yVar) {
        switch (this.f565j) {
            case 0:
                int l8 = yVar.l();
                if (l8 == 0 || ((byte) l8) != l8) {
                    return false;
                }
                return true;
            case 5:
                int l10 = yVar.l();
                if (l10 == 0 || ((short) l10) != l10) {
                    return false;
                }
                return true;
            case 9:
                int l11 = yVar.l();
                if (l11 == 0 || ((short) l11) != l11) {
                    return false;
                }
                return true;
            case 13:
                int l12 = yVar.l();
                if (l12 == 0 || ((short) l12) != l12) {
                    return false;
                }
                return true;
            case 18:
                return true;
            default:
                return super.b(yVar);
        }
    }

    public final int h() {
        switch (this.f565j) {
            case 0:
                return 1;
            case 1:
                return 1;
            case 2:
                return 1;
            case 3:
                return 1;
            case 4:
                return 1;
            case 5:
                return 2;
            case 6:
                return 2;
            case 7:
                return 2;
            case 8:
                return 2;
            case 9:
                return 2;
            case 10:
                return 2;
            case 11:
                return 2;
            case 12:
                return 2;
            case 13:
                return 2;
            case 14:
                return 2;
            case 15:
                return 2;
            case 16:
                return 3;
            case 17:
                return 3;
            case 18:
                return 3;
            case 19:
                return 3;
            case 20:
                return 3;
            case 21:
                return 3;
            case 22:
                return 4;
            case 23:
                return 5;
            case 24:
                return 5;
            case 25:
                return 5;
            default:
                throw new RuntimeException("unsupported");
        }
    }

    public BitSet n(g gVar) {
        switch (this.f565j) {
            case 2:
                l lVar = gVar.d;
                BitSet bitSet = new BitSet(1);
                bitSet.set(0, B1.a.X(((k) lVar.d(0)).d));
                return bitSet;
            case 3:
                l lVar2 = gVar.d;
                BitSet bitSet2 = new BitSet(1);
                bitSet2.set(0, B1.a.W(((k) lVar2.d(0)).d));
                return bitSet2;
            case 4:
                l lVar3 = gVar.d;
                BitSet bitSet3 = new BitSet(2);
                bitSet3.set(0, B1.a.X(((k) lVar3.d(0)).d));
                bitSet3.set(1, B1.a.X(((k) lVar3.d(1)).d));
                return bitSet3;
            case 6:
                l lVar4 = gVar.d;
                int length = lVar4.e.length;
                BitSet bitSet4 = new BitSet(length);
                boolean W6 = B1.a.W(((k) lVar4.d(0)).d);
                if (length == 1) {
                    bitSet4.set(0, W6);
                } else if (((k) lVar4.d(0)).d == ((k) lVar4.d(1)).d) {
                    bitSet4.set(0, W6);
                    bitSet4.set(1, W6);
                }
                return bitSet4;
            case 7:
                l lVar5 = gVar.d;
                BitSet bitSet5 = new BitSet(1);
                bitSet5.set(0, B1.a.W(((k) lVar5.d(0)).d));
                return bitSet5;
            case 8:
                l lVar6 = gVar.d;
                BitSet bitSet6 = new BitSet(1);
                bitSet6.set(0, B1.a.W(((k) lVar6.d(0)).d));
                return bitSet6;
            case 9:
                l lVar7 = gVar.d;
                BitSet bitSet7 = new BitSet(1);
                bitSet7.set(0, B1.a.W(((k) lVar7.d(0)).d));
                return bitSet7;
            case 10:
                l lVar8 = gVar.d;
                BitSet bitSet8 = new BitSet(2);
                bitSet8.set(0, B1.a.W(((k) lVar8.d(0)).d));
                bitSet8.set(1, B1.a.W(((k) lVar8.d(1)).d));
                return bitSet8;
            case 11:
                l lVar9 = gVar.d;
                BitSet bitSet9 = new BitSet(2);
                bitSet9.set(0, B1.a.X(((k) lVar9.d(0)).d));
                bitSet9.set(1, B1.a.X(((k) lVar9.d(1)).d));
                return bitSet9;
            case 12:
                l lVar10 = gVar.d;
                BitSet bitSet10 = new BitSet(2);
                bitSet10.set(0, B1.a.X(((k) lVar10.d(0)).d));
                bitSet10.set(1, B1.a.X(((k) lVar10.d(1)).d));
                return bitSet10;
            case 13:
                l lVar11 = gVar.d;
                BitSet bitSet11 = new BitSet(2);
                bitSet11.set(0, B1.a.X(((k) lVar11.d(0)).d));
                bitSet11.set(1, B1.a.X(((k) lVar11.d(1)).d));
                return bitSet11;
            case 14:
                l lVar12 = gVar.d;
                BitSet bitSet12 = new BitSet(2);
                bitSet12.set(0, B1.a.W(((k) lVar12.d(0)).d));
                bitSet12.set(1, B1.a.Y(((k) lVar12.d(1)).d));
                return bitSet12;
            case 15:
                l lVar13 = gVar.d;
                BitSet bitSet13 = new BitSet(3);
                bitSet13.set(0, B1.a.W(((k) lVar13.d(0)).d));
                bitSet13.set(1, B1.a.W(((k) lVar13.d(1)).d));
                bitSet13.set(2, B1.a.W(((k) lVar13.d(2)).d));
                return bitSet13;
            case 16:
                l lVar14 = gVar.d;
                int length2 = lVar14.e.length;
                BitSet bitSet14 = new BitSet(length2);
                boolean W10 = B1.a.W(((k) lVar14.d(0)).d);
                if (length2 == 1) {
                    bitSet14.set(0, W10);
                } else if (((k) lVar14.d(0)).d == ((k) lVar14.d(1)).d) {
                    bitSet14.set(0, W10);
                    bitSet14.set(1, W10);
                }
                return bitSet14;
            case 17:
                l lVar15 = gVar.d;
                BitSet bitSet15 = new BitSet(1);
                bitSet15.set(0, B1.a.W(((k) lVar15.d(0)).d));
                return bitSet15;
            case 18:
                l lVar16 = gVar.d;
                BitSet bitSet16 = new BitSet(1);
                bitSet16.set(0, B1.a.W(((k) lVar16.d(0)).d));
                return bitSet16;
            case 19:
                l lVar17 = gVar.d;
                BitSet bitSet17 = new BitSet(2);
                bitSet17.set(0, B1.a.Y(((k) lVar17.d(0)).d));
                bitSet17.set(1, B1.a.Y(((k) lVar17.d(1)).d));
                return bitSet17;
            case 20:
                l lVar18 = gVar.d;
                int length3 = lVar18.e.length;
                BitSet bitSet18 = new BitSet(length3);
                for (int i2 = 0; i2 < length3; i2++) {
                    k kVar = (k) lVar18.d(i2);
                    bitSet18.set(i2, B1.a.X((kVar.c() + kVar.d) - 1));
                }
                return bitSet18;
            case 22:
                l lVar19 = gVar.d;
                int length4 = lVar19.e.length;
                BitSet bitSet19 = new BitSet(length4);
                boolean W11 = B1.a.W(((k) lVar19.d(0)).d);
                if (length4 == 1) {
                    bitSet19.set(0, W11);
                } else if (((k) lVar19.d(0)).d == ((k) lVar19.d(1)).d) {
                    bitSet19.set(0, W11);
                    bitSet19.set(1, W11);
                }
                return bitSet19;
            case 23:
                l lVar20 = gVar.d;
                BitSet bitSet20 = new BitSet(1);
                bitSet20.set(0, B1.a.W(((k) lVar20.d(0)).d));
                return bitSet20;
            case 24:
                l lVar21 = gVar.d;
                BitSet bitSet21 = new BitSet(2);
                bitSet21.set(0, B1.a.Y(((k) lVar21.d(0)).d));
                bitSet21.set(1, B1.a.Y(((k) lVar21.d(1)).d));
                return bitSet21;
            default:
                return super.n(gVar);
        }
    }
}
