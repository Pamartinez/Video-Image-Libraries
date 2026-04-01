package com.samsung.android.gallery.app.ui.viewholders;

import android.view.View;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WidgetPicturesDateLocationViewHolder extends DateLocationViewHolder {
    public WidgetPicturesDateLocationViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        int color = view.getContext().getColor(R.color.white_color);
        this.mDateText.setTextColor(color);
        this.mLocationText.setTextColor(color);
        this.mLocationText.setOnClickListener((View.OnClickListener) null);
    }

    public void onClick(View view) {
    }
}
