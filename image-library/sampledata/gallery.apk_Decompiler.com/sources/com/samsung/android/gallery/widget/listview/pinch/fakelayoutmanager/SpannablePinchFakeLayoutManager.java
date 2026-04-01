package com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager;

import android.graphics.RectF;
import android.view.ViewGroup;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpannablePinchFakeLayoutManager extends PinchFakeLayoutManager {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class SpannableRefHolderValues extends PinchFakeLayoutManager.RefHolderValues {
        public SpannableRefHolderValues(ListViewHolder listViewHolder, PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache, int i2, int i7, float f, int i8) {
            super(listViewHolder, pinchLayoutManager, animPositionCache, i2, i7, f, i8);
        }

        public int getItemViewHeight(ListViewHolder listViewHolder, float f, int i2, int i7) {
            SpannablePinchFakeLayoutManager spannablePinchFakeLayoutManager = SpannablePinchFakeLayoutManager.this;
            return (int) (f * ((float) spannablePinchFakeLayoutManager.mPositionCache.getHintRowSpan(spannablePinchFakeLayoutManager.mLayoutManager, i2, i7)));
        }

        public int getItemViewWidth(ListViewHolder listViewHolder, float f, int i2, int i7) {
            SpannablePinchFakeLayoutManager spannablePinchFakeLayoutManager = SpannablePinchFakeLayoutManager.this;
            return (int) (f * ((float) spannablePinchFakeLayoutManager.mPositionCache.getHintColumnSpan(spannablePinchFakeLayoutManager.mLayoutManager, i2, i7)));
        }
    }

    public SpannablePinchFakeLayoutManager(PinchLayoutManager pinchLayoutManager, IPinchRecyclerView iPinchRecyclerView, ViewGroup viewGroup, AnimPositionCache animPositionCache) {
        super(pinchLayoutManager, iPinchRecyclerView, viewGroup, animPositionCache);
    }

    private RectF createSpanSizeRectF(float f, float f5) {
        float f8 = this.mSpanSize;
        return new RectF(f, f5, f + f8, f8 + f5);
    }

    private Integer getFirstPositionInRow(int i2, int i7) {
        int i8;
        Integer[] rowInfo = getRowInfo(i2, i7);
        if (rowInfo != null) {
            i8 = rowInfo[0].intValue();
        } else {
            i8 = 1;
        }
        return Integer.valueOf(i8);
    }

    private Integer getFistPositionInNextRow(int i2, int i7) {
        int i8;
        Integer[] hintRowInfo = this.mLayoutManager.getHintRowInfo(getRow(i7, i2) + 1, i2);
        if (hintRowInfo != null) {
            i8 = hintRowInfo[0].intValue();
        } else {
            i8 = this.mLayoutManager.getHintViewCount(i2);
        }
        return Integer.valueOf(i8);
    }

    private int getHeaderDeltaPosition(int i2) {
        if (i2 <= 0 || !this.mLayoutManager.hasHeader()) {
            return i2;
        }
        return i2 - 1;
    }

    private int getRow(int i2, int i7) {
        SpanInfo hintSpanInfo = this.mLayoutManager.getHintSpanInfo(i2, i7);
        if (hintSpanInfo != null) {
            return hintSpanInfo.getRow();
        }
        return 1;
    }

    private Integer[] getRowInfo(int i2, int i7) {
        return this.mLayoutManager.getHintRowInfo(getRow(i7, i2), i2);
    }

    private void putRect(HashMap<Integer, ArrayList<RectF>> hashMap, RectF rectF, int i2, int i7) {
        ArrayList arrayList = hashMap.get(Integer.valueOf(i2));
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        int size = arrayList.size();
        while (size < i7) {
            arrayList.add(createSpanSizeRectF(rectF.left - (((float) (i7 - size)) * this.mSpanSize), rectF.top));
            size++;
        }
        if (size > i7) {
            arrayList.set(i7, rectF);
        } else {
            arrayList.add(rectF);
        }
        int width = (int) rectF.width();
        int i8 = 1;
        while (true) {
            float f = (float) width;
            float f5 = this.mSpanSize;
            if (f <= f5) {
                break;
            }
            putRect(hashMap, createSpanSizeRectF((((float) i8) * f5) + rectF.left, rectF.top), i2, i7 + i8);
            i8++;
            width = (int) (f - this.mSpanSize);
        }
        int height = (int) rectF.height();
        int i10 = 1;
        while (true) {
            float f8 = (float) height;
            float f10 = this.mSpanSize;
            if (f8 > f10) {
                putRect(hashMap, createSpanSizeRectF(rectF.left, (((float) i10) * f10) + rectF.top), i2 + i10, i7);
                i10++;
                height = (int) (f8 - this.mSpanSize);
            } else {
                hashMap.put(Integer.valueOf(i2), arrayList);
                return;
            }
        }
    }

    private void putRectToHashMap(PinchFakeLayoutManager.RelativeY relativeY, boolean z, boolean z3, float f, PinchFakeLayoutManager.RefHolderValues refHolderValues) {
        if (relativeY.mIsDataActivated) {
            putRect(getPositionMap(true, z), getRectF(f, refHolderValues), relativeY.getActivatedY(), refHolderValues.mSpanIndex);
        } else {
            putRect(getPositionMap(false, z), getRectF(f, refHolderValues), relativeY.getActivatedY(), z3);
        }
    }

    public float createBottomFromTarget(PinchFakeLayoutManager.CalculateInfo calculateInfo, int i2, int i7, int i8, float f) {
        float f5;
        if (!this.mLayoutManager.isHintSpannable(calculateInfo.getGridSize())) {
            return super.createBottomFromTarget(calculateInfo, i2, i7, i8, f);
        }
        PinchFakeLayoutManager.RelativeY relativeY = new PinchFakeLayoutManager.RelativeY(i7, i8);
        float offset = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        boolean isCalculateOnly = calculateInfo.isCalculateOnly();
        int i10 = viewPosition;
        float hintViewHeight = (float) this.mPositionCache.getHintViewHeight(this.mLayoutManager, viewPosition, calculateInfo.getGridSize());
        boolean z = isCalculateOnly;
        int intValue = getFistPositionInNextRow(calculateInfo.getGridSize(), viewPosition).intValue();
        Float f8 = null;
        while (i10 < this.mLayoutManager.getHintViewCount(calculateInfo.getGridSize())) {
            ListViewHolder refViewHolder = getRefViewHolder(i10, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                i10++;
            } else {
                SpannableRefHolderValues spannableRefHolderValues = new SpannableRefHolderValues(refViewHolder, this.mLayoutManager, this.mPositionCache, i10, calculateInfo.getGridSize(), this.mSpanSize, i2);
                if (intValue <= getHeaderDeltaPosition(i10)) {
                    offset += hintViewHeight;
                    if (offset > f) {
                        if (f8 == null) {
                            f8 = Float.valueOf((((float) this.mRecyclerView.getBottom()) - offset) - hintViewHeight);
                        }
                        if (this.mCalculateRange.calculateBottomEnough(relativeY.positiveSum())) {
                            break;
                        }
                        z = true;
                    }
                    relativeY.increaseY(PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType));
                    intValue = getFistPositionInNextRow(calculateInfo.getGridSize(), i10).intValue();
                }
                float f10 = offset;
                if (PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType)) {
                    f5 = this.mSpanSize;
                } else {
                    f5 = (float) spannableRefHolderValues.mHeight;
                }
                hintViewHeight = f5;
                if (!z) {
                    addFakeViewHolder(calculateInfo.getGridSize(), i10, false);
                    this.mLastBottom = f10;
                    this.mLastHeight = hintViewHeight;
                }
                relativeY.setDataActivated(PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType));
                putRectToHashMap(relativeY, !calculateInfo.isCalculateOnly(), false, f10, spannableRefHolderValues);
                i10++;
                offset = f10;
            }
        }
        if (!calculateInfo.isCalculateOnly()) {
            this.mCalculateRange.updateBottom(relativeY.positiveSum());
        }
        if (f8 == null) {
            f8 = Float.valueOf((((float) this.mRecyclerView.getBottom()) - offset) - hintViewHeight);
        }
        if (f8.floatValue() > 0.0f) {
            return f8.floatValue();
        }
        return 0.0f;
    }

    public float createTopFromTarget(PinchFakeLayoutManager.CalculateInfo calculateInfo, int i2, float f) {
        float f5;
        float f8;
        SpannablePinchFakeLayoutManager spannablePinchFakeLayoutManager = this;
        if (!spannablePinchFakeLayoutManager.mLayoutManager.isHintSpannable(calculateInfo.getGridSize())) {
            return super.createTopFromTarget(calculateInfo, i2, f);
        }
        PinchFakeLayoutManager.RelativeY relativeY = new PinchFakeLayoutManager.RelativeY(0, 1);
        float offset = calculateInfo.getOffset();
        int viewPosition = calculateInfo.getViewPosition();
        float f10 = offset;
        boolean isCalculateOnly = calculateInfo.isCalculateOnly();
        int intValue = spannablePinchFakeLayoutManager.getFirstPositionInRow(calculateInfo.getGridSize(), viewPosition + 1).intValue();
        Float f11 = null;
        int i7 = viewPosition;
        while (true) {
            f5 = 0.0f;
            if (i7 <= 0) {
                break;
            }
            ListViewHolder refViewHolder = spannablePinchFakeLayoutManager.getRefViewHolder(i7, calculateInfo.getGridSize());
            if (refViewHolder == null) {
                i7--;
            } else {
                SpannablePinchFakeLayoutManager spannablePinchFakeLayoutManager2 = spannablePinchFakeLayoutManager;
                int i8 = i7;
                SpannableRefHolderValues spannableRefHolderValues = new SpannableRefHolderValues(refViewHolder, spannablePinchFakeLayoutManager.mLayoutManager, spannablePinchFakeLayoutManager.mPositionCache, i7, calculateInfo.getGridSize(), spannablePinchFakeLayoutManager.mSpanSize, i2);
                spannablePinchFakeLayoutManager = spannablePinchFakeLayoutManager2;
                if (intValue > spannablePinchFakeLayoutManager.getHeaderDeltaPosition(i8)) {
                    if (PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType)) {
                        f8 = spannablePinchFakeLayoutManager.mSpanSize;
                    } else {
                        f8 = (float) spannableRefHolderValues.mHeight;
                    }
                    if (f10 + f8 + f < 0.0f) {
                        if (f11 == null) {
                            f11 = Float.valueOf(f10);
                        }
                        spannablePinchFakeLayoutManager.setStartRows(relativeY);
                        if (spannablePinchFakeLayoutManager.mCalculateRange.calculateTopEnough(relativeY.positiveSum())) {
                            break;
                        }
                        isCalculateOnly = true;
                    }
                    f10 -= f8;
                    relativeY.decreaseY(PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType));
                    intValue = spannablePinchFakeLayoutManager.getFirstPositionInRow(calculateInfo.getGridSize(), i8).intValue();
                }
                float f12 = f10;
                if (!isCalculateOnly) {
                    spannablePinchFakeLayoutManager.addFakeViewHolder(calculateInfo.getGridSize(), i8, true);
                    spannablePinchFakeLayoutManager.mLastTop = Float.valueOf(f12);
                }
                relativeY.setDataActivated(PinchFakeLayoutManager.isData(spannableRefHolderValues.mItemViewType));
                spannablePinchFakeLayoutManager.putRectToHashMap(relativeY, !calculateInfo.isCalculateOnly(), true, f12, spannableRefHolderValues);
                i7 = i8 - 1;
                f10 = f12;
            }
        }
        PinchFakeLayoutManager.RelativeY relativeY2 = relativeY;
        if (!calculateInfo.isCalculateOnly()) {
            spannablePinchFakeLayoutManager.mCalculateRange.updateTop(relativeY2.positiveSum());
        }
        if (f11 == null) {
            f11 = Float.valueOf(f10);
        }
        spannablePinchFakeLayoutManager.setStartRows(relativeY2);
        float offset2 = calculateInfo.getOffset();
        if (f > 0.0f || f11.floatValue() >= 0.0f) {
            f5 = f11.floatValue();
        }
        return offset2 - f5;
    }
}
