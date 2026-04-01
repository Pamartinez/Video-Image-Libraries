package com.samsung.android.gallery.app.ui.viewer2.container.delegate;

import A.a;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.container.abstraction.IVuContainerView;
import com.samsung.android.gallery.app.ui.viewer2.delegate.AbsVuDelegate;
import com.samsung.android.gallery.app.ui.viewer2.model.ContainerModel;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BothSideImageLoader extends AbsVuDelegate<IVuContainerView> implements SubscriberListener {
    private final ArrayList<String> mRequestKey = new ArrayList<>();

    public BothSideImageLoader(IVuContainerView iVuContainerView) {
        super(iVuContainerView);
    }

    public String getDecodedImageKey(MediaItem mediaItem, int i2) {
        if (mediaItem == null) {
            return "data://bitmap/viewer/dummy";
        }
        return ArgumentsUtil.appendArgs(MediaItemUtil.getViewerBitmapKey(mediaItem, i2), "verify_subscriber", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
    }

    public void onDestroy() {
        Iterator<String> it = this.mRequestKey.iterator();
        while (it.hasNext()) {
            String next = it.next();
            this.mBlackboard.unsubscribe(next, this);
            BlackboardUtils.cancelAndEraseViewerBitmap(this.mBlackboard, next);
        }
        this.mRequestKey.clear();
    }

    public void onNotify(Object obj, Bundle bundle) {
        String str = this.TAG;
        Log.d(str, "onNotify imageLoaded " + ((String) bundle.get("_SUBSCRIBE_KEY")));
    }

    public void onViewCreated(View view, Bundle bundle) {
        MediaData mediaData = ((ContainerModel) this.mModel).getMediaData();
        int position = ((ContainerModel) this.mModel).getPosition();
        if (mediaData != null) {
            if (position > 0) {
                int i2 = position - 1;
                requestDecode(mediaData.read(i2), i2);
            }
            if (position < mediaData.getCount() - 1) {
                int i7 = position + 1;
                requestDecode(mediaData.read(i7), i7);
            }
        }
    }

    public void requestDecode(MediaItem mediaItem, int i2) {
        if (mediaItem != null && !mediaItem.isBroken()) {
            String removeArgs = ArgumentsUtil.removeArgs(getDecodedImageKey(mediaItem, i2));
            if (((Bitmap) this.mBlackboard.read(removeArgs)) == null) {
                a.k(i2, "preDecode : ", this.TAG);
                this.mBlackboard.subscribe(removeArgs, this);
                requestOriginalBitmap(mediaItem, i2);
                this.mRequestKey.add(removeArgs);
            }
        }
    }

    public void requestOriginalBitmap(MediaItem mediaItem, int i2) {
        String str;
        boolean z;
        if (mediaItem != null) {
            str = CommandKey.DATA_REQUEST(getDecodedImageKey(mediaItem, i2));
        } else {
            str = null;
        }
        if (str == null || !this.mBlackboard.publishIfEmpty(str, mediaItem)) {
            String str2 = this.TAG;
            Integer valueOf = Integer.valueOf(i2);
            if (str != null) {
                z = true;
            } else {
                z = false;
            }
            Log.e((CharSequence) str2, "requestOriginalBitmap failed", valueOf, Boolean.valueOf(z));
            return;
        }
        String str3 = this.TAG;
        Log.p(str3, "requestOriginalBitmap" + Logger.v(Integer.valueOf(i2), Long.valueOf(mediaItem.getFileId()), Integer.valueOf(mediaItem.getSimpleHashCode())));
    }
}
