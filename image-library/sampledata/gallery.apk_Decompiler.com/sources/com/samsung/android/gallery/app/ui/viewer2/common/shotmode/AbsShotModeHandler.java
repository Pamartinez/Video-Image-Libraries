package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestDownloadImageCmd;
import com.samsung.android.gallery.app.controller.sharing.RequestSharedAlbumCmd;
import com.samsung.android.gallery.app.controller.sharing.request.RequestCmdType;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemSuggest;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.module.viewer.ImageAudioHelper;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.chain.Chain;
import com.samsung.android.gallery.support.utils.chain.ChainBuilder;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AbsShotModeHandler implements Chain<AbsShotModeHandler>, ImageAudioHelper.AudioStreamInterface {
    protected final String TAG = getClass().getSimpleName();
    public boolean mDualButton;
    private AbsShotModeHandler mNext;
    public boolean mScaleToMin = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageFileHandlerHolder {
        static final AbsShotModeHandler handler = ((AbsShotModeHandler) new ChainBuilder().append(new AudioEmojiHandler()).build());
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ImageHandlerHolder {
        static final AbsShotModeHandler handler;

        static {
            ChainBuilder append = new ChainBuilder().append(new DualShotHandler()).append(new MotionPhotoHandler()).append(new SelectiveFocusHandler()).append(new Image360ShotHandler()).append(new VirtualShotHandler()).append(new Xmp360PhotoHandler()).append(new UrlLinkHandler()).append(new PathLinkHandler()).append(new AppLinkHandler()).append(new SharingGifHandler()).append(new LiveEffectHandler());
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                append.append(new PanoramaShotHandler());
            } else {
                append.append(new MotionPanoramaHandler());
            }
            if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS) {
                append.append(new LiveFocusOnlyHandler()).append(new SelfieFocusHandler());
            }
            if (PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO) {
                append.append(new XmpMotionPhotoHandler());
            }
            handler = (AbsShotModeHandler) append.build();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class JpegFileHandlerHolder {
        static final AbsShotModeHandler handler;

        static {
            ChainBuilder append = new ChainBuilder().append(new Xmp360PhotoHandler());
            if (PreferenceFeatures.OneUi30.SUPPORT_GOOGLE_MOTION_PHOTO) {
                append.append(new XmpMotionPhotoHandler());
            }
            handler = (AbsShotModeHandler) append.build();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class VideoHandlerHolder {
        static final AbsShotModeHandler handler = ((AbsShotModeHandler) new ChainBuilder().append(new FastMotionVideoHandler()).append(new HyperLapseVideoHandler()).append(new SlowMotionHandler()).append(new SuperSlowMotionHandler()).append(new Video360ShotHandler()).append(new GenericVideoHandler()).build());
    }

    public static AbsShotModeHandler get(MediaItem mediaItem) {
        if (mediaItem.isRevitalization() && !TextUtils.isEmpty(MediaItemSuggest.getOriginPath(mediaItem))) {
            return null;
        }
        if (mediaItem.isVideo()) {
            return VideoHandlerHolder.handler.getInternal(mediaItem);
        }
        if (mediaItem.isImage()) {
            return ImageHandlerHolder.handler.getInternal(mediaItem);
        }
        return null;
    }

    public static AbsShotModeHandler getFromImageFile(MediaItem mediaItem) {
        return ImageFileHandlerHolder.handler.getInternalFromFile(mediaItem);
    }

    public static AbsShotModeHandler getFromJpegFile(MediaItem mediaItem) {
        return JpegFileHandlerHolder.handler.getInternalFromFile(mediaItem);
    }

    public static AbsShotModeHandler getFromScreenShot(MediaItem mediaItem) {
        ScreenShotHandler screenShotHandler = new ScreenShotHandler();
        if (!screenShotHandler.support(mediaItem)) {
            return null;
        }
        AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString(), AnalyticsEventId.EVENT_DETAIL_VIEW_ADD_TO_WALLET_DISPLAY.toString(), screenShotHandler.getClassification());
        return screenShotHandler;
    }

    private AbsShotModeHandler getInternal(MediaItem mediaItem) {
        if (support(mediaItem)) {
            return this;
        }
        AbsShotModeHandler absShotModeHandler = this.mNext;
        if (absShotModeHandler != null) {
            return absShotModeHandler.getInternal(mediaItem);
        }
        return null;
    }

    private AbsShotModeHandler getInternalFromFile(MediaItem mediaItem) {
        if (checkFile(mediaItem)) {
            return this;
        }
        AbsShotModeHandler absShotModeHandler = this.mNext;
        if (absShotModeHandler != null) {
            return absShotModeHandler.getInternalFromFile(mediaItem);
        }
        return null;
    }

    private boolean isUpsm() {
        return Features.isEnabled(Features.IS_UPSM);
    }

    private boolean supportDownloadMotionEditor() {
        return Features.isEnabled(Features.SUPPORT_DOWNLOAD_SLOW_FAST_MOTION);
    }

    public void assertSharingVideo(MediaItem mediaItem) {
        String str = this.TAG;
        Log.e(str, "executeInternal not permitted " + mediaItem);
    }

    public boolean checkFile(MediaItem mediaItem) {
        return false;
    }

    public final boolean execute(EventContext eventContext, MediaItem mediaItem) {
        boolean z;
        if (supportUpsm() || !isUpsm()) {
            if (mediaItem == null || !mediaItem.isSharing() || supportExecuteInSharing()) {
                executeInternal(eventContext, mediaItem);
                z = true;
            } else {
                if (mediaItem.isImage()) {
                    new RequestDownloadImageCmd().execute(eventContext, mediaItem, Boolean.FALSE);
                } else if (mediaItem.isVideo()) {
                    new RequestSharedAlbumCmd().execute(eventContext, RequestCmdType.REQUEST_STREAMING_VIDEO, mediaItem);
                } else {
                    Log.e(this.TAG, "execute wrong contents type " + mediaItem);
                    return false;
                }
                z = false;
            }
            if (isEditable()) {
                BlackboardUtils.publishRefreshOnResumeToAllActivities(false);
            }
            return z;
        }
        Toast.makeText(eventContext.getContext(), SeApiCompat.naturalizeText(String.format(eventContext.getContext().getString(R.string.unable_in_max_power_saving), new Object[]{eventContext.getContext().getString(getContentDescriptionId())})), 0).show();
        return false;
    }

    public abstract void executeInternal(EventContext eventContext, MediaItem mediaItem);

    public long[] getAudioStreamInfo(String str) {
        return null;
    }

    public boolean getAudioStreamLooping() {
        return false;
    }

    public int getContentDescriptionId() {
        return getTitleId();
    }

    public Uri getExtraButtonUri() {
        return null;
    }

    public String getExtraTitleString() {
        return null;
    }

    public int getPlayButtonIconId() {
        return 0;
    }

    public abstract int getTitleId();

    public int getTitleId(MediaItem mediaItem) {
        return getTitleId();
    }

    public String getType() {
        return "";
    }

    public boolean isAudioEnabled() {
        return false;
    }

    public boolean isEditable() {
        return false;
    }

    public boolean isEnableToRunCloudOnly() {
        return false;
    }

    public boolean isLockScreen(Blackboard blackboard) {
        if (!LaunchIntent.isFromLockScreen(blackboard) || !SeApiCompat.isScreenLocked(AppResources.getAppContext())) {
            return false;
        }
        return true;
    }

    public boolean isVisible(MediaItem mediaItem) {
        return true;
    }

    public abstract boolean support(MediaItem mediaItem);

    public boolean supportExecuteInSharing() {
        return false;
    }

    public boolean supportMotionEditor() {
        if (!supportDownloadMotionEditor() || isUpsm()) {
            return false;
        }
        return true;
    }

    public boolean supportUpsm() {
        return true;
    }

    public String toString() {
        return this.TAG + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + Integer.toHexString(hashCode()) + "{" + getType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isEditable() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + isAudioEnabled() + "}";
    }

    public void setNext(AbsShotModeHandler absShotModeHandler) {
        this.mNext = absShotModeHandler;
    }

    public void executeExtra(EventContext eventContext, MediaItem mediaItem) {
    }
}
