package com.samsung.android.gallery.widget.search.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.samsung.android.gallery.module.search.root.IntelligentSearchIndexFieldIcon;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.R$color;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchFiltersViewHolder extends ListViewHolder {
    ImageView mCircleThumb;
    ViewGroup mContainer;
    TextView mKeywordView;
    final boolean mSupportTheme;
    ImageView mTypeIcon;

    public SearchFiltersViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mSupportTheme = z;
    }

    private void bindFieldIcon(IntelligentSearchIndexFieldIcon intelligentSearchIndexFieldIcon, boolean z) {
        int i2;
        if (this.mTypeIcon == null || intelligentSearchIndexFieldIcon == null || intelligentSearchIndexFieldIcon.getDrawableResId() == null) {
            ViewUtils.setVisibility(this.mTypeIcon, 8);
            ViewMarginUtils.setHorizontalPadding(this.mContainer, this.itemView.getContext().getResources().getDimensionPixelSize(R$dimen.search_filter_result_item_horizontal_padding));
            return;
        }
        Drawable drawable = this.itemView.getContext().getDrawable(intelligentSearchIndexFieldIcon.getDrawableResId().intValue());
        if (drawable == null) {
            ViewUtils.setVisibility(this.mTypeIcon, 8);
            ViewMarginUtils.setHorizontalPadding(this.mContainer, this.itemView.getContext().getResources().getDimensionPixelSize(R$dimen.search_filter_result_item_horizontal_padding));
            return;
        }
        ViewUtils.setVisibility(this.mTypeIcon, 0);
        ViewMarginUtils.setStartPadding(this.mContainer, this.itemView.getContext().getResources().getDimensionPixelSize(R$dimen.search_filter_result_item_start_padding));
        ViewMarginUtils.setEndPadding(this.mContainer, this.itemView.getContext().getResources().getDimensionPixelSize(R$dimen.search_filter_result_item_end_padding));
        Drawable mutate = drawable.mutate();
        if (!this.mSupportTheme) {
            mutate.setTint(this.itemView.getContext().getColor(R$color.search_filter_icon_color_no_theme));
            this.mTypeIcon.setBackground(mutate);
            return;
        }
        if (intelligentSearchIndexFieldIcon.getTintColorResId() != null) {
            mutate.setTint(this.itemView.getContext().getColor(intelligentSearchIndexFieldIcon.getTintColorResId().intValue()));
        } else if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            Context context = this.itemView.getContext();
            if (z) {
                i2 = R$color.search_all_filter_pushed_text_color;
            } else {
                i2 = R$color.search_all_filter_text_color;
            }
            mutate.setTint(context.getColor(i2));
        }
        this.mTypeIcon.setBackground(mutate);
    }

    private void bindThumbTypeAllFilterVH(Bitmap bitmap, boolean z) {
        int i2 = 0;
        ViewUtils.setVisibility(this.mCircleThumb, 0);
        this.mCircleThumb.setImageBitmap(bitmap);
        this.mCircleThumb.setClipToOutline(true);
        ViewGroup viewGroup = this.mContainer;
        if (z) {
            i2 = this.mCircleThumb.getResources().getDimensionPixelSize(R$dimen.search_all_filter_creature_selector_line);
        }
        ViewMarginUtils.setPadding(viewGroup, i2);
        this.mContainer.setSelected(z);
        setEnable(true);
    }

    private void bindThumbTypeVH(Bitmap bitmap, boolean z, boolean z3) {
        ColorDrawable colorDrawable;
        ViewUtils.setVisibility(this.mCircleThumb, 0);
        ViewUtils.setVisibility(this.mTypeIcon, 8);
        ViewUtils.setVisibility(this.mKeywordView, 8);
        ViewMarginUtils.setHorizontalPadding(this.mContainer, 0);
        ImageView imageView = this.mCircleThumb;
        if (z) {
            colorDrawable = new ColorDrawable(-1728053248);
        } else {
            colorDrawable = null;
        }
        imageView.setForeground(colorDrawable);
        this.mCircleThumb.setImageBitmap(bitmap);
        this.mCircleThumb.setClipToOutline(true);
        this.mContainer.setSelected(false);
        setEnable(z3);
    }

    public void bind(String str, boolean z, boolean z3, IntelligentSearchIndexFieldIcon intelligentSearchIndexFieldIcon) {
        ViewUtils.setVisibility(this.mCircleThumb, 8);
        ViewUtils.setVisibility(this.mTypeIcon, 0);
        ViewUtils.setVisibility(this.mKeywordView, 0);
        this.mCircleThumb.setImageDrawable((Drawable) null);
        this.mContainer.setSelected(z);
        bindFieldIcon(intelligentSearchIndexFieldIcon, z);
        ViewUtils.setText(this.mKeywordView, str);
        setEnable(z3);
    }

    public void bindThumbnailTypeViewHolder(Bitmap bitmap, boolean z, boolean z3) {
        if (getItemViewType() == 1) {
            bindThumbTypeAllFilterVH(bitmap, z);
        } else {
            bindThumbTypeVH(bitmap, z, z3);
        }
    }

    public final void bindView(View view) {
        this.mContainer = (ViewGroup) view.findViewById(R$id.container);
        this.mTypeIcon = (ImageView) view.findViewById(R$id.type_icon);
        this.mCircleThumb = (ImageView) view.findViewById(R$id.circle_thumb);
        this.mKeywordView = (TextView) view.findViewById(R$id.title);
    }

    public void setEnable(boolean z) {
        float f;
        this.mContainer.setEnabled(z);
        ViewGroup viewGroup = this.mContainer;
        if (z) {
            f = 1.0f;
        } else {
            f = 0.5f;
        }
        viewGroup.setAlpha(f);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mContainer.setOnClickListener(onClickListener);
    }
}
