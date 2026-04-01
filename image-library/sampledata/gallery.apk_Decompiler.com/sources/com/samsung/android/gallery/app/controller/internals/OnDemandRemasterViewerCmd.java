package com.samsung.android.gallery.app.controller.internals;

import A.a;
import M9.f;
import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.mp.helper.RevitalizedApi;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemBuilder;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapOptionsFactory;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandRemasterViewerCmd extends BaseCommand {
    private MediaItem mMediaItem;

    private boolean hasRemasteredImage(MediaItem mediaItem) {
        if (!mediaItem.isRevitalization() || !FileUtils.exists(MediaItemSuggest.getRemasterPath(mediaItem))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(Runnable runnable, Blackboard blackboard, long j2, boolean z) {
        if (this.mMediaItem == null) {
            Log.w(this.TAG, "Couldn't launch remaster viewer -> item is null");
            return;
        }
        PreferenceCache.RemasterNewBadge.setBoolean(false);
        if (hasRemasteredImage(this.mMediaItem)) {
            if (runnable != null) {
                runnable.run();
            }
            launchRemasterViewer(blackboard, this.mMediaItem);
        } else if (BlackboardUtils.isRemasterServiceStarted()) {
            Log.d(this.TAG, "Remaster service is running");
            showToast((int) R.string.wait_before_remastering_another_one, 1);
        } else {
            if (runnable != null) {
                runnable.run();
            }
            launchOnDemandRemasterViewer(blackboard, this.mMediaItem, j2, z);
        }
    }

    private void launchOnDemandRemasterViewer(Blackboard blackboard, MediaItem mediaItem, long j2, boolean z) {
        MediaItem mediaItem2 = new MediaItem();
        mediaItem2.cloneBasicInfo(mediaItem);
        mediaItem2.setSefFileType(mediaItem.getSefFileType(), mediaItem.getSefFileSubType());
        Log.d(this.TAG, "RevitalizedType", Long.valueOf(j2), Long.valueOf(MediaItemSuggest.getRevitalizedType(mediaItem)));
        MediaItemSuggest.setRevitalizedType(mediaItem2, j2);
        MediaItemSuggest.setOriginalPath(mediaItem2, mediaItem.getPath());
        mediaItem2.setPath(mediaItem.getPath());
        mediaItem2.setRevitalization();
        MediaItemUtil.setRemasterAutosave(mediaItem2, z);
        updateCommonAttrs(mediaItem2);
        launchViewer(blackboard, mediaItem2);
        Log.d(this.TAG, "launch on-demand remasterViewer");
    }

    private void launchRemasterViewer(Blackboard blackboard, MediaItem mediaItem) {
        Cursor revitalizedCursor;
        MediaItem mediaItem2 = null;
        try {
            revitalizedCursor = new RevitalizedApi().getRevitalizedCursor(mediaItem.getFileId());
            if (revitalizedCursor != null) {
                if (revitalizedCursor.moveToNext()) {
                    mediaItem2 = MediaItemBuilder.create(revitalizedCursor);
                }
            }
            String str = this.TAG;
            Log.d(str, "success=" + MediaItemUtil.getSimpleLog(mediaItem2));
            if (revitalizedCursor != null) {
                revitalizedCursor.close();
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("query fail="), this.TAG);
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        if (mediaItem2 == null) {
            Log.e(this.TAG, "fail launch revitalizeViewer");
            return;
        }
        updateCommonAttrs(mediaItem2);
        launchViewer(blackboard, mediaItem2);
        Log.d(this.TAG, "launch remasterViewer");
        return;
        throw th;
    }

    private void launchViewer(Blackboard blackboard, MediaItem mediaItem) {
        new VuLauncher(blackboard).disableTimeline().publishData().addTrueArgument("on-demand_remaster").launch("location://revitalized/single", 0, mediaItem);
    }

    private void updateCommonAttrs(MediaItem mediaItem) {
        String path = mediaItem.getPath();
        if (!TextUtils.isEmpty(path)) {
            BitmapOptions parse = BitmapOptionsFactory.parse(path);
            mediaItem.setSize(parse.outWidth, parse.outHeight);
            MediaItemUtil.setRemastering(mediaItem, true);
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (isLowStorage()) {
            Log.w(this.TAG, "Couldn't execute remaster viewer by low storage");
            return;
        }
        this.mMediaItem = objArr[0];
        long longValue = objArr[1].longValue();
        boolean booleanValue = objArr[3].booleanValue();
        SimpleThreadPool.getInstance().execute(new f(this, objArr[2], getBlackboard(), longValue, booleanValue));
    }
}
