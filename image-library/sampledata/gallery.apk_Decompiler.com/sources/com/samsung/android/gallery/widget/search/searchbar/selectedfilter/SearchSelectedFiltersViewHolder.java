package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewMatrixUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SearchSelectedFiltersViewHolder extends RecyclerView.ViewHolder {
    ImageView mCircleThumb;
    ViewGroup mContainer;
    View mDeleteIcon;
    ImageView mRoundThumb;
    ViewGroup mSubContainer;
    TextView mTitleView;
    ImageView mTypeIcon;

    public SearchSelectedFiltersViewHolder(View view) {
        super(view);
        this.mContainer = (ViewGroup) view.findViewById(R$id.container);
        this.mSubContainer = (ViewGroup) view.findViewById(R$id.subContainer);
        this.mCircleThumb = (ImageView) view.findViewById(R$id.circle_thumb);
        this.mRoundThumb = (ImageView) view.findViewById(R$id.round_thumb);
        this.mTypeIcon = (ImageView) view.findViewById(R$id.type_icon);
        this.mTitleView = (TextView) view.findViewById(R$id.title);
        this.mDeleteIcon = view.findViewById(R$id.delete_icon);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setRoundThumb$0(MediaItem mediaItem) {
        ViewMatrixUtils.setViewMatrix(this.mRoundThumb, mediaItem, mediaItem.getCropRectRatio(), mediaItem.getOrientation(), mediaItem.getOrientationTag(), true);
    }

    public void setCircleThumb(Bitmap bitmap, boolean z) {
        this.mCircleThumb.setImageBitmap(bitmap);
        this.mCircleThumb.setClipToOutline(true);
        setThumbOnly(z, true);
        if (z) {
            ViewMarginUtils.setMarginRelative(this.mDeleteIcon, Integer.valueOf(this.itemView.getResources().getDimensionPixelOffset(R$dimen.search_toolbar_chip_close_button_margin_start_with_thumb)), (Integer) null, (Integer) null, (Integer) null);
        }
    }

    public void setIcon(int i2) {
        this.mTypeIcon.setImageResource(i2);
        this.mCircleThumb.setVisibility(8);
        this.mTypeIcon.setVisibility(0);
        this.mTitleView.setVisibility(0);
    }

    public void setRoundThumb(Bitmap bitmap, MediaItem mediaItem, boolean z) {
        this.mRoundThumb.setImageBitmap(bitmap);
        this.mRoundThumb.setScaleType(ImageView.ScaleType.MATRIX);
        this.mRoundThumb.post(new b(1, this, mediaItem));
        this.mRoundThumb.setClipToOutline(true);
        setThumbOnly(z, false);
        if (z) {
            ViewMarginUtils.setMarginRelative(this.mDeleteIcon, Integer.valueOf(this.itemView.getResources().getDimensionPixelOffset(R$dimen.search_toolbar_chip_close_button_margin_start_with_thumb)), (Integer) null, (Integer) null, (Integer) null);
        }
    }

    public void setThumbOnly(boolean z, boolean z3) {
        if (z3) {
            this.mCircleThumb.setVisibility(0);
            this.mRoundThumb.setVisibility(8);
        } else {
            this.mCircleThumb.setVisibility(8);
            this.mRoundThumb.setVisibility(0);
        }
        if (z) {
            ViewUtils.setVisibleOrGone(this.mSubContainer, true);
            this.mTypeIcon.setVisibility(8);
            this.mTitleView.setVisibility(8);
            return;
        }
        ViewUtils.setVisibleOrGone(this.mSubContainer, false);
    }

    public void setTitle(String str) {
        this.mTitleView.setText(str);
        this.mTitleView.setVisibility(0);
    }

    public void setWithoutIcon() {
        ViewUtils.setVisibleOrGone(this.mSubContainer, true);
        this.mTitleView.setVisibility(0);
        ViewMarginUtils.setMarginRelative(this.mTitleView, Integer.valueOf(this.mTitleView.getResources().getDimensionPixelOffset(R$dimen.search_toolbar_chip_title_start_margin)), (Integer) null, (Integer) null, (Integer) null);
        this.mTypeIcon.setVisibility(8);
        this.mCircleThumb.setVisibility(8);
    }
}
