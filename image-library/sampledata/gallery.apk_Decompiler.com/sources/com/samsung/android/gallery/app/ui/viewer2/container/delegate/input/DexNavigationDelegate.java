package com.samsung.android.gallery.app.ui.viewer2.container.delegate.input;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import h.C0199b;
import o7.a;
import o7.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DexNavigationDelegate extends AbsVuDelegate<IVuContainerView> {
    ImageView mDexNextButton;
    ImageView mDexPrevButton;
    RelativeLayout mDexView;

    public DexNavigationDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private int getItemCount() {
        RecyclerView.Adapter adapter;
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager != null) {
            adapter = viewPager.getAdapter();
        } else {
            adapter = null;
        }
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    private void inflate(View view) {
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.dex_navigation_button_stub);
        if (this.mDexView == null && viewStub != null) {
            RelativeLayout relativeLayout = (RelativeLayout) viewStub.inflate();
            this.mDexView = relativeLayout;
            this.mDexNextButton = (ImageView) relativeLayout.findViewById(R.id.dex_next_button);
            this.mDexPrevButton = (ImageView) this.mDexView.findViewById(R.id.dex_prev_button);
        }
    }

    private void init() {
        this.mDexNextButton.setOnClickListener(new b(this, 0));
        this.mDexPrevButton.setOnClickListener(new b(this, 1));
    }

    private boolean isFirstItem() {
        if (((ContainerModel) this.mModel).getPosition() < 1) {
            return true;
        }
        return false;
    }

    private boolean isLastItem() {
        if (((ContainerModel) this.mModel).getPosition() >= getItemCount() - 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$2(View view) {
        int i2;
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager == null || viewPager.isUserInputEnabled()) {
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.SCROLL_TO;
            boolean isEnabled = Features.isEnabled(Features.IS_RTL);
            int position = ((ContainerModel) this.mModel).getPosition();
            if (isEnabled) {
                i2 = position - 1;
            } else {
                i2 = position + 1;
            }
            actionInvoker.invoke(viewerAction, Integer.valueOf(i2), Boolean.TRUE);
            return;
        }
        Log.e(this.TAG, "scrollTo disabled");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$3(View view) {
        int i2;
        ViewPager2 viewPager = ((ContainerModel) this.mModel).getViewPager();
        if (viewPager == null || viewPager.isUserInputEnabled()) {
            ActionInvoker actionInvoker = this.mActionInvoker;
            ViewerAction viewerAction = ViewerAction.SCROLL_TO;
            boolean isEnabled = Features.isEnabled(Features.IS_RTL);
            int position = ((ContainerModel) this.mModel).getPosition();
            if (isEnabled) {
                i2 = position + 1;
            } else {
                i2 = position - 1;
            }
            actionInvoker.invoke(viewerAction, Integer.valueOf(i2), Boolean.TRUE);
            return;
        }
        Log.e(this.TAG, "scrollTo disabled");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        updateVisibility();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$1(Object[] objArr) {
        updateVisibility();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$4(Resources resources) {
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.horizontal_navigation_icon_margin);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.horizontal_navigation_icon_extra_margin);
        ViewUtils.setTouchAreaComposite(this.mDexPrevButton, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset2, dimensionPixelOffset2);
        ViewUtils.setTouchAreaComposite(this.mDexNextButton, dimensionPixelOffset2, dimensionPixelOffset2, dimensionPixelOffset, dimensionPixelOffset2);
    }

    /* access modifiers changed from: private */
    public void setAlpha(Object... objArr) {
        ViewUtils.setAlpha(this.mDexView, objArr[0].floatValue());
    }

    private void updateDexNavigationButtons() {
        ImageView imageView;
        ImageView imageView2;
        ViewUtils.setVisibility(this.mDexPrevButton, 0);
        ViewUtils.setVisibility(this.mDexNextButton, 0);
        if (isFirstItem()) {
            if (Features.isEnabled(Features.IS_RTL)) {
                imageView2 = this.mDexNextButton;
            } else {
                imageView2 = this.mDexPrevButton;
            }
            ViewUtils.setVisibility(imageView2, 4);
        }
        if (isLastItem()) {
            if (Features.isEnabled(Features.IS_RTL)) {
                imageView = this.mDexPrevButton;
            } else {
                imageView = this.mDexNextButton;
            }
            ViewUtils.setVisibility(imageView, 4);
        }
    }

    private void updateLayout() {
        Resources resources;
        Context context = ((IVuContainerView) this.mView).getContext();
        if (context != null) {
            resources = context.getResources();
        } else {
            resources = null;
        }
        if (this.mDexView != null && resources != null) {
            int multiWindowToolbarHeight = resources.getDisplayMetrics().heightPixels - WindowUtils.getMultiWindowToolbarHeight(((IVuContainerView) this.mView).getActivity());
            int toolBarHeight = SystemUi.getToolBarHeight(context);
            int i2 = ((ContainerModel) this.mModel).getOverlaySize().bottom;
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mDexView.getLayoutParams();
            layoutParams.topMargin = ((((multiWindowToolbarHeight - toolBarHeight) - i2) / 2) + toolBarHeight) - (ViewUtils.getMeasuredHeight(this.mDexNextButton) / 2);
            this.mDexView.setLayoutParams(layoutParams);
            this.mDexView.postDelayed(new C0199b(18, this, resources), 200);
        }
    }

    private void updateVisibility() {
        boolean z;
        boolean z3;
        ViewerObjectComposite currentViewer = ((IVuContainerView) this.mView).getCurrentViewer();
        int containerWidgetVisibility = ((ContainerModel) this.mModel).getContainerWidgetVisibility();
        Context context = ((IVuContainerView) this.mView).getContext();
        boolean z7 = true;
        if (context != null) {
            z = context.getResources().getBoolean(R.bool.supportDexNavigationButton);
        } else {
            z = true;
        }
        if (currentViewer != null) {
            z3 = OverlayViewState.isShow((OverlayViewState.StateListener) currentViewer.getModel());
        } else {
            z3 = false;
        }
        RelativeLayout relativeLayout = this.mDexView;
        if (containerWidgetVisibility != 0 || !z || z3) {
            z7 = false;
        }
        ViewUtils.setVisibleOrInvisible(relativeLayout, z7);
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        updateLayout();
        updateVisibility();
    }

    public void onEnterTransitionEnd() {
        updateVisibility();
        updateLayout();
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        updateDexNavigationButtons();
    }

    public void onViewCreated(View view, Bundle bundle) {
        inflate(view);
        init();
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        this.mActionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_ALPHA, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.UPDATE_CONTAINER_DECOR_VISIBILITY, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new a(this, 2));
    }
}
