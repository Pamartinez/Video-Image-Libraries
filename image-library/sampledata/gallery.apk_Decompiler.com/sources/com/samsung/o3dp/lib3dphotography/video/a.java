package com.samsung.o3dp.lib3dphotography.video;

import com.samsung.o3dp.lib3dphotography.video.VideoEncoder;
import java.io.File;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements VideoEncoder.VideoEncoderCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VideoEncoderTask f4214a;
    public final /* synthetic */ byte[] b;

    public /* synthetic */ a(VideoEncoderTask videoEncoderTask, byte[] bArr) {
        this.f4214a = videoEncoderTask;
        this.b = bArr;
    }

    public final void onEncodingComplete(File file) {
        this.f4214a.lambda$new$0(this.b, file);
    }
}
