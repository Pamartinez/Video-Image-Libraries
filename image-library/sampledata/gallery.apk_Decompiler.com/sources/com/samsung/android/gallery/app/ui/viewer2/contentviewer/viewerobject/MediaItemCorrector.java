package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.graphics.Bitmap;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import java.util.concurrent.atomic.AtomicBoolean;
import n0.C0235b;
import u7.C0520a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaItemCorrector extends ViewerObject {
    private final AtomicBoolean mActive = new AtomicBoolean(true);

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: com.samsung.android.gallery.module.graphics.BitmapOptions} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ void lambda$onPreviewLoad$0(android.graphics.Bitmap r3, com.samsung.android.gallery.module.data.MediaItem r4) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L_0x000c
            java.lang.String r1 = "options"
            java.lang.Object r3 = com.samsung.android.gallery.support.library.SeApiCompat.getBitmapTag(r3, r1, r0)
            r0 = r3
            com.samsung.android.gallery.module.graphics.BitmapOptions r0 = (com.samsung.android.gallery.module.graphics.BitmapOptions) r0
        L_0x000c:
            if (r0 != 0) goto L_0x0016
            java.lang.String r3 = r4.getPath()
            com.samsung.android.gallery.module.graphics.BitmapOptions r0 = com.samsung.android.gallery.module.graphics.BitmapOptionsFactory.parse(r3)
        L_0x0016:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r1 = "correct "
            r3.<init>(r1)
            java.lang.String r1 = com.samsung.android.gallery.module.data.MediaItemUtil.getMediaLog(r4)
            r3.append(r1)
            java.lang.String r1 = ","
            r3.append(r1)
            r3.append(r0)
            java.lang.String r3 = r3.toString()
            java.lang.String r1 = "MediaItemCorrector"
            com.samsung.android.gallery.support.utils.Log.e(r1, r3)
            com.samsung.android.gallery.support.utils.Log.majorEvent(r3)
            int r3 = r0.outWidth
            if (r3 <= 0) goto L_0x0060
            int r1 = r0.outHeight
            if (r1 <= 0) goto L_0x0060
            r4.setSize(r3, r1)
            com.samsung.android.gallery.support.config.SdkConfig$GED r3 = com.samsung.android.gallery.support.config.SdkConfig.GED.R
            boolean r3 = com.samsung.android.gallery.support.config.SdkConfig.atLeast((com.samsung.android.gallery.support.config.SdkConfig.GED) r3)
            if (r3 == 0) goto L_0x0056
            android.net.Uri r3 = com.samsung.android.gallery.module.data.ContentUri.getSecUri(r4)
            int r4 = r0.outWidth
            int r0 = r0.outHeight
            com.samsung.android.gallery.database.dal.mp.helper.FilesApi.updateMediaAsync(r3, r4, r0)
        L_0x0056:
            com.samsung.android.gallery.support.actioninvoker.ActionInvoker r2 = r2.mActionInvoker
            com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction r3 = com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction.UPDATE_PHOTO_BITMAP
            r4 = 0
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r2.invoke(r3, r4)
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.MediaItemCorrector.lambda$onPreviewLoad$0(android.graphics.Bitmap, com.samsung.android.gallery.module.data.MediaItem):void");
    }

    /* access modifiers changed from: private */
    public void onPreviewLoad(Object... objArr) {
        Bitmap bitmap = objArr[0];
        MediaItem mediaItem = objArr[1];
        if (this.mActive.getAndSet(false) && MediaItemUtil.checkWrongSize(mediaItem, bitmap)) {
            this.mThread.runOnBgThread(new C0235b(this, bitmap, mediaItem, 28));
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.PREVIEW_LOADED, new C0520a(3, this));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new C0520a(3, this));
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mActive.set(true);
    }
}
