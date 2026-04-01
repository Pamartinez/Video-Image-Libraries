package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.graphics.Bitmap;
import android.graphics.RenderEffect;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.abstraction.RelationshipKeySet;
import com.samsung.android.gallery.module.data.CreatureData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurParams;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryPeopleItemViewHolder extends ImageTitleViewHolder {
    private final boolean mIsExpansionMode;
    private boolean mIsSelectionMode;
    private View mItemContainer;
    private ImageView mRemoveIcon;
    private View mTitleArea;

    public CategoryPeopleItemViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mIsExpansionMode = z;
    }

    private boolean hasTitle(MediaItem mediaItem) {
        return !TextUtils.isEmpty(getTitleText(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindImage$0() {
        int width = this.mImageView.getWidth();
        int height = this.mImageView.getHeight();
        if (width > 0 && height > 0) {
            this.mImageView.setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumCover(new BlurParams.Builder(width, height).setPercent(0.5f).setPivotPercent(0.25f).build()));
        }
    }

    public void bind(MediaItem mediaItem) {
        int i2;
        int i7 = 0;
        super.bind(mediaItem);
        boolean hasTitle = hasTitle(mediaItem);
        TextView textView = this.mTitleText;
        if (hasTitle) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(textView, i2);
        View view = this.mTitleArea;
        if (!hasTitle) {
            i7 = 4;
        }
        ViewUtils.setVisibility(view, i7);
        if (!this.mIsExpansionMode) {
            ViewMarginUtils.setMarginRelative(this.itemView, (Integer) null, 0, (Integer) null, 0);
        }
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        if (!PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || Build.VERSION.SDK_INT < 31) {
            return;
        }
        if (ViewUtils.isVisible(this.mTitleArea)) {
            this.mImageView.post(new b(25, this));
        } else {
            this.mImageView.setRenderEffect((RenderEffect) null);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mRemoveIcon = (ImageView) view.findViewById(R.id.creature_remove_icon);
        this.mItemContainer = view.findViewById(R.id.item_container);
        this.mTitleArea = view.findViewById(R.id.title_area);
    }

    public String getContentDescription() {
        int i2;
        if (hasTitle(getMediaItem())) {
            return super.getContentDescription();
        }
        if (getMediaItem().isPeople()) {
            i2 = R.string.untagged_person;
        } else {
            i2 = R.string.untagged_pet;
        }
        return getString(i2);
    }

    public String getTitleText(MediaItem mediaItem) {
        CreatureData of2 = CreatureData.of(mediaItem);
        if (!TextUtils.isEmpty(of2.creatureName)) {
            return of2.creatureName;
        }
        if (!TextUtils.isEmpty(of2.relationshipType)) {
            return RelationshipKeySet.getRelationshipName(getContext(), of2.relationshipType);
        }
        return super.getTitleText(mediaItem);
    }

    public boolean isCheckBoxEnabled() {
        return this.mIsSelectionMode;
    }

    public void recycle() {
        super.recycle();
        ViewUtils.setVisibility(this.mRemoveIcon, 8);
    }

    public void setCheckboxEnabled(boolean z) {
        this.mIsSelectionMode = z;
    }

    public void setChecked(boolean z) {
        if (z) {
            drawVisualCue();
        } else {
            eraseVisualCue();
        }
    }

    public void setRemoveIconEnable(boolean z, int i2) {
        if (z) {
            ((FrameLayout.LayoutParams) this.mRemoveIcon.getLayoutParams()).gravity = i2;
        }
        ViewUtils.setVisibleOrGone(this.mRemoveIcon, z);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            ViewUtils.setViewShape(this.mItemContainer, i2, f);
        }
        return super.setThumbnailShape(i2, f);
    }

    public void toggleVisualCue() {
        MediaItem mediaItem = getMediaItem();
        if (mediaItem == null || CreatureData.of(mediaItem).isCreatureHide) {
            eraseVisualCue();
        } else {
            drawVisualCue();
        }
    }
}
