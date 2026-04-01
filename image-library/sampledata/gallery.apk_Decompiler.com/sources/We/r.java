package We;

import gf.C1074e;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends w implements C1074e {

    /* renamed from: a  reason: collision with root package name */
    public final Constructor f3893a;

    public r(Constructor constructor) {
        j.e(constructor, "member");
        this.f3893a = constructor;
    }

    public final Member b() {
        return this.f3893a;
    }

    public final ArrayList getTypeParameters() {
        TypeVariable[] typeParameters = this.f3893a.getTypeParameters();
        j.d(typeParameters, "getTypeParameters(...)");
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable c5 : typeParameters) {
            arrayList.add(new C(c5));
        }
        return arrayList;
    }
}
