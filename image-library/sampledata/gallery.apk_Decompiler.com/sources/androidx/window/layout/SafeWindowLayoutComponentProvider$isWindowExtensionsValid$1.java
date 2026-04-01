package androidx.window.layout;

import Ae.a;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeWindowLayoutComponentProvider$isWindowExtensionsValid$1 extends k implements a {
    final /* synthetic */ ClassLoader $classLoader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SafeWindowLayoutComponentProvider$isWindowExtensionsValid$1(ClassLoader classLoader) {
        super(0);
        this.$classLoader = classLoader;
    }

    public final Boolean invoke() {
        boolean z;
        SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = SafeWindowLayoutComponentProvider.INSTANCE;
        Method method = safeWindowLayoutComponentProvider.windowExtensionsClass(this.$classLoader).getMethod("getWindowLayoutComponent", (Class[]) null);
        Class access$windowLayoutComponentClass = safeWindowLayoutComponentProvider.windowLayoutComponentClass(this.$classLoader);
        j.d(method, "getWindowLayoutComponentMethod");
        if (safeWindowLayoutComponentProvider.isPublic(method)) {
            j.d(access$windowLayoutComponentClass, "windowLayoutComponentClass");
            if (safeWindowLayoutComponentProvider.doesReturn(method, (Class<?>) access$windowLayoutComponentClass)) {
                z = true;
                return Boolean.valueOf(z);
            }
        }
        z = false;
        return Boolean.valueOf(z);
    }
}
