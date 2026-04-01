package com.samsung.android.gallery.support.utils;

import N2.j;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefFileCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SefFileUtils {
    public static void deleteBurstShotData(String str) {
        try {
            SecureFile secureFile = new SecureFile(str);
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            sefFileCompat.deleteData(secureFile, SefInfo.BURST_SHOT_INFO.keyName);
            sefFileCompat.deleteData(secureFile, SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName);
        } catch (IOException e) {
            j.r(e, new StringBuilder("deleteBurstShotData failed e="), "SefFileUtils");
        }
    }

    public static void deleteSingleTakenShotData(String str) {
        try {
            SecureFile secureFile = new SecureFile(str);
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            sefFileCompat.deleteData(secureFile, SefInfo.SINGLE_TAKE_SHOT_INFO.keyName);
            sefFileCompat.deleteData(secureFile, SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName);
        } catch (IOException e) {
            j.r(e, new StringBuilder("deleteSingleTakenShotData failed e="), "SefFileUtils");
        }
    }

    public static Map<String, byte[]> getBurstShotSefDataMap(int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(SefInfo.BURST_SHOT_INFO.keyName, String.valueOf(i2).getBytes());
        hashMap.put(SefInfo.BURST_SHOT_BEST_PHOTO_INFO.keyName, SefInfo.Data.BURST_SHOT_BEST_PHOTO_DATA);
        return hashMap;
    }

    public static Map<String, byte[]> getSingleTakenSefDataMap(int i2) {
        HashMap hashMap = new HashMap();
        byte[] bytes = String.valueOf(i2).getBytes();
        hashMap.put(SefInfo.SINGLE_TAKE_SHOT_INFO.keyName, bytes);
        hashMap.put(SefInfo.SINGLE_TAKE_BEST_PHOTO_INFO.keyName, bytes);
        return hashMap;
    }

    public static boolean updateFileWithSef(Map<String, byte[]> map, String str) {
        try {
            SecureFile secureFile = new SecureFile(str);
            SefFileCompat sefFileCompat = SeApiCompat.getSefFileCompat();
            for (Map.Entry next : map.entrySet()) {
                if (sefFileCompat.hasData((File) secureFile, (String) next.getKey())) {
                    sefFileCompat.addData((File) secureFile, SefInfo.of((String) next.getKey()), (byte[]) next.getValue());
                }
            }
            return true;
        } catch (IOException e) {
            j.r(e, new StringBuilder("updateFileWithSef failed e="), "SefFileUtils");
            return false;
        }
    }
}
