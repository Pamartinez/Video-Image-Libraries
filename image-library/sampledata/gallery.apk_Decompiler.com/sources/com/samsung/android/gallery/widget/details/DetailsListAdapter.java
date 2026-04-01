package com.samsung.android.gallery.widget.details;

import B8.e;
import a8.d;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import java.util.ArrayList;
import java.util.Objects;
import o6.t;
import sb.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DetailsListAdapter<VH extends ListViewHolder> extends RecyclerView.Adapter<VH> {
    protected final MediaItem DUMMY_ITEM = new MediaItem();
    protected final Object LOCK = new Object();
    protected final String TAG = getClass().getSimpleName();
    private final ArrayList<MediaItem> mData = new ArrayList<>();
    protected ListViewHolder.OnItemClickListener mItemClickListener;
    protected ListViewHolder.OnItemLongClickListener mItemLongClickListener;
    private LayoutInflater mLayoutInflater;
    private final RecyclerView mListView;

    public DetailsListAdapter(RecyclerView recyclerView) {
        this.mListView = recyclerView;
    }

    private boolean isDataChanged(ArrayList<MediaItem> arrayList) {
        if (arrayList.size() != this.mData.size()) {
            return true;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.mData.get(i2).equals(arrayList.get(i2))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$2(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(listViewHolder, uniqueKey, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyItemAdded$0(int i2) {
        notifyItemInserted(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyItemDeleted$1(int i2) {
        notifyItemRemoved(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$3(ListViewHolder listViewHolder, UniqueKey uniqueKey, Bitmap bitmap) {
        MediaItem mediaItem = listViewHolder.getMediaItem();
        if (mediaItem != null && mediaItem.hashCode() == uniqueKey.getKey()) {
            onBindImage(listViewHolder, bitmap);
        }
    }

    private void notifyItemAdded(int i2) {
        try {
            notifyItemInserted(i2);
        } catch (IllegalStateException e) {
            String str = this.TAG;
            Log.e(str, "notifyItemInserted failed e=" + e.getMessage());
            postOnView(new a(this, i2, 1));
        }
    }

    private void notifyItemDeleted(int i2) {
        try {
            notifyItemRemoved(i2);
        } catch (IllegalStateException e) {
            String str = this.TAG;
            Log.e(str, "notifyItemRemoved failed e=" + e.getMessage());
            postOnView(new a(this, i2, 0));
        }
    }

    private void onThumbnailLoadCompleted(VH vh, UniqueKey uniqueKey, Bitmap bitmap) {
        ViewUtils.post(this.mListView, new d((Object) this, (Object) vh, (Object) uniqueKey, (Object) bitmap, 21));
    }

    private void postOnView(Runnable runnable) {
        try {
            RecyclerView recyclerView = this.mListView;
            if (recyclerView != null && !recyclerView.isComputingLayout()) {
                this.mListView.post(runnable);
            }
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("post on view failed e="), this.TAG);
        }
    }

    public final void append(MediaItem mediaItem) {
        synchronized (this.LOCK) {
            this.mData.add(mediaItem);
        }
        notifyItemAdded(this.mData.size());
    }

    public final void bindThumbnail(VH vh, MediaItem mediaItem) {
        ThumbKind thumbKind = getThumbKind();
        vh.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onBindImage(vh, memCache);
            return;
        }
        ThumbnailLoader instance = ThumbnailLoader.getInstance();
        Objects.requireNonNull(mediaItem);
        instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new l6.a(7, this, vh));
    }

    public final void delete(int i2) {
        synchronized (this.LOCK) {
            try {
                if (this.mData.get(i2) == null) {
                    String str = this.TAG;
                    Log.e(str, "delete failed null item at " + i2);
                    return;
                }
                this.mData.remove(i2);
                notifyItemDeleted(i2);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public final ArrayList<MediaItem> getData() {
        return this.mData;
    }

    public int getItemCount() {
        return this.mData.size();
    }

    public final LayoutInflater getLayoutInflater(Context context) {
        if (this.mLayoutInflater == null) {
            this.mLayoutInflater = LayoutInflater.from(context);
        }
        return this.mLayoutInflater;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        return r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.samsung.android.gallery.module.data.MediaItem getMediaItem(int r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.LOCK
            monitor-enter(r0)
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r1 = r2.mData     // Catch:{ all -> 0x0021 }
            int r1 = r1.size()     // Catch:{ all -> 0x0021 }
            if (r1 == 0) goto L_0x0025
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r1 = r2.mData     // Catch:{ all -> 0x0021 }
            int r1 = r1.size()     // Catch:{ all -> 0x0021 }
            if (r3 < r1) goto L_0x0014
            goto L_0x0025
        L_0x0014:
            java.util.ArrayList<com.samsung.android.gallery.module.data.MediaItem> r1 = r2.mData     // Catch:{ all -> 0x0021 }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ all -> 0x0021 }
            com.samsung.android.gallery.module.data.MediaItem r3 = (com.samsung.android.gallery.module.data.MediaItem) r3     // Catch:{ all -> 0x0021 }
            if (r3 != 0) goto L_0x0023
            com.samsung.android.gallery.module.data.MediaItem r3 = r2.DUMMY_ITEM     // Catch:{ all -> 0x0021 }
            goto L_0x0023
        L_0x0021:
            r2 = move-exception
            goto L_0x0029
        L_0x0023:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r3
        L_0x0025:
            com.samsung.android.gallery.module.data.MediaItem r2 = r2.DUMMY_ITEM     // Catch:{ all -> 0x0021 }
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            return r2
        L_0x0029:
            monitor-exit(r0)     // Catch:{ all -> 0x0021 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.details.DetailsListAdapter.getMediaItem(int):com.samsung.android.gallery.module.data.MediaItem");
    }

    public ThumbKind getThumbKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public boolean isDataEmpty() {
        return this.mData.isEmpty();
    }

    public final void notifyDataChanged() {
        try {
            notifyDataSetChanged();
        } catch (IllegalStateException e) {
            String str = this.TAG;
            Log.e(str, "notifyDataChanged failed e=" + e.getMessage());
            postOnView(new t(26, this));
        }
    }

    public void onBindImage(VH vh, Bitmap bitmap) {
        vh.bindImage(bitmap);
    }

    public void onBindViewHolder(VH vh, int i2) {
        vh.setOnItemClickListener(this.mItemClickListener);
        vh.setOnItemLongClickListener(this.mItemLongClickListener);
    }

    public final void setData(ArrayList<MediaItem> arrayList) {
        synchronized (this.LOCK) {
            try {
                if (isDataChanged(arrayList)) {
                    Log.d(this.TAG, "setData", Integer.valueOf(this.mData.size()), Integer.valueOf(arrayList.size()));
                    this.mData.clear();
                    this.mData.addAll(arrayList);
                    notifyDataChanged();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void setItemClickListener(ListViewHolder.OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    public final void setItemLongClickListener(ListViewHolder.OnItemLongClickListener onItemLongClickListener) {
        this.mItemLongClickListener = onItemLongClickListener;
    }

    public void onViewRecycled(VH vh) {
        vh.recycle();
    }
}
