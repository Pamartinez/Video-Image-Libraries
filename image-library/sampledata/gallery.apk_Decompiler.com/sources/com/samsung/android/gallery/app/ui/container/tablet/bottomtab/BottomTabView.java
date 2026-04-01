package com.samsung.android.gallery.app.ui.container.tablet.bottomtab;

import A2.g;
import android.view.View;
import android.view.ViewStub;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.abstraction.SimpleTabSelectedListener;
import com.samsung.android.gallery.widget.bottom.BottomTabLayout;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BottomTabView {
    private View mBottomTabContainer;
    private View mBottomTabFloatingContainer;
    private BottomTabLayout mBottomTabLayout;
    private ViewStub mViewStub;

    private void release() {
        View view = this.mBottomTabContainer;
        if (view != null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) null);
            this.mBottomTabContainer = null;
        }
    }

    public void bindView(View view) {
        this.mViewStub = (ViewStub) view.findViewById(R.id.bottom_tab_layout_view_stub);
    }

    public void blockFocus(boolean z) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.blockFocus(z);
        }
    }

    public BottomTabLayout getBottomTabLayout() {
        return this.mBottomTabLayout;
    }

    public void hideTabLayout() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.applyAnimation(false);
        }
    }

    public void inflateView() {
        ViewStub viewStub;
        View view = this.mBottomTabContainer;
        if (view != null) {
            ViewUtils.replaceView(view, this.mViewStub);
            release();
        }
        if (this.mBottomTabContainer == null && (viewStub = this.mViewStub) != null) {
            View inflate = viewStub.inflate();
            this.mBottomTabContainer = inflate;
            this.mBottomTabLayout = (BottomTabLayout) inflate.findViewById(R.id.tab_layout);
            this.mBottomTabFloatingContainer = this.mBottomTabContainer.findViewById(R.id.tab_layout_floating_container);
        }
    }

    public void invalidate() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.invalidate();
        }
    }

    public boolean isViewActive() {
        return !ViewUtils.isGone(this.mBottomTabContainer);
    }

    public boolean needInflated() {
        if (this.mBottomTabContainer == null) {
            return true;
        }
        return false;
    }

    public void removeTabSelectListener(SimpleTabSelectedListener simpleTabSelectedListener) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.removeOnTabSelectedListener((g) simpleTabSelectedListener);
        }
    }

    public void requestApplyInsets() {
        View view = this.mBottomTabContainer;
        if (view != null) {
            view.requestApplyInsets();
        }
    }

    public void scrollToMenuTab() {
        int i2;
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            if (Features.isEnabled(Features.IS_RTL)) {
                i2 = 17;
            } else {
                i2 = 66;
            }
            bottomTabLayout.fullScroll(i2);
        }
    }

    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener onApplyWindowInsetsListener) {
        View view = this.mBottomTabContainer;
        if (view != null) {
            view.setOnApplyWindowInsetsListener(onApplyWindowInsetsListener);
        }
    }

    public void setTabSelectListener(SimpleTabSelectedListener simpleTabSelectedListener) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.addOnTabSelectedListener((g) simpleTabSelectedListener);
        }
    }

    public void setTabSelected(int i2, boolean z) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.setTabSelected(i2, z);
        }
    }

    public void setVisibility(int i2) {
        ViewUtils.setVisibility(this.mBottomTabContainer, i2);
        ViewUtils.setVisibility(this.mBottomTabFloatingContainer, i2);
    }

    public void showTabLayout() {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.applyAnimation(true);
        }
    }

    public void unbindView() {
        release();
        this.mViewStub = null;
    }

    public void updateBadgeOnTab(int i2, boolean z) {
        BottomTabLayout bottomTabLayout = this.mBottomTabLayout;
        if (bottomTabLayout != null) {
            bottomTabLayout.updateBadge(i2, z);
        }
    }

    public void updateTabContainerLayout() {
        View view = this.mBottomTabContainer;
        if (view != null) {
            view.getLayoutParams().height = this.mBottomTabContainer.getResources().getDimensionPixelSize(R.dimen.bottom_tab_floating_height);
            this.mBottomTabContainer.requestLayout();
        }
    }
}
