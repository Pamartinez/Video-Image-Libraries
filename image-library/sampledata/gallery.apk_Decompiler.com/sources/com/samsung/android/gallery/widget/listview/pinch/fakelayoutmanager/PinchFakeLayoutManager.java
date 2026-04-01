package com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager;

import D6.a;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$bool;
import com.samsung.android.gallery.widget.abstraction.ViewPoolInjector;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchFakeLayoutManager {
    protected CalculateRange mCalculateRange = new CalculateRange(-1, -1);
    private final HashMap<Integer, ArrayList<RectF>> mDataPositionRectMap = new HashMap<>();
    private final HashMap<Integer, ArrayList<RectF>> mDividerPositionRectMap = new HashMap<>();
    private final HashMap<Integer, ArrayList<RectF>> mFakeDataPositionRectMap = new HashMap<>();
    private final HashMap<Integer, ArrayList<RectF>> mFakeDividerPositionRectMap = new HashMap<>();
    private final ArrayList<ListViewHolder> mFakeViewHolders = new ArrayList<>();
    private final ViewGroup mFakeViewLayout;
    protected final boolean mIsRtl;
    protected float mLastBottom;
    protected float mLastHeight;
    protected Float mLastTop;
    protected final PinchLayoutManager mLayoutManager;
    protected final AnimPositionCache mPositionCache;
    protected final IPinchRecyclerView mRecyclerView;
    private final int mRecyclerViewBottom;
    protected final SparseArray<ListViewHolder> mRefHolders = new SparseArray<>();
    protected float mSpanSize;
    private int mStartDataRow;
    private int mStartDividerRow;
    private boolean mUseViewPool = true;
    private int mViewHolderMarginBaseGrid = -1;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CalculateInfo {
        private final int mGrid;
        private boolean mIsCalculateOnly;
        private final float mOffset;
        private int mViewPosition;

        public CalculateInfo(int i2, int i7, float f) {
            this.mGrid = i2;
            this.mViewPosition = i7;
            this.mOffset = f;
        }

        /* access modifiers changed from: private */
        public void setCalculateOnly() {
            this.mIsCalculateOnly = true;
        }

        public void decreaseViewPosition() {
            this.mViewPosition--;
        }

        public int getGridSize() {
            return this.mGrid;
        }

        public float getOffset() {
            return this.mOffset;
        }

        public int getViewPosition() {
            return this.mViewPosition;
        }

        public boolean isCalculateOnly() {
            return this.mIsCalculateOnly;
        }

        public void updateViewPositionToFirstData(PinchLayoutManager pinchLayoutManager) {
            int childCount = pinchLayoutManager.getChildCount();
            while (true) {
                int i2 = this.mViewPosition;
                if (i2 < childCount && !PinchFakeLayoutManager.isData(pinchLayoutManager.getHintItemViewType(i2, this.mGrid))) {
                    this.mViewPosition++;
                } else {
                    return;
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class CalculateRange {
        private int mBottom;
        private int mTop;

        public CalculateRange(int i2, int i7) {
            this.mTop = i2;
            this.mBottom = i7;
        }

        public boolean calculateBottomEnough(int i2) {
            if (this.mBottom < i2) {
                return true;
            }
            return false;
        }

        public boolean calculateTopEnough(int i2) {
            if (this.mTop < i2) {
                return true;
            }
            return false;
        }

        public void updateBottom(int i2) {
            int i7 = this.mBottom;
            if (i7 > 0) {
                this.mBottom = Math.max(i7, i2);
            }
        }

        public void updateTop(int i2) {
            int i7 = this.mTop;
            if (i7 > 0) {
                this.mTop = Math.max(i7, i2);
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RefHolderValues {
        final int mHeight;
        final int mItemViewType;
        protected final int mSpanIndex;
        final float mStartX;
        final int mWidth;

        public RefHolderValues(ListViewHolder listViewHolder, PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, int i2, int i7, float f, int i8) {
            this.mItemViewType = pinchLayoutManager.getHintItemViewType(i2, i7);
            this.mSpanIndex = animPositionCache.getHintStartSpan(pinchLayoutManager, i2, i7);
            this.mWidth = getItemViewWidth(listViewHolder, f, i2, i7);
            this.mHeight = getItemViewHeight(listViewHolder, f, i2, i7);
            this.mStartX = getXPosition(animPositionCache.getHintColumnSpan(pinchLayoutManager, i2, i7), i8, f, i7);
        }

        private boolean isRealRatioData(int i2) {
            if (!PinchFakeLayoutManager.isData(this.mItemViewType) || !PinchFakeLayoutManager.this.isRealRatio(i2)) {
                return false;
            }
            return true;
        }

        public int getItemViewHeight(ListViewHolder listViewHolder, float f, int i2, int i7) {
            if ((PinchFakeLayoutManager.isData(this.mItemViewType) && (PinchFakeLayoutManager.this.isRealRatio(i7) || PinchFakeLayoutManager.this.isYearData(this.mItemViewType))) || this.mItemViewType == -2) {
                PinchFakeLayoutManager pinchFakeLayoutManager = PinchFakeLayoutManager.this;
                return pinchFakeLayoutManager.mPositionCache.getHintViewHeight(pinchFakeLayoutManager.mLayoutManager, i2, i7);
            } else if (listViewHolder.getRootView().getHeight() > 0) {
                return listViewHolder.getRootView().getHeight();
            } else {
                PinchFakeLayoutManager pinchFakeLayoutManager2 = PinchFakeLayoutManager.this;
                return pinchFakeLayoutManager2.mPositionCache.getHintViewHeight(pinchFakeLayoutManager2.mLayoutManager, i2, i7);
            }
        }

        public int getItemViewWidth(ListViewHolder listViewHolder, float f, int i2, int i7) {
            if (isRealRatioData(i7)) {
                PinchFakeLayoutManager pinchFakeLayoutManager = PinchFakeLayoutManager.this;
                return pinchFakeLayoutManager.mPositionCache.getHintColumnSpan(pinchFakeLayoutManager.mLayoutManager, i2, i7);
            } else if (listViewHolder.getRootView().getWidth() > 0) {
                return listViewHolder.getRootView().getWidth();
            } else {
                PinchFakeLayoutManager pinchFakeLayoutManager2 = PinchFakeLayoutManager.this;
                float spanSize = pinchFakeLayoutManager2.getSpanSize(i7, pinchFakeLayoutManager2.mLayoutManager.getHintSpanCount(i7));
                PinchFakeLayoutManager pinchFakeLayoutManager3 = PinchFakeLayoutManager.this;
                return (int) (spanSize * ((float) pinchFakeLayoutManager3.mPositionCache.getHintColumnSpan(pinchFakeLayoutManager3.mLayoutManager, i2, i7)));
            }
        }

        public float getXPosition(int i2, int i7, float f, int i8) {
            int i10;
            PinchFakeLayoutManager pinchFakeLayoutManager = PinchFakeLayoutManager.this;
            if (!pinchFakeLayoutManager.mIsRtl || pinchFakeLayoutManager.isYearData(this.mItemViewType)) {
                i10 = this.mSpanIndex;
            } else if (ViewHolderValue.isDivider(this.mItemViewType)) {
                i10 = 0;
            } else {
                i10 = (i7 - this.mSpanIndex) - i2;
            }
            return (f * ((float) i10)) + ((float) PinchFakeLayoutManager.this.mLayoutManager.getHintPaddingLeft(i8));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RelativeY {
        int mData;
        int mDivider;
        boolean mIsDataActivated;

        public RelativeY(int i2, int i7) {
            this.mData = i2;
            this.mDivider = i7;
        }

        public void decreaseY(boolean z) {
            if (z) {
                this.mData--;
            } else {
                this.mDivider--;
            }
        }

        public int getActivatedY() {
            if (this.mIsDataActivated) {
                return this.mData;
            }
            return this.mDivider;
        }

        public void increaseY(boolean z) {
            if (z) {
                this.mData++;
            } else {
                this.mDivider++;
            }
        }

        public int positiveSum() {
            return Math.abs(this.mData + this.mDivider);
        }

        public void setDataActivated(boolean z) {
            this.mIsDataActivated = z;
        }
    }

    public PinchFakeLayoutManager(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, ViewGroup viewGroup, AnimPositionCache animPositionCache) {
        this.mLayoutManager = pinchLayoutManager;
        this.mRecyclerView = iPinchRecyclerView;
        this.mFakeViewLayout = viewGroup;
        this.mPositionCache = animPositionCache;
        this.mIsRtl = iPinchRecyclerView.getResources().getBoolean(R$bool.is_right_to_left);
        this.mRecyclerViewBottom = iPinchRecyclerView.getBottom();
    }

    private float addFakeViews(CalculateInfo calculateInfo) {
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, calculateInfo.getGridSize());
        this.mSpanSize = getSpanSize(calculateInfo.getGridSize(), hintSpanCount);
        if (!calculateInfo.isCalculateOnly()) {
            this.mLayoutManager.preparePinchAnimation(GridValue.revert(calculateInfo.getGridSize()));
        }
        float createBottomFromTarget = createBottomFromTarget(calculateInfo, hintSpanCount);
        calculateInfo.decreaseViewPosition();
        float createTopFromTarget = createTopFromTarget(calculateInfo, hintSpanCount, createBottomFromTarget);
        if (!calculateInfo.isCalculateOnly()) {
            createExtraBottom(calculateInfo.getGridSize(), hintSpanCount, calculateInfo.getOffset(), createTopFromTarget);
        }
        return createTopFromTarget;
    }

    private boolean addLeft(ArrayList<RectF> arrayList, int i2) {
        if (arrayList.size() >= i2) {
            return false;
        }
        RectF rectF = arrayList.get(0);
        if (this.mIsRtl) {
            float f = rectF.right;
            arrayList.add(0, new RectF(f, rectF.top, rectF.width() + f, rectF.bottom));
            return true;
        }
        arrayList.add(0, new RectF(rectF.left - rectF.width(), rectF.top, rectF.left, rectF.bottom));
        return true;
    }

    private boolean addRight(ArrayList<RectF> arrayList, int i2) {
        if (arrayList.size() >= i2) {
            return false;
        }
        RectF rectF = (RectF) C0212a.i(arrayList, 1);
        if (this.mIsRtl) {
            arrayList.add(new RectF(rectF.left - rectF.width(), rectF.top, rectF.left, rectF.bottom));
        } else {
            float f = rectF.right;
            arrayList.add(new RectF(f, rectF.top, rectF.width() + f, rectF.bottom));
        }
        return true;
    }

    private void clearReferences() {
        for (int i2 = 0; i2 < this.mRefHolders.size(); i2++) {
            SparseArray<ListViewHolder> sparseArray = this.mRefHolders;
            recycleHolder(sparseArray.get(sparseArray.keyAt(i2)));
        }
        this.mRefHolders.clear();
    }

    private ListViewHolder createAndBindFakeViewHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolder = createFakeViewHolder(i2, i7);
        createFakeViewHolder.setFakePosition(i2);
        if (isGridData(createFakeViewHolder.getViewType())) {
            this.mLayoutManager.updateViewSize(createFakeViewHolder.getRootView(), createFakeViewHolder.getViewType(), i7);
            this.mLayoutManager.bindHolder(createFakeViewHolder, i2);
            return createFakeViewHolder;
        }
        this.mLayoutManager.bindFakeHolder(createFakeViewHolder, i2, i7);
        return createFakeViewHolder;
    }

    private float createBottomFromTarget(CalculateInfo calculateInfo, int i2) {
        return createBottomFromTarget(calculateInfo, i2, 0, 0, (float) this.mRecyclerViewBottom);
    }

    private void createExtraBottom(int i2, int i7, float f, float f5) {
        int viewPosition;
        float f8 = f - f5;
        if (f8 > 0.0f && this.mLastBottom + f8 > ((float) this.mRecyclerViewBottom) && (viewPosition = ((ListViewHolder) C0212a.i(this.mFakeViewHolders, 1)).getViewPosition()) <= this.mLayoutManager.getHintViewCount(i2) - 2) {
            int intValue = getMaxKey(getFakeDataRect()).intValue();
            int intValue2 = getMaxKey(getFakeDividerRect()).intValue();
            int i8 = viewPosition + 1;
            if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, i8, i2) == 0) {
                this.mLastBottom += this.mLastHeight;
                if (isData(this.mLayoutManager.getHintItemViewType(i8, i2))) {
                    intValue++;
                } else {
                    intValue2++;
                }
            }
            createBottomFromTarget(new CalculateInfo(i2, i8, this.mLastBottom), i7, intValue, intValue2, ((float) this.mRecyclerViewBottom) + f8);
        }
    }

    private float createFakeViewAtBottom(CalculateInfo calculateInfo) {
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, calculateInfo.getGridSize());
        float spanSize = getSpanSize(calculateInfo.getGridSize(), hintSpanCount);
        float offset = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        int i2 = 0;
        float f = 0.0f;
        while (viewPosition < this.mLayoutManager.getHintViewCount(calculateInfo.getGridSize())) {
            ListViewHolder refViewHolder = this.getRefViewHolder(viewPosition, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                viewPosition++;
            } else {
                PinchFakeLayoutManager pinchFakeLayoutManager = this;
                RefHolderValues refHolderValues = new RefHolderValues(refViewHolder, this.mLayoutManager, this.mPositionCache, viewPosition, calculateInfo.getGridSize(), spanSize, hintSpanCount);
                if (refHolderValues.mSpanIndex == 0 && viewPosition != calculateInfo.getViewPosition()) {
                    float f5 = f + ((float) ((ViewGroup.MarginLayoutParams) refViewHolder.getRootView().getLayoutParams()).topMargin) + offset;
                    if (f5 > ((float) pinchFakeLayoutManager.mRecyclerViewBottom)) {
                        return 0.0f;
                    }
                    i2++;
                    offset = f5;
                }
                pinchFakeLayoutManager.addFakeViewHolder(calculateInfo.getGridSize(), viewPosition, false);
                pinchFakeLayoutManager.putRectToDataHashMap(i2, pinchFakeLayoutManager.getRectF(offset, refHolderValues));
                f = (float) refHolderValues.mHeight;
                viewPosition++;
                this = pinchFakeLayoutManager;
            }
        }
        PinchFakeLayoutManager pinchFakeLayoutManager2 = this;
        return (((float) (pinchFakeLayoutManager2.mRecyclerViewBottom - pinchFakeLayoutManager2.mRecyclerView.getPaddingBottom())) - offset) - f;
    }

    private void createFakeViewAtTop(CalculateInfo calculateInfo) {
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, calculateInfo.getGridSize());
        float spanSize = getSpanSize(calculateInfo.getGridSize(), hintSpanCount);
        float offset = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        int i2 = 0;
        while (viewPosition >= 0) {
            ListViewHolder refViewHolder = this.getRefViewHolder(viewPosition, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                viewPosition++;
            } else {
                PinchFakeLayoutManager pinchFakeLayoutManager = this;
                RefHolderValues refHolderValues = new RefHolderValues(refViewHolder, this.mLayoutManager, this.mPositionCache, viewPosition, calculateInfo.getGridSize(), spanSize, hintSpanCount);
                if (refHolderValues.mSpanIndex == 0) {
                    if (offset >= 0.0f) {
                        offset -= (float) refHolderValues.mHeight;
                        if (viewPosition != calculateInfo.getViewPosition()) {
                            i2++;
                        }
                    } else {
                        return;
                    }
                }
                pinchFakeLayoutManager.addFakeViewHolder(calculateInfo.getGridSize(), viewPosition, false);
                pinchFakeLayoutManager.putRectToDataHashMap(i2, pinchFakeLayoutManager.getRectF(offset, refHolderValues));
                viewPosition--;
                this = pinchFakeLayoutManager;
            }
        }
    }

    private ListViewHolder createFakeViewHolderInternal(int i2, int i7) {
        ListViewHolder listViewHolder = (ListViewHolder) this.mRecyclerView.getRecycledViewPool().getRecycledView(i7);
        if (listViewHolder == null) {
            return this.mLayoutManager.createHintListViewHolder(this.mFakeViewLayout, i7, i2);
        }
        return listViewHolder;
    }

    private void createReferenceViewHolders(CalculateInfo calculateInfo) {
        calculateInfo.updateViewPositionToFirstData(this.mLayoutManager);
        putHolder(calculateInfo.getGridSize(), -4);
        putHolder(calculateInfo.getGridSize(), -2);
        putHolder(calculateInfo.getGridSize(), -1);
        putDataHolder(calculateInfo.getViewPosition(), calculateInfo.getGridSize());
        ViewGroup viewGroup = (ViewGroup) this.mRecyclerView.getParent();
        measureView(this.mFakeViewLayout, viewGroup.getWidth(), viewGroup.getHeight());
    }

    private ListViewHolder createViewHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(i2, i7);
        View rootView = createFakeViewHolderInternal.getRootView();
        removeIfHasParent(rootView, createFakeViewHolderInternal.getViewType());
        this.mFakeViewLayout.addView(rootView);
        return createFakeViewHolderInternal;
    }

    private int expandRectMap(int i2, int i7, int[] iArr, HashMap<Integer, ArrayList<RectF>> hashMap) {
        int i8 = 0;
        for (Map.Entry next : hashMap.entrySet()) {
            if (((ArrayList) next.getValue()).size() > i7) {
                return 0;
            }
            i8 = expandRectMap(hashMap, (Integer) next.getKey(), (ArrayList) next.getValue(), i2, i7, iArr);
        }
        return i8;
    }

    private int getHeightMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    private Integer getMaxKey(HashMap<Integer, ArrayList<RectF>> hashMap) {
        return hashMap.keySet().stream().max(new a(26)).orElse(0);
    }

    private int getRealGridSize(int i2) {
        return GridValue.revert(i2);
    }

    private int getWidthMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    private void initValues(CalculateRange calculateRange) {
        this.mStartDataRow = 1;
        this.mLastBottom = 0.0f;
        if (calculateRange == null) {
            calculateRange = new CalculateRange(-1, -1);
        }
        this.mCalculateRange = calculateRange;
        if (this.mFakeViewLayout.getChildCount() > 0) {
            ViewUtils.removeAllViews(this.mFakeViewLayout);
        }
    }

    public static boolean isData(int i2) {
        if (i2 < 0 || i2 > 4) {
            return false;
        }
        return true;
    }

    private boolean isRowChanged(int i2, int i7) {
        if (i2 >= this.mLayoutManager.getHintViewCount(i7) - 1 || this.mPositionCache.getHintStartSpan(this.mLayoutManager, i2 + 1, i7) > 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isYearData(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    private void measureView(View view, int i2, int i7) {
        view.measure(getWidthMeasureSpec(i2), getHeightMeasureSpec(i7));
        view.layout(0, 0, i2, i7);
    }

    private void putHolder(int i2, int i7) {
        this.mRefHolders.put(i7, createViewHolder(i2, i7));
    }

    private void putRectToDataHashMap(int i2, RectF rectF) {
        putRect(this.mDataPositionRectMap, rectF, i2, false);
    }

    private void putRectToHashMap(RelativeY relativeY, boolean z, boolean z3, float f, RefHolderValues refHolderValues) {
        putRect(getPositionMap(relativeY.mIsDataActivated, z), getRectF(f, refHolderValues), relativeY.getActivatedY(), z3);
    }

    private void putRecycledViewPool(RecyclerView.RecycledViewPool recycledViewPool, ListViewHolder listViewHolder) {
        if (recycledViewPool != null) {
            ViewPoolInjector.inject(recycledViewPool, listViewHolder, listViewHolder.getViewType());
        }
    }

    private void recycleHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null) {
            View rootView = listViewHolder.getRootView();
            ViewUtils.removeView((ViewGroup) rootView.getParent(), rootView);
            listViewHolder.recycle();
            if (this.mUseViewPool) {
                putRecycledViewPool(this.mRecyclerView.getRecycledViewPool(), listViewHolder);
            }
        }
    }

    private void removeIfHasParent(View view, int i2) {
        if (view.getParent() != null) {
            Log.e("PinchFakeLayoutManager", "invalid parent - remove from parent : " + view.getParent() + " , " + i2);
            ViewUtils.removeSelf(view);
            new InternalException("createFakeViewHolderInternal - invalid parent").post();
        }
    }

    public float addFakeView(CalculateInfo calculateInfo, CalculateRange calculateRange) {
        initValues(calculateRange);
        createReferenceViewHolders(calculateInfo);
        float addFakeViews = addFakeViews(calculateInfo);
        clearReferences();
        return addFakeViews;
    }

    public float addFakeViewAtBottom(CalculateInfo calculateInfo) {
        this.mUseViewPool = false;
        initValues((CalculateRange) null);
        createReferenceViewHolders(calculateInfo);
        float createFakeViewAtBottom = createFakeViewAtBottom(calculateInfo);
        clearReferences();
        return createFakeViewAtBottom;
    }

    public void addFakeViewAtTop(CalculateInfo calculateInfo) {
        this.mUseViewPool = false;
        initValues((CalculateRange) null);
        createReferenceViewHolders(calculateInfo);
        createFakeViewAtTop(calculateInfo);
        clearReferences();
    }

    public void addFakeViewHolder(int i2, int i7, boolean z) {
        if (z) {
            this.mFakeViewHolders.add(0, createAndBindFakeViewHolder(i7, i2));
        } else {
            this.mFakeViewHolders.add(createAndBindFakeViewHolder(i7, i2));
        }
    }

    public int calculateRectMap(CalculateInfo calculateInfo, int i2, int[] iArr) {
        createReferenceViewHolders(calculateInfo);
        calculateInfo.setCalculateOnly();
        addFakeViews(calculateInfo);
        clearReferences();
        int revert = GridValue.revert(i2);
        int max = Math.max(calculateInfo.getGridSize(), revert);
        return Math.max(expandRectMap(calculateInfo.getGridSize(), max, iArr, this.mDataPositionRectMap), expandRectMap(revert, max, iArr, this.mFakeDataPositionRectMap));
    }

    public void clear() {
        Iterator<ListViewHolder> it = this.mFakeViewHolders.iterator();
        while (it.hasNext()) {
            recycleHolder(it.next());
        }
        this.mFakeViewHolders.clear();
        this.mDataPositionRectMap.clear();
        this.mDividerPositionRectMap.clear();
        this.mFakeDataPositionRectMap.clear();
        this.mFakeDividerPositionRectMap.clear();
    }

    public ListViewHolder createFakeViewHolder(int i2, int i7) {
        int i8;
        int hintItemViewType = this.mLayoutManager.getHintItemViewType(i2, i7);
        ListViewHolder createViewHolder = createViewHolder(i7, hintItemViewType);
        View rootView = createViewHolder.getRootView();
        ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
        if (isGridData(hintItemViewType)) {
            layoutParams.width = this.mLayoutManager.getHintWidthSpace(i7) / getRealGridSize(i7);
            layoutParams.height = -2;
        } else if (isData(hintItemViewType)) {
            int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, i7);
            int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i2, i7);
            int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, i2, i7);
            if (isYearData(hintItemViewType)) {
                i8 = this.mLayoutManager.getHintWidthSpace(i7);
            } else {
                i8 = (int) (((((float) this.mLayoutManager.getHintWidthSpace(i7)) / ((float) hintSpanCount)) * ((float) hintColumnSpan)) + 0.5f);
            }
            layoutParams.width = i8;
            layoutParams.height = hintViewHeight;
        } else if (hintItemViewType == -2) {
            int hintViewHeight2 = this.mPositionCache.getHintViewHeight(this.mLayoutManager, i2, i7);
            layoutParams.width = this.mLayoutManager.getHintWidthSpace(i7);
            layoutParams.height = hintViewHeight2;
        } else {
            layoutParams.width = this.mLayoutManager.getHintWidthSpace(i7);
        }
        rootView.setLayoutParams(layoutParams);
        PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
        int i10 = this.mViewHolderMarginBaseGrid;
        if (i10 > 0) {
            i7 = i10;
        }
        pinchLayoutManager.setViewHolderMargin(createViewHolder, i7);
        return createViewHolder;
    }

    public float createTopFromTarget(CalculateInfo calculateInfo, int i2, float f) {
        float offset;
        float max;
        RelativeY relativeY = new RelativeY(0, 1);
        float offset2 = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        boolean isCalculateOnly = calculateInfo.isCalculateOnly();
        while (viewPosition >= 0) {
            ListViewHolder refViewHolder = getRefViewHolder(viewPosition, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                viewPosition--;
            } else {
                int i7 = viewPosition;
                RefHolderValues refHolderValues = new RefHolderValues(refViewHolder, this.mLayoutManager, this.mPositionCache, viewPosition, calculateInfo.getGridSize(), this.mSpanSize, i2);
                if (isRowChanged(i7, calculateInfo.getGridSize())) {
                    if (((float) refHolderValues.mHeight) + offset2 + f < 0.0f) {
                        if (this.mLastTop == null) {
                            this.mLastTop = Float.valueOf(offset2);
                        }
                        setStartRows(relativeY);
                        if (this.mCalculateRange.calculateTopEnough(relativeY.positiveSum())) {
                            break;
                        }
                        isCalculateOnly = true;
                    }
                    relativeY.decreaseY(isData(refHolderValues.mItemViewType));
                    offset2 -= (float) refHolderValues.mHeight;
                }
                float f5 = offset2;
                if (!isCalculateOnly) {
                    addFakeViewHolder(calculateInfo.getGridSize(), i7, true);
                    this.mLastTop = Float.valueOf(f5);
                }
                relativeY.setDataActivated(isData(refHolderValues.mItemViewType));
                putRectToHashMap(relativeY, !calculateInfo.isCalculateOnly(), true, f5, refHolderValues);
                viewPosition = i7 - 1;
                offset2 = f5;
            }
        }
        RelativeY relativeY2 = relativeY;
        if (!calculateInfo.isCalculateOnly()) {
            this.mCalculateRange.updateTop(relativeY2.positiveSum());
        }
        if (this.mLastTop == null) {
            this.mLastTop = Float.valueOf(offset2);
        }
        setStartRows(relativeY2);
        if (f <= 0.0f) {
            offset = calculateInfo.getOffset();
            max = Math.max(this.mLastTop.floatValue(), 0.0f);
        } else if (getHeight() - f > ((float) this.mRecyclerView.getHeight())) {
            return calculateInfo.getOffset() + f;
        } else {
            offset = calculateInfo.getOffset();
            max = this.mLastTop.floatValue();
        }
        return offset - max;
    }

    public HashMap<Integer, ArrayList<RectF>> getDataRect() {
        return this.mDataPositionRectMap;
    }

    public float getDataRectTop() {
        if (this.mDataPositionRectMap.size() > 0) {
            HashMap<Integer, ArrayList<RectF>> hashMap = this.mDataPositionRectMap;
            ArrayList arrayList = hashMap.get(Collections.min(hashMap.keySet()));
            if (arrayList != null && arrayList.size() > 0) {
                return ((RectF) arrayList.get(0)).top;
            }
        }
        return 0.0f;
    }

    public HashMap<Integer, ArrayList<RectF>> getDividerRect() {
        return this.mDividerPositionRectMap;
    }

    public HashMap<Integer, ArrayList<RectF>> getFakeDataRect() {
        return this.mFakeDataPositionRectMap;
    }

    public HashMap<Integer, ArrayList<RectF>> getFakeDividerRect() {
        return this.mFakeDividerPositionRectMap;
    }

    public ArrayList<ListViewHolder> getFakeViewHolders() {
        return this.mFakeViewHolders;
    }

    public float getHeight() {
        float f;
        float f5 = this.mLastBottom + this.mLastHeight;
        Float f8 = this.mLastTop;
        if (f8 == null) {
            f = 0.0f;
        } else {
            f = f8.floatValue();
        }
        return f5 - f;
    }

    public HashMap<Integer, ArrayList<RectF>> getPositionMap(boolean z, boolean z3) {
        if (z) {
            if (z3) {
                return this.mFakeDataPositionRectMap;
            }
            return this.mDataPositionRectMap;
        } else if (z3) {
            return this.mFakeDividerPositionRectMap;
        } else {
            return this.mDividerPositionRectMap;
        }
    }

    public RectF getRectF(float f, RefHolderValues refHolderValues) {
        float f5 = refHolderValues.mStartX;
        return new RectF(f5, f, ((float) refHolderValues.mWidth) + f5, ((float) refHolderValues.mHeight) + f);
    }

    public ListViewHolder getRefViewHolder(int i2, int i7) {
        return this.mRefHolders.get(this.mLayoutManager.getHintItemViewType(i2, i7));
    }

    public float getSpanSize(int i2, int i7) {
        return Math.max(((float) this.mLayoutManager.getHintWidthSpace(i2)) / ((float) i7), 1.0f);
    }

    public int getStartDataRow() {
        return this.mStartDataRow;
    }

    public int getStartDividerRow() {
        return this.mStartDividerRow;
    }

    public boolean isGridData(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }

    public boolean isRealRatio(int i2) {
        if (getRealGridSize(i2) == 1) {
            return true;
        }
        return false;
    }

    public void putDataHolder(int i2, int i7) {
        this.mRefHolders.put(0, createFakeViewHolder(i2, i7));
        this.mRefHolders.put(4, createFakeViewHolder(i2, i7));
        this.mRefHolders.put(3, createFakeViewHolder(i2, i7));
    }

    public void putRect(HashMap<Integer, ArrayList<RectF>> hashMap, RectF rectF, int i2, boolean z) {
        ArrayList arrayList = hashMap.get(Integer.valueOf(i2));
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (z) {
            arrayList.add(0, rectF);
        } else {
            arrayList.add(rectF);
        }
        hashMap.put(Integer.valueOf(i2), arrayList);
    }

    public void setStartRows(RelativeY relativeY) {
        if (this.mStartDataRow > 0) {
            this.mStartDataRow = relativeY.mData;
            this.mStartDividerRow = relativeY.mDivider;
        }
    }

    public void setViewHolderMarginBaseGrid(int i2) {
        this.mViewHolderMarginBaseGrid = i2;
    }

    public float createBottomFromTarget(CalculateInfo calculateInfo, int i2, int i7, int i8, float f) {
        RelativeY relativeY = new RelativeY(i7, i8);
        float offset = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        int i10 = viewPosition;
        float hintViewHeight = (float) this.mPositionCache.getHintViewHeight(this.mLayoutManager, viewPosition, calculateInfo.getGridSize());
        boolean isCalculateOnly = calculateInfo.isCalculateOnly();
        Float f5 = null;
        while (i10 < this.mLayoutManager.getHintViewCount(calculateInfo.getGridSize())) {
            ListViewHolder refViewHolder = getRefViewHolder(i10, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                i10++;
            } else {
                RefHolderValues refHolderValues = new RefHolderValues(refViewHolder, this.mLayoutManager, this.mPositionCache, i10, calculateInfo.getGridSize(), this.mSpanSize, i2);
                if (refHolderValues.mSpanIndex == 0 && i10 != calculateInfo.getViewPosition()) {
                    offset += ((float) ((ViewGroup.MarginLayoutParams) refViewHolder.getRootView().getLayoutParams()).topMargin) + hintViewHeight;
                    if (offset > f) {
                        if (f5 == null) {
                            f5 = Float.valueOf((((float) this.mRecyclerViewBottom) - offset) - hintViewHeight);
                        }
                        if (this.mCalculateRange.calculateBottomEnough(relativeY.positiveSum())) {
                            break;
                        }
                        isCalculateOnly = true;
                    }
                    relativeY.increaseY(isData(refHolderValues.mItemViewType));
                }
                float f8 = offset;
                hintViewHeight = (float) refHolderValues.mHeight;
                if (!isCalculateOnly) {
                    addFakeViewHolder(calculateInfo.getGridSize(), i10, false);
                    this.mLastBottom = f8;
                    this.mLastHeight = (float) refHolderValues.mHeight;
                }
                relativeY.setDataActivated(isData(refHolderValues.mItemViewType));
                putRectToHashMap(relativeY, !calculateInfo.isCalculateOnly(), false, f8, refHolderValues);
                i10++;
                offset = f8;
            }
        }
        if (!calculateInfo.isCalculateOnly()) {
            this.mCalculateRange.updateBottom(relativeY.positiveSum());
        }
        if (f5 == null) {
            f5 = Float.valueOf((((float) this.mRecyclerViewBottom) - offset) - hintViewHeight);
        }
        if (f5.floatValue() > 0.0f) {
            return f5.floatValue();
        }
        return 0.0f;
    }

    private int expandRectMap(HashMap<Integer, ArrayList<RectF>> hashMap, Integer num, ArrayList<RectF> arrayList, int i2, int i7, int[] iArr) {
        do {
        } while (addRight(arrayList, i2));
        int i8 = 0;
        while (arrayList.size() < i7) {
            for (int i10 = 0; i10 < iArr[1] && addRight(arrayList, i7); i10++) {
            }
            for (int i11 = 0; i11 < iArr[0] && addLeft(arrayList, i7); i11++) {
                i8++;
            }
        }
        hashMap.put(num, arrayList);
        return i8;
    }
}
