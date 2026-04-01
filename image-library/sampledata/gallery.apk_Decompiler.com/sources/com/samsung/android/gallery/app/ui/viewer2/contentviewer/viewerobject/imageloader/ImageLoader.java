package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import A4.C0372g;
import A4.M;
import D7.a;
import D7.f;
import android.graphics.Bitmap;
import android.util.Size;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeTickLog;
import com.samsung.android.gallery.widget.utils.ResourceUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageLoader extends AbsImageLoader {
    private boolean isScreenSizeImage(MediaItem mediaItem, Size size) {
        if (mediaItem.isImage() && Math.max(mediaItem.getWidth(), mediaItem.getHeight()) == Math.max(size.getHeight(), size.getWidth()) && Math.min(mediaItem.getWidth(), mediaItem.getHeight()) == Math.min(size.getHeight(), size.getWidth())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        updatePhotoBitmap(this.mModel.getMediaItem(), this.mModel.getMediaItem(), this.mModel.getPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        requestDecode(this.mModel.getMediaItem(), this.mModel.getPosition());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$processLoadedBitmap$4() {
        this.mActionInvoker.invoke(ViewerAction.INVALIDATE_TOOLBAR_MENU, new Object[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFullSizeBitmap$2(Bitmap bitmap, int i2, TimeTickLog timeTickLog, MediaItem mediaItem) {
        if (this.mModel.isViewConfirmed() && bitmap != null && matchRequested(i2)) {
            StringCompat stringCompat = this.TAG;
            Log.i(stringCompat, "update full size" + Logger.vt(bitmap, timeTickLog));
            processLoadedBitmap(mediaItem, bitmap, false);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateFullSizeBitmap$3(int i2, MediaItem mediaItem, int i7) {
        if (this.mModel.isViewConfirmed() && matchRequested(i2)) {
            TimeTickLog timeTickLog = new TimeTickLog();
            Bitmap decodedBitmap = BitmapUtils.getDecodedBitmap(mediaItem.getPath(), BitmapOptionsFactory.parse((FileItemInterface) mediaItem, true).adjustInSampleSize(i7));
            timeTickLog.tick();
            this.mThread.runOnUiThread(new a(this, decodedBitmap, i2, timeTickLog, mediaItem));
        }
    }

    private boolean matchRequested(int i2) {
        boolean z;
        MediaItem mediaItem = this.mModel.getMediaItem();
        int i7 = 0;
        if (mediaItem == null || mediaItem.getSimpleHashCode() != i2) {
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            StringCompat stringCompat = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (mediaItem != null) {
                i7 = mediaItem.getSimpleHashCode();
            }
            Log.w((CharSequence) stringCompat, "update full size skip due to hash mismatch", valueOf, Integer.valueOf(i7));
        }
        return z;
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.UPDATE_PHOTO_BITMAP, new f(this, 0));
        this.mActionInvoker.add(ViewerAction.GROUP_CURRENT_ITEM_CHANGED, new f(this, 1));
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        boolean z;
        boolean z3;
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        MediaItem mediaItem3 = this.mModel.getMediaItem();
        if (SdkConfig.atLeast(SdkConfig.GED.T)) {
            z = MediaItemUtil.equalsBitmap(mediaItem3, mediaItem);
        } else {
            z = MediaItemUtil.equalsPpp(mediaItem3, mediaItem);
        }
        if (!z) {
            updatePhotoBitmap(this.mModel.getMediaItem(), mediaItem, i2);
            return;
        }
        MediaItem mediaItem4 = this.mLastRequestedItem;
        if (mediaItem4 == null || !mediaItem4.isPostProcessing() || mediaItem.isPostProcessing()) {
            z3 = false;
        } else {
            z3 = true;
        }
        if (z3) {
            Log.d(this.TAG, "needsUpdateBitmap pppDone", Boolean.valueOf(z3), this.mLastRequestedItem);
            updatePhotoBitmap(this.mLastRequestedItem, mediaItem, i2);
        } else if (!MediaItemUtil.equalsSimple(this.mLastRequestedItem, mediaItem)) {
            Log.d(this.TAG, "needsUpdateBitmap path updated", this.mLastRequestedItem);
            updatePhotoBitmap(this.mLastRequestedItem, mediaItem, i2);
        }
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateFullSizeBitmap();
    }

    public void processLoadedBitmap(MediaItem mediaItem, Bitmap bitmap, boolean z) {
        if (bitmap == null && mediaItem.isBroken()) {
            bitmap = ThumbnailLoader.getInstance().getReplacedThumbnail(this.mModel.getContext(), ResourceUtil.getBrokenDrawable(mediaItem), ResourceUtil.getBrokenBgColor(mediaItem));
            this.mThread.runOnUiThread(new C0372g(29, this));
            Log.d(this.TAG, "replace null to broken");
        }
        if (bitmap != null) {
            super.processLoadedBitmap(mediaItem, bitmap, z);
        }
    }

    public void updateFullSizeBitmap() {
        Size size = DeviceInfo.getDefaultDisplay().getSize();
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null && isScreenSizeImage(mediaItem, size)) {
            Bitmap bitmap = this.mModel.getBitmap();
            int max = Math.max(size.getHeight(), size.getWidth());
            if (bitmap == null || Math.max(bitmap.getHeight(), bitmap.getWidth()) < max) {
                SimpleThreadPool.getInstance().execute(new M((Object) this, mediaItem.getSimpleHashCode(), (Object) mediaItem, max, 2));
            }
        }
    }

    public void updatePhotoBitmap(MediaItem mediaItem, MediaItem mediaItem2, int i2) {
        eraseDecodedBitmap(mediaItem, this.mModel.getPosition(), false);
        requestDecode(mediaItem2, i2);
    }
}
