package com.samsung.android.gallery.app.ui.menu.list;

import T6.a;
import android.content.Intent;
import android.view.MenuItem;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.OpenSamsungFamilyGroupCmd;
import com.samsung.android.gallery.app.controller.internals.ViewAsCmd;
import com.samsung.android.gallery.app.controller.remote.StartSlideshowV2Cmd;
import com.samsung.android.gallery.app.controller.sharing.AddContentsToSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.ChangeSharedAlbumCoverCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.DeleteSharedAlbumItemCmd;
import com.samsung.android.gallery.app.controller.sharing.LeaveSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RenameSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.SharedSortByDialogCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mde.MdeGroupHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingPicturesMenuHandler extends MenuHandler {
    private List<FileItemInterface> getAllItemList(EventContext eventContext) {
        return (List) Arrays.stream(eventContext.getAllItems()).collect(Collectors.toList());
    }

    private MediaItem getCurrentAlbum(EventContext eventContext) {
        MediaItem currentItem = eventContext.getCurrentItem();
        if (currentItem != null) {
            return currentItem;
        }
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        if (selectedItems == null || selectedItems.length != 1) {
            return null;
        }
        return selectedItems[0];
    }

    private MediaItem getHeaderItem(EventContext eventContext) {
        return eventContext.getHeaderItem();
    }

    private void handleCancel(EventContext eventContext) {
        ThreadUtil.postOnUiThreadDelayed(new a(eventContext, 3), 100);
    }

    private void requestDownloadSharedContent(EventContext eventContext) {
        MediaItem[] selectedItems = eventContext.getSelectedItems();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, selectedItems);
        new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_DOWNLOAD_CONTENTS, arrayList);
    }

    private void requestShowGroupDetail(EventContext eventContext) {
        MediaItem headerItem = eventContext.getHeaderItem();
        if (headerItem == null) {
            Log.d(this.TAG, "requestShowGroupDetail failed. null item");
            return;
        }
        if (!Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ALBUM) || !MdeGroupHelper.isSAFamilyGroup(MediaItemMde.getGroupId(headerItem))) {
            MdeGroupHelper.requestShowGroupDetail(eventContext.getContext(), MediaItemMde.getGroupId(headerItem), headerItem.getTitle());
        } else {
            new OpenSamsungFamilyGroupCmd().execute(eventContext, new Object[0]);
        }
        eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_DETAIL_GROUP_DETAIL);
    }

    private void startAlbumSetting(EventContext eventContext) {
        postEvent(eventContext, EventMessage.obtain(6009));
        eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_ALBUM_SETTING);
    }

    private void startShareLink(EventContext eventContext) {
        String webLinkUrl = MediaItemMde.getWebLinkUrl(getHeaderItem(eventContext));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", webLinkUrl);
        eventContext.getContext().startActivity(Intent.createChooser(intent, eventContext.getContext().getString(R.string.share_short)));
        eventContext.postAnalyticsLog(AnalyticsEventId.EVENT_SHARED_ALBUM_LINK_MENU);
    }

    public boolean onItemSelected(EventContext eventContext, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_add_content_to_sharing:
                new AddContentsToSharedAlbumCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_album_settings:
                startAlbumSetting(eventContext);
                return true;
            case R.id.action_cancel:
                handleCancel(eventContext);
                return true;
            case R.id.action_change_sharing_cover_image:
                new ChangeSharedAlbumCoverCmd().execute(eventContext, new Object[0]);
                return true;
            case R.id.action_create_story_album:
                new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_CREATE_STORY, getAllItemList(eventContext));
                return true;
            case R.id.action_delete_shared_album:
                new DeleteSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_MENU_SHARING_DELETE_ALBUM, new MediaItem[]{getHeaderItem(eventContext)});
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_download_in_sharing_album:
                requestDownloadSharedContent(eventContext);
                return true;
            case R.id.action_leave_shared_album:
                new LeaveSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_MENU_SHARING_LEAVE_ALBUM, new MediaItem[]{getHeaderItem(eventContext)});
                return true;
            case R.id.action_preview_suggestion_to_add:
                postEvent(eventContext, EventMessage.obtain(6010, Boolean.TRUE));
                return true;
            case R.id.action_remove:
                new DeleteSharedAlbumItemCmd().execute(eventContext, AnalyticsEventId.EVENT_MENU_SHARING_LEAVE_ALBUM, eventContext.getSelectedItems(), MediaItemMde.getGroupId(getHeaderItem(eventContext)));
                return true;
            case R.id.action_rename_shared_album:
                new RenameSharedAlbumCmd().execute(eventContext, AnalyticsEventId.EVENT_MENU_SHARING_RENAME_ALBUM);
                publishPopoverToolbarInfo(eventContext, menuItem.getItemId());
                return true;
            case R.id.action_select:
                postEvent(eventContext, EventMessage.obtain(1002));
                return true;
            case R.id.action_share_link_to_album:
                startShareLink(eventContext);
                return true;
            case R.id.action_show_group_detail:
                requestShowGroupDetail(eventContext);
                return true;
            case R.id.action_slideshow:
                new StartSlideshowV2Cmd().execute(eventContext, null);
                return true;
            case R.id.action_sortby:
                new SharedSortByDialogCmd().execute(eventContext, getCurrentAlbum(eventContext));
                return true;
            case R.id.action_view_as:
                new ViewAsCmd().execute(eventContext, new Object[0]);
                return true;
            default:
                return false;
        }
    }
}
