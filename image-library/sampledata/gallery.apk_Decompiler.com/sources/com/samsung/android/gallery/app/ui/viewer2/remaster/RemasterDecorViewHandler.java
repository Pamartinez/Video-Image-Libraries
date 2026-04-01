package com.samsung.android.gallery.app.ui.viewer2.remaster;

import S7.b;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterDecorViewHandler extends ViewerObject {
    /* access modifiers changed from: private */
    public View mHeaderView;
    private View mSeparatorView;
    private CoordinatorLayout mViewerLayout;

    private void hideHeaderText() {
        startHeaderTextAnim(R.anim.remaster_header_text_out, new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisibility(RemasterDecorViewHandler.this.mHeaderView, 4);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        hideHeaderText();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        showHeaderText();
    }

    /* access modifiers changed from: private */
    public void onReplacedHandlerLayout(Object... objArr) {
        this.mHeaderView = this.mViewerLayout.findViewById(R.id.remaster_header_view);
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
    }

    private void setVisibility(int i2) {
        ViewUtils.setVisibility(this.mHeaderView, i2);
        ViewUtils.setVisibility(this.mSeparatorView, i2);
    }

    private void showHeaderText() {
        startHeaderTextAnim(R.anim.remaster_header_text_in, new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ViewUtils.setVisibility(RemasterDecorViewHandler.this.mHeaderView, 0);
            }
        });
    }

    private void startHeaderTextAnim(int i2, Animation.AnimationListener animationListener) {
        View view = this.mHeaderView;
        if (view != null) {
            Animation loadAnimation = AnimationUtils.loadAnimation(view.getContext(), i2);
            loadAnimation.setDuration(150);
            loadAnimation.setAnimationListener(animationListener);
            this.mHeaderView.startAnimation(loadAnimation);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.REMASTER_HANDLER_TOUCH_DOWN, new b(this, 1));
        this.mActionInvoker.add(ViewerAction.REMASTER_HANDLER_TOUCH_UP, new b(this, 2));
        this.mActionInvoker.add(ViewerAction.REPLACED_HANDLER_LAYOUT, new b(this, 3));
    }

    public void onInitialized() {
        this.mHeaderView = this.mViewerLayout.findViewById(R.id.remaster_header_view);
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        setVisibility(8);
    }
}
