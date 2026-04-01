package com.samsung.android.gallery.support.library.v0.media;

import N2.j;
import android.media.audiofx.SemSoundAlive;
import android.util.Log;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SoundAliveCompat {
    private SemSoundAlive mSoundAlive;

    public SoundAliveCompat(int i2) {
        try {
            SemSoundAlive semSoundAlive = new SemSoundAlive(0, i2);
            this.mSoundAlive = semSoundAlive;
            semSoundAlive.setErrorListener(new b(this));
        } catch (Exception e) {
            j.D(e, new StringBuilder("construct failed. e="), "SoundAliveCompat");
            this.mSoundAlive = null;
        }
    }

    /* access modifiers changed from: private */
    public void onError() {
        Log.e("SoundAliveCompat", "onError");
        release();
    }

    public void release() {
        SemSoundAlive semSoundAlive = this.mSoundAlive;
        if (semSoundAlive != null) {
            try {
                semSoundAlive.setErrorListener((SemSoundAlive.OnErrorListener) null);
                this.mSoundAlive.set3dEffectPosition(false, MapUtil.INVALID_LOCATION);
                this.mSoundAlive.release();
            } catch (Exception e) {
                j.D(e, new StringBuilder("release failed e="), "SoundAliveCompat");
            }
            this.mSoundAlive = null;
        }
    }

    public void set3dEffectPosition(double d) {
        SemSoundAlive semSoundAlive = this.mSoundAlive;
        if (semSoundAlive != null && d != MapUtil.INVALID_LOCATION) {
            try {
                semSoundAlive.set3dEffectPosition(true, d);
            } catch (Exception e) {
                j.D(e, new StringBuilder("set3dEffectPosition failed e="), "SoundAliveCompat");
                release();
            }
        }
    }
}
