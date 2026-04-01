package com.samsung.android.gallery.widget.photoview;

import A.a;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.ImageRegionDecoder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import java.lang.ref.WeakReference;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LoadTileTask extends AsyncTask<Void, Void, Bitmap> {
    protected final String TAG = getClass().getSimpleName();
    PhotoViewConfig mConfig;
    private final WeakReference<ImageRegionDecoder> mRegionDecoderRef;
    private final WeakReference<Tile> mTileRef;
    private final WeakReference<PhotoView> mViewRef;

    public LoadTileTask(PhotoView photoView, ImageRegionDecoder imageRegionDecoder, Tile tile) {
        if (SuperHdrConfig.SUPPORT) {
            this.mConfig = photoView.mConfig;
        }
        this.mViewRef = new WeakReference<>(photoView);
        this.mRegionDecoderRef = new WeakReference<>(imageRegionDecoder);
        this.mTileRef = new WeakReference<>(tile);
        tile.loading = true;
    }

    private void canceled(Tile tile) {
        if (tile != null) {
            tile.loading = false;
            tile.visible = false;
        }
    }

    private ImageRegionDecoder getRegionDecoder() {
        return this.mRegionDecoderRef.get();
    }

    private boolean tryToDecodeRegion(PhotoView photoView, ImageRegionDecoder imageRegionDecoder, Tile tile) {
        if (photoView == null || !photoView.isViewAndBitmapReady() || tile == null || !tile.visible || imageRegionDecoder == null || imageRegionDecoder.isRecycled()) {
            return false;
        }
        return true;
    }

    public Bitmap decodeRegion(Tile tile) {
        String str;
        StringBuilder sb2;
        Semaphore decoderLock;
        boolean z;
        if (tile == null) {
            Log.w(this.TAG, "decodeRegion failed. null tile");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            PhotoView photoView = getPhotoView();
            ImageRegionDecoder regionDecoder = getRegionDecoder();
            decoderLock = photoView.getDecoderLock();
            boolean z3 = false;
            if (decoderLock.tryAcquire(10, TimeUnit.SECONDS)) {
                if (isCancelled() || !tryToDecodeRegion(photoView, regionDecoder, tile)) {
                    canceled(tile);
                } else {
                    fileSRect(photoView, tile);
                    BitmapOptions bitmapOptions = new BitmapOptions();
                    bitmapOptions.inSampleSize = tile.sampleSize;
                    if (SuperHdrConfig.SUPPORT) {
                        if (!this.mConfig.mPhotoHdrSupported || !SuperHdrConfig.isEnabled()) {
                            z = false;
                        } else {
                            z = true;
                        }
                        if (z && this.mConfig.mPhotoHdrCandidate) {
                            z3 = true;
                        }
                        bitmapOptions.withHdr(z, z3);
                    }
                    if (!isCancelled()) {
                        Bitmap decodeRegion = regionDecoder.decodeRegion(tile.fileSRect, bitmapOptions);
                        decoderLock.release();
                        if (tile.loading) {
                            String str2 = this.TAG;
                            StringBuilder sb3 = new StringBuilder("decodeRegion ");
                            sb3.append(tile);
                            sb3.append(" +");
                            a.x(sb3, currentTimeMillis, str2);
                        }
                        return decodeRegion;
                    }
                    canceled(tile);
                }
                decoderLock.release();
            } else {
                Log.w((CharSequence) this.TAG, "decode region fail", tile);
                tile.loading = false;
            }
            if (tile.loading) {
                str = this.TAG;
                sb2 = new StringBuilder("decodeRegion ");
                sb2.append(tile);
                sb2.append(" +");
                a.x(sb2, currentTimeMillis, str);
            }
        } catch (Exception e) {
            try {
                e.printStackTrace();
                Log.e(this.TAG, "decodeRegion failed. e=" + e);
                if (tile.loading) {
                    str = this.TAG;
                    sb2 = new StringBuilder("decodeRegion ");
                }
            } catch (Throwable th) {
                if (tile.loading) {
                    String str3 = this.TAG;
                    StringBuilder sb4 = new StringBuilder("decodeRegion ");
                    sb4.append(tile);
                    sb4.append(" +");
                    a.x(sb4, currentTimeMillis, str3);
                }
                throw th;
            }
        } catch (Throwable th2) {
            decoderLock.release();
            throw th2;
        }
        return null;
    }

    public void fileSRect(PhotoView photoView, Tile tile) {
        photoView.mImageProcessor.fileSRect(tile.sRect, tile.fileSRect);
        Rect rect = tile.fileSRect;
        int i2 = rect.left;
        Rect rect2 = tile.borders;
        rect.set(i2 - rect2.left, rect.top - rect2.top, rect.right + rect2.right, rect.bottom + rect2.bottom);
    }

    public PhotoView getPhotoView() {
        return this.mViewRef.get();
    }

    public Tile getTile() {
        return this.mTileRef.get();
    }

    public void verifyTiledBitmap(Bitmap bitmap, Tile tile) {
        int width = tile.fileSRect.width() / tile.sampleSize;
        int height = tile.fileSRect.height() / tile.sampleSize;
        if (bitmap.getWidth() != width || bitmap.getHeight() != height) {
            Log.e(this.TAG, "onPostExecute() : got wrong size bitmap " + bitmap.getWidth() + "x" + bitmap.getHeight() + " vs " + width + "x" + height + ArcCommonLog.TAG_COMMA + tile);
        }
    }

    public Bitmap doInBackground(Void... voidArr) {
        return decodeRegion(getTile());
    }

    public void onPostExecute(Bitmap bitmap) {
        Tile tile = getTile();
        if (isCancelled()) {
            canceled(tile);
            return;
        }
        PhotoView photoView = getPhotoView();
        if (photoView != null && tile != null && bitmap != null) {
            tile.bitmap = bitmap;
            verifyTiledBitmap(bitmap, tile);
            tile.loading = false;
            photoView.onTileLoaded(tile.sampleSize, tile);
        } else if (tile != null) {
            tile.loading = false;
        }
        if (photoView != null) {
            photoView.onTileTaskDone(this);
        }
    }
}
