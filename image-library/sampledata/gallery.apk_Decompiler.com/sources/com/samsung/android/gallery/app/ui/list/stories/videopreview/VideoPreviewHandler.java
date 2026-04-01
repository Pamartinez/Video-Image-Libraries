package com.samsung.android.gallery.app.ui.list.stories.videopreview;

import K4.a;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VideoPreviewHandler {
    private static final boolean FILTER_ENABLED = PreferenceFeatures.isEnabled(PreferenceFeatures.StoriesFilter);
    private boolean mAudioFocused = true;
    private final Handler mHandler;
    private int mPreviewDelay = 30;
    private final AtomicReference<Pair<MediaItem, PreviewViewHolder>> mPreviewingItem = new AtomicReference<>();
    private boolean mSlideshow;
    private boolean mSupportAudio;
    private boolean mUserMute;
    private VideoPreviewProvider mVideoPreviewProvider;

    public VideoPreviewHandler(VideoPreviewProvider videoPreviewProvider, boolean z) {
        this.mVideoPreviewProvider = videoPreviewProvider;
        this.mSupportAudio = z;
        this.mHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                int i2 = message.what;
                if (i2 == 1000) {
                    VideoPreviewHandler.this.handleRequestPreview();
                } else if (i2 == 1001) {
                    VideoPreviewHandler.this.handleStopPreview();
                }
            }
        };
    }

    private boolean canPreview(RecyclerView.ViewHolder viewHolder) {
        if (this.mSlideshow) {
            if (!(viewHolder instanceof PreviewViewHolder) || !StoryHelper.isGeneralSlideshowVideoFormat(((PreviewViewHolder) viewHolder).getPreviewItem())) {
                return false;
            }
            return true;
        } else if (!(viewHolder instanceof PreviewViewHolder) || !StoryHelper.isVideoItem(((PreviewViewHolder) viewHolder).getPreviewItem())) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public void handleRequestPreview() {
        VideoPreviewProvider videoPreviewProvider = this.mVideoPreviewProvider;
        if (videoPreviewProvider != null) {
            PreviewViewHolder videoPreviewHolder = videoPreviewProvider.getVideoPreviewHolder();
            boolean isSameWithCurrent = isSameWithCurrent(videoPreviewHolder);
            if (!isSameWithCurrent) {
                stopCurrentPreview();
            }
            if (videoPreviewHolder != null && !isSameWithCurrent && canPreview(videoPreviewHolder)) {
                videoPreviewHolder.resetPreviewError();
                Log.d("VideoPreviewHandler", "start preview = " + videoPreviewHolder.getAbsoluteAdapterPosition());
                videoPreviewHolder.preparePreview(new a(4, this));
                if (FILTER_ENABLED) {
                    videoPreviewHolder.applyFilter(videoPreviewProvider.getFilterPath(), videoPreviewProvider.getFilterLevel());
                }
                videoPreviewHolder.muteAudio(isAudioDisabled());
                videoPreviewHolder.startPreview();
                videoPreviewProvider.onStart(videoPreviewHolder);
                this.mPreviewingItem.set(new Pair(videoPreviewHolder.getMediaItem(), videoPreviewHolder));
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleStopPreview() {
        stopCurrentPreview();
    }

    private boolean isAudioDisabled() {
        if (!this.mSupportAudio || !Features.isEnabled(Features.SUPPORT_BGM_SERVICE) || this.mUserMute || !this.mAudioFocused) {
            return true;
        }
        return false;
    }

    private boolean isSameWithCurrent(PreviewViewHolder previewViewHolder) {
        Pair pair = this.mPreviewingItem.get();
        if (pair == null || pair.first == null || previewViewHolder == null || previewViewHolder.getMediaItem() == null || previewViewHolder.getMediaItem().getFileId() != ((MediaItem) pair.first).getFileId() || pair.second != previewViewHolder) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleRequestPreview$1(PreviewViewHolder previewViewHolder, boolean z) {
        Log.d("VideoPreviewHandler", "preview complete = " + previewViewHolder.getAbsoluteAdapterPosition() + ArcCommonLog.TAG_COMMA + isSameWithCurrent(previewViewHolder));
        if (isSameWithCurrent(previewViewHolder)) {
            this.mPreviewingItem.set((Object) null);
            Optional.ofNullable(this.mVideoPreviewProvider).ifPresent(new K5.a(4, previewViewHolder));
        }
    }

    private void stopCurrentPreview() {
        Object obj;
        Pair pair = this.mPreviewingItem.get();
        if (pair != null && (obj = pair.second) != null) {
            ((PreviewViewHolder) obj).stopPreview();
            this.mPreviewingItem.set((Object) null);
        }
    }

    public void destroy() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        handleStopPreview();
        this.mVideoPreviewProvider = null;
    }

    public void muteAudio(boolean z) {
        Object obj;
        this.mUserMute = z;
        Pair pair = this.mPreviewingItem.get();
        if (pair != null && (obj = pair.second) != null) {
            ((PreviewViewHolder) obj).muteAudio(isAudioDisabled());
        }
    }

    public void onFilterChanged() {
        Pair pair;
        Object obj;
        if (FILTER_ENABLED && (pair = this.mPreviewingItem.get()) != null && (obj = pair.second) != null) {
            ((PreviewViewHolder) obj).applyFilter(this.mVideoPreviewProvider.getFilterPath(), this.mVideoPreviewProvider.getFilterLevel());
        }
    }

    public void pausePlayer() {
        Object obj;
        Pair pair = this.mPreviewingItem.get();
        if (this.mHandler.hasMessages(1000)) {
            this.mHandler.removeMessages(1000);
        }
        if (pair != null && (obj = pair.second) != null) {
            ((PreviewViewHolder) obj).pausePlayer();
        }
    }

    public void requestVideoPreview() {
        if (!this.mHandler.hasMessages(1000)) {
            Handler handler = this.mHandler;
            handler.sendMessageDelayed(handler.obtainMessage(1000), (long) this.mPreviewDelay);
        }
    }

    public void resumePlayer() {
        Object obj;
        Pair pair = this.mPreviewingItem.get();
        if (pair == null || pair.second == null || this.mVideoPreviewProvider.getVideoPreviewHolder() != (obj = pair.second) || !((PreviewViewHolder) obj).resumePlayer()) {
            requestVideoPreview();
        }
    }

    public void setAudioFocused(boolean z) {
        Object obj;
        Log.d("VideoPreviewHandler", "setAudioFocused", Boolean.valueOf(z));
        this.mAudioFocused = z;
        Pair pair = this.mPreviewingItem.get();
        if (pair != null && (obj = pair.second) != null) {
            ((PreviewViewHolder) obj).muteAudio(isAudioDisabled());
        }
    }

    public void setKeepPause(boolean z) {
        if (z) {
            pausePlayer();
        } else {
            resumePlayer();
        }
    }

    public void setPreviewDelay(int i2) {
        this.mPreviewDelay = i2;
    }

    public void setSlideshow(boolean z) {
        this.mSlideshow = z;
    }

    public void stopVideoPreview() {
        if (!this.mHandler.hasMessages(1001)) {
            Handler handler = this.mHandler;
            handler.sendMessage(handler.obtainMessage(1001));
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface VideoPreviewProvider {
        int getFilterLevel() {
            return -1;
        }

        String getFilterPath();

        PreviewViewHolder getVideoPreviewHolder();

        void onComplete(PreviewViewHolder previewViewHolder) {
        }

        void onStart(PreviewViewHolder previewViewHolder) {
        }
    }
}
