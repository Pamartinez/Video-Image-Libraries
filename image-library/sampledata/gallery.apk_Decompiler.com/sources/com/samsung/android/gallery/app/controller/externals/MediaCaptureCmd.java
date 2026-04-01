package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.ConvertingUsageType;
import com.samsung.android.gallery.module.abstraction.MediaCaptureMode;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import com.sec.android.gallery3d.R;
import i.C0212a;
import java.io.File;
import java.io.IOException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaCaptureCmd extends BaseCommand {
    MediaCaptureMode mCaptureMode;
    MediaItem mMediaItem;
    ConvertingUsageType mUsageType;

    private String getFilePath(MediaItem mediaItem, String str) {
        StringBuilder s = C0212a.s(str);
        s.append(File.separator);
        s.append(FileUtils.getBaseName(mediaItem.getPath()));
        s.append("_");
        s.append(mediaItem.getTitle());
        s.append(O3DPConstant.MP4_EXTENSION);
        return s.toString();
    }

    private File getMediaCaptureSharePath(MediaItem mediaItem) {
        File externalFilesDir = getContext().getExternalFilesDir(".share");
        if (externalFilesDir != null && FileUtils.makeDirectoryIfAbsent(externalFilesDir)) {
            return new File(getFilePath(mediaItem, externalFilesDir.getAbsolutePath()));
        }
        throw new IOException();
    }

    private boolean isAllowingShareDirectly(File file) {
        if (!file.exists() || file.getAbsolutePath().equals(Blackboard.getApplicationInstance().read("data://running_media_capture", null))) {
            return false;
        }
        return true;
    }

    private void launchMediaCaptureService(EventContext eventContext, MediaItem[] mediaItemArr, int i2, int i7) {
        notifyMediaCapturePrepare();
        getBlackboard().publish("data://user/selected", mediaItemArr);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MediaCaptureService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", eventContext.getLocationKey());
        intent.putExtra("usage_type", i2);
        intent.putExtra("media_capture_mode", i7);
        getContext().startService(intent);
    }

    private void notifyMediaCapturePrepare() {
        getBlackboard().postEvent(EventMessage.obtain(1116));
    }

    public String getAnalyticsDetail() {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            dynamicViewPlayInfo = null;
        } else {
            dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
        }
        if (dynamicViewPlayInfo != null) {
            return dynamicViewPlayInfo.getAnalyticsDetail().toString();
        }
        return null;
    }

    public String getEventId() {
        boolean z;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null || !mediaItem.isLogVideo()) {
            z = false;
        } else {
            z = true;
        }
        if (ConvertingUsageType.COMMON_SHARE.equals(this.mUsageType)) {
            if (z) {
                return AnalyticsEventId.EVENT_SUGGEST_SHARE.toString();
            }
            return AnalyticsEventId.EVENT_DETAIL_VIEW_SHARE_DYNAMIC_VIEW.toString();
        } else if (z) {
            return AnalyticsEventId.EVENT_VIEWER_SAVE_AS_COPY_CLICK.toString();
        } else {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_DYNAMIC_VIEW.toString();
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!isLowStorage()) {
            MediaItem mediaItem = objArr[0];
            this.mMediaItem = mediaItem;
            if (mediaItem == null) {
                Log.e(this.TAG, "Unable to operate. item is null");
                return;
            }
            this.mUsageType = objArr[1];
            this.mCaptureMode = objArr[2];
            try {
                boolean isEmpty = Blackboard.getApplicationInstance().isEmpty("data://running_media_capture");
                String str = this.TAG;
                StringBuilder sb2 = new StringBuilder("isServiceRunning : ");
                sb2.append(!isEmpty);
                Log.d(str, sb2.toString());
                if (!isEmpty) {
                    showToast((int) R.string.video_conversion_is_already_running);
                    return;
                }
                if (ConvertingUsageType.COMMON_SHARE.equals(this.mUsageType)) {
                    File mediaCaptureSharePath = getMediaCaptureSharePath(this.mMediaItem);
                    if (isAllowingShareDirectly(mediaCaptureSharePath)) {
                        new ShareViaCmd().execute(eventContext, new MediaItem[]{UriItemLoader.createUriItem(getContext(), mediaCaptureSharePath)}, null);
                        return;
                    }
                }
                launchMediaCaptureService(eventContext, new MediaItem[]{this.mMediaItem}, this.mUsageType.ordinal(), this.mCaptureMode.ordinal());
            } catch (Exception e) {
                a.s(e, new StringBuilder("fail to startService="), this.TAG);
            }
        }
    }
}
