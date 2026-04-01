package com.samsung.android.gallery.module.data;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.mtp.MtpDevice;
import android.mtp.MtpObjectInfo;
import android.mtp.MtpStorageInfo;
import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.module.mtp.MtpClientListener;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.UriBuilder;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MtpMediaItemLoader extends MediaItemLoader {
    private static final String[] MTP_DEVICE_ITEM_LIST_PROJECTION = {"__absID", "__mediaType", "__mimeType", "__Title", "__absPath", "__dateTaken", "__size", "__width", "__height", "__storageType"};
    private static final String[] MTP_DEVICE_LIST_PROJECTION = {"__albumID", "__Title", "__absPath", "__mediaType", "__storageType"};
    private static boolean sDeviceLoadRequested = false;
    private static boolean sItemLoadRequested = false;

    private static void dismissDialog(Blackboard blackboard) {
        blackboard.publish("command://DismissSpinner", (Object) null);
    }

    private static String getBasePath(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            return cacheDir.getAbsolutePath();
        }
        return "";
    }

    private static String getMimeTypeFromFormat(int i2) {
        if (i2 == 14337) {
            return "image/jpeg";
        }
        if (i2 == 14340) {
            return "image/bmp";
        }
        if (i2 == 14347) {
            return "image/png";
        }
        if (i2 == 14343) {
            return "image/gif";
        }
        if (i2 != 14344) {
            return "unknown";
        }
        return "image/jpeg";
    }

    public static Cursor getMtpDeviceCursor(Context context) {
        Log.mt("MtpMediaItemLoader", "create mtp device list cursor start [" + sDeviceLoadRequested + "]");
        if (sDeviceLoadRequested) {
            Log.mtw("MtpMediaItemLoader", "create mtp device list cursor already requested, so skip.");
            return null;
        }
        sDeviceLoadRequested = true;
        MtpClient instance = MtpClient.getInstance(context);
        List<MtpDevice> deviceList = instance.getDeviceList();
        MatrixCursor matrixCursor = new MatrixCursor(MTP_DEVICE_LIST_PROJECTION);
        String basePath = getBasePath(context);
        for (MtpDevice next : deviceList) {
            String deviceName = next.getDeviceName();
            if (instance.getStorageList(deviceName) != null) {
                matrixCursor.addRow(new Object[]{Integer.valueOf(next.getDeviceId()), instance.getDeviceModelName(deviceName), C0212a.B(basePath, "#", deviceName), Integer.valueOf(MediaType.Image.toInt()), Integer.valueOf(StorageType.Mtp.toInt())});
            }
        }
        Log.mt("MtpMediaItemLoader", "create mtp device list cursor end [" + matrixCursor.getCount() + "]");
        if (matrixCursor.getCount() == 0) {
            Log.mte("MtpMediaItemLoader", instance.dump());
        }
        sDeviceLoadRequested = false;
        return matrixCursor;
    }

    /* JADX WARNING: type inference failed for: r2v23, types: [java.lang.Object, com.samsung.android.gallery.module.mtp.MtpClientListener] */
    public static Cursor getMtpDeviceItemCursor(Context context, Blackboard blackboard, String str) {
        Blackboard blackboard2;
        int i2;
        String str2 = str;
        if (sItemLoadRequested) {
            Log.mtw("MtpMediaItemLoader", "create mtp device item cursor already requested. skip");
            return null;
        }
        Log.mt("MtpMediaItemLoader", "create mtp device item list cursor start");
        sItemLoadRequested = true;
        MatrixCursor matrixCursor = new MatrixCursor(MTP_DEVICE_ITEM_LIST_PROJECTION);
        boolean z = false;
        if (TextUtils.isEmpty(str2)) {
            Log.w("MtpMediaItemLoader", "create mtp device item list cursor end. device name is null");
            sItemLoadRequested = false;
            return matrixCursor;
        }
        ArrayList arrayList = new ArrayList();
        MtpClient instance = MtpClient.getInstance(context);
        instance.resetAddedCount();
        boolean isAvailable = isAvailable(blackboard);
        if (isAvailable) {
            showDialog(blackboard);
            blackboard2 = blackboard;
            instance.setListener(blackboard2, new Object());
        } else {
            blackboard2 = blackboard;
        }
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(0, "root");
        List<MtpStorageInfo> storageList = instance.getStorageList(str2);
        String basePath = getBasePath(context);
        if (storageList != null) {
            for (MtpStorageInfo storageId : storageList) {
                long currentTimeMillis = System.currentTimeMillis();
                int storageId2 = storageId.getStorageId();
                ArrayList<MtpObjectInfo> objectListFromSubDirectory = instance.getObjectListFromSubDirectory(str2, storageId2, 0, hashMap, hashMap2);
                boolean z3 = z;
                Log.mt("MtpMediaItemLoader", "load all items" + Logger.vt(Integer.valueOf(storageId2), str2, Integer.valueOf(objectListFromSubDirectory.size()), Long.valueOf(currentTimeMillis)));
                arrayList.addAll(objectListFromSubDirectory);
                hashMap = hashMap;
                z = z3;
            }
        }
        HashMap hashMap3 = hashMap;
        boolean z7 = z;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MtpObjectInfo mtpObjectInfo = (MtpObjectInfo) it.next();
            Integer valueOf = Integer.valueOf(mtpObjectInfo.getObjectHandle());
            Integer valueOf2 = Integer.valueOf(MediaType.Image.toInt());
            String mimeTypeFromFormat = getMimeTypeFromFormat(mtpObjectInfo.getFormat());
            String name = mtpObjectInfo.getName();
            matrixCursor.addRow(new Object[]{valueOf, valueOf2, mimeTypeFromFormat, name, basePath + "#" + str2 + "#" + mtpObjectInfo.getName().hashCode() + "#" + mtpObjectInfo.getObjectHandle(), Long.valueOf(mtpObjectInfo.getDateModified()), Long.valueOf(mtpObjectInfo.getCompressedSizeLong()), Integer.valueOf(mtpObjectInfo.getImagePixWidth()), Integer.valueOf(mtpObjectInfo.getImagePixHeight()), Integer.valueOf(StorageType.Mtp.toInt())});
        }
        if (isAvailable) {
            instance.setListener((Blackboard) null, (MtpClientListener) null);
            dismissDialog(blackboard2);
        }
        StringBuilder sb2 = new StringBuilder("create mtp device item list cursor end ");
        sb2.append(isAvailable);
        sb2.append(ArcCommonLog.TAG_COMMA);
        if (storageList != null) {
            i2 = storageList.size();
        } else {
            i2 = -1;
        }
        sb2.append(i2);
        sb2.append(ArcCommonLog.TAG_COMMA);
        sb2.append(matrixCursor.getCount());
        Log.mt("MtpMediaItemLoader", sb2.toString());
        StringBuilder sb3 = new StringBuilder(C0212a.m("[", str2, "]\n"));
        for (Map.Entry entry : hashMap3.entrySet()) {
            sb3.append("-");
            sb3.append(Logger.getEncodedString(((String) hashMap2.get(entry.getKey())) + " : " + ((String) entry.getValue())));
            sb3.append("\n");
        }
        DebugLogger.getMtpInstance().insertLog(sb3.toString());
        sItemLoadRequested = z7;
        return matrixCursor;
    }

    private static boolean isAvailable(Blackboard blackboard) {
        if (blackboard == null || !isInMtpPictures(blackboard) || blackboard.pop("data://user/fromMtpViewer") != null) {
            return false;
        }
        return true;
    }

    private static boolean isInMtpPictures(Blackboard blackboard) {
        String str = (String) blackboard.read("location://variable/currentv1");
        if (str == null || LocationKey.isContentViewer(str) || !LocationKey.isMtpPictures(str)) {
            return false;
        }
        return true;
    }

    private static boolean isMtpItemList(Blackboard blackboard) {
        String str = (String) blackboard.read("location://variable/currentv1");
        if (str == null || !str.startsWith("location://mtp/fileList") || str.contains("viewer")) {
            return false;
        }
        return true;
    }

    private static void publishEvent(Blackboard blackboard, String str) {
        if (blackboard != null && isMtpItemList(blackboard)) {
            blackboard.post(str, (Object) null);
        }
    }

    private static void showDialog(Blackboard blackboard) {
        publishEvent(blackboard, CommandKey.DATA_REQUEST(new UriBuilder("data://user/dialog/SimpleSpinner").appendArg("message", 0).build()));
    }

    /* access modifiers changed from: private */
    public static void updateDialog(Blackboard blackboard, int i2) {
        publishEvent(blackboard, new UriBuilder("command://UpdateSpinner").appendArg("message", i2).build());
    }
}
