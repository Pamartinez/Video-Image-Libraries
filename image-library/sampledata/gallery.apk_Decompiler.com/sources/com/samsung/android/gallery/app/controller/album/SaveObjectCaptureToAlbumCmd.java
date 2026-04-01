package com.samsung.android.gallery.app.controller.album;

import A6.a;
import A9.b;
import H3.n;
import android.content.ClipDescription;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.PersistableBundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.database.dal.mp.helper.TagEditApi;
import com.samsung.android.gallery.module.deepsky.DeepSkyHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SecureFile;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveObjectCaptureToAlbumCmd extends BaseCommand {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFile$1(String str, String str2, Uri uri) {
        ThreadUtil.postOnBgThreadDelayed(new b(this, str, uri, 24), 500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveFile$2(String str, String str2, String str3) {
        saveAndScan(getContext(), str, str2, new n(0, this, str3));
    }

    private void saveAndScan(Context context, String str, String str2, MediaScannerConnection.OnScanCompletedListener onScanCompletedListener) {
        String str3;
        SecureFile secureFile = new SecureFile(str2);
        if (!FileUtils.makeDirectoryIfAbsent((File) secureFile)) {
            Log.e(this.TAG, "fail to save. target album is not exist");
            return;
        }
        if (FileUtils.exists(str)) {
            str3 = secureFile.getAbsolutePath() + File.separator + FileUtils.getNameFromPath(str);
        } else {
            str3 = null;
            str = null;
        }
        if (str != null) {
            FileUtils.move(str, str3, true);
            MediaScannerConnection.scanFile(context.getApplicationContext(), new String[]{str3}, (String[]) null, onScanCompletedListener);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: saveCaptureInfo */
    public void lambda$saveFile$0(String str, Uri uri) {
        if (str == null) {
            Log.e(this.TAG, "save capture info failed, given path is null.");
            return;
        }
        try {
            if (!new TagEditApi().updateCapturedFilePath(Long.parseLong(uri.getLastPathSegment()), str)) {
                Log.e(this.TAG, "save capture info to db failed");
            }
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "save capture info failed, ", (Throwable) e);
        }
    }

    private void saveFile(ClipDescription clipDescription, String str) {
        if (clipDescription != null) {
            PersistableBundle extras = clipDescription.getExtras();
            if (extras != null) {
                DeepSkyHelper.post(new a((Object) this, (Object) extras.getString("captured_file_path"), (Object) str, (Object) extras.getString("capture_info_path"), 6));
                return;
            }
            Log.e(this.TAG, "fail to save. extras is null.");
            return;
        }
        Log.e(this.TAG, "fail to save. description is null.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onExecute(com.samsung.android.gallery.app.controller.EventContext r4, java.lang.Object... r5) {
        /*
            r3 = this;
            r4 = 0
            r0 = r5[r4]
            android.content.ClipData r0 = (android.content.ClipData) r0
            if (r0 != 0) goto L_0x000f
            java.lang.String r3 = r3.TAG
            java.lang.String r4 = "fail to save. clipData is null."
            com.samsung.android.gallery.support.utils.Log.e(r3, r4)
            return
        L_0x000f:
            r1 = 1
            r5 = r5[r1]
            com.samsung.android.gallery.module.data.MediaItem r5 = (com.samsung.android.gallery.module.data.MediaItem) r5
            boolean r1 = r5.isCloudOnly()
            if (r1 == 0) goto L_0x001f
            java.lang.String r5 = r5.getCloudServerPath()
            goto L_0x0023
        L_0x001f:
            java.lang.String r5 = r5.getPath()
        L_0x0023:
            if (r5 == 0) goto L_0x0036
            java.lang.String r1 = java.io.File.separator
            boolean r2 = r5.contains(r1)
            if (r2 == 0) goto L_0x0036
            int r1 = r5.lastIndexOf(r1)
            java.lang.String r4 = r5.substring(r4, r1)
            goto L_0x0037
        L_0x0036:
            r4 = 0
        L_0x0037:
            if (r4 != 0) goto L_0x0041
            java.lang.String r3 = r3.TAG
            java.lang.String r4 = "fail to save. targetAlbumPath is null."
            com.samsung.android.gallery.support.utils.Log.e(r3, r4)
            return
        L_0x0041:
            android.content.ClipDescription r5 = r0.getDescription()
            r3.saveFile(r5, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.controller.album.SaveObjectCaptureToAlbumCmd.onExecute(com.samsung.android.gallery.app.controller.EventContext, java.lang.Object[]):void");
    }
}
