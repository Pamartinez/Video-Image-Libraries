package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.story.TitleAlign;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesCatItemViewHolder extends StoriesCatItemBaseViewHolder {
    private LinearLayout mTitleContainer;
    private LinearLayout mTopRightDeco;

    public StoriesCatItemViewHolder(View view, int i2, String str) {
        super(view, i2, str);
    }

    private int getBorderResId() {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return R.drawable.stories_category_item_border_v85;
        }
        return R.drawable.stories_category_item_border;
    }

    private void setTitleFont(TextView textView, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN && mediaItem != null) {
            textView.setTypeface(FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(mediaItem), mediaItem.getAlbumID(), StringCompat.isKorean(mediaItem.getTitle())));
        }
    }

    private void updateDecoViewLayout(int i2, boolean z) {
        updateTitleLayout();
    }

    private void updateLayout() {
        updateTitleLayout();
    }

    private void updateTitleLayout() {
        TitleAlign.BOTTOM_MIDDLE.setLayoutAlign((View) this.mTitleContainer);
        setTitleFont(getTitleView(), this.mMediaItem);
    }

    public void addThumbnailBorder(Drawable drawable) {
        this.mImageView.setForeground(drawable);
    }

    public void bind(MediaItem mediaItem) {
        boolean z;
        updateLayout();
        super.bind(mediaItem);
        if (MediaItemStory.getStoryFavorite(getMediaItem()) > 0) {
            z = true;
        } else {
            z = false;
        }
        setFavoriteInfo(z);
        DebugHelper.showInfoView((ListViewHolder) this, (int) R.id.deco_view_layout, new Rect(5, 10, 40, 5));
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mTitleContainer = (LinearLayout) view.findViewById(R.id.title_container);
        this.mTopRightDeco = (LinearLayout) view.findViewById(R.id.top_right_deco);
        ViewUtils.setVisibleOrGone(this.mGradientView, !supportGradientBlur());
    }

    public View getDecoView(int i2) {
        if (i2 == 59) {
            return this.mFavoriteView;
        }
        return super.getDecoView(i2);
    }

    public View getScalableView() {
        return this.mThumbnailContainer;
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

    public TextView getSubTitleView() {
        return (TextView) this.mTitleContainer.findViewById(R.id.duration);
    }

    public void initBorder() {
        setThumbnailShape(1, (float) ItemProperty.getItemRadius(getContext(), this.mDataKey));
        addThumbnailBorder(getContext().getDrawable(getBorderResId()));
    }

    public void recycle() {
        super.recycle();
        View view = this.mGradientView;
        if (view != null) {
            view.setTransitionName((String) null);
        }
    }

    public void setDecoViewVisibility(MediaItem mediaItem) {
        setDecoViewVisibilityInSelectionMode();
    }

    public void setDecoViewVisibilityInSelectionMode() {
        Drawable drawable;
        ImageView imageView = this.mFavoriteView;
        if (isSelectionMode()) {
            drawable = null;
        } else {
            drawable = getContext().getDrawable(R.drawable.recoil_oval_shape);
        }
        ViewUtils.setForeground(imageView, drawable);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mImageContainer, i2, f);
        return this;
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            if (!PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN || RectUtils.isValidRect(cropRect)) {
                super.setViewMatrix();
                return;
            }
            RectF smartCropForCover = CoverRect.getSmartCropForCover(this.mMediaItem);
            if (RectUtils.isValidRect(smartCropForCover)) {
                if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
                    i2 = 0;
                } else {
                    i2 = this.mMediaItem.getOrientation();
                }
                setViewMatrix(smartCropForCover, i2, this.mMediaItem.getOrientationTag(), false);
                return;
            }
            super.setViewMatrix();
        }
    }

    public boolean supportCollage() {
        return false;
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 64) != 0) {
            if (objArr != null && objArr.length > 0) {
                setFavoriteInfo(objArr[0].booleanValue());
            }
            return true;
        } else if ((i2 & SerializeOptions.SORT) == 0) {
            return super.updateDecoration(i2, objArr);
        } else {
            if (objArr != null && objArr.length > 0) {
                updateDecoViewLayout(objArr[0].intValue(), objArr[1].booleanValue());
            }
            return true;
        }
    }
}
