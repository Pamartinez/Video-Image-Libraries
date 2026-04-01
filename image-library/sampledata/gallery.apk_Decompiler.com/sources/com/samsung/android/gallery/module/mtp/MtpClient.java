package com.samsung.android.gallery.module.mtp;

import Gb.a;
import N2.j;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.mtp.MtpDevice;
import android.mtp.MtpObjectInfo;
import android.mtp.MtpStorageInfo;
import android.text.TextUtils;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MtpMediaItemLoader;
import com.samsung.android.gallery.module.data.k;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpClient {
    private static final MtpClient sInstance = new MtpClient();
    private int mAddedCount = 0;
    private Blackboard mBlackboard;
    private MtpClientListener mListener = null;
    private MtpDeviceLoader mLoader = null;

    private MtpClient() {
    }

    private boolean checkValidDirectory(MtpObjectInfo mtpObjectInfo) {
        return !mtpObjectInfo.getName().startsWith(".");
    }

    private MtpDevice getDevice(String str) {
        return this.mLoader.getDevice(str);
    }

    public static MtpClient getInstance(Context context) {
        return sInstance.init(context);
    }

    private List<MtpObjectInfo> getObjectList(String str, int i2, int i7) {
        MtpDevice device = getDevice(str);
        if (device == null) {
            StringBuilder u = C0212a.u("unable to getObjectList. device is null [", str, "][", i2, "][");
            u.append(i7);
            u.append("]");
            Log.mtw("MtpClient", u.toString());
            return null;
        }
        if (i7 == 0) {
            i7 = -1;
        }
        int[] objectHandles = device.getObjectHandles(i2, 0, i7);
        if (objectHandles == null) {
            StringBuilder u3 = C0212a.u("unable to getObjectList. handles is null [", str, "][", i2, "][");
            u3.append(i7);
            u3.append("]");
            Log.mtw("MtpClient", u3.toString());
            return null;
        }
        ArrayList arrayList = new ArrayList(objectHandles.length);
        for (int i8 : objectHandles) {
            if (Thread.currentThread().isInterrupted()) {
                Log.mtw("MtpClient", "stop getObjectList. interrupted");
                return arrayList;
            }
            MtpObjectInfo objectInfo = device.getObjectInfo(i8);
            if (objectInfo == null) {
                StringBuilder u6 = C0212a.u("unable to getObjectList. info is null [", str, "][", i2, "][");
                u6.append(i7);
                u6.append("][");
                u6.append(i8);
                u6.append("]");
                Log.mtw("MtpClient", u6.toString());
            } else {
                arrayList.add(objectInfo);
            }
        }
        return arrayList;
    }

    private MtpClient init(Context context) {
        if (this.mLoader == null) {
            this.mLoader = new MtpDeviceLoader(context);
        }
        return this;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getObjectListFromSubDirectory$0(MtpObjectInfo mtpObjectInfo) {
        return "(" + mtpObjectInfo.getName() + "$" + mtpObjectInfo.getName().hashCode() + "$" + mtpObjectInfo.getObjectHandle() + "$" + mtpObjectInfo.getParent() + ")";
    }

    private ArrayList<MtpObjectInfo> queryItems(String str, int i2, int i7, ArrayList<MtpObjectInfo> arrayList) {
        Blackboard blackboard;
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList<MtpObjectInfo> arrayList2 = new ArrayList<>();
        List<MtpObjectInfo> objectList = getObjectList(str, i2, i7);
        if (objectList == null) {
            Log.mtw("MtpClient", "[" + i7 + "] children is null");
            return arrayList2;
        }
        StringBuilder o2 = C0086a.o(i7, "[", "] children [");
        o2.append(objectList.size());
        o2.append("][");
        o2.append(System.currentTimeMillis() - currentTimeMillis);
        o2.append("]");
        Log.mt("MtpClient", o2.toString());
        for (MtpObjectInfo next : objectList) {
            int format = next.getFormat();
            String name = next.getName();
            String encodedString = Logger.getEncodedString(name);
            if (format == 12289) {
                arrayList.add(next);
            } else if (format == 14337 || format == 14340 || format == 14347 || format == 14343 || format == 14344) {
                arrayList2.add(next);
                int i8 = this.mAddedCount + 1;
                this.mAddedCount = i8;
                MtpClientListener mtpClientListener = this.mListener;
                if (!(mtpClientListener == null || (blackboard = this.mBlackboard) == null)) {
                    ((k) mtpClientListener).getClass();
                    MtpMediaItemLoader.updateDialog(blackboard, i8);
                }
            } else {
                StringBuilder u = C0212a.u("other [", encodedString, "][", format, "][");
                u.append(next.getParent());
                u.append("][");
                u.append(next.getObjectHandle());
                u.append("]");
                Log.mtw("MtpClient", u.toString());
                if (".nomedia".equals(name)) {
                    Log.mtw("MtpClient", ".nomedia exist so skip.");
                    arrayList.clear();
                    arrayList2.clear();
                    return arrayList2;
                }
            }
        }
        return arrayList2;
    }

    public void closeDevice(String str) {
        this.mLoader.closeDevice(str);
    }

    public String dump() {
        return this.mLoader.getDump();
    }

    public List<MtpDevice> getDeviceList() {
        return this.mLoader.getDeviceList();
    }

    public String getDeviceModelName(String str) {
        return this.mLoader.getDeviceModelName(str);
    }

    public String getDeviceNameFromPath(String str) {
        if (TextUtils.isEmpty(str)) {
            Log.mtw("MtpClient", "path is null.");
            return null;
        }
        String[] split = str.split("#");
        if (split.length > 1) {
            return split[1];
        }
        Log.mtw("MtpClient", "no wild key.");
        return null;
    }

    public byte[] getObject(String str, int i2, int i7) {
        if (str == null) {
            Log.mtw("MtpClient", "getObject skip. null device name");
            return null;
        }
        MtpDevice device = getDevice(str);
        if (device == null) {
            Log.mtw("MtpClient", "getObject fail to open for ".concat(str));
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        try {
            return device.getObject(i2, i7);
        } catch (OutOfMemoryError e) {
            Log.mte("MtpClient", "getObject OOM {" + i2 + GlobalPostProcInternalPPInterface.SPLIT_REGEX + i7 + "} " + e.getMessage());
            return null;
        } finally {
            StringBuilder o2 = C0086a.o(i2, "getObject {", "} +");
            o2.append(System.currentTimeMillis() - currentTimeMillis);
            Log.mt("MtpClient", o2.toString());
        }
    }

    public ArrayList<MtpObjectInfo> getObjectListFromSubDirectory(String str, int i2, int i7, Map<Integer, String> map, Map<Integer, String> map2) {
        Map<Integer, String> map3;
        Map<Integer, String> map4;
        int i8;
        String str2;
        MtpClient mtpClient;
        ArrayList arrayList = new ArrayList();
        ArrayList<MtpObjectInfo> queryItems = queryItems(str, i2, i7, arrayList);
        if (!queryItems.isEmpty()) {
            map.put(Integer.valueOf(queryItems.get(0).getParent()), (String) queryItems.stream().limit(10).map(new a(17)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            MtpObjectInfo mtpObjectInfo = (MtpObjectInfo) it.next();
            if (this.checkValidDirectory(mtpObjectInfo)) {
                Integer valueOf = Integer.valueOf(mtpObjectInfo.getObjectHandle());
                map2.put(valueOf, mtpObjectInfo.getName() + "$" + mtpObjectInfo.getObjectHandle());
                mtpClient = this;
                str2 = str;
                i8 = i2;
                map4 = map;
                map3 = map2;
                queryItems.addAll(mtpClient.getObjectListFromSubDirectory(str2, i8, mtpObjectInfo.getObjectHandle(), map4, map3));
            } else {
                mtpClient = this;
                str2 = str;
                i8 = i2;
                map4 = map;
                map3 = map2;
            }
            this = mtpClient;
            str = str2;
            i2 = i8;
            map = map4;
            map2 = map3;
        }
        return queryItems;
    }

    public List<MtpStorageInfo> getStorageList(String str) {
        MtpDevice device = getDevice(str);
        if (device == null) {
            Log.mtw("MtpClient", "getStorageList failed to open " + str);
            return null;
        }
        int[] storageIds = device.getStorageIds();
        if (storageIds == null) {
            Log.mtw("MtpClient", "getStorageList failed to get storage ids from " + str);
            return null;
        }
        Log.mt("MtpClient", "getStorageList start " + Arrays.toString(storageIds));
        ArrayList arrayList = new ArrayList(storageIds.length);
        for (int i2 : storageIds) {
            MtpStorageInfo storageInfo = device.getStorageInfo(i2);
            if (storageInfo == null) {
                Log.mtw("MtpClient", "getStorageList failed to get mtp storage info from " + i2 + ArcCommonLog.TAG_COMMA + str);
            } else {
                arrayList.add(storageInfo);
            }
        }
        Log.mt("MtpClient", "getStorageList end " + arrayList.size());
        return arrayList;
    }

    public byte[] getThumbnail(String str, int i2) {
        if (str == null) {
            Log.mtw("MtpClient", "unable to getThumbnail. device name is null.");
            return null;
        }
        MtpDevice device = getDevice(str);
        if (device != null) {
            return device.getThumbnail(i2);
        }
        Log.mtw("MtpClient", "unable to getThumbnail. " + str + " is not opened.");
        return null;
    }

    public boolean importFile(String str, int i2, String str2) {
        MtpDevice device = getDevice(str);
        boolean z = false;
        if (device == null || TextUtils.isEmpty(str2)) {
            StringBuilder k = j.k("unable to importFile. invalid ", str, " [");
            k.append(Logger.getEncodedString(str2));
            k.append("]");
            Log.mtw("MtpClient", k.toString());
            return false;
        }
        Log.mt("MtpClient", "importFile start [" + i2 + "]");
        MtpObjectInfo objectInfo = device.getObjectInfo(i2);
        if (objectInfo != null) {
            z = device.importFile(i2, str2);
        }
        Log.mt("MtpClient", "importFile end [" + i2 + "][" + z + "][" + objectInfo + "]");
        return z;
    }

    public boolean isActivityResumed(Activity activity) {
        return SeApiCompat.isActivityResumed(activity);
    }

    public boolean isAvailable() {
        return this.mLoader.isAvailable();
    }

    public boolean isCamera(UsbDevice usbDevice) {
        return this.mLoader.isCamera(usbDevice);
    }

    public boolean isDeviceOpened(String str) {
        return this.mLoader.isDeviceOpened(str);
    }

    public void resetAddedCount() {
        this.mAddedCount = 0;
    }

    public void restartGallery(Activity activity) {
        try {
            Log.mt("MtpClient", "restartGallery");
            if (isActivityResumed(activity)) {
                Intent intent = new Intent();
                intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.GalleryActivity");
                intent.addFlags(268468224);
                activity.startActivity(intent);
            }
            activity.finish();
        } catch (Exception e) {
            Log.mte("MtpClient", e.getMessage());
        }
    }

    public void setListener(Blackboard blackboard, MtpClientListener mtpClientListener) {
        this.mBlackboard = blackboard;
        this.mListener = mtpClientListener;
    }
}
