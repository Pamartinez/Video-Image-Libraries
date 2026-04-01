package com.samsung.android.gallery.app.ui.list.stories.pictures.legacy;

import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.IStoryPicturesBaseView;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryHeaderViewListener;
import com.samsung.android.gallery.app.ui.list.stories.pictures.abstraction.StoryPicturesBasePresenter;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.list.StoryPicturesMenuHandler;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryPicturesLegacyPresenter<V extends IStoryPicturesBaseView> extends StoryPicturesBasePresenter<V> implements StoryHeaderViewListener {
    public StoryPicturesLegacyPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
    }

    private boolean isStoryTrip(MediaData mediaData) {
        MediaItem mediaItem = this.mHeaderItem;
        if (mediaItem == null || !StoryTripManager.isStoryTrip(mediaData, MediaItemStory.getStoryType(mediaItem))) {
            return false;
        }
        return true;
    }

    private void prepareStoryTrip(MediaData mediaData) {
        new StoryTripManager().makeStoryTripDay(mediaData);
    }

    public MenuHandler createMenuHandler() {
        return new StoryPicturesMenuHandler();
    }

    public void notifyDataChanged(MediaData mediaData) {
        if (mediaData == null || mediaData.getCount() == 0) {
            getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
        } else if (isStoryTrip(mediaData)) {
            prepareStoryTrip(mediaData);
        }
    }
}
