package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import Ab.a;
import android.graphics.RectF;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryHeaderViewListener;
import com.samsung.android.gallery.app.ui.viewholders.ImageViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyCoverViewHolder extends ImageViewHolder {
    View mGradientView;
    ImageButton mHighlightPlay;
    private StoryHeaderViewListener mListener;

    public StoryPicturesLegacyCoverViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindView$0(View view) {
        onHighlightClicked();
    }

    private void updateHighlightVideoPlay(MediaItem mediaItem) {
        if (MediaItemStory.hasStoryHighlightVideo(mediaItem)) {
            this.mHighlightPlay.setVisibility(0);
            this.mHighlightPlay.setTooltipText(getContext().getString(R.string.speak_play_video));
            return;
        }
        this.mHighlightPlay.setVisibility(8);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        addThumbnailBorder(getContext().getDrawable(R.drawable.story_pictures_cover_thumbnail_border));
        updateHighlightVideoPlay(mediaItem);
        setTransitionName();
    }

    public void bindView(View view) {
        super.bindView(view);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.highlight_play);
        this.mHighlightPlay = imageButton;
        imageButton.setOnClickListener(new a(17, this));
        this.mGradientView = view.findViewById(R.id.gradient_view);
    }

    public View getDecoView(int i2) {
        if (i2 == 32) {
            return this.mGradientView;
        }
        if (i2 == 33) {
            return this.mHighlightPlay;
        }
        return super.getDecoView(i2);
    }

    public void onHighlightClicked() {
        StoryHeaderViewListener storyHeaderViewListener = this.mListener;
        if (storyHeaderViewListener != null) {
            storyHeaderViewListener.onHighlightPlayClicked();
        }
    }

    public void recycle() {
        ImageButton imageButton = this.mHighlightPlay;
        if (imageButton != null) {
            imageButton.setVisibility(8);
        }
        super.recycle();
    }

    public void setEnabledCover(boolean z) {
        float f;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.4f;
        }
        this.mHighlightPlay.setAlpha(f);
        this.mHighlightPlay.setEnabled(z);
        this.mHighlightPlay.setFocusable(z);
        getImage().setAlpha(f);
    }

    public void setListener(StoryHeaderViewListener storyHeaderViewListener) {
        this.mListener = storyHeaderViewListener;
    }

    public void setTransitionName() {
        String str;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            str = String.valueOf(MediaItemStory.getStoryId(mediaItem));
        } else {
            str = "";
        }
        ImageView image = getImage();
        SharedTransition.setTransitionName(image, "story/" + str);
        ImageButton imageButton = this.mHighlightPlay;
        SharedTransition.setTransitionName(imageButton, "story/highlight/" + str);
        View view = this.mGradientView;
        SharedTransition.setTransitionName(view, "story/gradient/" + str);
    }

    public void setViewMatrix() {
        int i2;
        RectF stringToRectF = RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(this.mMediaItem));
        if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
            i2 = 0;
        } else {
            i2 = this.mMediaItem.getOrientation();
        }
        setViewMatrix(stringToRectF, i2);
    }
}
