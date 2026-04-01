package androidx.appcompat.util.theme.resource;

import android.content.Context;
import androidx.appcompat.util.SeslMisc;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0013\b\u0016\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0015\u001a\u00020\u00142\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÖ\u0003¢\u0006\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0017\u001a\u0004\b\u0018\u0010\u0011R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0017\u001a\u0004\b\u0019\u0010\u0011¨\u0006\u001a"}, d2 = {"androidx/appcompat/util/theme/resource/SeslThemeResourceColor$ThemeResourceColor", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceColor$ResourceColor;", "", "lightThemeResId", "darkThemeResId", "<init>", "(II)V", "resId", "(I)V", "Landroid/content/Context;", "context", "getColor", "(Landroid/content/Context;)I", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getLightThemeResId", "getDarkThemeResId", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslThemeResourceColor$ThemeResourceColor extends SeslThemeResourceColor$ResourceColor {
    private final int darkThemeResId;
    private final int lightThemeResId;

    public SeslThemeResourceColor$ThemeResourceColor(int i2, int i7) {
        this.lightThemeResId = i2;
        this.darkThemeResId = i7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeslThemeResourceColor$ThemeResourceColor)) {
            return false;
        }
        SeslThemeResourceColor$ThemeResourceColor seslThemeResourceColor$ThemeResourceColor = (SeslThemeResourceColor$ThemeResourceColor) obj;
        if (this.lightThemeResId == seslThemeResourceColor$ThemeResourceColor.lightThemeResId && this.darkThemeResId == seslThemeResourceColor$ThemeResourceColor.darkThemeResId) {
            return true;
        }
        return false;
    }

    public int getColor(Context context) {
        j.e(context, "context");
        if (SeslMisc.isLightTheme(context)) {
            return this.lightThemeResId;
        }
        return this.darkThemeResId;
    }

    public int hashCode() {
        return Integer.hashCode(this.darkThemeResId) + (Integer.hashCode(this.lightThemeResId) * 31);
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("ThemeResourceColor(lightThemeResId=");
        sb2.append(this.lightThemeResId);
        sb2.append(", darkThemeResId=");
        return N2.j.e(sb2, this.darkThemeResId, ')');
    }

    public SeslThemeResourceColor$ThemeResourceColor(int i2) {
        this(i2, i2);
    }
}
