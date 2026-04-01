package com.samsung.android.gallery.app.controller.internals;

import M3.c;
import M5.a;
import O3.y;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UploadToRemoteCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$onComplete$1(EventContext eventContext, MediaItem[] mediaItemArr) {
        MediaItem currentItem = eventContext.getCurrentItem();
        if (currentItem != null) {
            new RemoteClient(RemoteGalleryUtil.getIp(currentItem)).sendFiles(mediaItemArr);
            Optional.ofNullable(eventContext.getMediaData()).ifPresent(new c(eventContext, 3));
        }
    }

    /* access modifiers changed from: private */
    public void onComplete(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null) {
            try {
                SimpleThreadPool.getInstance().execute(new a(24, eventContext, (MediaItem[]) arrayList.get(0)));
            } catch (ClassCastException e) {
                String str = this.TAG;
                Log.e(str, "fail to parsing item=" + e.getMessage());
            }
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/pick/Item").appendArg("pick-max-item", 100).build()).setOnDataCompleteListener(new y(1, this)).start();
    }
}
