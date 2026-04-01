package androidx.window.layout;

import Ae.a;
import android.graphics.Rect;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1 extends k implements a {
    final /* synthetic */ ClassLoader $classLoader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1(ClassLoader classLoader) {
        super(0);
        this.$classLoader = classLoader;
    }

    public final Boolean invoke() {
        boolean z;
        SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = SafeWindowLayoutComponentProvider.INSTANCE;
        Class access$foldingFeatureClass = safeWindowLayoutComponentProvider.foldingFeatureClass(this.$classLoader);
        Method method = access$foldingFeatureClass.getMethod("getBounds", (Class[]) null);
        Method method2 = access$foldingFeatureClass.getMethod("getType", (Class[]) null);
        Method method3 = access$foldingFeatureClass.getMethod("getState", (Class[]) null);
        j.d(method, "getBoundsMethod");
        w wVar = v.f4727a;
        if (safeWindowLayoutComponentProvider.doesReturn(method, wVar.b(Rect.class)) && safeWindowLayoutComponentProvider.isPublic(method)) {
            j.d(method2, "getTypeMethod");
            Class cls = Integer.TYPE;
            if (safeWindowLayoutComponentProvider.doesReturn(method2, wVar.b(cls)) && safeWindowLayoutComponentProvider.isPublic(method2)) {
                j.d(method3, "getStateMethod");
                if (safeWindowLayoutComponentProvider.doesReturn(method3, wVar.b(cls)) && safeWindowLayoutComponentProvider.isPublic(method3)) {
                    z = true;
                    return Boolean.valueOf(z);
                }
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}
