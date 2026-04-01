package com.samsung.android.gallery.module.camera;

import A.a;
import Ba.f;
import L5.b;
import M4.d;
import M8.c;
import N2.j;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.data.MediaItemRetryLoader;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.module.trash.factory.TrashProviderFactory;
import com.samsung.android.gallery.module.trash.helper.TrashDeleteHelper;
import com.samsung.android.gallery.module.trash.helper.TrashEmptyHelper;
import com.samsung.android.gallery.module.trash.helper.TrashHelperFactory;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SecureFile;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PostProcessingHelper {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SupportShotModes {
        static final HashSet<String> set = new HashSet<>(List.of("image", "live_focus", "Selfie focus", "selective_focus", "motion_photo", "selfie", "3d_capture_image"));
    }

    private void deleteOriginal(Context context, long j2, MediaItem mediaItem) {
        StringBuilder j3 = j.j(j2, "deleteAfterPpp delete original [", "][");
        j3.append(Logger.getEncodedString(mediaItem.getPath()));
        j3.append("]");
        Log.d("PPP_Helper", j3.toString());
        TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(context, false);
        deleteHelper.deleteItem(mediaItem);
        deleteHelper.done();
        DebugLogger pppInstance = DebugLogger.getPppInstance();
        pppInstance.insertLog(j2 + ":completed(D)");
    }

    private void emptyFromTrash(Context context, MediaItem mediaItem) {
        TrashEmptyHelper emptyHelper = TrashHelperFactory.getEmptyHelper(context, false);
        Log.d("PPP_Helper", "emptyFromTrash : " + mediaItem.getFileId(), Logger.getEncodedString(mediaItem.getPath()));
        emptyHelper.emptyItem(mediaItem);
        emptyHelper.done();
    }

    private MediaItem getItemFromTrash(Context context, long j2) {
        Cursor trashCursor = TrashProviderFactory.getInstance(context).getTrashCursor(j2);
        if (trashCursor != null) {
            try {
                if (trashCursor.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(trashCursor);
                    trashCursor.close();
                    return load;
                }
            } catch (Throwable th) {
                th.addSuppressed(th);
            }
        }
        if (trashCursor == null) {
            return null;
        }
        trashCursor.close();
        return null;
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$pppCompleted$1(MediaItem mediaItem) {
        boolean z;
        if (mediaItem.isPostProcessing() || mediaItem.getMediaId() == 0) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$pppCompleted$2(Context context, long j2, MediaItem mediaItem) {
        MediaItem itemFromTrash = getItemFromTrash(context, j2);
        if (itemFromTrash != null) {
            originalMoveToTrash(context, j2, mediaItem, itemFromTrash);
        } else {
            deleteOriginal(context, j2, mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$updateCompletedPpp$4(MediaItem mediaItem) {
        boolean z;
        if (mediaItem.isPostProcessing() || mediaItem.getMediaId() == 0) {
            z = false;
        } else {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateCompletedPpp$6(ArrayList arrayList, long j2, MediaItem mediaItem) {
        if (arrayList.isEmpty()) {
            Log.e((CharSequence) "PPP_Helper", a.f("updateCompletedPpp skip ", j2), mediaItem);
        } else if (mediaItem.isDng()) {
            Log.e((CharSequence) "PPP_Helper", a.f("updateCompletedPpp skip. dng :", j2), mediaItem);
        } else {
            String shotMode = mediaItem.getShotMode();
            if (shotMode == null || SupportShotModes.set.contains(shotMode)) {
                String f = a.f("publish PPP_COMPLETED : ", j2);
                Log.d("PPP_Helper", f, mediaItem, "dataStamp=" + mediaItem.getDataStamp());
                Log.d("PPP_Helper", "publish PPP_COMPLETED : " + Arrays.toString(arrayList.toArray()));
                arrayList.forEach(new f((Object) this, j2, mediaItem, 3));
                return;
            }
            Log.w("PPP_Helper", "ppp not support for ".concat(shotMode));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onFailGetTrashed */
    public void lambda$pppCompleted$3(Context context, long j2, MediaItem mediaItem) {
        if (mediaItem != null) {
            Object loadSecMediaId = MediaItemLoader.loadSecMediaId(j2);
            String f = a.f("deleteAfterPpp load fail : ", j2);
            if (loadSecMediaId == null) {
                loadSecMediaId = "deleted";
            }
            Log.e((CharSequence) "PPP_Helper", f, loadSecMediaId);
            DebugLogger pppInstance = DebugLogger.getPppInstance();
            pppInstance.insertLog(j2 + ":completed(NE)");
            return;
        }
        Log.d("PPP_Helper", "not deleted : " + j2);
        DebugLogger pppInstance2 = DebugLogger.getPppInstance();
        pppInstance2.insertLog(j2 + ":completed(ND)");
        if (Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            updateCompletedPpp(j2);
        }
    }

    private void originalMoveToTrash(Context context, long j2, MediaItem mediaItem, MediaItem mediaItem2) {
        TrashData.of(mediaItem).deleteTime = TrashData.of(mediaItem2).deleteTime;
        emptyFromTrash(context, mediaItem2);
        Log.d("PPP_Helper", a.f("deleteAfterPpp move to Trash original : ", j2), mediaItem, Logger.getEncodedString(mediaItem.getPath()));
        TrashDeleteHelper deleteHelper = TrashHelperFactory.getDeleteHelper(context);
        deleteHelper.deleteItem(mediaItem);
        deleteHelper.done();
        DebugLogger pppInstance = DebugLogger.getPppInstance();
        pppInstance.insertLog(j2 + ":completed(T)");
    }

    /* access modifiers changed from: private */
    /* renamed from: publishCompletedItem */
    public void lambda$updateCompletedPpp$5(long j2, MediaItem mediaItem, Blackboard blackboard) {
        blackboard.publish(ArgumentsUtil.appendArgs("data://pppCompleted", "date_time", String.valueOf(System.currentTimeMillis())), mediaItem);
    }

    private void updateCompletedPpp(long j2) {
        long j3 = j2;
        new MediaItemRetryLoader(20, 10).setResultChecker(new b(8)).setSuccessCallback(new f((Object) this, (Object) Blackboard.getActivityBlackboardList(), j3, 2)).setFailCallback(new c(j3, 0)).getMediaItemFromFileId(j3);
    }

    public boolean isInTrash(Context context, long j2) {
        if (getItemFromTrash(context, j2) != null) {
            return true;
        }
        return false;
    }

    public void pppCompleted(Context context, long j2) {
        DebugLogger pppInstance = DebugLogger.getPppInstance();
        pppInstance.insertLog(j2 + ":completed");
        Context context2 = context;
        long j3 = j2;
        new MediaItemRetryLoader(10, 50).updateQueryParams(new d(15)).setResultChecker(new b(6)).setRetryChecker(new b(7)).setSuccessCallback(new M8.b(this, context2, j3, 0)).setFailCallback(new M8.b(this, context2, j3, 1)).getMediaItemFromFileId(j2);
    }

    public void saveTemp(String str, Bundle bundle) {
        String string = bundle.getString("tempFilePath", (String) null);
        String directoryFromPath = FileUtils.getDirectoryFromPath(str, true);
        String nameFromPath = FileUtils.getNameFromPath(string);
        if (!TextUtils.isEmpty(nameFromPath)) {
            String A10 = C0212a.A(directoryFromPath, nameFromPath);
            if (FileUtils.copy(string, A10)) {
                MediaScanner.scanFile(A10, new M8.a(A10, 0));
            } else {
                a.u("save temp fail : ", A10, "PPP_Helper");
            }
        }
    }

    public void validate(String str, Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("ppp Completed failed, invalid arg={null}");
        } else if (bundle == null) {
            throw new IllegalArgumentException("ppp Completed failed, invalid extras={null}");
        } else if (bundle.getLong("secMpId", -1) == -1) {
            throw new IllegalArgumentException("ppp Completed failed, invalid extras.secMpId");
        } else if (bundle.getString("tempFilePath", (String) null) != null) {
            SecureFile secureFile = new SecureFile(str);
            if (!secureFile.exists()) {
                throw new IllegalArgumentException("ppp Completed failed, file not exist : ".concat(str));
            } else if (!secureFile.canWrite()) {
                throw new IllegalArgumentException("ppp Completed failed, can not write : ".concat(str));
            }
        } else {
            throw new IllegalArgumentException("ppp Completed failed, invalid extras.tempFilePath");
        }
    }
}
