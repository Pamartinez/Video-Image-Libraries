package com.samsung.android.gallery.app.controller.album;

import A.a;
import A8.C0545b;
import Ad.C0720a;
import Fa.C0571z;
import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FolderGroupCmd extends BaseCommand {
    private MediaItem mCurrentFolder = null;

    /* access modifiers changed from: private */
    public void addAlbumToFolder(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList == null || arrayList.isEmpty() || !(arrayList.get(0) instanceof Object[])) {
            Log.e((CharSequence) this.TAG, "skip add album to folder, data is invalid", arrayList);
            return;
        }
        int[] iArr = (int[]) ((Object[]) arrayList.get(0))[0];
        if (iArr != null && iArr.length != 0 && this.mCurrentFolder != null) {
            if (AlbumHelper.getInstance().updateFolder(iArr, this.mCurrentFolder.getFolderID(), this.mCurrentFolder.getFolderName())) {
                getBlackboard().publish("data://useradd_items_to_folder", iArr);
            } else {
                Log.e(this.TAG, "Error adding items to folder");
            }
            a.w(new StringBuilder("item size is "), iArr.length, this.TAG);
        }
    }

    private String getDisabledAlbumType() {
        return null;
    }

    private static List<Integer> getNestedIds(MediaItem[] mediaItemArr) {
        ArrayList arrayList = new ArrayList();
        for (MediaItem itemsInFolder : mediaItemArr) {
            arrayList.addAll((Collection) Arrays.stream(itemsInFolder.getItemsInFolder()).map(new Gb.a(7)).collect(Collectors.toCollection(new C0720a(1))));
        }
        return arrayList;
    }

    private void groupingAlbums(EventContext eventContext) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/move/AlbumFolderChoice").appendArg("disabledAlbumType", getDisabledAlbumType()).build()).setOnDataCompleteListener(new h(7, this)).start();
        postAnalyticsLog(AnalyticsEventId.EVENT_MENU_ADD_ALBUMS);
    }

    private static boolean isFromAlbums(EventContext eventContext) {
        if (LocationKey.isAlbumsMatch(eventContext.getLocationKey()) || LocationKey.isAllAlbumsMatch(eventContext.getLocationKey())) {
            return true;
        }
        return false;
    }

    private boolean isRootEmptyFolder(MediaItem[] mediaItemArr) {
        if (this.mCurrentFolder == null && mediaItemArr.length == 1 && mediaItemArr[0].isFolder() && mediaItemArr[0].getItemsInFolder().length == 0) {
            return true;
        }
        return false;
    }

    private boolean ungroupingFromAlbum(MediaItem[] mediaItemArr, ArrayList<Integer> arrayList) {
        if (!isRootEmptyFolder(mediaItemArr) && !AlbumHelper.getInstance().clearFolderInfoFromFolder(arrayList)) {
            Log.e(this.TAG, "Error un-grouping folder : selection fromAlbums");
            return false;
        } else if (AlbumHelper.getInstance().removeFolder(getContext(), arrayList, (List<Integer>) null)) {
            return true;
        } else {
            Log.e(this.TAG, "Error deleting folder : selection fromAlbums");
            return false;
        }
    }

    private boolean ungroupingNotFromAlbum(MediaItem[] mediaItemArr, ArrayList<Integer> arrayList) {
        boolean allMatch = Arrays.stream(mediaItemArr).allMatch(new C0571z(12));
        if (this.mCurrentFolder == null) {
            Log.e(this.TAG, "current folder is null");
            return false;
        } else if (allMatch) {
            if (AlbumHelper.getInstance().updateFolder(getNestedIds(mediaItemArr).stream().mapToInt(new C0545b(9)).toArray(), this.mCurrentFolder.getFolderID(), this.mCurrentFolder.getFolderName()) && AlbumHelper.getInstance().removeFolder(getContext(), arrayList, (List<Integer>) null)) {
                return true;
            }
            Log.e(this.TAG, "Error un-grouping folder : selection from folder (only folder case)");
            return false;
        } else if (AlbumHelper.getInstance().deleteAlbumFromFolder(arrayList)) {
            return true;
        } else {
            Log.e(this.TAG, "Error un-grouping folder : selection from folder");
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005b, code lost:
        if (ungroupingNotFromAlbum(r1, r7) == false) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecute(com.samsung.android.gallery.app.controller.EventContext r6, java.lang.Object... r7) {
        /*
            r5 = this;
            java.lang.String r0 = "local_db_updating"
            r1 = 0
            r1 = r7[r1]
            com.samsung.android.gallery.module.data.MediaItem[] r1 = (com.samsung.android.gallery.module.data.MediaItem[]) r1
            r2 = 1
            r7 = r7[r2]
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r5.getBlackboard()
            java.lang.String r3 = "data://current_folder"
            r4 = 0
            java.lang.Object r2 = r2.read(r3, r4)
            com.samsung.android.gallery.module.data.MediaItem r2 = (com.samsung.android.gallery.module.data.MediaItem) r2
            r5.mCurrentFolder = r2
            com.samsung.android.gallery.support.blackboard.Blackboard r2 = r5.getBlackboard()     // Catch:{ all -> 0x0055 }
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x0055 }
            r2.publish(r0, r3)     // Catch:{ all -> 0x0055 }
            if (r7 == 0) goto L_0x008b
            java.util.stream.Stream r7 = java.util.Arrays.stream(r1)     // Catch:{ all -> 0x0055 }
            Gb.a r2 = new Gb.a     // Catch:{ all -> 0x0055 }
            r3 = 7
            r2.<init>(r3)     // Catch:{ all -> 0x0055 }
            java.util.stream.Stream r7 = r7.map(r2)     // Catch:{ all -> 0x0055 }
            Ad.a r2 = new Ad.a     // Catch:{ all -> 0x0055 }
            r3 = 1
            r2.<init>(r3)     // Catch:{ all -> 0x0055 }
            java.util.stream.Collector r2 = java.util.stream.Collectors.toCollection(r2)     // Catch:{ all -> 0x0055 }
            java.lang.Object r7 = r7.collect(r2)     // Catch:{ all -> 0x0055 }
            java.util.ArrayList r7 = (java.util.ArrayList) r7     // Catch:{ all -> 0x0055 }
            boolean r6 = isFromAlbums(r6)     // Catch:{ all -> 0x0055 }
            if (r6 == 0) goto L_0x0057
            boolean r1 = r5.ungroupingFromAlbum(r1, r7)     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x0067
            goto L_0x005d
        L_0x0055:
            r6 = move-exception
            goto L_0x008f
        L_0x0057:
            boolean r1 = r5.ungroupingNotFromAlbum(r1, r7)     // Catch:{ all -> 0x0055 }
            if (r1 != 0) goto L_0x0067
        L_0x005d:
            com.samsung.android.gallery.support.blackboard.Blackboard r5 = r5.getBlackboard()
            java.lang.Boolean r6 = java.lang.Boolean.FALSE
            r5.publish(r0, r6)
            return
        L_0x0067:
            com.samsung.android.gallery.support.blackboard.Blackboard r1 = r5.getBlackboard()     // Catch:{ all -> 0x0055 }
            java.lang.String r2 = "data://userfolder_ungrouping"
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch:{ all -> 0x0055 }
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r7}     // Catch:{ all -> 0x0055 }
            r1.publish(r2, r6)     // Catch:{ all -> 0x0055 }
            com.samsung.android.gallery.support.blackboard.Blackboard r6 = r5.getBlackboard()     // Catch:{ all -> 0x0055 }
            r7 = 1003(0x3eb, float:1.406E-42)
            com.samsung.android.gallery.support.blackboard.key.EventMessage r7 = com.samsung.android.gallery.support.blackboard.key.EventMessage.obtain(r7)     // Catch:{ all -> 0x0055 }
            r6.postEvent(r7)     // Catch:{ all -> 0x0055 }
            com.samsung.android.gallery.support.analytics.AnalyticsEventId r6 = com.samsung.android.gallery.support.analytics.AnalyticsEventId.EVENT_MENU_ALBUM_UNGROUP     // Catch:{ all -> 0x0055 }
            r5.postAnalyticsLog(r6)     // Catch:{ all -> 0x0055 }
            goto L_0x005d
        L_0x008b:
            r5.groupingAlbums(r6)     // Catch:{ all -> 0x0055 }
            goto L_0x005d
        L_0x008f:
            com.samsung.android.gallery.support.blackboard.Blackboard r5 = r5.getBlackboard()
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r5.publish(r0, r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.FolderGroupCmd.onExecute(com.samsung.android.gallery.app.controller.EventContext, java.lang.Object[]):void");
    }
}
