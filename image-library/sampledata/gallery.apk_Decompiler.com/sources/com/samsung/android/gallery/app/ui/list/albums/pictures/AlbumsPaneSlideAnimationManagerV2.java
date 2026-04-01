package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumsPaneSlideAnimationManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV3;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.AnimationManager;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.pinch.v3.FakeViewParent;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchAnimInfo;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.SingleGridItemBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsPaneSlideAnimationManagerV2 extends PicturesPinchAnimationManagerV3 implements IAlbumsPaneSlideAnimationManager {
    private final View mAlbumList;
    /* access modifiers changed from: private */
    public final AnimationManager mAnimationManager = new AnimationManager();
    private PinchAnimInfo mExtraMonthXsInfo;
    private ArrayList<PinchItem> mExtraMonthXsItems;
    private FakeViewParent mExtraMonthXsParent;
    /* access modifiers changed from: private */
    public final IAlbumsPaneSlideAnimationManager.OnSlideAnimationListener mListener;
    private final GalleryListView mRecyclerView;
    private final Supplier<Boolean> mSplitModeSupplier;

    public AlbumsPaneSlideAnimationManagerV2(PinchLayoutManager pinchLayoutManager, View view, GridInfo.ClusterInfo clusterInfo, GalleryListView galleryListView, Supplier<Boolean> supplier, IAlbumsPaneSlideAnimationManager.OnSlideAnimationListener onSlideAnimationListener) {
        super(pinchLayoutManager, clusterInfo, false, false);
        this.mRecyclerView = galleryListView;
        this.mAlbumList = view;
        this.mSplitModeSupplier = supplier;
        this.mListener = onSlideAnimationListener;
    }

    private void addMonthXsAnimation(ArrayList<PinchItem> arrayList, float f, float f5, boolean z) {
        boolean z3;
        float f8;
        float f10;
        Iterator<PinchItem> it = arrayList.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.isDivider()) {
                next.setVisibility(false);
                f10 = f;
                f8 = f5;
                z3 = z;
            } else {
                next.setVisibility(true);
                f10 = f;
                f8 = f5;
                z3 = z;
                addAnimation(next.getAnimators(this.mLayoutManager, this.mGridInfo, f10, this.mShiftHeaderOffset, f8, z3));
            }
            f = f10;
            f5 = f8;
            z = z3;
        }
    }

    private void cacheRealRatioPosition(int i2, int i7) {
        int i8;
        if (this.mGridInfo.isRealRatio(i7)) {
            int hintItemCount = this.mLayoutManager.getHintItemCount(i7);
            int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
            if (findFirstVisibleItemPosition < 0 || findLastVisibleItemPosition < 0) {
                Log.w(this.TAG, "invalid visibleRange=" + findFirstVisibleItemPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + findLastVisibleItemPosition);
                return;
            }
            int i10 = (findLastVisibleItemPosition - findFirstVisibleItemPosition) + 1;
            int max = Math.max(0, (findFirstVisibleItemPosition - i10) + 1);
            int min = Math.min(findLastVisibleItemPosition + i10, hintItemCount - 1);
            int i11 = 0;
            while (true) {
                i8 = min - max;
                if (i11 > i8) {
                    break;
                }
                this.mPositionCache.cacheViewSizeInfo(this.mLayoutManager, max + i11, i2);
                i11++;
            }
            calculateToPosition(i7);
            for (int i12 = 0; i12 <= i8; i12++) {
                this.mPositionCache.cacheViewSizeInfo(this.mLayoutManager, max + i12, i7);
            }
            calculateToPosition(i2);
        }
    }

    private void calculateToPosition(int i2) {
        this.mLayoutManager.calculateToPosition(i2);
    }

    private float getMonthXsScale() {
        return (((float) this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to())) / ((float) this.mGridInfo.calcRealToGrid())) / (((float) this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from())) / ((float) this.mGridInfo.from()));
    }

    private float getXDelta() {
        int width;
        boolean z = false;
        if (!this.mGridInfo.hasNoTargetGrid()) {
            z = GridValue.isSplit(this.mGridInfo.to(), false, false);
        } else if (!this.mSplitModeSupplier.get().booleanValue()) {
            z = true;
        }
        if (this.mIsRtl) {
            width = this.mAlbumList.getWidth();
            if (!z) {
                return (float) width;
            }
        } else {
            width = this.mAlbumList.getWidth();
            if (z) {
                return (float) width;
            }
        }
        return (float) (-width);
    }

    private void initializeAlbumListAnim() {
        addAnimation((PropertyAnimator) new TranslationAnimator(this.mAlbumList, new RectF(this.mAlbumList.getX(), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight()), new RectF(this.mAlbumList.getX() + getXDelta(), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight())));
    }

    private void prepareCluster(int i2, int i7) {
        if (this.mGridInfo.isMonthForViewing(i2)) {
            this.mLayoutManager.createMonthXsFakeCluster(GridValue.revert(i7), this.mLayoutManager.getHintWidthSpace(i2) - ((int) getXDelta()));
        }
    }

    private void preparePositionCache(int i2, int i7) {
        cacheRealRatioPosition(i2, i7);
    }

    private void startNoItemSlideAnimation() {
        this.mAnimationManager.addAnimations(getPropertyAnimators());
        this.mAnimationManager.setAnimationProgress(0.0f);
        this.mAnimationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                AlbumsPaneSlideAnimationManagerV2.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManagerV2.this.mAnimationManager.clear();
            }

            public void onAnimationEnd() {
                AlbumsPaneSlideAnimationManagerV2.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManagerV2.this.mListener.onSlideEnd();
            }

            public void onAnimationStart() {
                AlbumsPaneSlideAnimationManagerV2.this.mListener.onSlideStart();
            }
        });
        this.mAnimationManager.playLayoutAnimation(false, 360);
    }

    public void addAnimators(boolean z) {
        if (this.mExtraMonthXsInfo == null) {
            super.addAnimators(z);
            return;
        }
        calculateHeaderHeightDelta();
        updateActualGridInfo();
        float monthXsScale = getMonthXsScale();
        float xDelta = getXDelta();
        addMonthXsAnimation(getPinchItems(PicturesPinchAnimationManagerV3.Type.FAKE_MONTH_XS), xDelta, monthXsScale, false);
        addMonthXsAnimation(this.mExtraMonthXsItems, xDelta, monthXsScale, true);
        startAnimation();
        this.mIsThumbKindChanged = false;
    }

    public void cancelAnimation() {
        this.mAnimationManager.cancel();
    }

    public void clearAnimInfo() {
        super.clearAnimInfo();
        this.mExtraMonthXsInfo = null;
    }

    public void clearPinchItems() {
        super.clearPinchItems();
        clearItems(this.mExtraMonthXsParent, this.mExtraMonthXsItems);
        this.mExtraMonthXsItems = null;
        this.mExtraMonthXsParent = null;
    }

    public void createMonthXsFakeView() {
        super.createMonthXsFakeView();
        if (this.mExtraMonthXsInfo != null) {
            if (this.mExtraMonthXsParent == null) {
                this.mExtraMonthXsParent = new FakeViewParent(getRecyclerView(), this.mLayoutManager, this.mPositionCache);
            }
            this.mExtraMonthXsParent.setRecyclerViewPool(getRecyclerView().getRecycledViewPool());
            int hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from());
            this.mLayoutManager.calculateToPositionOnCluster(this.mGridInfo.calcRealToGrid(), hintWidthSpace - ((int) getXDelta()));
            int i2 = this.mGridInfo.to();
            Iterator<PinchItem> it = this.mExtraMonthXsItems.iterator();
            while (it.hasNext()) {
                PinchItem next = it.next();
                next.setViewHolder(createConcatFakeViewHolder(this.mExtraMonthXsParent, next, i2));
                if (next.isData()) {
                    this.mLayoutManager.bindFakeHolder(next.getViewHolder(), next.getViewPosition(i2), i2);
                }
            }
            this.mLayoutManager.calculateToPositionOnCluster(this.mGridInfo.from(), hintWidthSpace);
        }
    }

    public void createPinchItems() {
        super.createPinchItems();
        if (this.mExtraMonthXsInfo != null) {
            this.mExtraMonthXsItems = new SingleGridItemBuilder(this.mLayoutManager, getRecyclerView(), this.mExtraMonthXsInfo).fake(true).concat(true).skipHeader(true).bitmapCache(this.mBitmapCache).build();
        }
    }

    public PinchAnimInfo getEndGridInfo() {
        PinchAnimInfo pinchAnimInfo = this.mExtraMonthXsInfo;
        if (pinchAnimInfo != null) {
            return pinchAnimInfo;
        }
        return super.getEndGridInfo();
    }

    public boolean hasMonthXsCache(ListViewHolder listViewHolder) {
        return false;
    }

    public void initAnimInfo() {
        if (PicturesPinchAnimationManagerV3.USE_MONTH_XS_TYPE) {
            GridInfo gridInfo = this.mGridInfo;
            if (gridInfo.isMonthForViewing(gridInfo.from())) {
                int[] pivotInfo = getPivotInfo();
                this.mGridInfo.useConcatThumbnailForce();
                this.mMonthXsInfo = createAnimInfo(this.mGridInfo.from(), pivotInfo);
                this.mExtraMonthXsInfo = createAnimInfo(this.mGridInfo.to(), pivotInfo);
                return;
            }
        }
        super.initAnimInfo();
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        super.onAnimationCompleted(z, z3);
        this.mLayoutManager.resetFakeCluster();
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        prepareCluster(i2, i7);
        preparePositionCache(i2, i7);
        this.mGridInfo.setActiveColumns(new int[]{i2, i7});
        initializeAlbumListAnim();
        super.onPrepareAnimation(i2, i7, i8);
    }

    public void onPrepareNoItemAnimation() {
        this.mIsRtl = getRecyclerView().getContext().getResources().getBoolean(R.bool.is_right_to_left);
        initializeAlbumListAnim();
        startNoItemSlideAnimation();
    }

    public void restoreBitmapFromCache(ListViewHolder listViewHolder) {
        super.restoreBitmapFromCache(listViewHolder);
    }

    public void setFakeViewLayoutAlpha() {
        super.setFakeViewLayoutAlpha();
        setFakeViewLayoutAlpha(this.mExtraMonthXsParent);
    }

    public void startAnimation() {
        this.mAnimationManager.addAnimations(getPropertyAnimators());
        this.mAnimationManager.setAnimationProgress(0.0f);
        this.mAnimationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                Log.d(AlbumsPaneSlideAnimationManagerV2.this.TAG, "SlideAnimation#cancel");
                AlbumsPaneSlideAnimationManagerV2.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManagerV2.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManagerV2.this.mLayoutManager.scrollToPositionWithOffset(AlbumsPaneSlideAnimationManagerV2.this.mScrollPosition, AlbumsPaneSlideAnimationManagerV2.this.mScrollOffset);
            }

            public void onAnimationEnd() {
                AlbumsPaneSlideAnimationManagerV2.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManagerV2.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManagerV2.this.mListener.onSlideEnd();
                AlbumsPaneSlideAnimationManagerV2.this.mLayoutManager.scrollToPositionWithOffset(AlbumsPaneSlideAnimationManagerV2.this.mScrollPosition, AlbumsPaneSlideAnimationManagerV2.this.mScrollOffset);
            }

            public void onAnimationStart() {
                AlbumsPaneSlideAnimationManagerV2.this.onAnimationStarted();
            }
        });
        this.mAnimationManager.playLayoutAnimation(false, 360);
    }

    public GalleryListView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void cacheBitmap() {
    }

    public PinchAnimationManager getAnimationManager() {
        return this;
    }
}
