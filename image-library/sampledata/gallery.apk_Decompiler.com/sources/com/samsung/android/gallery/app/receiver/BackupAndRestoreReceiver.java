package com.samsung.android.gallery.app.receiver;

import A6.a;
import B5.c;
import N2.j;
import S3.d;
import U5.b;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import c0.C0086a;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.database.dal.local.table.AlbumEntry;
import com.samsung.android.gallery.database.dal.local.table.AlbumEntryJsonUtil;
import com.samsung.android.gallery.module.album.SaveAlbumCoverTask;
import com.samsung.android.gallery.module.settings.SettingPreferenceEntry;
import com.samsung.android.gallery.module.similarphoto.SimilarPhotoHelper;
import com.samsung.android.gallery.module.smartswitch.SmartSwitchThreadFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.GalleryPreferenceEntry;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.BnRConstants;
import com.samsung.android.gallery.support.utils.BnRDocStorageHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BackupAndRestoreReceiver extends BroadcastReceiver {
    private static final boolean SUPPORT_DOCUMENT_URI = SdkConfig.atLeast(SdkConfig.GED.S);

    /* access modifiers changed from: private */
    /* renamed from: backup */
    public void lambda$onReceive$0(Context context, Intent intent, String str, String str2) {
        boolean z;
        boolean z3;
        boolean z7;
        int i2;
        Context context2 = context;
        Log.d("BackupAndRestoreReceiver", "backup start");
        String stringExtra = intent.getStringExtra("SAVE_PATH");
        List<Uri> pathUris = BnRDocStorageHelper.getPathUris(context, intent);
        boolean z9 = true;
        if (!SUPPORT_DOCUMENT_URI || pathUris.size() <= 0) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            stringExtra = BnRConstants.BACKUP_DIR;
            FileUtils.deleteDirectory(new File(stringExtra));
            FileUtils.makeDirectoryIfAbsent(stringExtra);
        }
        Log.d("BackupAndRestoreReceiver", "backup path = " + Logger.getEncodedString(stringExtra));
        boolean saveJsonForSettings = saveJsonForSettings(stringExtra);
        if (PreferenceFeatures.OneUi6x.SUPPORT_PREFERENCE_BACKUP_AND_RESTORE) {
            z3 = saveJsonForPreference(stringExtra);
        } else {
            z3 = true;
        }
        ArrayList<AlbumEntry> entries = AlbumBnRHelper.getInstance().getEntries(context2);
        if (entries == null || entries.size() <= 0) {
            z7 = true;
        } else {
            z7 = AlbumEntryJsonUtil.saveJsonForAlbumDb(entries, stringExtra, "BACKUP_ALBUM_DB.txt");
            if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER) {
                AlbumBnRHelper.getInstance().copyAlbumCover(context2.getExternalFilesDir(".album"), new File(stringExtra));
            }
        }
        if (z) {
            File[] listFiles = new File(stringExtra).listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                i2 = 0;
            } else {
                i2 = 0;
                for (File copyFileToDirUri : listFiles) {
                    i2 += BnRDocStorageHelper.copyFileToDirUri(context2, copyFileToDirUri, pathUris.get(0));
                }
            }
            FileUtils.deleteDirectory(new File(stringExtra));
            StringBuilder sb2 = new StringBuilder("doBackup cpDone toUri[");
            sb2.append(pathUris.get(0));
            sb2.append("] cpCount[");
            sb2.append(i2);
            j.y(sb2, "]", "BackupAndRestoreReceiver");
        }
        if (!saveJsonForSettings && !z7) {
            z9 = false;
        }
        sendResponseBackupIntent(context2, z9, str, str2);
        Log.d("BackupAndRestoreReceiver", "backup AlbumCover=" + z7 + " Settings=" + saveJsonForSettings + " Preference= " + z3);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader;
        StringBuilder sb2 = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb2.append(readLine);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    private String findRestorePath(String str) {
        String p6 = C0212a.p(C0212a.s(str), File.separator, "backup");
        long length = new File(p6).length();
        long length2 = new File(str).length();
        int i2 = (length > 0 ? 1 : (length == 0 ? 0 : -1));
        if (i2 <= 0 && length2 <= 0) {
            return null;
        }
        if (i2 > 0) {
            return p6;
        }
        return str;
    }

    private boolean isCanceled(int i2) {
        if (i2 == 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$restoreGalleryPreferenceEntry$2(Blackboard blackboard) {
        if (blackboard == Blackboard.getApplicationInstance() || BlackboardUtils.readActivity(blackboard).isDestroyed()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$restoreGalleryPreferenceEntry$3(GalleryPreferenceEntry galleryPreferenceEntry, String str, Blackboard blackboard) {
        if (galleryPreferenceEntry.isSimilarModeChanged()) {
            blackboard.postEvent(EventMessage.obtain(1072));
        }
        blackboard.publish("command://RemoveSiblingsFragments", new String[]{str});
    }

    private void notifyDataChanged() {
        BlackboardUtils.forceRefreshPicturesDataGlobal();
    }

    private SettingPreferenceEntry parseJsonForSettings(String str) {
        Log.d("BackupAndRestoreReceiver", "parseJsonForSettings");
        if (str != null) {
            return new SettingPreferenceEntry().loadJson(new JSONObject(str));
        }
        Log.e("BackupAndRestoreReceiver", "restoreJson for Settings is null");
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: restore */
    public void lambda$onReceive$1(Context context, Intent intent, String str) {
        boolean z;
        boolean z3;
        Log.d("BackupAndRestoreReceiver", "restore start");
        String stringExtra = intent.getStringExtra("SAVE_PATH");
        List<Uri> pathUris = BnRDocStorageHelper.getPathUris(context, intent);
        boolean z7 = SUPPORT_DOCUMENT_URI;
        boolean z9 = false;
        if (pathUris.size() > 1) {
            z = true;
        } else {
            z = false;
        }
        if (!z7 || z) {
            if (!z7 && (stringExtra = findRestorePath(stringExtra)) == null) {
                if (z) {
                    z7 = true;
                } else {
                    Log.e("BackupAndRestoreReceiver", "temp file path not exist, no uri to restore");
                    sendResponseRestoreIntent(context, false, str);
                    return;
                }
            }
            if (z7) {
                String str2 = BnRConstants.RESTORE_DIR;
                FileUtils.deleteDirectory(new File(str2));
                FileUtils.makeDirectoryIfAbsent(str2);
                int moveUrisToDir = BnRDocStorageHelper.moveUrisToDir(context, pathUris.get(0), pathUris.subList(1, pathUris.size()), new File(str2));
                stringExtra = findRestorePath(str2);
                if (stringExtra == null) {
                    Log.e("BackupAndRestoreReceiver", "target file path is null");
                    sendResponseRestoreIntent(context, false, str);
                    return;
                }
                StringBuilder o2 = C0086a.o(moveUrisToDir, "doRestore cpCount[", "] targetPath = ");
                o2.append(Logger.getEncodedString(stringExtra));
                Log.d("BackupAndRestoreReceiver", o2.toString());
            }
            Log.d("BackupAndRestoreReceiver", "restore filePath = " + Logger.getEncodedString(stringExtra));
            boolean restoreForSettings = restoreForSettings(context, stringExtra);
            if (PreferenceFeatures.OneUi6x.SUPPORT_PREFERENCE_BACKUP_AND_RESTORE) {
                z3 = restoreForPreference(stringExtra);
            } else {
                z3 = true;
            }
            boolean restoreForAlbumCover = restoreForAlbumCover(context, stringExtra);
            if (PreferenceFeatures.OneUi41.SUPPORT_PERMANENT_ALBUM_COVER) {
                AlbumBnRHelper.getInstance().copyAlbumCover(new File(stringExtra), context.getExternalFilesDir(".album"));
                new SaveAlbumCoverTask(context).execute(new Void[0]);
            }
            if (restoreForSettings || restoreForAlbumCover) {
                z9 = true;
            }
            sendResponseRestoreIntent(context, z9, str);
            Log.d("BackupAndRestoreReceiver", "restore isAlbumCover= " + restoreForAlbumCover + " isSettings= " + restoreForSettings + " isPreference=" + z3);
            saveRestoreFile(stringExtra);
            if (z7) {
                FileUtils.deleteDirectory(new File(stringExtra));
            }
            notifyDataChanged();
            return;
        }
        Log.e("BackupAndRestoreReceiver", "no uri to restore");
        sendResponseRestoreIntent(context, false, str);
    }

    private boolean restoreForAlbumCover(Context context, String str) {
        return AlbumBnRHelper.getInstance().restoreAlbumDatabase(context, str, "BACKUP_ALBUM_DB.txt");
    }

    private boolean restoreForSettings(Context context, String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str + File.separator + "BACKUP_SETTINGS_PREFERENCE_VALUE.txt");
            restoreSettingPreferenceEntry(context, parseJsonForSettings(convertInputStreamToString(fileInputStream)));
            fileInputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void restoreGalleryPreferenceEntry(GalleryPreferenceEntry galleryPreferenceEntry) {
        Log.d("BackupAndRestoreReceiver", "restoreGalleryPreferenceEntry");
        if (galleryPreferenceEntry != null && galleryPreferenceEntry.save()) {
            if (galleryPreferenceEntry.isSimilarModeChanged()) {
                SimilarPhotoHelper.toggleSimilarPhotoMode();
            }
            String loadString = GalleryPreference.getInstance().loadString("location://variable/currentv1", "location://timeline");
            if (galleryPreferenceEntry.needToRecreate(loadString)) {
                Blackboard.getApplicationInstance().post("global://event/activityRecreate", (Object) null);
            } else {
                Blackboard.getBlackboardMap().values().stream().filter(new d(18)).forEach(new b(8, galleryPreferenceEntry, loadString));
            }
        }
    }

    private void restoreSettingPreferenceEntry(Context context, SettingPreferenceEntry settingPreferenceEntry) {
        Log.d("BackupAndRestoreReceiver", "restoreSettingPreferenceEntry");
        if (settingPreferenceEntry != null) {
            settingPreferenceEntry.save(context);
            Blackboard.postEventGlobal(EventMessage.obtain(7005));
        }
    }

    private void restoreStoryOptions() {
        SettingPreferenceEntry loadPreference = new SettingPreferenceEntry().loadPreference();
        if (loadPreference != null) {
            loadPreference.restoreStoryOptions();
        }
    }

    private boolean saveJsonForSettings(String str) {
        Log.d("BackupAndRestoreReceiver", "saveJsonForSettings");
        try {
            JSONObject buildJson = new SettingPreferenceEntry().loadPreference().buildJson();
            FileUtils.makeDirectoryIfAbsent(str);
            byte[] bytes = buildJson.toString().getBytes();
            return saveStreamToFile(bytes, str + File.separator + "BACKUP_SETTINGS_PREFERENCE_VALUE.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void saveRestoreFile(String str) {
        AlbumBnRHelper instance = AlbumBnRHelper.getInstance();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(BnRConstants.KEEP_RESTORE_DIR);
        String str2 = File.separator;
        sb2.append(str2);
        sb2.append("BACKUP_ALBUM_DB.txt");
        instance.backupRestoredFile(sb2.toString());
        AlbumBnRHelper instance2 = AlbumBnRHelper.getInstance();
        instance2.backupRestoredFile(str + str2 + "BACKUP_ALBUM_DB.txt");
    }

    private boolean saveStreamToFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(new SecureFile(str));
                byte[] bArr2 = new byte[1024];
                while (true) {
                    int read = byteArrayInputStream.read(bArr2, 0, 1024);
                    if (read != -1) {
                        fileOutputStream.write(bArr2, 0, read);
                    } else {
                        fileOutputStream.close();
                        byteArrayInputStream.close();
                        return true;
                    }
                }
            } catch (Throwable th) {
                byteArrayInputStream.close();
                throw th;
            }
            throw th;
        } catch (IOException e) {
            j.r(e, new StringBuilder("saveStream failed e="), "BackupAndRestoreReceiver");
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    private void sendResponseBackupIntent(Context context, boolean z, String str, String str2) {
        Intent intent = new Intent("com.samsung.android.intent.action.RESPONSE_BACKUP_GALLERY_SETTING");
        if (z) {
            intent.putExtra("RESULT", 0);
            intent.putExtra("ERR_CODE", 0);
        } else {
            intent.putExtra("RESULT", 1);
            intent.putExtra("ERR_CODE", 3);
        }
        intent.putExtra("REQ_SIZE", 0);
        intent.putExtra("SOURCE", str);
        intent.putExtra("EXPORT_SESSION_TIME", str2);
        context.sendBroadcast(intent, "com.wssnps.permission.COM_WSSNPS");
    }

    private void sendResponseRestoreIntent(Context context, boolean z, String str) {
        Intent intent = new Intent("com.samsung.android.intent.action.RESPONSE_RESTORE_GALLERY_SETTING");
        if (z) {
            intent.putExtra("RESULT", 0);
            intent.putExtra("ERR_CODE", 0);
        } else {
            intent.putExtra("RESULT", 1);
            intent.putExtra("ERR_CODE", 3);
        }
        intent.putExtra("REQ_SIZE", 0);
        intent.putExtra("SOURCE", str);
        context.sendBroadcast(intent, "com.wssnps.permission.COM_WSSNPS");
    }

    public void onReceive(Context context, Intent intent) {
        BackupAndRestoreReceiver backupAndRestoreReceiver;
        String action = intent.getAction();
        Log.d("BackupAndRestoreReceiver", "onReceive() = " + action);
        if (action == null) {
            return;
        }
        if (!action.equals("com.samsung.android.intent.action.REQUEST_BACKUP_GALLERY_SETTING") || !isCanceled(intent.getIntExtra("ACTION", 0))) {
            if (PreferenceFeatures.OneUi7x.SUPPORT_BNR_V2) {
                SmartSwitchThreadFactory.start(context, intent);
                backupAndRestoreReceiver = this;
            } else {
                String stringExtra = intent.getStringExtra("SOURCE");
                String stringExtra2 = intent.getStringExtra("EXPORT_SESSION_TIME");
                if (!action.equals("com.samsung.android.intent.action.REQUEST_BACKUP_GALLERY_SETTING")) {
                    backupAndRestoreReceiver = this;
                    Context context2 = context;
                    Intent intent2 = intent;
                    if (action.equals("com.samsung.android.intent.action.REQUEST_RESTORE_GALLERY_SETTING")) {
                        new Thread(new a((Object) backupAndRestoreReceiver, (Object) context2, (Object) intent2, (Object) stringExtra, 27)).start();
                    }
                } else if (!RuntimePermissionUtil.hasBackupRestorePermission(context)) {
                    sendResponseFail(context, "com.samsung.android.intent.action.RESPONSE_BACKUP_GALLERY_SETTING", 4, stringExtra, stringExtra2);
                    backupAndRestoreReceiver = this;
                } else {
                    backupAndRestoreReceiver = this;
                    new Thread(new c(backupAndRestoreReceiver, context, intent, stringExtra, stringExtra2, 18)).start();
                }
            }
            if (action.equals("com.samsung.cmh.action.option.restoration")) {
                backupAndRestoreReceiver.restoreStoryOptions();
            }
        }
    }

    public GalleryPreferenceEntry parseJsonForPreference(String str) {
        Log.d("BackupAndRestoreReceiver", "parseJsonForPreference");
        if (str != null) {
            return new GalleryPreferenceEntry().loadJson(new JSONObject(str));
        }
        Log.e("BackupAndRestoreReceiver", "restoreJson for Settings is null");
        return null;
    }

    public boolean restoreForPreference(String str) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(str + File.separator + "BACKUP_GALLERY_PREFERENCE_VALUE.txt");
            restoreGalleryPreferenceEntry(parseJsonForPreference(convertInputStreamToString(fileInputStream)));
            fileInputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public boolean saveJsonForPreference(String str) {
        Log.d("BackupAndRestoreReceiver", "saveJsonForPreference");
        try {
            JSONObject buildJson = new GalleryPreferenceEntry().loadPreference().buildJson();
            FileUtils.makeDirectoryIfAbsent(str);
            byte[] bytes = buildJson.toString().getBytes();
            return saveStreamToFile(bytes, str + File.separator + "BACKUP_GALLERY_PREFERENCE_VALUE.txt");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendResponseFail(Context context, String str, int i2, String str2, String str3) {
        Intent intent = new Intent(str);
        intent.putExtra("RESULT", 1);
        intent.putExtra("ERR_CODE", i2);
        intent.putExtra("REQ_SIZE", 0);
        intent.putExtra("REQ_SIZE", str2);
        intent.putExtra("EXPORT_SESSION_TIME", str3);
        context.sendBroadcast(intent, "com.wssnps.permission.COM_WSSNPS");
    }
}
