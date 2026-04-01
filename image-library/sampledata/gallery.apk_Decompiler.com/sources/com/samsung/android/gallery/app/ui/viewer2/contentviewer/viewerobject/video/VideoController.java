package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import B2.i;
import B4.c;
import E6.a;
import H7.A;
import H7.B;
import H7.y;
import H7.z;
import android.content.res.Configuration;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoController extends ViewerObject implements FragmentLifeCycle {
    protected int mDuration = 0;
    protected TextView mDurationTextView;
    protected TextView mElapsedTextView;
    protected boolean mIsPlaying;
    private boolean mLongPressed;
    protected ImageButton mPlayPauseButton;
    protected TextView mPlayVideoTextView;
    protected int mPosition = 0;
    protected TextView mSlashView;
    private float mStartX;
    protected View mTimeTextLayout;
    /* access modifiers changed from: protected */
    public View mVideoControllerView;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        setControllerBinding(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoError$3() {
        lambda$onVideoStarted$1(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoStopped$2() {
        lambda$onVideoStarted$1(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateTextView$4(int i2, int i7) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!LocationKey.isHighlightGroupPanelView(this.mModel.getContainerModel().getLocationKey()) || mediaItem == null) {
            this.mElapsedTextView.setText(TimeUtil.getIsoLocalTime((long) i2));
            this.mDurationTextView.setText(TimeUtil.getIsoLocalTime((long) i7));
            return;
        }
        this.mElapsedTextView.setText(TimeUtil.getIsoLocalTime((long) ((int) Math.max(0, ((long) i2) - (mediaItem.getVideoThumbStartTime() / 1000)))));
        this.mDurationTextView.setText(TimeUtil.getIsoLocalTime((long) ((int) Math.max(0, (MediaItemUtil.getHighLightClipEndTime(mediaItem) - mediaItem.getVideoThumbStartTime()) / 1000))));
    }

    /* access modifiers changed from: private */
    public boolean onPlayPauseViewLongClicked(View view) {
        if (!supportVideoSpeedController()) {
            return false;
        }
        this.mLongPressed = true;
        this.mActionInvoker.invoke(ViewerAction.START_VIDEO_SPEED_CONTROLLER, Float.valueOf(this.mStartX));
        this.mActionInvoker.invoke(ViewerAction.UPDATE_VIEWER_DECOR_BOTTOM_LAYOUT_VISIBILITY, Boolean.FALSE, Boolean.TRUE);
        return false;
    }

    /* access modifiers changed from: private */
    public boolean onPlayPauseViewOnTouched(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mStartX = motionEvent.getX();
        } else if ((action == 1 || (action != 2 && action == 3)) && this.mLongPressed) {
            this.mLongPressed = false;
            this.mActionInvoker.invoke(ViewerAction.DISPATCH_MOTION_EVENT_TO_VIDEO_SPEED_CONTROLLER, motionEvent);
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        float floatValue = objArr[0].floatValue();
        int i2 = this.mDuration;
        updateTextView(i2, Math.round(((float) i2) * floatValue));
    }

    /* access modifiers changed from: private */
    public void onVideoSpeedChangeCompleted(Object... objArr) {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.UPDATE_VIEWER_DECOR_BOTTOM_LAYOUT_VISIBILITY;
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, bool, bool);
        lambda$onVideoStarted$1(this.mIsPlaying);
    }

    private void setInternalViewVisibility() {
        if (LocationKey.isSuperSlowGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            ViewUtils.setVisibility(this.mPlayVideoTextView, 0);
            ViewUtils.setVisibility(this.mTimeTextLayout, 8);
        }
    }

    private boolean supportVideoSpeedController() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER) {
            if (this.mModel.isInstantSlowMoPlayEnabled()) {
                Log.w(this.TAG, "supportVideoSpeedController : failed (instant slow-mo enabled");
                return false;
            }
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (mediaItem == null || FoldUtils.isFlipCoverScreen(this.mModel.getActivity()) || RecordingMode.isSlowMo(mediaItem.getRecordingMode()) || MediaItemUtil.isSuperSlowMotion(mediaItem) || mediaItem.isMotionPhoto() || MediaItemUtil.isHighLightClip(mediaItem)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIDEO_CONTROLLER_VIEW, new y(this, 0));
        this.mActionInvoker.add(ViewerAction.VIDEO_CONTROLLER_VISIBILITY_CHANGE, new y(this, 1));
        this.mActionInvoker.add(ViewerAction.VIDEO_STARTED, new y(this, 2));
        this.mActionInvoker.add(ViewerAction.VIDEO_STOPPED, new y(this, 3));
        this.mActionInvoker.add(ViewerAction.VIDEO_ERROR, new y(this, 4));
        this.mActionInvoker.add(ViewerAction.PLAY_TIME_UPDATED, new y(this, 5));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK, new y(this, 6));
        if (PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER) {
            this.mActionInvoker.add(ViewerAction.VIDEO_SPEED_CHANGE_COMPLETED, new y(this, 7));
        }
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.VIDEO_CONTROLLER_VIEW_INFLATE, new Object[0]);
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewUtils.postOnGlobalLayout(this.mVideoControllerView, new z(this, 2));
    }

    public void onPlayPauseViewClicked(View view) {
        String str;
        if (this.mModel.isVideoSpeedChangeOnGoing()) {
            Log.d(this.TAG, "ignore onPlayPauseViewClicked because of video speed change on going");
        } else if (this.mModel.isUnsupportedVideo()) {
            Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
        } else {
            this.mActionInvoker.invoke(ViewerAction.PLAY_PAUSE_CLICKED, Boolean.valueOf(!this.mIsPlaying));
            EventContext eventContext = this.mModel.getContainerModel().getEventContext();
            if (eventContext != null) {
                AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_PAUSE.toString());
            }
            if (this.mIsPlaying) {
                str = "pauseVideo";
            } else {
                str = "playVideo";
            }
            Log.majorEvent(str);
        }
    }

    public void onPlayTimeUpdated(Object... objArr) {
        this.mDuration = objArr[0].intValue();
        int max = Math.max(objArr[1].intValue(), 0);
        this.mPosition = max;
        updateTextView(this.mDuration, max);
    }

    public void onVideoError(Object... objArr) {
        this.mThread.runOnUiThread(new z(this, 1));
    }

    public void onVideoStarted(Object... objArr) {
        this.mThread.runOnUiThread(new c((Object) this, objArr[0].booleanValue(), 10));
    }

    public void onVideoStopped(Object... objArr) {
        this.mThread.runOnUiThread(new z(this, 0));
    }

    public void onViewAttached() {
        super.onViewAttached();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if ((PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer() || this.mModel.getStateHelper().isForcePlayVideoInGallery()) && mediaItem != null && !this.mModel.isSingleTakenShot() && MediaItemUtil.supportPreviewPlay(mediaItem)) {
            updateTextView(mediaItem.getFileDuration(), this.mPosition);
            lambda$onVideoStarted$1(false);
            return;
        }
        ViewUtils.setVisibility(this.mVideoControllerView, 8);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mDuration = 0;
        this.mPosition = 0;
    }

    public void setClickTouchListener() {
        if (PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER) {
            this.mVideoControllerView.setOnLongClickListener(new a(1, this));
            this.mVideoControllerView.setOnTouchListener(new i(10, this));
        }
        this.mVideoControllerView.setOnClickListener(new A(0, this));
    }

    public void setControllerBinding(View view) {
        this.mVideoControllerView = view.findViewById(R.id.video_controller_view);
        this.mElapsedTextView = (TextView) view.findViewById(R.id.text_elapsed);
        this.mTimeTextLayout = view.findViewById(R.id.time_text_layout);
        this.mDurationTextView = (TextView) view.findViewById(R.id.text_duration);
        this.mPlayPauseButton = (ImageButton) view.findViewById(R.id.play_pause_button);
        this.mPlayVideoTextView = (TextView) view.findViewById(R.id.play_video_text_view);
        this.mSlashView = (TextView) view.findViewById(R.id.text_duration_slash);
        setClickTouchListener();
        lambda$onVideoStarted$1(true);
        updateTouchArea();
        setInternalViewVisibility();
    }

    /* renamed from: setPlayPauseButton */
    public void lambda$onVideoStarted$1(boolean z) {
        int i2;
        int i7;
        this.mIsPlaying = z;
        if (!this.mLongPressed) {
            ImageButton imageButton = this.mPlayPauseButton;
            if (z) {
                i2 = R.drawable.gallery_ic_detail_pause;
            } else {
                i2 = R.drawable.gallery_ic_detail_play;
            }
            imageButton.setImageResource(i2);
            View view = this.mVideoControllerView;
            StringBuilder sb2 = new StringBuilder();
            if (z) {
                i7 = R.string.pause;
            } else {
                i7 = R.string.play;
            }
            sb2.append(AppResources.getString(i7));
            sb2.append(" ");
            sb2.append(AppResources.getString(R.string.speak_button));
            view.setContentDescription(sb2.toString());
        }
    }

    public void setVideoControllerVisibility(Object... objArr) {
        ViewUtils.setVisibility(this.mVideoControllerView, objArr[0].intValue());
        this.mVideoControllerView.requestLayout();
    }

    public void updateTextView(int i2, int i7) {
        this.mThread.runOnUiThread(new B(this, i7, i2, 0));
    }

    public void updateTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mVideoControllerView, R.dimen.decor_button_touch_area);
    }
}
