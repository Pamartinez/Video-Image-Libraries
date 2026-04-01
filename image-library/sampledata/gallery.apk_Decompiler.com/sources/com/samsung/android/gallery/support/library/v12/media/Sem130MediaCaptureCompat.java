package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import com.samsung.android.gallery.support.library.abstraction.MediaCaptureBgmCompat;
import com.samsung.android.gallery.support.library.v11.media.Sem120MediaCaptureCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem130MediaCaptureCompat extends Sem120MediaCaptureCompat {
    public MediaCaptureBgmCompat createBgmCompat() {
        return new Sem130MediaCaptureBgmCompat();
    }

    public void setAudioVolumeFadeout(int i2, int i7) {
        try {
            this.mMediaCapture.setAudioVolumeFade(2, -1, -1, i2, i7);
        } catch (Exception e) {
            j.D(e, new StringBuilder("setAudioVolumeFade failed. e="), this.TAG);
        }
    }
}
