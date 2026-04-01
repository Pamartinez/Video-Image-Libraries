package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioEmojiHandler extends AbsShotModeHandler {
    public boolean checkFile(MediaItem mediaItem) {
        if (mediaItem == null || !support(mediaItem) || !SeApiCompat.getMediaTranscodeCompat().hasAudioStreamInSticker(MediaItemMde.getHiddenFilePath(mediaItem, mediaItem.getPath()))) {
            return false;
        }
        return true;
    }

    public long[] getAudioStreamInfo(String str) {
        return SeApiCompat.getMediaTranscodeCompat().getAudioStreamInfoFromSticker(str);
    }

    public int getTitleId() {
        return R.string.play_sound;
    }

    public boolean isAudioEnabled() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        return mediaItem.isShotMode("sticker");
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
    }
}
