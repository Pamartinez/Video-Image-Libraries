package com.samsung.android.gallery.app.controller.story;

import Ob.a;
import R6.c;
import android.content.Context;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.MetadataTimeHelper;
import com.samsung.android.gallery.module.story.StoryHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.MediaScannerListener;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.sgpl.media.MediaMetadataInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveRecapVideoCmd extends BaseCommand {
    private MediaItem mItem;
    private boolean mShowToast;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(EventContext eventContext) {
        saveRecap(eventContext.getContext(), this.mItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveRecap$1(MediaItem mediaItem, String str) {
        onComplete(true, mediaItem, str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$saveRecap$2(MediaItem mediaItem) {
        onComplete(false, mediaItem, (String) null);
    }

    private void onComplete(boolean z, MediaItem mediaItem, String str) {
        Log.d(this.TAG, "onoComplete", Boolean.valueOf(z), MediaItemUtil.getSimpleLog(mediaItem), Boolean.valueOf(FileUtils.exists(mediaItem.getPath())));
        if (this.mShowToast) {
            showToast(z, mediaItem, str);
        }
    }

    private void saveRecap(Context context, MediaItem mediaItem) {
        InputStream openInputStream;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            openInputStream = context.getContentResolver().openInputStream(ContentUri.getUri(mediaItem));
            String createSaveFilePath = StoryHelper.createSaveFilePath(StorageInfo.getDefault().stories);
            if (FileUtils.makeParentIfAbsent(createSaveFilePath)) {
                FileUtils.writeFile(createSaveFilePath, openInputStream);
                updateDateTime(mediaItem, currentTimeMillis, createSaveFilePath);
                MediaScanner.scanFile(createSaveFilePath, (MediaScannerListener) null);
                ThreadUtil.postOnUiThread(new c(this, mediaItem, createSaveFilePath, 14));
                String str = this.TAG;
                Log.d(str, "save recap {" + Logger.getEncodedString(createSaveFilePath) + "} " + (System.currentTimeMillis() - currentTimeMillis));
            }
            if (openInputStream != null) {
                openInputStream.close();
                return;
            }
            return;
        } catch (Exception e) {
            ThreadUtil.postOnUiThread(new a(26, this, mediaItem));
            Log.e((CharSequence) this.TAG, "save recap failed. e=", e.getMessage());
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void showToast(boolean z, MediaItem mediaItem, String str) {
        if (z) {
            Utils.showToast(AppResources.getAppContext(), AppResources.getString(R.string.video_saved_in, BucketUtils.translatePath(str, false)));
            return;
        }
        Utils.showToast(AppResources.getAppContext(), (int) R.string.video_save_fail);
    }

    private boolean updateDateTime(MediaItem mediaItem, long j2, String str) {
        if (mediaItem == null || str == null || mediaItem.isCloudOnly()) {
            return false;
        }
        if (!mediaItem.isHeif() && !mediaItem.isVideo()) {
            return false;
        }
        try {
            String replace = TimeUtil.getSystemTimeZoneOffsetTag().replace(NumericEnum.SEP, "");
            SeApiCompat.getSefFileCompat().addData(new File(str), SefInfo.VIDEO_EDITED_UTC_OFFSET, replace.getBytes());
            String localTime = MetadataTimeHelper.getLocalTime(mediaItem, j2, replace);
            MediaMetadataInterface mediaMetadataInterface = new MediaMetadataInterface(str);
            mediaMetadataInterface.setAttribute(MediaMetadataInterface.Attribute.ATTR_CREATION_TIME, localTime);
            return mediaMetadataInterface.saveAttributes();
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
            return false;
        }
    }

    public String getAnalyticsDetail() {
        return String.valueOf(MediaItemStory.getStorySaType(this.mItem));
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_STORY_HIGHLIGHT_SAVE.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mItem = objArr[0];
        Context context = eventContext.getContext();
        boolean z = true;
        if (objArr.length >= 2) {
            z = objArr[1].booleanValue();
        }
        this.mShowToast = z;
        if (context != null) {
            SimpleThreadPool.getInstance().execute(new a(25, this, eventContext));
        }
    }
}
