package com.samsung.android.gallery.module.media.quramsoft;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface AgifDecoder extends AutoCloseable {
    static AgifDecoder of(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 36) {
            return new AgifDecoderSemImpl(context, uri);
        }
        return new AgifDecoderQrmImpl(context, uri);
    }

    int decodeFrame(Bitmap bitmap);

    int getDelay();

    int getHeight();

    int getNumOfFrame();

    int getWidth();
}
