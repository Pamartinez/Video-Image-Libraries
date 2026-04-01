package com.samsung.android.gallery.widget.listview.pinch.v3;

import D6.a;
import android.graphics.RectF;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import i.C0212a;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchRectDelegate {
    private int mEndPosition;
    private int mGapDelta;
    private final int mGridSize;
    private final boolean mIsRtl;
    private int mItemGap;
    private final PinchLayoutManager mLayoutManager;
    private final PinchRange mPinchRange;
    private final AnimPositionCache mPositionCache;
    private int mStartOffset;
    private int mStartPosition;
    private final GridInfoSupplier mSupplier;
    private final int mViewWidth;

    public PinchRectDelegate(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, PinchRange pinchRange, int i2, GridInfoSupplier gridInfoSupplier) {
        this.mLayoutManager = pinchLayoutManager;
        this.mPositionCache = animPositionCache;
        this.mPinchRange = pinchRange;
        this.mGridSize = i2;
        this.mViewWidth = pinchLayoutManager.getWidth();
        this.mIsRtl = pinchLayoutManager.isLayoutRtl();
        this.mSupplier = gridInfoSupplier;
    }

    private void addRectForPivot(ArrayList<RectF> arrayList) {
        float f;
        float f5;
        if (!arrayList.isEmpty()) {
            RectF rectF = arrayList.get(0);
            float width = rectF.width() + ((float) (this.mItemGap * 2));
            for (int i2 = 1; i2 <= this.mStartOffset; i2++) {
                boolean z = this.mIsRtl;
                float f8 = rectF.left;
                float f10 = ((float) i2) * width;
                if (z) {
                    f = f10 + f8;
                } else {
                    f = f8 - f10;
                }
                if (z) {
                    f5 = (((float) i2) * width) + rectF.right;
                } else {
                    f5 = rectF.right - (((float) i2) * width);
                }
                arrayList.add(0, new RectF(f, rectF.top, f5, rectF.bottom));
            }
        }
    }

    private int getItemGap(int i2) {
        if (ViewHolderValue.isData(i2)) {
            return this.mItemGap * 2;
        }
        return 0;
    }

    private int getItemPaddingStart() {
        return this.mLayoutManager.getHintPaddingStart(this.mGridSize) + this.mGapDelta;
    }

    private float getItemViewWidth(int i2) {
        if (isCustomWidthData(this.mLayoutManager.getHintItemViewType(i2, this.mGridSize))) {
            return (float) this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i2, this.mGridSize);
        }
        return getSpanWidth(this.mPositionCache) * ((float) this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i2, this.mGridSize));
    }

    private ArrayList<RectF> getRectBottomRange(HashMap<Integer, ArrayList<RectF>> hashMap, int i2, int i7) {
        float f;
        float f5;
        ArrayList<RectF> arrayList = new ArrayList<>();
        int hintViewCount = this.mPositionCache.getHintViewCount(this.mLayoutManager, this.mGridSize);
        int itemPaddingStart = getItemPaddingStart();
        int i8 = i7 + 1;
        float itemGap = ((RectF) hashMap.get(Integer.valueOf(i7)).get(0)).bottom + ((float) getItemGap(this.mLayoutManager.getHintItemViewType(this.mEndPosition, this.mGridSize)));
        while (i8 <= i2) {
            int hintNextRowIndex = this.mLayoutManager.getHintNextRowIndex(this.mEndPosition, this.mGridSize, hintViewCount);
            this.mEndPosition = hintNextRowIndex;
            int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, hintNextRowIndex, this.mGridSize);
            float itemViewWidth = getItemViewWidth(this.mEndPosition);
            int itemGap2 = getItemGap(this.mLayoutManager.getHintItemViewType(this.mEndPosition, this.mGridSize));
            ArrayList<RectF> arrayList2 = new ArrayList<>();
            boolean z = this.mIsRtl;
            if (z) {
                f = ((float) this.mViewWidth) - ((((float) itemPaddingStart) + itemViewWidth) - ((float) itemGap2));
            } else {
                f = (float) itemPaddingStart;
            }
            if (z) {
                f5 = (float) (this.mViewWidth - itemPaddingStart);
            } else {
                f5 = (((float) itemPaddingStart) + itemViewWidth) - ((float) itemGap2);
            }
            float f8 = (float) itemGap2;
            RectF rectF = new RectF(f, itemGap, f5, (((float) hintViewHeight) + itemGap) - f8);
            arrayList2.add(rectF);
            if (ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(this.mEndPosition, this.mGridSize))) {
                addRectForPivot(arrayList2);
            }
            hashMap.put(Integer.valueOf(i8), arrayList2);
            i8++;
            itemGap = rectF.bottom + f8;
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private ArrayList<RectF> getRectTopRange(HashMap<Integer, ArrayList<RectF>> hashMap, int i2) {
        float f;
        float f5;
        ArrayList<RectF> arrayList = new ArrayList<>();
        boolean isLayoutRtl = this.mLayoutManager.isLayoutRtl();
        int width = this.mLayoutManager.getWidth();
        int itemPaddingStart = getItemPaddingStart();
        Integer minRow = getMinRow(hashMap);
        int intValue = minRow.intValue() - 1;
        float itemGap = ((RectF) hashMap.get(minRow).get(0)).top - ((float) getItemGap(this.mLayoutManager.getHintItemViewType(this.mStartPosition, this.mGridSize)));
        while (intValue >= i2) {
            int hintPrevRowIndex = this.mLayoutManager.getHintPrevRowIndex(this.mStartPosition, this.mGridSize);
            this.mStartPosition = hintPrevRowIndex;
            int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, hintPrevRowIndex, this.mGridSize);
            float itemViewWidth = getItemViewWidth(this.mStartPosition);
            int itemGap2 = getItemGap(this.mLayoutManager.getHintItemViewType(this.mStartPosition, this.mGridSize));
            ArrayList<RectF> arrayList2 = new ArrayList<>();
            if (isLayoutRtl) {
                f = ((float) width) - ((((float) itemPaddingStart) + itemViewWidth) - ((float) itemGap2));
            } else {
                f = (float) itemPaddingStart;
            }
            if (isLayoutRtl) {
                f5 = (float) (width - itemPaddingStart);
            } else {
                f5 = (((float) itemPaddingStart) + itemViewWidth) - ((float) itemGap2);
            }
            float f8 = (float) itemGap2;
            RectF rectF = new RectF(f, itemGap - ((float) hintViewHeight), f5, itemGap - f8);
            arrayList2.add(rectF);
            if (ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(this.mStartPosition, this.mGridSize))) {
                addRectForPivot(arrayList2);
            }
            hashMap.put(Integer.valueOf(intValue), arrayList2);
            itemGap = rectF.top - f8;
            intValue--;
            arrayList = arrayList2;
        }
        return arrayList;
    }

    private float getSpanWidth(AnimPositionCache animPositionCache) {
        return Math.max(1.0f, ((float) this.mLayoutManager.getHintWidthSpace(this.mGridSize)) / ((float) animPositionCache.getHintSpanCount(this.mLayoutManager, this.mGridSize)));
    }

    private boolean isCustomWidthData(int i2) {
        if (!ViewHolderValue.isData(i2) || !this.mSupplier.useCustomWidth(this.mGridSize)) {
            return false;
        }
        return true;
    }

    public void addColumn(ArrayList<RectF> arrayList, int i2) {
        float f;
        float f5;
        RectF rectF = new RectF((RectF) C0212a.i(arrayList, 1));
        float width = rectF.width() + ((float) (this.mItemGap * 2));
        int size = (i2 - arrayList.size()) + 1;
        for (int i7 = 1; i7 <= size; i7++) {
            boolean z = this.mIsRtl;
            float f8 = rectF.left;
            float f10 = ((float) i7) * width;
            if (z) {
                f = f8 - f10;
            } else {
                f = f8 + f10;
            }
            if (z) {
                f5 = rectF.right - (((float) i7) * width);
            } else {
                f5 = rectF.right + (((float) i7) * width);
            }
            arrayList.add(new RectF(f, rectF.top, f5, rectF.bottom));
        }
    }

    public ArrayList<RectF> addRow(HashMap<Integer, ArrayList<RectF>> hashMap, int i2) {
        int intValue = getMaxRow(hashMap).intValue();
        if (i2 > intValue) {
            return getRectBottomRange(hashMap, i2, intValue);
        }
        return getRectTopRange(hashMap, i2);
    }

    public void fillDefaultRange(HashMap<Integer, ArrayList<RectF>> hashMap) {
        int i2;
        float f;
        int i7;
        int i8;
        float startY = this.mPinchRange.getStartY() + ((float) this.mGapDelta);
        int startPosition = this.mPinchRange.getStartPosition();
        for (int startRow = this.mPinchRange.getStartRow(); startRow <= this.mPinchRange.getEndRow(); startRow++) {
            ArrayList arrayList = new ArrayList();
            int hintItemViewType = this.mLayoutManager.getHintItemViewType(startPosition, this.mGridSize);
            if (ViewHolderValue.isHeader(hintItemViewType)) {
                i2 = this.mLayoutManager.getHintPaddingLeft(this.mGridSize);
            } else {
                int itemPaddingStart = getItemPaddingStart();
                if (this.mIsRtl) {
                    i8 = this.mGapDelta * 2;
                } else {
                    i8 = 0;
                }
                i2 = itemPaddingStart - i8;
            }
            int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, startPosition, this.mGridSize);
            float f5 = 0.0f;
            for (int i10 = 0; i10 < this.mPinchRange.getColumnCount(startRow); i10++) {
                float itemViewWidth = getItemViewWidth(startPosition);
                int i11 = (int) itemViewWidth;
                float f8 = (itemViewWidth % 1.0f) + f5;
                int itemGap = getItemGap(hintItemViewType);
                if (ViewHolderValue.isHeader(hintItemViewType)) {
                    arrayList.add(new RectF((float) (this.mLayoutManager.getSpacing(this.mGridSize) + i2), startY - ((float) this.mGapDelta), (float) (i2 + i11), ((float) hintViewHeight) + startY));
                } else {
                    if (f8 > 1.0f && i10 < this.mPinchRange.getColumnCount(startRow) - 1) {
                        i2++;
                        f8 -= 1.0f;
                    }
                    boolean z = this.mIsRtl;
                    if (z) {
                        f = (float) (this.mViewWidth - (i2 + i11));
                    } else {
                        f = (float) i2;
                    }
                    if (z) {
                        i7 = this.mViewWidth - i2;
                    } else {
                        i7 = i2 + i11;
                    }
                    arrayList.add(new RectF(f, startY, (float) (i7 - itemGap), (((float) hintViewHeight) + startY) - ((float) itemGap)));
                }
                f5 = f8;
                i2 += i11;
                startPosition++;
            }
            if (ViewHolderValue.isData(hintItemViewType)) {
                addRectForPivot(arrayList);
            }
            startY += (float) hintViewHeight;
            hashMap.put(Integer.valueOf(startRow), arrayList);
        }
        this.mStartPosition = this.mPinchRange.getStartPosition();
        this.mEndPosition = this.mPinchRange.getEndPosition();
    }

    public Integer getMaxRow(HashMap<Integer, ArrayList<RectF>> hashMap) {
        return hashMap.keySet().stream().max(new a(26)).orElse(0);
    }

    public Integer getMinRow(HashMap<Integer, ArrayList<RectF>> hashMap) {
        return hashMap.keySet().stream().min(new a(26)).orElse(0);
    }

    public void setItemGap(int i2, int i7) {
        this.mItemGap = i2;
        this.mGapDelta = i2 - i7;
    }

    public void setStartOffset(int i2) {
        this.mStartOffset = i2;
    }
}
