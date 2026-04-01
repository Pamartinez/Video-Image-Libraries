package com.samsung.android.gallery.app.controller.album;

import A9.b;
import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameFolderCmd extends BaseCommand {
    private MediaItem mSelectedItem;

    private void addNewEmptyFolder(boolean z, String str) {
        MediaItem mediaItem = (MediaItem) getBlackboard().read("data://current_folder", null);
        if (z) {
            AlbumHelper.getInstance().addNewEmptyFolder(str, this.mSelectedItem.getAlbumOrder());
        } else if (mediaItem != null) {
            AlbumHelper.getInstance().addNewEmptyFolder(mediaItem.getFolderID(), mediaItem.getFolderName(), str, this.mSelectedItem.getAlbumOrder());
        } else {
            Log.e(this.TAG, "fromAlbums = false, but currentFolder is null");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$renameFolder$0(EventContext eventContext, String str) {
        if (!this.mSelectedItem.isFolder()) {
            Log.e(this.TAG, "selected item is not a folder");
            return;
        }
        if (this.mSelectedItem.getItemsInFolder().length > 0) {
            addNewEmptyFolder(LocationKey.isAlbums(eventContext.getLocationKey()), str);
            AlbumHelper.getInstance().renameFolderName(this.mSelectedItem.getFolderID(), str);
            removePrevFolder();
            reloadAlbum();
        } else {
            updateEmptyFolderName(str);
        }
        if (eventContext.isSelectionMode()) {
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    private void reloadAlbum() {
        getBlackboard().postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    private void removePrevFolder() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.mSelectedItem.getAlbumID()));
        AlbumHelper.getInstance().removeFolder(getContext(), arrayList, (List<Integer>) null);
    }

    /* access modifiers changed from: private */
    public void renameFolder(EventContext eventContext, ArrayList<Object> arrayList) {
        String str;
        if (arrayList == null || arrayList.isEmpty()) {
            Log.e(this.TAG, "renameFolder data is null");
            return;
        }
        Object obj = ((Object[]) arrayList.get(0))[0];
        if (obj != null) {
            str = (String) obj;
        } else {
            str = null;
        }
        if (str == null) {
            Log.e(this.TAG, "folderName is null");
        } else {
            ThreadUtil.postOnBgThread(new b(this, eventContext, str, 23));
        }
    }

    private void updateEmptyFolderName(String str) {
        if (AlbumHelper.getInstance().renameEmptyFolderName(this.mSelectedItem.getFolderID(), str)) {
            reloadAlbum();
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            if (eventContext.isSelectionMode()) {
                this.mSelectedItem = eventContext.getSelectedItems()[0];
            } else {
                this.mSelectedItem = objArr[0];
            }
            if (this.mSelectedItem == null) {
                Log.e(this.TAG, "item is null. stop RenameFolderCmd");
                return;
            }
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/FolderRename").appendArg("name", this.mSelectedItem.getFolderName()).build()).setOnDataCompleteListener(new h(11, this)).start();
        }
    }
}
