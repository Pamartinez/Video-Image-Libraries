package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.PreviewViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListV2ViewHolder extends PreviewViewHolder {
    private View mContentRoot;

    public StoryHighlightListV2ViewHolder(View view, int i2) {
        super(view, i2);
    }

    public boolean applyImageColorFilter() {
        return false;
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mContentRoot = view.findViewById(R.id.content_root);
    }

    public View getDecoView(int i2) {
        if (i2 == 2) {
            return this.mContentRoot;
        }
        return super.getDecoView(i2);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        View scalableView = getScalableView();
        if (scalableView != null) {
            ViewUtils.setViewShape(scalableView, i2, f);
        }
        return super.setThumbnailShape(i2, f);
    }
}
