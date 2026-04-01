package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$bool;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import androidx.appcompat.util.SeslMisc;
import androidx.appcompat.util.SeslShowButtonShapesHelper;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider;
import androidx.core.widget.TextViewCompat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ActionMenuPresenter extends BaseMenuPresenter implements ActionProvider.SubUiVisibilityListener {
    private final SparseBooleanArray mActionButtonGroups = new SparseBooleanArray();
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    /* access modifiers changed from: private */
    public boolean mIsLightTheme = false;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    /* access modifiers changed from: private */
    public NumberFormat mNumberFormat = NumberFormat.getInstance(Locale.getDefault());
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback = new PopupPresenterCallback();
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private boolean mStrictWidthLimit;
    /* access modifiers changed from: private */
    public CharSequence mTooltipText;
    /* access modifiers changed from: private */
    public boolean mUseTextItemMode;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ActionButtonSubmenu extends MenuPopupHelper {
        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public ActionButtonSubmenu(android.content.Context r8, androidx.appcompat.view.menu.SubMenuBuilder r9, android.view.View r10) {
            /*
                r6 = this;
                androidx.appcompat.widget.ActionMenuPresenter.this = r7
                r4 = 0
                int r5 = androidx.appcompat.R$attr.actionOverflowMenuStyle
                r0 = r6
                r1 = r8
                r2 = r9
                r3 = r10
                r0.<init>(r1, r2, r3, r4, r5)
                android.view.MenuItem r6 = r2.getItem()
                androidx.appcompat.view.menu.MenuItemImpl r6 = (androidx.appcompat.view.menu.MenuItemImpl) r6
                boolean r6 = r6.isActionButton()
                if (r6 != 0) goto L_0x0025
                androidx.appcompat.widget.ActionMenuPresenter$OverflowMenuButton r6 = r7.mOverflowButton
                if (r6 != 0) goto L_0x0022
                androidx.appcompat.view.menu.MenuView r6 = r7.mMenuView
                android.view.View r6 = (android.view.View) r6
            L_0x0022:
                r0.setAnchorView(r6)
            L_0x0025:
                androidx.appcompat.widget.ActionMenuPresenter$PopupPresenterCallback r6 = r7.mPopupPresenterCallback
                r0.setPresenterCallback(r6)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.ActionMenuPresenter.ActionButtonSubmenu.<init>(androidx.appcompat.widget.ActionMenuPresenter, android.content.Context, androidx.appcompat.view.menu.SubMenuBuilder, android.view.View):void");
        }

        public void onDismiss() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.mActionButtonPopup = null;
            actionMenuPresenter.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ActionMenuPopupCallback extends ActionMenuItemView.PopupCallback {
        public ActionMenuPopupCallback() {
        }

        public ShowableListMenu getPopup() {
            ActionButtonSubmenu actionButtonSubmenu = ActionMenuPresenter.this.mActionButtonPopup;
            if (actionButtonSubmenu != null) {
                return actionButtonSubmenu.getPopup();
            }
            return null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup overflowPopup) {
            this.mPopup = overflowPopup;
        }

        public void run() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View view = (View) ActionMenuPresenter.this.mMenuView;
            int dimensionPixelSize = ActionMenuPresenter.this.mContext.getResources().getDimensionPixelSize(R$dimen.sesl_action_menu_view_padding_end);
            if (!(view == null || view.getWindowToken() == null || !this.mPopup.tryShow(dimensionPixelSize, 0))) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OverflowImageView extends AppCompatImageView {
        private Configuration mConfiguration;
        private SeslShowButtonShapesHelper mSBSHelper;

        public OverflowImageView(Context context) {
            super(context, (AttributeSet) null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setLongClickable(true);
            CharSequence unused = ActionMenuPresenter.this.mTooltipText = getResources().getString(R$string.sesl_action_menu_overflow_description);
            TooltipCompat.setTooltipText(this, ActionMenuPresenter.this.mTooltipText);
            this.mConfiguration = ActionMenuPresenter.this.mContext.getResources().getConfiguration();
        }

        public void onConfigurationChanged(Configuration configuration) {
            int i2;
            super.onConfigurationChanged(configuration);
            Configuration configuration2 = this.mConfiguration;
            if (configuration2 != null) {
                i2 = configuration2.diff(configuration);
            } else {
                i2 = 4096;
            }
            this.mConfiguration = configuration;
            Context context = getContext();
            int[] iArr = R$styleable.View;
            int i7 = R$attr.actionOverflowButtonStyle;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, iArr, i7, 0);
            setMinimumHeight(obtainStyledAttributes.getDimensionPixelSize(R$styleable.View_android_minHeight, 0));
            obtainStyledAttributes.recycle();
            CharSequence unused = ActionMenuPresenter.this.mTooltipText = context.getResources().getString(R$string.sesl_action_menu_overflow_description);
            if ((i2 & 4096) != 0) {
                TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes((AttributeSet) null, R$styleable.AppCompatImageView, i7, 0);
                Drawable drawable = ContextCompat.getDrawable(context, obtainStyledAttributes2.getResourceId(R$styleable.AppCompatImageView_android_src, -1));
                if (drawable != null) {
                    setImageDrawable(drawable);
                }
                obtainStyledAttributes2.recycle();
            }
        }

        public void onMeasure(int i2, int i7) {
            super.onMeasure(i2, i7);
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            if (ActionMenuPresenter.this.showOverflowMenu() && isHovered()) {
                TooltipCompat.setTooltipNull(true);
            }
            return true;
        }

        public boolean performLongClick() {
            TooltipCompat.seslSetNextTooltipForceActionBarPosX(true);
            TooltipCompat.seslSetNextTooltipForceBelow(true);
            return super.performLongClick();
        }

        public boolean setFrame(int i2, int i7, int i8, int i10) {
            boolean frame = super.setFrame(i2, i7, i8, i10);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (!(drawable == null || background == null)) {
                int width = getWidth();
                int height = getHeight();
                int paddingLeft = (getPaddingLeft() - getPaddingRight()) / 2;
                DrawableCompat.setHotspotBounds(background, paddingLeft, 0, width + paddingLeft, height);
            }
            return frame;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OverflowMenuButton extends FrameLayout implements ActionMenuView.ActionMenuChildView {
        private ViewGroup mBadgeBackground;
        private CharSequence mBadgeContentDescription;
        private TextView mBadgeText;
        private CharSequence mContentDescription;
        private View mInnerView;

        public OverflowMenuButton(Context context) {
            super(context);
            View view;
            if (ActionMenuPresenter.this.mUseTextItemMode) {
                view = new OverflowTextView(context);
            } else {
                view = new OverflowImageView(context);
            }
            this.mInnerView = view;
            addView(view, new FrameLayout.LayoutParams(-2, -2));
            Resources resources = getResources();
            View view2 = this.mInnerView;
            if (view2 instanceof OverflowImageView) {
                this.mContentDescription = view2.getContentDescription();
                this.mBadgeContentDescription = this.mContentDescription + " , " + resources.getString(R$string.sesl_preferecne_badge_description);
            }
            if (TextUtils.isEmpty(this.mContentDescription)) {
                String string = resources.getString(R$string.sesl_action_menu_overflow_description);
                this.mContentDescription = string;
                View view3 = this.mInnerView;
                if (view3 != null) {
                    view3.setContentDescription(string);
                }
            }
            ViewGroup viewGroup = (ViewGroup) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.sesl_action_menu_item_badge, this, false);
            this.mBadgeBackground = viewGroup;
            this.mBadgeText = (TextView) viewGroup.getChildAt(0);
            addView(this.mBadgeBackground);
        }

        public View getInnerView() {
            return this.mInnerView;
        }

        public boolean needsDividerAfter() {
            return false;
        }

        public boolean needsDividerBefore() {
            return false;
        }

        public void onConfigurationChanged(Configuration configuration) {
            float f;
            super.onConfigurationChanged(configuration);
            Resources resources = getResources();
            this.mBadgeText.setTextSize(0, (float) ((int) resources.getDimension(R$dimen.sesl_menu_item_badge_text_size)));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mBadgeBackground.getLayoutParams();
            CharSequence text = this.mBadgeText.getText();
            if (text == null || text.toString() == null) {
                int i2 = R$dimen.sesl_badge_default_width;
                float dimension = resources.getDimension(i2);
                if (text != null) {
                    f = resources.getDimension(R$dimen.sesl_badge_additional_width) * ((float) text.length());
                } else {
                    f = 0.0f;
                }
                marginLayoutParams.width = (int) (dimension + f);
                marginLayoutParams.height = (int) (resources.getDimension(R$dimen.sesl_badge_additional_width) + resources.getDimension(i2));
                marginLayoutParams.topMargin = (int) getResources().getDimension(R$dimen.sesl_menu_item_number_badge_top_margin);
                marginLayoutParams.setMarginEnd((int) resources.getDimension(R$dimen.sesl_menu_item_number_badge_end_margin));
            } else {
                int i7 = R$dimen.sesl_menu_item_badge_size;
                marginLayoutParams.width = (int) resources.getDimension(i7);
                marginLayoutParams.height = (int) resources.getDimension(i7);
            }
            this.mBadgeBackground.setLayoutParams(marginLayoutParams);
            if (this.mInnerView instanceof OverflowImageView) {
                this.mContentDescription = getContentDescription();
                this.mBadgeContentDescription = this.mContentDescription + " , " + resources.getString(R$string.sesl_preferecne_badge_description);
            }
            if (TextUtils.isEmpty(this.mContentDescription)) {
                this.mContentDescription = resources.getString(R$string.sesl_action_menu_overflow_description);
                this.mBadgeContentDescription = this.mContentDescription + " , " + resources.getString(R$string.sesl_preferecne_badge_description);
            }
            if (this.mBadgeBackground.getVisibility() == 0) {
                View view = this.mInnerView;
                if (view instanceof OverflowImageView) {
                    view.setContentDescription(this.mBadgeContentDescription);
                    return;
                }
                return;
            }
            View view2 = this.mInnerView;
            if (view2 instanceof OverflowImageView) {
                view2.setContentDescription(this.mContentDescription);
            }
        }

        public void setBadgeText(String str, int i2) {
            int i7;
            String str2;
            int i8;
            int i10;
            if (i2 > 99) {
                i2 = 99;
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mBadgeBackground.getLayoutParams();
            if (str != null) {
                Resources resources = getResources();
                int i11 = R$dimen.sesl_menu_item_badge_size;
                i7 = (int) resources.getDimension(i11);
                i8 = (int) getResources().getDimension(i11);
                str2 = "";
            } else {
                str2 = ActionMenuPresenter.this.mNumberFormat.format((long) i2);
                Resources resources2 = getResources();
                int i12 = R$dimen.sesl_badge_default_width;
                float dimension = resources2.getDimension(i12);
                Resources resources3 = getResources();
                int i13 = R$dimen.sesl_badge_additional_width;
                i7 = (int) ((resources3.getDimension(i13) * ((float) str2.length())) + dimension);
                i8 = (int) (getResources().getDimension(i13) + getResources().getDimension(i12));
                marginLayoutParams.topMargin = (int) getResources().getDimension(R$dimen.sesl_menu_item_number_badge_top_margin);
                marginLayoutParams.setMarginEnd((int) getResources().getDimension(R$dimen.sesl_menu_item_number_badge_end_margin));
            }
            this.mBadgeText.setText(str2);
            marginLayoutParams.width = i7;
            marginLayoutParams.height = i8;
            this.mBadgeBackground.setLayoutParams(marginLayoutParams);
            ViewGroup viewGroup = this.mBadgeBackground;
            if (i2 > 0) {
                i10 = 0;
            } else {
                i10 = 8;
            }
            viewGroup.setVisibility(i10);
            if (this.mBadgeBackground.getVisibility() == 0) {
                View view = this.mInnerView;
                if (view instanceof OverflowImageView) {
                    view.setContentDescription(this.mBadgeContentDescription);
                    return;
                }
                return;
            }
            View view2 = this.mInnerView;
            if (view2 instanceof OverflowImageView) {
                view2.setContentDescription(this.mContentDescription);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OverflowPopup extends MenuPopupHelper {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OverflowPopup(Context context, MenuBuilder menuBuilder, View view, boolean z) {
            super(context, menuBuilder, view, z, R$attr.actionOverflowMenuStyle);
            setGravity(8388613);
            setPresenterCallback(ActionMenuPresenter.this.mPopupPresenterCallback);
        }

        public void onDismiss() {
            if (ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class OverflowTextView extends AppCompatTextView {
        private SeslShowButtonShapesHelper mSBBHelper;

        public OverflowTextView(Context context) {
            super(context, (AttributeSet) null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes((AttributeSet) null, R$styleable.AppCompatTheme, 0, 0);
            TextViewCompat.setTextAppearance(this, obtainStyledAttributes.getResourceId(R$styleable.AppCompatTheme_actionMenuTextAppearance, 0));
            obtainStyledAttributes.recycle();
            setText(getResources().getString(R$string.sesl_more_item_label));
            boolean unused = ActionMenuPresenter.this.mIsLightTheme = SeslMisc.isLightTheme(context);
            if (ActionMenuPresenter.this.mIsLightTheme) {
                setBackgroundResource(R$drawable.sesl_action_bar_item_text_background_light);
            } else {
                setBackgroundResource(R$drawable.sesl_action_bar_item_text_background_dark);
            }
            seslSetButtonShapeEnabled(true);
        }

        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
        }

        public void onMeasure(int i2, int i7) {
            super.onMeasure(i2, i7);
        }

        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PopupPresenterCallback implements MenuPresenter.Callback {
        public PopupPresenterCallback() {
        }

        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder instanceof SubMenuBuilder) {
                menuBuilder.getRootMenu().close(false);
            }
            MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                callback.onCloseMenu(menuBuilder, z);
            }
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            if (menuBuilder == ActionMenuPresenter.this.mMenu) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder) menuBuilder).getItem().getItemId();
            MenuPresenter.Callback callback = ActionMenuPresenter.this.getCallback();
            if (callback != null) {
                return callback.onOpenSubMenu(menuBuilder);
            }
            return false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };
        public int openSubMenuId;

        public SavedState() {
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeInt(this.openSubMenuId);
        }

        public SavedState(Parcel parcel) {
            this.openSubMenuId = parcel.readInt();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.sesl_action_menu_layout, R$layout.sesl_action_menu_item_layout);
        this.mUseTextItemMode = context.getResources().getBoolean(R$bool.sesl_action_bar_text_item_mode);
    }

    private View findViewForItem(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof MenuView.ItemView) && ((MenuView.ItemView) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    public void bindItemView(MenuItemImpl menuItemImpl, MenuView.ItemView itemView) {
        itemView.initialize(menuItemImpl, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) itemView;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback();
        }
        actionMenuItemView.setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return hideSubMenus() | hideOverflowMenu();
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i2) {
        if (viewGroup.getChildAt(i2) == this.mOverflowButton) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i2);
    }

    public boolean flagActionItems() {
        int i2;
        ArrayList<MenuItemImpl> arrayList;
        int i7;
        int i8;
        int i10;
        boolean z;
        boolean z3;
        boolean z7;
        ActionMenuPresenter actionMenuPresenter = this;
        MenuBuilder menuBuilder = actionMenuPresenter.mMenu;
        View view = null;
        boolean z9 = false;
        if (menuBuilder != null) {
            arrayList = menuBuilder.getVisibleItems();
            i2 = arrayList.size();
        } else {
            arrayList = null;
            i2 = 0;
        }
        int i11 = actionMenuPresenter.mMaxItems;
        int i12 = actionMenuPresenter.mActionItemWidthLimit;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        MenuView menuView = actionMenuPresenter.mMenuView;
        if (menuView == null) {
            Log.d("ActionMenuPresenter", "mMenuView is null, maybe Menu has not been initialized.");
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) menuView;
        boolean z10 = false;
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < i2; i15++) {
            MenuItemImpl menuItemImpl = arrayList.get(i15);
            if (menuItemImpl.requiresActionButton()) {
                i13++;
            } else if (menuItemImpl.requestsActionButton()) {
                i14++;
            } else {
                z10 = true;
            }
            if (actionMenuPresenter.mExpandedActionViewsExclusive && menuItemImpl.isActionViewExpanded()) {
                i11 = 0;
            }
        }
        if (actionMenuPresenter.mReserveOverflow && (z10 || i14 + i13 > i11)) {
            i11--;
        }
        int i16 = i11 - i13;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.mActionButtonGroups;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.mStrictWidthLimit) {
            int i17 = actionMenuPresenter.mMinCellSize;
            i8 = i12 / i17;
            i7 = ((i12 % i17) / i8) + i17;
        } else {
            i8 = 0;
            i7 = 0;
        }
        int i18 = 0;
        int i19 = 0;
        while (i18 < i2) {
            MenuItemImpl menuItemImpl2 = arrayList.get(i18);
            if (menuItemImpl2.requiresActionButton()) {
                View itemView = actionMenuPresenter.getItemView(menuItemImpl2, view, viewGroup);
                if (actionMenuPresenter.mStrictWidthLimit) {
                    i8 -= ActionMenuView.measureChildForCells(itemView, i7, i8, makeMeasureSpec, z9 ? 1 : 0);
                } else {
                    itemView.measure(makeMeasureSpec, makeMeasureSpec);
                }
                int measuredWidth = itemView.getMeasuredWidth();
                i12 -= measuredWidth;
                if (i19 == 0) {
                    i19 = measuredWidth;
                }
                int groupId = menuItemImpl2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                menuItemImpl2.setIsActionButton(true);
                z = z9;
                i10 = i2;
            } else if (menuItemImpl2.requestsActionButton()) {
                int groupId2 = menuItemImpl2.getGroupId();
                boolean z11 = sparseBooleanArray.get(groupId2);
                if ((i16 > 0 || z11) && i12 > 0 && (!actionMenuPresenter.mStrictWidthLimit || i8 > 0)) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                boolean z12 = z3;
                i10 = i2;
                if (z3) {
                    View itemView2 = actionMenuPresenter.getItemView(menuItemImpl2, (View) null, viewGroup);
                    if (actionMenuPresenter.mStrictWidthLimit) {
                        int measureChildForCells = ActionMenuView.measureChildForCells(itemView2, i7, i8, makeMeasureSpec, 0);
                        i8 -= measureChildForCells;
                        if (measureChildForCells == 0) {
                            z12 = false;
                        }
                    } else {
                        itemView2.measure(makeMeasureSpec, makeMeasureSpec);
                    }
                    boolean z13 = z12;
                    int measuredWidth2 = itemView2.getMeasuredWidth();
                    i12 -= measuredWidth2;
                    if (i19 == 0) {
                        i19 = measuredWidth2;
                    }
                    if (i12 >= 0) {
                        z7 = true;
                    } else {
                        z7 = false;
                    }
                    z3 = z13 & z7;
                }
                if (z3 && groupId2 != 0) {
                    sparseBooleanArray.put(groupId2, true);
                } else if (z11) {
                    sparseBooleanArray.put(groupId2, false);
                    int i20 = 0;
                    while (i20 < i18) {
                        MenuItemImpl menuItemImpl3 = arrayList.get(i20);
                        if (menuItemImpl3.getGroupId() == groupId2) {
                            if (menuItemImpl3.isActionButton()) {
                                i16++;
                            }
                            menuItemImpl3.setIsActionButton(false);
                        }
                        i20++;
                    }
                }
                if (z3) {
                    i16--;
                }
                menuItemImpl2.setIsActionButton(z3);
                z = false;
            } else {
                z = z9;
                i10 = i2;
                menuItemImpl2.setIsActionButton(z);
            }
            i18++;
            z9 = z;
            i2 = i10;
            view = null;
            actionMenuPresenter = this;
        }
        return true;
    }

    public View getItemView(MenuItemImpl menuItemImpl, View view, ViewGroup viewGroup) {
        int i2;
        View actionView = menuItemImpl.getActionView();
        if (actionView == null || menuItemImpl.hasCollapsibleActionView()) {
            actionView = super.getItemView(menuItemImpl, view, viewGroup);
        }
        if (menuItemImpl.isActionViewExpanded()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        actionView.setVisibility(i2);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    public MenuView getMenuView(ViewGroup viewGroup) {
        MenuView menuView = this.mMenuView;
        MenuView menuView2 = super.getMenuView(viewGroup);
        if (menuView != menuView2) {
            ((ActionMenuView) menuView2).setPresenter(this);
        }
        return menuView2;
    }

    public Drawable getOverflowIcon() {
        if (this.mUseTextItemMode) {
            return null;
        }
        OverflowMenuButton overflowMenuButton = this.mOverflowButton;
        if (overflowMenuButton != null) {
            return ((AppCompatImageView) overflowMenuButton.getInnerView()).getDrawable();
        }
        if (this.mPendingOverflowIconSet) {
            return this.mPendingOverflowIcon;
        }
        return null;
    }

    public boolean hideOverflowMenu() {
        MenuView menuView;
        OpenOverflowRunnable openOverflowRunnable = this.mPostedOpenRunnable;
        if (openOverflowRunnable == null || (menuView = this.mMenuView) == null) {
            OverflowPopup overflowPopup = this.mOverflowPopup;
            if (overflowPopup == null) {
                return false;
            }
            overflowPopup.dismiss();
            return true;
        }
        ((View) menuView).removeCallbacks(openOverflowRunnable);
        this.mPostedOpenRunnable = null;
        return true;
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu actionButtonSubmenu = this.mActionButtonPopup;
        if (actionButtonSubmenu == null) {
            return false;
        }
        actionButtonSubmenu.dismiss();
        return true;
    }

    public void initForMenu(Context context, MenuBuilder menuBuilder) {
        super.initForMenu(context, menuBuilder);
        Resources resources = context.getResources();
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(context);
        if (!this.mReserveOverflowSet) {
            this.mReserveOverflow = actionBarPolicy.showsOverflowMenuButton();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit();
        }
        if (!this.mMaxItemsSet) {
            this.mMaxItems = actionBarPolicy.getMaxActionButtons();
        }
        int i2 = this.mWidthLimit;
        if (this.mReserveOverflow) {
            if (this.mOverflowButton == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                overflowMenuButton.setId(R$id.sesl_action_bar_overflow_button);
                if (this.mPendingOverflowIconSet) {
                    if (this.mUseTextItemMode) {
                        ((AppCompatImageView) this.mOverflowButton.getInnerView()).setImageDrawable(this.mPendingOverflowIcon);
                    }
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.mOverflowButton.measure(makeMeasureSpec, makeMeasureSpec);
            }
            i2 -= this.mOverflowButton.getMeasuredWidth();
        } else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = i2;
        this.mMinCellSize = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    public boolean isOverflowMenuShowPending() {
        if (this.mPostedOpenRunnable != null || isOverflowMenuShowing()) {
            return true;
        }
        return false;
    }

    public boolean isOverflowMenuShowing() {
        OverflowPopup overflowPopup = this.mOverflowPopup;
        if (overflowPopup == null || !overflowPopup.isShowing()) {
            return false;
        }
        return true;
    }

    public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        dismissPopupMenus();
        super.onCloseMenu(menuBuilder, z);
    }

    public void onConfigurationChanged(Configuration configuration) {
        OverflowMenuButton overflowMenuButton;
        ActionBarPolicy actionBarPolicy = ActionBarPolicy.get(this.mContext);
        if (!this.mMaxItemsSet) {
            this.mMaxItems = actionBarPolicy.getMaxActionButtons();
        }
        if (!this.mWidthLimitSet) {
            this.mWidthLimit = actionBarPolicy.getEmbeddedMenuWidthLimit();
        }
        if (!this.mReserveOverflow || (overflowMenuButton = this.mOverflowButton) == null) {
            this.mActionItemWidthLimit = this.mWidthLimit;
        } else {
            this.mActionItemWidthLimit = this.mWidthLimit - overflowMenuButton.getMeasuredWidth();
        }
        MenuBuilder menuBuilder = this.mMenu;
        if (menuBuilder != null) {
            menuBuilder.onItemsChanged(true);
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        int i2;
        MenuBuilder menuBuilder;
        MenuItem findItem;
        if ((parcelable instanceof SavedState) && (i2 = ((SavedState) parcelable).openSubMenuId) > 0 && (menuBuilder = this.mMenu) != null && (findItem = menuBuilder.findItem(i2)) != null) {
            onSubMenuSelected((SubMenuBuilder) findItem.getSubMenu());
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.openSubMenuId = this.mOpenSubMenuId;
        return savedState;
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder) {
        boolean z = false;
        if (subMenuBuilder == null || !subMenuBuilder.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder2 = subMenuBuilder;
        while (subMenuBuilder2.getParentMenu() != this.mMenu) {
            subMenuBuilder2 = (SubMenuBuilder) subMenuBuilder2.getParentMenu();
        }
        View findViewForItem = findViewForItem(subMenuBuilder2.getItem());
        if (findViewForItem == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder.getItem().getItemId();
        int size = subMenuBuilder.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            MenuItem item = subMenuBuilder.getItem(i2);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
            i2++;
        }
        ActionButtonSubmenu actionButtonSubmenu = new ActionButtonSubmenu(this.mContext, subMenuBuilder, findViewForItem);
        this.mActionButtonPopup = actionButtonSubmenu;
        actionButtonSubmenu.setForceShowIcon(z);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder);
        return true;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    public void setMenuView(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable drawable) {
        if (!this.mUseTextItemMode) {
            OverflowMenuButton overflowMenuButton = this.mOverflowButton;
            if (overflowMenuButton != null) {
                ((AppCompatImageView) overflowMenuButton.getInnerView()).setImageDrawable(drawable);
                return;
            }
            this.mPendingOverflowIconSet = true;
            this.mPendingOverflowIcon = drawable;
        }
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public boolean shouldIncludeItem(int i2, MenuItemImpl menuItemImpl) {
        return menuItemImpl.isActionButton();
    }

    public boolean showOverflowMenu() {
        MenuBuilder menuBuilder;
        if (!this.mReserveOverflow || isOverflowMenuShowing() || (menuBuilder = this.mMenu) == null || this.mMenuView == null || this.mPostedOpenRunnable != null || menuBuilder.getNonActionItems().isEmpty()) {
            return false;
        }
        OpenOverflowRunnable openOverflowRunnable = new OpenOverflowRunnable(new OverflowPopup(this.mContext, this.mMenu, this.mOverflowButton, true));
        this.mPostedOpenRunnable = openOverflowRunnable;
        ((View) this.mMenuView).post(openOverflowRunnable);
        return true;
    }

    public void updateMenuView(boolean z) {
        ArrayList<MenuItemImpl> arrayList;
        MenuView menuView;
        MenuView menuView2;
        super.updateMenuView(z);
        MenuView menuView3 = this.mMenuView;
        if (menuView3 != null) {
            ((View) menuView3).requestLayout();
        }
        MenuBuilder menuBuilder = this.mMenu;
        boolean z3 = false;
        if (menuBuilder != null) {
            ArrayList<MenuItemImpl> actionItems = menuBuilder.getActionItems();
            int size = actionItems.size();
            for (int i2 = 0; i2 < size; i2++) {
                ActionProvider supportActionProvider = actionItems.get(i2).getSupportActionProvider();
                if (supportActionProvider != null) {
                    supportActionProvider.setSubUiVisibilityListener(this);
                }
            }
        }
        MenuBuilder menuBuilder2 = this.mMenu;
        if (menuBuilder2 != null) {
            arrayList = menuBuilder2.getNonActionItems();
        } else {
            arrayList = null;
        }
        if (this.mReserveOverflow && arrayList != null) {
            int size2 = arrayList.size();
            if (size2 == 1) {
                z3 = !arrayList.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z3 = true;
            }
        }
        if (z3) {
            if (this.mOverflowButton == null) {
                OverflowMenuButton overflowMenuButton = new OverflowMenuButton(this.mSystemContext);
                this.mOverflowButton = overflowMenuButton;
                overflowMenuButton.setId(R$id.sesl_action_bar_overflow_button);
            }
            ViewGroup viewGroup = (ViewGroup) this.mOverflowButton.getParent();
            if (viewGroup != this.mMenuView) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.mMenuView;
                if (actionMenuView != null) {
                    actionMenuView.addView((View) this.mOverflowButton, (ViewGroup.LayoutParams) actionMenuView.generateOverflowButtonLayoutParams());
                }
            }
        } else {
            OverflowMenuButton overflowMenuButton2 = this.mOverflowButton;
            if (overflowMenuButton2 != null && overflowMenuButton2.getParent() == (menuView2 = this.mMenuView)) {
                if (menuView2 != null) {
                    ((ViewGroup) menuView2).removeView(this.mOverflowButton);
                }
                if (isOverflowMenuShowing()) {
                    hideOverflowMenu();
                }
            }
        }
        if (!(this.mOverflowButton == null || (menuView = this.mMenuView) == null)) {
            ActionMenuView actionMenuView2 = (ActionMenuView) menuView;
            this.mOverflowButton.setBadgeText(actionMenuView2.getOverflowBadgeText(), actionMenuView2.getSumOfDigitsInBadges());
        }
        OverflowMenuButton overflowMenuButton3 = this.mOverflowButton;
        if ((overflowMenuButton3 == null || overflowMenuButton3.getVisibility() != 0) && isOverflowMenuShowing()) {
            hideOverflowMenu();
        }
        MenuView menuView4 = this.mMenuView;
        if (menuView4 != null) {
            ((ActionMenuView) menuView4).setOverflowReserved(this.mReserveOverflow);
        }
    }
}
