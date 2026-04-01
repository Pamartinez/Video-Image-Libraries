package com.samsung.android.gallery.support.library.v12;

import com.samsung.android.gallery.support.library.abstraction.MediaCaptureCompat;
import com.samsung.android.gallery.support.library.v12.media.Sem141MediaCaptureCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem141ApiCompatImpl extends Sem140ApiCompatImpl {
    public MediaCaptureCompat getMediaCaptureCompat() {
        return new Sem141MediaCaptureCompat();
    }
}
