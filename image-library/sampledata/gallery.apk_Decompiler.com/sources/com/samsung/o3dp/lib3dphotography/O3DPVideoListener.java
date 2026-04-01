package com.samsung.o3dp.lib3dphotography;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class O3DPVideoListener extends O3DPListener {
    public abstract void onVideoEncoded(Uri uri);

    public void onProgress(float f) {
    }
}
