package com.samsung.android.gallery.app.ui.list.stories.highlight.delegate;

import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.DataRequest;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Delegate;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.Event;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.EventHandler;
import com.samsung.android.gallery.app.ui.list.stories.highlight.abstraction.IStoryHighlightView;
import com.samsung.android.gallery.app.ui.list.stories.videopreview.VideoPreviewHandler;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.behavior.SheetBehaviorCompat;
import h.C0199b;
import java.util.concurrent.atomic.AtomicInteger;
import o6.t;
import o6.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoPreviewDelegate extends Delegate implements VideoPreviewHandler.VideoPreviewProvider {
    private final EventHandler mEventHandler;
    private boolean mIsTransitionEnd;
    private final AtomicInteger mPreviewBlockRequest = new AtomicInteger(0);
    private final VideoPreviewHandler mVideoPreviewHandler;

    public VideoPreviewDelegate(IStoryHighlightView iStoryHighlightView) {
        super(iStoryHighlightView);
        this.mEventHandler = iStoryHighlightView.getEventHandler();
        VideoPreviewHandler videoPreviewHandler = new VideoPreviewHandler(this, supportAudio(iStoryHighlightView));
        this.mVideoPreviewHandler = videoPreviewHandler;
        videoPreviewHandler.setSlideshow(iStoryHighlightView.getOptions().isSlideshow());
        if (supportAudio(iStoryHighlightView) && iStoryHighlightView.getOptions().isAudioDefaultMuted()) {
            videoPreviewHandler.muteAudio(true);
        }
    }

    private boolean isVideoPlayable() {
        if (this.mView.isDestroyed() || this.mPreviewBlockRequest.get() != 0 || ((Boolean) this.mEventHandler.requestData(DataRequest.PLAYER_KEEP_PAUSED, Boolean.FALSE)).booleanValue() || !this.mEventHandler.isBottomSheetHidden() || !this.mIsTransitionEnd) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addListenEvent$1(Object[] objArr) {
        this.mIsTransitionEnd = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleResolutionChange$0() {
        if (this.mPreviewBlockRequest.decrementAndGet() == 0 && isVideoPlayable()) {
            requestVideoPreview(Boolean.FALSE);
        }
    }

    /* access modifiers changed from: private */
    public void onAudioFocus(Object... objArr) {
        this.mVideoPreviewHandler.setAudioFocused(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onBottomSheetStateChanged(Object... objArr) {
        if (isVideoPlayable() && SheetBehaviorCompat.isHidden(objArr[0].intValue())) {
            this.mVideoPreviewHandler.resumePlayer();
            Log.d(this.TAG, "resume (bottom is hidden)");
        } else if (SheetBehaviorCompat.isExpanded(objArr[0].intValue())) {
            this.mVideoPreviewHandler.pausePlayer();
            Log.d(this.TAG, "pause (bottom is expanded)");
        }
    }

    /* access modifiers changed from: private */
    public void onFilterChanged(Object... objArr) {
        this.mVideoPreviewHandler.onFilterChanged();
    }

    /* access modifiers changed from: private */
    public void onKeepPause(Object... objArr) {
        this.mVideoPreviewHandler.setKeepPause(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void onUserMute(Object... objArr) {
        this.mVideoPreviewHandler.muteAudio(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public void requestVideoPreview(Object... objArr) {
        if (isVideoPlayable()) {
            boolean z = false;
            if (objArr != null && objArr.length > 0 && objArr[0].booleanValue()) {
                z = true;
            }
            IStoryHighlightView iStoryHighlightView = this.mView;
            if (z) {
                ThreadUtil.postOnUiThreadDelayed(new C0199b(17, this, iStoryHighlightView), 300);
            } else {
                lambda$requestVideoPreview$2(iStoryHighlightView);
            }
        }
    }

    /* access modifiers changed from: private */
    public void stopVideoPreview(Object... objArr) {
        this.mVideoPreviewHandler.stopVideoPreview();
    }

    private boolean supportAudio(IStoryHighlightView iStoryHighlightView) {
        return iStoryHighlightView.getOptions().supportBgm();
    }

    public void addListenEvent() {
        addEvent(Event.PLAYER_KEEP_PAUSE, new y(this, 0));
        addEvent(Event.START_VIDEO_PREVIEW, new y(this, 1));
        addEvent(Event.STOP_VIDEO_PREVIEW, new y(this, 2));
        addEvent(Event.USER_AUDIO_MUTE, new y(this, 3));
        addEvent(Event.AUDIO_FOCUS, new y(this, 4));
        addEvent(Event.ON_FILTER_CHANGED, new y(this, 5));
        addEvent(Event.BOTTOM_SHEET_STATE_CHANGED, new y(this, 6));
        addEvent(Event.ENTER_TRANSITION_END, new y(this, 7));
    }

    public int getFilterLevel() {
        return this.mEventHandler.getFilter().getIntensity();
    }

    public String getFilterPath() {
        return (String) this.mEventHandler.requestData(DataRequest.FILTER_PATH, "");
    }

    public PreviewViewHolder getVideoPreviewHolder() {
        return (PreviewViewHolder) this.mEventHandler.requestData(DataRequest.CURRENT_VIEW_HOLDER);
    }

    public void handleResolutionChange(int i2) {
        this.mVideoPreviewHandler.stopVideoPreview();
        this.mPreviewBlockRequest.incrementAndGet();
        ThreadUtil.postOnUiThreadDelayed(new t(2, this), 300);
    }

    public void onPause() {
        super.onPause();
        this.mVideoPreviewHandler.stopVideoPreview();
    }

    public void onResume() {
        super.onResume();
        requestVideoPreview(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    /* renamed from: requestVideoPreview */
    public void lambda$requestVideoPreview$2(IStoryHighlightView iStoryHighlightView) {
        if (iStoryHighlightView != null && iStoryHighlightView.isViewResumed()) {
            this.mVideoPreviewHandler.requestVideoPreview();
        }
    }
}
