package androidx.appcompat.view;

import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class WindowCallbackWrapper implements Window.Callback {
    final Window.Callback mWrapped;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api23Impl {
        public static boolean onSearchRequested(Window.Callback callback, SearchEvent searchEvent) {
            return callback.onSearchRequested(searchEvent);
        }

        public static ActionMode onWindowStartingActionMode(Window.Callback callback, ActionMode.Callback callback2, int i2) {
            return callback.onWindowStartingActionMode(callback2, i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api24Impl {
        public static void onProvideKeyboardShortcuts(Window.Callback callback, List<KeyboardShortcutGroup> list, Menu menu, int i2) {
            callback.onProvideKeyboardShortcuts(list, menu, i2);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Api26Impl {
        public static void onPointerCaptureChanged(Window.Callback callback, boolean z) {
            callback.onPointerCaptureChanged(z);
        }
    }

    public WindowCallbackWrapper(Window.Callback callback) {
        if (callback != null) {
            this.mWrapped = callback;
            return;
        }
        throw new IllegalArgumentException("Window callback may not be null");
    }

    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return this.mWrapped.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return this.mWrapped.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return this.mWrapped.dispatchKeyShortcutEvent(keyEvent);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return this.mWrapped.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mWrapped.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return this.mWrapped.dispatchTrackballEvent(motionEvent);
    }

    public final Window.Callback getWrapped() {
        return this.mWrapped;
    }

    public void onActionModeFinished(ActionMode actionMode) {
        this.mWrapped.onActionModeFinished(actionMode);
    }

    public void onActionModeStarted(ActionMode actionMode) {
        this.mWrapped.onActionModeStarted(actionMode);
    }

    public void onAttachedToWindow() {
        this.mWrapped.onAttachedToWindow();
    }

    public boolean onCreatePanelMenu(int i2, Menu menu) {
        return this.mWrapped.onCreatePanelMenu(i2, menu);
    }

    public View onCreatePanelView(int i2) {
        return this.mWrapped.onCreatePanelView(i2);
    }

    public void onDetachedFromWindow() {
        this.mWrapped.onDetachedFromWindow();
    }

    public boolean onMenuItemSelected(int i2, MenuItem menuItem) {
        return this.mWrapped.onMenuItemSelected(i2, menuItem);
    }

    public boolean onMenuOpened(int i2, Menu menu) {
        return this.mWrapped.onMenuOpened(i2, menu);
    }

    public void onPanelClosed(int i2, Menu menu) {
        this.mWrapped.onPanelClosed(i2, menu);
    }

    public void onPointerCaptureChanged(boolean z) {
        Api26Impl.onPointerCaptureChanged(this.mWrapped, z);
    }

    public boolean onPreparePanel(int i2, View view, Menu menu) {
        return this.mWrapped.onPreparePanel(i2, view, menu);
    }

    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> list, Menu menu, int i2) {
        Api24Impl.onProvideKeyboardShortcuts(this.mWrapped, list, menu, i2);
    }

    public boolean onSearchRequested(SearchEvent searchEvent) {
        return Api23Impl.onSearchRequested(this.mWrapped, searchEvent);
    }

    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        this.mWrapped.onWindowAttributesChanged(layoutParams);
    }

    public void onWindowFocusChanged(boolean z) {
        this.mWrapped.onWindowFocusChanged(z);
    }

    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i2) {
        return Api23Impl.onWindowStartingActionMode(this.mWrapped, callback, i2);
    }

    public boolean onSearchRequested() {
        return this.mWrapped.onSearchRequested();
    }
}
