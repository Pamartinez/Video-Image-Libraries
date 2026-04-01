package com.samsung.android.gallery.widget.videoview.mediaplayer.plugin;

import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerPluginComposite implements IMediaPlayerPlugin {
    private final IMediaPlayerPlugin mParent;
    private final ArrayList<IMediaPlayerPlugin> mPluginList = new ArrayList<>();

    public MediaPlayerPluginComposite(IMediaPlayerPlugin iMediaPlayerPlugin) {
        this.mParent = iMediaPlayerPlugin;
    }

    private void createPlugin(MediaItem mediaItem) {
        if (!this.mPluginList.isEmpty()) {
            close();
        }
        if (mediaItem.getVideoThumbStartTime() > 0) {
            this.mPluginList.add(new SlowMoPlugin());
        }
        if (DynamicViewData.hasPlayInfo(mediaItem)) {
            this.mPluginList.add(new DynamicPlugin(this.mParent));
        }
        if (mediaItem.isMotionPhoto()) {
            this.mPluginList.add(new MotionPhotoPlugin());
        }
    }

    public void close() {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.mPluginList.clear();
    }

    public void onVideoCompleted(MediaPlayerCompat mediaPlayerCompat) {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().onVideoCompleted(mediaPlayerCompat);
        }
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            z |= it.next().onVideoInfo(mediaPlayerCompat, i2, i7);
        }
        return z;
    }

    public void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().onVideoPrepared(mediaPlayerCompat);
        }
    }

    public void open(MediaItem mediaItem) {
        createPlugin(mediaItem);
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().open(mediaItem);
        }
    }

    public void setPlaybackLoop(boolean z) {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().setPlaybackLoop(z);
        }
    }

    public void setSlowMo(int i2, int i7, boolean z) {
        Iterator<IMediaPlayerPlugin> it = this.mPluginList.iterator();
        while (it.hasNext()) {
            it.next().setSlowMo(i2, i7, z);
        }
    }
}
