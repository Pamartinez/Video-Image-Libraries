package androidx.core.view;

import android.view.Menu;
import androidx.core.internal.view.SupportMenu;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api28Impl {
        public static void setGroupDividerEnabled(Menu menu, boolean z) {
            menu.setGroupDividerEnabled(z);
        }
    }

    public static void setGroupDividerEnabled(Menu menu, boolean z) {
        if (menu instanceof SupportMenu) {
            ((SupportMenu) menu).setGroupDividerEnabled(z);
        } else {
            Api28Impl.setGroupDividerEnabled(menu, z);
        }
    }
}
