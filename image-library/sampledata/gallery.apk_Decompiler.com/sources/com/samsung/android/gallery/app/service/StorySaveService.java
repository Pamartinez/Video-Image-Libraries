package com.samsung.android.gallery.app.service;

import A4.B;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import androidx.window.embedding.c;
import c4.C0442l;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.service.support.StoryServiceHelper;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StorySaveService extends StoryBaseService {
    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startViewer$1() {
        onTerminateService((Intent) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startViewer$2(String str) {
        Cursor query;
        MediaItem mediaItem = null;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new B(str, 23));
            if (query != null) {
                if (query.moveToFirst()) {
                    mediaItem = MediaItemBuilder.create(query);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (mediaItem != null) {
            ArrayList arrayList = new ArrayList();
            Intent intent = new Intent("android.intent.action.VIEW");
            arrayList.add(ContentUri.getUri(mediaItem));
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
            intent.setData((Uri) arrayList.get(0));
            intent.putExtra("uriListData", arrayList);
            intent.putExtra("useUriList", true);
            intent.addFlags(335544320);
            startActivity(intent);
        } else {
            Log.e(this.TAG, "save file is not exist");
        }
        ThreadUtil.postOnUiThread(new C0442l(this, 1));
        return;
        throw th;
    }

    private void startViewer(Intent intent) {
        int intExtra = intent.getIntExtra("notification_id", -1);
        String stringExtra = intent.getStringExtra("notification_data");
        this.mNotificationHelper.dismiss(intExtra);
        removeTask(intExtra);
        if (!TextUtils.isEmpty(stringExtra)) {
            ThreadUtil.postOnBgThread(new c(11, this, stringExtra));
        }
    }

    public StoryServiceHelper.OperationType getOperationType() {
        if (this.mFromStoryPictures) {
            return StoryServiceHelper.OperationType.SAVE_CARD;
        }
        return StoryServiceHelper.OperationType.SAVE_COVER;
    }

    public String getServiceClassName() {
        return "com.samsung.android.gallery.app.service.StorySaveService";
    }

    public void onCallActivity(Intent intent) {
        Log.d(this.TAG, "call gallery activity");
        startViewer(intent);
    }

    public void onFinishedInternal(String str) {
        if (!TextUtils.isEmpty(str)) {
            MediaScanner.scanFolder(FileUtils.getDirectoryFromPath(str), (MediaScannerListener) null);
        }
    }

    public void onTerminateService(Intent intent) {
        super.onTerminateService(intent);
        if (isTaskEmpty()) {
            executeHandlePostDelay(new C0442l(this, 0), 500);
        } else {
            stopForeground(false);
        }
    }

    public void onServicePrepared(String str) {
    }
}
