package He;

import Ke.t0;
import Ke.v0;
import Ke.x0;
import i.C0212a;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1196n;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C implements TypeVariable, Type {
    public final v d;

    public C(v vVar) {
        this.d = vVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TypeVariable) || !getName().equals(((TypeVariable) obj).getName())) {
            return false;
        }
        getGenericDeclaration();
        throw null;
    }

    public final Type[] getBounds() {
        x0 x0Var = ((t0) this.d).e;
        t tVar = t0.g[0];
        Object invoke = x0Var.invoke();
        j.d(invoke, "getValue(...)");
        Iterable<u> iterable = (List) invoke;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (u v : iterable) {
            arrayList.add(F.v(v, true));
        }
        return (Type[]) arrayList.toArray(new Type[0]);
    }

    public final GenericDeclaration getGenericDeclaration() {
        throw new v0(C0212a.l("An operation is not implemented: ", "getGenericDeclaration() is not yet supported for type variables created from KType: " + this.d), 2);
    }

    public final String getName() {
        return ((t0) this.d).a();
    }

    public final String getTypeName() {
        return getName();
    }

    public final int hashCode() {
        getName();
        getGenericDeclaration();
        throw null;
    }

    public final String toString() {
        return getName();
    }
}
