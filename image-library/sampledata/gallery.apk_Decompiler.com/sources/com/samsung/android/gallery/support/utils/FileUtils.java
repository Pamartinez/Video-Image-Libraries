package com.samsung.android.gallery.support.utils;

import A.a;
import N2.j;
import N9.b;
import O3.o;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import c0.C0086a;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.StorageVolumeCompat;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import t1.C0280e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileUtils {
    public static volatile String EXTERNAL_STORAGE_DIR = "";
    private static final String GALLERY_INTERNAL = C0212a.p(new StringBuilder(), File.separator, "com.sec.android.gallery3d");
    public static final String PPP_TEMP_DIR;
    static volatile String SDCARD_DIR = "/NoSdCard";
    static volatile String SDCARD_RW_DIR = "/NoSdCard";
    static volatile String SDCARD_VOLUME;
    private static volatile String sExternalCacheDir;
    private static volatile String sExternalFilesDir;
    private static volatile String sFilesDir;
    private static volatile String sMountedExternalNames = "''";
    private static volatile String sMountedVolumeNames = "'external_primary'";
    private static volatile List<String> sPrimaryStorageNameList = new ArrayList();
    private static volatile List<String> sRemovableStorageNameList = new ArrayList();
    private static volatile List<String> sStorageNameList = new ArrayList();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RelativePathPatternHolder {
        static final Pattern pattern = Pattern.compile("(?i)^/storage/(?:emulated/\\d+/|[^/]+/)");

        public static String getRelativePath(String str) {
            Matcher matcher = pattern.matcher(str);
            if (!matcher.find()) {
                return null;
            }
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf == -1 || lastIndexOf < matcher.end()) {
                return "/";
            }
            return str.substring(matcher.end(), lastIndexOf + 1);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class UserIdPatternHolder {
        static final Pattern pattern = Pattern.compile("(?i)^/storage/emulated/([^/]+)");

        public static String getUserIdFromPath(String str) {
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        }
    }

    static {
        String str;
        if (Features.isEnabled(Features.IS_REPAIR_MODE)) {
            str = "/data/sec_maintenance/camera/";
        } else {
            str = "/data/sec/camera/";
        }
        PPP_TEMP_DIR = str;
    }

    public static String addPostfix(String str, int i2) {
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf != -1) {
            String substring = str.substring(0, lastIndexOf);
            String substring2 = str.substring(lastIndexOf);
            return substring + " (" + i2 + ')' + substring2;
        }
        return str + " (" + i2 + ')';
    }

    public static String addSuffix(String str, String str2) {
        if (str == null) {
            return C0212a.l("zzz", str2);
        }
        int lastIndexOf = str.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return C0212a.A(str, str2);
        }
        return str.substring(0, lastIndexOf) + str2 + str.substring(lastIndexOf);
    }

    public static boolean adjustDateModified(String str, long j2) {
        long j3;
        if ((j2 / 1000) % 2 == 0) {
            j3 = j2 + 1000;
        } else {
            j3 = j2 - 1000;
        }
        return setDateModified(str, j3);
    }

    public static boolean belongToStorage(List<String> list, String str) {
        for (String startsWith : list) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static void computeStorageList(List<StorageVolumeCompat> list, List<String> list2, List<String> list3, List<String> list4) {
        boolean z;
        int dualAppProfileId;
        if (list4 == null || SeApiCompat.isProfileNotSupportSdCard(AppResources.getAppContext())) {
            z = false;
        } else {
            z = true;
        }
        list.stream().filter(new C0680s(4)).forEach(new b((List) list2, (List) list3, z, (List) list4));
        if (SdkConfig.atLeast(SdkConfig.GED.T) && (dualAppProfileId = SeApiCompat.getDualAppProfileId()) >= 95 && dualAppProfileId < 100) {
            String i2 = C0086a.i(dualAppProfileId, "/storage/emulated/");
            if (!list3.contains(i2)) {
                list2.add(i2);
                list3.add(i2);
            }
        }
    }

    public static boolean copy(String str, String str2) {
        return copy((File) new SecureFile(str), (File) new SecureFile(str2));
    }

    public static boolean copyFileTime(File file, File file2) {
        try {
            long lastModified = file.lastModified();
            if (lastModified > 0 && file2.exists() && file2.setLastModified(lastModified)) {
                return true;
            }
            Log.e("FileUtils", "copyFileTime failed " + Logger.getEncodedString((Object) file));
            return false;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("copyFileTime failed e="), "FileUtils");
            return false;
        }
    }

    public static File createNewFile(File file) {
        try {
            makeDirectoryIfAbsent(file.getParentFile());
            if (file.createNewFile()) {
                return file;
            }
            Log.e("FileUtils", "createNewFile failed to make file " + Logger.getEncodedString((Object) file));
            return null;
        } catch (IOException | SecurityException e) {
            Log.e("FileUtils", "createNewFile failed e=" + Logger.toSimpleString(e));
            return null;
        }
    }

    public static File createNewFileIfAbsent(String str) {
        SecureFile secureFile = new SecureFile(str);
        if (!secureFile.exists()) {
            createNewFile((File) secureFile);
        }
        return secureFile;
    }

    public static boolean delete(File file) {
        if (file != null) {
            return !file.exists() || file.delete();
        }
        return false;
    }

    public static boolean deleteDirectory(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    if (!deleteDirectory(file2)) {
                        Log.e("FileUtils", "deleteDirectory failed " + file2);
                        return false;
                    }
                } else if (!file2.delete()) {
                    Log.e("FileUtils", "deleteDirectory failed " + file2);
                    return false;
                }
            }
        }
        if (file.delete()) {
            return true;
        }
        Log.e("FileUtils", "deleteDirectory failed " + file);
        return false;
    }

    public static void deleteEmptyDirectory(String str) {
        boolean z;
        String str2;
        int i2;
        SecureFile secureFile = new SecureFile(str);
        if (secureFile.exists()) {
            String[] list = secureFile.list();
            if (list == null || list.length != 0 || !secureFile.delete()) {
                z = false;
            } else {
                z = true;
            }
            if (z) {
                str2 = "";
            } else {
                str2 = " failed";
            }
            String concat = "deleteEmptyDirectory".concat(str2);
            if (list != null) {
                i2 = list.length;
            } else {
                i2 = -1;
            }
            Log.d("FileUtils", concat, Integer.valueOf(i2));
        }
    }

    public static void deleteFilesInDir(File file) {
        String[] strArr;
        String str;
        if (file.isDirectory()) {
            strArr = file.list();
        } else {
            strArr = null;
        }
        if (strArr != null && strArr.length > 0) {
            int i2 = 0;
            for (String secureFile : strArr) {
                if (!new SecureFile(file, secureFile).delete()) {
                    i2++;
                }
            }
            if (i2 > 0) {
                str = " failed";
            } else {
                str = "";
            }
            Log.d("FileUtils", "deleteFilesInDir".concat(str), Integer.valueOf(strArr.length), Integer.valueOf(i2));
        }
    }

    public static String dump() {
        return "(" + EXTERNAL_STORAGE_DIR + ',' + SDCARD_DIR + "),(" + sMountedVolumeNames + "),(" + TextUtils.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, sStorageNameList) + ")";
    }

    public static boolean equals(File file, File file2) {
        if (file == null || file2 == null) {
            return false;
        }
        try {
            return file.getCanonicalPath().equals(file2.getCanonicalPath());
        } catch (Exception e) {
            a.s(e, new StringBuilder("equals(File,File) failed e="), "FileUtils");
            return false;
        }
    }

    public static boolean exists(String str) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return false;
        }
        return true;
    }

    public static String getAbsolutePath(String str) {
        return new SecureFile(str).getAbsolutePath();
    }

    public static String getBaseName(String str) {
        String nameFromPath = getNameFromPath(str);
        int lastIndexOf = nameFromPath.lastIndexOf(".");
        if (lastIndexOf < 0) {
            return nameFromPath;
        }
        return nameFromPath.substring(0, lastIndexOf);
    }

    public static int getBucketId(String str) {
        return str.toLowerCase(Locale.US).hashCode();
    }

    public static String getBucketNameFromPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return getNameFromPath(getDirectoryFromPath(str, false));
        } catch (Exception e) {
            a.s(e, new StringBuilder("getBucketName failed "), "FileUtils");
            return "";
        }
    }

    public static String getCanonicalPath(String str) {
        try {
            return new SecureFile(str).getCanonicalPath();
        } catch (IOException unused) {
            return getAbsolutePath(str);
        }
    }

    public static long getDateModified(String str) {
        File file;
        if (str != null) {
            file = new File(str);
        } else {
            file = null;
        }
        if (file == null || !file.exists()) {
            return -1;
        }
        return file.lastModified();
    }

    public static String getDirectoryFromPath(String str) {
        return getDirectoryFromPath(str, true);
    }

    public static Object[] getDirectoryInfo(ArrayList<File> arrayList) {
        int i2;
        int i7;
        long j2;
        File[] fileArr;
        int i8 = 0;
        long j3 = 0;
        try {
            Iterator<File> it = arrayList.iterator();
            i7 = 0;
            i2 = 0;
            j2 = 0;
            while (it.hasNext()) {
                try {
                    File next = it.next();
                    if (next != null) {
                        fileArr = next.listFiles();
                    } else {
                        fileArr = null;
                    }
                    if (fileArr != null) {
                        if (fileArr.length > 0) {
                            for (File file : fileArr) {
                                if (file != null) {
                                    if (isInRemovableStorage(SecureDigitalPolicy.getReadablePath(file.getAbsolutePath()))) {
                                        i2++;
                                        j2 += file.length();
                                    } else {
                                        i7++;
                                        j3 += file.length();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e = e;
                    i8 = i7;
                    Log.e("FileUtils", e.getMessage());
                    i7 = i8;
                    return new Object[]{Integer.valueOf(i7), Integer.valueOf(i2), Long.valueOf(j3), Long.valueOf(j2)};
                }
            }
        } catch (Exception e7) {
            e = e7;
            i2 = 0;
            j2 = 0;
            Log.e("FileUtils", e.getMessage());
            i7 = i8;
            return new Object[]{Integer.valueOf(i7), Integer.valueOf(i2), Long.valueOf(j3), Long.valueOf(j2)};
        }
        return new Object[]{Integer.valueOf(i7), Integer.valueOf(i2), Long.valueOf(j3), Long.valueOf(j2)};
    }

    public static String getExtension(String str) {
        if (str != null && str.length() > 0) {
            int lastIndexOf = str.lastIndexOf(File.separator);
            int lastIndexOf2 = str.lastIndexOf(".");
            if (lastIndexOf2 >= 0 && lastIndexOf2 >= lastIndexOf) {
                return str.substring(lastIndexOf2 + 1);
            }
        }
        return "";
    }

    public static String getExternalCacheDir() {
        if (sExternalCacheDir == null) {
            try {
                sExternalCacheDir = AppResources.getAppContext().getExternalCacheDir().getPath();
            } catch (Exception e) {
                Log.e("FileUtils", "getExternalCacheDir failed e=" + e.getMessage());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EXTERNAL_STORAGE_DIR);
                sb2.append("/Android/data");
                return C0212a.p(sb2, GALLERY_INTERNAL, "/cache");
            }
        }
        return sExternalCacheDir;
    }

    public static String getExternalFilesDir(String str) {
        String str2;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getExternalFilesDir());
        if (str == null || str.length() <= 0) {
            str2 = "";
        } else {
            str2 = C0212a.p(new StringBuilder(), File.separator, str);
        }
        sb2.append(str2);
        String sb3 = sb2.toString();
        File file = new File(sb3);
        if (!file.exists() && !file.mkdirs()) {
            Log.e((CharSequence) "FileUtils", "getExternalFilesDir#mkdirs failed", sb3);
        }
        return sb3;
    }

    public static List<String> getExternalStorageNameList() {
        return sPrimaryStorageNameList;
    }

    public static String getFilesDir() {
        if (sFilesDir == null) {
            try {
                sFilesDir = AppResources.getAppContext().getFilesDir().getPath();
            } catch (Exception e) {
                Log.e("FileUtils", "getFilesDir failed e=" + e.getMessage());
                StringBuilder sb2 = new StringBuilder();
                sb2.append(EXTERNAL_STORAGE_DIR.replaceFirst("/storage/emulated/", "/data/user/"));
                return C0212a.p(sb2, GALLERY_INTERNAL, "/files");
            }
        }
        return sFilesDir;
    }

    public static String getHiddenLogFile(Context context) {
        String str = context.getFilesDir().getParent() + "/dump/";
        makeDirectoryIfAbsent(str);
        return C0212a.A(str, "hidden.log");
    }

    public static String getMountedExternalNames() {
        return sMountedExternalNames;
    }

    public static String getMountedVolumeAndExternalNames() {
        return sMountedVolumeNames + GlobalPostProcInternalPPInterface.SPLIT_REGEX + sMountedExternalNames;
    }

    public static String getMountedVolumeNames() {
        return sMountedVolumeNames;
    }

    public static String getNameFromPath(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf < 0) {
            return str;
        }
        return str.substring(lastIndexOf + 1);
    }

    @Deprecated
    public static String getNewFilePath(String str) {
        return new FileNameBuilder(str).buildUnique();
    }

    public static String getOwner(File file) {
        try {
            return Files.getOwner(file.toPath(), new LinkOption[]{LinkOption.NOFOLLOW_LINKS}).getName();
        } catch (Exception e) {
            a.s(e, new StringBuilder("getOwner failed. e="), "FileUtils");
            return "u0_unknown";
        }
    }

    public static String getParent(String str) {
        return new SecureFile(str).getParent();
    }

    public static String getParentDirectory(String str) {
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            str = C0280e.d(1, 0, str);
        }
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf > 0) {
            return str.substring(0, lastIndexOf + 1);
        }
        return "";
    }

    public static String getRelativePath(String str) {
        if (str == null) {
            return null;
        }
        return RelativePathPatternHolder.getRelativePath(str);
    }

    public static String getSdcardDir() {
        return SDCARD_DIR;
    }

    public static List<String> getSdcardDirList() {
        return sRemovableStorageNameList;
    }

    public static String getSdcardRwDir() {
        return SDCARD_RW_DIR;
    }

    public static String getSdcardVolume(String str) {
        if (str == null || !str.startsWith("/storage/") || str.startsWith("/storage/emulated/")) {
            return "";
        }
        int indexOf = str.indexOf(File.separator, 9);
        if (indexOf > 9) {
            return str.substring(9, indexOf);
        }
        if (indexOf < 0) {
            return str.substring(9);
        }
        return "";
    }

    public static String getStorageName(String str) {
        if (str == null) {
            return null;
        }
        if (str.startsWith(EXTERNAL_STORAGE_DIR)) {
            return EXTERNAL_STORAGE_DIR;
        }
        if (str.startsWith(SDCARD_DIR)) {
            return SDCARD_DIR;
        }
        if (str.startsWith("/storage/emulated/")) {
            int indexOf = str.indexOf(File.separator, 19);
            if (indexOf >= 0) {
                return str.substring(0, indexOf);
            }
        } else if (str.startsWith("/storage/")) {
            int indexOf2 = str.indexOf(File.separator, 10);
            if (indexOf2 >= 0) {
                return str.substring(0, indexOf2);
            }
        } else {
            Log.majorEvent("FileUtils", "getStorageName invalid{" + Logger.getEncodedString(str) + '}');
            return null;
        }
        return str;
    }

    public static List<String> getStorageNameList() {
        return sStorageNameList;
    }

    public static String getUniqueFilename(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        SecureFile secureFile = new SecureFile(str);
        String[] splitNameAndExtension = splitNameAndExtension(str2);
        String str3 = splitNameAndExtension[0];
        int i2 = 1;
        String str4 = splitNameAndExtension[1];
        String B = C0212a.B(str3, ".", str4);
        while (new SecureFile((File) secureFile, B).exists()) {
            B = String.format(Locale.getDefault(), "%s (%d).%s", new Object[]{str3, Integer.valueOf(i2), str4});
            i2++;
        }
        return B;
    }

    public static Uri getUri(String str) {
        return Uri.fromFile(new File(str));
    }

    public static StorageVolumeCompat getUsbStorageVolume(Context context) {
        List<StorageVolumeCompat> storageVolumes = SeApiCompat.getStorageVolumes(context);
        for (int size = storageVolumes.size() - 1; size >= 0; size--) {
            StorageVolumeCompat storageVolumeCompat = storageVolumes.get(size);
            if (storageVolumeCompat.isUsb() && storageVolumeCompat.isMounted()) {
                return storageVolumeCompat;
            }
        }
        return null;
    }

    public static List<StorageVolumeCompat> getUsbStorageVolumes(Context context) {
        return (List) SeApiCompat.getStorageVolumes(context).stream().filter(new C0680s(2)).collect(Collectors.toList());
    }

    public static String getUserIdFromPath(String str) {
        if (str == null) {
            return null;
        }
        return UserIdPatternHolder.getUserIdFromPath(str);
    }

    public static String getVolumeName(String str) {
        if (str != null && !str.startsWith("/storage/emulated/") && str.startsWith("/storage/")) {
            int indexOf = str.indexOf(File.separator, 9);
            if (indexOf > 9) {
                return str.substring(9, indexOf).toLowerCase();
            }
            if (indexOf < 0) {
                return str.substring(9).toLowerCase();
            }
        }
        return "external_primary";
    }

    public static boolean hasSdcardVolume() {
        if (SDCARD_VOLUME != null) {
            return true;
        }
        return false;
    }

    public static String info(String str) {
        return TextUtils.isEmpty(str) ? "file(null)" : info(new File(str));
    }

    public static void initMountedStorageNames(Context context) {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        List<StorageVolumeCompat> storageVolumes = SeApiCompat.getStorageVolumes(context);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        computeStorageList(storageVolumes, arrayList, arrayList2, (List<String>) null);
        sStorageNameList = arrayList;
        sPrimaryStorageNameList = arrayList2;
        if (arrayList2.isEmpty()) {
            str = "''";
        } else {
            str = (String) arrayList2.stream().map(new C0670h(16)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
        }
        sMountedExternalNames = str;
        Log.d("FileUtils", "initMountedStorageNames {" + dump() + "} +" + (System.currentTimeMillis() - currentTimeMillis));
    }

    public static void initMountedVolumes(Context context) {
        sMountedVolumeNames = loadMountedVolumes(context, (List<String>) null);
    }

    public static boolean initialize(Context context) {
        String str;
        String str2;
        String str3 = null;
        TimeTickLog timeTickLog = new TimeTickLog((String) null);
        String path = Environment.getExternalStorageDirectory().getPath();
        boolean equals = path.equals(EXTERNAL_STORAGE_DIR);
        EXTERNAL_STORAGE_DIR = path;
        if (!equals) {
            sFilesDir = null;
            sExternalFilesDir = null;
            sExternalCacheDir = null;
        }
        timeTickLog.tick();
        List<StorageVolumeCompat> storageVolumes = SeApiCompat.getStorageVolumes(context);
        timeTickLog.tick();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        computeStorageList(storageVolumes, arrayList, arrayList2, arrayList3);
        timeTickLog.tick();
        if (!equals || !StringCompat.equalsList(arrayList, sStorageNameList) || !StringCompat.equalsList(arrayList3, sRemovableStorageNameList)) {
            sStorageNameList = arrayList;
            sPrimaryStorageNameList = arrayList2;
            sRemovableStorageNameList = arrayList3;
            if (arrayList3.size() > 0) {
                str = (String) arrayList3.get(0);
            } else {
                str = "/NoSdCard";
            }
            SDCARD_DIR = str;
            SDCARD_RW_DIR = SecureDigitalPolicy.getWritablePath(SDCARD_DIR);
            if (SDCARD_DIR != null && SDCARD_DIR.startsWith("/storage/")) {
                str3 = SDCARD_DIR.substring(9);
            }
            SDCARD_VOLUME = str3;
            if (arrayList2.isEmpty()) {
                str2 = "''";
            } else {
                str2 = (String) arrayList2.stream().map(new C0670h(17)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
            }
            sMountedExternalNames = str2;
            sMountedVolumeNames = loadMountedVolumes(context, arrayList3);
            timeTickLog.tick();
            Log.i("FileUtils", "init {" + dump() + "} +" + timeTickLog.summary());
            return true;
        }
        Log.d("FileUtils", "init skip {" + dump() + "} +" + timeTickLog.summary());
        return false;
    }

    public static boolean initializeIfAbsent(Context context) {
        if (!TextUtils.isEmpty(EXTERNAL_STORAGE_DIR) || !initialize(context)) {
            return false;
        }
        return true;
    }

    public static boolean isCloudVideoThumbCache(String str) {
        if (str == null) {
            return false;
        }
        if (str.contains("/.Trash/")) {
            return true;
        }
        return FileType.isKnownImageType(str);
    }

    public static boolean isDirectory(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyDummyImage(String str) {
        if (str == null || !str.endsWith("!$&Welcome@#Image.jpg")) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyFolder(File file) {
        try {
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("isEmptyFolder failed e="), "FileUtils");
        }
        return false;
    }

    public static boolean isFile(String str) {
        File file = new File(str);
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return true;
    }

    public static boolean isInAndroidMediaDir(String str) {
        if (str == null) {
            return false;
        }
        for (String next : sStorageNameList) {
            if (str.startsWith(next)) {
                return str.startsWith(StorageInfo.getInstance(next).media);
            }
        }
        return false;
    }

    public static boolean isInExternalCacheDir(String str) {
        if (str == null || !str.startsWith(getExternalCacheDir())) {
            return false;
        }
        return true;
    }

    public static boolean isInExternalDir(String str) {
        if (str == null || str.isEmpty() || str.startsWith(EXTERNAL_STORAGE_DIR) || str.startsWith("/data/sec/") || str.startsWith("/Samsung Cloud/") || str.startsWith("/Galaxy Cloud/")) {
            return true;
        }
        if (sPrimaryStorageNameList.size() <= 1 || !belongToStorage(sPrimaryStorageNameList, str)) {
            return false;
        }
        return true;
    }

    public static boolean isInExternalFilesDir(String str) {
        if (str == null || !str.startsWith(getExternalFilesDir())) {
            return false;
        }
        return true;
    }

    public static boolean isInRemovableStorage(String str) {
        if (str == null || !belongToStorage(sRemovableStorageNameList, str)) {
            return false;
        }
        return true;
    }

    public static boolean isInSameStorage(String str, String str2) {
        String storageName = getStorageName(str);
        if (storageName == null || !storageName.equals(getStorageName(str2))) {
            return false;
        }
        return true;
    }

    public static boolean isNonMoviePath(String str) {
        if (str == null || StorageInfo.getDefault().isNonMoviePath(str) || StorageInfo.getRemovable().isNonMoviePath(str)) {
            return true;
        }
        return false;
    }

    public static boolean isPostProcessingFile(String str) {
        if (str == null || !str.startsWith(PPP_TEMP_DIR)) {
            return false;
        }
        return true;
    }

    public static boolean isProgressiveCandidate(String str) {
        if (str.contains("/DCIM/Camera/")) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
            return true;
        }
        return false;
    }

    public static boolean isRootDirectory(String str) {
        if (str == null || !sStorageNameList.contains(str)) {
            return false;
        }
        return true;
    }

    public static boolean isSdcardMounted(Context context) {
        return SeApiCompat.isSdcardMounted(context);
    }

    public static boolean isValidPath(String str) {
        if (str == null) {
            return false;
        }
        if ((!SdkConfig.atLeast(SdkConfig.GED.Q) || !belongToStorage(sStorageNameList, str)) && !str.startsWith(EXTERNAL_STORAGE_DIR) && !str.startsWith(SDCARD_DIR) && str.startsWith("/data/sec/")) {
            return false;
        }
        return true;
    }

    public static boolean isZeroVideoFrameTimePath(String str) {
        if (PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME || isNonMoviePath(str)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$computeStorageList$2(List list, List list2, boolean z, List list3, StorageVolumeCompat storageVolumeCompat) {
        list.add(storageVolumeCompat.directory);
        if (storageVolumeCompat.primary) {
            list2.add(storageVolumeCompat.directory);
        } else if (z && storageVolumeCompat.isSdcard()) {
            list3.add(storageVolumeCompat.directory);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getUsbStorageVolumes$10(StorageVolumeCompat storageVolumeCompat) {
        if (!storageVolumeCompat.isUsb() || !storageVolumeCompat.isMounted()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$list$9(ArrayList arrayList, File[] fileArr) {
        Stream map = Stream.of(fileArr).map(new o(13));
        Objects.requireNonNull(arrayList);
        map.forEach(new f4.a(arrayList, 17));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$loadMountedVolumes$3(StringJoiner stringJoiner, String str) {
        stringJoiner.add("'" + str + "'");
        if (SDCARD_VOLUME == null && str != null && !"external_primary".equals(str)) {
            SDCARD_VOLUME = str.toUpperCase();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$loadMountedVolumes$4(StorageVolumeCompat storageVolumeCompat) {
        if (storageVolumeCompat.primary || !storageVolumeCompat.isSdcard()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ long lambda$size$8(File file) {
        if (file.isFile()) {
            return file.length();
        }
        return size(file);
    }

    public static long length(String str) {
        return new File(str).length();
    }

    public static List<String> list(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String file : strArr) {
            Optional.ofNullable(new File(file).listFiles()).ifPresent(new C0683v(arrayList, 6));
        }
        return arrayList;
    }

    public static String loadMountedVolumes(Context context, List<String> list) {
        String str;
        try {
            Set<String> externalVolumeNames = MediaStore.getExternalVolumeNames(context);
            if (!externalVolumeNames.isEmpty()) {
                StringJoiner stringJoiner = new StringJoiner(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
                externalVolumeNames.forEach(new P(2, stringJoiner));
                return stringJoiner.toString().toLowerCase();
            }
        } catch (Exception e) {
            Log.majorEvent("FileUtils", "loadMountedVolumes failed. retry with storage-list e=" + e.getMessage());
        }
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            list.stream().map(new C0670h(13)).forEach(new f4.a(arrayList, 17));
        } else if (!SeApiCompat.isProfileNotSupportSdCard(context)) {
            SeApiCompat.getStorageVolumes(context).stream().filter(new C0680s(3)).map(new C0670h(14)).forEach(new f4.a(arrayList, 17));
        }
        if (arrayList.size() > 0) {
            str = ((String) arrayList.get(0)).toUpperCase();
        } else {
            str = null;
        }
        SDCARD_VOLUME = str;
        arrayList.add(0, "external_primary");
        return (String) arrayList.stream().map(new C0670h(15)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX));
    }

    public static boolean makeDirectoryIfAbsent(String str) {
        return str != null && makeDirectoryIfAbsent((File) new SecureFile(str));
    }

    public static boolean makeParentIfAbsent(String str) {
        return str != null && makeDirectoryIfAbsent(new SecureFile(str).getParentFile());
    }

    public static String makeUniqueFilename(String str) {
        return makeUniqueFilename(str, "");
    }

    public static boolean move(String str, String str2) {
        SecureFile secureFile = new SecureFile(str);
        SecureFile secureFile2 = new SecureFile(str2);
        boolean renameTo = secureFile.renameTo(secureFile2);
        return (renameTo || !makeDirectoryIfAbsent(secureFile2.getParentFile())) ? renameTo : secureFile.renameTo(secureFile2);
    }

    public static byte[] readBytes(String str, long j2, int i2) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[i2];
            fileInputStream.skip(j2);
            fileInputStream.read(bArr, 0, i2);
            fileInputStream.close();
            return bArr;
        } catch (Exception e) {
            a.s(e, new StringBuilder("readBytes failed. e="), "FileUtils");
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static byte[] readFile(File file) {
        try {
            return Files.readAllBytes(file.toPath());
        } catch (Error | Exception e) {
            Log.e((CharSequence) "FileUtils", "readFile failed", e);
            return null;
        }
    }

    public static boolean rename(String str, String str2) {
        SecureFile secureFile = new SecureFile(str);
        SecureFile secureFile2 = new SecureFile(str2);
        if (secureFile2.exists() || !secureFile.renameTo(secureFile2)) {
            return false;
        }
        return true;
    }

    public static String replaceExtension(String str, String str2) {
        return j.f(new StringBuilder(), splitNameAndExtension(str)[0], ".", str2);
    }

    public static boolean setDateModified(String str, long j2) {
        if (j2 > 0 && str != null) {
            SecureFile secureFile = new SecureFile(str);
            if (!secureFile.exists() || !secureFile.setLastModified(j2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static long size(File file) {
        File[] listFiles;
        if (file == null || (listFiles = file.listFiles()) == null || listFiles.length <= 0) {
            return 0;
        }
        return Stream.of(listFiles).mapToLong(new C0674l(0)).sum();
    }

    public static String[] splitNameAndExtension(String str) {
        int lastIndexOf = str.lastIndexOf(File.separator);
        int lastIndexOf2 = str.lastIndexOf(".");
        if (lastIndexOf2 < 0 || lastIndexOf2 < lastIndexOf) {
            return new String[]{str, ""};
        }
        return new String[]{str.substring(0, lastIndexOf2), str.substring(lastIndexOf2 + 1)};
    }

    public static String[] splitPathAndName(String str) {
        if (TextUtils.isEmpty(str)) {
            return new String[]{"", ""};
        }
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            return new String[]{C0280e.d(1, 0, str), ""};
        }
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf < 0) {
            return new String[]{"", str};
        }
        return new String[]{str.substring(0, lastIndexOf), str.substring(lastIndexOf + 1)};
    }

    public static void truncateFile(RandomAccessFile randomAccessFile) {
        long filePointer = randomAccessFile.getFilePointer();
        if (randomAccessFile.length() > filePointer) {
            randomAccessFile.getChannel().truncate(filePointer);
        }
    }

    public static boolean writeFile(String str, byte[] bArr) {
        return writeFile((File) new SecureFile(str), bArr);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0143, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0144, code lost:
        r20 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00dc, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00f0, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00f3, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0105, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0116, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:?, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0125, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x013d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:45:0x00d8, B:66:0x0101] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:49:0x00df, B:75:0x0112] */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:51:0x00e2, B:83:0x0121] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x0143 A[ExcHandler: FileNotFoundException (e java.io.FileNotFoundException), Splitter:B:1:0x0010] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0101 A[SYNTHETIC, Splitter:B:66:0x0101] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean copy(java.io.File r28, java.io.File r29) {
        /*
            java.lang.String r0 = ","
            java.lang.String r1 = "FileUtils"
            long r2 = java.lang.System.currentTimeMillis()
            boolean r4 = r29.exists()
            long r5 = r29.length()
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0143, IOException -> 0x0148 }
            r9 = r28
            r8.<init>(r9)     // Catch:{ FileNotFoundException -> 0x0143, IOException -> 0x013d }
            java.io.FileOutputStream r10 = new java.io.FileOutputStream     // Catch:{ all -> 0x0130 }
            r11 = r29
            r10.<init>(r11)     // Catch:{ all -> 0x012a }
            java.nio.channels.FileChannel r12 = r8.getChannel()     // Catch:{ all -> 0x011b }
            java.nio.channels.FileChannel r17 = r10.getChannel()     // Catch:{ all -> 0x010a }
            long r13 = r12.size()     // Catch:{ all -> 0x00f6 }
            r15 = 0
        L_0x002c:
            int r18 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r18 >= 0) goto L_0x007a
            r18 = r13
            r13 = r15
            long r15 = r18 - r13
            r21 = r8
            r7 = r18
            r20 = 0
            long r15 = r12.transferTo(r13, r15, r17)     // Catch:{ all -> 0x0076 }
            r26 = r13
            r13 = r17
            r17 = r2
            r2 = r26
            long r2 = r2 + r15
            int r14 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r14 >= 0) goto L_0x006a
            java.lang.String r14 = "copy#transferTo remained"
            long r22 = r7 - r2
            r24 = r2
            java.lang.Long r2 = java.lang.Long.valueOf(r22)     // Catch:{ all -> 0x0066 }
            java.lang.Long r3 = java.lang.Long.valueOf(r15)     // Catch:{ all -> 0x0066 }
            java.lang.Long r15 = java.lang.Long.valueOf(r24)     // Catch:{ all -> 0x0066 }
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3, r15}     // Catch:{ all -> 0x0066 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r14, r2)     // Catch:{ all -> 0x0066 }
            goto L_0x006c
        L_0x0066:
            r0 = move-exception
        L_0x0067:
            r2 = r0
            goto L_0x00ff
        L_0x006a:
            r24 = r2
        L_0x006c:
            r2 = r17
            r15 = r24
            r17 = r13
            r13 = r7
            r8 = r21
            goto L_0x002c
        L_0x0076:
            r0 = move-exception
            r13 = r17
            goto L_0x0067
        L_0x007a:
            r21 = r8
            r7 = r13
            r13 = r17
            r20 = 0
            r17 = r2
            r2 = r15
            if (r4 == 0) goto L_0x009e
            int r4 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x009e
            java.lang.String r4 = "copy#truncate"
            java.lang.Long r14 = java.lang.Long.valueOf(r2)     // Catch:{ all -> 0x0066 }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0066 }
            java.lang.Object[] r5 = new java.lang.Object[]{r14, r5}     // Catch:{ all -> 0x0066 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r4, r5)     // Catch:{ all -> 0x0066 }
            r13.truncate(r2)     // Catch:{ all -> 0x0066 }
        L_0x009e:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0066 }
            r4.<init>()     // Catch:{ all -> 0x0066 }
            java.lang.String r5 = "copy {"
            r4.append(r5)     // Catch:{ all -> 0x0066 }
            int r5 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            r6 = 1
            if (r5 != 0) goto L_0x00af
            r5 = r6
            goto L_0x00b1
        L_0x00af:
            r5 = r20
        L_0x00b1:
            r4.append(r5)     // Catch:{ all -> 0x0066 }
            r4.append(r0)     // Catch:{ all -> 0x0066 }
            r4.append(r7)     // Catch:{ all -> 0x0066 }
            r4.append(r0)     // Catch:{ all -> 0x0066 }
            r4.append(r2)     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = "} +"
            r4.append(r0)     // Catch:{ all -> 0x0066 }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0066 }
            long r2 = r2 - r17
            r4.append(r2)     // Catch:{ all -> 0x0066 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0066 }
            com.samsung.android.gallery.support.utils.Log.d(r1, r0)     // Catch:{ all -> 0x0066 }
            if (r13 == 0) goto L_0x00df
            r13.close()     // Catch:{ all -> 0x00dc }
            goto L_0x00df
        L_0x00dc:
            r0 = move-exception
        L_0x00dd:
            r2 = r0
            goto L_0x0110
        L_0x00df:
            r12.close()     // Catch:{ all -> 0x00f3 }
            r10.close()     // Catch:{ all -> 0x00f0 }
            r21.close()     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00e9 }
            return r6
        L_0x00e9:
            r0 = move-exception
        L_0x00ea:
            r2 = r0
            goto L_0x014c
        L_0x00ed:
            r0 = move-exception
            goto L_0x01e7
        L_0x00f0:
            r0 = move-exception
        L_0x00f1:
            r2 = r0
            goto L_0x0134
        L_0x00f3:
            r0 = move-exception
        L_0x00f4:
            r2 = r0
            goto L_0x0121
        L_0x00f6:
            r0 = move-exception
            r21 = r8
            r13 = r17
            r20 = 0
            goto L_0x0067
        L_0x00ff:
            if (r13 == 0) goto L_0x0109
            r13.close()     // Catch:{ all -> 0x0105 }
            goto L_0x0109
        L_0x0105:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ all -> 0x00dc }
        L_0x0109:
            throw r2     // Catch:{ all -> 0x00dc }
        L_0x010a:
            r0 = move-exception
            r21 = r8
            r20 = 0
            goto L_0x00dd
        L_0x0110:
            if (r12 == 0) goto L_0x011a
            r12.close()     // Catch:{ all -> 0x0116 }
            goto L_0x011a
        L_0x0116:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ all -> 0x00f3 }
        L_0x011a:
            throw r2     // Catch:{ all -> 0x00f3 }
        L_0x011b:
            r0 = move-exception
            r21 = r8
            r20 = 0
            goto L_0x00f4
        L_0x0121:
            r10.close()     // Catch:{ all -> 0x0125 }
            goto L_0x0129
        L_0x0125:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ all -> 0x00f0 }
        L_0x0129:
            throw r2     // Catch:{ all -> 0x00f0 }
        L_0x012a:
            r0 = move-exception
        L_0x012b:
            r21 = r8
            r20 = 0
            goto L_0x00f1
        L_0x0130:
            r0 = move-exception
            r11 = r29
            goto L_0x012b
        L_0x0134:
            r21.close()     // Catch:{ all -> 0x0138 }
            goto L_0x013c
        L_0x0138:
            r0 = move-exception
            r2.addSuppressed(r0)     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00e9 }
        L_0x013c:
            throw r2     // Catch:{ FileNotFoundException -> 0x00ed, IOException -> 0x00e9 }
        L_0x013d:
            r0 = move-exception
        L_0x013e:
            r11 = r29
            r20 = 0
            goto L_0x00ea
        L_0x0143:
            r0 = move-exception
            r20 = 0
            goto L_0x01e7
        L_0x0148:
            r0 = move-exception
            r9 = r28
            goto L_0x013e
        L_0x014c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "copy failed. src="
            r0.<init>(r3)
            java.lang.String r3 = r9.getAbsolutePath()
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r3)
            r0.append(r3)
            java.lang.String r3 = ", dst="
            r0.append(r3)
            java.lang.String r3 = r11.getAbsolutePath()
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.getEncodedString((java.lang.String) r3)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            r3 = 0
            java.lang.String r0 = r9.getAbsolutePath()     // Catch:{ ErrnoException -> 0x0189 }
            android.system.StructStat r4 = android.system.Os.stat(r0)     // Catch:{ ErrnoException -> 0x0189 }
            java.lang.String r0 = r11.getAbsolutePath()     // Catch:{ ErrnoException -> 0x0187 }
            android.system.StructStat r3 = android.system.Os.stat(r0)     // Catch:{ ErrnoException -> 0x0187 }
            goto L_0x0192
        L_0x0187:
            r0 = move-exception
            goto L_0x018b
        L_0x0189:
            r0 = move-exception
            r4 = r3
        L_0x018b:
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
        L_0x0192:
            if (r4 == 0) goto L_0x01e0
            if (r3 == 0) goto L_0x01e0
            int r0 = r4.st_uid
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r5 = r4.st_gid
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            int r4 = r4.st_mode
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r5, r4}
            java.lang.String r4 = "uid: %d, gid: %d, mode: %x"
            java.lang.String r0 = java.lang.String.format(r4, r0)
            java.lang.String r5 = "src : "
            java.lang.String r0 = r5.concat(r0)
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
            int r0 = r3.st_uid
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            int r5 = r3.st_gid
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            int r3 = r3.st_mode
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r5, r3}
            java.lang.String r0 = java.lang.String.format(r4, r0)
            java.lang.String r3 = "dst : "
            java.lang.String r0 = r3.concat(r0)
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
        L_0x01e0:
            r2.printStackTrace()
            r11.delete()
            goto L_0x01fc
        L_0x01e7:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "copy failed. e="
            r2.<init>(r3)
            java.lang.String r0 = r0.getMessage()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.samsung.android.gallery.support.utils.Log.e(r1, r0)
        L_0x01fc:
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.FileUtils.copy(java.io.File, java.io.File):boolean");
    }

    public static boolean delete(String str) {
        return str != null && delete((File) new SecureFile(str));
    }

    public static String getDirectoryFromPath(String str, boolean z) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(File.separator)) <= 0) {
            return "";
        }
        if (z) {
            lastIndexOf++;
        }
        return str.substring(0, lastIndexOf);
    }

    public static String info(File file) {
        StringBuilder sb2 = new StringBuilder("file(");
        sb2.append(file.exists() ? Long.valueOf(file.length()) : "not found");
        sb2.append(") ");
        sb2.append(Logger.getEncodedString(file.getPath()));
        return sb2.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (com.samsung.android.gallery.support.utils.MyFilesApi.makeDirectoryIfAbsent(r3) != false) goto L_0x0025;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean makeDirectoryIfAbsent(java.io.File r3) {
        /*
            r0 = 1
            if (r3 == 0) goto L_0x0026
            boolean r1 = r3.exists()
            if (r1 != 0) goto L_0x0025
            boolean r1 = r3.mkdirs()
            if (r1 == 0) goto L_0x0010
            goto L_0x0025
        L_0x0010:
            boolean r1 = com.samsung.android.gallery.support.utils.VoldApi.SUPPORT
            if (r1 == 0) goto L_0x001b
            boolean r1 = com.samsung.android.gallery.support.utils.VoldApi.makeDirectoryIfAbsent(r3)
            if (r1 == 0) goto L_0x001b
            return r0
        L_0x001b:
            boolean r1 = com.samsung.android.gallery.support.utils.MyFilesApi.SUPPORT
            if (r1 == 0) goto L_0x0026
            boolean r1 = com.samsung.android.gallery.support.utils.MyFilesApi.makeDirectoryIfAbsent(r3)
            if (r1 == 0) goto L_0x0026
        L_0x0025:
            return r0
        L_0x0026:
            r1 = 0
            if (r3 == 0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r0 = r1
        L_0x002b:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r0 = "FileUtils"
            java.lang.String r2 = "makeDirectoryIfAbsent failed"
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r0, (java.lang.String) r2, (java.lang.Object[]) r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.FileUtils.makeDirectoryIfAbsent(java.io.File):boolean");
    }

    public static boolean makeParentIfAbsent(File file) {
        return file != null && makeDirectoryIfAbsent(file.getParentFile());
    }

    public static String makeUniqueFilename(String str, String str2) {
        String[] splitPathAndName = splitPathAndName(str);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(splitPathAndName[0]);
        sb2.append(File.separator);
        String str3 = splitPathAndName[0];
        StringBuilder s = C0212a.s(str2);
        s.append(splitPathAndName[1]);
        sb2.append(getUniqueFilename(str3, s.toString()));
        return sb2.toString();
    }

    public static boolean writeFile(File file, byte[] bArr) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) "FileUtils", "writeFile(byte[]) failed", (Throwable) e);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static byte[] readFile(String str) {
        return readFile(new File(str));
    }

    public static File createNewFile(String str) {
        SecureFile secureFile = new SecureFile(str);
        if (secureFile.exists() && secureFile.delete()) {
            Log.i("FileUtils", "createNewFile with deleting old");
        }
        return createNewFile((File) secureFile);
    }

    public static String getExtension(String str, boolean z) {
        String extension = getExtension(str);
        return (!z || TextUtils.isEmpty(extension)) ? extension : C0212a.l(".", extension);
    }

    public static boolean move(String str, String str2, boolean z) {
        if (z) {
            return copy(str, str2) && delete(str);
        }
        return move(str, str2);
    }

    public static boolean equals(String str, String str2) {
        return (str == null || str2 == null || !equals(new File(str), new File(str2))) ? false : true;
    }

    public static void writeFile(String str, InputStream inputStream) {
        writeFile((File) new SecureFile(str), inputStream);
    }

    public static void writeFile(File file, InputStream inputStream) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[2048];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("writeFile(is) failed e="), "FileUtils");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public static String getExternalFilesDir() {
        if (sExternalFilesDir != null) {
            return sExternalFilesDir;
        }
        try {
            return AppResources.getAppContext().getExternalFilesDir("").getPath();
        } catch (Exception e) {
            Log.e((CharSequence) "FileUtils", "getExternalFilesDir failed", (Throwable) e);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(EXTERNAL_STORAGE_DIR);
            sb2.append("/Android/data");
            return C0212a.p(sb2, GALLERY_INTERNAL, "/files");
        }
    }

    public static String getFilesDir(String str) {
        String filesDir = getFilesDir();
        if (!TextUtils.isEmpty(str)) {
            filesDir = C0212a.p(C0212a.s(filesDir), File.separator, str);
        }
        File file = new File(filesDir);
        if (!file.exists() && !file.mkdirs()) {
            a.u("getFilesDir#mkdir failed ", str, "FileUtils");
        }
        return filesDir;
    }

    public static boolean isEmptyFolder(String str) {
        try {
            SecureFile secureFile = new SecureFile(str.trim());
            if (secureFile.isDirectory()) {
                File[] listFiles = secureFile.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("isEmptyFolder failed e="), "FileUtils");
        }
        return false;
    }

    public static String[] splitPathAndName(String str, boolean z) {
        String[] splitPathAndName = splitPathAndName(str);
        if (z && !TextUtils.isEmpty(splitPathAndName[0]) && !TextUtils.isEmpty(splitPathAndName[1])) {
            splitPathAndName[1] = getUniqueFilename(splitPathAndName[0], splitPathAndName[1]);
        }
        return splitPathAndName;
    }

    public static byte[] readBytes(InputStream inputStream) {
        if (Build.VERSION.SDK_INT >= 33) {
            return inputStream.readAllBytes();
        }
        byte[] bArr = new byte[inputStream.available()];
        inputStream.read(bArr);
        return bArr;
    }

    public static void writeFile(File file, InputStream inputStream, byte[] bArr) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            while (true) {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("writeFile(is) failed e="), "FileUtils");
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }
}
