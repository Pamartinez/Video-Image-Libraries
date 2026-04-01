package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BecomingNoisyReceiver extends BroadcastReceiver {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private Context mContext;
    private final IMediaPlayerView mMediaPlayerView;

    public BecomingNoisyReceiver(IMediaPlayerView iMediaPlayerView) {
        this.mMediaPlayerView = iMediaPlayerView;
    }

    public void onReceive(Context context, Intent intent) {
        IMediaPlayerView iMediaPlayerView;
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction()) && (iMediaPlayerView = this.mMediaPlayerView) != null) {
            iMediaPlayerView.pauseVideo();
        }
    }

    public void register(Context context) {
        if (this.mContext == null && context != null) {
            Log.d(this.TAG, "BecomingNoisyReceiver registered");
            this.mContext = context;
            AndroidCompat.registerReceiver(context, this, new IntentFilter("android.media.AUDIO_BECOMING_NOISY"));
        }
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }

    public void unregister() {
        AndroidCompat.unregisterReceiver(this.mContext, this);
        this.mContext = null;
    }
}
