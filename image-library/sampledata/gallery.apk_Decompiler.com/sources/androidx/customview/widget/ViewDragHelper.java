package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewDragHelper {
    private static final Interpolator sInterpolator = new Interpolator() {
        public float getInterpolation(float f) {
            float f5 = f - 1.0f;
            return (f5 * f5 * f5 * f5 * f5) + 1.0f;
        }
    };
    private int mActivePointerId = -1;
    private final Callback mCallback;
    private View mCapturedView;
    private final int mDefaultEdgeSize;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private boolean mIsUpdateOffsetLR = true;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final Runnable mSetIdleRunnable = new Runnable() {
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };
    private int mTouchSlop;
    private int mTrackingEdges;
    private VelocityTracker mVelocityTracker;

    private ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (callback != null) {
            this.mParentView = viewGroup;
            this.mCallback = callback;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            int i2 = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.mDefaultEdgeSize = i2;
            this.mEdgeSize = i2;
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            this.mMaxVelocity = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.mMinVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.mScroller = new OverScroller(context, sInterpolator);
        } else {
            throw new IllegalArgumentException("Callback may not be null");
        }
    }

    private boolean checkNewEdgeDrag(float f, float f5, int i2, int i7) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f5);
        if (!((this.mInitialEdgesTouched[i2] & i7) != i7 || (this.mTrackingEdges & i7) == 0 || (this.mEdgeDragsLocked[i2] & i7) == i7 || (this.mEdgeDragsInProgress[i2] & i7) == i7)) {
            int i8 = this.mTouchSlop;
            if (abs > ((float) i8) || abs2 > ((float) i8)) {
                if (abs < abs2 * 0.5f && this.mCallback.onEdgeLock(i7)) {
                    int[] iArr = this.mEdgeDragsLocked;
                    iArr[i2] = iArr[i2] | i7;
                    return false;
                } else if ((this.mEdgeDragsInProgress[i2] & i7) != 0 || abs <= ((float) this.mTouchSlop)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkTouchSlop(View view, float f, float f5) {
        boolean z;
        boolean z3;
        if (view != null) {
            if (this.mCallback.getViewHorizontalDragRange(view) > 0) {
                z = true;
            } else {
                z = false;
            }
            if (this.mCallback.getViewVerticalDragRange(view) > 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z && z3) {
                float f8 = f5 * f5;
                int i2 = this.mTouchSlop;
                if (f8 + (f * f) > ((float) (i2 * i2))) {
                    return true;
                }
            } else if (!z ? !z3 || Math.abs(f5) <= ((float) this.mTouchSlop) : Math.abs(f) <= ((float) this.mTouchSlop)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private int clampMag(int i2, int i7, int i8) {
        int abs = Math.abs(i2);
        if (abs < i7) {
            return 0;
        }
        if (abs > i8) {
            return i2 > 0 ? i8 : -i8;
        }
        return i2;
    }

    private void clearMotionHistory() {
        float[] fArr = this.mInitialMotionX;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
    }

    private int computeAxisDuration(int i2, int i7, int i8) {
        int i10;
        if (i2 == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f = (float) (width / 2);
        float distanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, ((float) Math.abs(i2)) / ((float) width))) * f) + f;
        int abs = Math.abs(i7);
        if (abs > 0) {
            i10 = Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4;
        } else {
            i10 = (int) (((((float) Math.abs(i2)) / ((float) i8)) + 1.0f) * 256.0f);
        }
        return Math.min(i10, 600);
    }

    private int computeSettleDuration(View view, int i2, int i7, int i8, int i10) {
        float f;
        float f5;
        float f8;
        float f10;
        int clampMag = clampMag(i8, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag2 = clampMag(i10, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i7);
        int abs3 = Math.abs(clampMag);
        int abs4 = Math.abs(clampMag2);
        int i11 = abs3 + abs4;
        int i12 = abs + abs2;
        if (clampMag != 0) {
            f = (float) abs3;
            f5 = (float) i11;
        } else {
            f = (float) abs;
            f5 = (float) i12;
        }
        float f11 = f / f5;
        if (clampMag2 != 0) {
            f8 = (float) abs4;
            f10 = (float) i11;
        } else {
            f8 = (float) abs2;
            f10 = (float) i12;
        }
        float f12 = f8 / f10;
        int computeAxisDuration = computeAxisDuration(i2, clampMag, this.mCallback.getViewHorizontalDragRange(view));
        return (int) ((((float) computeAxisDuration(i7, clampMag2, this.mCallback.getViewVerticalDragRange(view))) * f12) + (((float) computeAxisDuration) * f11));
    }

    public static ViewDragHelper create(ViewGroup viewGroup, Callback callback) {
        return new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void dispatchViewReleased(float f, float f5) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f5);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((double) ((f - 0.5f) * 0.47123894f));
    }

    private void dragTo(int i2, int i7, int i8, int i10) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (i8 != 0) {
            i2 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, i2, i8);
            if (this.mIsUpdateOffsetLR || getViewDragState() != 2) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, i2 - left);
            }
        }
        int i11 = i2;
        if (i10 != 0) {
            i7 = this.mCallback.clampViewPositionVertical(this.mCapturedView, i7, i10);
            ViewCompat.offsetTopAndBottom(this.mCapturedView, i7 - top);
        }
        int i12 = i7;
        if (i8 != 0 || i10 != 0) {
            this.mCallback.onViewPositionChanged(this.mCapturedView, i11, i12, i11 - left, i12 - top);
        }
    }

    private void ensureMotionHistorySizeForId(int i2) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= i2) {
            int i7 = i2 + 1;
            float[] fArr2 = new float[i7];
            float[] fArr3 = new float[i7];
            float[] fArr4 = new float[i7];
            float[] fArr5 = new float[i7];
            int[] iArr = new int[i7];
            int[] iArr2 = new int[i7];
            int[] iArr3 = new int[i7];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.mInitialEdgesTouched;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.mEdgeDragsInProgress;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.mEdgeDragsLocked;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
    }

    private boolean forceSettleCapturedViewAt(int i2, int i7, int i8, int i10) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i11 = i2 - left;
        int i12 = i7 - top;
        if (i11 == 0 && i12 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        int i13 = i12;
        int i14 = i11;
        int computeSettleDuration = computeSettleDuration(this.mCapturedView, i14, i13, i8, i10);
        this.mScroller.startScroll(left, top, i14, i13, computeSettleDuration);
        setDragState(2);
        return true;
    }

    private int getEdgesTouched(int i2, int i7) {
        int i8;
        if (i2 < this.mParentView.getLeft() + this.mEdgeSize) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        if (i7 < this.mParentView.getTop() + this.mEdgeSize) {
            i8 |= 4;
        }
        if (i2 > this.mParentView.getRight() - this.mEdgeSize) {
            i8 |= 2;
        }
        if (i7 > this.mParentView.getBottom() - this.mEdgeSize) {
            return i8 | 8;
        }
        return i8;
    }

    private boolean isValidPointerForActionMove(int i2) {
        if (isPointerDown(i2) && i2 != -1) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    private void reportNewEdgeDrags(float f, float f5, int i2) {
        boolean checkNewEdgeDrag = checkNewEdgeDrag(f, f5, i2, 1);
        if (checkNewEdgeDrag(f5, f, i2, 4)) {
            checkNewEdgeDrag |= true;
        }
        if (checkNewEdgeDrag(f, f5, i2, 2)) {
            checkNewEdgeDrag |= true;
        }
        if (checkNewEdgeDrag(f5, f, i2, 8)) {
            checkNewEdgeDrag |= true;
        }
        if (checkNewEdgeDrag) {
            int[] iArr = this.mEdgeDragsInProgress;
            iArr[i2] = iArr[i2] | checkNewEdgeDrag;
            this.mCallback.onEdgeDragStarted(checkNewEdgeDrag ? 1 : 0, i2);
        }
    }

    private void saveInitialMotion(float f, float f5, int i2) {
        ensureMotionHistorySizeForId(i2);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[i2] = f;
        fArr[i2] = f;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[i2] = f5;
        fArr2[i2] = f5;
        this.mInitialEdgesTouched[i2] = getEdgesTouched((int) f, (int) f5);
        this.mPointersDown |= 1 << i2;
    }

    private void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i2 = 0; i2 < pointerCount; i2++) {
            int pointerId = motionEvent.getPointerId(i2);
            if (isValidPointerForActionMove(pointerId)) {
                float x9 = motionEvent.getX(i2);
                float y = motionEvent.getY(i2);
                this.mLastMotionX[pointerId] = x9;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void captureChildView(View view, int i2) {
        if (view.getParent() == this.mParentView) {
            this.mCapturedView = view;
            this.mActivePointerId = i2;
            this.mCallback.onViewCaptured(view, i2);
            setDragState(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.mParentView + ")");
    }

    public boolean continueSettling(boolean z) {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0 && this.mIsUpdateOffsetLR) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (!(left == 0 && top == 0)) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        if (this.mDragState == 2) {
            return true;
        }
        return false;
    }

    public View findTopChildUnder(int i2, int i7) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i7 >= childAt.getTop() && i7 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public int getViewDragState() {
        return this.mDragState;
    }

    public boolean isCapturedViewUnder(int i2, int i7) {
        return isViewUnder(this.mCapturedView, i2, i7);
    }

    public boolean isPointerDown(int i2) {
        if ((this.mPointersDown & (1 << i2)) != 0) {
            return true;
        }
        return false;
    }

    public boolean isViewUnder(View view, int i2, int i7) {
        if (view != null && i2 >= view.getLeft() && i2 < view.getRight() && i7 >= view.getTop() && i7 < view.getBottom()) {
            return true;
        }
        return false;
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int i2;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i7 = 0;
        if (actionMasked == 0) {
            float x9 = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View findTopChildUnder = findTopChildUnder((int) x9, (int) y);
            saveInitialMotion(x9, y, pointerId);
            tryCaptureViewForDrag(findTopChildUnder, pointerId);
            int i8 = this.mInitialEdgesTouched[pointerId];
            int i10 = this.mTrackingEdges;
            if ((i8 & i10) != 0) {
                this.mCallback.onEdgeTouched(i8 & i10, pointerId);
            }
        } else if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
            } else if (actionMasked == 5) {
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                float x10 = motionEvent.getX(actionIndex);
                float y8 = motionEvent.getY(actionIndex);
                saveInitialMotion(x10, y8, pointerId2);
                if (this.mDragState == 0) {
                    tryCaptureViewForDrag(findTopChildUnder((int) x10, (int) y8), pointerId2);
                    int i11 = this.mInitialEdgesTouched[pointerId2];
                    int i12 = this.mTrackingEdges;
                    if ((i11 & i12) != 0) {
                        this.mCallback.onEdgeTouched(i11 & i12, pointerId2);
                    }
                } else if (isCapturedViewUnder((int) x10, (int) y8)) {
                    tryCaptureViewForDrag(this.mCapturedView, pointerId2);
                }
            } else if (actionMasked == 6) {
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                if (this.mDragState == 1 && pointerId3 == this.mActivePointerId) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (true) {
                        if (i7 >= pointerCount) {
                            i2 = -1;
                            break;
                        }
                        int pointerId4 = motionEvent.getPointerId(i7);
                        if (pointerId4 != this.mActivePointerId) {
                            View findTopChildUnder2 = findTopChildUnder((int) motionEvent.getX(i7), (int) motionEvent.getY(i7));
                            View view = this.mCapturedView;
                            if (findTopChildUnder2 == view && tryCaptureViewForDrag(view, pointerId4)) {
                                i2 = this.mActivePointerId;
                                break;
                            }
                        }
                        i7++;
                    }
                    if (i2 == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(pointerId3);
            }
        } else if (this.mDragState != 1) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (i7 < pointerCount2) {
                int pointerId5 = motionEvent.getPointerId(i7);
                if (isValidPointerForActionMove(pointerId5)) {
                    float x11 = motionEvent.getX(i7);
                    float y9 = motionEvent.getY(i7);
                    float f = x11 - this.mInitialMotionX[pointerId5];
                    float f5 = y9 - this.mInitialMotionY[pointerId5];
                    reportNewEdgeDrags(f, f5, pointerId5);
                    if (this.mDragState != 1) {
                        View findTopChildUnder3 = findTopChildUnder((int) x11, (int) y9);
                        if (checkTouchSlop(findTopChildUnder3, f, f5) && tryCaptureViewForDrag(findTopChildUnder3, pointerId5)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i7++;
            }
            saveLastMotion(motionEvent);
        } else if (isValidPointerForActionMove(this.mActivePointerId)) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            float x12 = motionEvent.getX(findPointerIndex);
            float y10 = motionEvent.getY(findPointerIndex);
            float[] fArr = this.mLastMotionX;
            int i13 = this.mActivePointerId;
            int i14 = (int) (x12 - fArr[i13]);
            int i15 = (int) (y10 - this.mLastMotionY[i13]);
            dragTo(this.mCapturedView.getLeft() + i14, this.mCapturedView.getTop() + i15, i14, i15);
            saveLastMotion(motionEvent);
        }
    }

    public void setDragState(int i2) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i2) {
            this.mDragState = i2;
            this.mCallback.onViewDragStateChanged(i2);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public boolean settleCapturedViewAt(int i2, int i7) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i2, i7, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00de, code lost:
        if (r12 != r11) goto L_0x00e7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean shouldInterceptTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r1.getActionMasked()
            int r3 = r1.getActionIndex()
            if (r2 != 0) goto L_0x0011
            r0.cancel()
        L_0x0011:
            android.view.VelocityTracker r4 = r0.mVelocityTracker
            if (r4 != 0) goto L_0x001b
            android.view.VelocityTracker r4 = android.view.VelocityTracker.obtain()
            r0.mVelocityTracker = r4
        L_0x001b:
            android.view.VelocityTracker r4 = r0.mVelocityTracker
            r4.addMovement(r1)
            r4 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0103
            if (r2 == r6) goto L_0x00ff
            if (r2 == r4) goto L_0x0070
            r7 = 3
            if (r2 == r7) goto L_0x00ff
            r7 = 5
            if (r2 == r7) goto L_0x003c
            r4 = 6
            if (r2 == r4) goto L_0x0033
            goto L_0x0132
        L_0x0033:
            int r1 = r1.getPointerId(r3)
            r0.clearMotionHistory(r1)
            goto L_0x0132
        L_0x003c:
            int r2 = r1.getPointerId(r3)
            float r7 = r1.getX(r3)
            float r1 = r1.getY(r3)
            r0.saveInitialMotion(r7, r1, r2)
            int r3 = r0.mDragState
            if (r3 != 0) goto L_0x005f
            int[] r1 = r0.mInitialEdgesTouched
            r1 = r1[r2]
            int r3 = r0.mTrackingEdges
            r1 = r1 & r3
            if (r1 == 0) goto L_0x0132
            androidx.customview.widget.ViewDragHelper$Callback r3 = r0.mCallback
            r3.onEdgeTouched(r1, r2)
            goto L_0x0132
        L_0x005f:
            if (r3 != r4) goto L_0x0132
            int r3 = (int) r7
            int r1 = (int) r1
            android.view.View r1 = r0.findTopChildUnder(r3, r1)
            android.view.View r3 = r0.mCapturedView
            if (r1 != r3) goto L_0x0132
            r0.tryCaptureViewForDrag(r1, r2)
            goto L_0x0132
        L_0x0070:
            float[] r2 = r0.mInitialMotionX
            if (r2 == 0) goto L_0x0132
            float[] r2 = r0.mInitialMotionY
            if (r2 != 0) goto L_0x007a
            goto L_0x0132
        L_0x007a:
            int r2 = r1.getPointerCount()
            r3 = 0
        L_0x007f:
            if (r3 >= r2) goto L_0x00fb
            int r4 = r1.getPointerId(r3)
            boolean r7 = r0.isValidPointerForActionMove(r4)
            if (r7 != 0) goto L_0x008d
            goto L_0x00f8
        L_0x008d:
            float r7 = r1.getX(r3)
            float r8 = r1.getY(r3)
            float[] r9 = r0.mInitialMotionX
            r9 = r9[r4]
            float r9 = r7 - r9
            float[] r10 = r0.mInitialMotionY
            r10 = r10[r4]
            float r10 = r8 - r10
            int r7 = (int) r7
            int r8 = (int) r8
            android.view.View r7 = r0.findTopChildUnder(r7, r8)
            if (r7 == 0) goto L_0x00b1
            boolean r8 = r0.checkTouchSlop(r7, r9, r10)
            if (r8 == 0) goto L_0x00b1
            r8 = r6
            goto L_0x00b2
        L_0x00b1:
            r8 = 0
        L_0x00b2:
            if (r8 == 0) goto L_0x00e7
            int r11 = r7.getLeft()
            int r12 = (int) r9
            int r13 = r11 + r12
            androidx.customview.widget.ViewDragHelper$Callback r14 = r0.mCallback
            int r12 = r14.clampViewPositionHorizontal(r7, r13, r12)
            int r13 = r7.getTop()
            int r14 = (int) r10
            int r15 = r13 + r14
            androidx.customview.widget.ViewDragHelper$Callback r5 = r0.mCallback
            int r5 = r5.clampViewPositionVertical(r7, r15, r14)
            androidx.customview.widget.ViewDragHelper$Callback r14 = r0.mCallback
            int r14 = r14.getViewHorizontalDragRange(r7)
            androidx.customview.widget.ViewDragHelper$Callback r15 = r0.mCallback
            int r15 = r15.getViewVerticalDragRange(r7)
            if (r14 == 0) goto L_0x00e0
            if (r14 <= 0) goto L_0x00e7
            if (r12 != r11) goto L_0x00e7
        L_0x00e0:
            if (r15 == 0) goto L_0x00fb
            if (r15 <= 0) goto L_0x00e7
            if (r5 != r13) goto L_0x00e7
            goto L_0x00fb
        L_0x00e7:
            r0.reportNewEdgeDrags(r9, r10, r4)
            int r5 = r0.mDragState
            if (r5 != r6) goto L_0x00ef
            goto L_0x00fb
        L_0x00ef:
            if (r8 == 0) goto L_0x00f8
            boolean r4 = r0.tryCaptureViewForDrag(r7, r4)
            if (r4 == 0) goto L_0x00f8
            goto L_0x00fb
        L_0x00f8:
            int r3 = r3 + 1
            goto L_0x007f
        L_0x00fb:
            r17.saveLastMotion(r18)
            goto L_0x0132
        L_0x00ff:
            r0.cancel()
            goto L_0x0132
        L_0x0103:
            float r2 = r1.getX()
            float r3 = r1.getY()
            r5 = 0
            int r1 = r1.getPointerId(r5)
            r0.saveInitialMotion(r2, r3, r1)
            int r2 = (int) r2
            int r3 = (int) r3
            android.view.View r2 = r0.findTopChildUnder(r2, r3)
            android.view.View r3 = r0.mCapturedView
            if (r2 != r3) goto L_0x0124
            int r3 = r0.mDragState
            if (r3 != r4) goto L_0x0124
            r0.tryCaptureViewForDrag(r2, r1)
        L_0x0124:
            int[] r2 = r0.mInitialEdgesTouched
            r2 = r2[r1]
            int r3 = r0.mTrackingEdges
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0132
            androidx.customview.widget.ViewDragHelper$Callback r3 = r0.mCallback
            r3.onEdgeTouched(r2, r1)
        L_0x0132:
            int r0 = r0.mDragState
            if (r0 != r6) goto L_0x0137
            return r6
        L_0x0137:
            r16 = 0
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean smoothSlideViewTo(View view, int i2, int i7) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(i2, i7, 0, 0);
        if (!forceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return forceSettleCapturedViewAt;
    }

    public boolean tryCaptureViewForDrag(View view, int i2) {
        if (view == this.mCapturedView && this.mActivePointerId == i2) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, i2)) {
            return false;
        }
        this.mActivePointerId = i2;
        captureChildView(view, i2);
        return true;
    }

    private float clampMag(float f, float f5, float f8) {
        float abs = Math.abs(f);
        if (abs < f5) {
            return 0.0f;
        }
        if (abs > f8) {
            return f > 0.0f ? f8 : -f8;
        }
        return f;
    }

    private void clearMotionHistory(int i2) {
        if (this.mInitialMotionX != null && isPointerDown(i2)) {
            this.mInitialMotionX[i2] = 0.0f;
            this.mInitialMotionY[i2] = 0.0f;
            this.mLastMotionX[i2] = 0.0f;
            this.mLastMotionY[i2] = 0.0f;
            this.mInitialEdgesTouched[i2] = 0;
            this.mEdgeDragsInProgress[i2] = 0;
            this.mEdgeDragsLocked[i2] = 0;
            this.mPointersDown = (~(1 << i2)) & this.mPointersDown;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static abstract class Callback {
        public abstract int clampViewPositionHorizontal(View view, int i2, int i7);

        public abstract int clampViewPositionVertical(View view, int i2, int i7);

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public boolean onEdgeLock(int i2) {
            return false;
        }

        public abstract void onViewDragStateChanged(int i2);

        public abstract void onViewPositionChanged(View view, int i2, int i7, int i8, int i10);

        public abstract void onViewReleased(View view, float f, float f5);

        public abstract boolean tryCaptureView(View view, int i2);

        public int getOrderedChildIndex(int i2) {
            return i2;
        }

        public void onEdgeDragStarted(int i2, int i7) {
        }

        public void onEdgeTouched(int i2, int i7) {
        }

        public void onViewCaptured(View view, int i2) {
        }
    }
}
