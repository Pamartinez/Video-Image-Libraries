package com.samsung.android.gallery.support.utils;

import A4.C0371f;
import V8.a;
import android.text.TextUtils;
import com.samsung.android.gallery.support.R$string;
import com.samsung.android.gallery.support.utils.StorageInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ld.b;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BucketUtils {
    private static final CopyOnWriteArrayList<Integer> sBucketList = new CopyOnWriteArrayList<>();
    private static final CopyOnWriteArrayList<Integer> sBucketListMx = new CopyOnWriteArrayList<>();
    private static final ConcurrentHashMap<Integer, BucketData> sBucketMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Integer, BucketData> sBucketMapMx = new ConcurrentHashMap<>();
    private static int sCameraBucketId;
    private static HashSet<Integer> sCameraBucketSet = new HashSet<>();
    private static int sRootBucketId;
    private static int sSdCameraBucketId;
    private static HashSet<Integer> sSdCameraBucketSet = new HashSet<>();
    private static int sSdRootBucketId;
    private static HashSet<Integer> sSdRootBucketSet = new HashSet<>();
    private static final HashSet<Integer> sVirtualSet = ((HashSet) Stream.of(new String[]{"Virtual/Favourites", "Virtual/Video", "Virtual/Recently", "Virtual/People", "Virtual/Location"}).map(new C0670h(10)).collect(Collectors.toCollection(new b(7))));

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class BucketData {
        final String data;
        final int id;
        final int index;

        public BucketData(String str, int i2) {
            this.data = str;
            this.id = FileUtils.getBucketId(str);
            this.index = i2;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SharedProfilePatternHolder {
        static final Pattern pattern = Pattern.compile("^/storage/emulated/(95|96|97|98|99)/");

        public static Matcher matcher(String str) {
            return pattern.matcher(str);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VirtualBucketHolder {
        public static final int favorite = FileUtils.getBucketId("Virtual/Favourites");
        public static final int location = FileUtils.getBucketId("Virtual/Location");
        public static final int mergedCamera = FileUtils.getBucketId("/Merged_Album_name/Camera");
        public static final int mergedDownload = FileUtils.getBucketId("/Merged_Album_name/Download");
        public static final int mergedQuickShare = FileUtils.getBucketId("/Merged_Album_name/Quick Share");
        public static final int mergedScreenShot = FileUtils.getBucketId("/Merged_Album_name/Screenshots");
        public static final int people = FileUtils.getBucketId("Virtual/People");
        public static final int recent = FileUtils.getBucketId("Virtual/Recently");
        public static final int repair = FileUtils.getBucketId("Virtual/Repair");
        public static final int screenShots = FileUtils.getBucketId("Virtual/Screenshots");
        public static final int video = FileUtils.getBucketId("Virtual/Video");
    }

    /* JADX WARNING: type inference failed for: r0v13, types: [java.lang.Object, com.samsung.android.gallery.support.utils.SystemEnvironment$EnvironmentChangeListener] */
    static {
        SystemEnvironment.addObserver("BucketUtils", new Object(), 1);
        initialize();
    }

    public static boolean contains(int i2) {
        if (sBucketMap.containsKey(Integer.valueOf(i2)) || sVirtualSet.contains(Integer.valueOf(i2))) {
            return true;
        }
        return false;
    }

    public static boolean containsFavourite(int[] iArr) {
        return Arrays.stream(iArr).anyMatch(new C0668f(0));
    }

    public static boolean containsMx(int i2) {
        return sBucketMapMx.containsKey(Integer.valueOf(i2));
    }

    public static boolean containsRecent(int[] iArr) {
        return Arrays.stream(iArr).anyMatch(new C0668f(1));
    }

    public static List<String> getPredefined(List<String> list) {
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.RetailMode)) {
            return getRetailDirs();
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        list.stream().map(new a(10)).forEach(new C0679q(2, arrayList, arrayList2));
        StorageInfo storageInfo = StorageInfo.getDefault();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(storageInfo.camera);
        arrayList3.addAll(arrayList);
        arrayList3.add(storageInfo.screenShot);
        arrayList3.add(storageInfo.download);
        arrayList3.addAll(arrayList2);
        return arrayList3;
    }

    public static String getPredefinedKeyword(int i2) {
        if (VirtualBucketHolder.favorite == i2) {
            return "F";
        }
        if (VirtualBucketHolder.recent == i2) {
            return "R";
        }
        if (VirtualBucketHolder.mergedCamera == i2) {
            return "Cm";
        }
        if (VirtualBucketHolder.mergedDownload == i2) {
            return "Dm";
        }
        if (VirtualBucketHolder.mergedScreenShot == i2) {
            return "Sm";
        }
        if (VirtualBucketHolder.mergedQuickShare == i2) {
            return "Qm";
        }
        if (isSdCamera(i2)) {
            return "Cs";
        }
        if (isCameras(i2)) {
            return "C";
        }
        if (isDownload(i2)) {
            return "D";
        }
        if (isScreenshot(i2)) {
            return "S";
        }
        if (StorageInfo.getDefault().buckets().quickShare == i2) {
            return "Q";
        }
        Log.e((CharSequence) "BucketUtils", "getPredefinedKeyword unknown bucket", Integer.valueOf(i2));
        return null;
    }

    public static List<String> getPredefinedMx(List<String> list) {
        ArrayList arrayList = new ArrayList();
        list.stream().map(new a(10)).forEach(new C0683v(arrayList, 4));
        StorageInfo storageInfo = StorageInfo.getDefault();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("Virtual/Recently");
        arrayList2.add("Virtual/Favourites");
        arrayList2.add(storageInfo.camera);
        arrayList2.addAll(arrayList);
        arrayList2.add("/Merged_Album_name/Camera");
        arrayList2.add(storageInfo.screenShot);
        arrayList2.add("/Merged_Album_name/Screenshots");
        arrayList2.add(storageInfo.download);
        arrayList2.add("/Merged_Album_name/Download");
        arrayList2.add(storageInfo.quickShare);
        arrayList2.add("/Merged_Album_name/Quick Share");
        return arrayList2;
    }

    public static List<String> getRetailDirs() {
        ArrayList arrayList = new ArrayList();
        String loadString = GalleryPreference.getInstance().loadString(PreferenceName.RETAIL_ALBUM_SET, "");
        if (!loadString.isEmpty()) {
            try {
                JSONArray jSONArray = new JSONArray(loadString);
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    arrayList.add(jSONArray.optString(i2));
                }
                return arrayList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        FileUtils.getSdcardDirList().stream().map(new a(10)).forEach(new C0371f((Object) arrayList2, (Object) arrayList3, (Object) arrayList4, 26));
        StorageInfo storageInfo = StorageInfo.getDefault();
        arrayList.add(storageInfo.camera);
        arrayList.addAll(arrayList2);
        arrayList.add(storageInfo.cameraCover);
        arrayList.addAll(arrayList3);
        arrayList.add(storageInfo.gear);
        arrayList.add(storageInfo.gear360);
        arrayList.add(storageInfo.myEmoji);
        arrayList.add(storageInfo.arEmoji);
        arrayList.add(storageInfo.arEmojiCamera);
        arrayList.add(storageInfo.galaxyAvatar);
        arrayList.add(storageInfo.avatarCamera);
        arrayList.add(storageInfo.decoPic);
        arrayList.add(storageInfo.screenShot);
        arrayList.add(storageInfo.videoCaptures);
        arrayList.add(storageInfo.download);
        arrayList.addAll(arrayList4);
        arrayList.add(storageInfo.agif);
        arrayList.add(storageInfo.gif);
        arrayList.add(storageInfo.superSlow);
        arrayList.add(storageInfo.collage);
        arrayList.add(storageInfo.videoCollage);
        arrayList.add(storageInfo.bixbyVision);
        arrayList.add(storageInfo.videoScreenShots);
        return arrayList;
    }

    public static String getTranslatedCloudStorage() {
        return StringResources.getCloudBrand() + File.separator + AppResources.getString(R$string.samsung_gallery_app_name);
    }

    public static String getTranslatedDirectory(String str) {
        return FileUtils.getDirectoryFromPath(translatePath(str, false), false);
    }

    public static String getTranslatedDualMessengerStorage() {
        return AppResources.getString(R$string.internal_storage_dual_messenger);
    }

    public static String getTranslatedRemovableStorage() {
        return AppResources.getString(R$string.new_album_storage_sdcard);
    }

    public static String getTranslatedStorage(boolean z) {
        return z ? getTranslatedCloudStorage() : getTranslatedStorage();
    }

    public static int index(int i2) {
        BucketData bucketData = sBucketMap.get(Integer.valueOf(i2));
        if (bucketData != null) {
            return bucketData.index;
        }
        return Integer.MAX_VALUE;
    }

    public static int indexEx(int i2) {
        BucketData bucketData = sBucketMapMx.get(Integer.valueOf(i2));
        if (bucketData != null) {
            return bucketData.index;
        }
        return Integer.MAX_VALUE;
    }

    public static void initialize() {
        StorageInfo.BucketHolder buckets = StorageInfo.getInstance(FileUtils.EXTERNAL_STORAGE_DIR).buckets();
        sRootBucketId = buckets.root;
        sCameraBucketId = buckets.camera;
        StorageInfo.BucketHolder buckets2 = StorageInfo.getInstance(FileUtils.SDCARD_DIR).buckets();
        sSdRootBucketId = buckets2.root;
        sSdCameraBucketId = buckets2.camera;
        List<String> sdcardDirList = FileUtils.getSdcardDirList();
        HashSet<Integer> hashSet = new HashSet<>();
        HashSet<Integer> hashSet2 = new HashSet<>();
        sdcardDirList.stream().map(new C0670h(11)).forEach(new C0679q(1, hashSet2, hashSet));
        for (String instance : sdcardDirList) {
            hashSet2.add(Integer.valueOf(StorageInfo.getInstance(instance).buckets().camera));
        }
        sSdCameraBucketSet = hashSet2;
        sSdRootBucketSet = hashSet;
        HashSet<Integer> hashSet3 = new HashSet<>();
        hashSet3.add(Integer.valueOf(sCameraBucketId));
        hashSet3.addAll(hashSet2);
        sCameraBucketSet = hashSet3;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i7 = 0;
        for (String bucketData : getPredefined(sdcardDirList)) {
            BucketData bucketData2 = new BucketData(bucketData, i7);
            hashMap.put(Integer.valueOf(bucketData2.id), bucketData2);
            arrayList.add(Integer.valueOf(bucketData2.id));
            i7++;
        }
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = sBucketList;
        copyOnWriteArrayList.clear();
        copyOnWriteArrayList.addAll(arrayList);
        ConcurrentHashMap<Integer, BucketData> concurrentHashMap = sBucketMap;
        concurrentHashMap.clear();
        concurrentHashMap.putAll(hashMap);
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = new ArrayList();
        for (String bucketData3 : getPredefinedMx(sdcardDirList)) {
            BucketData bucketData4 = new BucketData(bucketData3, i2);
            hashMap2.put(Integer.valueOf(bucketData4.id), bucketData4);
            arrayList2.add(Integer.valueOf(bucketData4.id));
            i2++;
        }
        CopyOnWriteArrayList<Integer> copyOnWriteArrayList2 = sBucketListMx;
        copyOnWriteArrayList2.clear();
        copyOnWriteArrayList2.addAll(arrayList2);
        ConcurrentHashMap<Integer, BucketData> concurrentHashMap2 = sBucketMapMx;
        concurrentHashMap2.clear();
        concurrentHashMap2.putAll(hashMap2);
        Log.d("BucketUtils", "initialize {" + sBucketList.size() + ',' + copyOnWriteArrayList2 + ',' + sVirtualSet.size() + '}');
    }

    public static boolean isCamera(int i2) {
        if (sCameraBucketId == i2) {
            return true;
        }
        return false;
    }

    public static boolean isCameras(int i2) {
        return sCameraBucketSet.contains(Integer.valueOf(i2));
    }

    public static boolean isDownload(int i2) {
        if (StorageInfo.getDefault().buckets().download == i2) {
            return true;
        }
        return false;
    }

    public static boolean isFavourite(int i2) {
        if (VirtualBucketHolder.favorite == i2) {
            return true;
        }
        return false;
    }

    public static boolean isRecent(int i2) {
        if (VirtualBucketHolder.recent == i2) {
            return true;
        }
        return false;
    }

    public static boolean isRoot(int i2) {
        if (sRootBucketId == i2 || sSdRootBucketSet.contains(Integer.valueOf(i2))) {
            return true;
        }
        return false;
    }

    public static boolean isScreenshot(int i2) {
        if (StorageInfo.getDefault().buckets().screenShot == i2) {
            return true;
        }
        return false;
    }

    public static boolean isSdCamera(int i2) {
        return sSdCameraBucketSet.contains(Integer.valueOf(i2));
    }

    public static boolean isSystem(int i2) {
        if (isCameras(i2)) {
            return true;
        }
        StorageInfo.BucketHolder buckets = StorageInfo.getDefault().buckets();
        if (buckets.screenShot == i2 || buckets.videoCaptures == i2 || buckets.download == i2 || buckets.screenRecordings == i2 || StorageInfo.getRemovable().buckets().download == i2) {
            return true;
        }
        return false;
    }

    public static boolean isVideos(int i2) {
        if (VirtualBucketHolder.video == i2) {
            return true;
        }
        return false;
    }

    public static boolean isVirtualAlbum(int i2) {
        return sVirtualSet.contains(Integer.valueOf(i2));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getPredefined$4(List list, List list2, StorageInfo storageInfo) {
        list.add(storageInfo.camera);
        list2.add(storageInfo.download);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getRetailDirs$6(List list, List list2, List list3, StorageInfo storageInfo) {
        list.add(storageInfo.camera);
        list2.add(storageInfo.cameraCover);
        list3.add(storageInfo.download);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$initialize$2(HashSet hashSet, HashSet hashSet2, StorageInfo.BucketHolder bucketHolder) {
        hashSet.add(Integer.valueOf(bucketHolder.camera));
        hashSet2.add(Integer.valueOf(bucketHolder.root));
    }

    public static String translatePath(String str, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        for (String next : FileUtils.getStorageNameList()) {
            if (str.startsWith(next)) {
                StorageInfo instance = StorageInfo.getInstance(next);
                if (instance.primary) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(File.separator);
                    if (instance.dual) {
                        str2 = getTranslatedDualMessengerStorage();
                    } else {
                        str2 = getTranslatedStorage(z);
                    }
                    sb2.append(str2);
                    return str.replace(next, sb2.toString());
                }
                return str.replace(next, File.separator + getTranslatedRemovableStorage());
            }
        }
        if (str.startsWith("/storage/emulated/")) {
            Matcher matcher = SharedProfilePatternHolder.matcher(str);
            if (!matcher.find()) {
                return str;
            }
            StringBuilder sb3 = new StringBuilder();
            String str3 = File.separator;
            sb3.append(str3);
            sb3.append(getTranslatedDualMessengerStorage());
            sb3.append(str3);
            return matcher.replaceFirst(sb3.toString());
        } else if (!PocFeatures.SUPPORT_PRIVATE_ALBUM || !str.startsWith("/data/")) {
            return str;
        } else {
            return "/Unknown directory/";
        }
    }

    public static List<Integer> values() {
        return sBucketList;
    }

    public static List<Integer> valuesCamera() {
        return new ArrayList(sCameraBucketSet);
    }

    public static List<Integer> valuesMx() {
        return sBucketListMx;
    }

    public static String getTranslatedStorage() {
        return AppResources.getString(R$string.new_album_storage_internal_storage);
    }
}
