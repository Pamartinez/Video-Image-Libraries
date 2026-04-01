package com.samsung.android.gallery.support.library.v0;

import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.samsung.android.gallery.support.library.v0.system.DexInfo;
import com.samsung.android.gallery.support.library.v0.system.DexInfo95;
import com.samsung.android.media.SemHEIFCodec;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Sem95ApiCompatImpl extends Sem90ApiCompatImpl {
    public DexInfo computeDexInfo(Context context) {
        return new DexInfo95().compute(context);
    }

    public Bitmap getThumbnailHeif(String str, BitmapFactory.Options options) {
        try {
            return SemHEIFCodec.getThumbnail(str, options);
        } catch (Exception e) {
            j.C(e, new StringBuilder("getHeifThumbnail(HEIF) failed. e="), this.TAG);
            return null;
        }
    }

    public boolean supportHeif() {
        return true;
    }
}
