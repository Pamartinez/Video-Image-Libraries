package androidx.appcompat.oneui.common.internal.resource;

import android.content.Context;
import androidx.appcompat.util.SeslMisc;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nR\u0010\u0010\u0004\u001a\u00028\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0010\u0010\u0003\u001a\u00028\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000b"}, d2 = {"Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceImpl;", "T", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;", "resourceLight", "resourceDark", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Ljava/lang/Object;", "getResource", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThemeResourceImpl<T> extends ThemeResource<T> {
    private final T resourceDark;
    private final T resourceLight;

    public ThemeResourceImpl(T t, T t3) {
        this.resourceLight = t;
        this.resourceDark = t3;
    }

    public T getResource(Context context) {
        j.e(context, "context");
        if (SeslMisc.isLightTheme(context)) {
            return this.resourceLight;
        }
        return this.resourceDark;
    }
}
