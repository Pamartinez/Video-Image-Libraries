package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionMenuItem implements SupportMenuItem {
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private Context mContext;
    private int mFlags = 16;
    private final int mGroup;
    private boolean mHasIconTint = false;
    private boolean mHasIconTintMode = false;
    private Drawable mIconDrawable;
    private ColorStateList mIconTintList = null;
    private PorterDuff.Mode mIconTintMode = null;
    private final int mId;
    private Intent mIntent;
    private final int mOrdering;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers = 4096;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers = 4096;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public ActionMenuItem(Context context, int i2, int i7, int i8, int i10, CharSequence charSequence) {
        this.mContext = context;
        this.mId = i7;
        this.mGroup = i2;
        this.mOrdering = i10;
        this.mTitle = charSequence;
    }

    private void applyIconTint() {
        Drawable drawable = this.mIconDrawable;
        if (drawable == null) {
            return;
        }
        if (this.mHasIconTint || this.mHasIconTintMode) {
            Drawable wrap = DrawableCompat.wrap(drawable);
            this.mIconDrawable = wrap;
            Drawable mutate = wrap.mutate();
            this.mIconDrawable = mutate;
            if (this.mHasIconTint) {
                DrawableCompat.setTintList(mutate, this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                DrawableCompat.setTintMode(this.mIconDrawable, this.mIconTintMode);
            }
        }
    }

    public boolean collapseActionView() {
        return false;
    }

    public boolean expandActionView() {
        return false;
    }

    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    public View getActionView() {
        return null;
    }

    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        return this.mIconDrawable;
    }

    public ColorStateList getIconTintList() {
        return this.mIconTintList;
    }

    public PorterDuff.Mode getIconTintMode() {
        return this.mIconTintMode;
    }

    public Intent getIntent() {
        return this.mIntent;
    }

    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mOrdering;
    }

    public SubMenu getSubMenu() {
        return null;
    }

    public androidx.core.view.ActionProvider getSupportActionProvider() {
        return null;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.mTitleCondensed;
        if (charSequence != null) {
            return charSequence;
        }
        return this.mTitle;
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasSubMenu() {
        return false;
    }

    public boolean isActionViewExpanded() {
        return false;
    }

    public boolean isCheckable() {
        if ((this.mFlags & 1) != 0) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.mFlags & 2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        if ((this.mFlags & 16) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        if ((this.mFlags & 8) == 0) {
            return true;
        }
        return false;
    }

    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setAlphabeticShortcut(char c5) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c5);
        return this;
    }

    public MenuItem setCheckable(boolean z) {
        this.mFlags = z | (this.mFlags & true) ? 1 : 0;
        return this;
    }

    public MenuItem setChecked(boolean z) {
        int i2;
        int i7 = this.mFlags & -3;
        if (z) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        this.mFlags = i2 | i7;
        return this;
    }

    public MenuItem setEnabled(boolean z) {
        int i2;
        int i7 = this.mFlags & -17;
        if (z) {
            i2 = 16;
        } else {
            i2 = 0;
        }
        this.mFlags = i2 | i7;
        return this;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.mIconDrawable = drawable;
        applyIconTint();
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        this.mHasIconTint = true;
        applyIconTint();
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mIconTintMode = mode;
        this.mHasIconTintMode = true;
        applyIconTint();
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public MenuItem setNumericShortcut(char c5) {
        this.mShortcutNumericChar = c5;
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c5, char c6) {
        this.mShortcutNumericChar = c5;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c6);
        return this;
    }

    public SupportMenuItem setSupportActionProvider(androidx.core.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        return this;
    }

    public MenuItem setVisible(boolean z) {
        int i2 = 8;
        int i7 = this.mFlags & 8;
        if (z) {
            i2 = 0;
        }
        this.mFlags = i7 | i2;
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c5, int i2) {
        this.mShortcutAlphabeticChar = Character.toLowerCase(c5);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        return this;
    }

    public MenuItem setNumericShortcut(char c5, int i2) {
        this.mShortcutNumericChar = c5;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public MenuItem setTitle(int i2) {
        this.mTitle = this.mContext.getResources().getString(i2);
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        return this;
    }

    public SupportMenuItem setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    public MenuItem setIcon(int i2) {
        this.mIconDrawable = ContextCompat.getDrawable(this.mContext, i2);
        applyIconTint();
        return this;
    }

    public MenuItem setShortcut(char c5, char c6, int i2, int i7) {
        this.mShortcutNumericChar = c5;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i2);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c6);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i7);
        return this;
    }

    public SupportMenuItem setActionView(int i2) {
        throw new UnsupportedOperationException();
    }

    public void setShowAsAction(int i2) {
    }
}
