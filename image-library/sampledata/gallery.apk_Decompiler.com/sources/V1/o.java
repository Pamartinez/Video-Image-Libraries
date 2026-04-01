package v1;

import E1.e;
import P1.b;
import P1.h;
import android.os.SystemClock;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.a;
import t1.C0276a;
import u1.C0285c;
import u1.d;
import w1.C0316c;
import w1.f;
import w1.g;
import w1.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class o implements b {
    public final C0298c d;
    public final int e;
    public final C0296a f;
    public final long g;

    /* renamed from: h  reason: collision with root package name */
    public final long f1971h;

    public o(C0298c cVar, int i2, C0296a aVar, long j2, long j3) {
        this.d = cVar;
        this.e = i2;
        this.f = aVar;
        this.g = j2;
        this.f1971h = j3;
    }

    public static C0316c a(k kVar, a aVar, int i2) {
        C0316c cVar;
        x xVar = aVar.u;
        if (xVar == null) {
            cVar = null;
        } else {
            cVar = xVar.g;
        }
        if (cVar != null && cVar.e) {
            int[] iArr = cVar.g;
            int i7 = 0;
            if (iArr == null) {
                int[] iArr2 = cVar.f1997i;
                if (iArr2 != null) {
                    while (true) {
                        if (i7 >= iArr2.length) {
                            break;
                        } else if (iArr2[i7] == i2) {
                            break;
                        } else {
                            i7++;
                        }
                    }
                }
            } else {
                while (true) {
                    if (i7 >= iArr.length) {
                        break;
                    } else if (iArr[i7] == i2) {
                        break;
                    } else {
                        i7++;
                    }
                }
            }
            if (kVar.l < cVar.f1996h) {
                return cVar;
            }
        }
        return null;
    }

    public final void o(h hVar) {
        boolean z;
        int i2;
        int i7;
        int i8;
        int i10;
        long j2;
        long j3;
        int i11;
        long j8 = this.g;
        C0298c cVar = this.d;
        if (cVar.a()) {
            w1.h hVar2 = (w1.h) g.b().f2007a;
            if (hVar2 == null || hVar2.e) {
                k kVar = (k) cVar.f1962j.get(this.f);
                if (kVar != null) {
                    C0285c cVar2 = kVar.b;
                    if (cVar2 instanceof a) {
                        a aVar = (a) cVar2;
                        int i12 = (j8 > 0 ? 1 : (j8 == 0 ? 0 : -1));
                        boolean z3 = true;
                        int i13 = 0;
                        if (i12 > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        int i14 = aVar.f1344p;
                        if (hVar2 != null) {
                            z &= hVar2.f;
                            i2 = hVar2.g;
                            i7 = hVar2.f2008h;
                            int i15 = hVar2.d;
                            if (aVar.u == null || aVar.b()) {
                                i8 = i15;
                            } else {
                                C0316c a7 = a(kVar, aVar, this.e);
                                if (a7 != null) {
                                    if (!a7.f || i12 <= 0) {
                                        z3 = false;
                                    }
                                    i7 = a7.f1996h;
                                    i8 = i15;
                                    z = z3;
                                } else {
                                    return;
                                }
                            }
                        } else {
                            i2 = 5000;
                            i7 = 100;
                            i8 = 0;
                        }
                        int i16 = i2;
                        int i17 = i7;
                        int i18 = -1;
                        if (hVar.g()) {
                            i10 = 0;
                        } else {
                            Exception e7 = hVar.e();
                            if (e7 instanceof d) {
                                Status status = ((d) e7).d;
                                i11 = status.d;
                                C0276a aVar2 = status.g;
                                if (aVar2 != null) {
                                    i10 = i11;
                                    i13 = aVar2.e;
                                }
                            } else {
                                i11 = 101;
                            }
                            i10 = i11;
                            i13 = -1;
                        }
                        if (z) {
                            long j10 = this.f1971h;
                            long currentTimeMillis = System.currentTimeMillis();
                            i18 = (int) (SystemClock.elapsedRealtime() - j10);
                            j3 = currentTimeMillis;
                            j2 = j8;
                        } else {
                            j2 = 0;
                            j3 = 0;
                        }
                        p pVar = new p(new f(this.e, i10, i13, j2, j3, (String) null, (String) null, i14, i18), i8, (long) i16, i17);
                        e eVar = cVar.m;
                        eVar.sendMessage(eVar.obtainMessage(18, pVar));
                    }
                }
            }
        }
    }
}
