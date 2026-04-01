package com.samsung.android.gallery.app.controller.externals;

import android.content.Intent;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlaySlowMotionVideoCmd extends AbstractPlayCmd {
    public Intent createIntent(MediaItem mediaItem) {
        Intent intent;
        if (!SdkConfig.atLeast(SdkConfig.GED.P) || !SeApiCompat.isScreenLocked(getActivity())) {
            intent = new Intent("android.intent.action.VIEW");
        } else {
            intent = new Intent("com.samsung.intent.action.SECURE_LOCK");
            intent.addFlags(8388608);
            intent.putExtra("createBySecureLock", true);
        }
        intent.setClassName("com.samsung.app.slowmotion", "com.samsung.app.slowmotion.slowmotionactivity.SlowMotionActivity");
        intent.putExtra(OCRServiceConstant.KEY_PARAM_URI, ContentUri.getUri(mediaItem));
        intent.putExtra("key_recorded_mode", getMotionType());
        setIntentWithCommonExtra(intent);
        addVideoTransitionInfo(intent, (MediaItem) null);
        return intent;
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_SLOW_MOTION.toString();
    }

    public int getMotionType() {
        return 0;
    }

    public void handleFail() {
        if (!isPackageInstalled("com.samsung.app.slowmotion")) {
            guideDownloadPackage("com.samsung.app.slowmotion", getContext().getString(R.string.slow_motion));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Runnable runnable;
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && (runnable = BlackboardUtils.getAppTransitionCallback(getBlackboard())[1]) != null) {
            runnable.run();
        }
        super.onExecute(eventContext, objArr);
    }

    public void startActivity(Intent intent) {
        if (!startActivityWithTransition(intent, 793, "gallery_to_ve")) {
            super.startActivity(intent);
        }
    }
}
