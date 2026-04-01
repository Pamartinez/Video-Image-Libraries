package com.samsung.android.gallery.app.service;

import S3.d;
import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.module.service.download.DownloadTask;
import com.samsung.android.gallery.module.service.download.LTWDownloadTask;
import com.samsung.android.gallery.support.utils.Utils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LTWDownloadService extends DownloadService {
    private boolean isTaskProgressing() {
        return this.mTasks.values().stream().anyMatch(new d(24));
    }

    private void startAlbum(int i2) {
        Intent intent = new Intent("com.android.gallery.action.SHORTCUT_ALBUM_VIEW");
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("ALBUM_ID", i2);
        intent.addFlags(335544320);
        startActivity(intent);
    }

    public DownloadTask createDownloadTask(Intent intent) {
        return new LTWDownloadTask(this, intent.getStringExtra("target_album_path"), intent.getIntExtra("selected_album_id", -1)).setCallback(this).setNotificationHelper(this.mNotificationHelper);
    }

    public String getServiceClassName() {
        return "com.samsung.android.gallery.app.service.LTWDownloadService";
    }

    public void onInterruptService(Intent intent) {
        super.onInterruptService(intent);
        stopService();
    }

    public void onStartService(Intent intent) {
        if (isTaskProgressing()) {
            Utils.showToast((Context) this, (int) R.string.another_action_already_in_progress);
        } else {
            super.onStartService(intent);
        }
    }

    public void startGalleryActivity(Intent intent) {
        int intExtra = intent.getIntExtra("ALBUM_ID", 0);
        if (intExtra == 0) {
            startMain();
        } else {
            startAlbum(intExtra);
        }
    }
}
