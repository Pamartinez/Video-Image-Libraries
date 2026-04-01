package com.samsung.android.gallery.app.controller.viewer;

import V3.b;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartCapturedFileViewCmd extends BaseCommand {
    private MediaItem mMediaItem;

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004e A[SYNTHETIC, Splitter:B:12:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadMediaItem() {
        /*
            r5 = this;
            com.samsung.android.gallery.module.data.MediaItem r0 = r5.mMediaItem
            com.samsung.android.gallery.module.data.DetailsData r0 = com.samsung.android.gallery.module.data.DetailsData.of(r0)
            java.lang.String r0 = r0.capturedPath
            java.lang.String r1 = com.samsung.android.gallery.database.dal.abstraction.DbKey.ALL_PICTURES     // Catch:{ Exception -> 0x0052 }
            A4.B r2 = new A4.B     // Catch:{ Exception -> 0x0052 }
            r3 = 17
            r2.<init>(r0, r3)     // Catch:{ Exception -> 0x0052 }
            android.database.Cursor r0 = com.samsung.android.gallery.database.dal.DbCompat.query(r1, r2)     // Catch:{ Exception -> 0x0052 }
            if (r0 == 0) goto L_0x0045
            boolean r1 = r0.moveToFirst()     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0045
            com.samsung.android.gallery.module.data.MediaItem r1 = com.samsung.android.gallery.module.data.MediaItemBuilder.create((android.database.Cursor) r0)     // Catch:{ all -> 0x0043 }
            com.samsung.android.gallery.module.viewer.VuLauncher r2 = new com.samsung.android.gallery.module.viewer.VuLauncher     // Catch:{ all -> 0x0043 }
            com.samsung.android.gallery.support.blackboard.Blackboard r3 = r5.getBlackboard()     // Catch:{ all -> 0x0043 }
            r2.<init>(r3)     // Catch:{ all -> 0x0043 }
            com.samsung.android.gallery.module.viewer.VuLauncher r2 = r2.publishData()     // Catch:{ all -> 0x0043 }
            com.samsung.android.gallery.module.viewer.VuLauncher r2 = r2.requestBitmapUrgent()     // Catch:{ all -> 0x0043 }
            java.lang.String r3 = "view_origin_item"
            com.samsung.android.gallery.module.viewer.VuLauncher r2 = r2.addTrueArgument(r3)     // Catch:{ all -> 0x0043 }
            java.lang.String r3 = "location://quickView"
            com.samsung.android.gallery.module.data.MediaItem[] r1 = new com.samsung.android.gallery.module.data.MediaItem[]{r1}     // Catch:{ all -> 0x0043 }
            r4 = 0
            r2.launch(r3, r4, r1)     // Catch:{ all -> 0x0043 }
            goto L_0x004c
        L_0x0043:
            r1 = move-exception
            goto L_0x0055
        L_0x0045:
            java.lang.String r1 = r5.TAG     // Catch:{ all -> 0x0043 }
            java.lang.String r2 = "can not find item from db"
            com.samsung.android.gallery.support.utils.Log.e(r1, r2)     // Catch:{ all -> 0x0043 }
        L_0x004c:
            if (r0 == 0) goto L_0x0054
            r0.close()     // Catch:{ Exception -> 0x0052 }
            return
        L_0x0052:
            r0 = move-exception
            goto L_0x0060
        L_0x0054:
            return
        L_0x0055:
            if (r0 == 0) goto L_0x005f
            r0.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ Exception -> 0x0052 }
        L_0x005f:
            throw r1     // Catch:{ Exception -> 0x0052 }
        L_0x0060:
            java.lang.String r5 = r5.TAG
            java.lang.String r1 = "load media item failed"
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r5, (java.lang.String) r1, (java.lang.Throwable) r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.viewer.StartCapturedFileViewCmd.loadMediaItem():void");
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_VIEW_ORIGINAL_BY_PATH.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        SimpleThreadPool.getInstance().execute(new b(6, this));
    }
}
