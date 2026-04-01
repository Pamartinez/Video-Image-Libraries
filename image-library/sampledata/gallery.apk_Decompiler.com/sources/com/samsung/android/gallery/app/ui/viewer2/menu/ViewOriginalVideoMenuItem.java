package com.samsung.android.gallery.app.ui.viewer2.menu;

import M8.c;
import O3.b;
import Q7.i;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DbCompat;
import com.samsung.android.gallery.database.dal.abstraction.DbKey;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemLoader;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewOriginalVideoMenuItem extends ViewerMenuItem {
    public ViewOriginalVideoMenuItem(EventContext eventContext, ActionInvoker actionInvoker) {
        super(eventContext, actionInvoker, R.string.view_original_video);
    }

    private String getTempListLocationKey(MediaItem mediaItem) {
        return "location://tempList/" + mediaItem.getSimpleHashCode();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$1(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        Log.d(this.TAG, "onMenuSelectInternal load prev async");
        show(mediaItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$2(MediaItem mediaItem) {
        if (ThumbnailLoader.getInstance().loadPreview(mediaItem, new b(10, this, mediaItem)) != null) {
            Log.d(this.TAG, "onMenuSelectInternal load prev sync");
            show(mediaItem);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onMenuSelectInternal$3(MediaItem mediaItem) {
        MediaItem loadViewOriginalItem = loadViewOriginalItem(mediaItem.getFileId());
        if (loadViewOriginalItem != null) {
            ThreadUtil.postOnUiThread(new i(this, loadViewOriginalItem, 1));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$setMenuAttribute$0(MediaItem mediaItem, String str) {
        return !LocationKey.isSecondDepthGroupPanelView(str);
    }

    private MediaItem loadViewOriginalItem(long j2) {
        Cursor query;
        try {
            query = DbCompat.query(DbKey.ALL_PICTURES, new c(j2, 1));
            if (query != null) {
                if (query.moveToFirst()) {
                    MediaItem load = MediaItemLoader.load(query);
                    query.close();
                    return load;
                }
            }
            if (query == null) {
                return null;
            }
            query.close();
            return null;
        } catch (Exception e) {
            Log.e(this.TAG, e.getMessage());
            return null;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void show(MediaItem mediaItem) {
        new VuLauncher(this.mEventContext.getBlackboard()).disableTimeline().publishData().launch(getTempListLocationKey(mediaItem), 0, mediaItem);
    }

    public boolean onMenuSelectInternal(View view) {
        MediaItem currentItem = this.mEventContext.getCurrentItem();
        if (currentItem == null) {
            Log.d(this.TAG, "ViewOriginal Menu Select failed: null item");
            return false;
        }
        ThreadUtil.postOnBgThread(new i(this, currentItem, 0));
        return true;
    }

    public void setMenuAttribute() {
        setShowAsActionFlag(0).include("location://highlight/fileList", "location://SingleTakenShotviewer/suggestionHighlight").validate(new l(16)).validate(ViewerMenuItem.IS_NOT_BROKEN);
    }
}
