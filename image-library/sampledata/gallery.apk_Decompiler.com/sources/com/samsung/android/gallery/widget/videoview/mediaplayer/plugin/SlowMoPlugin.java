package com.samsung.android.gallery.widget.videoview.mediaplayer.plugin;

import A.a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SlowMoPlugin implements IMediaPlayerPlugin {
    private final String TAG = getClass().getSimpleName();
    private MediaItem mMediaItem;
    private int mSlowMoEffect;
    private int mSlowMoOffset;
    private boolean mSlowMoRegion;
    private int[] mSlowMoTitleRegion = {0, 0};

    public void close() {
        this.mMediaItem = null;
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        if (i2 == 10974) {
            this.mSlowMoTitleRegion = mediaPlayerCompat.getSuperSlowTitleRegion();
            MediaItem mediaItem = this.mMediaItem;
            if (mediaItem != null) {
                VideoPropData.of(mediaItem).superSlowTitleRegion = this.mSlowMoTitleRegion;
            }
            String str = this.TAG;
            StringBuilder sb2 = new StringBuilder("onVideoInfo SLOWMO_REGION=");
            sb2.append(this.mSlowMoTitleRegion[0]);
            sb2.append(ArcCommonLog.TAG_COMMA);
            a.w(sb2, this.mSlowMoTitleRegion[1], str);
        } else if (i2 == 9000002 && mediaPlayerCompat != null) {
            if (this.mSlowMoRegion) {
                mediaPlayerCompat.setSuperSlowAllRegionEnabled();
            }
            int i8 = this.mSlowMoEffect;
            if (i8 > 0) {
                mediaPlayerCompat.setSuperSlowPlaybackEffect(i8, Math.max(this.mSlowMoTitleRegion[0] - this.mSlowMoOffset, 0));
            }
        }
        return true;
    }

    public void open(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }

    public void setSlowMo(int i2, int i7, boolean z) {
        this.mSlowMoEffect = i2;
        this.mSlowMoOffset = i7;
        this.mSlowMoRegion = z;
    }
}
