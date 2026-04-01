package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DrawableCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api21Impl {
        public static void applyTheme(Drawable drawable, Resources.Theme theme) {
            drawable.applyTheme(theme);
        }

        public static boolean canApplyTheme(Drawable drawable) {
            return drawable.canApplyTheme();
        }

        public static ColorFilter getColorFilter(Drawable drawable) {
            return drawable.getColorFilter();
        }

        public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            drawable.inflate(resources, xmlPullParser, attributeSet, theme);
        }

        public static void setHotspot(Drawable drawable, float f, float f5) {
            drawable.setHotspot(f, f5);
        }

        public static void setHotspotBounds(Drawable drawable, int i2, int i7, int i8, int i10) {
            drawable.setHotspotBounds(i2, i7, i8, i10);
        }

        public static void setTint(Drawable drawable, int i2) {
            drawable.setTint(i2);
        }

        public static void setTintList(Drawable drawable, ColorStateList colorStateList) {
            drawable.setTintList(colorStateList);
        }

        public static void setTintMode(Drawable drawable, PorterDuff.Mode mode) {
            drawable.setTintMode(mode);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static int getLayoutDirection(Drawable drawable) {
            return drawable.getLayoutDirection();
        }

        public static boolean setLayoutDirection(Drawable drawable, int i2) {
            return drawable.setLayoutDirection(i2);
        }
    }

    public static void applyTheme(Drawable drawable, Resources.Theme theme) {
        Api21Impl.applyTheme(drawable, theme);
    }

    public static boolean canApplyTheme(Drawable drawable) {
        return Api21Impl.canApplyTheme(drawable);
    }

    public static void clearColorFilter(Drawable drawable) {
        drawable.clearColorFilter();
    }

    @Deprecated
    public static int getAlpha(Drawable drawable) {
        return drawable.getAlpha();
    }

    public static ColorFilter getColorFilter(Drawable drawable) {
        return Api21Impl.getColorFilter(drawable);
    }

    public static int getLayoutDirection(Drawable drawable) {
        return Api23Impl.getLayoutDirection(drawable);
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        Api21Impl.inflate(drawable, resources, xmlPullParser, attributeSet, theme);
    }

    @Deprecated
    public static boolean isAutoMirrored(Drawable drawable) {
        return drawable.isAutoMirrored();
    }

    @Deprecated
    public static void jumpToCurrentState(Drawable drawable) {
        drawable.jumpToCurrentState();
    }

    @Deprecated
    public static void setAutoMirrored(Drawable drawable, boolean z) {
        drawable.setAutoMirrored(z);
    }

    public static void setHotspot(Drawable drawable, float f, float f5) {
        Api21Impl.setHotspot(drawable, f, f5);
    }

    public static void setHotspotBounds(Drawable drawable, int i2, int i7, int i8, int i10) {
        Api21Impl.setHotspotBounds(drawable, i2, i7, i8, i10);
    }

    public static boolean setLayoutDirection(Drawable drawable, int i2) {
        return Api23Impl.setLayoutDirection(drawable, i2);
    }

    public static void setTint(Drawable drawable, int i2) {
        Api21Impl.setTint(drawable, i2);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorStateList) {
        Api21Impl.setTintList(drawable, colorStateList);
    }

    public static void setTintMode(Drawable drawable, PorterDuff.Mode mode) {
        Api21Impl.setTintMode(drawable, mode);
    }

    public static <T extends Drawable> T unwrap(Drawable drawable) {
        if (drawable instanceof WrappedDrawable) {
            return ((WrappedDrawable) drawable).getWrappedDrawable();
        }
        return drawable;
    }

    public static Drawable wrap(Drawable drawable) {
        return drawable;
    }
}
