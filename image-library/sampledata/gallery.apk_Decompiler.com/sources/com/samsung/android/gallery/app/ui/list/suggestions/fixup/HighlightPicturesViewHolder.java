package com.samsung.android.gallery.app.ui.list.suggestions.fixup;

import android.view.View;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewType;
import com.samsung.android.gallery.module.dynamicview.PlaybackOption;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightPicturesViewHolder extends PreviewViewHolder {
    /* access modifiers changed from: private */
    public final ArrayList<PlaybackOption> mPlaybackOptions = new ArrayList<>();
    protected float mRoundRadius;
    protected TextView mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class PlaybackPreviewListener extends PreviewViewHolder.PreviewListener {
        public /* synthetic */ PlaybackPreviewListener(HighlightPicturesViewHolder highlightPicturesViewHolder, int i2) {
            this();
        }

        public PlaybackOption getNextPlaybackOption(int i2) {
            return HighlightPicturesViewHolder.this.getPlaybackOption(i2);
        }

        public String getPlaybackOptionsDebug() {
            if (HighlightPicturesViewHolder.this.mPlaybackOptions.size() > 1) {
                return "#" + HighlightPicturesViewHolder.this.mPlaybackOptions.size() + NumericEnum.SEP + HighlightPicturesViewHolder.this.mPlaybackOptions.get(0) + "..." + HighlightPicturesViewHolder.this.mPlaybackOptions.get(HighlightPicturesViewHolder.this.mPlaybackOptions.size() - 1);
            } else if (HighlightPicturesViewHolder.this.mPlaybackOptions.size() != 1) {
                return "#0";
            } else {
                return "#1:" + HighlightPicturesViewHolder.this.mPlaybackOptions.get(0);
            }
        }

        public boolean isPlaybackPreview() {
            return HighlightPicturesViewHolder.this.hasPlaybackRanges();
        }

        private PlaybackPreviewListener() {
            super();
        }
    }

    public HighlightPicturesViewHolder(View view, int i2) {
        super(view, i2);
        this.mRoundRadius = view.getResources().getDimension(R.dimen.suggestion_highlight_pictures_item_round_radius);
    }

    /* access modifiers changed from: private */
    public PlaybackOption getPlaybackOption(int i2) {
        if (i2 < 0 || i2 >= this.mPlaybackOptions.size()) {
            return null;
        }
        return this.mPlaybackOptions.get(i2);
    }

    /* access modifiers changed from: private */
    public boolean hasPlaybackRanges() {
        if (this.mPlaybackOptions.size() > 0) {
            return true;
        }
        return false;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        setThumbnailShape(1, this.mRoundRadius);
        addThumbnailBorder(getContext().getDrawable(R.drawable.sharings_thumbnail_border));
        bindTitle(updateItemInfo(mediaItem));
    }

    public void bindTitle(String str) {
        this.mTitle.setText(str);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitle = (TextView) view.findViewById(R.id.title);
    }

    public int getPreviewDuration() {
        if (!hasPlaybackRanges()) {
            return 10000;
        }
        Iterator<PlaybackOption> it = this.mPlaybackOptions.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 += it.next().mRelativeDurationMs;
        }
        return i2;
    }

    public PreviewViewHolder.PreviewListener getPreviewListener() {
        return new PlaybackPreviewListener(this, 0);
    }

    public int getSeekTime() {
        if (!hasPlaybackRanges()) {
            return super.getSeekTime();
        }
        PlaybackOption playbackOption = getPlaybackOption(0);
        if (playbackOption != null) {
            return playbackOption.mStartMs;
        }
        return super.getSeekTime();
    }

    public String updateItemInfo(MediaItem mediaItem) {
        DynamicViewInfo dynamicViewInfo = DynamicViewData.of(mediaItem).dynamicViewInfo;
        if (dynamicViewInfo == null) {
            Log.e((CharSequence) this.TAG, "no DynamicViewInfo", MediaItemUtil.getSimpleLog(mediaItem));
        }
        DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(dynamicViewInfo).setFileDuration(mediaItem.getFileDuration()).setPlayType(DynamicViewType.getSuggestionsType(mediaItem.getFileDuration())).build();
        if (build.getSize() > 0) {
            DynamicViewData.of(mediaItem).dynamicViewPlayInfo = build;
            mediaItem.setVideoHighlightTime(Long.valueOf(build.getVideoThumbStartTime()), (Long) null);
            this.mPlaybackOptions.clear();
            Iterator<PlaybackOption> it = build.getPlayList().iterator();
            while (it.hasNext()) {
                PlaybackOption next = it.next();
                this.mPlaybackOptions.add(new PlaybackOption(next.mStartMs, next.mEndMs, next.mSpeed));
            }
        }
        return build.getTypeString();
    }
}
