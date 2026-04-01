package androidx.window.layout;

import Ae.a;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.layout.WindowLayoutComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.k;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeWindowLayoutComponentProvider$windowLayoutComponent$2 extends k implements a {
    public static final SafeWindowLayoutComponentProvider$windowLayoutComponent$2 INSTANCE = new SafeWindowLayoutComponentProvider$windowLayoutComponent$2();

    public SafeWindowLayoutComponentProvider$windowLayoutComponent$2() {
        super(0);
    }

    public final WindowLayoutComponent invoke() {
        ClassLoader classLoader = SafeWindowLayoutComponentProvider.class.getClassLoader();
        if (classLoader == null || !SafeWindowLayoutComponentProvider.INSTANCE.canUseWindowLayoutComponent(classLoader)) {
            WindowLayoutComponent windowLayoutComponent = null;
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            WindowLayoutComponent windowLayoutComponent2 = null;
            return null;
        }
    }
}
