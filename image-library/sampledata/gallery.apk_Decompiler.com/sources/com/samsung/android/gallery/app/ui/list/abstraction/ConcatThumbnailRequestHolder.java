package com.samsung.android.gallery.app.ui.list.abstraction;

import A4.O;
import A4.P;
import A4.Q;
import A4.S;
import B8.e;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOperator;
import com.samsung.android.gallery.module.thumbnail.ConcatThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailLoadedListener;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ConcatThumbnailRequestHolder extends ThumbnailRequestHolder {
    private final int mColCount;
    private final String mConcatThumbnailKey;
    private final ConcatThumbnailLoader mConcatThumbnailLoader = ConcatThumbnailLoader.getInstance();
    private final int mItemCount;
    private final List<MediaItem> mItemList;
    private final ThumbnailLoadedListener mListener;
    private int mPieceBitmapSize;
    private ThumbKind mPieceThumbKind;
    private final boolean mRtl;
    private int mSidePadding;

    public ConcatThumbnailRequestHolder(ListViewHolder listViewHolder, List<MediaItem> list, int i2, ThumbnailLoadedListener thumbnailLoadedListener) {
        super(listViewHolder);
        this.mItemList = list;
        this.mItemCount = list.size();
        this.mColCount = GridValue.revert(i2);
        this.mListener = thumbnailLoadedListener;
        this.mConcatThumbnailKey = getConcatThumbnailKey();
        this.mRtl = Features.isEnabled(Features.IS_RTL);
    }

    private Bitmap createConcatBitmap(Bitmap bitmap, int i2, ArrayList<MediaItem> arrayList) {
        int i7;
        Bitmap bitmap2;
        ConcatThumbnailRequestHolder concatThumbnailRequestHolder;
        int i8 = (this.mSidePadding * 2) + this.mPieceBitmapSize;
        int i10 = i2 * i8;
        if (bitmap == null || bitmap.getWidth() != i10) {
            bitmap = Bitmap.createBitmap(i10, i8, Bitmap.Config.ARGB_8888);
        }
        Bitmap bitmap3 = bitmap;
        Canvas canvas = new Canvas(bitmap3);
        Paint paint = new Paint();
        boolean isEnabled = Features.isEnabled(Features.IS_RTL);
        if (isEnabled) {
            i7 = (i10 - this.mPieceBitmapSize) - this.mSidePadding;
        } else {
            i7 = this.mSidePadding;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<MediaItem> it = this.mItemList.iterator();
        int i11 = i7;
        while (true) {
            int i12 = 0;
            if (!it.hasNext()) {
                break;
            }
            MediaItem next = it.next();
            if (next != null) {
                bitmap2 = ThumbnailLoader.getInstance().getMemCache(next, this.mPieceThumbKind);
            } else {
                bitmap2 = null;
            }
            if (bitmap2 != null) {
                if (!next.isVideo()) {
                    i12 = next.getOrientation();
                }
                int i13 = i12;
                ConcatThumbnailRequestHolder concatThumbnailRequestHolder2 = this;
                Matrix buildMatrix = concatThumbnailRequestHolder2.buildMatrix(bitmap3, bitmap2, i13, next.getOrientationTag(), i11);
                concatThumbnailRequestHolder = concatThumbnailRequestHolder2;
                canvas.drawBitmap(bitmap2, buildMatrix, paint);
            } else {
                concatThumbnailRequestHolder = this;
                arrayList2.add(Integer.valueOf(i11));
                if (next != null) {
                    arrayList.add(next);
                }
            }
            i11 = concatThumbnailRequestHolder.setLeftPosition(isEnabled, i11);
            this = concatThumbnailRequestHolder;
        }
        ConcatThumbnailRequestHolder concatThumbnailRequestHolder3 = this;
        if (!arrayList2.isEmpty()) {
            int i14 = concatThumbnailRequestHolder3.mPieceBitmapSize;
            Rect rect = new Rect(0, 0, i14, i14);
            Paint paint2 = new Paint();
            paint2.setStyle(Paint.Style.FILL);
            paint2.setColor(concatThumbnailRequestHolder3.mViewHolder.itemView.getContext().getColor(R.color.thumbnail_icon_background));
            arrayList2.forEach(new S(concatThumbnailRequestHolder3, rect, canvas, paint2, 0));
        }
        return bitmap3;
    }

    private String getConcatThumbnailKey() {
        StringBuilder sb2 = new StringBuilder();
        this.mItemList.forEach(new P(sb2, 0));
        return this.mColCount + "#" + sb2;
    }

    private int getPieceBitmapSize() {
        int i2 = this.mPieceBitmapSize;
        return i2 * i2 * 4;
    }

    private boolean isInvalidPieceBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.getByteCount() != getPieceBitmapSize()) {
            return true;
        }
        return false;
    }

    private boolean isRecycled(ListViewHolder listViewHolder) {
        if (listViewHolder == null || listViewHolder.getMediaItem() == null || listViewHolder.getViewPosition() < 0 || !isSameView(listViewHolder)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createConcatBitmap$3(Rect rect, Canvas canvas, Paint paint, Integer num) {
        rect.offsetTo(num.intValue(), this.mSidePadding);
        canvas.drawRect(rect, paint);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$loadConcatThumbnailAsync$1(AtomicInteger atomicInteger, MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Rect rect;
        atomicInteger.getAndDecrement();
        if (bitmap != null && bitmap.getConfig() == Bitmap.Config.RGBA_F16) {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        }
        if (isInvalidPieceBitmap(bitmap)) {
            if (bitmap == null) {
                bitmap = this.mConcatThumbnailLoader.getBrokenPieceBitmap(this.mPieceBitmapSize);
            } else {
                RectF rawCropRectRatio = mediaItem.getRawCropRectRatio();
                if (!RectUtils.isValidRect(rawCropRectRatio) || mediaItem.isCustomCover()) {
                    rect = null;
                } else {
                    rect = RectUtils.getSmartCropRectWithLimit(rawCropRectRatio, bitmap.getWidth(), bitmap.getHeight());
                }
                bitmap = new BitmapOperator(bitmap).resize(this.mPieceBitmapSize).crop(rect).stretchable(true).apply();
            }
        }
        Bitmap partialConcatThumbMemCache = getPartialConcatThumbMemCache();
        if (partialConcatThumbMemCache != null && drawPartialConcatBitmap(partialConcatThumbMemCache, bitmap, mediaItem)) {
            onLoadedAndSaveMemCache(partialConcatThumbMemCache, atomicInteger);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$loadConcatThumbnailAsync$2() {
        return isRecycled(this.mViewHolder);
    }

    private void onLoadedAndSaveMemCache(Bitmap bitmap, AtomicInteger atomicInteger) {
        onLoadedConcatBitmap(bitmap);
        if (atomicInteger.get() == 0) {
            putConcatThumbMemCache(bitmap);
        }
    }

    private void onLoadedConcatBitmap(Bitmap bitmap) {
        if (this.mListener != null && !isRecycled(this.mViewHolder)) {
            this.mListener.onLoaded(bitmap, this, this.mViewHolder.getThumbKind());
        }
    }

    private void putConcatThumbMemCache(Bitmap bitmap) {
        this.mConcatThumbnailLoader.putConcatBitmap(this.mConcatThumbnailKey, bitmap);
    }

    private void putPartialConcatThumbMemCache(Bitmap bitmap) {
        this.mConcatThumbnailLoader.putPartialConcatBitmap(this.mConcatThumbnailKey, bitmap);
    }

    public static void request(ListViewHolder listViewHolder, List<MediaItem> list, int i2, Supplier<Integer> supplier, ThumbnailLoadedListener thumbnailLoadedListener) {
        if (list == null || list.isEmpty() || list.get(0) == null) {
            Log.w("ConcatThumbReqHolder", "request skip. null items");
        } else if (listViewHolder.getImage() != null) {
            MediaItem mediaItem = list.get(0);
            listViewHolder.bind(mediaItem);
            listViewHolder.getImage().setTag(mediaItem.getPath());
            listViewHolder.setChildItemCount(list.size());
            new ConcatThumbnailRequestHolder(listViewHolder, list, i2, thumbnailLoadedListener).setPieceBitmapKind(ThumbKind.SMALL_CROP_KIND).setSidePadding(supplier.get().intValue()).loadConcatThumbnailAsync();
        }
    }

    public static boolean requestAndBindImage(ListViewHolder listViewHolder, List<MediaItem> list, int i2) {
        if (listViewHolder.getImage() == null || list == null || list.isEmpty()) {
            Log.w("ConcatThumbReqHolder", "requestAndBindImage skip. null items");
            return false;
        }
        MediaItem mediaItem = list.get(0);
        listViewHolder.bind(mediaItem);
        listViewHolder.getImage().setTag(mediaItem.getPath());
        ConcatThumbnailRequestHolder pieceBitmapKind = new ConcatThumbnailRequestHolder(listViewHolder, list, i2, (ThumbnailLoadedListener) null).setPieceBitmapKind(ThumbKind.SMALL_CROP_KIND);
        Bitmap concatThumbMemCache = pieceBitmapKind.getConcatThumbMemCache();
        if (concatThumbMemCache == null) {
            Bitmap partialConcatThumbMemCache = pieceBitmapKind.getPartialConcatThumbMemCache();
            if (partialConcatThumbMemCache != null) {
                listViewHolder.bindImage(partialConcatThumbMemCache);
            }
            return false;
        }
        listViewHolder.bindImage(concatThumbMemCache);
        return true;
    }

    private int setLeftPosition(boolean z, int i2) {
        if (z) {
            return i2 - (this.mPieceBitmapSize - (this.mSidePadding * 2));
        }
        return (this.mSidePadding * 2) + this.mPieceBitmapSize + i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (r9 != false) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0021, code lost:
        if (r9 != false) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0029, code lost:
        if (r9 != false) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
        if (r9 != false) goto L_0x0034;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Matrix buildMatrix(android.graphics.Bitmap r6, android.graphics.Bitmap r7, int r8, int r9, int r10) {
        /*
            r5 = this;
            int r0 = r5.mPieceBitmapSize
            float r0 = (float) r0
            int r1 = r7.getHeight()
            float r1 = (float) r1
            float r0 = r0 / r1
            int r1 = r7.getWidth()
            float r1 = (float) r1
            float r1 = r1 * r0
            int r7 = r7.getHeight()
            float r7 = (float) r7
            float r7 = r7 * r0
            boolean r9 = com.samsung.android.gallery.support.utils.ExifUtils.isHorizontalMirror(r9)
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            float r7 = r7 / r2
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            if (r8 != 0) goto L_0x0025
            if (r9 == 0) goto L_0x003d
        L_0x0023:
            float r1 = -r1
            goto L_0x003d
        L_0x0025:
            r4 = 90
            if (r8 != r4) goto L_0x002c
            if (r9 == 0) goto L_0x0023
            goto L_0x003d
        L_0x002c:
            r4 = 180(0xb4, float:2.52E-43)
            if (r8 != r4) goto L_0x0036
            if (r9 == 0) goto L_0x0033
            goto L_0x0034
        L_0x0033:
            float r1 = -r1
        L_0x0034:
            float r7 = r7 * r3
            goto L_0x003d
        L_0x0036:
            r4 = 270(0x10e, float:3.78E-43)
            if (r8 != r4) goto L_0x003d
            if (r9 == 0) goto L_0x0034
            goto L_0x0033
        L_0x003d:
            float r10 = (float) r10
            int r5 = r5.mPieceBitmapSize
            float r5 = (float) r5
            float r5 = r5 / r2
            float r5 = r5 + r10
            float r5 = r5 - r1
            int r6 = r6.getHeight()
            float r6 = (float) r6
            float r6 = r6 / r2
            float r6 = r6 - r7
            android.graphics.Matrix r7 = new android.graphics.Matrix
            r7.<init>()
            r7.setScale(r0, r0)
            if (r8 <= 0) goto L_0x0059
            float r8 = (float) r8
            r7.postRotate(r8)
        L_0x0059:
            if (r9 == 0) goto L_0x0060
            r8 = 1065353216(0x3f800000, float:1.0)
            r7.postScale(r3, r8)
        L_0x0060:
            r7.postTranslate(r5, r6)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.abstraction.ConcatThumbnailRequestHolder.buildMatrix(android.graphics.Bitmap, android.graphics.Bitmap, int, int, int):android.graphics.Matrix");
    }

    public boolean drawPartialConcatBitmap(Bitmap bitmap, Bitmap bitmap2, MediaItem mediaItem) {
        int indexOf = indexOf(mediaItem);
        int i2 = 0;
        if (indexOf < 0) {
            return false;
        }
        int i7 = this.mSidePadding;
        int i8 = (((i7 * 2) + this.mPieceBitmapSize) * indexOf) + i7;
        if (this.mRtl) {
            i8 = (bitmap.getWidth() - i8) - this.mPieceBitmapSize;
        }
        int i10 = i8;
        if (!mediaItem.isVideo()) {
            i2 = mediaItem.getOrientation();
        }
        Bitmap bitmap3 = bitmap;
        Bitmap bitmap4 = bitmap2;
        new Canvas(bitmap3).drawBitmap(bitmap4, buildMatrix(bitmap3, bitmap4, i2, mediaItem.getOrientationTag(), i10), new Paint());
        return true;
    }

    public int getChildItemCount() {
        return this.mItemCount;
    }

    public Bitmap getConcatThumbMemCache() {
        return this.mConcatThumbnailLoader.getConcatBitmap(this.mConcatThumbnailKey);
    }

    public Bitmap getPartialConcatThumbMemCache() {
        return this.mConcatThumbnailLoader.getPartialConcatBitmap(this.mConcatThumbnailKey);
    }

    public int indexOf(MediaItem mediaItem) {
        String thumbCacheKey = mediaItem.getThumbCacheKey();
        int i2 = 0;
        for (MediaItem thumbCacheKey2 : this.mItemList) {
            if (thumbCacheKey.equals(thumbCacheKey2.getThumbCacheKey())) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public boolean isSameView(ListViewHolder listViewHolder) {
        try {
            if (listViewHolder.getViewPosition() == getPosition()) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException | NullPointerException unused) {
            return true;
        }
    }

    public void loadConcatThumbnailAsync() {
        if (this.mItemCount > 0) {
            Bitmap concatThumbMemCache = getConcatThumbMemCache();
            if (concatThumbMemCache != null) {
                onLoadedConcatBitmap(concatThumbMemCache);
                return;
            }
            ArrayList arrayList = new ArrayList();
            Bitmap createConcatBitmap = createConcatBitmap(getPartialConcatThumbMemCache(), this.mColCount, arrayList);
            onLoadedConcatBitmap(createConcatBitmap);
            if (arrayList.isEmpty()) {
                this.mConcatThumbnailLoader.putConcatBitmap(this.mConcatThumbnailKey, createConcatBitmap);
                return;
            }
            putPartialConcatThumbMemCache(createConcatBitmap);
            AtomicInteger atomicInteger = new AtomicInteger(arrayList.size());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem = (MediaItem) it.next();
                ThumbnailLoader instance = ThumbnailLoader.getInstance();
                ThumbKind thumbKind = this.mViewHolder.getThumbKind();
                Objects.requireNonNull(mediaItem);
                instance.loadThumbnail(mediaItem, thumbKind, new e(mediaItem, 1), new Q((Object) this, (Object) atomicInteger, (Object) mediaItem, 0), new O(1, this));
            }
            onLoadedAndSaveMemCache(createConcatBitmap, atomicInteger);
        }
    }

    public ConcatThumbnailRequestHolder setPieceBitmapKind(ThumbKind thumbKind) {
        this.mPieceThumbKind = thumbKind;
        this.mPieceBitmapSize = thumbKind.size();
        return this;
    }

    public ConcatThumbnailRequestHolder setSidePadding(int i2) {
        this.mSidePadding = i2;
        return this;
    }
}
