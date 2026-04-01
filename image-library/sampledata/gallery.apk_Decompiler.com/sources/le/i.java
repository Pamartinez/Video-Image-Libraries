package Le;

import D0.c;
import L1.d;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i extends w {
    public final /* synthetic */ int e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(Member member, Type type, Class cls, Type[] typeArr, int i2) {
        super(member, type, cls, typeArr);
        this.e = i2;
    }

    public final Object call(Object[] objArr) {
        switch (this.e) {
            case 0:
                j.e(objArr, "args");
                d.b(this, objArr);
                c cVar = new c(2);
                cVar.b(objArr);
                cVar.a((Object) null);
                ArrayList arrayList = cVar.d;
                return ((Constructor) this.f3530a).newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                j.e(objArr, "args");
                d.b(this, objArr);
                return ((Constructor) this.f3530a).newInstance(Arrays.copyOf(objArr, objArr.length));
        }
    }
}
