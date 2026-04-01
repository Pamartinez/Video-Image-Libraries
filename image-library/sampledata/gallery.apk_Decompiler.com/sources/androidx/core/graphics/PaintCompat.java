package androidx.core.graphics;

import android.graphics.BlendMode;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.core.util.Pair;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PaintCompat {
    private static final ThreadLocal<Pair<Rect, Rect>> sRectThreadLocal = new ThreadLocal<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static boolean hasGlyph(Paint paint, String str) {
            return paint.hasGlyph(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api29Impl {
        public static void setBlendMode(Paint paint, Object obj) {
            paint.setBlendMode((BlendMode) obj);
        }
    }

    public static boolean hasGlyph(Paint paint, String str) {
        return Api23Impl.hasGlyph(paint, str);
    }

    public static boolean setBlendMode(Paint paint, BlendModeCompat blendModeCompat) {
        Object obj;
        if (blendModeCompat != null) {
            obj = BlendModeUtils$Api29Impl.obtainBlendModeFromCompat(blendModeCompat);
        } else {
            obj = null;
        }
        Api29Impl.setBlendMode(paint, obj);
        return true;
    }
}
