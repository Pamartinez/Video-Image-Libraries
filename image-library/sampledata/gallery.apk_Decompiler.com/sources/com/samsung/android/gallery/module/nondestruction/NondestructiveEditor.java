package com.samsung.android.gallery.module.nondestruction;

import A.a;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.hash.Hashing;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.scsp.framework.core.util.HashUtil;
import com.samsung.scsp.media.file.FileApiContract;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NondestructiveEditor {
    private static final Uri NDS_TABLE_URI = Uri.parse("content://secmedia/nondestruction");
    static final ConcurrentHashMap<Integer, String> sCache = new ConcurrentHashMap<>();

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class PhotoNdsFolder {
        static final String HIDDEN_PHOTO_FOLDER_PATH;

        static {
            String str;
            if (Features.isEnabled(Features.IS_REPAIR_MODE)) {
                str = "/data/sec_maintenance/photoeditor/";
            } else {
                str = "/data/sec/photoeditor/";
            }
            HIDDEN_PHOTO_FOLDER_PATH = str;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoNdsFolder {
        static final String HIDDEN_VIDEO_FOLDER_PATH;

        static {
            String str;
            if (Features.isEnabled(Features.IS_REPAIR_MODE)) {
                str = "/data/sec_maintenance/videoeditor/";
            } else {
                str = "/data/sec/videoeditor/";
            }
            HIDDEN_VIDEO_FOLDER_PATH = str;
        }
    }

    private void addEffectValueToSef(String str, String str2) {
        String createNondestructiveMetaData = createNondestructiveMetaData(str);
        if (createNondestructiveMetaData != null) {
            SeApiCompat.getSefFileCompat().addData(new File(str2), SefInfo.PE_RE_EDIT_DATA, createNondestructiveMetaData.getBytes());
        }
    }

    private void addHashKeyToSef(String str, String str2) {
        SeApiCompat.getSefFileCompat().addData(new File(str2), SefInfo.ORIGINAL_PATH_HASH_KEY, str.getBytes());
    }

    private void addMetaDataToSef(String str, String str2, String str3) {
        addHashKeyToSef(str, str3);
        addEffectValueToSef(str2, str3);
    }

    private String convertByteArrayToHexString(byte[] bArr) {
        StringBuilder sb2 = new StringBuilder();
        for (byte b : bArr) {
            sb2.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb2.toString();
    }

    private String createHiddenOriginalFileName(File file) {
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            String path = file.getPath();
            String hashByPath = getHashByPath(path);
            if (TextUtils.isEmpty(hashByPath)) {
                Log.w("NondestructiveEditor", "failed to create hash key from path");
            } else {
                long length = file.length();
                String extension = FileUtils.getExtension(path, true);
                return hashByPath + "_" + length + extension;
            }
        }
        return file.getName();
    }

    private String createNondestructiveMetaData(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("originalPath", str);
            jSONObject.put("isNotReEdit", true);
            jSONObject.put("sepVersion", Build.VERSION.SEM_PLATFORM_INT);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encodeHash(File file) {
        return Hashing.SHA256().hashString(file.getAbsolutePath()) + "/" + file.length();
    }

    private String getHashByPath(String str) {
        FileInputStream fileInputStream;
        try {
            MessageDigest instance = MessageDigest.getInstance(HashUtil.SHA256);
            fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[262144];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    String convertByteArrayToHexString = convertByteArrayToHexString(instance.digest());
                    fileInputStream.close();
                    return convertByteArrayToHexString;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private String getHiddenDirPath(String str) {
        StringJoiner stringJoiner = new StringJoiner("/", "", "/");
        stringJoiner.add(PhotoNdsFolder.HIDDEN_PHOTO_FOLDER_PATH);
        if (FileUtils.isInRemovableStorage(str)) {
            stringJoiner.add("secondary").add(FileUtils.getSdcardVolume(str));
        } else {
            stringJoiner.add(String.valueOf(SeApiCompat.getMyUserId()));
        }
        return stringJoiner.toString();
    }

    public static String getHiddenOriginalFromPath(String str) {
        try {
            byte[] data = SeApiCompat.getSefFileCompat().getData(new File(str), SefInfo.PE_RE_EDIT_DATA.keyName);
            if (data == null || data.length <= 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(new String(data));
            if (jSONObject.has("originalPath")) {
                return jSONObject.getString("originalPath");
            }
            return null;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHiddenOriginalFromVideoPath(String str) {
        try {
            byte[] data = SeApiCompat.getSefFileCompat().getData(new File(str), SefInfo.VE_RE_EDIT_DATA.keyName);
            if (data == null || data.length <= 0) {
                Log.w("NondestructiveEditor", "Sef data is empty");
                return null;
            }
            JSONObject jSONObject = new JSONObject(new String(data));
            if (!jSONObject.has("ORIGINAL_FILE_PATH")) {
                return null;
            }
            return new NondestructiveCryptoHelper().decrypt(jSONObject.getString("ORIGINAL_FILE_PATH"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getHiddenOriginalPath(boolean z, String str) {
        if (str == null) {
            Log.e("NondestructiveEditor", "failed to get hidden original path");
            return null;
        } else if (z) {
            return getHiddenOriginalFromVideoPath(str);
        } else {
            return getHiddenOriginalFromPath(str);
        }
    }

    public static boolean isInLegalStorage(String str) {
        if (str == null) {
            return false;
        }
        if (str.startsWith(PhotoNdsFolder.HIDDEN_PHOTO_FOLDER_PATH) || str.startsWith(VideoNdsFolder.HIDDEN_VIDEO_FOLDER_PATH)) {
            return true;
        }
        return false;
    }

    private File makeHiddenDirectory(File file) {
        String parent;
        SecureFile secureFile = new SecureFile(getHiddenDirPath(file.getAbsolutePath()));
        if (!SdkConfig.atLeast(SdkConfig.SEM.T_MR1) && (parent = file.getParent()) != null) {
            secureFile = new SecureFile((File) secureFile, parent);
        }
        if (!secureFile.exists()) {
            boolean mkdirs = secureFile.mkdirs();
            Log.d("NondestructiveEditor", "make child hidden folder: " + mkdirs, "");
        }
        return secureFile;
    }

    public static void removeHiddenOriginal(long j2, int i2) {
        Log.d("NondestructiveEditor", "removeHiddenOriginal", Long.valueOf(j2));
        sCache.remove(Integer.valueOf(i2));
    }

    public String copyToHiddenDir(Context context, String str, String str2) {
        String str3;
        String hiddenOriginalFromPath = getHiddenOriginalFromPath(str);
        if (FileUtils.exists(hiddenOriginalFromPath)) {
            Log.d("NondestructiveEditor", "already existed in hidden dir: ");
            str3 = getOriginalFileHash(context, hiddenOriginalFromPath);
        } else {
            SecureFile secureFile = new SecureFile(str);
            File makeHiddenDirectory = makeHiddenDirectory(secureFile);
            SecureFile secureFile2 = new SecureFile(makeHiddenDirectory, createHiddenOriginalFileName(secureFile));
            if (secureFile2.exists()) {
                secureFile2 = new SecureFile(makeHiddenDirectory, System.currentTimeMillis() + "_" + secureFile.getName());
            }
            if (FileUtils.copy((File) secureFile, (File) secureFile2)) {
                SeApiCompat.touchOnVold(AppResources.getAppContext(), secureFile2.getPath());
                str3 = encodeHash(secureFile2);
                insertOriginalData(AppResources.getAppContext(), secureFile2, str3);
                Log.d("NondestructiveEditor", "copied original image to hidden: ");
            } else {
                str3 = null;
            }
            hiddenOriginalFromPath = secureFile2.getAbsolutePath();
        }
        if (!TextUtils.isEmpty(str3) && !TextUtils.isEmpty(hiddenOriginalFromPath) && !TextUtils.isEmpty(str2)) {
            addMetaDataToSef(str3, hiddenOriginalFromPath, str2);
        }
        return hiddenOriginalFromPath;
    }

    public void deleteEffectValue(String str) {
        try {
            SeApiCompat.getSefFileCompat().deleteData(new SecureFile(str), SefInfo.PE_RE_EDIT_DATA.keyName);
        } catch (IOException unused) {
            Log.e("NondestructiveEditor", "deleteEffectValue failed");
        }
    }

    public String getLocalOriginalFilePath(Context context, String str) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(NDS_TABLE_URI, new String[]{FileApiContract.Parameter.PATH}, "hash = ?", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    Log.d("NondestructiveEditor", "get local original file path " + Logger.getEncodedString(str));
                    query.close();
                    return string;
                }
            }
            Log.d("NondestructiveEditor", "get local original file path. empty in database");
            if (query == null) {
                return "";
            }
            query.close();
            return "";
        } catch (Exception e) {
            Exception exc = e;
            Log.d("NondestructiveEditor", "get local original file path failed. e=" + exc.getMessage());
            return "";
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public String getOriginalFileHash(Context context, String str) {
        Cursor query;
        Throwable th;
        try {
            query = context.getContentResolver().query(NDS_TABLE_URI, new String[]{"hash"}, "path = ?", new String[]{str}, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    String string = query.getString(0);
                    query.close();
                    return string;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("get original file hash failed. e="), "NondestructiveEditor");
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        return encodeHash(new File(str));
        throw th;
    }

    public void insertOriginalData(Context context, File file, String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long length = file.length();
            String absolutePath = file.getAbsolutePath();
            ContentValues contentValues = new ContentValues();
            contentValues.put("hash", str);
            contentValues.put(FileApiContract.Parameter.PATH, absolutePath);
            contentValues.put(IParameterKey.SIZE, Long.valueOf(length));
            Uri insert = context.getContentResolver().insert(NDS_TABLE_URI, contentValues);
            Log.d("NondestructiveEditor", "insert original data" + Logger.vt(insert, Logger.getEncodedString(absolutePath), str, Long.valueOf(currentTimeMillis)));
        } catch (Exception e) {
            a.s(e, new StringBuilder("insert original data failed. e="), "NondestructiveEditor");
        }
    }
}
