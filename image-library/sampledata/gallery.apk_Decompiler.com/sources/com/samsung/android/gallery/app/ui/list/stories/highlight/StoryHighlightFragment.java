package com.samsung.android.gallery.app.ui.list.stories.highlight;

import A4.C0385u;
import Ad.C0720a;
import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Animation;
import com.samsung.android.gallery.app.ui.abstraction.DefaultEntryAnim;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.stories.highlight.StoryHighlightPresenter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Options;
import com.samsung.android.gallery.app.ui.list.stories.highlight.delegate.ControlDelegate;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.toolbar.GalleryToolbar;
import com.samsung.android.sum.core.descriptor.b;
import com.sec.android.gallery3d.R;
import e6.C0453a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightFragment<V extends IStoryHighlightView, P extends StoryHighlightPresenter> extends MvpBaseFragment<V, P> implements IStoryHighlightView {
    private final Delegate mDelegate;
    private final EventHandler mEventHandler;
    private boolean mInDefaultEntryAnim;
    private boolean mOnBackPressed;
    private final Options mOptions = new StoryHighlightOptions(this);
    private final SharedTransitionHandler mSharedTransition;

    public StoryHighlightFragment() {
        EventHandler eventHandler = new EventHandler(this);
        this.mEventHandler = eventHandler;
        ControlDelegate controlDelegate = new ControlDelegate(this);
        this.mDelegate = controlDelegate;
        eventHandler.attach(controlDelegate);
        this.mSharedTransition = new SharedTransitionHandler(this);
    }

    private int getReturnPosition() {
        return ((StoryHighlightPresenter) this.mPresenter).findHeaderItemPosition();
    }

    private ToolbarUpdater getToolbarUpdater() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryHighlightPresenter) p6).getToolbarUpdater();
        }
        return null;
    }

    private void handlePostEventInternal(Event event, Object... objArr) {
        MediaItem mediaItem;
        boolean z = false;
        if (event == Event.FRAGMENT_HEADER_UPDATE) {
            if (objArr == null || objArr.length <= 0) {
                mediaItem = null;
            } else {
                mediaItem = objArr[0];
            }
            onHeaderUpdated(mediaItem);
        } else if (event == Event.FRAGMENT_NAVIGATION_VISIBLE) {
            if (objArr != null && objArr.length > 0 && objArr[0].booleanValue()) {
                z = true;
            }
            setNavigationBarVisible(z);
        } else if (event == Event.FRAGMENT_RESET_TRANSITION_INFO) {
            this.mSharedTransition.resetTransitionInfo();
        } else if (event == Event.FRAGMENT_UPDATE_LOCATION_KEY) {
            if (objArr != null && objArr.length > 0) {
                setLocationKey(objArr[0]);
            }
        } else if (event == Event.FRAGMENT_STORY_CHANGED) {
            this.mSharedTransition.setTransitionInfoChanged();
            this.mEventHandler.lambda$postEvent$0(Event.RESET_THEME, new Object[0]);
            Log.d(this.TAG, "onStoryChange");
        } else if (event == Event.FRAGMENT_UPDATE_KEEP_SCREEN_ON) {
            updateKeepScreenOn();
        } else if (event == Event.FRAGMENT_TRANSITION_INFO_CHANGED) {
            this.mSharedTransition.setTransitionInfoChanged();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createDefaultEnterAnimation$0(Boolean bool) {
        this.mInDefaultEntryAnim = false;
        this.mEventHandler.lambda$postEvent$0(Event.DEFAULT_ENTRY_ANIM_END, new Object[0]);
    }

    private void onHeaderUpdated(MediaItem mediaItem) {
        Log.d(this.TAG, "onHeaderUpdated", MediaItemUtil.getSimpleLog(mediaItem));
        this.mDelegate.onHeaderUpdated();
        this.mSharedTransition.onHeaderUpdated(mediaItem);
    }

    /* access modifiers changed from: private */
    public boolean onTouchToolbar() {
        if (!isDestroyed()) {
            if (!this.mEventHandler.isBottomSheetHidden()) {
                return true;
            }
            if (this.mEventHandler.isFilterViewVisible() && !this.mEventHandler.isBgmPickerVisible() && ((StoryHighlightPresenter) this.mPresenter).setInputBlock("Story_TouchToolbar", 500)) {
                this.mEventHandler.postEvent(Event.HIDE_THEME_VIEW, new Object[0]);
                return true;
            }
        }
        return false;
    }

    private void updateKeepScreenOn() {
        boolean z;
        if (!isResumed() || !this.mEventHandler.isBottomSheetHidden() || this.mEventHandler.isKeepPaused()) {
            z = false;
        } else {
            z = true;
        }
        keepScreenOn(z);
    }

    public Animation createDefaultEnterAnimation(Context context, int i2) {
        DefaultEntryAnim defaultEntryAnim = new DefaultEntryAnim();
        this.mInDefaultEntryAnim = true;
        defaultEntryAnim.setAnimationEndListener(new b(29, this));
        return defaultEntryAnim.createAnimation(context, i2);
    }

    public EventHandler getEventHandler() {
        return this.mEventHandler;
    }

    public ArrayList<MediaItem> getExportItems() {
        MediaItem[] mediaItemArr;
        P p6 = this.mPresenter;
        if (p6 != null) {
            mediaItemArr = ((StoryHighlightPresenter) p6).getAllItems();
        } else {
            mediaItemArr = new MediaItem[0];
        }
        return (ArrayList) Arrays.stream(mediaItemArr).collect(Collectors.toCollection(new C0720a(1)));
    }

    public MediaItem getHeaderItem() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryHighlightPresenter) p6).getHeaderItem();
        }
        return null;
    }

    public int getLayoutId() {
        return R.layout.fragment_story_highlight_layout;
    }

    public MediaData getMediaData() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryHighlightPresenter) p6).getMediaData();
        }
        return null;
    }

    public Options getOptions() {
        return this.mOptions;
    }

    public String getScreenId() {
        if (getOptions().isOnDemandStory()) {
            return AnalyticsScreenId.SCREEN_ONDEMAND_STORY_HIGHLIGHT_VIEW.toString();
        }
        if (this.mEventHandler.isBgmPickerVisible()) {
            return AnalyticsScreenId.SCREEN_STORY_HIGHLIGHT_BGM_PICKER.toString();
        }
        return AnalyticsScreenId.SCREEN_EVENT_VIEW_SLIDE_SHOW.toString();
    }

    public MediaData getStoriesData() {
        P p6 = this.mPresenter;
        if (p6 != null) {
            return ((StoryHighlightPresenter) p6).getStoriesData();
        }
        return null;
    }

    public GalleryToolbar getToolbar() {
        return this.mToolbar;
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        Log.d(this.TAG, "handleDensityChange", Integer.valueOf(i2));
        this.mDelegate.handleDensityChange(i2);
        this.mSharedTransition.handleDensityChange();
    }

    public void handleOrientationChange(int i2) {
        super.handleOrientationChange(i2);
        this.mDelegate.handleOrientationChange(i2);
        this.mSharedTransition.handleOrientationChange();
    }

    public void handlePostEvent(Event event, Object... objArr) {
        if (!isDestroyed()) {
            handlePostEventInternal(event, objArr);
            ((StoryHighlightPresenter) this.mPresenter).handlePostEvent(event, objArr);
        }
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Log.d(this.TAG, "handleResolutionChange", Integer.valueOf(i2));
        this.mDelegate.handleResolutionChange(i2);
        Optional.ofNullable(getToolbarUpdater()).ifPresent(new C0453a(15));
        this.mSharedTransition.handleResolutionChange();
    }

    public void initView(View view) {
        GalleryToolbar galleryToolbar = (GalleryToolbar) view.findViewById(R.id.toolbar);
        this.mToolbar = galleryToolbar;
        galleryToolbar.setTouchBlocker(new C0385u(17, this));
        this.mDelegate.initView(view);
        Optional.ofNullable(getToolbarUpdater()).ifPresent(new C0453a(16));
        if (!this.mSharedTransition.prepareEnter(view, new int[]{0, 0, 0, 0}) || getOptions().useDefaultEnterTransition()) {
            onEnterTransitionEndV2();
        }
    }

    public boolean isBackPressed() {
        return this.mOnBackPressed;
    }

    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        this.mDelegate.onApplyWindowInsets(view, windowInsets);
        return windowInsets;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mSharedTransition.setBlackboard(getBlackboard());
        this.mDelegate.onAttach();
    }

    public boolean onBackPressed() {
        if (!getOptions().useDefaultEnterTransition() && !SharedTransition.isEnterTransitionFinished(this.mBlackboard)) {
            Log.e(this.TAG, "onBackPressed skip. transition not finished");
            return true;
        } else if (this.mDelegate.onBackPressed()) {
            return true;
        } else {
            this.mSharedTransition.prepareReturn(getHeaderItem(), getReturnPosition());
            this.mOnBackPressed = true;
            return super.onBackPressed();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mDelegate.onConfigurationChanged(configuration);
    }

    public void onDataChangedOnUi() {
        if (!isDestroyed()) {
            this.mDelegate.onDataChangedOnUi();
            this.mSharedTransition.onDataChangedOneUi();
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
        this.mSharedTransition.onEnterTransitionEndV2();
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_END, new Object[0]);
    }

    public void onEnterTransitionStartV2() {
        Log.d(this.TAG, "onEnterTransitionStartV2");
        this.mSharedTransition.onEnterTransitionStartV2();
        this.mEventHandler.lambda$postEvent$0(Event.ENTER_TRANSITION_START, new Object[0]);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return this.mDelegate.onKeyEvent(i2, keyEvent);
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        return this.mDelegate.onKeyEvent(i2, keyEvent);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        this.mDelegate.onMultiWindowModeChanged(z);
        Optional.ofNullable(getToolbarUpdater()).ifPresent(new C0453a(14));
    }

    public void onPause() {
        super.onPause();
        this.mDelegate.onPause();
        keepScreenOn(false);
    }

    public void onPrepareSharedTransitionV2() {
        Log.d(this.TAG, "onPrepareSharedTransitionV2", Boolean.valueOf(!getOptions().useDefaultEnterTransition()));
        if (!getOptions().useDefaultEnterTransition()) {
            this.mSharedTransition.onPrepareSharedTransitionV2(this);
        }
    }

    public void onResume() {
        super.onResume();
        this.mDelegate.onResume();
        updateKeepScreenOn();
    }

    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onTrimMemory=" + i2);
        this.mDelegate.onTrimMemory(i2);
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
        if (dataRequest == DataRequest.FRAGMENT_REQ_STORY_ALBUM_BY_ID) {
            if (objArr == null || objArr.length <= 0) {
                return null;
            }
            P p6 = this.mPresenter;
            if (p6 != null) {
                return ((StoryHighlightPresenter) p6).getStoryAlbumById(objArr[0].intValue());
            }
            return -1;
        } else if (dataRequest == DataRequest.FRAGMENT_REQ_ONGOING_ENTRY_ANIM) {
            return Boolean.valueOf(this.mInDefaultEntryAnim);
        } else {
            return null;
        }
    }

    public void setNavigationBarVisible(boolean z) {
        if (z) {
            if (isDexMode()) {
                showNavigationBar();
            } else {
                showNavigationBar(false);
            }
        } else if (!isDexMode()) {
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

    public boolean supportExitPredictiveBack() {
        return false;
    }

    public boolean supportPinchShrink() {
        return PreferenceFeatures.OneUi8x.STORY_ONE_UI_85;
    }

    public StoryHighlightPresenter createPresenter(IStoryHighlightView iStoryHighlightView) {
        return new StoryHighlightPresenter(this.mBlackboard, iStoryHighlightView);
    }

    public P getPresenter() {
        return (StoryHighlightPresenter) this.mPresenter;
    }
}
