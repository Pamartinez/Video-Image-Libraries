package com.samsung.android.gallery.widget.livemotion;

import A4.H;
import A4.Q;
import A6.a;
import android.graphics.Bitmap;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.livemotion.abstraction.ILiveMotionAdapter;
import com.samsung.android.gallery.widget.utils.ResourceUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LiveMotionAdapter<VH extends ListViewHolder> extends RecyclerView.Adapter<VH> implements ILiveMotionAdapter {
    protected final String TAG = getClass().getSimpleName();
    protected MediaData mData;
    private int mHeaderDataPosition = 0;
    private boolean mIsDataPrepared = false;

    private Bitmap getBrokenImage(MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getReplacedThumbnail(AppResources.getAppContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$bindThumbnail$1(ListViewHolder listViewHolder, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        onThumbnailLoadCompleted(listViewHolder, mediaItem, bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onThumbnailLoadCompleted$2(Bitmap bitmap, MediaItem mediaItem, ListViewHolder listViewHolder) {
        if (bitmap != null) {
            if (ThumbnailLoader.getInstance().isReplacedThumbnail(bitmap)) {
                mediaItem.setBroken(true);
            }
            listViewHolder.bindImage(bitmap);
            return;
        }
        mediaItem.setBroken(true);
        listViewHolder.bindImage(getBrokenImage(mediaItem));
    }

    private void preloadBitmap(MediaItem mediaItem) {
        if (mediaItem != null) {
            ThumbKind thumbKind = getThumbKind(mediaItem);
            if (ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind) == null) {
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new H(27, (Object) this, (Object) mediaItem));
            }
        }
    }

    public void bindThumbnail(MediaItem mediaItem, VH vh) {
        ThumbKind thumbKind = getThumbKind(mediaItem);
        vh.setThumbKind(thumbKind);
        Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
        if (memCache != null) {
            onThumbnailLoadCompleted(vh, mediaItem, memCache);
        } else {
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new Q((Object) this, (Object) vh, (Object) mediaItem, 6));
        }
    }

    public MediaItem getBindMediaItem(int i2) {
        return getMediaItem(i2);
    }

    public abstract int getDataPosition(int i2);

    public int getFocusDataPosition() {
        return this.mHeaderDataPosition;
    }

    public abstract int getItemCount();

    public MediaItem getMediaItem(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || i2 == -1) {
            return null;
        }
        return mediaData.read(i2);
    }

    public int getNextDataPosition(int i2) {
        MediaData mediaData = this.mData;
        if (mediaData == null || mediaData.getCount() == 0) {
            return -1;
        }
        return (i2 + 1) % this.mData.getCount();
    }

    public abstract ThumbKind getThumbKind(MediaItem mediaItem);

    public boolean isDataPrepared() {
        if (!this.mIsDataPrepared || getItemCount() <= 0) {
            return false;
        }
        return true;
    }

    public void onThumbnailLoadCompleted(VH vh, MediaItem mediaItem, Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        ThreadUtil.runOnUiThread(new a((Object) this, (Object) bitmap2, (Object) mediaItem, (Object) vh, 9));
    }

    public boolean prepareNext(int i2) {
        preloadBitmap(getMediaItem(getNextDataPosition(i2)));
        return true;
    }

    public void release() {
        this.mData = null;
        this.mHeaderDataPosition = 0;
        this.mIsDataPrepared = false;
    }

    public void setData(MediaData mediaData) {
        this.mData = mediaData;
        this.mIsDataPrepared = true;
        notifyDataSetChanged();
    }

    public void setHeaderDataPosition(int i2) {
        this.mHeaderDataPosition = i2;
    }

    public void onBindViewHolder(VH vh, int i2) {
        MediaItem bindMediaItem;
        int dataPosition = getDataPosition(i2);
        if (dataPosition >= 0 && (bindMediaItem = getBindMediaItem(dataPosition)) != null) {
            onPreBindViewHolder(vh, bindMediaItem, i2);
            vh.bind(bindMediaItem);
            bindThumbnail(bindMediaItem, vh);
        }
    }

    public void onPreBindViewHolder(VH vh, MediaItem mediaItem, int i2) {
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$preloadBitmap$0(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
    }
}
