package com.samsung.android.motionphoto.utils.v2;

import Ae.b;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ MotionPhotoEditImpl e;
    public final /* synthetic */ ByteBuffer f;

    public /* synthetic */ g(MotionPhotoEditImpl motionPhotoEditImpl, ByteBuffer byteBuffer, int i2) {
        this.d = i2;
        this.e = motionPhotoEditImpl;
        this.f = byteBuffer;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return MotionPhotoEditImpl.replaceVideo$lambda$7(this.e, this.f, (MediaFile) obj);
            default:
                return MotionPhotoEditImpl.replaceMPVDBox$lambda$14(this.e, this.f, (FileChannel) obj);
        }
    }
}
