package com.samsung.android.gallery.app.ui.list.trash.container;

import A4.C0369d;
import A4.L;
import U6.a;
import U6.b;
import U6.c;
import U6.d;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.oneui.floatingactioncontainer.FloatingToolbarLayout;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.app.ui.list.trash.TrashFragment;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.sec.android.gallery3d.R;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashContainerFragment extends MvpBaseFragment<ITrashContainerView, TrashContainerPresenter> implements ITrashContainerView {
    private static final String[] LOCATION_KEY = {"location://trash", "location://family/shared/trash"};
    private GalleryAppBarLayout mAppBarLayout;
    private MvpBaseFragment<?, ?> mCurrentFragment;
    private FloatingToolbarLayout mFloatingToolbarLayout;
    private final HashMap<Integer, MvpBaseFragment<?, ?>> mFragmentMap = new HashMap<>();
    private boolean mHasFamilyAlbum;
    private View mTabLayout;
    private final View[] mTabView = new View[2];

    private void adjustViewSideMargin(View view, View view2, boolean z) {
        int i2;
        if (ViewUtils.isVisible(view2) && !isDrawerMode()) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
            int i7 = 0;
            if (z) {
                i2 = WindowUtils.getSystemInsetsLeft(rootWindowInsets);
            } else {
                i2 = 0;
            }
            marginLayoutParams.leftMargin = i2;
            if (z) {
                i7 = WindowUtils.getSystemInsetsRight(rootWindowInsets);
            }
            marginLayoutParams.rightMargin = i7;
        }
    }

    private MvpBaseFragment<?, ?> computeFragment(int i2) {
        return this.mFragmentMap.computeIfAbsent(Integer.valueOf(i2), new d(this, i2, 0));
    }

    private boolean isEditMode() {
        return ArgumentsUtil.getArgValue(getLocationKey(), "editMode", false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ MvpBaseFragment lambda$computeFragment$1(int i2, Integer num) {
        Bundle bundle;
        TrashFragment trashFragment = new TrashFragment();
        Bundle arguments = getArguments();
        if (arguments != null) {
            bundle = arguments.deepCopy();
        } else {
            bundle = new Bundle();
        }
        bundle.putString("locationKey", new UriBuilder(LOCATION_KEY[i2]).appendArg("editMode", isEditMode()).build());
        bundle.putInt("fragment-layout", R.layout.fragment_child_trash_layout);
        bundle.putBoolean("has-family-album", this.mHasFamilyAlbum);
        trashFragment.setArguments(bundle);
        return trashFragment;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$2(View view) {
        onTabClick(view, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$3(View view) {
        view.setOnClickListener(new a(this, 0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$4(View view) {
        onTabClick(view, 1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$5(View view) {
        view.setOnClickListener(new a(this, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$6(View view) {
        setViewState(view, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindView$7(View view) {
        setViewEnabled(view, false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSelectionModeChanged$0() {
        this.mBlackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCollapsedHeight$8() {
        WindowInsets windowInsets;
        this.mTabLayout.requestLayout();
        if (getView() != null) {
            windowInsets = getView().getRootWindowInsets();
        } else {
            windowInsets = null;
        }
        GalleryAppBarLayout galleryAppBarLayout = this.mAppBarLayout;
        if (galleryAppBarLayout != null) {
            this.mAppBarLayout.seslSetCollapsedHeight((float) (this.mTabLayout.getHeight() + WindowUtils.getSystemInsetsTop(windowInsets) + ((int) galleryAppBarLayout.seslGetDefaultCollapsedHeight())));
        }
    }

    private void onTabClick(View view, int i2) {
        char c5;
        AnalyticsEventId analyticsEventId;
        if (view.isSelected()) {
            Log.d(this.TAG, "onTabClick skip", Integer.valueOf(i2));
            return;
        }
        Log.d(this.TAG, "onTabClick", Integer.valueOf(i2));
        View[] viewArr = this.mTabView;
        if (i2 == 0) {
            c5 = 1;
        } else {
            c5 = 0;
        }
        setViewState(viewArr[c5], false);
        setViewState(view, true);
        switchChildFragment(i2);
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            if (mvpBaseFragment instanceof TrashFragment) {
                ((TrashFragment) mvpBaseFragment).onCommitFromContainer();
            }
            String screenId = this.mCurrentFragment.getScreenId();
            if (i2 == 0) {
                analyticsEventId = AnalyticsEventId.EVENT_RECYCLE_BIN_MY_ALBUM_TAB;
            } else {
                analyticsEventId = AnalyticsEventId.EVENT_RECYCLE_BIN_FAMILY_ALBUM_TAB;
            }
            postAnalyticsLog(screenId, analyticsEventId);
        }
    }

    private void setSystemUiBarAndBgColor() {
        Context context = getContext();
        if (context != null) {
            this.mBlackboard.post("command://ChangeActivityBgColor", Integer.valueOf(context.getColor(R.color.default_background)));
        }
    }

    private void setViewEnabled(View view, boolean z) {
        float f;
        view.setEnabled(z);
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        view.setAlpha(f);
    }

    private void setViewState(View view, boolean z) {
        ColorStateList colorStateList;
        int i2;
        if (z) {
            colorStateList = getResources().getColorStateList(R.color.trash_tab_selected_tint_color, (Resources.Theme) null);
        } else {
            colorStateList = null;
        }
        view.setBackgroundTintList(colorStateList);
        view.setSelected(z);
        TextView textView = (TextView) view;
        Resources resources = getResources();
        if (z) {
            i2 = R.color.trash_tab_selected_title_color;
        } else {
            i2 = R.color.trash_tab_unselected_title_color;
        }
        textView.setTextColor(resources.getColorStateList(i2, (Resources.Theme) null));
    }

    private void switchChildFragment(int i2) {
        Fragment computeFragment = computeFragment(i2);
        Optional ofNullable = Optional.ofNullable(computeFragment.getTag());
        String str = (String) ofNullable.orElse("trash#" + i2);
        Log.d(this.TAG, "switchChildFragment", Integer.valueOf(i2), str);
        FragmentManager childFragmentManager = getChildFragmentManager();
        Fragment findFragmentByTag = childFragmentManager.findFragmentByTag(str);
        FragmentTransaction beginTransaction = childFragmentManager.beginTransaction();
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            beginTransaction.hide(mvpBaseFragment);
        }
        if (findFragmentByTag != null) {
            beginTransaction.show(findFragmentByTag).commitAllowingStateLoss();
            ((MvpBaseFragment) findFragmentByTag).updateToolbar();
            computeFragment = findFragmentByTag;
        } else {
            beginTransaction.add((int) R.id.trash_fragment_container, computeFragment, str).commitAllowingStateLoss();
        }
        if (this.mDisplayWithDrawer) {
            ((MvpBaseFragment) computeFragment).onDisplayedWithDrawer();
        }
        this.mCurrentFragment = (MvpBaseFragment) computeFragment;
    }

    private void updateCollapsedHeight() {
        View view = this.mTabLayout;
        if (view != null) {
            view.post(new c(this, 0));
        }
    }

    public String getAppState() {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment instanceof IBaseListView) {
            return mvpBaseFragment.getAppState();
        }
        return null;
    }

    public GalleryAppBarLayout getAppbarLayout() {
        return this.mAppBarLayout;
    }

    public FloatingToolbarLayout getFloatingToolbarLayout() {
        return this.mFloatingToolbarLayout;
    }

    public IBaseFragment getFocusedChild() {
        return this.mCurrentFragment;
    }

    public int getLayoutId() {
        return R.layout.fragment_trash_container_layout;
    }

    public int getTabLayoutHeight() {
        return ViewUtils.getHeight(this.mTabLayout);
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        updateAppbarScroll();
    }

    public boolean hasNestedContainer() {
        return true;
    }

    public boolean onAdvancedMouseEvent(MotionEvent motionEvent) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            return mvpBaseFragment.onAdvancedMouseEvent(motionEvent);
        }
        return super.onAdvancedMouseEvent(motionEvent);
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        GalleryToolbar galleryToolbar;
        boolean isApplyWindowInsets = isApplyWindowInsets(windowInsets);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = 0;
            adjustViewSideMargin(view, this.mAppBarLayout, isApplyWindowInsets);
            adjustViewSideMargin(view, this.mTabLayout, isApplyWindowInsets);
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 && (galleryToolbar = this.mToolbar) != null) {
                adjustViewSideMargin(view, (View) galleryToolbar.getParent(), isApplyWindowInsets);
            }
            updateCollapsedHeight();
        }
        return windowInsets;
    }

    public boolean onBackPressed() {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !mvpBaseFragment.onBackPressed()) {
            return false;
        }
        return true;
    }

    public void onBindView(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mTabLayout = view.findViewById(R.id.tab_layout);
        this.mAppBarLayout = (GalleryAppBarLayout) view.findViewById(R.id.appbar);
        this.mFloatingToolbarLayout = (FloatingToolbarLayout) view.findViewById(R.id.sesl_floating_toolbar_layout);
        int i2 = 0;
        int argValue = ArgumentsUtil.getArgValue(getLocationKey(), "index", 0);
        this.mHasFamilyAlbum = ArgumentsUtil.getArgValue(getLocationKey(), "has_family_album", false);
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) || !this.mHasFamilyAlbum) {
            ViewUtils.setVisibility(this.mTabLayout, 8);
        } else {
            this.mTabView[0] = view.findViewById(R.id.trash_for_local);
            this.mTabView[1] = view.findViewById(R.id.trash_for_family_shared);
            Optional.ofNullable(this.mTabView[0]).ifPresent(new b(this, 0));
            Optional.ofNullable(this.mTabView[1]).ifPresent(new b(this, 1));
            Optional.of(this.mTabView[argValue]).ifPresent(new b(this, 2));
            if (isEditMode()) {
                while (true) {
                    View[] viewArr = this.mTabView;
                    if (i2 >= viewArr.length) {
                        break;
                    }
                    if (i2 != argValue) {
                        Optional.of(viewArr[i2]).ifPresent(new b(this, 3));
                    }
                    i2++;
                }
            }
            updateCollapsedHeight();
        }
        switchChildFragment(argValue);
    }

    public void onDestroy() {
        super.onDestroy();
        removeChildFragments();
        this.mCurrentFragment = null;
        this.mFragmentMap.clear();
    }

    public void onDisplayedWithDrawer() {
        this.mDisplayWithDrawer = true;
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            return mvpBaseFragment.onHandleEvent(eventMessage);
        }
        return super.onHandleEvent(eventMessage);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !mvpBaseFragment.onKeyDown(i2, keyEvent)) {
            return false;
        }
        return true;
    }

    public boolean onKeyLongPress(int i2, KeyEvent keyEvent) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !mvpBaseFragment.onKeyLongPress(i2, keyEvent)) {
            return false;
        }
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment == null || !mvpBaseFragment.onKeyUp(i2, keyEvent)) {
            return false;
        }
        return true;
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        updateAppbarScroll();
    }

    public boolean onPenEvent(MotionEvent motionEvent) {
        MvpBaseFragment<?, ?> mvpBaseFragment = this.mCurrentFragment;
        if (mvpBaseFragment != null) {
            return mvpBaseFragment.onPenEvent(motionEvent);
        }
        return super.onPenEvent(motionEvent);
    }

    public void onSelectionModeChanged(boolean z) {
        if (isViewActive()) {
            Log.d(this.TAG, "onSelectionModeChanged", Boolean.valueOf(z), Boolean.valueOf(isHidden()));
            if (z || !isEditMode()) {
                View[] viewArr = this.mTabView;
                int length = viewArr.length;
                int i2 = 0;
                while (i2 < length) {
                    View view = viewArr[i2];
                    if (view == null || view.isSelected()) {
                        i2++;
                    } else {
                        setViewEnabled(view, !z);
                        return;
                    }
                }
                return;
            }
            ThreadUtil.postOnUiThreadDelayed(new c(this, 1), 500);
        }
    }

    public void onStart() {
        super.onStart();
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB) {
            setSystemUiBarAndBgColor();
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateAppbarScroll();
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public void setCurrentTransitioningAnim(int i2) {
        Optional.ofNullable(this.mCurrentFragment).ifPresent(new C0369d(i2, 13));
    }

    public void setDefaultExitTransitioning(boolean z) {
        Optional.ofNullable(this.mCurrentFragment).ifPresent(new L(z, 20));
    }

    public void startShrinkAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public void updateAppbarScroll() {
        if (this.mAppBarLayout != null && DrawerUtil.supportDrawerLayout(getContext())) {
            this.mAppBarLayout.disableAppbarScroll();
        }
    }

    public TrashContainerPresenter createPresenter(ITrashContainerView iTrashContainerView) {
        return new TrashContainerPresenter(getBlackboard(), iTrashContainerView);
    }

    public void postAnalyticsLog() {
    }

    public void initView(View view) {
    }

    public void showOrHideChildFragments(boolean z) {
    }
}
