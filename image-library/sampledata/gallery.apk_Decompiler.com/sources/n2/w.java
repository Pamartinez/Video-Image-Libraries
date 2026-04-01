package N2;

import A0.l;
import D0.e;
import D0.f;
import R2.a;
import R2.b;
import R2.g;
import R2.k;
import U2.d;
import t1.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    public final l f549a;
    public final l b;

    /* renamed from: c  reason: collision with root package name */
    public final e f550c;
    public final f d;
    public final int e;
    public int[] f = null;
    public final int g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f551h;

    /* JADX WARNING: type inference failed for: r5v0, types: [R2.e, java.lang.Object, N2.t] */
    /* JADX WARNING: type inference failed for: r1v3, types: [R2.e, D1.i, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v11, types: [R2.e, D1.i, java.lang.Object] */
    public w(l lVar, int i2, i iVar) {
        this.f549a = lVar;
        this.b = new l(lVar);
        this.g = i2;
        int i7 = 0;
        boolean[] zArr = {true};
        b bVar = (b) lVar.e;
        Object[] objArr = bVar.e;
        if (bVar.g == -1) {
            ? obj = new Object();
            obj.d = 0;
            int length = objArr.length;
            for (int i8 = 0; i8 < length; i8++) {
                g gVar = ((a) bVar.d(i8)).b;
                int length2 = gVar.e.length;
                for (int i10 = 0; i10 < length2; i10++) {
                    ((R2.f) gVar.d(i10)).c(obj);
                }
            }
            bVar.g = obj.d;
        }
        int i11 = bVar.g;
        ? obj2 = new Object();
        obj2.f = zArr;
        obj2.d = i11;
        obj2.e = i2;
        int length3 = objArr.length;
        for (int i12 = 0; i12 < length3; i12++) {
            g gVar2 = ((a) bVar.d(i12)).b;
            int length4 = gVar2.e.length;
            for (int i13 = 0; i13 < length4; i13++) {
                ((R2.f) gVar2.d(i13)).c(obj2);
            }
        }
        this.f551h = zArr[0];
        int length5 = objArr.length * 3;
        int i14 = 0;
        for (Object obj3 : objArr) {
            a aVar = (a) obj3;
            if (aVar != null) {
                i14 += aVar.b.e.length;
            }
        }
        int i15 = i14 + length5;
        if (bVar.g == -1) {
            ? obj4 = new Object();
            obj4.d = 0;
            int length6 = objArr.length;
            for (int i16 = 0; i16 < length6; i16++) {
                g gVar3 = ((a) bVar.d(i16)).b;
                int length7 = gVar3.e.length;
                for (int i17 = 0; i17 < length7; i17++) {
                    ((R2.f) gVar3.d(i17)).c(obj4);
                }
            }
            bVar.g = obj4.d;
        }
        int i18 = bVar.g + (!this.f551h ? this.g : i7);
        this.e = i18;
        e eVar = new e(iVar, i15, length5, i18);
        this.f550c = eVar;
        this.d = new f(this, eVar);
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [U2.d, Nf.f, R2.l] */
    public static R2.l a(R2.f fVar, k kVar) {
        R2.l lVar = fVar.g;
        int i2 = fVar.d.f652a;
        if (!(i2 == 14 || i2 == 16)) {
            switch (i2) {
                case 20:
                case 21:
                case 22:
                    break;
            }
        }
        if (lVar.e.length == 2 && kVar.d == ((k) lVar.d(1)).d) {
            lVar = R2.l.i((k) lVar.d(1), (k) lVar.d(0));
        }
        if (kVar == null) {
            return lVar;
        }
        int length = lVar.e.length;
        ? dVar = new d(length + 1);
        int i7 = 0;
        while (i7 < length) {
            int i8 = i7 + 1;
            dVar.e(i8, lVar.d(i7));
            i7 = i8;
        }
        dVar.e(0, kVar);
        if (!lVar.d) {
            dVar.d = false;
        }
        return dVar;
    }
}
