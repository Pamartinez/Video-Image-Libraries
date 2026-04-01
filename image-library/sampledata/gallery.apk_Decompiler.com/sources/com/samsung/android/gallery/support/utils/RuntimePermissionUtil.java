package com.samsung.android.gallery.support.utils;

import android.content.Context;
import android.os.Build;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class RuntimePermissionUtil {
    public static String[] AUDIO_PERMISSION_GROUP = {"android.permission.READ_MEDIA_AUDIO"};
    private static final String[] BACKUP_RESTORE_PERMISSION_GROUP;
    public static final String[] CONTACTS_PERMISSION_GROUP = {"android.permission.READ_CONTACTS", "android.permission.WRITE_CONTACTS"};
    public static final String[] DEFAULT_PERMISSION_GROUP;
    private static final String[] DEFAULT_PERMISSION_GROUP_LEGACY;
    private static final String[] DEFAULT_PERMISSION_GROUP_T;
    public static final String[] LAUNCH_PERMISSION_GROUP;
    public static final String[] LOCATION_PERMISSION_GROUP = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] NOTIFICATION_PERMISSION_GROUP = {"android.permission.POST_NOTIFICATIONS"};
    private static final Map<String, String> PERMISSION_GROUP_MAP = new HashMap<String, String>() {
        {
            put("android.permission.READ_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
            put("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission-group.STORAGE");
            if (Build.VERSION.SDK_INT >= 33) {
                put("android.permission.READ_MEDIA_IMAGES", "android.permission-group.READ_MEDIA_VISUAL");
                put("android.permission.READ_MEDIA_VIDEO", "android.permission-group.READ_MEDIA_VISUAL");
            }
            put("android.permission.READ_CONTACTS", "android.permission-group.CONTACTS");
            put("android.permission.WRITE_CONTACTS", "android.permission-group.CONTACTS");
            put("android.permission.ACCESS_COARSE_LOCATION", "android.permission-group.LOCATION");
            put("android.permission.ACCESS_FINE_LOCATION", "android.permission-group.LOCATION");
        }
    };

    static {
        String[] strArr = {"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.ACCESS_MEDIA_LOCATION"};
        DEFAULT_PERMISSION_GROUP_T = strArr;
        String[] strArr2 = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        DEFAULT_PERMISSION_GROUP_LEGACY = strArr2;
        if (Build.VERSION.SDK_INT < 33) {
            strArr = strArr2;
        }
        DEFAULT_PERMISSION_GROUP = strArr;
        LAUNCH_PERMISSION_GROUP = strArr;
        BACKUP_RESTORE_PERMISSION_GROUP = strArr;
    }

    public static ArrayList<String> getDisabledPermissionList(Context context, String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (!(str == null || context.checkSelfPermission(str) == 0)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String getPermissionGroup(String str) {
        return (String) Optional.ofNullable(PERMISSION_GROUP_MAP.get(str)).orElse("");
    }

    public static boolean hasBackupRestorePermission(Context context) {
        return hasPermission(context, BACKUP_RESTORE_PERMISSION_GROUP);
    }

    public static boolean hasLaunchPermission(Context context) {
        return hasPermission(context, LAUNCH_PERMISSION_GROUP);
    }

    public static boolean hasPermission(Context context, String[] strArr) {
        if (context == null) {
            return false;
        }
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            if (str == null || context.checkSelfPermission(str) == 0) {
                i2++;
            } else {
                Log.d("RuntimePermission", "hasPermission invalid", str);
                return false;
            }
        }
        return true;
    }

    public static boolean hasPermissionAtLeast(Context context, String[] strArr) {
        int length = strArr.length;
        int i2 = 0;
        while (i2 < length) {
            String str = strArr[i2];
            if (str == null || context.checkSelfPermission(str) != 0) {
                i2++;
            } else {
                Log.d("RuntimePermission", "hasPermission", str);
                return true;
            }
        }
        return false;
    }

    public static boolean isNotYetExistPermission(String str) {
        if (Build.VERSION.SDK_INT < 33) {
            return "android.permission.POST_NOTIFICATIONS".equals(str);
        }
        return false;
    }
}
