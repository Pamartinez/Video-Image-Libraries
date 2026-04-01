package com.samsung.android.gallery.widget.story.transitory;

import A4.J;
import M6.a;
import W8.C0579a;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import ba.C0582a;
import bc.C0584a;
import bc.C0586c;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listview.InterceptDispatchTouchListener;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.ViewPagerContainer;
import com.samsung.android.gallery.widget.story.transitory.ViewPagerTouchDelegate;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.Optional;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesViewPager extends ViewPager2.OnPageChangeCallback implements ViewPagerTouchDelegate.OnTouchEventListener {
    protected final String TAG = getClass().getSimpleName();
    protected final AccessibilityState mAccessibilityState = new AccessibilityState();
    protected StoriesViewPagerAdapter mAdapter;
    protected ViewPagerListener mCallback;
    protected int mContentWidth = -1;
    protected PageTransformer mPageTransformer;
    protected int mSideMargin;
    protected ViewPagerTouchDelegate mTouchDelegate;
    protected Supplier<Boolean> mTouchable;
    protected ViewPager2 mViewPager;
    private final ViewPagerContainer mViewPagerParent;

    public StoriesViewPager(ViewGroup viewGroup) {
        this.mViewPagerParent = (ViewPagerContainer) viewGroup;
        init(viewGroup);
    }

    private void init(ViewGroup viewGroup) {
        ViewPager2 viewPager2 = (ViewPager2) viewGroup.findViewById(R$id.transitory_viewpager);
        this.mViewPager = viewPager2;
        viewPager2.setOffscreenPageLimit(2);
        this.mViewPager.registerOnPageChangeCallback(this);
        this.mViewPager.setPageTransformer(getTransformer());
        this.mViewPager.setUserInputEnabled(true);
        getRecyclerView().setFocusable(false);
        setDispatchTouchInterceptor(this.mViewPager);
        setListAccessibilityDelegate();
        getRecyclerView().setNestedScrollingEnabled(false);
        ViewUtils.disableSeslRecoil(this.mViewPager);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPageSelected$3(int i2, ViewPagerListener viewPagerListener) {
        viewPagerListener.onPageSelected(i2, this.mViewPager.getScrollState());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCurrentItem$0() {
        this.mViewPager.requestTransform();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setCurrentItem$1(int i2, boolean z) {
        setCurrentItem(i2, z, 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setListAccessibilityDelegate$5(RecyclerView recyclerView) {
        recyclerView.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
                ViewPager2 viewPager2;
                if (accessibilityEvent.getEventType() != 4096 || (viewPager2 = StoriesViewPager.this.mViewPager) == null || !viewPager2.isFakeDragging()) {
                    super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
                }
            }
        });
    }

    private void setDispatchTouchInterceptor(ViewPager2 viewPager2) {
        this.mTouchDelegate = new ViewPagerTouchDelegate(viewPager2, this);
        this.mViewPagerParent.setDispatchTouchInterceptor(createTouchInterceptor());
    }

    private void setListAccessibilityDelegate() {
        Optional.ofNullable(getRecyclerView()).ifPresent(new C0586c(this, 0));
    }

    public abstract InterceptDispatchTouchListener createTouchInterceptor();

    public void destroy() {
        this.mAccessibilityState.destroy();
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter != null) {
            storiesViewPagerAdapter.destroy();
        }
    }

    public int getCurrentItem() {
        return this.mViewPager.getCurrentItem();
    }

    public ListViewHolder getCurrentViewHolder() {
        return getViewHolder(this.mViewPager.getCurrentItem());
    }

    public MediaItem getMediaItem(int i2) {
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter != null) {
            return storiesViewPagerAdapter.getMediaItem(i2);
        }
        return null;
    }

    public ListViewHolder getPreviewableViewHolder() {
        return getViewHolder(this.mViewPager.getCurrentItem());
    }

    public RecyclerView getRecyclerView() {
        return (RecyclerView) this.mViewPager.getChildAt(0);
    }

    public abstract PageTransformer getTransformer();

    public ViewPager2 getView() {
        return this.mViewPager;
    }

    public ListViewHolder getViewHolder(int i2) {
        return (ListViewHolder) getRecyclerView().findViewHolderForAdapterPosition(i2);
    }

    public void initAccessibilityState() {
        this.mAccessibilityState.initAccessibilityState(new C0586c(this, 1));
    }

    public void notifyItemRangeChanged(String str) {
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter != null) {
            storiesViewPagerAdapter.notifyItemRangeChanged(0, storiesViewPagerAdapter.getItemCount(), str);
        }
    }

    public void onPageSelected(int i2) {
        Optional.ofNullable(this.mCallback).ifPresent(new J((Object) this, i2, 6));
        if (this.mAccessibilityState.isVoiceServiceEnabled()) {
            Optional.ofNullable(getCurrentViewHolder()).ifPresent(new C0579a(21));
        }
    }

    public void refreshTransformer() {
        boolean z;
        PageTransformer transformer = getTransformer();
        if (transformer != null) {
            this.mViewPager.setPageTransformer(transformer);
            this.mViewPager.requestTransform();
            transformer.setTransformRequested();
        }
        String str = this.TAG;
        if (transformer != null) {
            z = true;
        } else {
            z = false;
        }
        Log.d(str, "refreshTransformer", Boolean.valueOf(z));
    }

    public void registerOnPageChangeCallback() {
        this.mViewPager.unregisterOnPageChangeCallback(this);
        this.mViewPager.registerOnPageChangeCallback(this);
    }

    public void setAdapter(StoriesViewPagerAdapter storiesViewPagerAdapter) {
        this.mAdapter = storiesViewPagerAdapter;
        if (storiesViewPagerAdapter != null) {
            storiesViewPagerAdapter.setSideMargin(this.mSideMargin);
            this.mAdapter.setContentWidth(this.mContentWidth);
        }
        this.mViewPager.setAdapter(storiesViewPagerAdapter);
    }

    public void setCurrentItem(int i2, int i7) {
        setCurrentItem(i2, false, i7);
    }

    public void setPageViewInfo(int i2, int i7, int i8, int i10) {
        this.mSideMargin = i7;
        this.mContentWidth = i2;
        StoriesViewPagerAdapter storiesViewPagerAdapter = this.mAdapter;
        if (storiesViewPagerAdapter != null) {
            storiesViewPagerAdapter.setSideMargin(i7);
            this.mAdapter.setContentWidth(i2);
        }
    }

    public void setTouchable(Supplier<Boolean> supplier) {
        this.mTouchable = supplier;
    }

    public void setViewPagerCallback(ViewPagerListener viewPagerListener) {
        this.mCallback = viewPagerListener;
        Optional.ofNullable(this.mPageTransformer).ifPresent(new C0582a(2, viewPagerListener));
    }

    public void unregisterOnPageChangeCallback() {
        this.mViewPager.unregisterOnPageChangeCallback(this);
    }

    public void setCurrentItem(int i2, boolean z, int i7) {
        if (i7 <= 0) {
            this.mViewPager.setCurrentItem(i2, z);
            this.mViewPager.requestTransform();
            this.mViewPager.post(new C0584a(1, this));
            return;
        }
        ThreadUtil.postOnUiThreadDelayed(new a(this, i2, z, 1), (long) i7);
    }

    public void onTouchCancel() {
    }

    public void onTouchUp() {
    }

    public void swipe() {
    }

    public void onAccessibilityStateChanged(boolean z) {
    }
}
