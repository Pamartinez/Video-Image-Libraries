package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PackageMonitorCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringResources;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartVideoEditorCmd extends BaseCommand {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VersionHolder {
        static final long sVersion = PackageMonitorCompat.getInstance().getPackageVersion("com.sec.android.app.vepreload");

        public static boolean support(long j2) {
            if (sVersion >= j2) {
                return true;
            }
            return false;
        }
    }

    private Intent createIntent(MediaItem mediaItem) {
        boolean z;
        String str;
        String str2;
        Uri uri = ContentUri.getUri(mediaItem);
        MediaHelper.VideoInfo videoInfo = MetadataManager.getInstance().get(mediaItem);
        if (!mediaItem.isHlgVideo() || videoInfo == null || videoInfo.getVideoBitDepth() != 10) {
            z = false;
        } else {
            z = true;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(uri);
        Intent intent = new Intent();
        intent.setType("*/*");
        if (VersionHolder.support(519000000)) {
            str = "android.intent.action.MAIN";
        } else {
            str = "android.intent.action.EDIT";
        }
        intent.setAction(str);
        intent.setClassName("com.sec.android.app.vepreload", "com.sec.android.app.vepreload.activity.SimpleVideoEditActivity");
        intent.putExtra("HLG10", z);
        if (videoInfo != null) {
            intent.putExtra("video_color_transfer", videoInfo.getExtraValue(36));
            intent.putExtra("video_bit_depth", videoInfo.getExtraValue(1028));
            if (RecordingMode.isHdr10Plus(mediaItem.getRecordingMode())) {
                str2 = "hdr10+";
            } else if (mediaItem.isHdr10Video()) {
                str2 = "hdr10";
            } else if (mediaItem.isHlgVideo()) {
                str2 = "hlg";
            } else {
                str2 = "normal";
            }
            intent.putExtra("video_type", str2);
        }
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
        boolean handleSharedItemEdit = handleSharedItemEdit(mediaItem, intent);
        setIntentWithCommonExtra(intent);
        if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO) {
            InstantSlowMoUtils.setIntentInstantSlowMoExtra(intent, mediaItem);
        }
        addVideoTransitionInfo(intent, (MediaItem) null);
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            intent.addFlags(65536);
            intent.addFlags(32768);
        }
        if (isConfigured(1)) {
            intent.putExtra("direct_launch", "audio_eraser");
        }
        if (PreferenceFeatures.OneUi8x.IS_ONE_UI_85 && isConfigured(2)) {
            intent.putExtra("direct_launch", "lut_mode");
        }
        if (SdkConfig.atLeast(SdkConfig.GED.S)) {
            intent.addFlags(3);
            if (!handleSharedItemEdit) {
                getContext().grantUriPermission("com.sec.android.app.vepreload", uri, 3);
            }
            return intent;
        }
        intent.addFlags(1);
        return intent;
    }

    private boolean startVideoEditor(MediaItem mediaItem, boolean z, Runnable runnable) {
        if (!Features.isEnabled(Features.SUPPORT_DOWNLOAD_VIDEO_EDITOR) || z) {
            new StartVideoTrimmerCmd().execute(getHandler(), mediaItem, runnable);
        } else {
            try {
                Intent createIntent = createIntent(mediaItem);
                if (!SystemUi.isInMultiWindowMode(getActivity()) && startActivityWithTransition(createIntent, 792, "gallery_to_ve")) {
                    return true;
                }
                getContext().startActivity(createIntent);
            } catch (ActivityNotFoundException unused) {
                guideDownloadPackage("com.sec.android.app.vepreload", StringResources.getVideoEditorAppName());
                return false;
            } catch (Exception e) {
                String str = this.TAG;
                Log.e(str, "startVideoEditor failed. e=" + Logger.toSimpleString((Throwable) e));
                showToast((int) R.string.unsupported_file);
                if (e instanceof SecurityException) {
                    new InternalException("startVideoEditor failed with security exception").post();
                }
                return false;
            }
        }
        return true;
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Runnable runnable;
        if (isUpsm()) {
            showToast(SeApiCompat.naturalizeText(getContext().getString(R.string.unable_in_max_power_saving, new Object[]{StringResources.getVideoEditorAppName()})));
        } else if (guidePackageEnabling("com.sec.android.app.vepreload")) {
            Log.w(this.TAG, "goto settings due to package disabled");
        } else {
            if (SystemUi.isInMultiWindowMode(getActivity()) || !Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
                runnable = null;
            } else {
                Runnable[] appTransitionCallback = BlackboardUtils.getAppTransitionCallback(getBlackboard());
                Runnable runnable2 = appTransitionCallback[1];
                if (runnable2 != null) {
                    runnable2.run();
                }
                runnable = appTransitionCallback[2];
            }
            if (!startVideoEditor(objArr[0], objArr[1].booleanValue(), runnable) && runnable != null) {
                runnable.run();
            }
        }
    }
}
