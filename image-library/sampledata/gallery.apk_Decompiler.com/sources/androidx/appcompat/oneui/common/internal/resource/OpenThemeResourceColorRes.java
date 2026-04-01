package androidx.appcompat.oneui.common.internal.resource;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/appcompat/oneui/common/internal/resource/OpenThemeResourceColorRes;", "Landroidx/appcompat/oneui/common/internal/resource/OpenThemeResourceImpl;", "defaultThemeResource", "Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColorRes;", "openThemeResource", "(Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColorRes;Landroidx/appcompat/oneui/common/internal/resource/ThemeResourceColorRes;)V", "getResource", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Integer;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class OpenThemeResourceColorRes extends OpenThemeResourceImpl {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OpenThemeResourceColorRes(ThemeResourceColorRes themeResourceColorRes, ThemeResourceColorRes themeResourceColorRes2) {
        super(themeResourceColorRes, themeResourceColorRes2);
        j.e(themeResourceColorRes, "defaultThemeResource");
        j.e(themeResourceColorRes2, "openThemeResource");
    }

    public Integer getResource(Context context) {
        j.e(context, "context");
        return super.getResource(context);
    }
}
