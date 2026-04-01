package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$styleable;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.SeslShowButtonShapesHelper;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView, View.OnLongClickListener {
    private final Drawable initBackgroundDrawable;
    private boolean mAllowTextWithIcon;
    private float mDefaultTextSize;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    private boolean mIsChangedRelativePadding;
    private boolean mIsLastItem;
    MenuItemImpl mItemData;
    MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    PopupCallback mPopupCallback;
    private SeslShowButtonShapesHelper mSBSHelper;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ActionMenuItemForwardingListener extends ForwardingListener {
        public ActionMenuItemForwardingListener() {
            super(ActionMenuItemView.this);
        }

        public ShowableListMenu getPopup() {
            PopupCallback popupCallback = ActionMenuItemView.this.mPopupCallback;
            if (popupCallback != null) {
                return popupCallback.getPopup();
            }
            return null;
        }

        public boolean onForwardingStarted() {
            ShowableListMenu popup;
            ActionMenuItemView actionMenuItemView = ActionMenuItemView.this;
            MenuBuilder.ItemInvoker itemInvoker = actionMenuItemView.mItemInvoker;
            if (itemInvoker == null || !itemInvoker.invokeItem(actionMenuItemView.mItemData) || (popup = getPopup()) == null || !popup.isShowing()) {
                return false;
            }
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class PopupCallback {
        public abstract ShowableListMenu getPopup();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private boolean shouldAllowTextWithIcon() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i2 = configuration.screenWidthDp;
        int i7 = configuration.screenHeightDp;
        if (i2 >= 480) {
            return true;
        }
        if ((i2 < 640 || i7 < 480) && configuration.orientation != 2) {
            return false;
        }
        return true;
    }

    private void updateTextButtonVisibility() {
        CharSequence charSequence;
        CharSequence charSequence2;
        CharSequence charSequence3;
        int i2;
        boolean seslIsTextButtonVisible = seslIsTextButtonVisible();
        CharSequence charSequence4 = null;
        if (seslIsTextButtonVisible) {
            charSequence = this.mTitle;
        } else {
            charSequence = null;
        }
        setText(charSequence);
        if (seslIsTextButtonVisible) {
            if (SeslMisc.isLightTheme(getContext())) {
                i2 = R$drawable.sesl_action_bar_item_text_background_light;
            } else {
                i2 = R$drawable.sesl_action_bar_item_text_background_dark;
            }
            setBackgroundResource(i2);
        } else {
            setBackground(this.initBackgroundDrawable);
        }
        CharSequence contentDescription = this.mItemData.getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            if (seslIsTextButtonVisible) {
                charSequence3 = null;
            } else {
                charSequence3 = this.mItemData.getTitle();
            }
            setContentDescription(charSequence3);
        } else {
            setContentDescription(contentDescription);
        }
        CharSequence tooltipText = this.mItemData.getTooltipText();
        if (TextUtils.isEmpty(tooltipText)) {
            if (seslIsTextButtonVisible) {
                charSequence2 = null;
            } else {
                charSequence2 = this.mItemData.getTitle();
            }
            TooltipCompat.setTooltipText(this, charSequence2);
        } else {
            TooltipCompat.setTooltipText(this, tooltipText);
        }
        if (this.mDefaultTextSize > 0.0f) {
            setTextSize(1, this.mDefaultTextSize * Math.min(getResources().getConfiguration().fontScale, 1.2f));
        }
        if (seslIsTextButtonVisible) {
            charSequence4 = this.mTitle;
        }
        setText(charSequence4);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public boolean hasText() {
        return !TextUtils.isEmpty(getText());
    }

    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        int i7;
        this.mItemData = menuItemImpl;
        setIcon(menuItemImpl.getIcon());
        setTitle(menuItemImpl.getTitleForItemView(this));
        setId(menuItemImpl.getItemId());
        if (menuItemImpl.isVisible()) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        setVisibility(i7);
        setEnabled(menuItemImpl.isEnabled());
        if (menuItemImpl.hasSubMenu() && this.mForwardingListener == null) {
            this.mForwardingListener = new ActionMenuItemForwardingListener();
        }
    }

    public boolean needsDividerAfter() {
        return hasText();
    }

    public boolean needsDividerBefore() {
        if (!hasText() || this.mItemData.getIcon() != null) {
            return false;
        }
        return true;
    }

    public void onClick(View view) {
        MenuBuilder.ItemInvoker itemInvoker = this.mItemInvoker;
        if (itemInvoker != null) {
            itemInvoker.invokeItem(this.mItemData);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    public void onHoverChanged(boolean z) {
        TooltipCompat.seslSetNextTooltipForceActionBarPosX(true);
        TooltipCompat.seslSetNextTooltipForceBelow(true);
        super.onHoverChanged(z);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    public boolean onLongClick(View view) {
        return false;
    }

    public void onMeasure(int i2, int i7) {
        int i8;
        int i10;
        boolean hasText = hasText();
        if (hasText && (i10 = this.mSavedPaddingLeft) >= 0) {
            super.setPadding(i10, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i2, i7);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int measuredWidth = getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            i8 = Math.min(size, this.mMinWidth);
        } else {
            i8 = this.mMinWidth;
        }
        if (mode != 1073741824 && this.mMinWidth > 0 && measuredWidth < i8) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), i7);
        }
        if (!hasText && this.mIcon != null) {
            int measuredWidth2 = getMeasuredWidth();
            int width = this.mIcon.getBounds().width();
            if (!this.mIsChangedRelativePadding) {
                super.setPadding((measuredWidth2 - width) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
        }
    }

    public void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence contentDescription = getContentDescription();
        if (!TextUtils.isEmpty(contentDescription)) {
            accessibilityEvent.getText().add(contentDescription);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState((Parcelable) null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ForwardingListener forwardingListener;
        if (!this.mItemData.hasSubMenu() || (forwardingListener = this.mForwardingListener) == null || !forwardingListener.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performLongClick() {
        if (this.mIcon == null) {
            TooltipCompat.setTooltipNull(true);
            return true;
        }
        TooltipCompat.seslSetNextTooltipForceActionBarPosX(true);
        TooltipCompat.seslSetNextTooltipForceBelow(true);
        return super.performLongClick();
    }

    public boolean prefersCondensedTitle() {
        return true;
    }

    public boolean seslIsTextButtonVisible() {
        boolean z = true;
        boolean z3 = !TextUtils.isEmpty(this.mTitle);
        if (this.mIcon != null && (!this.mItemData.showsTextAsAction() || (!this.mAllowTextWithIcon && !this.mExpandedFormat))) {
            z = false;
        }
        return z3 & z;
    }

    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
    }

    public void setExpandedFormat(boolean z) {
        if (this.mExpandedFormat != z) {
            this.mExpandedFormat = z;
            MenuItemImpl menuItemImpl = this.mItemData;
            if (menuItemImpl != null) {
                menuItemImpl.actionFormatChanged();
            }
        }
    }

    public boolean setFrame(int i2, int i7, int i8, int i10) {
        boolean frame = super.setFrame(i2, i7, i8, i10);
        if (this.mIsChangedRelativePadding) {
            Drawable background = getBackground();
            if (this.mIcon != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int paddingLeft = (getPaddingLeft() - getPaddingRight()) / 2;
                DrawableCompat.setHotspotBounds(background, paddingLeft, 0, width + paddingLeft, height);
                return frame;
            } else if (background != null) {
                DrawableCompat.setHotspotBounds(background, 0, 0, getWidth(), getHeight());
            }
        }
        return frame;
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int i2 = this.mMaxIconSize;
            if (intrinsicWidth > i2) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i2) / ((float) intrinsicWidth)));
                intrinsicWidth = i2;
            }
            if (intrinsicHeight > i2) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i2) / ((float) intrinsicHeight)));
            } else {
                i2 = intrinsicHeight;
            }
            drawable.setBounds(0, 0, intrinsicWidth, i2);
        }
        setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        if (!hasText() || ViewCompat.getLayoutDirection(this) != 1) {
            setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        }
        updateTextButtonVisibility();
    }

    public void setIsLastItem(boolean z) {
        this.mIsLastItem = z;
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker itemInvoker) {
        this.mItemInvoker = itemInvoker;
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        this.mSavedPaddingLeft = i2;
        super.setPadding(i2, i7, i8, i10);
    }

    public void setPaddingRelative(int i2, int i7, int i8, int i10) {
        this.mSavedPaddingLeft = i2;
        this.mIsChangedRelativePadding = true;
        super.setPaddingRelative(i2, i7, i8, i10);
    }

    public void setPopupCallback(PopupCallback popupCallback) {
        this.mPopupCallback = popupCallback;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        setContentDescription(charSequence);
        updateTextButtonVisibility();
    }

    public ActionMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mIsChangedRelativePadding = false;
        this.mDefaultTextSize = 0.0f;
        this.mIsLastItem = false;
        Resources resources = context.getResources();
        this.mAllowTextWithIcon = shouldAllowTextWithIcon();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMenuItemView, i2, 0);
        this.mMinWidth = obtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionMenuItemView_android_minWidth, 0);
        obtainStyledAttributes.recycle();
        this.mMaxIconSize = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        setOnLongClickListener(this);
        this.mSavedPaddingLeft = -1;
        setSaveEnabled(false);
        Resources.Theme theme = context.getTheme();
        int[] iArr = R$styleable.AppCompatTheme;
        TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes((AttributeSet) null, iArr, 0, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(R$styleable.AppCompatTheme_actionMenuTextAppearance, 0);
        obtainStyledAttributes2.recycle();
        TypedArray obtainStyledAttributes3 = context.obtainStyledAttributes(resourceId, R$styleable.TextAppearance);
        TypedValue peekValue = obtainStyledAttributes3.peekValue(R$styleable.TextAppearance_android_textSize);
        obtainStyledAttributes3.recycle();
        if (peekValue != null) {
            this.mDefaultTextSize = TypedValue.complexToFloat(peekValue.data);
        }
        seslSetButtonShapeEnabled(true);
        TypedArray obtainStyledAttributes4 = context.getTheme().obtainStyledAttributes((AttributeSet) null, iArr, 0, 0);
        int resourceId2 = obtainStyledAttributes4.getResourceId(R$styleable.AppCompatTheme_actionButtonStyle, 0);
        obtainStyledAttributes4.recycle();
        TypedArray obtainStyledAttributes5 = context.getTheme().obtainStyledAttributes(resourceId2, new int[]{16842964});
        this.initBackgroundDrawable = obtainStyledAttributes5.getDrawable(0);
        obtainStyledAttributes5.recycle();
    }

    public void setCheckable(boolean z) {
    }

    public void setChecked(boolean z) {
    }
}
