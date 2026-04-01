package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevitalizedPicturesViewHolder extends PreviewViewHolder {
    TextView mResolution;
    RelativeLayout mThumbnailLayout;
    TextView mTitle;
    LinearLayout mTitleLayout;

    public RevitalizedPicturesViewHolder(View view, int i2) {
        super(view, i2);
        updateLayout(i2);
        updateBorder();
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mThumbnailLayout = (RelativeLayout) view.findViewById(R.id.thumbnail_layout);
        this.mTitleLayout = (LinearLayout) view.findViewById(R.id.revitalized_card_title_layout);
        this.mTitle = (TextView) view.findViewById(R.id.revitalized_card_title);
        this.mResolution = (TextView) view.findViewById(R.id.resolution);
    }

    public TextView getCountView() {
        return this.mResolution;
    }

    public TextView getTitleView() {
        return this.mTitle;
    }

    public void setRemasterImageText(MediaItem mediaItem, String str) {
        BitmapOptions parse = BitmapOptionsFactory.parse(mediaItem.getPath(), true);
        ViewUtils.setText(this.mTitle, str);
        ViewUtils.setMaxLines(this.mTitle, RevitalizedDelegate.getRevitalizedTitleMaxLine(getViewType(), true));
        if (RevitalizedDelegate.compareResolution(mediaItem, parse)) {
            ViewUtils.setVisibility(this.mResolution, 8);
            return;
        }
        ViewUtils.setMaxLines(this.mResolution, RevitalizedDelegate.getRevitalizedSubTitleMaxLine(getViewType()));
        ViewUtils.setText(this.mResolution, RevitalizedDelegate.getResolutionDescription(mediaItem, parse, getViewType()));
        ViewUtils.setVisibility(this.mResolution, 0);
    }

    public void updateBorder() {
        setThumbnailShape(1, getContext().getResources().getDimension(R.dimen.revitalized_card_round_radius));
        addThumbnailBorder(getContext().getDrawable(R.drawable.remaster_thumbnail_border));
    }

    public void updateLayout(int i2) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mTitleLayout.getLayoutParams();
        int[] titleLayoutParams = RevitalizedDelegate.getTitleLayoutParams(i2);
        marginLayoutParams.height = titleLayoutParams[0];
        marginLayoutParams.topMargin = titleLayoutParams[1];
        marginLayoutParams.setMarginEnd(titleLayoutParams[2]);
        this.mTitleLayout.setLayoutParams(marginLayoutParams);
        this.mResolution.setTextSize(0, RevitalizedDelegate.getResolutionDescriptionSize(i2));
    }

    public void setRemasterImageText(String str) {
        ViewUtils.setText(this.mTitle, str);
        ViewUtils.setMaxLines(this.mTitle, RevitalizedDelegate.getRevitalizedTitleMaxLine(getViewType(), false));
        ViewUtils.setVisibility(this.mResolution, 8);
    }
}
