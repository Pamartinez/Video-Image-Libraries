package com.samsung.android.gallery.support.library.v12.media;

import N2.j;
import com.samsung.android.media.mediacapture.SemMediaCapture;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem160MediaCaptureCompat extends Sem141MediaCaptureCompat {
    public void setBoomerangConfiguration(int i2, int i7, float f, int i8) {
        try {
            SemMediaCapture semMediaCapture = this.mMediaCapture;
            SemMediaCapture semMediaCapture2 = this.mMediaCapture;
            Objects.requireNonNull(semMediaCapture2);
            semMediaCapture.setBoomerangConfiguration(new SemMediaCapture.BoomerangConfiguration(semMediaCapture2, i2, i7, f, i8));
        } catch (Exception e) {
            j.C(e, new StringBuilder("setBoomerangConfiguration failed. e="), this.TAG);
        }
    }
}
