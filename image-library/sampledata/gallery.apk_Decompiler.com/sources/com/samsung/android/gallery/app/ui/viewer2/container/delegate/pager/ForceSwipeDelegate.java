package com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager;

import A.a;
import B7.d;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.abstraction.delegate.NotReadyException;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.pagetransformer.DeletePageTransformer;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.Optional;
import o6.B;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ForceSwipeDelegate extends AbsVuDelegate<IVuContainerView> {
    private VuContainerAdapter mAdapter;
    private boolean mIsWorking;
    private boolean mMoveForward;
    private int mPosition;
    private ViewPager2 mViewPager;

    public ForceSwipeDelegate(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    private void abortForceSwipe() {
        finish("abortForceSwipe");
    }

    private void enableViewPagerTouch(boolean z) {
        Log.d(this.TAG, "enableViewPagerTouch", Boolean.valueOf(z));
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.valueOf(z));
    }

    private void finish(String str) {
        Optional.ofNullable((ViewPagerDelegate) getDelegate(ViewPagerDelegate.class)).ifPresent(new B(27));
        enableViewPagerTouch(((ContainerModel) this.mModel).getStateHelper().isEnabledViewPagerTouch());
        ThreadUtil.postOnUiThread(new e(3, this, str));
    }

    private boolean isEnabled() {
        if (this.mViewPager == null || ((IVuContainerView) this.mView).isDestroyed()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$finish$1(String str) {
        this.mIsWorking = false;
        this.mActionInvoker.invoke(ViewerAction.FORCE_SWIPE_FINISHED, new Object[0]);
        ((IVuContainerView) this.mView).printLog(this.TAG, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvokeListener$0(Object[] objArr) {
        prepare();
    }

    private void onForceSwipe() {
        int i2;
        if (isEnabled()) {
            boolean z = !this.mMoveForward;
            int position = ((ContainerModel) this.mModel).getPosition();
            if (this.mMoveForward) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            int i7 = position + i2;
            int count = ((ContainerModel) this.mModel).getMediaData().getCount();
            if (count > 1) {
                if (i7 > count - 1) {
                    i7 = count - 2;
                    z = true;
                }
                boolean z3 = false;
                if (i7 < 0) {
                    i7 = 1;
                    z = false;
                }
                enableViewPagerTouch(false);
                updateDirectorsViewCache();
                int[] iArr = {350, 150, 400};
                try {
                    this.mViewPager.setPageTransformer(new DeletePageTransformer(z, iArr));
                } catch (Exception e) {
                    a.s(e, new StringBuilder("failed to setPageTransformer->"), this.TAG);
                }
                this.mIsWorking = true;
                if (this.mViewPager.getScrollState() == 0) {
                    z3 = true;
                }
                this.mActionInvoker.invoke(ViewerAction.FORCE_SWIPE, Integer.valueOf(i7), Boolean.valueOf(z3), Integer.valueOf(iArr[1] + iArr[2]));
            } else if (((IVuContainerView) this.mView).isFirstFragment()) {
                ((IVuContainerView) this.mView).finish();
            }
        }
    }

    private void updateDirectorsViewCache() {
        DirectorsViewCache.getInstance().deleteFromCache(((ContainerModel) this.mModel).getCurrentMediaItem());
    }

    public boolean isWorking() {
        return this.mIsWorking;
    }

    public void onApplyWindowInsets() {
        abortForceSwipe();
    }

    public void onConfigurationChanged(Configuration configuration, boolean z, boolean z3, boolean z7, boolean z9) {
        abortForceSwipe();
    }

    public void onEnterTransitionEnd() {
        VuContainerAdapter vuContainerAdapter = (VuContainerAdapter) this.mViewPager.getAdapter();
        this.mAdapter = vuContainerAdapter;
        if (vuContainerAdapter == null) {
            throw new NotReadyException("mViewPager is not ready");
        }
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        if (eventMessage.what != 3015) {
            return false;
        }
        onForceSwipe();
        return true;
    }

    public void onPageSelected(int i2, ViewerObjectComposite viewerObjectComposite) {
        boolean z;
        if (this.mIsWorking) {
            finish("ForceSwipe -> onDataChanged");
        } else {
            int i7 = this.mPosition;
            if (i2 != i7) {
                if (i2 > i7) {
                    z = true;
                } else {
                    z = false;
                }
                this.mMoveForward = z;
            }
        }
        this.mPosition = i2;
    }

    public void onViewCreated(View view, Bundle bundle) {
        this.mViewPager = ((ContainerModel) this.mModel).getViewPager();
        this.mPosition = ((ContainerModel) this.mModel).getPosition();
        this.mMoveForward = true;
    }

    public void prepare() {
        VuContainerAdapter vuContainerAdapter = this.mAdapter;
        if (vuContainerAdapter != null) {
            vuContainerAdapter.prepareForceSwipeCache(this.mViewPager.getCurrentItem());
        }
    }

    public void setActionInvokeListener(ActionInvoker actionInvoker) {
        actionInvoker.add(ViewerAction.PREPARE_FORCE_SWIPE, new d(26, this));
    }
}
