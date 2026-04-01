package com.samsung.android.gallery.bixby.bixby.handler;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.bixby.bixby.abstraction.GalleryActionHandler;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.bixby2.Sbixby;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ReminderActionHandler extends GalleryActionHandler {
    private JSONObject getInformation(MediaItem mediaItem) {
        JSONObject jSONObject = new JSONObject();
        try {
            Log.bx(this.TAG, "get information start");
            String path = mediaItem.getPath();
            Uri uri = ContentUri.getUri(mediaItem);
            jSONObject.put("KEY_STATUS", "success");
            String str = "";
            if (path == null) {
                path = str;
            }
            jSONObject.put("KEY_PATH", path);
            if (uri != null) {
                str = uri.toString();
            }
            jSONObject.put("KEY_URI", str);
            jSONObject.put("KEY_MEDIA_TYPE", mediaItem.getMediaType().toInt());
            jSONObject.put("KEY_MIME_TYPE", mediaItem.getMimeType());
            jSONObject.put("KEY_LATITUDE", mediaItem.getLatitude());
            jSONObject.put("KEY_LONGITUDE", mediaItem.getLongitude());
            jSONObject.put("KEY_TIME", mediaItem.getDateTaken());
            jSONObject.put("KEY_ORIENTATION", mediaItem.getOrientation());
            jSONObject.put("KEY_IS_360_VIDEO", mediaItem.is360Video());
            jSONObject.put("KEY_IS_DRM", mediaItem.isDrm());
            jSONObject.put("KEY_IS_BROKEN", mediaItem.isBroken());
            jSONObject.put("KEY_CLOUD_SERVER_ID", mediaItem.getCloudServerId());
            jSONObject.put("KEY_CLOUD_SERVER_PATH", mediaItem.getCloudServerPath());
            jSONObject.put("KEY_CLOUD_THUMB_PATH", mediaItem.getCloudThumbPath());
            jSONObject.put("KEY_DURATION", mediaItem.getFileDuration());
            jSONObject.put("KEY_GROUP_MEDIA_ID", mediaItem.getGroupMediaId());
            jSONObject.put("KEY_GROUP_TYPE", mediaItem.getGroupType());
            jSONObject.put("KEY_HEIGHT", mediaItem.getHeight());
            jSONObject.put("KEY_RECORDING_MODE", mediaItem.getRecordingMode());
            jSONObject.put("KEY_RECORDING_TYPE", mediaItem.getRecordingType());
            jSONObject.put("KEY_SEF_FILE_SUB_TYPE", mediaItem.getSefFileSubType());
            jSONObject.put("KEY_SEF_FILE_TYPE", mediaItem.getSefFileType());
            jSONObject.put("KEY_SEF_FILE_TYPES", mediaItem.getSefFileTypes());
            jSONObject.put("KEY_SIZE", mediaItem.getFileSize());
            jSONObject.put("KEY_STORAGE_TYPE", mediaItem.getStorageType().toInt());
            jSONObject.put("KEY_WIDTH", mediaItem.getWidth());
            String str2 = this.TAG;
            Log.bx(str2, "get information end [" + Logger.getEncodedString(jSONObject.toString()) + "]");
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public boolean isSupported() {
        return "DETAIL_VIEW_REMINDER".equals(this.mAction);
    }

    public void onExecute(Context context, Blackboard blackboard) {
        String str = this.TAG;
        Log.bx(str, "found blackboard to start ACTION_VIEWER_REMINDER [" + blackboard.getName() + "]");
        if (!isActivityResumed(BlackboardUtils.readActivity(blackboard))) {
            Log.bxe(this.TAG, "activity is not resumed. so skip.");
            return;
        }
        MediaItem mediaItem = (MediaItem) blackboard.pop("data://bixby_mediaItem");
        if (isExecutableInViewer(mediaItem, (String) blackboard.read("location://variable/currentv1"), Sbixby.getStateHandler().getAppState(context, (Bundle) null), blackboard.getName())) {
            sendCallback(getResultString(getInformation(mediaItem)));
        }
    }
}
