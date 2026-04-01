package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import androidx.recyclerview.R$string;
import androidx.recyclerview.widget.RecyclerView;
import androidx.reflect.view.SeslHapticFeedbackConstantsReflector;
import c0.C0086a;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ItemTouchHelper extends RecyclerView.ItemDecoration implements RecyclerView.OnChildAttachStateChangeListener {
    private int mActionState = 0;
    int mActivePointerId = -1;
    Callback mCallback;
    private RecyclerView.ChildDrawingOrderCallback mChildDrawingOrderCallback = null;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    private String mEndDraggingText = null;
    GestureDetectorCompat mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    private ItemTouchHelperGestureListener mItemTouchHelperGestureListener;
    private float mMaxSwipeVelocity;
    private String mMoveDraggingText = null;
    private final RecyclerView.OnItemTouchListener mOnItemTouchListener = new RecyclerView.OnItemTouchListener() {
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            int findPointerIndex;
            RecoverAnimation findAnimation;
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(0);
                ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
                Log.i("ItemTouchHelper", "onInterceptTouchEvent: #1 set mInitialTouchX = " + ItemTouchHelper.this.mInitialTouchX);
                ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
                if (ItemTouchHelper.this.mUsePagingTouchSlopForStylus) {
                    if (motionEvent.isFromSource(16386)) {
                        ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                        int unused = itemTouchHelper.mSlop = itemTouchHelper.mPagingTouchSlop;
                    } else {
                        ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                        int unused2 = itemTouchHelper2.mSlop = itemTouchHelper2.mTouchSlop;
                    }
                }
                ItemTouchHelper.this.obtainVelocityTracker();
                ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                if (itemTouchHelper3.mSelected == null && (findAnimation = itemTouchHelper3.findAnimation(motionEvent)) != null) {
                    Log.i("ItemTouchHelper", "onInterceptTouchEvent: #2 mInitialTouchX = " + ItemTouchHelper.this.mInitialTouchX + " animation.mX = " + findAnimation.mX);
                    ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                    itemTouchHelper4.mInitialTouchX = itemTouchHelper4.mInitialTouchX - findAnimation.mX;
                    Log.i("ItemTouchHelper", "onInterceptTouchEvent: #2 set mInitialTouchX = " + ItemTouchHelper.this.mInitialTouchX);
                    ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                    itemTouchHelper5.mInitialTouchY = itemTouchHelper5.mInitialTouchY - findAnimation.mY;
                    itemTouchHelper5.endRecoverAnimation(findAnimation.mViewHolder, true);
                    if (ItemTouchHelper.this.mPendingCleanup.remove(findAnimation.mViewHolder.itemView)) {
                        ItemTouchHelper itemTouchHelper6 = ItemTouchHelper.this;
                        itemTouchHelper6.mCallback.clearView(itemTouchHelper6.mRecyclerView, findAnimation.mViewHolder);
                    }
                    ItemTouchHelper.this.select(findAnimation.mViewHolder, findAnimation.mActionState);
                    ItemTouchHelper itemTouchHelper7 = ItemTouchHelper.this;
                    itemTouchHelper7.updateDxDy(motionEvent, itemTouchHelper7.mSelectedFlags, 0);
                }
            } else if (actionMasked == 3 || actionMasked == 1) {
                ItemTouchHelper itemTouchHelper8 = ItemTouchHelper.this;
                itemTouchHelper8.mActivePointerId = -1;
                itemTouchHelper8.select((RecyclerView.ViewHolder) null, 0);
            } else {
                int i2 = ItemTouchHelper.this.mActivePointerId;
                if (i2 != -1 && (findPointerIndex = motionEvent.findPointerIndex(i2)) >= 0) {
                    ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, findPointerIndex);
                }
            }
            VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.mSelected != null) {
                return true;
            }
            return false;
        }

        public void onRequestDisallowInterceptTouchEvent(boolean z) {
            if (z) {
                ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
            }
        }

        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
            VelocityTracker velocityTracker = ItemTouchHelper.this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
            if (ItemTouchHelper.this.mActivePointerId != -1) {
                int actionMasked = motionEvent.getActionMasked();
                int findPointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                if (findPointerIndex >= 0) {
                    ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, findPointerIndex);
                }
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                RecyclerView.ViewHolder viewHolder = itemTouchHelper.mSelected;
                if (viewHolder != null) {
                    int i2 = 1;
                    if (actionMasked != 1) {
                        if (actionMasked != 2) {
                            if (actionMasked == 3) {
                                VelocityTracker velocityTracker2 = itemTouchHelper.mVelocityTracker;
                                if (velocityTracker2 != null) {
                                    velocityTracker2.clear();
                                }
                            } else if (actionMasked == 6) {
                                int actionIndex = motionEvent.getActionIndex();
                                int pointerId = motionEvent.getPointerId(actionIndex);
                                ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                                if (pointerId == itemTouchHelper2.mActivePointerId) {
                                    if (actionIndex != 0) {
                                        i2 = 0;
                                    }
                                    itemTouchHelper2.mActivePointerId = motionEvent.getPointerId(i2);
                                    ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                                    itemTouchHelper3.updateDxDy(motionEvent, itemTouchHelper3.mSelectedFlags, actionIndex);
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        } else if (motionEvent.getButtonState() == 32) {
                            ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
                            ItemTouchHelper.this.mActivePointerId = -1;
                            return;
                        } else if (findPointerIndex >= 0) {
                            ItemTouchHelper itemTouchHelper4 = ItemTouchHelper.this;
                            itemTouchHelper4.updateDxDy(motionEvent, itemTouchHelper4.mSelectedFlags, findPointerIndex);
                            ItemTouchHelper.this.moveIfNecessary(viewHolder);
                            ItemTouchHelper itemTouchHelper5 = ItemTouchHelper.this;
                            itemTouchHelper5.mRecyclerView.removeCallbacks(itemTouchHelper5.mScrollRunnable);
                            ItemTouchHelper.this.mScrollRunnable.run();
                            ItemTouchHelper.this.mRecyclerView.invalidate();
                            return;
                        } else {
                            return;
                        }
                    }
                    ItemTouchHelper.this.select((RecyclerView.ViewHolder) null, 0);
                    ItemTouchHelper.this.mActivePointerId = -1;
                }
            }
        }
    };
    View mOverdrawChild = null;
    int mOverdrawChildPosition = -1;
    /* access modifiers changed from: private */
    public int mPagingTouchSlop = 0;
    final List<View> mPendingCleanup = new ArrayList();
    List<RecoverAnimation> mRecoverAnimations = new ArrayList();
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable = new Runnable() {
        public void run() {
            ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
            if (itemTouchHelper.mSelected != null && itemTouchHelper.scrollIfNecessary()) {
                ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                RecyclerView.ViewHolder viewHolder = itemTouchHelper2.mSelected;
                if (viewHolder != null) {
                    itemTouchHelper2.moveIfNecessary(viewHolder);
                }
                ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                itemTouchHelper3.mRecyclerView.removeCallbacks(itemTouchHelper3.mScrollRunnable);
                ViewCompat.postOnAnimation(ItemTouchHelper.this.mRecyclerView, this);
            }
        }
    };
    RecyclerView.ViewHolder mSelected = null;
    int mSelectedFlags;
    private float mSelectedStartX;
    private float mSelectedStartY;
    /* access modifiers changed from: private */
    public int mSlop;
    private String mStartDraggingText = null;
    private List<RecyclerView.ViewHolder> mSwapTargets;
    private float mSwipeEscapeVelocity;
    private final float[] mTmpPosition = new float[2];
    private Rect mTmpRect;
    /* access modifiers changed from: private */
    public int mTouchSlop = 0;
    /* access modifiers changed from: private */
    public boolean mUsePagingTouchSlopForStylus = false;
    VelocityTracker mVelocityTracker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mShouldReactToLongPress = true;

        public ItemTouchHelperGestureListener() {
        }

        public void doNotReactToLongPress() {
            this.mShouldReactToLongPress = false;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        public void onLongPress(MotionEvent motionEvent) {
            View findChildView;
            RecyclerView.ViewHolder childViewHolder;
            if (this.mShouldReactToLongPress && (findChildView = ItemTouchHelper.this.findChildView(motionEvent)) != null && (childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(findChildView)) != null) {
                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                if (!itemTouchHelper.mCallback.hasDragFlag(itemTouchHelper.mRecyclerView, childViewHolder)) {
                    childViewHolder.itemView.announceForAccessibility(ItemTouchHelper.this.mRecyclerView.getContext().getString(R$string.dragndroplist_item_cannot_be_dragged, new Object[]{Integer.valueOf(childViewHolder.getLayoutPosition() + 1)}));
                    return;
                }
                int pointerId = motionEvent.getPointerId(0);
                int i2 = ItemTouchHelper.this.mActivePointerId;
                if (pointerId == i2) {
                    int findPointerIndex = motionEvent.findPointerIndex(i2);
                    float x9 = motionEvent.getX(findPointerIndex);
                    float y = motionEvent.getY(findPointerIndex);
                    ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                    itemTouchHelper2.mInitialTouchX = x9;
                    itemTouchHelper2.mInitialTouchY = y;
                    itemTouchHelper2.mDy = 0.0f;
                    itemTouchHelper2.mDx = 0.0f;
                    if (itemTouchHelper2.mCallback.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.select(childViewHolder, 2);
                    }
                }
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class SimpleCallback extends Callback {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;

        public SimpleCallback(int i2, int i7) {
            this.mDefaultSwipeDirs = i7;
            this.mDefaultDragDirs = i2;
        }

        public int getDragDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultDragDirs;
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return Callback.makeMovementFlags(getDragDirs(recyclerView, viewHolder), getSwipeDirs(recyclerView, viewHolder));
        }

        public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return this.mDefaultSwipeDirs;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ViewDropHandler {
        void prepareForDrop(View view, View view2, int i2, int i7);
    }

    public ItemTouchHelper(Callback callback) {
        this.mCallback = callback;
    }

    private int checkHorizontalSwipe(RecyclerView.ViewHolder viewHolder, int i2) {
        int i7;
        if ((i2 & 12) == 0) {
            return 0;
        }
        int i8 = 4;
        if (this.mDx > 0.0f) {
            i7 = 8;
        } else {
            i7 = 4;
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (xVelocity > 0.0f) {
                i8 = 8;
            }
            float abs = Math.abs(xVelocity);
            if ((i8 & i2) != 0 && i7 == i8 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(yVelocity)) {
                return i8;
            }
        }
        float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder) * ((float) this.mRecyclerView.getWidth());
        if ((i2 & i7) == 0 || Math.abs(this.mDx) <= swipeThreshold) {
            return 0;
        }
        return i7;
    }

    private int checkVerticalSwipe(RecyclerView.ViewHolder viewHolder, int i2) {
        int i7;
        if ((i2 & 3) == 0) {
            return 0;
        }
        int i8 = 1;
        if (this.mDy > 0.0f) {
            i7 = 2;
        } else {
            i7 = 1;
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null && this.mActivePointerId > -1) {
            velocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
            float xVelocity = this.mVelocityTracker.getXVelocity(this.mActivePointerId);
            float yVelocity = this.mVelocityTracker.getYVelocity(this.mActivePointerId);
            if (yVelocity > 0.0f) {
                i8 = 2;
            }
            float abs = Math.abs(yVelocity);
            if ((i8 & i2) != 0 && i8 == i7 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(xVelocity)) {
                return i8;
            }
        }
        float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder) * ((float) this.mRecyclerView.getHeight());
        if ((i2 & i7) == 0 || Math.abs(this.mDy) <= swipeThreshold) {
            return 0;
        }
        return i7;
    }

    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration(this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener(this);
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(0);
            recoverAnimation.cancel();
            this.mCallback.clearView(this.mRecyclerView, recoverAnimation.mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        releaseVelocityTracker();
        stopGestureDetection();
    }

    private List<RecyclerView.ViewHolder> findSwapTargets(RecyclerView.ViewHolder viewHolder) {
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        List<RecyclerView.ViewHolder> list = this.mSwapTargets;
        if (list == null) {
            this.mSwapTargets = new ArrayList();
            this.mDistances = new ArrayList();
        } else {
            list.clear();
            this.mDistances.clear();
        }
        int boundingBoxMargin = this.mCallback.getBoundingBoxMargin();
        int round = Math.round(this.mSelectedStartX + this.mDx) - boundingBoxMargin;
        int round2 = Math.round(this.mSelectedStartY + this.mDy) - boundingBoxMargin;
        int i2 = boundingBoxMargin * 2;
        int width = viewHolder2.itemView.getWidth() + round + i2;
        int height = viewHolder2.itemView.getHeight() + round2 + i2;
        int i7 = (round + width) / 2;
        int i8 = (round2 + height) / 2;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int childCount = layoutManager.getChildCount();
        Rect rect = new Rect(0, 0, this.mRecyclerView.getWidth(), this.mRecyclerView.getHeight());
        Rect rect2 = new Rect(round, round2, width, height);
        boolean z = true;
        if ((layoutManager instanceof LinearLayoutManager) && layoutManager.canScrollVertically()) {
            if (round < 0) {
                rect2.right -= round;
                rect2.left = 0;
                z = false;
            }
            if (width > this.mRecyclerView.getWidth()) {
                rect2.left -= width - this.mRecyclerView.getWidth();
                rect2.right = this.mRecyclerView.getWidth();
                z = false;
            }
            if (round2 < 0) {
                rect2.bottom -= round2;
                rect2.top = 0;
                z = false;
            }
            if (height > this.mRecyclerView.getHeight()) {
                rect2.top -= height - this.mRecyclerView.getHeight();
                rect2.bottom = this.mRecyclerView.getHeight();
                z = false;
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = layoutManager.getChildAt(i10);
            if (!(childAt == null || childAt == viewHolder2.itemView)) {
                Rect rect3 = new Rect(childAt.getLeft(), childAt.getTop(), childAt.getRight(), childAt.getBottom());
                if (Rect.intersects(rect2, rect3) && (z || rect.contains(rect3))) {
                    RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(childAt);
                    if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, childViewHolder)) {
                        int abs = Math.abs(i7 - ((childAt.getRight() + childAt.getLeft()) / 2));
                        int abs2 = Math.abs(i8 - ((childAt.getBottom() + childAt.getTop()) / 2));
                        int i11 = (abs2 * abs2) + (abs * abs);
                        int size = this.mSwapTargets.size();
                        int i12 = 0;
                        int i13 = 0;
                        while (i12 < size && i11 > this.mDistances.get(i12).intValue()) {
                            i13++;
                            i12++;
                        }
                        this.mSwapTargets.add(i13, childViewHolder);
                        this.mDistances.add(i13, Integer.valueOf(i11));
                    }
                }
            }
        }
        return this.mSwapTargets;
    }

    private RecyclerView.ViewHolder findSwipedView(MotionEvent motionEvent) {
        View findChildView;
        RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        int i2 = this.mActivePointerId;
        if (i2 == -1) {
            return null;
        }
        int findPointerIndex = motionEvent.findPointerIndex(i2);
        float abs = Math.abs(motionEvent.getX(findPointerIndex) - this.mInitialTouchX);
        float abs2 = Math.abs(motionEvent.getY(findPointerIndex) - this.mInitialTouchY);
        int i7 = this.mSlop;
        if (abs < ((float) i7) && abs2 < ((float) i7)) {
            return null;
        }
        if (abs > abs2 && layoutManager.canScrollHorizontally()) {
            return null;
        }
        if ((abs2 <= abs || !layoutManager.canScrollVertically()) && (findChildView = findChildView(motionEvent)) != null) {
            return this.mRecyclerView.getChildViewHolder(findChildView);
        }
        return null;
    }

    private void getSelectedDxDy(float[] fArr, int i2) {
        if ((this.mSelectedFlags & 12) != 0) {
            fArr[0] = (this.mSelectedStartX + this.mDx) - ((float) this.mSelected.itemView.getLeft());
            StringBuilder o2 = C0086a.o(i2, "getSelectedDxDy: #1 calledBy = ", " outPosition[0] = ");
            o2.append(fArr[0]);
            o2.append(", mSelectedStartX = ");
            o2.append(this.mSelectedStartX);
            o2.append(", mDx = ");
            o2.append(this.mDx);
            o2.append(", mSelected.itemView.getLeft() = ");
            o2.append(this.mSelected.itemView.getLeft());
            Log.i("ItemTouchHelper", o2.toString());
        } else {
            fArr[0] = this.mSelected.itemView.getTranslationX();
            StringBuilder o3 = C0086a.o(i2, "getSelectedDxDy: #2 calledBy = ", " outPosition[0] = ");
            o3.append(this.mSelected.itemView.getTranslationX());
            Log.i("ItemTouchHelper", o3.toString());
        }
        if ((this.mSelectedFlags & 3) != 0) {
            fArr[1] = (this.mSelectedStartY + this.mDy) - ((float) this.mSelected.itemView.getTop());
        } else {
            fArr[1] = this.mSelected.itemView.getTranslationY();
        }
    }

    private static boolean hitTest(View view, float f, float f5, float f8, float f10) {
        if (f < f8 || f > f8 + ((float) view.getWidth()) || f5 < f10 || f5 > f10 + ((float) view.getHeight())) {
            return false;
        }
        return true;
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void setupCallbacks() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(this.mRecyclerView.getContext());
        this.mSlop = viewConfiguration.getScaledTouchSlop();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mRecyclerView.addItemDecoration(this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener(this);
        startGestureDetection();
    }

    private void startGestureDetection() {
        this.mItemTouchHelperGestureListener = new ItemTouchHelperGestureListener();
        this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), this.mItemTouchHelperGestureListener);
    }

    private void stopGestureDetection() {
        ItemTouchHelperGestureListener itemTouchHelperGestureListener = this.mItemTouchHelperGestureListener;
        if (itemTouchHelperGestureListener != null) {
            itemTouchHelperGestureListener.doNotReactToLongPress();
            this.mItemTouchHelperGestureListener = null;
        }
        if (this.mGestureDetector != null) {
            this.mGestureDetector = null;
        }
    }

    private int swipeIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (this.mActionState == 2) {
            return 0;
        }
        int movementFlags = this.mCallback.getMovementFlags(this.mRecyclerView, viewHolder);
        int convertToAbsoluteDirection = (this.mCallback.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection(this.mRecyclerView)) & 65280) >> 8;
        if (convertToAbsoluteDirection == 0) {
            return 0;
        }
        int i2 = (movementFlags & 65280) >> 8;
        if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
            int checkHorizontalSwipe = checkHorizontalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkHorizontalSwipe <= 0) {
                int checkVerticalSwipe = checkVerticalSwipe(viewHolder, convertToAbsoluteDirection);
                if (checkVerticalSwipe > 0) {
                    return checkVerticalSwipe;
                }
            } else if ((i2 & checkHorizontalSwipe) == 0) {
                return Callback.convertToRelativeDirection(checkHorizontalSwipe, ViewCompat.getLayoutDirection(this.mRecyclerView));
            } else {
                return checkHorizontalSwipe;
            }
        } else {
            int checkVerticalSwipe2 = checkVerticalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkVerticalSwipe2 > 0) {
                return checkVerticalSwipe2;
            }
            int checkHorizontalSwipe2 = checkHorizontalSwipe(viewHolder, convertToAbsoluteDirection);
            if (checkHorizontalSwipe2 > 0) {
                if ((i2 & checkHorizontalSwipe2) == 0) {
                    return Callback.convertToRelativeDirection(checkHorizontalSwipe2, ViewCompat.getLayoutDirection(this.mRecyclerView));
                }
                return checkHorizontalSwipe2;
            }
        }
        return 0;
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                destroyCallbacks();
            }
            this.mRecyclerView = recyclerView;
            if (recyclerView != null) {
                Resources resources = recyclerView.getResources();
                this.mSwipeEscapeVelocity = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_max_velocity);
                setupCallbacks();
            }
        }
    }

    public void checkSelectForSwipe(int i2, MotionEvent motionEvent, int i7) {
        RecyclerView.ViewHolder findSwipedView;
        int absoluteMovementFlags;
        if (this.mSelected == null && i2 == 2 && this.mActionState != 2 && this.mCallback.isItemViewSwipeEnabled() && this.mRecyclerView.getScrollState() != 1 && (findSwipedView = findSwipedView(motionEvent)) != null && (absoluteMovementFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, findSwipedView) & 65280) >> 8) != 0) {
            float x9 = motionEvent.getX(i7);
            float y = motionEvent.getY(i7);
            float f = x9 - this.mInitialTouchX;
            float f5 = y - this.mInitialTouchY;
            float abs = Math.abs(f);
            float abs2 = Math.abs(f5);
            int i8 = this.mSlop;
            if (abs >= ((float) i8) || abs2 >= ((float) i8)) {
                if (abs > abs2) {
                    if (f < 0.0f && (absoluteMovementFlags & 4) == 0) {
                        return;
                    }
                    if (f > 0.0f && (absoluteMovementFlags & 8) == 0) {
                        return;
                    }
                } else if (f5 < 0.0f && (absoluteMovementFlags & 1) == 0) {
                    return;
                } else {
                    if (f5 > 0.0f && (absoluteMovementFlags & 2) == 0) {
                        return;
                    }
                }
                this.mDy = 0.0f;
                this.mDx = 0.0f;
                this.mActivePointerId = motionEvent.getPointerId(0);
                select(findSwipedView, 1);
            }
        }
    }

    public void endRecoverAnimation(RecyclerView.ViewHolder viewHolder, boolean z) {
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            if (recoverAnimation.mViewHolder == viewHolder) {
                recoverAnimation.mOverridden |= z;
                if (!recoverAnimation.mEnded) {
                    recoverAnimation.cancel();
                }
                this.mRecoverAnimations.remove(size);
                return;
            }
        }
    }

    public RecoverAnimation findAnimation(MotionEvent motionEvent) {
        if (this.mRecoverAnimations.isEmpty()) {
            return null;
        }
        View findChildView = findChildView(motionEvent);
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            if (recoverAnimation.mViewHolder.itemView == findChildView) {
                return recoverAnimation;
            }
        }
        return null;
    }

    public View findChildView(MotionEvent motionEvent) {
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        RecyclerView.ViewHolder viewHolder = this.mSelected;
        if (viewHolder != null) {
            View view = viewHolder.itemView;
            if (hitTest(view, x9, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return view;
            }
        }
        for (int size = this.mRecoverAnimations.size() - 1; size >= 0; size--) {
            RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(size);
            View view2 = recoverAnimation.mViewHolder.itemView;
            if (hitTest(view2, x9, y, recoverAnimation.mX, recoverAnimation.mY)) {
                return view2;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x9, y);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        rect.setEmpty();
    }

    public boolean hasRunningRecoverAnim() {
        int size = this.mRecoverAnimations.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.mRecoverAnimations.get(i2).mEnded) {
                return true;
            }
        }
        return false;
    }

    public void moveIfNecessary(RecyclerView.ViewHolder viewHolder) {
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            float moveThreshold = this.mCallback.getMoveThreshold(viewHolder);
            int i2 = (int) (this.mSelectedStartX + this.mDx);
            int i7 = (int) (this.mSelectedStartY + this.mDy);
            if (((float) Math.abs(i7 - viewHolder.itemView.getTop())) >= ((float) viewHolder.itemView.getHeight()) * moveThreshold || ((float) Math.abs(i2 - viewHolder.itemView.getLeft())) >= ((float) viewHolder.itemView.getWidth()) * moveThreshold) {
                List<RecyclerView.ViewHolder> findSwapTargets = findSwapTargets(viewHolder);
                if (findSwapTargets.size() != 0) {
                    RecyclerView.ViewHolder chooseDropTarget = this.mCallback.chooseDropTarget(viewHolder, findSwapTargets, i2, i7);
                    if (chooseDropTarget == null) {
                        this.mSwapTargets.clear();
                        this.mDistances.clear();
                        return;
                    }
                    int absoluteAdapterPosition = chooseDropTarget.getAbsoluteAdapterPosition();
                    int absoluteAdapterPosition2 = viewHolder.getAbsoluteAdapterPosition();
                    if (this.mCallback.onMove(this.mRecyclerView, viewHolder, chooseDropTarget)) {
                        RecyclerView.ViewHolder viewHolder2 = viewHolder;
                        this.mCallback.onMoved(this.mRecyclerView, viewHolder2, absoluteAdapterPosition2, chooseDropTarget, absoluteAdapterPosition, i2, i7);
                        viewHolder2.itemView.performHapticFeedback(SeslHapticFeedbackConstantsReflector.semGetVibrationIndex(41));
                        String str = this.mMoveDraggingText;
                        if (str == null || str.isEmpty()) {
                            this.mSelected.itemView.announceForAccessibility(this.mRecyclerView.getContext().getString(R$string.dragndroplist_drag_move, new Object[]{Integer.valueOf(absoluteAdapterPosition + 1)}));
                        } else {
                            this.mSelected.itemView.announceForAccessibility(this.mMoveDraggingText);
                        }
                    }
                }
            }
        }
    }

    public void obtainVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }

    public void onChildViewDetachedFromWindow(View view) {
        removeChildDrawingOrderCallbackIfNecessary(view);
        RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder != null) {
            RecyclerView.ViewHolder viewHolder = this.mSelected;
            if (viewHolder == null || childViewHolder != viewHolder) {
                endRecoverAnimation(childViewHolder, false);
                if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
                    this.mCallback.clearView(this.mRecyclerView, childViewHolder);
                    return;
                }
                return;
            }
            select((RecyclerView.ViewHolder) null, 0);
        }
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f5;
        this.mOverdrawChildPosition = -1;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition, 2);
            float[] fArr = this.mTmpPosition;
            float f8 = fArr[0];
            f = fArr[1];
            f5 = f8;
        } else {
            f5 = 0.0f;
            f = 0.0f;
        }
        this.mCallback.onDraw(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f5, f);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        float f;
        float f5;
        if (this.mSelected != null) {
            getSelectedDxDy(this.mTmpPosition, 1);
            float[] fArr = this.mTmpPosition;
            float f8 = fArr[0];
            f = fArr[1];
            f5 = f8;
        } else {
            f5 = 0.0f;
            f = 0.0f;
        }
        this.mCallback.onDrawOver(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, f5, f);
    }

    public void postDispatchSwipe(final RecoverAnimation recoverAnimation, final int i2) {
        this.mRecyclerView.post(new Runnable() {
            public void run() {
                RecyclerView recyclerView = ItemTouchHelper.this.mRecyclerView;
                if (recyclerView != null && recyclerView.isAttachedToWindow()) {
                    RecoverAnimation recoverAnimation = recoverAnimation;
                    if (!recoverAnimation.mOverridden && recoverAnimation.mViewHolder.getAbsoluteAdapterPosition() != -1) {
                        StringBuilder sb2 = new StringBuilder("postDispatchSwipe$run: mRecyclerView = ");
                        sb2.append(ItemTouchHelper.this.mRecyclerView);
                        sb2.append(", isAttachedToWindow = ");
                        sb2.append(ItemTouchHelper.this.mRecyclerView.isAttachedToWindow());
                        sb2.append(", !anim.mOverridden = ");
                        sb2.append(!recoverAnimation.mOverridden);
                        sb2.append(", anim.mViewHolder.getAdapterPosition() = ");
                        sb2.append(recoverAnimation.mViewHolder.getAdapterPosition());
                        Log.i("ItemTouchHelper", sb2.toString());
                        RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                        if ((itemAnimator == null || !itemAnimator.isRunning((RecyclerView.ItemAnimator.ItemAnimatorFinishedListener) null)) && !ItemTouchHelper.this.hasRunningRecoverAnim()) {
                            Log.i("ItemTouchHelper", "postDispatchSwipe$run: mCallback.onSwiped anim.mViewHolder = " + recoverAnimation.mViewHolder + ", anim.mViewHolder.itemView = " + recoverAnimation.mViewHolder.itemView + " swipeDir=" + i2);
                            ItemTouchHelper.this.mCallback.onSwiped(recoverAnimation.mViewHolder, i2);
                            ItemTouchHelper.this.endRecoverAnimation(recoverAnimation.mViewHolder, false);
                            return;
                        }
                        ItemTouchHelper.this.mRecyclerView.post(this);
                        return;
                    }
                }
                Log.i("ItemTouchHelper", "Failed to call mCallback.onSwiped()!, call seslOnSwipeFailed, flag = 0x" + Integer.toHexString(recoverAnimation.mViewHolder.getFlags()));
                ItemTouchHelper.this.mCallback.seslOnSwipeFailed(recoverAnimation.mViewHolder, i2);
                ItemTouchHelper.this.endRecoverAnimation(recoverAnimation.mViewHolder, false);
            }
        });
    }

    public void removeChildDrawingOrderCallbackIfNecessary(View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback((RecyclerView.ChildDrawingOrderCallback) null);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009c, code lost:
        if (r6 < 0) goto L_0x00cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00c7, code lost:
        if (r6 > 0) goto L_0x00cb;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00fe  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scrollIfNecessary() {
        /*
            r15 = this;
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            r1 = 0
            r2 = -9223372036854775808
            if (r0 != 0) goto L_0x000a
            r15.mDragScrollStartTimeInMs = r2
            return r1
        L_0x000a:
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r15.mDragScrollStartTimeInMs
            int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0018
            r6 = 0
        L_0x0016:
            r13 = r6
            goto L_0x001b
        L_0x0018:
            long r6 = r4 - r6
            goto L_0x0016
        L_0x001b:
            androidx.recyclerview.widget.RecyclerView r0 = r15.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            android.graphics.Rect r6 = r15.mTmpRect
            if (r6 != 0) goto L_0x002c
            android.graphics.Rect r6 = new android.graphics.Rect
            r6.<init>()
            r15.mTmpRect = r6
        L_0x002c:
            androidx.recyclerview.widget.RecyclerView$ViewHolder r6 = r15.mSelected
            android.view.View r6 = r6.itemView
            android.graphics.Rect r7 = r15.mTmpRect
            r0.calculateItemDecorationsForChild(r6, r7)
            boolean r6 = r0.canScrollHorizontally()
            r7 = 0
            if (r6 == 0) goto L_0x007c
            float r6 = r15.mSelectedStartX
            float r8 = r15.mDx
            float r6 = r6 + r8
            int r6 = (int) r6
            android.graphics.Rect r8 = r15.mTmpRect
            int r8 = r8.left
            int r8 = r6 - r8
            androidx.recyclerview.widget.RecyclerView r9 = r15.mRecyclerView
            int r9 = r9.getPaddingLeft()
            int r8 = r8 - r9
            float r9 = r15.mDx
            int r10 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r10 >= 0) goto L_0x0059
            if (r8 >= 0) goto L_0x0059
        L_0x0057:
            r11 = r8
            goto L_0x007d
        L_0x0059:
            int r8 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r8 <= 0) goto L_0x007c
            androidx.recyclerview.widget.RecyclerView$ViewHolder r8 = r15.mSelected
            android.view.View r8 = r8.itemView
            int r8 = r8.getWidth()
            int r8 = r8 + r6
            android.graphics.Rect r6 = r15.mTmpRect
            int r6 = r6.right
            int r8 = r8 + r6
            androidx.recyclerview.widget.RecyclerView r6 = r15.mRecyclerView
            int r6 = r6.getWidth()
            androidx.recyclerview.widget.RecyclerView r9 = r15.mRecyclerView
            int r9 = r9.getPaddingRight()
            int r6 = r6 - r9
            int r8 = r8 - r6
            if (r8 <= 0) goto L_0x007c
            goto L_0x0057
        L_0x007c:
            r11 = r1
        L_0x007d:
            boolean r0 = r0.canScrollVertically()
            if (r0 == 0) goto L_0x00ca
            float r0 = r15.mSelectedStartY
            float r6 = r15.mDy
            float r0 = r0 + r6
            int r0 = (int) r0
            android.graphics.Rect r6 = r15.mTmpRect
            int r6 = r6.top
            int r6 = r0 - r6
            androidx.recyclerview.widget.RecyclerView r8 = r15.mRecyclerView
            int r8 = r8.getPaddingTop()
            int r6 = r6 - r8
            float r8 = r15.mDy
            int r9 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x009f
            if (r6 >= 0) goto L_0x009f
            goto L_0x00cb
        L_0x009f:
            int r6 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r6 <= 0) goto L_0x00ca
            androidx.recyclerview.widget.RecyclerView r6 = r15.mRecyclerView
            int r6 = r6.getHeight()
            androidx.recyclerview.widget.RecyclerView r7 = r15.mRecyclerView
            int r7 = r7.seslGetBottomScrollOffset()
            int r6 = r6 - r7
            androidx.recyclerview.widget.RecyclerView r7 = r15.mRecyclerView
            int r7 = r7.getPaddingBottom()
            int r6 = r6 - r7
            androidx.recyclerview.widget.RecyclerView$ViewHolder r7 = r15.mSelected
            android.view.View r7 = r7.itemView
            int r7 = r7.getHeight()
            int r7 = r7 + r0
            android.graphics.Rect r0 = r15.mTmpRect
            int r0 = r0.bottom
            int r7 = r7 + r0
            int r6 = r7 - r6
            if (r6 <= 0) goto L_0x00ca
            goto L_0x00cb
        L_0x00ca:
            r6 = r1
        L_0x00cb:
            if (r11 == 0) goto L_0x00e3
            androidx.recyclerview.widget.ItemTouchHelper$Callback r8 = r15.mCallback
            androidx.recyclerview.widget.RecyclerView r9 = r15.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$ViewHolder r0 = r15.mSelected
            android.view.View r0 = r0.itemView
            int r10 = r0.getWidth()
            androidx.recyclerview.widget.RecyclerView r0 = r15.mRecyclerView
            int r12 = r0.getWidth()
            int r11 = r8.interpolateOutOfBoundsScroll(r9, r10, r11, r12, r13)
        L_0x00e3:
            r0 = r11
            if (r6 == 0) goto L_0x00fe
            androidx.recyclerview.widget.ItemTouchHelper$Callback r8 = r15.mCallback
            androidx.recyclerview.widget.RecyclerView r9 = r15.mRecyclerView
            androidx.recyclerview.widget.RecyclerView$ViewHolder r7 = r15.mSelected
            android.view.View r7 = r7.itemView
            int r10 = r7.getHeight()
            androidx.recyclerview.widget.RecyclerView r7 = r15.mRecyclerView
            int r12 = r7.getHeight()
            r11 = r6
            int r6 = r8.interpolateOutOfBoundsScroll(r9, r10, r11, r12, r13)
            goto L_0x00ff
        L_0x00fe:
            r11 = r6
        L_0x00ff:
            if (r0 != 0) goto L_0x0107
            if (r6 == 0) goto L_0x0104
            goto L_0x0107
        L_0x0104:
            r15.mDragScrollStartTimeInMs = r2
            return r1
        L_0x0107:
            long r7 = r15.mDragScrollStartTimeInMs
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 != 0) goto L_0x010f
            r15.mDragScrollStartTimeInMs = r4
        L_0x010f:
            androidx.recyclerview.widget.RecyclerView r15 = r15.mRecyclerView
            r15.scrollBy(r0, r6)
            r15 = 1
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.ItemTouchHelper.scrollIfNecessary():boolean");
    }

    public void select(RecyclerView.ViewHolder viewHolder, int i2) {
        boolean z;
        boolean z3;
        boolean z7;
        final int i7;
        float f;
        float f5;
        RecyclerView.ViewHolder viewHolder2 = viewHolder;
        int i8 = i2;
        if (viewHolder2 != this.mSelected || i8 != this.mActionState) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            int i10 = this.mActionState;
            endRecoverAnimation(viewHolder2, true);
            this.mActionState = i8;
            if (i8 == 2) {
                if (viewHolder2 != null) {
                    this.mOverdrawChild = viewHolder2.itemView;
                    addChildDrawingOrderCallback();
                } else {
                    throw new IllegalArgumentException("Must pass a ViewHolder when dragging");
                }
            }
            int i11 = (1 << ((i8 * 8) + 8)) - 1;
            RecyclerView.ViewHolder viewHolder3 = this.mSelected;
            boolean z9 = false;
            if (viewHolder3 != null) {
                if (viewHolder3.itemView.getParent() != null) {
                    if (i10 == 2) {
                        i7 = 0;
                    } else {
                        i7 = swipeIfNecessary(viewHolder3);
                    }
                    releaseVelocityTracker();
                    int i12 = 4;
                    if (i7 == 1 || i7 == 2) {
                        f5 = 0.0f;
                        f = Math.signum(this.mDy) * ((float) this.mRecyclerView.getHeight());
                    } else if (i7 == 4 || i7 == 8 || i7 == 16 || i7 == 32) {
                        f = 0.0f;
                        f5 = Math.signum(this.mDx) * ((float) this.mRecyclerView.getWidth());
                    } else {
                        f5 = 0.0f;
                        f = 0.0f;
                    }
                    if (i10 == 2) {
                        String str = this.mEndDraggingText;
                        if (str == null || str.isEmpty()) {
                            this.mSelected.itemView.announceForAccessibility(this.mRecyclerView.getContext().getString(R$string.dragndroplist_drag_release, new Object[]{Integer.valueOf(this.mSelected.getLayoutPosition() + 1)}));
                        } else {
                            this.mSelected.itemView.announceForAccessibility(this.mEndDraggingText);
                        }
                        i12 = 8;
                    } else if (i7 > 0) {
                        i12 = 2;
                    }
                    getSelectedDxDy(this.mTmpPosition, 3);
                    float[] fArr = this.mTmpPosition;
                    float f8 = fArr[0];
                    float f10 = fArr[1];
                    final RecyclerView.ViewHolder viewHolder4 = viewHolder3;
                    z = true;
                    z3 = false;
                    AnonymousClass3 r0 = new RecoverAnimation(viewHolder3, i12, i10, f8, f10, f5, f) {
                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            Log.i("ItemTouchHelper", "select: *** Start RecoverAnimation$onAnimationEnd ***");
                            if (this.mOverridden) {
                                Log.i("ItemTouchHelper", "select: *** End RecoverAnimation$onAnimationEnd *** return #1");
                                return;
                            }
                            Log.i("ItemTouchHelper", "select$onAnimationEnd: swipeDir = " + i7);
                            if (i7 <= 0) {
                                Log.i("ItemTouchHelper", "select$onAnimationEnd: #2 call mCallback.clearView(mRecyclerView = " + ItemTouchHelper.this.mRecyclerView + ", prevSelected = " + viewHolder4 + ")");
                                ItemTouchHelper itemTouchHelper = ItemTouchHelper.this;
                                itemTouchHelper.mCallback.clearView(itemTouchHelper.mRecyclerView, viewHolder4);
                            } else if (!viewHolder4.itemView.isAttachedToWindow()) {
                                Log.i("ItemTouchHelper", "select$onAnimationEnd: #3 call mCallback.clearView(mRecyclerView = " + ItemTouchHelper.this.mRecyclerView + ", prevSelected = " + viewHolder4 + ")");
                                ItemTouchHelper itemTouchHelper2 = ItemTouchHelper.this;
                                itemTouchHelper2.mCallback.clearView(itemTouchHelper2.mRecyclerView, viewHolder4);
                            } else {
                                ItemTouchHelper.this.mPendingCleanup.add(viewHolder4.itemView);
                                this.mIsPendingCleanup = true;
                                if (i7 > 0) {
                                    Log.i("ItemTouchHelper", "select$onAnimationEnd: postDispatchSwipe #4");
                                    ItemTouchHelper.this.postDispatchSwipe(this, i7);
                                } else {
                                    Log.i("ItemTouchHelper", "select$onAnimationEnd: swipeDir <= 0 #5 do nothing");
                                }
                            }
                            ItemTouchHelper itemTouchHelper3 = ItemTouchHelper.this;
                            View view = itemTouchHelper3.mOverdrawChild;
                            View view2 = viewHolder4.itemView;
                            if (view == view2) {
                                itemTouchHelper3.removeChildDrawingOrderCallbackIfNecessary(view2);
                            }
                            Log.i("ItemTouchHelper", "select: *** End RecoverAnimation$onAnimationEnd *** #6");
                        }
                    };
                    long animationDuration = this.mCallback.getAnimationDuration(this.mRecyclerView, i12, f5 - f8, f - f10);
                    Log.i("ItemTouchHelper", "select: setDuration = " + animationDuration);
                    r0.setDuration(animationDuration);
                    this.mRecoverAnimations.add(r0);
                    r0.start();
                    z9 = true;
                } else {
                    z = true;
                    z3 = false;
                    removeChildDrawingOrderCallbackIfNecessary(viewHolder3.itemView);
                    this.mCallback.clearView(this.mRecyclerView, viewHolder3);
                    z9 = false;
                }
                this.mSelected = null;
            } else {
                z = true;
                z3 = false;
            }
            if (viewHolder2 != null) {
                this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, viewHolder2) & i11) >> (this.mActionState * 8);
                this.mSelectedStartX = (float) viewHolder2.itemView.getLeft();
                this.mSelectedStartY = (float) viewHolder2.itemView.getTop();
                this.mSelected = viewHolder2;
            }
            ViewParent parent = this.mRecyclerView.getParent();
            if (parent != null) {
                if (this.mSelected != null) {
                    z7 = z;
                } else {
                    z7 = z3;
                }
                parent.requestDisallowInterceptTouchEvent(z7);
            }
            if (!z9) {
                this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
            }
            int i13 = this.mActionState;
            if (i13 == 0) {
                this.mCallback.onSelectedChanged(viewHolder3, i13);
            } else {
                this.mCallback.onSelectedChanged(this.mSelected, i13);
            }
            if (i8 == 2) {
                this.mSelected.itemView.performHapticFeedback(z3 ? 1 : 0);
                String str2 = this.mStartDraggingText;
                if (str2 == null || str2.isEmpty()) {
                    this.mSelected.itemView.announceForAccessibility(this.mRecyclerView.getContext().getString(R$string.dragndroplist_drag_start, new Object[]{Integer.valueOf(this.mSelected.getLayoutPosition() + 1)}));
                } else {
                    this.mSelected.itemView.announceForAccessibility(this.mStartDraggingText);
                }
            }
            this.mRecyclerView.invalidate();
        }
    }

    public void startDrag(RecyclerView.ViewHolder viewHolder) {
        if (!this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but dragging is not enabled");
            viewHolder.itemView.announceForAccessibility(this.mRecyclerView.getContext().getString(R$string.dragndroplist_item_cannot_be_dragged, new Object[]{Integer.valueOf(viewHolder.getLayoutPosition() + 1)}));
        } else if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
        } else {
            obtainVelocityTracker();
            this.mDy = 0.0f;
            this.mDx = 0.0f;
            select(viewHolder, 2);
        }
    }

    public void updateDxDy(MotionEvent motionEvent, int i2, int i7) {
        float x9 = motionEvent.getX(i7);
        float y = motionEvent.getY(i7);
        this.mDx = x9 - this.mInitialTouchX;
        StringBuilder sb2 = new StringBuilder("updateDxDy: mDx = ");
        C0086a.y(sb2, this.mDx, " = (x = ", x9, " - mInitialTouchX = ");
        sb2.append(this.mInitialTouchX);
        sb2.append(")");
        Log.i("ItemTouchHelper", sb2.toString());
        this.mDy = y - this.mInitialTouchY;
        if ((i2 & 4) == 0) {
            this.mDx = Math.max(0.0f, this.mDx);
            Log.i("ItemTouchHelper", "updateDxDy: direction LEFT mDx = " + this.mDx);
        }
        if ((i2 & 8) == 0) {
            this.mDx = Math.min(0.0f, this.mDx);
            Log.i("ItemTouchHelper", "updateDxDy: direction RIGHT mDx = " + this.mDx);
        }
        if ((i2 & 1) == 0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((i2 & 2) == 0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }

    private void addChildDrawingOrderCallback() {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        private static final Interpolator sDragScrollInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                return f * f * f * f * f;
            }
        };
        private static final Interpolator sDragViewScrollCapInterpolator = new Interpolator() {
            public float getInterpolation(float f) {
                float f5 = f - 1.0f;
                return (f5 * f5 * f5 * f5 * f5) + 1.0f;
            }
        };
        private int mCachedMaxScrollSpeed = -1;

        public static int convertToRelativeDirection(int i2, int i7) {
            int i8;
            int i10 = i2 & 789516;
            if (i10 == 0) {
                return i2;
            }
            int i11 = i2 & (~i10);
            if (i7 == 0) {
                i8 = i10 << 2;
            } else {
                int i12 = i10 << 1;
                i11 |= -789517 & i12;
                i8 = (i12 & 789516) << 2;
            }
            return i11 | i8;
        }

        private int getMaxDragScroll(RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R$dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }

        public static int makeFlag(int i2, int i7) {
            return i7 << (i2 * 8);
        }

        public static int makeMovementFlags(int i2, int i7) {
            int makeFlag = makeFlag(0, i7 | i2);
            return makeFlag(2, i2) | makeFlag(1, i7) | makeFlag;
        }

        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return true;
        }

        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder viewHolder, List<RecyclerView.ViewHolder> list, int i2, int i7) {
            int abs;
            int abs2;
            int left;
            int abs3;
            int right;
            int abs4;
            int width = viewHolder.itemView.getWidth() + i2;
            int height = viewHolder.itemView.getHeight() + i7;
            int left2 = i2 - viewHolder.itemView.getLeft();
            int top = i7 - viewHolder.itemView.getTop();
            int size = list.size();
            RecyclerView.ViewHolder viewHolder2 = null;
            int i8 = -1;
            for (int i10 = 0; i10 < size; i10++) {
                RecyclerView.ViewHolder viewHolder3 = list.get(i10);
                if (left2 > 0 && (right = viewHolder3.itemView.getRight() - width) < 0 && viewHolder3.itemView.getRight() > viewHolder.itemView.getRight() && (abs4 = Math.abs(right)) > i8) {
                    viewHolder2 = viewHolder3;
                    i8 = abs4;
                }
                if (left2 < 0 && (left = viewHolder3.itemView.getLeft() - i2) > 0 && viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft() && (abs3 = Math.abs(left)) > i8) {
                    viewHolder2 = viewHolder3;
                    i8 = abs3;
                }
                if (top < 0) {
                    int bottom = (viewHolder3.itemView.getBottom() + viewHolder3.itemView.getTop()) / 2;
                    int bottom2 = (viewHolder.itemView.getBottom() + viewHolder.itemView.getTop()) / 2;
                    int i11 = bottom - i7;
                    if (i11 > 0 && bottom < bottom2 && (abs2 = Math.abs(i11)) > i8) {
                        viewHolder2 = viewHolder3;
                        i8 = abs2;
                    }
                }
                if (top > 0) {
                    int bottom3 = (viewHolder3.itemView.getBottom() + viewHolder3.itemView.getTop()) / 2;
                    int bottom4 = (viewHolder.itemView.getBottom() + viewHolder.itemView.getTop()) / 2;
                    int i12 = bottom3 - height;
                    if (i12 < 0 && bottom3 > bottom4 && (abs = Math.abs(i12)) > i8) {
                        viewHolder2 = viewHolder3;
                        i8 = abs;
                    }
                }
            }
            return viewHolder2;
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            ItemTouchUIUtilImpl.INSTANCE.clearView(viewHolder.itemView);
        }

        public int convertToAbsoluteDirection(int i2, int i7) {
            int i8;
            int i10 = i2 & 3158064;
            if (i10 == 0) {
                return i2;
            }
            int i11 = i2 & (~i10);
            if (i7 == 0) {
                i8 = i10 >> 2;
            } else {
                int i12 = i10 >> 1;
                i11 |= -3158065 & i12;
                i8 = (3158064 & i12) >> 2;
            }
            return i8 | i11;
        }

        public final int getAbsoluteMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return convertToAbsoluteDirection(getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection(recyclerView));
        }

        public long getAnimationDuration(RecyclerView recyclerView, int i2, float f, float f5) {
            RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                if (i2 == 8) {
                    return 200;
                }
                return 250;
            } else if (i2 == 8) {
                return itemAnimator.getMoveDuration();
            } else {
                return itemAnimator.getRemoveDuration();
            }
        }

        public int getBoundingBoxMargin() {
            return 0;
        }

        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public abstract int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder);

        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return 0.5f;
        }

        public boolean hasDragFlag(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if ((getAbsoluteMovementFlags(recyclerView, viewHolder) & 16711680) != 0) {
                return true;
            }
            return false;
        }

        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int i2, int i7, int i8, long j2) {
            int maxDragScroll = getMaxDragScroll(recyclerView);
            float f = 1.0f;
            int interpolation = (int) (sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, (((float) Math.abs(i7)) * 1.0f) / ((float) i2))) * ((float) (((int) Math.signum((float) i7)) * maxDragScroll)));
            if (j2 <= 2000) {
                f = ((float) j2) / 2000.0f;
            }
            int interpolation2 = (int) (sDragScrollInterpolator.getInterpolation(f) * ((float) interpolation));
            if (interpolation2 != 0) {
                return interpolation2;
            }
            if (i7 > 0) {
                return 1;
            }
            return -1;
        }

        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        public boolean isLongPressDragEnabled() {
            return true;
        }

        public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f5, int i2, boolean z) {
            ItemTouchUIUtilImpl.INSTANCE.onDraw(canvas, recyclerView, viewHolder.itemView, f, f5, i2, z);
        }

        public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f5, int i2, boolean z) {
            ItemTouchUIUtilImpl.INSTANCE.onDrawOver(canvas, recyclerView, viewHolder.itemView, f, f5, i2, z);
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i2, float f, float f5) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                RecoverAnimation recoverAnimation = list.get(i7);
                recoverAnimation.update();
                int save = canvas.save();
                onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDraw(canvas, recyclerView, viewHolder, f, f5, i2, true);
                canvas.restoreToCount(save2);
            }
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, List<RecoverAnimation> list, int i2, float f, float f5) {
            List<RecoverAnimation> list2 = list;
            int size = list2.size();
            boolean z = false;
            for (int i7 = 0; i7 < size; i7++) {
                RecoverAnimation recoverAnimation = list2.get(i7);
                int save = canvas.save();
                onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                int save2 = canvas.save();
                onChildDrawOver(canvas, recyclerView, viewHolder, f, f5, i2, true);
                canvas.restoreToCount(save2);
            }
            for (int i8 = size - 1; i8 >= 0; i8--) {
                RecoverAnimation recoverAnimation2 = list2.get(i8);
                boolean z3 = recoverAnimation2.mEnded;
                if (z3 && !recoverAnimation2.mIsPendingCleanup) {
                    list2.remove(i8);
                } else if (!z3) {
                    z = true;
                }
            }
            if (z) {
                recyclerView.invalidate();
            }
        }

        public abstract boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2);

        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i7, int i8, int i10) {
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler) layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, i8, i10);
                return;
            }
            if (layoutManager.canScrollHorizontally()) {
                if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                    recyclerView.scrollToPosition(i7);
                }
                if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                    recyclerView.scrollToPosition(i7);
                }
            }
            if (layoutManager.canScrollVertically()) {
                if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                    recyclerView.scrollToPosition(i7);
                }
                if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                    recyclerView.scrollToPosition(i7);
                }
            }
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
            if (viewHolder != null) {
                ItemTouchUIUtilImpl.INSTANCE.onSelected(viewHolder.itemView);
            }
        }

        public abstract void onSwiped(RecyclerView.ViewHolder viewHolder, int i2);

        public float getSwipeEscapeVelocity(float f) {
            return f;
        }

        public float getSwipeVelocityThreshold(float f) {
            return f;
        }

        public void seslOnSwipeFailed(RecyclerView.ViewHolder viewHolder, int i2) {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RecoverAnimation implements Animator.AnimatorListener {
        private final PathInterpolator PATH_INTERPOLATOR;
        final int mActionState;
        final int mAnimationType;
        boolean mEnded = false;
        private float mFraction;
        boolean mIsPendingCleanup;
        boolean mOverridden = false;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        final ValueAnimator mValueAnimator;
        final RecyclerView.ViewHolder mViewHolder;
        float mX;
        float mY;

        public RecoverAnimation(RecyclerView.ViewHolder viewHolder, int i2, int i7, float f, float f5, float f8, float f10) {
            PathInterpolator pathInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
            this.PATH_INTERPOLATOR = pathInterpolator;
            this.mActionState = i7;
            this.mAnimationType = i2;
            this.mViewHolder = viewHolder;
            this.mStartDx = f;
            this.mStartDy = f5;
            this.mTargetX = f8;
            this.mTargetY = f10;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.mValueAnimator = ofFloat;
            ofFloat.setInterpolator(pathInterpolator);
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    RecoverAnimation.this.setFraction(valueAnimator.getAnimatedFraction());
                }
            });
            ofFloat.setTarget(viewHolder.itemView);
            ofFloat.addListener(this);
            setFraction(0.0f);
        }

        public void cancel() {
            this.mValueAnimator.cancel();
        }

        public void onAnimationCancel(Animator animator) {
            setFraction(1.0f);
        }

        public void onAnimationEnd(Animator animator) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }

        public void setDuration(long j2) {
            this.mValueAnimator.setDuration(j2);
        }

        public void setFraction(float f) {
            this.mFraction = f;
        }

        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }

        public void update() {
            float f = this.mStartDx;
            float f5 = this.mTargetX;
            if (f == f5) {
                this.mX = this.mViewHolder.itemView.getTranslationX();
            } else {
                this.mX = C0212a.a(f5, f, this.mFraction, f);
            }
            float f8 = this.mStartDy;
            float f10 = this.mTargetY;
            if (f8 == f10) {
                this.mY = this.mViewHolder.itemView.getTranslationY();
            } else {
                this.mY = C0212a.a(f10, f8, this.mFraction, f8);
            }
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }
    }

    public void onChildViewAttachedToWindow(View view) {
    }
}
