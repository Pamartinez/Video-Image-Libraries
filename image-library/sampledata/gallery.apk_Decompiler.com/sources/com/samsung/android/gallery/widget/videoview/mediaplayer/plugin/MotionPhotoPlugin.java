package com.samsung.android.gallery.widget.videoview.mediaplayer.plugin;

import A.a;
import O3.b;
import android.media.PlaybackParams;
import c0.C0086a;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayback;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Log;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoPlugin implements IMediaPlayerPlugin {
    private static final boolean SUPPORT_SLOW_MO_WITH_DYNAMIC_VIEW_CONFIGURATION = SdkConfig.atLeast(SdkConfig.SEM.V);
    private MediaPlayback mLastSlowMoViewModePlayback;
    private MediaItem mMediaItem;
    ArrayList<MediaPlayback> mPlayBackList = new ArrayList<>();
    private float mSpeed = 1.0f;
    private MotionPhotoViewMode mViewMode;
    private AtomicInteger playbackPos = new AtomicInteger(0);

    private void changeSpeedForSlowMoViewMode(MediaPlayerCompat mediaPlayerCompat, MediaPlayback mediaPlayback, int i2) {
        String i7 = C0086a.i(i2, "changed speed : ");
        Log.d("MotionPhotoPlugin", i7, this.mSpeed + " -> " + mediaPlayback.speed);
        if (mediaPlayback.speed == 0.25f) {
            mediaPlayerCompat.beginInstantSlowMoPlay();
        } else if (this.mSpeed == 0.25f) {
            mediaPlayerCompat.endInstantSlowMoPlay();
        }
        setSpeed(mediaPlayerCompat, mediaPlayback.speed);
    }

    private ArrayList<MediaPlayback> getPlayBackList(MotionPhotoViewMode motionPhotoViewMode) {
        ArrayList<MediaPlayback> arrayList = new ArrayList<>();
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem == null) {
            return arrayList;
        }
        DetailsData of2 = DetailsData.of(mediaItem);
        ArrayList<MediaPlayback> arrayList2 = of2.motionPhotoPlaybacks;
        if (arrayList2 != null) {
            return arrayList2;
        }
        ArrayList<MediaPlayback> playbackInfo = new MotionPhotoUtils().getPlaybackInfo(new File(this.mMediaItem.getPath()), motionPhotoViewMode);
        of2.motionPhotoPlaybacks = playbackInfo;
        return playbackInfo;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPlaybackSlowMoViewMode$0(MediaPlayerCompat mediaPlayerCompat, MediaPlayerCompat mediaPlayerCompat2) {
        this.mLastSlowMoViewModePlayback = null;
        this.playbackPos.set(0);
        mediaPlayerCompat.seekTo(0);
        setPlaybackSlowMoViewMode(mediaPlayerCompat);
        mediaPlayerCompat.start();
    }

    private void prepareBoomerangConfiguration(MediaPlayerCompat mediaPlayerCompat) {
        this.mPlayBackList.clear();
        ArrayList<MediaPlayback> playBackList = getPlayBackList(MotionPhotoViewMode.BOOMERANG);
        this.mPlayBackList = playBackList;
        mediaPlayerCompat.setDynamicViewConfiguration(playBackList, true);
    }

    private void prepareSlowMoPlayConfiguration(MediaPlayerCompat mediaPlayerCompat) {
        this.mPlayBackList.clear();
        ArrayList<MediaPlayback> playBackList = getPlayBackList(MotionPhotoViewMode.SLOW_MO);
        this.mPlayBackList = playBackList;
        if (SUPPORT_SLOW_MO_WITH_DYNAMIC_VIEW_CONFIGURATION) {
            mediaPlayerCompat.setDynamicViewConfiguration(playBackList, true);
        } else {
            setPlaybackSlowMoViewMode(mediaPlayerCompat);
        }
    }

    private void setPlaybackSlowMoViewMode(MediaPlayerCompat mediaPlayerCompat) {
        mediaPlayerCompat.setPlaybackRange(0, ((MediaPlayback) C0212a.i(this.mPlayBackList, 1)).endMs);
        Iterator<MediaPlayback> it = this.mPlayBackList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            MediaPlayback next = it.next();
            if (next.speed == 0.25f) {
                mediaPlayerCompat.setDynamicViewConfiguration(new ArrayList(Collections.singletonList(next)), false);
                break;
            }
        }
        mediaPlayerCompat.setPlaybackCompleteListener(new b(28, this, mediaPlayerCompat));
    }

    private void setSpeed(MediaPlayerCompat mediaPlayerCompat, float f) {
        if (mediaPlayerCompat != null) {
            PlaybackParams playbackParam = mediaPlayerCompat.getPlaybackParam();
            playbackParam.setSpeed(f);
            mediaPlayerCompat.setPlaybackParam(playbackParam);
            this.mSpeed = f;
        }
    }

    private void updatePlaybackForSlowMoViewMode(MediaPlayerCompat mediaPlayerCompat, int i2) {
        try {
            if (this.playbackPos.get() < this.mPlayBackList.size()) {
                if (this.mLastSlowMoViewModePlayback == null) {
                    this.mLastSlowMoViewModePlayback = this.mPlayBackList.get(this.playbackPos.get());
                }
                MediaPlayback mediaPlayback = this.mLastSlowMoViewModePlayback;
                if (mediaPlayback.speed != this.mSpeed) {
                    changeSpeedForSlowMoViewMode(mediaPlayerCompat, mediaPlayback, i2);
                } else if (i2 >= mediaPlayback.endMs) {
                    MediaPlayback mediaPlayback2 = this.mPlayBackList.get(this.playbackPos.incrementAndGet());
                    this.mLastSlowMoViewModePlayback = mediaPlayback2;
                    changeSpeedForSlowMoViewMode(mediaPlayerCompat, mediaPlayback2, i2);
                }
            }
        } catch (Exception e) {
            a.s(e, new StringBuilder("updatePlaybackForSlowMoViewMode failed : "), "MotionPhotoPlugin");
        }
    }

    public void close() {
        this.mMediaItem = null;
        this.mSpeed = 1.0f;
        this.mViewMode = MotionPhotoViewMode.ON;
    }

    public void onVideoCompleted(MediaPlayerCompat mediaPlayerCompat) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null && MediaItemUtil.getMotionPhotoViewMode(mediaItem) == MotionPhotoViewMode.SLOW_MO) {
            if (this.mSpeed != 1.0f) {
                setSpeed(mediaPlayerCompat, 1.0f);
            }
            mediaPlayerCompat.endInstantSlowMoPlay();
        }
    }

    public boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        if (i2 != 9000001 || SUPPORT_SLOW_MO_WITH_DYNAMIC_VIEW_CONFIGURATION || this.mViewMode != MotionPhotoViewMode.SLOW_MO) {
            return true;
        }
        updatePlaybackForSlowMoViewMode(mediaPlayerCompat, i7);
        return true;
    }

    public void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
        MediaItem mediaItem = this.mMediaItem;
        if (mediaItem != null) {
            MotionPhotoViewMode motionPhotoViewMode = MediaItemUtil.getMotionPhotoViewMode(mediaItem);
            this.mViewMode = motionPhotoViewMode;
            if (motionPhotoViewMode == MotionPhotoViewMode.BOOMERANG) {
                prepareBoomerangConfiguration(mediaPlayerCompat);
            } else if (motionPhotoViewMode == MotionPhotoViewMode.SLOW_MO) {
                prepareSlowMoPlayConfiguration(mediaPlayerCompat);
            }
        }
    }

    public void open(MediaItem mediaItem) {
        this.mMediaItem = mediaItem;
    }
}
