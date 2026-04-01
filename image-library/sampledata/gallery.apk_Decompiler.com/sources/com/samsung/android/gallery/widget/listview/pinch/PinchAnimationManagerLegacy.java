package com.samsung.android.gallery.widget.listview.pinch;

import A.a;
import Fb.h;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import c0.C0086a;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.abstraction.ViewPoolInjector;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinchAnimationManagerLegacy extends PinchAnimationManager {
    protected SparseArray<ListViewHolder> mFakeViewHolders = new SparseArray<>();

    public PinchAnimationManagerLegacy(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo) {
        super(pinchLayoutManager, clusterInfo);
    }

    private void createFakeViews(ArrayList<PinchItem> arrayList) {
        View view;
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        addFakeViewsAtTop(arrayList, arrayList2);
        addFakeViewAtBottom(arrayList, arrayList3);
        arrayList.addAll(0, arrayList2);
        arrayList.addAll(arrayList3);
        arrayList4.addAll(arrayList2);
        arrayList4.addAll(arrayList3);
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            PinchItem pinchItem = (PinchItem) it.next();
            ListViewHolder refViewHolder = getRefViewHolder(pinchItem.getFromViewPosition(), true);
            PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
            AnimPositionCache animPositionCache = this.mPositionCache;
            if (refViewHolder != null) {
                view = refViewHolder.getRootView();
            } else {
                view = null;
            }
            pinchItem.calculateTo(pinchLayoutManager, animPositionCache, view);
        }
    }

    private View findFirstVisibleView() {
        return this.mLayoutManager.findViewByPosition(this.mLayoutManager.findFirstVisibleItemPosition());
    }

    private int getReferenceViewHeight(View view) {
        return view.getHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin;
    }

    private void recycleFakeViews() {
        for (int i2 = 0; i2 < this.mFakeViewHolders.size(); i2++) {
            SparseArray<ListViewHolder> sparseArray = this.mFakeViewHolders;
            ListViewHolder listViewHolder = sparseArray.get(sparseArray.keyAt(i2));
            if (listViewHolder != null) {
                ViewUtils.removeSelf(listViewHolder.getRootView());
                listViewHolder.recycle();
                putRecycledViewPool(listViewHolder);
            }
        }
        this.mFakeViewHolders.clear();
    }

    private boolean startFromTopPosition() {
        if (this.mLayoutManager.isAppbarVisible() || this.mLayoutManager.isAppbarCollapsed()) {
            return true;
        }
        return false;
    }

    public void addFakeViewAtBottom(ArrayList<PinchItem> arrayList, ArrayList<PinchItem> arrayList2) {
        int i2 = this.mBottomFakeViewCount;
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, this.mGridInfo.from());
        float spanWidth = getSpanWidth(this.mGridInfo.from(), hintSpanCount);
        int hintItemCount = getHintItemCount(this.mGridInfo.from());
        int i7 = 1;
        PinchItem pinchItem = (PinchItem) C0212a.i(arrayList, 1);
        int fromViewPosition = pinchItem.getFromViewPosition();
        int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, fromViewPosition, this.mGridInfo.from()) + this.mPositionCache.getHintStartSpan(this.mLayoutManager, fromViewPosition, this.mGridInfo.from());
        float f = pinchItem.getFromRect().top;
        int toViewPosition = pinchItem.getToViewPosition() + i2 + 1;
        int toViewPosition2 = pinchItem.getToViewPosition();
        boolean z = false;
        int i8 = hintColumnSpan;
        int i10 = fromViewPosition + 1;
        int i11 = (int) pinchItem.getFromRect().bottom;
        float f5 = f;
        int i12 = toViewPosition2;
        int i13 = 0;
        while (toViewPosition >= i12 && i2 != 0 && i10 < hintItemCount) {
            ListViewHolder refViewHolder = getRefViewHolder(i10, z);
            if (refViewHolder != null) {
                ListViewHolder createAndBindFakeViewHolder = createAndBindFakeViewHolder(i10, this.mGridInfo.from());
                int i14 = i13 + i7;
                if (i14 > 120) {
                    Log.e((CharSequence) this.TAG, "too many bottom fake views", "toGrid=" + this.mGridInfo.to(), "fromStart=" + fromViewPosition + 1, C0086a.i(i2, "fakeViewCount="), "toStart=" + pinchItem.getToViewPosition() + 1, C0086a.i(toViewPosition, "toEnd="), a.d(i10, i12, "currentPos(", GlobalPostProcInternalPPInterface.SPLIT_REGEX, ")"));
                    return;
                }
                int i15 = i12;
                int hintColumnSpan2 = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i10, this.mGridInfo.from());
                int i16 = i14;
                float f8 = spanWidth;
                int i17 = i2;
                int i18 = i10;
                int i19 = hintColumnSpan2;
                int i20 = hintItemCount;
                ListViewHolder listViewHolder = refViewHolder;
                int i21 = i16;
                float xPosition = getXPosition(this.mPositionCache.getHintStartSpan(this.mLayoutManager, i10, this.mGridInfo.from()), f8, hintSpanCount, i19, this.mGridInfo.from(), createAndBindFakeViewHolder.getViewType());
                float f10 = f8;
                int i22 = hintSpanCount;
                int itemViewWidth = getItemViewWidth(listViewHolder, i18, this.mGridInfo.from());
                int itemViewHeight = getItemViewHeight(listViewHolder, i18, this.mGridInfo.from());
                if (i8 + i19 > i22) {
                    f5 = (float) (i11 + ((ViewGroup.MarginLayoutParams) listViewHolder.getRootView().getLayoutParams()).topMargin);
                    i8 = 0;
                }
                float f11 = f5;
                i8 += i19;
                measureView(createAndBindFakeViewHolder.getRootView(), xPosition, f11, itemViewWidth, itemViewHeight);
                PinchItem pinchItem2 = new PinchItem(createAndBindFakeViewHolder.getRootView(), i18, i15 + 1, this.mGridInfo);
                pinchItem2.setItemViewType(createAndBindFakeViewHolder.getViewType());
                pinchItem2.calculateToPosition(this.mLayoutManager, this.mPositionCache);
                int toViewPosition3 = pinchItem2.getToViewPosition();
                arrayList2.add(pinchItem2);
                this.mFakeViewHolders.put(i18, createAndBindFakeViewHolder);
                i10 = i18 + 1;
                i12 = toViewPosition3;
                f5 = f11;
                hintSpanCount = i22;
                i11 = (int) (((float) itemViewHeight) + f11);
                i13 = i21;
                i2 = i17;
                hintItemCount = i20;
                spanWidth = f10;
                i7 = 1;
                z = false;
            } else {
                return;
            }
        }
    }

    public void addFakeViewBitmapToCache() {
        for (int i2 = 0; i2 < this.mFakeViewHolders.size(); i2++) {
            SparseArray<ListViewHolder> sparseArray = this.mFakeViewHolders;
            addBitmapToCache(sparseArray.get(sparseArray.keyAt(i2)));
        }
    }

    public void addFakeViewsAtTop(ArrayList<PinchItem> arrayList, ArrayList<PinchItem> arrayList2) {
        int i2 = this.mTopFakeViewCount;
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, this.mGridInfo.from());
        float spanWidth = getSpanWidth(this.mGridInfo.from(), hintSpanCount);
        boolean z = false;
        PinchItem pinchItem = arrayList.get(0);
        int fromViewPosition = pinchItem.getFromViewPosition();
        int toViewPosition = (pinchItem.getToViewPosition() - i2) - 1;
        int hintStartSpan = this.mPositionCache.getHintStartSpan(this.mLayoutManager, fromViewPosition, this.mGridInfo.from());
        float f = pinchItem.getFromRect().top;
        int i7 = fromViewPosition - 1;
        int toViewPosition2 = pinchItem.getToViewPosition();
        int i8 = hintStartSpan;
        float f5 = f;
        int i10 = 0;
        int i11 = i7;
        while (toViewPosition <= toViewPosition2 && i2 != 0 && i11 >= 0) {
            ListViewHolder createAndBindFakeViewHolder = createAndBindFakeViewHolder(i11, this.mGridInfo.from());
            ListViewHolder refViewHolder = getRefViewHolder(i11, z);
            int i12 = i10 + 1;
            if (i12 > 120) {
                Log.e(this.TAG, "too many bottom fakeviews=".concat(String.format("toGrid(%d),fromStart(%d),fakeViewCount(%d),toStart(%d),toEnd(%d),currentPos(%d %d)", new Object[]{Integer.valueOf(this.mGridInfo.to()), Integer.valueOf(i7), Integer.valueOf(i2), Integer.valueOf(pinchItem.getToViewPosition() - 1), Integer.valueOf(toViewPosition), Integer.valueOf(i11), Integer.valueOf(toViewPosition2)})));
                return;
            }
            int i13 = i12;
            int hintStartSpan2 = this.mPositionCache.getHintStartSpan(this.mLayoutManager, i11, this.mGridInfo.from());
            int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i11, this.mGridInfo.from());
            ListViewHolder listViewHolder = refViewHolder;
            int from = this.mGridInfo.from();
            int viewType = createAndBindFakeViewHolder.getViewType();
            ListViewHolder listViewHolder2 = listViewHolder;
            int i14 = i13;
            ListViewHolder listViewHolder3 = createAndBindFakeViewHolder;
            int i15 = hintColumnSpan;
            int i16 = hintStartSpan2;
            int i17 = i2;
            ListViewHolder listViewHolder4 = listViewHolder2;
            float xPosition = getXPosition(i16, spanWidth, hintSpanCount, i15, from, viewType);
            int i18 = i16;
            float f8 = spanWidth;
            int i19 = hintSpanCount;
            int itemViewWidth = getItemViewWidth(listViewHolder4, i11, this.mGridInfo.from());
            int itemViewHeight = getItemViewHeight(listViewHolder4, i11, this.mGridInfo.from());
            if (i8 <= 0) {
                f5 -= (float) itemViewHeight;
            }
            float f10 = f5;
            measureView(listViewHolder3.getRootView(), xPosition, f10, itemViewWidth, itemViewHeight);
            PinchItem pinchItem2 = new PinchItem(listViewHolder3.getRootView(), i11, toViewPosition2 - 1, this.mGridInfo);
            pinchItem2.setItemViewType(listViewHolder3.getViewType());
            pinchItem2.calculateToPosition(this.mLayoutManager, this.mPositionCache);
            toViewPosition2 = pinchItem2.getToViewPosition();
            arrayList2.add(pinchItem2);
            this.mFakeViewHolders.put(i11, listViewHolder3);
            i11--;
            f5 = f10;
            i8 = i18;
            i10 = i14;
            i2 = i17;
            hintSpanCount = i19;
            spanWidth = f8;
            z = false;
        }
    }

    public void addTranslationAnimator(View view, View view2, ListViewHolder listViewHolder, RectF rectF) {
        if (view != null && view.getVisibility() != 8 && view2 != null) {
            RectF rect = getRect(view);
            float[] childToXY = getChildToXY(view, listViewHolder.getRootView(), getBaseToXY(view, view2), rectF);
            float f = childToXY[0];
            float f5 = childToXY[1];
            TranslationAnimator translationAnimator = new TranslationAnimator(view, rect, new RectF(f, f5, f, f5));
            translationAnimator.setAnimationListener(new h(24, this));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    public void animationCompletedInternal(boolean z, boolean z3) {
        super.animationCompletedInternal(z, z3);
        recycleFakeViews();
    }

    public void calculateFakeViewSpace(ArrayList<PinchItem> arrayList) {
        int i2;
        int i7;
        int i8;
        int i10;
        int hintItemCount = getHintItemCount(this.mGridInfo.to());
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, this.mGridInfo.to());
        PinchItem pinchItem = (PinchItem) C0212a.i(arrayList, 1);
        int bottom = (int) (((float) getRecyclerView().getBottom()) - pinchItem.getToRect().bottom);
        int toViewPosition = pinchItem.getToViewPosition() + 1;
        int toSpanSize = pinchItem.getToSpanSize() + pinchItem.getToSpanIndex();
        int i11 = 0;
        while (toViewPosition < hintItemCount) {
            int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, toViewPosition, this.mGridInfo.to());
            if (toSpanSize + hintColumnSpan > hintSpanCount) {
                if (bottom < 0) {
                    break;
                }
                ListViewHolder refViewHolder = getRefViewHolder(toViewPosition, true);
                if (refViewHolder != null) {
                    i10 = getReferenceViewHeight(refViewHolder.getRootView());
                } else {
                    i10 = this.mPositionCache.getHintViewHeight(this.mLayoutManager, toViewPosition, this.mGridInfo.to());
                }
                bottom -= i10;
                toSpanSize = 0;
            }
            toViewPosition++;
            i11 = i2 + 1;
            toSpanSize += hintColumnSpan;
        }
        if (bottom <= 0) {
            bottom = 0;
        }
        PinchItem pinchItem2 = arrayList.get(0);
        int yPosition = (int) ((getYPosition(pinchItem2) + ((float) bottom)) - ((float) getRecyclerView().getTop()));
        int toViewPosition2 = pinchItem2.getToViewPosition() - 1;
        int toSpanIndex = pinchItem2.getToSpanIndex();
        int i12 = 0;
        while (toViewPosition2 >= 0) {
            int hintColumnSpan2 = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, toViewPosition2, this.mGridInfo.to());
            if (toSpanIndex - hintColumnSpan2 < 0) {
                if (yPosition < 0) {
                    break;
                }
                ListViewHolder refViewHolder2 = getRefViewHolder(toViewPosition2, true);
                if (refViewHolder2 != null) {
                    i8 = getReferenceViewHeight(refViewHolder2.getRootView());
                } else {
                    i8 = this.mPositionCache.getHintViewHeight(this.mLayoutManager, toViewPosition2, this.mGridInfo.to());
                }
                yPosition -= i8;
                toSpanIndex = hintSpanCount;
            }
            toViewPosition2--;
            i12++;
            toSpanIndex -= hintColumnSpan2;
        }
        if (yPosition > 0) {
            bottom -= yPosition;
            int bottom2 = (int) ((((float) getRecyclerView().getBottom()) - pinchItem.getToRect().bottom) - ((float) bottom));
            int toSpanSize2 = pinchItem.getToSpanSize() + pinchItem.getToSpanIndex();
            int toViewPosition3 = pinchItem.getToViewPosition() + 1;
            i2 = 0;
            while (toViewPosition3 < hintItemCount) {
                int hintColumnSpan3 = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, toViewPosition3, this.mGridInfo.to());
                if (toSpanSize2 + hintColumnSpan3 > hintSpanCount) {
                    if (bottom2 < 0) {
                        break;
                    }
                    ListViewHolder refViewHolder3 = getRefViewHolder(toViewPosition3, true);
                    if (refViewHolder3 != null) {
                        i7 = getReferenceViewHeight(refViewHolder3.getRootView());
                    } else {
                        i7 = this.mPositionCache.getHintViewHeight(this.mLayoutManager, toViewPosition3, this.mGridInfo.to());
                    }
                    bottom2 -= i7;
                    toSpanSize2 = 0;
                }
                toViewPosition3++;
                i2++;
                toSpanSize2 += hintColumnSpan3;
            }
        }
        this.mShiftOffset = bottom;
        this.mTopFakeViewCount = i12;
        this.mBottomFakeViewCount = i2;
    }

    public void calculatePosition(ArrayList<PinchItem> arrayList) {
        if (arrayList.isEmpty()) {
            Log.w(this.TAG, "calculatePosition#No animate views");
            return;
        }
        calculatePosition(arrayList, 0);
        calculateFakeViewSpace(arrayList);
        calculateHeaderViewSpace(arrayList);
        createFakeViews(arrayList);
        filterInvalidItems(arrayList);
        calculatePosition(arrayList, this.mShiftOffset);
    }

    public ListViewHolder createAndBindFakeViewHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolder = createFakeViewHolder(i2, i7);
        createFakeViewHolder.setFakePosition(i2);
        this.mLayoutManager.bindHolder(createFakeViewHolder, i2);
        return createFakeViewHolder;
    }

    public abstract ListViewHolder createFakeViewHolder(int i2, int i7);

    public ListViewHolder createFakeViewHolderInternal(ViewGroup viewGroup, int i2) {
        ListViewHolder listViewHolder = (ListViewHolder) getRecyclerView().getRecycledViewPool().getRecycledView(i2);
        if (listViewHolder == null) {
            listViewHolder = this.mLayoutManager.createListViewHolder(viewGroup, i2);
        }
        View rootView = listViewHolder.getRootView();
        if (rootView.getParent() != null) {
            String str = this.TAG;
            Log.e(str, "invalid parent - remove from parent : " + rootView.getParent() + " , " + listViewHolder.getItemViewType());
            ViewUtils.removeSelf(rootView);
            new InternalException("createFakeViewHolderInternal - invalid parent").post();
        }
        updateFakeViewSize(rootView, i2);
        viewGroup.addView(rootView);
        return listViewHolder;
    }

    public View findFocusView() {
        if (startFromTopPosition()) {
            return findFirstVisibleView();
        }
        float[] focusXY = getFocusXY();
        if (focusXY != null) {
            int lastVisibleItemPosition = getLastVisibleItemPosition();
            double d = Double.MAX_VALUE;
            View view = null;
            for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= lastVisibleItemPosition; findFirstVisibleItemPosition++) {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (focusable(findViewByPosition)) {
                    float width = (((float) findViewByPosition.getWidth()) / 2.0f) + findViewByPosition.getX();
                    float height = (((float) findViewByPosition.getHeight()) / 2.0f) + findViewByPosition.getY();
                    double sqrt = Math.sqrt(Math.pow((double) (height - focusXY[1]), 2.0d) + Math.pow((double) (width - focusXY[0]), 2.0d));
                    if (sqrt < d) {
                        view = findViewByPosition;
                        d = sqrt;
                    }
                }
            }
            return view;
        }
        Log.w(this.TAG, "no focused view");
        return findFirstVisibleView();
    }

    public boolean focusable(View view) {
        if (((ListViewHolder) getChildViewHolder(view)) != null) {
            return true;
        }
        return false;
    }

    public float[] getBaseToXY(View view, View view2) {
        float f;
        if (view.getLayoutDirection() == 1 && view2.getWidth() == 0) {
            f = (float) view.getWidth();
        } else {
            f = 0.0f;
        }
        return new float[]{((getRootX(view2) + view.getX()) - getRootX(view)) - f, (getRootY(view2) + view.getY()) - getRootY(view)};
    }

    public float[] getChildToXY(View view, View view2, float[] fArr, RectF rectF) {
        int i2;
        boolean z;
        float f;
        int i7;
        int i8;
        float f5 = fArr[1];
        if (view.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            i2 = ((LinearLayout.LayoutParams) view.getLayoutParams()).gravity;
        } else if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            i2 = ((FrameLayout.LayoutParams) view.getLayoutParams()).gravity;
        } else if (view.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
            if (layoutParams.getRule(10) < 0) {
                i7 = 48;
            } else {
                i7 = 80;
            }
            if (layoutParams.getRule(11) < 0 || layoutParams.getRule(21) < 0) {
                i8 = 8388613;
            } else {
                i8 = 8388611;
            }
            i2 = i8 | i7;
        } else {
            i2 = 0;
        }
        int i10 = 8388615 & i2;
        if (view.getLayoutDirection() == 1) {
            z = true;
        } else {
            z = false;
        }
        if (i10 == 8388613) {
            if (z) {
                f = fArr[0];
            } else {
                f = fArr[0] + (rectF.width() - ((float) view2.getWidth()));
            }
        } else if (i10 != 8388611) {
            f = fArr[0];
        } else if (z) {
            f = (rectF.width() - ((float) view2.getWidth())) + fArr[0];
        } else {
            f = fArr[0];
        }
        if ((i2 & 112) == 80) {
            f5 = (rectF.height() - ((float) view2.getHeight())) + fArr[1];
        }
        return new float[]{f, f5};
    }

    public float getFocusedYPosition(PinchItem pinchItem, int i2) {
        View view = pinchItem.getView();
        if (view == null || startFromTopPosition()) {
            return (float) i2;
        }
        return (view.getY() + ((float) i2)) - (((float) (pinchItem.getToHeight() - view.getHeight())) / 2.0f);
    }

    public abstract int getHeightMeasureSpec(int i2);

    public abstract int getHintItemCount(int i2);

    public int getItemViewHeight(ListViewHolder listViewHolder, int i2, int i7) {
        return listViewHolder.getRootView().getHeight();
    }

    public int getItemViewWidth(ListViewHolder listViewHolder, int i2, int i7) {
        return listViewHolder.getRootView().getWidth();
    }

    public int getLastVisibleItemPosition() {
        return this.mLayoutManager.findLastVisibleItemPosition();
    }

    public abstract Comparator<PinchItem> getPositionComparator();

    public abstract ListViewHolder getRefViewHolder(int i2, boolean z);

    public float getRootX(View view) {
        float f = 0.0f;
        while (view.getId() != R$id.recycler_view_item) {
            f += view.getX();
            view = (View) view.getParent();
        }
        return f;
    }

    public float getRootY(View view) {
        float f = 0.0f;
        while (view.getId() != R$id.recycler_view_item) {
            f += view.getY();
            view = (View) view.getParent();
        }
        return f;
    }

    public float getSpanWidth(int i2, int i7) {
        return Math.max(((float) this.mLayoutManager.getHintWidthSpace(i2)) / ((float) i7), 1.0f);
    }

    public abstract int getWidthMeasureSpec(int i2);

    public float getXPosition(int i2, float f, int i7, int i8, int i10, int i11) {
        float hintPaddingLeft = (float) this.mLayoutManager.getHintPaddingLeft(i10);
        if (this.mIsRtl) {
            i2 = (i7 - i2) - i8;
        }
        return (f * ((float) i2)) + hintPaddingLeft;
    }

    public float getYPosition(PinchItem pinchItem) {
        if (pinchItem != null) {
            return pinchItem.getToRect().top;
        }
        return 0.0f;
    }

    public boolean isThumbKindChanged() {
        return false;
    }

    public void measureView(View view, int i2, int i7) {
        view.measure(getWidthMeasureSpec(i2), getHeightMeasureSpec(i7));
        view.layout(0, 0, i2, i7);
    }

    public final void putRecycledViewPool(ListViewHolder listViewHolder) {
        IPinchRecyclerView recyclerView;
        if (listViewHolder.getViewType() != 1 && (recyclerView = getRecyclerView()) != null) {
            ViewPoolInjector.inject(recyclerView.getRecycledViewPool(), listViewHolder, listViewHolder.getViewType());
        }
    }

    public void measureView(View view, float f, float f5, int i2, int i7) {
        view.measure(getWidthMeasureSpec(i2), getHeightMeasureSpec(i7));
        int i8 = (int) f;
        int i10 = (int) f5;
        view.layout(i8, i10, i2 + i8, i7 + i10);
    }

    public void calculatePosition(ArrayList<PinchItem> arrayList, int i2) {
        PinchItem pinchItem;
        int i7;
        float f;
        float f5;
        int i8;
        View findViewFromInvalidItem;
        ArrayList<PinchItem> arrayList2 = arrayList;
        Comparator<PinchItem> positionComparator = getPositionComparator();
        if (positionComparator != null) {
            arrayList2.sort(positionComparator);
        }
        View findFocusView = findFocusView();
        if (findFocusView != null) {
            int i10 = 0;
            while (true) {
                if (i10 >= arrayList2.size()) {
                    break;
                } else if (arrayList2.get(i10).getView() == findFocusView) {
                    pinchItem = arrayList2.get(i10);
                    i7 = i10;
                    break;
                } else {
                    i10++;
                }
            }
        }
        pinchItem = null;
        i7 = 0;
        PinchItem pinchItem2 = pinchItem;
        if (pinchItem2 != null || (findViewFromInvalidItem = findViewFromInvalidItem(findFocusView)) == null) {
            f5 = 0.0f;
            f = -1.0f;
        } else {
            f5 = findViewFromInvalidItem.getY() + ((float) findViewFromInvalidItem.getHeight());
            f = f5;
        }
        int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, this.mGridInfo.to());
        float spanWidth = getSpanWidth(this.mGridInfo.to(), hintSpanCount);
        float f8 = f5;
        int i11 = 0;
        int i12 = 0;
        int i13 = i7;
        while (i13 < arrayList2.size()) {
            PinchItem pinchItem3 = arrayList2.get(i13);
            int i14 = i11;
            float f10 = spanWidth;
            int toSpanIndex = pinchItem3.getToSpanIndex();
            PinchItem pinchItem4 = pinchItem3;
            int toSpanSize = pinchItem4.getToSpanSize();
            PinchItem pinchItem5 = pinchItem4;
            int i15 = i14;
            int i16 = this.mGridInfo.to();
            PinchItem pinchItem6 = pinchItem5;
            float xPosition = getXPosition(toSpanIndex, f10, hintSpanCount, toSpanSize, i16, pinchItem5.getItemViewType());
            if (pinchItem2 == null || pinchItem6 != pinchItem2) {
                int i17 = i2;
                if (pinchItem6.getToSpanSize() + i12 > hintSpanCount) {
                    f8 = (float) i15;
                    i12 = 0;
                }
            } else {
                float focusedYPosition = getFocusedYPosition(pinchItem2, i2);
                i12 = pinchItem6.getToSpanIndex();
                f8 = focusedYPosition;
            }
            i12 = pinchItem6.getToSpanSize() + i12;
            pinchItem6.calculateFromRect();
            if (ViewHolderValue.isHeader(pinchItem6.getItemViewType())) {
                pinchItem6.calculateToRect(xPosition, f8, (float) this.mShiftHeaderOffset);
            } else {
                pinchItem6.calculateToRect(xPosition, f8);
            }
            i13++;
            float f11 = f10;
            i11 = (int) pinchItem6.getToRect().bottom;
            spanWidth = f11;
        }
        float f12 = spanWidth;
        float yPosition = (float) ((int) getYPosition(pinchItem2));
        if (pinchItem2 == null) {
            i8 = 0;
        } else {
            i8 = pinchItem2.getToSpanIndex();
        }
        int i18 = i8;
        float f13 = yPosition;
        for (int i19 = i7 - 1; i19 >= 0; i19--) {
            PinchItem pinchItem7 = arrayList2.get(i19);
            float xPosition2 = getXPosition(pinchItem7.getToSpanIndex(), f12, hintSpanCount, pinchItem7.getToSpanSize(), this.mGridInfo.to(), pinchItem7.getItemViewType());
            if (i18 <= 0) {
                f13 -= (float) pinchItem7.getToHeight();
            }
            pinchItem7.calculateFromRect();
            pinchItem7.calculateToRect(xPosition2, f13);
            f13 = (float) ((int) getYPosition(pinchItem7));
            i18 = pinchItem7.getToSpanIndex();
        }
        if (pinchItem2 == null) {
            pinchItem2 = arrayList2.get(0);
        }
        Iterator<PinchItem> it = arrayList2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            PinchItem next = it.next();
            if (next.getToRect().top <= 0.0f && next.getToRect().bottom >= 0.0f) {
                pinchItem2 = next;
                break;
            }
        }
        int yPosition2 = (int) getYPosition(pinchItem2);
        this.mScrollOffset = yPosition2;
        if (f != -1.0f) {
            this.mScrollOffset = (int) (((float) yPosition2) + f);
        }
        if (pinchItem2 != null) {
            this.mScrollPosition = pinchItem2.getToViewPosition();
        }
    }

    public void addTranslationAnimator(View view, View view2, ListViewHolder listViewHolder, PinchItem pinchItem) {
        addTranslationAnimator(view, view2, listViewHolder, pinchItem.getToRect());
    }

    public void calculateHeaderViewSpace(ArrayList<PinchItem> arrayList) {
    }

    public void filterInvalidItems(ArrayList<PinchItem> arrayList) {
    }

    public View findViewFromInvalidItem(View view) {
        return view;
    }

    public void updateFakeViewSize(View view, int i2) {
    }
}
