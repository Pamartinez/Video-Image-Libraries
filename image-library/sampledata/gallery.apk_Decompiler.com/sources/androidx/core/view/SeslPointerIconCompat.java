package androidx.core.view;

import androidx.reflect.view.SeslPointerIconReflector;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0007R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/core/view/SeslPointerIconCompat;", "", "()V", "TYPE_SEM_STYLUS_DEFAULT", "", "TYPE_SEM_STYLUS_MORE", "TYPE_SEM_STYLUS_PEN_SELECT", "TYPE_SEM_STYLUS_SCROLL_DOWN", "TYPE_SEM_STYLUS_SCROLL_LEFT", "TYPE_SEM_STYLUS_SCROLL_RIGHT", "TYPE_SEM_STYLUS_SCROLL_UP", "isSemStylusDefault", "", "iconId", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SeslPointerIconCompat {
    public static final SeslPointerIconCompat INSTANCE = new SeslPointerIconCompat();
    public static final int TYPE_SEM_STYLUS_DEFAULT = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_DEFAULT();
    public static final int TYPE_SEM_STYLUS_MORE = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_MORE();
    public static final int TYPE_SEM_STYLUS_PEN_SELECT = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_PEN_SELECT();
    public static final int TYPE_SEM_STYLUS_SCROLL_DOWN = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_DOWN();
    public static final int TYPE_SEM_STYLUS_SCROLL_LEFT = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_LEFT();
    public static final int TYPE_SEM_STYLUS_SCROLL_RIGHT = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_RIGHT();
    public static final int TYPE_SEM_STYLUS_SCROLL_UP = SeslPointerIconReflector.getField_SEM_TYPE_STYLUS_SCROLL_UP();

    private SeslPointerIconCompat() {
    }

    public static final boolean isSemStylusDefault(int i2) {
        if (i2 == TYPE_SEM_STYLUS_DEFAULT) {
            return true;
        }
        return false;
    }
}
