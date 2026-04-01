package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.flipcover;

import B7.c;
import android.graphics.Rect;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverDecorLayoutHandler extends ViewerObject implements FragmentLifeCycle {
    private int mBottomMargin;
    private int mBottomMarginWithoutNavi;
    private int mEndMargin;
    private View mFlipCoverDecorLayout;

    private void initMargin() {
        this.mBottomMarginWithoutNavi = this.mFlipCoverDecorLayout.getResources().getDimensionPixelOffset(R.dimen.viewer_flip_cover_decor_layout_margin_bottom_without_navi_bar);
        this.mBottomMargin = this.mFlipCoverDecorLayout.getResources().getDimensionPixelOffset(R.dimen.viewer_flip_cover_decor_layout_margin_bottom_with_navi_bar);
        this.mEndMargin = this.mFlipCoverDecorLayout.getResources().getDimensionPixelOffset(R.dimen.viewer_flip_cover_decor_layout_portrait_margin_end);
    }

    /* access modifiers changed from: private */
    public void initView(Object... objArr) {
        this.mFlipCoverDecorLayout = objArr[0];
        initMargin();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        updateVisibility(objArr[0].booleanValue());
    }

    private void updateLayout() {
        int i2;
        int i7;
        if (this.mFlipCoverDecorLayout != null) {
            Rect cutouts = this.mModel.getContainerModel().getCutouts();
            ViewMarginUtils.setPadding(this.mFlipCoverDecorLayout, cutouts.left, cutouts.top, cutouts.right, cutouts.bottom);
            View view = this.mFlipCoverDecorLayout;
            if (cutouts.bottom == 0) {
                i2 = this.mBottomMarginWithoutNavi;
            } else {
                i2 = this.mBottomMargin;
            }
            ViewMarginUtils.setBottomMargin(view, i2);
            View view2 = this.mFlipCoverDecorLayout;
            if (ResourceCompat.isLandscape(this.mModel.getContext())) {
                i7 = 0;
            } else {
                i7 = this.mEndMargin;
            }
            ViewMarginUtils.setRightMargin(view2, i7);
        }
    }

    private void updateVisibility(boolean z) {
        ViewUtils.setVisibleOrGone(this.mFlipCoverDecorLayout, z);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.FLIP_COVER_DECOR_LAYOUT, new c(this, 0));
        this.mActionInvoker.add(ViewerAction.UPDATE_VIEWER_DECOR_VISIBILITY, new c(this, 1));
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.FLIP_COVER_DECOR_LAYOUT_INFLATE, new Object[0]);
    }

    public void onApplyWindowInsets() {
        updateLayout();
    }

    public void onViewAttached() {
        super.onViewAttached();
        updateVisibility(this.mModel.getContainerModel().isOsdVisible());
        updateLayout();
    }
}
