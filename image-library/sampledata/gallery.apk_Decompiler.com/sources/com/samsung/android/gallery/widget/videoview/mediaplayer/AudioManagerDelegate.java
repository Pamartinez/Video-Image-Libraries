package com.samsung.android.gallery.widget.videoview.mediaplayer;

import com.samsung.android.gallery.module.media.AudioManagerHelper;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AudioManagerDelegate implements AudioManagerHelper.OnAudioFocusManagerListener {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final AudioManagerHelper mAudioManager = new AudioManagerHelper(AppResources.getAppContext(), this);
    private final ArrayList<MediaPlayerListener> mMediaPlayerListener = new ArrayList<>();

    public void addMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        if (!this.mMediaPlayerListener.contains(mediaPlayerListener)) {
            this.mMediaPlayerListener.add(mediaPlayerListener);
        }
    }

    public void close() {
        setAudioFocusEnabled(false);
    }

    public boolean isIncomingCall() {
        return "com.android.server.telecom".equals(this.mAudioManager.getAudioFocusedPackageName());
    }

    public void onAudioDucked(boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "onAudioDucked{" + z + "}");
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onAudioDucked(z);
        }
    }

    public void onAudioEnabled(boolean z) {
        StringCompat stringCompat = this.TAG;
        Log.v(stringCompat, "onAudioEnabled{" + z + "}");
        Iterator<MediaPlayerListener> it = this.mMediaPlayerListener.iterator();
        while (it.hasNext()) {
            it.next().onAudioFocusChanged(z);
        }
    }

    public void removeMediaPlayerListener(MediaPlayerListener mediaPlayerListener) {
        this.mMediaPlayerListener.remove(mediaPlayerListener);
    }

    public void setAudioFocusEnabled(boolean z) {
        this.mAudioManager.setAudioFocusEnabled(z);
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }
}
