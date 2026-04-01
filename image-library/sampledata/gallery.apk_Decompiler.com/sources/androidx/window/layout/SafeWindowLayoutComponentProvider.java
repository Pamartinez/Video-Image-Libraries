package androidx.window.layout;

import Ae.a;
import He.C0748d;
import L1.d;
import a.C0068a;
import androidx.window.extensions.layout.WindowLayoutComponent;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import me.f;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\t\u0010\bJ\u0017\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\n\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u000b\u0010\bJ\u0017\u0010\f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0003¢\u0006\u0004\b\f\u0010\bJ\u001d\u0010\u000f\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u0006*\u00020\u00112\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001f\u0010\u0014\u001a\u00020\u0006*\u00020\u00112\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0016H\u0002¢\u0006\u0004\b\u0014\u0010\u0017J'\u0010\u0019\u001a\u0012\u0012\u0002\b\u0003 \u0018*\b\u0012\u0002\b\u0003\u0018\u00010\u00160\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ'\u0010\u001b\u001a\u0012\u0012\u0002\b\u0003 \u0018*\b\u0012\u0002\b\u0003\u0018\u00010\u00160\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001b\u0010\u001aJ'\u0010\u001c\u001a\u0012\u0012\u0002\b\u0003 \u0018*\b\u0012\u0002\b\u0003\u0018\u00010\u00160\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001c\u0010\u001aJ'\u0010\u001d\u001a\u0012\u0012\u0002\b\u0003 \u0018*\b\u0012\u0002\b\u0003\u0018\u00010\u00160\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u001d\u0010\u001aR\u001d\u0010#\u001a\u0004\u0018\u00010\u001e8FX\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R\u0018\u0010$\u001a\u00020\u0006*\u00020\u00118BX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "<init>", "()V", "Ljava/lang/ClassLoader;", "classLoader", "", "canUseWindowLayoutComponent", "(Ljava/lang/ClassLoader;)Z", "isWindowLayoutProviderValid", "isWindowExtensionsValid", "isFoldingFeatureValid", "isWindowLayoutComponentValid", "Lkotlin/Function0;", "block", "validate", "(LAe/a;)Z", "Ljava/lang/reflect/Method;", "LHe/d;", "clazz", "doesReturn", "(Ljava/lang/reflect/Method;LHe/d;)Z", "Ljava/lang/Class;", "(Ljava/lang/reflect/Method;Ljava/lang/Class;)Z", "kotlin.jvm.PlatformType", "windowExtensionsProviderClass", "(Ljava/lang/ClassLoader;)Ljava/lang/Class;", "windowExtensionsClass", "foldingFeatureClass", "windowLayoutComponentClass", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponent$delegate", "Lme/f;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "windowLayoutComponent", "isPublic", "(Ljava/lang/reflect/Method;)Z", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SafeWindowLayoutComponentProvider {
    public static final SafeWindowLayoutComponentProvider INSTANCE = new SafeWindowLayoutComponentProvider();
    private static final f windowLayoutComponent$delegate = d.q(SafeWindowLayoutComponentProvider$windowLayoutComponent$2.INSTANCE);

    private SafeWindowLayoutComponentProvider() {
    }

    /* access modifiers changed from: private */
    public final boolean canUseWindowLayoutComponent(ClassLoader classLoader) {
        if (!isWindowLayoutProviderValid(classLoader) || !isWindowExtensionsValid(classLoader) || !isWindowLayoutComponentValid(classLoader) || !isFoldingFeatureValid(classLoader)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public final boolean doesReturn(Method method, C0748d dVar) {
        return doesReturn(method, (Class<?>) C0068a.A(dVar));
    }

    /* access modifiers changed from: private */
    public final Class<?> foldingFeatureClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.layout.FoldingFeature");
    }

    private final boolean isFoldingFeatureValid(ClassLoader classLoader) {
        return validate(new SafeWindowLayoutComponentProvider$isFoldingFeatureValid$1(classLoader));
    }

    /* access modifiers changed from: private */
    public final boolean isPublic(Method method) {
        return Modifier.isPublic(method.getModifiers());
    }

    private final boolean isWindowExtensionsValid(ClassLoader classLoader) {
        return validate(new SafeWindowLayoutComponentProvider$isWindowExtensionsValid$1(classLoader));
    }

    private final boolean isWindowLayoutComponentValid(ClassLoader classLoader) {
        return validate(new SafeWindowLayoutComponentProvider$isWindowLayoutComponentValid$1(classLoader));
    }

    private final boolean isWindowLayoutProviderValid(ClassLoader classLoader) {
        return validate(new SafeWindowLayoutComponentProvider$isWindowLayoutProviderValid$1(classLoader));
    }

    private final boolean validate(a aVar) {
        try {
            return ((Boolean) aVar.invoke()).booleanValue();
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return false;
        }
    }

    /* access modifiers changed from: private */
    public final Class<?> windowExtensionsClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.WindowExtensions");
    }

    /* access modifiers changed from: private */
    public final Class<?> windowExtensionsProviderClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.WindowExtensionsProvider");
    }

    /* access modifiers changed from: private */
    public final Class<?> windowLayoutComponentClass(ClassLoader classLoader) {
        return classLoader.loadClass("androidx.window.extensions.layout.WindowLayoutComponent");
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        return (WindowLayoutComponent) windowLayoutComponent$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final boolean doesReturn(Method method, Class<?> cls) {
        return method.getReturnType().equals(cls);
    }
}
