package com.samsung.android.gallery.module.smartswitch;

import N2.j;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.database.dal.local.table.AlbumEntry;
import com.samsung.android.gallery.database.dal.local.table.AlbumEntryJsonUtil;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.support.utils.BnRConstants;
import com.samsung.android.gallery.support.utils.BnRDocStorageHelper;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.RuntimePermissionUtil;
import i.C0212a;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class BackupThread extends SmartSwitchThread {
    public BackupThread(Context context, Intent intent) {
        super(context, intent);
        this.mDirPath = BnRConstants.BACKUP_DIR;
    }

    private boolean backupAlbum() {
        Log.d(this.TAG, "backup Album");
        ArrayList<AlbumEntry> entries = AlbumBnRHelper.getInstance().getEntries(this.mContext);
        if (entries == null || entries.isEmpty()) {
            return true;
        }
        boolean saveJsonForAlbumDb = AlbumEntryJsonUtil.saveJsonForAlbumDb(entries, this.mDirPath, "BACKUP_ALBUM_DB.txt");
        AlbumBnRHelper.getInstance().copyAlbumCover(this.mContext.getExternalFilesDir(".album"), new File(this.mDirPath));
        return saveJsonForAlbumDb;
    }

    private boolean backupGalleryPreference() {
        Log.d(this.TAG, "backup GalleryPreference");
        byte[] bytes = GalleryPreferenceEntryV2.backup().toString().getBytes();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mDirPath);
        return saveStreamToFile(bytes, C0212a.p(sb2, File.separator, "BACKUP_GALLERY_PREFERENCE_VALUE.txt"));
    }

    private boolean backupPrivateAlbum() {
        String privateAlbumMimeType = getPrivateAlbumMimeType(this.mIntent);
        if (privateAlbumMimeType != null) {
            Log.d(this.TAG, "backup private album");
            PrivateDatabase instance = PrivateDatabase.getInstance();
            instance.backup(this.mDirPath + File.separator + "BACKUP_PRIVATE_ALBUM_DB.bak", privateAlbumMimeType);
            return true;
        }
        Log.d(this.TAG, "skip backup private album");
        return true;
    }

    private boolean backupSettingPreference() {
        Log.d(this.TAG, "backup SettingPreference");
        byte[] bytes = SettingPreferenceEntryV2.backup().toString().getBytes();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mDirPath);
        return saveStreamToFile(bytes, C0212a.p(sb2, File.separator, "BACKUP_SETTINGS_PREFERENCE_VALUE.txt"));
    }

    private void copyBackupData(List<Uri> list) {
        int i2 = 0;
        Uri uri = list.get(0);
        File[] listFiles = new File(this.mDirPath).listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i7 = 0;
            while (i2 < length) {
                i7 += BnRDocStorageHelper.copyFileToDirUri(this.mContext, listFiles[i2], uri);
                i2++;
            }
            i2 = i7;
        }
        Log.d(this.TAG, "copyBackupData done", Integer.valueOf(i2), Logger.getEncodedString((Object) uri));
    }

    private String getPrivateAlbumMimeType(Intent intent) {
        if (intent == null) {
            return null;
        }
        boolean booleanExtra = intent.getBooleanExtra("PRIVATE_ALBUM_PHOTO_SELECTED", false);
        boolean booleanExtra2 = intent.getBooleanExtra("PRIVATE_ALBUM_VIDEO_SELECTED", false);
        if (booleanExtra && booleanExtra2) {
            return "*/*";
        }
        if (booleanExtra) {
            return "image/*";
        }
        if (booleanExtra2) {
            return "video/*";
        }
        return null;
    }

    private boolean saveStreamToFile(byte[] bArr, String str) {
        FileOutputStream fileOutputStream;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(str);
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
            j.r(e, new StringBuilder("saveStream failed e="), this.TAG);
            return false;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    public boolean executeMainAction(List<Uri> list) {
        boolean z;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.set(backupSettingPreference());
        if (backupGalleryPreference() || atomicBoolean.get()) {
            z = true;
        } else {
            z = false;
        }
        atomicBoolean.set(z);
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            backupPrivateAlbum();
        }
        if (backupAlbum() || atomicBoolean.get()) {
            return true;
        }
        return false;
    }

    public void executePostAction(List<Uri> list, boolean z) {
        int i2;
        copyBackupData(list);
        Context context = this.mContext;
        Intent intent = this.mIntent;
        if (z) {
            i2 = 0;
        } else {
            i2 = 3;
        }
        SmartSwitchBroadcastSender.respondBackup(context, intent, i2);
    }

    public boolean isValidPath(List<Uri> list) {
        return !list.isEmpty();
    }

    public void run() {
        if (!RuntimePermissionUtil.hasBackupRestorePermission(this.mContext)) {
            SmartSwitchBroadcastSender.respondBackup(this.mContext, this.mIntent, 4);
        } else {
            super.run();
        }
    }
}
