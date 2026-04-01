package com.samsung.android.gallery.app.ui.abstraction;

import A4.L;
import J5.c;
import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.animation.Animation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import c4.C0438h;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.abstraction.MvpBasePresenter;
import com.samsung.android.gallery.module.foldable.FoldStateManager;
import com.samsung.android.gallery.module.foldable.FoldableScreen;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.abstraction.BoosterCompat;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.cache.LayoutCache;
import com.samsung.android.gallery.widget.predictiveback.PredictiveBackDelegate;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.BackPressUtils;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.sec.android.gallery3d.R;
import e4.a;
import e4.b;
import i.C0212a;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MvpBaseFragment<V extends IMvpBaseView, P extends MvpBasePresenter> extends Fragment implements IMvpBaseView, SharedTransition.TransitionListener, ComponentCallbacks2 {
    protected final String HASH_TAG;
    /* access modifiers changed from: protected */
    public final StringCompat TAG;
    protected View mBackupView;
    protected boolean mBindViewCalled;
    /* access modifiers changed from: protected */
    public Blackboard mBlackboard;
    protected String mBlackboardTag;
    protected String mCallerKey;
    private int mConfigChangeState;
    protected int mCurrentTransitioningAnim;
    private boolean mDefaultExitTransitioning;
    protected final MvpBaseDelegate mDelegate = new MvpBaseDelegate();
    protected boolean mDisplayWithDrawer;
    String mLocationKey;
    private final AtomicBoolean mNoBackgroundRestore;
    protected final PredictiveBackDelegate mPredictiveBackDelegate;
    /* access modifiers changed from: protected */
    public P mPresenter;
    private boolean mReservedPreventNoBackground;
    protected final MvpBaseSystemUi mSystemUi = new MvpBaseSystemUi();
    protected GalleryToolbar mToolbar;
    private boolean mViewReady;

    public MvpBaseFragment() {
        StringCompat stringCompat = new StringCompat(getClass().getSimpleName());
        this.TAG = stringCompat;
        this.HASH_TAG = stringCompat + Log.TAG_SEPARATOR + Integer.toHexString(hashCode());
        this.mPredictiveBackDelegate = new PredictiveBackDelegate(stringCompat, this, new c(25, this), new b(0, this), new b(1, this));
        this.mNoBackgroundRestore = new AtomicBoolean(false);
    }

    private void createMvpModule() {
        this.mPresenter = createPresenterInner(this);
    }

    private P createPresenterInner(V v) {
        P createPresenter = createPresenter(v);
        createPresenter.setLocationKey(this.mLocationKey);
        return createPresenter;
    }

    private void initBaseFragment() {
        setBlackboard(getActivity());
        Bundle arguments = getArguments();
        if (arguments != null) {
            loadArguments(arguments);
        }
        createMvpModule();
    }

    private boolean isDrawerVisible() {
        if (!isTabletDpi()) {
            return false;
        }
        if (supportTabLayout() || LocationKey.isDrawerVisibleLocation(getLocationKey())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$keepScreenOn$4(boolean z) {
        keepScreenOn(getActivity(), z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateAnimation$0() {
        notifyTransitionToCaller(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCreateAnimation$1() {
        setDefaultExitTransitioning(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showOrHideChildFragments$3(boolean z, Fragment fragment) {
        this.mDelegate.showOrHide(fragment, !z);
    }

    private void notifyTransitionToCaller(boolean z) {
        Optional.ofNullable(getCallerFragment()).ifPresent(new L(z, 25));
    }

    private void preventNoBackground() {
        View view;
        if (this.mReservedPreventNoBackground && isRemoving() && (view = getView()) != null && view.getBackground() == null) {
            view.setBackgroundColor(view.getContext().getColor(R.color.default_background));
            this.mNoBackgroundRestore.set(true);
        }
    }

    private void restoreNoBackground() {
        if (this.mNoBackgroundRestore.getAndSet(false) && getView() != null) {
            getView().setBackground((Drawable) null);
        }
    }

    private void setActivityToolbarVisible(Activity activity) {
        Toolbar toolbar;
        int i2;
        if (activity != null) {
            toolbar = (Toolbar) activity.findViewById(R.id.activity_toolbar);
        } else {
            toolbar = null;
        }
        if (toolbar != null) {
            if (supportActivityToolbar()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            toolbar.setVisibility(i2);
        }
    }

    private void setBlackboard(Activity activity) {
        if (activity != null) {
            String obj = activity.toString();
            this.mBlackboardTag = obj;
            this.mBlackboard = Blackboard.getInstance(obj);
            return;
        }
        throw new AssertionError("fail to refer blackboard");
    }

    private void setCallerKey(String str) {
        if (str != null) {
            this.mCallerKey = str;
        }
    }

    private void setOnApplyWindowInsetsListener() {
        ArrayList arrayList = new ArrayList();
        registerWindowInsetListener(arrayList);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setOnApplyWindowInsetsListener(new Ca.c(3, this));
        }
    }

    private void setScreenModeInternal() {
        enterFullListScreen(false);
    }

    public void adjustContentAreaMargin(View view, WindowInsets windowInsets, boolean z) {
        this.mDelegate.adjustContentAreaMargin(this, view, windowInsets, z);
    }

    public void changeScreenMode() {
        setScreenMode();
    }

    public final void clearScreenLocked() {
        this.mSystemUi.clearScreenLocked();
    }

    public Animation createDefaultEnterAnimation(Context context, int i2) {
        return new DefaultEntryAnim().createAnimation(context, i2);
    }

    public abstract P createPresenter(V v);

    public boolean delayScreenMode() {
        return false;
    }

    public final void dump(PrintWriter printWriter, String str) {
        this.mDelegate.dump(this, printWriter, str);
    }

    public void enterFullListScreen(boolean z) {
        this.mSystemUi.enterFullListScreen(z);
        SystemUi.updateSystemUiVisibilityForFullScreen(getActivity());
    }

    public void enterFullScreen(boolean z) {
        this.mSystemUi.enterFullScreen(z);
        SystemUi.updateSystemUiVisibilityForFullScreen(getActivity());
    }

    public void finish() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            blackboard.publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
            return;
        }
        throw new AssertionError("unexpected state. black board is null");
    }

    public String getAnalyticsScreenId(String str) {
        FoldStateManager instance;
        if (TextUtils.isEmpty(str) || (instance = FoldStateManager.getInstance(this.mBlackboard)) == null || instance.getScreen() == FoldableScreen.MAIN) {
            return str;
        }
        return C0212a.A(str, "_S1");
    }

    public GalleryAppBarLayout getAppbarLayout() {
        return null;
    }

    public final Context getApplicationContext() {
        return AppResources.getAppContext();
    }

    public final Blackboard getBlackboard() {
        return this.mBlackboard;
    }

    public final BoosterCompat getBoosterCompat() {
        return this.mDelegate.getBoosterCompat(this);
    }

    public IBaseFragment getCallerFragment() {
        if (this.mCallerKey == null || getFragmentManager() == null) {
            return null;
        }
        return (IBaseFragment) getFragmentManager().findFragmentByTag(this.mCallerKey);
    }

    public List<Fragment> getChildFragments() {
        return getChildFragmentManager().getFragments();
    }

    public View getContentView() {
        return this.mSystemUi.getContentView(getActivity());
    }

    public final EventContext getEventContext() {
        return this.mPresenter;
    }

    public abstract int getLayoutId();

    public final String getLocationKey() {
        return this.mLocationKey;
    }

    public ArrayList<View> getSharedViewList(Blackboard blackboard) {
        return null;
    }

    public int getStatusBarHeight() {
        return this.mSystemUi.getStatusBarHeight(getActivity());
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public boolean handleConfigurationChange(Configuration configuration) {
        if (configuration == null) {
            com.samsung.android.gallery.support.utils.Log.e(this.TAG, "handleConfigurationChange skip. null config");
            return true;
        }
        boolean orientation = this.mSystemUi.setOrientation(configuration.orientation);
        if (orientation) {
            handleOrientationChange(configuration.orientation);
        }
        this.mConfigChangeState = 0;
        boolean densityDpi = this.mSystemUi.setDensityDpi(configuration.densityDpi);
        boolean resolution = this.mSystemUi.setResolution(configuration);
        boolean hardKeyboardHidden = this.mSystemUi.setHardKeyboardHidden(configuration);
        if (densityDpi || resolution) {
            this.mSystemUi.updateDpi(getResources());
        }
        if (densityDpi) {
            this.mConfigChangeState |= 2;
            handleDensityChange(configuration.densityDpi);
        }
        if (resolution) {
            this.mConfigChangeState |= 4;
            handleResolutionChange(this.mSystemUi.getResolution());
        }
        if (hardKeyboardHidden) {
            handleDualScreenChanged();
        }
        this.mConfigChangeState = 0;
        if (orientation || densityDpi || resolution) {
            return true;
        }
        return false;
    }

    public void handleOrientationChange(int i2) {
        this.mDelegate.handleOrientationChange(this, i2);
    }

    public boolean hasNestedContainer() {
        return false;
    }

    public final String hashTag() {
        return this.HASH_TAG;
    }

    public void hideNavigationBar() {
        this.mSystemUi.hideNavigationBar();
    }

    public void initArguments(Bundle bundle) {
        this.mLocationKey = bundle.getString("locationKey", "");
        this.mCallerKey = bundle.getString("callerKey", "");
        this.mBlackboardTag = bundle.getString("blackboardKey", "");
    }

    public boolean initializeActionBar() {
        AppCompatActivity appCompatActivity;
        if (isViewActive() && supportToolbar()) {
            if (useParentToolbar()) {
                return true;
            }
            GalleryToolbar toolbar = getToolbar();
            if (toolbar == null || (appCompatActivity = (AppCompatActivity) getActivity()) == null) {
                com.samsung.android.gallery.support.utils.Log.d(this.TAG, "initializeActionBar skip. no toolbar to init");
            } else {
                appCompatActivity.setSupportActionBar(toolbar);
                toolbar.bringToFront();
                return true;
            }
        }
        return false;
    }

    public void invalidateOptionsMenu() {
        Optional.ofNullable(getActivity()).ifPresent(new C0438h(18));
    }

    public boolean isActivityBusy() {
        P p6 = this.mPresenter;
        if (p6 == null || !p6.isActivityBusy()) {
            return false;
        }
        return true;
    }

    public final boolean isActivityResumed() {
        FragmentActivity activity = getActivity();
        if (activity == null || !activity.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.RESUMED)) {
            return false;
        }
        return true;
    }

    public boolean isApplyWindowInsets(WindowInsets windowInsets) {
        if (windowInsets != null) {
            return true;
        }
        return false;
    }

    public boolean isConfigStateChanged(int i2) {
        if ((this.mConfigChangeState & i2) != 0) {
            return true;
        }
        return false;
    }

    public boolean isDefaultExitTransitioning() {
        if (!PocFeatures.isEnabled(PocFeatures.FragmentPredictiveBack)) {
            return this.mDefaultExitTransitioning;
        }
        int i2 = this.mCurrentTransitioningAnim;
        if (i2 == R.animator.sesl_fragment_open_exit || i2 == R.animator.sesl_fragment_close_exit) {
            return true;
        }
        return false;
    }

    public boolean isDestroyed() {
        if (this.mPresenter == null) {
            return true;
        }
        return false;
    }

    public final boolean isDexMode() {
        return this.mDelegate.isDexMode(this);
    }

    public boolean isDisplayWithDrawer() {
        return this.mDisplayWithDrawer;
    }

    public boolean isDrawerMode() {
        Blackboard blackboard = this.mBlackboard;
        if (blackboard == null || !((Boolean) blackboard.read("data://drawer_tab_enabled", Boolean.FALSE)).booleanValue()) {
            return false;
        }
        return true;
    }

    public boolean isFirstFragment() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            return true;
        }
        return false;
    }

    public final boolean isFromLockScreen() {
        return this.mSystemUi.isFromLockScreen();
    }

    public final boolean isInMultiWindowMode() {
        return SystemUi.isInMultiWindowMode((Activity) getActivity());
    }

    public boolean isInputBlocked() {
        P p6 = this.mPresenter;
        if (p6 == null || !p6.isInputBlocked()) {
            return false;
        }
        return true;
    }

    public boolean isInputBlockedExceptBackKey() {
        P p6 = this.mPresenter;
        if (p6 == null || !p6.isInputBlockedExceptBackKey()) {
            return false;
        }
        return true;
    }

    public boolean isLandscape() {
        return this.mDelegate.isLandscape(this);
    }

    public boolean isNormalLaunchMode() {
        return PickerUtil.isNormalLaunchMode(this.mBlackboard);
    }

    public final boolean isPortrait() {
        return this.mSystemUi.isPortrait();
    }

    public boolean isSinglePickLaunchMode() {
        return PickerUtil.isSinglePickLaunchMode(this.mBlackboard);
    }

    public final boolean isTabletDpi() {
        return this.mSystemUi.isTabletDpi();
    }

    public final boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    public final boolean isViewActive() {
        View view = getView();
        if (!isAdded() || isHidden() || view == null || view.getWindowToken() == null) {
            return false;
        }
        return true;
    }

    public final boolean isViewHidden() {
        return isHidden();
    }

    public final boolean isViewReady() {
        if (!this.mViewReady || this.mBackupView == null) {
            return false;
        }
        return true;
    }

    public final boolean isViewResumed() {
        return isResumed();
    }

    public final void keepScreenOn(boolean z) {
        ThreadUtil.runOnUiThread(new B4.c((Object) this, z, 19));
    }

    public void loadArguments(Bundle bundle) {
        String string = bundle.getString("locationKey");
        String string2 = bundle.getString("callerKey");
        setLocationKey(string);
        setCallerKey(string2);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        initBaseFragment();
        this.mSystemUi.onViewAttach(getActivity(), this.mBlackboard);
        this.mPresenter.onViewAttach();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onConfigurationChanged(Configuration configuration) {
        boolean isAospLargeScreenDpi = this.mSystemUi.isAospLargeScreenDpi();
        super.onConfigurationChanged(configuration);
        handleConfigurationChange(configuration);
        changeScreenMode();
        this.mPredictiveBackDelegate.onConfigurationChanged();
        if (isAospLargeScreenDpi != this.mSystemUi.isAospLargeScreenDpi()) {
            this.mSystemUi.cancelForceRotate(getActivity());
        }
    }

    public void onCreate(Bundle bundle) {
        Trace.beginSection(this.TAG + " onCreate");
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.l(stringCompat, "onCreate " + this.HASH_TAG);
        super.onCreate(bundle);
        this.mPresenter.onViewCreate();
        this.mPredictiveBackDelegate.onCreate();
        Trace.endSection();
    }

    public Animation onCreateAnimation(int i2, boolean z, int i7) {
        if (z && (i7 == R.anim.depth_in_enter || i7 == R.anim.fragment_open_enter)) {
            Animation createDefaultEnterAnimation = createDefaultEnterAnimation(getActivity(), i7);
            if (createDefaultEnterAnimation != null) {
                if (PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION) {
                    notifyTransitionToCaller(true);
                    ThreadUtil.postOnUiThreadDelayed(new a(0, this), 300);
                }
                return createDefaultEnterAnimation;
            }
        } else if (PreferenceFeatures.OneUi7x.FRAGMENT_H_SLIDE_ANIMATION && !z && i7 == R.anim.fragment_close_exit) {
            setDefaultExitTransitioning(true);
            ThreadUtil.postOnUiThreadDelayed(new a(1, this), 300);
        }
        return super.onCreateAnimation(i2, z, i7);
    }

    public Animator onCreateAnimator(int i2, boolean z, boolean z3) {
        Animator animator;
        if (!isDrawerVisible()) {
            animator = super.onCreateAnimator(i2, z, z3);
        } else {
            animator = null;
        }
        this.mDelegate.customizeAnimation(getContext(), animator);
        this.mDelegate.postOpenEnterAnimStartEvent(this, animator, i2);
        this.mDelegate.setCurrentTransitioningAnim(this, animator, i2);
        this.mDelegate.rollbackProperty(this, animator);
        return animator;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            Trace.beginSection(this.TAG + " onCreateView");
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.v(stringCompat, "onCreateView " + this.HASH_TAG);
            this.mDefaultExitTransitioning = false;
            handleConfigurationChange(getResources().getConfiguration());
            if (this.mBackupView == null && supportLayoutCache()) {
                View cachedLayout = LayoutCache.getInstance().getCachedLayout(getLayoutId());
                this.mBackupView = cachedLayout;
                if (cachedLayout != null && !Objects.equals(getContext(), this.mBackupView.getContext())) {
                    com.samsung.android.gallery.support.utils.Log.w(this.TAG, "onCreateView#LayoutCache context is different");
                    this.mBackupView = null;
                }
            }
            if (this.mBackupView == null) {
                this.mBackupView = layoutInflater.inflate(getLayoutId(), viewGroup, false);
            }
            if (!this.mBindViewCalled) {
                this.mBindViewCalled = true;
                bindView(this.mBackupView);
                onBindView(this.mBackupView);
            }
            onPrepareSharedTransitionV2();
            this.mPredictiveBackDelegate.onCreateView(this.mBackupView);
            return this.mBackupView;
        } finally {
            Trace.endSection();
        }
    }

    public void onDestroy() {
        this.mPresenter.onViewDestroy();
        this.mPresenter = null;
        this.mBackupView = null;
        this.mViewReady = false;
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.l(stringCompat, "onDestroy " + this.HASH_TAG);
        this.mBindViewCalled = false;
        this.mPredictiveBackDelegate.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        super.onDestroyView();
        restoreNoBackground();
        ViewUtils.removeSelf(getView());
        this.mPredictiveBackDelegate.onDestroyView();
        if (getToolbar() != null) {
            this.mPresenter.releaseToolbar(getToolbar());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.mCallerKey = null;
        this.mToolbar = null;
        this.mSystemUi.onViewDetach();
    }

    public void onDump(PrintWriter printWriter, String str) {
        this.mDelegate.onDump(this, printWriter, str);
    }

    public boolean onHandleEvent(EventMessage eventMessage) {
        P p6 = this.mPresenter;
        if (p6 == null || !p6.handleEvent(eventMessage)) {
            return false;
        }
        return true;
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!isDestroyed()) {
            showOrHideChildFragments(z);
            this.mPresenter.onHiddenChanged(z);
            if (!z && getToolbar() != null && initializeActionBar() && getToolbar() != null) {
                this.mPresenter.updateToolbar(getToolbar());
            }
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 84) {
            return true;
        }
        return false;
    }

    public void onMultiWindowModeChanged(boolean z) {
        if (SystemUi.hasNoStatusBarInLandscape(getActivity())) {
            setScreenMode();
        }
    }

    public final boolean onOptionsItemSelected(MenuItem menuItem, boolean z) {
        if (z) {
            if (menuItem.getItemId() == R.id.action_view_as) {
                new ViewAsCmd().execute(this.mPresenter, new Object[0]);
            }
            return true;
        } else if (!isViewActive() || !this.mPresenter.onOptionsItemSelected(menuItem)) {
            return false;
        } else {
            return true;
        }
    }

    public void onPause() {
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.l(stringCompat, "onPause " + this.HASH_TAG);
        super.onPause();
        this.mPresenter.onViewPause();
    }

    public void onPostResume() {
        handleConfigurationChange(getResources().getConfiguration());
        setActivityToolbarVisible(getActivity());
        if (isVisible()) {
            postAnalyticsLog();
        }
    }

    public void onPrepareSharedTransitionV2() {
        SharedTransition.onPrepareOthers(this.mBlackboard, this);
    }

    public void onResume() {
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.l(stringCompat, "onResume " + this.HASH_TAG);
        super.onResume();
        this.mPresenter.onViewResume();
        onPostResume();
        this.mBlackboard.publish("data://resumed_fragment", this.HASH_TAG);
        this.mPredictiveBackDelegate.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.clear();
    }

    public void onStart() {
        super.onStart();
        this.mPresenter.onViewStart();
        setScreenMode();
    }

    public void onStop() {
        super.onStop();
        this.mPresenter.onViewStop();
        preventNoBackground();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (!this.mViewReady) {
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.l(stringCompat, "onViewCreated " + this.HASH_TAG);
            this.mViewReady = true;
            this.mPresenter.onViewCreated(view);
        }
        if (initializeActionBar()) {
            updateToolbar();
        }
        setOnApplyWindowInsetsListener();
        this.mPredictiveBackDelegate.onViewCreated();
    }

    public void postAnalyticsLog() {
        AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(getScreenId()));
    }

    public void postponeEnterTransitionV2() {
        com.samsung.android.gallery.support.utils.Log.st(this.TAG, "postponeEnterTransitionV2");
        super.postponeEnterTransition();
    }

    public void predictiveBackCanceled() {
        initializeActionBar();
    }

    public void releaseInputBlocking() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            p6.releaseInputBlocking(-1);
        }
    }

    public final void removeChildFragments() {
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (!fragments.isEmpty()) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            for (Fragment remove : fragments) {
                beginTransaction.remove(remove);
            }
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void reservePreventNoBackground(boolean z) {
        this.mReservedPreventNoBackground = z;
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
        if (bundle != null) {
            initArguments(bundle);
        }
    }

    public void setCurrentTransitioningAnim(int i2) {
        this.mCurrentTransitioningAnim = i2;
    }

    public void setDefaultExitTransitioning(boolean z) {
        this.mDefaultExitTransitioning = z;
    }

    public boolean setInputBlock(String str) {
        P p6 = this.mPresenter;
        return p6 != null && p6.setInputBlock(str);
    }

    public void setLocationKey(String str) {
        if (str != null) {
            this.mLocationKey = str;
            return;
        }
        StringCompat stringCompat = this.TAG;
        com.samsung.android.gallery.support.utils.Log.e(stringCompat, "setLocationKey k=null\n" + ThreadUtil.getCallStack(3, 10));
    }

    public void setOptionsMenu(boolean z) {
        P p6 = this.mPresenter;
        if (p6 != null) {
            p6.enableOptionsMenu(z);
        }
    }

    public void setScreenMode() {
        setScreenModeInternal();
    }

    public void setScreenViewerMode() {
        enterFullScreen(false);
    }

    public void setToolbar(GalleryToolbar galleryToolbar) {
        this.mToolbar = galleryToolbar;
    }

    public void showNavigationBar() {
        this.mSystemUi.showNavigationBar();
    }

    public final void showOrHide(Fragment fragment, boolean z) {
        this.mDelegate.showOrHide(fragment, z);
    }

    public void showOrHideChildFragments(boolean z) {
        getChildFragmentManager().getFragments().stream().filter(new com.samsung.android.gallery.module.dynamicview.a(19)).forEach(new E7.c(this, z, 12));
    }

    public void startPostponedEnterTransitionV2() {
        com.samsung.android.gallery.support.utils.Log.st(this.TAG, "startPostponedEnterTransition");
        super.startPostponedEnterTransition();
    }

    public boolean supportEnterPredictiveBack() {
        return BackPressUtils.supportPredictiveBack(getContext());
    }

    public boolean supportExitPredictiveBack() {
        return BackPressUtils.supportPredictiveBack(getContext());
    }

    public boolean supportFloatingUi() {
        return true;
    }

    public boolean supportLayoutCache() {
        return false;
    }

    public final CharSequence tag() {
        return this.TAG;
    }

    public void updateToolbar() {
        updateToolbar(false);
    }

    public boolean useAdvancedMouseDragAndDrop() {
        return this.mDelegate.useAdvancedMouseDragAndDrop(this);
    }

    public boolean useParentToolbar() {
        return false;
    }

    private void keepScreenOn(Activity activity, boolean z) {
        if (activity == null) {
            activity = BlackboardUtils.readActivity(this.mBlackboardTag);
        }
        SystemUi.keepScreenOn(activity, z);
    }

    public void hideNavigationBar(boolean z) {
        this.mSystemUi.hideNavigationBar(z);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId) {
        if (analyticsEventId != null) {
            AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(getScreenId()), analyticsEventId.toString());
        }
    }

    public boolean setInputBlock(String str, int i2) {
        P p6 = this.mPresenter;
        return p6 != null && p6.setInputBlock(str, i2);
    }

    public void showNavigationBar(boolean z) {
        this.mSystemUi.showNavigationBar(z);
    }

    public void updateToolbar(boolean z) {
        GalleryToolbar toolbar = getToolbar();
        if (z) {
            if (toolbar != null) {
                toolbar.setNavigationIcon((Drawable) null);
            }
            if (!isDestroyed()) {
                this.mPresenter.resetCurrentNaviUpResId();
            }
        }
        if (toolbar != null && !isDestroyed()) {
            this.mPresenter.updateToolbar(toolbar);
            this.mPresenter.setNavigationUpClickListener(toolbar);
        }
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str) {
        if (analyticsEventId != null && str != null) {
            AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(getScreenId()), analyticsEventId.toString(), str);
        }
    }

    public void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, String str2) {
        if (str != null && analyticsEventId != null && str2 != null) {
            AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(str), analyticsEventId.toString(), str2);
        }
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId, String str, String str2) {
        if (analyticsEventId == null || str == null || str2 == null) {
            StringCompat stringCompat = this.TAG;
            com.samsung.android.gallery.support.utils.Log.w(stringCompat, "fail postCustomDimension : " + analyticsEventId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + str + ArcCommonLog.TAG_COMMA + str2);
            return;
        }
        AnalyticsLogger.getInstance().postCustomDimension(getAnalyticsScreenId(getScreenId()), analyticsEventId.toString(), str, str2);
    }

    public void postAnalyticsLog(AnalyticsEventId analyticsEventId, Pair<String, String>[] pairArr) {
        if (analyticsEventId == null || pairArr == null) {
            StringCompat stringCompat = this.TAG;
            StringBuilder sb2 = new StringBuilder("fail postCustomDimension : ");
            sb2.append(analyticsEventId);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(pairArr != null);
            com.samsung.android.gallery.support.utils.Log.w(stringCompat, sb2.toString());
            return;
        }
        AnalyticsLogger.getInstance().postCustomDimension(getAnalyticsScreenId(getScreenId()), analyticsEventId.toString(), pairArr);
    }

    public final void postAnalyticsLog(AnalyticsEventId analyticsEventId, long j2) {
        AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(getScreenId()), analyticsEventId.toString(), j2);
    }

    public final void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId) {
        if (str != null && analyticsEventId != null) {
            AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(str), analyticsEventId.toString());
        }
    }

    public final void postAnalyticsLog(String str, AnalyticsEventId analyticsEventId, long j2) {
        if (str != null && analyticsEventId != null) {
            AnalyticsLogger.getInstance().postLog(getAnalyticsScreenId(str), analyticsEventId.toString(), j2);
        }
    }

    public void handleDualScreenChanged() {
    }

    public void onDisplayedWithDrawer() {
    }

    public void onEnterTransitionEndV2() {
    }

    public void onEnterTransitionStartV2() {
    }

    public void onSelectedFromBottomTab() {
    }

    public void bindView(View view) {
    }

    public String getFragmentTag(String str) {
        return str;
    }

    public void handleDensityChange(int i2) {
    }

    public void handleResolutionChange(int i2) {
    }

    public void onBindView(View view) {
    }

    public void onTrimMemory(int i2) {
    }

    public void registerWindowInsetListener(List<View> list) {
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        return windowInsets;
    }
}
