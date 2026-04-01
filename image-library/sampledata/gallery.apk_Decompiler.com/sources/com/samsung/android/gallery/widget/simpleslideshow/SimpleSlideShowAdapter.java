package com.samsung.android.gallery.widget.simpleslideshow;

import B8.d;
import B8.e;
import O3.b;
import Yb.a;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.creature.people.PeopleData;
import com.samsung.android.gallery.module.creature.people.PeopleDataManager;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SimpleSlideShowAdapter<VH extends ListViewHolder> extends RecyclerView.Adapter<VH> implements ISimpleSlideShowAdapter {
    private MediaData mData;
    private int mEntryDataPosition = 0;
    private boolean mIsDataPrepared = false;
    private final ConcurrentHashMap<Long, ArrayList<PeopleData>> mPeopleData = new ConcurrentHashMap<>();

    private void bindThumbnail(MediaItem mediaItem, VH vh) {
        ThumbKind thumbKind = getThumbKind(mediaItem);
        vh.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(vh, memCache);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new b(17, this, vh));
        }
    }

    private int getNextDataPosition(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || mediaData.getCount() == 0) {
            return -1;
        }
        return (i2 + 1) % this.mData.getCount();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$3(ListViewHolder listViewHolder, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(listViewHolder, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preparePeopleData$2(MediaItem mediaItem, ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (arrayList != null && !arrayList.isEmpty()) {
            arrayList.stream().limit(5).forEach(new d(arrayList2, 24));
        }
        this.mPeopleData.put(Long.valueOf(mediaItem.getFileId()), arrayList2);
    }

    private void onThumbnailLoadCompleted(VH vh, Bitmap bitmap) {
        ThreadUtil.runOnUiThread(new a(vh, bitmap, 0));
    }

    private void preloadBitmap(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbKind thumbKind = getThumbKind(mediaItem);
            if (ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind) == null) {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 3));
            }
        }
    }

    private void preparePeopleData(MediaItem mediaItem) {
        if (mediaItem != null && mediaItem.isImage() && !this.mPeopleData.containsKey(Long.valueOf(mediaItem.getFileId()))) {
            PeopleDataManager.load(mediaItem.getFileId(), new U5.b(10, this, mediaItem));
        }
    }

    public abstract VH createViewHolder(View view, int i2);

    public int getDataPosition(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || mediaData.getCount() == 0 || i2 < 0) {
            return -1;
        }
        return (i2 + this.mEntryDataPosition) % this.mData.getCount();
    }

    public int getEntryDataPosition() {
        return this.mEntryDataPosition;
    }

    public int getItemCount() {
        MediaData mediaData = this.mData;
        if (mediaData == null || mediaData.getCount() <= 0) {
            return 0;
        }
        return 20000;
    }

    public int getLayoutId() {
        return R$layout.recycler_item_simple_slideshow_item_layout;
    }

    public MediaItem getMediaItem(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || i2 == -1) {
            return null;
        }
        return mediaData.read(i2);
    }

    public List<PeopleData> getPeopleData(int i2) {
        MediaItem mediaItem;
        if (supportFaceCircle() && (mediaItem = getMediaItem(i2)) != null) {
            return this.mPeopleData.get(Long.valueOf(mediaItem.getFileId()));
        }
        return null;
    }

    public abstract ThumbKind getThumbKind(MediaItem mediaItem);

    public boolean isDataPrepared() {
        if (!this.mIsDataPrepared || getItemCount() <= 0) {
            return false;
        }
        return true;
    }

    public abstract void onPreBindViewHolder(VH vh, MediaItem mediaItem, int i2);

    public boolean prepareNext(int i2) {
        MediaItem mediaItem = getMediaItem(getNextDataPosition(i2));
        preloadBitmap(mediaItem);
        if (!supportFaceCircle()) {
            return true;
        }
        preparePeopleData(mediaItem);
        return true;
    }

    public void release() {
        this.mData = null;
        this.mEntryDataPosition = 0;
        this.mIsDataPrepared = false;
    }

    public void setData(MediaData mediaData) {
        this.mData = mediaData;
        this.mIsDataPrepared = true;
        notifyDataSetChanged();
    }

    public void setEntryDataPosition(int i2) {
        this.mEntryDataPosition = i2;
    }

    public abstract boolean supportFaceCircle();

    public void onBindViewHolder(VH vh, int i2) {
        MediaItem mediaItem;
        int dataPosition = getDataPosition(i2);
        if (dataPosition >= 0 && (mediaItem = getMediaItem(dataPosition)) != null) {
            onPreBindViewHolder(vh, mediaItem, i2);
            vh.bind(mediaItem);
            bindThumbnail(mediaItem, vh);
        }
    }

    public VH onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return createViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false), i2);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$preloadBitmap$0(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
