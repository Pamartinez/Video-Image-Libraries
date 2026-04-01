package com.samsung.android.gallery.app.ui.list.stories.highlight.theme;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoryThemeViewHolder extends ImageTitleViewHolder {
    private View mThumbnailContainer;

    public StoryThemeViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mThumbnailContainer = view.findViewById(R.id.thumbnail_container);
    }

    public ListViewHolder setThumbnailShape(int i2, float f) {
        ViewUtils.setViewShape(this.mThumbnailContainer, i2, f);
        return this;
    }
}
