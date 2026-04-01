package com.samsung.android.gallery.app.ui.list.pictures;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.pinch.v3.BitmapCache;
import com.samsung.android.gallery.widget.listview.pinch.v3.DataItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.DividerItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.FakeViewParent;
import com.samsung.android.gallery.widget.listview.pinch.v3.FocusFrom;
import com.samsung.android.gallery.widget.listview.pinch.v3.FocusTo;
import com.samsung.android.gallery.widget.listview.pinch.v3.IFocused;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchAnimInfo;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.v3.PinchItemBuilder;
import com.samsung.android.gallery.widget.listview.pinch.v3.SingleGridItemBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import e5.C0451a;
import e5.h;
import e5.i;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesPinchAnimationManagerV3 extends PinchAnimationManager {
    protected static final boolean USE_MONTH_XS_TYPE = PreferenceFeatures.Poc.USE_CONCAT_THUMBNAIL;
    private final GridInfo mActualGridInfo = new GridInfo((GridInfo.ClusterInfo) null);
    protected final BitmapCache mBitmapCache;
    private final HashMap<Type, FakeViewParent> mFakeViewParents = new HashMap<>();
    private int mFinalGrid;
    private IFocused mFocusFrom;
    private final boolean mFromFlipCover;
    /* access modifiers changed from: private */
    public boolean mIsAnimating;
    protected boolean mIsThumbKindChanged;
    protected PinchAnimInfo mMonthXsInfo;
    protected final HashMap<Integer, PinchAnimInfo> mPinchAnimInfo = new HashMap<>();
    private final HashMap<Type, ArrayList<PinchItem>> mPinchItems = new HashMap<>();
    private Runnable mSingleGridAnimationEndAction;
    private PinchItem mStickDividerItem;
    private final boolean mSupportPivotOnFingerPos;
    private boolean mWriteDump;
    private PinchAnimInfo mYearAnimInfo;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Type {
        RECYCLER_VIEW,
        FAKE_NORMAL,
        FAKE_MONTH_XS,
        FAKE_YEAR
    }

    public PicturesPinchAnimationManagerV3(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z, boolean z3) {
        super(pinchLayoutManager, clusterInfo);
        this.mSupportPivotOnFingerPos = z;
        this.mFromFlipCover = z3;
        this.mBitmapCache = new BitmapCache();
    }

    private void addFadeInAnimation(Type type) {
        addAnimation((PropertyAnimator) new AlphaAnimator(getView(type), 0.0f, 1.0f));
    }

    private void addFadeOutAnimation(Type type) {
        addAnimation((PropertyAnimator) new AlphaAnimator(getView(type), 1.0f, 0.0f));
    }

    private void addItemAnimators(boolean z) {
        boolean z3;
        Iterator<PinchItem> it = getPinchItems(Type.FAKE_NORMAL).iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.isRelated(this.mGridInfo)) {
                if (next instanceof DividerItem) {
                    next.setCrossOver(isCrossOver(next));
                }
                next.setVisibility(true);
                z3 = z;
                addAnimation(next.getAnimators(this.mGridInfo, this.mLayoutManager, this.mShiftHeaderOffset, z3, new h(this, 1)));
            } else {
                z3 = z;
                next.setVisibility(false);
            }
            z = z3;
        }
    }

    private void addListAnimators() {
        if (this.mGridInfo.withYear()) {
            addListAnimators(Type.FAKE_YEAR);
        } else if (!USE_MONTH_XS_TYPE || !this.mGridInfo.withMonthForViewing()) {
            ViewUtils.setAlpha((View) getRecyclerView(), 1.0f);
            ViewUtils.setAlpha(this.mFakeViewParents.get(Type.FAKE_NORMAL), 1.0f);
        } else {
            addListAnimators(Type.FAKE_MONTH_XS);
        }
    }

    private void addMonthXsAnimators(boolean z) {
        boolean z3;
        if (USE_MONTH_XS_TYPE) {
            Iterator<PinchItem> it = getPinchItems(Type.FAKE_MONTH_XS).iterator();
            while (it.hasNext()) {
                PinchItem next = it.next();
                if (this.mGridInfo.withMonthForViewing()) {
                    next.setVisibility(true);
                    z3 = z;
                    addAnimation(next.getAnimators(this.mGridInfo, this.mLayoutManager, this.mShiftHeaderOffset, z3, new h(this, 0)));
                } else {
                    z3 = z;
                    next.setVisibility(false);
                }
                z = z3;
            }
        }
    }

    private void addMonthXsListAlphaAnimator() {
        HashMap<Type, FakeViewParent> hashMap = this.mFakeViewParents;
        Type type = Type.FAKE_MONTH_XS;
        if (hashMap.get(type) != null) {
            if (this.mGridInfo.fromMonthForViewing()) {
                addFadeOutAnimation(type);
                addFadeInAnimation(Type.RECYCLER_VIEW);
                addFadeInAnimation(Type.FAKE_NORMAL);
                return;
            }
            addFadeInAnimation(type);
            addFadeOutAnimation(Type.RECYCLER_VIEW);
            addFadeOutAnimation(Type.FAKE_NORMAL);
        } else if (this.mGridInfo.fromMonthForViewing()) {
            addFadeOutAnimation(Type.RECYCLER_VIEW);
            addFadeInAnimation(Type.FAKE_NORMAL);
        } else {
            addFadeInAnimation(Type.RECYCLER_VIEW);
            addFadeOutAnimation(Type.FAKE_NORMAL);
        }
    }

    private void addMonthXsListScaleAnimator() {
        HashMap<Type, FakeViewParent> hashMap = this.mFakeViewParents;
        Type type = Type.FAKE_MONTH_XS;
        if (hashMap.get(type) != null) {
            if (this.mGridInfo.fromMonthForViewing()) {
                addZoomOutAnimation(type);
                addZoomInAnimation(Type.RECYCLER_VIEW);
                addZoomInAnimation(Type.FAKE_NORMAL);
                return;
            }
            addZoomInAnimation(type);
            addZoomOutAnimation(Type.RECYCLER_VIEW);
            addZoomOutAnimation(Type.FAKE_NORMAL);
        } else if (this.mGridInfo.fromMonthForViewing()) {
            addZoomOutAnimation(Type.RECYCLER_VIEW);
            addZoomInAnimation(Type.FAKE_NORMAL);
        } else {
            addZoomInAnimation(Type.RECYCLER_VIEW);
            addZoomOutAnimation(Type.FAKE_NORMAL);
        }
    }

    private void addStickDividerAnimators(boolean z) {
        PinchItem pinchItem = this.mStickDividerItem;
        if (pinchItem != null) {
            addAnimation(pinchItem.getAnimators(this.mGridInfo, this.mLayoutManager, this.mShiftHeaderOffset, z, new h(this, 2)));
        }
    }

    private void addYearAnimators(boolean z) {
        boolean z3;
        Iterator<PinchItem> it = getPinchItems(Type.FAKE_YEAR).iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (this.mGridInfo.withYear()) {
                next.setVisibility(true);
                z3 = z;
                addAnimation(next.getAnimators(this.mGridInfo, this.mLayoutManager, this.mShiftHeaderOffset, z3, new h(this, 3)));
            } else {
                z3 = z;
                next.setVisibility(false);
            }
            z = z3;
        }
    }

    private void addYearListAlphaAnimator() {
        HashMap<Type, FakeViewParent> hashMap = this.mFakeViewParents;
        Type type = Type.FAKE_YEAR;
        if (hashMap.get(type) != null) {
            HashMap<Type, FakeViewParent> hashMap2 = this.mFakeViewParents;
            Type type2 = Type.FAKE_MONTH_XS;
            if (hashMap2.get(type2) != null) {
                addFadeOutAnimation(type2);
            } else {
                addFadeOutAnimation(Type.RECYCLER_VIEW);
                addFadeOutAnimation(Type.FAKE_NORMAL);
            }
            addFadeInAnimation(type);
            return;
        }
        addFadeOutAnimation(Type.RECYCLER_VIEW);
        HashMap<Type, FakeViewParent> hashMap3 = this.mFakeViewParents;
        Type type3 = Type.FAKE_MONTH_XS;
        if (hashMap3.get(type3) != null) {
            addFadeInAnimation(type3);
        } else {
            addFadeInAnimation(Type.FAKE_NORMAL);
        }
    }

    private void addYearListScaleAnimator() {
        HashMap<Type, FakeViewParent> hashMap = this.mFakeViewParents;
        Type type = Type.FAKE_YEAR;
        if (hashMap.get(type) != null) {
            HashMap<Type, FakeViewParent> hashMap2 = this.mFakeViewParents;
            Type type2 = Type.FAKE_MONTH_XS;
            if (hashMap2.get(type2) != null) {
                addZoomOutAnimation(type2);
            } else {
                addZoomOutAnimation(Type.RECYCLER_VIEW);
                addZoomOutAnimation(Type.FAKE_NORMAL);
            }
            addZoomInAnimation(type);
            return;
        }
        addZoomOutAnimation(Type.RECYCLER_VIEW);
        HashMap<Type, FakeViewParent> hashMap3 = this.mFakeViewParents;
        Type type3 = Type.FAKE_MONTH_XS;
        if (hashMap3.get(type3) != null) {
            addZoomInAnimation(type3);
        } else {
            addZoomInAnimation(Type.FAKE_NORMAL);
        }
    }

    private void addZoomInAnimation(Type type) {
        float f;
        ScaleAnimator scaleAnimator = new ScaleAnimator(getView(type), ((float) this.mGridInfo.getRealToGrid()) / ((float) this.mGridInfo.from()), 1.0f);
        if (this.mSupportPivotOnFingerPos) {
            f = this.mFocusFrom.getFocusedX();
        } else {
            f = (float) getHintPaddingLeft();
        }
        addAnimation((PropertyAnimator) scaleAnimator.pivotX(f).pivotY(this.mFocusFrom.getFocusedY()));
    }

    private void addZoomOutAnimation(Type type) {
        float f;
        ScaleAnimator scaleAnimator = new ScaleAnimator(getView(type), 1.0f, ((float) this.mGridInfo.from()) / ((float) this.mGridInfo.getRealToGrid()));
        if (this.mSupportPivotOnFingerPos) {
            f = this.mFocusFrom.getFocusedX();
        } else {
            f = (float) getHintPaddingLeft();
        }
        addAnimation((PropertyAnimator) scaleAnimator.pivotX(f).pivotY(this.mFocusFrom.getFocusedY()));
    }

    private SparseArray<Integer> createDividerGroup() {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        for (int i2 : this.mGridInfo.getActiveColumns()) {
            sparseArray.put(i2, Integer.valueOf(this.mGridInfo.isMonth(i2) ? 1 : 0));
        }
        return sparseArray;
    }

    private void createFakeView() {
        int i2;
        HashMap<Type, ArrayList<PinchItem>> hashMap = this.mPinchItems;
        Type type = Type.FAKE_NORMAL;
        if (hashMap.get(type) != null) {
            FakeViewParent fakeViewParent = this.mFakeViewParents.get(type);
            if (fakeViewParent == null) {
                fakeViewParent = new FakeViewParent(getRecyclerView(), this.mLayoutManager, this.mPositionCache);
            }
            fakeViewParent.setRecyclerViewPool(getRecyclerView().getRecycledViewPool());
            if (this.mFromFlipCover) {
                i2 = GridMarginHelper.getMarginInFlipCover(getRecyclerView(), getGridForCreateFakeViewHolder());
            } else {
                i2 = GridMarginHelper.getMargin(getRecyclerView(), getGridForCreateFakeViewHolder());
            }
            Iterator<PinchItem> it = getPinchItems(type).iterator();
            ListViewHolder listViewHolder = null;
            while (it.hasNext()) {
                PinchItem next = it.next();
                int intValue = ((Integer) Optional.ofNullable(next.getBaseGrid()).orElse(Integer.valueOf(getGridForCreateFakeViewHolder()))).intValue();
                if (next.isFakeView()) {
                    next.setViewHolder(createFakeViewHolder(fakeViewParent, next, intValue, i2));
                }
                if (next.isData()) {
                    if (listViewHolder == null) {
                        listViewHolder = createMatrixMakeHolder(fakeViewParent, next, intValue, i2);
                    }
                    ((DataItem) next).setMatrixMakeHolder(listViewHolder);
                }
            }
            this.mFakeViewParents.put(Type.FAKE_NORMAL, fakeViewParent);
        }
    }

    private ListViewHolder createFakeViewHolder(FakeViewParent fakeViewParent, PinchItem pinchItem, int i2, int i7) {
        boolean z;
        ListViewHolder createFakeViewHolder = fakeViewParent.createFakeViewHolder(pinchItem, i2, i7);
        if (pinchItem instanceof DividerItem) {
            if (!isSelectionMode() || isSingleSelectionMode()) {
                z = false;
            } else {
                z = true;
            }
            createFakeViewHolder.setCheckboxEnabled(1, z);
        }
        this.mLayoutManager.setViewHolderMargin(createFakeViewHolder, getGridForCreateFakeViewHolder());
        return createFakeViewHolder;
    }

    private ListViewHolder createMatrixMakeHolder(FakeViewParent fakeViewParent, PinchItem pinchItem, int i2, int i7) {
        ListViewHolder createFakeViewHolder = createFakeViewHolder(fakeViewParent, pinchItem, i2, i7);
        ViewUtils.removeSelf(createFakeViewHolder.itemView);
        return createFakeViewHolder;
    }

    private void createPinchItemsForClickedAnimation(PinchAnimInfo pinchAnimInfo, boolean z) {
        Type type;
        HashMap<Type, ArrayList<PinchItem>> hashMap = this.mPinchItems;
        if (z) {
            type = Type.FAKE_MONTH_XS;
        } else {
            type = Type.FAKE_NORMAL;
        }
        hashMap.put(type, createSingleGridItem(pinchAnimInfo, z));
    }

    private ArrayList<PinchItem> createSingleGridItem(PinchAnimInfo pinchAnimInfo, boolean z) {
        return new SingleGridItemBuilder(this.mLayoutManager, getRecyclerView(), pinchAnimInfo).fake(true).concat(z).bitmapCache(this.mBitmapCache).build();
    }

    private void createYearFakeView() {
        if (!this.mGridInfo.fromYear()) {
            HashMap<Type, ArrayList<PinchItem>> hashMap = this.mPinchItems;
            Type type = Type.FAKE_YEAR;
            if (hashMap.get(type) != null) {
                FakeViewParent fakeViewParent = this.mFakeViewParents.get(type);
                if (fakeViewParent == null) {
                    fakeViewParent = new FakeViewParent(getRecyclerView(), this.mLayoutManager, this.mPositionCache);
                }
                fakeViewParent.setRecyclerViewPool(getRecyclerView().getRecycledViewPool());
                int gridSize = this.mYearAnimInfo.getFocused().getGridSize();
                Iterator<PinchItem> it = getPinchItems(type).iterator();
                while (it.hasNext()) {
                    PinchItem next = it.next();
                    next.setViewHolder(createConcatFakeViewHolder(fakeViewParent, next, gridSize));
                    if (next.isData()) {
                        this.mLayoutManager.bindFakeHolder(next.getViewHolder(), next.getViewPosition(gridSize), gridSize);
                    }
                }
                this.mFakeViewParents.put(Type.FAKE_YEAR, fakeViewParent);
            }
        }
    }

    private IFocused getFocus(int i2) {
        if (!this.mGridInfo.isFrom(i2)) {
            return new FocusTo.Finder(this.mLayoutManager, this.mFocusFrom).target(i2).useConcatThumbnail(this.mGridInfo.fromConcatThumbnail()).find();
        }
        return this.mFocusFrom;
    }

    private int getGridForCreateFakeViewHolder() {
        if (!USE_MONTH_XS_TYPE) {
            boolean fromYear = this.mGridInfo.fromYear();
            GridInfo gridInfo = this.mGridInfo;
            if (fromYear) {
                return gridInfo.to();
            }
            return gridInfo.from();
        } else if (this.mGridInfo.fromMonthForViewing() || this.mGridInfo.fromYear()) {
            return this.mGridInfo.getMinMonth();
        } else {
            return this.mGridInfo.from();
        }
    }

    private int getHintPaddingLeft() {
        return this.mLayoutManager.getHintPaddingLeft(this.mFocusFrom.getGridSize());
    }

    private ThumbKind getThumbKind(int i2) {
        if (this.mGridInfo.isRealRatio(i2)) {
            return ThumbKind.XLARGE_NC_KIND;
        }
        if (this.mGridInfo.isMonth(i2)) {
            return ThumbKind.SMALL_CROP_KIND;
        }
        return ThumbKind.MEDIUM_KIND;
    }

    private View getView(Type type) {
        Object obj;
        if (type == Type.RECYCLER_VIEW) {
            obj = getRecyclerView();
        } else {
            obj = this.mFakeViewParents.get(type);
        }
        return (View) obj;
    }

    private boolean hasValidMonthXsInfo() {
        if (!USE_MONTH_XS_TYPE || this.mMonthXsInfo == null || !this.mGridInfo.isMonth(this.mFinalGrid)) {
            return false;
        }
        return true;
    }

    private void initMonthAnimInfo(float[] fArr) {
        findFocus(fArr);
        for (int i2 : this.mGridInfo.getActiveColumns()) {
            if (this.mGridInfo.isMonth(i2)) {
                this.mPinchAnimInfo.put(Integer.valueOf(i2), createAnimInfo(i2, (int[]) null));
            }
        }
    }

    private boolean isCrossOver(PinchItem pinchItem) {
        RectF rect = pinchItem.getRect(this.mGridInfo.from());
        RectF rect2 = pinchItem.getRect(this.mGridInfo.to());
        Iterator<PinchItem> it = getPinchItems(Type.FAKE_NORMAL).iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            RectF rect3 = next.getRect(this.mGridInfo.from());
            RectF rect4 = next.getRect(this.mGridInfo.to());
            if (!(rect == null || rect2 == null || rect3 == null || rect4 == null)) {
                float f = rect.top;
                float f5 = rect3.top;
                if (f > f5 && rect2.top < rect4.top) {
                    return true;
                }
                if (f < f5 && rect2.top > rect4.top) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addItemAnimators$3() {
        this.mWriteDump = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addMonthXsAnimators$5() {
        this.mWriteDump = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addStickDividerAnimators$4() {
        this.mWriteDump = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addYearAnimators$6() {
        this.mWriteDump = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getPivotInfo$0(int i2) {
        return !this.mGridInfo.isRealRatio(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getPivotInfo$1(int i2) {
        return !this.mGridInfo.isYear(i2);
    }

    private void resetListView() {
        ViewUtils.setAlpha((View) getRecyclerView(), 1.0f);
        resetScale((View) getRecyclerView());
    }

    private void runAnimatorsForYearOrMonthForViewingClicked(int i2) {
        AnimatorSet duration = new AnimatorSet().setDuration((long) i2);
        duration.playTogether(new ArrayList(getPropertyAnimators()));
        duration.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                PicturesPinchAnimationManagerV3.this.endYearOrMonthForViewingAnimation();
                PicturesPinchAnimationManagerV3.this.mIsAnimating = false;
            }
        });
        duration.start();
    }

    private void runSingleGridAnimationEndAction() {
        Runnable runnable = this.mSingleGridAnimationEndAction;
        if (runnable != null) {
            runnable.run();
            this.mSingleGridAnimationEndAction = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: singleGridAnimationEndAction */
    public void lambda$addListAnimators$2(Type type) {
        if (this.mFakeViewParents.get(type) != null) {
            ViewUtils.setAlpha(this.mFakeViewParents.get(type), 1.0f);
        } else {
            ViewUtils.setAlpha(this.mFakeViewParents.get(Type.FAKE_NORMAL), 1.0f);
        }
        resetScale((View) getRecyclerView());
        for (FakeViewParent resetScale : this.mFakeViewParents.values()) {
            resetScale(resetScale);
        }
    }

    private void startFadeInAnimation() {
        ListViewHolder listViewHolder;
        int childCount = this.mLayoutManager.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            if (!(childAt == null || (listViewHolder = (ListViewHolder) getChildViewHolder(childAt)) == null)) {
                addDecoViewFadeInAnimation(arrayList, listViewHolder);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(150).playTogether(arrayList);
        animatorSet.start();
    }

    private void updateEndPosition() {
        PinchAnimInfo endGridInfo = getEndGridInfo();
        if (endGridInfo != null) {
            this.mScrollPosition = endGridInfo.getRange().getStartPosition();
            this.mScrollOffset = (int) endGridInfo.getRange().getStartY();
        }
    }

    private void updateFocusedX(FocusFrom focusFrom) {
        int hintDataCountInView;
        if (this.mGridInfo.fromConcatThumbnail() && (hintDataCountInView = this.mLayoutManager.getHintDataCountInView(focusFrom.getViewPosition(), focusFrom.getGridSize())) < focusFrom.getGridSize()) {
            focusFrom.setFocusedX(Math.min(focusFrom.getFocusedX(), (float) (this.mLayoutManager.getHintColumnSpan(focusFrom.getViewPosition(), focusFrom.getGridSize()) * (hintDataCountInView - 1))));
        }
    }

    private void updateThumbKindChanged() {
        boolean z;
        boolean z3 = this.mIsThumbKindChanged;
        if (this.mGridInfo.withMonth() || this.mGridInfo.withRealRatio()) {
            z = true;
        } else {
            z = false;
        }
        this.mIsThumbKindChanged = z3 | z;
    }

    public void addAnimators(boolean z) {
        calculateHeaderHeightDelta();
        updateActualGridInfo();
        addStickDividerAnimators(z);
        addMonthXsAnimators(z);
        addYearAnimators(z);
        addListAnimators();
        addItemAnimators(z);
        if (z) {
            startAnimation();
        } else {
            updateAnimation();
        }
        updateThumbKindChanged();
    }

    public void addDecoViewFadeInAnimation(ArrayList<Animator> arrayList, ListViewHolder listViewHolder) {
        View decoView = listViewHolder.getDecoView(11);
        if (decoView != null) {
            arrayList.add(new AlphaAnimator(decoView, 0.0f, 1.0f));
        }
    }

    public void animationCompletedInternal(boolean z, boolean z3) {
        int i2;
        GridInfo gridInfo = this.mActualGridInfo;
        if (z3) {
            i2 = gridInfo.from();
        } else {
            i2 = gridInfo.to();
        }
        this.mFinalGrid = i2;
        super.animationCompletedInternal(z, z3);
        resetListView();
        updateEndPosition();
        clearAnimInfo();
        clearPinchItems();
    }

    public void cacheBitmap() {
        MediaItem mediaItem;
        Iterator<PinchItem> it = getPinchItems(Type.FAKE_NORMAL).iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.isData() && (mediaItem = next.getMediaItem(this.mFinalGrid, this.mLayoutManager)) != null) {
                putToBitmapKeyHolder(mediaItem, getThumbKind(this.mFinalGrid));
            }
        }
        Iterator<PinchItem> it2 = getPinchItems(Type.FAKE_MONTH_XS).iterator();
        while (it2.hasNext()) {
            ListViewHolder viewHolder = it2.next().getViewHolder();
            if (viewHolder != null && viewHolder.hasBitmap()) {
                BitmapCache bitmapCache = this.mBitmapCache;
                bitmapCache.put("3/" + viewHolder.getViewPosition(), viewHolder.getBitmap());
            }
        }
        Iterator<PinchItem> it3 = getPinchItems(Type.FAKE_YEAR).iterator();
        while (it3.hasNext()) {
            ListViewHolder viewHolder2 = it3.next().getViewHolder();
            if (viewHolder2 != null && viewHolder2.hasBitmap()) {
                BitmapCache bitmapCache2 = this.mBitmapCache;
                bitmapCache2.put("4/" + viewHolder2.getViewPosition(), viewHolder2.getBitmap());
            }
        }
    }

    public void calculateHeaderHeightDelta() {
        for (ArrayList<PinchItem> it : this.mPinchItems.values()) {
            Iterator it2 = it.iterator();
            while (true) {
                if (it2.hasNext()) {
                    PinchItem pinchItem = (PinchItem) it2.next();
                    if (pinchItem.isRelated(this.mGridInfo) && pinchItem.isHeader()) {
                        this.mShiftHeaderOffset = pinchItem.getHeightDelta(this.mLayoutManager, this.mGridInfo);
                        return;
                    }
                }
            }
        }
    }

    public void clearAnimInfo() {
        this.mPinchAnimInfo.clear();
        this.mMonthXsInfo = null;
        this.mYearAnimInfo = null;
    }

    public void clearBitmapCache() {
        super.clearBitmapCache();
        this.mBitmapCache.clear();
    }

    public void clearItems(FakeViewParent fakeViewParent, ArrayList<PinchItem> arrayList) {
        if (arrayList != null) {
            Iterator<PinchItem> it = arrayList.iterator();
            while (it.hasNext()) {
                PinchItem next = it.next();
                next.onAnimationCompleted();
                if (fakeViewParent != null && next.isFakeView()) {
                    fakeViewParent.recycleHolder(next.getViewHolder());
                }
                next.setVisibility(true);
            }
            arrayList.clear();
            ViewUtils.removeAllViews(fakeViewParent);
            ViewUtils.removeSelf(fakeViewParent);
        }
    }

    public void clearPinchItems() {
        PinchItem pinchItem = this.mStickDividerItem;
        if (pinchItem != null) {
            pinchItem.onAnimationCompleted();
            this.mStickDividerItem = null;
        }
        for (Type type : Type.values()) {
            clearItems(this.mFakeViewParents.get(type), this.mPinchItems.get(type));
        }
        this.mPinchItems.clear();
        this.mFakeViewParents.clear();
    }

    public PinchAnimInfo createAnimInfo(int i2, int[] iArr) {
        int i7;
        int i8;
        if (this.mFromFlipCover) {
            i7 = GridMarginHelper.getMarginInFlipCover(getRecyclerView(), i2);
        } else {
            i7 = GridMarginHelper.getMargin(getRecyclerView(), i2);
        }
        if (this.mFromFlipCover) {
            i8 = GridMarginHelper.getMarginInFlipCover(getRecyclerView(), getGridForCreateFakeViewHolder());
        } else {
            i8 = GridMarginHelper.getMargin(getRecyclerView(), getGridForCreateFakeViewHolder());
        }
        return new PinchAnimInfo.Builder(this.mLayoutManager, this.mPositionCache, getFocus(i2), this.mGridInfo).itemGap(i7, i8).shiftToTop(!this.mGridInfo.isFrom(i2)).pivotInfo(iArr).build();
    }

    public ListViewHolder createConcatFakeViewHolder(FakeViewParent fakeViewParent, PinchItem pinchItem, int i2) {
        ListViewHolder createFakeViewHolder = fakeViewParent.createFakeViewHolder(pinchItem, i2, 0);
        createFakeViewHolder.setFakePosition(pinchItem.getViewPosition(i2));
        this.mLayoutManager.setViewHolderMargin(createFakeViewHolder, i2);
        return createFakeViewHolder;
    }

    public void createMonthXsFakeView() {
        if (!this.mGridInfo.fromMonthForViewing()) {
            HashMap<Type, ArrayList<PinchItem>> hashMap = this.mPinchItems;
            Type type = Type.FAKE_MONTH_XS;
            if (hashMap.get(type) != null) {
                FakeViewParent fakeViewParent = this.mFakeViewParents.get(type);
                if (fakeViewParent == null) {
                    fakeViewParent = new FakeViewParent(getRecyclerView(), this.mLayoutManager, this.mPositionCache);
                }
                fakeViewParent.setRecyclerViewPool(getRecyclerView().getRecycledViewPool());
                int gridSize = this.mMonthXsInfo.getFocused().getGridSize();
                Iterator<PinchItem> it = getPinchItems(type).iterator();
                while (it.hasNext()) {
                    PinchItem next = it.next();
                    next.setViewHolder(createConcatFakeViewHolder(fakeViewParent, next, gridSize));
                    if (next.isData()) {
                        this.mLayoutManager.bindFakeHolder(next.getViewHolder(), next.getViewPosition(gridSize), gridSize);
                    }
                }
                this.mFakeViewParents.put(Type.FAKE_MONTH_XS, fakeViewParent);
            }
        }
    }

    public void createPinchItems() {
        this.mPinchItems.put(Type.FAKE_NORMAL, new PinchItemBuilder(this.mLayoutManager, getRecyclerView(), this.mPinchAnimInfo).base(getGridForCreateFakeViewHolder()).setBitmapCache(this.mBitmapCache).dividerGroup(createDividerGroup()).build());
        if (this.mMonthXsInfo != null) {
            this.mPinchItems.put(Type.FAKE_MONTH_XS, new SingleGridItemBuilder(this.mLayoutManager, getRecyclerView(), this.mMonthXsInfo).fake(!this.mGridInfo.fromMonthForViewing()).concat(true).bitmapCache(this.mBitmapCache).build());
        }
        if (this.mYearAnimInfo != null) {
            this.mPinchItems.put(Type.FAKE_YEAR, new SingleGridItemBuilder(this.mLayoutManager, getRecyclerView(), this.mYearAnimInfo).fake(!this.mGridInfo.fromYear()).concat(true).bitmapCache(this.mBitmapCache).build());
        }
    }

    public void findFocus(float[] fArr) {
        FocusFrom find = new FocusFrom.Finder(this.mLayoutManager).find(fArr, this.mGridInfo.from());
        updateFocusedX(find);
        this.mFocusFrom = find;
    }

    public PinchAnimInfo getEndGridInfo() {
        PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(this.mFinalGrid));
        if (pinchAnimInfo != null) {
            return pinchAnimInfo;
        }
        if (hasValidMonthXsInfo()) {
            return this.mMonthXsInfo;
        }
        return this.mYearAnimInfo;
    }

    public ArrayList<PinchItem> getPinchItems(Type type) {
        return (ArrayList) Optional.ofNullable(this.mPinchItems.get(type)).orElse(new ArrayList());
    }

    public int[] getPivotInfo() {
        if (!this.mSupportPivotOnFingerPos) {
            return null;
        }
        int orElse = Arrays.stream(this.mGridInfo.getActiveColumns()).filter(new i(this, 0)).min().orElse(0);
        int orElse2 = Arrays.stream(this.mGridInfo.getActiveColumns()).filter(new i(this, 1)).max().orElse(0);
        return new int[]{orElse, orElse2, (int) ((this.mFocusFrom.getFocusedX() / ((float) this.mLayoutManager.getWidth())) * ((float) orElse2))};
    }

    public boolean hasMonthXsCache(ListViewHolder listViewHolder) {
        if (!USE_MONTH_XS_TYPE || listViewHolder.getViewType() != 3) {
            return false;
        }
        return true;
    }

    public void initAnimInfo() {
        int[] pivotInfo = getPivotInfo();
        for (int i2 : this.mGridInfo.getActiveColumns()) {
            if (this.mLayoutManager.hasCluster(i2)) {
                if (this.mGridInfo.isYear(i2)) {
                    this.mYearAnimInfo = createAnimInfo(i2, pivotInfo);
                } else if (!USE_MONTH_XS_TYPE || !this.mGridInfo.isMonthForViewing(i2)) {
                    this.mPinchAnimInfo.put(Integer.valueOf(i2), createAnimInfo(i2, pivotInfo));
                } else {
                    this.mMonthXsInfo = createAnimInfo(i2, pivotInfo);
                }
            }
        }
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public boolean isThumbKindChanged() {
        return this.mIsThumbKindChanged;
    }

    public void onAnimationStarted() {
        super.onAnimationStarted();
        for (ArrayList<PinchItem> it : this.mPinchItems.values()) {
            Iterator it2 = it.iterator();
            while (it2.hasNext()) {
                ((PinchItem) it2.next()).onAnimationStarted();
            }
        }
        stopPreview();
    }

    public void onDump(PrintWriter printWriter, String str) {
        if (this.mWriteDump) {
            Logger.dumpLog(printWriter, "=== pinch animation ==");
            Logger.dumpLog(printWriter, "GridInfo : " + this.mGridInfo.from() + " > " + this.mGridInfo.to() + ", final : " + this.mFinalGrid);
            StringBuilder sb2 = new StringBuilder("From");
            sb2.append(this.mFocusFrom.getLog());
            Logger.dumpLog(printWriter, sb2.toString());
            for (int valueOf : this.mGridInfo.getActiveColumns()) {
                PinchAnimInfo pinchAnimInfo = this.mPinchAnimInfo.get(Integer.valueOf(valueOf));
                if (pinchAnimInfo != null) {
                    Logger.dumpLog(printWriter, "Target" + pinchAnimInfo.getFocused().getLog());
                    Logger.dumpLog(printWriter, pinchAnimInfo.getRange().getLog());
                    Logger.dumpLog(printWriter, pinchAnimInfo.getRectMap().getLog());
                }
            }
            Logger.dumpLog(printWriter, "=== pinch animation end ==");
        }
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            startFadeInAnimation();
            setItemViewMargin();
            super.onLayout();
        }
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        super.onPrepareAnimation(i2, i7, i8);
        findFocus(getFocusXY());
        if (!this.mLayoutManager.hasHeader() || this.mFocusFrom.getViewPosition() != 0) {
            initAnimInfo();
            createPinchItems();
            createMonthXsFakeView();
            createYearFakeView();
            createFakeView();
            addAnimators(true);
            setFakeViewLayoutAlpha();
        }
    }

    public void restoreBitmapFromCache(ListViewHolder listViewHolder) {
        if (listViewHolder != null && (listViewHolder.getViewType() == 4 || hasMonthXsCache(listViewHolder))) {
            BitmapCache bitmapCache = this.mBitmapCache;
            Bitmap bitmap = bitmapCache.get(listViewHolder.getViewType() + "/" + listViewHolder.getViewPosition());
            if (bitmap != null) {
                listViewHolder.bindImage(bitmap);
            }
        } else if (listViewHolder == null || listViewHolder.getMediaItem() == null) {
            super.restoreBitmapFromCache(listViewHolder);
        } else {
            Bitmap bitmap2 = this.mBitmapCache.get(listViewHolder.getMediaItem(), listViewHolder.getThumbKind());
            if (bitmap2 != null) {
                listViewHolder.bindImage(bitmap2);
            } else {
                super.restoreBitmapFromCache(listViewHolder);
            }
        }
    }

    public void setFakeViewLayoutAlpha() {
        for (FakeViewParent fakeViewLayoutAlpha : this.mFakeViewParents.values()) {
            setFakeViewLayoutAlpha(fakeViewLayoutAlpha);
        }
    }

    public void startMonthForViewingClickedAnimation(int i2, RectF rectF) {
        if (!this.mIsAnimating) {
            getRecyclerView().setClipChildren(false);
            this.mIsAnimating = true;
            this.mGridInfo.updateMonthForViewingToMonth();
            initAnimInfo(this.mLayoutManager.getHintViewPosition(i2, this.mGridInfo.to()), rectF, Type.FAKE_NORMAL);
            createPinchItemsForClickedAnimation(this.mPinchAnimInfo.get(Integer.valueOf(this.mGridInfo.to())), false);
            createFakeView();
            addAnimators(true);
            setFakeViewLayoutAlpha();
            runAnimatorsForYearOrMonthForViewingClicked(250);
        }
    }

    public void startYearClickedAnimation(int i2, RectF rectF) {
        if (!this.mIsAnimating) {
            getRecyclerView().setClipChildren(false);
            this.mIsAnimating = true;
            this.mGridInfo.updateYearToMonth();
            if (USE_MONTH_XS_TYPE) {
                initAnimInfo(i2, rectF, Type.FAKE_MONTH_XS);
                createPinchItemsForClickedAnimation(this.mMonthXsInfo, true);
            } else {
                initAnimInfo(i2, rectF, Type.FAKE_NORMAL);
                createPinchItemsForClickedAnimation(this.mPinchAnimInfo.get(Integer.valueOf(this.mGridInfo.to())), false);
            }
            createMonthXsFakeView();
            createFakeView();
            addAnimators(true);
            setFakeViewLayoutAlpha();
            runAnimatorsForYearOrMonthForViewingClicked(350);
        }
    }

    public void updateActualGridInfo() {
        this.mActualGridInfo.set(this.mGridInfo);
    }

    public void updateAnimator(int i2, int i7) {
        int[] activeColumns = this.mGridInfo.getActiveColumns();
        this.mGridInfo.set(activeColumns[i2], activeColumns[i7]);
        clearAnimators();
        runSingleGridAnimationEndAction();
        addAnimators(false);
    }

    public void setFakeViewLayoutAlpha(FakeViewParent fakeViewParent) {
        if (fakeViewParent != null) {
            fakeViewParent.setVisibility(0);
            fakeViewParent.setAlpha(0.0f);
            fakeViewParent.post(new C0451a(3, fakeViewParent));
        }
    }

    private void addListAnimators(Type type) {
        boolean z;
        if (type == Type.FAKE_YEAR) {
            addYearListAlphaAnimator();
            addYearListScaleAnimator();
            z = this.mGridInfo.fromYear();
        } else {
            addMonthXsListAlphaAnimator();
            addMonthXsListScaleAnimator();
            z = this.mGridInfo.fromMonthForViewing();
        }
        if (z) {
            GridInfo gridInfo = this.mGridInfo;
            gridInfo.set(gridInfo.to(), this.mGridInfo.to());
        } else {
            GridInfo gridInfo2 = this.mGridInfo;
            gridInfo2.set(gridInfo2.from(), this.mGridInfo.from());
        }
        this.mSingleGridAnimationEndAction = new com.samsung.o3dp.lib3dphotography.i(9, this, type);
    }

    private void initAnimInfo(final int i2, final RectF rectF, Type type) {
        this.mFocusFrom = new IFocused() {
            public float getFocusedX() {
                return rectF.centerX();
            }

            public float getFocusedY() {
                return rectF.centerY();
            }

            public int getGridSize() {
                return PicturesPinchAnimationManagerV3.this.mGridInfo.to();
            }

            public float getScrollOffset() {
                return rectF.top;
            }

            public int getViewPosition() {
                return i2;
            }
        };
        int margin = GridMarginHelper.getMargin(getRecyclerView(), this.mGridInfo.to());
        if (type == Type.FAKE_MONTH_XS) {
            this.mMonthXsInfo = new PinchAnimInfo.Builder(this.mLayoutManager, this.mPositionCache, this.mFocusFrom, this.mGridInfo).itemGap(margin, margin).shiftToTop(true).build();
        } else {
            this.mPinchAnimInfo.put(Integer.valueOf(this.mGridInfo.to()), new PinchAnimInfo.Builder(this.mLayoutManager, this.mPositionCache, this.mFocusFrom, this.mGridInfo).itemGap(margin, margin).shiftToTop(true).build());
        }
    }

    public void startMonthForViewingClickedAnimation(int i2, float f, float f5) {
        if (!this.mIsAnimating) {
            getRecyclerView().setClipChildren(false);
            this.mIsAnimating = true;
            updateColumns();
            this.mGridInfo.updateMonthForViewingToMonth();
            initMonthAnimInfo(new float[]{f, f5});
            createPinchItems();
            createFakeView();
            addAnimators(true);
            setFakeViewLayoutAlpha();
            runAnimatorsForYearOrMonthForViewingClicked(250);
        }
    }
}
