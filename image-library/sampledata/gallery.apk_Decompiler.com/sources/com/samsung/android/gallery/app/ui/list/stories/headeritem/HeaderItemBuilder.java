package com.samsung.android.gallery.app.ui.list.stories.headeritem;

import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.database.dbtype.StoryType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HeaderItemBuilder {
    private final Blackboard mBlackboard;
    Consumer<MediaItem> mHeaderUpdateListener;
    private final IMvpBaseView mView;

    public HeaderItemBuilder(IMvpBaseView iMvpBaseView) {
        this.mView = iMvpBaseView;
        this.mBlackboard = iMvpBaseView.getBlackboard();
    }

    private StoryHeaderItem2 create(int i2) {
        if (fromFavorite()) {
            return new FavoriteStoryHeaderItem(this.mView);
        }
        if (isTransitoryStory(i2)) {
            return new TransitoryStoryHeaderItem(this.mView);
        }
        return new StoryHeaderItem2(this.mView);
    }

    private boolean fromFavorite() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "fromStoryFavorite", false);
    }

    private String getCategoryKey() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "categoryKey");
    }

    private MediaItem getHeaderFromCache(int i2) {
        if (UnsafeCast.isInvalid(i2)) {
            return null;
        }
        return (MediaItem) this.mBlackboard.pop(LocationKey.getHeaderCacheKey("stories", i2));
    }

    private int getHeaderId() {
        return UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "id"));
    }

    private int getType(MediaItem mediaItem) {
        int i2 = UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "type"));
        if (i2 <= 0) {
            return MediaItemStory.getStoryType(mediaItem);
        }
        return i2;
    }

    private boolean isTransitoryStory(int i2) {
        if ((!PreferenceFeatures.OneUi5x.STORY_ONE_UI_50 || !StoryType.isTransitoryType(i2)) && !"location://stories/category/transitory".equals(getCategoryKey())) {
            return false;
        }
        return true;
    }

    public HeaderItem build() {
        MediaItem headerFromCache = getHeaderFromCache(getHeaderId());
        return create(getType(headerFromCache)).setCacheItem(headerFromCache).setHeaderUpdateListener(this.mHeaderUpdateListener);
    }

    public HeaderItemBuilder setHeaderUpdateListener(Consumer<MediaItem> consumer) {
        this.mHeaderUpdateListener = consumer;
        return this;
    }
}
