package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import A.a;
import Fb.k;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.effectfilter.Filter;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Timer;
import com.samsung.android.gallery.widget.filmstrip3.BitmapPreLoaderByFilmSnap;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripSnapHelper;
import com.samsung.android.gallery.widget.filmstrip3.FilmStripViewHolder;
import com.samsung.android.gallery.widget.filmstrip3.OnFilmStripItemClickListener;
import com.samsung.android.gallery.widget.filmstrip3.stories.StoriesFilmStripView;
import com.samsung.android.gallery.widget.filmstrip3.stories.StoriesFilmStripViewAdapter;
import com.samsung.android.gallery.widget.livemotion.abstraction.ViewPagerScrolledValues;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import g6.f;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import k6.b;
import m7.c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FilmStripViewDelegate extends Delegate implements OnFilmStripItemClickListener {
    private static final int RELEASE_SCROLL_BLOCKING_TIMER = Timer.getTimerId();
    private StoriesFilmStripViewAdapter mAdapter;
    private final AtomicBoolean mBlockInteract = new AtomicBoolean(true);
    /* access modifiers changed from: private */
    public FilmStripViewHolder mCenterViewHolder;
    private View mContainer;
    private final AtomicBoolean mDataInit = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public StoriesFilmStripView mFilmStripView;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 100) {
                FilmStripViewDelegate.this.applyFilter();
            }
        }
    };
    private final RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private boolean isUpdatableScrollState(RecyclerView recyclerView) {
            if (recyclerView == null || recyclerView.getScrollState() == 0) {
                return false;
            }
            return true;
        }

        public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
            FilmStripViewHolder centerViewHolder = FilmStripViewDelegate.this.mFilmStripView.getCenterViewHolder();
            if (isUpdatableScrollState(recyclerView) && centerViewHolder != null && FilmStripViewDelegate.this.mCenterViewHolder != centerViewHolder) {
                int bindingAdapterPosition = centerViewHolder.getBindingAdapterPosition();
                FilmStripViewDelegate.this.mEventHandler.lambda$postEvent$0(Event.FILM_STRIP_CENTER_CHANGED, Integer.valueOf(bindingAdapterPosition));
                FilmStripViewDelegate.this.mCenterViewHolder = centerViewHolder;
                a.k(bindingAdapterPosition, "onScrolled = ", FilmStripViewDelegate.this.TAG);
            }
        }
    };
    private float mPageChangePositionAmount = -1.0f;
    private float mPageChangePositionOffset = -1.0f;

    public FilmStripViewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
    }

    /* access modifiers changed from: private */
    public void applyFilter() {
        if (!this.mView.isDestroyed()) {
            StoriesFilmStripViewAdapter storiesFilmStripViewAdapter = this.mAdapter;
            storiesFilmStripViewAdapter.notifyItemRangeChanged(0, storiesFilmStripViewAdapter.getItemCount(), "PAYLOAD_FILTER");
        }
    }

    /* access modifiers changed from: private */
    public void applyFilterBitmap(Bitmap bitmap, BiConsumer<Bitmap, Filter> biConsumer) {
        this.mEventHandler.lambda$postEvent$0(Event.APPLY_FILTER_TO_BITMAP, bitmap, Boolean.FALSE, biConsumer);
    }

    private float getAdjustedPositionOffset(float f, boolean z) {
        if (!z) {
            return f;
        }
        float f5 = this.mPageChangePositionAmount;
        if (f5 != -1.0f) {
            return (f - this.mPageChangePositionOffset) / f5;
        }
        this.mPageChangePositionAmount = 1.0f - f;
        this.mPageChangePositionOffset = f;
        return 0.0f;
    }

    private int getCurrentViewPagePosition() {
        return ((Integer) Optional.ofNullable((Integer) this.mEventHandler.requestData(DataRequest.VIEW_PAGER_CURRENT)).orElse(-1)).intValue();
    }

    private boolean isViewReady() {
        if (this.mFilmStripView != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        onDataChangedOnUi();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initView$0(int i2) {
        this.mBlockInteract.set(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$2() {
        Integer num = (Integer) this.mEventHandler.requestData(DataRequest.VIEW_PAGER_CURRENT);
        if (!(num == null || this.mAdapter == null || num.intValue() >= this.mAdapter.getItemCount())) {
            scrollToPosition(num.intValue());
        }
        String str = this.TAG;
        Log.d(str, "onConfigurationChanged current = " + num);
        this.mBlockInteract.set(false);
    }

    private void resetPageChangePosition() {
        this.mPageChangePositionAmount = -1.0f;
        this.mPageChangePositionOffset = -1.0f;
    }

    private void scrollByViewpager(ViewPagerScrolledValues viewPagerScrolledValues) {
        if (!viewPagerScrolledValues.isPageInOut() && !viewPagerScrolledValues.isSwipeMode()) {
            return;
        }
        if (viewPagerScrolledValues.getPositionOffset() == 0.0f) {
            resetPageChangePosition();
            return;
        }
        float adjustedPositionOffset = getAdjustedPositionOffset(viewPagerScrolledValues.getPositionOffset(), viewPagerScrolledValues.isPageInOut());
        this.mFilmStripView.scrollOffset(viewPagerScrolledValues.getPosition(), adjustedPositionOffset);
    }

    private void scrollToPosition(int i2) {
        if (i2 >= 0) {
            resetPageChangePosition();
            FilmStripViewHolder centerViewHolder = this.mFilmStripView.getCenterViewHolder();
            if (!this.mDataInit.get()) {
                return;
            }
            if (centerViewHolder == null || centerViewHolder.getAbsoluteAdapterPosition() != i2) {
                this.mFilmStripView.scrollToPosition(i2);
            }
        }
    }

    private void sendAnalyticsLog(Event event, Object... objArr) {
        if (event == Event.FILM_STRIP_CENTER_CHANGED) {
            postAnalyticsLog(AnalyticsEventId.EVENT_STORY_HIGHLIGHT_FILM_SELECT);
        }
    }

    private void setData(Context context, MediaData mediaData) {
        if (mediaData != null) {
            StoriesFilmStripViewAdapter storiesFilmStripViewAdapter = this.mAdapter;
            if (storiesFilmStripViewAdapter == null) {
                StoriesFilmStripViewAdapter storiesFilmStripViewAdapter2 = new StoriesFilmStripViewAdapter(context, mediaData, new f(5, this));
                this.mAdapter = storiesFilmStripViewAdapter2;
                storiesFilmStripViewAdapter2.setGeneralSlideshow(this.mView.getOptions().isSlideshow());
                this.mAdapter.addFilmStripItemClickListener(this);
                this.mFilmStripView.setAdapter(this.mAdapter);
                return;
            }
            storiesFilmStripViewAdapter.setFilmStripData(mediaData, true);
        }
    }

    private boolean updateImmediately() {
        if (!this.mView.getOptions().useDefaultEnterTransition() || !((Boolean) this.mView.requestData(DataRequest.FRAGMENT_REQ_ONGOING_ENTRY_ANIM, new Object[0])).booleanValue()) {
            return true;
        }
        return false;
    }

    public void addListenEvent() {
        addEvent(Event.VIEW_PAGER_SELECTED);
        addEvent(Event.VIEW_PAGER_SCROLLED);
        addEvent(Event.CHANGE_STORY);
        addEvent(Event.VIEW_PAGER_IDLE);
        addEvent(Event.ON_FILTER_CHANGED);
        addEvent(Event.DEFAULT_ENTRY_ANIM_END, new o4.a(4, this));
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        View view = this.mContainer;
        if (view != null && this.mFilmStripView != null && this.mAdapter != null) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            View d = C0086a.d(viewGroup, R.layout.stories_filmstrip_layout, viewGroup, false);
            ViewUtils.replaceView(viewGroup, this.mContainer, d);
            this.mContainer = d;
            StoriesFilmStripView storiesFilmStripView = (StoriesFilmStripView) d.findViewById(R.id.film_strip_view);
            this.mFilmStripView = storiesFilmStripView;
            storiesFilmStripView.addOnScrollListener(this.mOnScrollListener);
            this.mFilmStripView.setAdapter(this.mAdapter);
            scrollToPosition(getCurrentViewPagePosition());
        }
    }

    public void handleEvent(Event event, Object... objArr) {
        StoriesFilmStripView storiesFilmStripView = this.mFilmStripView;
        if (storiesFilmStripView != null) {
            if (event == Event.VIEW_PAGER_SELECTED) {
                if (objArr.length <= 1 || objArr[1].intValue() != 2) {
                    scrollToPosition(objArr[0].intValue());
                }
            } else if (event == Event.VIEW_PAGER_SCROLLED) {
                scrollByViewpager(objArr[0]);
            } else if (event == Event.CHANGE_STORY) {
                this.mDataInit.set(false);
            } else if (event == Event.VIEW_PAGER_IDLE) {
                storiesFilmStripView.resetViewHolderPosition();
            } else if (event == Event.ON_FILTER_CHANGED) {
                if (this.mHandler.hasMessages(100)) {
                    this.mHandler.removeMessages(100);
                }
                this.mHandler.sendEmptyMessageDelayed(100, 500);
            }
        }
    }

    public void initView(View view) {
        View inflate = ((ViewStub) view.findViewById(R.id.film_strip_layout_stub)).inflate();
        this.mContainer = inflate;
        StoriesFilmStripView storiesFilmStripView = (StoriesFilmStripView) inflate.findViewById(R.id.film_strip_view);
        this.mFilmStripView = storiesFilmStripView;
        storiesFilmStripView.addOnScrollListener(this.mOnScrollListener);
        setData(view.getContext(), this.mView.getMediaData());
        Timer.startTimer(RELEASE_SCROLL_BLOCKING_TIMER, 2000, new k(5, this));
        this.mFilmStripView.setOnFindTargetSnapPosition(new BitmapPreLoaderByFilmSnap(this.mView.getBlackboard(), this.mFilmStripView));
        if (this.mDataInit.get()) {
            scrollToPosition(getCurrentViewPagePosition());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetPageChangePosition();
        this.mBlockInteract.set(true);
        this.mFilmStripView.post(new b(28, this));
    }

    public void onDataChangedOnUi() {
        if (updateImmediately()) {
            if (isViewReady() && this.mView.getMediaData() != null) {
                setData(this.mView.getContext(), this.mView.getMediaData());
            }
            if (!this.mDataInit.getAndSet(true) && isViewReady()) {
                scrollToPosition(getCurrentViewPagePosition());
            }
        }
    }

    public void onDestroy() {
        this.mFilmStripView.removeOnScrollListener(this.mOnScrollListener);
        this.mFilmStripView.setOnFindTargetSnapPosition((FilmStripSnapHelper.OnFindTargetSnapPosition) null);
        this.mHandler.removeCallbacksAndMessages((Object) null);
        Optional.ofNullable(this.mAdapter).ifPresent(new c(27));
    }

    public void onItemClick(int i2, FilmStripViewHolder filmStripViewHolder) {
        this.mFilmStripView.scrollToPosition(i2);
        EventHandler eventHandler = this.mEventHandler;
        Event event = Event.FILM_STRIP_CENTER_CHANGED;
        eventHandler.lambda$postEvent$0(event, Integer.valueOf(i2));
        sendAnalyticsLog(event, new Object[0]);
    }
}
