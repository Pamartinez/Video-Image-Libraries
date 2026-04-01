package com.samsung.android.gallery.app.controller.album;

import F8.a;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AlbumMoveCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(boolean z, MediaItem[] mediaItemArr, MediaItem mediaItem) {
        if (z) {
            ArrayList arrayList = new ArrayList();
            for (MediaItem albumID : mediaItemArr) {
                arrayList.add(Integer.valueOf(albumID.getAlbumID()));
            }
            if (!AlbumHelper.getInstance().deleteAlbumFromFolder(arrayList)) {
                Log.e(this.TAG, "Error moving items to root");
                return;
            }
        } else if (mediaItem == null) {
            Log.e(this.TAG, "Error moving to folder, current folder is null");
            return;
        } else {
            int[] iArr = new int[mediaItemArr.length];
            for (int i2 = 0; i2 < mediaItemArr.length; i2++) {
                iArr[i2] = mediaItemArr[i2].getAlbumID();
            }
            if (!AlbumHelper.getInstance().updateFolder(iArr, mediaItem.getFolderID(), mediaItem.getFolderName())) {
                Log.e(this.TAG, "Error moving items to folder");
                return;
            }
        }
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        ThreadUtil.postOnBgThread(new a((Object) this, objArr[2].booleanValue(), (Object) objArr[0], (Object) objArr[1], 3));
    }
}
