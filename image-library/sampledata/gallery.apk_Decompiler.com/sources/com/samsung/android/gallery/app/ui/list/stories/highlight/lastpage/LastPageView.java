package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import Fb.k;
import android.animation.Animator;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.WindowInsets;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.RecyclerPageScrollHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.PageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.SaveWithReplayPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.item.SaveWithRewriteStoryPageItem;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.OnDemandPage;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.page.PageHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.EntryEffectHelper;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.LastPageDataHolder;
import com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage.pageuidata.PageSpec;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.utils.WindowUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import com.sec.android.gallery3d.R;
import ic.l;
import java.util.ArrayList;
import java.util.Optional;
import n4.C0489a;
import o6.B;
import o6.p;
import o6.t;
import q4.e;
import q6.b;
import q6.c;
import q6.d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LastPageView implements ILastPageView {
    private static final int NEXT_PAGE_TIMER = Timer.getTimerId();
    private PageRecyclerAdapter mAdapter;
    private EmptyTouchHandler mEmptyTouchHandler;
    private final EntryEffectHelper mEntryEffectHelper = new EntryEffectHelper();
    private boolean mIsShowing;
    /* access modifiers changed from: private */
    public View mLastPageRoot;
    private RecyclerView mPageRecyclerView;
    private final RecyclerPageScrollHelper.PageScrollListener mPageScrollListener = new p(6, this);
    protected final LastPagePresenter mPresenter;
    private View mReplayBtn;
    private View mReplayToolBtn;
    private View mSaveAsStoryBtn;
    private View mSaveAsVideoBtn;
    private RecyclerPageScrollHelper mScrollHelper;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
            super.onScrollStateChanged(recyclerView, i2);
            LastPageView.this.updateFocusedPosition(i2);
            if (!LastPageView.this.isScrollIdle(i2)) {
                LastPageView.this.stopNextPageTimer();
            }
        }
    };
    private int mSideQueOffset = -1;
    private LinearSnapHelper mSnapHelper;
    private View mToolbar;
    private final VideoPreview mVideoPreview;
    private final IStoryHighlightView mView;

    public LastPageView(IStoryHighlightView iStoryHighlightView) {
        this.mView = iStoryHighlightView;
        this.mPresenter = createPresenter();
        this.mVideoPreview = new VideoPreview();
    }

    private void clearRecyclerView() {
        RecyclerView recyclerView = this.mPageRecyclerView;
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            Optional.ofNullable((PageHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2))).ifPresent(new B(19));
        }
        recyclerView.removeAllViews();
        recyclerView.getRecycledViewPool().clear();
    }

    private void completeEntryEffect() {
        this.mEntryEffectHelper.setComplete();
    }

    private ArrayList<PageItem> getPageItems() {
        ArrayList<PageItem> arrayList = new ArrayList<>(this.mPresenter.getPageItems());
        if (isLandscape() && !arrayList.isEmpty()) {
            if (isOnDemandView()) {
                arrayList.add(new SaveWithRewriteStoryPageItem());
            }
            if (isRecap()) {
                arrayList.add(new SaveWithReplayPageItem());
            }
        }
        return arrayList;
    }

    private int getSideQueOffset() {
        View view = (View) this.mLastPageRoot.getParent();
        if (this.mSideQueOffset == -1 && view.getWidth() != 0) {
            int i2 = (new PageSpec(this.mView, view).calculate().sideGap / 2) * 3;
            this.mSideQueOffset = i2;
            Log.d("LastPageView", "side que offset", Integer.valueOf(i2));
        }
        return this.mSideQueOffset;
    }

    private void handleAction(int i2, Object... objArr) {
        this.mPresenter.handleAction(i2, objArr);
    }

    /* access modifiers changed from: private */
    public void handleConfigurationChange() {
        if (isShowing()) {
            completeEntryEffect();
        }
        requestPreview(false);
        prepareConfigChange();
        clearRecyclerView();
        initScroll();
        updatePageLayout();
        invalidateChild();
        restorePageScroll();
        requestPreview(true, StatusCodes.INPUT_MISSING);
    }

    private void initEntryEndEffect() {
        int i2;
        this.mEntryEffectHelper.setEnable(supportEntryEffect());
        if (this.mEntryEffectHelper.effectRequired() && this.mPresenter.hasRelatedPage()) {
            this.mEntryEffectHelper.addEntryEndEffect(this.mReplayBtn);
            float f = new PageSpec(this.mView, (View) this.mLastPageRoot.getParent()).calculate().baseSize * 0.138f;
            EntryEffectHelper entryEffectHelper = this.mEntryEffectHelper;
            RecyclerView recyclerView = this.mPageRecyclerView;
            if (Features.isEnabled(Features.IS_RTL)) {
                i2 = -1;
            } else {
                i2 = 1;
            }
            entryEffectHelper.addEntryEndScrollEffect(recyclerView, ((float) i2) * f);
            Log.d("LastPageView", "initEntryEndEffect scrollOffset=", Float.valueOf(f));
        }
    }

    private void initLayoutManager(View view, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new PageLayoutManager(view.getContext(), 0, false));
    }

    private void initPageRecyclerView(View view) {
        RecyclerView recyclerView = (RecyclerView) this.mLastPageRoot.findViewById(R.id.page_recycler);
        this.mPageRecyclerView = recyclerView;
        recyclerView.setOverScrollMode(2);
        this.mPageRecyclerView.seslSetRecoilEnabled(false);
        initLayoutManager(view, this.mPageRecyclerView);
        initScroll();
        PageRecyclerAdapter pageRecyclerAdapter = new PageRecyclerAdapter(this.mView, view);
        this.mAdapter = pageRecyclerAdapter;
        this.mPageRecyclerView.setAdapter(pageRecyclerAdapter);
    }

    private void initReference() {
        this.mEmptyTouchHandler = new EmptyTouchHandler(this.mLastPageRoot, new d(this, 1));
        this.mVideoPreview.setListView(this.mPageRecyclerView);
    }

    private void initScroll() {
        initSnapHelper();
        initScrollHelper();
    }

    private void initScrollHelper() {
        this.mSideQueOffset = -1;
        if (this.mPageRecyclerView != null) {
            if (ResourceCompat.isLandscape((View) this.mLastPageRoot.getParent())) {
                RecyclerPageScrollHelper recyclerPageScrollHelper = this.mScrollHelper;
                if (recyclerPageScrollHelper != null) {
                    recyclerPageScrollHelper.setPageScrollListener((RecyclerPageScrollHelper.PageScrollListener) null);
                }
            } else {
                if (this.mScrollHelper == null) {
                    this.mScrollHelper = new RecyclerPageScrollHelper(this.mPageRecyclerView);
                }
                this.mScrollHelper.setPageScrollListener(this.mPageScrollListener);
            }
            this.mPageRecyclerView.removeOnScrollListener(this.mScrollListener);
            this.mPageRecyclerView.addOnScrollListener(this.mScrollListener);
        }
    }

    private void initSnapHelper() {
        if (this.mPageRecyclerView == null) {
            return;
        }
        if (ResourceCompat.isLandscape((View) this.mLastPageRoot.getParent())) {
            LinearSnapHelper linearSnapHelper = this.mSnapHelper;
            if (linearSnapHelper != null) {
                linearSnapHelper.attachToRecyclerView((RecyclerView) null);
                return;
            }
            return;
        }
        if (this.mSnapHelper == null) {
            this.mSnapHelper = new RecyclerViewPagerSnapHelper();
        }
        this.mSnapHelper.attachToRecyclerView(this.mPageRecyclerView);
    }

    private void invalidateChild() {
        if (isOnDemandView() || isRecap()) {
            this.mAdapter.setData(getPageItems());
        }
        this.mAdapter.notifyDataSetChanged();
        this.mPageRecyclerView.post(new c(this, 0));
    }

    private boolean isLandscape() {
        return ResourceCompat.isLandscape((View) this.mLastPageRoot.getParent());
    }

    private boolean isOnDemandView() {
        return this.mView.getOptions().isOnDemandStory();
    }

    private boolean isRecap() {
        return this.mView.getOptions().isRecap();
    }

    /* access modifiers changed from: private */
    public boolean isScrollIdle(int i2) {
        return i2 == 0;
    }

    private boolean isTrip() {
        String argValue = ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "categoryKey");
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.TripStoryMap) || argValue == null || !argValue.startsWith("location://stories/category/trip")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initReference$0(Boolean bool) {
        handleAction(2, -1);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$invalidateChild$6() {
        if (isShowing()) {
            notifyToChildren(Event.LAST_PAGE_SHOW);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$10(View view, float f) {
        int i2;
        float f5 = f * (-((float) getSideQueOffset()));
        View childAt = ((ViewGroup) view).getChildAt(0);
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        childAt.setTranslationX(f5 * ((float) i2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMultiWindowModeChanged$5(boolean z) {
        View view = this.mLastPageRoot;
        if (view != null) {
            if (z) {
                ViewMarginUtils.setVerticalPadding(view, 0, 0);
            } else {
                Rect systemInsets = WindowUtils.getSystemInsets(view.getRootWindowInsets());
                ViewMarginUtils.setVerticalPadding(view, systemInsets.top, systemInsets.bottom);
            }
            Log.d("LastPageView", "multiWindow", Boolean.valueOf(z), Integer.valueOf(view.getPaddingTop()), Integer.valueOf(view.getPaddingBottom()));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestPreview$11(boolean z) {
        if (!z || !this.mView.isViewResumed() || !this.mIsShowing) {
            this.mVideoPreview.stopPreview();
        } else {
            this.mVideoPreview.startPreview();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$restorePageScroll$7() {
        moveToPage(this.mPresenter.getFocusPosition(), false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setNextPageTimer$4(int i2) {
        ThreadUtil.runOnUiThread(new c(this, 2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnClickListener$1(int i2, View view) {
        handleAction(i2, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$2() {
        notifyToChildren(Event.LAST_PAGE_SHOW);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFocusedPosition$9(RecyclerView.LayoutManager layoutManager) {
        if (!ResourceCompat.isLandscape((View) this.mPageRecyclerView)) {
            this.mPresenter.saveFocusPosition(((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition(), "scrollState");
        }
    }

    /* access modifiers changed from: private */
    public void moveToPage(int i2, boolean z) {
        Log.d("LastPageView", "moveToPage", Integer.valueOf(i2), Boolean.valueOf(z));
        if (z) {
            this.mPageRecyclerView.smoothScrollToPosition(i2);
        } else {
            Optional.ofNullable(this.mPageRecyclerView.getLayoutManager()).ifPresent(new C0489a(i2, 10));
        }
        RecyclerPageScrollHelper recyclerPageScrollHelper = this.mScrollHelper;
        if (recyclerPageScrollHelper != null) {
            ThreadUtil.postOnUiThread(new t(15, recyclerPageScrollHelper));
        }
    }

    private void notifyToChildren(Event event) {
        int childCount = this.mPageRecyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerView = this.mPageRecyclerView;
            PageHolder pageHolder = (PageHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
            if (pageHolder != null) {
                if (Event.LAST_PAGE_HIDE.equals(event)) {
                    pageHolder.onLastPageShow(false);
                } else if (Event.LAST_PAGE_SHOW.equals(event)) {
                    pageHolder.onLastPageShow(true);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void onNextPageAlarm() {
        if (isShowing() && this.mAdapter.getItemCount() > 1 && !isLandscape() && isScrollIdle()) {
            moveToPage(1, true);
        }
    }

    private void prepareConfigChange() {
        int childCount = this.mPageRecyclerView.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerView = this.mPageRecyclerView;
            PageHolder pageHolder = (PageHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
            if (pageHolder != null) {
                pageHolder.prepareResolutionChange();
            }
        }
    }

    private void resetEntryEffect() {
        this.mEntryEffectHelper.reset();
    }

    private void restorePageScroll() {
        ThreadUtil.postOnUiThreadDelayed(new c(this, 5), 100);
    }

    private void setActionBtnVisibility() {
        boolean z;
        boolean isLandscape = isLandscape();
        if (isOnDemandView()) {
            ViewUtils.setVisibleOrGone(this.mReplayToolBtn, false);
            ViewUtils.setVisibleOrGone(this.mReplayBtn, false);
            ViewUtils.setVisibleOrGone(this.mSaveAsStoryBtn, this.mAdapter.isEmpty());
        } else if (isRecap()) {
            ViewUtils.setVisibleOrGone(this.mReplayToolBtn, false);
            ViewUtils.setVisibleOrGone(this.mSaveAsStoryBtn, false);
            ViewUtils.setVisibleOrGone(this.mReplayBtn, false);
            ViewUtils.setVisibleOrGone(this.mSaveAsVideoBtn, false);
        } else {
            if (!isLandscape || this.mAdapter.isEmpty()) {
                z = true;
            } else {
                z = false;
            }
            ViewUtils.setVisibleOrGone(this.mReplayToolBtn, !z);
            ViewUtils.setVisibleOrGone(this.mReplayBtn, z);
            ViewUtils.setVisibleOrGone(this.mSaveAsStoryBtn, false);
        }
    }

    private void setNextPageTimer() {
        stopNextPageTimer();
        Timer.startTimer(NEXT_PAGE_TIMER, 7000, new k(6, this));
    }

    private void setOnClickListener(View view, int i2) {
        if (view != null) {
            view.setOnClickListener(new e(this, i2, 2));
        }
    }

    private void startAnimation(final boolean z, int i2) {
        float f;
        if (!this.mView.isDestroyed()) {
            ViewPropertyAnimator animate = this.mLastPageRoot.animate();
            if (z) {
                f = 1.0f;
            } else {
                f = 0.0f;
            }
            animate.alpha(f).setDuration((long) i2).setListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    float f;
                    int i2;
                    LastPageView.this.moveToPage(0, false);
                    LastPageView.this.requestPreview(z, 0);
                    View p6 = LastPageView.this.mLastPageRoot;
                    if (z) {
                        f = 1.0f;
                    } else {
                        f = 0.0f;
                    }
                    ViewUtils.setAlpha(p6, f);
                    View p8 = LastPageView.this.mLastPageRoot;
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 4;
                    }
                    ViewUtils.setVisibility(p8, i2);
                }

                public void onAnimationStart(Animator animator) {
                    ViewUtils.setVisibility(LastPageView.this.mLastPageRoot, 0);
                }
            }).start();
        }
    }

    private boolean supportEntryEffect() {
        if (isTrip() || this.mEntryEffectHelper.isDisabled() || this.mView.getOptions().isOnDemandStory()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void updateFocusedPosition(int i2) {
        if (isScrollIdle(i2)) {
            Optional.ofNullable(this.mPageRecyclerView.getLayoutManager()).ifPresent(new d(this, 0));
        }
    }

    /* access modifiers changed from: private */
    public void updatePageLayout() {
        Log.d("LastPageView", "updatePageLayout", Boolean.valueOf(isShowing()), Integer.valueOf(this.mLastPageRoot.getHeight()));
        if (isShowing() && this.mLastPageRoot.getHeight() > 0) {
            setActionBtnVisibility();
            if (this.mAdapter.getItemCount() > 0) {
                PageSpec.Config calculate = new PageSpec(this.mView, (View) this.mLastPageRoot.getParent()).calculate();
                ViewMarginUtils.setTopMargin(this.mLastPageRoot.findViewById(R.id.page_list_parent), calculate.pageTopSpace);
                ViewMarginUtils.setMargin(this.mReplayBtn, (Integer) null, 0, (Integer) null, Integer.valueOf(calculate.pageBottomSpace));
                ViewMarginUtils.setMargin(this.mSaveAsStoryBtn, (Integer) null, 0, (Integer) null, Integer.valueOf(calculate.pageBottomSpace));
                Log.d("LastPageView", "updateReplayLayout with data", calculate);
                return;
            }
            int height = (this.mLastPageRoot.getHeight() / 2) - 200;
            ViewMarginUtils.setMargin(this.mReplayBtn, (Integer) null, Integer.valueOf(height), (Integer) null, 0);
            ViewMarginUtils.setMargin(this.mSaveAsStoryBtn, (Integer) null, Integer.valueOf(height), (Integer) null, 0);
            Log.d("LastPageView", "updateReplayLayout with no data", Integer.valueOf(height));
        }
    }

    public LastPagePresenter createPresenter() {
        return new LastPagePresenter(this);
    }

    public void destroy() {
        this.mPresenter.destroy();
        this.mPageRecyclerView.removeOnScrollListener(this.mScrollListener);
        Optional.ofNullable((Object) null).ifPresent(new l(8));
        Optional.ofNullable(this.mEmptyTouchHandler).ifPresent(new B(20));
        stopNextPageTimer();
    }

    public String getEditedTitle() {
        int childCount = this.mPageRecyclerView.getChildCount();
        String str = "";
        for (int i2 = 0; i2 < childCount; i2++) {
            RecyclerView recyclerView = this.mPageRecyclerView;
            PageHolder pageHolder = (PageHolder) recyclerView.findContainingViewHolder(recyclerView.getChildAt(i2));
            if (pageHolder instanceof OnDemandPage) {
                str = ((OnDemandPage) pageHolder).getEditedTitle();
            }
        }
        if (TextUtils.isEmpty(str)) {
            return (String) Optional.ofNullable(this.mView.getHeaderItem()).map(new n5.e(22)).orElse("");
        }
        return str;
    }

    public EntryEffectHelper getEntryEffectHelper() {
        return this.mEntryEffectHelper;
    }

    public LastPageDataHolder getPageDataHolder() {
        return this.mPresenter.getPageDataHolder();
    }

    public IStoryHighlightView getParent() {
        return this.mView;
    }

    public void hide(boolean z) {
        int i2;
        if (this.mIsShowing) {
            this.mIsShowing = false;
            stopNextPageTimer();
            if (z) {
                i2 = 100;
            } else {
                i2 = 0;
            }
            startAnimation(false, i2);
            this.mPresenter.resetCountDown();
            notifyToChildren(Event.LAST_PAGE_HIDE);
            Log.d("LastPageView", "hide");
        }
    }

    public void initView(ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.mView.getContext()).inflate(R.layout.story_highlight_lastpage_layout, (ViewGroup) null, false);
        this.mLastPageRoot = inflate;
        ViewUtils.addView(viewGroup, inflate);
        this.mToolbar = this.mLastPageRoot.findViewById(R.id.tool);
        this.mReplayBtn = this.mLastPageRoot.findViewById(R.id.replay_button_layout);
        this.mReplayToolBtn = this.mLastPageRoot.findViewById(R.id.replay_tool_button);
        this.mSaveAsStoryBtn = this.mLastPageRoot.findViewById(R.id.save_story_layout);
        if (isOnDemandView()) {
            setOnClickListener(this.mSaveAsStoryBtn, 3);
        }
        setOnClickListener(this.mReplayBtn, 0);
        setOnClickListener(this.mReplayToolBtn, 0);
        setOnClickListener(this.mLastPageRoot.findViewById(R.id.close_tool_button), 1);
        ViewUtils.setAccessibilityRoleDescription(this.mReplayBtn, R.string.speak_button);
        ViewUtils.setAccessibilityRoleDescription(this.mReplayToolBtn, R.string.speak_button);
        ViewUtils.setAccessibilityRoleDescription(this.mLastPageRoot.findViewById(R.id.close_tool_button), R.string.speak_button);
        ViewUtils.setAccessibilityRoleDescription(this.mSaveAsStoryBtn, R.string.speak_button);
        ViewUtils.setAccessibilityRoleDescription(this.mSaveAsVideoBtn, R.string.speak_button);
        initPageRecyclerView(viewGroup);
        setActionBtnVisibility();
        initReference();
    }

    public boolean isLastPosition() {
        int intValue = ((Integer) this.mView.getEventHandler().requestData(DataRequest.VIEW_PAGER_CURRENT, -1)).intValue();
        if (this.mView.getMediaData() == null || intValue != this.mView.getMediaData().getCount() - 1) {
            return false;
        }
        return true;
    }

    public boolean isShowing() {
        return this.mIsShowing;
    }

    public void loadData(Runnable runnable) {
        this.mPresenter.loadData(runnable);
    }

    public void onApplyWindowInsets(WindowInsets windowInsets) {
        Rect systemInsets = WindowUtils.getSystemInsets(windowInsets);
        ViewMarginUtils.setVerticalPadding(this.mLastPageRoot, systemInsets.top, systemInsets.bottom);
        ViewMarginUtils.setHorizontalMargin(this.mToolbar, systemInsets.left, systemInsets.right);
    }

    public void onConfigurationChanged() {
        ThreadUtil.postOnUiThread(new c(this, 3));
    }

    public void onDataChangedOnUi() {
        this.mPresenter.onDataChangedOnUi();
    }

    public void onHeaderUpdate() {
        PageRecyclerAdapter pageRecyclerAdapter = this.mAdapter;
        if (pageRecyclerAdapter != null) {
            pageRecyclerAdapter.notifyItemRangeChanged(0, pageRecyclerAdapter.getItemCount(), Event.UPDATE_TITLE.toString());
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        ThreadUtil.postOnUiThreadDelayed(new b(this, z, 1), 150);
    }

    public void pause() {
        this.mVideoPreview.pause();
        requestPreview(false);
    }

    public void replay() {
        handleAction(0, new Object[0]);
    }

    public void requestPreview(boolean z) {
        requestPreview(z, 0);
    }

    public void reset() {
        this.mPresenter.reset();
        Optional.ofNullable(this.mAdapter).ifPresent(new B(21));
        resetEntryEffect();
    }

    public void resetCountDown() {
        this.mPresenter.resetCountDown();
    }

    public void resume() {
        this.mVideoPreview.resume();
        if (isLastPosition()) {
            requestPreview(true);
        }
    }

    public void saveStoryFromOnDemand() {
        handleAction(3, new Object[0]);
    }

    public void show(boolean z) {
        int i2;
        Log.d("LastPageView", "show", Boolean.valueOf(this.mIsShowing), Boolean.valueOf(z));
        if (!this.mIsShowing) {
            this.mIsShowing = true;
            setNextPageTimer();
            updateDataAndUi();
            initEntryEndEffect();
            if (z) {
                i2 = 400;
            } else {
                i2 = 0;
            }
            startAnimation(true, i2);
            ThreadUtil.postOnUiThreadDelayed(new c(this, 4), 100);
        }
    }

    public void stopNextPageTimer() {
        Timer.stopTimer(NEXT_PAGE_TIMER);
    }

    public void updateDataAndUi() {
        if (isShowing()) {
            ArrayList<PageItem> pageItems = getPageItems();
            this.mAdapter.setData(pageItems);
            ViewUtils.setVisibleOrGone((View) this.mPageRecyclerView.getParent(), !pageItems.isEmpty());
            if (this.mLastPageRoot.getHeight() > 0) {
                updatePageLayout();
            } else {
                this.mLastPageRoot.post(new c(this, 1));
            }
        }
    }

    private boolean isScrollIdle() {
        RecyclerView recyclerView = this.mPageRecyclerView;
        return recyclerView != null && isScrollIdle(recyclerView.getScrollState());
    }

    /* access modifiers changed from: private */
    public void requestPreview(boolean z, int i2) {
        ThreadUtil.postOnUiThreadDelayed(new b(this, z, 0), (long) i2);
    }
}
