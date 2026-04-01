package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A4.C0385u;
import M6.a;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.DataUpdateChecker;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPager;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerAdapter;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.StoryHighlightViewPagerV2;
import com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager.ViewPagerHolder;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.livemotion.DurationMeasure;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerScrolledValues;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import o6.A;
import o6.B;
import o6.t;
import o6.z;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerDelegate extends Delegate implements ViewPagerCallback {
    private StoryHighlightViewPagerAdapter mAdapter;
    private ViewGroup mContainer;
    private DurationMeasure mDurationMeasure;
    private boolean mIsOsdHiddenByZoomIn;
    private boolean mIsStoryChanged;
    private Boolean mIsSwipeMode;
    private boolean mIsTransitionEnd = false;
    private KeyboardDelegate mKeyboardDelegate;
    private final DataUpdateChecker mUpdateChecker = createDataUpdaterChecker();
    private StoryHighlightViewPager mViewPager;

    public ViewPagerDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    private StoryHighlightViewPager createViewPager(ViewGroup viewGroup) {
        return new StoryHighlightViewPagerV2(viewGroup, this.mDurationMeasure);
    }

    /* access modifiers changed from: private */
    public boolean hasFocus() {
        StoryHighlightViewPager storyHighlightViewPager = this.mViewPager;
        if (storyHighlightViewPager == null || !storyHighlightViewPager.hasFocus() || this.mEventHandler.isShowingRelatedView() || ((Boolean) this.mEventHandler.requestData(DataRequest.THEME_VIEW_VISIBLE, Boolean.FALSE)).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isDataReady() {
        if (this.mView.getMediaData() == null || this.mView.getMediaData().getRealCount() <= 0) {
            return false;
        }
        return true;
    }

    private boolean isDiffDataCount(MediaData mediaData) {
        StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter;
        if (mediaData == null || (storyHighlightViewPagerAdapter = this.mAdapter) == null || storyHighlightViewPagerAdapter.getItemCount() != mediaData.getRealCount()) {
            return true;
        }
        return false;
    }

    private boolean isViewReady() {
        if (this.mViewPager == null || this.mAdapter == null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        this.mViewPager.pause();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$2(Object[] objArr) {
        this.mViewPager.resume();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$3(Object[] objArr) {
        this.mViewPager.changeTheme(this.mEventHandler.getEffectTheme());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$4(Object[] objArr) {
        this.mViewPager.setAllowTouch(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$5(Object[] objArr) {
        onThemeViewVisibilityChanged(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$6(Object[] objArr) {
        onThemeViewVisibilityChanged(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$10(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mViewPager.isActiveZoom());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$11(DataRequest dataRequest, Object[] objArr) {
        return this.mViewPager.getCurrentPageTransform();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$12(DataRequest dataRequest, Object[] objArr) {
        int i2;
        if (objArr == null || objArr.length <= 0) {
            i2 = -1;
        } else {
            i2 = objArr[0].intValue();
        }
        return this.mViewPager.getTransformBuilder().getKenBurnsInfo(this.mView.getMediaData(), i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$7(DataRequest dataRequest, Object[] objArr) {
        return getCurrentViewHolder();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$8(DataRequest dataRequest, Object[] objArr) {
        return Boolean.valueOf(this.mViewPager.isPlaying());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Object lambda$addRequestProvider$9(DataRequest dataRequest, Object[] objArr) {
        return Integer.valueOf(this.mViewPager.getCurrentItem());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(RecyclerView.ViewHolder viewHolder) {
        requestPreviewPlay(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveToHighlight$14(int i2, boolean z) {
        this.mViewPager.onEscapeEndPosition(i2, z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThemeInitialized$15(StoryHighlightViewPager storyHighlightViewPager) {
        storyHighlightViewPager.resetTheme(this.mEventHandler.getEffectTheme());
    }

    /* access modifiers changed from: private */
    public void moveToHighlight(Object... objArr) {
        boolean z = false;
        Integer num = objArr[0];
        int intValue = num.intValue();
        if (intValue >= 0) {
            this.mEventHandler.lambda$postEvent$0(Event.FILM_STRIP_CENTER_CHANGED, num);
        }
        if (objArr.length > 1 && objArr[1].booleanValue()) {
            z = true;
        }
        ThreadUtil.postOnUiThreadDelayed(new a(this, intValue, z, 3), 100);
    }

    private void notifyOnViewIn(int i2) {
        Optional.ofNullable((ViewPagerHolder) this.mViewPager.getViewHolder(i2)).ifPresent(new B(0));
        Optional.ofNullable((ViewPagerHolder) this.mViewPager.getViewHolder(i2 - 1)).ifPresent(new B(1));
        Optional.ofNullable((ViewPagerHolder) this.mViewPager.getViewHolder(i2 + 1)).ifPresent(new B(1));
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        Event event;
        boolean z;
        int i2;
        boolean isHidden = SheetBehaviorCompat.isHidden(objArr[0].intValue());
        EventHandler eventHandler = this.mEventHandler;
        if (!isHidden || eventHandler.isFilterViewVisible() || this.mEventHandler.isBgmPickerVisible()) {
            event = Event.PLAYER_PAUSE;
        } else {
            event = Event.PLAYER_RESUME;
        }
        eventHandler.lambda$postEvent$0(event, new Object[0]);
        StoryHighlightViewPager storyHighlightViewPager = this.mViewPager;
        if (this.mEventHandler.isShowingRelatedView() || !isHidden) {
            z = false;
        } else {
            z = true;
        }
        storyHighlightViewPager.setAllowTouch(z);
        if (!this.mEventHandler.isShowingRelatedView() && isHidden && this.mViewPager.isDoneEndAnimation()) {
            this.mEventHandler.lambda$postEvent$0(Event.SLIDE_SHOW_DONE, new Object[0]);
        }
        ViewGroup viewGroup = this.mContainer;
        if (isHidden) {
            i2 = 131072;
        } else {
            i2 = 393216;
        }
        viewGroup.setDescendantFocusability(i2);
    }

    /* access modifiers changed from: private */
    public void onFilmCenterChanged(Object... objArr) {
        int intValue = objArr[0].intValue();
        this.mAdapter.updateLastBoundBitmap();
        this.mViewPager.onEscapeEndPosition(intValue, false);
        this.mViewPager.scrollToPosition(intValue, false);
    }

    /* access modifiers changed from: private */
    public void onFilterChanged(Object... objArr) {
        this.mAdapter.onFilterChanged();
        StoryHighlightViewPagerAdapter storyHighlightViewPagerAdapter = this.mAdapter;
        storyHighlightViewPagerAdapter.notifyItemRangeChanged(0, storyHighlightViewPagerAdapter.getItemCount(), "PAYLOAD_FILTER");
    }

    /* access modifiers changed from: private */
    public void onKeepPaused(Object... objArr) {
        this.mViewPager.setKeepPause(objArr[0].booleanValue());
        this.mAdapter.keepPause(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onRelatedViewStateChanged(Object... objArr) {
        this.mViewPager.setAllowTouch(!objArr[0].booleanValue());
        this.mViewPager.restoreZoom();
    }

    /* access modifiers changed from: private */
    public void onStoryChanged(Object... objArr) {
        this.mIsStoryChanged = true;
        this.mAdapter.clearFocusedPositionHolder();
    }

    /* access modifiers changed from: private */
    public void onThemeInitialized(Object... objArr) {
        Optional.ofNullable(this.mViewPager).ifPresent(new A(this, 13));
    }

    private void onThemeViewVisibilityChanged(boolean z) {
        Event event;
        StoryHighlightViewPager storyHighlightViewPager = this.mViewPager;
        if (storyHighlightViewPager != null && !storyHighlightViewPager.isActiveZoom()) {
            EventHandler eventHandler = this.mEventHandler;
            if (z) {
                event = Event.PLAYER_PAUSE;
            } else {
                event = Event.PLAYER_RESUME;
            }
            eventHandler.postEvent(event, new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void onTransitionEnd(Object... objArr) {
        if (this.mViewPager != null) {
            ThreadUtil.postOnUiThreadDelayed(new t(3, this), 300);
            this.mViewPager.setAllowTouch(true);
            this.mViewPager.resume();
            this.mIsTransitionEnd = true;
        }
    }

    private void refreshAll() {
        this.mViewPager.stop();
        this.mAdapter.setData(this.mView.getMediaData());
        this.mAdapter.updateHeaderData();
        if (this.mIsStoryChanged) {
            this.mViewPager.setCurrentItem(0);
            this.mIsStoryChanged = false;
        } else {
            this.mViewPager.moveFocusPosition();
        }
        if (this.mIsTransitionEnd) {
            this.mViewPager.start();
        }
        requestPreviewPlay(true);
    }

    private void requestPreviewPlay(boolean z) {
        this.mEventHandler.lambda$postEvent$0(Event.START_VIDEO_PREVIEW, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void setVisible(Object... objArr) {
        this.mViewPager.setVisibility(0, 0);
    }

    private void updateData() {
        if (isViewReady() && isDataReady()) {
            if (this.mIsStoryChanged) {
                this.mUpdateChecker.reset();
                this.mViewPager.onStoryChanged();
            }
            if (this.mUpdateChecker.handleDataChanged(this.mView.getMediaData(), this.mAdapter)) {
                boolean isDiffDataCount = isDiffDataCount(this.mView.getMediaData());
                this.mAdapter.invalidateData();
                if (isDiffDataCount) {
                    this.mViewPager.start();
                }
            } else {
                refreshAll();
            }
            this.mViewPager.onDataUpdated();
        }
    }

    public void addListenEvent() {
        addEvent(Event.FILM_STRIP_CENTER_CHANGED, new A(this, 14));
        addEvent(Event.ENTER_TRANSITION_END, new A(this, 3));
        addEvent(Event.ENTER_TRANSITION_POST_END, new A(this, 4));
        addEvent(Event.PLAYER_KEEP_PAUSE, new A(this, 5));
        addEvent(Event.PLAYER_PAUSE, new A(this, 6));
        addEvent(Event.PLAYER_RESUME, new A(this, 7));
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED, new A(this, 8));
        addEvent(Event.CHANGE_STORY, new A(this, 9));
        addEvent(Event.MOVE_TO_HIGHLIGHT, new A(this, 10));
        addEvent(Event.RELATED_VIEW_STATE_CHANGED, new A(this, 11));
        addEvent(Event.ON_FILTER_CHANGED, new A(this, 15));
        addEvent(Event.ON_THEME_CHANGED, new A(this, 16));
        addEvent(Event.BGM_PICKER_STATE_CHANGED, new A(this, 17));
        addEvent(Event.ON_THEME_INITIALIZED, new A(this, 0));
        addEvent(Event.SHOW_THEME_OPTION, new A(this, 1));
        addEvent(Event.HIDE_THEME_OPTION, new A(this, 2));
    }

    public void addRequestProvider() {
        addRequestProvider(DataRequest.CURRENT_VIEW_HOLDER, new z(this, 0));
        addRequestProvider(DataRequest.IS_VIEW_PAGER_PLAYING, new z(this, 1));
        addRequestProvider(DataRequest.VIEW_PAGER_CURRENT, new z(this, 2));
        addRequestProvider(DataRequest.IS_ACTIVE_ZOOM, new z(this, 3));
        addRequestProvider(DataRequest.VIEW_PAGER_CURRENT_TRANSFORM, new z(this, 4));
        addRequestProvider(DataRequest.VIEW_PAGER_ENCODING_INFO, new z(this, 5));
    }

    public DataUpdateChecker createDataUpdaterChecker() {
        return new DataUpdateChecker();
    }

    public ViewPagerHolder getCurrentViewHolder() {
        return (ViewPagerHolder) this.mViewPager.getPreviewableViewHolder();
    }

    public void handleResolutionChange(int i2) {
        this.mViewPager.handleResolutionChange(i2);
    }

    public void initView(View view) {
        this.mContainer = (ViewGroup) view.findViewById(R.id.cover_viewpager_container);
        this.mDurationMeasure = this.mEventHandler.getDurationMeasure();
        this.mViewPager = createViewPager((ViewGroup) view.findViewById(R.id.viewpager_layout));
        this.mAdapter = new StoryHighlightViewPagerAdapter(this.mView);
        this.mViewPager.setPreviewListener(new A(this, 12));
        this.mViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setSupportEndEffect(supportEndEffect());
        this.mViewPager.setSupportFaceCircle(supportFaceCircle());
        if (isDataReady()) {
            this.mAdapter.setData(this.mView.getMediaData());
        }
        this.mKeyboardDelegate = new KeyboardDelegate(this.mView, this.mViewPager, new C0385u(23, this));
        this.mViewPager.changeTheme(this.mEventHandler.getEffectTheme());
        this.mViewPager.setViewPagerCallback(this);
    }

    public boolean isPlayable() {
        if (!this.mView.isViewResumed() || !this.mEventHandler.isBottomSheetHidden() || this.mEventHandler.isFilterViewVisible()) {
            return false;
        }
        return true;
    }

    public void onClick() {
        Log.d(this.TAG, "onClick");
        this.mEventHandler.postEvent(Event.TOGGLE_OSD, new Object[0]);
    }

    public void onDataChangedOnUi() {
        updateData();
    }

    public void onDestroy() {
        this.mViewPager.setViewPagerCallback((ViewPagerCallback) null);
        this.mViewPager.destroy();
        this.mAdapter.destroy();
    }

    public void onDestroyView() {
        this.mViewPager.stop();
    }

    public boolean onKeyEvent(int i2, KeyEvent keyEvent) {
        KeyboardDelegate keyboardDelegate = this.mKeyboardDelegate;
        if (keyboardDelegate == null || !keyboardDelegate.onKeyEvent(i2, keyEvent)) {
            return false;
        }
        return true;
    }

    public void onPageScrolled(ViewPagerScrolledValues viewPagerScrolledValues) {
        this.mEventHandler.lambda$postEvent$0(Event.VIEW_PAGER_SCROLLED, viewPagerScrolledValues);
        Boolean bool = this.mIsSwipeMode;
        if (bool == null || bool.booleanValue() != viewPagerScrolledValues.isSwipeMode()) {
            this.mEventHandler.lambda$postEvent$0(Event.VIEW_PAGER_SWIPE_MODE_CHANGED, Boolean.valueOf(viewPagerScrolledValues.isSwipeMode()));
        }
        this.mIsSwipeMode = Boolean.valueOf(viewPagerScrolledValues.isSwipeMode());
    }

    public void onPageSelected(int i2, int i7) {
        this.mEventHandler.lambda$postEvent$0(Event.VIEW_PAGER_SELECTED, Integer.valueOf(i2), Integer.valueOf(i7));
        this.mAdapter.validateOriginalImage(i2, this.mViewPager.getViewHolder(i2));
        if (PreferenceFeatures.OneUi8x.STORY_AI_EDIT_VI) {
            notifyOnViewIn(i2);
        }
    }

    public void onPause() {
        super.onPause();
        this.mViewPager.pause();
    }

    public boolean onPreClick() {
        if (!this.mEventHandler.isFilterViewVisible()) {
            return false;
        }
        this.mEventHandler.lambda$postEvent$0(Event.HIDE_THEME_VIEW, new Object[0]);
        Log.d(this.TAG, "onPreClick - hide theme view");
        return true;
    }

    public void onPreSlideShowDone() {
        this.mEventHandler.lambda$postEvent$0(Event.UPDATE_DECO_UI_VISIBILITY, Boolean.FALSE);
    }

    public void onResume() {
        super.onResume();
        if (this.mIsTransitionEnd) {
            this.mViewPager.start();
        }
    }

    public void onSlideShowDone() {
        Log.d(this.TAG, "onSlideShowDone");
        this.mEventHandler.lambda$postEvent$0(Event.SLIDE_SHOW_DONE, new Object[0]);
    }

    public void onTrimMemory(int i2) {
        this.mAdapter.onTrimMemory(i2);
    }

    public void onViewPagerIdleState() {
        this.mEventHandler.lambda$postEvent$0(Event.VIEW_PAGER_IDLE, new Object[0]);
    }

    public void onZoomState(boolean z) {
        this.mIsSwipeMode = Boolean.valueOf(this.mViewPager.isSwipeMode());
        if (z && this.mEventHandler.isShowingBottomDecoView()) {
            this.mEventHandler.lambda$postEvent$0(Event.ON_OFF_OSD, Boolean.FALSE);
            this.mIsOsdHiddenByZoomIn = true;
        } else if (this.mIsOsdHiddenByZoomIn) {
            this.mEventHandler.lambda$postEvent$0(Event.ON_OFF_OSD, Boolean.TRUE);
            this.mIsOsdHiddenByZoomIn = false;
        }
        Log.d(this.TAG, "onZoomState", Boolean.valueOf(z), this.mIsSwipeMode);
        this.mEventHandler.lambda$postEvent$0(Event.VIEW_PAGER_SWIPE_MODE_CHANGED, this.mIsSwipeMode);
    }

    public boolean supportEndEffect() {
        return true;
    }

    public boolean supportFaceCircle() {
        return false;
    }
}
