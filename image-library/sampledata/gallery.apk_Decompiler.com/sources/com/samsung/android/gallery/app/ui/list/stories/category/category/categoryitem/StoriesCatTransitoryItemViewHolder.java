package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import W8.C0579a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.database.dbtype.StoryLevel2Cat;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.scsp.media.api.constant.MediaApiContract;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatTransitoryItemViewHolder extends StoriesCatItemBaseViewHolder {
    protected int mChildCount;
    private ViewGroup mContentParent;
    protected int mDataCount;
    protected View mGradientView;
    private TextView mHour;
    private ImageView mHourglass;
    private AnimatedVectorDrawable mHourglassDrawable;
    private ImageView mShapeView;
    protected View mTitleArea;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class IrregularShapeHolder {
        static final int[] shapes = {R.drawable.irregular_1_mask_only_1_invert, R.drawable.irregular_1_mask_only_2_invert, R.drawable.irregular_1_mask_only_3_invert, R.drawable.irregular_1_mask_only_4_invert, R.drawable.irregular_1_mask_only_5_invert, R.drawable.irregular_1_mask_only_6_invert, R.drawable.irregular_1_mask_only_7_invert, R.drawable.irregular_1_mask_only_8_invert, R.drawable.irregular_1_mask_only_9_invert, R.drawable.irregular_1_mask_only_10_invert};
    }

    public StoriesCatTransitoryItemViewHolder(View view, int i2, String str) {
        super(view, i2, str);
    }

    private int getRemainDays(MediaItem mediaItem) {
        int todayInMillis;
        long storyCreationTime = MediaItemStory.getStoryCreationTime(mediaItem);
        int maxExpiryDay = getMaxExpiryDay(mediaItem);
        if (storyCreationTime < 0 || (todayInMillis = (int) ((((((long) maxExpiryDay) * MediaApiContract.DAY_IN_MILLI) + storyCreationTime) - TimeUtil.getTodayInMillis()) / MediaApiContract.DAY_IN_MILLI)) <= 0) {
            return -1;
        }
        return todayInMillis;
    }

    private String getRemainDaysString(MediaItem mediaItem) {
        int remainDays = getRemainDays(mediaItem);
        if (remainDays > 0) {
            return AppResources.getQuantityString(R.plurals.n_days, remainDays, Integer.valueOf(remainDays));
        }
        return null;
    }

    private Drawable getShapeDrawable(int i2) {
        int i7 = (i2 % 3) + i2;
        int[] iArr = IrregularShapeHolder.shapes;
        int length = i7 % iArr.length;
        if (getContext() != null) {
            return getContext().getDrawable(iArr[length]);
        }
        return null;
    }

    private boolean isCoverDesignItem(MediaItem mediaItem) {
        return mediaItem.isPng();
    }

    private void onDateUpdated() {
        MediaItem mediaItem;
        if (this.mHourglass != null && (mediaItem = this.mMediaItem) != null) {
            updateHourglassView(mediaItem);
        }
    }

    private void resetBorder() {
        setThumbnailShape(1, 0.0f);
    }

    private boolean supportShape(MediaItem mediaItem) {
        if (!PocFeatures.isEnabled(PocFeatures.StoriesIrregularCover) || isCoverDesignItem(mediaItem) || StoryHelper.isVideoItem(mediaItem) || mediaItem.isBroken()) {
            return false;
        }
        return true;
    }

    private void updateHourglassView(MediaItem mediaItem) {
        boolean z;
        if (this.mHourglass != null) {
            boolean z3 = false;
            if (MediaItemStory.getStoryType(mediaItem) != StoryType.N_YEARS_AGO.getValue()) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                String remainDaysString = getRemainDaysString(mediaItem);
                if (!TextUtils.isEmpty(remainDaysString)) {
                    this.mHour.setText(remainDaysString);
                    ViewUtils.setContentDescription((View) this.mHourglass.getParent(), remainDaysString);
                }
                Optional.ofNullable(this.mHourglassDrawable).ifPresent(new C0579a(1));
                ViewUtils.setVisibleOrGone((View) this.mHourglass.getParent(), z3);
            }
            z3 = z;
            Optional.ofNullable(this.mHourglassDrawable).ifPresent(new C0579a(1));
            ViewUtils.setVisibleOrGone((View) this.mHourglass.getParent(), z3);
        }
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        getViewForActionListener().setAccessibilityDelegate(this.mAccessibilityDelegate);
        ViewUtils.setVisibleOrGone(this.mNewLabel, isNewLabel(mediaItem));
        updateView(mediaItem);
        DebugHelper.showInfoView((ListViewHolder) this, (int) R.id.deco_layout, new Rect(5, 10, 55, 5));
    }

    public /* bridge */ /* synthetic */ void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContentParent = (ViewGroup) view.findViewById(R.id.content_parent);
        this.mTitleArea = view.findViewById(R.id.title_area);
        this.mGradientView = view.findViewById(R.id.gradient_view);
        this.mShapeView = (ImageView) view.findViewById(R.id.shape_view);
        this.mHourglass = (ImageView) view.findViewById(R.id.hourglass_icon);
        this.mHour = (TextView) view.findViewById(R.id.hourglass_text);
        ImageView imageView = this.mHourglass;
        if (imageView != null) {
            this.mHourglassDrawable = (AnimatedVectorDrawable) imageView.getDrawable();
        }
        ViewUtils.setVisibleOrGone(this.mGradientView, !supportGradientBlur());
    }

    public void executeHourglass() {
        ImageView imageView = this.mHourglass;
        if (imageView != null && ViewUtils.isVisible((View) imageView.getParent())) {
            Optional.ofNullable(this.mHourglassDrawable).ifPresent(new C0579a(2));
        }
    }

    public String getContentDescription() {
        if (this.mMediaItem == null) {
            return "";
        }
        String contentDescription = super.getContentDescription();
        int remainDays = getRemainDays(this.mMediaItem);
        if (remainDays <= 0) {
            return contentDescription;
        }
        StringBuilder t = C0212a.t(contentDescription, ArcCommonLog.TAG_COMMA);
        t.append(AppResources.getQuantityString(R.plurals.n_days_until_story_is_deleted, remainDays, Integer.valueOf(remainDays)));
        return t.toString();
    }

    public View getDecoView(int i2) {
        if (i2 == 59) {
            return this.mFavoriteView;
        }
        return super.getDecoView(i2);
    }

    public int getMaxExpiryDay(MediaItem mediaItem) {
        if (MediaItemStory.getStoryType(mediaItem) == StoryType.N_YEARS_AGO.getValue()) {
            return 5;
        }
        if (MediaItemStory.getStorySaType(mediaItem) == StoryLevel2Cat.YEARLY_RECAP_BRIEF.getType() || MediaItemStory.getStorySaType(mediaItem) == StoryLevel2Cat.YEARLY_RECAP_PLACES.getType() || MediaItemStory.getStorySaType(mediaItem) == StoryLevel2Cat.YEARLY_RECAP_MOMENTS.getType() || MediaItemStory.getStorySaType(mediaItem) == StoryLevel2Cat.YEARLY_RECAP_PEOPLE_AND_PETS.getType()) {
            return 18;
        }
        return 7;
    }

    public /* bridge */ /* synthetic */ int getPreviewDuration() {
        return super.getPreviewDuration();
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
        return (TextView) this.mContentParent.findViewById(R.id.duration);
    }

    public View getViewForActionListener() {
        ViewGroup viewGroup = this.mContentParent;
        if (viewGroup != null) {
            return viewGroup;
        }
        return this.itemView;
    }

    public void handleEvent(int i2, Object... objArr) {
        if (i2 == 1000) {
            setDataCount(objArr[0].intValue(), objArr[1].intValue());
        } else if (i2 == 1001) {
            setAccessibilityBackground();
        } else if (i2 == 1002) {
            requestAccessibilityFocus();
        } else if (i2 == 1003) {
            executeHourglass();
        } else if (i2 == 1004) {
            onDateUpdated();
        }
        super.handleEvent(i2, objArr);
    }

    public void initBorder() {
        setThumbnailShape(1, (float) ItemProperty.getItemRadius(getContext(), "location://stories/category/transitory"));
    }

    public void recycle() {
        super.recycle();
        View view = this.mGradientView;
        if (view != null) {
            view.setTransitionName((String) null);
        }
        Optional.ofNullable(this.mHourglassDrawable).ifPresent(new C0579a(1));
        ViewUtils.setViewSize(this.mContentParent, -1, -1);
    }

    public void requestAccessibilityFocus() {
        this.mContentParent.sendAccessibilityEvent(8);
    }

    public void resetViewProperty() {
        resetViewProperty(this.itemView);
        resetViewProperty(this.mContentParent);
    }

    public void setContentDescription() {
        if (this.mMediaItem != null) {
            this.mContentParent.setContentDescription(getContentDescription());
        }
    }

    public void setDataCount(int i2, int i7) {
        this.mDataCount = i2;
        this.mChildCount = i7;
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

    public boolean supportCollage() {
        return false;
    }

    public boolean supportGradientBlur() {
        return false;
    }

    public boolean updateDecoration(int i2, Object... objArr) {
        if ((i2 & SerializeOptions.SORT) != 0) {
            updateView(getMediaItem());
        }
        return super.updateDecoration(i2, objArr);
    }

    public void updateView(MediaItem mediaItem) {
        ViewUtils.setVisibleOrGone(this.mShapeView, supportShape(mediaItem));
        if (supportShape(mediaItem)) {
            resetBorder();
            this.mShapeView.setImageDrawable(getShapeDrawable(MediaItemStory.getStoryId(mediaItem)));
        } else {
            initBorder();
        }
        if (isCoverDesignItem(mediaItem)) {
            this.mImageView.setBackground((Drawable) null);
            ViewUtils.setVisibleOrGone(this.mGradientView, false);
        } else {
            this.mImageView.setBackgroundColor(this.mBgColor);
            ViewUtils.setVisibleOrGone(this.mGradientView, true);
        }
        updateHourglassView(mediaItem);
    }

    private void resetViewProperty(View view) {
        ViewUtils.setAlpha(view, 1.0f);
        ViewUtils.setTranslationX(view, 0.0f);
        ViewUtils.setScale(view, 1.0f, 1.0f);
    }

    public void setAccessibilityBackground() {
    }

    public void setFavoriteInfo(boolean z) {
    }
}
