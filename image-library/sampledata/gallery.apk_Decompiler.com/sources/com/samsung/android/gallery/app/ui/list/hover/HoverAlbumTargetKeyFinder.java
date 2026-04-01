package com.samsung.android.gallery.app.ui.list.hover;

import android.database.Cursor;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.message.Message;
import java.util.ArrayList;
import java.util.HashSet;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class HoverAlbumTargetKeyFinder {
    private final Blackboard mBlackboard;
    private String mHoverLocationKey;

    public HoverAlbumTargetKeyFinder(Blackboard blackboard) {
        this.mBlackboard = blackboard;
    }

    private String getAlbumPicturesKey(MediaItem mediaItem, int i2) {
        String requestKeyWithType = getRequestKeyWithType(mediaItem, new UriBuilder("location://albums/fileList").appendArg(Message.KEY_POSITION, i2).appendArg("type", mediaItem.getAlbumType().toInt()).build());
        this.mHoverLocationKey = requestKeyWithType;
        return requestKeyWithType;
    }

    private String getSharingPicturesKey(MediaItem mediaItem, int i2) {
        String build = new UriBuilder("location://sharing/albums/fileList").appendArg("id", MediaItemMde.getSpaceId(mediaItem)).appendArg(Message.KEY_POSITION, i2).appendArg("groupId", MediaItemMde.getGroupId(mediaItem)).appendArg("owner", MediaItemMde.isOwnedByMe(mediaItem)).build();
        if (isHoverLocationKeyEmpty()) {
            String DATA_CURSOR = DataKey.DATA_CURSOR("location://sharing/albums/fileList");
            Cursor sharedItemCursor = new MdeDatabase().getSharedItemCursor(MediaItemMde.getSpaceId(mediaItem), (String) null);
            this.mBlackboard.publish(DATA_CURSOR, new Cursor[]{sharedItemCursor});
            this.mHoverLocationKey = DATA_CURSOR;
        }
        return build;
    }

    private String getStoryPicturesKey(MediaItem mediaItem, int i2) {
        Cursor query;
        int albumID = mediaItem.getAlbumID();
        String build = new UriBuilder(C0086a.i(albumID, "location://story/albums/fileList/")).appendArg("id", albumID).appendArg(Message.KEY_POSITION, i2).build();
        if (isHoverLocationKeyEmpty()) {
            String DATA_CURSOR = DataKey.DATA_CURSOR("location://story/albums/fileList");
            Cursor query2 = DbCompat.query(DbKey.STORY_FILES, new a(albumID, 0));
            try {
                query = DbCompat.query(DbKey.STORY_FILES_DAY, new a(albumID, 1));
                this.mBlackboard.publish(DATA_CURSOR, new Cursor[]{query2, query});
                this.mHoverLocationKey = DATA_CURSOR;
                if (query != null) {
                    query.close();
                }
                if (query2 != null) {
                    query2.close();
                    return build;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        return build;
        throw th;
        throw th;
    }

    public String find(String str, MediaItem mediaItem, int i2) {
        if (str.contains("location://story/albums")) {
            return getStoryPicturesKey(mediaItem, i2);
        }
        if (mediaItem.isSharing()) {
            return getSharingPicturesKey(mediaItem, i2);
        }
        if (str.contains("location://search/fileList/Category")) {
            return str;
        }
        return getAlbumPicturesKey(mediaItem, i2);
    }

    public String getRequestKeyWithType(MediaItem mediaItem, String str) {
        HashSet<Integer> subAlbumIds;
        if (supportMergedAlbum(mediaItem)) {
            MediaItem[] albumsInFolder = mediaItem.getAlbumsInFolder();
            ArrayList arrayList = new ArrayList();
            for (MediaItem albumID : albumsInFolder) {
                arrayList.add(Integer.valueOf(albumID.getAlbumID()));
            }
            if (arrayList.size() == 0 && (subAlbumIds = AlbumInfo.getSubAlbumIds(mediaItem.getAlbumID())) != null) {
                arrayList.addAll(subAlbumIds);
            }
            return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(str, "mergedAlbumId", String.valueOf(mediaItem.getAlbumID())), "ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
        }
        String appendArgs = ArgumentsUtil.appendArgs(str, "id", String.valueOf(mediaItem.getAlbumID()));
        if (isMxAlbums() && BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        }
        return ArgumentsUtil.appendArgs(appendArgs, "type", String.valueOf(mediaItem.getAlbumType().toInt()));
    }

    public boolean isHoverLocationKeyEmpty() {
        if (this.mHoverLocationKey == null) {
            return true;
        }
        return false;
    }

    public boolean isMxAlbums() {
        return PreferenceFeatures.OneUi5x.MX_ALBUMS;
    }

    public void onRemoveHoverPreview() {
        if (!isHoverLocationKeyEmpty()) {
            this.mBlackboard.erase(this.mHoverLocationKey);
            this.mHoverLocationKey = null;
        }
    }

    public boolean supportMergedAlbum(MediaItem mediaItem) {
        if (!isMxAlbums() || !mediaItem.isMergedAlbum()) {
            return false;
        }
        return true;
    }
}
