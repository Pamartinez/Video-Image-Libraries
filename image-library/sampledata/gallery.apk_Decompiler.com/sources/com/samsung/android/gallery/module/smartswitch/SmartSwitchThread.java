package com.samsung.android.gallery.module.smartswitch;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.support.utils.BnRDocStorageHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import java.io.File;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class SmartSwitchThread extends Thread {
    final String TAG = getClass().getSimpleName();
    final Context mContext;
    String mDirPath;
    final Intent mIntent;

    public SmartSwitchThread(Context context, Intent intent) {
        this.mContext = context;
        this.mIntent = intent;
    }

    public void clearTargetDirectory() {
        FileUtils.deleteDirectory(new File(this.mDirPath));
        FileUtils.makeDirectoryIfAbsent(this.mDirPath);
    }

    public abstract boolean executeMainAction(List<Uri> list);

    public abstract void executePostAction(List<Uri> list, boolean z);

    public void executePreAction(List<Uri> list) {
        clearTargetDirectory();
    }

    public abstract boolean isValidPath(List<Uri> list);

    public void run() {
        List<Uri> pathUris = BnRDocStorageHelper.getPathUris(this.mContext, this.mIntent);
        if (isValidPath(pathUris)) {
            executePreAction(pathUris);
            if (!TextUtils.isEmpty(this.mDirPath)) {
                executePostAction(pathUris, executeMainAction(pathUris));
                return;
            }
        }
        SmartSwitchBroadcastSender.respondError(this.mContext, this.mIntent);
    }
}
