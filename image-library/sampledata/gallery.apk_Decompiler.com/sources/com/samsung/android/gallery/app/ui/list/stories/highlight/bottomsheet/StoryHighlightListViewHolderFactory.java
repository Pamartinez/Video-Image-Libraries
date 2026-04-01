package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListViewHolderFactory extends PicturesViewHolderFactory {
    public StoryHighlightListViewHolderFactory(Context context) {
        super(context);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_story_highlight_list_v2_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        return new StoryHighlightListV2ViewHolder(view, i2);
    }
}
