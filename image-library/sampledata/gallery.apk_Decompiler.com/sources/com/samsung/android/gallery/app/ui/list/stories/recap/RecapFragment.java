package com.samsung.android.gallery.app.ui.list.stories.recap;

import A4.C0385u;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.WindowInsets;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.app.ui.list.stories.recap.RecapPresenter;
import com.samsung.android.gallery.app.ui.list.stories.recap.delegate.DelegateFactory;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecapFragment<V extends IRecapView, P extends RecapPresenter<IRecapView>> extends MvpBaseFragment<V, P> implements IRecapView {
    private Delegate mDelegate;
    private final EventHandler mEventHandler = new EventHandler(this);
    private final SharedTransitionHandler mSharedTransition = new SharedTransitionHandler(this);

    private int getReturnPosition() {
        return ((RecapPresenter) this.mPresenter).findHeaderItemPosition();
    }

    private void handlePostEventInternal(Event event, Object... objArr) {
        if (event == Event.FRAGMENT_UPDATE_KEEP_SCREEN_ON) {
            updateKeepScreenOn();
            return;
        }
        boolean z = false;
        if (event == Event.FRAGMENT_NAVIGATION_VISIBLE) {
            if (objArr != null && objArr.length > 0 && objArr[0].booleanValue()) {
                z = true;
            }
            setNavigationBarVisible(z);
        } else if (event == Event.VIDEO_PAUSE) {
            keepScreenOn(false);
        }
    }

    /* access modifiers changed from: private */
    public boolean onTouchToolBar() {
        return false;
    }

    private void updateKeepScreenOn() {
        boolean z;
        if (!isResumed() || this.mEventHandler.isKeepPaused()) {
            z = false;
        } else {
            z = true;
        }
        keepScreenOn(z);
    }

    public EventHandler getEventHandler() {
        return this.mEventHandler;
    }

    public ArrayList<MediaItem> getExportItems() {
        return new ArrayList<>();
    }

    public MediaItem getHeaderItem() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((RecapPresenter) p6).getHeaderItem();
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.recap_fragment_layout;
    }

    public MediaData getMediaData() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((RecapPresenter) p6).getMediaData();
        }
        return null;
    }

    public Options getOptions() {
        return new RecapOptions();
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_RECAP_VIEW.toString();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        this.mDelegate.handleOrientationChange(i2);
    }

    public void handlePostEvent(Event event, Object... objArr) {
        if (!isDestroyed()) {
            handlePostEventInternal(event, objArr);
            ((RecapPresenter) this.mPresenter).handlePostEvent(event, objArr);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Log.d(this.TAG, "handleResolutionChange", Integer.valueOf(i2));
        this.mDelegate.handleResolutionChange(i2);
    }

    public void initView(View view) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mToolbar = galleryToolbar;
        galleryToolbar.setTouchBlocker(new C0385u(4, this));
        this.mDelegate.initView(view);
        if (!this.mSharedTransition.bindView(view)) {
            onEnterTransitionEndV2();
        }
    }

    public boolean isBackPressed() {
        return false;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mDelegate.onApplyWindowInsets(view, windowInsets);
        return windowInsets;
    }

    public void onAttach(Context context) {
        Delegate create = DelegateFactory.create(this);
        this.mDelegate = create;
        this.mEventHandler.attach(create);
        super.onAttach(context);
        this.mSharedTransition.setBlackboard(getBlackboard());
        this.mDelegate.onAttach();
    }

    public boolean onBackPressed() {
        if (this.mDelegate.onBackPressed()) {
            return true;
        }
        this.mSharedTransition.prepareReturn(getHeaderItem(), getReturnPosition());
        return super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    public void onDataChangedOnUi() {
        if (!isDestroyed()) {
            this.mDelegate.onDataChangedOnUi();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mDelegate.onDestroy();
        GalleryToolbar galleryToolbar = this.mToolbar;
        if (galleryToolbar != null) {
            galleryToolbar.setTouchBlocker((BooleanSupplier) null);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mDelegate.onDestroyView();
    }

    public void onEnterTransitionEndV2() {
        Log.d(this.TAG, "onEnterTransitionEndV2");
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_END, new Object[0]);
    }

    public void onEnterTransitionStartV2() {
        Log.d(this.TAG, "onEnterTransitionStartV2");
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_START, new Object[0]);
    }

    public void onPause() {
        super.onPause();
        this.mDelegate.onPause();
        keepScreenOn(false);
    }

    public void onPrepareSharedTransitionV2() {
        if (!getOptions().useDefaultEnterTransition()) {
            this.mSharedTransition.onPrepareSharedTransitionV2(this);
        }
    }

    public void onResume() {
        super.onResume();
        this.mDelegate.onResume();
        updateKeepScreenOn();
    }

    public void postAnalyticsLog() {
        LaunchIntent launchIntent;
        Blackboard blackboard = this.mBlackboard;
        if (blackboard != null) {
            launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
        } else {
            launchIntent = null;
        }
        if (launchIntent == null || !"com.samsung.storyservice".equals(launchIntent.getExtra("caller-package", ""))) {
            super.postAnalyticsLog();
            return;
        }
        postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_LAUNCH_FROM_NOTIFICATION, String.valueOf(launchIntent.getExtra("saType", -1)));
    }

    public void registerWindowInsetListener(List<View> list) {
        list.add(getView());
    }

    public Object requestData(DataRequest dataRequest, Object... objArr) {
        return null;
    }

    public void setNavigationBarVisible(boolean z) {
        if (z) {
            showNavigationBar(false);
        } else {
            hideNavigationBar(false);
        }
    }

    public void setScreenMode() {
        setScreenViewerMode();
        this.mDelegate.setScreenMode();
    }

    public boolean supportEnterDefaultTransition() {
        return getOptions().useDefaultEnterTransition();
    }

    public boolean supportExitDefaultTransition() {
        return getOptions().useDefaultExitTransition();
    }

    public boolean supportPinchShrink() {
        return false;
    }

    public RecapPresenter<IRecapView> createPresenter(IRecapView iRecapView) {
        return new RecapPresenter<>(this.mBlackboard, iRecapView);
    }

    public P getPresenter() {
        return (RecapPresenter) this.mPresenter;
    }
}
