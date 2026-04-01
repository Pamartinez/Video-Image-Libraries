package com.samsung.android.gallery.app.ui.viewer2.remaster;

import B2.i;
import Qb.e;
import S7.a;
import android.graphics.Rect;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DivideHandler extends ViewerObject implements FragmentLifeCycle {
    private View mCircleHandler;
    private View mContentContainer;
    private boolean mIsGestureNaviBar;
    private PhotoViewCompat mPhotoView;
    private PhotoViewCompat mRemasterPhotoView;
    private View mSeparatorView;
    private float mTouchDownPx;
    private float mTouchDownViewX;
    private View mVerticalLine;
    private CoordinatorLayout mViewerLayout;

    private void clipBounds(float f) {
        int width = (int) (((float) this.mContentContainer.getWidth()) * f);
        Size displaySize = DeviceInfo.getDisplaySize(this.mModel.getContext());
        this.mRemasterPhotoView.setClipBounds(new Rect(width, 0, displaySize.getWidth(), displaySize.getHeight()));
    }

    /* access modifiers changed from: private */
    public void expandHandlerTouchArea(Object... objArr) {
        ViewUtils.setTouchAreaComposite(this.mCircleHandler, R.dimen.remaster_viewer_expanded_touch_area);
    }

    private int getScaledImageWidth() {
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (photoViewCompat == null) {
            return 0;
        }
        return (int) (this.mPhotoView.getCurrentScale() * ((float) photoViewCompat.getSourceWidth()));
    }

    private float getValidPoint(float f) {
        int i2;
        int width = this.mContentContainer.getWidth();
        int scaledImageWidth = getScaledImageWidth();
        if (this.mIsGestureNaviBar) {
            i2 = this.mCircleHandler.getWidth();
        } else {
            i2 = 0;
        }
        if (scaledImageWidth >= width) {
            return getValidPointOuter(f, width, i2);
        }
        return getValidPointInner(f, scaledImageWidth, width, i2);
    }

    private float getValidPointInner(float f, int i2, int i7, int i8) {
        int width = (this.mSeparatorView.getWidth() + i8) / 2;
        float f5 = (float) width;
        float f8 = (float) (i7 / 2);
        float f10 = ((float) i2) / 2.0f;
        float f11 = f8 + f10;
        if (f + f5 >= f11) {
            return Math.min((float) (i7 - width), f11 - f5);
        }
        float width2 = (float) ((this.mSeparatorView.getWidth() - i8) / 2);
        float f12 = f8 - f10;
        if (f + width2 <= f12) {
            return Math.max(((float) (-this.mSeparatorView.getWidth())) / 2.0f, f12 - width2);
        }
        return f;
    }

    private float getValidPointOuter(float f, int i2, int i7) {
        int i8;
        int width = (this.mSeparatorView.getWidth() + i7) / 2;
        if (this.mIsGestureNaviBar) {
            i8 = (-(this.mSeparatorView.getWidth() - this.mCircleHandler.getWidth())) / 2;
        } else {
            i8 = (-this.mSeparatorView.getWidth()) / 2;
        }
        return (float) Math.min((int) Math.max((float) i8, f), i2 - width);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        separateTo(Float.valueOf(0.5f));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        resetPosition();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetPosition$3() {
        separateTo(Float.valueOf(0.5f));
    }

    /* access modifiers changed from: private */
    public void onReplacedHandlerLayout(Object... objArr) {
        this.mCircleHandler.setOnTouchListener((View.OnTouchListener) null);
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
        this.mVerticalLine = this.mViewerLayout.findViewById(R.id.vertical_line);
        View findViewById = this.mViewerLayout.findViewById(R.id.circle_handler);
        this.mCircleHandler = findViewById;
        findViewById.setOnTouchListener(new i(12, this));
        separateTo(Float.valueOf(0.5f));
    }

    private void onScroll(float f) {
        float validPoint = getValidPoint(this.mTouchDownViewX + (f - this.mTouchDownPx));
        this.mSeparatorView.setX(validPoint);
        clipBounds(((((float) this.mSeparatorView.getWidth()) / 2.0f) + validPoint) / ((float) this.mContentContainer.getWidth()));
    }

    /* access modifiers changed from: private */
    public boolean onTouchScrollView(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            this.mTouchDownPx = motionEvent.getRawX();
            this.mTouchDownViewX = this.mSeparatorView.getX();
            ViewUtils.setAlpha(this.mVerticalLine, 0.5f);
            this.mActionInvoker.invoke(ViewerAction.REMASTER_HANDLER_TOUCH_DOWN, new Object[0]);
        } else if (action == 1) {
            ViewUtils.setAlpha(this.mVerticalLine, 1.0f);
            view.getParent().requestDisallowInterceptTouchEvent(false);
            this.mActionInvoker.invoke(ViewerAction.REMASTER_HANDLER_TOUCH_UP, new Object[0]);
        } else if (action == 2) {
            onScroll(motionEvent.getRawX());
        } else if (action == 3) {
            ViewUtils.setAlpha(this.mVerticalLine, 1.0f);
            this.mActionInvoker.invoke(ViewerAction.REMASTER_HANDLER_TOUCH_UP, new Object[0]);
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onZoomed(Object... objArr) {
        if (getScaledImageWidth() < this.mViewerLayout.getWidth()) {
            View view = this.mSeparatorView;
            view.setX(getValidPoint(view.getX()));
        }
    }

    private void resetPosition() {
        ViewUtils.post(this.mContentContainer, new e(11, this));
    }

    /* access modifiers changed from: private */
    public void separateTo(Object... objArr) {
        float floatValue = objArr[0].floatValue();
        this.mSeparatorView.setX(((float) (this.mContentContainer.getWidth() - this.mSeparatorView.getWidth())) * floatValue);
        clipBounds(floatValue);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new a(this, 0));
        this.mActionInvoker.add(ViewerAction.REMASTER_ON_SCALE_CHANGED, new a(this, 1));
        this.mActionInvoker.add(ViewerAction.REMASTER_VIEW_SCROLLED, new a(this, 2));
        this.mActionInvoker.add(ViewerAction.ON_HANDLER_MOVING_ANIMATION_ENDED, new a(this, 3));
        this.mActionInvoker.add(ViewerAction.ZOOM_WITH_TARGET_RECT, new a(this, 4));
        this.mActionInvoker.add(ViewerAction.REPLACED_HANDLER_LAYOUT, new a(this, 5));
        this.mActionInvoker.add(ViewerAction.RESET_REMASTER_HANDLER_POSITION, new a(this, 6));
    }

    public void onInitialized() {
        this.mPhotoView = (PhotoViewCompat) this.mViewerLayout.findViewById(R.id.photo_view);
        this.mRemasterPhotoView = (PhotoViewCompat) this.mViewerLayout.findViewById(R.id.remaster_photo_view);
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
        this.mVerticalLine = this.mViewerLayout.findViewById(R.id.vertical_line);
        this.mContentContainer = this.mViewerLayout.findViewById(R.id.content_container);
        this.mCircleHandler = this.mViewerLayout.findViewById(R.id.circle_handler);
        this.mIsGestureNaviBar = DeviceInfo.isGestureNaviBar(this.mModel.getActivity());
        this.mCircleHandler.setOnTouchListener(new i(12, this));
    }

    public void onTableModeChanged(boolean z, int i2) {
        resetPosition();
    }

    public void onViewDetached() {
        this.mRemasterPhotoView.setClipBounds((Rect) null);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mRemasterPhotoView.setClipBounds((Rect) null);
    }
}
