package androidx.viewpager.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.math.MathUtils;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.EdgeEffectCompat;
import androidx.customview.view.AbsSavedState;
import c0.C0086a;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.google.android.material.tabs.a;
import com.samsung.android.sdk.cover.ScoverState;
import i.C0212a;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPager extends ViewGroup {
    private static final Comparator<ItemInfo> COMPARATOR = new Comparator<ItemInfo>() {
        public int compare(ItemInfo itemInfo, ItemInfo itemInfo2) {
            return itemInfo.position - itemInfo2.position;
        }
    };
    static final int[] LAYOUT_ATTRS = {16842931};
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f5 = f - 1.0f;
            return (f5 * f5 * f5 * f5 * f5) + 1.0f;
        }
    };
    private static final ViewPositionComparator sPositionComparator = new ViewPositionComparator();
    private int mActivePointerId = -1;
    PagerAdapter mAdapter;
    private List<OnAdapterChangeListener> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private boolean mDragInGutterEnabled = true;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable = new Runnable() {
        public void run() {
            ViewPager.this.setScrollState(0);
            ViewPager.this.populate();
        }
    };
    private int mExpectedAdapterCount;
    private boolean mFakeDragging;
    private boolean mFirstLayout = true;
    private float mFirstOffset = -3.4028235E38f;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private OnPageChangeListener mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsChangedConfiguration = false;
    private boolean mIsMouseWheelEventSupport = false;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<ItemInfo> mItems = new ArrayList<>();
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset = Float.MAX_VALUE;
    public EdgeEffect mLeftEdge;
    private int mLeftIncr = -1;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private PagerObserver mObserver;
    private int mOffscreenPageLimit = 1;
    private OnPageChangeListener mOnPageChangeListener;
    private List<OnPageChangeListener> mOnPageChangeListeners;
    private int mPageMargin;
    private int mPagingTouchSlop = 0;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState = null;
    private ClassLoader mRestoredClassLoader = null;
    private int mRestoredCurItem = -1;
    public EdgeEffect mRightEdge;
    private int mScaledTouchSlop = 0;
    private int mScrollState = 0;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private boolean mSupportLayoutDirectionForDatePicker = false;
    private final ItemInfo mTempItem = new ItemInfo();
    private final Rect mTempRect = new Rect();
    private int mTopPageBounds;
    private int mTouchSlop;
    private float mTouchSlopRatio = 0.5f;
    private boolean mUsePagingTouchSlopForStylus = false;
    private VelocityTracker mVelocityTracker;

    @Inherited
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public @interface DecorView {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ItemInfo {
        Object object;
        float offset;
        int position;
        boolean scrolling;
        float widthFactor;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class MyAccessibilityDelegate extends AccessibilityDelegateCompat {
        public MyAccessibilityDelegate() {
        }

        private boolean canScroll() {
            PagerAdapter pagerAdapter = ViewPager.this.mAdapter;
            if (pagerAdapter == null || pagerAdapter.getCount() <= 1) {
                return false;
            }
            return true;
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            PagerAdapter pagerAdapter;
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName("androidx.viewpager.widget.ViewPager");
            accessibilityEvent.setScrollable(canScroll());
            if (accessibilityEvent.getEventType() == 4096 && (pagerAdapter = ViewPager.this.mAdapter) != null) {
                accessibilityEvent.setItemCount(pagerAdapter.getCount());
                accessibilityEvent.setFromIndex(ViewPager.this.mCurItem);
                accessibilityEvent.setToIndex(ViewPager.this.mCurItem);
            }
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setClassName("androidx.viewpager.widget.ViewPager");
            accessibilityNodeInfoCompat.setScrollable(canScroll());
            if (ViewPager.this.canScrollHorizontally(1)) {
                accessibilityNodeInfoCompat.addAction(4096);
            }
            if (ViewPager.this.canScrollHorizontally(-1)) {
                accessibilityNodeInfoCompat.addAction((int) SerializeOptions.SORT);
            }
        }

        public boolean performAccessibilityAction(View view, int i2, Bundle bundle) {
            if (super.performAccessibilityAction(view, i2, bundle)) {
                return true;
            }
            if (i2 != 4096) {
                if (i2 != 8192 || !ViewPager.this.canScrollHorizontally(-1)) {
                    return false;
                }
                ViewPager viewPager = ViewPager.this;
                viewPager.setCurrentItem(viewPager.mCurItem - 1);
                return true;
            } else if (!ViewPager.this.canScrollHorizontally(1)) {
                return false;
            } else {
                ViewPager viewPager2 = ViewPager.this;
                viewPager2.setCurrentItem(viewPager2.mCurItem + 1);
                return true;
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnAdapterChangeListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnPageChangeListener {
        void onPageScrollStateChanged(int i2);

        void onPageScrolled(int i2, float f, int i7);

        void onPageSelected(int i2);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PagerObserver extends DataSetObserver {
        public PagerObserver() {
        }

        public void onChanged() {
            ViewPager.this.dataSetChanged();
        }

        public void onInvalidated() {
            ViewPager.this.dataSetChanged();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }

            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        Parcelable adapterState;
        ClassLoader loader;
        int position;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder sb2 = new StringBuilder("FragmentPager.SavedState{");
            sb2.append(Integer.toHexString(System.identityHashCode(this)));
            sb2.append(" position=");
            return C0086a.l(sb2, this.position, "}");
        }

        public void writeToParcel(Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.position);
            parcel.writeParcelable(this.adapterState, i2);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
            this.position = parcel.readInt();
            this.adapterState = parcel.readParcelable(classLoader);
            this.loader = classLoader;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewPositionComparator implements Comparator<View> {
        public int compare(View view, View view2) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
            boolean z = layoutParams.isDecor;
            if (z != layoutParams2.isDecor) {
                return z ? 1 : -1;
            }
            return layoutParams.position - layoutParams2.position;
        }
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViewPager(context, attributeSet);
    }

    private void calculatePageOffsets(ItemInfo itemInfo, int i2, ItemInfo itemInfo2) {
        float f;
        float f5;
        float f8;
        int i7;
        int i8;
        ItemInfo itemInfo3;
        ItemInfo itemInfo4;
        int count = this.mAdapter.getCount();
        int clientWidth = getClientWidth();
        if (clientWidth > 0) {
            f = ((float) this.mPageMargin) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        if (itemInfo2 != null) {
            int i10 = itemInfo2.position;
            int i11 = itemInfo.position;
            if (i10 < i11) {
                float f10 = itemInfo2.offset + itemInfo2.widthFactor + f;
                int i12 = i10 + 1;
                int i13 = 0;
                while (i12 <= itemInfo.position && i13 < this.mItems.size()) {
                    Object obj = this.mItems.get(i13);
                    while (true) {
                        itemInfo4 = (ItemInfo) obj;
                        if (i12 > itemInfo4.position && i13 < this.mItems.size() - 1) {
                            i13++;
                            obj = this.mItems.get(i13);
                        }
                    }
                    while (i12 < itemInfo4.position) {
                        f10 += this.mAdapter.getPageWidth(i12) + f;
                        i12++;
                    }
                    itemInfo4.offset = f10;
                    f10 += itemInfo4.widthFactor + f;
                    i12++;
                }
            } else if (i10 > i11) {
                int size = this.mItems.size() - 1;
                float f11 = itemInfo2.offset;
                while (true) {
                    i10--;
                    if (i10 < itemInfo.position || size < 0) {
                        break;
                    }
                    Object obj2 = this.mItems.get(size);
                    while (true) {
                        itemInfo3 = (ItemInfo) obj2;
                        if (i10 < itemInfo3.position && size > 0) {
                            size--;
                            obj2 = this.mItems.get(size);
                        }
                    }
                    while (i10 > itemInfo3.position) {
                        f11 -= this.mAdapter.getPageWidth(i10) + f;
                        i10--;
                    }
                    f11 -= itemInfo3.widthFactor + f;
                    itemInfo3.offset = f11;
                }
            }
        }
        int size2 = this.mItems.size();
        float f12 = itemInfo.offset;
        int i14 = itemInfo.position;
        int i15 = i14 - 1;
        if (i14 == 0) {
            f5 = f12;
        } else {
            f5 = -3.4028235E38f;
        }
        this.mFirstOffset = f5;
        int i16 = count - 1;
        if (i14 == i16) {
            f8 = (itemInfo.widthFactor + f12) - 1.0f;
        } else {
            f8 = Float.MAX_VALUE;
        }
        this.mLastOffset = f8;
        int i17 = i2 - 1;
        while (i17 >= 0) {
            ItemInfo itemInfo5 = this.mItems.get(i17);
            while (true) {
                i8 = itemInfo5.position;
                if (i15 <= i8) {
                    break;
                }
                f12 -= this.mAdapter.getPageWidth(i15) + f;
                i15--;
            }
            f12 -= itemInfo5.widthFactor + f;
            itemInfo5.offset = f12;
            if (i8 == 0) {
                this.mFirstOffset = f12;
            }
            i17--;
            i15--;
        }
        float f13 = itemInfo.offset + itemInfo.widthFactor + f;
        int i18 = itemInfo.position + 1;
        int i19 = i2 + 1;
        while (i19 < size2) {
            ItemInfo itemInfo6 = this.mItems.get(i19);
            while (true) {
                i7 = itemInfo6.position;
                if (i18 >= i7) {
                    break;
                }
                f13 += this.mAdapter.getPageWidth(i18) + f;
                i18++;
            }
            if (i7 == i16) {
                this.mLastOffset = (itemInfo6.widthFactor + f13) - 1.0f;
            }
            itemInfo6.offset = f13;
            f13 += itemInfo6.widthFactor + f;
            i19++;
            i18++;
        }
    }

    private void completeScroll(boolean z) {
        boolean z3;
        if (this.mScrollState == 2) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (!(scrollX == currX && scrollY == currY)) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        pageScrolled(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo itemInfo = this.mItems.get(i2);
            if (itemInfo.scrolling) {
                itemInfo.scrolling = false;
                z3 = true;
            }
        }
        if (!z3) {
            return;
        }
        if (z) {
            ViewCompat.postOnAnimation(this, this.mEndScrollRunnable);
        } else {
            this.mEndScrollRunnable.run();
        }
    }

    private int determineTargetPage(int i2, float f, int i7, int i8) {
        int i10;
        float f5;
        int i11;
        if (Math.abs(i8) <= this.mFlingDistance || Math.abs(i7) <= this.mMinimumVelocity || EdgeEffectCompat.getDistance(this.mLeftEdge) != 0.0f || EdgeEffectCompat.getDistance(this.mRightEdge) != 0.0f) {
            if (i2 >= this.mCurItem) {
                f5 = 0.4f;
            } else {
                f5 = 0.6f;
            }
            i10 = i2 - (this.mLeftIncr * ((int) (f + f5)));
        } else {
            if (i7 > 0) {
                i11 = 0;
            } else {
                i11 = this.mLeftIncr;
            }
            i10 = i2 - i11;
        }
        if (this.mItems.size() > 0) {
            return MathUtils.clamp(i10, this.mItems.get(0).position, ((ItemInfo) C0212a.i(this.mItems, 1)).position);
        }
        return i10;
    }

    private void dispatchOnPageScrolled(int i2, float f, int i7) {
        OnPageChangeListener onPageChangeListener;
        OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
        if (onPageChangeListener2 != null) {
            onPageChangeListener2.onPageScrolled(i2, f, i7);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i8 = 0; i8 < size; i8++) {
                try {
                    onPageChangeListener = this.mOnPageChangeListeners.get(i8);
                } catch (IndexOutOfBoundsException unused) {
                    StringBuilder o2 = C0086a.o(i8, "IndexOutOfBoundsException: Index: ", ", Size: ");
                    o2.append(this.mOnPageChangeListeners.size());
                    Log.e("ViewPager", o2.toString());
                    onPageChangeListener = null;
                }
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(i2, f, i7);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrolled(i2, f, i7);
        }
    }

    private void dispatchOnPageSelected(int i2) {
        OnPageChangeListener onPageChangeListener;
        OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
        if (onPageChangeListener2 != null) {
            onPageChangeListener2.onPageSelected(i2);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                try {
                    onPageChangeListener = this.mOnPageChangeListeners.get(i7);
                } catch (IndexOutOfBoundsException unused) {
                    StringBuilder o2 = C0086a.o(i7, "IndexOutOfBoundsException: Index: ", ", Size: ");
                    o2.append(this.mOnPageChangeListeners.size());
                    Log.e("ViewPager", o2.toString());
                    onPageChangeListener = null;
                }
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageSelected(i2);
        }
    }

    private void dispatchOnScrollStateChanged(int i2) {
        OnPageChangeListener onPageChangeListener;
        OnPageChangeListener onPageChangeListener2 = this.mOnPageChangeListener;
        if (onPageChangeListener2 != null) {
            onPageChangeListener2.onPageScrollStateChanged(i2);
        }
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                try {
                    onPageChangeListener = this.mOnPageChangeListeners.get(i7);
                } catch (IndexOutOfBoundsException unused) {
                    StringBuilder o2 = C0086a.o(i7, "IndexOutOfBoundsException: Index: ", ", Size: ");
                    o2.append(this.mOnPageChangeListeners.size());
                    Log.e("ViewPager", o2.toString());
                    onPageChangeListener = null;
                }
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(i2);
                }
            }
        }
        OnPageChangeListener onPageChangeListener3 = this.mInternalPageChangeListener;
        if (onPageChangeListener3 != null) {
            onPageChangeListener3.onPageScrollStateChanged(i2);
        }
    }

    private void endDrag() {
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private Rect getChildRectInPagerCoordinates(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getScrollStart() {
        if (seslIsDatePickerLayoutRtl()) {
            return 16777216 - getScrollX();
        }
        return getScrollX();
    }

    private ItemInfo infoForCurrentScrollPosition() {
        float f;
        float f5;
        int i2;
        int scrollStart = getScrollStart();
        int clientWidth = getClientWidth();
        float f8 = 0.0f;
        if (clientWidth > 0) {
            f = ((float) scrollStart) / ((float) clientWidth);
        } else {
            f = 0.0f;
        }
        if (clientWidth > 0) {
            f5 = ((float) this.mPageMargin) / ((float) clientWidth);
        } else {
            f5 = 0.0f;
        }
        int i7 = 0;
        boolean z = true;
        ItemInfo itemInfo = null;
        int i8 = -1;
        float f10 = 0.0f;
        while (i7 < this.mItems.size()) {
            ItemInfo itemInfo2 = this.mItems.get(i7);
            if (!z && itemInfo2.position != (i2 = i8 + 1)) {
                itemInfo2 = this.mTempItem;
                itemInfo2.offset = f8 + f10 + f5;
                itemInfo2.position = i2;
                itemInfo2.widthFactor = this.mAdapter.getPageWidth(i2);
                i7--;
            }
            ItemInfo itemInfo3 = itemInfo2;
            f8 = itemInfo3.offset;
            float f11 = itemInfo3.widthFactor + f8 + f5;
            if (!z && f < f8) {
                break;
            } else if (f < f11 || i7 == this.mItems.size() - 1) {
                return itemInfo3;
            } else {
                int i10 = itemInfo3.position;
                float f12 = itemInfo3.widthFactor;
                i7++;
                ItemInfo itemInfo4 = itemInfo3;
                i8 = i10;
                f10 = f12;
                itemInfo = itemInfo4;
                z = false;
            }
        }
        return itemInfo;
    }

    private static boolean isDecorView(View view) {
        if (view.getClass().getAnnotation(DecorView.class) != null) {
            return true;
        }
        return false;
    }

    private boolean isGutterDrag(float f, float f5) {
        if (this.mDragInGutterEnabled) {
            return false;
        }
        if (f < ((float) this.mGutterSize) && f5 > 0.0f) {
            return true;
        }
        if (f <= ((float) (getWidth() - this.mGutterSize)) || f5 >= 0.0f) {
            return false;
        }
        return true;
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int i2;
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            if (actionIndex == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            this.mLastMotionX = motionEvent.getX(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    private boolean pageScrolled(int i2) {
        if (this.mItems.size() != 0) {
            if (seslIsDatePickerLayoutRtl()) {
                i2 = 16777216 - i2;
            }
            ItemInfo infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            int clientWidth = getClientWidth();
            int i7 = this.mPageMargin;
            int i8 = clientWidth + i7;
            float f = (float) clientWidth;
            int i10 = infoForCurrentScrollPosition.position;
            float f5 = ((((float) i2) / f) - infoForCurrentScrollPosition.offset) / (infoForCurrentScrollPosition.widthFactor + (((float) i7) / f));
            this.mCalledSuper = false;
            onPageScrolled(i10, f5, (int) (((float) i8) * f5));
            if (this.mCalledSuper) {
                return true;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        } else if (this.mFirstLayout) {
            return false;
        } else {
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean performDrag(float r13, float r14) {
        /*
            r12 = this;
            boolean r0 = r12.seslIsDatePickerLayoutRtl()
            r1 = 0
            if (r0 == 0) goto L_0x0009
            r12.mIsChangedConfiguration = r1
        L_0x0009:
            boolean r0 = r12.seslIsDatePickerLayoutRtl()
            if (r0 == 0) goto L_0x0014
            android.widget.EdgeEffect r0 = r12.mRightEdge
            android.widget.EdgeEffect r2 = r12.mLeftEdge
            goto L_0x0018
        L_0x0014:
            android.widget.EdgeEffect r0 = r12.mLeftEdge
            android.widget.EdgeEffect r2 = r12.mRightEdge
        L_0x0018:
            float r3 = r12.mLastMotionX
            float r3 = r3 - r13
            r12.mLastMotionX = r13
            float r13 = r12.releaseHorizontalGlow(r3, r14)
            float r3 = r3 - r13
            r4 = 0
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            r4 = 1
            if (r13 == 0) goto L_0x002a
            r13 = r4
            goto L_0x002b
        L_0x002a:
            r13 = r1
        L_0x002b:
            float r5 = java.lang.Math.abs(r3)
            r6 = 953267991(0x38d1b717, float:1.0E-4)
            int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r5 >= 0) goto L_0x0037
            return r13
        L_0x0037:
            int r5 = r12.getScrollX()
            float r5 = (float) r5
            float r5 = r5 + r3
            boolean r3 = r12.seslIsDatePickerLayoutRtl()
            r6 = 1266679808(0x4b800000, float:1.6777216E7)
            if (r3 == 0) goto L_0x0047
            float r5 = r6 - r5
        L_0x0047:
            int r3 = r12.getClientWidth()
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r7 = r12.mItems
            java.lang.Object r7 = r7.get(r1)
            androidx.viewpager.widget.ViewPager$ItemInfo r7 = (androidx.viewpager.widget.ViewPager.ItemInfo) r7
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r8 = r12.mItems
            java.lang.Object r8 = i.C0212a.i(r8, r4)
            androidx.viewpager.widget.ViewPager$ItemInfo r8 = (androidx.viewpager.widget.ViewPager.ItemInfo) r8
            int r9 = r7.position
            if (r9 != 0) goto L_0x0061
            r9 = r4
            goto L_0x0062
        L_0x0061:
            r9 = r1
        L_0x0062:
            if (r9 == 0) goto L_0x0075
            boolean r7 = r12.seslIsDatePickerLayoutRtl()
            if (r7 == 0) goto L_0x0070
            float r7 = (float) r3
            float r10 = r12.mFirstOffset
            float r10 = r10 * r7
            float r10 = r10 + r7
            goto L_0x0079
        L_0x0070:
            float r7 = (float) r3
            float r10 = r12.mFirstOffset
        L_0x0073:
            float r10 = r10 * r7
            goto L_0x0079
        L_0x0075:
            float r7 = r7.offset
            float r10 = (float) r3
            goto L_0x0073
        L_0x0079:
            int r7 = r8.position
            androidx.viewpager.widget.PagerAdapter r11 = r12.mAdapter
            int r11 = r11.getCount()
            int r11 = r11 - r4
            if (r7 != r11) goto L_0x0085
            r1 = r4
        L_0x0085:
            if (r1 == 0) goto L_0x0098
            boolean r7 = r12.seslIsDatePickerLayoutRtl()
            if (r7 == 0) goto L_0x0093
            float r7 = (float) r3
            float r8 = r12.mLastOffset
            float r8 = r8 * r7
            float r8 = r8 + r7
            goto L_0x009c
        L_0x0093:
            float r7 = (float) r3
            float r8 = r12.mLastOffset
        L_0x0096:
            float r8 = r8 * r7
            goto L_0x009c
        L_0x0098:
            float r7 = r8.offset
            float r8 = (float) r3
            goto L_0x0096
        L_0x009c:
            int r7 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r7 >= 0) goto L_0x00b7
            if (r9 == 0) goto L_0x00b3
            float r13 = r10 - r5
            float r1 = (float) r3
            float r13 = r13 / r1
            int r1 = r12.getHeight()
            float r1 = (float) r1
            float r14 = r14 / r1
            r1 = 1065353216(0x3f800000, float:1.0)
            float r1 = r1 - r14
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r0, r13, r1)
            goto L_0x00b4
        L_0x00b3:
            r4 = r13
        L_0x00b4:
            r13 = r4
            r5 = r10
            goto L_0x00cd
        L_0x00b7:
            int r0 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x00cd
            if (r1 == 0) goto L_0x00ca
            float r5 = r5 - r8
            float r13 = (float) r3
            float r5 = r5 / r13
            int r13 = r12.getHeight()
            float r13 = (float) r13
            float r14 = r14 / r13
            androidx.core.widget.EdgeEffectCompat.onPullDistance(r2, r5, r14)
            goto L_0x00cb
        L_0x00ca:
            r4 = r13
        L_0x00cb:
            r13 = r4
            r5 = r8
        L_0x00cd:
            boolean r14 = r12.seslIsDatePickerLayoutRtl()
            if (r14 == 0) goto L_0x00d5
            float r5 = r6 - r5
        L_0x00d5:
            float r14 = r12.mLastMotionX
            int r0 = (int) r5
            float r1 = (float) r0
            float r5 = r5 - r1
            float r5 = r5 + r14
            r12.mLastMotionX = r5
            int r14 = r12.getScrollY()
            r12.scrollTo(r0, r14)
            r12.pageScrolled(r0)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.performDrag(float, float):boolean");
    }

    private void recomputeScrollPosition(int i2, int i7, int i8, int i10) {
        float f;
        int i11;
        if (i7 <= 0 || this.mItems.isEmpty()) {
            ItemInfo infoForPosition = infoForPosition(this.mCurItem);
            if (infoForPosition != null) {
                f = Math.min(infoForPosition.offset, this.mLastOffset);
            } else {
                f = 0.0f;
            }
            int paddingLeft = (int) (f * ((float) ((i2 - getPaddingLeft()) - getPaddingRight())));
            if (paddingLeft != getScrollX()) {
                completeScroll(false);
                scrollTo(paddingLeft, getScrollY());
            }
        } else if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
        } else {
            int paddingLeft2 = ((i2 - getPaddingLeft()) - getPaddingRight()) + i8;
            float scrollStart = ((float) getScrollStart()) / ((float) (((i7 - getPaddingLeft()) - getPaddingRight()) + i10));
            if (seslIsDatePickerLayoutRtl()) {
                i11 = (int) (1.6777216E7f - (scrollStart * ((float) paddingLeft2)));
            } else {
                i11 = (int) (scrollStart * ((float) paddingLeft2));
            }
            scrollTo(i11, getScrollY());
        }
    }

    private float releaseHorizontalGlow(float f, float f5) {
        float height = f5 / ((float) getHeight());
        float width = f / ((float) getWidth());
        float f8 = 0.0f;
        if (EdgeEffectCompat.getDistance(this.mLeftEdge) != 0.0f) {
            f8 = -EdgeEffectCompat.onPullDistance(this.mLeftEdge, -width, 1.0f - height);
        } else if (EdgeEffectCompat.getDistance(this.mRightEdge) != 0.0f) {
            f8 = EdgeEffectCompat.onPullDistance(this.mRightEdge, width, height);
        }
        return f8 * ((float) getWidth());
    }

    private void removeNonDecorViews() {
        int i2 = 0;
        while (i2 < getChildCount()) {
            if (!((LayoutParams) getChildAt(i2).getLayoutParams()).isDecor) {
                removeViewAt(i2);
                i2--;
            }
            i2++;
        }
    }

    private void requestParentDisallowInterceptTouchEvent(boolean z) {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(z);
        }
    }

    private boolean resetTouch() {
        this.mActivePointerId = -1;
        endDrag();
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        if (!this.mLeftEdge.isFinished() || !this.mRightEdge.isFinished()) {
            return true;
        }
        return false;
    }

    private void scrollToItem(int i2, boolean z, int i7, boolean z3) {
        int i8;
        ItemInfo infoForPosition = infoForPosition(i2);
        if (infoForPosition != null) {
            float clientWidth = (float) getClientWidth();
            i8 = (int) (MathUtils.clamp(infoForPosition.offset, this.mFirstOffset, this.mLastOffset) * clientWidth);
            if (seslIsDatePickerLayoutRtl()) {
                i8 = (16777216 - ((int) ((clientWidth * infoForPosition.widthFactor) + 0.5f))) - i8;
            }
        } else {
            i8 = 0;
        }
        if (z) {
            smoothScrollTo(i8, 0, i7);
            if (z3) {
                dispatchOnPageSelected(i2);
                return;
            }
            return;
        }
        if (z3) {
            dispatchOnPageSelected(i2);
        }
        completeScroll(false);
        scrollTo(i8, 0);
        pageScrolled(i8);
    }

    private boolean seslIsDatePickerLayoutRtl() {
        if (!this.mSupportLayoutDirectionForDatePicker || ViewCompat.getLayoutDirection(this) != 1) {
            return false;
        }
        return true;
    }

    private void setScrollingCacheEnabled(boolean z) {
        if (this.mScrollingCacheEnabled != z) {
            this.mScrollingCacheEnabled = z;
        }
    }

    private void sortChildDrawingOrder() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                this.mDrawingOrderedChildren.add(getChildAt(i2));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i7) {
        ItemInfo infoForChild;
        if (arrayList != null) {
            int size = arrayList.size();
            int descendantFocusability = getDescendantFocusability();
            if (descendantFocusability != 393216) {
                for (int i8 = 0; i8 < getChildCount(); i8++) {
                    View childAt = getChildAt(i8);
                    if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem) {
                        childAt.addFocusables(arrayList, i2, i7);
                    }
                }
            }
            if ((descendantFocusability == 262144 && size != arrayList.size()) || !isFocusable()) {
                return;
            }
            if ((i7 & 1) != 1 || !isInTouchMode() || isFocusableInTouchMode()) {
                arrayList.add(this);
            }
        }
    }

    public ItemInfo addNewItem(int i2, int i7) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.position = i2;
        itemInfo.object = this.mAdapter.instantiateItem((ViewGroup) this, i2);
        itemInfo.widthFactor = this.mAdapter.getPageWidth(i2);
        if (i7 < 0 || i7 >= this.mItems.size()) {
            this.mItems.add(itemInfo);
            return itemInfo;
        }
        this.mItems.add(i7, itemInfo);
        return itemInfo;
    }

    public void addOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(onAdapterChangeListener);
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(onPageChangeListener);
    }

    public void addTouchables(ArrayList<View> arrayList) {
        ItemInfo infoForChild;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (layoutParams2 != null) {
            boolean isDecorView = layoutParams2.isDecor | isDecorView(view);
            layoutParams2.isDecor = isDecorView;
            if (!this.mInLayout) {
                super.addView(view, i2, layoutParams);
            } else if (!isDecorView) {
                layoutParams2.needsMeasure = true;
                addViewInLayout(view, i2, layoutParams);
            } else {
                throw new IllegalStateException("Cannot add pager decor view during layout");
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00b6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean arrowScroll(int r5) {
        /*
            r4 = this;
            android.view.View r0 = r4.findFocus()
            r1 = 0
            if (r0 != r4) goto L_0x0009
        L_0x0007:
            r0 = r1
            goto L_0x0060
        L_0x0009:
            if (r0 == 0) goto L_0x0060
            android.view.ViewParent r2 = r0.getParent()
        L_0x000f:
            boolean r3 = r2 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x001b
            if (r2 != r4) goto L_0x0016
            goto L_0x0060
        L_0x0016:
            android.view.ViewParent r2 = r2.getParent()
            goto L_0x000f
        L_0x001b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.Class r3 = r0.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            android.view.ViewParent r0 = r0.getParent()
        L_0x002f:
            boolean r3 = r0 instanceof android.view.ViewGroup
            if (r3 == 0) goto L_0x0048
            java.lang.String r3 = " => "
            r2.append(r3)
            java.lang.Class r3 = r0.getClass()
            java.lang.String r3 = r3.getSimpleName()
            r2.append(r3)
            android.view.ViewParent r0 = r0.getParent()
            goto L_0x002f
        L_0x0048:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "arrowScroll tried to find focus based on non-child current focused view "
            r0.<init>(r3)
            java.lang.String r2 = r2.toString()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r2 = "ViewPager"
            android.util.Log.e(r2, r0)
            goto L_0x0007
        L_0x0060:
            android.view.FocusFinder r1 = android.view.FocusFinder.getInstance()
            android.view.View r1 = r1.findNextFocus(r4, r0, r5)
            r2 = 66
            r3 = 17
            if (r1 == 0) goto L_0x00b0
            if (r1 == r0) goto L_0x00b0
            if (r5 != r3) goto L_0x0090
            android.graphics.Rect r2 = r4.mTempRect
            android.graphics.Rect r2 = r4.getChildRectInPagerCoordinates(r2, r1)
            int r2 = r2.left
            android.graphics.Rect r3 = r4.mTempRect
            android.graphics.Rect r3 = r4.getChildRectInPagerCoordinates(r3, r0)
            int r3 = r3.left
            if (r0 == 0) goto L_0x008b
            if (r2 < r3) goto L_0x008b
            boolean r0 = r4.pageLeft()
            goto L_0x00c7
        L_0x008b:
            boolean r0 = r1.requestFocus()
            goto L_0x00c7
        L_0x0090:
            if (r5 != r2) goto L_0x00bc
            android.graphics.Rect r2 = r4.mTempRect
            android.graphics.Rect r2 = r4.getChildRectInPagerCoordinates(r2, r1)
            int r2 = r2.left
            android.graphics.Rect r3 = r4.mTempRect
            android.graphics.Rect r3 = r4.getChildRectInPagerCoordinates(r3, r0)
            int r3 = r3.left
            if (r0 == 0) goto L_0x00ab
            if (r2 > r3) goto L_0x00ab
            boolean r0 = r4.pageRight()
            goto L_0x00c7
        L_0x00ab:
            boolean r0 = r1.requestFocus()
            goto L_0x00c7
        L_0x00b0:
            if (r5 == r3) goto L_0x00c3
            r0 = 1
            if (r5 != r0) goto L_0x00b6
            goto L_0x00c3
        L_0x00b6:
            if (r5 == r2) goto L_0x00be
            r0 = 2
            if (r5 != r0) goto L_0x00bc
            goto L_0x00be
        L_0x00bc:
            r0 = 0
            goto L_0x00c7
        L_0x00be:
            boolean r0 = r4.pageRight()
            goto L_0x00c7
        L_0x00c3:
            boolean r0 = r4.pageLeft()
        L_0x00c7:
            if (r0 == 0) goto L_0x00d0
            int r5 = android.view.SoundEffectConstants.getContantForFocusDirection(r5)
            r4.playSoundEffect(r5)
        L_0x00d0:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.arrowScroll(int):boolean");
    }

    public boolean canScroll(View view, boolean z, int i2, int i7, int i8) {
        int i10;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i11 = i7 + scrollX;
                if (i11 >= childAt.getLeft() && i11 < childAt.getRight() && (i10 = i8 + scrollY) >= childAt.getTop() && i10 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i2, i11 - childAt.getLeft(), i10 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (!z || !view.canScrollHorizontally(-i2)) {
            return false;
        }
        return true;
    }

    public boolean canScrollHorizontally(int i2) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        if (i2 < 0) {
            if (scrollX > ((int) (((float) clientWidth) * this.mFirstOffset))) {
                return true;
            }
            return false;
        } else if (i2 <= 0 || scrollX >= ((int) (((float) clientWidth) * this.mLastOffset))) {
            return false;
        } else {
            return true;
        }
    }

    public void canSupportLayoutDirectionForDatePicker(boolean z) {
        this.mSupportLayoutDirectionForDatePicker = z;
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (!(layoutParams instanceof LayoutParams) || !super.checkLayoutParams(layoutParams)) {
            return false;
        }
        return true;
    }

    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            completeScroll(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            scrollTo(currX, currY);
            if (!pageScrolled(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void dataSetChanged() {
        boolean z;
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        if (this.mItems.size() >= (this.mOffscreenPageLimit * 2) + 1 || this.mItems.size() >= count) {
            z = false;
        } else {
            z = true;
        }
        int i2 = this.mCurItem;
        int i7 = 0;
        boolean z3 = false;
        while (i7 < this.mItems.size()) {
            ItemInfo itemInfo = this.mItems.get(i7);
            int itemPosition = this.mAdapter.getItemPosition(itemInfo.object);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i7);
                    i7--;
                    if (!z3) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z3 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, itemInfo.position, itemInfo.object);
                    int i8 = this.mCurItem;
                    if (i8 == itemInfo.position) {
                        i2 = Math.max(0, Math.min(i8, count - 1));
                    }
                } else {
                    int i10 = itemInfo.position;
                    if (i10 != itemPosition) {
                        if (i10 == this.mCurItem) {
                            i2 = itemPosition;
                        }
                        itemInfo.position = itemPosition;
                    }
                }
                z = true;
            }
            i7++;
        }
        if (z3) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z) {
            int childCount = getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i11).getLayoutParams();
                if (!layoutParams.isDecor) {
                    layoutParams.widthFactor = 0.0f;
                }
            }
            setCurrentItemInternal(i2, false, true);
            requestLayout();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ItemInfo infoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    public void draw(Canvas canvas) {
        PagerAdapter pagerAdapter;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean z = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (pagerAdapter = this.mAdapter) != null && pagerAdapter.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int save = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                if (seslIsDatePickerLayoutRtl()) {
                    canvas.translate((float) (getPaddingTop() + (-height)), ((-(this.mLastOffset + 1.0f)) * ((float) width)) + 1.6777216E7f);
                } else {
                    canvas.translate((float) (getPaddingTop() + (-height)), this.mFirstOffset * ((float) width));
                }
                this.mLeftEdge.setSize(height, width);
                z = this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(save);
            }
            if (!this.mRightEdge.isFinished()) {
                int save2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                if (seslIsDatePickerLayoutRtl()) {
                    canvas.translate((float) (-getPaddingTop()), (this.mFirstOffset * ((float) width2)) - 1.6777216E7f);
                } else {
                    canvas.translate((float) (-getPaddingTop()), (-(this.mLastOffset + 1.0f)) * ((float) width2));
                }
                this.mRightEdge.setSize(height2, width2);
                z |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(save2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 21) {
            if (keyCode != 22) {
                if (keyCode != 61) {
                    return false;
                }
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
                return false;
            } else if (keyEvent.hasModifiers(2)) {
                return pageRight();
            } else {
                return arrowScroll(66);
            }
        } else if (keyEvent.hasModifiers(2)) {
            return pageLeft();
        } else {
            return arrowScroll(17);
        }
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public PagerAdapter getAdapter() {
        return this.mAdapter;
    }

    public int getChildDrawingOrder(int i2, int i7) {
        if (this.mDrawingOrder == 2) {
            i7 = (i2 - 1) - i7;
        }
        return ((LayoutParams) this.mDrawingOrderedChildren.get(i7).getLayoutParams()).childIndex;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.view.ViewParent] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.viewpager.widget.ViewPager.ItemInfo infoForAnyChild(android.view.View r2) {
        /*
            r1 = this;
        L_0x0000:
            android.view.ViewParent r0 = r2.getParent()
            if (r0 == r1) goto L_0x0010
            boolean r2 = r0 instanceof android.view.View
            if (r2 != 0) goto L_0x000c
            r1 = 0
            return r1
        L_0x000c:
            r2 = r0
            android.view.View r2 = (android.view.View) r2
            goto L_0x0000
        L_0x0010:
            androidx.viewpager.widget.ViewPager$ItemInfo r1 = r1.infoForChild(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.infoForAnyChild(android.view.View):androidx.viewpager.widget.ViewPager$ItemInfo");
    }

    public ItemInfo infoForChild(View view) {
        for (int i2 = 0; i2 < this.mItems.size(); i2++) {
            ItemInfo itemInfo = this.mItems.get(i2);
            if (this.mAdapter.isViewFromObject(view, itemInfo.object)) {
                return itemInfo;
            }
        }
        return null;
    }

    public ItemInfo infoForPosition(int i2) {
        for (int i7 = 0; i7 < this.mItems.size(); i7++) {
            ItemInfo itemInfo = this.mItems.get(i7);
            if (itemInfo.position == i2) {
                return itemInfo;
            }
        }
        return null;
    }

    public void initViewPager(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f);
        this.mCloseEnough = (int) (2.0f * f);
        this.mDefaultGutterSize = (int) (f * 16.0f);
        ViewCompat.setAccessibilityDelegate(this, new MyAccessibilityDelegate());
        if (ViewCompat.getImportantForAccessibility(this) == 0) {
            ViewCompat.setImportantForAccessibility(this, 1);
        }
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            private final Rect mTempRect = new Rect();

            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                WindowInsetsCompat onApplyWindowInsets = ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                if (onApplyWindowInsets.isConsumed()) {
                    return onApplyWindowInsets;
                }
                Rect rect = this.mTempRect;
                rect.left = onApplyWindowInsets.getSystemWindowInsetLeft();
                rect.top = onApplyWindowInsets.getSystemWindowInsetTop();
                rect.right = onApplyWindowInsets.getSystemWindowInsetRight();
                rect.bottom = onApplyWindowInsets.getSystemWindowInsetBottom();
                int childCount = ViewPager.this.getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    WindowInsetsCompat dispatchApplyWindowInsets = ViewCompat.dispatchApplyWindowInsets(ViewPager.this.getChildAt(i2), onApplyWindowInsets);
                    rect.left = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetLeft(), rect.left);
                    rect.top = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetTop(), rect.top);
                    rect.right = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetRight(), rect.right);
                    rect.bottom = Math.min(dispatchApplyWindowInsets.getSystemWindowInsetBottom(), rect.bottom);
                }
                return new WindowInsetsCompat.Builder(onApplyWindowInsets).setSystemWindowInsets(Insets.of(rect)).build();
            }
        });
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        int i2;
        float f;
        int i7;
        float f5;
        super.onDraw(canvas);
        if (this.mPageMargin > 0 && this.mMarginDrawable != null && this.mItems.size() > 0 && this.mAdapter != null) {
            int scrollX = getScrollX();
            int width = getWidth();
            float f8 = (float) width;
            float f10 = ((float) this.mPageMargin) / f8;
            int i8 = 0;
            ItemInfo itemInfo = this.mItems.get(0);
            float f11 = itemInfo.offset;
            int size = this.mItems.size();
            int i10 = itemInfo.position;
            int i11 = this.mItems.get(size - 1).position;
            while (i10 < i11) {
                while (true) {
                    i2 = itemInfo.position;
                    if (i10 > i2 && i8 < size) {
                        i8++;
                        itemInfo = this.mItems.get(i8);
                    }
                }
                if (i10 == i2) {
                    if (seslIsDatePickerLayoutRtl()) {
                        f = 1.6777216E7f - itemInfo.offset;
                    } else {
                        f = (itemInfo.offset + itemInfo.widthFactor) * f8;
                    }
                    f11 = itemInfo.offset + itemInfo.widthFactor + f10;
                } else {
                    float pageWidth = this.mAdapter.getPageWidth(i10);
                    if (seslIsDatePickerLayoutRtl()) {
                        f5 = 1.6777216E7f - f11;
                    } else {
                        f5 = (f11 + pageWidth) * f8;
                    }
                    f11 = pageWidth + f10 + f11;
                }
                if (((float) this.mPageMargin) + f > ((float) scrollX)) {
                    i7 = scrollX;
                    this.mMarginDrawable.setBounds(Math.round(f), this.mTopPageBounds, Math.round(((float) this.mPageMargin) + f), this.mBottomPageBounds);
                    this.mMarginDrawable.draw(canvas);
                } else {
                    Canvas canvas2 = canvas;
                    i7 = scrollX;
                }
                if (f <= ((float) (i7 + width))) {
                    i10++;
                    scrollX = i7;
                } else {
                    return;
                }
            }
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mIsMouseWheelEventSupport && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue > 0.0f) {
                setCurrentItem(this.mCurItem - 1, true);
                return true;
            } else if (axisValue < 0.0f) {
                setCurrentItem(this.mCurItem + 1, true);
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int i2;
        int findPointerIndex;
        float f;
        int action = motionEvent.getAction() & ScoverState.TYPE_NFC_SMART_COVER;
        if (action == 3 || action == 1) {
            resetTouch();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x9 = motionEvent.getX();
            this.mInitialMotionX = x9;
            this.mLastMotionX = x9;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            if (this.mUsePagingTouchSlopForStylus) {
                if (motionEvent.isFromSource(16386)) {
                    i2 = this.mPagingTouchSlop;
                } else {
                    i2 = this.mScaledTouchSlop;
                }
                this.mTouchSlop = i2;
            }
            this.mScroller.computeScrollOffset();
            if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                requestParentDisallowInterceptTouchEvent(true);
                setScrollState(1);
            } else if (EdgeEffectCompat.getDistance(this.mLeftEdge) == 0.0f && EdgeEffectCompat.getDistance(this.mRightEdge) == 0.0f) {
                completeScroll(false);
                this.mIsBeingDragged = false;
            } else {
                this.mIsBeingDragged = true;
                setScrollState(1);
                if (EdgeEffectCompat.getDistance(this.mLeftEdge) != 0.0f) {
                    EdgeEffectCompat.onPullDistance(this.mLeftEdge, 0.0f, 1.0f - (this.mLastMotionY / ((float) getHeight())));
                }
                if (EdgeEffectCompat.getDistance(this.mRightEdge) != 0.0f) {
                    EdgeEffectCompat.onPullDistance(this.mRightEdge, 0.0f, this.mLastMotionY / ((float) getHeight()));
                }
            }
        } else if (action == 2) {
            int i7 = this.mActivePointerId;
            if (!(i7 == -1 || (findPointerIndex = motionEvent.findPointerIndex(i7)) == -1)) {
                float x10 = motionEvent.getX(findPointerIndex);
                float f5 = x10 - this.mLastMotionX;
                float abs = Math.abs(f5);
                float y8 = motionEvent.getY(findPointerIndex);
                float abs2 = Math.abs(y8 - this.mInitialMotionY);
                int i8 = (f5 > 0.0f ? 1 : (f5 == 0.0f ? 0 : -1));
                if (i8 != 0 && !isGutterDrag(this.mLastMotionX, f5)) {
                    if (canScroll(this, false, (int) f5, (int) x10, (int) y8)) {
                        this.mLastMotionX = x10;
                        this.mLastMotionY = y8;
                        this.mIsUnableToDrag = true;
                        return false;
                    }
                }
                int i10 = this.mTouchSlop;
                if (abs > ((float) i10) && abs * this.mTouchSlopRatio > abs2) {
                    this.mIsBeingDragged = true;
                    requestParentDisallowInterceptTouchEvent(true);
                    setScrollState(1);
                    if (i8 > 0) {
                        f = this.mInitialMotionX + ((float) this.mTouchSlop);
                    } else {
                        f = this.mInitialMotionX - ((float) this.mTouchSlop);
                    }
                    this.mLastMotionX = f;
                    this.mLastMotionY = y8;
                    setScrollingCacheEnabled(true);
                } else if (abs2 > ((float) i10)) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && performDrag(x10, y8)) {
                    ViewCompat.postInvalidateOnAnimation(this);
                }
            }
        } else if (action == 6) {
            onSecondaryPointerUp(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.getChildCount()
            int r2 = r22 - r20
            int r3 = r23 - r21
            int r4 = r0.getPaddingLeft()
            int r5 = r0.getPaddingTop()
            int r6 = r0.getPaddingRight()
            int r7 = r0.getPaddingBottom()
            int r8 = r0.getScrollX()
            r10 = 0
            r11 = 0
        L_0x0020:
            r12 = 8
            if (r10 >= r1) goto L_0x00b8
            android.view.View r13 = r0.getChildAt(r10)
            int r14 = r13.getVisibility()
            if (r14 == r12) goto L_0x00b4
            android.view.ViewGroup$LayoutParams r12 = r13.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r12 = (androidx.viewpager.widget.ViewPager.LayoutParams) r12
            boolean r14 = r12.isDecor
            if (r14 == 0) goto L_0x00b4
            int r12 = r12.gravity
            r14 = r12 & 7
            r12 = r12 & 112(0x70, float:1.57E-43)
            r15 = 1
            if (r14 == r15) goto L_0x0061
            r15 = 3
            if (r14 == r15) goto L_0x005b
            r15 = 5
            if (r14 == r15) goto L_0x0049
            r14 = r4
            goto L_0x006e
        L_0x0049:
            int r14 = r2 - r6
            int r15 = r13.getMeasuredWidth()
            int r14 = r14 - r15
            int r15 = r13.getMeasuredWidth()
            int r6 = r6 + r15
        L_0x0055:
            r17 = r14
            r14 = r4
            r4 = r17
            goto L_0x006e
        L_0x005b:
            int r14 = r13.getMeasuredWidth()
            int r14 = r14 + r4
            goto L_0x006e
        L_0x0061:
            int r14 = r13.getMeasuredWidth()
            int r14 = r2 - r14
            int r14 = r14 / 2
            int r14 = java.lang.Math.max(r14, r4)
            goto L_0x0055
        L_0x006e:
            r15 = 16
            if (r12 == r15) goto L_0x0094
            r15 = 48
            if (r12 == r15) goto L_0x008e
            r15 = 80
            if (r12 == r15) goto L_0x007c
            r12 = r5
            goto L_0x00a1
        L_0x007c:
            int r12 = r3 - r7
            int r15 = r13.getMeasuredHeight()
            int r12 = r12 - r15
            int r15 = r13.getMeasuredHeight()
            int r7 = r7 + r15
        L_0x0088:
            r17 = r12
            r12 = r5
            r5 = r17
            goto L_0x00a1
        L_0x008e:
            int r12 = r13.getMeasuredHeight()
            int r12 = r12 + r5
            goto L_0x00a1
        L_0x0094:
            int r12 = r13.getMeasuredHeight()
            int r12 = r3 - r12
            int r12 = r12 / 2
            int r12 = java.lang.Math.max(r12, r5)
            goto L_0x0088
        L_0x00a1:
            int r4 = r4 + r8
            int r15 = r13.getMeasuredWidth()
            int r15 = r15 + r4
            int r16 = r13.getMeasuredHeight()
            int r9 = r16 + r5
            r13.layout(r4, r5, r15, r9)
            int r11 = r11 + 1
            r5 = r12
            r4 = r14
        L_0x00b4:
            int r10 = r10 + 1
            goto L_0x0020
        L_0x00b8:
            int r2 = r2 - r4
            int r2 = r2 - r6
            r8 = 0
        L_0x00bb:
            if (r8 >= r1) goto L_0x0119
            android.view.View r9 = r0.getChildAt(r8)
            int r10 = r9.getVisibility()
            if (r10 == r12) goto L_0x0116
            android.view.ViewGroup$LayoutParams r10 = r9.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r10 = (androidx.viewpager.widget.ViewPager.LayoutParams) r10
            boolean r13 = r10.isDecor
            if (r13 != 0) goto L_0x0116
            androidx.viewpager.widget.ViewPager$ItemInfo r13 = r0.infoForChild(r9)
            if (r13 == 0) goto L_0x0116
            float r14 = (float) r2
            float r13 = r13.offset
            float r13 = r13 * r14
            int r13 = (int) r13
            boolean r15 = r0.seslIsDatePickerLayoutRtl()
            if (r15 == 0) goto L_0x00ec
            r15 = 16777216(0x1000000, float:2.3509887E-38)
            int r15 = r15 - r6
            int r15 = r15 - r13
            int r13 = r9.getMeasuredWidth()
            int r15 = r15 - r13
            goto L_0x00ee
        L_0x00ec:
            int r15 = r4 + r13
        L_0x00ee:
            boolean r13 = r10.needsMeasure
            if (r13 == 0) goto L_0x0109
            r13 = 0
            r10.needsMeasure = r13
            float r10 = r10.widthFactor
            float r14 = r14 * r10
            int r10 = (int) r14
            r13 = 1073741824(0x40000000, float:2.0)
            int r10 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r13)
            int r14 = r3 - r5
            int r14 = r14 - r7
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r13)
            r9.measure(r10, r13)
        L_0x0109:
            int r10 = r9.getMeasuredWidth()
            int r10 = r10 + r15
            int r13 = r9.getMeasuredHeight()
            int r13 = r13 + r5
            r9.layout(r15, r5, r10, r13)
        L_0x0116:
            int r8 = r8 + 1
            goto L_0x00bb
        L_0x0119:
            r0.mTopPageBounds = r5
            int r3 = r3 - r7
            r0.mBottomPageBounds = r3
            r0.mDecorChildCount = r11
            boolean r1 = r0.mFirstLayout
            if (r1 != 0) goto L_0x012b
            boolean r1 = r0.mIsChangedConfiguration
            if (r1 == 0) goto L_0x0129
            goto L_0x012b
        L_0x0129:
            r13 = 0
            goto L_0x0133
        L_0x012b:
            int r1 = r0.mCurItem
            r13 = 0
            r0.scrollToItem(r1, r13, r13, r13)
            r0.mIsChangedConfiguration = r13
        L_0x0133:
            r0.mFirstLayout = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i2, int i7) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        boolean z;
        int i8;
        setMeasuredDimension(View.getDefaultSize(0, i2), View.getDefaultSize(0, i7));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i10 = 0;
        while (true) {
            boolean z3 = true;
            int i11 = 1073741824;
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            if (!(childAt.getVisibility() == 8 || (layoutParams2 = (LayoutParams) childAt.getLayoutParams()) == null || !layoutParams2.isDecor)) {
                int i12 = layoutParams2.gravity;
                int i13 = i12 & 7;
                int i14 = i12 & 112;
                if (i14 == 48 || i14 == 80) {
                    z = true;
                } else {
                    z = false;
                }
                if (!(i13 == 3 || i13 == 5)) {
                    z3 = false;
                }
                int i15 = Integer.MIN_VALUE;
                if (z) {
                    i8 = Integer.MIN_VALUE;
                    i15 = 1073741824;
                } else if (z3) {
                    i8 = 1073741824;
                } else {
                    i8 = Integer.MIN_VALUE;
                }
                int i16 = layoutParams2.width;
                if (i16 != -2) {
                    if (i16 == -1) {
                        i16 = paddingLeft;
                    }
                    i15 = 1073741824;
                } else {
                    i16 = paddingLeft;
                }
                int i17 = layoutParams2.height;
                if (i17 == -2) {
                    i17 = measuredHeight;
                    i11 = i8;
                } else if (i17 == -1) {
                    i17 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i16, i15), View.MeasureSpec.makeMeasureSpec(i17, i11));
                if (z) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z3) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i10++;
        }
        View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i18 = 0; i18 < childCount2; i18++) {
            View childAt2 = getChildAt(i18);
            if (!(childAt2.getVisibility() == 8 || (layoutParams = (LayoutParams) childAt2.getLayoutParams()) == null || layoutParams.isDecor)) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (((float) paddingLeft) * layoutParams.widthFactor), 1073741824), makeMeasureSpec);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onPageScrolled(int r12, float r13, int r14) {
        /*
            r11 = this;
            int r0 = r11.mDecorChildCount
            r1 = 1
            if (r0 <= 0) goto L_0x006b
            int r0 = r11.getScrollX()
            int r2 = r11.getPaddingLeft()
            int r3 = r11.getPaddingRight()
            int r4 = r11.getWidth()
            int r5 = r11.getChildCount()
            r6 = 0
        L_0x001a:
            if (r6 >= r5) goto L_0x006b
            android.view.View r7 = r11.getChildAt(r6)
            android.view.ViewGroup$LayoutParams r8 = r7.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r8 = (androidx.viewpager.widget.ViewPager.LayoutParams) r8
            boolean r9 = r8.isDecor
            if (r9 != 0) goto L_0x002b
            goto L_0x0068
        L_0x002b:
            int r8 = r8.gravity
            r8 = r8 & 7
            if (r8 == r1) goto L_0x004f
            r9 = 3
            if (r8 == r9) goto L_0x0049
            r9 = 5
            if (r8 == r9) goto L_0x0039
            r8 = r2
            goto L_0x005c
        L_0x0039:
            int r8 = r4 - r3
            int r9 = r7.getMeasuredWidth()
            int r8 = r8 - r9
            int r9 = r7.getMeasuredWidth()
            int r3 = r3 + r9
        L_0x0045:
            r10 = r8
            r8 = r2
            r2 = r10
            goto L_0x005c
        L_0x0049:
            int r8 = r7.getWidth()
            int r8 = r8 + r2
            goto L_0x005c
        L_0x004f:
            int r8 = r7.getMeasuredWidth()
            int r8 = r4 - r8
            int r8 = r8 / 2
            int r8 = java.lang.Math.max(r8, r2)
            goto L_0x0045
        L_0x005c:
            int r2 = r2 + r0
            int r9 = r7.getLeft()
            int r2 = r2 - r9
            if (r2 == 0) goto L_0x0067
            r7.offsetLeftAndRight(r2)
        L_0x0067:
            r2 = r8
        L_0x0068:
            int r6 = r6 + 1
            goto L_0x001a
        L_0x006b:
            r11.dispatchOnPageScrolled(r12, r13, r14)
            r11.mCalledSuper = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    public boolean onRequestFocusInDescendants(int i2, Rect rect) {
        int i7;
        int i8;
        int i10;
        ItemInfo infoForChild;
        int childCount = getChildCount();
        if ((i2 & 2) != 0) {
            i8 = childCount;
            i10 = 0;
            i7 = 1;
        } else {
            i10 = childCount - 1;
            i8 = -1;
            i7 = -1;
        }
        while (i10 != i8) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() == 0 && (infoForChild = infoForChild(childAt)) != null && infoForChild.position == this.mCurItem && childAt.requestFocus(i2, rect)) {
                return true;
            }
            i10 += i7;
        }
        return false;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            pagerAdapter.restoreState(savedState.adapterState, savedState.loader);
            setCurrentItemInternal(savedState.position, false, true);
            return;
        }
        this.mRestoredCurItem = savedState.position;
        this.mRestoredAdapterState = savedState.adapterState;
        this.mRestoredClassLoader = savedState.loader;
    }

    public void onRtlPropertiesChanged(int i2) {
        int i7;
        super.onRtlPropertiesChanged(i2);
        if (this.mSupportLayoutDirectionForDatePicker) {
            if (i2 == 0) {
                i7 = -1;
            } else {
                i7 = 1;
            }
            this.mLeftIncr = i7;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = this.mCurItem;
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter != null) {
            savedState.adapterState = pagerAdapter.saveState();
        }
        return savedState;
    }

    public void onSizeChanged(int i2, int i7, int i8, int i10) {
        super.onSizeChanged(i2, i7, i8, i10);
        if (i2 != i8) {
            int i11 = this.mPageMargin;
            recomputeScrollPosition(i2, i8, i11, i11);
            if (this.mPageMargin > 0) {
                setCurrentItemInternal(this.mCurItem, false, true, 0);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        PagerAdapter pagerAdapter;
        float f;
        float f5;
        if (this.mFakeDragging) {
            return true;
        }
        boolean z = false;
        if ((motionEvent.getAction() == 0 && motionEvent.getEdgeFlags() != 0) || (pagerAdapter = this.mAdapter) == null || pagerAdapter.getCount() == 0) {
            return false;
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int action = motionEvent.getAction() & ScoverState.TYPE_NFC_SMART_COVER;
        if (action == 0) {
            this.mScroller.abortAnimation();
            this.mPopulatePending = false;
            populate();
            float x9 = motionEvent.getX();
            this.mInitialMotionX = x9;
            this.mLastMotionX = x9;
            float y = motionEvent.getY();
            this.mInitialMotionY = y;
            this.mLastMotionY = y;
            this.mActivePointerId = motionEvent.getPointerId(0);
        } else if (action != 1) {
            if (action == 2) {
                if (!this.mIsBeingDragged) {
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex == -1) {
                        z = resetTouch();
                    } else {
                        float x10 = motionEvent.getX(findPointerIndex);
                        float abs = Math.abs(x10 - this.mLastMotionX);
                        float y8 = motionEvent.getY(findPointerIndex);
                        float abs2 = Math.abs(y8 - this.mLastMotionY);
                        if (abs > ((float) this.mTouchSlop) && abs > abs2) {
                            this.mIsBeingDragged = true;
                            requestParentDisallowInterceptTouchEvent(true);
                            float f8 = this.mInitialMotionX;
                            if (x10 - f8 > 0.0f) {
                                f5 = f8 + ((float) this.mTouchSlop);
                            } else {
                                f5 = f8 - ((float) this.mTouchSlop);
                            }
                            this.mLastMotionX = f5;
                            this.mLastMotionY = y8;
                            setScrollState(1);
                            setScrollingCacheEnabled(true);
                            ViewParent parent = getParent();
                            if (parent != null) {
                                parent.requestDisallowInterceptTouchEvent(true);
                            }
                        }
                    }
                }
                if (this.mIsBeingDragged) {
                    int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
                    z = findPointerIndex2 == -1 ? resetTouch() : performDrag(motionEvent.getX(findPointerIndex2), motionEvent.getY(findPointerIndex2));
                }
            } else if (action != 3) {
                if (action == 5) {
                    int actionIndex = motionEvent.getActionIndex();
                    this.mLastMotionX = motionEvent.getX(actionIndex);
                    this.mActivePointerId = motionEvent.getPointerId(actionIndex);
                } else if (action == 6) {
                    onSecondaryPointerUp(motionEvent);
                    int findPointerIndex3 = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex3 == -1) {
                        z = resetTouch();
                    } else {
                        this.mLastMotionX = motionEvent.getX(findPointerIndex3);
                    }
                }
            } else if (this.mIsBeingDragged) {
                scrollToItem(this.mCurItem, true, 0, false);
                z = resetTouch();
            }
        } else if (this.mIsBeingDragged) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            float clientWidth = (float) getClientWidth();
            float scrollStart = ((float) getScrollStart()) / clientWidth;
            ItemInfo infoForCurrentScrollPosition = infoForCurrentScrollPosition();
            float f10 = ((float) this.mPageMargin) / clientWidth;
            int i2 = infoForCurrentScrollPosition.position;
            if (seslIsDatePickerLayoutRtl()) {
                f = (infoForCurrentScrollPosition.offset - scrollStart) / (infoForCurrentScrollPosition.widthFactor + f10);
            } else {
                f = (scrollStart - infoForCurrentScrollPosition.offset) / (infoForCurrentScrollPosition.widthFactor + f10);
            }
            int findPointerIndex4 = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex4 == -1) {
                z = resetTouch();
            } else {
                int determineTargetPage = determineTargetPage(i2, f, xVelocity, (int) (motionEvent.getX(findPointerIndex4) - this.mInitialMotionX));
                setCurrentItemInternal(determineTargetPage, true, true, xVelocity);
                boolean resetTouch = resetTouch();
                if (determineTargetPage == i2 && resetTouch) {
                    if (EdgeEffectCompat.getDistance(this.mRightEdge) != 0.0f) {
                        this.mRightEdge.onAbsorb(-xVelocity);
                    } else if (EdgeEffectCompat.getDistance(this.mLeftEdge) != 0.0f) {
                        this.mLeftEdge.onAbsorb(xVelocity);
                    }
                }
                z = resetTouch;
            }
        }
        if (z) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
        return true;
    }

    public boolean pageLeft() {
        int i2 = this.mCurItem;
        if (i2 <= 0) {
            return false;
        }
        setCurrentItem(i2 + this.mLeftIncr, true);
        return true;
    }

    public boolean pageRight() {
        PagerAdapter pagerAdapter = this.mAdapter;
        if (pagerAdapter == null || this.mCurItem >= pagerAdapter.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem - this.mLeftIncr, true);
        return true;
    }

    public void populate() {
        populate(this.mCurItem);
    }

    public void removeOnAdapterChangeListener(OnAdapterChangeListener onAdapterChangeListener) {
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(onAdapterChangeListener);
        }
    }

    public void removeOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        List<OnPageChangeListener> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(onPageChangeListener);
        }
    }

    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public void seslSetConfigurationChanged(boolean z) {
        this.mIsChangedConfiguration = z;
    }

    public void seslSetSupportedMouseWheelEvent(boolean z) {
        this.mIsMouseWheelEventSupport = z;
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        PagerAdapter pagerAdapter2 = this.mAdapter;
        if (pagerAdapter2 != null) {
            pagerAdapter2.setViewPagerObserver((DataSetObserver) null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i2 = 0; i2 < this.mItems.size(); i2++) {
                ItemInfo itemInfo = this.mItems.get(i2);
                this.mAdapter.destroyItem((ViewGroup) this, itemInfo.position, itemInfo.object);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            removeNonDecorViews();
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        PagerAdapter pagerAdapter3 = this.mAdapter;
        this.mAdapter = pagerAdapter;
        this.mExpectedAdapterCount = 0;
        if (pagerAdapter != null) {
            if (this.mObserver == null) {
                this.mObserver = new PagerObserver();
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (!z) {
                populate();
            } else {
                requestLayout();
            }
        }
        List<OnAdapterChangeListener> list = this.mAdapterChangeListeners;
        if (list != null && !list.isEmpty()) {
            int size = this.mAdapterChangeListeners.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((a) this.mAdapterChangeListeners.get(i7)).a(this, pagerAdapter3, pagerAdapter);
            }
        }
    }

    public void setCurrentItem(int i2) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i2, !this.mFirstLayout, false);
    }

    public void setCurrentItemInternal(int i2, boolean z, boolean z3) {
        setCurrentItemInternal(i2, z, z3, 0);
    }

    public void setDragInGutterEnabled(boolean z) {
        this.mDragInGutterEnabled = z;
    }

    public void setOffscreenPageLimit(int i2) {
        if (i2 < 1) {
            Log.w("ViewPager", "Requested offscreen page limit " + i2 + " too small; defaulting to 1");
            i2 = 1;
        }
        if (i2 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i2;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }

    public void setPageMargin(int i2) {
        int i7 = this.mPageMargin;
        this.mPageMargin = i2;
        int width = getWidth();
        recomputeScrollPosition(width, width, i2, i7);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setScrollState(int i2) {
        if (this.mScrollState != i2) {
            this.mScrollState = i2;
            dispatchOnScrollStateChanged(i2);
        }
    }

    public void smoothScrollTo(int i2, int i7, int i8) {
        int scrollX;
        int i10;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            if (this.mIsScrollStarted) {
                scrollX = this.mScroller.getCurrX();
            } else {
                scrollX = this.mScroller.getStartX();
            }
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        }
        int i11 = scrollX;
        int scrollY = getScrollY();
        int i12 = i2 - i11;
        int i13 = i7 - scrollY;
        if (i12 == 0 && i13 == 0) {
            completeScroll(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i14 = clientWidth / 2;
        float f = (float) clientWidth;
        float f5 = (float) i14;
        float distanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i12)) * 1.0f) / f)) * f5) + f5;
        int abs = Math.abs(i8);
        if (abs > 0) {
            i10 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i10 = (int) (((((float) Math.abs(i12)) / ((this.mAdapter.getPageWidth(this.mCurItem) * f) + ((float) this.mPageMargin))) + 1.0f) * 100.0f);
        }
        int min = Math.min(i10, 600);
        this.mIsScrollStarted = false;
        Scroller scroller2 = this.mScroller;
        if (scroller2 != null) {
            scroller2.startScroll(i11, scrollY, i12, i13, min);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public boolean verifyDrawable(Drawable drawable) {
        if (super.verifyDrawable(drawable) || drawable == this.mMarginDrawable) {
            return true;
        }
        return false;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends ViewGroup.LayoutParams {
        int childIndex;
        public int gravity;
        public boolean isDecor;
        boolean needsMeasure;
        int position;
        float widthFactor = 0.0f;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, ViewPager.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInteger(0, 48);
            obtainStyledAttributes.recycle();
        }
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        if (r10 == r11) goto L_0x0074;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void populate(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r0.mCurItem
            r4 = 2
            if (r2 == r1) goto L_0x001b
            boolean r5 = r0.mSupportLayoutDirectionForDatePicker
            if (r5 == 0) goto L_0x0014
            if (r2 >= r1) goto L_0x0012
            r4 = 66
            goto L_0x0014
        L_0x0012:
            r4 = 17
        L_0x0014:
            androidx.viewpager.widget.ViewPager$ItemInfo r2 = r0.infoForPosition(r2)
            r0.mCurItem = r1
            goto L_0x001c
        L_0x001b:
            r2 = 0
        L_0x001c:
            androidx.viewpager.widget.PagerAdapter r1 = r0.mAdapter
            if (r1 != 0) goto L_0x0024
            r0.sortChildDrawingOrder()
            return
        L_0x0024:
            boolean r1 = r0.mPopulatePending
            if (r1 == 0) goto L_0x002c
            r0.sortChildDrawingOrder()
            return
        L_0x002c:
            android.os.IBinder r1 = r0.getWindowToken()
            if (r1 != 0) goto L_0x0034
            goto L_0x0222
        L_0x0034:
            androidx.viewpager.widget.PagerAdapter r1 = r0.mAdapter
            r1.startUpdate((android.view.ViewGroup) r0)
            int r1 = r0.mOffscreenPageLimit
            int r5 = r0.mCurItem
            int r5 = r5 - r1
            r6 = 0
            int r5 = java.lang.Math.max(r6, r5)
            androidx.viewpager.widget.PagerAdapter r7 = r0.mAdapter
            int r7 = r7.getCount()
            int r8 = r7 + -1
            int r9 = r0.mCurItem
            int r9 = r9 + r1
            int r1 = java.lang.Math.min(r8, r9)
            int r8 = r0.mExpectedAdapterCount
            if (r7 != r8) goto L_0x0223
            r8 = r6
        L_0x0057:
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r9 = r0.mItems
            int r9 = r9.size()
            if (r8 >= r9) goto L_0x0073
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r9 = r0.mItems
            java.lang.Object r9 = r9.get(r8)
            androidx.viewpager.widget.ViewPager$ItemInfo r9 = (androidx.viewpager.widget.ViewPager.ItemInfo) r9
            int r10 = r9.position
            int r11 = r0.mCurItem
            if (r10 < r11) goto L_0x0070
            if (r10 != r11) goto L_0x0073
            goto L_0x0074
        L_0x0070:
            int r8 = r8 + 1
            goto L_0x0057
        L_0x0073:
            r9 = 0
        L_0x0074:
            if (r9 != 0) goto L_0x007e
            if (r7 <= 0) goto L_0x007e
            int r9 = r0.mCurItem
            androidx.viewpager.widget.ViewPager$ItemInfo r9 = r0.addNewItem(r9, r8)
        L_0x007e:
            if (r9 == 0) goto L_0x01af
            int r11 = r8 + -1
            if (r11 < 0) goto L_0x008d
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r12 = r0.mItems
            java.lang.Object r12 = r12.get(r11)
            androidx.viewpager.widget.ViewPager$ItemInfo r12 = (androidx.viewpager.widget.ViewPager.ItemInfo) r12
            goto L_0x008e
        L_0x008d:
            r12 = 0
        L_0x008e:
            int r13 = r0.getClientWidth()
            r14 = 1073741824(0x40000000, float:2.0)
            if (r13 > 0) goto L_0x0098
            r3 = 0
            goto L_0x00a4
        L_0x0098:
            float r15 = r9.widthFactor
            float r15 = r14 - r15
            int r3 = r0.getPaddingLeft()
            float r3 = (float) r3
            float r6 = (float) r13
            float r3 = r3 / r6
            float r3 = r3 + r15
        L_0x00a4:
            int r6 = r0.mCurItem
            int r6 = r6 + -1
            r15 = 0
        L_0x00a9:
            if (r6 < 0) goto L_0x00b3
            int r16 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r16 < 0) goto L_0x00de
            if (r6 >= r5) goto L_0x00de
            if (r12 != 0) goto L_0x00b6
        L_0x00b3:
            r16 = 0
            goto L_0x010f
        L_0x00b6:
            r16 = 0
            int r10 = r12.position
            if (r6 != r10) goto L_0x010c
            boolean r10 = r12.scrolling
            if (r10 != 0) goto L_0x010c
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r10 = r0.mItems
            r10.remove(r11)
            androidx.viewpager.widget.PagerAdapter r10 = r0.mAdapter
            java.lang.Object r12 = r12.object
            r10.destroyItem((android.view.ViewGroup) r0, (int) r6, (java.lang.Object) r12)
            int r11 = r11 + -1
            int r8 = r8 + -1
            if (r11 < 0) goto L_0x00db
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r10 = r0.mItems
            java.lang.Object r10 = r10.get(r11)
            androidx.viewpager.widget.ViewPager$ItemInfo r10 = (androidx.viewpager.widget.ViewPager.ItemInfo) r10
            goto L_0x00dc
        L_0x00db:
            r10 = 0
        L_0x00dc:
            r12 = r10
            goto L_0x010c
        L_0x00de:
            r16 = 0
            if (r12 == 0) goto L_0x00f6
            int r10 = r12.position
            if (r6 != r10) goto L_0x00f6
            float r10 = r12.widthFactor
            float r15 = r15 + r10
            int r11 = r11 + -1
            if (r11 < 0) goto L_0x00db
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r10 = r0.mItems
            java.lang.Object r10 = r10.get(r11)
            androidx.viewpager.widget.ViewPager$ItemInfo r10 = (androidx.viewpager.widget.ViewPager.ItemInfo) r10
            goto L_0x00dc
        L_0x00f6:
            int r10 = r11 + 1
            androidx.viewpager.widget.ViewPager$ItemInfo r10 = r0.addNewItem(r6, r10)
            float r10 = r10.widthFactor
            float r15 = r15 + r10
            int r8 = r8 + 1
            if (r11 < 0) goto L_0x00db
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r10 = r0.mItems
            java.lang.Object r10 = r10.get(r11)
            androidx.viewpager.widget.ViewPager$ItemInfo r10 = (androidx.viewpager.widget.ViewPager.ItemInfo) r10
            goto L_0x00dc
        L_0x010c:
            int r6 = r6 + -1
            goto L_0x00a9
        L_0x010f:
            float r3 = r9.widthFactor
            int r5 = r8 + 1
            int r6 = (r3 > r14 ? 1 : (r3 == r14 ? 0 : -1))
            if (r6 >= 0) goto L_0x01a2
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0128
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            java.lang.Object r6 = r6.get(r5)
            androidx.viewpager.widget.ViewPager$ItemInfo r6 = (androidx.viewpager.widget.ViewPager.ItemInfo) r6
            goto L_0x0129
        L_0x0128:
            r6 = 0
        L_0x0129:
            if (r13 > 0) goto L_0x012e
            r10 = r16
            goto L_0x0136
        L_0x012e:
            int r10 = r0.getPaddingRight()
            float r10 = (float) r10
            float r11 = (float) r13
            float r10 = r10 / r11
            float r10 = r10 + r14
        L_0x0136:
            int r11 = r0.mCurItem
        L_0x0138:
            int r11 = r11 + 1
            if (r11 >= r7) goto L_0x01a2
            int r12 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x016c
            if (r11 <= r1) goto L_0x016c
            if (r6 != 0) goto L_0x0145
            goto L_0x01a2
        L_0x0145:
            int r12 = r6.position
            if (r11 != r12) goto L_0x01a1
            boolean r12 = r6.scrolling
            if (r12 != 0) goto L_0x01a1
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r12 = r0.mItems
            r12.remove(r5)
            androidx.viewpager.widget.PagerAdapter r12 = r0.mAdapter
            java.lang.Object r6 = r6.object
            r12.destroyItem((android.view.ViewGroup) r0, (int) r11, (java.lang.Object) r6)
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x016a
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            java.lang.Object r6 = r6.get(r5)
            androidx.viewpager.widget.ViewPager$ItemInfo r6 = (androidx.viewpager.widget.ViewPager.ItemInfo) r6
            goto L_0x01a1
        L_0x016a:
            r6 = 0
            goto L_0x01a1
        L_0x016c:
            if (r6 == 0) goto L_0x0188
            int r12 = r6.position
            if (r11 != r12) goto L_0x0188
            float r6 = r6.widthFactor
            float r3 = r3 + r6
            int r5 = r5 + 1
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x016a
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            java.lang.Object r6 = r6.get(r5)
            androidx.viewpager.widget.ViewPager$ItemInfo r6 = (androidx.viewpager.widget.ViewPager.ItemInfo) r6
            goto L_0x01a1
        L_0x0188:
            androidx.viewpager.widget.ViewPager$ItemInfo r6 = r0.addNewItem(r11, r5)
            int r5 = r5 + 1
            float r6 = r6.widthFactor
            float r3 = r3 + r6
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x016a
            java.util.ArrayList<androidx.viewpager.widget.ViewPager$ItemInfo> r6 = r0.mItems
            java.lang.Object r6 = r6.get(r5)
            androidx.viewpager.widget.ViewPager$ItemInfo r6 = (androidx.viewpager.widget.ViewPager.ItemInfo) r6
        L_0x01a1:
            goto L_0x0138
        L_0x01a2:
            r0.calculatePageOffsets(r9, r8, r2)
            androidx.viewpager.widget.PagerAdapter r1 = r0.mAdapter
            int r2 = r0.mCurItem
            java.lang.Object r3 = r9.object
            r1.setPrimaryItem((android.view.ViewGroup) r0, (int) r2, (java.lang.Object) r3)
            goto L_0x01b1
        L_0x01af:
            r16 = 0
        L_0x01b1:
            androidx.viewpager.widget.PagerAdapter r1 = r0.mAdapter
            r1.finishUpdate((android.view.ViewGroup) r0)
            int r1 = r0.getChildCount()
            r2 = 0
        L_0x01bb:
            if (r2 >= r1) goto L_0x01e4
            android.view.View r3 = r0.getChildAt(r2)
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            androidx.viewpager.widget.ViewPager$LayoutParams r5 = (androidx.viewpager.widget.ViewPager.LayoutParams) r5
            r5.childIndex = r2
            boolean r6 = r5.isDecor
            if (r6 != 0) goto L_0x01e1
            float r6 = r5.widthFactor
            int r6 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r6 != 0) goto L_0x01e1
            androidx.viewpager.widget.ViewPager$ItemInfo r3 = r0.infoForChild(r3)
            if (r3 == 0) goto L_0x01e1
            float r6 = r3.widthFactor
            r5.widthFactor = r6
            int r3 = r3.position
            r5.position = r3
        L_0x01e1:
            int r2 = r2 + 1
            goto L_0x01bb
        L_0x01e4:
            r0.sortChildDrawingOrder()
            boolean r1 = r0.hasFocus()
            if (r1 == 0) goto L_0x0222
            android.view.View r1 = r0.findFocus()
            if (r1 == 0) goto L_0x01f8
            androidx.viewpager.widget.ViewPager$ItemInfo r3 = r0.infoForAnyChild(r1)
            goto L_0x01f9
        L_0x01f8:
            r3 = 0
        L_0x01f9:
            if (r3 == 0) goto L_0x0201
            int r1 = r3.position
            int r2 = r0.mCurItem
            if (r1 == r2) goto L_0x0222
        L_0x0201:
            r6 = 0
        L_0x0202:
            int r1 = r0.getChildCount()
            if (r6 >= r1) goto L_0x0222
            android.view.View r1 = r0.getChildAt(r6)
            androidx.viewpager.widget.ViewPager$ItemInfo r2 = r0.infoForChild(r1)
            if (r2 == 0) goto L_0x021f
            int r2 = r2.position
            int r3 = r0.mCurItem
            if (r2 != r3) goto L_0x021f
            boolean r1 = r1.requestFocus(r4)
            if (r1 == 0) goto L_0x021f
            goto L_0x0222
        L_0x021f:
            int r6 = r6 + 1
            goto L_0x0202
        L_0x0222:
            return
        L_0x0223:
            android.content.res.Resources r1 = r0.getResources()     // Catch:{ NotFoundException -> 0x0230 }
            int r2 = r0.getId()     // Catch:{ NotFoundException -> 0x0230 }
            java.lang.String r1 = r1.getResourceName(r2)     // Catch:{ NotFoundException -> 0x0230 }
            goto L_0x0238
        L_0x0230:
            int r1 = r0.getId()
            java.lang.String r1 = java.lang.Integer.toHexString(r1)
        L_0x0238:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "The application's PagerAdapter changed the adapter's contents without calling PagerAdapter#notifyDataSetChanged! Expected adapter item count: "
            r3.<init>(r4)
            int r4 = r0.mExpectedAdapterCount
            java.lang.String r5 = ", found: "
            java.lang.String r6 = " Pager id: "
            N2.j.x(r3, r4, r5, r7, r6)
            r3.append(r1)
            java.lang.String r1 = " Pager class: "
            r3.append(r1)
            java.lang.Class r1 = r0.getClass()
            r3.append(r1)
            java.lang.String r1 = " Problematic adapter: "
            r3.append(r1)
            androidx.viewpager.widget.PagerAdapter r0 = r0.mAdapter
            java.lang.Class r0 = r0.getClass()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.populate(int):void");
    }

    public void setCurrentItemInternal(int i2, boolean z, boolean z3, int i7) {
        PagerAdapter pagerAdapter = this.mAdapter;
        boolean z7 = false;
        if (pagerAdapter == null || pagerAdapter.getCount() <= 0) {
            setScrollingCacheEnabled(false);
        } else if (z3 || this.mCurItem != i2 || this.mItems.size() == 0) {
            if (i2 < 0) {
                i2 = 0;
            } else if (i2 >= this.mAdapter.getCount()) {
                i2 = this.mAdapter.getCount() - 1;
            }
            int i8 = this.mOffscreenPageLimit;
            int i10 = this.mCurItem;
            if (i2 > i10 + i8 || i2 < i10 - i8) {
                for (int i11 = 0; i11 < this.mItems.size(); i11++) {
                    this.mItems.get(i11).scrolling = true;
                }
            }
            if (this.mCurItem != i2) {
                z7 = true;
            }
            if (this.mFirstLayout) {
                this.mCurItem = i2;
                if (z7) {
                    dispatchOnPageSelected(i2);
                }
                requestLayout();
                return;
            }
            populate(i2);
            scrollToItem(i2, z, i7, z7);
        } else {
            setScrollingCacheEnabled(false);
        }
    }

    public void setCurrentItem(int i2, boolean z) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i2, z, false);
    }

    public void setPageMarginDrawable(int i2) {
        setPageMarginDrawable(ContextCompat.getDrawable(getContext(), i2));
    }
}
