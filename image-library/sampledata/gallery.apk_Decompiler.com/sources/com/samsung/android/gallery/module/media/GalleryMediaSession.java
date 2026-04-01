package com.samsung.android.gallery.module.media;

import A.a;
import B4.c;
import C3.i;
import F9.b;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class GalleryMediaSession {
    static volatile MediaSession sMediaSession;

    public static void createMediaSession(MediaSession.Callback callback, boolean z) {
        ThreadUtil.postOnMediaPlayThread(new c(z, (Object) callback, 7));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$createMediaSession$0(boolean z, MediaSession.Callback callback) {
        if (sMediaSession == null) {
            if (z) {
                refreshMediaSessionFocus();
            }
            try {
                sMediaSession = new MediaSession(AppResources.getAppContext(), "com.sec.android.gallery3d");
                sMediaSession.setCallback(callback);
                sMediaSession.setActive(true);
            } catch (Exception e) {
                a.s(e, new StringBuilder("createMediaSession failed. e="), "GalleryMediaSession");
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$releaseMediaSession$2() {
        if (sMediaSession != null) {
            sMediaSession.setCallback((MediaSession.Callback) null);
            sMediaSession.release();
            sMediaSession = null;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$setPlaybackState$1(int i2, long j2) {
        if (sMediaSession != null) {
            sMediaSession.setPlaybackState(new PlaybackState.Builder().setState(i2, j2, 1.0f).build());
        }
    }

    private static void refreshMediaSessionFocus() {
        try {
            AudioTrack build = new AudioTrack.Builder().setAudioFormat(new AudioFormat.Builder().setEncoding(1).setSampleRate(48000).setChannelMask(1).build()).build();
            build.play();
            build.release();
        } catch (Exception e) {
            Log.d("GalleryMediaSession", "refreshMediaSessionFocus failed. e=" + e.getMessage());
        }
    }

    public static void releaseMediaSession() {
        ThreadUtil.postOnMediaPlayThread(new i(4));
    }

    public static void setPlaybackState(int i2, long j2) {
        ThreadUtil.postOnMediaPlayThread(new b(i2, j2));
    }
}
