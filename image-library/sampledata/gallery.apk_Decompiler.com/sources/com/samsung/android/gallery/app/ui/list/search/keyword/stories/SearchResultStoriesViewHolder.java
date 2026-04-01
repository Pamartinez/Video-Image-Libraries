package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleDurationViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultStoriesViewHolder extends ImageTitleDurationViewHolder {
    private View mDecoViewLayout;

    public SearchResultStoriesViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bindView(View view) {
        super.bindView(view);
        this.mDecoViewLayout = view.findViewById(R.id.deco_view_layout);
        setThumbnailShape(1, (float) getContext().getResources().getDimensionPixelOffset(R.dimen.search_filter_results_stories_item_radius));
    }

    public View getDecoView(int i2) {
        if (i2 == 57) {
            return this.mDecoViewLayout;
        }
        return super.getDecoView(i2);
    }
}
