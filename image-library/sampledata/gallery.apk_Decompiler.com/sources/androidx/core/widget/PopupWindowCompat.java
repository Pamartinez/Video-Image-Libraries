package androidx.core.widget;

import android.view.View;
import android.widget.PopupWindow;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PopupWindowCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static boolean getOverlapAnchor(PopupWindow popupWindow) {
            return popupWindow.getOverlapAnchor();
        }

        public static void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
            popupWindow.setOverlapAnchor(z);
        }

        public static void setWindowLayoutType(PopupWindow popupWindow, int i2) {
            popupWindow.setWindowLayoutType(i2);
        }
    }

    public static boolean getOverlapAnchor(PopupWindow popupWindow) {
        return Api23Impl.getOverlapAnchor(popupWindow);
    }

    public static void setOverlapAnchor(PopupWindow popupWindow, boolean z) {
        Api23Impl.setOverlapAnchor(popupWindow, z);
    }

    public static void setWindowLayoutType(PopupWindow popupWindow, int i2) {
        Api23Impl.setWindowLayoutType(popupWindow, i2);
    }

    @Deprecated
    public static void showAsDropDown(PopupWindow popupWindow, View view, int i2, int i7, int i8) {
        popupWindow.showAsDropDown(view, i2, i7, i8);
    }
}
