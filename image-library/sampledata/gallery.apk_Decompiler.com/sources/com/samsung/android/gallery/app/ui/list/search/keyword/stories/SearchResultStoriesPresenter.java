package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StorySharedTransitionHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.story.StoryUriBuilder;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import n0.C0235b;
import o6.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultStoriesPresenter implements MediaData.OnDataChangeListener {
    private final EventContext mHandler;
    private MediaData mMediaData;
    private final ISearchResultStoriesContainer mView;

    public SearchResultStoriesPresenter(EventContext eventContext, ISearchResultStoriesContainer iSearchResultStoriesContainer) {
        this.mHandler = eventContext;
        this.mView = iSearchResultStoriesContainer;
    }

    private void closeMediaData() {
        MediaData mediaData = this.mMediaData;
        if (mediaData != null) {
            mediaData.unregister(this);
            this.mMediaData.close();
            this.mMediaData = null;
        }
    }

    private String getSearchKeywordStory(String str) {
        return new UriBuilder("location://search/fileList/KeywordStories").appendArg("name", str).build();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onStoriesClicked$0(MediaItem mediaItem, ListViewHolder listViewHolder) {
        if (MediaItemStory.isRecap(mediaItem)) {
            moveToRecap(mediaItem);
        } else {
            moveToStoryHighlight(listViewHolder, mediaItem);
        }
    }

    private void moveToRecap(MediaItem mediaItem) {
        getBlackboard().post("command://MoveURL", new StoryUriBuilder(getBlackboard(), mediaItem, true, "location://recap").withFromLocation("location://story/albums").appendArg("media_item", BlackboardUtils.publishMediaItem(getBlackboard(), mediaItem)).build());
    }

    private void moveToStoryHighlight(ListViewHolder listViewHolder, MediaItem mediaItem) {
        StorySharedTransitionHelper.addStoryAlbumTransition(getBlackboard(), listViewHolder, mediaItem);
        getBlackboard().post("command://MoveURL", new StoryUriBuilder(getBlackboard(), (ThumbnailInterface) mediaItem).withBlurBitmap().build());
    }

    /* access modifiers changed from: private */
    public void onDataChangedOnUi() {
        this.mView.onDataChangedOnUi(this.mMediaData);
    }

    private void openMediaData(String str) {
        if (this.mMediaData == null) {
            MediaData open = MediaDataFactory.getInstance(this.mHandler.getBlackboard()).open(getSearchKeywordStory(str));
            this.mMediaData = open;
            open.register(this);
        }
    }

    public void destroy() {
        closeMediaData();
    }

    public Blackboard getBlackboard() {
        return this.mHandler.getBlackboard();
    }

    public MediaData getMediaData() {
        return this.mMediaData;
    }

    public void loadStories(String str) {
        closeMediaData();
        openMediaData(str);
    }

    public void onDataChanged() {
        if (ThreadUtil.isMainThread()) {
            onDataChangedOnUi();
        } else {
            ThreadUtil.postOnUiThread(new t(29, this));
        }
    }

    public void onDataRangeChanged(int i2, int i7) {
    }

    public void onStoriesClicked(ListViewHolder listViewHolder, MediaItem mediaItem) {
        SimpleThreadPool.getInstance().execute(new C0235b(this, mediaItem, listViewHolder, 24));
    }

    public void onDataRangeChanged(int i2, int i7, Object obj) {
    }

    public void onDataRangeInserted(int i2, int i7) {
    }
}
