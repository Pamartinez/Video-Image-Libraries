package com.samsung.android.gallery.module.publisher;

import K5.a;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.remotegallery.RemoteClient;
import com.samsung.android.gallery.module.remotegallery.RemoteGalleryUtil;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.BundleWrapper;
import com.samsung.android.gallery.support.utils.CollectionCursor;
import com.samsung.android.gallery.support.utils.GsonTool;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemoteAlbumPublisher {
    String targetIp = null;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$exec$0(MediaItem mediaItem) {
        mediaItem.setPath("remote://" + this.targetIp + mediaItem.getPath());
        mediaItem.setStorageType(StorageType.RemoteItem);
        if (!mediaItem.isMotionPhoto()) {
            mediaItem.setSefFileType(0, 0);
        }
        mediaItem.setCount(1);
    }

    public boolean exec(Blackboard blackboard, long j2, Bundle bundle, CursorPublisher cursorPublisher) {
        MediaItem mediaItem = (MediaItem) blackboard.read("data://albums/current");
        if (mediaItem != null && ((long) mediaItem.getAlbumID()) == j2 && RemoteGalleryUtil.isRemoteAlbum(mediaItem)) {
            MediaItem mediaItem2 = new MediaItem();
            mediaItem2.setDisplayName(mediaItem.getDisplayName().replace("remote.", "remote://"));
            this.targetIp = RemoteGalleryUtil.getIp(mediaItem2);
        } else if (BundleWrapper.getString(bundle, "remoteIp", (String) null) != null) {
            this.targetIp = BundleWrapper.getString(bundle, "remoteIp", (String) null);
        }
        if (TextUtils.isEmpty(this.targetIp)) {
            return false;
        }
        Log.i("RemoteAlbumPublisher", "targetIp : " + Logger.getEncodedString(this.targetIp));
        Cursor[] cursorArr = new Cursor[6];
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 20; i2++) {
            CollectionCursor collectionCursor = new CollectionCursor("__absID");
            ArrayList arrayList2 = new ArrayList();
            int i7 = 100 * i2;
            new RemoteClient(this.targetIp).query(arrayList2, i7, 100);
            if (arrayList2.isEmpty()) {
                Log.i("RemoteAlbumPublisher", "break " + this.targetIp, Integer.valueOf(arrayList2.size()), 100, Integer.valueOf(arrayList.size()));
                return true;
            }
            arrayList2.forEach(new a(10, this));
            arrayList.addAll(arrayList2);
            arrayList.forEach(new a(11, collectionCursor));
            Bundle bundle2 = new Bundle();
            bundle2.putString("dataHash", collectionCursor.toString().hashCode() + "_" + collectionCursor.getCount());
            bundle2.putString("all", GsonTool.toJsonString(arrayList));
            collectionCursor.setExtras(bundle2);
            cursorArr[5] = collectionCursor;
            blackboard.erase(DataKey.DATA_CURSOR("location://albums/fileList"));
            cursorPublisher.publishCursorArray("location://albums/fileList", cursorArr);
            Log.i("RemoteAlbumPublisher", "publishCursorArray : " + this.targetIp, Integer.valueOf(i7), 100, Integer.valueOf(arrayList.size()));
            if (arrayList2.size() < 100) {
                Log.i("RemoteAlbumPublisher", "break " + this.targetIp, Integer.valueOf(arrayList2.size()), 100, Integer.valueOf(arrayList.size()));
                return true;
            }
        }
        return true;
    }
}
