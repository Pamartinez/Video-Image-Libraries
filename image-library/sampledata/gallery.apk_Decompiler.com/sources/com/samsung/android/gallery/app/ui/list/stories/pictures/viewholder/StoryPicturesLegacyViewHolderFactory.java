package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import android.content.Context;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.StoryDateLocationViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyViewHolderFactory extends PicturesViewHolderFactory {
    public StoryPicturesLegacyViewHolderFactory(Context context) {
        super(context);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        return new StoryDateLocationViewHolder(view, i2);
    }

    public int getFirstTimelineLayoutId() {
        return R.layout.recycler_item_story_pictures_timeline_first_layout;
    }

    public int getTimelineLayoutId() {
        return R.layout.recycler_item_story_pictures_timeline_body_layout;
    }
}
