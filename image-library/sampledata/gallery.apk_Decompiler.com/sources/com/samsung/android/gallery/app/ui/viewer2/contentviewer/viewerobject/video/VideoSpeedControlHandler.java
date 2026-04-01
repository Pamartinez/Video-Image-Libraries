package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import Fb.h;
import H3.l;
import H7.D;
import H7.E;
import H7.q;
import android.app.Activity;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.tip.PopupTipBuilder;
import com.samsung.android.gallery.widget.videoview.controller.VideoSpeedControllerCompat;
import com.sec.android.gallery3d.R;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.IntSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoSpeedControlHandler extends ViewerObject implements FragmentLifeCycle {
    private static final float[] mSpeedArray = {0.5f, 1.0f, 2.0f};
    private final Runnable mChangeCompletedRunnable = new l(6, this);
    private final VideoSpeedControllerCompat.ChangeSpeedListener mChangeSpeedListener = new h(15, this);
    private float mChangedLevelStartX;
    private Integer mLastSpeedLevel = null;
    private final AtomicBoolean mOnceInit = new AtomicBoolean(false);
    private final AtomicBoolean mOnceSpeedChanged = new AtomicBoolean(false);
    private View mPlayControlView;
    private float mStartX;
    /* access modifiers changed from: private */
    public VideoSpeedControllerCompat mVideoSpeedControllerCompat;

    private void changeSpeedLevel(int i2) {
        this.mLastSpeedLevel = Integer.valueOf(i2);
        this.mVideoSpeedControllerCompat.updateSelected(i2);
        this.mActionInvoker.invoke(ViewerAction.VIDEO_SPEED_ON_CHANGED, Float.valueOf(mSpeedArray[this.mLastSpeedLevel.intValue()]));
    }

    private VideoSpeedControllerCompat createVideoSpeedControllerCompat() {
        View view = this.mModel.getContainerModel().getView();
        if (view != null) {
            return new VideoSpeedControllerCompat((ViewGroup) view, R.layout.video_speed_controller_layout, R.id.video_speed_controller_main).setMargin((IntSupplier) null, new q(1, this), (IntSupplier) null, (IntSupplier) null).setViewIdListDismissWhenClick(new int[]{R.id.video_speed_controller_root_view}).setIgnoreRootViewTouch(true).setHideRootViewOnTouch(true).setControlActionListener(this.mChangeSpeedListener).setDismissListener(new PopupTipBuilder.OnDismissListener() {
                public void onDismiss() {
                    VideoSpeedControlHandler.this.mVideoSpeedControllerCompat = null;
                }

                public void onHide() {
                    VideoSpeedControlHandler.this.lambda$new$2();
                }
            }).build().attach();
        }
        Log.e(this.TAG, "failed to create speed controller, view is null");
        return null;
    }

    /* access modifiers changed from: private */
    public int getControllerTopMargin() {
        return this.mPlayControlView.getTop() - (getDimensionPixelOffset(R.dimen.video_speed_control_layout_height) - getDimensionPixelOffset(R.dimen.play_button_view_height));
    }

    private String getDetailId() {
        int i2;
        Integer num = this.mLastSpeedLevel;
        if (num != null) {
            i2 = num.intValue();
        } else {
            i2 = 1;
        }
        if (i2 == 0) {
            return AnalyticsDetail.EVENT_DETAIL_VIDEO_SPEED_050X.toString();
        }
        if (i2 == 2) {
            return AnalyticsDetail.EVENT_DETAIL_VIDEO_SPEED_200X.toString();
        }
        return AnalyticsDetail.EVENT_DETAIL_VIDEO_SPEED_100X.toString();
    }

    private int getDimensionPixelOffset(int i2) {
        Resources resources;
        Activity activity = this.mModel.getActivity();
        if (activity != null) {
            resources = activity.getResources();
        } else {
            resources = null;
        }
        if (resources != null) {
            return resources.getDimensionPixelOffset(i2);
        }
        return 0;
    }

    private void handleActionMoveEvent(MotionEvent motionEvent) {
        float x9 = motionEvent.getX() - this.mStartX;
        if (Math.abs(x9) > 100.0f) {
            boolean z = false;
            if (!Features.isEnabled(Features.IS_RTL) ? x9 > 0.0f : x9 <= 0.0f) {
                z = true;
            }
            if (this.mChangedLevelStartX != 0.0f && Math.abs(motionEvent.getX() - this.mChangedLevelStartX) <= 100.0f) {
                return;
            }
            if (z && this.mLastSpeedLevel.intValue() < 2) {
                this.mLastSpeedLevel = Integer.valueOf(this.mLastSpeedLevel.intValue() + 1);
                this.mChangedLevelStartX = motionEvent.getX();
                changeSpeedLevel(this.mLastSpeedLevel.intValue());
                this.mOnceSpeedChanged.set(true);
            } else if (this.mLastSpeedLevel.intValue() > 0) {
                this.mLastSpeedLevel = Integer.valueOf(this.mLastSpeedLevel.intValue() - 1);
                this.mChangedLevelStartX = motionEvent.getX();
                changeSpeedLevel(this.mLastSpeedLevel.intValue());
                this.mOnceSpeedChanged.set(true);
            }
        } else if (this.mOnceSpeedChanged.get() && this.mLastSpeedLevel.intValue() != 1) {
            Integer num = 1;
            this.mLastSpeedLevel = num;
            changeSpeedLevel(num.intValue());
        }
    }

    private void handleActionUpEvent(MotionEvent motionEvent) {
        if (Math.abs(this.mStartX - motionEvent.getX()) > 100.0f) {
            this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.FALSE);
        } else {
            Log.w((CharSequence) this.TAG, "handleActionUpEvent : under threshold", Float.valueOf(this.mStartX), Float.valueOf(motionEvent.getRawX()), Float.valueOf(motionEvent.getX()));
        }
        resetValue(false);
        hideVideoSpeedControllerOnDelay(3000);
    }

    /* access modifiers changed from: private */
    /* renamed from: hideVideoSpeedController */
    public void lambda$new$2() {
        VideoSpeedControllerCompat videoSpeedControllerCompat = this.mVideoSpeedControllerCompat;
        if (videoSpeedControllerCompat != null && videoSpeedControllerCompat.isShowing()) {
            this.mVideoSpeedControllerCompat.show(false);
            this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.TRUE);
            this.mModel.setVideoSpeedChangeOnGoing(false);
            this.mActionInvoker.invoke(ViewerAction.VIDEO_SPEED_CHANGE_COMPLETED, new Object[0]);
            postAnalyticsLog();
        }
    }

    private void hideVideoSpeedControllerOnDelay(int i2) {
        ThreadUtil.removeCallbackOnUiThread(this.mChangeCompletedRunnable);
        ThreadUtil.postOnUiThreadDelayed(this.mChangeCompletedRunnable, (long) i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPlayControlView = objArr[0].findViewById(R.id.video_controller_view);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        onControllerViewOnTouched(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(int i2) {
        Integer valueOf = Integer.valueOf(i2);
        this.mLastSpeedLevel = valueOf;
        this.mActionInvoker.invoke(ViewerAction.VIDEO_SPEED_ON_CHANGED, Float.valueOf(mSpeedArray[valueOf.intValue()]));
        hideVideoSpeedControllerOnDelay(100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onFinalized$5(VideoSpeedControllerCompat videoSpeedControllerCompat) {
        this.mVideoSpeedControllerCompat.resetControllerView();
        this.mVideoSpeedControllerCompat.dismiss();
        this.mVideoSpeedControllerCompat = null;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refresh$4(VideoSpeedControllerCompat videoSpeedControllerCompat) {
        ThreadUtil.removeCallbackOnUiThread(this.mChangeCompletedRunnable);
        lambda$new$2();
        videoSpeedControllerCompat.resetControllerView();
        this.mOnceInit.set(false);
        videoSpeedControllerCompat.refresh();
        setSpeedControllerBinding();
        changeSpeedLevel(this.mLastSpeedLevel.intValue());
    }

    private boolean onControllerViewOnTouched(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action != 1) {
            if (action == 2) {
                handleActionMoveEvent(motionEvent);
                return true;
            } else if (action != 3) {
                return false;
            }
        }
        handleActionUpEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: private */
    public void onVideoSpeedControllerStart(Object... objArr) {
        this.mStartX = objArr[0].floatValue();
        if (this.mVideoSpeedControllerCompat == null) {
            VideoSpeedControllerCompat createVideoSpeedControllerCompat = createVideoSpeedControllerCompat();
            this.mVideoSpeedControllerCompat = createVideoSpeedControllerCompat;
            if (createVideoSpeedControllerCompat == null) {
                Log.w(this.TAG, "mVideoSpeedControllerCompat is null!!");
                return;
            }
            setSpeedControllerBinding();
        }
        showVideoSpeedController();
    }

    private void postAnalyticsLog() {
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        if (eventContext != null) {
            AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_SPEED_CHANGE_CONTROLLER.toString(), getDetailId());
        }
    }

    private void refresh() {
        Optional.ofNullable(this.mVideoSpeedControllerCompat).ifPresent(new D(this, 0));
    }

    private void resetValue() {
        resetValue(true);
    }

    private void setSpeedControllerBinding() {
        if (!this.mOnceInit.getAndSet(true)) {
            this.mVideoSpeedControllerCompat.setSpeedControllerBinding();
            if (this.mLastSpeedLevel == null) {
                this.mLastSpeedLevel = 1;
            }
        }
    }

    private void showVideoSpeedController() {
        changeSpeedLevel(this.mLastSpeedLevel.intValue());
        this.mOnceSpeedChanged.set(false);
        this.mVideoSpeedControllerCompat.show(true);
        this.mModel.setVideoSpeedChangeOnGoing(true);
        this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.FALSE);
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_CONTROLLER_VIEW, new E(this, 0));
        this.mActionInvoker.add(ViewerAction.START_VIDEO_SPEED_CONTROLLER, new E(this, 1));
        this.mActionInvoker.add(ViewerAction.DISPATCH_MOTION_EVENT_TO_VIDEO_SPEED_CONTROLLER, new E(this, 2));
    }

    public void onApplyWindowInsets() {
        refresh();
    }

    public void onFinalized() {
        super.onFinalized();
        Optional.ofNullable(this.mVideoSpeedControllerCompat).ifPresent(new D(this, 1));
    }

    public void onPause() {
        resetValue();
    }

    public void onViewDetached() {
        resetValue();
    }

    private void resetValue(boolean z) {
        if (z) {
            Integer num = 1;
            this.mLastSpeedLevel = num;
            this.mActionInvoker.invoke(ViewerAction.VIDEO_SPEED_ON_CHANGED, Float.valueOf(mSpeedArray[num.intValue()]));
        }
        this.mChangedLevelStartX = 0.0f;
        this.mStartX = 0.0f;
        this.mOnceSpeedChanged.set(false);
    }
}
