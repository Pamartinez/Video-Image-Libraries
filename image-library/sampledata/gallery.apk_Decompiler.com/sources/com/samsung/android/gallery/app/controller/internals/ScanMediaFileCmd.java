package com.samsung.android.gallery.app.controller.internals;

import B8.d;
import O3.x;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScanMediaFileCmd extends FixDateTimeCmd {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$scanMediaFile$2(MediaItem[] mediaItemArr) {
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (MediaItem mediaItem : mediaItemArr) {
            if (isInterrupted()) {
                break;
            }
            if (isApplicable(mediaItem)) {
                arrayList.add(mediaItem.getPath());
                if (mediaItem.getGroupMediaId() > 0) {
                    getGroupItems(mediaItem, new d(arrayList, 11));
                }
            }
        }
        Log.i(this.TAG, "update" + Logger.vt(Integer.valueOf(arrayList.size()), Long.valueOf(currentTimeMillis)));
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                FileUtils.setDateModified(str, FileUtils.getDateModified(str) + 1000);
            }
        }
        scanMedia(arrayList);
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public boolean isApplicable(MediaItem mediaItem) {
        return true;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        showProgressDialog(0);
        ThreadUtil.postOnUiThreadDelayed(new x(this, selectedItems, 0), 100);
    }

    /* renamed from: scanMediaFile */
    public void lambda$onExecute$0(MediaItem[] mediaItemArr) {
        SimpleThreadPool.getInstance().execute(new x(this, mediaItemArr, 1));
    }
}
