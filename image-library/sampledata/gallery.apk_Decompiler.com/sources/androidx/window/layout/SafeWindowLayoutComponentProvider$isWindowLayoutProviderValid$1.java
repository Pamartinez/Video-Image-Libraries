package androidx.window.layout;

import Ae.a;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1 extends k implements a {
    final /* synthetic */ ClassLoader $classLoader;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1(ClassLoader classLoader) {
        super(0);
        this.$classLoader = classLoader;
    }

    public final Boolean invoke() {
        SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider = SafeWindowLayoutComponentProvider.INSTANCE;
        Method declaredMethod = safeWindowLayoutComponentProvider.windowExtensionsProviderClass(this.$classLoader).getDeclaredMethod("getWindowExtensions", (Class[]) null);
        Class access$windowExtensionsClass = safeWindowLayoutComponentProvider.windowExtensionsClass(this.$classLoader);
        j.d(declaredMethod, "getWindowExtensionsMethod");
        j.d(access$windowExtensionsClass, "windowExtensionsClass");
        return Boolean.valueOf(safeWindowLayoutComponentProvider.doesReturn(declaredMethod, (Class<?>) access$windowExtensionsClass) && safeWindowLayoutComponentProvider.isPublic(declaredMethod));
    }
}
