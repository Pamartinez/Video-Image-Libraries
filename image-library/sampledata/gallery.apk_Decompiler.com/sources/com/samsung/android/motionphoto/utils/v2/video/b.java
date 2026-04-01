package com.samsung.android.motionphoto.utils.v2.video;

import Ae.a;
import android.media.MediaExtractor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements a {
    public final /* synthetic */ MediaExtractor d;
    public final /* synthetic */ int e;

    public /* synthetic */ b(MediaExtractor mediaExtractor, int i2) {
        this.d = mediaExtractor;
        this.e = i2;
    }

    public final Object invoke() {
        return VideoTranscoder.prepareDecoder$lambda$25(this.d, this.e);
    }
}
