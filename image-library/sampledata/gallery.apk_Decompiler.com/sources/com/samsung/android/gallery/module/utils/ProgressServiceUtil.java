package com.samsung.android.gallery.module.utils;

import A.a;
import N2.j;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import g6.f;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProgressServiceUtil {
    private static final HashMap<String, String> CONTINUE_SERVICE_MAP = new HashMap<String, String>() {
        {
            put("AddTagService", "com.samsung.android.gallery.app.service.AddTagService");
            put("DeleteService", "com.samsung.android.gallery.app.service.DeleteService");
            put("EmptyService", "com.samsung.android.gallery.app.service.EmptyService");
            put("FileOperationService", "com.samsung.android.gallery.app.service.FileOperationService");
            put("MtpImportService", "com.samsung.android.gallery.app.service.MtpImportService");
            put("RestoreService", "com.samsung.android.gallery.app.service.RestoreService");
            put("SaveBurstShotService", "com.samsung.android.gallery.app.service.SaveGroupShotService");
            put("MotionPhotoClipService", "com.samsung.android.gallery.app.service.MotionPhotoClipService");
            put("FileOpService", "com.samsung.android.gallery.app.service.FileOpService");
        }
    };
    private final String mBlackboardName;
    private String mCurrentService = null;

    public ProgressServiceUtil(String str) {
        this.mBlackboardName = str;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$continueIfServiceRunning$0(String str, Blackboard blackboard) {
        String str2 = (String) blackboard.read("data://running_service");
        if (str2 != null) {
            this.mCurrentService = str2;
        }
    }

    public void continueIfServiceRunning(Context context) {
        Blackboard.getBlackboardMap().forEach(new f(10, this));
        String str = null;
        try {
            String str2 = this.mCurrentService;
            if (str2 != null) {
                String str3 = CONTINUE_SERVICE_MAP.get(str2);
                try {
                    this.mCurrentService = null;
                    if (str3 != null) {
                        Log.d("ProgressServiceUtil", str3.concat(" is running, so continue it."));
                        Intent intent = new Intent();
                        intent.setClassName("com.sec.android.gallery3d", str3);
                        intent.setAction("com.samsung.android.gallery.app.service.CONTINUE_SERVICE ");
                        intent.putExtra("blackboard_name", this.mBlackboardName);
                        context.startService(intent);
                    }
                } catch (Exception e) {
                    e = e;
                    str = str3;
                    a.s(e, j.k("unable to ", str, " continue service. "), "ProgressServiceUtil");
                }
            }
        } catch (Exception e7) {
            e = e7;
            a.s(e, j.k("unable to ", str, " continue service. "), "ProgressServiceUtil");
        }
    }

    public void stopMtpService(Context context, String str) {
        try {
            Log.d("ProgressServiceUtil", "import service is running, so stop it.");
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MtpImportService");
            intent.setAction("com.samsung.android.gallery.app.service.STOP_SERVICE");
            intent.putExtra("mtp_device_name", str);
            intent.putExtra("mtp_import_interruption", true);
            context.startService(intent);
        } catch (Exception e) {
            a.s(e, new StringBuilder("unable to stop mtp service. "), "ProgressServiceUtil");
        }
    }
}
