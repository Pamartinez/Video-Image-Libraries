package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.imageloader;

import D7.a;
import D7.b;
import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.SubscriberListenerInfo;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ObjectDictionary;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsImageLoader extends ViewerObject implements FragmentLifeCycle {
    private SubscriberListenerInfo mImageSubscriber;
    protected MediaItem mLastRequestedItem;
    private int mRequestDecodeDelay = 150;

    private boolean checkSameBitmap(MediaItem mediaItem, Bitmap bitmap) {
        if (this.mModel.getBitmap() != bitmap || !MediaItemUtil.equalsSimple(mediaItem, this.mModel.getMediaItem())) {
            return false;
        }
        return true;
    }

    private boolean isRecyclableBitmapItem(MediaItem mediaItem) {
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        if (mediaItem == null || mediaItem.isGroupShot() || LocationKey.isStories(locationKey) || LocationKey.isMapCluster(locationKey) || LocationKey.isMapFiltered(locationKey)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestDecode$1(Blackboard blackboard, String str, MediaItem mediaItem, int i2) {
        Bitmap bitmap = (Bitmap) blackboard.read(str);
        if (bitmap == null) {
            unsubscribe(mediaItem);
            subscribe(str, mediaItem, i2);
            requestOriginalBitmap(mediaItem, i2);
            return;
        }
        Log.d(this.TAG, "request decode skip#2", bitmap);
        processLoadedBitmap(mediaItem, bitmap, false);
    }

    public void eraseDecodedBitmap(MediaItem mediaItem, int i2, boolean z) {
        String decodedImageKey = getDecodedImageKey(mediaItem, i2);
        if (z && isRecyclableBitmapItem(mediaItem)) {
            Bitmap bitmap = (Bitmap) getBlackboard().read(decodedImageKey);
            if (bitmap == null && MediaItemUtil.equals(this.mModel.getRepresentativeItem(), mediaItem)) {
                bitmap = this.mModel.removeBitmap();
                Log.d(this.TAG, "eraseDecodedBitmap from model");
            }
            if (bitmap != null && ObjectDictionary.decreaseRefCounter(bitmap) == 0 && !mediaItem.isPostProcessing()) {
                BitmapUtils.putBitmap(bitmap);
            }
        }
        BlackboardUtils.cancelAndEraseViewerBitmap(getBlackboard(), decodedImageKey);
    }

    public String getDecodedImageKey(MediaItem mediaItem, int i2) {
        if (mediaItem == null) {
            return "data://bitmap/viewer/dummy";
        }
        return ArgumentsUtil.appendArgs(MediaItemUtil.getViewerBitmapKey(mediaItem, i2), "verify_subscriber", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        if (i7 == i2 && i2 == 0 && mediaItem2.isPostProcessing() && this.mModel.getStateHelper().isQuickView() && this.mModel.getStateHelper().isFromCamera()) {
            Log.w(this.TAG, "ppp invalidate");
            this.mRequestDecodeDelay = 2;
        }
    }

    public boolean isValidItem(MediaItem mediaItem) {
        return MediaItemUtil.equalsBitmap(this.mModel.getMediaItem(), mediaItem);
    }

    public boolean needsLastRequestedItem() {
        return true;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        requestBitmap("onBind");
    }

    /* renamed from: onImageLoaded */
    public void lambda$subscribe$2(MediaItem mediaItem, int i2, Object obj, Bundle bundle) {
        String str;
        StringCompat stringCompat = this.TAG;
        Integer valueOf = Integer.valueOf(i2);
        if (obj instanceof Bitmap) {
            str = Logger.toSimpleString((Bitmap) obj);
        } else {
            str = Logger.getSimpleName(obj);
        }
        Log.d(stringCompat, "onImageLoaded", valueOf, str);
        if (isValidItem(mediaItem)) {
            processLoadedBitmap(mediaItem, (Bitmap) obj, false);
        } else {
            eraseDecodedBitmap(mediaItem, i2, false);
        }
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mRequestDecodeDelay = 150;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mImageSubscriber = null;
        this.mThread.cancelBgThread((Object) "requestDecode");
        ContentModel contentModel = this.mModel;
        if (contentModel != null) {
            List<MediaItem> subItems = contentModel.getSubItems();
            if (subItems.size() > 0) {
                for (MediaItem eraseDecodedBitmap : subItems) {
                    eraseDecodedBitmap(eraseDecodedBitmap, this.mModel.getPosition(), true);
                }
            } else {
                MediaItem mediaItem = this.mLastRequestedItem;
                if (mediaItem != null) {
                    eraseDecodedBitmap(mediaItem, this.mModel.getPosition(), true);
                }
            }
        }
        this.mLastRequestedItem = null;
        this.mImageSubscriber = null;
    }

    public void processLoadedBitmap(MediaItem mediaItem, Bitmap bitmap, boolean z) {
        if (bitmap.isRecycled()) {
            Log.e(this.TAG, "processLoadedBitmap, bitmap is Recycled");
            return;
        }
        ObjectDictionary.increaseRefCounter(bitmap);
        processLoadedBitmapInternal(bitmap, mediaItem, z);
    }

    public void processLoadedBitmapInternal(Bitmap bitmap, MediaItem mediaItem, boolean z) {
        if (this.mModel.setBitmap(bitmap, mediaItem)) {
            this.mActionInvoker.invoke(ViewerAction.BITMAP_LOADED, bitmap, mediaItem, Boolean.valueOf(z));
        }
    }

    public void requestBitmap(String str) {
        requestDecode(this.mModel.getMediaItem(), this.mModel.getPosition());
    }

    public void requestDecode(MediaItem mediaItem, int i2) {
        MediaItem mediaItem2;
        AbsImageLoader absImageLoader;
        if (mediaItem != null) {
            String removeArgs = ArgumentsUtil.removeArgs(getDecodedImageKey(mediaItem, i2));
            Blackboard blackboard = getBlackboard();
            Bitmap bitmap = (Bitmap) blackboard.read(removeArgs);
            if (bitmap != null) {
                Log.d(this.TAG, "request decode skip#1", bitmap);
                processLoadedBitmap(mediaItem, bitmap, checkSameBitmap(mediaItem, bitmap));
                absImageLoader = this;
                mediaItem2 = mediaItem;
            } else {
                absImageLoader = this;
                mediaItem2 = mediaItem;
                this.mThread.runOnBgThread(new a(absImageLoader, blackboard, removeArgs, mediaItem2, i2, 0), "requestDecode", (long) absImageLoader.mRequestDecodeDelay);
                absImageLoader.mRequestDecodeDelay = 150;
            }
            if (absImageLoader.needsLastRequestedItem()) {
                absImageLoader.mLastRequestedItem = mediaItem2.clone();
                return;
            }
            return;
        }
        Log.e(this.TAG, "request decode failed with null data");
    }

    public void requestOriginalBitmap(MediaItem mediaItem, int i2) {
        String str;
        boolean z;
        if (mediaItem != null) {
            str = CommandKey.DATA_REQUEST(getDecodedImageKey(mediaItem, i2));
        } else {
            str = null;
        }
        if (str == null || !getBlackboard().publishIfEmpty(str, mediaItem)) {
            StringCompat stringCompat = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (str != null) {
                z = true;
            } else {
                z = false;
            }
            Log.e((CharSequence) stringCompat, "requestOriginalBitmap failed", valueOf, Boolean.valueOf(z));
            return;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.p(stringCompat2, "requestOriginalBitmap" + Logger.v(Integer.valueOf(i2), Long.valueOf(mediaItem.getFileId()), Integer.valueOf(mediaItem.getSimpleHashCode())));
    }

    public void subscribe(String str, MediaItem mediaItem, int i2) {
        this.mImageSubscriber = subscribe(str, new b(this, mediaItem, i2, 0));
    }

    public void unsubscribe(MediaItem mediaItem) {
        SubscriberListenerInfo subscriberListenerInfo = this.mImageSubscriber;
        if (subscriberListenerInfo != null) {
            unsubscribe(subscriberListenerInfo);
        }
        this.mImageSubscriber = null;
    }

    public void onViewAttached() {
    }
}
