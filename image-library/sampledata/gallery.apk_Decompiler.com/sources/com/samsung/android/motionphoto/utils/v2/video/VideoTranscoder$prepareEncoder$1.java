package com.samsung.android.motionphoto.utils.v2.video;

import Ae.c;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import me.x;

@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public /* synthetic */ class VideoTranscoder$prepareEncoder$1 extends h implements c {
    public VideoTranscoder$prepareEncoder$1(Object obj) {
        super(2, obj, VideoEncoderCallback.class, "setEOSHint", "setEOSHint(IJ)V", 0);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), ((Number) obj2).longValue());
        return x.f4917a;
    }

    public final void invoke(int i2, long j2) {
        ((VideoEncoderCallback) this.receiver).setEOSHint(i2, j2);
    }
}
