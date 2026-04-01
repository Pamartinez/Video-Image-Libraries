package androidx.appcompat.oneui.common.internal.resource;

import android.content.Context;
import androidx.appcompat.util.SeslMisc;
import androidx.core.util.SeslDisplayUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B#\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u000b\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/appcompat/oneui/common/internal/resource/OpenThemeResourceImpl;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;", "", "defaultThemeResource", "openThemeResource", "<init>", "(Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;)V", "Landroid/content/Context;", "context", "getResource", "(Landroid/content/Context;)Ljava/lang/Integer;", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;", "getDefaultThemeResource", "()Landroidx/appcompat/oneui/common/internal/resource/ThemeResource;", "getOpenThemeResource", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OpenThemeResourceImpl extends ThemeResource<Integer> {
    private final ThemeResource<Integer> defaultThemeResource;
    private final ThemeResource<Integer> openThemeResource;

    public OpenThemeResourceImpl(ThemeResource<Integer> themeResource, ThemeResource<Integer> themeResource2) {
        j.e(themeResource, "defaultThemeResource");
        j.e(themeResource2, "openThemeResource");
        this.defaultThemeResource = themeResource;
        this.openThemeResource = themeResource2;
    }

    public Integer getResource(Context context) {
        int i2;
        j.e(context, "context");
        if (SeslDisplayUtils.isDexEnabled(context) || SeslMisc.isDefaultTheme(context)) {
            i2 = this.defaultThemeResource.getResource(context).intValue();
        } else {
            i2 = this.openThemeResource.getResource(context).intValue();
        }
        return Integer.valueOf(i2);
    }
}
