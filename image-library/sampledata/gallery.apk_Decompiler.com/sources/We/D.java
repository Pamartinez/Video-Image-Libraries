package We;

import gf.C1071b;
import java.lang.annotation.Annotation;
import java.util.Collection;
import kotlin.jvm.internal.j;
import og.k;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends s implements C1071b {

    /* renamed from: a  reason: collision with root package name */
    public final B f3879a;
    public final Annotation[] b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3880c;
    public final boolean d;

    public D(B b5, Annotation[] annotationArr, String str, boolean z) {
        j.e(annotationArr, "reflectAnnotations");
        this.f3879a = b5;
        this.b = annotationArr;
        this.f3880c = str;
        this.d = z;
    }

    public final C0893e a(C1236c cVar) {
        j.e(cVar, "fqName");
        return k.w(this.b, cVar);
    }

    public final Collection getAnnotations() {
        return k.z(this.b);
    }

    public final String toString() {
        String str;
        C1240g gVar;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(D.class.getName());
        sb2.append(": ");
        if (this.d) {
            str = "vararg ";
        } else {
            str = "";
        }
        sb2.append(str);
        String str2 = this.f3880c;
        if (str2 != null) {
            gVar = C1240g.d(str2);
        } else {
            gVar = null;
        }
        sb2.append(gVar);
        sb2.append(": ");
        sb2.append(this.f3879a);
        return sb2.toString();
    }
}
