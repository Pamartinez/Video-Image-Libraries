package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerAppsTransition2 extends MediaPlayerAppsTransition {
    boolean mAcceptAppTransVideoPlayer = false;

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 10001) {
            return false;
        }
        if (!this.mAcceptAppTransVideoPlayer) {
            Log.d(this.TAG, "skip EVENT_APP_TRANSITION_VIDEO_PLAYER");
            return true;
        }
        Bundle extras = ((Intent) eventMessage.obj).getExtras();
        this.mVideoAppBundle = extras;
        if (extras != null) {
            extras.putString("launch_from", "com.samsung.android.video");
        }
        return true;
    }

    public void registerFinishCallback(Context context) {
        this.mAcceptAppTransVideoPlayer = true;
    }

    public synchronized void unregisterPlayerReceiver(Context context) {
        this.mAcceptAppTransVideoPlayer = false;
        this.mVideoAppBundle = null;
    }
}
