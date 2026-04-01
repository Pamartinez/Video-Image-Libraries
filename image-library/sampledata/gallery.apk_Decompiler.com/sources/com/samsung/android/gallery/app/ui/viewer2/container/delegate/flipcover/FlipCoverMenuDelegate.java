package com.samsung.android.gallery.app.ui.viewer2.container.delegate.flipcover;

import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.menu.DeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.FavoriteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.GroupShotDeleteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.menu.UnFavoriteMenuItem;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import m7.C0488a;
import m7.b;
import m7.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FlipCoverMenuDelegate extends AbsVuDelegate<IVuContainerView> {
    private int mBottomMargin;
    private int mBottomMarginWithoutNavi;
    private final List<FlipCoverMenuHandler> mMenuHandlers = new ArrayList();
    private View mMenuView;
    private final ViewPager2.OnPageChangeCallback mPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        ViewerObjectComposite currentViewer;

        public void onPageScrollStateChanged(int i2) {
            if (i2 == 1) {
                this.currentViewer = ((IVuContainerView) FlipCoverMenuDelegate.this.mView).getCurrentViewer();
                FlipCoverMenuDelegate.this.fadeOut();
            } else if (i2 == 0 && this.currentViewer == ((IVuContainerView) FlipCoverMenuDelegate.this.mView).getCurrentViewer()) {
                FlipCoverMenuDelegate.this.fadeIn();
            }
        }
    };

    public FlipCoverMenuDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    /* access modifiers changed from: private */
    public void fadeIn() {
        updateVisibility();
        if (ViewUtils.isVisible(this.mMenuView)) {
            SimpleAnimator.alphaIn(this.mMenuView, 200);
        }
    }

    /* access modifiers changed from: private */
    public void fadeOut() {
        updateVisibility();
        if (ViewUtils.isVisible(this.mMenuView)) {
            SimpleAnimator.alphaOut(this.mMenuView, 200);
        }
    }

    private float getItemRatio(MediaItem mediaItem) {
        float height;
        int width;
        int orientation = mediaItem.getOrientation();
        if (orientation == 90 || orientation == 270) {
            height = (float) mediaItem.getHeight();
            width = mediaItem.getWidth();
        } else {
            height = (float) mediaItem.getWidth();
            width = mediaItem.getHeight();
        }
        return (float) (((double) Math.round((height / ((float) width)) * 100.0f)) / 100.0d);
    }

    private void initLayout(View view) {
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.flip_cover_menu);
        if (ViewUtils.isViewStub(viewStub)) {
            this.mMenuView = viewStub.inflate();
            initMenu();
            initMargin();
            updateLayout();
        }
    }

    private void initMargin() {
        View view = this.mMenuView;
        if (view != null) {
            this.mBottomMarginWithoutNavi = view.getResources().getDimensionPixelOffset(R.dimen.viewer_flip_cover_decor_layout_margin_bottom_without_navi_bar);
            this.mBottomMargin = this.mMenuView.getResources().getDimensionPixelOffset(R.dimen.viewer_flip_cover_decor_layout_margin_bottom_with_navi_bar);
        }
    }

    private void initMenu() {
        View view;
        EventContext eventContext = ((IVuContainerView) this.mView).getEventContext();
        if (!this.mMenuHandlers.isEmpty() || eventContext == null || (view = this.mMenuView) == null) {
            Log.e((CharSequence) this.TAG, "initMenu already called or something is null", eventContext, this.mMenuView);
            return;
        }
        this.mMenuHandlers.add(new FlipCoverMenuHandler(eventContext, (ViewGroup) view, new FavoriteMenuItem(eventContext, this.mActionInvoker)));
        this.mMenuHandlers.add(new FlipCoverMenuHandler(eventContext, (ViewGroup) this.mMenuView, new UnFavoriteMenuItem(eventContext, this.mActionInvoker)));
        this.mMenuHandlers.add(new FlipCoverMenuHandler(eventContext, (ViewGroup) this.mMenuView, new DeleteMenuItem(eventContext, this.mActionInvoker)));
        this.mMenuHandlers.add(new FlipCoverMenuHandler(eventContext, (ViewGroup) this.mMenuView, new GroupShotDeleteMenuItem(eventContext, this.mActionInvoker)));
        this.mMenuHandlers.forEach(new C0488a(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initMenu$5(FlipCoverMenuHandler flipCoverMenuHandler) {
        ViewUtils.addView((ViewGroup) this.mMenuView, flipCoverMenuHandler.getView());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$3(ViewPager2 viewPager2) {
        viewPager2.registerOnPageChangeCallback(this.mPageChangeCallback);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDestroy$4(ViewPager2 viewPager2) {
        viewPager2.unregisterOnPageChangeCallback(this.mPageChangeCallback);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        this.mMenuHandlers.forEach(new c(2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        updateVisibility();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$2(Object[] objArr) {
        updateVisibility(objArr[0]);
    }

    private void updateLayout() {
        int i2;
        if (this.mMenuView != null) {
            Rect cutouts = ((ContainerModel) this.mModel).getCutouts();
            ViewMarginUtils.setPadding(this.mMenuView, cutouts.left, cutouts.top, cutouts.right, cutouts.bottom);
            View view = this.mMenuView;
            if (cutouts.bottom == 0) {
                i2 = this.mBottomMarginWithoutNavi;
            } else {
                i2 = this.mBottomMargin;
            }
            ViewMarginUtils.setBottomMargin(view, i2);
        }
    }

    private void updateMarginEnd() {
        MediaItem currentMediaItem = ((ContainerModel) this.mModel).getCurrentMediaItem();
        if (currentMediaItem != null && this.mMenuView != null) {
            float itemRatio = getItemRatio(currentMediaItem);
            int dimensionPixelSize = this.mMenuView.getResources().getDimensionPixelSize(R.dimen.viewer_flip_cover_button_default_margin_end);
            if (((IVuContainerView) this.mView).isLandscape()) {
                if (itemRatio == 1.0f) {
                    dimensionPixelSize = this.mMenuView.getResources().getDimensionPixelSize(R.dimen.viewer_flip_cover_button_1_1_ration_margin_end);
                } else if (itemRatio == 0.75f) {
                    dimensionPixelSize = this.mMenuView.getResources().getDimensionPixelSize(R.dimen.viewer_flip_cover_button_3_4_ration_margin_end);
                }
            }
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mMenuView.getLayoutParams();
            marginLayoutParams.setMarginEnd(dimensionPixelSize);
            this.mMenuView.setLayoutParams(marginLayoutParams);
        }
    }

    private void updateVisibility() {
        updateVisibility((Boolean) null);
    }

    public void onApplyWindowInsets() {
        super.onApplyWindowInsets();
        updateLayout();
    }

    public void onBindView(View view) {
        super.onBindView(view);
        Optional.ofNullable(((ContainerModel) this.mModel).getViewPager()).ifPresent(new C0488a(this, 2));
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        super.onConfigurationChanged(configuration, z, z3, z7, z9);
        updateMarginEnd();
    }

    public void onDestroy() {
        super.onDestroy();
        Optional.ofNullable(((ContainerModel) this.mModel).getViewPager()).ifPresent(new C0488a(this, 1));
        this.mMenuHandlers.forEach(new c(0));
    }

    public void onPageInvalidate(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageInvalidate(i2, viewerObjectComposite);
        this.mMenuHandlers.forEach(new c(1));
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        super.onPageSelected(i2, viewerObjectComposite);
        updateMarginEnd();
        fadeIn();
        this.mMenuHandlers.forEach(new c(3));
    }

    public void onViewCreated(View view, Bundle bundle) {
        initLayout(view);
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        super.setActionInvokeListener(actionInvoker);
        actionInvoker.add(ViewerAction.INVALIDATE_TOOLBAR_MENU, new b(this, 0));
        actionInvoker.add(ViewerAction.TOGGLE_OSD, new b(this, 1));
        actionInvoker.add(ViewerAction.SET_DECOR_VISIBILITY, new b(this, 2));
    }

    private void updateVisibility(Boolean bool) {
        if (this.mMenuView != null) {
            ViewUtils.setVisibleOrGone(this.mMenuView, bool != null ? bool.booleanValue() : ((ContainerModel) this.mModel).isOsdVisible() && ((IVuContainerView) this.mView).getCurrentViewer() != null);
        }
    }
}
