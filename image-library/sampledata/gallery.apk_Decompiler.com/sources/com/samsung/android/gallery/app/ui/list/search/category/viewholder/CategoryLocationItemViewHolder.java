package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.database.dbtype.TagType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurParams;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import k6.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryLocationItemViewHolder extends CategoryTitleCountViewHolder {
    private final boolean mIsBottomBlur;
    protected ImageView mPoiIcon;

    public CategoryLocationItemViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mIsBottomBlur = z;
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
        super.bind(mediaItem);
        if (!PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71 && PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            ViewMarginUtils.setMarginRelative(this.itemView, (Integer) null, Integer.valueOf(this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_category_item_top_margin_61)), (Integer) null, 0);
        }
        if (this.mPoiIcon == null || TagType.get(mediaItem.getTagType()) != TagType.POI) {
            ViewUtils.setVisibility(this.mPoiIcon, 8);
            return;
        }
        ImageView imageView = this.mPoiIcon;
        if (Features.isEnabled(Features.SUPPORT_SAMSUNG_PLACE)) {
            i2 = R.drawable.gallery_ic_mapview_location_logo;
        } else {
            i2 = R.drawable.gallery_ic_mapview_foursquare;
        }
        imageView.setImageResource(i2);
        ViewUtils.setVisibility(this.mPoiIcon, 0);
    }

    public void bindImage(Bitmap bitmap) {
        super.bindImage(bitmap);
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && Build.VERSION.SDK_INT >= 31) {
            ViewUtils.setTextColor(this.mCount, R.color.search_category_item_location_count_color_oneui85);
            if (this.mIsBottomBlur) {
                this.mImageView.post(new b(24, this));
            }
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mPoiIcon = (ImageView) view.findViewById(R.id.poi_icon);
    }
}
