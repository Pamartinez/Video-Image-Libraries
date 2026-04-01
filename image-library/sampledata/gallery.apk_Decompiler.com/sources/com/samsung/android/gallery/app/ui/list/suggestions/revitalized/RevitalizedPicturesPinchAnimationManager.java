package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import D6.a;
import M9.o;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManagerLegacy;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesPinchAnimationManager extends PinchAnimationManagerLegacy {
    private static final Comparator<PinchItem> POSITION_COMPARATOR = new a(5);
    private FrameLayout mFakeViewParent;
    private ListViewHolder mFromRefViewHolder;
    private boolean mIsAnimating = false;
    private boolean mIsCheckBoxVisible = false;
    private final ArrayList<PinchItem> mPinchItems = new ArrayList<>();
    private ListViewHolder mReferenceViewHolder;

    public RevitalizedPicturesPinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager, (GridInfo.ClusterInfo) null);
    }

    private void clearAnimationInfo() {
        ListViewHolder listViewHolder = this.mReferenceViewHolder;
        if (listViewHolder != null) {
            View rootView = listViewHolder.getRootView();
            ViewUtils.removeSelf(rootView);
            rootView.setVisibility(0);
            this.mReferenceViewHolder.recycle();
            putRecycledViewPool(this.mReferenceViewHolder);
        }
        this.mReferenceViewHolder = null;
        this.mFromRefViewHolder = null;
        this.mPinchItems.clear();
    }

    private void createItemViewAnimItem() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(childAt);
            if (listViewHolder != null) {
                int viewPosition = listViewHolder.getViewPosition();
                ListViewHolder refViewHolder = getRefViewHolder(viewPosition, true);
                PinchItem pinchItem = new PinchItem(childAt, viewPosition, this.mGridInfo);
                pinchItem.setItemViewType(this.mLayoutManager.getItemViewType(childAt));
                pinchItem.calculateTo(this.mLayoutManager, this.mPositionCache, refViewHolder.getRootView());
                this.mPinchItems.add(pinchItem);
            }
        }
    }

    private ListViewHolder createListViewHolder(int i2) {
        ListViewHolder listViewHolder;
        RecyclerView.RecycledViewPool recycledViewPool = getRecyclerView().getRecycledViewPool();
        if (recycledViewPool == null || recycledViewPool.getRecycledViewCount(i2) <= 0 || (listViewHolder = (ListViewHolder) recycledViewPool.getRecycledView(i2)) == null) {
            return this.mLayoutManager.createListViewHolder(this.mFakeViewParent, i2);
        }
        return listViewHolder;
    }

    private void createReferenceView() {
        int i2;
        if (this.mLayoutManager.isDefaultDepth(this.mGridInfo.to())) {
            i2 = 6;
        } else {
            i2 = 5;
        }
        ListViewHolder fakeViewHolder = getFakeViewHolder(i2, this.mGridInfo.to());
        this.mReferenceViewHolder = fakeViewHolder;
        fakeViewHolder.getRootView().setVisibility(4);
        View rootParent = getRootParent();
        if (rootParent != null) {
            measureView(this.mFakeViewParent, rootParent.getWidth(), rootParent.getHeight());
        }
    }

    private ListViewHolder getFakeViewHolder(int i2, int i7) {
        ListViewHolder createListViewHolder = createListViewHolder(i2);
        TextView titleView = createListViewHolder.getTitleView();
        TextView countView = createListViewHolder.getCountView();
        titleView.setText("");
        countView.setText("");
        if (this.mIsCheckBoxVisible) {
            createListViewHolder.getCheckbox().setVisibility(0);
        }
        this.mFakeViewParent.addView(createListViewHolder.getRootView());
        this.mLayoutManager.updateViewSize(createListViewHolder.getRootView(), i2, i7);
        return createListViewHolder;
    }

    private View getRootParent() {
        View view = (View) getRecyclerView();
        while (view != null && view.getId() != R.id.content) {
            view = (View) view.getParent();
        }
        if (view != null || getRecyclerView() == null) {
            return view;
        }
        return (View) getRecyclerView().getParent();
    }

    private float[] getTransitionRadii() {
        float dimension = getRecyclerView().getResources().getDimension(R.dimen.revitalized_card_round_radius);
        return new float[]{dimension, dimension};
    }

    private RectF[] getTransitionRect(View view, View view2, PinchItem pinchItem) {
        float[] childToXY = getChildToXY(view, this.mReferenceViewHolder.getRootView(), getBaseToXY(view, view2), pinchItem.getToRect());
        RectF rect = getRect(view);
        float f = childToXY[0];
        float f5 = childToXY[1];
        return new RectF[]{rect, new RectF(f, f5, ((float) view2.getWidth()) + f, ((float) view2.getHeight()) + f5)};
    }

    private void initializeAnimation() {
        if (this.mGridInfo.hasNoTransition()) {
            clearAnimationInfo();
            return;
        }
        prepareItemViewAnim();
        prepareChildViewAnim();
        clearAnimationInfo();
    }

    private boolean isCheckboxVisible() {
        ListViewHolder listViewHolder = this.mFromRefViewHolder;
        if (listViewHolder == null || !listViewHolder.hasCheckbox() || this.mFromRefViewHolder.getCheckbox().getVisibility() != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareThumbnailAnimation$2(View view) {
        resetTranslate(view);
        resetScale(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFakeViewLayoutAlpha$0() {
        this.mFakeViewParent.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$3(PinchItem pinchItem, PinchItem pinchItem2) {
        if (pinchItem.getFromViewPosition() > pinchItem2.getFromViewPosition()) {
            return 1;
        }
        return -1;
    }

    private void prepareCheckBoxAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        CheckBox checkbox = listViewHolder.getCheckbox();
        if (checkbox.getVisibility() == 0) {
            checkbox.setTranslationZ(1.0f);
            addTranslationAnimator((View) checkbox, (View) this.mReferenceViewHolder.getCheckbox(), this.mReferenceViewHolder, pinchItem);
        }
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void prepareChildViewAnim() {
        /*
            r5 = this;
            java.util.ArrayList<com.samsung.android.gallery.widget.listview.pinch.PinchItem> r0 = r5.mPinchItems
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0037
            java.lang.Object r1 = r0.next()
            com.samsung.android.gallery.widget.listview.pinch.PinchItem r1 = (com.samsung.android.gallery.widget.listview.pinch.PinchItem) r1
            android.view.View r2 = r1.getView()
            int r3 = r1.getFromViewPosition()
            android.util.SparseArray<com.samsung.android.gallery.widget.listviewholder.ListViewHolder> r4 = r5.mFakeViewHolders
            java.lang.Object r3 = r4.get(r3)
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r3 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r3
            if (r3 != 0) goto L_0x002b
            androidx.recyclerview.widget.RecyclerView$ViewHolder r2 = r5.getChildViewHolder(r2)
            r3 = r2
            com.samsung.android.gallery.widget.listviewholder.ListViewHolder r3 = (com.samsung.android.gallery.widget.listviewholder.ListViewHolder) r3
        L_0x002b:
            if (r3 == 0) goto L_0x0006
            r5.prepareThumbnailAnimation(r3, r1)
            r5.prepareTitleAnimation(r3)
            r5.prepareCheckBoxAnimation(r3, r1)
            goto L_0x0006
        L_0x0037:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.suggestions.revitalized.RevitalizedPicturesPinchAnimationManager.prepareChildViewAnim():void");
    }

    private void prepareItemViewAnim() {
        createItemViewAnimItem();
        calculatePosition(this.mPinchItems);
        Iterator<PinchItem> it = this.mPinchItems.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            TranslationAnimator translationAnimator = new TranslationAnimator(next.getView(), next.getFromRect(), next.getToRect());
            translationAnimator.setAnimationListener(new Q6.a(this, 0));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    private void prepareThumbnailAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View scalableView = listViewHolder.getScalableView();
        if (scalableView != null && scalableView.getVisibility() == 0) {
            ImageView image = this.mReferenceViewHolder.getImage();
            RectF[] transitionRect = getTransitionRect(scalableView, image, pinchItem);
            float[] transitionRadii = getTransitionRadii();
            ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, transitionRect[0], transitionRect[1]);
            scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1]);
            scaleAnimator.setAnimationListener(new Q6.a(this, 1));
            addAnimation((PropertyAnimator) scaleAnimator);
            addTranslationAnimator(scalableView, (View) image, this.mReferenceViewHolder, pinchItem);
        }
    }

    private void prepareTitleAnimation(ListViewHolder listViewHolder) {
        addAlphaAnimator((View) listViewHolder.getTitleView().getParent(), 0.0f, 0.0f, 1.0f);
    }

    private void removeCachedItemViews() {
        getRecyclerView().setItemViewCacheSize(0);
        this.mLayoutManager.setItemPrefetchEnabled(false);
    }

    private void restoreViewBorders() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null) {
                this.mLayoutManager.updateViewBorders(listViewHolder, -1);
            }
        }
    }

    private void saveFromViewInfo() {
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount() && this.mFromRefViewHolder == null; i2++) {
            View childAt = this.mLayoutManager.getChildAt(i2);
            if (childAt != null) {
                this.mFromRefViewHolder = (ListViewHolder) getChildViewHolder(childAt);
            }
        }
        this.mIsCheckBoxVisible = isCheckboxVisible();
    }

    private void setFakeViewLayoutAlpha() {
        this.mFakeViewParent.setAlpha(0.0f);
        this.mFakeViewParent.post(new o(25, this));
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

    public ListViewHolder createFakeViewHolder(int i2, int i7) {
        return getFakeViewHolder(this.mLayoutManager.getHintItemViewType(i2, i7), i7);
    }

    public int getHeightMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    public int getHintItemCount(int i2) {
        return this.mLayoutManager.getItemCount();
    }

    public Comparator<PinchItem> getPositionComparator() {
        return POSITION_COMPARATOR;
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        if (z) {
            return this.mReferenceViewHolder;
        }
        return this.mFromRefViewHolder;
    }

    public int getWidthMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        this.mFakeViewParent.setVisibility(8);
        super.onAnimationCompleted(z, z3);
        this.mIsAnimating = false;
    }

    public void onAnimationStarted() {
        this.mFakeViewParent.setVisibility(0);
        this.mIsAnimating = true;
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            removeCachedItemViews();
            getRecyclerView().resetRecyclerViewCache();
            restoreViewBorders();
        }
        super.onLayout();
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        super.onPrepareAnimation(i2, i7, i8);
        FrameLayout frameLayout = (FrameLayout) ((ViewGroup) getRecyclerView().getParent()).findViewById(R.id.fake_view_layout);
        this.mFakeViewParent = frameLayout;
        frameLayout.setVisibility(4);
        if (!this.mGridInfo.hasNoTransition()) {
            saveFromViewInfo();
            createReferenceView();
            initializeAnimation();
            startAnimation();
            setFakeViewLayoutAlpha();
        }
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
}
