package com.samsung.android.motionphoto.utils.v2.io;

import com.samsung.android.motionphoto.utils.v2.MediaFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Ae.b {
    public final /* synthetic */ int d;
    public final /* synthetic */ long e;
    public final /* synthetic */ Object f;

    public /* synthetic */ b(long j2, ByteBuffer byteBuffer, int i2) {
        this.d = i2;
        this.e = j2;
        this.f = byteBuffer;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                return Integer.valueOf(ImageMetaBase$byteReader$1.read$lambda$2(this.e, (ByteBuffer) this.f, (FileChannel) obj));
            case 1:
                return JPEGMetaWriter.reserveXMPByCopy$lambda$3(this.e, (ByteBuffer) this.f, (FileChannel) obj);
            default:
                return MediaFile.getMimeTypeOfMediaAt$lambda$11((MediaFile) this.f, this.e, (FileChannel) obj);
        }
    }

    public /* synthetic */ b(MediaFile mediaFile, long j2) {
        this.d = 2;
        this.f = mediaFile;
        this.e = j2;
    }
}
