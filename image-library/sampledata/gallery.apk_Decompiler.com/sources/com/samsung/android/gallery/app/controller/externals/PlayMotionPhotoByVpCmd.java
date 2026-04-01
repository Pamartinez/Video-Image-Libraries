package com.samsung.android.gallery.app.controller.externals;

import Ga.d;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.abstraction.FileProviderUtil;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordBundleWrapper;
import com.samsung.android.sdk.ocr.service.OCRServiceConstant;
import com.samsung.o3dp.lib3dphotography.common.O3DPConstant;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayMotionPhotoByVpCmd extends AbstractPlayCmd {
    public static final boolean ENABLED;

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.GED.S) || Features.isEnabled(Features.IS_SEP_LITE)) {
            z = false;
        } else {
            z = true;
        }
        ENABLED = z;
    }

    private String buildFilename(String str) {
        return FileUtils.getExternalFilesDir("motion") + File.separator + FileUtils.getBaseName(str) + O3DPConstant.MP4_EXTENSION;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onExecute$0(String str, Object obj, Bundle bundle) {
        Log.d(this.TAG, "delete temp", Boolean.valueOf(FileUtils.delete(str)), "");
    }

    public Intent createIntent(MediaItem mediaItem) {
        Intent intent;
        String path = this.mMediaItem.getPath();
        String buildFilename = buildFilename(path);
        Log.d(this.TAG, "temp", buildFilename);
        FileUtils.delete(buildFilename);
        if (!MotionPhotoUtils.exportVideo(path, buildFilename)) {
            return null;
        }
        if (SeApiCompat.isScreenLocked(getActivity())) {
            intent = new Intent("com.samsung.intent.action.SECURE_LOCK");
            intent.addFlags(8388608);
            intent.putExtra("createBySecureLock", true);
        } else {
            intent = new Intent("android.intent.action.VIEW");
        }
        Uri uri = FileProviderUtil.getUri(AppResources.getAppContext(), buildFilename);
        intent.setDataAndType(uri, "video/*");
        intent.putExtra(OCRServiceConstant.KEY_PARAM_URI, uri);
        boolean isSharing = mediaItem.isSharing();
        String title = mediaItem.getTitle();
        if (!isSharing) {
            title = FileUtils.getBaseName(title);
        }
        intent.putExtra("android.intent.extra.TITLE", title);
        intent.putExtra("from_gallery_to_videoplayer", true);
        addVideoTransitionInfo(intent, this.mMediaItem);
        intent.putExtra(KeywordBundleWrapper.BUNDLE_KEY_CATEGORY, -1);
        intent.setClassName("com.samsung.android.video", "com.samsung.android.video.player.activity.MoviePlayer");
        intent.addFlags(1);
        return intent;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        Runnable runnable;
        this.mActivity = getActivity();
        this.mMediaItem = objArr[0];
        if (Features.isEnabled(Features.SUPPORT_APP_TRANSITION) && (runnable = BlackboardUtils.getAppTransitionCallback(getBlackboard())[0]) != null) {
            runnable.run();
        }
        if (eventContext.getContext() != null) {
            String buildFilename = buildFilename(this.mMediaItem.getPath());
            try {
                Intent createIntent = createIntent(this.mMediaItem);
                startActivity(createIntent);
                Log.d(this.TAG, "intent = ", createIntent);
                eventContext.subscribeInstant("lifecycle://on_activity_resume", new d(1, this, buildFilename));
            } catch (Error | Exception e) {
                FileUtils.delete(buildFilename);
                handleFail();
                String str = this.TAG;
                Log.d(str, "play failed. e=" + e.getMessage());
            }
        }
    }

    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (!Features.isEnabled(Features.SUPPORT_APP_TRANSITION)) {
            this.mActivity.overridePendingTransition(0, 0);
        }
    }
}
