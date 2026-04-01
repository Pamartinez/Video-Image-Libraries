package Hf;

import Kf.e;
import Kf.f;
import Re.b;
import Re.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import kotlin.jvm.internal.j;
import ne.C1194l;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class B extends c0 implements e, f {
    /* renamed from: B0 */
    public abstract B y0(boolean z);

    /* renamed from: C0 */
    public abstract B A0(I i2);

    public String toString() {
        StringBuilder sb2 = new StringBuilder();
        for (b x9 : getAnnotations()) {
            String[] strArr = {"[", C1283j.e.x(x9, (d) null), "] "};
            for (int i2 = 0; i2 < 3; i2++) {
                sb2.append(strArr[i2]);
            }
        }
        sb2.append(s0());
        if (!e0().isEmpty()) {
            C1194l.Q0(e0(), sb2, ArcCommonLog.TAG_COMMA, "<", ">", (Ae.b) null, 112);
        }
        if (u0()) {
            sb2.append("?");
        }
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }
}
