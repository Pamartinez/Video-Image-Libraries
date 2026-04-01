package com.samsung.android.gallery.app.ui.container.menu;

import Ad.C0720a;
import D6.a;
import android.content.Context;
import com.samsung.android.gallery.app.ui.container.menu.BottomMenuItem;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import f7.C0455a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BottomMenuFactory {
    private static final Map<String, Integer> sUsbVolumeTable = new HashMap();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class MenuHolder {
        public static final BottomMenuItem FAVORITES = new BottomMenuItem.Favorite();
        public static final BottomMenuItem LOCATION = new BottomMenuItem.Location();
        public static final BottomMenuItem MTP = new BottomMenuItem.Mtp();
        public static final BottomMenuItem PRIVATE_ALBUM = new BottomMenuItem.PrivateAlbum();
        public static final BottomMenuItem RECENT = new BottomMenuItem.Recent();
        public static final BottomMenuItem SETTINGS = new BottomMenuItem.Settings();
        public static final BottomMenuItem SHARED = new BottomMenuItem.Shared();
        public static final BottomMenuItem SHOT_MODE = new BottomMenuItem.ShotModes();
        public static final BottomMenuItem STUDIO = new BottomMenuItem.Studio();
        public static final BottomMenuItem SUGGESTIONS = new BottomMenuItem.Suggestion();
        public static final BottomMenuItem TRASH = new BottomMenuItem.Trash();
        public static final BottomMenuItem VIDEOS = new BottomMenuItem.Video();
        static final List<BottomMenuItem> list = listOf();

        private static List<BottomMenuItem> listOf() {
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
                return Arrays.asList(new BottomMenuItem[]{VIDEOS, FAVORITES, RECENT, SHOT_MODE, PRIVATE_ALBUM, TRASH, SETTINGS, STUDIO});
            }
            if (PreferenceFeatures.OneUi8x.IS_ONE_UI_80) {
                return Arrays.asList(new BottomMenuItem[]{VIDEOS, FAVORITES, RECENT, SHOT_MODE, LOCATION, SHARED, SUGGESTIONS, TRASH, SETTINGS, MTP, PRIVATE_ALBUM});
            }
            return Arrays.asList(new BottomMenuItem[]{VIDEOS, FAVORITES, RECENT, SUGGESTIONS, LOCATION, SHARED, TRASH, SETTINGS, MTP, SHOT_MODE, PRIVATE_ALBUM});
        }
    }

    public static ArrayList<BottomMenuItem> create(Context context) {
        ArrayList<BottomMenuItem> arrayList = (ArrayList) MenuHolder.list.stream().filter(new C0455a(context, 1)).collect(Collectors.toCollection(new C0720a(1)));
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            int size = BottomTabItemDecorationFactory.BOTTOM_MENU_CIRCLE_VIEW_SLOT - arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList.add(new BottomMenuItem.Dummy());
            }
            BottomMenuItem bottomMenuItem = MenuHolder.MTP;
            if (bottomMenuItem.support(context)) {
                arrayList.add(bottomMenuItem);
            }
        }
        if (Features.isEnabled(Features.SUPPORT_USB_STORAGE)) {
            updateUsbVolumes(context);
            List<StorageVolumeCompat> usbStorageVolumes = FileUtils.getUsbStorageVolumes(context);
            if (!usbStorageVolumes.isEmpty()) {
                int intValue = sUsbVolumeTable.values().stream().max(new a(26)).orElse(0).intValue();
                for (StorageVolumeCompat next : usbStorageVolumes) {
                    intValue++;
                    arrayList.add(new BottomMenuItem.UsbStorage(next, ((Integer) Optional.ofNullable(sUsbVolumeTable.get(next.name)).orElse(Integer.valueOf(intValue))).intValue(), usbStorageVolumes.size()));
                }
            }
        }
        return arrayList;
    }

    private static void updateUsbVolumes(Context context) {
        List<StorageVolumeCompat> usbStorageVolumes = FileUtils.getUsbStorageVolumes(context);
        if (!usbStorageVolumes.isEmpty()) {
            int intValue = sUsbVolumeTable.values().stream().max(new a(26)).orElse(0).intValue();
            for (StorageVolumeCompat next : usbStorageVolumes) {
                Map<String, Integer> map = sUsbVolumeTable;
                if (!map.containsKey(next.name)) {
                    intValue++;
                    map.put(next.name, Integer.valueOf(intValue));
                }
            }
            return;
        }
        sUsbVolumeTable.clear();
    }
}
