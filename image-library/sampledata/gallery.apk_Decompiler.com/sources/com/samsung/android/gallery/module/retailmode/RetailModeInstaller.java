package com.samsung.android.gallery.module.retailmode;

import R6.c;
import T3.e;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.settings.SettingPreference;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import org.json.JSONArray;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RetailModeInstaller {
    private static final RetailModeInstaller sInstance = new RetailModeInstaller();

    private boolean checkInvalidTabType(int i2) {
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            return true;
        }
        return false;
    }

    private boolean checkInvalidViewDepth(int i2) {
        if (i2 < 1 || i2 > 5) {
            return false;
        }
        return true;
    }

    public static RetailModeInstaller getInstance() {
        return sInstance;
    }

    private int getSortByValue(Intent intent) {
        int i2;
        int intExtra = intent.getIntExtra("sortby_from_retail", 0);
        if (intExtra == 1) {
            i2 = 10;
        } else if (intExtra == 2) {
            i2 = 20;
        } else if (intExtra != 3) {
            i2 = 0;
        } else {
            i2 = 30;
        }
        Log.i("RetailModeInstaller", "sortby_from_retail=" + i2);
        int intExtra2 = intent.getIntExtra("order_from_retail", 0);
        if (intExtra2 == 1) {
            i2++;
        } else if (intExtra2 == 2) {
            i2 += 2;
        }
        Log.i("RetailModeInstaller", "order_from_retail=" + i2);
        if (i2 != 0) {
            return i2;
        }
        return 12;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setup$0(Context context, Intent intent) {
        PreferenceFeatures.setEnabled(PreferenceFeatures.RetailMode, true);
        SettingPreference.EssentialAlbum.setAndNotifyIfChanged(context, false);
        setCurrentTabType(intent);
        setViewDepth(intent);
        setAlbumSetWithSortBy(intent);
    }

    private void savePreference(Intent intent, String str, Object obj, boolean z) {
        if (intent.hasExtra("test") && intent.getBooleanExtra("test", false)) {
            return;
        }
        if (z) {
            GalleryPreference.getInstance().saveSortBy(str, ((Integer) obj).intValue());
        } else if (obj instanceof Integer) {
            GalleryPreference.getInstance().saveState(str, ((Integer) obj).intValue());
        } else if (obj instanceof String) {
            GalleryPreference.getInstance().saveState(str, (String) obj);
        }
    }

    public void demoReset(Context context) {
        try {
            Optional.ofNullable((ActivityManager) context.getSystemService("activity")).ifPresent(new e(6));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean setAlbumSetWithSortBy(Intent intent) {
        if (intent.hasExtra("albumset_from_retail")) {
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("albumset_from_retail");
            if (stringArrayListExtra == null || stringArrayListExtra.isEmpty()) {
                return false;
            }
            int sortByValue = getSortByValue(intent);
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = stringArrayListExtra.iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next.contains("/storage/emulated/0")) {
                    return false;
                }
                String concat = "/storage/emulated/0".concat(next);
                jSONArray.put(concat);
                savePreference(intent, String.valueOf(FileUtils.getBucketId(concat)), Integer.valueOf(sortByValue), true);
            }
            Log.i("RetailModeInstaller", "albumset_from_retail=" + Logger.getEncodedString(jSONArray.toString()));
            savePreference(intent, PreferenceName.RETAIL_ALBUM_SET.key(), jSONArray.toString(), false);
        }
        return true;
    }

    public boolean setCurrentTabType(Intent intent) {
        String str;
        if (intent.hasExtra("defaultview_tab_from_retail")) {
            int intExtra = intent.getIntExtra("defaultview_tab_from_retail", 0);
            if (!checkInvalidTabType(intExtra)) {
                return false;
            }
            Log.i("RetailModeInstaller", "defaultview_tab_from_retail=" + intExtra);
            if (1 == intExtra) {
                str = "location://timeline";
            } else if (2 == intExtra) {
                str = "location://albums";
            } else {
                str = "location://story/albums";
            }
            savePreference(intent, "location://variable/currentv1", str, false);
        }
        return true;
    }

    public boolean setViewDepth(Intent intent) {
        if (intent.hasExtra("defaultview_level_from_retail")) {
            int intExtra = intent.getIntExtra("defaultview_level_from_retail", 1);
            if (!checkInvalidViewDepth(intExtra)) {
                return false;
            }
            Log.i("RetailModeInstaller", "defaultview_level_from_retail=" + intExtra);
            savePreference(intent, PreferenceName.ALBUMS_GRID_SIZE.key(), Integer.valueOf(intExtra - 1), false);
        }
        if (intent.hasExtra("albumview_level_from_retail")) {
            int intExtra2 = intent.getIntExtra("albumview_level_from_retail", 1);
            if (!checkInvalidViewDepth(intExtra2)) {
                return false;
            }
            Log.i("RetailModeInstaller", "albumview_level_from_retail=" + intExtra2);
            savePreference(intent, PreferenceName.ALBUM_PICTURES_GRID_SIZE.key(), Integer.valueOf(intExtra2 - 1), false);
        }
        return true;
    }

    public void setup(Context context, Intent intent) {
        new Thread(new c(this, context, intent, 7)).start();
    }
}
