package com.samsung.android.gallery.app.controller.internals;

import K5.a;
import M9.o;
import O3.p;
import android.text.TextUtils;
import com.arcsoft.libarccommon.parameters.ASVLOFFSCREEN;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.UriItemLoader;
import com.samsung.android.gallery.module.longexposure.LongExposureHelper;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.viewer.VuLauncher;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.widget.dialog.AiProgressDialogCompat;
import com.sec.android.gallery3d.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LongExposureCmd extends BaseCommand {
    private boolean mIsAutoSave;
    private final AtomicBoolean mIsCancel = new AtomicBoolean(false);
    private final LongExposureHelper mLongExposureHelper = new LongExposureHelper(new a(19, this));
    private MediaItem mMediaItem;
    private AiProgressDialogCompat mProgressDialog;

    private void autoSave(MediaItem mediaItem) {
        new AutoSaveAsCopyCmd().execute(getHandler(), mediaItem);
        clearCache(mediaItem);
    }

    private void clearCache(MediaItem mediaItem) {
        ThumbnailLoader.getInstance().removeCache(mediaItem);
    }

    private void hideAiProgress() {
        AiProgressDialogCompat aiProgressDialogCompat = this.mProgressDialog;
        if (aiProgressDialogCompat != null) {
            aiProgressDialogCompat.dismissAiProgress();
            this.mProgressDialog = null;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startLongExposure$0() {
        if (this.mLongExposureHelper.startLongExposure(getContext(), this.mMediaItem) != 0) {
            showToast(getContext().getResources().getString(R.string.cant_make_long_exposure));
            hideAiProgress();
            getBlackboard().postEvent(EventMessage.obtain(ASVLOFFSCREEN.ASVL_PAF_DEPTH_U16));
        }
    }

    private void launchViewer(MediaItem mediaItem) {
        new VuLauncher(getBlackboard()).disableTimeline().prepareThumbnail().forcePlayVideoInGallery(PreferenceFeatures.VideoPlayerFeature.SUPPORT_SETTING).publishData().launch("location://LongExposure", 0, mediaItem);
        clearCache(mediaItem);
    }

    /* access modifiers changed from: private */
    public void onCancel() {
        if (!this.mIsCancel.getAndSet(true)) {
            AiProgressDialogCompat aiProgressDialogCompat = this.mProgressDialog;
            if (aiProgressDialogCompat != null) {
                aiProgressDialogCompat.updateMessage(R.string.canceling_long_exposure);
            }
            SimpleThreadPool instance = SimpleThreadPool.getInstance();
            LongExposureHelper longExposureHelper = this.mLongExposureHelper;
            Objects.requireNonNull(longExposureHelper);
            instance.execute(new o(12, longExposureHelper));
        }
    }

    /* access modifiers changed from: private */
    public void onFinishLongExposure(String str) {
        hideAiProgress();
        if (this.mIsCancel.get()) {
            Log.d(this.TAG, "cancel long exposure");
            getBlackboard().postEvent(EventMessage.obtain(ASVLOFFSCREEN.ASVL_PAF_DEPTH_U16));
        } else if (TextUtils.isEmpty(str)) {
            Log.e(this.TAG, "empty path");
            getBlackboard().postEvent(EventMessage.obtain(ASVLOFFSCREEN.ASVL_PAF_DEPTH_U16));
        } else {
            MediaItem longExposureItem = UriItemLoader.getLongExposureItem(getApplicationContext(), str, this.mMediaItem);
            if (this.mIsAutoSave) {
                autoSave(longExposureItem);
            } else {
                launchViewer(longExposureItem);
            }
        }
    }

    private void startLongExposure() {
        SimpleThreadPool.getInstance().execute(new p(this, 1));
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mMediaItem = objArr[0];
        this.mIsAutoSave = objArr[1].booleanValue();
        if (this.mMediaItem == null) {
            Log.e(this.TAG, "item is null. stop LongExposureCmd");
            return;
        }
        AiProgressDialogCompat aiProgressDialogCompat = new AiProgressDialogCompat(getBlackboard());
        this.mProgressDialog = aiProgressDialogCompat;
        aiProgressDialogCompat.showAiProgress(getContext(), R.string.creating_long_exposure, new p(this, 0));
        startLongExposure();
    }
}
