package com.samsung.android.gallery.module.trash.helper;

import A.a;
import N2.j;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.cloud.sdk.CloudErrorType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.trash.abstraction.ITrashProvider;
import com.samsung.android.gallery.module.trash.abstraction.TrashBaseItem;
import com.samsung.android.gallery.module.trash.abstraction.TrashLogType;
import com.samsung.android.gallery.module.trash.factory.AbsTrashFactory;
import com.samsung.android.gallery.module.trash.factory.TrashFactory;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.support.TrashLogger;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.scsp.media.Media;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import o6.p;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TrashHelper {
    protected final String TAG = tag();
    boolean mInterrupted = false;
    private final MediaScanner mMediaScanner;
    TrashProgressListener mProgressListener = null;
    private int mProgressedCount = 0;
    private final Set<String> mScanPaths = new LinkedHashSet();
    private final String mStringNewAlbumStorageSdCard;
    private int mTotalCount = 0;
    ArrayList<File> mTrashDirs;
    protected final AbsTrashFactory mTrashFactory;
    protected final ITrashProvider mTrashProvider;

    public TrashHelper(Context context) {
        this.mTrashProvider = TrashProviderFactory.getInstance(context);
        this.mTrashFactory = TrashFactory.getInstance(context);
        this.mTrashDirs = getTrashDirs(context);
        this.mMediaScanner = new MediaScanner().setListener(new p(14, this)).setBulk(true);
        this.mStringNewAlbumStorageSdCard = context.getString(R$string.new_album_storage_sdcard);
    }

    private boolean existExtension(String str) {
        return !TextUtils.isEmpty(FileUtils.getExtension(str));
    }

    private ArrayList<File> getTrashDirs(Context context) {
        File file;
        HashMap<String, File> internalTrash = this.mTrashFactory.getInternalTrash(".Trash");
        ArrayList<File> arrayList = new ArrayList<>(internalTrash.values());
        if (FileUtils.isSdcardMounted(context) && !internalTrash.isEmpty() && (file = internalTrash.get(FileUtils.EXTERNAL_STORAGE_DIR)) != null) {
            String replace = file.getAbsolutePath().replace(FileUtils.EXTERNAL_STORAGE_DIR, "");
            for (String A10 : FileUtils.getSdcardDirList()) {
                arrayList.add(new SecureFile(C0212a.A(A10, replace)));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void scanDone() {
        Log.d(this.TAG, "requested scanning is completed.");
    }

    public void addScanPath(String str) {
        if (!TextUtils.isEmpty(str) && !isSecureDataPath(str)) {
            this.mScanPaths.add(str);
        }
    }

    public void bulkScan(boolean z) {
        if (!this.mScanPaths.isEmpty()) {
            int size = this.mScanPaths.size();
            if (size >= 2 || z) {
                getLogger().scanned(size);
                this.mMediaScanner.scanFiles(this.mScanPaths);
                this.mScanPaths.clear();
                if (z) {
                    this.mMediaScanner.setBulk(false);
                }
            }
        }
    }

    public String copyFile(String str, String str2) {
        try {
            return FileOpUtils.copy(str, str2, false);
        } catch (IOException e) {
            j.r(e, new StringBuilder("Copy failed. "), this.TAG);
            return null;
        }
    }

    public void createNoMediaFile() {
        Iterator<File> it = this.mTrashDirs.iterator();
        while (it.hasNext()) {
            FileUtils.createNewFileIfAbsent(it.next().getAbsolutePath() + File.separator + ".nomedia");
        }
    }

    public void deleteCloudThumbnail(String str) {
        if (!TextUtils.isEmpty(str) && !FileUtils.delete(str)) {
            String str2 = this.TAG;
            Log.w(str2, "unable to delete cloud thumbnail [" + FileUtils.exists(str) + "]");
        }
    }

    public boolean deleteFromTrashDB(String str) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (this.mTrashProvider.deleteTrash("__absPath =? ", new String[]{str}) > 0) {
                z = true;
            } else {
                z = false;
            }
            return z;
        } finally {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("deleted from Trash db ["));
        }
    }

    public void done() {
        TrashProgressListener trashProgressListener = this.mProgressListener;
        if (trashProgressListener != null) {
            trashProgressListener.onComplete();
        }
    }

    public void dump(TrashLogType trashLogType, String str) {
        getLogger().dump(trashLogType, str, true);
    }

    public String getDump(FileItemInterface fileItemInterface) {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(fileItemInterface.toString());
        String path = fileItemInterface.getPath();
        if (path != null) {
            sb2.append("=P{e:");
            sb2.append(FileUtils.exists(path));
            sb2.append(",sd:");
            if (FileUtils.isInRemovableStorage(path)) {
                sb2.append("true(");
                if (path.startsWith(FileUtils.getSdcardRwDir())) {
                    str = "rw";
                } else {
                    str = "r";
                }
                sb2.append(str);
                sb2.append(")");
            } else {
                sb2.append(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
            }
            sb2.append("}");
        } else {
            sb2.append("=P{null}");
        }
        return sb2.toString();
    }

    public CloudErrorType getErrorCode(Media media) {
        if (media == null) {
            return CloudErrorType.ETC;
        }
        return CloudErrorType.parseOf(media.rcode.intValue());
    }

    public abstract TrashLogger getLogger();

    public Media getMedia(FileItemInterface fileItemInterface) {
        Media media = new Media();
        media.photoId = fileItemInterface.getCloudServerId();
        media.clientTimestamp = Long.valueOf(fileItemInterface.getCloudTimestamp());
        return media;
    }

    public String getPathWithCheckDataMatch(TrashBaseItem trashBaseItem) {
        String path = trashBaseItem.getPath();
        if (!existExtension(FileUtils.getNameFromPath(path)) || !trashBaseItem.isNotMatchData()) {
            return path;
        }
        return path.substring(0, path.lastIndexOf(46));
    }

    public int getRate() {
        int i2 = this.mTotalCount;
        if (i2 != 0) {
            return (this.mProgressedCount * 100) / i2;
        }
        return 0;
    }

    public String getReadableTrashStorage() {
        Object[] objArr;
        String str;
        if (Features.isEnabled(Features.SUPPORT_ONE_TRASH)) {
            objArr = TrashProviderFactory.getInstance(AppResources.getAppContext()).getTrashInfo();
        } else {
            objArr = FileUtils.getDirectoryInfo(this.mTrashDirs);
        }
        double longValue = (double) ((Long) objArr[3]).longValue();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(StringCompat.toReadableSize((double) ((Long) objArr[2]).longValue()));
        if (longValue > MapUtil.INVALID_LOCATION) {
            str = "; " + StringCompat.toReadableSize(longValue) + " in " + this.mStringNewAlbumStorageSdCard;
        } else {
            str = "";
        }
        sb2.append(str);
        return sb2.toString();
    }

    public Object[] getTrashDirectoryInfo() {
        return FileUtils.getDirectoryInfo(this.mTrashDirs);
    }

    public final MediaItem getTrashItem(Cursor cursor) {
        MediaItem create = MediaItemBuilder.create(cursor);
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            String str = TrashData.of(create).restoreData;
            if (!TextUtils.isEmpty(str)) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    create.setGroupMediaId(jSONObject.getLong("__burstGroupID"));
                    if (jSONObject.getInt("__bestImage") > 0) {
                        create.setBestImage();
                    }
                    create.getCloudData().thumbPath = jSONObject.getString("__cloudTP");
                    return create;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return create;
    }

    public void increaseProgress() {
        int i2;
        TrashProgressListener trashProgressListener = this.mProgressListener;
        if (trashProgressListener != null && (i2 = this.mTotalCount) != 0) {
            int i7 = this.mProgressedCount + 1;
            this.mProgressedCount = i7;
            trashProgressListener.onProgress((i7 * 100) / i2);
        }
    }

    public boolean isCloud(StorageType storageType) {
        if (StorageType.Cloud.equals(storageType) || StorageType.LocalCloud.equals(storageType)) {
            return true;
        }
        return false;
    }

    public boolean isCloudOnly(StorageType storageType) {
        return StorageType.Cloud.equals(storageType);
    }

    public boolean isCopyOrDeleteCloudThumbnail(TrashBaseItem trashBaseItem) {
        String str;
        if (keepCloudThumbnail(trashBaseItem)) {
            if (isCloudOnly(trashBaseItem.getStorageType())) {
                str = trashBaseItem.getPath();
            } else {
                str = trashBaseItem.getLCThumbPath();
            }
            if (!TextUtils.isEmpty(str)) {
                Iterator<File> it = this.mTrashDirs.iterator();
                while (it.hasNext()) {
                    File next = it.next();
                    if (next != null && str.startsWith(next.getAbsolutePath())) {
                        return true;
                    }
                }
                return str.startsWith("/data/sec/cloud/.Trash");
            }
        }
        return true;
    }

    public boolean isLocal(StorageType storageType) {
        if (StorageType.Local.equals(storageType) || StorageType.LocalCloud.equals(storageType)) {
            return true;
        }
        return false;
    }

    public boolean isLocalCloud(StorageType storageType) {
        return StorageType.LocalCloud.equals(storageType);
    }

    public boolean isLocalOnly(StorageType storageType) {
        return StorageType.Local.equals(storageType);
    }

    public boolean isSecureDataPath(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/data/")) {
            return false;
        }
        return true;
    }

    public boolean isSucceed() {
        return getLogger().isSucceed();
    }

    public boolean isTrashEmpty() {
        if (this.mTrashProvider.getTrashTotalCount() == 0) {
            return true;
        }
        return false;
    }

    public boolean keepCloudThumbnail(TrashBaseItem trashBaseItem) {
        if (!trashBaseItem.isCloud() || !Features.isEnabled(Features.SUPPORT_ONE_TRASH_CLOUD_THUMBNAIL_NOT_REMOVE)) {
            return false;
        }
        return true;
    }

    public String moveFile(TrashBaseItem trashBaseItem, String str, String str2, boolean z) {
        String str3;
        try {
            if (!Features.isEnabled(Features.SUPPORT_PPP_MENU) || !FileUtils.isPostProcessingFile(str)) {
                str3 = FileOpUtils.move(str, str2, z);
            } else {
                str3 = FileOpUtils.copy(str, str2, z);
            }
        } catch (Exception e) {
            String message = e.getMessage();
            trashBaseItem.setMoveError(message);
            a.u("move failed. ", message, this.TAG);
            String str4 = this.TAG;
            Log.e(str4, "[" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(str2) + "]");
            str3 = null;
        }
        if (str3 == null || new File(str3).exists()) {
            return str3;
        }
        Log.e(this.TAG, "move failed. File.renameTo() returns true but file not exist");
        String str5 = this.TAG;
        Log.e(str5, "[" + Logger.getEncodedString(str) + "][" + Logger.getEncodedString(str2) + "][" + Logger.getEncodedString(str3) + "]");
        return null;
    }

    public void preDump(TrashLogType trashLogType, int i2, int i7, int i8, boolean z, String str) {
        getLogger().preDump(trashLogType, i2, i7, i8, z, str);
    }

    public void printError(Exception exc) {
        exc.printStackTrace();
    }

    public final void removeDiskCache(TrashBaseItem trashBaseItem) {
        ThumbnailLoader.getInstance().removeDiskCache(trashBaseItem);
    }

    public void setInterrupted() {
        this.mInterrupted = true;
    }

    public void setProgressListener(int i2, TrashProgressListener trashProgressListener) {
        this.mProgressedCount = 0;
        this.mTotalCount = i2;
        this.mProgressListener = trashProgressListener;
    }

    public abstract String tag();

    public void updateTotalDeleteCount(int i2) {
        this.mTotalCount += i2;
    }

    public boolean updateTrashDb(ContentValues contentValues, String str, String[] strArr) {
        String str2;
        StringBuilder sb2;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        try {
            if (this.mTrashProvider.updateTrash(contentValues, str, strArr) > 0) {
                z = true;
            }
            str2 = this.TAG;
            sb2 = new StringBuilder("updated from trash db [");
        } catch (Exception e) {
            e.printStackTrace();
            str2 = this.TAG;
            sb2 = new StringBuilder("updated from trash db [");
        } catch (Throwable th) {
            j.m(currentTimeMillis, "]", this.TAG, new StringBuilder("updated from trash db ["));
            throw th;
        }
        j.m(currentTimeMillis, "]", str2, sb2);
        return z;
    }
}
