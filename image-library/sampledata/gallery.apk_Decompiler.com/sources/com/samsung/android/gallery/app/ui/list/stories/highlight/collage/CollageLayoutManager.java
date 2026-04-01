package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CollageLayoutManager extends SpannedGridLayoutManager {
    private Float mBaseSize;
    ViewGroup mParent;

    public CollageLayoutManager(ViewGroup viewGroup, int i2) {
        super(i2, true);
        this.mParent = viewGroup;
    }

    private float getBaseSize() {
        int i2;
        Float f = this.mBaseSize;
        if (f != null) {
            return f.floatValue();
        }
        boolean isLandscape = ResourceCompat.isLandscape((View) this.mParent);
        ViewGroup viewGroup = this.mParent;
        if (isLandscape) {
            i2 = viewGroup.getHeight();
        } else {
            i2 = viewGroup.getWidth();
        }
        return (float) i2;
    }

    private void updateLayout(View view, int i2) {
        ViewMarginUtils.setPadding(view, (int) (getBaseSize() * ResourceCompat.getFloatFromDimension(this.mParent.getResources(), (int) R.dimen.story_collage_item_gap_ratio)));
    }

    public void addView(View view, int i2) {
        updateLayout(view, i2);
        super.addView(view, i2);
    }

    public boolean canScrollVertically() {
        return false;
    }

    public void setBaseSize(float f) {
        this.mBaseSize = Float.valueOf(f);
    }
}
