package com.samsung.android.gallery.app.ui.list.search.suggestion;

import A5.a;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteFragment;
import com.samsung.android.gallery.app.ui.list.search.autoComplete.SearchAutoCompleteFragmentV2;
import com.samsung.android.gallery.app.ui.list.search.recommendation.RecommendationFragment;
import com.samsung.android.gallery.app.ui.list.search.suggestion.ISuggestionContainerView;
import com.samsung.android.gallery.app.ui.list.search.suggestion.SuggestionContainerPresenter;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.HashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SuggestionContainerFragment<V extends ISuggestionContainerView, P extends SuggestionContainerPresenter> extends MvpBaseFragment<V, P> implements ISuggestionContainerView {
    protected GalleryAppBarLayout mAppBarLayout;
    private MvpBaseFragment mCurrentFragment;
    private View mDecorView;
    private final HashMap<String, MvpBaseFragment> mFragmentListMap = new HashMap<>();
    protected View mMainLayout;

    private void adjustContentAreaMargin(View view, WindowInsets windowInsets) {
        int i2;
        int i7;
        boolean isApplyWindowInsets = isApplyWindowInsets(windowInsets);
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i8 = 0;
        if (isApplyWindowInsets) {
            i2 = WindowUtils.getSystemInsetsLeft(rootWindowInsets);
        } else {
            i2 = 0;
        }
        marginLayoutParams.leftMargin = i2;
        if (isApplyWindowInsets) {
            i7 = WindowUtils.getSystemInsetsRight(rootWindowInsets);
        } else {
            i7 = 0;
        }
        marginLayoutParams.rightMargin = i7;
        marginLayoutParams.topMargin = WindowUtils.getSystemInsetsTop(rootWindowInsets);
        if (!isLandscape() && !isInMultiWindowMode()) {
            i8 = DeviceInfo.getNavigationBarHeight();
        }
        marginLayoutParams.bottomMargin = i8;
    }

    /* access modifiers changed from: private */
    public MvpBaseFragment createFragment(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        if (removeArgs.startsWith("location://search/AutoComplete")) {
            if (Features.isEnabled(Features.SUPPORT_SCS_SEARCH_AUTOCOMPLETE)) {
                return new SearchAutoCompleteFragmentV2();
            }
            return new SearchAutoCompleteFragment();
        } else if (removeArgs.startsWith("location://search/fileList/Recommendation")) {
            return new RecommendationFragment();
        } else {
            throw new IllegalArgumentException(C0212a.l("unexpected locationKey=", str));
        }
    }

    private MvpBaseFragment getCurrentFragment() {
        return this.mCurrentFragment;
    }

    private MvpBaseFragment getFragment(String str) {
        return this.mFragmentListMap.computeIfAbsent(str, new a(14, this));
    }

    private void setCurrentFragment(MvpBaseFragment mvpBaseFragment) {
        this.mCurrentFragment = mvpBaseFragment;
    }

    private void switchCurrentKey(String str) {
        if (!str.equals((String) this.mBlackboard.read("location://variable/currentv1"))) {
            this.mBlackboard.publish("location://variable/currentv1", str);
        }
    }

    private void updateMainLayoutPaddingHorizontal() {
        ViewUtils.setMainLayoutFlexibleSideSpacing(this.mMainLayout);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mMainLayout = view.findViewById(R.id.main_layout);
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mAppBarLayout = (GalleryAppBarLayout) view.findViewById(R.id.appbar);
    }

    public GalleryAppBarLayout getAppbarLayout() {
        return this.mAppBarLayout;
    }

    public String getFragmentTag(String str) {
        return "SuggestionContainerFragment";
    }

    public int getLayoutId() {
        return R.layout.search_suggestion_container;
    }

    public void handleDensityChange(int i2) {
        getBoosterCompat().acquireFull();
        if (getToolbar() != null) {
            getToolbar().handleDensityChanged();
        }
        ((SuggestionContainerPresenter) this.mPresenter).handleDensityChange();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        updatePadding();
        this.mBlackboard.postEvent(EventMessage.obtain(8506, Boolean.FALSE));
    }

    public void handleResolutionChange(int i2) {
        updatePadding();
    }

    public boolean isApplyWindowInsets(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return true;
        }
        return false;
    }

    public boolean isVolatileFragment() {
        return true;
    }

    public boolean needToRegisterInsetListener() {
        return true;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (SdkConfig.atLeast(SdkConfig.GED.P) && (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            adjustContentAreaMargin(view, windowInsets);
        }
        return windowInsets;
    }

    public boolean onBackPressed() {
        ((SuggestionContainerPresenter) this.mPresenter).onBackPressed();
        return super.onBackPressed();
    }

    public void onBindView(View view) {
        super.onBindView(view);
        if (this.mDecorView == null && getActivity() != null) {
            this.mDecorView = getActivity().getWindow().getDecorView();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.mCurrentFragment == null) {
            switchFragment(getLocationKey());
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.mCurrentFragment = null;
        this.mFragmentListMap.clear();
        removeChildFragments();
        super.onDestroy();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updatePadding();
    }

    public void registerWindowInsetListener(List<View> list) {
        if (needToRegisterInsetListener()) {
            list.add(getView());
        }
    }

    public void switchFragment(String str) {
        String removeArgs = ArgumentsUtil.removeArgs(str);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Fragment findFragmentByTag = childFragmentManager.findFragmentByTag(removeArgs);
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        if (getCurrentFragment() != null) {
            beginTransaction.hide(getCurrentFragment());
        }
        if (findFragmentByTag != null) {
            beginTransaction.show(findFragmentByTag).commitAllowingStateLoss();
        } else {
            findFragmentByTag = getFragment(removeArgs);
            if (findFragmentByTag.isAdded()) {
                beginTransaction.show(findFragmentByTag).commitAllowingStateLoss();
            } else {
                beginTransaction.add((int) R.id.fragment_container, findFragmentByTag, removeArgs).commitAllowingStateLoss();
            }
        }
        switchCurrentKey(str);
        setCurrentFragment((MvpBaseFragment) findFragmentByTag);
    }

    public void updatePadding() {
        updateMainLayoutPaddingHorizontal();
    }

    public SuggestionContainerPresenter createPresenter(ISuggestionContainerView iSuggestionContainerView) {
        return new SuggestionContainerPresenter(this.mBlackboard, iSuggestionContainerView);
    }

    public void postAnalyticsLog() {
    }

    public void initView(View view) {
    }
}
