package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import androidx.appcompat.R$attr;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import com.sec.android.gallery3d.R;
import h2.C0206a;
import h2.C0211f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NavigationMenuItemView extends C0211f implements MenuView.ItemView {
    public static final int[] u = {16842912};

    /* renamed from: j  reason: collision with root package name */
    public int f1474j;
    public boolean k;
    public boolean l;
    public final boolean m = true;
    public final CheckedTextView n;

    /* renamed from: o  reason: collision with root package name */
    public FrameLayout f1475o;

    /* renamed from: p  reason: collision with root package name */
    public MenuItemImpl f1476p;
    public ColorStateList q;
    public boolean r;
    public Drawable s;
    public final C0206a t;

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0206a aVar = new C0206a(1, this);
        this.t = aVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.n = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView, aVar);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.f1475o == null) {
                this.f1475o = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.f1475o.removeAllViews();
            this.f1475o.addView(view);
        }
    }

    public MenuItemImpl getItemData() {
        return this.f1476p;
    }

    public final void initialize(MenuItemImpl menuItemImpl, int i2) {
        int i7;
        StateListDrawable stateListDrawable;
        this.f1476p = menuItemImpl;
        if (menuItemImpl.getItemId() > 0) {
            setId(menuItemImpl.getItemId());
        }
        if (menuItemImpl.isVisible()) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        setVisibility(i7);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(u, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            ViewCompat.setBackground(this, stateListDrawable);
        }
        setCheckable(menuItemImpl.isCheckable());
        setChecked(menuItemImpl.isChecked());
        setEnabled(menuItemImpl.isEnabled());
        setTitle(menuItemImpl.getTitle());
        setIcon(menuItemImpl.getIcon());
        setActionView(menuItemImpl.getActionView());
        setContentDescription(menuItemImpl.getContentDescription());
        TooltipCompat.setTooltipText(this, menuItemImpl.getTooltipText());
        CharSequence title = this.f1476p.getTitle();
        CheckedTextView checkedTextView = this.n;
        if (title == null && this.f1476p.getIcon() == null && this.f1476p.getActionView() != null) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.f1475o;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                layoutParams.width = -1;
                this.f1475o.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.f1475o;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            layoutParams2.width = -2;
            this.f1475o.setLayoutParams(layoutParams2);
        }
    }

    public final int[] onCreateDrawableState(int i2) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i2 + 1);
        MenuItemImpl menuItemImpl = this.f1476p;
        if (menuItemImpl != null && menuItemImpl.isCheckable() && this.f1476p.isChecked()) {
            View.mergeDrawableStates(onCreateDrawableState, u);
        }
        return onCreateDrawableState;
    }

    public final boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.l != z) {
            this.l = z;
            this.t.sendAccessibilityEvent(this.n, 2048);
        }
    }

    public void setChecked(boolean z) {
        int i2;
        refreshDrawableState();
        CheckedTextView checkedTextView = this.n;
        checkedTextView.setChecked(z);
        Typeface typeface = checkedTextView.getTypeface();
        if (!z || !this.m) {
            i2 = 0;
        } else {
            i2 = 1;
        }
        checkedTextView.setTypeface(typeface, i2);
    }

    public void setHorizontalPadding(int i2) {
        setPadding(i2, getPaddingTop(), i2, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.r) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = DrawableCompat.wrap(drawable).mutate();
                DrawableCompat.setTintList(drawable, this.q);
            }
            int i2 = this.f1474j;
            drawable.setBounds(0, 0, i2, i2);
        } else if (this.k) {
            if (this.s == null) {
                Drawable drawable2 = ResourcesCompat.getDrawable(getResources(), R.drawable.navigation_empty_icon, getContext().getTheme());
                this.s = drawable2;
                if (drawable2 != null) {
                    int i7 = this.f1474j;
                    drawable2.setBounds(0, 0, i7, i7);
                }
            }
            drawable = this.s;
        }
        TextViewCompat.setCompoundDrawablesRelative(this.n, drawable, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public void setIconPadding(int i2) {
        this.n.setCompoundDrawablePadding(i2);
    }

    public void setIconSize(int i2) {
        this.f1474j = i2;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        boolean z;
        this.q = colorStateList;
        if (colorStateList != null) {
            z = true;
        } else {
            z = false;
        }
        this.r = z;
        MenuItemImpl menuItemImpl = this.f1476p;
        if (menuItemImpl != null) {
            setIcon(menuItemImpl.getIcon());
        }
    }

    public void setMaxLines(int i2) {
        this.n.setMaxLines(i2);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.k = z;
    }

    public void setTextAppearance(int i2) {
        TextViewCompat.setTextAppearance(this.n, i2);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.n.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.n.setText(charSequence);
    }
}
