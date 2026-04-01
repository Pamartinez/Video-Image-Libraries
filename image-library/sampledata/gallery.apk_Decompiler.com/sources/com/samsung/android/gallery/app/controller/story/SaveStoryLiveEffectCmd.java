package com.samsung.android.gallery.app.controller.story;

import N2.j;
import R6.c;
import U3.e;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.StoryCoverData;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileNameBuilder;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.sec.android.gallery3d.R;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveStoryLiveEffectCmd extends BaseCommand {
    private String createFileName(String str) {
        return new FileNameBuilder(j.f(new StringBuilder(), StorageInfo.getDefault().stories, "/", str)).setPostFix("_LiveEffect").setExtension(IFormat.FORMAT_MP4).buildUnique();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveLiveEffect$1(MediaItem mediaItem, String str) {
        onComplete(true, mediaItem, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveLiveEffect$2(MediaItem mediaItem) {
        onComplete(false, mediaItem, (String) null);
    }

    private void onComplete(boolean z, MediaItem mediaItem, String str) {
        Log.d(this.TAG, "onoComplete", Boolean.valueOf(z), MediaItemUtil.getSimpleLog(mediaItem), Boolean.valueOf(FileUtils.exists(mediaItem.getPath())));
        if (z) {
            Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.video_saved_in, BucketUtils.translatePath(str, false)));
            return;
        }
        Utils.showToast(AppResources.getAppContext(), (int) R.string.video_save_fail);
    }

    /* access modifiers changed from: private */
    /* renamed from: saveLiveEffect */
    public void lambda$onExecute$0(MediaItem mediaItem) {
        InputStream openInputStream;
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            openInputStream = getContext().getContentResolver().openInputStream(ContentUri.getUri(mediaItem));
            StoryCoverData storyCoverData = (StoryCoverData) MediaItemStory.getStoryOriginCoverData(mediaItem);
            if (storyCoverData != null) {
                str = FileUtils.getBaseName(storyCoverData.path);
            } else {
                str = mediaItem.getDisplayName();
            }
            String createFileName = createFileName(str);
            if (FileUtils.makeParentIfAbsent(createFileName)) {
                FileUtils.writeFile(createFileName, openInputStream);
                MediaScanner.scanFile(createFileName, (MediaScannerListener) null);
                ThreadUtil.postOnUiThread(new c(this, mediaItem, createFileName, 15));
                String str2 = this.TAG;
                Log.d(str2, "saveLiveEffect {" + Logger.getEncodedString(createFileName) + "} " + (System.currentTimeMillis() - currentTimeMillis));
            }
            if (openInputStream != null) {
                openInputStream.close();
                return;
            }
            return;
        } catch (Exception e) {
            ThreadUtil.postOnUiThread(new e(this, mediaItem, 1));
            Log.e((CharSequence) this.TAG, "saveLiveEffect failed. e=", e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        SimpleThreadPool.getInstance().execute(new e(this, objArr[0], 0));
    }
}
