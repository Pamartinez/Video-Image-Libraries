package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.ViewStub;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.behavior.DrawerTabBehavior;
import com.samsung.android.gallery.widget.drawer.DrawerRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabView {
    private final View.AccessibilityDelegate mAccessibilityDelegate = new View.AccessibilityDelegate() {
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            if (Build.VERSION.SDK_INT >= 34) {
                accessibilityNodeInfo.setRequestInitialAccessibilityFocus(true);
            }
        }
    };
    private final DrawerTabBehavior.DrawerSlideCallback mCallback;
    private int mCollapsedWidth;
    private View mDrawerArea;
    private View mDrawerLayout;
    private DrawerTabBehavior<View> mDrawerTabBehavior;
    private View mDrawerTabContainer;
    private boolean mIsRtl;
    private final RecyclerView.ItemAnimator mItemAnimator = new DefaultItemAnimator();
    private View mNavigationButton;
    private int mNavigationButtonMarginHorizontal;
    private int mNavigationButtonMarginTop;
    private View mNavigationNewBadge;
    private DrawerRecyclerView mRecyclerView;
    private View mRootLayout;
    private View mSettingsButton;
    private View mSettingsButtonContainer;
    private int mSettingsButtonMarginHorizontal;
    private View mSettingsNewBadge;
    private ViewStub mViewStub;
    private int mWidth;

    public DrawerTabView(DrawerTabBehavior.DrawerSlideCallback drawerSlideCallback) {
        this.mCallback = drawerSlideCallback;
    }

    private void release() {
        DrawerTabBehavior<View> drawerTabBehavior = this.mDrawerTabBehavior;
        if (drawerTabBehavior != null) {
            drawerTabBehavior.removeDrawerSlideCallback();
            this.mDrawerTabBehavior.resetViewInfo();
            this.mDrawerTabBehavior = null;
        }
        View view = this.mDrawerLayout;
        if (view != null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
            this.mDrawerLayout = null;
        }
        View view2 = this.mNavigationButton;
        if (view2 != null) {
            view2.setOnClickListener((View.OnClickListener) null);
            this.mNavigationButton = null;
        }
        View view3 = this.mSettingsButton;
        if (view3 != null) {
            view3.setOnClickListener((View.OnClickListener) null);
            this.mSettingsButton = null;
        }
    }

    private void updateDimenValues() {
        View view = this.mDrawerLayout;
        if (view != null) {
            Resources resources = view.getResources();
            this.mNavigationButtonMarginTop = resources.getDimensionPixelSize(R.dimen.drawer_navigation_button_margin_top);
            this.mNavigationButtonMarginHorizontal = resources.getDimensionPixelSize(R.dimen.drawer_navigation_button_margin_horizontal);
            this.mSettingsButtonMarginHorizontal = resources.getDimensionPixelSize(R.dimen.drawer_settings_button_margin_horizontal);
            this.mCollapsedWidth = resources.getDimensionPixelSize(R.dimen.drawer_collapsed_width);
        }
    }

    private void updateNavigationNewBadgeAlpha(float f) {
        if (ViewUtils.isVisible(this.mNavigationNewBadge)) {
            ViewUtils.setAlpha(this.mNavigationNewBadge, f);
        }
    }

    private void updateViewLayout() {
        ViewMarginUtils.setTopMargin(this.mNavigationButton, this.mNavigationButtonMarginTop);
        ViewMarginUtils.setTopMargin(this.mSettingsButton, this.mNavigationButtonMarginTop);
        ViewMarginUtils.setHorizontalMargin(this.mNavigationButton, this.mNavigationButtonMarginHorizontal);
        ViewMarginUtils.setHorizontalMargin(this.mSettingsButton, this.mSettingsButtonMarginHorizontal);
        View view = this.mDrawerLayout;
        if (view != null) {
            Resources resources = view.getResources();
            ViewMarginUtils.setTopMargin(this.mNavigationNewBadge, resources.getDimensionPixelSize(R.dimen.drawer_navigation_new_badge_margin_top));
            ViewMarginUtils.setEndMargin(this.mNavigationNewBadge, resources.getDimensionPixelSize(R.dimen.drawer_navigation_new_badge_margin_end));
            ViewMarginUtils.setTopMargin(this.mSettingsNewBadge, resources.getDimensionPixelSize(R.dimen.drawer_settings_new_badge_margin_top_v2));
            ViewMarginUtils.setEndMargin(this.mSettingsNewBadge, resources.getDimensionPixelSize(R.dimen.drawer_settings_new_badge_margin_end_v2));
        }
    }

    public void bindView(View view) {
        this.mViewStub = (ViewStub) view.findViewById(R.id.drawer_tab_layout_view_stub);
        this.mIsRtl = view.getResources().getBoolean(R.bool.is_right_to_left);
    }

    public void completeAnimation() {
        DrawerTabItemViewHolder drawerTabItemViewHolder;
        if (this.mRecyclerView != null) {
            for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
                View childAt = this.mRecyclerView.getChildAt(i2);
                if (!(childAt == null || (drawerTabItemViewHolder = (DrawerTabItemViewHolder) this.mRecyclerView.getChildViewHolder(childAt)) == null)) {
                    drawerTabItemViewHolder.itemView.setAlpha(1.0f);
                    drawerTabItemViewHolder.updateIconVisibility(true);
                    drawerTabItemViewHolder.updateForeground();
                    drawerTabItemViewHolder.updateDynamicFocusViewVisibility(false);
                }
            }
        }
    }

    public void expandButtonTouchArea(View view, int i2) {
        ViewUtils.setTouchArea(view, i2, this.mNavigationButtonMarginTop, i2, this.mDrawerLayout.getResources().getDimensionPixelSize(R.dimen.drawer_navigation_button_margin_bottom));
    }

    public void expandNavigationButtonTouchArea() {
        expandButtonTouchArea(this.mNavigationButton, this.mNavigationButtonMarginHorizontal);
    }

    public void expandSettingsButtonTouchArea() {
        expandButtonTouchArea(this.mSettingsButton, this.mSettingsButtonMarginHorizontal);
    }

    public DrawerTabViewAdapter getAdapter() {
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView != null) {
            return (DrawerTabViewAdapter) drawerRecyclerView.getAdapter();
        }
        return null;
    }

    public int getCollapsedWidth() {
        return this.mCollapsedWidth;
    }

    public DrawerTabLayoutManager getLayoutManager() {
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView == null) {
            return null;
        }
        RecyclerView.LayoutManager layoutManager = drawerRecyclerView.getLayoutManager();
        if (layoutManager instanceof DrawerTabLayoutManager) {
            return (DrawerTabLayoutManager) layoutManager;
        }
        return null;
    }

    public DrawerRecyclerView getListView() {
        return this.mRecyclerView;
    }

    public int getWidth() {
        int i2 = this.mWidth;
        if (i2 > 0) {
            return i2;
        }
        View view = this.mDrawerLayout;
        if (view != null) {
            return view.getWidth();
        }
        return 0;
    }

    public void inflateView() {
        ViewStub viewStub;
        View view = this.mDrawerLayout;
        if (view != null) {
            ViewUtils.replaceView(view, this.mViewStub);
            release();
        }
        if (this.mDrawerLayout == null && (viewStub = this.mViewStub) != null) {
            View inflate = viewStub.inflate();
            this.mDrawerLayout = inflate;
            this.mRootLayout = inflate.findViewById(R.id.drawer_root_layout);
            this.mDrawerArea = this.mDrawerLayout.findViewById(R.id.drawer_area);
            this.mNavigationButton = this.mDrawerLayout.findViewById(R.id.drawer_navigation_button);
            this.mSettingsButton = this.mDrawerLayout.findViewById(R.id.drawer_settings_button);
            this.mSettingsNewBadge = this.mDrawerLayout.findViewById(R.id.drawer_settings_new_badge);
            this.mRecyclerView = (DrawerRecyclerView) this.mDrawerLayout.findViewById(R.id.drawer_recyclerView);
            updateDimenValues();
            this.mDrawerTabContainer = this.mDrawerLayout.findViewById(R.id.drawer_tab_container);
            this.mSettingsButtonContainer = this.mDrawerLayout.findViewById(R.id.drawer_settings_button_container);
            this.mNavigationNewBadge = this.mDrawerLayout.findViewById(R.id.drawer_navigation_new_badge);
            this.mRecyclerView.setOverScrollMode(2);
            updateNavigationButtonContentDescription(false);
            ViewUtils.setAccessibilityDelegate(this.mNavigationButton, this.mAccessibilityDelegate);
            updateViewLayout();
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                View view2 = this.mDrawerArea;
                view2.setBackground(view2.getContext().getDrawable(R.drawable.drawer_background_v2));
            }
            DrawerTabBehavior<View> from = DrawerTabBehavior.from(this.mDrawerLayout);
            this.mDrawerTabBehavior = from;
            from.setDrawerSlideCallback(this.mCallback);
            this.mDrawerLayout.bringToFront();
        }
    }

    public boolean isVisible() {
        return ViewUtils.isVisible(this.mDrawerLayout);
    }

    public boolean needInflated() {
        if (this.mDrawerLayout == null) {
            return true;
        }
        return false;
    }

    public void prepareAnimation() {
        DrawerTabItemViewHolder drawerTabItemViewHolder;
        if (this.mRecyclerView != null) {
            for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
                View childAt = this.mRecyclerView.getChildAt(i2);
                if (!(childAt == null || (drawerTabItemViewHolder = (DrawerTabItemViewHolder) this.mRecyclerView.getChildViewHolder(childAt)) == null)) {
                    drawerTabItemViewHolder.removeForeground();
                    drawerTabItemViewHolder.updateDynamicFocusViewVisibility(true);
                }
            }
        }
    }

    public void resetScrollPosition() {
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView != null && drawerRecyclerView.canScrollVertically(-1)) {
            this.mRecyclerView.scrollToPosition(0);
        }
    }

    public void setAdapter(DrawerTabViewAdapter drawerTabViewAdapter) {
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView != null) {
            drawerRecyclerView.setAdapter(drawerTabViewAdapter);
        }
    }

    public void setItemAnimator(boolean z) {
        RecyclerView.ItemAnimator itemAnimator;
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView != null) {
            if (z) {
                itemAnimator = this.mItemAnimator;
            } else {
                itemAnimator = null;
            }
            drawerRecyclerView.setItemAnimator(itemAnimator);
        }
    }

    public void setLayoutManager() {
        DrawerRecyclerView drawerRecyclerView = this.mRecyclerView;
        if (drawerRecyclerView != null) {
            drawerRecyclerView.setLayoutManager(new DrawerTabLayoutManager(drawerRecyclerView.getContext()));
        }
    }

    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        View view = this.mDrawerLayout;
        if (view != null) {
            view.setOnApplyWindowInsetsListener(onApplyWindowInsetsListener);
        }
    }

    public void setOnNavigationButtonClickListener(View.OnClickListener onClickListener) {
        View view = this.mNavigationButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public void setOnSettingsButtonClickListener(View.OnClickListener onClickListener) {
        View view = this.mSettingsButton;
        if (view != null) {
            view.setOnClickListener(onClickListener);
        }
    }

    public void setVisibility(boolean z) {
        int i2;
        View view = this.mDrawerLayout;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public void unbindView() {
        release();
        this.mViewStub = null;
    }

    public void updateBackground(boolean z) {
        float f;
        View view = this.mDrawerArea;
        if (view != null && view.getBackground() != null) {
            Drawable background = this.mDrawerArea.getBackground();
            if (z) {
                f = 0.65f;
            } else {
                f = 1.0f;
            }
            background.setAlpha((int) (f * 255.0f));
        }
    }

    public void updateBehaviorState(boolean z) {
        DrawerTabBehavior<View> drawerTabBehavior = this.mDrawerTabBehavior;
        if (drawerTabBehavior != null) {
            drawerTabBehavior.setState(z);
        }
    }

    public void updateDrawerTabContainerWidth(boolean z) {
        int i2;
        View view = this.mDrawerTabContainer;
        if (z) {
            i2 = -1;
        } else {
            i2 = -2;
        }
        ViewUtils.setWidth(view, i2);
    }

    public void updateIconVisibility(boolean z) {
        DrawerTabItemViewHolder drawerTabItemViewHolder;
        if (this.mRecyclerView != null) {
            for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
                View childAt = this.mRecyclerView.getChildAt(i2);
                if (!(childAt == null || (drawerTabItemViewHolder = (DrawerTabItemViewHolder) this.mRecyclerView.getChildViewHolder(childAt)) == null)) {
                    drawerTabItemViewHolder.updateIconVisibility(z);
                }
            }
        }
    }

    public void updateLayoutWithOffset(float f) {
        float f5;
        DrawerTabItemViewHolder drawerTabItemViewHolder;
        float f8;
        if (this.mDrawerLayout != null && this.mRecyclerView != null) {
            float f10 = 1.0f - f;
            float width = ((float) (getWidth() - this.mCollapsedWidth)) * f10;
            View view = this.mDrawerTabContainer;
            if (this.mIsRtl) {
                f5 = -width;
            } else {
                f5 = width;
            }
            ViewUtils.setTranslationX(view, f5);
            ViewUtils.setAlpha(this.mSettingsButtonContainer, f);
            updateNavigationNewBadgeAlpha(f10);
            if (f != 0.0f) {
                for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
                    View childAt = this.mRecyclerView.getChildAt(i2);
                    if (!(childAt == null || (drawerTabItemViewHolder = (DrawerTabItemViewHolder) this.mRecyclerView.getChildViewHolder(childAt)) == null)) {
                        drawerTabItemViewHolder.itemView.setAlpha(f);
                        if (this.mIsRtl) {
                            f8 = width;
                        } else {
                            f8 = -width;
                        }
                        drawerTabItemViewHolder.translateDynamicFocusView(f8);
                    }
                }
            }
        }
    }

    public void updateLayoutWithState(boolean z) {
        DrawerTabItemViewHolder drawerTabItemViewHolder;
        if (this.mRecyclerView != null) {
            updateDrawerTabContainerWidth(z);
            updateSettingsButtonVisibility(z);
            for (int i2 = 0; i2 < this.mRecyclerView.getChildCount(); i2++) {
                View childAt = this.mRecyclerView.getChildAt(i2);
                if (!(childAt == null || (drawerTabItemViewHolder = (DrawerTabItemViewHolder) this.mRecyclerView.getChildViewHolder(childAt)) == null)) {
                    drawerTabItemViewHolder.updateCollapsedBadgeVisibility(!z);
                }
            }
        }
    }

    public void updateNavigationButtonContentDescription(boolean z) {
        int i2;
        Resources resources = this.mNavigationButton.getResources();
        if (z) {
            i2 = R.string.collapse_navigation_drawer;
        } else {
            i2 = R.string.expand_navigation_drawer;
        }
        String string = resources.getString(i2);
        this.mNavigationButton.setContentDescription(string);
        this.mNavigationButton.setTooltipText(string);
    }

    public void updateNavigationNewBadge(boolean z, boolean z3) {
        float f;
        ViewUtils.setVisibleOrGone(this.mNavigationNewBadge, z);
        if (z3) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        updateNavigationNewBadgeAlpha(f);
    }

    public void updateRootBgColor(int i2) {
        ViewUtils.setBackgroundColor(this.mRootLayout, i2);
    }

    public void updateSettingsButtonVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mSettingsButtonContainer, z);
    }

    public void updateSettingsNewBadge(boolean z) {
        ViewUtils.setVisibleOrGone(this.mSettingsNewBadge, z);
    }

    public void updateWidth(int i2) {
        View view = this.mDrawerLayout;
        if (view != null && this.mWidth != i2) {
            view.getLayoutParams().width = i2;
            this.mWidth = i2;
        }
    }
}
