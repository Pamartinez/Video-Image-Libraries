package com.samsung.android.gallery.app.controller.internals;

import A4.Q;
import J6.c;
import M9.o;
import O3.q;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import com.samsung.android.gallery.app.controller.BaseSelectedCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.database.dal.DebugLogger;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaScanner;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.SelectableChecker;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoDeleteVideoCmd extends BaseSelectedCommand implements SelectableChecker<MediaItem> {
    private boolean mFromViewer;
    private MediaItem[] mSelectedItems;

    private String getConfirmDialogTitle() {
        if (this.mFromViewer) {
            return getContext().getString(R.string.deleting_video_clip_confirm_dialog);
        }
        Resources resources = getContext().getResources();
        MediaItem[] mediaItemArr = this.mSelectedItems;
        return resources.getQuantityString(R.plurals.delete_selected_motion_clip_video_item_plural, mediaItemArr.length, new Object[]{Integer.valueOf(mediaItemArr.length)});
    }

    private boolean isNeedEnterSelectionMode() {
        if (this.mFromViewer || getHandler().isSelectionMode()) {
            return false;
        }
        MediaItem[] mediaItemArr = this.mSelectedItems;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$1(MediaItem mediaItem, String str) {
        Log.d(this.TAG, "scan done");
        reuseThumbnailMemoryCache(mediaItem, str);
        Utils.showToast(getContext(), getContext().getString(R.string.deleting_video_from_motion_photo));
        getBlackboard().post("command://event/DataDirty", (Object) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$2(String str, MediaItem mediaItem) {
        if (MotionPhotoUtils.deleteVideo(str)) {
            String str2 = this.TAG;
            Log.d(str2, "success : " + mediaItem);
            MediaScanner.scanFile(str, new Q((Object) this, mediaItem, (Object) str, 9));
            DebugLogger.getDeleteInstance().lambda$apply$0("DeleteMotionPhotoClip(S)", Logger.v(str));
            return;
        }
        Log.e((CharSequence) this.TAG, C0212a.l("failed : ", str), mediaItem);
        Utils.showToast(getContext(), (int) R.string.image_save_fail);
        DebugLogger deleteInstance = DebugLogger.getDeleteInstance();
        deleteInstance.lambda$apply$0("DeleteMotionPhotoClip(S)", "failed" + Logger.v(str));
    }

    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        MediaItem mediaItem;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            Log.i(this.TAG, "onConfirmed : cancelled");
        } else if (this.mFromViewer) {
            MediaItem[] mediaItemArr = this.mSelectedItems;
            if (mediaItemArr.length > 0) {
                mediaItem = mediaItemArr[0];
            } else {
                mediaItem = null;
            }
            if (mediaItem != null) {
                SimpleThreadPool.getInstance().execute(new c(this, mediaItem.getPath(), mediaItem, 19));
            } else {
                Log.e(this.TAG, "failed. null");
            }
            getBlackboard().post("command://DismissDialog", (Object) null);
        } else {
            getBlackboard().publish("data://user/selected", this.mSelectedItems);
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MotionPhotoClipService");
            intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
            intent.putExtra("blackboard_name", getBlackboard().getName());
            intent.putExtra("motion_photo_operation_type", 0);
            getContext().startService(intent);
            getBlackboard().postEvent(EventMessage.obtain(1003));
        }
    }

    /* access modifiers changed from: private */
    public void onSelected(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        if (arrayList != null) {
            i2 = arrayList.size();
        } else {
            i2 = 0;
        }
        if (i2 == 0) {
            Log.i(this.TAG, "onSelected : cancelled");
            return;
        }
        this.mSelectedItems = (MediaItem[]) arrayList.get(0);
        ThreadUtil.postOnUiThread(new o(13, this));
    }

    private void reuseThumbnailMemoryCache(MediaItem mediaItem, String str) {
        String thumbCacheKey = mediaItem.getThumbCacheKey();
        mediaItem.setFileSize(FileUtils.length(str));
        ThumbnailLoader.getInstance().changeMemoryCacheKey(thumbCacheKey, mediaItem.getThumbCacheKey(), mediaItem.getCropRectRatio());
    }

    /* access modifiers changed from: private */
    /* renamed from: startConfirmDialog */
    public void lambda$onSelected$0() {
        Log.d(this.TAG, "show confirm dialog");
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("type", 0).appendArg("title", getConfirmDialogTitle()).appendArg("description", "").appendArg("option1", getContext().getString(R.string.delete)).appendArg("screenId", getScreenId()).appendArg("option1EventId", AnalyticsEventId.EVENT_DETAIL_VIEW_DELETE_MOTION_PHOTO_DELETE_VIDEO_CLIP_OK.toString()).appendArg("cancelEventId", AnalyticsEventId.EVENT_DETAIL_VIEW_DELETE_MOTION_PHOTO_DELETE_VIDEO_CLIP_CANCEL.toString()).build()).setOnDataCompleteListener(new q(this, 1)).start();
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MOTION_PHOTO_DELETE_VIDEO_CLIP.toString();
    }

    public int getMaxCount() {
        return getHandler().getSelectedItemCount() - 1;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem[] mediaItemArr;
        if (objArr == null || objArr.length <= 0) {
            mediaItemArr = eventContext.getSelectedItems();
        } else {
            mediaItemArr = objArr[0];
        }
        this.mSelectedItems = mediaItemArr;
        this.mFromViewer = LocationKey.isContentViewer(eventContext.getLocationKey());
        if (isNeedEnterSelectionMode()) {
            getBlackboard().publish("data://user/pick/ItemChecker", this);
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/selection/DeleteMotionPhotoVideoClip").appendArg("title", getContext().getResources().getString(R.string.delete_video_clips_option)).build()).setOnDataCompleteListener(new q(this, 0)).start();
            return;
        }
        lambda$onSelected$0();
    }

    public void showExceedMaxCountToast(Context context) {
        String str;
        int maxCount = getMaxCount();
        if (maxCount > 1) {
            str = context.getString(R.string.max_size_reached, new Object[]{Integer.valueOf(maxCount)});
        } else {
            str = context.getString(R.string.max_size_reached_for_one);
        }
        Utils.showToast(context, str);
    }

    public boolean isSupported(MediaItem mediaItem) {
        boolean isCloudOnly = mediaItem.isCloudOnly();
        boolean z = !isCloudOnly;
        if (isCloudOnly) {
            showToast((int) R.string.file_not_supported);
        }
        return z;
    }
}
