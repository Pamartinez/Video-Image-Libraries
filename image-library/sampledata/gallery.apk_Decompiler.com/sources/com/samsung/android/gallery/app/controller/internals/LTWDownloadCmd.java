package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LTWDownloadCmd extends BaseCommand {
    private MediaItem[] mItems = null;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem mediaItem) {
        operationDownload(AlbumHelper.getInstance().getValidPath(mediaItem), mediaItem.getAlbumID());
    }

    private void operationDownload(String str, int i2) {
        getBlackboard().publish("data://user/selected", this.mItems);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.LTWDownloadService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("target_album_path", str);
        intent.putExtra("selected_album_id", i2);
        getContext().startService(intent);
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem[] mediaItemArr = objArr[0];
            this.mItems = mediaItemArr;
            if (mediaItemArr == null || mediaItemArr.length == 0) {
                Log.e(this.TAG, "Unable to operate. no item selected.");
            } else {
                SimpleThreadPool.getInstance().execute(new a(15, this, objArr[1]));
            }
        }
    }
}
