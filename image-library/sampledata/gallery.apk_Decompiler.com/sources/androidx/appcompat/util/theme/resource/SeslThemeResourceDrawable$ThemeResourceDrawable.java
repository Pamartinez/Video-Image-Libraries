package androidx.appcompat.util.theme.resource;

import android.content.Context;
import androidx.appcompat.util.SeslMisc;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0015\u001a\u0004\b\u0016\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0018"}, d2 = {"androidx/appcompat/util/theme/resource/SeslThemeResourceDrawable$ThemeResourceDrawable", "Landroidx/appcompat/util/theme/resource/SeslThemeResourceDrawable$ResourceDrawable;", "", "lightThemeResId", "darkThemeResId", "<init>", "(II)V", "Landroid/content/Context;", "context", "getDrawable", "(Landroid/content/Context;)I", "", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "I", "getLightThemeResId", "getDarkThemeResId", "appcompat_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslThemeResourceDrawable$ThemeResourceDrawable extends SeslThemeResourceDrawable$ResourceDrawable {
    private final int darkThemeResId;
    private final int lightThemeResId;

    public SeslThemeResourceDrawable$ThemeResourceDrawable(int i2, int i7) {
        this.lightThemeResId = i2;
        this.darkThemeResId = i7;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeslThemeResourceDrawable$ThemeResourceDrawable)) {
            return false;
        }
        SeslThemeResourceDrawable$ThemeResourceDrawable seslThemeResourceDrawable$ThemeResourceDrawable = (SeslThemeResourceDrawable$ThemeResourceDrawable) obj;
        if (this.lightThemeResId == seslThemeResourceDrawable$ThemeResourceDrawable.lightThemeResId && this.darkThemeResId == seslThemeResourceDrawable$ThemeResourceDrawable.darkThemeResId) {
            return true;
        }
        return false;
    }

    public int getDrawable(Context context) {
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
        StringBuilder sb2 = new StringBuilder("ThemeResourceDrawable(lightThemeResId=");
        sb2.append(this.lightThemeResId);
        sb2.append(", darkThemeResId=");
        return N2.j.e(sb2, this.darkThemeResId, ')');
    }
}
