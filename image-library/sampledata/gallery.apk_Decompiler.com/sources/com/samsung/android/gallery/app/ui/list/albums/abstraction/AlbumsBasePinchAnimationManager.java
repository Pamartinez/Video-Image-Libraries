package com.samsung.android.gallery.app.ui.list.albums.abstraction;

import A4.C0372g;
import A4.C0375j;
import A4.O;
import A8.C0545b;
import Ad.j;
import C4.g;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.BackgroundAlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TintAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.animator.WidthAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManagerLegacy;
import com.samsung.android.gallery.widget.listview.pinch.PinchItem;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumsBasePinchAnimationManager extends PinchAnimationManagerLegacy {
    private static final Comparator<PinchItem> POSITION_COMPARATOR = Comparator.comparingInt(new C0545b(1));
    private Drawable mCheckboxDrawable = null;
    protected FrameLayout mFakeViewParent;
    private ListViewHolder mFromRefViewHolder;
    private boolean mIsCheckBoxVisible = false;
    private final ArrayList<PinchItem> mPinchItems = new ArrayList<>();
    private ListViewHolder mReferenceDividerHolder;
    private ListViewHolder mReferenceFirstDividerHolder;
    protected ListViewHolder mReferenceViewHolder;
    protected TransitionType mTransitionType;

    /* renamed from: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType[] r0 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType = r0
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.LIST_TO_GRID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_LIST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager$TransitionType r1 = com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.TransitionType.GRID_TO_GRID     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum TransitionType {
        LIST_TO_GRID,
        GRID_TO_LIST,
        GRID_TO_GRID,
        NO_TRANSITION
    }

    public AlbumsBasePinchAnimationManager(PinchLayoutManager pinchLayoutManager) {
        super(pinchLayoutManager, (GridInfo.ClusterInfo) null);
    }

    private void createFadeInAlbumSyncAnimator(ListViewHolder listViewHolder, ArrayList<Animator> arrayList) {
        View decoView = listViewHolder.getDecoView(26);
        if (decoView != null && decoView.getVisibility() == 0 && !PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            float alpha = decoView.getAlpha();
            decoView.setAlpha(0.0f);
            arrayList.add(new AlphaAnimator(decoView, 0.0f, alpha));
        }
    }

    private void createFadeInNewLabelAnimator(ListViewHolder listViewHolder, ArrayList<Animator> arrayList) {
        View decoView = listViewHolder.getDecoView(1);
        if (decoView != null && decoView.getVisibility() == 0) {
            float alpha = decoView.getAlpha();
            decoView.setAlpha(0.0f);
            arrayList.add(new AlphaAnimator(decoView, 0.0f, alpha));
        }
    }

    private void createFadeInReorderAnimator(ListViewHolder listViewHolder, ArrayList<Animator> arrayList) {
        View decoView = listViewHolder.getDecoView(22);
        if (decoView != null && decoView.getVisibility() == 0) {
            decoView.setAlpha(0.0f);
            arrayList.add(new AlphaAnimator(decoView, 0.0f, 1.0f));
        }
    }

    private void createFadeInTitleAnimator(ListViewHolder listViewHolder, ArrayList<Animator> arrayList) {
        View titleLayout = getTitleLayout(listViewHolder.getTitleView(), true);
        titleLayout.setAlpha(0.0f);
        arrayList.add(new AlphaAnimator(titleLayout, 0.0f, 1.0f));
    }

    private void createItemViewAnimItem() {
        int viewPosition;
        View view;
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = getLastVisibleItemPosition();
        if (findFirstVisibleItemPosition < 0 || lastVisibleItemPosition < 0) {
            String str = this.TAG;
            Log.w(str, "invalid visibleRange=" + findFirstVisibleItemPosition + GlobalPostProcInternalPPInterface.SPLIT_REGEX + lastVisibleItemPosition);
            return;
        }
        while (findFirstVisibleItemPosition <= lastVisibleItemPosition) {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
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
            findFirstVisibleItemPosition++;
        }
    }

    private ListViewHolder createListViewHolder(int i2) {
        ListViewHolder listViewHolder;
        RecyclerView.RecycledViewPool recycledViewPool = getRecyclerView().getRecycledViewPool();
        if (recycledViewPool.getRecycledViewCount(i2) <= 0 || (listViewHolder = (ListViewHolder) recycledViewPool.getRecycledView(i2)) == null) {
            return this.mLayoutManager.createListViewHolder(this.mFakeViewParent, i2);
        }
        return listViewHolder;
    }

    private Context getContext() {
        if (getRecyclerView() != null) {
            return getRecyclerView().getContext();
        }
        return null;
    }

    private ListViewHolder getFakeViewHolder(int i2, int i7) {
        ListViewHolder createListViewHolder = createListViewHolder(i2);
        ViewUtils.setText(createListViewHolder.getTitleView(), "title");
        ViewUtils.setText(createListViewHolder.getCountView(), "0");
        if (this.mIsCheckBoxVisible) {
            ViewUtils.setVisibleOrGone(createListViewHolder.getCheckbox(), true);
        }
        ViewUtils.setVisibleOrGone(createListViewHolder.getDecoView(21), true);
        ViewUtils.setVisibleOrGone(createListViewHolder.getDecoView(26), true);
        ViewUtils.setVisibleOrGone(createListViewHolder.getDecoView(1), true);
        this.mFakeViewParent.addView(createListViewHolder.getRootView());
        this.mLayoutManager.updateViewSize(createListViewHolder.getRootView(), i2, i7);
        if (i2 == 1) {
            createListViewHolder.getRootView().getLayoutParams().width = this.mLayoutManager.getHintWidthSpace(i7);
        }
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

    private int getToDepth() {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 == 1) {
            return 1;
        }
        if (i2 != 3) {
            return 0;
        }
        if (DeviceInfo.isDexMode(getContext())) {
            return this.mGridInfo.isGridGettingLarger() ^ true ? 1 : 0;
        }
        if (this.mGridInfo.isGridGettingLarger()) {
            return 1;
        }
        return 2;
    }

    private TransitionType getTransitionType() {
        if (this.mGridInfo.hasNoTransition()) {
            return TransitionType.NO_TRANSITION;
        }
        if (this.mGridInfo.isToRealRatio()) {
            return TransitionType.GRID_TO_LIST;
        }
        if (this.mGridInfo.isFromRealRatio()) {
            return TransitionType.LIST_TO_GRID;
        }
        return TransitionType.GRID_TO_GRID;
    }

    private void initializeAnimation() {
        if (this.mTransitionType == TransitionType.NO_TRANSITION) {
            clearAnimationInfo();
            return;
        }
        prepareItemViewAnim();
        prepareChildViewAnim();
        clearAnimationInfo();
    }

    private boolean isBlurNeeded(int i2, int i7) {
        if (i2 >= i7 - 2) {
            return true;
        }
        return false;
    }

    private boolean isCheckboxVisible() {
        ListViewHolder listViewHolder = this.mFromRefViewHolder;
        if (listViewHolder == null || !listViewHolder.hasCheckbox() || this.mFromRefViewHolder.getCheckbox().getVisibility() != 0) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareCheckBoxAnimation$5(View view) {
        Drawable buttonDrawable = ((CheckBox) view).getButtonDrawable();
        if (buttonDrawable != null) {
            buttonDrawable.clearColorFilter();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareChildViewAnim$2(PinchItem pinchItem, ListViewHolder listViewHolder) {
        int viewType = listViewHolder.getViewType();
        if (ViewHolderValue.isData(viewType)) {
            prepareDataViewHolder(pinchItem, listViewHolder);
        } else if (ViewHolderValue.isDivider(viewType)) {
            prepareDividerViewHolder(pinchItem, listViewHolder);
        } else {
            prepareOtherViewHolder(pinchItem, listViewHolder);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareFolderBackgroundAnimation$10(View view) {
        resetTranslate(view);
        resetScale(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareFolderChildAnimation$8(View view) {
        resetScale(view);
        resetTranslate(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareFolderChildAnimation$9(View view) {
        resetScale(view);
        resetTranslate(view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareThumbnailAnimation$3(View view) {
        resetTranslate(view);
        resetScale(view);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$prepareTitleAnimation$4(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = -2;
        view.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFakeViewLayoutAlpha$0() {
        this.mFakeViewParent.setAlpha(1.0f);
    }

    private void prepareAlbumExpiryAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(45);
        if (ViewUtils.isVisible(decoView)) {
            addTranslationAnimator(decoView, this.mReferenceViewHolder.getDecoView(45), listViewHolder, this.mReferenceViewHolder, pinchItem);
        }
    }

    private void prepareAlbumSyncAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(26);
        if (!ViewUtils.isVisible(decoView)) {
            return;
        }
        if (this.mTransitionType == TransitionType.GRID_TO_GRID || PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            View decoView2 = listViewHolder.getDecoView(21);
            View decoView3 = this.mReferenceViewHolder.getDecoView(21);
            ViewUtils.setVisibleOrGone(decoView3, ViewUtils.isVisible(decoView2));
            if (ViewUtils.isVisible(decoView2)) {
                ViewUtils.setWidth(decoView3, decoView2.getWidth());
            }
            remeasure(this.mReferenceViewHolder.getRootView());
            addTranslationAnimator(decoView, this.mReferenceViewHolder.getDecoView(26), this.mReferenceViewHolder, pinchItem);
            return;
        }
        float alpha = decoView.getAlpha();
        addAlphaAnimator(decoView, alpha, -10.0f, alpha);
    }

    private void prepareAlbumTypeAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(21);
        if (ViewUtils.isVisible(decoView)) {
            View decoView2 = this.mReferenceViewHolder.getDecoView(21);
            ViewUtils.setVisibility(decoView2, 0);
            addTranslationAnimator(decoView, decoView2, listViewHolder, this.mReferenceViewHolder, pinchItem);
        }
    }

    /* JADX WARNING: type inference failed for: r2v2, types: [androidx.recyclerview.widget.RecyclerView$ViewHolder] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void prepareChildViewAnim() {
        /*
            r5 = this;
            java.util.ArrayList<com.samsung.android.gallery.widget.listview.pinch.PinchItem> r0 = r5.mPinchItems
            java.util.Iterator r0 = r0.iterator()
        L_0x0006:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0039
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
            java.util.Optional r2 = java.util.Optional.ofNullable(r3)
            A4.A r3 = new A4.A
            r4 = 5
            r3.<init>((int) r4, (java.lang.Object) r5, (java.lang.Object) r1)
            r2.ifPresent(r3)
            goto L_0x0006
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.albums.abstraction.AlbumsBasePinchAnimationManager.prepareChildViewAnim():void");
    }

    private void prepareDataViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
        prepareThumbnailAnimation(listViewHolder, pinchItem);
        prepareAlbumDecoAnimation(listViewHolder, pinchItem);
        prepareTitleAnimation(listViewHolder, pinchItem);
        prepareReorderIconAnimation(listViewHolder, pinchItem);
        prepareNewAlbumAnimation(listViewHolder, pinchItem);
        prepareFolderAnimation(listViewHolder, pinchItem);
        prepareCheckBoxAnimation(listViewHolder, pinchItem);
    }

    private void prepareFolderBackgroundAnimation(ListViewHolder listViewHolder, PinchItem pinchItem, int i2) {
        PinchItem pinchItem2 = pinchItem;
        View decoView = listViewHolder.getDecoView(24);
        if (decoView != null && decoView.getVisibility() == 0) {
            View decoView2 = this.mReferenceViewHolder.getDecoView(24);
            RectF[] transitionRect = getTransitionRect(decoView, decoView2, pinchItem2);
            float[] transitionRadii = getTransitionRadii(false);
            float dimension = getRecyclerView().getResources().getDimension(getBorderThicknessResId(true));
            int color = getRecyclerView().getResources().getColor(getBorderColorResId(), (Resources.Theme) null);
            ScaleAnimator scaleAnimator = new ScaleAnimator(decoView, transitionRect[0], transitionRect[1]);
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                dimension = 0.0f;
            }
            scaleAnimator.enableBorder(true, dimension, transitionRadii[0], transitionRadii[1], color);
            if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1]);
            }
            scaleAnimator.setAnimationListener(new g(this, 2));
            addAnimation((PropertyAnimator) scaleAnimator);
            addTranslationAnimator(decoView, decoView2, this.mReferenceViewHolder, pinchItem2);
        }
    }

    private void prepareItemViewAnim() {
        createItemViewAnimItem();
        calculatePosition(this.mPinchItems);
        Iterator<PinchItem> it = this.mPinchItems.iterator();
        while (it.hasNext()) {
            PinchItem next = it.next();
            TranslationAnimator translationAnimator = new TranslationAnimator(next.getView(), next.getFromRect(), next.getToRect());
            translationAnimator.setAnimationListener(new g(this, 3));
            addAnimation((PropertyAnimator) translationAnimator);
        }
    }

    private void prepareNewAlbumAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(1);
        if (!ViewUtils.isVisible(decoView)) {
            return;
        }
        if (this.mTransitionType == TransitionType.GRID_TO_GRID) {
            addTranslationAnimator(decoView, this.mReferenceViewHolder.getDecoView(1), this.mReferenceViewHolder, pinchItem);
            return;
        }
        float alpha = decoView.getAlpha();
        addAlphaAnimator(decoView, alpha, -10.0f, alpha);
    }

    private void prepareReorderIconAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View decoView = listViewHolder.getDecoView(22);
        if (ViewUtils.isVisible(decoView)) {
            addAlphaAnimator(decoView, 0.0f, 0.0f, 1.0f);
        }
    }

    private void removeCachedItemViews() {
        getRecyclerView().setItemViewCacheSize(0);
        this.mLayoutManager.setItemPrefetchEnabled(false);
    }

    private void restore() {
        int childCount = this.mLayoutManager.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null) {
                this.mLayoutManager.updateAlbumTypeMargin(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateAlbumSyncMargin(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateViewBorders(listViewHolder, getRecyclerView().getDepth());
                this.mLayoutManager.updateFolderViewSize(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateEmptyAlbumViewSize(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateVirtualAlbumViewSize(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateTitleContainerMargin(listViewHolder, getRecyclerView().getDepth(), false);
                this.mLayoutManager.updateAlbumBlurInfo(listViewHolder, getRecyclerView().getDepth(), getRecyclerView().getMaxDepth());
            }
        }
    }

    private void saveFromViewInfo() {
        for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= getLastVisibleItemPosition(); findFirstVisibleItemPosition++) {
            if (ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, this.mGridInfo.from()))) {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition != null) {
                    this.mFromRefViewHolder = (ListViewHolder) getChildViewHolder(findViewByPosition);
                }
                if (this.mFromRefViewHolder != null) {
                    break;
                }
            }
        }
        this.mIsCheckBoxVisible = isCheckboxVisible();
    }

    private void setFakeViewLayoutAlpha() {
        this.mFakeViewParent.setAlpha(0.0f);
        this.mFakeViewParent.post(new C0372g(14, this));
    }

    private void startOnLayoutAnimation() {
        int childCount = this.mLayoutManager.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < childCount; i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null && ViewHolderValue.isData(listViewHolder.getViewType())) {
                createFadeInTitleAnimator(listViewHolder, arrayList);
                createFadeInNewLabelAnimator(listViewHolder, arrayList);
                createFadeInAlbumSyncAnimator(listViewHolder, arrayList);
                if (listViewHolder.getViewType() == 1) {
                    createFadeInReorderAnimator(listViewHolder, arrayList);
                }
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(250).playTogether(arrayList);
        animatorSet.setStartDelay(50);
        animatorSet.start();
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

    public void addTranslationAnimator(View view, View view2, ListViewHolder listViewHolder, ListViewHolder listViewHolder2, PinchItem pinchItem) {
        addTranslationAnimator(view, view2, listViewHolder2, pinchItem);
    }

    public void clearAnimationInfo() {
        recyclerViewHolder(this.mReferenceViewHolder);
        recyclerViewHolder(this.mReferenceFirstDividerHolder);
        recyclerViewHolder(this.mReferenceDividerHolder);
        this.mFromRefViewHolder = null;
        this.mReferenceViewHolder = null;
        this.mReferenceDividerHolder = null;
        this.mReferenceFirstDividerHolder = null;
        this.mTransitionType = TransitionType.NO_TRANSITION;
        this.mPinchItems.clear();
    }

    public TintAnimator createCheckboxTintAnimator(CheckBox checkBox, boolean z) {
        int[] iArr;
        Resources resources = checkBox.getResources();
        int[] iArr2 = {resources.getColor(R.color.expansion_view_checkbox_on_tint_color, (Resources.Theme) null), resources.getColor(R.color.photo_cover_checkbox_default_tint_color, (Resources.Theme) null)};
        int[] iArr3 = {resources.getColor(R.color.thumbnail_checkbox_on_color, (Resources.Theme) null), resources.getColor(R.color.thumbnail_checkbox_off_color, (Resources.Theme) null)};
        if (z) {
            iArr = iArr2;
        } else {
            iArr = iArr3;
        }
        if (z) {
            iArr2 = iArr3;
        }
        return new TintAnimator(checkBox, iArr, iArr2);
    }

    public ListViewHolder createFakeViewHolder(int i2, int i7) {
        return getFakeViewHolder(this.mLayoutManager.getHintItemViewType(i2, i7), i7);
    }

    public void createReferenceView() {
        int i2;
        if (this.mGridInfo.isToRealRatio()) {
            i2 = 1;
        } else {
            i2 = 2;
        }
        ListViewHolder fakeViewHolder = getFakeViewHolder(i2, this.mGridInfo.to());
        this.mReferenceViewHolder = fakeViewHolder;
        updateDataReferenceViewHolder(fakeViewHolder, getToDepth());
        this.mReferenceViewHolder.getRootView().setVisibility(4);
        this.mReferenceDividerHolder = createFakeViewHolderInternal(this.mFakeViewParent, -2);
        this.mReferenceFirstDividerHolder = createFakeViewHolderInternal(this.mFakeViewParent, -1);
        View rootParent = getRootParent();
        if (rootParent != null) {
            measureView(this.mFakeViewParent, rootParent.getWidth(), rootParent.getHeight());
        }
    }

    public int getBorderColorResId() {
        return R.color.album_border_color;
    }

    public int getBorderThicknessResId(boolean z) {
        if (z) {
            return R.dimen.folder_image_border_thickness;
        }
        return R.dimen.album_view_border_thickness;
    }

    public int getFromRadius(boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new AssertionError("No transition can't occur");
                } else if (z) {
                    if (this.mGridInfo.isGridGettingLarger()) {
                        return R.dimen.folder_image_radius_grid_max;
                    }
                    return R.dimen.folder_image_radius_grid;
                } else if (this.mGridInfo.isGridGettingLarger()) {
                    return R.dimen.album_view_corner_radius_grid_max;
                } else {
                    return R.dimen.album_view_corner_radius_grid;
                }
            } else if (z) {
                return R.dimen.folder_image_radius_grid;
            } else {
                return R.dimen.album_view_corner_radius_grid;
            }
        } else if (z) {
            return R.dimen.folder_image_radius_list;
        } else {
            return R.dimen.album_view_corner_radius_list;
        }
    }

    public int getHeightMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE);
    }

    public int getHintItemCount(int i2) {
        return this.mLayoutManager.getItemCount();
    }

    public Comparator<PinchItem> getPositionComparator() {
        return POSITION_COMPARATOR;
    }

    public ListViewHolder getRefViewHolder(int i2, boolean z) {
        int hintItemViewType = this.mLayoutManager.getHintItemViewType(i2, this.mGridInfo.from());
        if (ViewHolderValue.isFirstDivider(hintItemViewType)) {
            return this.mReferenceFirstDividerHolder;
        }
        if (ViewHolderValue.isDivider(hintItemViewType)) {
            return this.mReferenceDividerHolder;
        }
        if (z) {
            return this.mReferenceViewHolder;
        }
        return this.mFromRefViewHolder;
    }

    public View getTitleLayout(TextView textView, boolean z) {
        boolean z3;
        if (this.mTransitionType == TransitionType.GRID_TO_LIST) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 ^ z) {
            return (View) textView.getParent().getParent();
        }
        return (View) textView.getParent();
    }

    public int getToRadius(boolean z) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$app$ui$list$albums$abstraction$AlbumsBasePinchAnimationManager$TransitionType[this.mTransitionType.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new AssertionError("No transition can't occur");
                } else if (z) {
                    if (this.mGridInfo.isGridGettingLarger()) {
                        return R.dimen.folder_image_radius_grid;
                    }
                    return R.dimen.folder_image_radius_grid_max;
                } else if (this.mGridInfo.isGridGettingLarger()) {
                    return R.dimen.album_view_corner_radius_grid;
                } else {
                    return R.dimen.album_view_corner_radius_grid_max;
                }
            } else if (z) {
                return R.dimen.folder_image_radius_list;
            } else {
                return R.dimen.album_view_corner_radius_list;
            }
        } else if (z) {
            return R.dimen.folder_image_radius_grid;
        } else {
            return R.dimen.album_view_corner_radius_grid;
        }
    }

    public float[] getTransitionRadii(boolean z) {
        return new float[]{getRecyclerView().getResources().getDimension(getFromRadius(z)), getRecyclerView().getResources().getDimension(getToRadius(z))};
    }

    public RectF[] getTransitionRect(View view, View view2, PinchItem pinchItem) {
        float[] childToXY = getChildToXY(view, this.mReferenceViewHolder.getRootView(), getBaseToXY(view, view2), pinchItem.getToRect());
        RectF rect = getRect(view);
        float f = childToXY[0];
        float f5 = childToXY[1];
        return new RectF[]{rect, new RectF(f, f5, ((float) view2.getWidth()) + f, ((float) view2.getHeight()) + f5)};
    }

    public int getWidthMeasureSpec(int i2) {
        return View.MeasureSpec.makeMeasureSpec(i2, 1073741824);
    }

    public boolean isTextViewEllipsized(TextView textView) {
        int lineCount;
        Layout layout = textView.getLayout();
        if (layout == null || (lineCount = layout.getLineCount()) <= 0 || layout.getEllipsisCount(lineCount - 1) <= 0) {
            return false;
        }
        return true;
    }

    public boolean isWidthAnimationNeeded(TextView textView, int i2) {
        if (isTextViewEllipsized(textView) || i2 < textView.getWidth()) {
            return true;
        }
        return false;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        if (z) {
            this.mTransitionType = getTransitionType();
        }
        this.mFakeViewParent.setVisibility(8);
        super.onAnimationCompleted(z, z3);
    }

    public void onAnimationStarted() {
        this.mFakeViewParent.setVisibility(0);
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            TransitionType transitionType = this.mTransitionType;
            if (transitionType == TransitionType.GRID_TO_LIST || transitionType == TransitionType.LIST_TO_GRID) {
                startOnLayoutAnimation();
            } else {
                removeCachedItemViews();
                getRecyclerView().resetRecyclerViewCache();
            }
            restore();
            this.mTransitionType = TransitionType.NO_TRANSITION;
        }
        super.onLayout();
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        super.onPrepareAnimation(i2, i7, i8);
        FrameLayout frameLayout = (FrameLayout) ((ViewGroup) getRecyclerView().getParent()).findViewById(R.id.fake_view_layout);
        this.mFakeViewParent = frameLayout;
        frameLayout.setVisibility(4);
        TransitionType transitionType = getTransitionType();
        this.mTransitionType = transitionType;
        if (transitionType != TransitionType.NO_TRANSITION) {
            Context context = getContext();
            if (context != null) {
                this.mCheckboxDrawable = context.getDrawable(R.drawable.gallery_btn_uncheck_bg_mtrl);
            }
            saveFromViewInfo();
            createReferenceView();
            initializeAnimation();
            startAnimation();
            setFakeViewLayoutAlpha();
        }
    }

    public void prepareAlbumDecoAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        prepareAlbumTypeAnimation(listViewHolder, pinchItem);
        prepareAlbumSyncAnimation(listViewHolder, pinchItem);
        prepareAlbumExpiryAnimation(listViewHolder, pinchItem);
    }

    public void prepareCheckBoxAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        boolean z;
        float f;
        CheckBox checkbox = listViewHolder.getCheckbox();
        if (ViewUtils.isVisible(checkbox)) {
            float f5 = 1.0f;
            checkbox.setTranslationZ(1.0f);
            addTranslationAnimator((View) checkbox, (View) this.mReferenceViewHolder.getCheckbox(), this.mReferenceViewHolder, pinchItem);
            if (withList()) {
                if (this.mTransitionType == TransitionType.LIST_TO_GRID) {
                    z = true;
                } else {
                    z = false;
                }
                if (!Features.isEnabled(Features.IS_THEME_INSTALLED)) {
                    TintAnimator createCheckboxTintAnimator = createCheckboxTintAnimator(checkbox, z);
                    createCheckboxTintAnimator.setAnimationListener(new j(5));
                    addAnimation((PropertyAnimator) createCheckboxTintAnimator);
                    Drawable background = checkbox.getBackground();
                    Drawable drawable = this.mCheckboxDrawable;
                    if (z) {
                        f = 0.0f;
                    } else {
                        f = 1.0f;
                    }
                    if (!z) {
                        f5 = 0.0f;
                    }
                    BackgroundAlphaAnimator backgroundAlphaAnimator = new BackgroundAlphaAnimator(checkbox, drawable, f, f5);
                    backgroundAlphaAnimator.setAnimationListener(new O(15, background));
                    addAnimation((PropertyAnimator) backgroundAlphaAnimator);
                }
            }
        }
    }

    public void prepareFolderAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        int i2;
        PinchItem pinchItem2;
        ListViewHolder listViewHolder2;
        AlbumsBasePinchAnimationManager albumsBasePinchAnimationManager;
        View decoView = listViewHolder.getDecoView(23);
        if (!ViewUtils.isVisible(decoView)) {
            return;
        }
        if (this.mReferenceViewHolder.getDecoView(23) == null) {
            addAlphaAnimator(decoView, 1.0f, -10.0f, 1.0f);
            return;
        }
        ListViewHolder[] folderImageHolders = listViewHolder.getFolderImageHolders();
        ListViewHolder[] folderImageHolders2 = this.mReferenceViewHolder.getFolderImageHolders();
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            i2 = (int) Arrays.stream(listViewHolder.getFolderImageHolders()).filter(new C0375j(5)).count();
        } else {
            i2 = 4;
        }
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            this.mLayoutManager.updateFolderChildViewSize(folderImageHolders2, getToDepth(), i2);
            remeasure(this.mReferenceViewHolder.getRootView());
        }
        if (!(folderImageHolders == null || folderImageHolders2 == null)) {
            int i7 = 0;
            while (i7 < folderImageHolders.length) {
                ListViewHolder listViewHolder3 = folderImageHolders[i7];
                if (listViewHolder3 == null || listViewHolder3.getRootView().getVisibility() != 0 || folderImageHolders[i7].getImage() == null) {
                    albumsBasePinchAnimationManager = this;
                    listViewHolder2 = listViewHolder;
                    pinchItem2 = pinchItem;
                } else if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
                    albumsBasePinchAnimationManager = this;
                    listViewHolder2 = listViewHolder;
                    pinchItem2 = pinchItem;
                    albumsBasePinchAnimationManager.prepareFolderChildAnimation(folderImageHolders[i7], folderImageHolders2[i7], listViewHolder2, pinchItem2, this.isBlurNeeded(i7, i2), FolderImageBindHelper.getCornerEnabled(i7, i2));
                } else {
                    albumsBasePinchAnimationManager = this;
                    listViewHolder2 = listViewHolder;
                    pinchItem2 = pinchItem;
                    albumsBasePinchAnimationManager.prepareFolderChildAnimation(folderImageHolders[i7], folderImageHolders2[i7], pinchItem2);
                }
                i7++;
                this = albumsBasePinchAnimationManager;
                listViewHolder = listViewHolder2;
                pinchItem = pinchItem2;
            }
        }
        this.prepareFolderBackgroundAnimation(listViewHolder, pinchItem, i2);
    }

    public void prepareFolderChildAnimation(ListViewHolder listViewHolder, ListViewHolder listViewHolder2, ListViewHolder listViewHolder3, PinchItem pinchItem, boolean z, boolean[] zArr) {
        View scalableView = listViewHolder.getScalableView();
        View scalableView2 = listViewHolder2.getScalableView();
        RectF[] transitionRect = getTransitionRect(scalableView, scalableView2, pinchItem);
        float[] transitionRadii = getTransitionRadii(false);
        ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, transitionRect[0], transitionRect[1]);
        scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1], zArr);
        scaleAnimator.setAnimationListener(new g(this, 1));
        addAnimation((PropertyAnimator) scaleAnimator);
        addTranslationAnimator(scalableView, scalableView2, listViewHolder2, transitionRect[1]);
    }

    public void prepareThumbnailAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        View scalableView = listViewHolder.getScalableView();
        if (scalableView != null && scalableView.getVisibility() == 0) {
            View scalableView2 = this.mReferenceViewHolder.getScalableView();
            RectF[] transitionRect = getTransitionRect(scalableView, scalableView2, pinchItem);
            float[] transitionRadii = getTransitionRadii(false);
            float dimension = getRecyclerView().getResources().getDimension(getBorderThicknessResId(false));
            int color = getRecyclerView().getResources().getColor(getBorderColorResId(), (Resources.Theme) null);
            ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, transitionRect[0], transitionRect[1]);
            scaleAnimator.enableBorder(true, dimension, transitionRadii[0], transitionRadii[1], color);
            scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1]);
            scaleAnimator.setAnimationListener(new g(this, 5));
            addAnimation((PropertyAnimator) scaleAnimator);
            addTranslationAnimator(scalableView, scalableView2, listViewHolder, this.mReferenceViewHolder, pinchItem);
        }
    }

    public void prepareTitleAnimation(ListViewHolder listViewHolder, PinchItem pinchItem) {
        TextView titleView = listViewHolder.getTitleView();
        TextView countView = listViewHolder.getCountView();
        if (titleView != null && countView != null) {
            int i2 = 0;
            View titleLayout = getTitleLayout(titleView, false);
            View titleLayout2 = getTitleLayout(this.mReferenceViewHolder.getTitleView(), false);
            TextView countView2 = this.mReferenceViewHolder.getCountView();
            ViewUtils.setVisibility(countView2, countView.getVisibility());
            if (withList()) {
                addTranslationAnimator(titleLayout, titleLayout2, this.mReferenceViewHolder, pinchItem);
                addAlphaAnimator(titleLayout, 1.0f, -1.0f, 1.0f);
                return;
            }
            View decoView = listViewHolder.getDecoView(1);
            TextView titleView2 = this.mReferenceViewHolder.getTitleView();
            titleView2.setText(titleView.getText());
            countView2.setText(countView.getText());
            if (decoView != null) {
                if (ViewUtils.isVisible(decoView)) {
                    i2 = getRecyclerView().getResources().getDimensionPixelSize(R.dimen.new_badge_dot_size);
                }
                ViewMarginUtils.setEndMargin(titleView2, i2);
                ViewUtils.setVisibility(this.mReferenceViewHolder.getDecoView(1), decoView.getVisibility());
            }
            this.mLayoutManager.updateTitleContainerMargin(this.mReferenceViewHolder, getToDepth(), true ^ ViewUtils.isVisible(countView));
            remeasure(this.mReferenceViewHolder.getRootView());
            addTranslationAnimator((View) titleView, (View) titleView2, this.mReferenceViewHolder, pinchItem);
            addTranslationAnimator((View) countView, (View) countView2, this.mReferenceViewHolder, pinchItem);
            if (titleView.getWidth() > titleLayout2.getWidth() || isTextViewEllipsized(titleView)) {
                WidthAnimator widthAnimator = new WidthAnimator(titleView, titleView.getWidth(), titleLayout2.getWidth(), new g(this, 4));
                widthAnimator.setAnimationListener(new j(6));
                addAnimation((PropertyAnimator) widthAnimator);
            }
        }
    }

    public void recyclerViewHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null) {
            View rootView = listViewHolder.getRootView();
            ViewUtils.removeSelf(rootView);
            rootView.setVisibility(0);
            listViewHolder.recycle();
            putRecycledViewPool(listViewHolder);
        }
    }

    public void remeasure(View view) {
        measureView(view, view.getWidth(), view.getHeight());
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

    public void updateDataReferenceViewHolder(ListViewHolder listViewHolder, int i2) {
        listViewHolder.inflateFolderView(true);
        this.mLayoutManager.updateAlbumTypeMargin(listViewHolder, i2, true);
        this.mLayoutManager.updateAlbumSyncMargin(listViewHolder, i2, true);
        this.mLayoutManager.updateFolderViewSize(listViewHolder, i2, true);
        this.mLayoutManager.updateEmptyAlbumViewSize(listViewHolder, i2, true);
        this.mLayoutManager.updateVirtualAlbumViewSize(listViewHolder, i2, true);
    }

    public boolean withList() {
        if (this.mTransitionType != TransitionType.GRID_TO_GRID) {
            return true;
        }
        return false;
    }

    private void prepareFolderChildAnimation(ListViewHolder listViewHolder, ListViewHolder listViewHolder2, PinchItem pinchItem) {
        View scalableView = listViewHolder.getScalableView();
        View scalableView2 = listViewHolder2.getScalableView();
        RectF[] transitionRect = getTransitionRect(scalableView, scalableView2, pinchItem);
        float[] transitionRadii = getTransitionRadii(true);
        float dimension = getRecyclerView().getResources().getDimension(getBorderThicknessResId(listViewHolder.isFolder()));
        int color = getRecyclerView().getResources().getColor(getBorderColorResId(), (Resources.Theme) null);
        ScaleAnimator scaleAnimator = new ScaleAnimator(scalableView, transitionRect[0], transitionRect[1]);
        scaleAnimator.enableBorder(true, dimension, transitionRadii[0], transitionRadii[1], color);
        scaleAnimator.setOutlineProvider(transitionRadii[0], transitionRadii[1]);
        scaleAnimator.setAnimationListener(new g(this, 0));
        addAnimation((PropertyAnimator) scaleAnimator);
        addTranslationAnimator(scalableView, scalableView2, listViewHolder2, transitionRect[1]);
    }

    public void prepareDividerViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
    }

    public void prepareOtherViewHolder(PinchItem pinchItem, ListViewHolder listViewHolder) {
    }
}
