package com.samsung.android.gallery.app.ui.list.stories.recap.delegate;

import H.a;
import H6.g;
import H6.h;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.recap.IRecapView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerViewDelegate extends Delegate implements MediaPlayerListener {
    private boolean mIsAudioMute;
    private MediaItem mMediaItem;
    private IMediaPlayerView mMediaPlayerView;
    private Bitmap mPreviewBitmap;
    private int mSeekPosition;
    private ImageView mTransitionView;
    private boolean mViewConfirmed = false;

    public MediaPlayerViewDelegate(IRecapView iRecapView) {
        super(iRecapView);
    }

    private boolean isViewReady() {
        if (this.mMediaPlayerView != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$0() {
        ViewUtils.setVisibility(this.mTransitionView, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showPreview$1(Bitmap bitmap) {
        boolean z;
        ImageView imageView = this.mTransitionView;
        boolean z3 = false;
        if (imageView == null || bitmap == null) {
            String str = this.TAG;
            if (bitmap != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mTransitionView != null) {
                z3 = true;
            }
            Log.e((CharSequence) str, "show preview failed", valueOf, Boolean.valueOf(z3));
            return;
        }
        imageView.setImageBitmap(bitmap);
        this.mTransitionView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void onEnterTransitionEnd(Object... objArr) {
        Log.d(this.TAG, "onEnterTransitionEnd");
        this.mViewConfirmed = true;
        ImageView imageView = this.mTransitionView;
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                this.mPreviewBitmap = ((BitmapDrawable) drawable).getBitmap();
            }
        }
        this.mIsAudioMute = this.mView.getOptions().isAudioDefaultMuted();
        playVideo();
    }

    /* access modifiers changed from: private */
    public void onPlayPauseButtonClicked(Object... objArr) {
        AnalyticsDetail analyticsDetail;
        boolean booleanValue = objArr[0].booleanValue();
        if (booleanValue) {
            this.mMediaPlayerView.pauseVideo();
        } else {
            resumeVideo();
        }
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_PLAY_BUTTON;
        if (booleanValue) {
            analyticsDetail = AnalyticsDetail.STORY_HIGHLIGHT_PAUSE;
        } else {
            analyticsDetail = AnalyticsDetail.STORY_HIGHLIGHT_PLAY;
        }
        postAnalyticsLog(analyticsEventId, analyticsDetail);
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        int intValue = objArr[0].intValue();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.visualSeekTo(intValue);
            this.mSeekPosition = intValue;
        }
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeekBegin(Object... objArr) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.seekBegin();
        }
        ViewUtils.setVisibility(this.mTransitionView, 8);
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeekFinish(Object... objArr) {
        Log.d(this.TAG, "seekFinish");
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.seekFinish();
        }
    }

    /* access modifiers changed from: private */
    public void onUserAudioMute(Object... objArr) {
        AnalyticsDetail analyticsDetail;
        boolean booleanValue = objArr[0].booleanValue();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            this.mIsAudioMute = booleanValue;
            iMediaPlayerView.setAudioMute(booleanValue);
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_STORY_HIGHLIGHT_AUDIO_TOGGLE;
            if (booleanValue) {
                analyticsDetail = AnalyticsDetail.EVENT_AUDIO_MUTE;
            } else {
                analyticsDetail = AnalyticsDetail.EVENT_AUDIO_UNMUTE;
            }
            postAnalyticsLog(analyticsEventId, analyticsDetail);
        }
    }

    private boolean openVideo(MediaItem mediaItem, int i2) {
        if (this.mMediaPlayerView.isOpened()) {
            this.mMediaPlayerView.close();
        }
        this.mMediaPlayerView.setSupportTimeTick(true);
        this.mMediaPlayerView.setAudioMute(this.mIsAudioMute, true);
        if (!this.mMediaPlayerView.open(mediaItem)) {
            return false;
        }
        if (i2 > 0) {
            this.mMediaPlayerView.setRenderingPosition(i2);
        }
        return true;
    }

    private Bitmap pauseAndCapturedBitmap() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null) {
            return null;
        }
        if (iMediaPlayerView.isPlaying()) {
            this.mMediaPlayerView.pauseVideo();
        }
        return this.mMediaPlayerView.getViewBitmap();
    }

    /* access modifiers changed from: private */
    public void playHideAnimation() {
        boolean z;
        ImageView imageView = this.mTransitionView;
        boolean z3 = true;
        if (imageView == null || this.mMediaPlayerView == null) {
            String str = this.TAG;
            if (imageView != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mMediaPlayerView == null) {
                z3 = false;
            }
            Log.e((CharSequence) str, "skip hide animation", valueOf, Boolean.valueOf(z3));
            ViewUtils.setVisibility(this.mTransitionView, 8);
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView != null) {
                ViewUtils.setVisibility(iMediaPlayerView.getView(), 8);
            }
            this.mEventHandler.postEvent(Event.SLIDE_SHOW_DONE, new Object[0]);
            return;
        }
        Log.d(this.TAG, "hide animation start");
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{new AlphaAnimator((View) this.mTransitionView, 1.0f, 0.0f), new AlphaAnimator(this.mMediaPlayerView.getView(), 1.0f, 0.0f)});
        animatorSet.setDuration(660);
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator) {
                Log.d(MediaPlayerViewDelegate.this.TAG, "hide animation finished");
                MediaPlayerViewDelegate.this.mEventHandler.postEvent(Event.SLIDE_SHOW_DONE, new Object[0]);
            }
        });
        animatorSet.start();
    }

    private void playInternal(MediaItem mediaItem, int i2) {
        if (mediaItem == null) {
            Log.e(this.TAG, "play video failed: null item");
        } else if (!isViewReady() || !openVideo(mediaItem, i2)) {
            Log.d(this.TAG, "play video", Boolean.valueOf(isViewReady()));
        } else {
            this.mMediaPlayerView.play();
        }
    }

    private void playVideo() {
        MediaItem mediaItem;
        this.mMediaPlayerView.setVisibility(0);
        if (this.mView.getMediaData() == null || this.mView.getMediaData().getCount() <= 0) {
            mediaItem = null;
        } else {
            mediaItem = this.mView.getMediaData().read(0);
        }
        if (MediaItemUtil.equals(mediaItem, this.mMediaItem)) {
            playInternal(mediaItem, 0);
        } else {
            Log.e(this.TAG, "play video failed: item changed");
        }
    }

    /* access modifiers changed from: private */
    public void replayVideo(Object... objArr) {
        Log.d(this.TAG, "requestReplayVideo");
        showPreview(this.mPreviewBitmap);
        playVideo();
    }

    private void resumeVideo() {
        boolean z;
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView == null || this.mMediaItem == null) {
            String str = this.TAG;
            boolean z3 = false;
            if (iMediaPlayerView != null) {
                z = true;
            } else {
                z = false;
            }
            Boolean valueOf = Boolean.valueOf(z);
            if (this.mMediaItem != null) {
                z3 = true;
            }
            Log.e((CharSequence) str, "resumeVideo failed", valueOf, Boolean.valueOf(z3));
        } else if (iMediaPlayerView.isPlaying()) {
            Log.d(this.TAG, "resumePlay skipped. already playing");
        } else if (this.mMediaPlayerView.isInPlayState()) {
            Log.d(this.TAG, "resumePlay on isInPlayState");
            if (Math.abs(this.mMediaPlayerView.getCurrentPosition() - this.mSeekPosition) > 100) {
                this.mMediaPlayerView.seekTo(this.mSeekPosition);
            }
            this.mMediaPlayerView.resumeVideo();
        } else {
            Log.d(this.TAG, "resumePlay as new");
            playInternal(this.mMediaItem, this.mSeekPosition);
        }
    }

    private void setCapturedBitmap() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && iMediaPlayerView.isInPlayState()) {
            showPreview(pauseAndCapturedBitmap());
        }
    }

    private void showPreview(Bitmap bitmap) {
        ThreadUtil.postOnUiThread(new a(5, this, bitmap));
    }

    public void addListenEvent() {
        addEvent(Event.ENTER_TRANSITION_END, new g(this, 0));
        addEvent(Event.PLAYER_KEEP_PAUSE, new g(this, 1));
        addEvent(Event.REQUEST_VIDEO_SEEK_BEGIN, new g(this, 2));
        addEvent(Event.REQUEST_VIDEO_SEEK_FINISH, new g(this, 3));
        addEvent(Event.REQUEST_VIDEO_SEEK, new g(this, 4));
        addEvent(Event.USER_AUDIO_MUTE, new g(this, 5));
        addEvent(Event.REPLAY_VIDEO, new g(this, 6));
    }

    public void initView(View view) {
        super.initView(view);
        ImageView imageView = (ImageView) view.findViewById(R.id.transition_view);
        this.mTransitionView = imageView;
        imageView.setVisibility(0);
        IMediaPlayerView iMediaPlayerView = (IMediaPlayerView) view.findViewById(R.id.video_view);
        this.mMediaPlayerView = iMediaPlayerView;
        iMediaPlayerView.addMediaPlayerListener(this);
        this.mMediaPlayerView.setLooping(false);
        this.mMediaPlayerView.setLogTag(0);
        this.mMediaPlayerView.resetScale();
    }

    public void onDataChangedOnUi() {
        int i2;
        String str;
        MediaData mediaData = this.mView.getMediaData();
        boolean z = false;
        if (mediaData == null || mediaData.getCount() <= 0 || this.mMediaItem != null) {
            String str2 = this.TAG;
            if (mediaData != null) {
                i2 = mediaData.getCount();
            } else {
                i2 = -1;
            }
            Integer valueOf = Integer.valueOf(i2);
            if (this.mMediaItem == null) {
                z = true;
            }
            Log.d(str2, "onDataChanged skip", valueOf, Boolean.valueOf(z));
            return;
        }
        MediaItem read = mediaData.read(0);
        this.mMediaItem = read;
        playInternal(read, 0);
        String str3 = this.TAG;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            str = mediaItem.toString();
        } else {
            str = null;
        }
        Log.d(str3, "bindMediaData", str);
    }

    public void onDestroy() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.removeMediaPlayerListener(this);
        }
    }

    public void onPause() {
        super.onPause();
        Log.d(this.TAG, "onPause");
        setCapturedBitmap();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.close();
        }
    }

    public void onResume() {
        super.onResume();
        Log.d(this.TAG, "onResume");
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && this.mViewConfirmed) {
            if (!iMediaPlayerView.isOpened() && !ViewUtils.isTranslucent(this.mMediaPlayerView.getView())) {
                playInternal(this.mMediaItem, this.mSeekPosition);
            } else if (this.mMediaPlayerView.isPaused()) {
                this.mMediaPlayerView.resumeVideo();
            }
        }
    }

    public void onTimeTickUpdate(int i2, int i7) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && !iMediaPlayerView.isVisualSeeking()) {
            this.mSeekPosition = i7;
            this.mEventHandler.postEvent(Event.VIDEO_PLAY_TIME_UPDATED, Integer.valueOf(i7), Integer.valueOf(i2));
        }
    }

    public void onVideoCompleted() {
        ThreadUtil.postOnUiThread(new h(this, 0));
    }

    public void onVideoPause(int i2) {
        this.mSeekPosition = i2;
        this.mEventHandler.postEvent(Event.VIDEO_PAUSE, new Object[0]);
    }

    public void onVideoPlay(int i2) {
        ThreadUtil.postOnUiThreadDelayed(new h(this, 1), 52);
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && iMediaPlayerView.isPlaying()) {
            this.mEventHandler.postEvent(Event.VIDEO_PLAY, new Object[0]);
            this.mEventHandler.postEvent(Event.FRAGMENT_UPDATE_KEEP_SCREEN_ON, new Object[0]);
        }
    }

    public void onVideoPrepared(int i2, int i7) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            this.mEventHandler.postEvent(Event.HAS_AUDIO, Boolean.valueOf(iMediaPlayerView.hasAudioTrack()), Boolean.valueOf(this.mIsAudioMute));
            if (!this.mMediaPlayerView.isVisualSeeking()) {
                this.mEventHandler.postEvent(Event.VIDEO_PLAY_TIME_UPDATED, Integer.valueOf(this.mMediaPlayerView.getRenderingPosition()), Integer.valueOf(this.mMediaPlayerView.getDuration()));
            }
        }
    }
}
