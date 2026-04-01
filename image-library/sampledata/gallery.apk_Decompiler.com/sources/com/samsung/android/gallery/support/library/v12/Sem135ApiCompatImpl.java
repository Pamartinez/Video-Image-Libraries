package com.samsung.android.gallery.support.library.v12;

import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.library.v12.remster.SemRemasterManagerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem135ApiCompatImpl extends Sem131ApiCompatImpl {
    public VslMesDetectorCompat getVslMesDetectorCompat(String str) {
        SemRemasterManagerCompat semRemasterManagerCompat = new SemRemasterManagerCompat();
        if (semRemasterManagerCompat.support()) {
            return semRemasterManagerCompat;
        }
        return super.getVslMesDetectorCompat(str);
    }
}
