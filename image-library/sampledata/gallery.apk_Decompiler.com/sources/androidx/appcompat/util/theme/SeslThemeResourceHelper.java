package androidx.appcompat.util.theme;

import android.content.Context;
import android.graphics.drawable.Drawable;
import androidx.appcompat.util.theme.resource.SeslThemeResourceColor$ResourceColor;
import androidx.appcompat.util.theme.resource.SeslThemeResourceDrawable$ResourceDrawable;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/appcompat/util/theme/SeslThemeResourceHelper;", "", "Companion", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SeslThemeResourceHelper {
    public static final Companion Companion = new Companion((e) null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Landroidx/appcompat/util/theme/SeslThemeResourceHelper$Companion;", "", "()V", "getColorInt", "", "context", "Landroid/content/Context;", "resourceColor", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ResourceColor;", "getDrawable", "Landroid/graphics/drawable/Drawable;", "resource", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceDrawable$ResourceDrawable;", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        public final int getColorInt(Context context, SeslThemeResourceColor$ResourceColor seslThemeResourceColor$ResourceColor) {
            j.e(context, "context");
            j.e(seslThemeResourceColor$ResourceColor, "resourceColor");
            return ContextCompat.getColor(context, seslThemeResourceColor$ResourceColor.getColor(context));
        }

        public final Drawable getDrawable(Context context, SeslThemeResourceDrawable$ResourceDrawable seslThemeResourceDrawable$ResourceDrawable) {
            j.e(context, "context");
            j.e(seslThemeResourceDrawable$ResourceDrawable, "resource");
            return ContextCompat.getDrawable(context, seslThemeResourceDrawable$ResourceDrawable.getDrawable(context));
        }

        private Companion() {
        }
    }
}
