package com.samsung.android.gallery.module.media.quramsoft;

import android.graphics.Bitmap;
import android.os.Build;
import java.io.InputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AgifMovie {
    static AgifMovie of(String str) {
        return Build.VERSION.SDK_INT >= 36 ? new AgifMovieSemImpl(str) : new AgifMovieQrmImpl(str);
    }

    int decodeFrame(Bitmap bitmap);

    boolean finish();

    int getDelay();

    int getDuration();

    int getFrameCount();

    int getFrameInterval() {
        int delay = getDelay();
        if (delay <= 10) {
            return 100;
        }
        return delay;
    }

    int getHeight();

    int getNumOfFrame();

    int getWidth();

    static AgifMovie of(InputStream inputStream) {
        return Build.VERSION.SDK_INT >= 36 ? new AgifMovieSemImpl(inputStream) : new AgifMovieQrmImpl(inputStream);
    }

    static AgifMovie of(byte[] bArr, int i2) {
        return Build.VERSION.SDK_INT >= 36 ? new AgifMovieSemImpl(bArr, i2) : new AgifMovieQrmImpl(bArr, i2);
    }
}
