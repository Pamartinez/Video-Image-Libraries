package com.samsung.android.gallery.bixby.cmd.support;

import F3.b;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.bixby.R$string;
import com.samsung.android.gallery.bixby.bixby.search.SearchAlbumData;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelper;
import com.samsung.android.gallery.bixby.bixby.util.ActionHelperParams;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CmdUtil {
    private void showToast(Context context, String str) {
        ThreadUtil.postOnUiThread(new b(context, str, 5));
    }

    public boolean searchAlbumAndGroup(Context context, Intent intent, ActionHelperParams actionHelperParams) {
        boolean z;
        String str;
        String str2;
        SearchAlbumData queryAlbumAndGroupDataFromName = new ActionHelper().queryAlbumAndGroupDataFromName(context, actionHelperParams);
        if (!queryAlbumAndGroupDataFromName.isEmptyAlbum() || !actionHelperParams.isExcludeEmpty()) {
            z = false;
        } else {
            z = true;
        }
        int albumId = queryAlbumAndGroupDataFromName.getAlbumId();
        AlbumType albumType = queryAlbumAndGroupDataFromName.getAlbumType();
        Log.bx("CmdUtil", "searched album [" + albumId + "][" + z + "][" + albumType + "]");
        if (albumId == 0 || z) {
            if (z) {
                str = context.getString(R$string.no_items_in_album_toast);
            } else {
                str = context.getString(R$string.can_not_find_an_album_named, new Object[]{actionHelperParams.getName()});
            }
            showToast(context, str);
            intent.putExtra("bixby_locationKey", "SHOW_ALBUM_VIEW");
            return false;
        }
        intent.putExtra("ALBUM_ID", albumId);
        if (AlbumType.Folder.equals(albumType)) {
            str2 = "SEARCH_BY_GROUP";
        } else {
            str2 = "SEARCH_BY_NAME";
        }
        intent.putExtra("bixby_locationKey", str2);
        return true;
    }

    public boolean searchStory(Context context, Intent intent, String str) {
        int queryChannelIdFromName = new ActionHelper().queryChannelIdFromName(str);
        Log.bx("CmdUtil", "searched story [" + queryChannelIdFromName + "]");
        if (queryChannelIdFromName != -1) {
            intent.putExtra("start-story-detail-view", true);
            intent.putExtra("key-story-album-bucket-id", queryChannelIdFromName);
            intent.putExtra("bixby_locationKey", "SEARCH_BY_STORY");
            return true;
        }
        showToast(context, context.getString(R$string.can_not_find_a_story_named, new Object[]{str}));
        intent.putExtra("bixby_locationKey", "SHOW_STORY_VIEW");
        return false;
    }
}
