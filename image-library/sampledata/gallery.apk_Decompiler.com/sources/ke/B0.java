package Ke;

import Hf.C0774x;
import Qe.C0814d;
import Qe.C0831v;
import Qe.O;
import Te.C0852m;
import Te.u;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import qf.C1240g;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class B0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1283j f3485a = C1283j.f5083c;

    public static void a(C0814d dVar, StringBuilder sb2) {
        boolean z;
        u g = E0.g(dVar);
        u H5 = dVar.H();
        if (g != null) {
            sb2.append(d(g.getType()));
            sb2.append(".");
        }
        if (g == null || H5 == null) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            sb2.append("(");
        }
        if (H5 != null) {
            sb2.append(d(H5.getType()));
            sb2.append(".");
        }
        if (z) {
            sb2.append(")");
        }
    }

    public static String b(C0831v vVar) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("fun ");
        a(vVar, sb2);
        C1240g name = ((C0852m) vVar).getName();
        j.d(name, "getName(...)");
        sb2.append(f3485a.O(name, true));
        List B = vVar.B();
        j.d(B, "getValueParameters(...)");
        C1194l.Q0(B, sb2, ArcCommonLog.TAG_COMMA, "(", ")", C0779b.k, 48);
        sb2.append(": ");
        C0774x returnType = vVar.getReturnType();
        j.b(returnType);
        sb2.append(d(returnType));
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static String c(O o2) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        if (o2.G()) {
            str = "var ";
        } else {
            str = "val ";
        }
        sb2.append(str);
        a(o2, sb2);
        C1240g name = o2.getName();
        j.d(name, "getName(...)");
        sb2.append(f3485a.O(name, true));
        sb2.append(": ");
        C0774x type = o2.getType();
        j.d(type, "getType(...)");
        sb2.append(d(type));
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }

    public static String d(C0774x xVar) {
        j.e(xVar, "type");
        return f3485a.Y(xVar);
    }
}
