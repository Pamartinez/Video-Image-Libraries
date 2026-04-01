package Vf;

import Ae.c;
import cg.w;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.motionphoto.utils.v2.io.HEIFMetaWriter;
import java.io.FileDescriptor;
import kotlin.jvm.internal.j;
import qe.C1226b;
import qe.C1228d;
import qe.C1229e;
import qe.C1230f;
import qe.C1232h;
import qe.C1233i;

/* renamed from: Vf.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0884v implements c {
    public final /* synthetic */ int d;

    public /* synthetic */ C0884v(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj, Object obj2) {
        Integer num;
        int i2;
        Object obj3;
        C1226b bVar;
        switch (this.d) {
            case 0:
                Boolean bool = (Boolean) obj;
                bool.booleanValue();
                obj3 = bool;
                break;
            case 1:
                return ((C1232h) obj).plus((C1230f) obj2);
            case 2:
                return ((C1232h) obj).plus((C1230f) obj2);
            case 3:
                C1230f fVar = (C1230f) obj2;
                return Integer.valueOf(((Integer) obj).intValue() + 1);
            case 4:
                C1230f fVar2 = (C1230f) obj2;
                if (!(fVar2 instanceof x0)) {
                    return obj;
                }
                if (obj instanceof Integer) {
                    num = (Integer) obj;
                } else {
                    num = null;
                }
                if (num != null) {
                    i2 = num.intValue();
                } else {
                    i2 = 1;
                }
                if (i2 == 0) {
                    return fVar2;
                }
                return Integer.valueOf(i2 + 1);
            case 5:
                x0 x0Var = (x0) obj;
                C1230f fVar3 = (C1230f) obj2;
                if (x0Var != null) {
                    return x0Var;
                }
                if (fVar3 instanceof x0) {
                    return (x0) fVar3;
                }
                return null;
            case 6:
                obj3 = (w) obj;
                break;
            case 7:
                return Long.valueOf(HEIFMetaWriter._init_$lambda$0((FileDescriptor) obj, ((Integer) obj2).intValue()));
            case 8:
                String str = (String) obj;
                C1230f fVar4 = (C1230f) obj2;
                j.e(str, "acc");
                j.e(fVar4, "element");
                if (str.length() == 0) {
                    return fVar4.toString();
                }
                return str + ArcCommonLog.TAG_COMMA + fVar4;
            default:
                C1232h hVar = (C1232h) obj;
                C1230f fVar5 = (C1230f) obj2;
                j.e(hVar, "acc");
                j.e(fVar5, "element");
                C1232h minusKey = hVar.minusKey(fVar5.getKey());
                C1233i iVar = C1233i.d;
                if (minusKey == iVar) {
                    return fVar5;
                }
                C1228d dVar = C1228d.d;
                C1229e eVar = (C1229e) minusKey.get(dVar);
                if (eVar == null) {
                    bVar = new C1226b(minusKey, fVar5);
                } else {
                    C1232h minusKey2 = minusKey.minusKey(dVar);
                    if (minusKey2 == iVar) {
                        return new C1226b(fVar5, eVar);
                    }
                    bVar = new C1226b(new C1226b(minusKey2, fVar5), eVar);
                }
                return bVar;
        }
        C1230f fVar6 = (C1230f) obj2;
        return obj3;
    }
}
