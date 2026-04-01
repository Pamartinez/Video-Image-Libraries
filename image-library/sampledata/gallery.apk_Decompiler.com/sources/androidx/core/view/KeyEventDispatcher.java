package androidx.core.view;

import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class KeyEventDispatcher {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent keyEvent);
    }

    public static boolean dispatchBeforeHierarchy(View view, KeyEvent keyEvent) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(view, keyEvent);
    }

    public static boolean dispatchKeyEvent(Component component, View view, Window.Callback callback, KeyEvent keyEvent) {
        if (component == null) {
            return false;
        }
        return component.superDispatchKeyEvent(keyEvent);
    }
}
