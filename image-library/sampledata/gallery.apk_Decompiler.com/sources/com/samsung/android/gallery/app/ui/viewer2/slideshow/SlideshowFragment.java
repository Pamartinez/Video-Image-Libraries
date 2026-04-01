package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegateFragment;
import com.samsung.android.gallery.app.ui.viewer2.delegate.VuDelegateComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.slideshow.delegate.ViewPagerDelegate;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowFragment extends AbsVuDelegateFragment<ISlideshowView, SlideshowPresenter> implements ISlideshowView {
    protected ViewPager2 mViewPager;
    private ViewPagerDelegate mViewPagerDelegate;

    public void enableOsd(boolean z) {
        if (z) {
            ViewUtils.setVisibility(this.mToolbar, 0);
            if (SystemUi.isSystemBarBehaviorDefault(getActivity())) {
                SystemUi.setViewerSystemBar(getActivity());
            }
        } else {
            ViewUtils.setVisibility(this.mToolbar, 8);
        }
        updateNavigationBar();
    }

    public void finish() {
        if (isFirstFragment()) {
            getBlackboard().post("command://request_suicide", (Object) null);
        } else {
            super.finish();
        }
    }

    public ViewerObjectComposite getCurrentContentViewer() {
        ViewPagerDelegate viewPagerDelegate = this.mViewPagerDelegate;
        if (viewPagerDelegate != null) {
            return viewPagerDelegate.getCurrentContentViewer();
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.fragment_slideshow_container_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SLIDESHOW.toString();
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void initView(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);
    }

    public void onBindView(View view) {
        this.mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);
        super.onBindView(view);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mViewPagerDelegate = (ViewPagerDelegate) getDelegate(ViewPagerDelegate.class);
    }

    public Animator onCreateAnimator(int i2, boolean z, int i7) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.0f;
        }
        View view = getView();
        Property property = View.ALPHA;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, property, new float[]{0.3f, f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(getToolbar(), property, new float[]{0.3f, f});
        ofFloat2.setInterpolator(new LinearInterpolator());
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this.mViewPager, property, new float[]{0.3f, f});
        ofFloat3.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(250).playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        animatorSet.start();
        return super.onCreateAnimator(i2, z, i7);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ((ContainerModel) this.mModel).setLocationKey(getLocationKey());
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        StringBuilder t = C0212a.t(str, "mViewerPager : ");
        t.append(this.mViewPager);
        Logger.dumpLog(printWriter, t.toString());
        if (this.mViewPager != null) {
            StringBuilder t3 = C0212a.t(str, "mViewPager.getCurrentItem() : ");
            t3.append(this.mViewPager.getCurrentItem());
            Logger.dumpLog(printWriter, t3.toString());
            Logger.dumpLog(printWriter, str + "mViewPager.getChildCount() : " + this.mViewPager.getChildCount());
        }
        if (this.mPresenter != null) {
            StringBuilder t5 = C0212a.t(str, " dump of mPresenter : ");
            t5.append(this.mPresenter);
            Logger.dumpLog(printWriter, t5.toString());
            ((SlideshowPresenter) this.mPresenter).onDump(printWriter, str + " + ");
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewUtils.setBackgroundResource(getView(), R.color.black_color);
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
        if (iBaseFragment != null && iBaseFragment.supportExitDefaultTransition()) {
            fragmentTransaction.setCustomAnimations(0, 0, 0, R.anim.fade_out_250);
        }
    }

    public void updateNavigationBar() {
        if (isInMultiWindowMode()) {
            return;
        }
        if (((ContainerModel) this.mModel).isOsdVisible()) {
            showNavigationBar();
        } else {
            hideNavigationBar();
        }
    }

    public void updateToolbar() {
        super.updateToolbar();
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setBackgroundColor(galleryToolbar.getContext().getColor(R.color.viewer_tool_bar_background_color));
            galleryToolbar.setOverflowIcon(galleryToolbar.getContext().getDrawable(R.drawable.gallery_ic_ab_more));
            galleryToolbar.setTitle((CharSequence) null);
            galleryToolbar.setSubtitle((CharSequence) null);
        }
    }

    public VuDelegateComposite createDelegateComposite() {
        return new VuDelegateComposite(new SlideshowDelegateFactory(), this);
    }

    public ContainerModel createModel() {
        return new ContainerModel(this.mSystemUi, this);
    }

    public SlideshowPresenter createPresenter(ISlideshowView iSlideshowView) {
        return new SlideshowPresenter(this.mBlackboard, iSlideshowView);
    }

    public void onApplyWindowInsetModel(View view, WindowInsets windowInsets, ContainerModel containerModel) {
        containerModel.setWindowInsets(windowInsets);
    }

    public void onBindModel(ContainerModel containerModel) {
        containerModel.bindView(this.mPresenter, this.mViewPager, (ViewStub) null);
    }

    public void onCreateViewModel(ContainerModel containerModel) {
        containerModel.setLocationKey(getLocationKey());
    }

    public void printLog(String str, String str2) {
    }
}
