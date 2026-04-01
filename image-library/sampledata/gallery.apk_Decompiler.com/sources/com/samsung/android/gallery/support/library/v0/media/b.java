package com.samsung.android.gallery.support.library.v0.media;

import android.media.audiofx.SemSoundAlive;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements SemSoundAlive.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SoundAliveCompat f3151a;

    public /* synthetic */ b(SoundAliveCompat soundAliveCompat) {
        this.f3151a = soundAliveCompat;
    }

    public final void onError() {
        this.f3151a.onError();
    }
}
