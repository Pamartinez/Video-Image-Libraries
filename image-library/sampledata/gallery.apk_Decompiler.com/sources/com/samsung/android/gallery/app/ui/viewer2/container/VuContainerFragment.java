package com.samsung.android.gallery.app.ui.viewer2.container;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowInsets;
import android.view.animation.Animation;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.insets.ProtectionLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
import bc.d;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.app.remote.v2.IVuDispatcher;
import com.samsung.android.gallery.app.ui.IBaseFragment;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.BixbyDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.MultiWindowMarginDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.ViewerTableModeDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.film.FilmStripSeekerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.menu.ViewerMenuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.pager.ViewPagerDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.GroupPanelTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.RemasterTransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.container.delegate.transition.TransitionDelegate;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegateFragment;
import com.samsung.android.gallery.app.ui.viewer2.delegate.VuDelegateComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.debugger.LeakTracker;
import com.samsung.android.gallery.module.debugger.ViewerPerformanceTracker;
import com.samsung.android.gallery.module.directorsview.DirectorsViewCache;
import com.samsung.android.gallery.module.utils.ViewerUtils;
import com.samsung.android.gallery.settings.activity.e;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.Vu2ContainerLayout;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sum.core.message.Message;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import i.C0212a;
import ic.l;
import j7.a;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuContainerFragment extends AbsVuDelegateFragment<IVuContainerView, VuContainerPresenter> implements IVuContainerView, IVuDispatcher {
    ViewerObjectComposite mCurrentViewer;
    private int mDataVersion = -1;
    private TextView mDebugTextView;
    private FilmStripDelegate mFilmStripDelegate;
    private FilmStripSeekerDelegate mFilmStripSeekerDelegate;
    protected View mFilmViewLayout;
    protected ViewStub mFilmViewStub;
    private final VuContainerFragmentAnimator mFragmentAnimator = new VuContainerFragmentAnimator();
    private boolean mIsPendingBackPressed;
    protected RelativeLayout mMainLayout;
    private final Runnable mMediaDataUpdated = new C0451a(28, this);
    protected ViewPager2 mViewPager;
    private ViewPagerDelegate mViewPagerDelegate;

    private void bindViewInternal(View view) {
        this.mToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mViewPager = (ViewPager2) view.findViewById(R.id.view_pager);
        this.mMainLayout = (RelativeLayout) view.findViewById(R.id.main_layout);
        if (ViewerUtils.supportFilmStrip(getLocationKey())) {
            ViewStub viewStub = (ViewStub) view.findViewById(R.id.film_strip_view_stub);
            this.mFilmViewStub = viewStub;
            this.mFilmViewLayout = viewStub.inflate();
        }
        if (PocFeatures.DEBUG_VIEWER2) {
            View findViewById = view.findViewById(R.id.container_log_view);
            if (findViewById instanceof ViewStub) {
                findViewById = ((ViewStub) findViewById).inflate();
            }
            this.mDebugTextView = (TextView) findViewById;
        }
        inflatePhotoPreview(view);
    }

    private void cancelForceRotate() {
        this.mSystemUi.cancelForceRotate(getActivity());
    }

    private void executePendingBackPressed() {
        if (this.mIsPendingBackPressed) {
            this.mIsPendingBackPressed = false;
            onBackPressed();
        }
    }

    private String getDebugHeader() {
        return "\n" + TimeUtil.getIsoLocalTimeInMin(System.currentTimeMillis()) + "|";
    }

    private int getLayoutResource() {
        if (LocationKey.isRevitalizationView(getLocationKey())) {
            return R.layout.container_remaster_photo_preview;
        }
        return R.layout.container_photo_preview;
    }

    private int getReturnPosition(String str) {
        if (LocationKey.isSuggests(str)) {
            return ArgumentsUtil.getArgValue(str, Message.KEY_POSITION, 0);
        }
        int argValue = ArgumentsUtil.getArgValue(str, "fixed_return_position_hover", -1);
        if (argValue != -1) {
            return argValue;
        }
        return ((ContainerModel) this.mModel).getPosition();
    }

    private Class<? extends TransitionDelegate> getTransitionDelegateClass() {
        if (LocationKey.isRevitalizationView(getLocationKey())) {
            return RemasterTransitionDelegate.class;
        }
        if (LocationKey.isSecondDepthGroupPanelView(getLocationKey())) {
            return GroupPanelTransitionDelegate.class;
        }
        return TransitionDelegate.class;
    }

    private void inflatePhotoPreview(View view) {
        View findViewById = view.findViewById(R.id.viewer_container_preview);
        if (ViewUtils.isViewStub(findViewById)) {
            ViewStub viewStub = (ViewStub) findViewById;
            viewStub.setLayoutResource(getLayoutResource());
            viewStub.inflate();
        }
    }

    private boolean isIgnoredBackPressedCase() {
        ViewerObjectComposite currentViewer = getCurrentViewer();
        if (currentViewer == null) {
            Log.e(this.TAG, "onBackPressed consumed : current viewer is not ready yet");
            return true;
        } else if (!BottomSheetState.isExpanded((BottomSheetState.StateListener) this.mModel) || currentViewer.getModel().isViewConfirmed()) {
            return false;
        } else {
            Log.e(this.TAG, "onBackPressed consumed : details is expanded but currentViewer is not confirmed");
            return true;
        }
    }

    private boolean isMoveToViewer() {
        Stack stack = (Stack) getBlackboard().read("debug://FragmentStack");
        if (stack == null || stack.size() <= 0 || !((String) stack.peek()).contains("viewer")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$8() {
        onMediaDataChanged(-1, -1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$printLog$6(TextView textView, String str) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(textView.getText());
        sb2.append(getDebugHeader());
        sb2.append(str);
        if (sb2.length() > 580) {
            sb2.delete(0, sb2.indexOf("\n") + 1);
        }
        textView.setText(sb2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$printLog$7(String str, TextView textView) {
        textView.postDelayed(new d((Object) this, (Object) textView, (Object) str, 25), 100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$0(Object[] objArr) {
        keepScreenOn(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$1(Object[] objArr) {
        resetDataVersion();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$2(Object[] objArr) {
        setScreenMode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$3(Object[] objArr) {
        onRemoteDisplayFakedDragViewCallback(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$4(Object[] objArr) {
        onRemoteDisplayFakedDragViewCallback((ViewPager2.OnPageChangeCallback) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setActionInvoker$5(Object[] objArr) {
        showNavigationBar();
    }

    private void onRemoteDisplayFakedDragViewCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        if (getView() != null) {
            ((Vu2ContainerLayout) getView().findViewById(R.id.viewer_container_layout)).setFakeDragViewCallback(onPageChangeCallback);
        }
    }

    private void setActionInvoker() {
        this.mActionInvoker.add(ViewerAction.KEEP_SCREEN_ON, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.RESET_DATA_VERSION, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.SET_SCREEN_MODE, new a(this, 2));
        this.mActionInvoker.add(ViewerAction.REMOTE_DISPLAY_FAKED_DRAG_VIEW_CALLBACK, new a(this, 3));
        this.mActionInvoker.add(ViewerAction.REMOTE_DISPLAY_ELIMINATE_FAKED_DRAG_VIEW_CALLBACK, new a(this, 4));
        this.mActionInvoker.add(ViewerAction.SHOW_NAVIGATION_BAR, new a(this, 5));
    }

    private void setFilmStripSeekerUpdateEnable(boolean z) {
        FilmStripSeekerDelegate filmStripSeekerDelegate = this.mFilmStripSeekerDelegate;
        if (filmStripSeekerDelegate != null) {
            filmStripSeekerDelegate.setSeekerUpdateEnabled(z);
        }
    }

    private void updateGradientProtection(boolean z) {
        int i2;
        if (PocFeatures.isEnabled(PocFeatures.ViewerGradientProtection) && !PocFeatures.isEnabled(PocFeatures.GradientProtection)) {
            View view = getView();
            if (view instanceof ProtectionLayout) {
                ProtectionLayout protectionLayout = (ProtectionLayout) view;
                if (!((ContainerModel) this.mModel).isFlipCoverGallery()) {
                    if (z) {
                        i2 = protectionLayout.getResources().getColor(R.color.viewer_daynight_gradient_protection_color, (Resources.Theme) null);
                    } else {
                        i2 = protectionLayout.getResources().getColor(R.color.transparent, (Resources.Theme) null);
                    }
                    ViewUtils.updateGradientProtection(protectionLayout, ViewUtils.ProtectionGradientType.VERTICAL, i2);
                }
            }
        }
    }

    public void enableOsd(boolean z) {
        if (((ContainerModel) this.mModel).getStateHelper().isContainerResumed()) {
            if (!z) {
                Optional.ofNullable((ViewerMenuDelegate) getDelegate(ViewerMenuDelegate.class)).ifPresent(new l(9));
            } else if (SystemUi.isSystemBarBehaviorDefault(getActivity()) && !((ContainerModel) this.mModel).isFlipCoverGallery()) {
                SystemUi.setViewerSystemBar(getActivity());
            }
            updateBackgroundColor(z);
            updateNavigationBar();
            setFilmStripSeekerUpdateEnable(z);
        }
    }

    public void finish() {
        if (isFirstFragment()) {
            getBlackboard().post("command://request_suicide", (Object) null);
        } else {
            super.finish();
        }
    }

    public String getAppState() {
        BixbyDelegate bixbyDelegate = (BixbyDelegate) getDelegate(BixbyDelegate.class);
        if (bixbyDelegate != null) {
            return bixbyDelegate.getAppState();
        }
        return null;
    }

    public ViewerObjectComposite getCurrentViewer() {
        ViewerObjectComposite viewerObjectComposite = this.mCurrentViewer;
        if (viewerObjectComposite == null || viewerObjectComposite.getModel().isRecycled()) {
            return null;
        }
        return this.mCurrentViewer;
    }

    public Rect getCurrentWindowBounds() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return null;
        }
        return activity.getWindowManager().getCurrentWindowMetrics().getBounds();
    }

    public int getLayoutId() {
        return R.layout.fragment_vucontainer_layout;
    }

    public String getScreenId() {
        String analyticsScreenId = VuAnalytics.getAnalyticsScreenId(this.mBlackboard, getLocationKey(), ((ContainerModel) this.mModel).getCurrentMediaItem(), BottomSheetState.isExpanded((BottomSheetState.StateListener) this.mModel), ((ContainerModel) this.mModel).isOsdVisible(), ((ContainerModel) this.mModel).getStateHelper().isQuickView(), ((ContainerModel) this.mModel).isFlipCoverGallery());
        ViewerTableModeDelegate viewerTableModeDelegate = (ViewerTableModeDelegate) getDelegate(ViewerTableModeDelegate.class);
        if (viewerTableModeDelegate != null) {
            return viewerTableModeDelegate.getAnalyticsScreenId(analyticsScreenId);
        }
        if (LocationKey.isPrivateAlbum(getLocationKey())) {
            return C0212a.A(analyticsScreenId, "_PA");
        }
        return analyticsScreenId;
    }

    public boolean handleExternalTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mActionInvoker.invoke(ViewerAction.ACTION_FROM_EXTERNAL, Boolean.TRUE);
        }
        this.mViewPager.dispatchTouchEvent(motionEvent);
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.mActionInvoker.invoke(ViewerAction.ACTION_FROM_EXTERNAL, Boolean.FALSE);
        }
        return true;
    }

    public void initView(View view) {
        ((MultiWindowMarginDelegate) getDelegate(MultiWindowMarginDelegate.class)).bindView();
        ((TransitionDelegate) getDelegate(getTransitionDelegateClass())).bindView(view);
    }

    public boolean initializeActionBar() {
        return false;
    }

    public boolean isFirstFragment() {
        int i2;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            i2 = activity.getSupportFragmentManager().getBackStackEntryCount();
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public boolean onBackPressed() {
        if (isIgnoredBackPressedCase()) {
            if (((ContainerModel) this.mModel).getStateHelper().isTransitionToList()) {
                if (SharedTransition.isSharedViewTransaction(this.mBlackboard)) {
                    this.mActionInvoker.invoke(ViewerAction.PREPARE_RETURN_TRANSITION, ((ContainerModel) this.mModel).getCurrentMediaItem(), Integer.valueOf(getReturnPosition(getLocationKey())), null);
                    this.mActionInvoker.invoke(ViewerAction.BACK_KEY_PRESSED, new Object[0]);
                } else {
                    this.mIsPendingBackPressed = true;
                }
            }
            return true;
        }
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.BACK_KEY_PRESSED;
        boolean hasExclusive = actionInvoker.hasExclusive(viewerAction);
        if (!isFirstFragment() || hasExclusive) {
            if (isResumed()) {
                this.mActionInvoker.invoke(viewerAction, new Object[0]);
                if (!hasExclusive && ((ContainerModel) this.mModel).getStateHelper().isFromList()) {
                    this.mBlackboard.post("lifecycle://on_exit_from_viewer", (Object) null);
                }
            }
            return true;
        }
        Optional.ofNullable(getActivity()).ifPresent(new l(10));
        return false;
    }

    public void onBindView(View view) {
        bindViewInternal(view);
        super.onBindView(view);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ((VuContainerPresenter) this.mPresenter).onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LeakTracker.resetCheckViewerBitmap();
        setActionInvoker();
        this.mViewPagerDelegate = (ViewPagerDelegate) getDelegate(ViewPagerDelegate.class);
        this.mFilmStripDelegate = (FilmStripDelegate) getDelegate(FilmStripDelegate.class);
        this.mFilmStripSeekerDelegate = (FilmStripSeekerDelegate) getDelegate(FilmStripSeekerDelegate.class);
        if (this.mDataVersion == -1) {
            this.mDataVersion = ((ContainerModel) getModel()).getMediaData().getDataVersion();
        }
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "onCreate : " + this.mDataVersion + ", c=" + ((ContainerModel) getModel()).getMediaData().getCount());
    }

    public Animation onCreateAnimation(int i2, boolean z, int i7) {
        Animation onCreateAnimation = this.mFragmentAnimator.onCreateAnimation(getApplicationContext(), getView(), i7);
        if (onCreateAnimation != null) {
            return onCreateAnimation;
        }
        return super.onCreateAnimation(i2, z, i7);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_CREATE);
        ContentViewHolderFactory.prepareCache(getContext(), viewGroup);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mFilmViewLayout = null;
        this.mFilmViewStub = null;
        this.mViewPagerDelegate = null;
        this.mFilmStripDelegate = null;
        this.mFilmStripSeekerDelegate = null;
        this.mIsPendingBackPressed = false;
        ThreadUtil.removeCallbackOnUiThread(this.mMediaDataUpdated);
        ContentViewHolderFactory.onDestroy();
        DirectorsViewCache.getInstance().clear();
        LeakTracker.checkViewerBitmap(this.mBlackboard);
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        if (this.mCurrentViewer != null) {
            StringBuilder t = C0212a.t(str, " === dump of mCurrentViewer : ");
            t.append(this.mCurrentViewer);
            t.append(" ===");
            Logger.dumpLog(printWriter, t.toString());
            ViewerObjectComposite viewerObjectComposite = this.mCurrentViewer;
            viewerObjectComposite.onDump(printWriter, str + " + ");
        }
    }

    public void onEnterTransitionEndV2() {
        ViewerPerformanceTracker.Offset offset = ViewerPerformanceTracker.Offset.CF_TRANS_END;
        ViewerPerformanceTracker.beginTrace(offset);
        super.onEnterTransitionEndV2();
        this.mActionInvoker.invoke(ViewerAction.ENTER_TRANSITION_END, new Object[0]);
        ViewerPerformanceTracker.endTrace(offset);
    }

    public void onEnterTransitionStartV2() {
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_TRANS_ENTER);
        super.onEnterTransitionStartV2();
        this.mActionInvoker.invoke(ViewerAction.ENTER_TRANSITION_START, new Object[0]);
    }

    public void onMediaDataChanged(int i2, int i7) {
        ThreadUtil.assertUiThread("onMediaDataChanged");
        if (isDestroyed()) {
            printLog(this.TAG.toString(), "onMediaDataChanged : isDestroyed");
        } else if (((ContainerModel) this.mModel).getStateHelper().isQuickViewShrink()) {
            printLog(this.TAG.toString(), "onMediaDataChanged quick view shrink");
        } else {
            FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
            if (filmStripDelegate != null && (!filmStripDelegate.isViewPagerScrollStateIdle() || !this.mFilmStripDelegate.isFilmStripScrollStateIdle())) {
                String stringCompat = this.TAG.toString();
                printLog(stringCompat, "delay onMediaDataUpdated while scrolling : " + this.mFilmStripDelegate.isViewPagerScrollStateIdle() + "/" + this.mFilmStripDelegate.isFilmStripScrollStateIdle());
                if (this.mFilmStripDelegate.isViewPagerScrollStateIdle() && this.mFilmStripDelegate.isFilmStripScrollStateSettling()) {
                    this.mFilmStripDelegate.stopScroll();
                }
                ThreadUtil.postOnUiThreadDelayed(this.mMediaDataUpdated, 100);
            } else if (SeApiCompat.isActivityResumed(getActivity()) && !isResumed()) {
                printLog(this.TAG.toString(), "onMediaDataChanged onActivityResume but fragment onPause");
            } else if (((ContainerModel) getModel()).getMediaData().getCount() == 0) {
                Log.w(this.TAG, "onMediaDataChanged : getMediaData count = 0");
                finish();
            } else {
                int dataVersion = ((ContainerModel) getModel()).getMediaData().getDataVersion();
                if (this.mDataVersion != dataVersion) {
                    String stringCompat2 = this.TAG.toString();
                    printLog(stringCompat2, "onMediaDataChanged : " + this.mDataVersion + ">" + dataVersion);
                    this.mDataVersion = dataVersion;
                    super.onMediaDataChanged(i2, i7);
                    return;
                }
                StringCompat stringCompat3 = this.TAG;
                Log.w(stringCompat3, "onMediaDataChanged unexpected : " + this.mDataVersion);
            }
        }
    }

    public void onMediaDataUpdated(MediaData mediaData) {
        ViewPagerDelegate viewPagerDelegate = this.mViewPagerDelegate;
        if (viewPagerDelegate != null) {
            viewPagerDelegate.updateMediaData(mediaData);
        }
        FilmStripDelegate filmStripDelegate = this.mFilmStripDelegate;
        if (filmStripDelegate != null) {
            filmStripDelegate.updateMediaData(mediaData);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        ((VuContainerPresenter) this.mPresenter).onMultiWindowModeChanged(z);
    }

    public void onPause() {
        super.onPause();
        if (!isActivityResumed() || !isMoveToViewer()) {
            cancelForceRotate();
        }
    }

    public void onPrepareSharedTransitionV2() {
        ((TransitionDelegate) getDelegate(getTransitionDelegateClass())).onPrepareSharedTransitionV2();
    }

    public void onResume() {
        ViewerPerformanceTracker.setTime(ViewerPerformanceTracker.Offset.CF_RESUME);
        this.mBlackboard.erase("data://shrink_active");
        updateMediaDataIfNeeded();
        this.mBackupView.setAlpha(1.0f);
        Blackboard.publishGlobal("command:///RestoreContentViewBackground", (Object) null);
        super.onResume();
    }

    public void onReturnTransitionEndV2() {
        super.onReturnTransitionEndV2();
        this.mActionInvoker.invoke(ViewerAction.RETURN_TRANSITION_END, new Object[0]);
    }

    public void onReturnTransitionStartV2() {
        super.onReturnTransitionStartV2();
        this.mActionInvoker.invoke(ViewerAction.RETURN_TRANSITION_START, new Object[0]);
    }

    public void onTableModeChanged(boolean z, int i2) {
        super.onTableModeChanged(z, i2);
        ((VuContainerPresenter) this.mPresenter).onTableModeChanged(z, i2);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (isViewReady()) {
            Blackboard blackboard = this.mBlackboard;
            blackboard.post("lifecycle://on_view_created" + hashCode(), (Object) null);
        }
        if (((ContainerModel) this.mModel).isFlipCoverGallery()) {
            ViewUtils.setBackgroundResource(getView(), ((ContainerModel) this.mModel).getStateHelper().getBackgroundColorResource());
        }
        updateGradientProtection(true);
        StringCompat stringCompat = this.TAG;
        Log.p(stringCompat, "onViewCreated : " + this.mDataVersion + ", c=" + ((ContainerModel) getModel()).getMediaData().getCount());
    }

    public void printLog(String str, String str2) {
        Log.d(str, str2);
        if (PocFeatures.DEBUG_VIEWER2) {
            Optional.ofNullable(this.mDebugTextView).ifPresent(new e(23, this, str2));
        }
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public void requestForceRotate() {
        this.mSystemUi.requestForceRotate(getActivity());
        postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_SCREEN_ROTATE);
    }

    public void resetDataVersion() {
        this.mDataVersion = -1;
    }

    public void setCurrentViewer(ViewerObjectComposite viewerObjectComposite) {
        this.mCurrentViewer = viewerObjectComposite;
        ((ContainerModel) this.mModel).setContentModel(viewerObjectComposite.getModel());
        executePendingBackPressed();
    }

    public void setCustomAnimations(FragmentTransaction fragmentTransaction, IBaseFragment iBaseFragment) {
        this.mFragmentAnimator.setCustomAnimations(fragmentTransaction, getLocationKey());
    }

    public void setPosition(int i2, boolean z) {
        this.mActionInvoker.invoke(ViewerAction.SCROLL_TO, Integer.valueOf(i2), Boolean.valueOf(z));
    }

    public void setScreenMode() {
        if (!((ContainerModel) this.mModel).isFlipCoverGallery()) {
            setScreenViewerMode();
        }
    }

    public void showNavigationBar() {
        boolean z;
        FragmentActivity activity = getActivity();
        if (activity == null || activity.getResources().getBoolean(R.bool.isNightTheme) || ((ContainerModel) this.mModel).isFlipCoverGallery()) {
            z = false;
        } else {
            z = true;
        }
        super.showNavigationBar(z);
    }

    public void updateBackgroundColor(boolean z) {
        int i2;
        if (((ContainerModel) this.mModel).getStateHelper().isContainerResumed()) {
            View view = getView();
            if (z) {
                i2 = ((ContainerModel) this.mModel).getStateHelper().getBackgroundColorResource();
            } else {
                i2 = R.color.black_color;
            }
            ViewUtils.setBackgroundResource(view, i2);
        }
    }

    public void updateMediaDataIfNeeded() {
        MediaData mediaData = ((ContainerModel) getModel()).getMediaData();
        if (mediaData != null && mediaData.getDataVersion() != this.mDataVersion) {
            Log.d(this.TAG, "update media data");
            ThreadUtil.postOnUiThreadDelayed(this.mMediaDataUpdated, 100);
        }
    }

    public void updateNavigationBar() {
        if (isInMultiWindowMode()) {
            updateGradientProtection(false);
        } else if (!((ContainerModel) this.mModel).isOsdVisible()) {
            hideNavigationBar();
            updateGradientProtection(false);
        } else if (getActivity() != null) {
            showNavigationBar();
            updateGradientProtection(true);
        }
    }

    public VuDelegateComposite createDelegateComposite() {
        return new VuDelegateComposite(new VuDelegateFactory(), this);
    }

    public ContainerModel createModel() {
        return new ContainerModel(this.mSystemUi, this);
    }

    public VuContainerPresenter createPresenter(IVuContainerView iVuContainerView) {
        return new VuContainerPresenter(this.mBlackboard, iVuContainerView);
    }

    public void onApplyWindowInsetModel(View view, WindowInsets windowInsets, ContainerModel containerModel) {
        containerModel.setWindowInsets(windowInsets);
    }

    public void onBindModel(ContainerModel containerModel) {
        containerModel.bindView(this.mPresenter, this.mViewPager, this.mFilmViewStub);
        containerModel.setFilmStripLayout(this.mFilmViewLayout);
    }

    public void onCreateViewModel(ContainerModel containerModel) {
        containerModel.setLocationKey(getLocationKey());
        IBaseFragment callerFragment = getCallerFragment();
        containerModel.enablePinchShrink(callerFragment != null && callerFragment.supportPinchShrink());
    }

    public String getAnalyticsScreenId(String str) {
        return str;
    }
}
