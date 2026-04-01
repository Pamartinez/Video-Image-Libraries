package com.samsung.android.motionphoto.utils.v2.video;

import android.media.ImageReader;
import com.samsung.android.sum.core.channel.StapleSurfaceRWChannel;
import com.samsung.android.sum.core.channel.StapleSurfaceReadChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ImageReader.OnImageAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3254a;
    public final /* synthetic */ Object b;

    public /* synthetic */ a(int i2, Object obj) {
        this.f3254a = i2;
        this.b = obj;
    }

    public final void onImageAvailable(ImageReader imageReader) {
        int i2 = this.f3254a;
        Object obj = this.b;
        switch (i2) {
            case 0:
                ((VideoTranscoder) obj).onDecodedImageAvailable(imageReader);
                return;
            case 1:
                ((StapleSurfaceRWChannel) obj).onImageReceived(imageReader);
                return;
            default:
                ((StapleSurfaceReadChannel) obj).onImageReceived(imageReader);
                return;
        }
    }
}
