package androidx.appcompat.view.menu;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.widget.LinearLayout;
import androidx.appcompat.R$string;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MenuItemImpl implements SupportMenuItem, SeslMenuItem {
    private ActionProvider mActionProvider;
    private View mActionView;
    private String mBadgeText;
    private final int mCategoryOrder;
    private MenuItem.OnMenuItemClickListener mClickListener;
    private CharSequence mContentDescription;
    private int mFlags = 16;
    private final int mGroup;
    private boolean mHasIconTint = false;
    private boolean mHasIconTintMode = false;
    private Drawable mIconDrawable;
    private int mIconResId = 0;
    private ColorStateList mIconTintList = null;
    private PorterDuff.Mode mIconTintMode = null;
    private final int mId;
    private Intent mIntent;
    private boolean mIsActionViewExpanded = false;
    private Runnable mItemCallback;
    MenuBuilder mMenu;
    private ContextMenu.ContextMenuInfo mMenuInfo;
    private boolean mNeedToApplyIconTint = false;
    private MenuItem.OnActionExpandListener mOnActionExpandListener;
    private final int mOrdering;
    private int mSeslNaviMenuItemType;
    private char mShortcutAlphabeticChar;
    private int mShortcutAlphabeticModifiers = 4096;
    private char mShortcutNumericChar;
    private int mShortcutNumericModifiers = 4096;
    private int mShowAsAction;
    private SubMenuBuilder mSubMenu;
    private CharSequence mTitle;
    private CharSequence mTitleCondensed;
    private CharSequence mTooltipText;

    public MenuItemImpl(MenuBuilder menuBuilder, int i2, int i7, int i8, int i10, CharSequence charSequence, int i11) {
        this.mMenu = menuBuilder;
        this.mId = i7;
        this.mGroup = i2;
        this.mCategoryOrder = i8;
        this.mOrdering = i10;
        this.mTitle = charSequence;
        this.mShowAsAction = i11;
    }

    private static void appendModifier(StringBuilder sb2, int i2, int i7, String str) {
        if ((i2 & i7) == i7) {
            sb2.append(str);
        }
    }

    private Drawable applyIconTintIfNecessary(Drawable drawable) {
        if (drawable != null && this.mNeedToApplyIconTint && (this.mHasIconTint || this.mHasIconTintMode)) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (this.mHasIconTint) {
                DrawableCompat.setTintList(drawable, this.mIconTintList);
            }
            if (this.mHasIconTintMode) {
                DrawableCompat.setTintMode(drawable, this.mIconTintMode);
            }
            this.mNeedToApplyIconTint = false;
        }
        return drawable;
    }

    public void actionFormatChanged() {
        this.mMenu.onItemActionRequestChanged(this);
    }

    public boolean collapseActionView() {
        if ((this.mShowAsAction & 8) == 0) {
            return false;
        }
        if (this.mActionView == null) {
            return true;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionCollapse(this)) {
            return this.mMenu.collapseItemActionView(this);
        }
        return false;
    }

    public boolean expandActionView() {
        if (!hasCollapsibleActionView()) {
            return false;
        }
        MenuItem.OnActionExpandListener onActionExpandListener = this.mOnActionExpandListener;
        if (onActionExpandListener == null || onActionExpandListener.onMenuItemActionExpand(this)) {
            return this.mMenu.expandItemActionView(this);
        }
        return false;
    }

    public android.view.ActionProvider getActionProvider() {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.getActionProvider()");
    }

    public View getActionView() {
        View view = this.mActionView;
        if (view != null) {
            return view;
        }
        ActionProvider actionProvider = this.mActionProvider;
        if (actionProvider == null) {
            return null;
        }
        View onCreateActionView = actionProvider.onCreateActionView(this);
        this.mActionView = onCreateActionView;
        return onCreateActionView;
    }

    public int getAlphabeticModifiers() {
        return this.mShortcutAlphabeticModifiers;
    }

    public char getAlphabeticShortcut() {
        return this.mShortcutAlphabeticChar;
    }

    public String getBadgeText() {
        return this.mBadgeText;
    }

    public CharSequence getContentDescription() {
        return this.mContentDescription;
    }

    public int getGroupId() {
        return this.mGroup;
    }

    public Drawable getIcon() {
        Drawable drawable = this.mIconDrawable;
        if (drawable != null) {
            return applyIconTintIfNecessary(drawable);
        }
        if (this.mIconResId == 0) {
            return null;
        }
        Drawable drawable2 = AppCompatResources.getDrawable(this.mMenu.getContext(), this.mIconResId);
        this.mIconResId = 0;
        this.mIconDrawable = drawable2;
        return applyIconTintIfNecessary(drawable2);
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

    @ViewDebug.CapturedViewProperty
    public int getItemId() {
        return this.mId;
    }

    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mMenuInfo;
    }

    public int getNumericModifiers() {
        return this.mShortcutNumericModifiers;
    }

    public char getNumericShortcut() {
        return this.mShortcutNumericChar;
    }

    public int getOrder() {
        return this.mCategoryOrder;
    }

    public int getOrdering() {
        return this.mOrdering;
    }

    public int getSeslNaviMenuItemType() {
        return this.mSeslNaviMenuItemType;
    }

    public char getShortcut() {
        if (this.mMenu.isQwertyMode()) {
            return this.mShortcutAlphabeticChar;
        }
        return this.mShortcutNumericChar;
    }

    public String getShortcutLabel() {
        int i2;
        char shortcut = getShortcut();
        if (shortcut == 0) {
            return "";
        }
        Resources resources = this.mMenu.getContext().getResources();
        StringBuilder sb2 = new StringBuilder();
        if (ViewConfiguration.get(this.mMenu.getContext()).hasPermanentMenuKey()) {
            sb2.append(resources.getString(R$string.abc_prepend_shortcut_label));
        }
        if (this.mMenu.isQwertyMode()) {
            i2 = this.mShortcutAlphabeticModifiers;
        } else {
            i2 = this.mShortcutNumericModifiers;
        }
        appendModifier(sb2, i2, 65536, resources.getString(R$string.abc_menu_meta_shortcut_label));
        appendModifier(sb2, i2, 4096, resources.getString(R$string.abc_menu_ctrl_shortcut_label));
        appendModifier(sb2, i2, 2, resources.getString(R$string.abc_menu_alt_shortcut_label));
        appendModifier(sb2, i2, 1, resources.getString(R$string.abc_menu_shift_shortcut_label));
        appendModifier(sb2, i2, 4, resources.getString(R$string.abc_menu_sym_shortcut_label));
        appendModifier(sb2, i2, 8, resources.getString(R$string.abc_menu_function_shortcut_label));
        if (shortcut == 8) {
            sb2.append(resources.getString(R$string.abc_menu_delete_shortcut_label));
        } else if (shortcut == 10) {
            sb2.append(resources.getString(R$string.abc_menu_enter_shortcut_label));
        } else if (shortcut != ' ') {
            sb2.append(shortcut);
        } else {
            sb2.append(resources.getString(R$string.abc_menu_space_shortcut_label));
        }
        return sb2.toString();
    }

    public SubMenu getSubMenu() {
        return this.mSubMenu;
    }

    public ActionProvider getSupportActionProvider() {
        return this.mActionProvider;
    }

    @ViewDebug.CapturedViewProperty
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

    public CharSequence getTitleForItemView(MenuView.ItemView itemView) {
        if (itemView == null || !itemView.prefersCondensedTitle()) {
            return getTitle();
        }
        return getTitleCondensed();
    }

    public CharSequence getTooltipText() {
        return this.mTooltipText;
    }

    public boolean hasCollapsibleActionView() {
        ActionProvider actionProvider;
        if ((this.mShowAsAction & 8) != 0) {
            if (this.mActionView == null && (actionProvider = this.mActionProvider) != null) {
                this.mActionView = actionProvider.onCreateActionView(this);
            }
            if (this.mActionView != null) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubMenu() {
        if (this.mSubMenu != null) {
            return true;
        }
        return false;
    }

    public boolean invoke() {
        MenuItem.OnMenuItemClickListener onMenuItemClickListener = this.mClickListener;
        if (onMenuItemClickListener != null && onMenuItemClickListener.onMenuItemClick(this)) {
            return true;
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder.dispatchMenuItemSelected(menuBuilder, this)) {
            return true;
        }
        Runnable runnable = this.mItemCallback;
        if (runnable != null) {
            runnable.run();
            return true;
        }
        if (this.mIntent != null) {
            try {
                this.mMenu.getContext().startActivity(this.mIntent);
                return true;
            } catch (ActivityNotFoundException e) {
                Log.e("MenuItemImpl", "Can't find activity to handle intent; ignoring", e);
            }
        }
        ActionProvider actionProvider = this.mActionProvider;
        if (actionProvider == null || !actionProvider.onPerformDefaultAction()) {
            return false;
        }
        return true;
    }

    public boolean isActionButton() {
        if ((this.mFlags & 32) == 32) {
            return true;
        }
        return false;
    }

    public boolean isActionViewExpanded() {
        return this.mIsActionViewExpanded;
    }

    public boolean isCheckable() {
        if ((this.mFlags & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean isChecked() {
        if ((this.mFlags & 2) == 2) {
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

    public boolean isExclusiveCheckable() {
        if ((this.mFlags & 4) != 0) {
            return true;
        }
        return false;
    }

    public boolean isVisible() {
        ActionProvider actionProvider = this.mActionProvider;
        if (actionProvider == null || !actionProvider.overridesItemVisibility()) {
            if ((this.mFlags & 8) == 0) {
                return true;
            }
            return false;
        } else if ((this.mFlags & 8) != 0 || !this.mActionProvider.isVisible()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean requestsActionButton() {
        if ((this.mShowAsAction & 1) == 1) {
            return true;
        }
        return false;
    }

    public boolean requiresActionButton() {
        if ((this.mShowAsAction & 2) == 2) {
            return true;
        }
        return false;
    }

    public boolean requiresOverflow() {
        if (requiresActionButton() || requestsActionButton()) {
            return false;
        }
        return true;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        throw new UnsupportedOperationException("This is not supported, use MenuItemCompat.setActionProvider()");
    }

    public void setActionViewExpanded(boolean z) {
        this.mIsActionViewExpanded = z;
        this.mMenu.onItemsChanged(false);
    }

    public MenuItem setAlphabeticShortcut(char c5) {
        if (this.mShortcutAlphabeticChar == c5) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c5);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setBadgeText(String str) {
        String str2 = this.mBadgeText;
        if (str2 == null || !str2.equals(str)) {
            this.mBadgeText = str;
            this.mMenu.onItemsChanged(false);
        }
    }

    public MenuItem setCheckable(boolean z) {
        int i2 = this.mFlags;
        boolean z3 = z | (i2 & true);
        this.mFlags = z3 ? 1 : 0;
        if (i2 != z3) {
            this.mMenu.onItemsChanged(false);
        }
        return this;
    }

    public MenuItem setChecked(boolean z) {
        if ((this.mFlags & 4) != 0) {
            this.mMenu.setExclusiveItemChecked(this);
            return this;
        }
        setCheckedInt(z);
        return this;
    }

    public void setCheckedInt(boolean z) {
        int i2;
        int i7 = this.mFlags;
        int i8 = i7 & -3;
        if (z) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        int i10 = i2 | i8;
        this.mFlags = i10;
        if (i7 != i10) {
            this.mMenu.onItemsChanged(false);
        }
    }

    public MenuItem setEnabled(boolean z) {
        if (z) {
            this.mFlags |= 16;
        } else {
            this.mFlags &= -17;
        }
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        int i2;
        int i7 = this.mFlags & -5;
        if (z) {
            i2 = 4;
        } else {
            i2 = 0;
        }
        this.mFlags = i2 | i7;
    }

    public MenuItem setIcon(Drawable drawable) {
        this.mIconResId = 0;
        this.mIconDrawable = drawable;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.mIconTintList = colorStateList;
        this.mHasIconTint = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.mIconTintMode = mode;
        this.mHasIconTintMode = true;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIntent(Intent intent) {
        this.mIntent = intent;
        return this;
    }

    public void setIsActionButton(boolean z) {
        if (z) {
            this.mFlags |= 32;
        } else {
            this.mFlags &= -33;
        }
    }

    public void setMenuInfo(ContextMenu.ContextMenuInfo contextMenuInfo) {
        this.mMenuInfo = contextMenuInfo;
    }

    public MenuItem setNumericShortcut(char c5) {
        if (this.mShortcutNumericChar == c5) {
            return this;
        }
        this.mShortcutNumericChar = c5;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.mOnActionExpandListener = onActionExpandListener;
        return this;
    }

    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mClickListener = onMenuItemClickListener;
        return this;
    }

    public MenuItem setShortcut(char c5, char c6) {
        this.mShortcutNumericChar = c5;
        this.mShortcutAlphabeticChar = Character.toLowerCase(c6);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public void setShowAsAction(int i2) {
        int i7 = i2 & 3;
        if (i7 == 0 || i7 == 1 || i7 == 2) {
            this.mShowAsAction = i2;
            this.mMenu.onItemActionRequestChanged(this);
            return;
        }
        throw new IllegalArgumentException("SHOW_AS_ACTION_ALWAYS, SHOW_AS_ACTION_IF_ROOM, and SHOW_AS_ACTION_NEVER are mutually exclusive.");
    }

    public void setSubMenu(SubMenuBuilder subMenuBuilder) {
        this.mSubMenu = subMenuBuilder;
        subMenuBuilder.setHeaderTitle(getTitle());
    }

    public SupportMenuItem setSupportActionProvider(ActionProvider actionProvider) {
        ActionProvider actionProvider2 = this.mActionProvider;
        if (actionProvider2 != null) {
            actionProvider2.reset();
        }
        this.mActionView = null;
        this.mActionProvider = actionProvider;
        this.mMenu.onItemsChanged(true);
        ActionProvider actionProvider3 = this.mActionProvider;
        if (actionProvider3 != null) {
            actionProvider3.setVisibilityListener(new ActionProvider.VisibilityListener() {
                public void onActionProviderVisibilityChanged(boolean z) {
                    MenuItemImpl menuItemImpl = MenuItemImpl.this;
                    menuItemImpl.mMenu.onItemVisibleChanged(menuItemImpl);
                }
            });
        }
        return this;
    }

    public MenuItem setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        this.mMenu.onItemsChanged(false);
        SubMenuBuilder subMenuBuilder = this.mSubMenu;
        if (subMenuBuilder != null) {
            subMenuBuilder.setHeaderTitle(charSequence);
        }
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.mTitleCondensed = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setVisible(boolean z) {
        if (setVisibleInt(z)) {
            this.mMenu.onItemVisibleChanged(this);
        }
        return this;
    }

    public boolean setVisibleInt(boolean z) {
        int i2;
        int i7 = this.mFlags;
        int i8 = i7 & -9;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        int i10 = i2 | i8;
        this.mFlags = i10;
        if (i7 != i10) {
            return true;
        }
        return false;
    }

    public boolean shouldShowIcon() {
        return this.mMenu.getOptionalIconsVisible();
    }

    public boolean shouldShowShortcut() {
        if (!this.mMenu.isShortcutsVisible() || getShortcut() == 0) {
            return false;
        }
        return true;
    }

    public boolean showsTextAsAction() {
        if ((this.mShowAsAction & 4) == 4) {
            return true;
        }
        return false;
    }

    public String toString() {
        CharSequence charSequence = this.mTitle;
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public SupportMenuItem setContentDescription(CharSequence charSequence) {
        this.mContentDescription = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItemImpl setSeslNaviMenuItemType(int i2) {
        this.mSeslNaviMenuItemType = i2;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public SupportMenuItem setShowAsActionFlags(int i2) {
        setShowAsAction(i2);
        return this;
    }

    public SupportMenuItem setTooltipText(CharSequence charSequence) {
        this.mTooltipText = charSequence;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public SupportMenuItem setActionView(View view) {
        int i2;
        this.mActionView = view;
        this.mActionProvider = null;
        if (view != null && view.getId() == -1 && (i2 = this.mId) > 0) {
            view.setId(i2);
        }
        this.mMenu.onItemActionRequestChanged(this);
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c5, int i2) {
        if (this.mShortcutAlphabeticChar == c5 && this.mShortcutAlphabeticModifiers == i2) {
            return this;
        }
        this.mShortcutAlphabeticChar = Character.toLowerCase(c5);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setNumericShortcut(char c5, int i2) {
        if (this.mShortcutNumericChar == c5 && this.mShortcutNumericModifiers == i2) {
            return this;
        }
        this.mShortcutNumericChar = c5;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i2);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setShortcut(char c5, char c6, int i2, int i7) {
        this.mShortcutNumericChar = c5;
        this.mShortcutNumericModifiers = KeyEvent.normalizeMetaState(i2);
        this.mShortcutAlphabeticChar = Character.toLowerCase(c6);
        this.mShortcutAlphabeticModifiers = KeyEvent.normalizeMetaState(i7);
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setIcon(int i2) {
        this.mIconDrawable = null;
        this.mIconResId = i2;
        this.mNeedToApplyIconTint = true;
        this.mMenu.onItemsChanged(false);
        return this;
    }

    public MenuItem setTitle(int i2) {
        return setTitle((CharSequence) this.mMenu.getContext().getString(i2));
    }

    public SupportMenuItem setActionView(int i2) {
        Context context = this.mMenu.getContext();
        setActionView(LayoutInflater.from(context).inflate(i2, new LinearLayout(context), false));
        return this;
    }
}
