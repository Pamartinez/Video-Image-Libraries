package Af;

import Ae.b;
import Qe.C0812b;
import Qe.O;
import Te.K;
import kotlin.jvm.internal.j;
import qf.C1240g;

public final class m implements b {
    public static final m e = new m(0);
    public static final m f = new m(1);
    public static final m g = new m(2);

    /* renamed from: h  reason: collision with root package name */
    public static final m f3317h = new m(3);
    public final /* synthetic */ int d;

    public /* synthetic */ m(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                j.e((C1240g) obj, "it");
                return Boolean.TRUE;
            case 1:
                K k = (K) obj;
                j.e(k, "$this$selectMostSpecificInEachOverridableGroup");
                return k;
            case 2:
                O o2 = (O) obj;
                j.e(o2, "$this$selectMostSpecificInEachOverridableGroup");
                return o2;
            default:
                C0812b bVar = (C0812b) obj;
                j.e(bVar, "$this$selectMostSpecificInEachOverridableGroup");
                return bVar;
        }
    }
}
