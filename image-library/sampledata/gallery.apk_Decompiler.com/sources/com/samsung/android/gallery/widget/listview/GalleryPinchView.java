package com.samsung.android.gallery.widget.listview;

import Fa.F;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.R$styleable;
import com.samsung.android.gallery.widget.gesture.PinchGestureDetector;
import com.samsung.android.gallery.widget.listview.handler.AbsPinchAnimationHandler;
import com.samsung.android.gallery.widget.listview.handler.MultiDepthPinchAnimationHandler;
import com.samsung.android.gallery.widget.listview.handler.OneDepthPinchAnimationHandler;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.io.PrintWriter;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryPinchView extends GalleryListView {
    private AbsPinchAnimationHandler mActivePinchAnimation;
    private float[] mFocusXY = new float[2];
    private AbsPinchAnimationHandler mPinchListAnimationHandler;
    private boolean mSupportMultiDepth;
    private boolean mTabFocusBlockEnabled;

    public GalleryPinchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    private AbsPinchAnimationHandler getPinchListAnimationHandler() {
        AbsPinchAnimationHandler absPinchAnimationHandler;
        if (this.mPinchListAnimationHandler == null) {
            if (this.mSupportMultiDepth) {
                absPinchAnimationHandler = new MultiDepthPinchAnimationHandler(this);
            } else {
                absPinchAnimationHandler = new OneDepthPinchAnimationHandler(this);
            }
            this.mPinchListAnimationHandler = absPinchAnimationHandler;
        }
        return this.mPinchListAnimationHandler;
    }

    private boolean hasNoChild() {
        if (getChildCount() <= 0) {
            return true;
        }
        return false;
    }

    private boolean isResumed() {
        String str = (String) this.mBlackboard.read("lifecycle://last_activity_lifecycle");
        if (str == null || !str.equals("lifecycle://on_activity_resume")) {
            return false;
        }
        return true;
    }

    public void assertGridLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof GridLayoutManager)) {
            throw new AssertionError("layout manager should be LinearLayoutMnanager or child");
        }
    }

    public void changeDepth(int i2) {
        changeDepth(getLayoutManager(), i2);
    }

    public void checkPreviewCandidateAsync() {
        if (PreferenceFeatures.OneUi25.THUMBNAIL_PREVIEW) {
            Optional.ofNullable(getAdapter()).ifPresent(new F(13));
        }
    }

    public void completePinchAnimation() {
        resetTouch();
        this.mActivePinchAnimation = null;
    }

    public void dispatchCancelTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setAction(3);
        super.dispatchTouchEvent(obtain);
        obtain.recycle();
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mTabFocusBlockEnabled && !isInTouchMode()) {
            if (getLayoutManager().findLastVisibleItemPosition() < getItemCount() - 1) {
                this.mBlackboard.publish("data://bottomtab/focus", Boolean.TRUE);
                return super.dispatchKeyEvent(keyEvent);
            }
            this.mBlackboard.publish("data://bottomtab/focus", Boolean.FALSE);
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public void enableTabFocusBlock(boolean z) {
        this.mTabFocusBlockEnabled = z;
    }

    public View focusSearch(View view, int i2) {
        View findViewById;
        View focusSearch = super.focusSearch(view, i2);
        if (!isResumed() || view != focusSearch || i2 != 33 || getLayoutManager().findFirstVisibleItemPosition() != 0 || (findViewById = ((Activity) getContext()).findViewById(R$id.toolbar)) == null) {
            return focusSearch;
        }
        String str = this.TAG;
        Log.w(str, "focusSearch return toolbar@" + Logger.getSimpleName((Object) findViewById) + " activity@" + Logger.getSimpleName((Object) getContext()));
        return findViewById;
    }

    public float[] getFocusedXY() {
        return this.mFocusXY;
    }

    public boolean handleActivePinchAnimation(MotionEvent motionEvent) {
        if (this.mActivePinchAnimation == null) {
            return false;
        }
        updateTouch(motionEvent);
        return true;
    }

    public void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.PinchListView);
            this.mSupportMultiDepth = obtainStyledAttributes.getBoolean(R$styleable.PinchListView_supportMultiDepth, false);
            obtainStyledAttributes.recycle();
        }
    }

    public boolean isAnimating() {
        if (isOngoingPinchAnimation() || super.isAnimating()) {
            return true;
        }
        return false;
    }

    public boolean isListSelectable() {
        if (isOngoingPinchAnimation() || !super.isListSelectable()) {
            return false;
        }
        return true;
    }

    public boolean isOngoingPinchAnimation() {
        if (this.mActivePinchAnimation != null) {
            return true;
        }
        AbsPinchAnimationHandler absPinchAnimationHandler = this.mPinchListAnimationHandler;
        if (absPinchAnimationHandler == null || !absPinchAnimationHandler.isAnimating()) {
            return false;
        }
        return true;
    }

    public void onDump(PrintWriter printWriter, String str) {
        super.onDump(printWriter, str);
        Logger.dumpLog(printWriter, "mOngoingPinchAnimation : " + this.mActivePinchAnimation);
        Logger.dumpLog(printWriter, "mTabFocusBlockEnabled : " + this.mTabFocusBlockEnabled);
        AbsPinchAnimationHandler absPinchAnimationHandler = this.mPinchListAnimationHandler;
        if (absPinchAnimationHandler != null) {
            absPinchAnimationHandler.onDump(printWriter, str);
        }
    }

    public void onGridChanged() {
        updateDecoIcons();
        checkPreviewCandidateAsync();
    }

    public void onLayout(boolean z, int i2, int i7, int i8, int i10) {
        if (z && getAdapter() != null) {
            getAdapter().onLayoutChanged(this, i2, i7, i8, i10);
        }
        super.onLayout(z, i2, i7, i8, i10);
        AbsPinchAnimationHandler absPinchAnimationHandler = this.mPinchListAnimationHandler;
        if (absPinchAnimationHandler != null) {
            absPinchAnimationHandler.onLayout();
        }
    }

    public void onScale(PinchGestureDetector pinchGestureDetector, float f) {
        AbsPinchAnimationHandler absPinchAnimationHandler = this.mActivePinchAnimation;
        if (absPinchAnimationHandler != null) {
            absPinchAnimationHandler.onScale(f, pinchGestureDetector, getColumnCount(), getMaxColumnCount());
        }
    }

    public boolean onScaleBegin(float[] fArr) {
        String str = this.TAG;
        Log.d(str, "onScaleBegin {" + getChildCount() + "} " + this.mActivePinchAnimation);
        if (isAnimating() || hasNoChild() || isSimilarModeAnimating()) {
            return false;
        }
        hideScrollerPopup();
        this.mActivePinchAnimation = getPinchListAnimationHandler();
        this.mFocusXY = fArr;
        return true;
    }

    public void onScaleEnd() {
        String str = this.TAG;
        Log.d(str, "onScaleEnd {" + getChildCount() + "} " + this.mActivePinchAnimation);
        AbsPinchAnimationHandler absPinchAnimationHandler = this.mActivePinchAnimation;
        if (absPinchAnimationHandler != null) {
            absPinchAnimationHandler.onScaleEnd();
        }
        float[] fArr = this.mFocusXY;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        assertGridLayoutManager(layoutManager);
        super.setLayoutManager(layoutManager);
    }

    public void setPinchAnimationManager(PinchAnimationManager pinchAnimationManager) {
        if (pinchAnimationManager != null) {
            AbsPinchAnimationHandler pinchListAnimationHandler = getPinchListAnimationHandler();
            pinchListAnimationHandler.setPinchAnimationManager(pinchAnimationManager);
            pinchAnimationManager.setPinchAnimationInterface(pinchListAnimationHandler);
        }
    }

    public void setScale(boolean z) {
        String str;
        if (z) {
            str = "in";
        } else {
            str = "out";
        }
        Log.majorEvent("scale with pinch ".concat(str));
        if (z) {
            increaseSpans(getLayoutManager());
        } else {
            decreaseSpans(getLayoutManager());
        }
    }

    public void startMonthForViewingClickedAnimation(int i2, RectF rectF) {
        getPinchListAnimationHandler().startMonthForViewingClickedAnimation(i2, rectF);
    }

    public void startYearClickedAnimation(int i2, RectF rectF) {
        getPinchListAnimationHandler().startYearClickedAnimation(i2, rectF);
    }

    public void updateDecoIcons() {
        char c5;
        char c6;
        GalleryListAdapter adapter = getAdapter();
        if (adapter == null) {
            Log.e(this.TAG, "Adapter is NULL, Fragment already destroyed...");
            return;
        }
        boolean z = adapter.mHideDecoIcons;
        adapter.setGridSize(getColumnCount(), getHideDecoGrid());
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("updateDecoIcons {");
        if (isSelectionMode()) {
            c5 = 'S';
        } else {
            c5 = 'N';
        }
        sb2.append(c5);
        if (adapter.mHideDecoIcons) {
            c6 = 'H';
        } else {
            c6 = 'D';
        }
        sb2.append(c6);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getColumnCount());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getMaxColumnCount());
        sb2.append("}");
        Log.d(str, sb2.toString());
        if (isSelectionMode()) {
            adapter.notifySelectedItemChanged();
        }
        if (z != adapter.mHideDecoIcons) {
            adapter.notifyItemRangeChanged("updateDecoView");
        }
    }

    public GridLayoutManager getLayoutManager() {
        return (GridLayoutManager) super.getLayoutManager();
    }

    public void startMonthForViewingClickedAnimation(int i2, float f, float f5) {
        getPinchListAnimationHandler().startMonthForViewingClickedAnimation(i2, f, f5);
    }
}
