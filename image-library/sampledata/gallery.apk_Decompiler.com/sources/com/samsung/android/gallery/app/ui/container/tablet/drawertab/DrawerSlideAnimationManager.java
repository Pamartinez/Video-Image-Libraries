package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import A.a;
import android.animation.Animator;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.pictures.SpannablePinchAnimationManager;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.app.ui.viewholders.MxAlbumsDividerViewHolder;
import com.samsung.android.gallery.support.helper.DrawerUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.animator.AnimationManager;
import com.samsung.android.gallery.widget.animator.HeightAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.CloneView;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import o6.B;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DrawerSlideAnimationManager extends SpannablePinchAnimationManager {
    private final AnimationManager mAnimationManager = new AnimationManager();
    private int mDrawerState;
    private boolean mIsOpening;
    GalleryListView mRecyclerView;
    private boolean mWithWidthAnimation;

    public DrawerSlideAnimationManager(PinchLayoutManager pinchLayoutManager, GalleryListView galleryListView) {
        super(pinchLayoutManager, (GridInfo.ClusterInfo) null, false);
        int i2;
        this.mLayoutManager = pinchLayoutManager;
        this.mRecyclerView = galleryListView;
        if (DrawerUtil.isDrawerDefaultOpen(galleryListView.getContext())) {
            i2 = 3;
        } else {
            i2 = 4;
        }
        this.mDrawerState = i2;
    }

    private float addFakeItemTransAnimator(ListViewHolder listViewHolder, RectF rectF, float f) {
        if (listViewHolder.getRootView() == null || rectF == null) {
            return 0.0f;
        }
        float widthDelta = (float) getWidthDelta();
        addAnimation(new TranslationAnimator(listViewHolder.getRootView(), new RectF(rectF.left - widthDelta, f, rectF.right - widthDelta, rectF.height() + f), rectF).setAnimationListener(new a(3, this)));
        return rectF.height();
    }

    private void addFakeView(float[] fArr) {
        if (fArr[1] <= ((float) this.mRecyclerView.getBottom()) && this.mLayoutManager.findLastCompletelyVisibleItemPosition() != this.mLayoutManager.getChildCount() - 1) {
            initFakeViewLayout();
            initFakeLayoutManager();
            float createBottomFakeView = createBottomFakeView(fArr[1]);
            initializeFakeViewWidthAnimation(fArr[0], true);
            if (createBottomFakeView > 0.0f) {
                shiftTransAnimatorsToRect(createBottomFakeView);
                float[] firstVisibleItemInfo = getFirstVisibleItemInfo();
                createTopFakeView((int) firstVisibleItemInfo[0], firstVisibleItemInfo[1] + createBottomFakeView);
                initializeFakeViewWidthAnimation(firstVisibleItemInfo[1], false);
            }
        }
    }

    private void addItemTransAnimator(ListViewHolder listViewHolder, float f) {
        int widthDelta;
        int viewType = listViewHolder.getViewType();
        if (listViewHolder.getRootView() != null && viewType != -3) {
            TranslationAnimator translationAnimator = new TranslationAnimator(listViewHolder.getRootView());
            if (!this.mIsRtl) {
                widthDelta = getWidthDelta();
            } else if (ViewHolderValue.isDivider(viewType)) {
                widthDelta = -getWidthDelta();
            } else {
                widthDelta = 0;
            }
            addAnimation(translationAnimator.translateXRelative((float) widthDelta).translateYRelative(f).setAnimationListener(new a(6, this)));
        }
    }

    private void addWidthHeightAnimator() {
        setHeaderViewGravityForRtl(true);
        addAnimation(new WidthAnimator(this.mHeaderView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.from()), this.mLayoutManager.getHeaderWidth(this.mGridInfo.to()), new c(4)).setAnimationListener(new a(2, this)));
        TextView headerDescriptionView = this.mLayoutManager.getHeaderDescriptionView();
        if (headerDescriptionView != null) {
            int maxLines = headerDescriptionView.getMaxLines();
            TextUtils.TruncateAt ellipsize = headerDescriptionView.getEllipsize();
            int headerDescriptionWidthOffset = this.mLayoutManager.getHeaderDescriptionWidthOffset();
            StaticLayout headerDescriptionFrom = getHeaderDescriptionFrom(headerDescriptionView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.from()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            StaticLayout headerDescriptionTo = getHeaderDescriptionTo(headerDescriptionView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.to()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            if (headerDescriptionFrom != null && headerDescriptionTo != null) {
                headerDescriptionView.setMaxLines(Math.min(headerDescriptionFrom.getLineCount(), headerDescriptionTo.getLineCount()));
                headerDescriptionView.setEllipsize(TextUtils.TruncateAt.END);
                addAnimation(new HeightAnimator(headerDescriptionView, headerDescriptionFrom.getHeight(), headerDescriptionTo.getHeight()).setAnimationListener(new d(maxLines, ellipsize)));
            }
        }
    }

    private void calculateToPosition(int i2) {
        this.mLayoutManager.calculateToPosition(i2);
    }

    private float createBottomFakeView(float f) {
        return this.mFakeLayoutManager.addFakeViewAtBottom(new PinchFakeLayoutManager.CalculateInfo(this.mGridInfo.to(), this.mLayoutManager.findLastVisibleItemPosition() + 1, f));
    }

    private void createTopFakeView(int i2, float f) {
        this.mFakeLayoutManager.clear();
        this.mFakeLayoutManager.addFakeViewAtTop(new PinchFakeLayoutManager.CalculateInfo(this.mGridInfo.to(), i2, f));
    }

    private float[] getFirstVisibleItemInfo() {
        float f;
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.mRecyclerView.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
        float f5 = (float) (findFirstVisibleItemPosition - 1);
        if (findViewHolderForAdapterPosition != null) {
            f = findViewHolderForAdapterPosition.itemView.getY();
        } else {
            f = 0.0f;
        }
        return new float[]{f5, f};
    }

    private int getFromWidthSpace(ListViewHolder listViewHolder) {
        if (ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            return this.mLayoutManager.getWidth() - this.mLayoutManager.getExtraStartPadding(this.mGridInfo.from());
        }
        return this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from());
    }

    private int getToWidthSpace(ListViewHolder listViewHolder) {
        if (ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            return this.mLayoutManager.getWidth() - this.mLayoutManager.getExtraStartPadding(this.mGridInfo.to());
        }
        return this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to());
    }

    private void initFakeLayoutManager() {
        if (this.mFakeLayoutManager == null) {
            this.mFakeLayoutManager = createFakeLayoutManager(this.mFakeViewParent);
        }
    }

    private void initializeFakeViewWidthAnimation(float f, boolean z) {
        Iterator<ListViewHolder> it = this.mFakeLayoutManager.getFakeViewHolders().iterator();
        int i2 = 0;
        int i7 = 0;
        boolean z3 = true;
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            int hintStartSpan = this.mPositionCache.getHintStartSpan(this.mLayoutManager, next.getViewPosition(), this.mGridInfo.to());
            if (!z3 && hintStartSpan <= 0) {
                i2++;
                i7 = 0;
            }
            RectF rect = getRect(this.mFakeLayoutManager.getDataRect(), i2, i7, 0.0f);
            if (rect != null) {
                if (z) {
                    f += addFakeItemTransAnimator(next, rect, f);
                } else {
                    f -= addFakeItemTransAnimator(next, rect, f - rect.height());
                }
            }
            addItemWidthAnimator(next, useWidthAnimation(next), true);
            i7++;
            z3 = false;
        }
    }

    private float[] initializeRecyclerViewWidthAnimation() {
        float f;
        float f5 = 0.0f;
        if (isHeaderViewShowingOnRecyclerView()) {
            addHeaderViewOffset();
            f = (float) this.mHeaderDiff;
        } else {
            f = 0.0f;
        }
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount(); i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null && !isInvisible(listViewHolder.getViewPosition())) {
                addItemTransAnimator(listViewHolder, f);
                f += addItemWidthAnimator(listViewHolder, useWidthAnimation(listViewHolder), false);
                f5 = (float) listViewHolder.getRootView().getBottom();
            }
        }
        return new float[]{f5, f + f5};
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addItemWidthAnimator$10(View view) {
        lambda$addDataAnimator$2(view, false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addItemWidthAnimator$6(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addItemWidthAnimator$8(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addMatchedDividerAnimator$13(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addMatchedDividerAnimator$15(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$addWidthHeightAnimator$1(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addWidthHeightAnimator$2(View view) {
        ViewUtils.setWidth(view, -1);
        setHeaderViewGravityForRtl(false);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addWidthHeightAnimator$3(int i2, TextUtils.TruncateAt truncateAt, View view) {
        TextView textView = (TextView) view;
        ViewUtils.setHeight(view, -2);
        textView.setMaxLines(i2);
        textView.setEllipsize(truncateAt);
    }

    private void preparePositionCache(int i2, int i7) {
        int i8;
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
        this.mPositionCache.getHintSpanCount(this.mLayoutManager, i2);
        calculateToPosition(i7);
        for (int i12 = 0; i12 <= i8; i12++) {
            this.mPositionCache.cacheViewSizeInfo(this.mLayoutManager, max + i12, i7);
        }
        this.mPositionCache.getHintSpanCount(this.mLayoutManager, i7);
        calculateToPosition(i2);
    }

    /* access modifiers changed from: private */
    public void resetItemAnimator() {
        Optional.ofNullable(this.mRecyclerView).ifPresent(new B(4));
    }

    private void setHeaderViewGravityForRtl(boolean z) {
        int i2;
        View view = this.mHeaderView;
        if (view != null && this.mIsRtl) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
                if (z) {
                    i2 = 8388613;
                } else {
                    i2 = -1;
                }
                layoutParams2.gravity = i2;
            }
        }
    }

    private void shiftTransAnimatorsToRect(float f) {
        Iterator<Animator> it = getPropertyAnimators().iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (next instanceof TranslationAnimator) {
                ((TranslationAnimator) next).addYOffset(f);
            }
        }
    }

    public void addBitmapToCache(ListViewHolder listViewHolder) {
        super.addBitmapToCache(listViewHolder);
        ListViewHolder[] folderImageHolders = listViewHolder.getFolderImageHolders();
        if (folderImageHolders != null) {
            for (ListViewHolder listViewHolder2 : folderImageHolders) {
                if (listViewHolder2 != null && listViewHolder2.getRootView().getVisibility() == 0) {
                    addBitmapToCache(listViewHolder2);
                }
            }
        }
    }

    public void addHeaderTranslateAnimator(View view, float f, float f5, float f8) {
        float f10;
        float f11;
        View view2 = this.mHeaderView;
        RectF rectF = new RectF(f, f5, 0.0f, 0.0f);
        if (this.mIsRtl) {
            f10 = 0.0f;
        } else {
            f10 = ((float) getWidthDelta()) + f;
        }
        if (this.mIsRtl) {
            f11 = f - ((float) getWidthDelta());
        } else {
            f11 = 0.0f;
        }
        addAnimation(new TranslationAnimator(view2, rectF, new RectF(f10, f8, f11, 0.0f)).setAnimationListener(new a(0, this)));
        addWidthHeightAnimator();
    }

    public void addHeaderView() {
        View view;
        View view2;
        if (!this.mWithWidthAnimation) {
            super.addHeaderView();
            if (this.mHeaderCloneView != null) {
                if (this.mHeaderView.getParent() == null) {
                    view = this.mHeaderView;
                } else {
                    view = (View) this.mHeaderView.getParent();
                }
                this.mHeaderCloneView.setSource(view);
            }
        } else if (isHeaderViewShowingOnRecyclerView()) {
            this.mHeaderCloneView = new CloneView(getRecyclerView().getContext());
            if (this.mHeaderView.getParent() == null) {
                view2 = this.mHeaderView;
            } else {
                view2 = (View) this.mHeaderView.getParent();
            }
            this.mHeaderCloneView.setSource(view2);
            addHeaderTranslateAnimator(this.mHeaderCloneView, (float) view2.getLeft(), (float) view2.getTop(), (float) view2.getTop());
            addHeaderViewAtFirst(this.mHeaderCloneView);
        }
    }

    public void addHeaderViewOffset() {
        if (this.mHeaderView != null && isFakeViewShiftedOnTop()) {
            this.mScrollOffset = (int) (getFakeHeaderViewTop(this.mHeaderView) + ((float) getHeaderViewHeight()) + ((float) this.mLayoutManager.getHeaderBottomMargin(this.mGridInfo.to())) + ((float) this.mScrollOffset));
        } else if (isHeaderViewShowingOnRecyclerView() && this.mHeaderView.getParent() != null) {
            this.mScrollOffset = (int) (((View) this.mHeaderView.getParent()).getY() + ((float) getHeaderViewHeight()) + ((float) this.mLayoutManager.getHeaderBottomMargin(this.mGridInfo.to())) + ((float) this.mScrollOffset));
        } else if (this.mLayoutManager.findFirstVisibleItemPosition() == 0) {
            this.mScrollOffset = this.mLayoutManager.getHeaderViewHeight() + this.mScrollOffset;
        }
    }

    public float addItemWidthAnimator(ListViewHolder listViewHolder, boolean z, boolean z3) {
        float f;
        float f5;
        View view;
        if (listViewHolder.getRootView() != null) {
            int fromWidthSpace = getFromWidthSpace(listViewHolder);
            int toWidthSpace = getToWidthSpace(listViewHolder);
            if (!z) {
                if (z3) {
                    f = ((float) fromWidthSpace) / ((float) toWidthSpace);
                } else {
                    f = 1.0f;
                }
                if (z3) {
                    f5 = 1.0f;
                } else {
                    f5 = ((float) toWidthSpace) / ((float) fromWidthSpace);
                }
                if (listViewHolder.getScalableView() != null) {
                    view = listViewHolder.getScalableView();
                } else {
                    view = listViewHolder.getRootView();
                }
                addAnimation(new ScaleAnimator(view, f, f5).pivotX(0.0f).pivotY(0.0f).setAnimationListener(new a(1, this)));
                return ((((float) toWidthSpace) / ((float) fromWidthSpace)) - 1.0f) * ((float) view.getHeight());
            } else if (ViewHolderValue.isDivider(listViewHolder.getViewType())) {
                View dividerView = listViewHolder.getDividerView();
                if (dividerView != null) {
                    int endMargin = ViewMarginUtils.getEndMargin(dividerView) + ViewMarginUtils.getStartMargin(dividerView);
                    addAnimation(new WidthAnimator(dividerView, fromWidthSpace - endMargin, toWidthSpace - endMargin, new c(0)).setAnimationListener(new c(1)));
                }
            } else {
                addAnimation(new WidthAnimator(listViewHolder.getRootView(), fromWidthSpace, toWidthSpace, new c(2)).setAnimationListener(new c(3)));
                return 0.0f;
            }
        }
        return 0.0f;
    }

    public void addMatchedDividerAnimator(ListViewHolder listViewHolder, ListViewHolder listViewHolder2, RectF rectF) {
        float f;
        ListViewHolder listViewHolder3 = listViewHolder;
        ListViewHolder listViewHolder4 = listViewHolder2;
        RectF rectF2 = rectF;
        RectF rect = getRect(listViewHolder4.getRootView());
        RectF rectF3 = new RectF(rectF2);
        RectF rectF4 = new RectF(rect);
        RectF rectF5 = new RectF(rectF2);
        View dividerView = listViewHolder4.getDividerView();
        View dividerView2 = listViewHolder3.getDividerView();
        if (!(dividerView == null || dividerView2 == null)) {
            int fromWidthSpace = getFromWidthSpace(listViewHolder);
            int toWidthSpace = getToWidthSpace(listViewHolder);
            int startMargin = ViewMarginUtils.getStartMargin(dividerView);
            int endMargin = ViewMarginUtils.getEndMargin(dividerView);
            int i2 = startMargin + endMargin;
            ViewMarginUtils.setHorizontalMargin(dividerView2, startMargin, endMargin);
            int i7 = fromWidthSpace - i2;
            int i8 = toWidthSpace - i2;
            int i10 = fromWidthSpace;
            int i11 = toWidthSpace;
            addAnimation(new WidthAnimator(dividerView, i7, i8, new c(5)).setAnimationListener(new c(6)));
            addAnimation(new WidthAnimator(dividerView2, i7, i8, new c(7)).setAnimationListener(new c(8)));
            ViewUtils.setVisibleOrGone(listViewHolder3.getDecoView(1), ViewUtils.isVisible(listViewHolder4.getDecoView(1)));
            ViewUtils.setVisibleOrGone(listViewHolder3.getCheckbox(), listViewHolder4.isCheckBoxEnabled());
            View decoView = listViewHolder3.getDecoView(27);
            float f5 = 1.0f;
            if (!PreferenceFeatures.isEnabled(PreferenceFeatures.EssentialAlbums)) {
                f = 0.0f;
            } else if (isSelectionMode()) {
                f = ViewUtils.getAlpha(listViewHolder4.getDecoView(27));
            } else {
                f = 1.0f;
            }
            ViewUtils.setAlpha(decoView, f);
            View decoView2 = listViewHolder3.getDecoView(19);
            if (isSelectionMode()) {
                f5 = ViewUtils.getAlpha(listViewHolder4.getDecoView(19));
            }
            ViewUtils.setAlpha(decoView2, f5);
            if (this.mIsRtl) {
                float f8 = (float) ((i10 - i11) + endMargin);
                rectF3.left = (-f8) + ((float) i2);
                rectF4.left = f8;
            }
        }
        addAnimation(new TranslationAnimator(listViewHolder4.getRootView(), rect, rectF3).setAnimationListener(new a(4, this)));
        addAnimation(new TranslationAnimator(listViewHolder3.getRootView(), rectF4, rectF5).setAnimationListener(new a(5, this)));
    }

    public boolean animWithUpdateLayoutParam(RectF[] rectFArr) {
        if (isAlbum()) {
            return false;
        }
        return super.animWithUpdateLayoutParam(rectFArr);
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.util.function.Consumer, java.lang.Object] */
    public void completeAnimation(boolean z, boolean z3, Runnable runnable) {
        if (!isHeaderViewShowingOnRecyclerView() && isFakeViewShiftedOnTop()) {
            boolean z7 = this.mHeaderCloneView == null;
            clearHeaderView();
            if (z7) {
                Optional.ofNullable(getRecyclerView().getAdapter()).ifPresent(new Object());
            }
            lambda$completeAnimation$12(z3, runnable);
        } else if (z || isHeaderViewShowingOnRecyclerView()) {
            lambda$completeAnimation$12(z3, runnable);
        } else {
            ThreadUtil.postOnUiThreadDelayed(new b(this, z3, runnable), 100);
        }
    }

    public PinchFakeLayoutManager createFakeLayoutManager(ViewGroup viewGroup) {
        PinchFakeLayoutManager createFakeLayoutManager = super.createFakeLayoutManager(viewGroup);
        createFakeLayoutManager.setViewHolderMarginBaseGrid(this.mGridInfo.from());
        return createFakeLayoutManager;
    }

    public ScaleAnimator createScaleAnimator(ListViewHolder listViewHolder, boolean z, RectF[] rectFArr, boolean z3) {
        View view;
        RectF rectF;
        TextView countView = listViewHolder.getCountView();
        if (listViewHolder.getScalableView() == null || countView != null) {
            view = listViewHolder.getRootView();
        } else {
            view = listViewHolder.getScalableView();
        }
        if (z) {
            rectF = rectFArr[1];
        } else {
            rectF = rectFArr[0];
        }
        return new ScaleAnimator(view, rectF, rectFArr[0], rectFArr[1]).enableUpdateLayoutParam(z3).addPadding(this.mFromPadding, this.mToPadding);
    }

    public ListViewHolder findMatchedDivider(ArrayList<ListViewHolder> arrayList, ListViewHolder listViewHolder) {
        if (!(listViewHolder instanceof MxAlbumsDividerViewHolder)) {
            return super.findMatchedDivider(arrayList, listViewHolder);
        }
        String title = ((MxAlbumsDividerViewHolder) listViewHolder).getTitle();
        Iterator<ListViewHolder> it = arrayList.iterator();
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            if ((next instanceof MxAlbumsDividerViewHolder) && TextUtils.equals(title, ((MxAlbumsDividerViewHolder) next).getTitle())) {
                arrayList.remove(next);
                return next;
            }
        }
        return null;
    }

    public void finishAnimation(boolean z) {
        onAnimationCompleted(true, false);
        this.mAnimationManager.clear();
        if (this.mWithWidthAnimation) {
            this.mRecyclerView.requestLayout();
        } else if (this.mIsOpening == z) {
            this.mLayoutManager.scrollToPositionWithOffset(this.mScrollPosition, this.mScrollOffset);
        }
        this.mRecyclerView.post(new e(this));
        this.mRecyclerView.setDrawerAnimating(false);
        this.mIsAnimating = false;
    }

    public final RecyclerView.ViewHolder getChildViewHolder(View view) {
        if (view != null) {
            return this.mRecyclerView.getChildViewHolder(view);
        }
        return null;
    }

    public float[] getFocusedPositionInfo(int i2) {
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition == null || !ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, this.mGridInfo.from()))) {
                findFirstVisibleItemPosition++;
            } else {
                return new float[]{(float) findFirstVisibleItemPosition, findViewByPosition.getY(), findViewByPosition.getX(), findViewByPosition.getY(), 0.0f};
            }
        }
        return new float[]{0.0f, (float) this.mLayoutManager.getHintViewHeight(0, i2), 0.0f, 0.0f, 0.0f};
    }

    public float getHeaderFromTop(View view) {
        if (view != null) {
            return (float) (-view.getHeight());
        }
        return 0.0f;
    }

    public int getHeaderStartPos(View view) {
        return this.mLayoutManager.getExtraStartPadding(this.mGridInfo.from()) + super.getHeaderStartPos(view);
    }

    public int getWidthDelta() {
        return this.mLayoutManager.getExtraStartPadding(this.mGridInfo.to()) - this.mLayoutManager.getExtraStartPadding(this.mGridInfo.from());
    }

    public void initPadding() {
        int margin = GridMarginHelper.getMargin(getRecyclerView(), this.mGridInfo.from());
        this.mToPadding = margin;
        this.mFromPadding = margin;
    }

    public void initValues(int i2, int i7) {
        this.mWithWidthAnimation = true;
        this.mGridInfo.set(i2, i7);
        this.mIsRtl = getRecyclerView().getContext().getResources().getBoolean(R.bool.is_right_to_left);
    }

    public boolean isInvisible(int i2) {
        if (i2 < this.mLayoutManager.findFirstVisibleItemPosition() || i2 > this.mLayoutManager.findLastVisibleItemPosition()) {
            return true;
        }
        return false;
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        try {
            if (this.mGridInfo.isRealRatio(i7) || this.mLayoutManager.isHintSpannable(i7)) {
                preparePositionCache(i2, i7);
            }
            super.onPrepareAnimation(i2, i7, i8);
        } catch (NullPointerException unused) {
        }
    }

    public void onPrepareWidthAnimation(int i2, int i7) {
        getRecyclerView().setClipChildren(false);
        initValues(i2, i7);
        initHeaderView();
        preparePositionCache(i2, i7);
        addFakeView(initializeRecyclerViewWidthAnimation());
        addHeaderView();
        startAnimation();
    }

    public void restoreBitmapFromCache(ListViewHolder listViewHolder) {
        super.restoreBitmapFromCache(listViewHolder);
        ListViewHolder[] folderImageHolders = listViewHolder.getFolderImageHolders();
        if (listViewHolder.getRootView().getVisibility() == 0 && folderImageHolders != null) {
            for (ListViewHolder listViewHolder2 : folderImageHolders) {
                if (listViewHolder2 != null && listViewHolder2.getRootView().getVisibility() == 0) {
                    restoreBitmapFromCache(listViewHolder2);
                }
            }
        }
    }

    public void setAnimationProgress(float f) {
        if (this.mIsAnimating) {
            if (!this.mIsOpening) {
                f = 1.0f - f;
            }
            this.mAnimationManager.setAnimationProgress(f);
        }
    }

    public void setOpening(boolean z) {
        this.mIsOpening = z;
    }

    public void setState(int i2) {
        this.mDrawerState = i2;
    }

    public void startAnimation() {
        this.mAnimationManager.addAnimations(getPropertyAnimators());
        this.mAnimationManager.setAnimationProgress(0.0f);
        setVisibilityForStartAnimation();
        this.mRecyclerView.removeItemAnimator();
        this.mRecyclerView.setDrawerAnimating(true);
        this.mIsAnimating = true;
    }

    public boolean supportDividerAnimation() {
        return true;
    }

    public boolean useWidthAnimation(ListViewHolder listViewHolder) {
        if (ViewHolderValue.isDivider(listViewHolder.getViewType()) || listViewHolder.getViewType() == 1 || ViewHolderValue.isHeader(listViewHolder.getViewType())) {
            return true;
        }
        return false;
    }

    public GalleryListView getRecyclerView() {
        return this.mRecyclerView;
    }

    /* access modifiers changed from: private */
    /* renamed from: completeAnimation */
    public void lambda$completeAnimation$12(boolean z, Runnable runnable) {
        if (SheetBehaviorCompat.isInTransition(this.mDrawerState)) {
            a.w(new StringBuilder("completeAnimation skipped. state="), this.mDrawerState, this.TAG);
            return;
        }
        this.mAnimationManager.getAnimators().forEach(new B(3));
        this.mAnimationManager.onAnimationEnd((Animator) null);
        setItemViewMargin();
        finishAnimation(z);
        runnable.run();
    }
}
