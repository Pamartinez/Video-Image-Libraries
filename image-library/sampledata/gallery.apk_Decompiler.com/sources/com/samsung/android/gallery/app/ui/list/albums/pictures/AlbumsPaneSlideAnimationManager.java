package com.samsung.android.gallery.app.ui.list.albums.pictures;

import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.albums.pictures.IAlbumsPaneSlideAnimationManager;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.AnimationManager;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AlbumsPaneSlideAnimationManager extends PicturesPinchAnimationManager implements IAlbumsPaneSlideAnimationManager {
    private final View mAlbumList;
    /* access modifiers changed from: private */
    public final AnimationManager mAnimationManager = new AnimationManager();
    private int mHeaderFromHeight;
    private int mHeaderToHeight;
    /* access modifiers changed from: private */
    public final IAlbumsPaneSlideAnimationManager.OnSlideAnimationListener mListener;
    private final GalleryListView mRecyclerView;
    private final Supplier<Boolean> mSplitModeSupplier;

    public AlbumsPaneSlideAnimationManager(AlbumPicturesLayoutManager albumPicturesLayoutManager, GalleryListView galleryListView, View view, Supplier<Boolean> supplier, IAlbumsPaneSlideAnimationManager.OnSlideAnimationListener onSlideAnimationListener) {
        super(albumPicturesLayoutManager, (GridInfo.ClusterInfo) null);
        this.mLayoutManager = albumPicturesLayoutManager;
        this.mRecyclerView = galleryListView;
        this.mAlbumList = view;
        this.mListener = onSlideAnimationListener;
        this.mSplitModeSupplier = supplier;
    }

    private void addHeightAnimation() {
        TextView headerDescriptionView = this.mLayoutManager.getHeaderDescriptionView();
        if (headerDescriptionView != null && this.mShiftHeaderOffset != 0) {
            addAnimation(new HeightAnimator(headerDescriptionView, this.mHeaderFromHeight, this.mHeaderToHeight).setAnimationListener(new c(2)));
        }
    }

    private void addWidthAnimation(PinchItem pinchItem, int i2, int i7) {
        addAnimation(new WidthAnimator(pinchItem.getView(), i2, i7, new c(0)).setAnimationListener(new c(1)));
    }

    private void calculateToPosition(int i2) {
        this.mLayoutManager.calculateToPosition(i2);
    }

    private void initializeAlbumListAnim() {
        float x9;
        int width;
        int width2;
        boolean z = false;
        if (!this.mGridInfo.hasNoTargetGrid()) {
            z = GridValue.isSplit(this.mGridInfo.to(), false, false);
        } else if (!this.mSplitModeSupplier.get().booleanValue()) {
            z = true;
        }
        RectF rectF = new RectF(this.mAlbumList.getX(), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight());
        if (!this.mIsRtl) {
            x9 = this.mAlbumList.getX();
            if (z) {
                width = this.mAlbumList.getWidth();
                addAnimation((PropertyAnimator) new TranslationAnimator(this.mAlbumList, rectF, new RectF(x9 + ((float) width), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight())));
            }
            width2 = this.mAlbumList.getWidth();
        } else {
            x9 = this.mAlbumList.getX();
            if (z) {
                width2 = this.mAlbumList.getWidth();
            } else {
                width = this.mAlbumList.getWidth();
                addAnimation((PropertyAnimator) new TranslationAnimator(this.mAlbumList, rectF, new RectF(x9 + ((float) width), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight())));
            }
        }
        width = -width2;
        addAnimation((PropertyAnimator) new TranslationAnimator(this.mAlbumList, rectF, new RectF(x9 + ((float) width), this.mAlbumList.getY(), (float) this.mAlbumList.getWidth(), (float) this.mAlbumList.getHeight())));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addWidthAnimation$1(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addWidthAnimation$2(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        view.setLayoutParams(layoutParams);
    }

    private void preparePositionCache(int i2, int i7) {
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

    private void startNoItemSlideAnimation() {
        this.mAnimationManager.addAnimations(getPropertyAnimators());
        this.mAnimationManager.setAnimationProgress(0.0f);
        this.mAnimationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                AlbumsPaneSlideAnimationManager.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManager.this.mAnimationManager.clear();
            }

            public void onAnimationEnd() {
                AlbumsPaneSlideAnimationManager.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManager.this.mListener.onSlideEnd();
            }

            public void onAnimationStart() {
            }
        });
        this.mAnimationManager.playLayoutAnimation(false, 360);
    }

    private void startSlideAnimation() {
        this.mAnimationManager.addAnimations(getPropertyAnimators());
        this.mAnimationManager.setAnimationProgress(0.0f);
        this.mAnimationManager.setAnimationListener(new AnimationManager.AnimationListener() {
            public void onAnimationCancel() {
                Log.d(AlbumsPaneSlideAnimationManager.this.TAG, "SlideAnimation#cancel");
                AlbumsPaneSlideAnimationManager.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManager.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManager.this.mLayoutManager.scrollToPositionWithOffset(AlbumsPaneSlideAnimationManager.this.mScrollPosition, AlbumsPaneSlideAnimationManager.this.mScrollOffset);
            }

            public void onAnimationEnd() {
                AlbumsPaneSlideAnimationManager.this.onAnimationCompleted(true, false);
                AlbumsPaneSlideAnimationManager.this.mAnimationManager.clear();
                AlbumsPaneSlideAnimationManager.this.mListener.onSlideEnd();
                AlbumsPaneSlideAnimationManager.this.mLayoutManager.scrollToPositionWithOffset(AlbumsPaneSlideAnimationManager.this.mScrollPosition, AlbumsPaneSlideAnimationManager.this.mScrollOffset);
            }

            public void onAnimationStart() {
                AlbumsPaneSlideAnimationManager.this.onAnimationStarted();
                AlbumsPaneSlideAnimationManager.this.mListener.onSlideStart();
            }
        });
        this.mAnimationManager.playLayoutAnimation(false, 360);
    }

    public void calculateHeaderViewSpace(ArrayList<PinchItem> arrayList) {
        TextView headerDescriptionView;
        if (!arrayList.isEmpty() && ViewHolderValue.isHeader(arrayList.get(0).getItemViewType()) && (headerDescriptionView = this.mLayoutManager.getHeaderDescriptionView()) != null) {
            int maxLines = headerDescriptionView.getMaxLines();
            int headerDescriptionWidthOffset = this.mLayoutManager.getHeaderDescriptionWidthOffset();
            TextUtils.TruncateAt ellipsize = headerDescriptionView.getEllipsize();
            int hintWidthSpace = (this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to()) - (this.mLayoutManager.getSpacing(this.mGridInfo.to()) * 2)) - headerDescriptionWidthOffset;
            StaticLayout headerDescriptionFrom = getHeaderDescriptionFrom(headerDescriptionView, (this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from()) - (this.mLayoutManager.getSpacing(this.mGridInfo.from()) * 2)) - headerDescriptionWidthOffset, maxLines, ellipsize);
            StaticLayout headerDescriptionTo = getHeaderDescriptionTo(headerDescriptionView, hintWidthSpace, maxLines, ellipsize);
            if (headerDescriptionFrom != null && headerDescriptionTo != null) {
                this.mHeaderFromHeight = headerDescriptionFrom.getHeight();
                int height = headerDescriptionTo.getHeight();
                this.mHeaderToHeight = height;
                this.mShiftHeaderOffset = height - this.mHeaderFromHeight;
            }
        }
    }

    public void cancelAnimation() {
        this.mAnimationManager.cancel();
    }

    public void createViewAnim(PinchItem pinchItem) {
        if (ViewHolderValue.isHeader(pinchItem.getItemViewType())) {
            int hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from()) - (this.mLayoutManager.getSpacing(this.mGridInfo.from()) * 2);
            int hintWidthSpace2 = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to()) - (this.mLayoutManager.getSpacing(this.mGridInfo.getRealToGrid()) * 2);
            RectF rectF = new RectF(pinchItem.getToRect());
            rectF.offset((float) this.mLayoutManager.getSpacing(this.mGridInfo.getRealToGrid()), 0.0f);
            TranslationAnimator translationAnimator = new TranslationAnimator(pinchItem.getView(), pinchItem.getFromRect(), rectF);
            translationAnimator.setAnimationListener(new b(this));
            addAnimation((PropertyAnimator) translationAnimator);
            if (hintWidthSpace != hintWidthSpace2) {
                addWidthAnimation(pinchItem, hintWidthSpace, hintWidthSpace2);
                addHeightAnimation();
                return;
            }
            return;
        }
        super.createViewAnim(pinchItem);
    }

    public View findFocusView() {
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int childCount = this.mLayoutManager.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            if (childAt != null && findFirstVisibleItemPosition == this.mLayoutManager.getPosition(childAt)) {
                return childAt;
            }
        }
        return this.mLayoutManager.getChildAt(0);
    }

    public int getAnimationType() {
        return this.mGridInfo.isToRealRatio() ? 1 : 0;
    }

    public final RecyclerView.ViewHolder getChildViewHolder(View view) {
        if (view != null) {
            return this.mRecyclerView.getChildViewHolder(view);
        }
        return null;
    }

    public void initializeAnimation() {
        super.initializeAnimation();
        initializeAlbumListAnim();
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        preparePositionCache(i2, i7);
        super.onPrepareAnimation(i2, i7, i8);
    }

    public void onPrepareNoItemAnimation() {
        this.mIsRtl = getRecyclerView().getContext().getResources().getBoolean(R.bool.is_right_to_left);
        initializeAlbumListAnim();
        startNoItemSlideAnimation();
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder, int i2) {
        if (getAdapter() != null) {
            getAdapter().setViewHolderMargin(listViewHolder, i2, GridMarginHelper.getMargin(getRecyclerView(), this.mGridInfo.from()));
        }
    }

    public void startAnimation() {
        startSlideAnimation();
    }

    public GalleryListView getRecyclerView() {
        return this.mRecyclerView;
    }

    public PinchAnimationManager getAnimationManager() {
        return this;
    }
}
