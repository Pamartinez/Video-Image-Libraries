package com.samsung.android.gallery.widget.details;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import java.util.Arrays;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsDragHelper {
    private static final Interpolator sInterpolator = PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f);
    private int mActivePointerId = -1;
    private final ViewDragHelper.Callback mCallback;
    private View mCapturedView;
    private int mDragState;
    private int[] mEdgeDragsInProgress;
    private int[] mEdgeDragsLocked;
    private final int mEdgeSize;
    private int[] mInitialEdgesTouched;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private final float mMaxVelocity;
    private final float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private final OverScroller mScroller;
    private final Runnable mSetIdleRunnable = new t(25, this);
    private final int mTouchSlop;
    private VelocityTracker mVelocityTracker;

    private DetailsDragHelper(Context context, ViewGroup viewGroup, ViewDragHelper.Callback callback) {
        this.mParentView = viewGroup;
        this.mCallback = callback;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = (float) viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.mScroller = new OverScroller(context, sInterpolator);
    }

    private boolean checkNewEdgeDrag(float f, float f5, int i2, int i7) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f5);
        if (!((this.mInitialEdgesTouched[i2] & i7) != i7 || (this.mEdgeDragsLocked[i2] & i7) == i7 || (this.mEdgeDragsInProgress[i2] & i7) == i7)) {
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

    private float clampMag(float f, float f5, float f8) {
        float abs = Math.abs(f);
        if (abs < f5) {
            return 0.0f;
        }
        if (abs <= f8) {
            return f;
        }
        if (f > 0.0f) {
            return f8;
        }
        return -f8;
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

    public static DetailsDragHelper create(ViewGroup viewGroup, ViewDragHelper.Callback callback) {
        return new DetailsDragHelper(viewGroup.getContext(), viewGroup, callback);
    }

    private void dispatchViewReleased(float f, float f5) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f5);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void dragTo(int i2, int i7, int i8, int i10) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        if (i8 != 0) {
            i2 = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, i2, i8);
            if (getViewDragState() != 2) {
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

    private View findTopChildUnder(int i2, int i7) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.mParentView.getChildAt(this.mCallback.getOrderedChildIndex(childCount));
            if (i2 >= childAt.getLeft() && i2 < childAt.getRight() && i7 >= childAt.getTop() && i7 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private boolean forceSettleCapturedViewAt(int i2, int i7) {
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i8 = i2 - left;
        int i10 = i7 - top;
        if (i8 == 0 && i10 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        this.mScroller.startScroll(left, top, i8, i10, 400);
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

    private boolean isCapturedViewUnder(int i2, int i7) {
        return isViewUnder(this.mCapturedView, i2, i7);
    }

    private boolean isInvalidPointerForActionMove(int i2) {
        if (isPointerDown(i2) && i2 != -1) {
            return false;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i2 + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return true;
    }

    private boolean isPointerDown(int i2) {
        if ((this.mPointersDown & (1 << i2)) != 0) {
            return true;
        }
        return false;
    }

    private boolean isViewUnder(View view, int i2, int i7) {
        if (view != null && i2 >= view.getLeft() && i2 < view.getRight() && i7 >= view.getTop() && i7 < view.getBottom()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        setDragState(0);
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
            if (!isInvalidPointerForActionMove(pointerId)) {
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
            if (left != 0) {
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

    public int getViewDragState() {
        return this.mDragState;
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
        } else if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
        } else if (actionMasked == 2) {
            if (this.mDragState != 1) {
                int pointerCount = motionEvent.getPointerCount();
                while (i7 < pointerCount) {
                    int pointerId2 = motionEvent.getPointerId(i7);
                    if (!isInvalidPointerForActionMove(pointerId2)) {
                        float x10 = motionEvent.getX(i7);
                        float y8 = motionEvent.getY(i7);
                        float f = x10 - this.mInitialMotionX[pointerId2];
                        float f5 = y8 - this.mInitialMotionY[pointerId2];
                        reportNewEdgeDrags(f, f5, pointerId2);
                        if (this.mDragState != 1) {
                            View findTopChildUnder2 = findTopChildUnder((int) x10, (int) y8);
                            if (checkTouchSlop(findTopChildUnder2, f, f5) && tryCaptureViewForDrag(findTopChildUnder2, pointerId2)) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    i7++;
                }
            } else if (!isInvalidPointerForActionMove(this.mActivePointerId)) {
                int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                float x11 = motionEvent.getX(findPointerIndex);
                float y9 = motionEvent.getY(findPointerIndex);
                float[] fArr = this.mLastMotionX;
                int i8 = this.mActivePointerId;
                int i10 = (int) (x11 - fArr[i8]);
                int i11 = (int) (y9 - this.mLastMotionY[i8]);
                dragTo(this.mCapturedView.getLeft() + i10, this.mCapturedView.getTop() + i11, i10, i11);
            } else {
                return;
            }
            saveLastMotion(motionEvent);
        } else if (actionMasked == 3) {
            if (this.mDragState == 1) {
                dispatchViewReleased(0.0f, 0.0f);
            }
            cancel();
        } else if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x12 = motionEvent.getX(actionIndex);
            float y10 = motionEvent.getY(actionIndex);
            saveInitialMotion(x12, y10, pointerId3);
            if (this.mDragState == 0) {
                tryCaptureViewForDrag(findTopChildUnder((int) x12, (int) y10), pointerId3);
            } else if (isCapturedViewUnder((int) x12, (int) y10)) {
                tryCaptureViewForDrag(this.mCapturedView, pointerId3);
            }
        } else if (actionMasked == 6) {
            int pointerId4 = motionEvent.getPointerId(actionIndex);
            if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
                int pointerCount2 = motionEvent.getPointerCount();
                while (true) {
                    if (i7 >= pointerCount2) {
                        i2 = -1;
                        break;
                    }
                    int pointerId5 = motionEvent.getPointerId(i7);
                    if (pointerId5 != this.mActivePointerId) {
                        View findTopChildUnder3 = findTopChildUnder((int) motionEvent.getX(i7), (int) motionEvent.getY(i7));
                        View view = this.mCapturedView;
                        if (findTopChildUnder3 == view && tryCaptureViewForDrag(view, pointerId5)) {
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
            clearMotionHistory(pointerId4);
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
            return forceSettleCapturedViewAt(i2, i7);
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c4, code lost:
        if (r12 != r11) goto L_0x00cd;
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
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x00e9
            if (r2 == r6) goto L_0x00e5
            if (r2 == r5) goto L_0x005e
            r7 = 3
            if (r2 == r7) goto L_0x00e5
            r7 = 5
            if (r2 == r7) goto L_0x003c
            r5 = 6
            if (r2 == r5) goto L_0x0033
            goto L_0x010a
        L_0x0033:
            int r1 = r1.getPointerId(r3)
            r0.clearMotionHistory(r1)
            goto L_0x010a
        L_0x003c:
            int r2 = r1.getPointerId(r3)
            float r7 = r1.getX(r3)
            float r1 = r1.getY(r3)
            r0.saveInitialMotion(r7, r1, r2)
            int r3 = r0.mDragState
            if (r3 != r5) goto L_0x010a
            int r3 = (int) r7
            int r1 = (int) r1
            android.view.View r1 = r0.findTopChildUnder(r3, r1)
            android.view.View r3 = r0.mCapturedView
            if (r1 != r3) goto L_0x010a
            r0.tryCaptureViewForDrag(r1, r2)
            goto L_0x010a
        L_0x005e:
            float[] r2 = r0.mInitialMotionX
            if (r2 == 0) goto L_0x010a
            float[] r2 = r0.mInitialMotionY
            if (r2 != 0) goto L_0x0068
            goto L_0x010a
        L_0x0068:
            int r2 = r1.getPointerCount()
            r3 = 0
        L_0x006d:
            if (r3 >= r2) goto L_0x00e1
            int r5 = r1.getPointerId(r3)
            boolean r7 = r0.isInvalidPointerForActionMove(r5)
            if (r7 == 0) goto L_0x007a
            goto L_0x00de
        L_0x007a:
            float r7 = r1.getX(r3)
            float r8 = r1.getY(r3)
            float[] r9 = r0.mInitialMotionX
            r9 = r9[r5]
            float r9 = r7 - r9
            float[] r10 = r0.mInitialMotionY
            r10 = r10[r5]
            float r10 = r8 - r10
            int r7 = (int) r7
            int r8 = (int) r8
            android.view.View r7 = r0.findTopChildUnder(r7, r8)
            boolean r8 = r0.checkTouchSlop(r7, r9, r10)
            if (r8 == 0) goto L_0x00cd
            int r11 = r7.getLeft()
            int r12 = (int) r9
            int r13 = r11 + r12
            androidx.customview.widget.ViewDragHelper$Callback r14 = r0.mCallback
            int r12 = r14.clampViewPositionHorizontal(r7, r13, r12)
            int r13 = r7.getTop()
            int r14 = (int) r10
            int r15 = r13 + r14
            androidx.customview.widget.ViewDragHelper$Callback r4 = r0.mCallback
            int r4 = r4.clampViewPositionVertical(r7, r15, r14)
            androidx.customview.widget.ViewDragHelper$Callback r14 = r0.mCallback
            int r14 = r14.getViewHorizontalDragRange(r7)
            androidx.customview.widget.ViewDragHelper$Callback r15 = r0.mCallback
            int r15 = r15.getViewVerticalDragRange(r7)
            if (r14 == 0) goto L_0x00c6
            if (r14 <= 0) goto L_0x00cd
            if (r12 != r11) goto L_0x00cd
        L_0x00c6:
            if (r15 == 0) goto L_0x00e1
            if (r15 <= 0) goto L_0x00cd
            if (r4 != r13) goto L_0x00cd
            goto L_0x00e1
        L_0x00cd:
            r0.reportNewEdgeDrags(r9, r10, r5)
            int r4 = r0.mDragState
            if (r4 != r6) goto L_0x00d5
            goto L_0x00e1
        L_0x00d5:
            if (r8 == 0) goto L_0x00de
            boolean r4 = r0.tryCaptureViewForDrag(r7, r5)
            if (r4 == 0) goto L_0x00de
            goto L_0x00e1
        L_0x00de:
            int r3 = r3 + 1
            goto L_0x006d
        L_0x00e1:
            r17.saveLastMotion(r18)
            goto L_0x010a
        L_0x00e5:
            r0.cancel()
            goto L_0x010a
        L_0x00e9:
            float r2 = r1.getX()
            float r3 = r1.getY()
            r4 = 0
            int r1 = r1.getPointerId(r4)
            r0.saveInitialMotion(r2, r3, r1)
            int r2 = (int) r2
            int r3 = (int) r3
            android.view.View r2 = r0.findTopChildUnder(r2, r3)
            android.view.View r3 = r0.mCapturedView
            if (r2 != r3) goto L_0x010a
            int r3 = r0.mDragState
            if (r3 != r5) goto L_0x010a
            r0.tryCaptureViewForDrag(r2, r1)
        L_0x010a:
            int r0 = r0.mDragState
            if (r0 != r6) goto L_0x010f
            return r6
        L_0x010f:
            r16 = 0
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.details.DetailsDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean smoothSlideViewTo(View view, int i2, int i7) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(i2, i7);
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
}
