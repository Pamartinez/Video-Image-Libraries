package com.samsung.android.gallery.app.controller.album;

import Fb.h;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AutoAlbumHelper;
import com.samsung.android.gallery.module.album.ShortcutHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.scsp.media.file.FileApiContract;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RenameAutoAlbumCmd extends BaseCommand {
    MediaItem mAlbumItem;

    /* access modifiers changed from: private */
    public void renameAutoAlbum(EventContext eventContext, ArrayList<Object> arrayList) {
        Object obj;
        if (arrayList != null && (obj = ((Object[]) arrayList.get(0))[0]) != null) {
            String str = (String) obj;
            AutoAlbumHelper.getInstance().renameAutoAlbum(str, (long) this.mAlbumItem.getAlbumID());
            MediaItem clone = this.mAlbumItem.clone();
            clone.setDisplayName(str);
            Blackboard.postBroadcastEventGlobal(EventMessage.obtain(104, new Object[]{Integer.valueOf(this.mAlbumItem.getAlbumID()), clone}));
            if (eventContext.isSelectionMode()) {
                getBlackboard().postEvent(EventMessage.obtain(1003));
            }
            clone.setTitle(str);
            ShortcutHelper.getInstance().updateHomeScreenShortcut(getActivity(), clone);
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        this.mAlbumItem = mediaItem;
        if (mediaItem != null) {
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/AlbumRename").appendArg(FileApiContract.Parameter.PATH, FileUtils.getParentDirectory(FileUtils.getDirectoryFromPath(mediaItem.getAlbumPath()))).appendArg("name", this.mAlbumItem.getTitle()).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new h(10, this)).start();
            return;
        }
        Log.e(this.TAG, "item is null. stop RenameAutoAlbumCmd");
    }
}
