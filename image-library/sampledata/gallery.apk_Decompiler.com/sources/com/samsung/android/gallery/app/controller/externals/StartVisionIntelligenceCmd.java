package com.samsung.android.gallery.app.controller.externals;

import A.a;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartVisionIntelligenceCmd extends BaseCommand {
    private boolean mIsExtractText = false;

    private Intent createIntent(MediaItem mediaItem) {
        Uri uri;
        Intent intent = new Intent("samsung.intentfilter.visionintelligence.image");
        String str = "";
        if (!PocFeatures.DUAL_PHOTO_PREVIEW || !mediaItem.isStream()) {
            if (mediaItem.isSharing()) {
                uri = null;
            } else {
                uri = ContentUri.getUri(mediaItem);
            }
            intent.setDataAndType(uri, mediaItem.getMimeType());
            if (uri != null) {
                str = uri.toString();
            }
            intent.putExtra("IMAGE_URI", str);
            if (!mediaItem.isCloudOnly()) {
                intent.putExtra("IMAGE_FILE_PATH", mediaItem.getPath());
            }
        } else {
            uri = ContentUri.getStreamUri(getContext(), mediaItem);
            intent.setDataAndType(uri, mediaItem.getMimeType());
            if (uri != null) {
                str = uri.toString();
            }
            intent.putExtra("IMAGE_URI", str);
        }
        intent.putExtra("LAUNCH_MODE", 1);
        intent.putExtra("CMH_SCAN", -1);
        intent.putExtra("MIME_TYPE", getMimeType(mediaItem));
        if (mediaItem.isOCRScanned()) {
            intent.putExtra("START_MODE", 4);
            this.mIsExtractText = true;
        }
        setIntentWithCommonExtra(intent);
        intent.addFlags(65537);
        try {
            getContext().grantUriPermission("com.samsung.android.visionintelligence", uri, 1);
        } catch (Exception e) {
            a.s(e, new StringBuilder("createIntent#grantUriPermission failed. e="), this.TAG);
        }
        String str2 = this.TAG;
        Log.d(str2, "intent{" + mediaItem.getStorageType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + uri + GlobalPostProcInternalPPInterface.SPLIT_REGEX + getMimeType(mediaItem) + "}");
        return intent;
    }

    private String getMimeType(MediaItem mediaItem) {
        if (!mediaItem.isSharing()) {
            return mediaItem.getMimeType();
        }
        if (mediaItem.isJpeg()) {
            return "image/jpeg";
        }
        return "image/png";
    }

    private void startVisionService(Context context, MediaItem mediaItem) {
        try {
            context.startActivity(createIntent(mediaItem));
        } catch (Exception e) {
            a.s(e, new StringBuilder("startVisionService failed e="), this.TAG);
        }
    }

    public String getEventId() {
        if (!PreferenceFeatures.OneUi41.SUPPORT_CAMERA_AI || !this.mIsExtractText) {
            return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_VISION.toString();
        }
        return AnalyticsEventId.EVENT_DETAIL_VIEW_EXTRACT_TEXT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Activity activity = getActivity();
        if (isUpsm()) {
            Toast.makeText(activity, R.string.cannot_use_this_function_while_mpsm_is_on, 0).show();
        } else {
            startVisionService(activity, objArr[0]);
        }
    }
}
