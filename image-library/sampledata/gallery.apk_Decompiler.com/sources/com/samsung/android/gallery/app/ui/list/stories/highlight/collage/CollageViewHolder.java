package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import A.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sivs.ai.sdkcommon.tts.TextToSpeechConst;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageViewHolder extends PreviewViewHolder {
    private int[] mPlaybackRange;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class VideoPreviewListener extends PreviewViewHolder.PreviewListener {
        public /* synthetic */ VideoPreviewListener(CollageViewHolder collageViewHolder, int i2) {
            this();
        }

        public PlaybackOption getNextPlaybackOption(int i2) {
            int[] r = CollageViewHolder.this.getPlaybackRange();
            if (r != null) {
                return new PlaybackOption(r[0], r[1], 1.0f);
            }
            return new PlaybackOption(0, CollageViewHolder.this.getMaxDuration(), 1.0f);
        }

        public float getVolume() {
            return 1.0f;
        }

        public boolean isPlaybackPreview() {
            return CollageViewHolder.this.hasPlayBack();
        }

        public void onPreviewFail(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
            String access$000 = CollageViewHolder.this.TAG;
            StringBuilder h5 = a.h(i2, i7, "onPreviewFail error {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}");
            h5.append(isCriticalError(i2, i7));
            Log.w(access$000, h5.toString());
            if (isCriticalError(i2, i7)) {
                super.onPreviewFail(mediaPlayerCompat, i2, i7);
            }
        }

        public boolean useSecMediaPlayer() {
            return true;
        }

        private VideoPreviewListener() {
            super();
        }
    }

    public CollageViewHolder(View view, int i2) {
        super(view, i2, false);
    }

    /* access modifiers changed from: private */
    public int getMaxDuration() {
        return getPreviewDuration() + 1000;
    }

    /* access modifiers changed from: private */
    public int[] getPlaybackRange() {
        DynamicViewInfo dynamicViewInfo;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || (dynamicViewInfo = MediaItemUtil.getDynamicViewInfo(mediaItem)) == null) {
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

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        this.mPlaybackRange = getPlaybackRange();
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
    }

    public boolean canLooping(int i2) {
        return true;
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

    public void recycle() {
        super.recycle();
        ViewUtils.setAlpha(this.itemView, 1.0f);
    }

    public void setViewMatrix() {
        boolean z;
        if (this.mMediaItem != null) {
            int i2 = 0;
            if (getImage().getWidth() >= getImage().getHeight()) {
                z = true;
            } else {
                z = false;
            }
            RectF smartCropForCover = CoverRect.getSmartCropForCover((FileItemInterface) this.mMediaItem, z);
            if (RectUtils.isValidRect(smartCropForCover)) {
                if (!this.mMediaItem.isVideo() && !this.mMediaItem.isBroken()) {
                    i2 = this.mMediaItem.getOrientation();
                }
                setViewMatrix(smartCropForCover, i2, this.mMediaItem.getOrientationTag(), true);
                return;
            }
            super.setViewMatrix();
        }
    }
}
