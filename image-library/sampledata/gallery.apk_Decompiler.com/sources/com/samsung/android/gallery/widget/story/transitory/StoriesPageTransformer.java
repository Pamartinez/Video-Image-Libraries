package com.samsung.android.gallery.widget.story.transitory;

import android.view.View;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.story.transitory.PageTransformer;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPageTransformer extends PageTransformer {
    private PageTransformer.InnerMarginTransformer mMarginTransformer;

    private View getContentView(View view) {
        return view.findViewById(R$id.content_parent);
    }

    private void setPageScale(View view, float f) {
        if (f <= -1.0f || f >= 1.0f) {
            View contentView = getContentView(view);
            float f5 = PageTransformer.SCALE_AMOUNT;
            ViewUtils.setScale(contentView, 1.0f - f5, 1.0f - f5);
            return;
        }
        float abs = 1.0f - (Math.abs(f) * PageTransformer.SCALE_AMOUNT);
        ViewUtils.setScale(getContentView(view), abs, abs);
    }

    public void setPageMargin(int i2, int i7, int i8) {
        PageTransformer.InnerMarginTransformer innerMarginTransformer = this.mMarginTransformer;
        if (innerMarginTransformer == null) {
            this.mMarginTransformer = new PageTransformer.InnerMarginTransformer(i2, i7, i8);
        } else {
            innerMarginTransformer.setPageMargin(i2, i7, i8);
        }
    }

    public void transformPage(View view, float f) {
        setPageScale(view, f);
        PageTransformer.InnerMarginTransformer innerMarginTransformer = this.mMarginTransformer;
        if (innerMarginTransformer != null) {
            innerMarginTransformer.transformPage(view, f);
        }
        super.transformPage(view, f);
    }
}
