package com.samsung.android.gallery.app.ui.list.pictures;

import A.a;
import D7.l;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import c4.C0438h;
import com.samsung.android.gallery.app.ui.list.pictures.adapter.GridMarginHelper;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.animator.TranslationAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.CloneView;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import e5.C0451a;
import e5.f;
import e5.g;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PicturesPinchAnimationManagerV2 extends PinchAnimationManager {
    private final ConcurrentHashMap<Integer, Bitmap> mBitmapCache = new ConcurrentHashMap<>();
    protected int mFadeInDuration = 150;
    protected PinchFakeLayoutManager mFakeLayoutManager;
    protected ViewGroup mFakeViewParent;
    protected int mFromPadding;
    private final ArrayList<View> mGoneList = new ArrayList<>();
    protected CloneView mHeaderCloneView;
    protected int mHeaderDiff = 0;
    protected View mHeaderView;
    protected boolean mIsAnimating;
    private boolean mIsHeaderViewAddedAtFirst;
    private boolean mIsThumbKindChanged;
    private ArrayList<ListViewHolder> mRecyclerViewDividers;
    private float mShiftedOffset;
    private final boolean mSupportPivotOnFingerPos;
    protected int mToPadding;

    public PicturesPinchAnimationManagerV2(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z) {
        super(pinchLayoutManager, clusterInfo);
        this.mSupportPivotOnFingerPos = z;
    }

    private void addDividerAlphaAnimator(ListViewHolder listViewHolder, boolean z) {
        float f;
        View rootView = listViewHolder.getRootView();
        float f5 = 1.0f;
        if (z) {
            f = 0.0f;
        } else {
            f = 1.0f;
        }
        if (!z) {
            f5 = 0.0f;
        }
        AlphaAnimator alphaAnimator = new AlphaAnimator(rootView, f, f5);
        if (z) {
            alphaAnimator.setStartRelative(0.9f);
        } else {
            alphaAnimator.setDurationRelative(0.1f);
        }
        alphaAnimator.setAnimationListener(new f(this, 2));
        addAnimation((PropertyAnimator) alphaAnimator);
    }

    private void addDividerAnimator(ListViewHolder listViewHolder, int i2, int i7, boolean z) {
        if (listViewHolder.getRootView() != null) {
            RectF rect = getRect(this.mFakeLayoutManager.getFakeDividerRect(), i7, 0, this.mShiftedOffset);
            if (rect == null) {
                this.mGoneList.add(listViewHolder.getRootView());
            } else if (z || moveThroughThumbnail(i2, i7)) {
                addDividerAlphaAnimator(listViewHolder, true);
                addAnimation(new TranslationAnimator(listViewHolder.getRootView(), rect, rect).setAnimationListener(new f(this, 0)));
            } else {
                ListViewHolder findMatchedDivider = findMatchedDivider(this.mRecyclerViewDividers, listViewHolder);
                if (findMatchedDivider != null) {
                    addMatchedDividerAnimator(listViewHolder, findMatchedDivider, rect);
                    return;
                }
                addDividerAlphaAnimator(listViewHolder, true);
                addAnimation(new TranslationAnimator(listViewHolder.getRootView(), rect, rect).setAnimationListener(new f(this, 0)));
            }
        }
    }

    private void addFakeView(int i2, float f, PinchFakeLayoutManager.CalculateRange calculateRange) {
        if (this.mFakeLayoutManager == null) {
            this.mFakeLayoutManager = createFakeLayoutManager(this.mFakeViewParent);
        }
        this.mScrollPosition = i2;
        this.mScrollOffset = (int) this.mFakeLayoutManager.addFakeView(new PinchFakeLayoutManager.CalculateInfo(this.mGridInfo.to(), i2, f), calculateRange);
        addHeaderViewOffset();
        this.mShiftedOffset = ((float) this.mScrollOffset) - f;
    }

    private void addHeaderParentAlphaAnimator(View view) {
        AlphaAnimator alphaAnimator = new AlphaAnimator(view, 0.0f, 0.0f);
        alphaAnimator.setAnimationListener(new f(this, 2));
        addAnimation((PropertyAnimator) alphaAnimator);
    }

    private void adjustItemGap(RectF[] rectFArr, boolean z, boolean z3) {
        if (!z3 && !this.mGridInfo.withYear()) {
            if (this.mToPadding != 0 || this.mFromPadding != 0) {
                if (z) {
                    float width = rectFArr[0].width() - ((float) (this.mFromPadding * 2));
                    float width2 = rectFArr[1].width();
                    int i2 = this.mToPadding;
                    float f = width / (width2 - ((float) (i2 * 2)));
                    float f5 = (float) (this.mFromPadding - i2);
                    RectF rectF = rectFArr[0];
                    float f8 = rectF.left;
                    rectFArr[0] = new RectF(f8 + f5, rectF.top + f5, (rectFArr[1].width() * f) + f8 + f5, (rectFArr[1].height() * f) + rectFArr[0].top + f5);
                    return;
                }
                float width3 = rectFArr[1].width() - ((float) (this.mToPadding * 2));
                float width4 = rectFArr[0].width();
                int i7 = this.mFromPadding;
                float f10 = width3 / (width4 - ((float) (i7 * 2)));
                float f11 = (float) (this.mToPadding - i7);
                RectF rectF2 = rectFArr[1];
                float f12 = rectF2.left;
                rectFArr[1] = new RectF(f12 + f11, rectF2.top + f11, (rectFArr[0].width() * f10) + f12 + f11, (rectFArr[0].height() * f10) + rectFArr[1].top + f11);
            }
        }
    }

    private void clearFakeLayoutManager() {
        PinchFakeLayoutManager pinchFakeLayoutManager = this.mFakeLayoutManager;
        if (pinchFakeLayoutManager != null) {
            pinchFakeLayoutManager.clear();
            this.mFakeLayoutManager = null;
        }
    }

    private int getBottomRows(int i2) {
        int i7 = 0;
        for (int i8 = i2 + 1; i8 <= getLastVisibleItemPosition(); i8++) {
            if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, i8, this.mGridInfo.from()) == 0) {
                i7++;
            }
        }
        return i7;
    }

    private PinchFakeLayoutManager.CalculateRange getCalculateRange(float[] fArr) {
        if (this.mGridInfo.withYear()) {
            return null;
        }
        return new PinchFakeLayoutManager.CalculateRange(getTopRows((int) fArr[0]), getBottomRows((int) fArr[0]));
    }

    private ArrayList<ListViewHolder> getDividerHolders() {
        ArrayList<ListViewHolder> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount(); i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null && listViewHolder.getViewPosition() >= this.mLayoutManager.findFirstVisibleItemPosition() && ViewHolderValue.isDivider(listViewHolder.getViewType())) {
                arrayList.add(listViewHolder);
            }
        }
        return arrayList;
    }

    private RectF getHorizontalExpandedRect(ArrayList<RectF> arrayList, int i2, float f) {
        RectF rectF = (RectF) C0212a.i(arrayList, 1);
        int size = (i2 - arrayList.size()) + 1;
        if (this.mIsRtl) {
            float f5 = (float) size;
            return new RectF(rectF.left - (rectF.width() * f5), rectF.top + f, rectF.right - (rectF.width() * f5), rectF.bottom + f);
        }
        float f8 = (float) size;
        return new RectF((rectF.width() * f8) + rectF.left, rectF.top + f, (rectF.width() * f8) + rectF.right, rectF.bottom + f);
    }

    private int[] getHorizontalFocusedRatio(int i2) {
        int i7 = 0;
        int i8 = 1;
        if (isNotSupportPivotOnFingerPos()) {
            return new int[]{0, 1};
        }
        while (true) {
            int i10 = i2 - i7;
            if (i10 >= 0 && this.mPositionCache.getHintStartSpan(this.mLayoutManager, i10, this.mGridInfo.from()) != 0) {
                i7++;
            }
        }
        while (true) {
            int i11 = i2 + i8;
            if (i11 < this.mLayoutManager.getItemCount() && this.mPositionCache.getHintStartSpan(this.mLayoutManager, i11, this.mGridInfo.from()) != 0) {
                i8++;
            }
        }
        return getUnitRate(i7, i8 - 1);
    }

    private float getPivotX(float f) {
        if (this.mSupportPivotOnFingerPos) {
            return f;
        }
        return Math.max(((float) this.mLayoutManager.getHintHorizontalPadding(this.mGridInfo.to())) / 2.0f, ((float) this.mLayoutManager.getHintHorizontalPadding(this.mGridInfo.from())) / 2.0f);
    }

    private ScaleAnimator getScaleAnimator(View view, float f, float f5, float f8, float f10) {
        ScaleAnimator pivotY = new ScaleAnimator(view, f8, f10).pivotX(f5).pivotY(f);
        pivotY.setAnimationListener(new f(this, 1));
        return pivotY;
    }

    private float getScrollOffset(float[] fArr, int i2, int i7) {
        if (this.mGridInfo.toYear()) {
            return Math.max(fArr[1], 0.0f) + ((float) this.mLayoutManager.getHintExtraOffset(i2, i7, this.mGridInfo.to()));
        }
        return fArr[1];
    }

    private RectF getShiftedRectF(RectF rectF, float f) {
        if (rectF == null || f == 0.0f) {
            return rectF;
        }
        return new RectF(rectF.left, rectF.top + f, rectF.right, rectF.bottom + f);
    }

    private int[] getStartRows(int i2) {
        int i7;
        int[] iArr = new int[2];
        if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, i2, this.mGridInfo.from()) != 0) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        iArr[0] = i7;
        iArr[1] = 0;
        for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition < i2; findFirstVisibleItemPosition++) {
            if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, findFirstVisibleItemPosition, this.mGridInfo.from()) == 0) {
                char c5 = !ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, this.mGridInfo.from()));
                iArr[c5] = iArr[c5] - 1;
            }
        }
        return iArr;
    }

    private int getTopRows(int i2) {
        int i7;
        if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, i2, this.mGridInfo.from()) != 0) {
            i7 = -1;
        } else {
            i7 = 0;
        }
        for (int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition < i2; findFirstVisibleItemPosition++) {
            if (findFirstVisibleItemPosition < 0) {
                a.k(findFirstVisibleItemPosition, "getTopRows findFirstVisibleItemPosition returns ", this.TAG);
            } else if (this.mPositionCache.getHintStartSpan(this.mLayoutManager, findFirstVisibleItemPosition, this.mGridInfo.from()) == 0) {
                i7++;
            }
        }
        return i7;
    }

    private int[] getUnitRate(int i2, int i7) {
        ArrayList arrayList = new ArrayList();
        int i8 = i2;
        int i10 = 2;
        while (((double) i10) <= Math.sqrt((double) i2)) {
            if (i8 % i10 == 0) {
                arrayList.add(Integer.valueOf(i10));
                i8 /= i10;
                i10 = 2;
            }
            i10++;
        }
        if (i8 != 0) {
            arrayList.add(Integer.valueOf(i8));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            if (i7 % intValue == 0) {
                i2 /= intValue;
                i7 /= intValue;
            }
        }
        if (i2 == 0 && i7 == 0) {
            return new int[]{0, 1};
        }
        return new int[]{i2, i7};
    }

    private RectF getVerticalExpandedRect(HashMap<Integer, ArrayList<RectF>> hashMap, int i2, int i7, float f) {
        Object min;
        RectF rectF;
        if (hashMap.isEmpty()) {
            return null;
        }
        Set<Integer> keySet = hashMap.keySet();
        if (i2 > 0) {
            min = Collections.max(keySet);
        } else {
            min = Collections.min(keySet);
        }
        int intValue = ((Integer) min).intValue();
        ArrayList arrayList = hashMap.get(Integer.valueOf(intValue));
        if (arrayList == null) {
            return null;
        }
        if (i7 >= arrayList.size()) {
            rectF = getHorizontalExpandedRect(arrayList, i7, 0.0f);
        } else {
            rectF = (RectF) arrayList.get(i7);
        }
        float height = (rectF.height() * ((float) (i2 - intValue))) + f;
        return new RectF(rectF.left, rectF.top + height, rectF.right, rectF.bottom + height);
    }

    private void hideDividers() {
        Iterator<ListViewHolder> it = this.mRecyclerViewDividers.iterator();
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            if (next.getRootView() != null) {
                addDividerAlphaAnimator(next, false);
            }
        }
    }

    private void init() {
        initThumbKindChanged();
        initPadding();
        initViews();
    }

    private void initMonthForViewingClickedAnimation() {
        this.mGridInfo.updateMonthForViewingToMonth();
        initViews();
    }

    private void initThumbKindChanged() {
        this.mIsThumbKindChanged = this.mGridInfo.withMonth();
    }

    private void initViews() {
        initFakeViewLayout();
        initHeaderView();
    }

    private void initYearClickedAnimation() {
        initYearValues();
        initViews();
    }

    private void initYearValues() {
        this.mGridInfo.updateYearToMonth();
        initThumbKindChanged();
        initPadding();
    }

    private void initializeAnimation(float[] fArr, float f) {
        initializeListViewAnimation();
        if (this.mGridInfo.withYear()) {
            initializeYearAnimation(fArr[2], fArr[3]);
        } else {
            initializeTransAnimation((int) fArr[0], f);
        }
        addHeaderView();
    }

    private void initializeListViewAnimation() {
        addAnimation(new AlphaAnimator((View) getRecyclerView(), 1.0f, 0.0f).setAnimationListener(new f(this, 2)));
        addAnimation((PropertyAnimator) new AlphaAnimator((View) this.mFakeViewParent, 0.0f, 1.0f));
    }

    private void initializeRecyclerViewDividerAlphaAnimation() {
        for (int i2 = 0; i2 < this.mLayoutManager.getChildCount(); i2++) {
            ListViewHolder listViewHolder = (ListViewHolder) getChildViewHolder(this.mLayoutManager.getChildAt(i2));
            if (listViewHolder != null && !ViewHolderValue.isData(listViewHolder.getViewType())) {
                addToGoneList(listViewHolder.getRootView());
            }
        }
    }

    private void initializeYearAnimation(float f, float f5) {
        initializeRecyclerViewDividerAlphaAnimation();
        initializeFakeViewLayoutTransAnimation(0, true);
        initializeYearScaleAnimation(f, f5);
    }

    private void initializeYearScaleAnimation(float f, float f5) {
        float pivotX = getPivotX(f);
        float f8 = f5;
        addAnimation((PropertyAnimator) getScaleAnimator((View) getRecyclerView(), f8, pivotX, 1.0f, ((float) this.mGridInfo.from()) / ((float) this.mGridInfo.getRealToGrid())));
        addAnimation((PropertyAnimator) getScaleAnimator(this.mFakeViewParent, f8, pivotX, ((float) this.mGridInfo.getRealToGrid()) / ((float) this.mGridInfo.from()), 1.0f));
    }

    private boolean isYearData(int i2) {
        if (i2 == 4) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$addHeaderView$0(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams.height == -1) {
            layoutParams.height = -2;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setFakeViewLayoutAlpha$3() {
        this.mFakeViewParent.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startYearOrMonthForViewingClickedAnimation$4() {
        endYearOrMonthForViewingAnimation();
        this.mIsAnimating = false;
    }

    private boolean moveThroughThumbnail(int i2, int i7) {
        RectF rect;
        if (!this.mFakeLayoutManager.getDataRect().isEmpty() && (rect = getRect(this.mFakeLayoutManager.getDividerRect(), i7, 0, 0.0f)) != null) {
            RectF rect2 = getRect(this.mFakeLayoutManager.getDataRect(), i2 - 1, 0, 0.0f);
            if (rect2 != null && rect2.top > rect.top) {
                return true;
            }
            RectF rect3 = getRect(this.mFakeLayoutManager.getDataRect(), i2, 0, 0.0f);
            if (rect3 == null || rect3.bottom >= rect.bottom) {
                return false;
            }
            return true;
        }
        return false;
    }

    private void setFakeViewLayoutAlpha() {
        this.mFakeViewParent.setAlpha(0.0f);
        this.mFakeViewParent.post(new g(this, 0));
    }

    private void setFakeViewVisibility() {
        setFakeViewLayoutAlpha();
        this.mFakeViewParent.setVisibility(0);
    }

    private void setGoneListVisibility(int i2) {
        Iterator<View> it = this.mGoneList.iterator();
        while (it.hasNext()) {
            ViewUtils.setVisibility(it.next(), i2);
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
        animatorSet.setDuration((long) this.mFadeInDuration).playTogether(arrayList);
        animatorSet.start();
    }

    private void startYearOrMonthForViewingClickedAnimation(int i2) {
        setVisibilityForStartAnimation();
        Iterator<Animator> it = getPropertyAnimators().iterator();
        boolean z = false;
        while (it.hasNext()) {
            PropertyAnimator propertyAnimator = (PropertyAnimator) it.next();
            propertyAnimator.setDuration(i2);
            propertyAnimator.start();
            propertyAnimator.withEndAction(new C0451a(2, propertyAnimator));
            if (!z) {
                propertyAnimator.withEndAction(new g(this, 1));
                z = true;
            }
        }
    }

    public void addDataAnimator(ListViewHolder listViewHolder, int i2, int i7, boolean z, boolean z3) {
        RectF[] rectFArr = new RectF[2];
        RectF rect = getRect(this.mFakeLayoutManager.getFakeDataRect(), i2, i7, this.mShiftedOffset);
        rectFArr[1] = rect;
        if (!z3) {
            rect = getRect(this.mFakeLayoutManager.getDataRect(), i2, i7, 0.0f);
        }
        rectFArr[0] = rect;
        if (listViewHolder.getRootView() == null) {
            return;
        }
        if (rectFArr[0] == null || rectFArr[1] == null) {
            addToGoneList(listViewHolder.getRootView());
            return;
        }
        boolean animWithUpdateLayoutParam = animWithUpdateLayoutParam(rectFArr);
        adjustItemGap(rectFArr, z, animWithUpdateLayoutParam);
        addAnimation(new TranslationAnimator(listViewHolder.getRootView(), rectFArr[0], rectFArr[1]).setAnimationListener(new f(this, 0)));
        addAnimation(createScaleAnimator(listViewHolder, z, rectFArr, animWithUpdateLayoutParam).setAnimationListener(new l(this, animWithUpdateLayoutParam)));
        hideDecorView(listViewHolder, z);
    }

    public void addDecoViewFadeInAnimation(ArrayList<Animator> arrayList, ListViewHolder listViewHolder) {
        View decoView = listViewHolder.getDecoView(11);
        if (decoView != null) {
            addFadeInAnimation(arrayList, decoView);
        }
    }

    public void addFadeInAnimation(ArrayList<Animator> arrayList, View view) {
        addFadeInAnimation(arrayList, view, 0.0f, 1.0f);
    }

    public void addFakeViewBitmapToCache() {
        PinchFakeLayoutManager pinchFakeLayoutManager = this.mFakeLayoutManager;
        if (pinchFakeLayoutManager != null) {
            Iterator<ListViewHolder> it = pinchFakeLayoutManager.getFakeViewHolders().iterator();
            while (it.hasNext()) {
                ListViewHolder next = it.next();
                if (!isYearData(next.getViewType()) || !next.hasBitmap()) {
                    addBitmapToCache(next);
                } else {
                    this.mBitmapCache.put(Integer.valueOf(next.getViewPosition()), next.getBitmap());
                }
            }
        }
    }

    public void addHeaderTranslateAnimator(View view, float f, float f5, float f8) {
        addAnimation(new TranslationAnimator(view, new RectF(f, f5, 0.0f, 0.0f), new RectF(f, f8, 0.0f, 0.0f)).setAnimationListener(new f(this, 0)));
    }

    public void addHeaderView() {
        View view;
        if (isHeaderViewShowingOnRecyclerView()) {
            this.mHeaderCloneView = new CloneView(getRecyclerView().getContext());
            if (this.mHeaderView.getParent() == null) {
                view = this.mHeaderView;
            } else {
                view = (View) this.mHeaderView.getParent();
            }
            if (isFakeViewShiftedOnTop()) {
                this.mHeaderCloneView.setSource(this.mHeaderView);
                addHeaderTranslateAnimator(this.mHeaderCloneView, (float) view.getLeft(), (float) view.getTop(), getFakeHeaderViewTop(view));
                addHeaderParentAlphaAnimator(view);
            } else {
                this.mHeaderCloneView.setSource(view);
                addHeaderTranslateAnimator(this.mHeaderCloneView, (float) view.getLeft(), (float) view.getTop(), (float) view.getTop());
            }
            addHeaderViewAtFirst(this.mHeaderCloneView);
        } else if (this.mHeaderView != null && isFakeViewShiftedOnTop()) {
            View view2 = this.mHeaderView;
            addHeaderTranslateAnimator(view2, (float) getHeaderStartPos(view2), getHeaderFromTop(this.mHeaderView), getFakeHeaderViewTop(this.mHeaderView));
            ViewUtils.removeSelf(this.mHeaderView);
            Optional.ofNullable(this.mHeaderView.getLayoutParams()).ifPresent(new C0438h(24));
            addHeaderViewAtFirst(this.mHeaderView);
        }
    }

    public void addHeaderViewAtFirst(View view) {
        ((ViewGroup) getRecyclerView().getParent()).addView(view);
        this.mIsHeaderViewAddedAtFirst = true;
    }

    public void addHeaderViewOffset() {
        if (this.mHeaderView != null && isFakeViewShiftedOnTop()) {
            this.mScrollOffset = (int) (getFakeHeaderViewTop(this.mHeaderView) + ((float) getHeaderViewHeight()) + ((float) this.mLayoutManager.getHeaderBottomMargin(this.mGridInfo.to())) + ((float) this.mScrollOffset));
        } else if (isHeaderViewShowingOnRecyclerView() && this.mHeaderView.getParent() != null) {
            this.mScrollOffset = (int) (((View) this.mHeaderView.getParent()).getY() + ((float) getHeaderViewHeight()) + ((float) this.mLayoutManager.getHeaderBottomMargin(this.mGridInfo.to())) + ((float) this.mScrollOffset));
        }
    }

    public void addMatchedDividerAnimator(ListViewHolder listViewHolder, ListViewHolder listViewHolder2, RectF rectF) {
        RectF rect = getRect(listViewHolder2.getRootView());
        addAnimation(new TranslationAnimator(listViewHolder2.getRootView(), rect, rectF).setAnimationListener(new f(this, 0)));
        addAnimation(new TranslationAnimator(listViewHolder.getRootView(), rect, rectF).setAnimationListener(new f(this, 0)));
    }

    public void addToGoneList(View view) {
        this.mGoneList.add(view);
    }

    public boolean animWithUpdateLayoutParam(RectF[] rectFArr) {
        return withRealRatio();
    }

    public void clearBitmapCache() {
        super.clearBitmapCache();
        this.mBitmapCache.clear();
    }

    public void clearHeaderView() {
        if (this.mIsHeaderViewAddedAtFirst) {
            CloneView cloneView = this.mHeaderCloneView;
            if (cloneView != null) {
                ViewUtils.removeSelf(cloneView);
            } else {
                ViewUtils.removeSelf(this.mHeaderView);
            }
            this.mHeaderCloneView = null;
            this.mHeaderView = null;
            this.mIsHeaderViewAddedAtFirst = false;
        }
    }

    public ScaleAnimator createScaleAnimator(ListViewHolder listViewHolder, boolean z, RectF[] rectFArr, boolean z3) {
        View view;
        RectF rectF;
        if (listViewHolder.getScalableView() != null) {
            view = listViewHolder.getScalableView();
        } else {
            view = listViewHolder.getRootView();
        }
        if (z) {
            rectF = rectFArr[1];
        } else {
            rectF = rectFArr[0];
        }
        return new ScaleAnimator(view, rectF, rectFArr[0], rectFArr[1]).enableUpdateLayoutParam(z3).addPadding(this.mFromPadding, this.mToPadding);
    }

    public ListViewHolder findMatchedDivider(ArrayList<ListViewHolder> arrayList, ListViewHolder listViewHolder) {
        if (listViewHolder.getDateTextView() == null) {
            return null;
        }
        CharSequence text = listViewHolder.getDateTextView().getText();
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        Iterator<ListViewHolder> it = arrayList.iterator();
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            if (next != null && next.getDateTextView() != null && TextUtils.equals(text, next.getDateTextView().getText())) {
                arrayList.remove(next);
                return next;
            }
        }
        return null;
    }

    public float getFakeHeaderViewTop(View view) {
        return Math.min(0.0f, (((float) getRecyclerView().getHeight()) - this.mFakeLayoutManager.getHeight()) - ((float) view.getHeight()));
    }

    public float[] getFocusedPositionInfo(int i2) {
        char c5;
        double d;
        float[] focusXY = getFocusXY();
        int findFirstVisibleItemPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = getLastVisibleItemPosition();
        char c6 = 1;
        if (focusXY == null || this.mLayoutManager.isAppbarVisible() || isHeaderViewShowingOnRecyclerView()) {
            while (findFirstVisibleItemPosition <= lastVisibleItemPosition) {
                View findViewByPosition = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
                if (findViewByPosition == null || !ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, this.mGridInfo.from()))) {
                    findFirstVisibleItemPosition++;
                } else {
                    return new float[]{(float) findFirstVisibleItemPosition, findViewByPosition.getY(), findViewByPosition.getX(), findViewByPosition.getY(), 0.0f};
                }
            }
            return new float[]{0.0f, (float) this.mPositionCache.getHintViewHeight(this.mLayoutManager, 0, i2), 0.0f, 0.0f, 0.0f};
        }
        float[] fArr = new float[5];
        double d2 = Double.MAX_VALUE;
        while (findFirstVisibleItemPosition <= lastVisibleItemPosition) {
            View findViewByPosition2 = this.mLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition2 == null || !ViewHolderValue.isData(this.mLayoutManager.getHintItemViewType(findFirstVisibleItemPosition, this.mGridInfo.from()))) {
                c5 = c6;
                d = d2;
            } else {
                float width = (((float) findViewByPosition2.getWidth()) / 2.0f) + findViewByPosition2.getX();
                float height = (((float) findViewByPosition2.getHeight()) / 2.0f) + findViewByPosition2.getY();
                c5 = c6;
                d = d2;
                double sqrt = Math.sqrt(Math.pow((double) (height - focusXY[c6]), 2.0d) + Math.pow((double) (width - focusXY[0]), 2.0d));
                if (sqrt < d) {
                    fArr[0] = (float) findFirstVisibleItemPosition;
                    fArr[c5] = findViewByPosition2.getY();
                    fArr[2] = focusXY[0] - findViewByPosition2.getX();
                    float f = focusXY[c5];
                    fArr[3] = f;
                    fArr[4] = f - findViewByPosition2.getY();
                    if (hasFocusedPoint(findViewByPosition2, focusXY)) {
                        break;
                    }
                    d2 = sqrt;
                    findFirstVisibleItemPosition++;
                    c6 = c5;
                }
            }
            d2 = d;
            findFirstVisibleItemPosition++;
            c6 = c5;
        }
        return fArr;
    }

    public float getHeaderFromTop(View view) {
        if (view != null) {
            return this.mFakeLayoutManager.getDataRectTop() - ((float) view.getHeight());
        }
        return 0.0f;
    }

    public int getHeaderStartPos(View view) {
        if (view != null) {
            return view.getLeft();
        }
        return 0;
    }

    public int getHeaderViewHeight() {
        TextView headerDescriptionView = this.mLayoutManager.getHeaderDescriptionView();
        if (headerDescriptionView != null) {
            int maxLines = headerDescriptionView.getMaxLines();
            int headerDescriptionWidthOffset = this.mLayoutManager.getHeaderDescriptionWidthOffset();
            TextUtils.TruncateAt ellipsize = headerDescriptionView.getEllipsize();
            StaticLayout headerDescriptionFrom = getHeaderDescriptionFrom(headerDescriptionView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.from()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            StaticLayout headerDescriptionTo = getHeaderDescriptionTo(headerDescriptionView, this.mLayoutManager.getHeaderWidth(this.mGridInfo.to()) - headerDescriptionWidthOffset, maxLines, ellipsize);
            if (!(headerDescriptionFrom == null || headerDescriptionTo == null)) {
                this.mHeaderDiff = headerDescriptionTo.getHeight() - headerDescriptionFrom.getHeight();
                return this.mHeaderView.getHeight() + this.mHeaderDiff;
            }
        }
        return this.mHeaderView.getHeight();
    }

    public int getLastVisibleItemPosition() {
        return this.mLayoutManager.findLastVisibleItemPosition();
    }

    public RectF getRect(HashMap<Integer, ArrayList<RectF>> hashMap, int i2, int i7, float f) {
        ArrayList arrayList = hashMap.get(Integer.valueOf(i2));
        if (arrayList == null) {
            return getVerticalExpandedRect(hashMap, i2, i7, f);
        }
        if (i7 >= arrayList.size()) {
            return getHorizontalExpandedRect(arrayList, i7, f);
        }
        return getShiftedRectF((RectF) arrayList.get(i7), f);
    }

    public boolean hasFocusedPoint(View view, float[] fArr) {
        if (view.getX() >= fArr[0] || view.getX() + ((float) view.getWidth()) <= fArr[0] || view.getY() >= fArr[1] || view.getY() + ((float) view.getHeight()) <= fArr[1]) {
            return false;
        }
        return true;
    }

    public void hideDecorView(ListViewHolder listViewHolder, boolean z) {
        if (listViewHolder.getDecoView(11) != null) {
            addToGoneList(listViewHolder.getDecoView(11));
        }
    }

    public void initFakeViewLayout() {
        ViewGroup viewGroup = (ViewGroup) ((View) getRecyclerView().getParent()).findViewById(R.id.fake_view_layout);
        this.mFakeViewParent = viewGroup;
        viewGroup.setVisibility(4);
    }

    public void initHeaderView() {
        View headerView = this.mLayoutManager.getHeaderView();
        this.mHeaderView = headerView;
        if (!ViewUtils.isVisible(headerView) || ViewUtils.getMeasuredHeight(this.mHeaderView) == 0) {
            this.mHeaderView = null;
        }
    }

    public void initPadding() {
        this.mFromPadding = GridMarginHelper.getMargin(getRecyclerView(), this.mGridInfo.from());
        this.mToPadding = GridMarginHelper.getMargin(getRecyclerView(), this.mGridInfo.getRealToGrid());
    }

    public void initializeFakeViewLayoutTransAnimation(int i2, boolean z) {
        int i7;
        int startDataRow = this.mFakeLayoutManager.getStartDataRow();
        int startDividerRow = this.mFakeLayoutManager.getStartDividerRow();
        if (this.mGridInfo.isGridGettingLarger()) {
            i7 = 0;
        } else {
            i7 = i2;
        }
        Iterator<ListViewHolder> it = this.mFakeLayoutManager.getFakeViewHolders().iterator();
        boolean z3 = true;
        int i8 = -1;
        while (it.hasNext()) {
            ListViewHolder next = it.next();
            int hintStartSpan = this.mPositionCache.getHintStartSpan(this.mLayoutManager, next.getViewPosition(), this.mGridInfo.to());
            if (!z3 && hintStartSpan <= 0) {
                if (ViewHolderValue.isData(i8)) {
                    startDataRow++;
                } else {
                    startDividerRow++;
                }
                if (this.mGridInfo.isGridGettingLarger()) {
                    i7 = 0;
                } else {
                    i7 = i2;
                }
            }
            int i10 = startDividerRow;
            int i11 = startDataRow;
            int viewType = next.getViewType();
            if (ViewHolderValue.isData(viewType)) {
                addDataAnimator(next, i11, i7, true, z);
            } else {
                ListViewHolder listViewHolder = next;
                if (supportDividerAnimation()) {
                    addDividerAnimator(listViewHolder, i11, i10, z);
                } else {
                    addToGoneList(listViewHolder.getRootView());
                }
            }
            i7++;
            startDataRow = i11;
            z3 = false;
            startDividerRow = i10;
            i8 = viewType;
        }
    }

    public void initializeRecyclerViewTransAnimation(int i2, int i7) {
        int i8;
        PicturesPinchAnimationManagerV2 picturesPinchAnimationManagerV2;
        int i10 = getStartRows(i2)[0];
        if (this.mGridInfo.isGridGettingLarger()) {
            i8 = i7;
        } else {
            i8 = 0;
        }
        int i11 = -1;
        int i12 = 0;
        while (i12 < this.mLayoutManager.getChildCount()) {
            ListViewHolder listViewHolder = (ListViewHolder) this.getChildViewHolder(this.mLayoutManager.getChildAt(i12));
            if (listViewHolder == null || listViewHolder.getViewPosition() < this.mLayoutManager.findFirstVisibleItemPosition() || listViewHolder.getViewPosition() > this.getLastVisibleItemPosition()) {
                picturesPinchAnimationManagerV2 = this;
            } else {
                int hintStartSpan = this.mPositionCache.getHintStartSpan(this.mLayoutManager, listViewHolder.getViewPosition(), this.mGridInfo.from());
                if (i12 != 0 && hintStartSpan <= 0) {
                    if (ViewHolderValue.isData(i11)) {
                        i10++;
                    }
                    if (this.mGridInfo.isGridGettingLarger()) {
                        i8 = i7;
                    } else {
                        i8 = 0;
                    }
                }
                int i13 = i10;
                int i14 = i8;
                int viewType = listViewHolder.getViewType();
                if (ViewHolderValue.isData(viewType)) {
                    picturesPinchAnimationManagerV2 = this;
                    picturesPinchAnimationManagerV2.addDataAnimator(listViewHolder, i13, i14, false, false);
                } else {
                    picturesPinchAnimationManagerV2 = this;
                }
                i11 = viewType;
                i10 = i13;
                i8 = i14 + 1;
            }
            i12++;
            this = picturesPinchAnimationManagerV2;
        }
    }

    public void initializeTransAnimation(int i2, float f) {
        int calculateRectMap = this.mFakeLayoutManager.calculateRectMap(new PinchFakeLayoutManager.CalculateInfo(this.mGridInfo.from(), i2, f), this.mGridInfo.getRealToGrid(), getHorizontalFocusedRatio(i2));
        this.mRecyclerViewDividers = getDividerHolders();
        initializeRecyclerViewTransAnimation(i2, calculateRectMap);
        initializeFakeViewLayoutTransAnimation(calculateRectMap, false);
        hideDividers();
    }

    public boolean isAlbum() {
        return this.mLayoutManager.isAlbum();
    }

    public boolean isAnimating() {
        return this.mIsAnimating;
    }

    public boolean isFakeViewShiftedOnTop() {
        if (this.mFakeLayoutManager != null && ((float) getRecyclerView().getHeight()) > this.mFakeLayoutManager.getHeight()) {
            return true;
        }
        return false;
    }

    public boolean isHeaderViewShowingOnRecyclerView() {
        if (this.mHeaderView == null || this.mLayoutManager.findFirstVisibleItemPosition() != 0) {
            return false;
        }
        return true;
    }

    public boolean isNotSupportPivotOnFingerPos() {
        if (!this.mSupportPivotOnFingerPos || withRealRatio()) {
            return true;
        }
        return false;
    }

    public boolean isStories() {
        return this.mLayoutManager.isStories();
    }

    public boolean isThumbKindChanged() {
        return this.mIsThumbKindChanged;
    }

    public void onAnimationCompleted(boolean z, boolean z3) {
        clearHeaderView();
        setGoneListVisibility(0);
        this.mGoneList.clear();
        ViewUtils.setVisibility(this.mFakeViewParent, 8);
        super.onAnimationCompleted(z, z3);
        ViewUtils.removeAllViews(this.mFakeViewParent);
        clearFakeLayoutManager();
    }

    public void onAnimationStarted() {
        setVisibilityForStartAnimation();
    }

    public void onLayout() {
        if (this.mFinishingAnimation) {
            startFadeInAnimation();
            setItemViewMargin();
            super.onLayout();
        }
    }

    public void onPrepareAnimation(int i2, int i7, int i8) {
        int i10;
        super.onPrepareAnimation(i2, i7, i8);
        init();
        float[] focusedPositionInfo = getFocusedPositionInfo(this.mGridInfo.from());
        if (this.mLayoutManager.isYear(i2)) {
            i10 = this.mLayoutManager.getHintYearDataPosition((int) focusedPositionInfo[0], focusedPositionInfo[2], focusedPositionInfo[4], this.mGridInfo.from());
        } else {
            i10 = this.mLayoutManager.getHintDataPosition((int) focusedPositionInfo[0], focusedPositionInfo[2], focusedPositionInfo[4], this.mGridInfo.from());
        }
        int hintViewPosition = this.mLayoutManager.getHintViewPosition(i10, this.mGridInfo.to());
        float scrollOffset = getScrollOffset(focusedPositionInfo, hintViewPosition, i10);
        addFakeView(hintViewPosition, scrollOffset, getCalculateRange(focusedPositionInfo));
        initializeAnimation(focusedPositionInfo, scrollOffset);
        startAnimation();
        setFakeViewLayoutAlpha();
    }

    public void resetAlpha(View view) {
        ViewUtils.setAlpha(view, 1.0f);
    }

    /* renamed from: resetScale */
    public void lambda$addDataAnimator$2(View view, boolean z) {
        if (z) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
        } else {
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        }
        view.setPivotX(((float) view.getWidth()) / 2.0f);
        view.setPivotY(((float) view.getHeight()) / 2.0f);
    }

    public void resetTranslate(View view) {
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
    }

    public void restoreBitmapFromCache(ListViewHolder listViewHolder) {
        if (listViewHolder == null || !isYearData(listViewHolder.getViewType())) {
            super.restoreBitmapFromCache(listViewHolder);
            return;
        }
        Bitmap bitmap = this.mBitmapCache.get(Integer.valueOf(listViewHolder.getViewPosition()));
        if (bitmap != null) {
            listViewHolder.bindImage(bitmap);
        }
    }

    public void setVisibilityForStartAnimation() {
        ViewUtils.setVisibility(this.mFakeViewParent, 0);
        setGoneListVisibility(8);
    }

    public void startMonthForViewingClickedAnimation(int i2, float f, float f5) {
        initMonthForViewingClickedAnimation();
        addFakeView(i2, f5, new PinchFakeLayoutManager.CalculateRange(getTopRows(i2), getBottomRows(i2)));
        initializeListViewAnimation();
        initializeTransAnimation(i2, f5);
        addHeaderView();
        startAnimation();
        startYearOrMonthForViewingClickedAnimation(250);
        setFakeViewLayoutAlpha();
    }

    public void startYearClickedAnimation(int i2, RectF rectF) {
        if (!this.mIsAnimating) {
            this.mIsAnimating = true;
            initYearClickedAnimation();
            addFakeView(i2, rectF.top, (PinchFakeLayoutManager.CalculateRange) null);
            setFakeViewVisibility();
            initializeListViewAnimation();
            initializeYearAnimation(rectF.centerX(), rectF.centerY());
            startYearOrMonthForViewingClickedAnimation(350);
        }
    }

    public boolean supportDividerAnimation() {
        return !isSelectionMode();
    }

    public boolean withRealRatio() {
        return this.mGridInfo.withRealRatio();
    }

    public void addFadeInAnimation(ArrayList<Animator> arrayList, View view, float f, float f5) {
        view.setAlpha(f);
        arrayList.add(new AlphaAnimator(view, f, f5));
    }
}
