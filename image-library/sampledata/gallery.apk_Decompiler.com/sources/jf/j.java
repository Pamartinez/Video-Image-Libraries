package Jf;

import Hf.M;
import Ne.e;
import Ne.i;
import Qe.C0819i;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements M {

    /* renamed from: a  reason: collision with root package name */
    public final k f3480a;
    public final String[] b;

    /* renamed from: c  reason: collision with root package name */
    public final String f3481c;

    public j(k kVar, String... strArr) {
        kotlin.jvm.internal.j.e(kVar, "kind");
        kotlin.jvm.internal.j.e(strArr, "formatParams");
        this.f3480a = kVar;
        this.b = strArr;
        String a7 = b.ERROR_TYPE.a();
        String a10 = kVar.a();
        Object[] copyOf = Arrays.copyOf(strArr, strArr.length);
        this.f3481c = String.format(a7, Arrays.copyOf(new Object[]{String.format(a10, Arrays.copyOf(copyOf, copyOf.length))}, 1));
    }

    public final i f() {
        return (e) e.f.getValue();
    }

    public final C0819i g() {
        l.f3482a.getClass();
        return l.f3483c;
    }

    public final List getParameters() {
        return C1202t.d;
    }

    public final Collection h() {
        return C1202t.d;
    }

    public final boolean i() {
        return false;
    }

    public final String toString() {
        return this.f3481c;
    }
}
