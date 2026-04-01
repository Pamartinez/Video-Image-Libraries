package com.samsung.android.gallery.app.controller.internals;

import A.a;
import A4.C0370e;
import I5.e;
import O3.b;
import O3.t;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.module.clip.textextraction.DocumentScanner;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.fileio.redact.FileRedactorBuilder;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.utils.FileOpUtils;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevertOriginalImageCmd extends BaseCommand {
    long mBackupDateModified;
    ExifInterface mBackupExif;
    String mExpected;
    MediaItem mMediaItem;
    String mSource;
    String mTarget;
    Uri mTargetUri;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class ProcessManager {
        static final ConcurrentHashMap<Long, Long> map = new ConcurrentHashMap<>();

        public static void add(long j2) {
            map.put(Long.valueOf(j2), Long.valueOf(System.currentTimeMillis()));
        }

        public static long get(long j2) {
            Long l = map.get(Long.valueOf(j2));
            if (l == null) {
                return 0;
            }
            return l.longValue();
        }

        public static boolean isProcessing(long j2) {
            Long l = map.get(Long.valueOf(j2));
            if (l == null || System.currentTimeMillis() - l.longValue() >= 60000) {
                return false;
            }
            return true;
        }

        public static void remove(long j2) {
            map.remove(Long.valueOf(j2));
        }
    }

    private MediaItem buildRecoveredItem(MediaItem mediaItem, String str, Uri uri) {
        Cursor query;
        Throwable th;
        MediaItem clone = mediaItem.clone();
        clone.setPath(str);
        clone.setFileSize(FileUtils.length(str));
        try {
            query = getContext().getContentResolver().query(uri, (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    clone.setSize(query.getInt(query.getColumnIndex("width")), query.getInt(query.getColumnIndex("height")));
                    clone.setOrientation(query.getInt(query.getColumnIndex("orientation")));
                    clone.setMimeType(query.getString(query.getColumnIndex("mime_type")));
                    clone.setDateModified(query.getLong(query.getColumnIndex(IParameterKey.DATE_MODIFIED)));
                }
            }
            if (query != null) {
                query.close();
            }
            return clone;
        } catch (Exception e) {
            Exception exc = e;
            a.s(exc, new StringBuilder("buildRecoveredItem failed. e="), this.TAG);
            return clone;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    private void executeRevertJob(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
        this.mTargetUri = ContentUri.getWritableUri(mediaItem);
        this.mTarget = mediaItem.getPath();
        String hiddenOriginalPath = NondestructiveEditor.getHiddenOriginalPath(mediaItem.isVideo(), this.mTarget);
        this.mSource = hiddenOriginalPath;
        if (isInvalidPath(this.mTarget, hiddenOriginalPath)) {
            Log.e(this.TAG, "revertJob#prepareData failed. invalid path");
            return;
        }
        this.mExpected = FileUtils.replaceExtension(this.mTarget, FileUtils.getExtension(this.mSource));
        this.mBackupDateModified = FileUtils.getDateModified(this.mTarget);
        this.mBackupExif = ExifUtils.getExif(this.mTarget);
        String str = this.TAG;
        Log.d(str, "revertJob#prepareData " + MediaItemUtil.getDebugLog(mediaItem) + "\n" + ("SRC=" + FileUtils.info(this.mSource) + "\nDST=" + FileUtils.info(this.mTarget) + "\nEXP=" + Logger.getEncodedString(this.mExpected)));
        if (revertFile()) {
            MediaItem clone = mediaItem.clone();
            clone.setPath(this.mTarget);
            FileRedactorBuilder.edit(new FileItemInterface[]{clone}).setOperation(new A5.a(20, this)).setCallback(new b(5, this, mediaItem)).build().run();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$executeInternal$1(MediaItem mediaItem, ThreadPool.JobContext jobContext) {
        executeRevertJob(mediaItem);
        ThreadUtil.postOnBgThreadDelayed(new C0370e(mediaItem, 3), 1000);
        return Boolean.TRUE;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeRevertJob$2(long j2, MediaItem mediaItem, String str, Uri uri) {
        int i2;
        String str2 = this.TAG;
        Log.d(str2, "revertJob#scan-completed" + Logger.vt(uri, Long.valueOf(j2)) + "");
        prepareViewData(mediaItem, str, uri);
        cleanUpCache(mediaItem);
        new CleanCmhMediaContentInfoCmd().execute(getHandler(), Long.valueOf(mediaItem.getFileId()));
        NondestructiveEditor.removeHiddenOriginal(mediaItem.getFileId(), mediaItem.getComplexHashCode());
        BlackboardUtils.cancelAndEraseViewerBitmap(getBlackboard(), (FileItemInterface) mediaItem);
        if (mediaItem.isVideo()) {
            i2 = R.string.reverted_to_original_video;
        } else if (mediaItem.isGif()) {
            i2 = R.string.reverted_to_original_gif;
        } else {
            i2 = R.string.reverted_to_original_picture;
        }
        showToast(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeRevertJob$3(MediaItem mediaItem, int i2, int i7, int i8) {
        if (i7 > 0) {
            MediaScannerConnection.scanFile(getApplicationContext(), new String[]{this.mExpected}, (String[]) null, new t(this, System.currentTimeMillis(), mediaItem, 0));
            return;
        }
        Log.e(this.TAG, "revertJob#revert failed. recovery required");
        recoverFile();
    }

    private void prepareViewData(MediaItem mediaItem, String str, Uri uri) {
        System.currentTimeMillis();
        MediaItem buildRecoveredItem = buildRecoveredItem(mediaItem, str, uri);
        String str2 = this.TAG;
        Log.d(str2, "prepareViewData " + MediaItemUtil.getDebugLog(buildRecoveredItem));
        MetadataManager.getInstance().clear(buildRecoveredItem);
        ThumbnailLoader.getInstance().removeCache(mediaItem);
        if (buildRecoveredItem.isImage()) {
            getBlackboard().postEvent(EventMessage.obtain(3056, mediaItem.getThumbCacheKey()));
        }
        getBlackboard().postEvent(EventMessage.obtain(3043));
        getBlackboard().postEvent(EventMessage.obtain(3051, 0, str));
        getBlackboard().post("command://event/DataDirty", (Object) null);
        getBlackboard().erase(MediaItemUtil.getViewerBitmapKey(mediaItem));
    }

    public void cleanUpCache(MediaItem mediaItem) {
        if (mediaItem.isMotionPhoto()) {
            MetadataManager.getInstance().clear(mediaItem);
        }
        DocumentScanner.getInstance().resetCache(mediaItem.getFileId());
    }

    public final boolean copyFile(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!FileUtils.copy(str, str2)) {
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("copyFile failed");
            sb2.append(Logger.vt(currentTimeMillis));
            sb2.append(" ");
            sb2.append(Logger.getEncodedString(str + " > " + str2));
            Log.e(str3, sb2.toString());
            return false;
        }
        String str4 = this.TAG;
        Log.d(str4, "copyFile" + Logger.vt(currentTimeMillis) + "");
        return verifyFile(str, str2);
    }

    public void executeInternal(MediaItem mediaItem) {
        ProcessManager.add(mediaItem.getFileId());
        ThreadPool.getInstance().submit(new e(2, this, mediaItem));
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public boolean isInvalidPath(String str, String str2) {
        if (TextUtils.isEmpty(str) || !FileUtils.exists(str2)) {
            String str3 = this.TAG;
            Log.e(str3, "invalid file " + FileUtils.info(str2));
            return true;
        } else if (NondestructiveEditor.isInLegalStorage(str2)) {
            return false;
        } else {
            String str4 = this.TAG;
            Log.e(str4, "illegal path " + FileUtils.info(str2));
            return true;
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!(objArr == null || objArr.length == 0)) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem instanceof MediaItem) {
                MediaItem mediaItem2 = mediaItem;
                long fileId = mediaItem2.getFileId();
                if (ProcessManager.isProcessing(fileId)) {
                    Log.e((CharSequence) this.TAG, "onExecute skip. already under processing", Long.valueOf(fileId), Long.valueOf(ProcessManager.get(fileId)));
                    return;
                } else {
                    executeInternal(mediaItem2);
                    return;
                }
            }
        }
        String str = this.TAG;
        if (objArr == null) {
            objArr = new Object[]{"null"};
        }
        Log.e((CharSequence) str, "onExecute failed. wrong argument", objArr);
    }

    public final boolean renameFile(String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!FileUtils.rename(str, str2)) {
            String str3 = this.TAG;
            StringBuilder sb2 = new StringBuilder("renameFile failed");
            sb2.append(Logger.vt(currentTimeMillis));
            sb2.append(" ");
            sb2.append(Logger.getEncodedString(str + " > " + str2));
            Log.e(str3, sb2.toString());
            return false;
        }
        String str4 = this.TAG;
        Log.d(str4, "renameFile" + Logger.vt(currentTimeMillis) + "");
        return true;
    }

    public boolean revertFile() {
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        boolean copyFile = copyFile(this.mSource, this.mTarget);
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("revertFile");
        if (copyFile) {
            str = "";
        } else {
            str = " failed";
        }
        sb2.append(str);
        sb2.append(Logger.vt(currentTimeMillis));
        sb2.append(" ");
        sb2.append(FileUtils.info(this.mTarget));
        Log.d(str2, sb2.toString());
        return copyFile;
    }

    public boolean updateFile(FileItemInterface fileItemInterface) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.mTarget.equalsIgnoreCase(this.mExpected)) {
            String nameFromPath = FileUtils.getNameFromPath(this.mExpected);
            if (FileOpUtils.updateMimeType(getContext(), this.mTargetUri, nameFromPath, this.mExpected)) {
                if (FileUtils.length(this.mExpected) == 0) {
                    String str = this.TAG;
                    StringBuilder sb2 = new StringBuilder("updateFile#rename failed");
                    sb2.append(Logger.vt(currentTimeMillis));
                    sb2.append(" ");
                    sb2.append(Logger.getEncodedString(this.mTarget + " > " + nameFromPath));
                    Log.e(str, sb2.toString());
                    return false;
                }
                String str2 = this.TAG;
                Log.d(str2, "updateFile#rename" + Logger.vt(currentTimeMillis) + " " + FileUtils.info(this.mExpected));
            }
        } else if (this.mMediaItem.isImage() && this.mBackupExif != null && MimeType.isJpeg(FileType.getMimeType(this.mSource))) {
            ExifUtils.copyDateLocation(this.mBackupExif, this.mTarget);
        }
        if (this.mMediaItem.hasFilterAndTone()) {
            return true;
        }
        FileUtils.setDateModified(this.mExpected, this.mBackupDateModified);
        return true;
    }

    public final boolean verifyFile(String str, String str2) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        long length = FileUtils.length(str);
        if (length == FileUtils.length(str2)) {
            long j2 = length - 16;
            z = Arrays.equals(FileUtils.readBytes(str, j2, 16), FileUtils.readBytes(str2, j2, 16));
        } else {
            z = false;
        }
        if (z) {
            String str3 = this.TAG;
            Log.d(str3, "verifyFile" + Logger.vt(currentTimeMillis));
            return z;
        }
        String str4 = this.TAG;
        Log.e(str4, "verifyFile failed" + Logger.vt(Long.valueOf(length), Long.valueOf(currentTimeMillis)) + " " + FileUtils.info(str2));
        new InternalException(C0212a.p(new StringBuilder(), this.TAG, " verifyFile failed")).post();
        return z;
    }

    public void recoverFile() {
    }
}
