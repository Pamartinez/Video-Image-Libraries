package androidx.appcompat.util.theme.resource;

import android.content.Context;
import androidx.appcompat.util.SeslMisc;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\r\u001a\u00020\fHÖ\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018¨\u0006\u001a"}, d2 = {"androidx/appcompat/util/theme/resource/SeslThemeResourceColor$OpenThemeResourceColor", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ResourceColor;", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor;", "defaultThemeResource", "openThemeResource", "<init>", "(Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor;Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor;)V", "Landroid/content/Context;", "context", "", "getColor", "(Landroid/content/Context;)I", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor;", "getDefaultThemeResource", "()Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor;", "getOpenThemeResource", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslThemeResourceColor$OpenThemeResourceColor extends SeslThemeResourceColor$ResourceColor {
    private final SeslThemeResourceColor$ThemeResourceColor defaultThemeResource;
    private final SeslThemeResourceColor$ThemeResourceColor openThemeResource;

    public SeslThemeResourceColor$OpenThemeResourceColor(SeslThemeResourceColor$ThemeResourceColor seslThemeResourceColor$ThemeResourceColor, SeslThemeResourceColor$ThemeResourceColor seslThemeResourceColor$ThemeResourceColor2) {
        j.e(seslThemeResourceColor$ThemeResourceColor, "defaultThemeResource");
        j.e(seslThemeResourceColor$ThemeResourceColor2, "openThemeResource");
        this.defaultThemeResource = seslThemeResourceColor$ThemeResourceColor;
        this.openThemeResource = seslThemeResourceColor$ThemeResourceColor2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeslThemeResourceColor$OpenThemeResourceColor)) {
            return false;
        }
        SeslThemeResourceColor$OpenThemeResourceColor seslThemeResourceColor$OpenThemeResourceColor = (SeslThemeResourceColor$OpenThemeResourceColor) obj;
        if (j.a(this.defaultThemeResource, seslThemeResourceColor$OpenThemeResourceColor.defaultThemeResource) && j.a(this.openThemeResource, seslThemeResourceColor$OpenThemeResourceColor.openThemeResource)) {
            return true;
        }
        return false;
    }

    public int getColor(Context context) {
        j.e(context, "context");
        if (SeslMisc.isDefaultTheme(context)) {
            return this.defaultThemeResource.getColor(context);
        }
        return this.openThemeResource.getColor(context);
    }

    public int hashCode() {
        return this.openThemeResource.hashCode() + (this.defaultThemeResource.hashCode() * 31);
    }

    public String toString() {
        return "OpenThemeResourceColor(defaultThemeResource=" + this.defaultThemeResource + ", openThemeResource=" + this.openThemeResource + ')';
    }
}
