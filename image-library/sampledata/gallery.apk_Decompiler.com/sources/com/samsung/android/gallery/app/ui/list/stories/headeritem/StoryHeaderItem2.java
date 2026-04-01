package com.samsung.android.gallery.app.ui.list.stories.headeritem;

import android.text.TextUtils;
import ba.C0582a;
import bc.C0584a;
import com.samsung.android.gallery.app.ui.abstraction.IMvpBaseView;
import com.samsung.android.gallery.app.ui.list.stories.util.CoverHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.gallery.support.utils.UnsupportedApiException;
import com.samsung.android.sum.core.message.Message;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHeaderItem2 implements HeaderItem {
    protected final String TAG = getClass().getSimpleName();
    protected final Blackboard mBlackboard;
    private MediaItem mCacheItem;
    protected MediaItem mHeaderItem;
    private Consumer<MediaItem> mListener;
    protected MediaData mStoriesData;
    private final MediaData.OnDataChangeListener mStoriesMediaDataListener = new MediaData.SimpleDataChangeListener() {
        public void onDataChanged() {
            ThreadUtil.runOnUiThread(new C0584a(11, StoryHeaderItem2.this));
        }
    };
    protected MediaData mStoriesParent;
    protected final IMvpBaseView mView;

    public StoryHeaderItem2(IMvpBaseView iMvpBaseView) {
        this.mView = iMvpBaseView;
        this.mBlackboard = iMvpBaseView.getBlackboard();
    }

    private void createMediaData() {
        MediaData open = MediaDataFactory.getInstance(this.mBlackboard).open(getParentLocationKey());
        this.mStoriesParent = open;
        MediaData openMediaData = openMediaData(open);
        this.mStoriesData = openMediaData;
        openMediaData.register(this.mStoriesMediaDataListener);
    }

    private boolean equalsItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemStory.getStoryId(mediaItem) != MediaItemStory.getStoryId(mediaItem2) || MediaItemStory.hasStoryHighlightVideo(mediaItem) != MediaItemStory.hasStoryHighlightVideo(mediaItem2) || !TextUtils.equals(mediaItem.getPath(), mediaItem2.getPath()) || !TextUtils.equals(mediaItem.getTitle(), mediaItem2.getTitle()) || !TextUtils.equals(MediaItemStory.getStoryTimeDuration(mediaItem), MediaItemStory.getStoryTimeDuration(mediaItem2)) || !TextUtils.equals(MediaItemStory.getStoryCoverRectRatio(mediaItem), MediaItemStory.getStoryCoverRectRatio(mediaItem2)) || mediaItem.getDateModified() != mediaItem2.getDateModified() || mediaItem.getCount() != mediaItem2.getCount() || MediaItemStory.getStoryFavorite(mediaItem) != MediaItemStory.getStoryFavorite(mediaItem2)) {
            return false;
        }
        return true;
    }

    private MediaItem findHeaderById() {
        return this.mStoriesData.readById((long) getStoryId());
    }

    private String getCategoryKey() {
        return ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "categoryKey");
    }

    private MediaItem getHeaderById() {
        int storyId = getStoryId();
        if (storyId >= 1) {
            return this.mStoriesData.readById((long) storyId);
        }
        return null;
    }

    private MediaItem getHeaderByPosition() {
        int headerPosition = getHeaderPosition();
        if (headerPosition >= 0) {
            return this.mStoriesData.read(headerPosition);
        }
        return null;
    }

    private String getParentLocationKey() {
        return "location://story/albums";
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onHeaderLoaded$0(Consumer consumer) {
        consumer.accept(this.mHeaderItem);
    }

    private void onHeaderLoaded(MediaItem mediaItem) {
        MediaItem updatedHeaderItem = getUpdatedHeaderItem(mediaItem);
        boolean equalsItem = equalsItem(this.mHeaderItem, updatedHeaderItem);
        Log.d(this.TAG, "onHeaderLoaded changed", Boolean.valueOf(!equalsItem), Integer.valueOf(MediaItemStory.getStoryType(mediaItem)), MediaItemUtil.getDebugLog(mediaItem));
        if (updatedHeaderItem != null) {
            this.mHeaderItem = updatedHeaderItem;
            if (!equalsItem || this.mCacheItem != null) {
                Optional.ofNullable(this.mListener).ifPresent(new C0582a(5, this));
                this.mCacheItem = null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onStoriesDataChangedOnUi() {
        MediaData mediaData;
        if (!this.mView.isDestroyed() && (mediaData = this.mStoriesData) != null && mediaData.getCount() > 0 && this.mBlackboard.read("location://variable/currentv1") != null) {
            onHeaderLoaded(findHeaderById());
        }
    }

    public void close() {
        MediaData mediaData = this.mStoriesData;
        if (mediaData != null) {
            mediaData.unregister(this.mStoriesMediaDataListener);
            this.mStoriesData.close();
            this.mStoriesData = null;
        }
        MediaData mediaData2 = this.mStoriesParent;
        if (mediaData2 != null) {
            mediaData2.close();
            this.mStoriesParent = null;
        }
        this.mListener = null;
    }

    public MediaItem getHeaderItem() {
        return this.mHeaderItem;
    }

    public int getHeaderPosition() {
        return UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), Message.KEY_POSITION));
    }

    public String getLocationKey() {
        String categoryKey = getCategoryKey();
        if (!PreferenceFeatures.OneUi7x.STORY_ONE_UI_70 || "location://stories/category/more".equals(categoryKey) || TextUtils.isEmpty(categoryKey)) {
            return "location://story/albums";
        }
        return categoryKey;
    }

    public MediaData getStoriesData() {
        return this.mStoriesData;
    }

    public MediaItem getStoryById(int i2) {
        MediaData mediaData = this.mStoriesData;
        if (mediaData != null) {
            return mediaData.readById((long) i2);
        }
        return null;
    }

    public final int getStoryId() {
        return UnsafeCast.toInt(ArgumentsUtil.getArgValue(this.mView.getLocationKey(), "id"));
    }

    public MediaItem getUpdatedHeaderItem(MediaItem mediaItem) {
        if (!MediaItemStory.isCollageStory(mediaItem)) {
            return mediaItem;
        }
        MediaItem clone = mediaItem.clone();
        CoverHelper.changeAttributeOriginalCoverItem(clone);
        return clone;
    }

    public void loadHeaderItem() {
        if (this.mHeaderItem == null) {
            this.mHeaderItem = getHeaderByPosition();
        }
        if (this.mHeaderItem == null) {
            this.mHeaderItem = getHeaderById();
        }
    }

    public MediaData openMediaData(MediaData mediaData) {
        return MediaDataFactory.getInstance(this.mBlackboard).open(getLocationKey());
    }

    public int positionInStories() {
        int i2;
        try {
            MediaItem mediaItem = this.mHeaderItem;
            if (mediaItem != null) {
                i2 = MediaItemStory.getStoryId(mediaItem);
            } else {
                i2 = getHeaderPosition();
            }
            MediaData mediaData = this.mStoriesData;
            if (mediaData != null) {
                return mediaData.findPosition((long) i2);
            }
            return -1;
        } catch (UnsupportedApiException unused) {
            Log.e(this.TAG, "storyData.findPosition exception");
            return -1;
        }
    }

    public StoryHeaderItem2 setCacheItem(MediaItem mediaItem) {
        this.mHeaderItem = mediaItem;
        this.mCacheItem = mediaItem;
        return this;
    }

    public HeaderItem setHeaderUpdateListener(Consumer<MediaItem> consumer) {
        this.mListener = consumer;
        return this;
    }

    public StoryHeaderItem2 open() {
        createMediaData();
        loadHeaderItem();
        return this;
    }
}
