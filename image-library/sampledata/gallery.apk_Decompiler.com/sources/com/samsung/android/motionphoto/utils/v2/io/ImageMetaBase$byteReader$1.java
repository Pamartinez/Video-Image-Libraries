package com.samsung.android.motionphoto.utils.v2.io;

import com.samsung.android.motionphoto.utils.v2.MediaFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\u0006\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0006\u0010\u000b¨\u0006\f"}, d2 = {"com/samsung/android/motionphoto/utils/v2/io/ImageMetaBase$byteReader$1", "Lcom/samsung/android/motionphoto/utils/v2/io/MediaByteReader;", "", "position", "length", "", "read", "(JJ)[B", "Ljava/nio/ByteBuffer;", "buffer", "Lme/x;", "(JJLjava/nio/ByteBuffer;)V", "motionphoto_utils_v2.0.17_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ImageMetaBase$byteReader$1 implements MediaByteReader {
    final /* synthetic */ MediaFile $mediaFile;

    public ImageMetaBase$byteReader$1(MediaFile mediaFile) {
        this.$mediaFile = mediaFile;
    }

    /* access modifiers changed from: private */
    public static final int read$lambda$2(long j2, ByteBuffer byteBuffer, FileChannel fileChannel) {
        j.e(fileChannel, "it");
        fileChannel.position(j2);
        return fileChannel.read(byteBuffer);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        B1.a.g(r1, r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] read(long r2, long r4) {
        /*
            r1 = this;
            com.samsung.android.motionphoto.utils.v2.MediaFile r1 = r1.$mediaFile
            java.io.FileInputStream r1 = r1.newInputFileStream()
            java.nio.channels.FileChannel r0 = r1.getChannel()     // Catch:{ all -> 0x0018 }
            r0.position(r2)     // Catch:{ all -> 0x0018 }
            int r2 = (int) r4     // Catch:{ all -> 0x0018 }
            byte[] r2 = new byte[r2]     // Catch:{ all -> 0x0018 }
            r1.read(r2)     // Catch:{ all -> 0x0018 }
            r3 = 0
            B1.a.g(r1, r3)
            return r2
        L_0x0018:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001a }
        L_0x001a:
            r3 = move-exception
            B1.a.g(r1, r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.motionphoto.utils.v2.io.ImageMetaBase$byteReader$1.read(long, long):byte[]");
    }

    public void read(long j2, long j3, ByteBuffer byteBuffer) {
        j.e(byteBuffer, "buffer");
        this.$mediaFile.useInputFileChannel(new b(j2, byteBuffer, 0));
    }
}
