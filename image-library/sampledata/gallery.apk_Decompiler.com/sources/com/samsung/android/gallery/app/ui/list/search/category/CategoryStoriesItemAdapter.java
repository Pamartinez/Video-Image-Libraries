package com.samsung.android.gallery.app.ui.list.search.category;

import com.samsung.android.gallery.app.controller.story.UpdateStoryFavoriteCmd;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.StoryLauncher;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryStoriesItemAdapter<V extends ISearchView> extends CategoryItemAdapterV2<V> {
    public CategoryStoriesItemAdapter(V v, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, galleryListView, categoryPropertyHelper, z);
    }

    public void handleItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        boolean z = false;
        if (i7 == 6) {
            new UpdateStoryFavoriteCmd().addParameter("dataKey", getLocationKey()).execute(((ISearchView) this.mView).getPresenter(), new MediaItem[]{mediaItem}, 0);
        } else if (this.mBlackBoard != null) {
            StoryLauncher data = new StoryLauncher(this.mView).setData(mediaItem, i2);
            if (PreferenceFeatures.OneUi8x.SUPPORT_RECAP && MediaItemStory.isRecap(mediaItem)) {
                z = true;
            }
            data.setRecap(z).setFromLocation("location://search/fileList/Category/Stories/Others").execute();
            if (PocFeatures.RECAP_SHARED_TRANSITION) {
                StorySharedTransitionHelper.addStoryAlbumTransition(((ISearchView) this.mView).getBlackboard(), listViewHolder, listViewHolder.getMediaItem());
            }
        }
    }

    public MediaData getMediaData(V v, String str) {
        MediaData mediaData = super.getMediaData(v, str);
        if (mediaData != null) {
            return mediaData.getChildMediaData("location://search/fileList/Category/Stories/Others");
        }
        return null;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (list.contains("update_favorite")) {
            listViewHolder.updateDecoration(64, new Object[0]);
        } else {
            super.onBindViewHolder(listViewHolder, i2, list);
        }
    }
}
