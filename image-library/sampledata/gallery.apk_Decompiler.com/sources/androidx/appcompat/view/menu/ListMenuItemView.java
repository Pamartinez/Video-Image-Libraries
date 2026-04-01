package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.SeslDropDownItemTextView;
import androidx.appcompat.widget.TintTypedArray;
import java.text.NumberFormat;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ListMenuItemView extends LinearLayout implements MenuView.ItemView, AbsListView.SelectionBoundsAdjuster {
    private Drawable mBackground;
    private TextView mBadgeView;
    private CheckBox mCheckBox;
    private LinearLayout mContent;
    private SeslDropDownItemTextView mDropDownItemTextView;
    private boolean mForceShowIcon;
    private ImageView mGroupDivider;
    private boolean mHasListDivider;
    private ImageView mIconView;
    private LayoutInflater mInflater;
    private boolean mIsSubMenu;
    private MenuItemImpl mItemData;
    private NumberFormat mNumberFormat;
    private boolean mPreserveIconSpacing;
    private RadioButton mRadioButton;
    private TextView mShortcutView;
    private Drawable mSubMenuArrow;
    private ImageView mSubMenuArrowView;
    private int mTextAppearance;
    private Context mTextAppearanceContext;
    private LinearLayout mTitleParent;
    private TextView mTitleView;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private void addContentView(View view) {
        addContentView(view, -1);
    }

    private LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getContext());
        }
        return this.mInflater;
    }

    private void insertCheckBox() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.sesl_list_menu_item_checkbox, this, false);
        this.mCheckBox = checkBox;
        addContentView(checkBox);
    }

    private void insertIconView() {
        if (!this.mIsSubMenu) {
            ImageView imageView = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, this, false);
            this.mIconView = imageView;
            addContentView(imageView, 0);
        }
    }

    private void insertRadioButton() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.sesl_list_menu_item_radio, this, false);
        this.mRadioButton = radioButton;
        addContentView(radioButton);
    }

    private boolean isNumericValue(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private void seslCheckMaxFontScale(TextView textView, int i2) {
        float f = getResources().getConfiguration().fontScale;
        if (f > 1.2f) {
            textView.setTextSize(0, (((float) i2) / f) * 1.2f);
        }
    }

    private void setBadgeText(String str) {
        if (this.mBadgeView == null) {
            this.mBadgeView = (TextView) findViewById(R$id.menu_badge);
        }
        if (this.mBadgeView == null) {
            Log.i("ListMenuItemView", "SUB_MENU_ITEM_LAYOUT case, mBadgeView is null");
        } else if (this.mTitleParent == null) {
            Log.i("ListMenuItemView", "mTitleParent is null");
        } else {
            Resources resources = getResources();
            float dimension = resources.getDimension(R$dimen.sesl_badge_additional_width);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mBadgeView.getLayoutParams();
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mTitleParent.getLayoutParams();
            if (isNumericValue(str)) {
                String format = this.mNumberFormat.format((long) Math.min(Integer.parseInt(str), 99));
                seslCheckMaxFontScale(this.mBadgeView, resources.getDimensionPixelSize(R$dimen.sesl_menu_item_badge_text_size));
                this.mBadgeView.setText(format);
                int i2 = R$dimen.sesl_badge_default_width;
                layoutParams.width = (int) ((((float) format.length()) * dimension) + resources.getDimension(i2));
                layoutParams.height = (int) (resources.getDimension(i2) + dimension);
                layoutParams.addRule(15, -1);
                this.mBadgeView.setLayoutParams(layoutParams);
            } else {
                layoutParams.topMargin = (int) resources.getDimension(R$dimen.sesl_list_menu_item_dot_badge_top_margin);
                layoutParams2.width = -2;
                this.mTitleParent.setLayoutParams(layoutParams2);
                this.mBadgeView.setLayoutParams(layoutParams);
            }
            int i7 = layoutParams.width;
            int i8 = 0;
            if (str != null) {
                this.mTitleParent.setPaddingRelative(0, 0, getResources().getDimensionPixelSize(R$dimen.sesl_list_menu_item_dot_badge_end_margin) + i7, 0);
            }
            TextView textView = this.mBadgeView;
            if (str == null) {
                i8 = 8;
            }
            textView.setVisibility(i8);
        }
    }

    private void setSubMenuArrowVisible(boolean z) {
        int i2;
        ImageView imageView = this.mSubMenuArrowView;
        if (imageView != null && !this.mIsSubMenu) {
            if (z) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
        }
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.mGroupDivider;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mGroupDivider.getLayoutParams();
            rect.top = this.mGroupDivider.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
        }
    }

    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    public void initialize(MenuItemImpl menuItemImpl, int i2) {
        int i7;
        this.mItemData = menuItemImpl;
        if (menuItemImpl.isVisible()) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        setVisibility(i7);
        setTitle(menuItemImpl.getTitleForItemView(this));
        setCheckable(menuItemImpl.isCheckable());
        setShortcut(menuItemImpl.shouldShowShortcut(), menuItemImpl.getShortcut());
        setIcon(menuItemImpl.getIcon());
        setEnabled(menuItemImpl.isEnabled());
        setSubMenuArrowVisible(menuItemImpl.hasSubMenu());
        setContentDescription(menuItemImpl.getContentDescription());
        setBadgeText(menuItemImpl.getBadgeText());
    }

    public void onFinishInflate() {
        boolean z;
        super.onFinishInflate();
        setBackground(this.mBackground);
        SeslDropDownItemTextView seslDropDownItemTextView = (SeslDropDownItemTextView) findViewById(R$id.sub_menu_title);
        this.mDropDownItemTextView = seslDropDownItemTextView;
        if (seslDropDownItemTextView != null) {
            z = true;
        } else {
            z = false;
        }
        this.mIsSubMenu = z;
        if (!z) {
            TextView textView = (TextView) findViewById(R$id.title);
            this.mTitleView = textView;
            int i2 = this.mTextAppearance;
            if (i2 != -1) {
                textView.setTextAppearance(this.mTextAppearanceContext, i2);
            }
            TextView textView2 = this.mTitleView;
            if (textView2 != null) {
                textView2.setSingleLine(false);
                this.mTitleView.setMaxLines(2);
            }
            this.mShortcutView = (TextView) findViewById(R$id.shortcut);
            ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
            this.mSubMenuArrowView = imageView;
            if (imageView != null) {
                imageView.setImageDrawable(this.mSubMenuArrow);
            }
            this.mGroupDivider = (ImageView) findViewById(R$id.group_divider);
            this.mContent = (LinearLayout) findViewById(R$id.content);
            this.mTitleParent = (LinearLayout) findViewById(R$id.title_parent);
        }
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        TextView textView = this.mBadgeView;
        if (textView != null && textView.getVisibility() == 0 && this.mBadgeView.getWidth() > 0) {
            CharSequence title = this.mItemData.getTitle();
            if (!TextUtils.isEmpty(getContentDescription())) {
                accessibilityNodeInfo.setContentDescription(getContentDescription());
                return;
            }
            accessibilityNodeInfo.setContentDescription(title + " , " + getResources().getString(R$string.sesl_action_menu_overflow_badge_description));
        }
    }

    public void onMeasure(int i2, int i7) {
        if (this.mIconView != null && this.mPreserveIconSpacing && !this.mIsSubMenu) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.mIconView.getLayoutParams();
            int i8 = layoutParams.height;
            if (i8 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i8;
            }
        }
        super.onMeasure(i2, i7);
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void setCheckable(boolean z) {
        View view;
        CompoundButton compoundButton;
        if (z || this.mRadioButton != null || this.mCheckBox != null) {
            if (!this.mIsSubMenu) {
                if (this.mItemData.isExclusiveCheckable()) {
                    if (this.mRadioButton == null) {
                        insertRadioButton();
                    }
                    compoundButton = this.mRadioButton;
                    view = this.mCheckBox;
                } else {
                    if (this.mCheckBox == null) {
                        insertCheckBox();
                    }
                    compoundButton = this.mCheckBox;
                    view = this.mRadioButton;
                }
                if (z) {
                    compoundButton.setChecked(this.mItemData.isChecked());
                    if (compoundButton.getVisibility() != 0) {
                        compoundButton.setVisibility(0);
                    }
                    if (view != null && view.getVisibility() != 8) {
                        view.setVisibility(8);
                        return;
                    }
                    return;
                }
                CheckBox checkBox = this.mCheckBox;
                if (checkBox != null) {
                    checkBox.setVisibility(8);
                }
                RadioButton radioButton = this.mRadioButton;
                if (radioButton != null) {
                    radioButton.setVisibility(8);
                }
            } else if (z) {
                this.mDropDownItemTextView.setChecked(this.mItemData.isChecked());
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.mIsSubMenu) {
            this.mDropDownItemTextView.setChecked(z);
            return;
        }
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                insertRadioButton();
            }
            compoundButton = this.mRadioButton;
        } else {
            if (this.mCheckBox == null) {
                insertCheckBox();
            }
            compoundButton = this.mCheckBox;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        this.mPreserveIconSpacing = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        int i2;
        ImageView imageView = this.mGroupDivider;
        if (imageView != null) {
            if (this.mHasListDivider || !z) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            imageView.setVisibility(i2);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z;
        if (!this.mIsSubMenu) {
            if (this.mItemData.shouldShowIcon() || this.mForceShowIcon) {
                z = true;
            } else {
                z = false;
            }
            if (z || this.mPreserveIconSpacing) {
                ImageView imageView = this.mIconView;
                if (imageView != null || drawable != null || this.mPreserveIconSpacing) {
                    if (imageView == null) {
                        insertIconView();
                    }
                    if (drawable != null || this.mPreserveIconSpacing) {
                        ImageView imageView2 = this.mIconView;
                        if (!z) {
                            drawable = null;
                        }
                        imageView2.setImageDrawable(drawable);
                        if (this.mIconView.getVisibility() != 0) {
                            this.mIconView.setVisibility(0);
                            return;
                        }
                        return;
                    }
                    this.mIconView.setVisibility(8);
                }
            }
        }
    }

    public void setShortcut(boolean z, char c5) {
        int i2;
        if (!this.mIsSubMenu) {
            if (!z || !this.mItemData.shouldShowShortcut()) {
                i2 = 8;
            } else {
                i2 = 0;
            }
            if (i2 == 0) {
                this.mShortcutView.setText(this.mItemData.getShortcutLabel());
            }
            if (this.mShortcutView.getVisibility() != i2) {
                this.mShortcutView.setVisibility(i2);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.mIsSubMenu) {
            if (charSequence != null) {
                this.mDropDownItemTextView.setText(charSequence);
                if (this.mDropDownItemTextView.getVisibility() != 0) {
                    this.mDropDownItemTextView.setVisibility(0);
                }
            } else if (this.mDropDownItemTextView.getVisibility() != 8) {
                this.mDropDownItemTextView.setVisibility(8);
            }
        } else if (charSequence != null) {
            this.mTitleView.setText(charSequence);
            if (this.mTitleView.getVisibility() != 0) {
                this.mTitleView.setVisibility(0);
            }
        } else if (this.mTitleView.getVisibility() != 8) {
            this.mTitleView.setVisibility(8);
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        this.mIsSubMenu = false;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MenuView, i2, 0);
        this.mBackground = obtainStyledAttributes.getDrawable(R$styleable.MenuView_android_itemBackground);
        this.mTextAppearance = obtainStyledAttributes.getResourceId(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = obtainStyledAttributes.getBoolean(R$styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = context;
        this.mSubMenuArrow = obtainStyledAttributes.getDrawable(R$styleable.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes((AttributeSet) null, new int[]{16843049}, R$attr.dropDownListViewStyle, 0);
        this.mHasListDivider = obtainStyledAttributes2.hasValue(0);
        obtainStyledAttributes.recycle();
        obtainStyledAttributes2.recycle();
        this.mNumberFormat = NumberFormat.getInstance(Locale.getDefault());
    }

    private void addContentView(View view, int i2) {
        LinearLayout linearLayout = this.mContent;
        if (linearLayout != null) {
            linearLayout.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }
}
