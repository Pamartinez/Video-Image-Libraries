package com.samsung.android.gallery.module.cloud;

import K.a;
import N2.j;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.abstraction.SamsungCloud;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.scloud.cloudagent.CloudStore;
import com.samsung.android.scloud.cloudagent.exception.CloudException;
import com.samsung.scsp.media.Media;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SamsungCloudOImpl implements SamsungCloud {
    public boolean changeContentSyncState(Context context, boolean z) {
        try {
            return CloudStore.API.setCloudSettingsValue(context, "settings_is_sync_on", z ? 1 : 0);
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("changeContentSyncState failed. e="), "SamsungCloudOImpl");
            return false;
        }
    }

    public int copy(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(str);
        arrayList2.add(str2);
        return copy(context, (ArrayList<String>) arrayList, (ArrayList<String>) arrayList2);
    }

    public boolean delete(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        return delete(context, (ArrayList<String>) arrayList);
    }

    public ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        try {
            if (!TextUtils.isEmpty(downloadParams.getCloudServerId())) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(downloadParams.getCloudServerId());
                return CloudStore.API.downloadOriginalFileWithBlocking(context, arrayList, downloadParams.getOriginTargetPath(), true, true);
            }
            Log.d("SamsungCloudOImpl", "download cloud file", downloadParams.getCloudServerId());
            return null;
        } catch (CloudException | RuntimeException e) {
            Log.e((CharSequence) "SamsungCloudOImpl", "download failed. e=", e.getMessage());
            return null;
        } finally {
            Log.d("SamsungCloudOImpl", "download cloud file", downloadParams.getCloudServerId());
        }
    }

    public Media empty(Context context, Media media) {
        try {
            if (!TextUtils.isEmpty(media.photoId)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(media.photoId);
                if (100 == CloudStore.API.clearFilesWithBlocking(context, arrayList)) {
                    Log.d("SamsungCloudOImpl", "empty cloud file [success]");
                    return new Media();
                }
            }
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("empty failed. e="), "SamsungCloudOImpl");
        }
        Log.d("SamsungCloudOImpl", "empty cloud file [fail]");
        return null;
    }

    public DownloadCanceller getDownloadCanceller() {
        return new a(20);
    }

    public CloudDownloadMonitor getDownloadMonitor() {
        return null;
    }

    public String getDownloadUrl(Context context, long j2, String str) {
        try {
            return CloudStore.API.getDownloadURL(context, Uri.withAppendedPath(Uri.parse("content://com.samsung.android.scloud.cloudagent/data/images/media"), String.valueOf(j2)));
        } catch (CloudException | RuntimeException e) {
            A.a.s(e, new StringBuilder("getDownloadUrl failed. e="), "SamsungCloudOImpl");
            return null;
        }
    }

    public String getOriginalFilePath(String str) {
        try {
            if (TextUtils.isEmpty(str) || !str.contains("/")) {
                return str;
            }
            return str.substring(0, str.lastIndexOf("/"));
        } catch (StringIndexOutOfBoundsException e) {
            Log.e("SamsungCloudOImpl", "getOriginalFilePath failed. e=" + e.getMessage());
            return str;
        }
    }

    public String getStreamingUrl(Context context, long j2, String str) {
        try {
            return CloudStore.API.getStreamingURL(context, Uri.withAppendedPath(Uri.parse("content://com.samsung.android.scloud.cloudagent/data/video/media"), String.valueOf(j2)));
        } catch (CloudException | RuntimeException e) {
            A.a.s(e, new StringBuilder("getStreamingUrl failed. e="), "SamsungCloudOImpl");
            return null;
        }
    }

    public boolean isContentSyncOn(Context context) {
        int i2;
        try {
            Bundle cloudAgentVersion = CloudStore.API.getCloudAgentVersion(context);
            if (cloudAgentVersion != null) {
                i2 = cloudAgentVersion.getInt("version_code");
            } else {
                i2 = -1;
            }
            if (i2 >= 100000001) {
                return CloudStore.API.isCloudAvailableExceptAccount(context);
            }
            return CloudStore.API.isCloudAvailable(context);
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("isContentSyncOn failed. e="), "SamsungCloudOImpl");
            return false;
        }
    }

    public boolean isSyncOffFolder(Context context, String str) {
        try {
            return CloudStore.API.isSyncOffBucket(context, str);
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("isSyncOffFolder failed. e="), "SamsungCloudOImpl");
            return false;
        }
    }

    public int move(Context context, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(str);
        arrayList2.add(str2);
        return move(context, (ArrayList<String>) arrayList, (ArrayList<String>) arrayList2, z);
    }

    public Media restore(Context context, Media media) {
        try {
            if (!TextUtils.isEmpty(media.photoId)) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(media.photoId);
                if (100 == CloudStore.API.revertFilesWithBlocking(context, arrayList)) {
                    Log.d("SamsungCloudOImpl", "restore cloud file [success]");
                    return new Media();
                }
            }
        } catch (RuntimeException e) {
            j.u(e, new StringBuilder("restore failed. e="), "SamsungCloudOImpl");
        }
        Log.d("SamsungCloudOImpl", "restore cloud file [fail]");
        return null;
    }

    public boolean setFavorite(Context context, String str, String str2, int i2) {
        try {
            return CloudStore.API.setFavoriteWithBlocking(context, str, str2, i2);
        } catch (Exception e) {
            A.a.s(e, new StringBuilder("set favorite failed. e="), "SamsungCloudOImpl");
            return false;
        }
    }

    public boolean delete(Context context, ArrayList<String> arrayList) {
        return delete(context, arrayList, false);
    }

    public boolean delete(Context context, ArrayList<String> arrayList, boolean z) {
        boolean z3 = false;
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty()) {
                    int size = arrayList.size();
                    Log.d("SamsungCloudOImpl", "delete : size is " + size + ArcCommonLog.TAG_COMMA + z);
                    int i2 = 0;
                    boolean z7 = true;
                    while (i2 < size) {
                        int i7 = i2 + 2000;
                        z7 = z7 && CloudStore.API.deleteFileWithBlocking(context, new ArrayList(arrayList.subList(i2, Math.min(i7, size))), z);
                        i2 = i7;
                    }
                    z3 = z7;
                    A.a.v("bulk delete cloud file final [", "]", "SamsungCloudOImpl", z3);
                    return z3;
                }
            } catch (RuntimeException e) {
                j.u(e, new StringBuilder("delete failed. e="), "SamsungCloudOImpl");
            }
        }
        Log.w("SamsungCloudOImpl", "no related cloud files to force delete");
        z3 = true;
        A.a.v("bulk delete cloud file final [", "]", "SamsungCloudOImpl", z3);
        return z3;
    }

    public int copy(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        int i2 = 0;
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty() && arrayList2 != null && !arrayList2.isEmpty()) {
                    if (arrayList.size() != arrayList2.size()) {
                        return 0;
                    }
                    i2 = CloudStore.API.copyFileWithBlocking(context, arrayList, arrayList2);
                }
            } catch (RuntimeException e) {
                j.u(e, new StringBuilder("copy failed. e="), "SamsungCloudOImpl");
            }
        }
        Log.d("SamsungCloudOImpl", "copy cloud file [" + i2 + "]");
        return i2;
    }

    public int move(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z) {
        int i2 = 0;
        if (arrayList != null) {
            try {
                if (!arrayList.isEmpty() && arrayList2 != null && !arrayList2.isEmpty()) {
                    if (arrayList.size() != arrayList2.size()) {
                        return 0;
                    }
                    i2 = z ? CloudStore.API.moveFileWithBlocking(context, arrayList, arrayList2) : CloudStore.API.moveFileWithNoneBlocking(context, arrayList, arrayList2);
                }
            } catch (RuntimeException e) {
                j.u(e, new StringBuilder("move failed. e="), "SamsungCloudOImpl");
            }
        }
        Log.e("SamsungCloudOImpl", "move cloud file [" + i2 + "]");
        return i2;
    }
}
