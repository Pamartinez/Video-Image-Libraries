package com.samsung.android.gallery.module.media.quramsoft;

import android.graphics.Bitmap;
import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AgifEncoder extends AutoCloseable {
    static AgifEncoder of(String str) {
        if (Build.VERSION.SDK_INT >= 36) {
            return new AgifEncoderSemImpl(str);
        }
        return new AgifEncoderQrmImpl(str);
    }

    boolean addFrameTP(Bitmap bitmap);

    void setDelay(int i2);

    boolean start(int i2, int i7, int i8);
}
