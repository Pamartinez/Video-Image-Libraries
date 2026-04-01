package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import H3.l;
import H7.A;
import H7.C;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoSeekController extends VideoController implements FragmentLifeCycle {
    protected IMediaPlayerView mMediaPlayerView;
    protected SeekBar mSeekBar;
    private final SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        private int mLastProgress;

        public void onProgressChanged(SeekBar seekBar, int i2, boolean z) {
            if (z) {
                if (VideoSeekController.this.isPlayState()) {
                    VideoSeekController videoSeekController = VideoSeekController.this;
                    if (videoSeekController.mDuration > 0) {
                        videoSeekController.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK, Float.valueOf(((float) i2) / ((float) VideoSeekController.this.mDuration)), VideoSeekController.this.mModel.getMediaItem());
                        VideoSeekController.this.updateTimeStamp((long) i2);
                        return;
                    }
                }
                seekBar.setProgress(this.mLastProgress);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            if (VideoSeekController.this.isPlayState()) {
                VideoSeekController.this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new Object[0]);
            } else {
                this.mLastProgress = seekBar.getProgress();
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            if (VideoSeekController.this.isPlayState()) {
                VideoSeekController.this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new Object[0]);
            }
        }
    };
    private long mTimeStampInSec = -1;

    /* access modifiers changed from: private */
    public boolean isPlayState() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            return iMediaPlayerView.isInPlayState();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        setControllerBinding(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPlayTimeUpdated$2() {
        updateSeekBar(this.mPosition);
    }

    private void updateSeekBar(int i2) {
        int max = Math.max(i2, 0);
        updateTimeStamp((long) max);
        this.mSeekBar.setProgress(max);
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.VIDEO_SEEK_CONTROLLER_VIEW, new C(this, 0));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new C(this, 1));
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.VIDEO_SEEK_CONTROLLER_VIEW_INFLATE, new Object[0]);
    }

    public void onPlayTimeUpdated(Object... objArr) {
        this.mDuration = objArr[0].intValue();
        this.mPosition = Math.max(objArr[1].intValue(), 0);
        this.mThread.runOnUiThread(new l(5, this));
    }

    public void onVideoStarted(Object... objArr) {
        super.onVideoStarted(objArr);
        SeekBar seekBar = this.mSeekBar;
        if (seekBar != null) {
            seekBar.setMax(this.mDuration);
        }
    }

    public void setControllerBinding(View view) {
        this.mVideoControllerView = view.findViewById(R.id.video_seek_controller);
        this.mElapsedTextView = (TextView) view.findViewById(R.id.text_elapsed);
        this.mDurationTextView = (TextView) view.findViewById(R.id.text_duration);
        this.mPlayPauseButton = (ImageButton) view.findViewById(R.id.play_pause_button);
        this.mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        this.mPlayPauseButton.setOnClickListener(new A(1, this));
        this.mSeekBar.setOnSeekBarChangeListener(this.mSeekBarChangeListener);
        lambda$onVideoStarted$1(true);
    }

    public void updateTimeStamp(long j2) {
        long j3 = j2 / 1000;
        if (this.mTimeStampInSec != j3) {
            this.mTimeStampInSec = j3;
            this.mElapsedTextView.setText(TimeUtil.getIsoLocalTime(j2));
        }
    }
}
