package com.samsung.android.gallery.app.ui.viewer2.container;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.trace.Trace;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VuContainerAdapter extends RecyclerView.Adapter<AbsViewerHolder> implements AbsViewerHolder.StateListener {
    protected final Blackboard mBlackboard;
    private final LruCache<Integer, MediaItem> mCache = new LruCache<>(7);
    private final ContainerModel mContainerModel;
    private final ContentViewerPool mContentViewerPool;
    private int mCount = -1;
    private long mFirstFileId = 0;
    private boolean mFirstView = true;
    private final String mLocationKey;
    protected MediaData mMediaData;
    private final ViewChangeListener mViewChangeListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ViewChangeListener {
        void onViewConfirm(int i2, ViewerObjectComposite viewerObjectComposite);

        void onViewInvalidate(int i2, ViewerObjectComposite viewerObjectComposite);
    }

    public VuContainerAdapter(Blackboard blackboard, MediaData mediaData, ContainerModel containerModel, ViewChangeListener viewChangeListener) {
        this.mBlackboard = blackboard;
        setMediaData(mediaData);
        this.mContainerModel = containerModel;
        this.mViewChangeListener = viewChangeListener;
        this.mLocationKey = mediaData.getLocationKey();
        this.mContentViewerPool = new ContentViewerPool(getContext(), containerModel, new ContentViewCompositeFactory(containerModel), this);
    }

    private MediaItem getItem(int i2) {
        MediaItem mediaItem = this.mCache.get(Integer.valueOf(i2));
        if (mediaItem == null && (mediaItem = this.mMediaData.read(i2)) != null) {
            this.mCache.put(Integer.valueOf(i2), mediaItem);
        }
        return mediaItem;
    }

    private void invalidate(ViewerObjectComposite viewerObjectComposite, int i2, MediaItem mediaItem) {
        this.mContentViewerPool.recycle(viewerObjectComposite);
        viewerObjectComposite.invalidate(mediaItem, i2, viewerObjectComposite.getModel().getRepresentativeItem(), viewerObjectComposite.getModel().getPosition());
        this.mViewChangeListener.onViewInvalidate(i2, viewerObjectComposite);
        this.mContentViewerPool.bind(viewerObjectComposite);
    }

    private boolean isDynamicView() {
        String str = this.mLocationKey;
        if (str == null) {
            return false;
        }
        if (str.startsWith("location://highlight/fileList") || this.mLocationKey.startsWith("location://dynamicViewList")) {
            return true;
        }
        return false;
    }

    public void clearCache() {
        this.mCache.evictAll();
        this.mCount = -1;
    }

    public void destroy() {
        this.mContentViewerPool.destroy();
        clearCache();
    }

    public ViewerObjectComposite findContentViewer(int i2) {
        return this.mContentViewerPool.findContentViewer(i2);
    }

    public Context getContext() {
        return BlackboardUtils.readActivity(this.mBlackboard);
    }

    public int getItemCount() {
        if (this.mCount < 0) {
            this.mCount = this.mMediaData.getCount();
        }
        return this.mCount;
    }

    public long getItemId(int i2) {
        long videoThumbStartTime;
        MediaItem item = getItem(i2);
        if (item == null) {
            return super.getItemId(i2);
        }
        int i7 = 0;
        if (isDynamicView()) {
            String title = item.getTitle();
            videoThumbStartTime = item.getFileId() + ((long) item.getThumbCacheKey().hashCode());
            if (title != null) {
                i7 = title.hashCode();
            }
        } else if (item.isGroupShot() && !LocationKey.isSecondDepthGroupPanelView(this.mLocationKey)) {
            return (long) (item.getAlbumID() + "/" + item.getGroupMediaId()).hashCode();
        } else if (!item.isUriItem()) {
            boolean z = PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL;
            if (z && MediaItemUtil.isHighLightClip(item)) {
                String title2 = item.getTitle();
                videoThumbStartTime = item.getVideoThumbStartTime() + item.getFileId();
                if (title2 != null) {
                    i7 = title2.hashCode();
                }
            } else if (!z || !MediaItemUtil.isSuperSlowClip(item)) {
                return item.getFileId();
            } else {
                return item.getFileId() + ((long) MediaItemUtil.getSuperSlowClipEffect(item));
            }
        } else if (LocationKey.isAllDayTimeLapse(this.mLocationKey) || LocationKey.isLongExposure(this.mLocationKey)) {
            return ((Long) item.getExtra(ExtrasID.ORIGINAL_FILE_ID)).longValue();
        } else {
            Uri uri = ContentUri.getUri(item);
            if (uri != null) {
                return (long) uri.hashCode();
            }
            Log.e("VuContainerAdapter", "item uri is null");
            return super.getItemId(i2);
        }
        return videoThumbStartTime + ((long) i7);
    }

    public int getItemViewType(int i2) {
        MediaItem item = getItem(i2);
        if (item != null && item.isSimilarShot() && item.getCount() == DbTable.UNLOADED) {
            SimilarPhotoHelper.loadSimilarCountSync(item);
        }
        return this.mContentViewerPool.getViewHolderType(item);
    }

    public void onViewConfirm(AbsViewerHolder absViewerHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
        if (findContentViewer != null) {
            MediaItem mediaItem = findContentViewer.getModel().getMediaItem();
            if (mediaItem != null) {
                this.mContainerModel.resetLatestVideoPos(mediaItem.getThumbCacheKey().hashCode());
            }
            this.mViewChangeListener.onViewConfirm(findContentViewer.getModel().getPosition(), findContentViewer);
            findContentViewer.onViewConfirm();
        }
    }

    public void prepareForceSwipeCache(int i2) {
        int itemCount = getItemCount();
        getItem(i2);
        int i7 = i2 + 2;
        int i8 = itemCount - 1;
        if (i7 <= i8) {
            getItem(i7);
        }
        int i10 = i2 + 1;
        if (i10 <= i8) {
            getItem(i10);
        }
        if (i2 > 0) {
            getItem(i2 - 1);
        }
        if (i2 - 1 > 0) {
            getItem(i2 - 2);
        }
    }

    public void setFirstView() {
        this.mFirstView = true;
    }

    public void setMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
    }

    public void onBindViewHolder(AbsViewerHolder absViewerHolder, int i2) {
        try {
            Trace.beginSection("VuContainerAdapter onBindViewHolder " + i2);
            long currentTimeMillis = System.currentTimeMillis();
            ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
            if (findContentViewer != null) {
                MediaItem item = getItem(i2);
                if (item == null) {
                    Log.e((CharSequence) "VuContainerAdapter", "onBindViewHolder failed", Integer.valueOf(i2));
                } else if (absViewerHolder.isAttachedToWindow()) {
                    invalidate(findContentViewer, i2, item);
                    Log.d("VuContainerAdapter", "onBindViewHolder : invalidate" + Logger.vt(Integer.valueOf(i2), absViewerHolder, Long.valueOf(currentTimeMillis)));
                } else {
                    if (this.mFirstView) {
                        if (this.mFirstFileId == 0) {
                            this.mFirstFileId = item.getFileId();
                        }
                        findContentViewer.getModel().setFirstView(this.mFirstFileId == item.getFileId());
                        this.mFirstView = false;
                    }
                    findContentViewer.onBind(item, i2);
                    this.mContentViewerPool.bind(findContentViewer);
                    Log.d("VuContainerAdapter", "onBindViewHolder" + Logger.vt(Integer.valueOf(i2), Long.valueOf(item.getFileId()), Long.valueOf(currentTimeMillis)));
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    public AbsViewerHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        try {
            Trace.beginSection("VuContainerAdapter onCreateViewHolder " + i2);
            return this.mContentViewerPool.createContentViewer(this.mBlackboard, this.mContainerModel, viewGroup, i2).getViewHolder();
        } finally {
            Trace.endSection();
        }
    }

    public void onViewAttachedToWindow(AbsViewerHolder absViewerHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
        if (findContentViewer != null) {
            findContentViewer.onViewAttached();
        }
    }

    public void onViewDetachedFromWindow(AbsViewerHolder absViewerHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
        if (findContentViewer != null) {
            findContentViewer.onViewDetached();
            findContentViewer.detachActionInvoker();
            findContentViewer.getModel().setFirstView(false);
        }
    }

    public void onViewRecycled(AbsViewerHolder absViewerHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
        if (findContentViewer != null) {
            this.mContentViewerPool.recycle(findContentViewer);
            findContentViewer.onViewRecycled();
        }
    }
}
