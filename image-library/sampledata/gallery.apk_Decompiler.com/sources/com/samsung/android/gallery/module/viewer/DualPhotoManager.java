package com.samsung.android.gallery.module.viewer;

import B8.g;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dal.mp.helper.FilesApi;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.media.DualPhotoManipulator2;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.threadpool.ThreadPool;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import java.util.function.BiConsumer;
import va.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DualPhotoManager {
    private final Blackboard mBlackboard;
    private volatile boolean mIsRunning;
    private volatile boolean mIsSavedWidePhoto;
    private volatile boolean mIsWide;
    private final MediaItem mMediaItem;

    public DualPhotoManager(Blackboard blackboard, MediaItem mediaItem) {
        this.mBlackboard = blackboard;
        this.mMediaItem = mediaItem;
        if (mediaItem.isSharing()) {
            boolean isDualPhotoWide = MediaItemMde.isDualPhotoWide(mediaItem);
            this.mIsSavedWidePhoto = isDualPhotoWide;
            this.mIsWide = isDualPhotoWide;
            return;
        }
        boolean isWideImage = isWideImage(mediaItem.getPath());
        this.mIsSavedWidePhoto = isWideImage;
        this.mIsWide = isWideImage;
    }

    private boolean changeCover(boolean z, BiConsumer<Boolean, Boolean> biConsumer) {
        String str;
        boolean z3 = z;
        BiConsumer<Boolean, Boolean> biConsumer2 = biConsumer;
        try {
            String path = this.mMediaItem.getPath();
            long currentTimeMillis = System.currentTimeMillis();
            long dateModified = FileUtils.getDateModified(path);
            long length = FileUtils.length(path);
            this.mIsRunning = true;
            long changeCover = DualPhotoManipulator2.changeCover(z3, path, this.mMediaItem.getOrientation(), this.mMediaItem.getMediaId());
            if (changeCover > 0) {
                FileUtils.setDateModified(path, dateModified + 1000);
                boolean isWideImage = isWideImage(path);
                this.mIsSavedWidePhoto = isWideImage;
                this.mIsWide = isWideImage;
                StringBuilder sb2 = new StringBuilder("changeCover");
                if (z3) {
                    str = "wide";
                } else {
                    str = "closeup";
                }
                sb2.append(Logger.vt(str, Boolean.valueOf(this.mIsWide), Long.valueOf(length), Long.valueOf(changeCover), Long.valueOf(currentTimeMillis)));
                Log.d("DualPhotoManager", sb2.toString());
                updateDatabase(this.mMediaItem, changeCover);
                ThreadUtil.postOnUiThreadDelayed(new g((Object) this, (Object) biConsumer2, z3, 18), 500);
                return true;
            }
        } catch (Exception e) {
            Log.e((CharSequence) "DualPhotoManager", "changeCover failed", (Throwable) e);
        }
        this.mIsRunning = false;
        biConsumer2.accept(Boolean.FALSE, Boolean.valueOf(z3));
        Utils.showToast((Context) BlackboardUtils.readActivity(this.mBlackboard), R$string.error);
        return false;
    }

    private boolean isWideImage(String str) {
        return DualPhotoManipulator2.isWideImage(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$changeCover$1(BiConsumer biConsumer, boolean z) {
        BlackboardUtils.removeDataChangeObservingDelay(this.mBlackboard);
        this.mBlackboard.post("command://event/DataDirty", (Object) null);
        this.mIsRunning = false;
        biConsumer.accept(Boolean.TRUE, Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$changeDualPhoto$0(boolean z, BiConsumer biConsumer, ThreadPool.JobContext jobContext) {
        return Boolean.valueOf(changeCover(z, biConsumer));
    }

    private void updateDatabase(MediaItem mediaItem, long j2) {
        String str = null;
        if (SdkConfig.atLeast(SdkConfig.GED.R)) {
            new FilesApi().reserveDateTime(mediaItem.getFileId(), mediaItem.getDateTaken());
            MediaScanner.scanFile(mediaItem.getPath(), (MediaScannerListener) null);
            return;
        }
        Uri writableUri = ContentUri.getWritableUri(mediaItem);
        if (writableUri != null) {
            str = writableUri.toString();
        }
        String str2 = str;
        if (!TextUtils.isEmpty(str2)) {
            BlackboardUtils.forceRefreshPicturesData(this.mBlackboard, false);
            new FilesApi().updateMedia(str2, j2, mediaItem.getDateTaken(), mediaItem.getOrientation(), mediaItem.getDateModified() + 1);
        }
    }

    public boolean changeDualPhoto(boolean z, BiConsumer<Boolean, Boolean> biConsumer) {
        String str;
        if (z) {
            str = "wide";
        } else {
            str = "closeup";
        }
        Log.d("DualPhotoManager", "changeDualPhoto", str, Boolean.valueOf(this.mIsSavedWidePhoto));
        if (isTheSameWithCurrentMode(z)) {
            return false;
        }
        ThreadPool.getInstance().submit(new a(this, biConsumer, z));
        return true;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public boolean isTheSameWithCurrentMode(boolean z) {
        if (this.mIsSavedWidePhoto == z) {
            return true;
        }
        return false;
    }

    public boolean isWide() {
        return this.mIsWide;
    }
}
