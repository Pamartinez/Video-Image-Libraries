package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import H7.A;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageButton;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleDurationViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.story.abstraction.IStoryContent;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesBaseViewHolder extends ImageTitleDurationViewHolder implements IStoryContent {
    protected View mGradientView;
    ImageButton mHighlightPlay;
    protected View mNewLabel;

    public StoriesBaseViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onClickHighlightPlay();
    }

    private void setHighlightPlayEnabled(boolean z) {
        ImageButton imageButton;
        float f;
        if (supportHighLightPlay() && (imageButton = this.mHighlightPlay) != null) {
            if (z) {
                f = 1.0f;
            } else {
                f = 0.4f;
            }
            imageButton.setAlpha(f);
            this.mHighlightPlay.setEnabled(z);
            this.mHighlightPlay.setFocusable(z);
            this.mHighlightPlay.setTooltipText(getContext().getString(R.string.speak_play_video));
        }
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        super.bind(mediaItem);
        initBorder();
        View view = this.mNewLabel;
        int i7 = 4;
        if (view != null) {
            if (isNewLabel(mediaItem)) {
                i2 = 0;
            } else {
                i2 = 4;
            }
            view.setVisibility(i2);
        }
        if (this.mHighlightPlay != null && supportHighLightPlay()) {
            ImageButton imageButton = this.mHighlightPlay;
            if (MediaItemStory.hasStoryHighlightVideo(mediaItem)) {
                i7 = 0;
            }
            imageButton.setVisibility(i7);
            setHighlightPlayEnabled(!isCheckBoxEnabled());
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mHighlightPlay = (ImageButton) view.findViewById(R.id.highlight_play);
        this.mGradientView = view.findViewById(R.id.gradient_view);
        this.mNewLabel = view.findViewById(R.id.newLabel);
        ViewUtils.setOnClickListener(this.mHighlightPlay, new A(20, this));
    }

    public String getContentDescription() {
        String str = "";
        if (this.mMediaItem == null) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mMediaItem.getDisplayName());
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(MediaItemStory.getStoryTimeDuration(this.mMediaItem));
        if (isNewLabel(this.mMediaItem)) {
            str = ArcCommonLog.TAG_COMMA + AppResources.getString(R.string.new_story);
        }
        sb2.append(str);
        return sb2.toString();
    }

    public View getDecoView(int i2) {
        if (i2 == 1) {
            return this.mNewLabel;
        }
        if (i2 == 32) {
            return this.mGradientView;
        }
        if (i2 == 33) {
            return this.mHighlightPlay;
        }
        return super.getDecoView(i2);
    }

    public int getFocusedBorderDrawableId() {
        return R.drawable.stories_focused_border_on_dex;
    }

    public int getThumbnailBorderResId() {
        return R.drawable.stories_thumbnail_border;
    }

    public void initBorder() {
        setThumbnailShape(1, getContext().getResources().getDimension(R.dimen.stories_view_round_radius));
        addThumbnailBorder(getContext().getDrawable(getThumbnailBorderResId()));
    }

    public boolean isNewLabel(MediaItem mediaItem) {
        if (!StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem)) || !MediaItemStory.hasNewStoryLabel(mediaItem)) {
            return false;
        }
        return true;
    }

    public void onClickHighlightPlay() {
        onItemClickInternal(1001);
    }

    public void recycle() {
        ViewUtils.setVisibleOrGone(this.mHighlightPlay, false);
        ViewUtils.setVisibleOrGone(this.mNewLabel, false);
        super.recycle();
    }

    public void setCheckboxEnabled(boolean z) {
        super.setCheckboxEnabled(z);
        setHighlightPlayEnabled(!z);
    }

    public void setFocusedFilterOnAdvancedMouseEvent(boolean z) {
        super.setFocusedFilterOnAdvancedMouseEvent(z);
        if (!z) {
            addThumbnailBorder(getContext().getDrawable(R.drawable.stories_thumbnail_border));
        }
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            RectF stringToRectF = RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
            if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = this.mMediaItem.getOrientation();
            }
            setViewMatrix(stringToRectF, i2);
        }
    }

    public boolean supportHighLightPlay() {
        return true;
    }

    public void onClear() {
    }
}
