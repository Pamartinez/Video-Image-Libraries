package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import H7.A;
import M5.a;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseViewHolder;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.previewable.PreviewFactory;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesViewHolder extends StoriesBaseViewHolder {
    ViewGroup mCoverView;
    View mDimView;
    protected ViewGroup mImageContainer;
    ViewGroup mMenuViewContainer;
    private OnItemMenuClickListener mOnItemMenuClickListener;
    View mPreviewBackgroundView;
    private MediaItem mPreviewVideoItem;
    View mTitleContainer;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemMenuClickListener {
    }

    public StoriesViewHolder(View view, int i2) {
        super(view, i2);
        setUseThumbnailCheckbox(false);
    }

    private void calculateCollageArea(View view, boolean z) {
        boolean z3;
        int i2;
        int i7;
        if (MediaItemStory.getStoryCollagePath(getMediaItem()) != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        int storyCollageType = MediaItemStory.getStoryCollageType(getMediaItem());
        int collageColumns = StoryHelper.getCollageColumns(storyCollageType);
        int width = (this.itemView.getWidth() - this.itemView.getPaddingStart()) - this.itemView.getPaddingEnd();
        int round = (int) Math.round((((double) width) * 1.58839779d) / 100.0d);
        int i8 = round / 2;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mPreviewBackgroundView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (!z3 || storyCollageType == 0) {
            marginLayoutParams2.height = width;
            marginLayoutParams2.width = width;
        } else if (storyCollageType == 2 || storyCollageType == 3) {
            int i10 = (width - ((collageColumns + 1) * round)) / collageColumns;
            marginLayoutParams2.height = i10;
            marginLayoutParams2.width = i10;
            int storyCollageVideoIndex = MediaItemStory.getStoryCollageVideoIndex(getMediaItem()) % collageColumns;
            int storyCollageVideoIndex2 = MediaItemStory.getStoryCollageVideoIndex(getMediaItem()) / collageColumns;
            i2 = ((storyCollageVideoIndex + 1) * round) + (storyCollageVideoIndex * i10);
            i7 = ((storyCollageVideoIndex2 + 1) * round) + (i10 * storyCollageVideoIndex2);
            marginLayoutParams.width = marginLayoutParams2.width + i8;
            marginLayoutParams.height = marginLayoutParams2.height + i8;
            int i11 = i8 / 2;
            marginLayoutParams.setMargins(i2 - i11, i7 - i11, 0, 0);
            marginLayoutParams2.setMargins(i2, i7, 0, 0);
        } else if (storyCollageType == 1 && !z) {
            marginLayoutParams2.width = (width - ((collageColumns + 1) * round)) / collageColumns;
            marginLayoutParams2.height = width - (round * 2);
            int storyCollageVideoIndex3 = MediaItemStory.getStoryCollageVideoIndex(getMediaItem()) % collageColumns;
            int i12 = marginLayoutParams2.width * storyCollageVideoIndex3;
            i7 = (storyCollageVideoIndex3 + 1) * round;
            i2 = i12 + i7;
            marginLayoutParams.width = marginLayoutParams2.width + i8;
            marginLayoutParams.height = marginLayoutParams2.height + i8;
            int i112 = i8 / 2;
            marginLayoutParams.setMargins(i2 - i112, i7 - i112, 0, 0);
            marginLayoutParams2.setMargins(i2, i7, 0, 0);
        }
        i7 = 0;
        i2 = 0;
        marginLayoutParams.width = marginLayoutParams2.width + i8;
        marginLayoutParams.height = marginLayoutParams2.height + i8;
        int i1122 = i8 / 2;
        marginLayoutParams.setMargins(i2 - i1122, i7 - i1122, 0, 0);
        marginLayoutParams2.setMargins(i2, i7, 0, 0);
    }

    private String getMenuViewContentDescription() {
        try {
            return getContext().getString(R.string.speak_s_button, new Object[]{getString(R.string.more_options)});
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "fail to read string", e.getMessage());
            return "";
        }
    }

    private boolean hasPlayableItemForCollage(MediaItem mediaItem) {
        StoryCoverData storyCoverData = (StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem);
        if (storyCoverData == null || !MediaType.Video.equals(storyCoverData.mediaType) || MediaItemStory.getStoryCollageType(mediaItem) == 1) {
            return false;
        }
        return true;
    }

    private void initializePreviewItem(MediaItem mediaItem) {
        StoryCoverData storyCoverData = (StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem);
        MediaItem createEmpty = MediaItemBuilder.createEmpty();
        this.mPreviewVideoItem = createEmpty;
        createEmpty.setPath(storyCoverData.path);
        this.mPreviewVideoItem.setMediaType(MediaType.Video);
        this.mPreviewVideoItem.setOrientation(storyCoverData.orientation);
        VideoPropData.of(this.mPreviewVideoItem).videoDurationInPlayer = VideoPropData.of(mediaItem).videoDurationInPlayer;
        this.mPreviewVideoItem.setSize(storyCoverData.width, storyCoverData.height);
        this.mPreviewVideoItem.setVideoColorFormat(mediaItem.getVideoColorFormat());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$0(View view) {
        view.setLayoutParams(getImage().getLayoutParams());
    }

    public boolean applyImageColorFilter() {
        return false;
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        super.bind(mediaItem);
        if (hasPlayableItemForCollage(mediaItem)) {
            initializePreviewItem(mediaItem);
        }
        ViewGroup viewGroup = this.mMenuViewContainer;
        if (PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE || isSelectionMode()) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        ViewUtils.setVisibility(viewGroup, i2);
        ViewUtils.setContentDescription(this.mMenuViewContainer, getMenuViewContentDescription());
        SharedTransition.setTransitionName(getImage(), StorySharedTransitionHelper.getImageTransitionName(mediaItem));
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        View view = this.mDimView;
        if (view != null) {
            view.post(new a(3, this, view));
        }
    }

    public void bindPreviewView(View view) {
        ViewGroup viewGroup = this.mCoverView;
        if (viewGroup != null) {
            viewGroup.setVisibility(0);
            this.mCoverView.addView(view);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mImageContainer = (ViewGroup) view.findViewById(R.id.thumbnail_container);
        this.mCoverView = (ViewGroup) view.findViewById(R.id.thumbnail_preview_layout);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.item_menu);
        this.mMenuViewContainer = viewGroup;
        ViewUtils.setOnClickListener(viewGroup, new A(9, this));
        this.mDimView = view.findViewById(R.id.dim_view);
        this.mPreviewBackgroundView = view.findViewById(R.id.thumbnail_preview_background);
        this.mTitleContainer = view.findViewById(R.id.title_container);
    }

    public boolean canLooping(int i2) {
        return false;
    }

    public RectF getCropRect(MediaItem mediaItem) {
        if (!PreviewFactory.isPreviewableFormat(mediaItem)) {
            return RectUtils.stringToRectF(MediaItemStory.getStoryCoverRectRatio(mediaItem));
        }
        return null;
    }

    public View getDecoView(int i2) {
        if (i2 == 8) {
            return this.mDurationText;
        }
        if (i2 == 51) {
            return this.mCoverView;
        }
        if (i2 == 52) {
            return this.mMenuViewContainer;
        }
        if (i2 == 56) {
            return this.mImageContainer;
        }
        if (i2 == 57) {
            return this.mTitleContainer;
        }
        return super.getDecoView(i2);
    }

    public int getPreviewDuration() {
        return 10000;
    }

    public MediaItem getPreviewItem() {
        MediaItem mediaItem = this.mPreviewVideoItem;
        if (mediaItem != null) {
            return mediaItem;
        }
        return super.getPreviewItem();
    }

    public PreviewViewHolder.PreviewListener getPreviewListener() {
        return new PreviewViewHolder.PreviewListener() {
            public void onPreviewStart() {
                boolean z;
                super.onPreviewStart();
                if (MediaItemStory.getStoryCollagePath(StoriesViewHolder.this.getMediaItem()) != null) {
                    z = true;
                } else {
                    z = false;
                }
                int storyCollageType = MediaItemStory.getStoryCollageType(StoriesViewHolder.this.getMediaItem());
                StoriesViewHolder.this.mImageView.setVisibility(0);
                if (!z) {
                    return;
                }
                if (storyCollageType == 2 || storyCollageType == 3) {
                    ViewUtils.setVisibility(StoriesViewHolder.this.mPreviewBackgroundView, 0);
                }
            }
        };
    }

    public int getPreviewThumbnailOffsetMs() {
        return 0;
    }

    public void onViewClicked(View view) {
        OnItemMenuClickListener onItemMenuClickListener = this.mOnItemMenuClickListener;
        if (onItemMenuClickListener != null) {
            ((S5.a) onItemMenuClickListener).d.lambda$seOnItemMenuClickListener$2(this, new PointF(view.getX(), view.getY()), getMediaItem());
        }
    }

    public boolean previewCacheable() {
        return !hasPlayableItemForCollage(getMediaItem());
    }

    public void recycle() {
        super.recycle();
        int i2 = 8;
        ViewUtils.setVisibility(this.mCoverView, 8);
        ViewUtils.setVisibility(this.mDimView, 8);
        ViewUtils.setVisibility(this.mPreviewBackgroundView, 8);
        ViewGroup viewGroup = this.mMenuViewContainer;
        if (!PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE) {
            i2 = 0;
        }
        ViewUtils.setVisibility(viewGroup, i2);
        this.mOnItemMenuClickListener = null;
        this.mPreviewVideoItem = null;
    }

    public void removePreviewView(View view) {
        super.removePreviewView(view);
        ViewUtils.setVisibility(this.mCoverView, 8);
        ViewUtils.setVisibility(this.mPreviewBackgroundView, 8);
    }

    public void setCheckboxEnabled(boolean z) {
        int i2;
        super.setCheckboxEnabled(z);
        View view = this.mDimView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
        if (!PreferenceFeatures.OneUi40.SUPPORT_MEMORY_COVER_ACTION_ON_SELECTION_MODE) {
            ViewUtils.setVisibleOrGone(this.mMenuViewContainer, !z);
        }
    }

    public void setChecked(boolean z) {
        int i2;
        super.setChecked(z);
        View view = this.mDimView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        ViewUtils.setVisibility(view, i2);
    }

    public final void setOnItemMenuClickListener(OnItemMenuClickListener onItemMenuClickListener) {
        this.mOnItemMenuClickListener = onItemMenuClickListener;
    }

    public void setViewMatrix() {
        int i2;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            RectF cropRect = getCropRect(mediaItem);
            if (this.mMediaItem.isVideo() || this.mMediaItem.isBroken()) {
                i2 = 0;
            } else {
                i2 = this.mMediaItem.getOrientation();
            }
            setViewMatrix(cropRect, i2, RectUtils.isStretchable(cropRect, this.mMediaItem.getWidth(), this.mMediaItem.getHeight(), this.mMediaItem.getOrientation()));
        }
    }

    public void stopPreview(boolean z) {
        super.stopPreview(z);
        ViewUtils.setVisibility(this.mCoverView, 8);
        ViewUtils.setVisibility(this.mPreviewBackgroundView, 8);
    }

    public boolean supportCollage() {
        return true;
    }

    public boolean supportHighLightPlay() {
        return false;
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & 1024) != 0) {
            return true;
        }
        return super.updateDecoration(i2, objArr);
    }

    public void updatePreviewViewLayout(View view) {
        if (supportCollage()) {
            calculateCollageArea(view, true);
        } else {
            super.updatePreviewViewLayout(view);
        }
    }

    public void addThumbnailBorder(Drawable drawable) {
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        return this;
    }
}
