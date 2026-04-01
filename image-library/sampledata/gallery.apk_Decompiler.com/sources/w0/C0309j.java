package w0;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/* renamed from: w0.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0309j extends C0303d {
    public final LinkedHashMap d;
    public final LinkedList e;

    public C0309j() {
        super(C0308i.MAP);
        this.e = new LinkedList();
        this.d = new LinkedHashMap();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0309j) {
            C0309j jVar = (C0309j) obj;
            if (!super.equals(obj) || !this.d.equals(jVar.d)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() ^ super.hashCode();
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        if (this.f1983c) {
            sb2.append("{_ ");
        } else {
            sb2.append("{ ");
        }
        for (C0304e eVar : this.e) {
            sb2.append(eVar);
            sb2.append(": ");
            sb2.append(this.d.get(eVar));
            sb2.append(ArcCommonLog.TAG_COMMA);
        }
        if (sb2.toString().endsWith(ArcCommonLog.TAG_COMMA)) {
            sb2.setLength(sb2.length() - 2);
        }
        sb2.append(" }");
        return sb2.toString();
    }

    public C0309j(int i2) {
        super(C0308i.MAP);
        this.e = new LinkedList();
        this.d = new LinkedHashMap(i2);
    }
}
