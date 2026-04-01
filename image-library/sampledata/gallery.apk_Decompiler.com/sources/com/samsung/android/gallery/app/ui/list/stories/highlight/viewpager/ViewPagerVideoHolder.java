package com.samsung.android.gallery.app.ui.list.stories.highlight.viewpager;

import A.a;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.previewable.Previewable;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;
import com.sec.android.gallery3d.R;
import x6.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewPagerVideoHolder extends ViewPagerHolder {
    private int[] mPlaybackRange;
    private ViewGroup mPreviewLayout;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VideoPreviewListener extends PreviewViewHolder.PreviewListener {
        public /* synthetic */ VideoPreviewListener(ViewPagerVideoHolder viewPagerVideoHolder, int i2) {
            this();
        }

        public PlaybackOption getNextPlaybackOption(int i2) {
            int[] t = ViewPagerVideoHolder.this.getPlaybackRange();
            if (t != null) {
                return new PlaybackOption(t[0], t[1], 1.0f);
            }
            return new PlaybackOption(0, ViewPagerVideoHolder.this.getMaxDuration(), 1.0f);
        }

        public float getVolume() {
            return 1.0f;
        }

        public boolean isPlaybackPreview() {
            return ViewPagerVideoHolder.this.hasPlayBack();
        }

        public void onPreviewFail(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
            String access$200 = ViewPagerVideoHolder.this.TAG;
            StringBuilder h5 = a.h(i2, i7, "onPreviewFail error {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}");
            h5.append(isCriticalError(i2, i7));
            Log.w(access$200, h5.toString());
            if (isCriticalError(i2, i7)) {
                super.onPreviewFail(mediaPlayerCompat, i2, i7);
            }
        }

        public void onPreviewStart() {
            if (ViewPagerVideoHolder.this.mPreviewView != null) {
                ViewPagerVideoHolder.this.mPreviewView.setVisibility(0);
            }
        }

        public boolean useSecMediaPlayer() {
            return true;
        }

        private VideoPreviewListener() {
            super();
        }
    }

    public ViewPagerVideoHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public int getMaxDuration() {
        return getPreviewDuration() + 1000;
    }

    /* access modifiers changed from: private */
    public int[] getPlaybackRange() {
        MediaItem mediaItem;
        DynamicViewInfo dynamicViewInfo;
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave) || (mediaItem = this.mMediaItem) == null || (dynamicViewInfo = MediaItemUtil.getDynamicViewInfo(mediaItem)) == null) {
            return null;
        }
        int startMs = dynamicViewInfo.getStartMs();
        int min = Math.min(getMaxDuration() + startMs, this.mMediaItem.getFileDuration());
        if (startMs < min) {
            return new int[]{startMs, min};
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean hasPlayBack() {
        if (this.mPlaybackRange != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$hidePreviewView$0(View view) {
        removePreviewView(view);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mPlaybackRange = getPlaybackRange();
    }

    public void bindPreviewView(View view) {
        this.mPreviewLayout.addView(view);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mPreviewLayout = (ViewGroup) view.findViewById(R.id.thumbnail_preview_layout);
    }

    public boolean canLooping(int i2) {
        return true;
    }

    public Previewable createPreview() {
        return PreviewFactory.createStoryHighlightPreview(getPreviewItem(), getPreviewListener());
    }

    public PreviewViewHolder.PreviewListener createPreviewListener() {
        return new VideoPreviewListener(this, 0);
    }

    public int getPreviewDuration() {
        if (MediaItemStory.isLiveEffectItem(this.mMediaItem)) {
            return 7000;
        }
        return TextToSpeechConst.MAX_SPEECH_INPUT;
    }

    public int getPreviewThumbnailOffsetMs() {
        return 0;
    }

    public int getSeekTime() {
        if (!PreferenceFeatures.isEnabled(PreferenceFeatures.StoryHighlightSave)) {
            return super.getSeekTime();
        }
        DynamicViewInfo dynamicViewInfo = MediaItemUtil.getDynamicViewInfo(this.mMediaItem);
        if (dynamicViewInfo != null) {
            return dynamicViewInfo.getStartMs();
        }
        return super.getSeekTime();
    }

    public void hidePreviewView(boolean z) {
        this.mIsPreviewing = false;
        Previewable previewable = this.mPreview;
        if (previewable != null) {
            previewable.stopPreview();
            this.mPreview = null;
        }
        View view = this.mPreviewView;
        if (view != null) {
            this.mPreviewView = null;
            this.mImageView.post(new f(0, this, view));
        }
    }

    public void recycle() {
        super.recycle();
        this.mPlaybackRange = null;
    }

    public void updateTransformView(int i2, int i7, int i8, int i10) {
        super.updateTransformView(i2, i7, i8, i10);
    }
}
