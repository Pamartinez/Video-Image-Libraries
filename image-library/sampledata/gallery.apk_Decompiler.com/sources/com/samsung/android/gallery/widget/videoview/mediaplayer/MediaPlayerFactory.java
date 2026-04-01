package com.samsung.android.gallery.widget.videoview.mediaplayer;

import com.samsung.android.gallery.module.data.DynamicViewData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaPlayerFactory {
    static final int ALIVE_SOUND;
    static final int CONFIG;
    static final int REAL_SEEK_MODE;
    public static final boolean SUPPORT_AUDIO_ERASER_IN_GALLERY = Features.isEnabled(Features.SUPPORT_AUDIO_ERASER_IN_GALLERY);
    public static final boolean SUPPORT_AUDIO_FADE_OUT_3D_EFFECT;

    static {
        int i2;
        boolean isEnabled = Features.isEnabled(Features.SUPPORT_AUDIO_FADE_OUT);
        SUPPORT_AUDIO_FADE_OUT_3D_EFFECT = isEnabled;
        int i7 = 0;
        if (PreferenceFeatures.isEnabled(PreferenceFeatures.PhotoStrip41)) {
            i2 = 2;
        } else {
            i2 = 0;
        }
        REAL_SEEK_MODE = i2;
        if (isEnabled) {
            i7 = 4;
        }
        ALIVE_SOUND = i7;
        CONFIG = i2 | i7;
    }

    public static MediaPlayerCompat createBgmAudioPlayer() {
        return SeApiCompat.createSecBgmAudioPlayer(CONFIG);
    }

    public static MediaPlayerCompat createBgmVideoPlayer() {
        return SeApiCompat.createSecBgmVideoPlayer(CONFIG);
    }

    public static MediaPlayerCompat createMediaPlayer(boolean z) {
        if (z) {
            return SeApiCompat.createSecMediaPlayer(CONFIG);
        }
        return SeApiCompat.createMediaPlayer(CONFIG);
    }

    public static MediaPlayerCompat createMediaPlayerCompat(MediaItem mediaItem, boolean z) {
        if (z) {
            return SeApiCompat.createMediaPlayer(CONFIG);
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL && DynamicViewData.hasPlayInfo(mediaItem)) {
            return SeApiCompat.createSecBgmVideoPlayer(CONFIG);
        }
        if (SUPPORT_AUDIO_ERASER_IN_GALLERY) {
            return SeApiCompat.createSecMediaPlayer(CONFIG);
        }
        if (secMediaPlayerItem(mediaItem)) {
            return SeApiCompat.createSecMediaPlayer(CONFIG);
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || !InstantSlowMoUtils.supportInstantSlowMoPlay(mediaItem)) {
            return SeApiCompat.createMediaPlayer(CONFIG);
        }
        return SeApiCompat.createSecMediaPlayer(CONFIG);
    }

    public static boolean preferSecPlayer(MediaItem mediaItem) {
        if (MediaItemUtil.isSamsungPrivilegedVideo(mediaItem)) {
            return true;
        }
        if (!PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO || !InstantSlowMoUtils.supportInstantSlowMoPlay(mediaItem)) {
            return false;
        }
        return true;
    }

    private static boolean secMediaPlayerItem(MediaItem mediaItem) {
        if (MediaItemUtil.isSamsungPrivilegedVideo(mediaItem) || mediaItem.isMotionPhoto() || mediaItem.isStories() || mediaItem.getVideoThumbStartTime() > 0) {
            return true;
        }
        return false;
    }
}
