package com.samsung.android.gallery.module.badge;

import D3.i;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class UsbBadgeManager {
    private final List<String> mUsedUsbBadgeTable = new ArrayList();

    private static Pair<List<String>, List<String>> getBadgeVolumeData() {
        Object obj;
        String string = PreferenceCache.UsedUsbStorageBadgeTable.getString();
        if (TextUtils.isEmpty(string)) {
            obj = new ArrayList();
        } else {
            obj = (List) Arrays.stream(string.split("#")).collect(Collectors.toList());
        }
        return Pair.create(obj, (List) FileUtils.getUsbStorageVolumes(AppResources.getAppContext()).stream().map(new i(16)).collect(Collectors.toList()));
    }

    public static boolean needBadgeForUsbConnection() {
        Pair<List<String>, List<String>> badgeVolumeData = getBadgeVolumeData();
        List list = (List) badgeVolumeData.first;
        List list2 = (List) badgeVolumeData.second;
        if (list2.isEmpty() || list2.size() <= list.size()) {
            return false;
        }
        return true;
    }

    public static void updateWithBadge() {
        String str;
        Pair<List<String>, List<String>> badgeVolumeData = getBadgeVolumeData();
        List<String> list = (List) badgeVolumeData.first;
        List list2 = (List) badgeVolumeData.second;
        ArrayList arrayList = new ArrayList(list);
        for (String str2 : list) {
            if (!list2.contains(str2)) {
                arrayList.remove(str2);
            }
        }
        PreferenceCache preferenceCache = PreferenceCache.UsedUsbStorageBadgeTable;
        if (list2.isEmpty()) {
            str = "";
        } else {
            str = (String) arrayList.stream().collect(Collectors.joining("#"));
        }
        preferenceCache.setString(str);
        if (!list2.isEmpty() && list2.size() > list.size()) {
            Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), (Object) null);
        }
    }

    public boolean isUsed(String str) {
        return this.mUsedUsbBadgeTable.contains(str);
    }

    public void loadTable() {
        Collection collection;
        this.mUsedUsbBadgeTable.clear();
        String string = PreferenceCache.UsedUsbStorageBadgeTable.getString();
        if (TextUtils.isEmpty(string)) {
            collection = new ArrayList();
        } else {
            collection = (List) Arrays.stream(string.split("#")).collect(Collectors.toList());
        }
        this.mUsedUsbBadgeTable.addAll(collection);
    }

    public void use(String str) {
        if (!this.mUsedUsbBadgeTable.contains(str)) {
            this.mUsedUsbBadgeTable.add(str);
            PreferenceCache.UsedUsbStorageBadgeTable.setString((String) this.mUsedUsbBadgeTable.stream().collect(Collectors.joining("#")));
            Pair<List<String>, List<String>> badgeVolumeData = getBadgeVolumeData();
            List list = (List) badgeVolumeData.first;
            List list2 = (List) badgeVolumeData.second;
            if (!list2.isEmpty() && list2.size() == list.size()) {
                Blackboard.publishGlobal(CommandKey.DATA_REQUEST("data://badge/bottom_menu"), (Object) null);
            }
        }
    }
}
