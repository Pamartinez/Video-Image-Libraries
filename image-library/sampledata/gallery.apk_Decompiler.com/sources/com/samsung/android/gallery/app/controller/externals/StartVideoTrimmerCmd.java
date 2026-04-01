package com.samsung.android.gallery.app.controller.externals;

import N2.j;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartVideoTrimmerCmd extends BaseCommand {
    private Intent createIntent(MediaItem mediaItem) {
        Uri uri = ContentUri.getUri(mediaItem);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setClassName("com.samsung.app.newtrim", "com.samsung.app.newtrim.trimactivity.TrimActivity");
        intent.setDataAndType(uri, "video/*");
        intent.putExtra(OCRServiceConstant.KEY_PARAM_URI, uri);
        intent.putExtra("CALLER_APP", "easyshare");
        boolean handleSharedItemEdit = handleSharedItemEdit(mediaItem, intent);
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            intent.addFlags(3);
            if (!handleSharedItemEdit) {
                getContext().grantUriPermission("com.samsung.app.newtrim", uri, 3);
            }
        }
        addVideoTransitionInfo(intent, (MediaItem) null);
        return intent;
    }

    private boolean startVideoTrimmer(MediaItem mediaItem) {
        try {
            Intent createIntent = createIntent(mediaItem);
            if (!SystemUi.isInMultiWindowMode(getActivity()) && startActivityWithTransition(createIntent, 794, "gallery_to_ve")) {
                return true;
            }
            getActivity().startActivity(createIntent);
            return true;
        } catch (ActivityNotFoundException e) {
            if (Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_TRIMMER)) {
                guideDownloadPackage("com.samsung.app.newtrim", StringResources.getVideoTrimmerName());
                return false;
            }
            j.p(e, new StringBuilder("startVideoEdit failed e="), this.TAG);
            return false;
        }
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Runnable runnable;
        if (isUpsm()) {
            showToast(SeApiCompat.naturalizeText(getContext().getString(R.string.unable_in_max_power_saving, new Object[]{StringResources.getVideoTrimmerName()})));
            return;
        }
        MediaItem mediaItem = objArr[0];
        if (ContentUri.getUri(mediaItem) == null) {
            Log.d(this.TAG, "content uri is null");
        } else if (!startVideoTrimmer(mediaItem) && objArr.length > 1 && (runnable = objArr[1]) != null) {
            runnable.run();
        }
    }
}
