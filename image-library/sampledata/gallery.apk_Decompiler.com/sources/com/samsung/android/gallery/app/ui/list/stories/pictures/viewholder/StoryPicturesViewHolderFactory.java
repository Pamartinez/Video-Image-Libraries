package com.samsung.android.gallery.app.ui.list.stories.pictures.viewholder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesViewHolderFactory;
import com.samsung.android.gallery.app.ui.viewholders.StoryChapterViewHolder;
import com.samsung.android.gallery.app.ui.viewholders.StoryImageViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class StoryPicturesViewHolderFactory extends PicturesViewHolderFactory {
    public StoryPicturesViewHolderFactory(Context context) {
        super(context);
    }

    private ListViewHolder createFooterViewHolder(ViewGroup viewGroup, int i2) {
        return new StoryRelatedViewHolder(this.mLayoutInflater.inflate(getFooterItemLayoutId(), viewGroup, false), i2);
    }

    private int getFooterItemLayoutId() {
        return R.layout.recycler_item_story_pictures_footer_layout;
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2, int i7) {
        if (ViewHolderValue.isFooter(i2)) {
            return createFooterViewHolder(viewGroup, i2);
        }
        return super.createListViewHolder(viewGroup, i2, i7);
    }

    public int getDataLayoutId() {
        return R.layout.recycler_item_story_pictures_previewable_image_layout;
    }

    public ListViewHolder getDataViewHolder(View view, int i2) {
        return new StoryImageViewHolder(view, i2);
    }

    public ListViewHolder getDateLocationViewHolder(View view, int i2) {
        return new StoryChapterViewHolder(view, i2);
    }

    public int getFirstTimelineLayoutId() {
        return R.layout.recycler_item_story_pictures_first_chapter_layout;
    }

    public int getTimelineLayoutId() {
        return R.layout.recycler_item_story_pictures_chapter_layout;
    }
}
