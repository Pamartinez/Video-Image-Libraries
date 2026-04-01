package com.samsung.android.gallery.app.controller.internals;

import M5.a;
import O3.u;
import android.content.res.Resources;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveAsCopyCmd extends BaseCommand {
    private String getTargetPath(String str, String str2) {
        return new FileNameBuilder(str2).setExtension(FileUtils.getExtension(str)).buildUnique();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$moveFile$1(String str, Uri uri) {
        if (uri != null) {
            finish(str, uri);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(MediaItem mediaItem) {
        showToastInternal(mediaItem, saveAsFile(mediaItem));
    }

    private boolean moveFile(String str, String str2, String str3) {
        if (!FileUtils.move(str2, str3, true)) {
            return false;
        }
        FileUtils.setDateModified(str3, FileUtils.getDateModified(str));
        MediaScannerConnection.scanFile(getApplicationContext(), new String[]{str3}, (String[]) null, new u(0, this));
        return true;
    }

    private String saveAsFile(MediaItem mediaItem) {
        String str = (String) mediaItem.getExtra(ExtrasID.ORIGIN_PATH);
        if (str == null) {
            return null;
        }
        String longExposurePath = VideoPropData.getLongExposurePath(mediaItem);
        String targetPath = getTargetPath(longExposurePath, str);
        if (longExposurePath != null && moveFile(str, longExposurePath, targetPath)) {
            return targetPath;
        }
        String str2 = this.TAG;
        Log.e(str2, "save fail=" + Logger.getEncodedString(longExposurePath) + " , " + Logger.getEncodedString(targetPath));
        return null;
    }

    private void showToastInternal(MediaItem mediaItem, String str) {
        int i2;
        Resources resources = getContext().getResources();
        if (TextUtils.isEmpty(str)) {
            showToast(resources.getString(R.string.unable_to_save));
            return;
        }
        String translatedDirectory = BucketUtils.getTranslatedDirectory(str);
        if (mediaItem.isVideo()) {
            i2 = R.string.video_saved_in;
        } else {
            i2 = R.string.toast_image_saved_in;
        }
        showToast(resources.getString(i2, new Object[]{translatedDirectory}));
    }

    public void finish(String str, Uri uri) {
        getBlackboard().publish("command://MoveCMD", "command://MoveCMD/FinishFragment");
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_VIEWER_SAVE_AS_COPY_CLICK.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new a(20, this, objArr[0]));
    }
}
