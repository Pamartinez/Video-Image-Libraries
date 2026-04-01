package com.samsung.android.gallery.app.ui.list.sharings;

import Da.g;
import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.LeaveSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingsContextMenuHandler {
    private void deleteSharedAlbum(EventContext eventContext, MediaItem mediaItem) {
        new DeleteSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_DELETE, new MediaItem[]{mediaItem});
    }

    private int getAction(MediaItem mediaItem, int i2) {
        if (MediaItemMde.isOwnedByMe(mediaItem)) {
            return i2;
        }
        if (MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
            return 1;
        }
        return 10;
    }

    private String getTitle(Context context, MediaItem mediaItem) {
        if (MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
            return context.getString(R.string.shared_family_album);
        }
        return context.getString(R.string.name_shared_album, new Object[]{mediaItem.getTitle()});
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$showActionDialog$0(EventContext eventContext, MediaItem mediaItem, DialogInterface dialogInterface, int i2) {
        onDialogActionItemClicked(eventContext, getAction(mediaItem, i2), mediaItem);
    }

    private void leaveSharedAlbum(EventContext eventContext, MediaItem mediaItem) {
        new LeaveSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_LEAVE, new MediaItem[]{mediaItem});
    }

    private void onDialogActionItemClicked(EventContext eventContext, int i2, MediaItem mediaItem) {
        Log.sh("SharingsLongClickHandler", "onDialogActionItemClicked " + i2);
        if (i2 == 0) {
            deleteSharedAlbum(eventContext, mediaItem);
        } else if (i2 == 1) {
            renameSharedAlbum(eventContext, mediaItem);
        } else if (i2 == 10) {
            leaveSharedAlbum(eventContext, mediaItem);
        }
    }

    private void renameSharedAlbum(EventContext eventContext, MediaItem mediaItem) {
        new RenameSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_SHARED_VIEW_SPACE_DIALOG_RENAME, new MediaItem[]{mediaItem});
    }

    private void showActionDialog(IBaseListView iBaseListView, MediaItem mediaItem, String... strArr) {
        Context context = iBaseListView.getContext();
        if (context != null) {
            EventContext eventContext = iBaseListView.getEventContext();
            new AlertDialog.Builder(context).setTitle((CharSequence) getTitle(context, mediaItem)).setItems(strArr, new g(this, eventContext, mediaItem, 6)).create().show();
        }
    }

    public boolean handle(IBaseListView iBaseListView, MediaItem mediaItem) {
        Context context = iBaseListView.getContext();
        if (context == null || iBaseListView.getEventContext() == null) {
            return false;
        }
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS && MediaItemMde.isInvitation(mediaItem)) {
            return false;
        }
        if (MediaItemMde.isOwnedByMe(mediaItem)) {
            showActionDialog(iBaseListView, mediaItem, context.getString(R.string.delete), context.getString(R.string.rename));
            return true;
        } else if (MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(mediaItem))) {
            showActionDialog(iBaseListView, mediaItem, context.getString(R.string.rename));
            return true;
        } else {
            showActionDialog(iBaseListView, mediaItem, context.getString(R.string.leave));
            return true;
        }
    }
}
