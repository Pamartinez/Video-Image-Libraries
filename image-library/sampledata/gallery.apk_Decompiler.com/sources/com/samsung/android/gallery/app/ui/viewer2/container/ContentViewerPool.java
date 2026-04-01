package com.samsung.android.gallery.app.ui.viewer2.container;

import A9.c;
import android.content.Context;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.AbsViewerHolder;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObjectComposite;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ContentViewerPool {
    private final ContentViewCompositeFactory mContentViewCompositeFactory;
    private final ContentViewHolderFactory mContentViewHolderFactory;
    private final Context mContext;
    private final SparseArray<HashMap<RecyclerView.ViewHolder, ViewerObjectComposite>> mMapList = new SparseArray<>();
    private final SparseArray<ViewerObjectComposite> mPositionList = new SparseArray<>();
    private final HashMap<RecyclerView.ViewHolder, Integer> mTypeMap = new HashMap<>();

    public ContentViewerPool(Context context, ContainerModel containerModel, ContentViewCompositeFactory contentViewCompositeFactory, AbsViewerHolder.StateListener stateListener) {
        this.mContext = context;
        this.mContentViewHolderFactory = new ContentViewHolderFactory(context, containerModel, stateListener);
        this.mContentViewCompositeFactory = contentViewCompositeFactory;
    }

    private HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> getContentViewMap(int i2) {
        HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> hashMap = this.mMapList.get(i2);
        if (hashMap != null) {
            return hashMap;
        }
        HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> hashMap2 = new HashMap<>();
        this.mMapList.put(i2, hashMap2);
        return hashMap2;
    }

    private HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> getHashMap(RecyclerView.ViewHolder viewHolder) {
        Integer num = this.mTypeMap.get(viewHolder);
        if (num != null) {
            return this.mMapList.get(num.intValue());
        }
        return null;
    }

    public void bind(ViewerObjectComposite viewerObjectComposite) {
        this.mPositionList.put(viewerObjectComposite.getModel().getPosition(), viewerObjectComposite);
    }

    public ViewerObjectComposite createContentViewer(Blackboard blackboard, ContainerModel containerModel, ViewGroup viewGroup, int i2) {
        HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> contentViewMap = getContentViewMap(i2);
        ViewerObjectComposite create = this.mContentViewCompositeFactory.create(createViewHolder(viewGroup, i2), new ContentModel(this.mContext, blackboard, containerModel), i2);
        contentViewMap.put(create.getViewHolder(), create);
        this.mTypeMap.put(create.getViewHolder(), Integer.valueOf(i2));
        return create;
    }

    public AbsViewerHolder<?> createViewHolder(ViewGroup viewGroup, int i2) {
        return this.mContentViewHolderFactory.createContentViewHolder(viewGroup, i2);
    }

    public void destroy() {
        for (int i2 = 0; i2 < this.mMapList.size(); i2++) {
            HashMap hashMap = this.mMapList.get(this.mMapList.keyAt(i2));
            if (hashMap != null) {
                hashMap.forEach(new c(7));
                hashMap.forEach(new c(8));
            }
        }
        this.mMapList.clear();
        this.mTypeMap.clear();
        this.mPositionList.clear();
    }

    public ViewerObjectComposite findContentViewer(RecyclerView.ViewHolder viewHolder) {
        HashMap<RecyclerView.ViewHolder, ViewerObjectComposite> hashMap = getHashMap(viewHolder);
        if (hashMap != null) {
            return hashMap.get(viewHolder);
        }
        return null;
    }

    public int getViewHolderType(MediaItem mediaItem) {
        return this.mContentViewHolderFactory.getType(mediaItem);
    }

    public void recycle(ViewerObjectComposite viewerObjectComposite) {
        if (this.mPositionList.get(viewerObjectComposite.getModel().getPosition()) == viewerObjectComposite) {
            this.mPositionList.remove(viewerObjectComposite.getModel().getPosition());
        }
    }

    public ViewerObjectComposite findContentViewer(int i2) {
        return this.mPositionList.get(i2);
    }
}
