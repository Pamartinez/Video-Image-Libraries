package com.samsung.android.gallery.widget.photoview;

import A6.a;
import Ba.h;
import D3.j;
import J5.c;
import M9.o;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapSizeHolder;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoder;
import com.samsung.android.gallery.support.cache.BytesBuffer;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SerialExecutor;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.photoview.AliveZoomCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageProcessor {
    public static final ImageProcessor EMPTY = new ImageProcessor("");
    private final StringCompat TAG;
    public float mAlphaBlendingValue;
    private Bitmap mBitmap;
    private int mBitmapGenerationId;
    private BitmapOptions mBitmapOptions;
    final Semaphore mDecoderLock = new Semaphore(4);
    private final Executor mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
    private int mFullImageSampleSize = 1;
    ConcurrentHashMap<Integer, Integer> mIsRegionDecoderCreatingSet = new ConcurrentHashMap<>();
    public Bitmap mLastBitmap;
    private ImageRegionDecoder mRegionDecoder;
    private final SourceInfo mSourceInfo = new SourceInfo();
    private Map<Integer, List<Tile>> mTileMap;
    long mTileTaskElapsed;
    private final CopyOnWriteArrayList<LoadTileTask> mTileTasks = new CopyOnWriteArrayList<>();

    public ImageProcessor(String str) {
        StringCompat stringCompat = new StringCompat("ImageProcessor");
        this.TAG = stringCompat;
        stringCompat.setTag(str);
    }

    private boolean hasFullSizePreview() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmap.getWidth() != Math.round(((float) getWidth(false)) / ((float) this.mFullImageSampleSize))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasMissingTile$4(Tile tile) {
        if (!tile.visible) {
            return false;
        }
        if (tile.loading || tile.bitmap == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasMissingTile$5(PhotoViewPositionControl photoViewPositionControl, int i2, int i7, Tile tile) {
        if (!photoViewPositionControl.tileVisible(tile, i2, i7)) {
            return false;
        }
        if (tile.loading || tile.bitmap == null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$hasUnloadedTile$8(Tile tile) {
        if (!tile.visible || tile.loading || tile.bitmap != null) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initTileTask$1(View view, Integer num, ImageRegionDecoder imageRegionDecoder) {
        if (isAvailable(view)) {
            if (num.intValue() != getHash()) {
                Log.e(this.TAG, "initTileTask invalid");
                tryRegionDecoding(view);
            } else {
                onTilesInitialized(view, imageRegionDecoder, imageRegionDecoder.getWidth(), imageRegionDecoder.getHeight());
            }
        }
        this.mIsRegionDecoderCreatingSet.remove(num);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$initTileTask$2(View view, Integer num, long j2) {
        Integer num2;
        ImageProcessor imageProcessor;
        if (isAvailable(view)) {
            ImageRegionDecoder createRegionDecoder = createRegionDecoder();
            if (createRegionDecoder == null || !isAvailable(view)) {
                imageProcessor = this;
                num2 = num;
                imageProcessor.mIsRegionDecoderCreatingSet.remove(num2);
            } else {
                imageProcessor = this;
                num2 = num;
                ThreadUtil.postOnUiThread(new a((Object) imageProcessor, (Object) view, (Object) num2, (Object) createRegionDecoder, 12));
            }
            StringCompat stringCompat = imageProcessor.TAG;
            C0212a.z(new Object[]{num2, Logger.getSimpleName((Object) createRegionDecoder), Long.valueOf(j2)}, new StringBuilder("initTileTask"), stringCompat);
            return;
        }
        Integer num3 = num;
        this.mIsRegionDecoderCreatingSet.remove(num3);
        StringCompat stringCompat2 = this.TAG;
        C0212a.z(new Object[]{num3, Long.valueOf(j2)}, new StringBuilder("initTileTask skip"), stringCompat2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recycleTileMap$6(Integer num, List list) {
        list.forEach(new f(2, this));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$tryRegionDecoding$0(View view, BitmapOptions bitmapOptions) {
        if (isAvailable(view)) {
            this.mBitmapOptions = bitmapOptions;
            tryRegionDecoding(view, bitmapOptions);
            return;
        }
        Log.w((CharSequence) this.TAG, "tryRegionDecoding fail", Logger.v(view, Boolean.valueOf(this.mSourceInfo.isAvailable())));
    }

    private void recycleAfterDone(ImageRegionDecoder imageRegionDecoder, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        Log.v(this.TAG, C0212a.l("S.recycleRegionDecoder for ", str), Integer.valueOf(this.mDecoderLock.availablePermits()), 4);
        try {
            if (this.mDecoderLock.tryAcquire(4, 30, TimeUnit.SECONDS)) {
                imageRegionDecoder.recycle();
                this.mDecoderLock.release(4);
            }
        } catch (InterruptedException e) {
            StringCompat stringCompat = this.TAG;
            Log.e(stringCompat, "recycleAfterDone " + e);
            imageRegionDecoder.recycle();
        }
        Log.v(this.TAG, C0212a.l("E.recycleRegionDecoder for ", str), Logger.vt(Integer.valueOf(this.mDecoderLock.availablePermits()), Long.valueOf(currentTimeMillis)));
    }

    public void backupLastBitmap(Bitmap bitmap) {
        if (Features.isEnabled(Features.SUPPORT_PPP_BLENDING)) {
            this.mLastBitmap = bitmap;
            this.mAlphaBlendingValue = 1.0f;
        }
    }

    public int calculateInSampleSize(float f) {
        return this.mSourceInfo.calculateInSampleSize(f);
    }

    public void close() {
        SimpleThreadPool.getInstance().execute(new o(7, this));
    }

    public BitmapOptions createBitmapOptions() {
        return this.mSourceInfo.computeBitmapOptions();
    }

    public ImageRegionDecoder createRegionDecoder() {
        return this.mSourceInfo.createRegionDecoder();
    }

    public void fileSRect(Rect rect, Rect rect2) {
        if (is0Degree()) {
            rect2.set(rect);
        } else if (is90Degree()) {
            rect2.set(rect.top, getHeight(false) - rect.right, rect.bottom, getHeight(false) - rect.left);
        } else if (is180Degree()) {
            rect2.set(getWidth(false) - rect.right, getHeight(false) - rect.bottom, getWidth(false) - rect.left, getHeight(false) - rect.top);
        } else {
            rect2.set(getWidth(false) - rect.bottom, rect.left, getWidth(false) - rect.top, rect.right);
        }
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public int getBitmapGenerationId() {
        return this.mBitmapGenerationId;
    }

    public PointF getCenter(boolean z) {
        return this.mSourceInfo.getCenter(z);
    }

    public long getFileSize() {
        return this.mSourceInfo.getFileSize();
    }

    public int getFullImageSampleSize() {
        return this.mFullImageSampleSize;
    }

    public int getHash() {
        return (this.mFullImageSampleSize + "_" + this.mSourceInfo.getHash()).hashCode();
    }

    public int getHeight(boolean z) {
        return this.mSourceInfo.getHeight(z);
    }

    public PointF getInitialPosition(boolean z) {
        return this.mSourceInfo.getInitialPosition(z);
    }

    public int getOrientation() {
        return this.mSourceInfo.getOrientation();
    }

    public int getOrientationTag() {
        return this.mSourceInfo.getOrientationTag();
    }

    public Map<Integer, List<Tile>> getTileMap() {
        return this.mTileMap;
    }

    public int getWidth(boolean z) {
        return this.mSourceInfo.getWidth(z);
    }

    public boolean hasBitmap() {
        if (this.mBitmap != null) {
            return true;
        }
        return false;
    }

    public boolean hasDifferentSize(boolean z, int i2, int i7) {
        if (getWidth(z) == i2 && getHeight(z) == i7) {
            return false;
        }
        return true;
    }

    public boolean hasMissingTile(int i2) {
        Map<Integer, List<Tile>> map = this.mTileMap;
        if (map == null) {
            Log.w(this.TAG, "hasMissingTile : mTileMap is null");
            return true;
        }
        List list = map.get(Integer.valueOf(i2));
        if (list == null) {
            return false;
        }
        if (!list.stream().noneMatch(new j(1))) {
            return list.stream().anyMatch(new j(2));
        }
        Log.d(this.TAG, "all missing");
        return true;
    }

    public boolean hasSource() {
        return this.mSourceInfo.isAvailable();
    }

    public boolean hasUnloadedTile(int i2) {
        Map<Integer, List<Tile>> map;
        List list;
        if (this.mRegionDecoder == null || (map = this.mTileMap) == null || (list = map.get(Integer.valueOf(i2))) == null || !list.stream().anyMatch(new j(3))) {
            return false;
        }
        return true;
    }

    public void initBaseLayer(PhotoView photoView, Point point, PhotoViewPositionControl photoViewPositionControl) {
        boolean z;
        Log.d(this.TAG, "initBaseLayer {" + this.mFullImageSampleSize + "}");
        photoView.initTileMap(point);
        List<Tile> list = this.mTileMap.get(Integer.valueOf(this.mFullImageSampleSize));
        if (list != null) {
            for (Tile tile : list) {
                if (list.size() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                loadTileTask(tile, photoView, z);
            }
        }
        photoView.refreshRequiredTiles(true, photoViewPositionControl);
    }

    public void initTileMap(PhotoView photoView, Point point) {
        boolean z;
        int i2;
        int i7;
        Point point2 = point;
        Log.d(this.TAG, "initTileMap {" + point2.x + GlobalPostProcInternalPPInterface.SPLIT_REGEX + point2.y + "}");
        this.mTileMap = new LinkedHashMap();
        int i8 = this.mFullImageSampleSize;
        int i10 = 1;
        int i11 = 1;
        while (true) {
            int width = getWidth(true) / i10;
            int height = getHeight(true) / i11;
            int i12 = width / i8;
            int i13 = height / i8;
            int i14 = width;
            while (true) {
                if (i12 + i10 + 1 > point2.x || (((double) i12) > ((double) photoView.getViewWidth()) * 0.5d && i8 < this.mFullImageSampleSize)) {
                    i10++;
                    i14 = getWidth(true) / i10;
                    i12 = i14 / i8;
                }
            }
            int i15 = i11;
            int i16 = i13;
            int i17 = height;
            while (true) {
                if (i16 + i15 + 1 > point2.y || (((double) i16) > ((double) photoView.getViewHeight()) * 0.5d && i8 < this.mFullImageSampleSize)) {
                    i15++;
                    i17 = getHeight(true) / i15;
                    i16 = i17 / i8;
                }
            }
            ArrayList arrayList = new ArrayList(i10 * i15);
            int i18 = 0;
            while (i18 < i10) {
                for (int i19 = 0; i19 < i15; i19++) {
                    int i20 = i10;
                    Tile tile = new Tile();
                    tile.sampleSize = i8;
                    if (i8 == this.mFullImageSampleSize) {
                        z = true;
                    } else {
                        z = false;
                    }
                    tile.visible = z;
                    int i21 = i18 * i14;
                    int i22 = i19 * i17;
                    if (i18 == i20 - 1) {
                        i2 = getWidth(true);
                    } else {
                        i2 = (i18 + 1) * i14;
                    }
                    int i23 = i18;
                    if (i19 == i15 - 1) {
                        i7 = getHeight(true);
                    } else {
                        i7 = (i19 + 1) * i17;
                    }
                    tile.sRect = new Rect(i21, i22, i2, i7);
                    tile.vRect = new Rect(0, 0, 0, 0);
                    tile.fileSRect = new Rect(tile.sRect);
                    arrayList.add(tile);
                    i18 = i23;
                    setBorder(tile, i18, i19, i20, i15);
                    i10 = i20;
                }
                i18++;
            }
            this.mTileMap.put(Integer.valueOf(i8), arrayList);
            if (i8 != 1) {
                i8 /= 2;
                i11 = i15;
            } else {
                return;
            }
        }
    }

    public void initTileTask(View view) {
        boolean isSupportRegionDecoding = isSupportRegionDecoding();
        ImageRegionDecoder imageRegionDecoder = this.mRegionDecoder;
        if (imageRegionDecoder != null || !isSupportRegionDecoding) {
            Log.w((CharSequence) this.TAG, "initTileTask fail", imageRegionDecoder, Boolean.valueOf(isSupportRegionDecoding));
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Integer valueOf = Integer.valueOf(getHash());
        if (this.mIsRegionDecoderCreatingSet.putIfAbsent(valueOf, valueOf) != null) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "initTileTask duplicated " + valueOf);
            return;
        }
        SimpleThreadPool.getInstance().execute(new j((Object) this, (Object) view, (Serializable) valueOf, currentTimeMillis, 5));
    }

    public void invisibleTiles(int i2) {
        if (getTileMap() != null) {
            while (true) {
                int i7 = 0;
                for (Map.Entry<Integer, List<Tile>> value : getTileMap().entrySet()) {
                    for (Tile tile : (List) value.getValue()) {
                        int i8 = tile.sampleSize;
                        if (!(i8 == i2 || i8 == getFullImageSampleSize())) {
                            i7 = tile.sampleSize;
                            setTileInvisible(tile);
                        }
                    }
                    if (i7 > 0) {
                        Log.i(this.TAG, "invisibleTiles : " + i7);
                    }
                }
                return;
            }
        }
    }

    public boolean is0Degree() {
        if (getOrientation() == 0) {
            return true;
        }
        return false;
    }

    public boolean is180Degree() {
        if (getOrientation() == 180) {
            return true;
        }
        return false;
    }

    public boolean is270Degree() {
        if (getOrientation() == 270) {
            return true;
        }
        return false;
    }

    public boolean is90Degree() {
        if (getOrientation() == 90) {
            return true;
        }
        return false;
    }

    public boolean isAvailable(View view) {
        if (view.getContext() == null || !this.mSourceInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0037  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isBaseLayerReady() {
        /*
            r5 = this;
            java.util.Map<java.lang.Integer, java.util.List<com.samsung.android.gallery.widget.photoview.Tile>> r0 = r5.mTileMap
            r1 = 0
            if (r0 == 0) goto L_0x0048
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x000d:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0046
            java.lang.Object r2 = r0.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            int r4 = r5.mFullImageSampleSize
            if (r3 != r4) goto L_0x000d
            java.lang.Object r2 = r2.getValue()
            java.util.List r2 = (java.util.List) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x0031:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x000d
            java.lang.Object r3 = r2.next()
            com.samsung.android.gallery.widget.photoview.Tile r3 = (com.samsung.android.gallery.widget.photoview.Tile) r3
            boolean r4 = r3.loading
            if (r4 != 0) goto L_0x0045
            android.graphics.Bitmap r3 = r3.bitmap
            if (r3 != 0) goto L_0x0031
        L_0x0045:
            return r1
        L_0x0046:
            r5 = 1
            return r5
        L_0x0048:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.photoview.ImageProcessor.isBaseLayerReady():boolean");
    }

    public boolean isPathChanged(String str, BytesBuffer bytesBuffer) {
        return this.mSourceInfo.isChanged(str, bytesBuffer);
    }

    public boolean isPostProcessing() {
        return this.mSourceInfo.mPpp;
    }

    public boolean isPreviewMode() {
        if (this.mBitmap == null || isBaseLayerReady()) {
            return false;
        }
        return true;
    }

    public boolean isRunRegionDecoding() {
        if (this.mRegionDecoder != null) {
            return true;
        }
        return false;
    }

    public boolean isSameSource(MediaItem mediaItem) {
        return !this.mSourceInfo.isChanged(mediaItem.getPath(), (BytesBuffer) null);
    }

    public boolean isSourceChanged(MediaItem mediaItem, Bitmap bitmap) {
        if (!hasDifferentSize(false, mediaItem.getWidth(), mediaItem.getHeight()) && getOrientation() == mediaItem.getOrientation() && getFileSize() == mediaItem.getFileSize()) {
            return false;
        }
        return true;
    }

    public boolean isSourceReady() {
        return this.mSourceInfo.isSourceReady();
    }

    public boolean isSupportRegionDecoding() {
        return this.mSourceInfo.isSupportRegionDecoding();
    }

    public void loadTileCompatTask(AliveZoomCompat aliveZoomCompat, PhotoView photoView, AliveZoomCompat.TileCompat tileCompat, int i2) {
        AliveZoomScheduler.getInstance().execute(new AliveZoomLoadTileTask(photoView, photoView.mImageProcessor.mRegionDecoder, tileCompat).setExtra(aliveZoomCompat, i2, photoView.mImageProcessor.getOrientation()));
    }

    public void loadTileTask(Tile tile, PhotoView photoView, boolean z) {
        if (z && tile.sampleSize == this.mFullImageSampleSize && hasFullSizePreview()) {
            tile.bitmap = this.mBitmap;
            tile.canRecycle = false;
            if (!photoView.supportZoomCompat()) {
                tile.loading = false;
                StringCompat stringCompat = this.TAG;
                Log.v(stringCompat, "loadBaseTile :" + this.mFullImageSampleSize, tile);
                photoView.onTileLoaded(tile.sampleSize, tile);
                return;
            }
        }
        if (this.mTileTaskElapsed == 0) {
            this.mTileTaskElapsed = System.currentTimeMillis();
        }
        LoadTileTask loadTileTask = new LoadTileTask(photoView, this.mRegionDecoder, tile);
        loadTileTask.executeOnExecutor(this.mExecutor, new Void[0]);
        this.mTileTasks.add(loadTileTask);
    }

    public synchronized void onTilesInitialized(View view, ImageRegionDecoder imageRegionDecoder, int i2, int i7) {
        if (view.getContext() == null) {
            Log.w(this.TAG, "onTilesInitialized skip");
            return;
        }
        if (!isSourceReady() || !hasDifferentSize(false, i2, i7)) {
            StringCompat stringCompat = this.TAG;
            Log.d(stringCompat, "onTilesInitialized Size{" + i2 + "x" + i7 + "}");
        } else {
            StringCompat stringCompat2 = this.TAG;
            Log.w(stringCompat2, "onTilesInitialized wrong Size{" + i2 + "x" + i7 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getWidth(false) + "x" + getHeight(false) + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getFullImageSampleSize() + "}");
        }
        this.mRegionDecoder = imageRegionDecoder;
        resize(i2, i7);
        view.invalidate();
        view.requestLayout();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.function.Consumer, java.lang.Object] */
    /* renamed from: recycleRegionDecoder */
    public void lambda$close$9() {
        ImageRegionDecoder imageRegionDecoder = this.mRegionDecoder;
        if (imageRegionDecoder != null) {
            this.mRegionDecoder = null;
            this.mTileTasks.forEach(new Object());
            this.mTileTasks.clear();
            recycleAfterDone(imageRegionDecoder, this.TAG.getTag());
        }
    }

    public void recycleTileMap() {
        ThreadUtil.assertUiThread("recycleTileMap");
        Map<Integer, List<Tile>> map = this.mTileMap;
        if (map != null) {
            map.forEach(new h(6, this));
            this.mTileMap = null;
        }
    }

    public void refreshRequiredTiles(PhotoView photoView, boolean z, PhotoViewPositionControl photoViewPositionControl) {
        String str;
        boolean z3;
        if (this.mRegionDecoder != null && this.mTileMap != null) {
            int sampleSize = photoView.getSampleSize();
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry<Integer, List<Tile>> value : this.mTileMap.entrySet()) {
                List<Tile> list = (List) value.getValue();
                for (Tile tile : list) {
                    boolean z7 = tile.visible;
                    int i2 = tile.sampleSize;
                    if (i2 == sampleSize) {
                        if (photoViewPositionControl.tileVisible(tile, photoView.getViewWidth(), photoView.getViewHeight())) {
                            tile.visible = true;
                            if (!tile.loading && tile.bitmap == null && z) {
                                if (list.size() == 1) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                loadTileTask(tile, photoView, z3);
                            }
                        } else if (tile.sampleSize != getFullImageSampleSize()) {
                            setTileInvisible(tile);
                        }
                    } else if (i2 == getFullImageSampleSize()) {
                        tile.visible = true;
                    }
                    if (tile.visible != z7) {
                        sb2.append("{s");
                        sb2.append(tile.sampleSize);
                        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                        if (tile.visible) {
                            str = "v";
                        } else {
                            str = "iv";
                        }
                        sb2.append(str);
                        sb2.append(tile.sRect.toShortString());
                        sb2.append("}");
                    }
                }
            }
            if (sb2.length() > 1) {
                Log.d(this.TAG, "refreshRequiredTiles : " + sb2);
            }
        }
    }

    public void removeTileTask(LoadTileTask loadTileTask) {
        this.mTileTasks.remove(loadTileTask);
    }

    public void resize(int i2, int i7) {
        this.mSourceInfo.setSize(i2, i7);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mBitmapGenerationId = bitmap.getGenerationId();
        this.mBitmapOptions = (BitmapOptions) SeApiCompat.getBitmapTag(bitmap, "options", null);
    }

    public void setBorder(Tile tile, int i2, int i7, int i8, int i10) {
        int i11;
        int i12;
        int i13;
        int i14 = tile.sampleSize * 4;
        if (i2 == 0) {
            i11 = 0;
        } else {
            i11 = i14;
        }
        if (i7 == 0) {
            i12 = 0;
        } else {
            i12 = i14;
        }
        if (i2 == i8 - 1) {
            i13 = 0;
        } else {
            i13 = i14;
        }
        if (i7 == i10 - 1) {
            i14 = 0;
        }
        if (is0Degree()) {
            tile.borders.set(i11, i12, i13, i14);
        } else if (is90Degree()) {
            tile.borders.set(i12, i13, i14, i11);
        } else if (is180Degree()) {
            tile.borders.set(i13, i14, i11, i12);
        } else if (is270Degree()) {
            tile.borders.set(i14, i11, i12, i13);
        }
    }

    public void setSourceInfo(MediaItem mediaItem, Bitmap bitmap, BytesBuffer bytesBuffer) {
        this.mSourceInfo.setSourceInfo(mediaItem, bitmap, bytesBuffer);
    }

    public void setTag(String str) {
        this.TAG.setTag(str);
    }

    public void setTileInvisible(Tile tile) {
        tile.visible = false;
        Bitmap bitmap = tile.bitmap;
        tile.bitmap = null;
        if (!(!tile.canRecycle || bitmap == this.mBitmap || bitmap == null)) {
            BitmapUtils.putBitmap(bitmap);
        }
        tile.canRecycle = true;
    }

    public void tryRegionDecoding(View view) {
        if (!isAvailable(view) || !isSupportRegionDecoding()) {
            Log.w((CharSequence) this.TAG, "tryRegionDecoding fail", Logger.v(view, Boolean.valueOf(this.mSourceInfo.isAvailable()), Boolean.valueOf(isSupportRegionDecoding())));
            return;
        }
        BitmapOptions bitmapOptions = this.mBitmapOptions;
        if (bitmapOptions == null) {
            new SerialExecutor().runOnBg(new c(4, this)).runOnUi(new N3.c(3, this, view)).exec();
        } else {
            tryRegionDecoding(view, bitmapOptions);
        }
    }

    public boolean hasMissingTile(int i2, PhotoViewPositionControl photoViewPositionControl, int i7, int i8) {
        Map<Integer, List<Tile>> map = this.mTileMap;
        if (map == null) {
            Log.w(this.TAG, "hasMissingTile : mTileMap is null");
            return true;
        }
        List list = map.get(Integer.valueOf(i2));
        if (list != null) {
            return list.stream().anyMatch(new m(photoViewPositionControl, i7, i8));
        }
        return false;
    }

    private void tryRegionDecoding(View view, BitmapOptions bitmapOptions) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap == null || bitmapOptions.outWidth > bitmap.getWidth() || bitmapOptions.outHeight > this.mBitmap.getHeight()) {
            this.mFullImageSampleSize = BitmapOptions.computeInSampleSizeLowerBound(bitmapOptions.outWidth, bitmapOptions.outHeight, BitmapSizeHolder.size());
        } else {
            this.mFullImageSampleSize = 1;
        }
        if (this.mFullImageSampleSize == 1) {
            Log.d(this.TAG, "tryRegionDecoding skip {1}");
            return;
        }
        if (isSourceReady() && hasDifferentSize(false, bitmapOptions.outWidth, bitmapOptions.outHeight)) {
            StringCompat stringCompat = this.TAG;
            Log.w(stringCompat, "tryRegionDecoding wrong size {" + bitmapOptions.outWidth + "x" + bitmapOptions.outHeight + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mFullImageSampleSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getWidth(false) + "x" + getHeight(false) + "}");
            resize(bitmapOptions.outWidth, bitmapOptions.outHeight);
        }
        initTileTask(view);
    }
}
