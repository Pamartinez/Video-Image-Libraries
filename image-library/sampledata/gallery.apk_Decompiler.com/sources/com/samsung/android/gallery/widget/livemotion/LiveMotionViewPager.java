package com.samsung.android.gallery.widget.livemotion;

import A.a;
import A4.C0368c;
import A4.J;
import I4.b;
import Lb.c;
import Lb.d;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.story.EffectTheme;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.LiveMotionTouchDelegate;
import com.samsung.android.gallery.widget.livemotion.abstraction.IDuration;
import com.samsung.android.gallery.widget.livemotion.abstraction.ILiveMotionAdapter;
import com.samsung.android.gallery.widget.livemotion.abstraction.TransformBuilder;
import com.samsung.android.gallery.widget.livemotion.abstraction.TransformListener;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerCallback;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerScrolledValues;
import com.samsung.android.gallery.widget.livemotion.transform.PageTransform;
import com.samsung.android.gallery.widget.livemotion.zoom.ZoomDelegate;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LiveMotionViewPager extends ViewPager2.OnPageChangeCallback implements TransformListener, LiveMotionTouchDelegate.OnTouchEventListener {
    /* access modifiers changed from: protected */
    public final String TAG = getClass().getSimpleName();
    protected ILiveMotionAdapter mAdapter;
    /* access modifiers changed from: protected */
    public ViewPagerCallback mCallback;
    /* access modifiers changed from: protected */
    public final IDuration mDurationMeasure;
    private final EdgeEffectHandler mEdgeEffectHandler = new EdgeEffectHandler();
    private final ScheduleTimer mIntervalTimer = new ScheduleTimer();
    private boolean mIsAllowTouchCheck = false;
    protected boolean mKeepPause;
    private PeopleDataHelper mPeopleDataHelper;
    private Consumer<RecyclerView.ViewHolder> mPreviewListener;
    private LiveMotionTouchDelegate mTouchDelegate;
    private TransformBuilder mTransformBuilder;
    protected LiveMotionPageTransformer mTransformer;
    /* access modifiers changed from: protected */
    public ViewPager2 mViewPager;
    private final ViewPagerContainer mViewpagerRoot;
    protected final ZoomDelegate mZoomDelegate;

    public LiveMotionViewPager(ViewGroup viewGroup, IDuration iDuration) {
        this.mViewpagerRoot = (ViewPagerContainer) viewGroup;
        this.mZoomDelegate = new ZoomDelegate();
        this.mDurationMeasure = iDuration;
        this.mTransformBuilder = createTransformBuilder();
        initialize();
    }

    private ViewPagerScrolledValues createScrolledValues(int i2, float f) {
        return new ViewPagerScrolledValues(i2, f, this.mTransformer.isPageInOutStarted(), this.mTransformer.isSwipeMode(), this.mTransformer.getInterpolator());
    }

    private ListViewHolder getItemView(ViewPager2 viewPager2, int i2) {
        try {
            return (ListViewHolder) ((RecyclerView) viewPager2.getChildAt(0)).findViewHolderForLayoutPosition(i2);
        } catch (Exception e) {
            a.s(e, new StringBuilder("fail to find itemView="), this.TAG);
            return null;
        }
    }

    private PeopleDataHelper getPeopleDataHelper() {
        if (this.mPeopleDataHelper == null) {
            this.mPeopleDataHelper = new PeopleDataHelper();
        }
        return this.mPeopleDataHelper;
    }

    private boolean isPlayPaused() {
        return this.mTransformer.isPaused();
    }

    private void isTouchUpOrCancelInternal() {
        ThreadUtil.postOnUiThreadDelayed(new c(this, 0), 300);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isTouchUpOrCancel$4(Boolean bool) {
        onCompleteRestoreZoom();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isTouchUpOrCancelInternal$8() {
        LiveMotionTouchDelegate liveMotionTouchDelegate = this.mTouchDelegate;
        if (liveMotionTouchDelegate != null && !liveMotionTouchDelegate.isInTouchMode() && isSwipeMode()) {
            if (!isScrollIdle(this.mViewPager.getScrollState()) && this.mViewPager.isFakeDragging()) {
                Log.d(this.TAG, "no action -> stop fakeDragging");
                this.mViewPager.endFakeDrag();
            } else if (isScrollIdle(this.mViewPager.getScrollState())) {
                Log.d(this.TAG, "no action -> start");
                this.mTransformer.switchMode(true);
                start(50);
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onCompleteRestoreZoom$7() {
        this.mTransformer.switchMode(true);
        Optional.ofNullable(this.mCallback).ifPresent(new b(20));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageScrolled$1(int i2, float f, ViewPagerCallback viewPagerCallback) {
        viewPagerCallback.onPageScrolled(createScrolledValues(i2, f));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageSelectedInternal$0(int i2, ViewPagerCallback viewPagerCallback) {
        viewPagerCallback.onPageSelected(i2, this.mViewPager.getScrollState());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$setDispatchTouchInterceptor$2(MotionEvent motionEvent) {
        if (!isAllowTouch()) {
            return true;
        }
        LiveMotionTouchDelegate liveMotionTouchDelegate = this.mTouchDelegate;
        if (liveMotionTouchDelegate == null || !liveMotionTouchDelegate.onInterceptTouchEvent(motionEvent)) {
            return this.mViewPager.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListAccessibilityDelegate$10(RecyclerView recyclerView) {
        recyclerView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
                ViewPager2 viewPager2;
                if (accessibilityEvent.getEventType() != 4096 || (viewPager2 = LiveMotionViewPager.this.mViewPager) == null || !viewPager2.isFakeDragging()) {
                    super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setVisibility$9(int i2) {
        ViewUtils.setVisibility(this.mViewPager, i2);
    }

    private void onCompleteRestoreZoom() {
        isTouchUpOrCancelInternal();
        Optional.ofNullable(this.mCallback).ifPresent(new b(22));
        if (this.mKeepPause) {
            ThreadUtil.postOnUiThreadDelayed(new c(this, 1), 300);
        }
    }

    private void printMoveLog(String str) {
        int currentItem = this.mViewPager.getCurrentItem();
        int nextDataPosition = getNextDataPosition();
        String str2 = this.TAG;
        Log.d(str2, str + " View{" + currentItem + "->" + (currentItem + 1) + "},Data{" + getCurrentDataPosition() + "->" + nextDataPosition + "} " + MediaItemUtil.getSimpleLog(getAdapter().getMediaItem(nextDataPosition)));
    }

    private void requestPreview() {
        Consumer<RecyclerView.ViewHolder> consumer = this.mPreviewListener;
        if (consumer != null) {
            consumer.accept(getPreviewableViewHolder());
        }
    }

    private void resumePlay() {
        this.mTransformer.resume();
    }

    /* access modifiers changed from: private */
    public Boolean schedule(Integer num) {
        if (getAdapter().prepareNext(getNextDataPosition())) {
            if (isScrollIdle(this.mViewPager.getScrollState())) {
                transitionNext();
            }
            return Boolean.TRUE;
        }
        this.mViewPager.endFakeDrag();
        stop();
        onEndPosition();
        return Boolean.FALSE;
    }

    private void setDispatchTouchInterceptor(Context context) {
        this.mTouchDelegate = new LiveMotionTouchDelegate(context, this);
        this.mViewpagerRoot.setDispatchTouchInterceptor(new K4.a(7, this));
    }

    private void setListAccessibilityDelegate() {
        Optional.ofNullable(getRecyclerView()).ifPresent(new Lb.b(this, 1));
    }

    private void setPageTransformer(LiveMotionPageTransformer liveMotionPageTransformer) {
        if (liveMotionPageTransformer != null) {
            this.mTransformer = liveMotionPageTransformer;
            liveMotionPageTransformer.setTransformListener(this);
            this.mViewPager.setPageTransformer(liveMotionPageTransformer);
        }
    }

    private void setPeopleData(TransformBuilder transformBuilder) {
        int i2;
        if (supportFaceCircle()) {
            PeopleDataHelper peopleDataHelper = getPeopleDataHelper();
            int nextDataPosition = getNextDataPosition();
            peopleDataHelper.prepare(getAdapter().getMediaItem(Math.min(nextDataPosition + 1, getAdapter().getItemCount() - 1)));
            MediaItem mediaItem = getAdapter().getMediaItem(nextDataPosition);
            PeopleData find = peopleDataHelper.find(this.mViewPager, mediaItem);
            if (mediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = mediaItem.getOrientation();
            }
            transformBuilder.setPeopleData(find, i2);
        }
    }

    private void transitionNext() {
        if (getNextDataPosition() >= 0) {
            TransformBuilder duration = getTransformBuilder().setDuration(getDuration());
            setPeopleData(duration);
            this.mTransformer.setTransform(duration).transform(this.mViewPager);
            printMoveLog("transitionNext");
            return;
        }
        Log.w(this.TAG, "invalid dataPosition");
    }

    public void changeTheme(EffectTheme effectTheme) {
        this.mTransformBuilder.changeTheme(effectTheme);
    }

    public LiveMotionPageTransformer createPageTransformer() {
        return new LiveMotionPageTransformer(this.mViewPager.getContext());
    }

    public TransformBuilder createTransformBuilder() {
        return new ViewPagerTransformBuilder(this.mDurationMeasure);
    }

    public void destroy() {
        stop();
        ViewUtils.removeSelf(this.mViewPager);
        this.mViewPager.unregisterOnPageChangeCallback(this);
        this.mIntervalTimer.setScheduleListener((Function<Integer, Boolean>) null);
        this.mTransformer.setTransformListener((TransformListener) null);
        getAdapter().release();
    }

    public abstract ILiveMotionAdapter getAdapter();

    public final int getCurrentDataPosition() {
        return getAdapter().getDataPosition(this.mViewPager.getCurrentItem());
    }

    public int getCurrentItem() {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            return viewPager2.getCurrentItem();
        }
        return 0;
    }

    public final MediaItem getCurrentMediaItem() {
        return getAdapter().getMediaItem(this.mViewPager.getCurrentItem());
    }

    public PageTransform getCurrentPageTransform() {
        return this.mTransformer.getCurrentPageTransform();
    }

    public int getDuration() {
        return this.mDurationMeasure.getDuration(getCurrentMediaItem());
    }

    public abstract int getInitialPosition();

    public final int getNextDataPosition() {
        return getAdapter().getDataPosition(this.mViewPager.getCurrentItem() + 1);
    }

    public RecyclerView.ViewHolder getPreviewableViewHolder() {
        if (this.mTransformer.isPageInOutStarted()) {
            return getViewHolder(this.mViewPager.getCurrentItem() + 1);
        }
        return getViewHolder(this.mViewPager.getCurrentItem());
    }

    public RecyclerView getRecyclerView() {
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            return (RecyclerView) viewPager2.getChildAt(0);
        }
        return null;
    }

    public TransformBuilder getTransformBuilder() {
        return this.mTransformBuilder;
    }

    public RecyclerView.ViewHolder getViewHolder(int i2) {
        RecyclerView recyclerView = (RecyclerView) this.mViewPager.getChildAt(0);
        if (recyclerView != null) {
            return recyclerView.findViewHolderForLayoutPosition(i2);
        }
        return null;
    }

    public void handleResolutionChange(int i2) {
        stop();
        initialize();
        this.mZoomDelegate.reset();
        start();
    }

    public boolean hasFocus() {
        return this.mViewPager.hasFocus();
    }

    public void initialize() {
        int i2;
        Log.d(this.TAG, "initialize");
        ViewUtils.removeSelf(this.mViewPager);
        ViewPager2 viewPager2 = this.mViewPager;
        if (viewPager2 != null) {
            viewPager2.unregisterOnPageChangeCallback(this);
            this.mViewPager.setAccessibilityDelegate((View.AccessibilityDelegate) null);
            i2 = this.mViewPager.getCurrentItem();
        } else {
            i2 = -1;
        }
        ViewPager2 viewPager22 = (ViewPager2) View.inflate(this.mViewpagerRoot.getContext(), R$layout.live_motion_view_pager, this.mViewpagerRoot).findViewById(R$id.motion_viewpager);
        this.mViewPager = viewPager22;
        setBackgroundColor(viewPager22);
        if (!supportRecoilEffect()) {
            ViewUtils.disableSeslRecoil(this.mViewPager);
        }
        this.mViewPager.registerOnPageChangeCallback(this);
        this.mViewPager.setAccessibilityDelegate(new AccessibilityDelegate(this.mViewPager, new J5.c(2, this)));
        this.mIntervalTimer.setScheduleListener(new A5.a(15, this));
        LiveMotionPageTransformer liveMotionPageTransformer = this.mTransformer;
        if (liveMotionPageTransformer == null) {
            liveMotionPageTransformer = createPageTransformer();
        }
        setPageTransformer(liveMotionPageTransformer);
        this.mTransformer.setViewPager(this.mViewPager);
        if (i2 != -1) {
            if (getAdapter() != null) {
                setAdapter(getAdapter());
            }
            this.mViewPager.setCurrentItem(i2, false);
        }
        this.mViewPager.setOffscreenPageLimit(1);
        setListAccessibilityDelegate();
        Optional.ofNullable(getRecyclerView()).ifPresent(new b(23));
    }

    public boolean isAllowTouch() {
        return this.mIsAllowTouchCheck;
    }

    public boolean isPlayableState() {
        if (!getAdapter().isDataPrepared() || this.mKeepPause) {
            return false;
        }
        ViewPagerCallback viewPagerCallback = this.mCallback;
        if (viewPagerCallback == null || viewPagerCallback.isPlayable()) {
            return true;
        }
        return false;
    }

    public boolean isPlaying() {
        return this.mTransformer.isPlaying();
    }

    public boolean isScrollIdle(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public final boolean isSwipeMode() {
        return this.mTransformer.isSwipeMode();
    }

    public void isTouchUpOrCancel() {
        if (this.mZoomDelegate.isActive()) {
            this.mZoomDelegate.restore(new Lb.b(this, 0));
        } else {
            isTouchUpOrCancelInternal();
        }
    }

    public abstract boolean isVideo();

    public void moveFocusPosition() {
        if (this.mViewPager.isFakeDragging()) {
            this.mViewPager.endFakeDrag();
        }
        this.mViewPager.setCurrentItem(getInitialPosition(), false);
        String str = this.TAG;
        Log.d(str, "moveFocusPosition=" + getAdapter().getFocusDataPosition());
    }

    public boolean moveNext() {
        boolean z;
        int currentItem = this.mViewPager.getCurrentItem();
        if (this.mViewPager.getCurrentItem() < this.mAdapter.getItemCount() - 1) {
            currentItem = this.mViewPager.getCurrentItem() + 1;
        }
        if (currentItem == this.mViewPager.getCurrentItem()) {
            z = true;
        } else {
            z = false;
        }
        scrollToPosition(currentItem, false, z);
        return true;
    }

    public boolean movePrev() {
        boolean z;
        int currentItem = this.mViewPager.getCurrentItem();
        if (this.mViewPager.getCurrentItem() > 0) {
            currentItem = this.mViewPager.getCurrentItem() - 1;
        }
        if (currentItem == this.mViewPager.getCurrentItem()) {
            z = true;
        } else {
            z = false;
        }
        scrollToPosition(currentItem, false, z);
        return true;
    }

    public void moveToLast() {
        scrollToPosition(this.mAdapter.getItemCount() - 1, false, false);
    }

    public void onClicked(float f) {
        ViewPagerCallback viewPagerCallback = this.mCallback;
        if (viewPagerCallback == null || !viewPagerCallback.onPreClick()) {
            float width = ((float) this.mViewPager.getWidth()) * 0.15f;
            int i2 = -1;
            if (f < width) {
                int currentItem = getCurrentItem();
                if (Features.isEnabled(Features.IS_RTL)) {
                    i2 = 1;
                }
                int i7 = currentItem + i2;
                onEscapeEndPosition(i7, false);
                scrollToPosition(i7, false, true);
            } else if (f > ((float) this.mViewPager.getWidth()) - width) {
                int currentItem2 = getCurrentItem();
                if (!Features.isEnabled(Features.IS_RTL)) {
                    i2 = 1;
                }
                scrollToPosition(currentItem2 + i2, false, true);
            } else {
                ViewPagerCallback viewPagerCallback2 = this.mCallback;
                if (viewPagerCallback2 != null) {
                    viewPagerCallback2.onClick();
                }
            }
        }
    }

    public abstract void onEndPosition();

    public abstract void onEscapeEndPosition(int i2, boolean z);

    public boolean onMove(float f, float f5) {
        return this.mZoomDelegate.move(f, f5);
    }

    public void onPageScrollStateChanged(int i2) {
        super.onPageScrollStateChanged(i2);
        if (this.mTransformer.isSwipeMode() && isScrollIdle(i2)) {
            this.mTransformer.switchMode(true);
            start();
        }
        if (isScrollIdle(i2)) {
            Optional.ofNullable(this.mCallback).ifPresent(new b(24));
        }
    }

    public void onPageScrolled(int i2, float f, int i7) {
        Optional.ofNullable(this.mCallback).ifPresent(new d(this, i2, f, 0));
    }

    public void onPageSelected(int i2) {
        if (getAdapter() != null) {
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "onPageSelected{", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            o2.append(getAdapter().getDataPosition(i2));
            o2.append("}");
            Log.d(str, o2.toString());
        }
        onPageSelectedInternal(i2);
        requestPreview();
        RecyclerView.ViewHolder viewHolder = getViewHolder(i2);
        if (viewHolder != null && viewHolder.itemView.getAlpha() == 0.0f) {
            Log.w(this.TAG, "onPageSelected restore alpha");
            ViewUtils.setAlpha(viewHolder.itemView, 1.0f);
        }
        if (this.mTransformer.isSwipeMode()) {
            this.mTransformer.clearWithoutCancel();
            if (isScrollIdle(this.mViewPager.getScrollState())) {
                this.mTransformer.switchMode(true);
                start();
                Log.d(this.TAG, "onPageSelected #start");
            }
        }
    }

    public void onPageSelectedInternal(int i2) {
        if (getAdapter() != null) {
            getAdapter().updateCurrentPosition(i2);
            TransformBuilder transformBuilder = getTransformBuilder();
            transformBuilder.prepare(getItemView(this.mViewPager, i2 - 1));
            transformBuilder.prepare(getItemView(this.mViewPager, i2 + 1));
            Optional.ofNullable(this.mCallback).ifPresent(new J((Object) this, i2, 2));
        }
    }

    public void onScale(ScaleGestureDetector scaleGestureDetector) {
        this.mZoomDelegate.onScale(scaleGestureDetector.getScaleFactor());
        this.mZoomDelegate.move(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        if (this.mTransformer.isPageInOutStarted()) {
            Log.d(this.TAG, "handleOnScale while transition");
        }
        if (this.mZoomDelegate.isActive()) {
            this.mZoomDelegate.onScaleBegin(scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            return true;
        } else if (!this.mZoomDelegate.onScaleBegin(getPreviewableViewHolder(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY(), isVideo())) {
            return false;
        } else {
            setSwipeMode();
            ViewPagerCallback viewPagerCallback = this.mCallback;
            if (viewPagerCallback != null) {
                viewPagerCallback.onZoomState(true);
            }
            return true;
        }
    }

    public void onSlideShowDone() {
        Optional.ofNullable(this.mCallback).ifPresent(new b(21));
    }

    public void onTransformEnd() {
        this.mIntervalTimer.setDuration(0).start();
    }

    public void onTransformStart() {
        this.mIntervalTimer.stop();
    }

    public void pause() {
        this.mIntervalTimer.stop();
        this.mTransformer.preparePause(this.mViewPager);
        this.mTransformer.pause();
    }

    public void prepareScrollToPosition() {
        if (this.mViewPager.isFakeDragging()) {
            this.mTransformer.pause();
            this.mTransformer.prepareSwipe(this.mViewPager);
            this.mViewPager.endFakeDrag();
        }
    }

    public void requestDisallowInterceptTouchEvent(boolean z) {
        this.mViewPager.requestDisallowInterceptTouchEvent(z);
    }

    public void resetTheme(EffectTheme effectTheme) {
        this.mTransformBuilder = createTransformBuilder();
        changeTheme(effectTheme);
    }

    public void resume() {
        start();
    }

    public final void scrollToPosition(int i2, boolean z) {
        scrollToPosition(i2, z, false);
    }

    public void setAdapter(ILiveMotionAdapter iLiveMotionAdapter) {
        this.mAdapter = iLiveMotionAdapter;
        this.mViewPager.setAdapter((RecyclerView.Adapter) iLiveMotionAdapter);
    }

    public void setAllowTouch(boolean z) {
        this.mIsAllowTouchCheck = z;
    }

    public void setCurrentItem(int i2) {
        this.mViewPager.setCurrentItem(i2, false);
    }

    public void setKeepPause(boolean z) {
        this.mKeepPause = z;
        if (z) {
            pause();
        } else {
            start();
        }
    }

    public void setPreviewListener(Consumer<RecyclerView.ViewHolder> consumer) {
        this.mPreviewListener = consumer;
    }

    public void setSwipeMode() {
        this.mTransformer.pause();
        this.mTransformer.prepareSwipe(this.mViewPager);
        this.mIntervalTimer.stop();
    }

    public void setViewPagerCallback(ViewPagerCallback viewPagerCallback) {
        this.mCallback = viewPagerCallback;
    }

    public void setVisibility(int i2, int i7) {
        if (i7 > 0) {
            ThreadUtil.postOnUiThreadDelayed(new C0368c(this, i2, 8), (long) i7);
        } else {
            ViewUtils.setVisibility(this.mViewPager, i2);
        }
    }

    public final void start() {
        start(StatusCodes.INPUT_MISSING);
    }

    public void startInternal(int i2) {
        if (!this.mViewpagerRoot.hasTouchDelegate()) {
            this.mViewPager.setUserInputEnabled(true);
            setDispatchTouchInterceptor(this.mViewpagerRoot.getContext());
        }
        this.mIntervalTimer.stop();
        getAdapter().prepareNext(getCurrentDataPosition());
        this.mIntervalTimer.setDuration(i2).start();
    }

    public void stop() {
        this.mTransformer.cancel();
        this.mIntervalTimer.stop();
    }

    public abstract boolean supportFaceCircle();

    public boolean supportRecoilEffect() {
        return false;
    }

    public void updateLastTouch(float f, float f5) {
        this.mZoomDelegate.updateLastTouch(f, f5);
    }

    public void scrollToPosition(int i2, boolean z, boolean z3) {
        if (getCurrentDataPosition() != i2 || this.mAdapter.getItemCount() == 1) {
            prepareScrollToPosition();
            stop();
            this.mViewPager.setCurrentItem(i2, z);
            this.mTransformer.switchMode(true);
            start(550);
            if (z3) {
                this.mEdgeEffectHandler.showEdgeEffect(this.mViewPager, getAdapter(), i2);
            }
        }
    }

    public final void start(int i2) {
        if (isPlayableState() && !isPlaying()) {
            if (isPlayPaused()) {
                resumePlay();
            } else {
                startInternal(i2);
            }
        }
    }

    public void setBackgroundColor(ViewPager2 viewPager2) {
    }
}
