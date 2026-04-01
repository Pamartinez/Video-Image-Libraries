package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import M4.m;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.module.data.MediaCacheLoader;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.xmp.XmpUtils;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.ArrayList;
import q6.e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class XmpTypeLoader extends ViewerObject {
    /* access modifiers changed from: private */
    /* renamed from: load */
    public void lambda$loadOnBgThread$0(MediaItem mediaItem) {
        ArrayList<XmpType> arrayList;
        if (mediaItem != null && mediaItem.getXmpType() == null) {
            if (MediaCacheLoader.SUPPORT_XMP) {
                arrayList = MediaCacheLoader.getInstance().computeXmpTags(mediaItem, new m(mediaItem, 2));
            } else {
                arrayList = XmpUtils.getXmpTags(mediaItem);
            }
            updateXmpType(mediaItem, arrayList);
        }
    }

    private void loadOnBgThread(MediaItem mediaItem) {
        if (mediaItem.isJpeg()) {
            SimpleThreadPool.getInstance().execute(new e(24, this, mediaItem));
        }
    }

    private void updateXmpType(MediaItem mediaItem, ArrayList<XmpType> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            XmpType xmpType = XmpType.XmpImage360;
            if (arrayList.contains(xmpType)) {
                mediaItem.setXmpType(xmpType);
                this.mActionInvoker.invoke(ViewerAction.XMP_SHOT_MODE_UPDATED, new Object[0]);
                return;
            }
            XmpType xmpType2 = XmpType.XmpMotionPhoto;
            if (arrayList.contains(xmpType2)) {
                mediaItem.setXmpType(xmpType2);
                this.mActionInvoker.invoke(ViewerAction.XMP_SHOT_MODE_UPDATED, new Object[0]);
            }
        }
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        loadOnBgThread(mediaItem);
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        loadOnBgThread(mediaItem);
    }
}
