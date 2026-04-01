package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.dynamicview;

import A2.d;
import A4.C0367b;
import A7.a;
import c4.C0431a;
import com.samsung.android.gallery.app.controller.internals.RemoveHighlightCmd;
import com.samsung.android.gallery.app.controller.internals.ShowSnackBarForViewerCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.module.bgm.BgmUriProviderManager;
import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewParser;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.module.dynamicview.DynamicViewType;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.abstraction.BgmOptions;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.LatchBuilder;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ValueTrigger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicViewPlayerHandler extends MediaViewPlayerHandler {
    private final boolean SUPPORT_BGM_SERVICE = Features.isEnabled(Features.SUPPORT_BGM_SERVICE);
    private final boolean SUPPORT_FRC = SdkConfig.atLeast(SdkConfig.SEM.S_MR1);
    private final Consumer<Boolean> mBgmLoadListener = new C0367b(5, this);
    private final ValueTrigger<Boolean> mBgmLoaded = new ValueTrigger<>(Boolean.FALSE, "mBgmLoaded");
    private final BgmUriProviderManager mBgmManager = BgmUriProviderManager.getInstance();
    private BgmOptions mBgmOptions = null;

    private void createDynamicViewPlayInfo(MediaItem mediaItem) {
        if (mediaItem != null) {
            DynamicViewData of2 = DynamicViewData.of(mediaItem);
            DynamicViewPlayInfo dynamicViewPlayInfo = of2.dynamicViewPlayInfo;
            if (dynamicViewPlayInfo == null) {
                DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(of2.dynamicViewInfo).setFileDuration(mediaItem.getFileDuration()).setPlayType(DynamicViewType.getSuggestionsType(mediaItem.getFileDuration())).build();
                of2.dynamicViewPlayInfo = build;
                mediaItem.setTitle(build.getTypeString());
                mediaItem.setVideoHighlightTime(Long.valueOf(build.getVideoThumbStartTime()), (Long) null);
                if (build.getSize() <= 0) {
                    this.mThread.runOnBgThread(new d(6, this, mediaItem));
                    return;
                }
                return;
            }
            mediaItem.setTitle(dynamicViewPlayInfo.getTypeString());
        }
    }

    private boolean isAudioPlayable(MediaItem mediaItem) {
        if (!this.SUPPORT_BGM_SERVICE || !((Boolean) Optional.ofNullable(DynamicViewData.of(mediaItem).dynamicViewPlayInfo).map(new C0431a(1)).orElse(Boolean.FALSE)).booleanValue()) {
            return false;
        }
        return true;
    }

    private boolean isHighlightViewer(String str) {
        return LocationKey.isHighLightPictures(str);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onBgmLoaded$1() {
        this.mBgmOptions = prepareBgm();
        this.mBgmLoaded.set(Boolean.TRUE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPrepared$3() {
        int i2 = 0;
        while (i2 < 10) {
            try {
                if (!this.mBgmManager.isLoaded()) {
                    Thread.sleep(50);
                    i2++;
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playVideo$2() {
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            this.mMediaPlayerView.setBgm(bgmOptions);
        }
        this.mMediaPlayerView.play();
    }

    /* access modifiers changed from: private */
    public void onBgmLoaded(boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onBgmLoaded : " + z);
        if (z) {
            this.mThread.runOnBgThread(new a(this, 2));
        }
    }

    private void onSuccessMediaCapture(EventMessage eventMessage) {
        new ShowSnackBarForViewerCmd().execute(this.mModel.getContainerModel().getEventContext(), eventMessage.obj, null);
        new RemoveHighlightCmd().execute(this.mModel.getContainerModel().getEventContext(), new MediaItem[]{this.mModel.getMediaItem()}, Boolean.FALSE);
    }

    private BgmOptions prepareBgm() {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || !isAudioPlayable(mediaItem) || (dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo) == null) {
            return null;
        }
        int duration = dynamicViewPlayInfo.getDuration();
        int type = dynamicViewPlayInfo.getType();
        return this.mBgmManager.createBgmOptions(duration, mediaItem.getFileDuration(), type);
    }

    /* access modifiers changed from: private */
    /* renamed from: recreateDynamicViewPlayInfo */
    public void lambda$createDynamicViewPlayInfo$0(MediaItem mediaItem) {
        long currentTimeMillis = System.currentTimeMillis();
        int suggestionsType = DynamicViewType.getSuggestionsType(mediaItem.getFileDuration());
        int videoDuration = MediaHelper.getVideoDuration(mediaItem.getPath());
        if (videoDuration <= 0) {
            videoDuration = mediaItem.getFileDuration();
        }
        DynamicViewData of2 = DynamicViewData.of(mediaItem);
        DynamicViewInfo parse = DynamicViewParser.parse(of2.dynamicViewBlob, videoDuration, true);
        DynamicViewPlayInfo build = new DynamicViewInfo.PlayInfoBuilder(parse).setFileDuration(mediaItem.getFileDuration()).setPlayType(suggestionsType).build();
        of2.dynamicViewInfo = parse;
        of2.dynamicViewPlayInfo = build;
        mediaItem.setTitle(build.getTypeString());
        mediaItem.setVideoHighlightTime(Long.valueOf(build.getVideoThumbStartTime()), (Long) null);
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "recreateDynamicViewPlayInfo {" + build.getSize() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + videoDuration + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.getFileDuration() + "}+" + (System.currentTimeMillis() - currentTimeMillis));
    }

    private void updateToolbarTitle() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            this.mActionInvoker.invoke(ViewerAction.SET_TOOLBAR_TITLE, null, mediaItem.getTitle());
        }
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        switch (eventMessage.what) {
            case 1115:
                onSuccessMediaCapture(eventMessage);
                return true;
            case 1116:
                if (!this.SUPPORT_FRC) {
                    return true;
                }
                stopVideoView();
                StringCompat stringCompat = this.TAG;
                Log.d(stringCompat, "EVENT_ON_MEDIA_CAPTURE_PREPARE{" + this.mModel.getVideoSeekPosition() + "}");
                return true;
            case 1117:
                if (!this.SUPPORT_FRC) {
                    return true;
                }
                playVideo(this.mModel.getMediaItem(), this.mModel.getVideoSeekPosition());
                Log.d(this.TAG, "EVENT_ON_MEDIA_CAPTURE_FINISH");
                return true;
            default:
                return false;
        }
    }

    public boolean isLoopEnabled() {
        return false;
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mBgmLoaded.clear();
        this.mBgmOptions = null;
        if (this.SUPPORT_BGM_SERVICE) {
            this.mBgmManager.registerListener(this.mBgmLoadListener);
            this.mBgmManager.request();
        }
    }

    public void onResume() {
        Log.d(this.TAG, "onResume()");
        this.mPhotoView.setInitialCenterCrop(false);
        if (this.mModel.isViewConfirmed() && !this.mMediaPlayerView.isOpened()) {
            playVideo(this.mModel.getMediaItem(), this.mModel.getVideoSeekPosition());
        }
    }

    public void onVideoPrepared(int i2, int i7) {
        Log.d(this.TAG, "onVideoPrepared");
        if (this.mBgmManager.isLoaded()) {
            this.mBgmOptions = prepareBgm();
            this.mBgmLoaded.set(Boolean.TRUE);
        } else {
            new LatchBuilder("dynamicView").addWorker(new a(this, 1)).setTimeout(500).start();
            if (!this.mBgmManager.isLoaded()) {
                Log.w(this.TAG, "bgm prepare is too slow");
            } else {
                this.mBgmOptions = prepareBgm();
                this.mBgmLoaded.set(Boolean.TRUE);
            }
        }
        BgmOptions bgmOptions = this.mBgmOptions;
        if (bgmOptions != null) {
            this.mMediaPlayerView.setBgm(bgmOptions);
        }
        super.onVideoPrepared(i2, i7);
    }

    public void onViewAttached() {
        if (isHighlightViewer(this.mModel.getContainerModel().getLocationKey())) {
            createDynamicViewPlayInfo(this.mModel.getMediaItem());
        }
        super.onViewAttached();
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateToolbarTitle();
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mBgmOptions = null;
        this.mBgmLoaded.clear();
        if (this.SUPPORT_BGM_SERVICE) {
            this.mBgmManager.unregisterListener(this.mBgmLoadListener);
        }
    }

    public void playVideo(MediaItem mediaItem, int i2) {
        if (openVideo(mediaItem, i2)) {
            this.mBgmLoaded.when(Boolean.TRUE).then(new a(this, 0), "Play By bgm");
        }
    }
}
