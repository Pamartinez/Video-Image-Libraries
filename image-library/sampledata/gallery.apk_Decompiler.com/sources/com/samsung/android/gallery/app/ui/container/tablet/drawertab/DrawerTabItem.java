package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.module.album.AlbumInfo;
import com.samsung.android.gallery.module.album.AlbumTitleHelper;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerTabItem {
    private static final int STUDIO_ICON_RES;
    private static final int SUGGESTIONS_ICON_RES;
    private static final int SUGGESTIONS_TITLE_RES;
    private static final boolean SUPPORT_MX_VIRTUAL_ALBUM = PreferenceFeatures.OneUi5x.MX_ALBUMS;
    private static final HashMap<String, DefaultItemInfo> sDefaultItemInfo;
    private MediaItem mAlbumItem;
    private int mDefaultOrder;
    private int mDepth;
    private boolean mEnableDivider;
    private int mIconResId;
    private boolean mIsDragFocused;
    private boolean mIsEnabled;
    private boolean mIsExpandable;
    private boolean mIsExpanded;
    private boolean mIsSelectFocused;
    private String mLocationKey;
    private boolean mShowNewBadge;
    private boolean mSupportExpand;
    private String mTitle;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class DefaultItemInfo {
        final int defaultOrder;
        final int iconRes;
        final boolean supportExpand;
        final int titleRes;

        public DefaultItemInfo(int i2, int i7, boolean z, int i8) {
            this.iconRes = i2;
            this.titleRes = i7;
            this.supportExpand = z;
            this.defaultOrder = i8;
        }
    }

    static {
        int i2;
        int i7;
        int i8;
        boolean z = PreferenceFeatures.OneUi7x.IS_ONE_UI_70;
        if (z) {
            i2 = R.drawable.drawer_tab_ic_clean_out;
        } else {
            i2 = R.drawable.gallery_ic_drawer_suggestions;
        }
        SUGGESTIONS_ICON_RES = i2;
        if (z) {
            i7 = R.string.bottom_tab_drawer_clean_out;
        } else {
            i7 = R.string.bottom_tab_drawer_suggestions;
        }
        SUGGESTIONS_TITLE_RES = i7;
        boolean z3 = PreferenceFeatures.OneUi8x.IS_ONE_UI_85;
        if (z3) {
            i8 = R.drawable.ic_samsung_studio;
        } else {
            i8 = R.drawable.gallery_ic_drawer_videoeditor;
        }
        STUDIO_ICON_RES = i8;
        HashMap<String, DefaultItemInfo> hashMap = new HashMap<>();
        sDefaultItemInfo = hashMap;
        hashMap.put("location://timeline", new DefaultItemInfo(R.drawable.drawer_tab_ic_pictures, R.string.pictures, false, 0));
        hashMap.put("location://albums", new DefaultItemInfo(R.drawable.drawer_tab_ic_albums, R.string.tab_tag_albums, true, 1));
        hashMap.put("location://story/albums", new DefaultItemInfo(R.drawable.drawer_tab_ic_stories, R.string.tab_tag_events, false, 2));
        hashMap.put("location://collection", new DefaultItemInfo(R.drawable.drawer_tab_ic_stories, R.string.collection, false, 3));
        hashMap.put("location://search", new DefaultItemInfo(R.drawable.gallery_ic_search_keyword, R.string.search, false, 4));
        hashMap.put("location://virtual/album/video/fileList", new DefaultItemInfo(R.drawable.drawer_tab_ic_video, R.string.smart_album_videos, false, 5));
        hashMap.put(getFavoriteLocation(), new DefaultItemInfo(R.drawable.drawer_tab_ic_favorites, R.string.smart_album_favorites, false, 6));
        hashMap.put(getRecentLocation(), new DefaultItemInfo(R.drawable.drawer_tab_ic_recent, R.string.recently_added, false, 7));
        if (PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
            hashMap.put("location://search/fileList/Category/ShotMode", new DefaultItemInfo(R.drawable.gallery_ic_shot_types, R.string.shot_types, false, 8));
        } else {
            hashMap.put("location://search/fileList/Category/Location", new DefaultItemInfo(R.drawable.drawer_tab_ic_locations, R.string.searchview_location, false, 8));
        }
        int i10 = 9;
        if (!PreferenceFeatures.OneUi8x.REMOVE_SHARED_DRAWER_TAB_MENU) {
            hashMap.put("location://sharing/albums", new DefaultItemInfo(R.drawable.drawer_tab_ic_shared_album, R.string.shared_albums, false, 9));
            i10 = 10;
        }
        if (!z3 && Features.isEnabled(Features.SUPPORT_SUGGESTIONS)) {
            hashMap.put("location://suggestions", new DefaultItemInfo(i2, i7, false, i10));
            i10++;
        }
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            hashMap.put("location://albums/private/fileList", new DefaultItemInfo(R.drawable.gallery_ic_menu_locked, R.string.private_album, false, i10));
            i10++;
        }
        hashMap.put("location://trash", new DefaultItemInfo(R.drawable.drawer_tab_ic_trash, R.string.trash, false, i10));
        hashMap.put("location:///VideoStudio", new DefaultItemInfo(i8, R.string.video_studio_app_name, false, i10 + 1));
        hashMap.put("location://mtp", new DefaultItemInfo(R.drawable.drawer_tab_ic_mtp, R.string.set_label_mtp_devices, false, i10 + 2));
        hashMap.put("location://albums/otg/fileList", new DefaultItemInfo(R.drawable.drawer_tab_ic_mtp, R.string.usb_storage, false, i10 + 3));
    }

    public DrawerTabItem(String str) {
        this.mDefaultOrder = -1;
        this.mIsEnabled = true;
        this.mIsExpandable = true;
        this.mLocationKey = str;
        Optional.ofNullable(sDefaultItemInfo.get(str.startsWith("location://albums/otg/fileList") ? ArgumentsUtil.removeArgs(str) : str)).ifPresent(new h(1, this));
    }

    private String getAlbumPicturesRequestKey() {
        HashSet<Integer> subAlbumIds;
        String appendArgs = ArgumentsUtil.appendArgs("location://albums/fileList", "count", String.valueOf(this.mAlbumItem.getCount()));
        if (PreferenceFeatures.OneUi5x.MX_ALBUMS) {
            appendArgs = ArgumentsUtil.appendArgs(appendArgs, "type", String.valueOf(this.mAlbumItem.getAlbumType().toInt()));
            if (this.mAlbumItem.isMergedAlbum()) {
                MediaItem[] albumsInFolder = this.mAlbumItem.getAlbumsInFolder();
                ArrayList arrayList = new ArrayList();
                for (MediaItem albumID : albumsInFolder) {
                    arrayList.add(Integer.valueOf(albumID.getAlbumID()));
                }
                if (arrayList.size() == 0 && (subAlbumIds = AlbumInfo.getSubAlbumIds(this.mAlbumItem.getAlbumID())) != null) {
                    arrayList.addAll(subAlbumIds);
                }
                return ArgumentsUtil.appendArgs(ArgumentsUtil.appendArgs(appendArgs, "mergedAlbumId", String.valueOf(this.mAlbumItem.getAlbumID())), "ids", TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
            } else if (BucketUtils.isFavourite(this.mAlbumItem.getAlbumID())) {
                appendArgs = ArgumentsUtil.appendArgs(appendArgs, "with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
            }
        }
        return ArgumentsUtil.appendArgs(appendArgs, "id", String.valueOf(this.mAlbumItem.getAlbumID()));
    }

    public static String getFavoriteLocation() {
        if (SUPPORT_MX_VIRTUAL_ALBUM) {
            return new UriBuilder("location://albums/fileList/mxVirtual/favorite").appendArg("id", BucketUtils.VirtualBucketHolder.favorite).appendArg("show_album_info", false).appendArg("album_split_blocked", true).appendArg("with_group", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE).appendArg("type", AlbumType.Virtual.toInt()).build();
        }
        return "location://virtual/album/favorite/fileList";
    }

    private String getFoldersRequestKey() {
        return ArgumentsUtil.appendArgs("location://folder/root/" + this.mAlbumItem.getFolderID(), "id", String.valueOf(this.mAlbumItem.getFolderID()));
    }

    public static MediaItem getMxVirtualAlbumItem(int i2) {
        if (SUPPORT_MX_VIRTUAL_ALBUM) {
            return MediaItemBuilder.createVirtualAlbum(i2, AlbumTitleHelper.getAlbumTitle(i2));
        }
        return null;
    }

    public static String getRecentLocation() {
        if (SUPPORT_MX_VIRTUAL_ALBUM) {
            return new UriBuilder("location://albums/fileList/mxVirtual/recent").appendArg("id", BucketUtils.VirtualBucketHolder.recent).appendArg("show_album_info", false).appendArg("album_split_blocked", true).appendArg("type", AlbumType.Virtual.toInt()).build();
        }
        return "location://virtual/album/recently/fileList";
    }

    private boolean isFolder() {
        MediaItem mediaItem = this.mAlbumItem;
        if (mediaItem == null || !mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(DefaultItemInfo defaultItemInfo) {
        this.mIconResId = defaultItemInfo.iconRes;
        if (this.mLocationKey.startsWith("location://albums/otg/fileList")) {
            int argValue = ArgumentsUtil.getArgValue(this.mLocationKey, "id", 1);
            this.mTitle = AppResources.getString(defaultItemInfo.titleRes) + " " + argValue;
        } else {
            this.mTitle = AppResources.getString(defaultItemInfo.titleRes);
        }
        this.mSupportExpand = defaultItemInfo.supportExpand;
        this.mDefaultOrder = defaultItemInfo.defaultOrder;
        this.mIsSelectFocused = this.mLocationKey.equals(LocationKey.getCurrentLocation());
    }

    public MediaItem getAlbumItem() {
        return this.mAlbumItem;
    }

    public String getCountString() {
        MediaItem mediaItem;
        if (!supportCount() || (mediaItem = this.mAlbumItem) == null) {
            return "";
        }
        return StringCompat.toReadableNumber((long) mediaItem.getCount());
    }

    public int getDefaultOrder() {
        return this.mDefaultOrder;
    }

    public int getDepth() {
        return this.mDepth;
    }

    public int getIconResId() {
        return this.mIconResId;
    }

    public String getLocationKey() {
        MediaItem mediaItem;
        String str;
        if (this.mLocationKey == null && (mediaItem = this.mAlbumItem) != null) {
            if (mediaItem.isFolder()) {
                str = getFoldersRequestKey();
            } else {
                str = getAlbumPicturesRequestKey();
            }
            this.mLocationKey = str;
        }
        return this.mLocationKey;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public long getUniqueId() {
        int hashCode;
        if (LocationKey.isMxVirtualAlbum(this.mLocationKey)) {
            hashCode = hashCode();
        } else {
            MediaItem mediaItem = this.mAlbumItem;
            if (mediaItem != null) {
                hashCode = mediaItem.getAlbumID();
            } else {
                hashCode = hashCode();
            }
        }
        return (long) hashCode;
    }

    public boolean isAlbums() {
        return LocationKey.isAlbumsMatch(getLocationKey());
    }

    public boolean isAllAlbums() {
        return LocationKey.isAllAlbumsMatch(getLocationKey());
    }

    public boolean isClickable() {
        if (!isEnabled() || isSelectFocused()) {
            return false;
        }
        return true;
    }

    public boolean isCollection() {
        return "location://collection".equals(getLocationKey());
    }

    public boolean isDragFocused() {
        return this.mIsDragFocused;
    }

    public boolean isEnableDivider() {
        return this.mEnableDivider;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public boolean isExpandable() {
        if (!this.mSupportExpand || !this.mIsExpandable) {
            return false;
        }
        return true;
    }

    public boolean isExpanded() {
        return this.mIsExpanded;
    }

    public boolean isSelectFocused() {
        return this.mIsSelectFocused;
    }

    public boolean isStories() {
        return "location://story/albums".equals(getLocationKey());
    }

    public boolean isSuggestions() {
        return "location://suggestions".equals(getLocationKey());
    }

    public boolean isUsb() {
        if (getLocationKey() == null || !getLocationKey().startsWith("location://albums/otg/fileList")) {
            return false;
        }
        return true;
    }

    public boolean isVideoStudio() {
        return "location:///VideoStudio".equals(getLocationKey());
    }

    public boolean isVideos() {
        return "location://virtual/album/video/fileList".equals(getLocationKey());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002d, code lost:
        if (r6.mAlbumItem.getAlbumID() == r7) goto L_0x0020;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean needNewAlbumLabel(int r7) {
        /*
            r6 = this;
            com.samsung.android.gallery.module.data.MediaItem r0 = r6.mAlbumItem
            r1 = 0
            if (r0 == 0) goto L_0x003a
            boolean r0 = r0.isFolder()
            r2 = 1
            if (r0 == 0) goto L_0x0027
            com.samsung.android.gallery.module.data.MediaItem r0 = r6.mAlbumItem
            com.samsung.android.gallery.module.data.MediaItem[] r0 = r0.getAlbumsInFolder()
            if (r0 == 0) goto L_0x0025
            int r3 = r0.length
            r4 = r1
        L_0x0016:
            if (r4 >= r3) goto L_0x0025
            r5 = r0[r4]
            int r5 = r5.getAlbumID()
            if (r5 != r7) goto L_0x0022
        L_0x0020:
            r7 = r2
            goto L_0x0030
        L_0x0022:
            int r4 = r4 + 1
            goto L_0x0016
        L_0x0025:
            r7 = r1
            goto L_0x0030
        L_0x0027:
            com.samsung.android.gallery.module.data.MediaItem r0 = r6.mAlbumItem
            int r0 = r0.getAlbumID()
            if (r0 != r7) goto L_0x0025
            goto L_0x0020
        L_0x0030:
            boolean r0 = r6.showNewBadge()
            if (r0 == r7) goto L_0x003a
            r6.setShowNewBadge(r7)
            return r2
        L_0x003a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.container.tablet.drawertab.DrawerTabItem.needNewAlbumLabel(int):boolean");
    }

    public void setAlbumItem(MediaItem mediaItem) {
        this.mAlbumItem = mediaItem;
    }

    public void setDragFocused(boolean z) {
        this.mIsDragFocused = z;
    }

    public void setEnableDivider(boolean z) {
        this.mEnableDivider = z;
    }

    public void setEnabled(boolean z) {
        this.mIsEnabled = z;
    }

    public void setExpandable(boolean z) {
        this.mIsExpandable = z;
    }

    public void setExpanded(boolean z) {
        this.mIsExpanded = z;
    }

    public void setSelectFocused(boolean z) {
        this.mIsSelectFocused = z;
    }

    public void setShowNewBadge(boolean z) {
        this.mShowNewBadge = z;
    }

    public boolean showNewBadge() {
        return this.mShowNewBadge;
    }

    public boolean supportCount() {
        MediaItem mediaItem = this.mAlbumItem;
        if (mediaItem == null || mediaItem.isFolder()) {
            return false;
        }
        return true;
    }

    public boolean supportExpand() {
        return this.mSupportExpand;
    }

    public boolean supportPopupMenu() {
        if (isAlbums() || isAllAlbums() || isFolder()) {
            return true;
        }
        return false;
    }

    public void updateLocationKey(String str) {
        this.mLocationKey = str;
    }

    public DrawerTabItem(String str, MediaItem mediaItem) {
        this(str);
        this.mAlbumItem = mediaItem;
    }

    public DrawerTabItem(MediaItem mediaItem, int i2) {
        this.mDefaultOrder = -1;
        boolean z = true;
        this.mIsEnabled = true;
        this.mIsExpandable = true;
        this.mAlbumItem = mediaItem;
        this.mDepth = i2;
        this.mIconResId = mediaItem.isFolder() ? R.drawable.drawer_tab_ic_group : R.drawable.drawer_tab_ic_folders;
        this.mTitle = mediaItem.getDisplayName();
        this.mSupportExpand = (!mediaItem.isFolder() || mediaItem.getItemsInFolder().length <= 0) ? false : z;
    }
}
