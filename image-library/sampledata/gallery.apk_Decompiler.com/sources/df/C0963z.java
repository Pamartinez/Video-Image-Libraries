package df;

import Hf.C0774x;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* renamed from: df.z  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0963z {

    /* renamed from: a  reason: collision with root package name */
    public final C0774x f4264a;
    public final List b;

    /* renamed from: c  reason: collision with root package name */
    public final ArrayList f4265c;
    public final List d;

    public C0963z(C0774x xVar, List list, ArrayList arrayList, List list2) {
        this.f4264a = xVar;
        this.b = list;
        this.f4265c = arrayList;
        this.d = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0963z)) {
            return false;
        }
        C0963z zVar = (C0963z) obj;
        if (this.f4264a.equals(zVar.f4264a) && this.b.equals(zVar.b) && this.f4265c.equals(zVar.f4265c) && this.d.equals(zVar.d)) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.d.hashCode() + C0212a.e((this.f4265c.hashCode() + C0212a.f(this.b, this.f4264a.hashCode() * 961, 31)) * 31, 31, false);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MethodSignatureData(returnType=");
        sb2.append(this.f4264a);
        sb2.append(", receiverType=null, valueParameters=");
        sb2.append(this.b);
        sb2.append(", typeParameters=");
        sb2.append(this.f4265c);
        sb2.append(", hasStableParameterNames=false, errors=");
        return C0212a.r(sb2, this.d, ')');
    }
}
