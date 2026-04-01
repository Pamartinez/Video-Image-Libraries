package com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem;

import A4.C0372g;
import A4.N;
import C3.i;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.IClusterResult;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.Optional;
import java.util.function.Supplier;
import o6.p;
import q8.a;
import x5.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ClusterResultViewAdapter<V extends IClusterResult> extends GalleryListAdapter<ListViewHolder> {
    private static final Runnable DRAG_UPDATER = new i(29);
    private int mCount;
    private final EventContext mHandler;
    protected final MediaData mMediaData;
    protected final V mView;
    private final ClusterResultViewHolderFactory mViewHolderFactory = new ClusterResultViewHolderFactory(getContext());

    public ClusterResultViewAdapter(V v, EventContext eventContext) {
        super(eventContext.getBlackboard());
        setDragUpdater(DRAG_UPDATER);
        this.mView = v;
        MediaData mediaData = v.getMediaData();
        this.mMediaData = mediaData;
        this.mCount = mediaData.getCount();
        this.mHandler = eventContext;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
        r0 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean bindImageOnScrollIdle(com.samsung.android.gallery.widget.listviewholder.ListViewHolder r4, com.samsung.android.gallery.module.data.MediaItem r5) {
        /*
            r3 = this;
            android.widget.ImageView r0 = r4.getImage()
            if (r0 != 0) goto L_0x0007
            goto L_0x002f
        L_0x0007:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r1 = r4.getThumbKind()
            android.graphics.Bitmap r1 = r0.getMemCache(r5, r1)
            if (r1 == 0) goto L_0x002f
            boolean r0 = r0.isReplacedThumbnail(r1)
            r2 = 1
            if (r0 == 0) goto L_0x001f
            r5.setBroken(r2)
        L_0x001f:
            r4.bindImage(r1)
            android.view.View r5 = r4.itemView
            q6.e r0 = new q6.e
            r1 = 28
            r0.<init>(r1, r3, r4)
            r5.post(r0)
            return r2
        L_0x002f:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.search.pictures.cluster.clusteritem.ClusterResultViewAdapter.bindImageOnScrollIdle(com.samsung.android.gallery.widget.listviewholder.ListViewHolder, com.samsung.android.gallery.module.data.MediaItem):boolean");
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

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$replaceIfHighQualityRequired$5(int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (bitmap != null) {
            if (bitmap.getHeight() * bitmap.getWidth() > i2) {
                ((ThumbnailRequestHolder) uniqueKey).bindImageIfAvailable(bitmap);
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$replaceIfHighQualityRequired$6(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        if (listViewHolder.getViewPosition() != thumbnailRequestHolder.getPosition()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$requestThumbnail$2(ListViewHolder listViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        if (listViewHolder.getViewPosition() != thumbnailRequestHolder.getPosition()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: replaceIfHighQualityRequired */
    public void lambda$bindImageOnScrollIdle$1(ListViewHolder listViewHolder) {
        MediaItem mediaItem;
        int height;
        int intValue;
        if (listViewHolder.getViewPosition() >= 0 && (mediaItem = listViewHolder.getMediaItem()) != null && !mediaItem.isBroken() && (height = listViewHolder.itemView.getHeight() * listViewHolder.itemView.getWidth()) > (intValue = ((Integer) Optional.ofNullable(listViewHolder.getBitmap(false)).map(new a(23)).orElseGet(new com.samsung.android.motionphoto.utils.v2.i(height, 5))).intValue())) {
            ThumbKind thumbKind = ThumbKind.XLARGE_NC_KIND;
            if (intValue < thumbKind.volume()) {
                ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
                ThumbnailLoader.getInstance().loadThumbnail(mediaItem, thumbKind, thumbnailRequestHolder, new b(intValue), new N(listViewHolder, thumbnailRequestHolder, 5));
            }
        }
    }

    private void requestThumbnail(ListViewHolder listViewHolder) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(listViewHolder);
        ThumbnailLoader.getInstance().loadThumbnail(thumbnailRequestHolder.getMediaItem(), listViewHolder.getThumbKind(), thumbnailRequestHolder, new p(24, this), new N(listViewHolder, thumbnailRequestHolder, 4));
    }

    /* access modifiers changed from: private */
    public void setBitmapWithBind(Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        ThumbnailRequestHolder thumbnailRequestHolder = (ThumbnailRequestHolder) uniqueKey;
        if (thumbnailRequestHolder.bindImageIfAvailable(bitmap)) {
            ListViewHolder viewHolder = thumbnailRequestHolder.getViewHolder();
            lambda$bindImageOnScrollIdle$1(viewHolder);
            if (bitmap == null) {
                viewHolder.itemView.post(new C0372g(15, thumbnailRequestHolder));
            }
        }
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
        return this.mCount;
    }

    public int getItemCount() {
        return Math.min(this.mView.getMaxItemCount(), getDataCount());
    }

    public int getItemViewType(int i2) {
        return this.mView.getItemType();
    }

    public MediaItem getMediaItemFromCache(int i2) {
        return this.mMediaData.readCache(i2);
    }

    public boolean isAutoDragPossibleFromMainFragment() {
        Supplier supplier = (Supplier) this.mBlackBoard.read("data://user/AutoDragPossible");
        if (supplier == null || !((Boolean) supplier.get()).booleanValue()) {
            return false;
        }
        return true;
    }

    public final MediaItem loadMediaItemSync(int i2) {
        try {
            return this.mMediaData.read(i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void onDataChanged() {
        super.onDataChanged();
        this.mCount = this.mMediaData.getCount();
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.mView.onClusterItemClicked(listViewHolder, mediaItem, i7);
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        if (!supportLongPressed()) {
            return false;
        }
        boolean onListItemLongClickInternal = super.onListItemLongClickInternal(listViewHolder, i2, mediaItem, i7);
        if (!((Boolean) this.mBlackBoard.read("data://dragging_selection", Boolean.FALSE)).booleanValue()) {
            this.mBlackBoard.postEvent(EventMessage.obtain(1043, new Object[]{listViewHolder, mediaItem}));
        }
        return onListItemLongClickInternal;
    }

    public boolean supportLongPressed() {
        return false;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        listViewHolder.setThumbKind(ThumbKind.MEDIUM_KIND);
        bindViewHolderInternal(listViewHolder, i2);
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createViewHolder(this.mHandler, viewGroup, i2);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
