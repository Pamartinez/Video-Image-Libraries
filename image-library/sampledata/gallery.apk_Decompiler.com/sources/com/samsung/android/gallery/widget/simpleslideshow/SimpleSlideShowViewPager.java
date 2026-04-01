package com.samsung.android.gallery.widget.simpleslideshow;

import A5.a;
import V3.b;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimpleSlideShowViewPager implements TransformListener {
    protected ISimpleSlideShowAdapter mAdapter;
    private final ScheduleTimer mIntervalTimer = new ScheduleTimer();
    private final ViewPager2.OnPageChangeCallback mPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        public void onPageSelected(int i2) {
            if (SimpleSlideShowViewPager.this.mAdapter != null) {
                StringBuilder o2 = C0086a.o(i2, "onPageSelected{", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                o2.append(SimpleSlideShowViewPager.this.mAdapter.getDataPosition(i2));
                o2.append("}");
                Log.d("SimpleSlideShowViewPager", o2.toString());
            }
        }
    };
    private Consumer<MediaItem> mPreviewableConsumer;
    private final AtomicBoolean mScheduled = new AtomicBoolean(false);
    private SimpleSlideShowPageTransformer mTransformer;
    protected ViewPager2 mViewPager;
    private final ViewGroup mViewpagerRoot;

    public SimpleSlideShowViewPager(ViewGroup viewGroup) {
        this.mViewpagerRoot = viewGroup;
        initialize();
    }

    private void initialize() {
        ViewUtils.removeSelf(this.mViewPager);
        ViewPager2 viewPager2 = (ViewPager2) View.inflate(this.mViewpagerRoot.getContext(), R$layout.simple_slideshow_viewpager, this.mViewpagerRoot).findViewById(R$id.viewpager);
        this.mViewPager = viewPager2;
        viewPager2.setUserInputEnabled(false);
        this.mViewPager.registerOnPageChangeCallback(this.mPageChangeCallback);
        this.mIntervalTimer.setScheduleListener(new a(26, this));
    }

    private void moveNext() {
        if (this.mTransformer == null) {
            setPageTransformer(new SimpleSlideShowPageTransformer());
        }
        if (this.mTransformer != null) {
            int nextDataPosition = getNextDataPosition();
            if (nextDataPosition >= 0) {
                this.mTransformer.setTransformBuilder(getTransformBuilder(nextDataPosition)).transform(this.mViewPager);
                printMoveLog("moveNext");
                return;
            }
            Log.w("SimpleSlideShowViewPager", "invalid dataPosition");
        }
    }

    private boolean previewable(int i2) {
        return PreviewFactory.isPreviewableFormat(this.mAdapter.getMediaItem(i2));
    }

    private void printMoveLog(String str) {
        int currentItem = this.mViewPager.getCurrentItem();
        int nextDataPosition = getNextDataPosition();
        Log.d("SimpleSlideShowViewPager", str + " View{" + currentItem + "->" + (currentItem + 1) + "},Data{" + getCurrentDataPosition() + "->" + nextDataPosition + "} " + MediaItemUtil.getSimpleLog(this.mAdapter.getMediaItem(nextDataPosition)));
    }

    private void requestPreview() {
        if (this.mPreviewableConsumer != null) {
            SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
            if (simpleSlideShowPageTransformer != null && simpleSlideShowPageTransformer.isPageInOutStarted() && previewable(getNextDataPosition())) {
                this.mPreviewableConsumer.accept(this.mAdapter.getMediaItem(getNextDataPosition()));
            } else if (previewable(getCurrentDataPosition())) {
                this.mPreviewableConsumer.accept(this.mAdapter.getMediaItem(getCurrentDataPosition()));
            }
        }
    }

    /* access modifiers changed from: private */
    public Boolean schedule(Integer num) {
        if (!this.mAdapter.prepareNext(getNextDataPosition())) {
            return Boolean.FALSE;
        }
        moveNext();
        return Boolean.TRUE;
    }

    public void destroy() {
        stop();
        ViewUtils.removeSelf(this.mViewPager);
        this.mViewPager.unregisterOnPageChangeCallback(this.mPageChangeCallback);
        this.mIntervalTimer.setScheduleListener((Function<Integer, Boolean>) null);
        SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
        if (simpleSlideShowPageTransformer != null) {
            simpleSlideShowPageTransformer.setTransformListener((TransformListener) null);
        }
        this.mAdapter.release();
    }

    public abstract int getBackgroundResId();

    public final int getCurrentDataPosition() {
        return this.mAdapter.getDataPosition(this.mViewPager.getCurrentItem());
    }

    public abstract int getInterval();

    public final int getNextDataPosition() {
        return this.mAdapter.getDataPosition(this.mViewPager.getCurrentItem() + 1);
    }

    public RecyclerView.ViewHolder getPreviewableViewHolder() {
        SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
        if (simpleSlideShowPageTransformer != null && simpleSlideShowPageTransformer.isPageInOutStarted() && previewable(getNextDataPosition())) {
            return getViewHolder(this.mViewPager.getCurrentItem() + 1);
        }
        if (previewable(getCurrentDataPosition())) {
            return getViewHolder(this.mViewPager.getCurrentItem());
        }
        return null;
    }

    public abstract TransformBuilder getTransformBuilder(int i2);

    public RecyclerView.ViewHolder getViewHolder(int i2) {
        RecyclerView recyclerView = (RecyclerView) this.mViewPager.getChildAt(0);
        if (recyclerView != null) {
            return recyclerView.findViewHolderForLayoutPosition(i2);
        }
        return null;
    }

    public boolean isRunning() {
        return this.mScheduled.get();
    }

    public void onPageInOutStarted() {
        requestPreview();
        if (this.mViewPager.getBackground() == null && getBackgroundResId() != 0) {
            ViewUtils.setBackgroundResource(this.mViewPager, getBackgroundResId());
        }
    }

    public void onTransformEnd() {
        this.mIntervalTimer.setDuration(getInterval()).start();
    }

    public void onTransformStarted() {
        this.mIntervalTimer.stop();
    }

    public void pause() {
        SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
        if (simpleSlideShowPageTransformer != null) {
            simpleSlideShowPageTransformer.pause();
        }
    }

    public void resume() {
        SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
        if (simpleSlideShowPageTransformer != null) {
            simpleSlideShowPageTransformer.resume();
        }
    }

    public void setAdapter(ISimpleSlideShowAdapter iSimpleSlideShowAdapter) {
        this.mAdapter = iSimpleSlideShowAdapter;
    }

    public void setPageTransformer(SimpleSlideShowPageTransformer simpleSlideShowPageTransformer) {
        this.mTransformer = simpleSlideShowPageTransformer;
        simpleSlideShowPageTransformer.setTransformListener(this);
        this.mViewPager.setPageTransformer(simpleSlideShowPageTransformer);
    }

    public void setPreviewableConsumer(Consumer<MediaItem> consumer) {
        this.mPreviewableConsumer = consumer;
    }

    public void start(boolean z) {
        if (this.mAdapter.isDataPrepared()) {
            if (isRunning()) {
                stop();
                Log.w("SimpleSlideShowViewPager", "running(stop -> start)");
            }
            this.mViewPager.setAdapter((RecyclerView.Adapter) this.mAdapter);
            if (z) {
                this.mViewPager.post(new b(17, this));
            }
        }
    }

    public void stop() {
        recycle();
        SimpleSlideShowPageTransformer simpleSlideShowPageTransformer = this.mTransformer;
        if (simpleSlideShowPageTransformer != null) {
            simpleSlideShowPageTransformer.cancel();
        }
        this.mViewPager.setCurrentItem(0, false);
        this.mScheduled.set(false);
        this.mIntervalTimer.stop();
    }

    /* access modifiers changed from: private */
    public void start() {
        if (!this.mScheduled.getAndSet(true)) {
            this.mAdapter.prepareNext(getCurrentDataPosition());
            this.mIntervalTimer.setDuration(StatusCodes.INPUT_MISSING).start();
        }
    }

    public void recycle() {
    }
}
