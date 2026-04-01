package com.samsung.android.gallery.widget.hoverview;

import Eb.a;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.R$drawable;
import com.samsung.android.gallery.widget.R$layout;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewListView;
import com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder;
import com.samsung.android.gallery.widget.hoverview.IHoverPreviewData;
import com.samsung.android.gallery.widget.utils.ResourceUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class HoverPreviewListAdapter<D extends IHoverPreviewData> extends RecyclerView.Adapter<HoverPreviewViewHolder> {
    private static final int ITEM_LAYOUT_ID = R$layout.recycler_item_hover_preview_layout;
    private float mBackgroundRadius;
    private int mColumnCount = 0;
    private D mData;
    private HoverPreviewViewHolder.OnDeleteClickListener mDeleteClickListener;
    private int mHoverPreviewBackgroundHeight;
    private int mHoverPreviewBackgroundWidth;
    private boolean mIsAlbum;
    private boolean mIsHidOption;
    private boolean mIsSuggestion;
    private HoverPreviewListView.OnItemTouchListener mItemTouchListener;
    private HoverPreviewListView.OnLoadCompleteListener mLoadCompleteListener;
    private int mLoadedItemCount;
    private int mRowCount = 0;
    private HoverPreviewViewHolder.OnShareClickListener mShareClickListener;

    private Rect getIconSize(Context context, int i2) {
        Drawable drawable;
        if (i2 == 0 || (drawable = context.getDrawable(i2)) == null) {
            return null;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f = new float[]{2.5f, 2.0f, 1.5f}[this.mColumnCount - 1];
        return new Rect(0, 0, (int) (((float) intrinsicWidth) * f), (int) (((float) intrinsicHeight) * f));
    }

    private Bitmap getReplacedThumbnail(MediaItem mediaItem) {
        mediaItem.setBroken(true);
        return ThumbnailLoader.getInstance().getDefaultAlbumImage(true);
    }

    private void initializeViewHolderListener(HoverPreviewViewHolder hoverPreviewViewHolder) {
        hoverPreviewViewHolder.setOnItemTouchListener((HoverPreviewViewHolder.OnItemTouchListener) null);
        hoverPreviewViewHolder.setOnShareClickListener((HoverPreviewViewHolder.OnShareClickListener) null);
        hoverPreviewViewHolder.setOnDeleteClickListener((HoverPreviewViewHolder.OnDeleteClickListener) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(HoverPreviewViewHolder hoverPreviewViewHolder, MediaItem mediaItem, Context context, Bitmap bitmap, int i2) {
        hoverPreviewViewHolder.setImage(mediaItem, resizeThumbnail(context, bitmap, mediaItem), !this.mIsAlbum, i2, this.mBackgroundRadius);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$1(HoverPreviewViewHolder hoverPreviewViewHolder, MediaItem mediaItem, Context context, int i2, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        int i7 = i2;
        Context context2 = context;
        MediaItem mediaItem2 = mediaItem;
        ThreadUtil.postOnUiThread(new d(this, hoverPreviewViewHolder, mediaItem2, context2, bitmap, i7));
    }

    /* access modifiers changed from: private */
    public boolean lambda$onBindViewHolder$2(MediaItem mediaItem, View view, MotionEvent motionEvent, int i2) {
        return ((a) this.mItemTouchListener).d.lambda$init$1(view, motionEvent, i2, mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resizeThumbnail$3() {
        this.mLoadCompleteListener.onLoadCompleted();
    }

    private int makeRoundCorner(int i2) {
        int i7 = this.mColumnCount;
        return setRoundMode(0, i2 / i7, i2 % i7);
    }

    private int setRoundMode(int i2, int i7, int i8) {
        int i10 = this.mRowCount;
        if (i10 == 1 && this.mColumnCount == 1) {
            return 15;
        }
        if (i8 == 0) {
            if (i7 == 0) {
                i2 = 1;
            }
            if (i7 == i10 - 1) {
                return i2 | 4;
            }
            return i2;
        }
        if (i8 == this.mColumnCount - 1) {
            if (i7 == 0) {
                i2 = 2;
            }
            if (i7 == i10 - 1) {
                return i2 | 8;
            }
        }
        return i2;
    }

    private void setViewHolderClickListener(HoverPreviewViewHolder hoverPreviewViewHolder) {
        hoverPreviewViewHolder.setOnShareClickListener(this.mShareClickListener);
        hoverPreviewViewHolder.setOnDeleteClickListener(this.mDeleteClickListener);
    }

    private boolean supportDelete(MediaItem mediaItem) {
        if (this.mIsHidOption) {
            return false;
        }
        if ((!mediaItem.isSharing() || MediaItemMde.isOwnedByMe(mediaItem)) && !mediaItem.isMtp()) {
            return true;
        }
        return false;
    }

    private boolean supportShare(MediaItem mediaItem) {
        if (this.mIsHidOption || mediaItem.isSharing() || mediaItem.isMtp() || TrashData.isTrash(mediaItem) || this.mIsSuggestion) {
            return false;
        }
        return true;
    }

    public int getIconType(MediaItem mediaItem) {
        if (mediaItem.isVideo()) {
            int recordingMode = mediaItem.getRecordingMode();
            if (RecordingMode.isSlowMo(recordingMode)) {
                return R$drawable.gallery_ic_thumbnail_slow_motion;
            }
            if (recordingMode == 2) {
                return R$drawable.gallery_ic_thumbnail_fast_motion;
            }
            if (recordingMode == 5) {
                return R$drawable.gallery_ic_thumbnail_hyperlapse;
            }
            if (RecordingMode.isSuperSlowMo(recordingMode)) {
                return R$drawable.gallery_ic_thumbnail_super_slow_motion;
            }
            return R$drawable.gallery_ic_thumbnail_video;
        } else if (!mediaItem.isImage() || mediaItem.getGroupMediaId() <= 0) {
            return 0;
        } else {
            return R$drawable.gallery_ic_thumbnail_burst;
        }
    }

    public int getItemCount() {
        D d = this.mData;
        if (d == null) {
            return 0;
        }
        return d.getCount();
    }

    public void recycle() {
        D d = this.mData;
        if (d != null) {
            d.recycle();
        }
        this.mData = null;
    }

    public Bitmap resizeThumbnail(Context context, Bitmap bitmap, MediaItem mediaItem) {
        int i2;
        int i7 = this.mHoverPreviewBackgroundWidth / this.mColumnCount;
        int i8 = this.mHoverPreviewBackgroundHeight / this.mRowCount;
        if (bitmap == null) {
            mediaItem.setBroken(true);
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(context, ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
        }
        if (mediaItem.isVideo()) {
            i2 = 0;
        } else {
            i2 = mediaItem.getOrientation();
        }
        Bitmap rotateBitmap = BitmapUtils.rotateBitmap(bitmap, i2);
        if (i8 <= 0 || i7 <= 0) {
            StringBuilder sb2 = new StringBuilder("mHoverPreviewBackgroundWidth : ");
            sb2.append(this.mHoverPreviewBackgroundWidth);
            sb2.append("\nmColumnCount : ");
            sb2.append(this.mColumnCount);
            sb2.append("\nmHoverPreviewBackgroundHeight : ");
            sb2.append(this.mHoverPreviewBackgroundHeight);
            sb2.append("\nmRowCount : ");
            j.x(sb2, this.mRowCount, "\ntarget width : ", i7, "\ntarget height : ");
            sb2.append(i8);
            Log.e("HoverPreviewListAdapter", sb2.toString());
            new InternalException("Hover Preview Fail").post();
        } else if (this.mIsAlbum) {
            rotateBitmap = BitmapUtils.resizeAndCropCenter(rotateBitmap, i7, i8);
        } else {
            rotateBitmap = BitmapUtils.resize(rotateBitmap, i7, i8);
        }
        int i10 = this.mLoadedItemCount + 1;
        this.mLoadedItemCount = i10;
        if (i10 == getItemCount()) {
            ThreadUtil.postOnUiThread(new c(this));
        }
        return rotateBitmap;
    }

    public void setData(D d) {
        this.mData = d;
        this.mLoadedItemCount = 0;
    }

    public void setHideOption(boolean z) {
        this.mIsHidOption = z;
    }

    public void setIsAlbum(boolean z) {
        this.mIsAlbum = z;
    }

    public void setIsSuggestion(boolean z) {
        this.mIsSuggestion = z;
    }

    public void setListViewSize(Context context, int i2, int i7) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.hovering_image_gap);
        this.mHoverPreviewBackgroundWidth = i2 - ((this.mColumnCount * dimensionPixelSize) * 2);
        this.mHoverPreviewBackgroundHeight = i7 - ((this.mRowCount * dimensionPixelSize) * 2);
        this.mBackgroundRadius = (float) context.getResources().getDimensionPixelSize(R$dimen.hovering_background_res_radius);
    }

    public void setOnDeleteClickListener(HoverPreviewViewHolder.OnDeleteClickListener onDeleteClickListener) {
        this.mDeleteClickListener = onDeleteClickListener;
    }

    public void setOnItemTouchListener(HoverPreviewListView.OnItemTouchListener onItemTouchListener) {
        this.mItemTouchListener = onItemTouchListener;
    }

    public void setOnLoadCompleteListener(HoverPreviewListView.OnLoadCompleteListener onLoadCompleteListener) {
        this.mLoadCompleteListener = onLoadCompleteListener;
    }

    public void setOnShareClickListener(HoverPreviewViewHolder.OnShareClickListener onShareClickListener) {
        this.mShareClickListener = onShareClickListener;
    }

    public void setSpanSize(int i2, int i7) {
        this.mRowCount = i2;
        this.mColumnCount = i7;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onBindViewHolder(com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder r10, int r11) {
        /*
            r9 = this;
            D r0 = r9.mData
            if (r0 != 0) goto L_0x0006
            goto L_0x00a9
        L_0x0006:
            com.samsung.android.gallery.module.data.MediaItem r2 = r0.getMediaItem(r11)
            if (r2 != 0) goto L_0x0014
            java.lang.String r9 = "HoverPreviewListAdapter"
            java.lang.String r10 = "item is null! itemPosition : "
            A.a.k(r11, r10, r9)
            return
        L_0x0014:
            int r5 = r9.makeRoundCorner(r11)
            android.view.View r11 = r10.itemView
            android.content.Context r11 = r11.getContext()
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r0 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()
            com.samsung.android.gallery.module.thumbnail.type.ThumbKind r7 = com.samsung.android.gallery.module.thumbnail.type.ThumbKind.LARGE_KIND
            android.graphics.Bitmap r0 = r0.getMemCache(r2, r7)
            r8 = 1
            if (r0 == 0) goto L_0x003d
            android.graphics.Bitmap r3 = r9.resizeThumbnail(r11, r0, r2)
            boolean r0 = r9.mIsAlbum
            r4 = r0 ^ 1
            float r6 = r9.mBackgroundRadius
            r1 = r10
            r1.setImage(r2, r3, r4, r5, r6)
        L_0x0039:
            r5 = r11
            r4 = r2
            r2 = r9
            goto L_0x0079
        L_0x003d:
            r1 = r10
            boolean r10 = r2.isSharing()
            if (r10 == 0) goto L_0x0060
            java.lang.String r10 = r2.getPath()
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x0060
            android.graphics.Bitmap r10 = r9.getReplacedThumbnail(r2)
            android.graphics.Bitmap r3 = r9.resizeThumbnail(r11, r10, r2)
            boolean r10 = r9.mIsAlbum
            r4 = r10 ^ 1
            float r6 = r9.mBackgroundRadius
            r1.setImage(r2, r3, r4, r5, r6)
            goto L_0x0039
        L_0x0060:
            com.samsung.android.gallery.module.thumbnail.ThumbnailLoader r10 = com.samsung.android.gallery.module.thumbnail.ThumbnailLoader.getInstance()
            B8.e r0 = new B8.e
            r3 = 1
            r0.<init>(r2, r3)
            r3 = r1
            com.samsung.android.gallery.widget.hoverview.a r1 = new com.samsung.android.gallery.widget.hoverview.a
            r4 = r2
            r6 = r5
            r2 = r9
            r5 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            r9 = r1
            r1 = r3
            r10.loadThumbnail(r4, r7, r0, r9)
        L_0x0079:
            int r9 = r2.getIconType(r4)
            android.graphics.Rect r10 = r2.getIconSize(r5, r9)
            r1.setIcon(r9, r10)
            boolean r9 = r2.mIsAlbum
            if (r9 != 0) goto L_0x009d
            boolean r9 = r2.supportShare(r4)
            if (r9 == 0) goto L_0x0091
            r1.setEnableShare(r8)
        L_0x0091:
            boolean r9 = r2.supportDelete(r4)
            if (r9 == 0) goto L_0x009a
            r1.setEnableDelete(r8)
        L_0x009a:
            r2.setViewHolderClickListener(r1)
        L_0x009d:
            com.samsung.android.gallery.widget.hoverview.HoverPreviewListView$OnItemTouchListener r9 = r2.mItemTouchListener
            if (r9 == 0) goto L_0x00a9
            com.samsung.android.gallery.widget.hoverview.b r9 = new com.samsung.android.gallery.widget.hoverview.b
            r9.<init>(r2, r4)
            r1.setOnItemTouchListener(r9)
        L_0x00a9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.hoverview.HoverPreviewListAdapter.onBindViewHolder(com.samsung.android.gallery.widget.hoverview.HoverPreviewViewHolder, int):void");
    }

    public HoverPreviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new HoverPreviewViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(ITEM_LAYOUT_ID, viewGroup, false));
    }

    public void onViewRecycled(HoverPreviewViewHolder hoverPreviewViewHolder) {
        initializeViewHolderListener(hoverPreviewViewHolder);
        hoverPreviewViewHolder.recycle();
        super.onViewRecycled(hoverPreviewViewHolder);
    }
}
