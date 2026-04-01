package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ToolbarActionBar extends ActionBar {
    final DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    final AppCompatDelegateImpl.ActionBarMenuCallback mMenuCallback;
    private boolean mMenuCallbackSet;
    private final Toolbar.OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator = new Runnable() {
        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    };
    private ArrayList<Object> mMenuVisibilityListeners = new ArrayList<>();
    boolean mToolbarMenuPrepared;
    final Window.Callback mWindowCallback;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        private boolean mClosingActionMenu;

        public ActionMenuPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (!this.mClosingActionMenu) {
                this.mClosingActionMenu = true;
                ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder);
                this.mClosingActionMenu = false;
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public final class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ToolbarActionBar.this.mDecorToolbar.isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder);
            } else if (ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, (View) null, menuBuilder)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ToolbarMenuCallback implements AppCompatDelegateImpl.ActionBarMenuCallback {
        public ToolbarMenuCallback() {
        }

        public View onCreatePanelView(int i2) {
            if (i2 == 0) {
                return new View(ToolbarActionBar.this.mDecorToolbar.getContext());
            }
            return null;
        }

        public boolean onPreparePanel(int i2) {
            if (i2 != 0) {
                return false;
            }
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            if (toolbarActionBar.mToolbarMenuPrepared) {
                return false;
            }
            toolbarActionBar.mDecorToolbar.setMenuPrepared();
            ToolbarActionBar.this.mToolbarMenuPrepared = true;
            return false;
        }
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        AnonymousClass2 r0 = new Toolbar.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, menuItem);
            }
        };
        this.mMenuClicker = r0;
        Preconditions.checkNotNull(toolbar);
        ToolbarWidgetWrapper toolbarWidgetWrapper = new ToolbarWidgetWrapper(toolbar, false);
        this.mDecorToolbar = toolbarWidgetWrapper;
        this.mWindowCallback = (Window.Callback) Preconditions.checkNotNull(callback);
        toolbarWidgetWrapper.setWindowCallback(callback);
        toolbar.setOnMenuItemClickListener(r0);
        toolbarWidgetWrapper.setWindowTitle(charSequence);
        this.mMenuCallback = new ToolbarMenuCallback();
    }

    private Menu getMenu() {
        if (!this.mMenuCallbackSet) {
            this.mDecorToolbar.setMenuCallbacks(new ActionMenuPresenterCallback(), new MenuBuilderCallback());
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }

    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    public boolean collapseActionView() {
        if (!this.mDecorToolbar.hasExpandedActionView()) {
            return false;
        }
        this.mDecorToolbar.collapseActionView();
        return true;
    }

    public void dispatchMenuVisibilityChanged(boolean z) {
        if (z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            if (this.mMenuVisibilityListeners.size() > 0) {
                this.mMenuVisibilityListeners.get(0).getClass();
                throw new ClassCastException();
            }
        }
    }

    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    public boolean onKeyShortcut(int i2, KeyEvent keyEvent) {
        int i7;
        Menu menu = getMenu();
        if (menu == null) {
            return false;
        }
        if (keyEvent != null) {
            i7 = keyEvent.getDeviceId();
        } else {
            i7 = -1;
        }
        boolean z = true;
        if (KeyCharacterMap.load(i7).getKeyboardType() == 1) {
            z = false;
        }
        menu.setQwertyMode(z);
        return menu.performShortcut(i2, keyEvent, 0);
    }

    public boolean onMenuKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            openOptionsMenu();
        }
        return true;
    }

    public boolean openOptionsMenu() {
        if (this.mDecorToolbar.isOverflowMenuShowPending()) {
            return true;
        }
        return this.mDecorToolbar.showOverflowMenu();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populateOptionsMenu() {
        /*
            r5 = this;
            android.view.Menu r0 = r5.getMenu()
            boolean r1 = r0 instanceof androidx.appcompat.view.menu.MenuBuilder
            r2 = 0
            if (r1 == 0) goto L_0x000d
            r1 = r0
            androidx.appcompat.view.menu.MenuBuilder r1 = (androidx.appcompat.view.menu.MenuBuilder) r1
            goto L_0x000e
        L_0x000d:
            r1 = r2
        L_0x000e:
            if (r1 == 0) goto L_0x0013
            r1.stopDispatchingItemsChanged()
        L_0x0013:
            r0.clear()     // Catch:{ all -> 0x0028 }
            android.view.Window$Callback r3 = r5.mWindowCallback     // Catch:{ all -> 0x0028 }
            r4 = 0
            boolean r3 = r3.onCreatePanelMenu(r4, r0)     // Catch:{ all -> 0x0028 }
            if (r3 == 0) goto L_0x002a
            android.view.Window$Callback r5 = r5.mWindowCallback     // Catch:{ all -> 0x0028 }
            boolean r5 = r5.onPreparePanel(r4, r2, r0)     // Catch:{ all -> 0x0028 }
            if (r5 != 0) goto L_0x002d
            goto L_0x002a
        L_0x0028:
            r5 = move-exception
            goto L_0x0033
        L_0x002a:
            r0.clear()     // Catch:{ all -> 0x0028 }
        L_0x002d:
            if (r1 == 0) goto L_0x0032
            r1.startDispatchingItemsChanged()
        L_0x0032:
            return
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            r1.startDispatchingItemsChanged()
        L_0x0038:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.app.ToolbarActionBar.populateOptionsMenu():void");
    }

    public void setDisplayHomeAsUpEnabled(boolean z) {
        int i2;
        if (z) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        setDisplayOptions(i2, 4);
    }

    public void setDisplayOptions(int i2, int i7) {
        this.mDecorToolbar.setDisplayOptions((i2 & i7) | ((~i7) & this.mDecorToolbar.getDisplayOptions()));
    }

    public void setDisplayShowHomeEnabled(boolean z) {
        int i2;
        if (z) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        setDisplayOptions(i2, 2);
    }

    public void setTitle(CharSequence charSequence) {
        this.mDecorToolbar.setTitle(charSequence);
    }

    public void setWindowTitle(CharSequence charSequence) {
        this.mDecorToolbar.setWindowTitle(charSequence);
    }

    public void setTitle(int i2) {
        DecorToolbar decorToolbar = this.mDecorToolbar;
        decorToolbar.setTitle(i2 != 0 ? decorToolbar.getContext().getText(i2) : null);
    }

    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    public void setShowHideAnimationEnabled(boolean z) {
    }
}
