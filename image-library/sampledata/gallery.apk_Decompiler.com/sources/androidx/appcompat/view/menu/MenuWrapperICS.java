package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.core.internal.view.SupportMenu;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    private final SupportMenu mWrappedObject;

    public MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context);
        if (supportMenu != null) {
            this.mWrappedObject = supportMenu;
            return;
        }
        throw new IllegalArgumentException("Wrapped Object can not be null.");
    }

    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(charSequence));
    }

    public int addIntentOptions(int i2, int i7, int i8, ComponentName componentName, Intent[] intentArr, Intent intent, int i10, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2;
        MenuItem[] menuItemArr3 = menuItemArr;
        if (menuItemArr3 != null) {
            menuItemArr2 = new MenuItem[menuItemArr3.length];
        } else {
            menuItemArr2 = null;
        }
        MenuItem[] menuItemArr4 = menuItemArr2;
        int addIntentOptions = this.mWrappedObject.addIntentOptions(i2, i7, i8, componentName, intentArr, intent, i10, menuItemArr4);
        if (menuItemArr4 != null) {
            int length = menuItemArr4.length;
            for (int i11 = 0; i11 < length; i11++) {
                menuItemArr3[i11] = getMenuItemWrapper(menuItemArr4[i11]);
            }
        }
        return addIntentOptions;
    }

    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(charSequence));
    }

    public void clear() {
        internalClear();
        this.mWrappedObject.clear();
    }

    public void close() {
        this.mWrappedObject.close();
    }

    public MenuItem findItem(int i2) {
        return getMenuItemWrapper(this.mWrappedObject.findItem(i2));
    }

    public MenuItem getItem(int i2) {
        return getMenuItemWrapper(this.mWrappedObject.getItem(i2));
    }

    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    public boolean isShortcutKey(int i2, KeyEvent keyEvent) {
        return this.mWrappedObject.isShortcutKey(i2, keyEvent);
    }

    public boolean performIdentifierAction(int i2, int i7) {
        return this.mWrappedObject.performIdentifierAction(i2, i7);
    }

    public boolean performShortcut(int i2, KeyEvent keyEvent, int i7) {
        return this.mWrappedObject.performShortcut(i2, keyEvent, i7);
    }

    public void removeGroup(int i2) {
        internalRemoveGroup(i2);
        this.mWrappedObject.removeGroup(i2);
    }

    public void removeItem(int i2) {
        internalRemoveItem(i2);
        this.mWrappedObject.removeItem(i2);
    }

    public void setGroupCheckable(int i2, boolean z, boolean z3) {
        this.mWrappedObject.setGroupCheckable(i2, z, z3);
    }

    public void setGroupEnabled(int i2, boolean z) {
        this.mWrappedObject.setGroupEnabled(i2, z);
    }

    public void setGroupVisible(int i2, boolean z) {
        this.mWrappedObject.setGroupVisible(i2, z);
    }

    public void setQwertyMode(boolean z) {
        this.mWrappedObject.setQwertyMode(z);
    }

    public int size() {
        return this.mWrappedObject.size();
    }

    public MenuItem add(int i2) {
        return getMenuItemWrapper(this.mWrappedObject.add(i2));
    }

    public SubMenu addSubMenu(int i2) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i2));
    }

    public MenuItem add(int i2, int i7, int i8, CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(i2, i7, i8, charSequence));
    }

    public SubMenu addSubMenu(int i2, int i7, int i8, CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i2, i7, i8, charSequence));
    }

    public MenuItem add(int i2, int i7, int i8, int i10) {
        return getMenuItemWrapper(this.mWrappedObject.add(i2, i7, i8, i10));
    }

    public SubMenu addSubMenu(int i2, int i7, int i8, int i10) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(i2, i7, i8, i10));
    }
}
