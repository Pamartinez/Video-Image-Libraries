package com.samsung.android.gallery.app.controller.album;

import H3.l;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.album.AlbumHelper;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveToGroupCmd extends BaseCommand {
    private int mAlbumId;
    private int mGroupId;
    private String mGroupName;

    private boolean isValid(Uri uri) {
        this.mAlbumId = UnsafeCast.toInt(uri.getQueryParameter("KEY_ALBUM_ID"), -1);
        this.mGroupId = UnsafeCast.toInt(uri.getQueryParameter("KEY_GROUP_ID"), -1);
        this.mGroupName = uri.getQueryParameter("KEY_GROUP_NAME");
        if (this.mAlbumId != -1 && this.mGroupId != -1) {
            return true;
        }
        String str = this.TAG;
        Log.bxe(str, "invalid data [" + this.mAlbumId + "][" + this.mGroupId + "]");
        return false;
    }

    /* access modifiers changed from: private */
    public void moveToGroup() {
        String str = this.TAG;
        Log.bx(str, "move to group [" + this.mAlbumId + "][" + this.mGroupName + "][" + this.mGroupId + "]");
        if (!AlbumHelper.getInstance().updateFolder(new int[]{this.mAlbumId}, this.mGroupId, this.mGroupName)) {
            Log.bxe(this.TAG, "unable to add album to group");
            return;
        }
        Blackboard blackboard = getBlackboard();
        String str2 = (String) blackboard.read("location://variable/currentv1");
        String folderLocationKey = AlbumHelper.getInstance().getFolderLocationKey(getContext(), this.mGroupId);
        if (LocationKey.isAlbums(folderLocationKey)) {
            Log.bxe(this.TAG, "unable to find target group");
            return;
        }
        String appendArgs = ArgumentsUtil.appendArgs(folderLocationKey, "id", String.valueOf(this.mGroupId));
        if (LocationKey.isFolder(str2)) {
            blackboard.post("command://MoveToGroup", appendArgs);
        } else if (LocationKey.isAlbums(str2)) {
            blackboard.post("command://MoveURL", appendArgs);
        }
        blackboard.postEvent(EventMessage.obtain(104, 1, 0, "location://albums"));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (isValid(objArr[0])) {
            ThreadUtil.postOnBgThread(new l(1, this));
        }
    }
}
