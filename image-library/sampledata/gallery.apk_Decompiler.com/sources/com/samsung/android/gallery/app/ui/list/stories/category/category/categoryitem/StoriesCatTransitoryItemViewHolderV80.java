package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import H7.A;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatTransitoryItemViewHolderV80 extends StoriesCatTransitoryItemViewHolder {
    private TextView mCount;
    private View mCountLayout;
    private View mHourGlassLayout;

    public StoriesCatTransitoryItemViewHolderV80(View view, int i2, String str) {
        super(view, i2, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initSaveIcon$0(View view) {
        onItemClickInternal(1002);
    }

    private void setTitleFont(TextView textView, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN && mediaItem != null) {
            textView.setTypeface(FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(mediaItem), mediaItem.getAlbumID(), StringCompat.isKorean(mediaItem.getTitle())));
        }
    }

    private void updateCountView() {
        int i2;
        if (this.mCount != null) {
            int i7 = this.mDataCount;
            int i8 = this.mChildCount;
            int i10 = 1;
            if (i8 == 0) {
                i2 = 0;
            } else {
                i2 = i8 - 1;
            }
            int i11 = i7 + i2;
            int absoluteAdapterPosition = getAbsoluteAdapterPosition();
            int i12 = this.mChildCount;
            if (i12 != 0) {
                i10 = i12;
            }
            int i13 = absoluteAdapterPosition + i10;
            this.mCount.setText(Html.fromHtml(i13 + " / " + i11, 0));
            ViewGroup.LayoutParams layoutParams = this.mCount.getLayoutParams();
            if (layoutParams != null) {
                this.mCount.measure(0, 0);
                layoutParams.width = this.mCount.getMeasuredWidth() + 3;
                this.mCount.setLayoutParams(layoutParams);
            }
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY)) {
            setAccessibilityBackground();
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP) {
            ViewUtils.setVisibleOrGone(getTitleView(), !MediaItemStory.isRecap(getMediaItem()));
            ViewUtils.setVisibleOrGone(getSubTitleView(), !MediaItemStory.isRecap(getMediaItem()));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mCount = (TextView) view.findViewById(R.id.play_count);
        this.mCountLayout = view.findViewById(R.id.play_switch_layout);
        this.mHourGlassLayout = view.findViewById(R.id.hourglass_layout);
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            ViewUtils.setVisibleOrGone(this.mFavoriteView, false);
            initSaveIcon(view);
            return;
        }
        ViewUtils.setVisibleOrGone(this.mFavoriteView, true);
    }

    public void initSaveIcon(View view) {
        View findViewById = view.findViewById(R.id.icon_save);
        ViewUtils.setVisibleOrGone(findViewById, true);
        findViewById.setOnClickListener(new A(26, this));
    }

    public void setAccessibilityBackground() {
        int i2;
        boolean isEnabled = Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY);
        ImageView imageView = this.mFavoriteView;
        if (isEnabled) {
            i2 = R.drawable.story_highlight_filter_btn_bg_opaque;
        } else {
            i2 = R.drawable.story_highlight_filter_btn_bg;
        }
        ViewUtils.setBackgroundResource(imageView, i2);
        View view = this.mCountLayout;
        int i7 = R.drawable.story_highlight_deco_background;
        ViewUtils.setBackgroundResource(view, isEnabled ? R.drawable.story_highlight_deco_background_opaque : R.drawable.story_highlight_deco_background);
        View view2 = this.mHourGlassLayout;
        if (isEnabled) {
            i7 = R.drawable.story_highlight_deco_background_opaque;
        }
        ViewUtils.setBackgroundResource(view2, i7);
    }

    public void updateView(MediaItem mediaItem) {
        super.updateView(mediaItem);
        updateCountView();
        setTitleFont(getTitleView(), mediaItem);
    }
}
