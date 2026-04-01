package com.samsung.android.gallery.module.dataset;

import android.database.Cursor;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.comparator.Comparators;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MediaDataSearchAlbum extends MediaDataAlbum {
    protected AlbumDataHelper mAlbumDataHelper;

    public MediaDataSearchAlbum(Blackboard blackboard, String str, boolean z, boolean z3, boolean z7) {
        super(blackboard, str, z, z3, z7);
        this.mAlbumDataHelper = new AlbumDataHelper(str);
    }

    private void preload(ArrayList<MediaItem> arrayList, HashMap<Integer, MediaItem> hashMap) {
        ArrayList<MediaItem> checkMissingItemsInFilesTable = checkMissingItemsInFilesTable(hashMap, arrayList);
        if (!checkMissingItemsInFilesTable.isEmpty()) {
            if (this.mAlbumDataHelper.isOrderUpdated(arrayList)) {
                checkMissingItemsInFilesTable.sort(Comparators.getComparator(this.mLocationKey, 41));
                addAllNewAlbums(arrayList, checkMissingItemsInFilesTable);
            } else {
                arrayList.addAll(checkMissingItemsInFilesTable);
            }
        }
        arrayList.sort(this.mAlbumDataHelper.getComparator());
    }

    public ArrayList<MediaItem> createFullList(Cursor[] cursorArr, ArrayList<MediaItem> arrayList) {
        HashMap hashMap = new HashMap();
        ArrayList<MediaItem> arrayList2 = new ArrayList<>();
        preloadMap(cursorArr[0], hashMap);
        preload(arrayList2, hashMap);
        return arrayList2;
    }
}
