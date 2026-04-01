package com.samsung.android.sum.core.channel;

import android.media.ImageReader;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class q implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4069a;
    public final /* synthetic */ SurfaceChannelImpl b;

    public /* synthetic */ q(SurfaceChannelImpl surfaceChannelImpl, int i2) {
        this.f4069a = i2;
        this.b = surfaceChannelImpl;
    }

    public final void onImageAvailable(ImageReader imageReader) {
        int i2 = this.f4069a;
        SurfaceChannelImpl surfaceChannelImpl = this.b;
        switch (i2) {
            case 0:
                surfaceChannelImpl.onImageTransit(imageReader);
                return;
            default:
                surfaceChannelImpl.onImageReceive(imageReader);
                return;
        }
    }
}
