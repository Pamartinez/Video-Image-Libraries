package com.samsung.android.gallery.app.ui.viewer2.selection;

import B8.e;
import O3.l;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.cache.AbsCacheMgr$EvictListener;
import com.samsung.android.gallery.module.cache.BitmapCacheMgr;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailRecycler;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.threadpool.Sequencer;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SelectionViewAdapter extends RecyclerView.Adapter<SelectionViewHolder> {
    private final HashMap<Integer, MediaItem> mClipboard = new HashMap<>();
    private final MediaData.OnDataChangeListener mDataObserver = new MediaData.SimpleDataChangeListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$0() {
            SelectionViewAdapter.this.notifyDataSetChanged();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onDataChanged$1() {
            SelectionViewAdapter.this.notifyDataSetChanged();
        }

        public void onDataChanged() {
            int i2;
            if (SharedTransition.isEnterTransitionRunning(SelectionViewAdapter.this.mView.getBlackboard())) {
                i2 = StatusCodes.INPUT_MISSING;
            } else {
                i2 = 0;
            }
            Log.i("SelectionViewAdapter", "onDataChanged", Integer.valueOf(SelectionViewAdapter.this.mMediaData.getCount()), Integer.valueOf(i2));
            if (i2 > 0) {
                ThreadUtil.postOnUiThreadDelayed(new i(this, 0), (long) i2);
            } else {
                ThreadUtil.runOnUiThread(new i(this, 1));
            }
        }
    };
    private final AbsCacheMgr$EvictListener<String, Bitmap> mEvictListener = new e(this);
    private final BitmapCacheMgr<String> mLruCache = new BitmapCacheMgr<>(6, (AbsCacheMgr$EvictListener) null);
    /* access modifiers changed from: private */
    public final MediaData mMediaData;
    private final ListViewHolder.OnItemClickListener mOnItemClickListener = new e(this);
    private final ThumbnailRecycler mRecycler = new ThumbnailRecycler();
    private BiConsumer<Integer, Boolean> mSelectionCallback;
    private final Sequencer<Runnable> mSequencer;
    private int mSharedTransitionPosition = -1;
    /* access modifiers changed from: private */
    public final ISelectionView mView;

    public SelectionViewAdapter(ISelectionView iSelectionView, MediaData mediaData, String str) {
        this.mView = iSelectionView;
        this.mMediaData = mediaData;
        Sequencer<Runnable> sequencer = new Sequencer<>(ThreadUtil.createBackgroundThreadLooper("SelectionViewAdapter"), 3);
        this.mSequencer = sequencer;
        sequencer.setProcessor(new l(0));
        sequencer.enableParallel(3);
    }

    /* access modifiers changed from: private */
    /* renamed from: decodeOriginal */
    public void lambda$loadOnIdle$2(SelectionViewHolder selectionViewHolder, int i2, Runnable runnable, MediaItem mediaItem, long j2) {
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).withHeifCodec(false).adjustInSampleSize(720));
        if (decodedBitmap != null) {
            this.mLruCache.addCache(mediaItem.getThumbCacheKey(), decodedBitmap);
            ThreadUtil.postOnUiThread(new h(this, selectionViewHolder, mediaItem, i2, decodedBitmap, j2, runnable));
        } else if (runnable != null) {
            runnable.run();
        }
    }

    private MediaItem getItem(int i2) {
        return this.mMediaData.read(i2);
    }

    /* access modifiers changed from: private */
    /* renamed from: isRecycled */
    public boolean lambda$loadOrDecoding$6(SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2) {
        if (selectionViewHolder.isRecycled(i2) || !MediaItemUtil.equals(mediaItem, selectionViewHolder.getMediaItem())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$decodeOriginal$3(SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2, Bitmap bitmap, long j2, Runnable runnable) {
        if (!lambda$loadOrDecoding$6(selectionViewHolder, mediaItem, i2)) {
            Log.d("SelectionViewAdapter", "loadOnIdle(D) " + selectionViewHolder + ArcCommonLog.TAG_COMMA + mediaItem.getSimpleHashCode() + ArcCommonLog.TAG_COMMA + Logger.toSimpleString(bitmap) + " +" + (System.currentTimeMillis() - j2));
            selectionViewHolder.setThumbKind(ThumbKind.XLARGE_NC_KIND);
            selectionViewHolder.bindImage(bitmap);
        }
        Optional.ofNullable(runnable).ifPresent(new l(0));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadOrDecoding$5(SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Bitmap bitmap2 = bitmap;
        int i7 = i2;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new f(this, selectionViewHolder, mediaItem2, i7, bitmap2));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str, Bitmap bitmap) {
        this.mRecycler.tryRecycle(bitmap);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$1(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (i7 == 1 || i7 == 0) {
            boolean z = toggleSelected(i2, mediaItem);
            BiConsumer<Integer, Boolean> biConsumer = this.mSelectionCallback;
            if (biConsumer != null) {
                biConsumer.accept(Integer.valueOf(i2), Boolean.valueOf(z));
            }
            if (i7 == 0) {
                listViewHolder.setChecked(z);
                return;
            }
            return;
        }
        Log.d("SelectionViewAdapter", "onItemClick {" + i2 + ',' + i7 + '}');
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$onPositionEstimated$7(MediaItem mediaItem, int i2, long j2, ThreadPool.JobContext jobContext) {
        Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.of(mediaItem).adjustInSampleSize(ThumbKind.XLARGE_NC_KIND.size()));
        if (decodedBitmap != null) {
            Log.v("SelectionViewAdapter", "onPositionEstimated {E," + i2 + ',' + Logger.toSimpleString(decodedBitmap) + "} +" + (System.currentTimeMillis() - j2));
            this.mLruCache.addCache(mediaItem.getThumbCacheKey(), decodedBitmap);
        }
        return Boolean.TRUE;
    }

    private void loadOrDecoding(SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2) {
        if (!selectionViewHolder.hasBitmap() || selectionViewHolder.getThumbKind().size() <= ThumbKind.MEDIUM_KIND.size()) {
            Bitmap bitmap = (Bitmap) this.mLruCache.getCache(mediaItem.getThumbCacheKey());
            if (bitmap != null) {
                Log.d("SelectionViewAdapter", "loadOrDecoding(L) " + selectionViewHolder + " " + Logger.toSimpleString(bitmap));
                selectionViewHolder.setThumbKind(ThumbKind.XLARGE_NC_KIND);
                selectionViewHolder.bindImage(bitmap);
                return;
            }
            ThumbKind thumbKind = selectionViewHolder.getThumbKind();
            Bitmap memCache = ThumbnailLoader.getInstance().getMemCache(mediaItem, thumbKind);
            if (memCache != null) {
                Log.d("SelectionViewAdapter", "loadOrDecoding(C) " + selectionViewHolder + " " + Logger.toSimpleString(memCache));
                selectionViewHolder.bindImage(memCache);
                return;
            }
            ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new d(this, selectionViewHolder, mediaItem, i2), new d(this, selectionViewHolder, mediaItem, i2));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onBitmapLoaded */
    public void lambda$loadOrDecoding$4(SelectionViewHolder selectionViewHolder, MediaItem mediaItem, int i2, Bitmap bitmap) {
        if (!lambda$loadOrDecoding$6(selectionViewHolder, mediaItem, i2) && bitmap != null) {
            Bitmap bitmap2 = selectionViewHolder.getBitmap();
            if (bitmap2 != null) {
                if (bitmap2.getHeight() * bitmap2.getWidth() >= bitmap.getHeight() * bitmap.getWidth()) {
                    return;
                }
            }
            selectionViewHolder.bindImage(bitmap);
        }
    }

    public void destroy() {
        this.mClipboard.clear();
        this.mSequencer.exit();
    }

    public int getItemCount() {
        return this.mMediaData.getCount();
    }

    public MediaItem[] getSelectedItems() {
        return (MediaItem[]) this.mClipboard.values().toArray(new MediaItem[0]);
    }

    public Integer[] getSelectedPositions() {
        return (Integer[]) this.mClipboard.keySet().toArray(new Integer[0]);
    }

    public boolean isSelected(int i2) {
        return this.mClipboard.containsKey(Integer.valueOf(i2));
    }

    public void loadOnIdle(SelectionViewHolder selectionViewHolder, int i2, Runnable runnable) {
        if (selectionViewHolder != null) {
            if (selectionViewHolder.isOriginalBitmapLoaded()) {
                Optional.ofNullable(runnable).ifPresent(new l(0));
                return;
            }
            MediaItem mediaItem = selectionViewHolder.getMediaItem();
            if (mediaItem == null) {
                Log.w("SelectionViewAdapter", "loadOnIdle skip " + selectionViewHolder);
                return;
            }
            Bitmap bitmap = (Bitmap) this.mLruCache.getCache(mediaItem.getThumbCacheKey());
            if (bitmap != null) {
                Log.d("SelectionViewAdapter", "loadOnIdle(L) " + selectionViewHolder + " " + Logger.toSimpleString(bitmap));
                selectionViewHolder.setThumbKind(ThumbKind.XLARGE_NC_KIND);
                selectionViewHolder.bindImage(bitmap);
                Optional.ofNullable(runnable).ifPresent(new l(0));
                return;
            }
            this.mSequencer.request(mediaItem.getSimpleHashCode(), new g(this, selectionViewHolder, i2, runnable, mediaItem, System.currentTimeMillis()));
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mMediaData.register(this.mDataObserver);
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        this.mMediaData.unregister(this.mDataObserver);
    }

    public void onPositionEstimated(int i2, int i7) {
        Object obj;
        MediaItem read = this.mMediaData.read(i7);
        StringBuilder sb2 = new StringBuilder("onPositionEstimated {S,");
        sb2.append(i7);
        sb2.append(',');
        if (read != null) {
            obj = Long.valueOf(read.getFileId());
        } else {
            obj = "null";
        }
        sb2.append(obj);
        sb2.append('}');
        Log.d("SelectionViewAdapter", sb2.toString());
        if (read != null) {
            ThreadPool.getInstance().submit(new c(this, read, i7, System.currentTimeMillis()));
        }
    }

    public void selectAll(boolean z) {
        if (z) {
            for (int i2 = 0; i2 < this.mMediaData.getCount(); i2++) {
                this.mClipboard.put(Integer.valueOf(i2), getItem(i2));
            }
        } else {
            this.mClipboard.clear();
        }
        notifyItemRangeChanged(0, this.mMediaData.getCount(), "refresh_selection");
    }

    public SelectionViewAdapter setSelectionListener(BiConsumer<Integer, Boolean> biConsumer) {
        this.mSelectionCallback = biConsumer;
        return this;
    }

    public SelectionViewAdapter setSharedTransitionPosition(int i2) {
        this.mSharedTransitionPosition = i2;
        return this;
    }

    public boolean toggleSelected(int i2, MediaItem mediaItem) {
        if (this.mClipboard.containsKey(Integer.valueOf(i2))) {
            this.mClipboard.remove(Integer.valueOf(i2));
            return false;
        }
        this.mClipboard.put(Integer.valueOf(i2), mediaItem);
        return true;
    }

    public SelectionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return SelectionViewHolder.create(viewGroup, i2);
    }

    public void onViewAttachedToWindow(SelectionViewHolder selectionViewHolder) {
        Log.d("SelectionViewAdapter", "onViewAttachedToWindow " + selectionViewHolder);
        selectionViewHolder.attach();
        int i2 = this.mSharedTransitionPosition;
        if (i2 >= 0 && i2 == selectionViewHolder.getViewPosition()) {
            this.mSharedTransitionPosition = -1;
            this.mView.onViewHolderBound(selectionViewHolder);
        }
        loadOrDecoding(selectionViewHolder, selectionViewHolder.getMediaItem(), selectionViewHolder.getViewPosition());
    }

    public void onViewDetachedFromWindow(SelectionViewHolder selectionViewHolder) {
        Log.d("SelectionViewAdapter", "onViewDetachedFromWindow " + selectionViewHolder);
        selectionViewHolder.detach();
    }

    public void onViewRecycled(SelectionViewHolder selectionViewHolder) {
        MediaItem mediaItem = selectionViewHolder.getMediaItem();
        if (mediaItem != null) {
            this.mSequencer.cancel(mediaItem.getSimpleHashCode());
        }
        selectionViewHolder.recycle();
        super.onViewRecycled(selectionViewHolder);
    }

    public void onBindViewHolder(SelectionViewHolder selectionViewHolder, int i2) {
        MediaItem item = getItem(i2);
        selectionViewHolder.setOnItemClickListener(this.mOnItemClickListener);
        selectionViewHolder.bind(item);
        selectionViewHolder.setChecked(isSelected(i2));
    }

    public void onBindViewHolder(SelectionViewHolder selectionViewHolder, int i2, List<Object> list) {
        if (list.contains("refresh_selection")) {
            selectionViewHolder.setChecked(isSelected(i2));
        } else {
            super.onBindViewHolder(selectionViewHolder, i2, list);
        }
    }

    public void loadOnIdle(SelectionViewHolder selectionViewHolder, int i2) {
        loadOnIdle(selectionViewHolder, i2, (Runnable) null);
    }
}
