package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PdcRecommendItemViewHolder extends ImageTitleViewHolder {
    private final boolean mIsExpansionMode;
    private boolean mIsSelectionMode;
    private ImageView mRemoveIcon;

    public PdcRecommendItemViewHolder(View view, int i2, boolean z) {
        super(view, i2);
        this.mIsExpansionMode = z;
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        if (TextUtils.isEmpty(getTitleText(mediaItem))) {
            this.mTitleText.setVisibility(4);
        }
        if (!this.mIsExpansionMode) {
            ViewMarginUtils.setMarginRelative(this.itemView, (Integer) null, 0, (Integer) null, 0);
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mRemoveIcon = (ImageView) view.findViewById(R.id.creature_remove_icon);
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
}
