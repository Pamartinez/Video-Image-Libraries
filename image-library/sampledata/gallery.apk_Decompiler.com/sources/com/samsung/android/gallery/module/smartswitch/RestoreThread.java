package com.samsung.android.gallery.module.smartswitch;

import A.a;
import N2.j;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.database.dal.local.table.AlbumBnRHelper;
import com.samsung.android.gallery.module.album.SaveAlbumCoverTask;
import com.samsung.android.gallery.module.secured.PrivateDatabase;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.BnRConstants;
import com.samsung.android.gallery.support.utils.BnRDocStorageHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import i.C0212a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RestoreThread extends SmartSwitchThread {
    public RestoreThread(Context context, Intent intent) {
        super(context, intent);
        this.mDirPath = BnRConstants.RESTORE_DIR;
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
            j.r(e, new StringBuilder("convertInputStreamToString failed e="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        return sb2.toString();
        throw th;
    }

    private void copyRestoreData(List<Uri> list) {
        Uri uri = list.get(0);
        Log.d(this.TAG, "copyRestoreData done", Integer.valueOf(BnRDocStorageHelper.moveUrisToDir(this.mContext, uri, list.subList(1, list.size()), new File(this.mDirPath))), Logger.getEncodedString((Object) uri));
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

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$restoreGalleryPreference$0(Blackboard blackboard) {
        if (blackboard == Blackboard.getApplicationInstance() || BlackboardUtils.readActivity(blackboard).isDestroyed()) {
            return false;
        }
        return true;
    }

    private void notifyDataChanged() {
        BlackboardUtils.forceRefreshPicturesDataGlobal();
    }

    private boolean restoreAlbum() {
        Log.d(this.TAG, "restore Album");
        boolean restoreAlbumDatabase = AlbumBnRHelper.getInstance().restoreAlbumDatabase(this.mContext, this.mDirPath, "BACKUP_ALBUM_DB.txt");
        AlbumBnRHelper.getInstance().copyAlbumCover(new File(this.mDirPath), this.mContext.getExternalFilesDir(".album"));
        new SaveAlbumCoverTask(this.mContext).execute(new Void[0]);
        return restoreAlbumDatabase;
    }

    /* JADX WARNING: type inference failed for: r2v5, types: [java.util.function.Consumer, java.lang.Object] */
    private boolean restoreGalleryPreference() {
        FileInputStream fileInputStream;
        Log.d(this.TAG, "restore GalleryPreference");
        try {
            fileInputStream = new FileInputStream(this.mDirPath + File.separator + "BACKUP_GALLERY_PREFERENCE_VALUE.txt");
            if (GalleryPreferenceEntryV2.restore(convertInputStreamToString(fileInputStream))) {
                Blackboard.getApplicationInstance().post("global://event/activityRecreate", (Object) null);
            } else {
                Blackboard.getBlackboardMap().values().stream().filter(new a(1)).forEach(new Object());
            }
            fileInputStream.close();
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("restoreGalleryPreference failed e="), this.TAG);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private boolean restorePrivateAlbum() {
        Log.d(this.TAG, "restore private album");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.mDirPath);
        String str = File.separator;
        String p6 = C0212a.p(sb2, str, "BACKUP_PRIVATE_ALBUM_DB.bak");
        String privateAlbumMimeType = getPrivateAlbumMimeType(this.mIntent);
        if (privateAlbumMimeType != null) {
            boolean restore = PrivateDatabase.getInstance().restore(p6, privateAlbumMimeType);
            if (FileUtils.exists(p6)) {
                StringBuilder k = j.k("/data/sec/gallery/smartswitch", str, "secured.bak.");
                k.append(TimeUtil.getFileTimestamp(FileUtils.getDateModified(p6)));
                String sb3 = k.toString();
                FileUtils.makeParentIfAbsent(sb3);
                FileUtils.copy(p6, sb3);
            }
            return restore;
        }
        Log.e(this.TAG, "skip restore PA");
        return true;
    }

    private boolean restoreSettingPreference() {
        FileInputStream fileInputStream;
        Log.d(this.TAG, "restore SettingPreference");
        try {
            fileInputStream = new FileInputStream(this.mDirPath + File.separator + "BACKUP_SETTINGS_PREFERENCE_VALUE.txt");
            SettingPreferenceEntryV2.restore(convertInputStreamToString(fileInputStream));
            Blackboard.postEventGlobal(EventMessage.obtain(7005));
            fileInputStream.close();
            return true;
        } catch (Exception e) {
            a.s(e, new StringBuilder("restoreSettingPreference failed e="), this.TAG);
            return false;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void saveRestoreFile() {
        AlbumBnRHelper instance = AlbumBnRHelper.getInstance();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(BnRConstants.KEEP_RESTORE_DIR);
        String str = File.separator;
        sb2.append(str);
        sb2.append("BACKUP_ALBUM_DB.txt");
        instance.backupRestoredFile(sb2.toString());
        AlbumBnRHelper instance2 = AlbumBnRHelper.getInstance();
        instance2.backupRestoredFile(this.mDirPath + str + "BACKUP_ALBUM_DB.txt");
    }

    public boolean executeMainAction(List<Uri> list) {
        boolean z;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        atomicBoolean.set(restoreSettingPreference());
        if (restoreGalleryPreference() || atomicBoolean.get()) {
            z = true;
        } else {
            z = false;
        }
        atomicBoolean.set(z);
        if (PocFeatures.SUPPORT_PRIVATE_ALBUM) {
            restorePrivateAlbum();
        }
        if (restoreAlbum() || atomicBoolean.get()) {
            return true;
        }
        return false;
    }

    public void executePostAction(List<Uri> list, boolean z) {
        int i2;
        saveRestoreFile();
        Context context = this.mContext;
        Intent intent = this.mIntent;
        if (z) {
            i2 = 0;
        } else {
            i2 = 3;
        }
        SmartSwitchBroadcastSender.respondRestore(context, intent, i2);
        notifyDataChanged();
    }

    public void executePreAction(List<Uri> list) {
        super.executePreAction(list);
        copyRestoreData(list);
        this.mDirPath = findRestorePath(this.mDirPath);
    }

    public boolean isValidPath(List<Uri> list) {
        if (list.size() > 1) {
            return true;
        }
        return false;
    }
}
