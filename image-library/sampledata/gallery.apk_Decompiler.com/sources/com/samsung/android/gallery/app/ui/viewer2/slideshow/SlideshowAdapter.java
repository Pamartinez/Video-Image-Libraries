package com.samsung.android.gallery.app.ui.viewer2.slideshow;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.viewer2.container.ContentViewerPool;
import com.samsung.android.gallery.app.ui.viewer2.container.VuContainerAdapter;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlideshowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AbsViewerHolder.StateListener {
    protected final String TAG = getClass().getSimpleName();
    protected final Blackboard mBlackboard;
    protected final ContainerModel mContainerModel;
    private final ContentViewerPool mContentViewerPool;
    private int mInfiniteCount = -1;
    protected MediaData mMediaData;
    private final VuContainerAdapter.ViewChangeListener mViewChangeListener;

    public SlideshowAdapter(Blackboard blackboard, MediaData mediaData, ContainerModel containerModel, VuContainerAdapter.ViewChangeListener viewChangeListener) {
        this.mBlackboard = blackboard;
        setMediaData(mediaData);
        this.mContainerModel = containerModel;
        this.mViewChangeListener = viewChangeListener;
        this.mContentViewerPool = new SlideshowContentViewerPool(getContext(), containerModel, new SlideshowViewCompositeFactory(containerModel), this);
    }

    public void destroy() {
        this.mContentViewerPool.destroy();
    }

    public Context getContext() {
        return BlackboardUtils.readActivity(this.mBlackboard);
    }

    public int getDataPosition(int i2) {
        int count = this.mMediaData.getCount();
        if (count == 0) {
            return 0;
        }
        return i2 % count;
    }

    public int getItemCount() {
        if (this.mInfiniteCount == -1) {
            int count = this.mMediaData.getCount();
            if (count >= 100000) {
                this.mInfiniteCount = count;
            } else if (count > 0) {
                this.mInfiniteCount = (100000 / count) * count;
            }
        }
        return this.mInfiniteCount;
    }

    public long getItemId(int i2) {
        int dataPosition = getDataPosition(i2);
        MediaItem read = this.mMediaData.read(dataPosition);
        if (read != null) {
            return read.getFileId();
        }
        return super.getItemId(dataPosition);
    }

    public int getItemViewType(int i2) {
        MediaItem read = this.mMediaData.read(getDataPosition(i2));
        if (read == null || !read.isVideo()) {
            return 1;
        }
        return 20;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        long j2;
        int dataPosition = getDataPosition(i2);
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer(viewHolder);
        if (findContentViewer != null) {
            MediaItem read = this.mMediaData.read(dataPosition);
            String str = this.TAG;
            StringBuilder o2 = C0086a.o(dataPosition, "onBindViewHolder ", GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (read != null) {
                j2 = read.getFileId();
            } else {
                j2 = -1;
            }
            o2.append(j2);
            o2.append(", vh=");
            o2.append(viewHolder);
            Log.d(str, o2.toString());
            if (read != null) {
                findContentViewer.onBind(read, dataPosition);
                return;
            }
            return;
        }
        String str2 = this.TAG;
        Log.e(str2, "onBindViewHolder failed " + dataPosition + ", vh=" + viewHolder);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mContentViewerPool.createContentViewer(this.mBlackboard, this.mContainerModel, viewGroup, i2).getViewHolder();
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer(viewHolder);
        if (findContentViewer != null) {
            findContentViewer.onViewAttached();
        }
    }

    public void onViewConfirm(AbsViewerHolder absViewerHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer((RecyclerView.ViewHolder) absViewerHolder);
        if (findContentViewer != null) {
            this.mViewChangeListener.onViewConfirm(this.mContainerModel.getPosition(), findContentViewer);
            findContentViewer.onViewConfirm();
        }
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer(viewHolder);
        if (findContentViewer != null) {
            findContentViewer.onViewDetached();
            findContentViewer.detachActionInvoker();
        }
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        ViewerObjectComposite findContentViewer = this.mContentViewerPool.findContentViewer(viewHolder);
        if (findContentViewer != null) {
            findContentViewer.onViewRecycled();
        }
    }

    public void setMediaData(MediaData mediaData) {
        this.mMediaData = mediaData;
    }
}
