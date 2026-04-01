package com.samsung.android.gallery.app.ui.container.tablet.drawertab;

import D6.a;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.UriBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DrawerDefaultItemBuilder {
    private boolean mMtpTabEnabled;
    private boolean mSharedAlbumsTabEnabled;
    private boolean mUsbTabEnabled;
    private Map<String, Integer> mUsbVolumeTable;

    private boolean supportCollectionTab() {
        return PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    }

    private boolean supportStoryTab() {
        if (PreferenceFeatures.OneUi8x.COLLECTION_TAB || !Features.isEnabled(Features.SUPPORT_STORY)) {
            return false;
        }
        return true;
    }

    private boolean supportSuggestionsTab() {
        return Features.isEnabled(Features.SUPPORT_SUGGESTIONS);
    }

    public List<DrawerTabItem> build() {
        Map<String, Integer> map;
        boolean z;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DrawerTabItem("location://timeline"));
        arrayList.add(new DrawerTabItem("location://albums"));
        if (supportStoryTab()) {
            arrayList.add(new DrawerTabItem("location://story/albums"));
        }
        if (supportCollectionTab()) {
            arrayList.add(new DrawerTabItem("location://collection"));
        }
        arrayList.add(new DrawerTabItem("location://virtual/album/video/fileList"));
        arrayList.add(new DrawerTabItem(DrawerTabItem.getFavoriteLocation(), DrawerTabItem.getMxVirtualAlbumItem(BucketUtils.VirtualBucketHolder.favorite)));
        arrayList.add(new DrawerTabItem(DrawerTabItem.getRecentLocation(), DrawerTabItem.getMxVirtualAlbumItem(BucketUtils.VirtualBucketHolder.recent)));
        if (!PreferenceFeatures.OneUi8x.IS_ONE_UI_85 && supportSuggestionsTab()) {
            arrayList.add(new DrawerTabItem("location://suggestions"));
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_BOTTOM_TAB_SHOT_MODE_MENU) {
            arrayList.add(new DrawerTabItem("location://search/fileList/Category/ShotMode"));
        } else if (Features.isEnabled(Features.SUPPORT_LOCATION)) {
            arrayList.add(new DrawerTabItem("location://search/fileList/Category/Location"));
        }
        if (this.mSharedAlbumsTabEnabled) {
            arrayList.add(new DrawerTabItem("location://sharing/albums"));
        }
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            arrayList.add(new DrawerTabItem("location://albums/private/fileList"));
        }
        arrayList.add(new DrawerTabItem("location://trash"));
        if (Features.isEnabled(Features.SUPPORT_VIDEO_STUDIO)) {
            DrawerTabItem drawerTabItem = new DrawerTabItem("location:///VideoStudio");
            if (this.mMtpTabEnabled || this.mUsbTabEnabled) {
                z = true;
            } else {
                z = false;
            }
            drawerTabItem.setEnableDivider(z);
            arrayList.add(drawerTabItem);
        }
        if (this.mMtpTabEnabled) {
            arrayList.add(new DrawerTabItem("location://mtp"));
        }
        if (this.mUsbTabEnabled && (map = this.mUsbVolumeTable) != null) {
            int intValue = map.values().stream().max(new a(26)).orElse(0).intValue();
            for (StorageVolumeCompat next : FileUtils.getUsbStorageVolumes(AppResources.getAppContext())) {
                intValue++;
                arrayList.add(new DrawerTabItem(new UriBuilder("location://albums/otg/fileList").appendArg("id", ((Integer) Optional.ofNullable(this.mUsbVolumeTable.get(next.name)).orElse(Integer.valueOf(intValue))).intValue()).appendArg("name", next.name).appendArg("directory", next.directory + "/DCIM/Camera/").build()));
            }
        }
        return arrayList;
    }

    public DrawerDefaultItemBuilder setMtpTabEnabled(boolean z) {
        this.mMtpTabEnabled = z;
        return this;
    }

    public DrawerDefaultItemBuilder setSharedAlbumsTabEnabled(boolean z) {
        this.mSharedAlbumsTabEnabled = z;
        return this;
    }

    public DrawerDefaultItemBuilder setUsbTabEnabled(boolean z) {
        this.mUsbTabEnabled = z;
        return this;
    }

    public DrawerDefaultItemBuilder setUsbVolumeTable(Map<String, Integer> map) {
        this.mUsbVolumeTable = map;
        return this;
    }
}
