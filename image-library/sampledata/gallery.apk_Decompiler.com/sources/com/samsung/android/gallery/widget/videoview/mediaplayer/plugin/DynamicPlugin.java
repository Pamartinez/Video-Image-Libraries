package com.samsung.android.gallery.widget.videoview.mediaplayer.plugin;

import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dynamicview.DynamicViewPlayInfo;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DynamicPlugin implements IMediaPlayerPlugin {
    private final DynamicViewPlayInfo.CallBack mDynamicViewPlayCallback = new DynamicViewPlayInfo.CallBack() {
        public boolean loop() {
            return DynamicPlugin.this.mPlaybackLoop;
        }

        public void onPlayComplete() {
            if (DynamicPlugin.this.mParent != null) {
                DynamicPlugin.this.mParent.onVideoCompleted(DynamicPlugin.this.mMediaPlayer);
            }
            DynamicPlugin dynamicPlugin = DynamicPlugin.this;
            dynamicPlugin.onVideoCompleted(dynamicPlugin.mMediaPlayer);
        }
    };
    private MediaItem mMediaItem;
    /* access modifiers changed from: private */
    public MediaPlayerCompat mMediaPlayer;
    /* access modifiers changed from: private */
    public final IMediaPlayerPlugin mParent;
    /* access modifiers changed from: private */
    public boolean mPlaybackLoop = true;

    public DynamicPlugin(IMediaPlayerPlugin iMediaPlayerPlugin) {
        this.mParent = iMediaPlayerPlugin;
    }

    public void close() {
        this.mMediaItem = null;
    }

    public void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
        DynamicViewPlayInfo dynamicViewPlayInfo;
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            dynamicViewPlayInfo = null;
        } else {
            dynamicViewPlayInfo = DynamicViewData.of(mediaItem).dynamicViewPlayInfo;
        }
        if (dynamicViewPlayInfo != null && mediaPlayerCompat != null) {
            this.mMediaPlayer = mediaPlayerCompat;
            mediaPlayerCompat.setDynamicViewConfiguration(dynamicViewPlayInfo.getDynamicViewConfig());
            dynamicViewPlayInfo.initPlayBack(mediaPlayerCompat, this.mDynamicViewPlayCallback, this.mParent.getRenderingPosition());
        }
    }

    public void open(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void setPlaybackLoop(boolean z) {
        this.mPlaybackLoop = z;
    }
}
