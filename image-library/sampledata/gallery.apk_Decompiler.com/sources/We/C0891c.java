package We;

import Ae.b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1192j;
import qf.C1240g;

/* renamed from: We.c  reason: case insensitive filesystem */
public final class C0891c implements b {
    public static final C0891c e = new C0891c(0);
    public static final C0891c f = new C0891c(1);
    public static final C0891c g = new C0891c(2);

    /* renamed from: h  reason: collision with root package name */
    public static final C0891c f3884h = new C0891c(3);
    public final /* synthetic */ int d;

    public /* synthetic */ C0891c(int i2) {
        this.d = i2;
    }

    public final Object invoke(Object obj) {
        boolean z;
        switch (this.d) {
            case 0:
                ParameterizedType parameterizedType = (ParameterizedType) obj;
                List list = C0892d.f3885a;
                j.e(parameterizedType, "it");
                Type ownerType = parameterizedType.getOwnerType();
                if (ownerType instanceof ParameterizedType) {
                    return (ParameterizedType) ownerType;
                }
                return null;
            case 1:
                ParameterizedType parameterizedType2 = (ParameterizedType) obj;
                List list2 = C0892d.f3885a;
                j.e(parameterizedType2, "it");
                Type[] actualTypeArguments = parameterizedType2.getActualTypeArguments();
                j.d(actualTypeArguments, "getActualTypeArguments(...)");
                return C1192j.b0(actualTypeArguments);
            case 2:
                if (((Class) obj).getSimpleName().length() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                String simpleName = ((Class) obj).getSimpleName();
                if (!C1240g.f(simpleName)) {
                    simpleName = null;
                }
                if (simpleName != null) {
                    return C1240g.e(simpleName);
                }
                return null;
        }
    }
}
