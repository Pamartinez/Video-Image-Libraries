package Se;

import Ff.v;
import Qe.C0816f;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements b, d {
    public static final a b = new a(0);

    /* renamed from: c  reason: collision with root package name */
    public static final a f3725c = new a(1);
    public static final a d = new a(2);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3726a;

    public /* synthetic */ a(int i2) {
        this.f3726a = i2;
    }

    public Collection a(C0816f fVar) {
        return C1202t.d;
    }

    public boolean b(C0816f fVar, v vVar) {
        switch (this.f3726a) {
            case 1:
                j.e(fVar, "classDescriptor");
                return true;
            default:
                j.e(fVar, "classDescriptor");
                return !vVar.getAnnotations().g(e.f3727a);
        }
    }

    public Collection c(C0816f fVar) {
        return C1202t.d;
    }

    public Collection d(C0816f fVar) {
        j.e(fVar, "classDescriptor");
        return C1202t.d;
    }

    public Collection e(C1240g gVar, C0816f fVar) {
        j.e(gVar, "name");
        j.e(fVar, "classDescriptor");
        return C1202t.d;
    }
}
