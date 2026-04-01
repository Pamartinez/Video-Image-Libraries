package com.samsung.android.gallery.app.ui.list.search.keyword.stories;

import A4.N;
import A4.e0;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.search.keyword.stories.ISearchResultStoriesContainer;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchResultStoriesViewAdapter<V extends ISearchResultStoriesContainer> extends GalleryListAdapter<ListViewHolder> {
    private final MediaData mMediaData;
    private final V mView;
    private final SearchResultStoriesViewHolderFactory mViewHolderFactory = new SearchResultStoriesViewHolderFactory(getContext());

    public SearchResultStoriesViewAdapter(V v, Blackboard blackboard) {
        super(blackboard);
        this.mView = v;
        this.mMediaData = v.getMediaData();
    }

    private boolean bindImageOnScrollIdle(ListViewHolder listViewHolder, MediaItem mediaItem) {
        boolean z = false;
        if (listViewHolder.getImage() == null) {
            return false;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Bitmap memCache = instance.getMemCache(mediaItem, listViewHolder.getThumbKind());
        if (memCache != null) {
            z = true;
            if (instance.isReplacedThumbnail(memCache)) {
                mediaItem.setBroken(true);
            }
            listViewHolder.bindImage(memCache);
        }
        return z;
    }

    private void bindThumbnail(ListViewHolder listViewHolder, int i2) {
        listViewHolder.setImageUid(listViewHolder.getMediaItem().getPath());
        requestThumbnail(listViewHolder);
    }

    private void bindViewHolderInternal(ListViewHolder listViewHolder, int i2) {
        if (!bindViewHolderOnScrollIdle(listViewHolder, i2)) {
            bindViewHolderOnScrolling(listViewHolder, i2);
        }
    }

    private void bindViewHolderOnScrolling(ListViewHolder listViewHolder, int i2) {
        MediaItem mediaItemFromCache = getMediaItemFromCache(i2);
        if (mediaItemFromCache != null) {
            listViewHolder.bind(mediaItemFromCache);
            if (listViewHolder.getImage() != null) {
                bindThumbnail(listViewHolder, i2);
            }
        }
    }

    private MediaItem getMediaItemFromCache(int i2) {
        return this.mMediaData.readCache(i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$requestThumbnail$0(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        if (listViewHolder.getViewPosition() != thumbnailRequestHolder.getPosition()) {
            return true;
        }
        return false;
    }

    public boolean bindViewHolderOnScrollIdle(ListViewHolder listViewHolder, int i2) {
        MediaItem loadMediaItemSync = loadMediaItemSync(i2);
        if (loadMediaItemSync == null) {
            return false;
        }
        listViewHolder.bind(loadMediaItemSync);
        listViewHolder.setImageUid(loadMediaItemSync.getPath());
        return bindImageOnScrollIdle(listViewHolder, loadMediaItemSync);
    }

    public int getDataCount() {
        return this.mMediaData.getCount();
    }

    public int getItemCount() {
        return getDataCount();
    }

    public final MediaItem loadMediaItemSync(int i2) {
        try {
            return this.mMediaData.read(i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.mView.onStoriesClicked(listViewHolder, mediaItem);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return false;
    }

    public void requestThumbnail(ListViewHolder listViewHolder) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        ThumbnailLoader.getInstance().loadThumbnail(thumbnailRequestHolder.getMediaItem(), listViewHolder.getThumbKind(), thumbnailRequestHolder, new p(13, this), new N(listViewHolder, thumbnailRequestHolder, 3));
    }

    public void setBitmapWithBind(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThumbnailRequestHolder thumbnailRequestHolder = (ThumbnailRequestHolder) uniqueKey;
        String tag = thumbnailRequestHolder.getTag();
        String path = thumbnailRequestHolder.getPath();
        if (tag != null && tag.equals(path)) {
            ListViewHolder viewHolder = thumbnailRequestHolder.getViewHolder();
            if (viewHolder.getViewPosition() == thumbnailRequestHolder.getPosition()) {
                thumbnailRequestHolder.setResult(bitmap);
                viewHolder.itemView.post(new e0(thumbnailRequestHolder, bitmap, 1));
            }
        }
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        listViewHolder.setThumbKind(ThumbKind.MEDIUM_KIND);
        bindViewHolderInternal(listViewHolder, i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createViewHolder(viewGroup, i2);
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
