package com.samsung.android.gallery.app.ui.list.stories.viewholder;

import Bb.g;
import Bb.k;
import H7.A;
import M6.a;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.story.DurationHelper;
import com.samsung.android.gallery.module.story.FontTypefaceUtils;
import com.samsung.android.gallery.module.story.TitleAlign;
import com.samsung.android.gallery.module.story.smartrect.CoverRect;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.story.CoverGradientBlur;
import com.samsung.android.gallery.widget.story.DebugHelper;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchViewHolder extends StoriesViewHolder {
    private final DimenHelper mDimenHelper;
    Drawable[] mFavoriteDrawable;
    ImageView mFavoriteView;
    private boolean mIsLand;
    private final View.OnLayoutChangeListener mLayoutChangeListener = new g(3, this);
    private OnItemFavoriteClickListener mOnFavoriteClickListener;
    private int mPinchDepth;
    private FrameLayout mThumbnailContainer;
    private TitleAlign mTitleAlign;
    private LinearLayout mTitleContainer;
    private LinearLayout mTopRightDeco;

    @FunctionalInterface
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnItemFavoriteClickListener {
    }

    public StoriesPinchViewHolder(View view, int i2, DimenHelper dimenHelper) {
        super(view, i2);
        this.mDimenHelper = dimenHelper;
    }

    private void alignTitleLayout(int i2) {
        resetTitleAlignInfo(i2);
        if (this.mTitleAlign == null && getMediaItem() != null) {
            this.mTitleAlign = getTitleAlign(getMediaItem(), i2);
        }
        TitleAlign titleAlign = this.mTitleAlign;
        if (titleAlign == null) {
            titleAlign = TitleAlign.BOTTOM_MIDDLE;
        }
        titleAlign.setLayoutAlign((View) this.mTitleContainer);
        titleAlign.setTextAlign(getTitleView());
        titleAlign.setTextAlign(getSubTitleView());
        titleAlign.setGradientDirection(this.mGradientView);
    }

    private void applyCoverBlur() {
        if (PreferenceFeatures.OneUi8x.STORY_COVER_BLUR) {
            CoverGradientBlur.apply(this.mThumbnailContainer);
        }
    }

    private int getBorderResId(int i2) {
        if (i2 != 0) {
            return R.drawable.stories_thumbnail_border_large;
        }
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return R.drawable.stories_category_item_border_v85;
        }
        if (PreferenceFeatures.OneUi7x.STORY_ONE_UI_70) {
            return R.drawable.stories_category_item_border;
        }
        return R.drawable.stories_thumbnail_border_small;
    }

    private TitleAlign getTitleAlign(MediaItem mediaItem, int i2) {
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            return TitleAlign.BOTTOM_MIDDLE;
        }
        if (!PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN || !needTitleAlign(i2)) {
            return TitleAlign.BOTTOM_MIDDLE;
        }
        return TitleAlign.getTitleAlign(mediaItem);
    }

    private boolean needTitleAlign(int i2) {
        if (isTabletDpi() || i2 == 1) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onFavoriteViewClicked(View view) {
        OnItemFavoriteClickListener onItemFavoriteClickListener = this.mOnFavoriteClickListener;
        if (onItemFavoriteClickListener != null) {
            k kVar = (k) onItemFavoriteClickListener;
            ((StoriesPinchViewAdapter) kVar.e).lambda$setOnItemFavoriteClickListener$0((ListViewHolder) kVar.f, kVar.d, this, getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public void onLayoutChanged(View view, int i2, int i7, int i8, int i10, int i11, int i12, int i13, int i14) {
        if (i13 - i11 != i8 - i2) {
            applyCoverBlur();
        }
    }

    private void resetTitleAlignInfo(int i2) {
        if (this.mPinchDepth != i2 || this.mIsLand != ResourceCompat.isLandscape(getContext())) {
            this.mTitleAlign = null;
            this.mPinchDepth = i2;
            this.mIsLand = ResourceCompat.isLandscape(getContext());
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
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.viewholder.StoriesPinchViewHolder.setFavoriteInfo(boolean):void");
    }

    private void setTitleFont(TextView textView, MediaItem mediaItem) {
        if (PreferenceFeatures.OneUi5x.SUPPORT_STORIES_TITLE_ALIGN && mediaItem != null) {
            textView.setTypeface(FontTypefaceUtils.getTextFont(MediaItemStory.getStorySaType(mediaItem), mediaItem.getAlbumID(), StringCompat.isKorean(mediaItem.getTitle())));
        }
    }

    private void updateCheckBoxLayout(int i2, boolean z) {
        if (isCheckBoxEnabled()) {
            CheckBox checkbox = getCheckbox();
            int sideGap = this.mDimenHelper.getSideGap(i2, z) + this.mDimenHelper.getDimen(R.dimen.stories_view_checkbox_margin);
            ViewMarginUtils.setMarginRelative(checkbox, Integer.valueOf(sideGap), Integer.valueOf(sideGap), (Integer) null, (Integer) null);
        }
    }

    private void updateDecoViewLayout(int i2, boolean z) {
        updateTitleLayout(i2, z);
        lambda$updateTopRightDecoLayout$0(i2, z);
        updateCheckBoxLayout(i2, z);
        updateBorder(i2, z);
    }

    private void updateTitleLayout(int i2, boolean z) {
        TextView titleView = getTitleView();
        TextView subTitleView = getSubTitleView();
        titleView.setTextSize(0, (float) this.mDimenHelper.getTitleTextSize(i2, z));
        subTitleView.setTextSize(0, (float) this.mDimenHelper.getSubTitleTextSize(i2, z));
        ViewMarginUtils.setMarginRelative(titleView, 0, 0, 0, Integer.valueOf(this.mDimenHelper.getTitleGap(i2, z)));
        int sideGap = this.mDimenHelper.getSideGap(i2, z);
        int titleHorizontalMargin = getTitleHorizontalMargin(i2, z);
        int i7 = titleHorizontalMargin + sideGap;
        ViewMarginUtils.setMarginRelative(this.mTitleContainer, Integer.valueOf(i7), Integer.valueOf(sideGap), Integer.valueOf(i7), Integer.valueOf(getTitleBottomMargin(i2, z) + sideGap));
        ViewMarginUtils.setMarginRelative((View) this.mTitleContainer.getParent(), (Integer) null, Integer.valueOf(-this.mDimenHelper.getFavoritePadding(i2, z)), (Integer) null, (Integer) null);
        alignTitleLayout(i2);
        setTitleFont(titleView, this.mMediaItem);
    }

    /* access modifiers changed from: private */
    /* renamed from: updateTopRightDecoLayout */
    public void lambda$updateTopRightDecoLayout$0(int i2, boolean z) {
        int i7;
        if (this.itemView.getWidth() == 0) {
            this.itemView.post(new a(this, i2, z, 0));
            return;
        }
        int sideGap = this.mDimenHelper.getSideGap(i2, z);
        if (this.mFavoriteView != null) {
            ViewGroup.LayoutParams layoutParams = this.itemView.getLayoutParams();
            if (layoutParams == null || (i7 = layoutParams.width) <= 0) {
                i7 = this.itemView.getWidth();
            }
            sideGap += Math.max(((int) (this.mDimenHelper.getTopEndDecoMarginRatio() * ((float) i7))) - this.mFavoriteView.getPaddingStart(), 0);
        }
        ViewMarginUtils.setMarginRelative(this.mTopRightDeco, 0, Integer.valueOf(sideGap), Integer.valueOf(sideGap), 0);
    }

    public void addThumbnailBorder(Drawable drawable) {
        this.mImageView.setForeground(drawable);
    }

    public void bind(MediaItem mediaItem) {
        boolean z;
        super.bind(mediaItem);
        this.mThumbnailContainer.addOnLayoutChangeListener(this.mLayoutChangeListener);
        if (MediaItemStory.getStoryFavorite(getMediaItem()) > 0) {
            z = true;
        } else {
            z = false;
        }
        setFavoriteInfo(z);
        resetTitleAlignInfo(-1);
        DebugHelper.showInfoView((ListViewHolder) this, (int) R.id.deco_view_layout, true);
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        applyCoverBlur();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mFavoriteDrawable = new Drawable[]{getContext().getDrawable(R.drawable.story_favorite_off), getContext().getDrawable(R.drawable.story_favorite_on)};
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_favorite);
        this.mFavoriteView = imageView;
        ViewUtils.setOnClickListener(imageView, new A(8, this));
        this.mThumbnailContainer = (FrameLayout) view.findViewById(R.id.thumbnail_container);
        this.mTitleContainer = (LinearLayout) view.findViewById(R.id.title_container);
        this.mTopRightDeco = (LinearLayout) view.findViewById(R.id.top_right_deco);
        if (PreferenceFeatures.OneUi8x.STORY_ONE_UI_85) {
            ViewUtils.setVisibleOrGone(this.mGradientView, false);
        }
    }

    public View getDecoView(int i2) {
        if (i2 == 59) {
            return this.mFavoriteView;
        }
        return super.getDecoView(i2);
    }

    public int getPreviewDuration() {
        int i2;
        if (MediaItemStory.isLiveEffectItem(getMediaItem())) {
            i2 = 7000;
        } else {
            i2 = super.getPreviewDuration();
        }
        return DurationHelper.getItemDuration(getMediaItem(), new int[]{i2, i2});
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

    public int getTitleBottomMargin(int i2, boolean z) {
        return (int) (this.mDimenHelper.getTitleBottomMargin(i2, z) * ((float) ResourceCompat.getCoarseWindowWidth(this.itemView.getResources())));
    }

    public int getTitleHorizontalMargin(int i2, boolean z) {
        return (int) (this.mDimenHelper.getTitleHorizontalMargin(i2, z) * ((float) ResourceCompat.getCoarseWindowWidth(this.itemView.getResources())));
    }

    public boolean isTabletDpi() {
        return getContext().getResources().getBoolean(R.bool.isTabletDpi);
    }

    public void recycle() {
        super.recycle();
        View view = this.mGradientView;
        if (view != null) {
            view.setTransitionName((String) null);
        }
        this.mThumbnailContainer.removeOnLayoutChangeListener(this.mLayoutChangeListener);
        this.mOnFavoriteClickListener = null;
        this.mTitleAlign = null;
    }

    public void setCheckboxEnabled(boolean z) {
        String str;
        super.setCheckboxEnabled(z);
        ImageView imageView = this.mFavoriteView;
        if (imageView != null) {
            if (z) {
                str = AppResources.getString(R.string.disabled);
            } else {
                str = "";
            }
            imageView.setStateDescription(str);
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

    public final void setOnItemFavoriteClickListener(OnItemFavoriteClickListener onItemFavoriteClickListener) {
        this.mOnFavoriteClickListener = onItemFavoriteClickListener;
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

    public void updateBorder(int i2, boolean z) {
        setThumbnailShape(1, (float) this.mDimenHelper.getBorderRadius(i2, z));
        addThumbnailBorder(getContext().getDrawable(getBorderResId(i2)));
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

    public void initBorder() {
    }
}
