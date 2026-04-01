package androidx.room.util;

import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Ljava/nio/channels/ReadableByteChannel;", "input", "Ljava/nio/channels/FileChannel;", "output", "Lme/x;", "copy", "(Ljava/nio/channels/ReadableByteChannel;Ljava/nio/channels/FileChannel;)V", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileUtil {
    public static final void copy(ReadableByteChannel readableByteChannel, FileChannel fileChannel) {
        j.e(readableByteChannel, "input");
        j.e(fileChannel, "output");
        ReadableByteChannel readableByteChannel2 = readableByteChannel;
        FileChannel fileChannel2 = fileChannel;
        try {
            fileChannel2.transferFrom(readableByteChannel2, 0, Long.MAX_VALUE);
            fileChannel2.force(false);
            readableByteChannel2.close();
            fileChannel2.close();
        } catch (Throwable th) {
            Throwable th2 = th;
            readableByteChannel2.close();
            fileChannel2.close();
            throw th2;
        }
    }
}
