package com.samsung.android.gallery.widget.photoview;

import L5.b;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import c0.C0086a;
import com.samsung.android.gallery.module.aizoom.AliveZoomFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AliveZoomCompat implements ZoomCompat {
    private static final int PIXEL_PADDING;
    private boolean mAliveZoomEnabled = true;
    private final float[] mDstArray = new float[8];
    private final Matrix mMatrix = new Matrix();
    private WeakReference<PhotoView> mPhotoViewRef;
    private boolean mSaReport;
    private final BiConsumer<Boolean, Integer> mSceneDetectCallback = new a(this);
    private int mSceneType = -1;
    private int mSourceHash;
    private final float[] mSrcArray = new float[8];
    private boolean mSupportAiZoom;
    private Map<Integer, List<TileCompat>> mTileCompatMap;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TileCompat extends Tile {
        Rect fileRect;
        Rect inputRect;
        Rect paddingRect;

        public String toString() {
            char c5;
            char c6;
            char c8;
            StringBuilder sb2 = new StringBuilder("Tile{");
            if (this.visible) {
                c5 = 'V';
            } else {
                c5 = 'v';
            }
            sb2.append(c5);
            if (this.loading) {
                c6 = 'L';
            } else {
                c6 = 'l';
            }
            sb2.append(c6);
            if (this.bitmap != null) {
                c8 = 'B';
            } else {
                c8 = 'b';
            }
            sb2.append(c8);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.sampleSize);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.fileRect.left);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            return C0086a.l(sb2, this.fileRect.top, "}");
        }
    }

    static {
        int i2;
        if (AliveZoomFactory.SKIP_ALIVE_ZOOM_OUTPUT) {
            i2 = 0;
        } else {
            i2 = 16;
        }
        PIXEL_PADDING = i2;
    }

    private int getKeyForRequiredTiles(PhotoView photoView) {
        float scale = photoView.mPosCtrl.getScale();
        if (Math.floor((double) scale) >= 2.0d) {
            return -((int) scale);
        }
        return Math.min(photoView.mImageProcessor.getFullImageSampleSize(), photoView.calculateInSampleSize(scale));
    }

    private Rect getScaledRect(Rect rect, int i2) {
        if (i2 != 1) {
            rect.left *= i2;
            rect.top *= i2;
            rect.right *= i2;
            rect.bottom *= i2;
        }
        return rect;
    }

    private Rect inset(Rect rect, Rect rect2) {
        Rect rect3 = new Rect(rect);
        rect3.left -= rect2.left;
        rect3.top -= rect2.top;
        rect3.right += rect2.right;
        rect3.bottom += rect2.bottom;
        return rect3;
    }

    private boolean isTileVisible(PhotoView photoView, TileCompat tileCompat) {
        PhotoViewPositionControl photoViewPositionControl = photoView.mPosCtrl;
        float viewToSourceX = photoViewPositionControl.viewToSourceX(0.0f);
        float viewToSourceX2 = photoViewPositionControl.viewToSourceX((float) photoView.getWidth());
        float viewToSourceY = photoViewPositionControl.viewToSourceY(0.0f);
        float viewToSourceY2 = photoViewPositionControl.viewToSourceY((float) photoView.getHeight());
        Rect rect = tileCompat.inputRect;
        if (viewToSourceX > ((float) rect.right) || ((float) rect.left) > viewToSourceX2 || viewToSourceY > ((float) rect.bottom) || ((float) rect.top) > viewToSourceY2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSceneDetected$0(PhotoView photoView) {
        refreshRequiredTiles(photoView, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onSceneDetected$1(PhotoView photoView) {
        ThreadUtil.postOnUiThread(new g(0, this, photoView));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshRequiredTiles$5(Integer num, int i2, PhotoView photoView, boolean z, TileCompat tileCompat) {
        if (num.intValue() != i2 || !isTileVisible(photoView, tileCompat)) {
            photoView.mImageProcessor.setTileInvisible(tileCompat);
            return;
        }
        tileCompat.visible = true;
        if (!tileCompat.loading && tileCompat.bitmap == null && z) {
            loadTileCompatTask(photoView, tileCompat, -num.intValue());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$refreshRequiredTiles$6(int i2, PhotoView photoView, boolean z, Integer num, List list) {
        list.forEach(new e(this, num, i2, photoView, z));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderTileOnDraw$3(PhotoView photoView, Canvas canvas, TileCompat tileCompat) {
        Bitmap bitmap;
        photoView.mPosCtrl.sourceToViewRect(tileCompat.inputRect, tileCompat.vRect);
        if (!tileCompat.loading && (bitmap = tileCompat.bitmap) != null && !bitmap.isRecycled()) {
            updateMatrixForTile(tileCompat, photoView.mImageProcessor.getOrientation());
            canvas.drawBitmap(tileCompat.bitmap, this.mMatrix, photoView.mConfig.mBitmapPaint);
        }
        DebugDelegate debugDelegate = photoView.mDebugDelegate;
        if (debugDelegate != null) {
            debugDelegate.renderTileOnDraw(canvas, tileCompat);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renderTileOnDraw$4(int i2, PhotoView photoView, Canvas canvas, Integer num, List list) {
        if (num.intValue() == i2) {
            list.forEach(new d(this, photoView, canvas));
        }
    }

    private void loadTileCompatTask(PhotoView photoView, TileCompat tileCompat, int i2) {
        photoView.mImageProcessor.loadTileCompatTask(this, photoView, tileCompat, i2);
    }

    /* access modifiers changed from: private */
    public void onSceneDetected(boolean z, int i2) {
        WeakReference<PhotoView> weakReference;
        this.mSceneType = i2;
        if (!(i2 == -1 || (weakReference = this.mPhotoViewRef) == null)) {
            Optional.ofNullable(weakReference.get()).ifPresent(new f(0, this));
        }
        if (z) {
            this.mPhotoViewRef = null;
        }
    }

    private void updateMatrixForTile(TileCompat tileCompat, int i2) {
        this.mMatrix.reset();
        PhotoViewDelegate.setMatrixArray(this.mSrcArray, 0.0f, 0.0f, (float) tileCompat.bitmap.getWidth(), 0.0f, (float) tileCompat.bitmap.getWidth(), (float) tileCompat.bitmap.getHeight(), 0.0f, (float) tileCompat.bitmap.getHeight());
        PhotoViewDelegate.updateDstArrayWithRotation(this.mDstArray, tileCompat, i2);
        this.mMatrix.setPolyToPoly(this.mSrcArray, 0, this.mDstArray, 0, 4);
    }

    public int getSceneType() {
        if (support()) {
            return this.mSceneType;
        }
        return -1;
    }

    /* JADX WARNING: type inference failed for: r16v3, types: [int] */
    /* JADX WARNING: type inference failed for: r16v4, types: [int] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initTileMap(com.samsung.android.gallery.widget.photoview.PhotoView r22, android.graphics.Point r23) {
        /*
            r21 = this;
            r0 = r21
            r1 = r23
            boolean r2 = r0.support()
            if (r2 != 0) goto L_0x000c
            goto L_0x014b
        L_0x000c:
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            r0.mTileCompatMap = r2
            int r2 = r22.getSourceWidth()
            int r3 = r22.getSourceHeight()
            r4 = 1
            r5 = 2
            r6 = r4
            r7 = r6
        L_0x001f:
            r8 = 5
            if (r5 > r8) goto L_0x014b
            int r8 = r2 / r6
            int r9 = r3 / r7
            int r10 = r8 * r5
            int r11 = r9 * r5
        L_0x002a:
            int r12 = r10 + r6
            int r12 = r12 + r4
            int r13 = r1.x
            if (r12 > r13) goto L_0x0042
            double r12 = (double) r10
            int r10 = r22.getWidth()
            double r14 = (double) r10
            r16 = 4600877379321698714(0x3fd999999999999a, double:0.4)
            double r14 = r14 * r16
            int r10 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r10 <= 0) goto L_0x004a
        L_0x0042:
            r18 = r2
            r19 = r3
            r20 = r6
            goto L_0x013c
        L_0x004a:
            int r10 = r11 + r7
            int r10 = r10 + r4
            int r12 = r1.y
            if (r10 > r12) goto L_0x005d
            double r10 = (double) r11
            int r12 = r22.getHeight()
            double r12 = (double) r12
            double r12 = r12 * r16
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 <= 0) goto L_0x0065
        L_0x005d:
            r18 = r2
            r19 = r3
            r20 = r6
            goto L_0x012b
        L_0x0065:
            java.util.ArrayList r10 = new java.util.ArrayList
            int r11 = r6 * r7
            r10.<init>(r11)
            r11 = 0
            r12 = r11
        L_0x006e:
            if (r12 >= r6) goto L_0x0112
            r13 = r11
        L_0x0071:
            if (r13 >= r7) goto L_0x0102
            com.samsung.android.gallery.widget.photoview.AliveZoomCompat$TileCompat r14 = new com.samsung.android.gallery.widget.photoview.AliveZoomCompat$TileCompat
            r14.<init>()
            r14.sampleSize = r4
            r14.visible = r11
            android.graphics.Rect r15 = new android.graphics.Rect
            if (r12 != 0) goto L_0x0082
            r4 = r11
            goto L_0x0086
        L_0x0082:
            int r16 = PIXEL_PADDING
            r4 = r16
        L_0x0086:
            if (r13 != 0) goto L_0x0089
            goto L_0x008d
        L_0x0089:
            int r16 = PIXEL_PADDING
            r11 = r16
        L_0x008d:
            int r1 = r6 + -1
            if (r12 != r1) goto L_0x0095
            r18 = r2
            r2 = 0
            goto L_0x009b
        L_0x0095:
            int r16 = PIXEL_PADDING
            r18 = r2
            r2 = r16
        L_0x009b:
            r19 = r3
            int r3 = r7 + -1
            if (r13 != r3) goto L_0x00a5
            r20 = r6
            r6 = 0
            goto L_0x00ab
        L_0x00a5:
            int r16 = PIXEL_PADDING
            r20 = r6
            r6 = r16
        L_0x00ab:
            r15.<init>(r4, r11, r2, r6)
            r14.paddingRect = r15
            android.graphics.Rect r2 = new android.graphics.Rect
            int r4 = r12 * r8
            int r6 = r13 * r9
            if (r12 != r1) goto L_0x00bb
            r1 = r18
            goto L_0x00be
        L_0x00bb:
            int r1 = r12 + 1
            int r1 = r1 * r8
        L_0x00be:
            if (r13 != r3) goto L_0x00c3
            r3 = r19
            goto L_0x00c6
        L_0x00c3:
            int r3 = r13 + 1
            int r3 = r3 * r9
        L_0x00c6:
            r2.<init>(r4, r6, r1, r3)
            r14.inputRect = r2
            android.graphics.Rect r1 = r14.paddingRect
            android.graphics.Rect r1 = r0.inset(r2, r1)
            r14.fileRect = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            android.graphics.Rect r2 = r14.inputRect
            r1.<init>(r2)
            android.graphics.Rect r1 = r0.getScaledRect(r1, r5)
            r14.sRect = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            android.graphics.Rect r2 = r14.inputRect
            r1.<init>(r2)
            r14.fileSRect = r1
            android.graphics.Rect r1 = new android.graphics.Rect
            r2 = 0
            r1.<init>(r2, r2, r2, r2)
            r14.vRect = r1
            r10.add(r14)
            int r13 = r13 + 1
            r1 = r23
            r11 = r2
            r2 = r18
            r3 = r19
            r6 = r20
            r4 = 1
            goto L_0x0071
        L_0x0102:
            r18 = r2
            r19 = r3
            r20 = r6
            r2 = r11
            int r12 = r12 + 1
            r1 = r23
            r2 = r18
            r4 = 1
            goto L_0x006e
        L_0x0112:
            r18 = r2
            r19 = r3
            r20 = r6
            java.util.Map<java.lang.Integer, java.util.List<com.samsung.android.gallery.widget.photoview.AliveZoomCompat$TileCompat>> r1 = r0.mTileCompatMap
            int r2 = -r5
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r1.put(r2, r10)
            int r5 = r5 + 1
            r1 = r23
            r2 = r18
            r4 = 1
            goto L_0x001f
        L_0x012b:
            int r7 = r7 + 1
            int r9 = r19 / r7
            int r11 = r9 * r5
            r1 = r23
            r2 = r18
            r3 = r19
            r6 = r20
            r4 = 1
            goto L_0x004a
        L_0x013c:
            int r6 = r20 + 1
            int r8 = r18 / r6
            int r10 = r8 * r5
            r1 = r23
            r2 = r18
            r3 = r19
            r4 = 1
            goto L_0x002a
        L_0x014b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.photoview.AliveZoomCompat.initTileMap(com.samsung.android.gallery.widget.photoview.PhotoView, android.graphics.Point):void");
    }

    public void recycleTileMap(PhotoView photoView) {
        Map<Integer, List<TileCompat>> map;
        if (support() && (map = this.mTileCompatMap) != null) {
            map.values().stream().flatMap(new b(25)).forEach(new f(1, photoView));
            this.mTileCompatMap = null;
        }
    }

    public void refreshRequiredTiles(PhotoView photoView, boolean z) {
        if (support() && photoView != null && photoView.mImageProcessor.isRunRegionDecoding() && this.mTileCompatMap != null && this.mSceneType != -1) {
            int keyForRequiredTiles = getKeyForRequiredTiles(photoView);
            this.mTileCompatMap.forEach(new c(this, keyForRequiredTiles, photoView, z));
            if (!this.mSaReport && (-keyForRequiredTiles) >= 2) {
                this.mSaReport = true;
                AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString(), AnalyticsEventId.EVENT_ALIVE_ZOOM_USED.toString());
            }
        }
    }

    public void release() {
        if (support()) {
            AliveZoomScheduler.getInstance().release(this.mSourceHash);
        }
    }

    public void renderTileOnDraw(PhotoView photoView, Canvas canvas) {
        if (support() && this.mTileCompatMap != null) {
            this.mTileCompatMap.forEach(new b(this, getKeyForRequiredTiles(photoView), photoView, canvas));
        }
    }

    public void reset(boolean z) {
        if (z) {
            this.mSceneType = -1;
            if (support()) {
                this.mPhotoViewRef = null;
                AliveZoomScheduler.getInstance().release(this.mSourceHash);
            }
        }
    }

    public void setAliveZoomEnabled(boolean z) {
        this.mAliveZoomEnabled = z;
    }

    public void setImage(PhotoView photoView, MediaItem mediaItem, Bitmap bitmap) {
        boolean z;
        int height = mediaItem.getHeight() * mediaItem.getWidth();
        if (height <= 0 || height >= 4194304 || mediaItem.getFileSize() >= 52428800) {
            z = false;
        } else {
            z = true;
        }
        this.mSupportAiZoom = z;
    }

    public boolean support() {
        if (!this.mAliveZoomEnabled || !this.mSupportAiZoom) {
            return false;
        }
        return true;
    }

    public void tryRegionDecoding(PhotoView photoView) {
        if (support()) {
            if (photoView.mImageProcessor.getFullImageSampleSize() == 1) {
                photoView.mImageProcessor.initTileTask(photoView);
            }
            Bitmap bitmap = photoView.mImageProcessor.getBitmap();
            if (this.mSceneType == -1 && bitmap != null && !bitmap.isRecycled()) {
                this.mPhotoViewRef = new WeakReference<>(photoView);
                this.mSourceHash = photoView.getSourceHash();
                AliveZoomScheduler.getInstance().detectScene(this.mSourceHash, bitmap, this.mSceneDetectCallback);
            }
        }
    }
}
