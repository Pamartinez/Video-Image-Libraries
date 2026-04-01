package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import o5.C0496a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryStoriesItemViewHolder extends CategoryTitleCountViewHolder {
    private ViewGroup mContentParent;
    Drawable[] mFavoriteDrawable;
    ImageView mFavoriteView;
    private View mGradientView;
    private View mNewLabel;
    private TextView mSubTitle;
    private FrameLayout mThumbnailContainer;

    public CategoryStoriesItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    /* access modifiers changed from: private */
    public void onFavoriteViewClicked(View view) {
        ListViewHolder.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, getAbsoluteAdapterPosition(), getMediaItem(), 6);
        }
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [boolean] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setFavoriteInfo(boolean r3) {
        /*
            r2 = this;
            android.widget.ImageView r0 = r2.mFavoriteView
            if (r0 == 0) goto L_0x0024
            android.graphics.drawable.Drawable[] r1 = r2.mFavoriteDrawable
            if (r1 == 0) goto L_0x0024
            r1 = r1[r3]
            r0.setImageDrawable(r1)
            if (r3 == 0) goto L_0x0013
            r3 = 2131888161(0x7f120821, float:1.941095E38)
            goto L_0x0016
        L_0x0013:
            r3 = 2131886164(0x7f120054, float:1.94069E38)
        L_0x0016:
            java.lang.String r3 = com.samsung.android.gallery.support.utils.AppResources.getString(r3)
            android.widget.ImageView r0 = r2.mFavoriteView
            r0.setContentDescription(r3)
            android.widget.ImageView r2 = r2.mFavoriteView
            r2.setTooltipText(r3)
        L_0x0024:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryStoriesItemViewHolder.setFavoriteInfo(boolean):void");
    }

    private void updateNewLabel(MediaItem mediaItem) {
        boolean z;
        if (mediaItem != null) {
            int i2 = 0;
            if (!StoryHelper.isNewStory(MediaItemStory.getStoryNotifyStatus(mediaItem)) || !MediaItemStory.hasNewStoryLabel(mediaItem)) {
                z = false;
            } else {
                z = true;
            }
            View view = this.mNewLabel;
            if (!z) {
                i2 = 4;
            }
            ViewUtils.setVisibility(view, i2);
        }
    }

    private void updateTitle(MediaItem mediaItem) {
        TextView textView = this.mSubTitle;
        if (textView != null) {
            textView.setText(MediaItemStory.getStoryTimeDuration(mediaItem));
        }
    }

    public void bind(MediaItem mediaItem) {
        boolean z;
        super.bind(mediaItem);
        updateTitle(mediaItem);
        updateNewLabel(mediaItem);
        if (MediaItemStory.getStoryFavorite(getMediaItem()) > 0) {
            z = true;
        } else {
            z = false;
        }
        setFavoriteInfo(z);
        getViewForActionListener().setAccessibilityDelegate(this.mAccessibilityDelegate);
        ViewUtils.setVisibleOrGone(this.mGradientView, false);
        DebugHelper.showInfoView((ListViewHolder) this, (int) R.id.deco_layout, new Rect(5, 10, 55, 5));
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR) {
            CoverGradientBlur.apply(this.mThumbnailContainer);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContentParent = (ViewGroup) view.findViewById(R.id.content_parent);
        this.mThumbnailContainer = (FrameLayout) view.findViewById(R.id.thumbnail_container);
        this.mGradientView = view.findViewById(R.id.gradient_view);
        this.mNewLabel = view.findViewById(R.id.newLabel);
        this.mFavoriteView = (ImageView) view.findViewById(R.id.icon_favorite);
        this.mSubTitle = (TextView) view.findViewById(R.id.duration);
        ViewUtils.setOnClickListener(this.mFavoriteView, new C0496a(3, this));
        ViewUtils.setVisibleOrGone(this.mNewLabel, false);
        this.mFavoriteDrawable = new Drawable[]{getContext().getDrawable(R.drawable.story_favorite_off), getContext().getDrawable(R.drawable.story_favorite_on)};
    }

    public RectF getCropRect(MediaItem mediaItem) {
        if (!PreviewFactory.isPreviewableFormat(mediaItem)) {
            return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
        }
        return null;
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
        return this.mSubTitle;
    }

    public View getViewForActionListener() {
        ViewGroup viewGroup = this.mContentParent;
        if (viewGroup != null) {
            return viewGroup;
        }
        return this.itemView;
    }

    public void recycle() {
        super.recycle();
        View view = this.mGradientView;
        if (view != null) {
            view.setTransitionName((String) null);
        }
        ViewUtils.setViewSize(this.mContentParent, -1, -1);
    }

    public void setContentDescription() {
        if (this.mMediaItem != null) {
            this.mContentParent.setContentDescription(getContentDescription());
        }
    }

    public void setDecoViewVisibility(MediaItem mediaItem) {
        setDecoViewVisibilityInSelectionMode();
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mThumbnailContainer, i2, f);
        return this;
    }

    public void setViewMatrix() {
        boolean z;
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            if (!PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN || RectUtils.isValidRect(cropRect)) {
                super.setViewMatrix();
                return;
            }
            MediaItem mediaItem2 = this.mMediaItem;
            ImageView image = getImage();
            if (getImage().getWidth() > getImage().getHeight()) {
                z = true;
            } else {
                z = false;
            }
            RectF coverDisplayRect = CoverRect.getCoverDisplayRect(mediaItem2, image, z);
            if (RectUtils.isValidRect(coverDisplayRect)) {
                if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
                    i2 = 0;
                } else {
                    i2 = this.mMediaItem.getOrientation();
                }
                setViewMatrix(coverDisplayRect, i2, this.mMediaItem.getOrientationTag(), false);
                return;
            }
            super.setViewMatrix();
        }
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        boolean z;
        if ((i2 & 64) == 0) {
            return super.updateDecoration(i2, objArr);
        }
        if (MediaItemStory.getStoryFavorite(getMediaItem()) > 0) {
            z = true;
        } else {
            z = false;
        }
        setFavoriteInfo(z);
        return true;
    }
}
