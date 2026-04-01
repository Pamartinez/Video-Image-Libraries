package com.samsung.android.gallery.app.controller.album;

import B8.d;
import D7.g;
import Fa.C0562p;
import Fa.F;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ChangeAlbumLevelCmd extends BaseCommand {
    private ConcurrentHashMap<MediaItem, Integer> mUpdateValues;

    /* access modifiers changed from: private */
    public void changeAlbumLevel() {
        ConcurrentHashMap<MediaItem, Integer> concurrentHashMap = this.mUpdateValues;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            Log.e("ChangeAlbumLevelCmd", "No item is selected");
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        this.mUpdateValues.forEach(new C0562p(arrayList, arrayList2, 1));
        arrayList.forEach(new F(19));
        arrayList2.forEach(new F(20));
        setAlbumLevels(arrayList, 1);
        setAlbumLevels(arrayList2, 0);
        stopSelectionMode();
        reloadAlbum();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$changeAlbumLevel$2(ArrayList arrayList, ArrayList arrayList2, MediaItem mediaItem, Integer num) {
        if (num.intValue() == 1) {
            arrayList.add(Integer.valueOf(mediaItem.getAlbumID()));
            if (mediaItem.isMergedAlbum()) {
                mediaItem.getChildList().forEach(new d(arrayList, 4));
                return;
            }
            return;
        }
        arrayList2.add(Integer.valueOf(mediaItem.getAlbumID()));
        if (mediaItem.isMergedAlbum()) {
            mediaItem.getChildList().forEach(new d(arrayList2, 5));
        }
    }

    private void reloadAlbum() {
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    private void setAlbumLevels(ArrayList<Integer> arrayList, int i2) {
        if (!arrayList.isEmpty() && !AlbumHelper.getInstance().updateAlbumLevel(arrayList, i2)) {
            Log.e("ChangeAlbumLevelCmd", "fail to update album level[" + i2 + "]");
        }
    }

    private void stopSelectionMode() {
        getBlackboard().postEvent(EventMessage.obtain(1003));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mUpdateValues = objArr[0];
        if (ThreadUtil.isMainThread()) {
            SimpleThreadPool.getInstance().execute(new g(27, this));
        } else {
            changeAlbumLevel();
        }
    }
}
