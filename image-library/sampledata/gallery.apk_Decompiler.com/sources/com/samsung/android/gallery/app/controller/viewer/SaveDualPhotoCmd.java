package com.samsung.android.gallery.app.controller.viewer;

import A.a;
import O3.t;
import R6.c;
import U5.b;
import android.media.MediaScannerConnection;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveDualPhotoCmd extends BaseCommand {
    private String createFileName(String str, String str2) {
        File file = new File(new File(str).getParent(), C0212a.A(str2, FileUtils.getExtension(str, true)));
        String absolutePath = file.getAbsolutePath();
        if (file.exists()) {
            file = new File(FileUtils.getNewFilePath(absolutePath));
        }
        return file.getAbsolutePath();
    }

    private boolean isDualShotViewChanged(FileItemInterface fileItemInterface) {
        String str = (String) fileItemInterface.getTag("dual-shot-state");
        if (str != null) {
            String[] split = str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            if (split.length <= 1 || split[0].equals(split[1])) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(String str, Boolean bool) {
        if (bool.booleanValue()) {
            showToast(getContext().getString(R.string.toast_image_saved, new Object[]{str}));
        } else {
            showToast((int) R.string.unable_to_save);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$1(MediaItem mediaItem, String str) {
        saveFile(mediaItem, str, new b(1, this, str));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFile$2(long j2, Consumer consumer, String str, Uri uri) {
        boolean z;
        String str2 = this.TAG;
        StringBuilder sb2 = new StringBuilder("saveFile {");
        sb2.append(uri);
        sb2.append("} +");
        a.x(sb2, j2, str2);
        if (consumer != null) {
            if (uri != null) {
                z = true;
            } else {
                z = false;
            }
            consumer.accept(Boolean.valueOf(z));
        }
    }

    private boolean saveCover(FileItemInterface fileItemInterface, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        String externalFilesDir = FileUtils.getExternalFilesDir("dual");
        File file = new File(externalFilesDir, fileItemInterface.getFileId() + "-copy.jpg");
        try {
            if (FileUtils.copy(new File(fileItemInterface.getPath()), file)) {
                if (!SeApiCompat.getSefFileCompat().deleteAllData(file)) {
                    return false;
                }
                ExifUtils.copyDateLocationIfAbsent(fileItemInterface.getPath(), file.getPath());
                FileUtils.copy(file, (File) new SecureFile(str));
                String str2 = this.TAG;
                Log.d(str2, "saveCover {" + fileItemInterface.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + FileUtils.length(str) + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            }
            FileUtils.delete(file);
            return true;
        } catch (IOException e) {
            Log.e((CharSequence) this.TAG, "saveFile failed", (Throwable) e);
            return false;
        } finally {
            FileUtils.delete(file);
        }
    }

    private boolean saveDual(FileItemInterface fileItemInterface, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bArr = (byte[]) fileItemInterface.getTag("data-stream");
        if (bArr == null) {
            Log.e(this.TAG, "saveDual failed. no stream");
            return false;
        }
        String externalFilesDir = FileUtils.getExternalFilesDir("dual");
        File file = new File(externalFilesDir, fileItemInterface.getFileId() + "-copy.jpg");
        try {
            if (FileUtils.writeFile(file.getPath(), bArr)) {
                ExifUtils.copyDateLocationIfAbsent(fileItemInterface.getPath(), file.getPath());
                FileUtils.copy(file, (File) new SecureFile(str));
                String str2 = this.TAG;
                Log.d(str2, "saveDual {" + fileItemInterface.getFileId() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + bArr.length + "} +" + (System.currentTimeMillis() - currentTimeMillis));
            }
            FileUtils.delete(file);
            return true;
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "saveFile failed", (Throwable) e);
            FileUtils.delete(file);
            return false;
        } catch (Throwable th) {
            FileUtils.delete(file);
            throw th;
        }
    }

    private void saveFile(MediaItem mediaItem, String str, Consumer<Boolean> consumer) {
        boolean z;
        long currentTimeMillis = System.currentTimeMillis();
        if (!PocFeatures.DUAL_PHOTO_PREVIEW || !isDualShotViewChanged(mediaItem)) {
            z = saveCover(mediaItem, str);
        } else {
            z = saveDual(mediaItem, str);
        }
        if (z) {
            MediaScannerConnection.scanFile(getContext(), new String[]{str}, (String[]) null, new t(this, currentTimeMillis, consumer, 1));
            return;
        }
        Consumer<Boolean> consumer2 = consumer;
        if (consumer2 != null) {
            consumer2.accept(Boolean.FALSE);
        }
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_IMAGE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem == null) {
            Log.e(this.TAG, "SaveDualPhotoCmd failed. null item");
            return;
        }
        String createFileName = createFileName(mediaItem.getPath(), TimeUtil.getFileTimestamp(System.currentTimeMillis()));
        String str = this.TAG;
        Log.d(str, "save {" + mediaItem.getTag("dual-shot-state") + "} " + MediaItemUtil.getSimpleLog(mediaItem));
        SimpleThreadPool.getInstance().execute(new c(this, mediaItem, createFileName, 20));
    }
}
