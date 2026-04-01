package com.samsung.android.gallery.app.ui.list.pictures;

import D6.a;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import c0.C0086a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManagerLegacy;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import e5.c;
import e5.d;
import e5.e;
import i.C0212a;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PicturesPinchAnimationManager extends PinchAnimationManagerLegacy {
    private static final Comparator<PinchItem> POSITION_COMPARATOR = new a(25);
    private int mAnimationType = -1;
    private final ArrayList<View> mFadeInContentTypeViews = new ArrayList<>();
    private PinchFakeLayoutManager mFakeLayoutManager;
    private ViewGroup mFakeViewParent;
    private ListViewHolder mFromDataRefViewHolder;
    private ListViewHolder mHeaderRefHolder;
    private final ArrayList<PinchItem> mInvalidItems = new ArrayList<>();
    private final ArrayList<PinchItem> mPinchItems = new ArrayList<>();
    private final SparseArray<ListViewHolder> mRefHolders = new SparseArray<>();
    private float mShiftedOffset;

    public PicturesPinchAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo) {
        super(pinchLayoutManager, clusterInfo);
    }

    private void addFadeInAnimator(ArrayList<Animator> arrayList, View view) {
        if (view != null && view.getVisibility() == 0) {
            view.setAlpha(0.0f);
            arrayList.add(new AlphaAnimator(view, 0.0f, 1.0f));
        }
    }

    private void addFadeInContentsTypeAnimation(ArrayList<Animator> arrayList, ListViewHolder listViewHolder) {
        View decoView = listViewHolder.getDecoView(13);
        if (this.mFadeInContentTypeViews.contains(decoView)) {
            addFadeInAnimator(arrayList, decoView);
        }
    }

    private void addFadeInTitleViewAnimation(ArrayList<Animator> arrayList, View view) {
        ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(view);
        if (listViewHolder == null) {
            return;
        }
        if (ViewHolderValue.isDivider(listViewHolder.getViewType()) || ViewHolderValue.isFooter(listViewHolder.getItemViewType())) {
            view.setAlpha(0.0f);
            arrayList.add(new AlphaAnimator(view, 0.0f, 1.0f));
        }
    }

    private void addItemAnimator(ListViewHolder listViewHolder, int i2, int i7, HashMap<Integer, ArrayList<RectF>> hashMap) {
        RectF rect = getRect(hashMap, i2, i7, this.mShiftedOffset);
        if (listViewHolder.getRootView() != null && rect != null) {
            TranslationAnimator translationAnimator = new TranslationAnimator(listViewHolder.getRootView(), rect, rect);
            translationAnimator.setAnimationListener(new c(this, 1));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    private void assertDataViewType(int i2) {
        if (!ViewHolderValue.isData(i2)) {
            throw new AssertionError(C0212a.j(i2, "not data view type (", ")"));
        }
    }

    private void assertViewHolder(String str) {
        Log.e(this.TAG, str);
        new InternalException(str).post();
    }

    private void createCheckBoxAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        ListViewHolder childViewPositionRefHolder;
        assertDataViewType(pinchItem.getItemViewType());
        if (getRecyclerView().isSelectionMode() && (childViewPositionRefHolder = getChildViewPositionRefHolder()) != null) {
            addTranslationAnimator((View) listViewHolder.getCheckbox(), (View) childViewPositionRefHolder.getCheckbox(), childViewPositionRefHolder, pinchItem);
        }
    }

    private void createContentTypeViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        assertDataViewType(pinchItem.getItemViewType());
        View decoView = listViewHolder.getDecoView(13);
        ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
        if (childViewPositionRefHolder != null) {
            addTranslationAnimator(decoView, childViewPositionRefHolder.getDecoView(13), childViewPositionRefHolder, pinchItem);
        }
        if (decoView != null && decoView.getVisibility() != 8 && isHideContentsDurationView(pinchItem, listViewHolder)) {
            addAlphaAnimator(decoView, 0.0f, 0.0f, 1.0f);
            this.mFadeInContentTypeViews.add(decoView);
        }
    }

    private void createDividerViewAnim(ListViewHolder listViewHolder) {
        View dividerView = listViewHolder.getDividerView();
        if (dividerView != null) {
            RectF rect = getRect(dividerView);
            TranslationAnimator translationAnimator = new TranslationAnimator(dividerView, rect, new RectF(rect.left, rect.top, rect.right, rect.bottom));
            translationAnimator.setAnimationListener(new c(this, 3));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    private void createExpandViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        ListViewHolder childViewPositionRefHolder;
        assertDataViewType(pinchItem.getItemViewType());
        if (getRecyclerView().isSelectionMode() && (childViewPositionRefHolder = getChildViewPositionRefHolder()) != null) {
            addTranslationAnimator(listViewHolder.getDecoView(12), childViewPositionRefHolder.getDecoView(12), childViewPositionRefHolder, pinchItem);
        }
    }

    private ListViewHolder createFromDataRefView(int[] iArr) {
        ListViewHolder listViewHolder;
        int i2 = 0;
        while (true) {
            if (i2 >= this.mLayoutManager.getChildCount()) {
                listViewHolder = null;
                break;
            }
            listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null && ViewHolderValue.isData(listViewHolder.getViewType())) {
                iArr[0] = this.mLayoutManager.getPosition(listViewHolder.getRootView());
                break;
            }
            i2++;
        }
        if (listViewHolder != null) {
            return listViewHolder;
        }
        while (true) {
            if (this.mLayoutManager.getHintItemViewType(iArr[0], this.mGridInfo.from()) == 0) {
                break;
            }
            int i7 = iArr[0] + 1;
            iArr[0] = i7;
            if (i7 > this.mLayoutManager.getItemCount()) {
                iArr[0] = -1;
                break;
            }
        }
        int i8 = iArr[0];
        if (i8 != -1) {
            return getDataRefViewHolder(i8, this.mGridInfo.from());
        }
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(this.mFakeViewParent, 0);
        ViewUtils.removeSelf(createFakeViewHolderInternal.itemView);
        return createFakeViewHolderInternal;
    }

    private ListViewHolder createHeaderViewHolder() {
        if (!this.mLayoutManager.hasHeader()) {
            return null;
        }
        ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(0));
        if (listViewHolder != null && ViewHolderValue.isHeader(listViewHolder.getItemViewType())) {
            return listViewHolder;
        }
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(this.mFakeViewParent, -3);
        this.mLayoutManager.bindHolder(createFakeViewHolderInternal, 0);
        return createFakeViewHolderInternal;
    }

    private void createImageViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder, boolean z) {
        float f;
        float f5;
        assertDataViewType(pinchItem.getItemViewType());
        ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
        if (childViewPositionRefHolder == null) {
            assertViewHolder("createImageViewAnim fail null vh{" + pinchItem.getItemViewType() + ",null}");
            return;
        }
        View rootView = childViewPositionRefHolder.getRootView();
        View scalableView = childViewPositionRefHolder.getScalableView();
        if (rootView == null || scalableView == null) {
            assertViewHolder("createImageViewAnim fail to get image-view from vh{" + pinchItem.getItemViewType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + childViewPositionRefHolder + "}");
            return;
        }
        View scalableView2 = listViewHolder.getScalableView();
        RectF rect = getRect(scalableView2);
        float[] childToXY = getChildToXY(scalableView2, rootView, getBaseToXY(scalableView2, scalableView), pinchItem.getToRect());
        if (this.mAnimationType == 1) {
            f5 = pinchItem.getToRect().width() - ((float) (rootView.getWidth() - scalableView.getWidth()));
            f = pinchItem.getToRect().height() - ((float) (rootView.getHeight() - scalableView.getHeight()));
        } else {
            f5 = (float) scalableView.getWidth();
            f = (float) scalableView.getHeight();
        }
        float f8 = childToXY[0];
        float f10 = childToXY[1];
        RectF rectF = new RectF(f8, f10, f5 + f8, f + f10);
        TranslationAnimator translationAnimator = new TranslationAnimator(scalableView2, rect, rectF);
        translationAnimator.setAnimationListener(new c(this, 2));
        addAnimation((PropertyAnimator) translationAnimator);
        ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView2, rect, rectF);
        scaleAnimator.enableUpdateLayoutParam(z);
        scaleAnimator.setAnimationListener(new e(z));
        addAnimation((PropertyAnimator) scaleAnimator);
    }

    private void createNDaysViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        ListViewHolder childViewPositionRefHolder;
        assertDataViewType(pinchItem.getItemViewType());
        View decoView = listViewHolder.getDecoView(18);
        if (decoView != null && (childViewPositionRefHolder = getChildViewPositionRefHolder()) != null) {
            addTranslationAnimator(decoView, childViewPositionRefHolder.getDecoView(18), childViewPositionRefHolder, pinchItem);
            WidthAnimator widthAnimator = new WidthAnimator((View) decoView.getParent(), decoView.getWidth(), getRefImageViewWidth(childViewPositionRefHolder, pinchItem.getToRect()), new d(1));
            widthAnimator.setAnimationListener(new d(2));
            addAnimation((PropertyAnimator) widthAnimator);
        }
    }

    private void createReferenceView() {
        initFakeViewLayout(4);
        this.mRefHolders.put(-4, createFakeViewHolderInternal(this.mFakeViewParent, -4));
        this.mRefHolders.put(-3, createHeaderViewHolder());
        this.mRefHolders.put(-1, createFakeViewHolderInternal(this.mFakeViewParent, -1));
        this.mRefHolders.put(-2, createFakeViewHolderInternal(this.mFakeViewParent, -2));
        int[] iArr = new int[1];
        this.mFromDataRefViewHolder = createFromDataRefView(iArr);
        ListViewHolder createToDataRefView = createToDataRefView(iArr[0]);
        setViewHolderMargin(createToDataRefView, this.mGridInfo.getRealToGrid());
        this.mRefHolders.put(0, createToDataRefView);
        ViewGroup viewGroup = (ViewGroup) getRecyclerView().getParent();
        measureView(this.mFakeViewParent, viewGroup.getWidth(), viewGroup.getHeight());
    }

    private void createStorageFavoriteSlot1ViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        assertDataViewType(pinchItem.getItemViewType());
        ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
        if (childViewPositionRefHolder != null) {
            addTranslationAnimator(listViewHolder.getDecoView(16), childViewPositionRefHolder.getDecoView(16), childViewPositionRefHolder, pinchItem);
        }
    }

    private void createStorageFavoriteSlot2ViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        assertDataViewType(pinchItem.getItemViewType());
        ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
        if (childViewPositionRefHolder != null) {
            addTranslationAnimator(listViewHolder.getDecoView(17), childViewPositionRefHolder.getDecoView(17), childViewPositionRefHolder, pinchItem);
        }
    }

    private void createTitleViewAnim(PinchItem pinchItem) {
        int hintWidthSpace;
        int hintWidthSpace2;
        if ((ViewHolderValue.isDivider(pinchItem.getItemViewType()) || ViewHolderValue.isFooter(pinchItem.getItemViewType()) || ViewHolderValue.isHeader(pinchItem.getItemViewType())) && (hintWidthSpace = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.from())) != (hintWidthSpace2 = this.mLayoutManager.getHintWidthSpace(this.mGridInfo.to()))) {
            WidthAnimator widthAnimator = new WidthAnimator(pinchItem.getView(), hintWidthSpace, hintWidthSpace2, new W.a(29));
            widthAnimator.setAnimationListener(new d(0));
            addAnimation((PropertyAnimator) widthAnimator);
        }
    }

    private void createVisualCueIconViewAnim(PinchItem pinchItem, ListViewHolder listViewHolder) {
        assertDataViewType(pinchItem.getItemViewType());
        ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
        if (childViewPositionRefHolder != null) {
            View decoView = listViewHolder.getDecoView(14);
            addTranslationAnimator(decoView, childViewPositionRefHolder.getDecoView(14), childViewPositionRefHolder, pinchItem);
            addAlphaAnimator(decoView, 0.0f, 0.0f, 1.0f);
        }
    }

    private void fetchDecoViewState(int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i7 = 0; i7 < this.mLayoutManager.getChildCount(); i7++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i7));
            if (listViewHolder != null) {
                arrayList.add(listViewHolder);
            }
        }
        if (i2 != 2) {
            for (int i8 = 0; i8 < this.mFakeViewHolders.size(); i8++) {
                SparseArray<ListViewHolder> sparseArray = this.mFakeViewHolders;
                ListViewHolder listViewHolder2 = sparseArray.get(sparseArray.keyAt(i8));
                if (listViewHolder2 != null) {
                    arrayList.add(listViewHolder2);
                }
            }
        }
        if (!this.mLayoutManager.isHintHideDecoItem(this.mGridInfo.from()) || i2 == 2) {
            int decoItemViewType = getDecoItemViewType(i2);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ListViewHolder listViewHolder3 = (ListViewHolder) it.next();
                if (listViewHolder3.getItemViewType() == 0) {
                    if (!isSelectionMode()) {
                        listViewHolder3.setSupportDecoItemType(decoItemViewType);
                        listViewHolder3.updateDecoItemViews();
                    } else if (isSingleSelectionMode()) {
                        if ((listViewHolder3.getSupportDecoItemType() & 32) != 0) {
                            decoItemViewType |= 32;
                        } else {
                            decoItemViewType &= -33;
                        }
                        listViewHolder3.setCheckboxEnabled(decoItemViewType, false);
                    } else {
                        listViewHolder3.setCheckboxEnabled(decoItemViewType, true);
                    }
                }
            }
        }
    }

    private ListViewHolder getChildViewPositionRefHolder() {
        return this.mRefHolders.get(0);
    }

    private int getDecoItemViewType(int i2) {
        int i7;
        int i8;
        PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
        boolean z = true;
        GridInfo gridInfo = this.mGridInfo;
        if (i2 == 1) {
            i7 = gridInfo.from();
        } else {
            i7 = gridInfo.to();
        }
        if (pinchLayoutManager.isHintHideDecoItem(i7)) {
            return 0;
        }
        if (!isSelectionMode()) {
            return 76;
        }
        if (isSingleSelectionMode()) {
            i8 = 94;
        } else {
            i8 = 95;
        }
        if (i2 == 0) {
            if (!hasSpaceForStorageType(this.mGridInfo.to()) || !hasSpaceForStorageType(this.mGridInfo.from())) {
                z = false;
            }
        } else if (i2 == 1) {
            z = hasSpaceForStorageType(this.mGridInfo.from());
        } else {
            z = hasSpaceForStorageType(this.mGridInfo.to());
        }
        if (z) {
            return (i8 | 16) & -129;
        }
        return (i8 & -17) | 128;
    }

    private PinchItem getDividerPositionItem(int i2, GridInfo gridInfo) {
        PinchItem pinchItem = new PinchItem((View) null, -1, i2, gridInfo);
        if (i2 == 1) {
            pinchItem.setItemViewType(-1);
            pinchItem.calculateTo(this.mLayoutManager, this.mPositionCache, getRefRootView(-1));
            return pinchItem;
        }
        pinchItem.setItemViewType(-2);
        pinchItem.calculateTo(this.mLayoutManager, this.mPositionCache, getRefRootView(-2));
        return pinchItem;
    }

    private float[] getFocusedPositionInfo() {
        int i2;
        char c5;
        float[] fArr;
        float f;
        float[] focusXY = getFocusXY();
        char c6 = 1;
        if (this.mLayoutManager.isAppbarVisible() || focusXY == null) {
            return new float[]{0.0f, (float) this.mPositionCache.getHintViewHeight(this.mLayoutManager, 0, this.mGridInfo.from()), 0.0f, 0.0f};
        }
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        float[] fArr2 = new float[4];
        double d = Double.MAX_VALUE;
        while (findFirstVisibleItemPosition <= findLastVisibleItemPosition) {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition == null || !focusable(findViewByPosition)) {
                fArr = fArr2;
                c5 = c6;
                i2 = findLastVisibleItemPosition;
            } else {
                float width = (((float) findViewByPosition.getWidth()) / 2.0f) + findViewByPosition.getX();
                float height = (((float) findViewByPosition.getHeight()) / 2.0f) + findViewByPosition.getY();
                fArr = fArr2;
                c5 = c6;
                i2 = findLastVisibleItemPosition;
                double sqrt = Math.sqrt(Math.pow((double) (height - focusXY[c6]), 2.0d) + Math.pow((double) (width - focusXY[0]), 2.0d));
                if (sqrt < d) {
                    fArr[0] = (float) findFirstVisibleItemPosition;
                    if (!this.mGridInfo.fromYear()) {
                        f = findViewByPosition.getY();
                    } else if (findViewByPosition.getY() < 0.0f) {
                        f = focusXY[c5] - findViewByPosition.getY();
                    } else {
                        f = focusXY[c5];
                    }
                    fArr[c5] = f;
                    fArr[2] = focusXY[0];
                    fArr[3] = focusXY[c5] - findViewByPosition.getY();
                    d = sqrt;
                }
            }
            findFirstVisibleItemPosition++;
            fArr2 = fArr;
            c6 = c5;
            findLastVisibleItemPosition = i2;
        }
        return fArr2;
    }

    private int getMinWidthShowStorage() {
        return getRecyclerView().getResources().getDimensionPixelSize(R.dimen.storage_type_icon_display_min_width);
    }

    private RectF getRect(HashMap<Integer, ArrayList<RectF>> hashMap, int i2, int i7, float f) {
        ArrayList arrayList = hashMap.get(Integer.valueOf(i2));
        if (arrayList == null) {
            return null;
        }
        RectF rectF = (RectF) arrayList.get(i7);
        if (rectF == null || f == 0.0f) {
            return rectF;
        }
        return new RectF(rectF.left, rectF.top + f, rectF.right, rectF.bottom + f);
    }

    private int getRefImageViewWidth(ListViewHolder listViewHolder, RectF rectF) {
        float f;
        if (this.mAnimationType == 1) {
            f = rectF.width() - ((float) (listViewHolder.getRootView().getWidth() - listViewHolder.getScalableView().getWidth()));
        } else {
            f = (float) listViewHolder.getScalableView().getWidth();
        }
        return (int) f;
    }

    private boolean hasSpaceForStorageType(int i2) {
        int i7;
        if (this.mLayoutManager.getHintWidthSpace(i2) / this.mPositionCache.getHintSpanCount(this.mLayoutManager, i2) >= getMinWidthShowStorage() || (i7 = this.mAnimationType) == 1 || i7 == 2) {
            return true;
        }
        return false;
    }

    private void hideDecoView(ListViewHolder listViewHolder) {
        ViewUtils.setVisibility(listViewHolder.getDecoView(13), 4);
        ViewUtils.setVisibility(listViewHolder.getDecoView(14), 4);
        ViewUtils.setVisibility(listViewHolder.getDecoView(16), 4);
        if (getRecyclerView().isSelectionMode()) {
            ViewUtils.setVisibility(listViewHolder.getCheckbox(), 4);
            ViewUtils.setVisibility(listViewHolder.getDecoView(12), 4);
            ViewUtils.setVisibility(listViewHolder.getDecoView(17), 4);
        }
    }

    private void initFakeViewLayout(int i2) {
        ViewGroup viewGroup = (ViewGroup) ((View) getRecyclerView().getParent()).findViewById(R.id.fake_view_layout);
        this.mFakeViewParent = viewGroup;
        viewGroup.setVisibility(i2);
    }

    private void initializeAlphaAnimation() {
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) getRecyclerView(), 1.0f, 0.0f);
        alphaAnimator.setAnimationListener(new d(3));
        addAnimation((PropertyAnimator) alphaAnimator);
        addAnimation((PropertyAnimator) new AlphaAnimator((View) this.mFakeViewParent, 0.0f, 1.0f));
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void initializeChildViewAnim() {
        /*
            r4 = this;
            r0 = 0
            r4.fetchDecoViewState(r0)
            java.util.ArrayList<com.samsung.android.gallery.widget.listview.pinch.PinchItem> r0 = r4.mPinchItems
            java.util.Iterator r0 = r0.iterator()
        L_0x000a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x006b
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.widget.listview.pinch.PinchItem r1 = (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r1
            int r2 = r1.getItemViewType()
            boolean r2 = com.samsung.android.gallery.widget.listviewholder.ViewHolderValue.isData(r2)
            if (r2 == 0) goto L_0x0042
            android.util.SparseArray<com.samsung.android.gallery.widget.listviewholder.ListViewHolder> r2 = r4.mFakeViewHolders
            int r3 = r1.getFromViewPosition()
            java.lang.Object r2 = r2.get(r3)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2
            if (r2 != 0) goto L_0x0038
            android.view.View r2 = r1.getView()
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r4.getChildViewHolder(r2)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2
        L_0x0038:
            if (r2 == 0) goto L_0x000a
            boolean r3 = r4.isViewSizeChanged()
            r4.createDataChildAnim(r1, r2, r3)
            goto L_0x000a
        L_0x0042:
            int r2 = r1.getItemViewType()
            boolean r2 = com.samsung.android.gallery.widget.listviewholder.ViewHolderValue.isDivider(r2)
            if (r2 == 0) goto L_0x000a
            android.util.SparseArray<com.samsung.android.gallery.widget.listviewholder.ListViewHolder> r2 = r4.mFakeViewHolders
            int r3 = r1.getFromViewPosition()
            java.lang.Object r2 = r2.get(r3)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2
            if (r2 != 0) goto L_0x0065
            android.view.View r1 = r1.getView()
            androidx.recyclerview.widget.RecyclerView$ViewHolder r1 = r4.getChildViewHolder(r1)
            r2 = r1
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r2 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r2
        L_0x0065:
            if (r2 == 0) goto L_0x000a
            r4.createDividerViewAnim(r2)
            goto L_0x000a
        L_0x006b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManager.initializeChildViewAnim():void");
    }

    private void initializeFakeViewLayoutTransAnimation() {
        int i2;
        int startDataRow = this.mFakeLayoutManager.getStartDataRow();
        int startDividerRow = this.mFakeLayoutManager.getStartDividerRow();
        Iterator<ListViewHolder> it = this.mFakeLayoutManager.getFakeViewHolders().iterator();
        int i7 = 0;
        int i8 = -1;
        boolean z = true;
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            int hintStartSpan = this.mPositionCache.getHintStartSpan(this.mLayoutManager, next.getViewPosition(), this.mGridInfo.to());
            if (!z && hintStartSpan <= 0) {
                if (ViewHolderValue.isData(i8)) {
                    startDataRow++;
                } else {
                    if (startDividerRow == -1) {
                        i2 = 2;
                    } else {
                        i2 = 1;
                    }
                    startDividerRow += i2;
                }
                i7 = 0;
            }
            i8 = next.getViewType();
            if (ViewHolderValue.isData(i8)) {
                addItemAnimator(next, startDataRow, i7, this.mFakeLayoutManager.getFakeDataRect());
            } else {
                addItemAnimator(next, startDividerRow, 0, this.mFakeLayoutManager.getFakeDividerRect());
            }
            i7++;
            z = false;
        }
    }

    private void initializeInvalidItemAnim() {
        Iterator<PinchItem> it = this.mInvalidItems.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.getView() != null) {
                AlphaAnimator alphaAnimator = new AlphaAnimator(next.getView(), 0.0f, 0.0f);
                alphaAnimator.setAnimationListener(new com.samsung.android.sdk.scs.ai.language.a(15, next));
                addAnimation((PropertyAnimator) alphaAnimator);
            }
        }
    }

    private void initializeItemViewAnim() {
        Iterator<PinchItem> it = this.mPinchItems.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            if (next.getView() != null) {
                createViewAnim(next);
            }
        }
    }

    private boolean isDataSetChanged() {
        int i2 = this.mAnimationType;
        if (i2 == 4 || i2 == 3 || i2 == 5) {
            return true;
        }
        return false;
    }

    private boolean isHideContentsDurationView(PinchItem pinchItem, ListViewHolder listViewHolder) {
        boolean z;
        boolean z3;
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem == null) {
            return false;
        }
        int fileDuration = mediaItem.getFileDuration();
        View decoView = listViewHolder.getDecoView(15);
        if (decoView != null && fileDuration > 0) {
            if (this.mFakeViewHolders.get(pinchItem.getFromViewPosition()) != null) {
                listViewHolder.updateDuration(getItemViewWidth(this.mFromDataRefViewHolder, pinchItem.getFromViewPosition(), this.mGridInfo.from()));
            }
            ListViewHolder childViewPositionRefHolder = getChildViewPositionRefHolder();
            if (childViewPositionRefHolder != null) {
                int width = childViewPositionRefHolder.getRootView().getWidth();
                if (decoView.getVisibility() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                boolean isDurationAvailable = childViewPositionRefHolder.isDurationAvailable(mediaItem, fileDuration, width);
                boolean hasEnoughSpaceForDuration = childViewPositionRefHolder.hasEnoughSpaceForDuration(mediaItem, width, fileDuration);
                if (!isDurationAvailable || !hasEnoughSpaceForDuration) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if ((!z || z3) && (z || !z3)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createImageViewAnim$10(boolean z, View view) {
        if (z) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            view.setLayoutParams(layoutParams);
        } else {
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
        view.setPivotX(((float) view.getWidth()) / 2.0f);
        view.setPivotY(((float) view.getHeight()) / 2.0f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createNDaysViewAnim$4(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createNDaysViewAnim$5(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$createTitleViewAnim$7(View view, int i2) {
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createTitleViewAnim$8(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -1;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFakeViewLayoutAlpha$2() {
        this.mFakeViewParent.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$12(PinchItem pinchItem, PinchItem pinchItem2) {
        if (pinchItem.getToViewPosition() > pinchItem2.getToViewPosition()) {
            return 1;
        }
        return -1;
    }

    private void preparePinchItems() {
        int viewPosition;
        View view;
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = this.mLayoutManager.findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition < 0 || findLastVisibleItemPosition < 0) {
            String str = this.TAG;
            Log.w(str, "invalid visibleRange=" + findFirstVisibleItemPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + findLastVisibleItemPosition);
            return;
        }
        int i2 = (findLastVisibleItemPosition - findFirstVisibleItemPosition) + 1;
        for (int i7 = 0; i7 < i2; i7++) {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition + i7);
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(findViewByPosition);
            if (!(listViewHolder == null || (viewPosition = listViewHolder.getViewPosition()) == -1)) {
                ListViewHolder refViewHolder = getRefViewHolder(viewPosition, true);
                PinchItem pinchItem = new PinchItem(findViewByPosition, viewPosition, this.mGridInfo);
                pinchItem.setItemViewType(listViewHolder.getViewType());
                try {
                    PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
                    AnimPositionCache animPositionCache = this.mPositionCache;
                    if (refViewHolder != null) {
                        view = refViewHolder.getRootView();
                    } else {
                        view = null;
                    }
                    pinchItem.calculateTo(pinchLayoutManager, animPositionCache, view);
                    this.mPinchItems.add(pinchItem);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i2 != this.mPinchItems.size()) {
            String str2 = this.TAG;
            StringBuilder o2 = C0086a.o(i2, "visibleItem=", ", pinchItem=");
            o2.append(this.mPinchItems.size());
            Log.e(str2, o2.toString());
        }
        if (isDataSetChanged()) {
            filterInvalidItems(this.mPinchItems);
        }
    }

    private void removeHeaderRefView() {
        ListViewHolder listViewHolder = this.mHeaderRefHolder;
        if (listViewHolder != null) {
            listViewHolder.recycle();
            ViewUtils.removeSelf(this.mHeaderRefHolder.getRootView());
            ViewUtils.setVisibility(this.mHeaderRefHolder.getRootView(), 0);
            putRecycledViewPool(this.mHeaderRefHolder);
            this.mHeaderRefHolder = null;
        }
    }

    private void setFakeViewLayoutAlpha() {
        this.mFakeViewParent.setAlpha(0.0f);
        this.mFakeViewParent.post(new C0451a(1, this));
    }

    private void startFadeInAnimation() {
        ListViewHolder listViewHolder;
        int childCount = this.mLayoutManager.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            if (!(childAt == null || (listViewHolder = (ListViewHolder) getChildViewHolder(childAt)) == null)) {
                if (isDataSetChanged()) {
                    addFadeInTitleViewAnimation(arrayList, childAt);
                }
                addFadeInContentsTypeAnimation(arrayList, listViewHolder);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(150).playTogether(arrayList);
        animatorSet.start();
    }

    public void addFakeView(int i2, float f) {
        if (this.mFakeLayoutManager == null) {
            this.mFakeLayoutManager = createFakeLayoutManager(this.mFakeViewParent);
        }
        this.mScrollPosition = i2;
        int addFakeView = (int) this.mFakeLayoutManager.addFakeView(new PinchFakeLayoutManager.CalculateInfo(this.mGridInfo.to(), i2, f), (PinchFakeLayoutManager.CalculateRange) null);
        this.mScrollOffset = addFakeView;
        this.mShiftedOffset = ((float) addFakeView) - f;
    }

    public void clearAnimationInfo() {
        this.mPinchItems.clear();
        this.mInvalidItems.clear();
        this.mFromDataRefViewHolder = null;
        for (int i2 = 0; i2 < this.mRefHolders.size(); i2++) {
            ListViewHolder listViewHolder = this.mRefHolders.get(this.mRefHolders.keyAt(i2));
            if (listViewHolder != null) {
                View rootView = listViewHolder.getRootView();
                ViewGroup viewGroup = (ViewGroup) rootView.getParent();
                if (viewGroup == this.mFakeViewParent) {
                    if (!ViewHolderValue.isHeader(listViewHolder.getItemViewType())) {
                        ViewUtils.removeView(viewGroup, rootView);
                        listViewHolder.recycle();
                        putRecycledViewPool(listViewHolder);
                    } else {
                        this.mHeaderRefHolder = listViewHolder;
                        ViewUtils.setVisibility(rootView, 4);
                    }
                }
            }
        }
        this.mRefHolders.clear();
    }

    public void createDataChildAnim(PinchItem pinchItem, ListViewHolder listViewHolder, boolean z) {
        createImageViewAnim(pinchItem, listViewHolder, z);
        createCheckBoxAnim(pinchItem, listViewHolder);
        createExpandViewAnim(pinchItem, listViewHolder);
        createStorageFavoriteSlot1ViewAnim(pinchItem, listViewHolder);
        createStorageFavoriteSlot2ViewAnim(pinchItem, listViewHolder);
        createContentTypeViewAnim(pinchItem, listViewHolder);
        createNDaysViewAnim(pinchItem, listViewHolder);
        createVisualCueIconViewAnim(pinchItem, listViewHolder);
    }

    public ListViewHolder createFakeViewHolder(int i2, int i7) {
        int hintItemViewType = this.mLayoutManager.getHintItemViewType(i2, i7);
        ListViewHolder createFakeViewHolderInternal = createFakeViewHolderInternal(this.mFakeViewParent, hintItemViewType);
        View rootView = createFakeViewHolderInternal.getRootView();
        if (ViewHolderValue.isData(hintItemViewType)) {
            int hintSpanCount = this.mPositionCache.getHintSpanCount(this.mLayoutManager, i7);
            int hintColumnSpan = this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i2, i7);
            int hintViewHeight = this.mPositionCache.getHintViewHeight(this.mLayoutManager, i2, i7);
            ViewGroup.LayoutParams layoutParams = rootView.getLayoutParams();
            layoutParams.width = (int) (((((float) this.mLayoutManager.getHintWidthSpace(i7)) / ((float) hintSpanCount)) * ((float) hintColumnSpan)) + 0.5f);
            layoutParams.height = hintViewHeight;
            rootView.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams2 = rootView.getLayoutParams();
            layoutParams2.width = this.mLayoutManager.getHintWidthSpace(i7);
            rootView.setLayoutParams(layoutParams2);
        }
        setViewHolderMargin(createFakeViewHolderInternal, this.mLayoutManager.getRealGridSize(i7));
        hideDecoView(createFakeViewHolderInternal);
        if ((ViewHolderValue.isDivider(hintItemViewType) || ViewHolderValue.isFooter(hintItemViewType)) && !isSelectionMode()) {
            createFakeViewHolderInternal.setCheckboxEnabled(false);
        }
        return createFakeViewHolderInternal;
    }

    public ListViewHolder createToDataRefView(int i2) {
        return getDataRefViewHolder(this.mPositionCache.getHintViewPosition(this.mLayoutManager, i2, this.mGridInfo.from(), this.mGridInfo.to()), this.mGridInfo.to());
    }

    public void createViewAnim(PinchItem pinchItem) {
        TranslationAnimator translationAnimator = new TranslationAnimator(pinchItem.getView(), pinchItem.getFromRect(), pinchItem.getToRect());
        translationAnimator.setAnimationListener(new c(this, 0));
        addAnimation((PropertyAnimator) translationAnimator);
        createTitleViewAnim(pinchItem);
    }

    public void filterInvalidItems(ArrayList<PinchItem> arrayList) {
        if (arrayList.isEmpty()) {
            Log.w(this.TAG, "filterInvalidItems#No animate view");
            return;
        }
        arrayList.sort(getPositionComparator());
        ArrayList arrayList2 = new ArrayList();
        int toViewPosition = arrayList.get(0).getToViewPosition();
        if (isDataSetChanged()) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                PinchItem pinchItem = arrayList.get(i2);
                if (!ViewHolderValue.isDivider(pinchItem.getItemViewType()) && !ViewHolderValue.isFooter(pinchItem.getItemViewType())) {
                    if (pinchItem.getToViewPosition() - toViewPosition > 1) {
                        arrayList2.add(getDividerPositionItem(pinchItem.getToViewPosition() - 1, this.mGridInfo));
                    }
                    arrayList2.add(pinchItem);
                    toViewPosition = pinchItem.getToViewPosition();
                } else if (ViewHolderValue.isHeader(pinchItem.getItemViewType())) {
                    arrayList2.add(pinchItem);
                    toViewPosition = pinchItem.getToViewPosition();
                } else if (pinchItem.getView() != null) {
                    this.mInvalidItems.add(pinchItem);
                }
            }
        } else {
            arrayList2.addAll(arrayList);
        }
        arrayList.clear();
        arrayList.addAll(arrayList2);
    }

    public View findViewFromInvalidItem(View view) {
        if (view == null) {
            return null;
        }
        Iterator<PinchItem> it = this.mInvalidItems.iterator();
        while (it.hasNext()) {
            if (it.next().getView() == view) {
                return view;
            }
        }
        return null;
    }

    public boolean focusable(View view) {
        ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(view);
        if (listViewHolder == null || !ViewHolderValue.isData(listViewHolder.getViewType())) {
            return false;
        }
        return true;
    }

    public abstract int getAnimationType();

    public ListViewHolder getDataRefViewHolder(int i2, int i7) {
        ListViewHolder createFakeViewHolder = createFakeViewHolder(i2, i7);
        hideDecoView(createFakeViewHolder);
        ImageView imageView = (ImageView) createFakeViewHolder.getDecoView(16);
        if (imageView != null) {
            ViewUtils.setVisibility(imageView, 4);
            imageView.setImageResource(R.drawable.gallery_ic_thumbnail_cloud_only);
        }
        ImageView imageView2 = (ImageView) createFakeViewHolder.getDecoView(17);
        if (imageView2 != null) {
            ViewUtils.setVisibility(imageView2, 4);
            imageView2.setImageResource(R.drawable.gallery_ic_thumbnail_cloud_only);
        }
        return createFakeViewHolder;
    }

    public int getHeightMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    public int getHintItemCount(int i2) {
        return this.mLayoutManager.getHintItemCount(i2);
    }

    public int getItemViewHeight(ListViewHolder listViewHolder, int i2, int i7) {
        if (!isViewSizeChanged() || !ViewHolderValue.isData(listViewHolder.getViewType())) {
            return super.getItemViewHeight(listViewHolder, i2, i7);
        }
        return this.mPositionCache.getHintViewHeight(this.mLayoutManager, i2, i7);
    }

    public int getItemViewWidth(ListViewHolder listViewHolder, int i2, int i7) {
        if (!isViewSizeChanged() || !ViewHolderValue.isData(listViewHolder.getViewType())) {
            return super.getItemViewWidth(listViewHolder, i2, i7);
        }
        return (int) (getSpanWidth(i7, this.mPositionCache.getHintSpanCount(this.mLayoutManager, i7)) * ((float) this.mPositionCache.getHintColumnSpan(this.mLayoutManager, i2, i7)));
    }

    public Comparator<PinchItem> getPositionComparator() {
        return POSITION_COMPARATOR;
    }

    public final View getRefRootView(int i2) {
        ListViewHolder listViewHolder = this.mRefHolders.get(i2);
        if (listViewHolder != null) {
            return listViewHolder.getRootView();
        }
        return null;
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        int hintItemViewType = this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from());
        if (!ViewHolderValue.isData(hintItemViewType)) {
            return this.mRefHolders.get(hintItemViewType);
        }
        if (!z) {
            return this.mFromDataRefViewHolder;
        }
        if (this.mAnimationType != 1) {
            return this.mRefHolders.get(hintItemViewType);
        }
        return null;
    }

    public int getWidthMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    public void initializeAnimation() {
        initializeItemViewAnim();
        initializeChildViewAnim();
        initializeInvalidItemAnim();
    }

    public boolean isThumbKindChanged() {
        int i2 = this.mAnimationType;
        if (i2 == 4 || i2 == 3 || i2 == 5) {
            return true;
        }
        return false;
    }

    public boolean isViewSizeChanged() {
        int i2 = this.mAnimationType;
        if (i2 == 2 || i2 == 1) {
            return true;
        }
        return false;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        int i2;
        if (z) {
            i2 = 2;
        } else {
            i2 = 1;
        }
        fetchDecoViewState(i2);
        removeHeaderRefView();
        ViewUtils.setVisibility(this.mFakeViewParent, 8);
        super.onAnimationCompleted(z, z3);
        ViewUtils.removeAllViews(this.mFakeViewParent);
        PinchFakeLayoutManager pinchFakeLayoutManager = this.mFakeLayoutManager;
        if (pinchFakeLayoutManager != null) {
            pinchFakeLayoutManager.clear();
            this.mFakeLayoutManager = null;
        }
    }

    public void onAnimationStarted() {
        ViewUtils.setVisibility(this.mFakeViewParent, 0);
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            startFadeInAnimation();
            this.mFadeInContentTypeViews.clear();
            setItemViewMargin();
            super.onLayout();
            this.mAnimationType = -1;
        }
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        super.onPrepareAnimation(i2, i7, i8);
        this.mAnimationType = getAnimationType();
        if (this.mGridInfo.withYear()) {
            initFakeViewLayout(0);
            float[] focusedPositionInfo = getFocusedPositionInfo();
            PinchLayoutManager pinchLayoutManager = this.mLayoutManager;
            addFakeView(pinchLayoutManager.getHintViewPosition(pinchLayoutManager.getHintDataPosition((int) focusedPositionInfo[0], focusedPositionInfo[2], focusedPositionInfo[3], this.mGridInfo.from()), this.mGridInfo.to()), focusedPositionInfo[1]);
            initializeAlphaAnimation();
            initializeFakeViewLayoutTransAnimation();
        } else {
            createReferenceView();
            preparePinchItems();
            calculatePosition(this.mPinchItems);
            initializeAnimation();
        }
        setFakeViewLayoutAlpha();
        clearAnimationInfo();
        startAnimation();
    }

    public abstract void setViewHolderMargin(ListViewHolder listViewHolder, int i2);

    public void updateFakeViewSize(View view, int i2) {
        if (i2 == -2) {
            ViewUtils.setHeight(view, this.mLayoutManager.getTitleHeight(this.mGridInfo.to()));
        }
    }
}
